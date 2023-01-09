package wannabit.io.cosmostaion.activities

import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ledger.live.ble.app.BleCosmosHelper
import cosmos.base.v1beta1.CoinOuterClass
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.base.chains.Cosmos
import wannabit.io.cosmostaion.base.chains.Osmosis
import wannabit.io.cosmostaion.databinding.ActivityLedgerSelectBinding
import wannabit.io.cosmostaion.databinding.ItemMnemonicAccountListBinding
import wannabit.io.cosmostaion.dialog.CommonAlertDialog
import wannabit.io.cosmostaion.dialog.NumberPickerDialog
import wannabit.io.cosmostaion.dialog.NumberPickerDialog.SelectListener
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.task.TaskResult
import wannabit.io.cosmostaion.task.UserTask.GenerateEmptyAccountTask
import wannabit.io.cosmostaion.task.gRpcTask.BalanceGrpcTask
import wannabit.io.cosmostaion.utils.LedgerManager
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WKey

class LedgerSelectActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLedgerSelectBinding
    private lateinit var adapter: WalletConnectManageAdapter
    private lateinit var items: List<ChainConfig>
    private var selectedList: MutableList<Ledger> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLedgerSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mBaseChain = BaseChain.getChain(mAccount.baseChain)
        mChainConfig = ChainFactory.getChain(mBaseChain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        items = listOf(Cosmos(), Osmosis())
        adapter = WalletConnectManageAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this@LedgerSelectActivity)
        binding.recyclerView.adapter = adapter
        binding.pathLayer.setOnClickListener {
            val numberPicker = NumberPickerDialog.newInstance()
            numberPicker.selectListener = SelectListener { path: Int ->
                binding.path.text = "$path"
                con()
            }
            numberPicker.isCancelable = false
            numberPicker.show(supportFragmentManager, NumberPickerDialog::class.java.name)
        }

        binding.btnDisconnect.setOnClickListener(this)
        binding.btnConnect.setOnClickListener(this)

        con()
    }

    private fun con() {
        LedgerManager.instance.connect(this, object : LedgerManager.ConnectListener {
            override fun error(errorType: LedgerManager.ErrorType) {
            }

            override fun connected() {
                onShowWaitDialog()
                loadAddress()
            }

        })
    }

    private fun loadAddress() {
        BleCosmosHelper.getAddress(LedgerManager.instance.bleManager,
            hrp = mChainConfig.addressPrefix(),
            hdPath = "44'/118'/0'/0/${binding.path.text}",
            listener = object : BleCosmosHelper.GetAddressListener {
                override fun error(code: String, message: String) {
                    runOnUiThread {
                        CommonAlertDialog.showDoubleButton(
                            this@LedgerSelectActivity,
                            "Ledger Error",
                            message,
                            "Cancel",
                            null,
                            "Retry"
                        ) { loadAddress() }
                    }
                }

                override fun success(address: String, pubKey: ByteArray) {
                    LedgerManager.instance.currentAddress = address
                    LedgerManager.instance.currentPubKey = pubKey
                    runOnUiThread { adapter.notifyDataSetChanged() }
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnDisconnect.id -> {
                finish()
            }

            binding.btnConnect.id -> {
                selectedList.forEach {
                    val lederAccount = it
                    baseDao.onSelectAccounts().find { it.address.equals(lederAccount.address, true) }?.let {
                        if (!it.hasPrivateKey) {
                            it.isFavo = true
                            it.path = lederAccount.path
                            baseDao.onUpdateAccount(it)
                            baseDao.setLastUser(it.id)
                            onStartMainActivity(0)
                            finish()
                        }

                    } ?: run {
                        GenerateEmptyAccountTask(baseApplication) {
                            onStartMainActivity(0)
                            finish()
                        }.execute(it.baseChain.chain, it.address, it.path)

                    }
                }
            }
        }
    }

    private inner class WalletConnectManageAdapter : RecyclerView.Adapter<WalletConnectManageAdapter.WalletConnectViewHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WalletConnectViewHolder {
            val binding = ItemMnemonicAccountListBinding.inflate(layoutInflater, viewGroup, false)
            return WalletConnectViewHolder(binding)
        }

        override fun onBindViewHolder(viewHolder: WalletConnectViewHolder, position: Int) {
            val item = items[position]
            LedgerManager.instance.currentAddress?.let {
                val address = WKey.bech32Encode(
                    item.addressPrefix().toByteArray(), WKey.bech32Decode(it).getData()
                )
                viewHolder.binding.chainImg.setImageResource(item.chainImg())
                viewHolder.binding.accountAddress.text = address
                val path = item.defaultPath().replace("X", binding.path.text.toString())
                viewHolder.binding.keyPath.text = path
                loadBalance(viewHolder, item, address)

                baseDao.onSelectAccounts().find { it.address.equals(address, true) }?.let {
                    if (it.hasPrivateKey) {
                        viewHolder.binding.accountState.text = "Imported"
                        viewHolder.binding.accountCard.background = ContextCompat.getDrawable(
                            this@LedgerSelectActivity,
                            R.drawable.box_white2_border)
                        viewHolder.binding.dimLayer.visibility = View.VISIBLE
                        viewHolder.binding.dimLayer.alpha = 0.5f
                    }

                } ?: run {
                    viewHolder.binding.accountCard.background = ContextCompat.getDrawable(
                        this@LedgerSelectActivity,
                        R.drawable.box_account_unselected
                    )
                }

                var selected = false
                viewHolder.binding.accountCard.setOnClickListener {
                    baseDao.onSelectAccounts().find { it.address.equals(address, true) }?.let {
                        if (it.hasPrivateKey) return@let

                    } ?: run {
                        selected = !selected
                        if (selected) {
                            viewHolder.binding.check.visibility = View.VISIBLE
                            viewHolder.binding.accountCard.background = ContextCompat.getDrawable(
                                this@LedgerSelectActivity,
                                R.drawable.box_account_selected_photon)
                            selectedList.add(Ledger(item.baseChain(), address, path))

                        } else {
                            viewHolder.binding.check.visibility = View.GONE
                            viewHolder.binding.accountCard.background = ContextCompat.getDrawable(
                                this@LedgerSelectActivity,
                                R.drawable.box_account_unselected)
                            selectedList.removeAt(position)
                        }
                    }
                }

            } ?: run {
                viewHolder.binding.accountAddress.text = "-"
            }
        }

        fun loadBalance(viewHolder: WalletConnectViewHolder, chainConfig: ChainConfig, address: String) {
            BalanceGrpcTask(baseApplication, { result: TaskResult? ->
                val balances: ArrayList<CoinOuterClass.Coin> = result!!.resultData as ArrayList<CoinOuterClass.Coin>
                if (balances.isNotEmpty()) {
                    balances.forEach {
                        if (it.denom.equals(chainConfig.mainDenom(), true)) {
                            val coin = Coin(it.denom, it.amount)
                            WDp.setDpCoin(
                                this@LedgerSelectActivity,
                                baseDao,
                                chainConfig,
                                coin,
                                viewHolder.binding.accountDenom,
                                viewHolder.binding.accountAvailable)
                            runOnUiThread {
                                onHideWaitDialog()
                            }
                        }
                    }
                }
            }, chainConfig.baseChain(), address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        inner class WalletConnectViewHolder(val binding: ItemMnemonicAccountListBinding) :
            RecyclerView.ViewHolder(binding.root)
    }
}

class Ledger (val baseChain: BaseChain, val address: String, val path: String)
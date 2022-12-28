package wannabit.io.cosmostaion.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ledger.live.ble.app.BleCosmosHelper
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
import wannabit.io.cosmostaion.task.UserTask.GenerateEmptyAccountTask
import wannabit.io.cosmostaion.utils.LedgerManager
import wannabit.io.cosmostaion.utils.WKey

class LedgerSelectActivity : BaseActivity() {
    private lateinit var binding: ActivityLedgerSelectBinding
    private lateinit var adapter: WalletConnectManageAdapter
    private lateinit var items: List<ChainConfig>
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
        items = listOf(Osmosis(), Cosmos())
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
        con()
    }

    private fun con() {
        LedgerManager.instance.connect(this, object : LedgerManager.ConnectListener {
            override fun error(errorType: LedgerManager.ErrorType) {
            }

            override fun connected() {
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

    private inner class WalletConnectManageAdapter :
        RecyclerView.Adapter<WalletConnectManageAdapter.WalletConnectViewHolder>() {
        override fun onCreateViewHolder(
            viewGroup: ViewGroup, viewType: Int
        ): WalletConnectViewHolder {
            val binding = ItemMnemonicAccountListBinding.inflate(layoutInflater, viewGroup, false)
            return WalletConnectViewHolder(binding)
        }

        override fun onBindViewHolder(viewHolder: WalletConnectViewHolder, position: Int) {
            val item = items[position]
            LedgerManager.instance.currentAddress?.let {
                val address = WKey.bech32Encode(
                    item.addressPrefix().toByteArray(), WKey.bech32Decode(it).getData()
                )
                viewHolder.binding.accountAddress.text = address
                val path = item.defaultPath().replace("X", binding.path.text.toString())
                viewHolder.binding.keyPath.text = path
                viewHolder.binding.accountCard.setOnClickListener {
                    baseDao.onSelectAccounts().find { it.address == address }?.let {
                        if (it.hasPrivateKey) {
                            Toast.makeText(
                                this@LedgerSelectActivity,
                                "Privatekey가 있는 계정은 렛져로 사용 불가합니다.",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            it.isFavo = true
                            it.path = path
                            baseDao.onUpdateAccount(it)
                            baseDao.setLastUser(it.id)
                            onStartMainActivity(0)
                            finish()
                        }
                    } ?: run {
                        GenerateEmptyAccountTask(
                            baseApplication
                        ) {
                            onStartMainActivity(0)
                            finish()
                        }.execute(item.baseChain().chain, address, path)
                    }
                }
            } ?: run {
                viewHolder.binding.accountAddress.text = "-"
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }

        inner class WalletConnectViewHolder(val binding: ItemMnemonicAccountListBinding) :
            RecyclerView.ViewHolder(binding.root)
    }
}
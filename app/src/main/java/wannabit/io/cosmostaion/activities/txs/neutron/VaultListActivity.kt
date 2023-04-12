package wannabit.io.cosmostaion.activities.txs.neutron

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityVaultListBinding
import wannabit.io.cosmostaion.databinding.ItemVaultListBinding
import wannabit.io.cosmostaion.dialog.PaddedVerticalButtonAlertDialog
import wannabit.io.cosmostaion.utils.visibleOrGone

class VaultListActivity : BaseActivity() {

    private lateinit var binding: ActivityVaultListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaultListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        onSwipeRefresh()
    }

    fun initView() {
        binding.toolbarTitle.text = "Vault List"
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = VaultListAdapter()
    }

    fun onSwipeRefresh() {
        binding.layerRefresher.setOnRefreshListener {

        }
    }

    private fun onUpdateView() {
        binding.apply {
            layerRefresher.isRefreshing = false
            onHideWaitDialog()
            binding.recycler.adapter?.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private inner class VaultListAdapter : RecyclerView.Adapter<VaultListAdapter.VaultHolder>() {

        var isMy = false

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaultListAdapter.VaultHolder {
            val binding = ItemVaultListBinding.inflate(layoutInflater, parent, false)
            return VaultHolder(binding)
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(holder: VaultHolder, position: Int) {
            holder.bind(position)
        }

        inner class VaultHolder(val vaultBinding: ItemVaultListBinding) : ViewHolder(vaultBinding.root) {
            fun bind(position: Int) {
                if (position == 0) isMy = true
                else isMy = false

                vaultBinding.apply {
                    if (isMy) {
                        cardRoot.setCardBackgroundColor(ContextCompat.getColor(this@VaultListActivity, mChainConfig.chainBgColor()))
                    } else {
                        cardRoot.setCardBackgroundColor(ContextCompat.getColor(this@VaultListActivity, R.color.colorTransBg))
                    }
                    view2.visibleOrGone(!isMy)
                    myAvailableTitle.visibleOrGone(!isMy)
                    myAvailable.visibleOrGone(!isMy)

                    cardRoot.setOnClickListener {
//                        PaddedVerticalButtonAlertDialog.showDoubleButton(this@VaultListActivity, null, null,
//                            getString(R.string.str_title_pool_join), { view: View? -> onCheckStartJoinPool(mPool) },
//                            getString(R.string.str_title_pool_exit), { view: View? -> onCheckStartExitPool(mPool, mDeposit) })
                    }
                }
            }
        }
    }
}
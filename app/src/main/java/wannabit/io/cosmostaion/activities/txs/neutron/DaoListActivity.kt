package wannabit.io.cosmostaion.activities.txs.neutron

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityDaoListBinding
import wannabit.io.cosmostaion.databinding.ItemDaoBinding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.network.res.neutron.ResDaoData
import wannabit.io.cosmostaion.utils.WDp
import java.math.BigDecimal

class DaoListActivity : BaseActivity() {

    private lateinit var binding: ActivityDaoListBinding

    private val neutronViewModel: NeutronViewModel by viewModels()

    private var mDaoList = mutableListOf<ResDaoData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        onSwipeRefresh()
        onShowWaitDialog()
    }

    fun initView() {
        with(binding) {
            mAccount = baseDao.onSelectAccount(baseDao.lastUser)
            mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            recycler.layoutManager = LinearLayoutManager(this@DaoListActivity)
            recycler.adapter = DaoListAdapter()

            neutronViewModel.loadDaoData(mChainConfig)
            loadDataObserve()
        }
    }

    private fun onSwipeRefresh() {
        binding.layerRefresher.setOnRefreshListener {
            loadDataObserve()
        }
    }

    private fun onUpdateView() {
        onHideWaitDialog()
        with(binding) {
            layerRefresher.isRefreshing = false
            recycler.adapter?.notifyDataSetChanged()
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

    private fun loadDataObserve() {
        neutronViewModel.daoData.observe(this) { response ->
            response?.let {
                mDaoList = response as MutableList<ResDaoData>
                onUpdateView()
            } ?: run {
                // github disconncet
            }
        }
    }

    private inner class DaoListAdapter : RecyclerView.Adapter<DaoListAdapter.DaoHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaoListAdapter.DaoHolder {
            val binding = ItemDaoBinding.inflate(layoutInflater, parent, false)
            return DaoHolder(binding)
        }

        override fun onBindViewHolder(holder: DaoHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return mDaoList.size
        }

        inner class DaoHolder(val mainBinding: ItemDaoBinding) : ViewHolder(mainBinding.root) {
            fun bind(position: Int) {
                with(mainBinding) {
                    when(position) {
                        0 -> {
                            cardRoot.setCardBackgroundColor(ContextCompat.getColor(this@DaoListActivity, mChainConfig.chainBgColor()))
                            daoImg.setImageDrawable(ContextCompat.getDrawable(this@DaoListActivity, R.drawable.icon_main_dao))
                        }
                        else -> {
                            cardRoot.setCardBackgroundColor(ContextCompat.getColor(this@DaoListActivity, R.color.colorTransBg))
                            daoImg.setImageDrawable(ContextCompat.getDrawable(this@DaoListActivity, R.drawable.icon_sub_dao))
                            view2.visibility = View.GONE
                            myVotingPowerTitle.visibility = View.GONE
                            myVotingPower.visibility = View.GONE
                        }
                    }
                    val daoInfo = mDaoList[position]
                    neutronViewModel.daoData.value?.let {
                        daoName.text = daoInfo.name?.uppercase()
                        daoDescription.text = daoInfo.description?.capitalize()
                        moduleCount.text = daoInfo.proposal_modules.size.toString()
                        daoUrl.text = daoInfo.dao_uri
                    }
                    myVotingPower.text = WDp.getDpAmount2(BigDecimal(baseDao.mVaultAmount), mChainConfig.decimal(), mChainConfig.decimal())

                    cardRoot.setOnClickListener {
                        Intent(this@DaoListActivity, DaoProposalListActivity::class.java).apply {
                            putExtra("contractAddress", daoInfo.address)
                            startActivity(this)
                        }
                    }
                }
            }
        }
    }
}
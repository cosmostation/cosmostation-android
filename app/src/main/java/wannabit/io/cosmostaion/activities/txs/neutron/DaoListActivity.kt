package wannabit.io.cosmostaion.activities.txs.neutron

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityDaoListBinding
import wannabit.io.cosmostaion.databinding.ItemDaoBinding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.network.res.neutron.ResDaoData

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
            daoCount.text = mDaoList.size.toString()
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
                neutronViewModel.loadMainDaoData(mChainConfig)
            }
        }

        neutronViewModel.data.observe(this) { response ->
            response?.let {
                Gson().fromJson(it[0].toString(), ResDaoData::class.java).let { data ->
                    mDaoList.add(data)
                }
                onUpdateView()
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
                            daoImg.setImageDrawable(ContextCompat.getDrawable(this@DaoListActivity, R.drawable.icon_main_vault))
                        }
                        else -> {
                            cardRoot.setCardBackgroundColor(ContextCompat.getColor(this@DaoListActivity, R.color.colorTransBg))
                            daoImg.setImageDrawable(ContextCompat.getDrawable(this@DaoListActivity, R.drawable.icon_sub_vault))
                        }
                    }
                    val daoInfo = mDaoList[position]
                    daoName.text = daoInfo.name?.uppercase()
                    daoDescription.text = daoInfo.description?.capitalize()

                    cardRoot.setOnClickListener {
                        Intent(this@DaoListActivity, DaoProposalListActivity::class.java).apply {
                            startActivity(this)
                        }
                    }
                }
            }
        }
    }
}
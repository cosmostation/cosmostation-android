package wannabit.io.cosmostaion.activities.txs.neutron

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityDaoListBinding
import wannabit.io.cosmostaion.databinding.ItemVoteInfoBinding
import wannabit.io.cosmostaion.databinding.ItemVoteTallyInfoBinding

class DaoListActivity : BaseActivity() {

    private lateinit var binding: ActivityDaoListBinding

    private var mItems: MutableList<String> = mutableListOf("Sub1Dao", "Sub2Dao", "Sub3Dao")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        onSwipeRefresh()
    }

    fun initView() {
        binding.toolbarTitle.text = "Dao List"
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = DaoListAdapter()
    }

    fun onSwipeRefresh() {
        binding.layerRefresher.setOnRefreshListener {

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

    private inner class DaoListAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            return when (viewType) {
//                DaoViewType.TYPE_MAIN_DAO.ordinal -> DaoMainHolder(ItemVoteInfoBinding.inflate(layoutInflater, parent, false))
//                else -> DaoSubHolder(ItemVoteMessageBinding.inflate(layoutInflater, parent, false))
//            }
            return DaoMainHolder(ItemVoteInfoBinding.inflate(layoutInflater, parent, false))
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            when (position) {
                DaoViewType.TYPE_MAIN_DAO.ordinal -> (viewHolder as DaoMainHolder).bind()
                else -> (viewHolder as DaoSubHolder).bind(position - 1)
            }
        }

        override fun getItemCount(): Int {
            return 1 + mItems.size
        }

        override fun getItemViewType(position: Int): Int {
            return when (position) {
                0 -> DaoViewType.TYPE_MAIN_DAO.ordinal
                else -> DaoViewType.TYPE_SUB_DAO.ordinal
            }
        }

        inner class DaoMainHolder(val voteInfoBinding: ItemVoteInfoBinding) : ViewHolder(voteInfoBinding.root) {
            fun bind() {
                voteInfoBinding.apply {

                }
            }
        }

        inner class DaoSubHolder(val voteTallyInfoBinding: ItemVoteTallyInfoBinding) : ViewHolder(voteTallyInfoBinding.root) {
            fun bind(position: Int) {
                voteTallyInfoBinding.apply {
                }
            }
        }
    }

    enum class DaoViewType() { TYPE_MAIN_DAO, TYPE_SUB_DAO }
}
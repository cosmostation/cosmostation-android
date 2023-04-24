package wannabit.io.cosmostaion.activities.txs.neutron

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.common.collect.Sets
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.databinding.ActivityDaoProposalListBinding
import wannabit.io.cosmostaion.databinding.ItemDaoProposalListBinding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.network.res.neutron.ResProposalData
import wannabit.io.cosmostaion.utils.WDp

class DaoProposalListActivity : BaseActivity() {

    private lateinit var binding: ActivityDaoProposalListBinding

    private val neutronViewModel: NeutronViewModel by viewModels()

    private var mProposalPeriodList = listOf<ProposalData?>()
    private var mProposalList = listOf<ProposalData?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoProposalListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        onSwipeRefresh()
        onShowWaitDialog()
    }

    fun initView() {
        binding.toolbarTitle.text = "Proposal List"
        mAccount = baseDao.onSelectAccount(baseDao.lastUser)
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain))

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = ProposalListAdapter()
        binding.recycler.addItemDecoration(proposalHeaderRecyclerView(this, true, getSectionCall()))

        neutronViewModel.loadDaoProposalListData(mChainConfig)
        loadDataObserve()
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

    private fun loadDataObserve() {
        neutronViewModel.data.observe(this) { response ->
            response?.let { proposals ->
                Gson().fromJson(proposals[0].toString(), ResProposalData::class.java).let { data ->
                    mProposalList = data.proposals.filter { proposal -> "open" != proposal?.proposal?.status }
                    mProposalPeriodList = data.proposals.filter { proposal -> "open" == proposal?.proposal?.status }
                }
                onUpdateView()
            }
        }
    }

    private fun onUpdateView() {
        with(binding) {
            neutronViewModel.data.value?.let {
                onHideWaitDialog()
                layerRefresher.isRefreshing = false
                binding.recycler.adapter?.notifyDataSetChanged()
            }
        }
    }

    private inner class ProposalListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            if (viewType == ProposalViewType.TYPE_PERIOD.ordinal) {
                return ProposalPeriodListHolder(ItemDaoProposalListBinding.inflate(layoutInflater, parent, false))
            } else {
                return ProposalListHolder(ItemDaoProposalListBinding.inflate(layoutInflater, parent, false))
            }
        }

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            if (getItemViewType(position) == ProposalViewType.TYPE_PERIOD.ordinal) {
                (viewHolder as ProposalPeriodListHolder).bind(position)
            } else {
                (viewHolder as ProposalListHolder).bind(position - mProposalPeriodList.size)
            }
        }

        override fun getItemCount(): Int {
            return mProposalPeriodList.size + mProposalList.size
        }

        override fun getItemViewType(position: Int): Int {
            return if (position < mProposalPeriodList.size) {
                ProposalViewType.TYPE_PERIOD.ordinal
            } else {
                ProposalViewType.TYPE_PASSED.ordinal
            }
        }

        inner class ProposalPeriodListHolder(val proposalPeriodBinding: ItemDaoProposalListBinding) : RecyclerView.ViewHolder(proposalPeriodBinding.root) {
            fun bind(position: Int) {
                proposalPeriodBinding.apply {
                    mProposalPeriodList[position]?.let { proposalData ->
                        proposalTitle.text = "# " + proposalData.id + " " + proposalData.proposal?.title
                        proposalExpiration.visibility = View.VISIBLE
                        proposalStatusLayout.visibility = View.GONE
                        proposalData.proposal?.expiration?.at_time?.toLong()?.let { expiration ->
                            proposalExpiration.text = WDp.getDpTime(this@DaoProposalListActivity, expiration.div(1000000)) + " " +
                                    WDp.getGapTime(expiration.div(1000000))
                        }

                        cardRoot.setOnClickListener {
                            Intent(this@DaoProposalListActivity, DaoProposalDetailActivity::class.java).apply {
                                putExtra("proposal_id", proposalData.id)
                                startActivity(this)
                            }
                        }
                    }
                }
            }
        }

        inner class ProposalListHolder(val proposalListBinding: ItemDaoProposalListBinding) : RecyclerView.ViewHolder(proposalListBinding.root) {
            fun bind(position: Int) {
                proposalListBinding.apply {
                    mProposalList[position]?.let { proposalData ->
                        proposalTitle.text = "# " + proposalData.id + " " + proposalData.proposal?.title
                        proposalExpiration.visibility = View.GONE
                        proposalStatusLayout.visibility = View.VISIBLE

                        when(proposalData.proposal?.status) {
                            "executed", "passed" -> proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalListActivity, R.drawable.ic_passed_img))
                            "rejected" -> proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalListActivity, R.drawable.ic_rejected_img))
                            "failed" -> proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalListActivity, R.drawable.ic_failed_img))
                        }
                        proposalStatus.text = proposalData.proposal?.status?.capitalize()

                        cardRoot.setOnClickListener {

                        }
                    }
                }
            }
        }
    }

    enum class ProposalViewType { TYPE_PERIOD, TYPE_PASSED }

    private inner class proposalHeaderRecyclerView(context: Context, private val sticky: Boolean, private val sectionCallback: SectionCallback) : ItemDecoration() {

        private val topPadding: Int
        private var headerView: View? = null
        private var mHeaderTitle: TextView? = null
        private var mItemCnt: TextView? = null

        init { topPadding = dpToPx(context, 34) }

        private fun dpToPx(context: Context, dp: Int): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)
            if (headerView == null) {
                headerView = inflateHeaderView(parent)
                mHeaderTitle = headerView!!.findViewById<View>(R.id.header_title) as TextView
                mItemCnt = headerView!!.findViewById<View>(R.id.recycler_cnt) as TextView
                fixLayoutSize(headerView, parent)
            }

            val headerTitleSet: MutableSet<String> = Sets.newHashSet()
            for (i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                val position = parent.getChildAdapterPosition(child)
                if (position == RecyclerView.NO_POSITION) return

                var title = ""
                val mSection = parent.adapter?.getItemViewType(position)
                if (mSection == ProposalViewType.TYPE_PERIOD.ordinal) {
                    title = sectionCallback.SectionHeader(mProposalPeriodList, mSection)
                    mItemCnt?.text = "" + mProposalPeriodList.size
                } else if (mSection == ProposalViewType.TYPE_PASSED.ordinal) {
                    title = sectionCallback.SectionHeader(mProposalList, mSection)
                    mItemCnt?.text = "" + mProposalList.size
                }

                mHeaderTitle?.text = title
                if (!headerTitleSet.contains(title) || sectionCallback.isSection(position)) {
                    drawHeader(c, child, headerView)
                    headerTitleSet.add(title)
                }
            }
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            val position = parent.getChildAdapterPosition(view)
            if (sectionCallback.isSection(position)) { outRect.top = topPadding }
        }

        private fun drawHeader(c: Canvas, child: View, headerView: View?) {
            c.save()
            if (sticky) {
                c.translate(0f, Math.max(0, child.top - headerView!!.height).toFloat())
            } else {
                c.translate(0f, (child.top - headerView!!.height).toFloat())
            }
            headerView.draw(c)
            c.restore()
        }

        private fun inflateHeaderView(parent: RecyclerView): View {
            return LayoutInflater.from(parent.context).inflate(R.layout.item_vote_header, parent, false)
        }

        private fun fixLayoutSize(view: View?, parent: ViewGroup) {
            val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)
            val childWidth = ViewGroup.getChildMeasureSpec(widthSpec, parent.paddingLeft + parent.paddingRight, view!!.layoutParams.width)
            val childHeight = ViewGroup.getChildMeasureSpec(heightSpec, parent.paddingTop + parent.paddingBottom, view.layoutParams.height)
            view.measure(childWidth, childHeight)
            view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        }
    }

    interface SectionCallback {
        fun isSection(position: Int): Boolean
        fun SectionHeader(proposalList: List<ProposalData?>, section: Int): String
    }

    private fun getSectionCall(): SectionCallback {
        return object : SectionCallback {
            override fun isSection(position: Int): Boolean {
                return position == 0 || position == mProposalPeriodList.size
            }

            override fun SectionHeader(proposalList: List<ProposalData?>, section: Int): String {
                return if (section == ProposalViewType.TYPE_PERIOD.ordinal) {
                    getString(R.string.str_voting_period)
                } else {
                    getString(R.string.str_vote_proposals)
                }
            }

        }
    }
}
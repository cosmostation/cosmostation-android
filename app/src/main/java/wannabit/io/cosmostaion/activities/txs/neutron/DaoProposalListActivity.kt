package wannabit.io.cosmostaion.activities.txs.neutron

import android.content.Context
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
import wannabit.io.cosmostaion.utils.WLog

class DaoProposalListActivity : BaseActivity() {

    private lateinit var binding: ActivityDaoProposalListBinding

    private val neutronViewModel: NeutronViewModel by viewModels()

    private var mProposalSingleList = mutableListOf<ProposalData?>()
    private var mProposalMultiList = mutableListOf<ProposalData?>()
    private var mProposalOverruleList = mutableListOf<ProposalData?>()

    private var isShowAll = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoProposalListBinding.inflate(layoutInflater)
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

            recycler.layoutManager = LinearLayoutManager(this@DaoProposalListActivity)
            recycler.adapter = ProposalListAdapter()
            recycler.addItemDecoration(proposalHeaderRecyclerView(this@DaoProposalListActivity, true, getSectionCall()))

            neutronViewModel.loadDaoProposalListData(mChainConfig)
            loadDataObserve()
            onCheckShowAll()
        }
    }

    fun onSwipeRefresh() {
        binding.layerRefresher.setOnRefreshListener {
            loadDataObserve()
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
        mProposalSingleList.clear()
        mProposalMultiList.clear()
        mProposalOverruleList.clear()
        neutronViewModel.data.observe(this) { response ->
            response?.let { proposals ->
                Gson().fromJson(proposals[0].toString(), ResProposalData::class.java).let { data ->
                    data.proposals.forEach {
                        if ("open" == it?.proposal?.status) mProposalSingleList.add(it)
                    }
                }

                Gson().fromJson(proposals[1].toString(), ResProposalData::class.java).let { data ->
                    WLog.w("test1234 : " + data.proposals[0]?.proposal?.title)
                    data.proposals.forEach {
                        if ("open" == it?.proposal?.status) mProposalMultiList.add(it)
                    }
                }

                Gson().fromJson(proposals[2].toString(), ResProposalData::class.java).let { data ->
                    data.proposals.forEach {
                        if ("open" == it?.proposal?.status) mProposalOverruleList.add(it)
                    }
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
                recycler.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun onCheckShowAll() {
        with(binding) {
            checkShowAll.setOnCheckedChangeListener { buttonView, isChecked ->
                neutronViewModel.data.value?.let { response ->
                    response.let { proposals ->
                        Gson().fromJson(proposals[0].toString(), ResProposalData::class.java).let { data ->
                            if (isChecked) {
                                isShowAll = !isShowAll
                                mProposalSingleList = data.proposals as MutableList<ProposalData?>
                            } else {
                                mProposalSingleList.clear()
                                data.proposals.forEach {
                                    if ("open" == it?.proposal?.status) mProposalSingleList.add(it)
                                }
                            }
                        }

                        Gson().fromJson(proposals[1].toString(), ResProposalData::class.java).let { data ->
                            if (isChecked) {
                                isShowAll = !isShowAll
                                mProposalMultiList = data.proposals as MutableList<ProposalData?>
                            } else {
                                mProposalMultiList.clear()
                                data.proposals.forEach {
                                    if ("open" == it?.proposal?.status) mProposalMultiList.add(it)
                                }
                            }
                        }

                        Gson().fromJson(proposals[2].toString(), ResProposalData::class.java).let { data ->
                            if (isChecked) {
                                isShowAll = !isShowAll
                                mProposalOverruleList = data.proposals as MutableList<ProposalData?>
                            } else {
                                mProposalOverruleList.clear()
                                data.proposals.forEach {
                                    if ("open" == it?.proposal?.status) mProposalOverruleList.add(it)
                                }
                            }
                        }
                    }
                }
                recycler.adapter?.notifyDataSetChanged()
            }
        }
    }

    private inner class ProposalListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                ProposalViewType.TYPE_SINGLE.ordinal -> ProposalSingleHolder(ItemDaoProposalListBinding.inflate(layoutInflater, parent, false))
                ProposalViewType.TYPE_MULTI.ordinal -> ProposalMultiHolder(ItemDaoProposalListBinding.inflate(layoutInflater, parent, false))
                else -> ProposalOverruleHolder(ItemDaoProposalListBinding.inflate(layoutInflater, parent, false))
            }
        }

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            if (getItemViewType(position) == ProposalViewType.TYPE_SINGLE.ordinal) {
                (viewHolder as ProposalSingleHolder).bind(position)
            } else if (getItemViewType(position) == ProposalViewType.TYPE_MULTI.ordinal) {
                (viewHolder as ProposalMultiHolder).bind(position - mProposalSingleList.size)
            } else {
                (viewHolder as ProposalMultiHolder).bind(position - mProposalSingleList.size - mProposalMultiList.size)
            }
        }

        override fun getItemCount(): Int {
            return mProposalSingleList.size + mProposalMultiList.size + mProposalOverruleList.size
        }

        override fun getItemViewType(position: Int): Int {
            return if (position < mProposalSingleList.size) {
                ProposalViewType.TYPE_SINGLE.ordinal
            } else if (position < mProposalSingleList.size + mProposalMultiList.size) {
                ProposalViewType.TYPE_MULTI.ordinal
            } else {
                ProposalViewType.TYPE_OVERRULE.ordinal
            }
        }

        inner class ProposalSingleHolder(val proposalListBinding: ItemDaoProposalListBinding) : RecyclerView.ViewHolder(proposalListBinding.root) {
            fun bind(position: Int) {
                mProposalSingleList[position]?.let {
                    onBindProposalItemViewHolder(proposalListBinding, it)
                }
            }
        }

        inner class ProposalMultiHolder(val proposalListBinding: ItemDaoProposalListBinding) : RecyclerView.ViewHolder(proposalListBinding.root) {
            fun bind(position: Int) {
                mProposalMultiList[position]?.let {
                    onBindProposalItemViewHolder(proposalListBinding, it)
                }
            }
        }

        inner class ProposalOverruleHolder(val proposalListBinding: ItemDaoProposalListBinding) : RecyclerView.ViewHolder(proposalListBinding.root) {
            fun bind(position: Int) {
                mProposalOverruleList[position]?.let {
                    onBindProposalItemViewHolder(proposalListBinding, it)
                }
            }
        }

        fun onBindProposalItemViewHolder(proposalPeriodBinding: ItemDaoProposalListBinding, proposalData: ProposalData) {
            with(proposalPeriodBinding) {
                proposalTitle.text = "# " + proposalData.id + " " + proposalData.proposal?.title
                proposalData.proposal?.let {
                    if ("open" == it.status) {
                        proposalExpiration.visibility = View.VISIBLE
                        proposalStatusLayout.visibility = View.GONE
                        it.expiration?.at_time?.toLong()?.let { expiration ->
                            proposalExpiration.text = WDp.getDpTime(this@DaoProposalListActivity, expiration.div(1000000)) + " " +
                                    WDp.getGapTime(expiration.div(1000000))
                        }

                    } else {
                        proposalExpiration.visibility = View.GONE
                        proposalStatusLayout.visibility = View.VISIBLE
                        when (it.status) {
                            "executed", "passed" -> proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalListActivity, R.drawable.ic_passed_img))
                            "rejected" -> proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalListActivity, R.drawable.ic_rejected_img))
                            "failed" -> proposalStatusImg.setImageDrawable(ContextCompat.getDrawable(this@DaoProposalListActivity, R.drawable.ic_failed_img))
                        }
                        proposalStatus.text = it.status?.capitalize()
                    }
                }
            }
        }
    }

    enum class ProposalViewType { TYPE_SINGLE, TYPE_MULTI, TYPE_OVERRULE }

    private inner class proposalHeaderRecyclerView(context: Context, private val sticky: Boolean, private val sectionCallback: SectionCallback) : ItemDecoration() {

        private val topPadding: Int
        private var headerView: View? = null
        private var mHeaderTitle: TextView? = null
        private var mItemCnt: TextView? = null

        init {
            topPadding = dpToPx(context, 34)
        }

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
                if (mSection == ProposalViewType.TYPE_SINGLE.ordinal) {
                    title = sectionCallback.SectionHeader(mProposalSingleList, mSection)
                    mItemCnt?.text = "" + mProposalSingleList.size
                } else if (mSection == ProposalViewType.TYPE_MULTI.ordinal) {
                    title = sectionCallback.SectionHeader(mProposalMultiList, mSection)
                    mItemCnt?.text = "" + mProposalMultiList.size
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
            if (sectionCallback.isSection(position)) {
                outRect.top = topPadding
            }
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
                return position == 0 || position == mProposalSingleList.size
            }

            override fun SectionHeader(proposalList: List<ProposalData?>, section: Int): String {
                return when (section) {
                    ProposalViewType.TYPE_SINGLE.ordinal -> getString(R.string.str_dao_single_list)
                    ProposalViewType.TYPE_MULTI.ordinal -> getString(R.string.str_dao_multi_list)
                    else -> getString(R.string.str_dao_multi_list)
                }
            }

        }
    }
}
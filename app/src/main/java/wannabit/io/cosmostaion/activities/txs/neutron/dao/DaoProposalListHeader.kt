package wannabit.io.cosmostaion.activities.txs.neutron.dao

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.common.collect.Sets
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.network.res.neutron.ProposalData

class DaoProposalListHeader(
    context: Context,
    private val sticky: Boolean,
    private val pairs: List<Pair<String?, ProposalData?>>,
    private val sectionCallback: SectionCallback
) : ItemDecoration() {

    private val topPadding: Int = dpToPx(context, 34)
    private var headerView: View? = null
    private var mHeaderTitle: TextView? = null
    private var mItemCnt: TextView? = null

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

            val title = sectionCallback.sectionHeader(pairs, position)
            val cnt = sectionCallback.sectionCnt(pairs, position)
            mHeaderTitle?.text = title
            mItemCnt?.text = cnt

            if (!headerTitleSet.contains(title) || sectionCallback.isSection(pairs, position) == true) {
                drawHeader(c, child, headerView)
                headerTitleSet.add(title)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (sectionCallback.isSection(pairs, position) == true) {
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

    interface SectionCallback {
        fun isSection(pairs: List<Pair<String?, ProposalData?>>, position: Int): Boolean?
        fun sectionHeader(pairs: List<Pair<String?, ProposalData?>>, position: Int): String
        fun sectionCnt(pairs: List<Pair<String?, ProposalData?>>, position: Int): String
    }
}
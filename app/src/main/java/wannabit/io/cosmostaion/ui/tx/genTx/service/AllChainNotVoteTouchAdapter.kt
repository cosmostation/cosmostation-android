package wannabit.io.cosmostaion.ui.tx.genTx.service

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.data.model.res.CosmosProposal

class AllChainNotVoteTouchAdapter(
    private val toDisplayInfos: MutableList<VoteAllModel>,
    private val onDelete: (VoteAllModel, CosmosProposal) -> Unit
) : ItemTouchHelper.Callback() {

    private var currentPosition: Int? = null
    private var previousPosition: Int? = null
    private var currentDx = 0f
    private var clamp = 0f

    private var isLeftSwiped: Boolean = false

    override fun getMovementFlags(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags =
            if (viewHolder.itemViewType == AllChainNotVoteAdapter.VIEW_TYPE_VOTING_HEADER) {
                0
            } else {
                ItemTouchHelper.LEFT
            }
        val rightSwipeFlags = if (isLeftSwiped) ItemTouchHelper.RIGHT else 0

        return makeMovementFlags(0, swipeFlags or rightSwipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val adapterPosition = viewHolder.adapterPosition
        val sectionPosition = getSectionPosition(adapterPosition)
        val itemPositionInSection = getItemPosition(adapterPosition, sectionPosition)

        val model = toDisplayInfos[sectionPosition]
        val proposal = model.proposals[itemPositionInSection]
        onDelete(model, proposal)
        getView(viewHolder)?.translationX = 0f
        getHideView(viewHolder)?.visibility = View.GONE
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        currentDx = 0f
        previousPosition = viewHolder.adapterPosition
        getDefaultUIUtil().clearView(getView(viewHolder))
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        viewHolder?.let {
            currentPosition = viewHolder.adapterPosition
            getDefaultUIUtil().onSelected(getView(it))
        }
    }

    private fun getSectionPosition(adapterPosition: Int): Int {
        var itemCountSoFar = 0
        for (index in toDisplayInfos.indices) {
            if (adapterPosition < itemCountSoFar + 1 + toDisplayInfos[index].proposals.size) {
                return index
            }
            itemCountSoFar += 1 + toDisplayInfos[index].proposals.size
        }

        throw IllegalArgumentException("Invalid position")
    }

    private fun getItemPosition(adapterPosition: Int, sectionPosition: Int): Int {
        var itemCountSoFar = 0
        for (index in 0 until sectionPosition) {
            itemCountSoFar += 1 + toDisplayInfos[index].proposals.size
        }
        return adapterPosition - itemCountSoFar - 1
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            getView(viewHolder)?.let { view ->
                val isClamped = getTag(viewHolder)
                val x = clampViewPositionHorizontal(view, dX, isClamped, isCurrentlyActive)
                isLeftSwiped = isClamped

                if (dX < 0) {
                    getHideView(viewHolder)?.visibility = View.VISIBLE
                } else {
                    if (isLeftSwiped) {
                        getHideView(viewHolder)?.visibility = View.VISIBLE
                    } else {
                        getHideView(viewHolder)?.visibility = View.GONE
                    }
                }

                currentDx = x
                getDefaultUIUtil().onDraw(
                    c, recyclerView, view, x, dY, actionState, isCurrentlyActive
                )
            }
            return
        }
    }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return defaultValue * 10
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        val isClamped = getTag(viewHolder)
        setTag(viewHolder, !isClamped && currentDx <= -clamp)
        return .8f
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View? {
        if (viewHolder is AllChainNotVoteViewHolder) {
            return viewHolder.itemView.findViewById(R.id.proposal_view)
        }
        return null
    }

    private fun getHideView(viewHolder: RecyclerView.ViewHolder): View? {
        if (viewHolder is AllChainNotVoteViewHolder) {
            return viewHolder.itemView.findViewById(R.id.delete_view)
        }
        return null
    }

    private fun clampViewPositionHorizontal(
        view: View, dX: Float, isClamped: Boolean, isCurrentlyActive: Boolean
    ): Float {
        val min: Float = -view.width.toFloat()
        val max = view.width.toFloat()

        val x = if (isClamped) {
            if (isCurrentlyActive) dX - clamp else -clamp
        } else {
            dX
        }
        return java.lang.Float.min(java.lang.Float.max(min, x), max)
    }

    private fun setTag(viewHolder: RecyclerView.ViewHolder, isClamped: Boolean) {
        viewHolder.itemView.tag = isClamped
    }

    private fun getTag(viewHolder: RecyclerView.ViewHolder): Boolean {
        return viewHolder.itemView.tag as? Boolean ?: false
    }

    fun setClamp(clamp: Float) {
        this.clamp = clamp
    }

    fun removePreviousClamp(recyclerView: RecyclerView) {
        if (currentPosition == previousPosition) return
        previousPosition?.let {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(it) ?: return
            getView(viewHolder)?.translationX = 0f
            getHideView(viewHolder)?.visibility = View.GONE
            setTag(viewHolder, false)
            previousPosition = null
        }
    }

    fun initializeCurrentPosition(recyclerView: RecyclerView) {
        for (i in 0 until (recyclerView.adapter?.itemCount ?: 0)) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) ?: return
            if (viewHolder is AllChainNotVoteViewHolder) {
                getView(viewHolder)?.translationX = 0f
                getHideView(viewHolder)?.visibility = View.GONE
                setTag(viewHolder, false)
            }
        }
    }
}
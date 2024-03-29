package wannabit.io.cosmostaion.ui.tx.step.service

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import java.lang.Float.max
import java.lang.Float.min

class AllChainClaimTouchAdapter(
    private val rewards: MutableList<ClaimAllModel>,
    private val onDelete: (ClaimAllModel) -> Unit
) : ItemTouchHelper.Callback() {

    private var currentPosition: Int? = null
    private var previousPosition: Int? = null
    private var currentDx = 0f
    private var clamp = 0f

    private var isLeftSwiped: Boolean = false

    override fun getMovementFlags(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
    ): Int {
        val reward = rewards[viewHolder.adapterPosition]
        val swipeFlags = if (reward.isBusy) {
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
        val reward = rewards[viewHolder.adapterPosition]
        onDelete(reward)
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

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ACTION_STATE_SWIPE) {
            val view = getView(viewHolder)
            val isClamped = getTag(viewHolder)
            val x = clampViewPositionHorizontal(view, dX, isClamped, isCurrentlyActive)
            isLeftSwiped = isClamped

            if (dX < 0) {
                getHideView(viewHolder).visibility = View.VISIBLE
            } else {
                if (isLeftSwiped) {
                    getHideView(viewHolder).visibility = View.VISIBLE
                } else {
                    getHideView(viewHolder).visibility = View.GONE
                }
            }

            currentDx = x
            getDefaultUIUtil().onDraw(
                c,
                recyclerView,
                view,
                x,
                dY,
                actionState,
                isCurrentlyActive
            )
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

    private fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as AllChainClaimViewHolder).itemView.findViewById(R.id.reward_view)
    }

    private fun getHideView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as AllChainClaimViewHolder).itemView.findViewById(R.id.delete_layout)
    }

    private fun clampViewPositionHorizontal(
        view: View,
        dX: Float,
        isClamped: Boolean,
        isCurrentlyActive: Boolean
    ): Float {
        val min: Float = -view.width.toFloat()
        val max = view.width.toFloat()

        val x = if (isClamped) {
            if (isCurrentlyActive) dX - clamp else -clamp
        } else {
            dX
        }
        return min(max(min, x), max)
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
        if (currentPosition == previousPosition)
            return
        previousPosition?.let {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(it) ?: return
            getView(viewHolder).translationX = 0f
            getHideView(viewHolder).visibility = View.GONE
            setTag(viewHolder, false)
            previousPosition = null
        }
    }

    fun initializeCurrentPosition(recyclerView: RecyclerView) {
        for (i in 0 until rewards.size) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) ?: return
            getView(viewHolder).translationX = 0f
            setTag(viewHolder, false)
        }
    }
}
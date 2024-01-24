package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class AccountListTouchAdapter(
    val listener: ItemTouchHelperListener
) : ItemTouchHelper.Callback() {

    private var itemTouchHelperListener: ItemTouchHelperListener = listener

    override fun isLongPressDragEnabled(): Boolean { return true }

    override fun isItemViewSwipeEnabled(): Boolean { return false }

    override fun getMovementFlags(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        if (viewHolder.itemViewType == AccountListAdapter.VIEW_TYPE_MNEMONIC_HEADER || viewHolder.itemViewType == AccountListAdapter.VIEW_TYPE_PRIVATE_HEADER) {
            return makeMovementFlags(0, 0)
        }
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        itemTouchHelperListener.onItemMove(
            viewHolder.adapterPosition, target.adapterPosition
        )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}

interface ItemTouchHelperListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
}
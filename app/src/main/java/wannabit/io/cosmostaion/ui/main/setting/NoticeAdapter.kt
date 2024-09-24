package wannabit.io.cosmostaion.ui.main.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.data.model.res.Notice
import wannabit.io.cosmostaion.databinding.ItemNoticeBinding

class NoticeAdapter : ListAdapter<Notice, NoticeViewHolder>(NoticeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val notice = currentList[position]
        holder.bind(notice)

        holder.binding.noticeLayout.setOnClickListener {
            notice.isExpandable = !notice.isExpandable
            notifyItemChanged(position)
        }
    }

    private class NoticeDiffCallback : DiffUtil.ItemCallback<Notice>() {

        override fun areItemsTheSame(oldItem: Notice, newItem: Notice): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Notice, newItem: Notice): Boolean {
            return oldItem == newItem
        }
    }
}
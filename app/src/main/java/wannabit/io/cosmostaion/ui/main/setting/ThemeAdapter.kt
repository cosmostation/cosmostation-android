package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.databinding.ItemStyleBinding

class ThemeAdapter(
    val context: Context
) : ListAdapter<String, StyleViewHolder>(StyleDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StyleViewHolder {
        val binding = ItemStyleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StyleViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: StyleViewHolder, position: Int) {
        val theme = currentList[position]
        holder.themeBind(theme)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(position) }
        }
    }

    private class StyleDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}
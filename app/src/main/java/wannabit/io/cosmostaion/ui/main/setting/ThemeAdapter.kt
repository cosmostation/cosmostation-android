package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.databinding.ItemThemeBinding

class ThemeAdapter(
    val context: Context
) : ListAdapter<String, ThemeViewHolder>(ThemeDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val binding = ItemThemeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ThemeViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        val theme = currentList[position]
        holder.bind(theme)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(position) }
        }
    }

    private class ThemeDiffCallback : DiffUtil.ItemCallback<String>() {

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
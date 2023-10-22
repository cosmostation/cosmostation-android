package wannabit.io.cosmostaion.ui.dialog.tx

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.data.model.res.NameService
import wannabit.io.cosmostaion.databinding.ItemNameServiceBinding

class NameServiceAdapter : ListAdapter<NameService, NameServiceViewHolder>(NameServiceDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameServiceViewHolder {
        val binding = ItemNameServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NameServiceViewHolder, position: Int) {
        val nameService = currentList[position]
        holder.bind(nameService)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(nameService.address)
            }
        }
    }

    private class NameServiceDiffCallback : DiffUtil.ItemCallback<NameService>() {

        override fun areItemsTheSame(oldItem: NameService, newItem: NameService): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: NameService, newItem: NameService): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
package com.fulldive.wallet.presentation.chains.choicenet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain

class ChainListAdapter(
        private var items: List<BaseChain>,
        private val onItemClicked: (BaseChain) -> Unit
) : RecyclerView.Adapter<ChainListAdapter.ChainHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ChainHolder {
        return ChainHolder(
                LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.item_choice_net, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ChainHolder, position: Int) {
        val baseChain: BaseChain = items[position]
        holder.imageView.setImageResource(baseChain.chainIcon)
        holder.textView.setText(baseChain.chainTitle)
        holder.itemView.setOnClickListener {
            onItemClicked(baseChain)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ChainHolder(
            itemView: View,
            val imageView: ImageView = itemView.findViewById(R.id.imageView),
            val textView: TextView = itemView.findViewById(R.id.textView)
    ) : RecyclerView.ViewHolder(itemView)
}
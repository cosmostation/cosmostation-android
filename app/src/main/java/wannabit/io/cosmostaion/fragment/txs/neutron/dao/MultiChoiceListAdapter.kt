package wannabit.io.cosmostaion.fragment.txs.neutron.dao

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemMultiChoiceBinding
import wannabit.io.cosmostaion.network.res.neutron.Choice

class MultiChoiceListAdapter(
    private val context: Context,
    private val choiceList: List<Choice?>?,
    val listener: ClickListener
) : RecyclerView.Adapter<MultiChoiceListAdapter.ChoiceHolder>() {

    private var optionId: Int = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChoiceHolder {
        val binding = ItemMultiChoiceBinding.inflate(LayoutInflater.from(context), parent, false)
        return ChoiceHolder(binding)
    }

    override fun getItemCount(): Int {
        return choiceList!!.size
    }

    override fun onBindViewHolder(holder: ChoiceHolder, position: Int) {
        holder.bind(position)
    }

    inner class ChoiceHolder(
        private val itemMultiChoiceBinding: ItemMultiChoiceBinding
    ) : RecyclerView.ViewHolder(itemMultiChoiceBinding.root) {
        fun bind(position: Int) {
            itemMultiChoiceBinding.apply {
                choiceList?.get(position)?.let { choiceInfo ->
                    choiceTitle.text = choiceInfo.option_type
                    choiceDescription.text = choiceInfo.description

                    choiceLayout.setOnClickListener {
                        optionId = choiceInfo.index!!
                        listener.onClickChoice(optionId)
                        notifyDataSetChanged()
                    }

                    if (optionId == choiceInfo.index) {
                        choiceTitle.setTextColor(ContextCompat.getColor(context, R.color.colorBlackDayNight))
                        choiceLayout.background = context.resources.getDrawable(R.drawable.box_vote_selected)
                        choiceImg.setColorFilter(ContextCompat.getColor(context, R.color.colorBlackDayNight), android.graphics.PorterDuff.Mode.SRC_IN)
                    } else {
                        choiceTitle.setTextColor(ContextCompat.getColor(context, R.color.colorGrayDayNight))
                        choiceLayout.background = context.resources.getDrawable(R.drawable.box_vote_quorum)
                        choiceImg.setColorFilter(ContextCompat.getColor(context, R.color.colorGrayDayNight), android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                }
            }
        }
    }

    interface ClickListener {
        fun onClickChoice(optionId: Int)
    }
}
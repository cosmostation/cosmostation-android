package wannabit.io.cosmostaion.activities.txs.neutron.dao

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.neutron.MainDaoProposalListActivity
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.databinding.ItemDaoBinding
import wannabit.io.cosmostaion.network.res.neutron.ResDaoData
import wannabit.io.cosmostaion.utils.WDp
import java.math.BigDecimal

class DaoListAdapter(
    private val context: Context,
    val baseDao: BaseData,
    val chainConfig: ChainConfig,
    var daoList: List<ResDaoData?> = listOf()
) : RecyclerView.Adapter<DaoListAdapter.DaoHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DaoHolder {
        val binding = ItemDaoBinding.inflate(LayoutInflater.from(context), parent, false)
        return DaoHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DaoHolder, position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return daoList.size
    }

    inner class DaoHolder(
        private val itemDaoBinding: ItemDaoBinding
    ) : RecyclerView.ViewHolder(itemDaoBinding.root) {
        fun bind(position: Int) {
            itemDaoBinding.apply {
                when (position) {
                    0 -> {
                        cardRoot.setCardBackgroundColor(ContextCompat.getColor(context, chainConfig.chainBgColor()))
                        daoImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_main_dao))
                    }
                    else -> {
                        cardRoot.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTransBg))
                        daoImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_sub_dao))
                        view2.visibility = View.GONE
                        myVotingPowerTitle.visibility = View.GONE
                        myVotingPower.visibility = View.GONE
                    }
                }

                daoList[position]?.let { daoInfo ->
                    daoName.text = daoInfo.name?.uppercase()
                    daoDescription.text = daoInfo.description?.capitalize()
                    moduleCount.text = daoInfo.proposal_modules.size.toString()
                    daoUrl.text = daoInfo.dao_uri

                    myVotingPower.text = WDp.getDpAmount2(BigDecimal(baseDao.mVaultAmount), chainConfig.decimal(), chainConfig.decimal())

                    cardRoot.setOnClickListener {
                        if (position == 0) {
                            Intent(context, MainDaoProposalListActivity::class.java).apply {
                                context.startActivity(this)
                            }
                        }
//                    if (position == 0) {
//                        Intent(this@DaoListActivity, MainDaoProposalListActivity::class.java).apply {
//                            startActivity(this)
//                        }
//                    } else {
//                        Intent(this@DaoListActivity, SubDaoProposalListActivity::class.java).apply {
//                            putExtra("contractAddress", daoInfo.proposal_modules[0]?.address)
//                            startActivity(this)
//                        }
//                    }
                    }
                }
            }
        }
    }
}
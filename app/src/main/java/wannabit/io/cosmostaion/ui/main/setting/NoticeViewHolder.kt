package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.noties.markwon.Markwon
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.formatTxTimeToYear
import wannabit.io.cosmostaion.data.model.res.Notice
import wannabit.io.cosmostaion.databinding.ItemNoticeBinding

class NoticeViewHolder(
    private val context: Context,
    val binding: ItemNoticeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(notice: Notice) {
        binding.apply {
            val title = "v1.10.11"
            val type = "Release"
            val content = "### Chain Support\r\n- Support SUI Mainnet\r\n- Support MANTRA Testnet\r\n### Additional\r\n- Support Drop-money\r\n- Support End-point initialization\r\n" +
                    "### Changes\r\n- Support Drop-money\r\n- Support End-point initialization"
            noticeView.setBackgroundResource(R.drawable.item_bg)

            val markDown = Markwon.create(context)
            markDown.setMarkdown(noticeContent, content)

//            noticeTitle.text = notice.title
            noticeTitle.text = title
            noticeCreated.text = formatTxTimeToYear(context, notice.created_at)
//            noticeType.text = notice.type
            noticeType.text = type
//            noticeContent.text = notice.content
//            noticeContent.text = content

            if (notice.isExpandable) {
                btnNoticeDetail.setImageResource(R.drawable.icon_up)
                noticeContentLayout.visibility = View.VISIBLE
            } else {
                btnNoticeDetail.setImageResource(R.drawable.icon_down)
                noticeContentLayout.visibility = View.GONE
            }
        }
    }
}
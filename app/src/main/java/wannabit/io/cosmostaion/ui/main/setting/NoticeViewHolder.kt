package wannabit.io.cosmostaion.ui.main.setting

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.noties.markwon.AbstractMarkwonPlugin
import io.noties.markwon.Markwon
import io.noties.markwon.MarkwonSpansFactory
import io.noties.markwon.core.MarkwonTheme
import org.commonmark.node.BulletList
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.formatTxTimeToYear
import wannabit.io.cosmostaion.data.model.res.Notice
import wannabit.io.cosmostaion.databinding.ItemNoticeBinding

class NoticeViewHolder(
    private val context: Context, val binding: ItemNoticeBinding
) : RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.P)
    fun bind(notice: Notice) {
        binding.apply {
            val title = "Wallet Update (v1.10.11)"
            val type = "Release"
            val content =
                "### 1. Chain Support\r\n- Support SUI Mainnet\r\n- Support MANTRA Testnet\r\n### 2. Additional\r\n- Support Drop-money\r\n- Support End-point initialization\r\n" + "### 3. Changes\r\n- Support Drop-money\r\n- Support End-point initialization".trimIndent()
            noticeView.setBackgroundResource(R.drawable.item_bg)
            btnNoticeDetail.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base03), PorterDuff.Mode.SRC_IN
            )

            val markDown = Markwon.builder(context).usePlugin(object : AbstractMarkwonPlugin() {
                override fun configureTheme(builder: MarkwonTheme.Builder) {
                    builder.listItemColor(context.getColor(R.color.color_base02)).bulletWidth(11)
                }

                override fun configureSpansFactory(builder: MarkwonSpansFactory.Builder) {
                    builder.setFactory(
                        BulletList::class.java
                    ) { _, _ ->
                        ForegroundColorSpan(context.getColor(R.color.color_base02))
                    }
                }
            }).build()
            markDown.setMarkdown(noticeContent, content)

//            noticeTitle.text = notice.title
            noticeTitle.text = title.lowercase().replaceFirstChar { it.lowercase().uppercase() }
            noticeCreated.text = " | Update : " + formatTxTimeToYear(context, notice.created_at)
//            noticeType.text = notice.type
            noticeType.text = type

            if (notice.isExpandable) {
                btnNoticeDetail.setImageResource(R.drawable.icon_expanded)
                noticeContentLayout.visibility = View.VISIBLE
            } else {
                btnNoticeDetail.setImageResource(R.drawable.icon_collapsed)
                noticeContentLayout.visibility = View.GONE
            }
        }
    }
}
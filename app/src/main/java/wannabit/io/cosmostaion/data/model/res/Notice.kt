package wannabit.io.cosmostaion.data.model.res

data class NoticeResponse(val list: MutableList<Notice>)
data class Notice(val type: String, val title: String, val created_at: String, val content: String, var isExpandable: Boolean = false)

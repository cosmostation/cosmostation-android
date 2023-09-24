package wannabit.io.cosmostaion.database.legacy.model

data class MWords(
    var id: Long,
    var uuid: String,
    var resource: String,
    var spec: String,
    var nickName: String?,
    var wordsCnt: Int,
    var isFavo: Boolean,
    var importTime: Long
)
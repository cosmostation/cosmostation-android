package wannabit.io.cosmostaion.database.legacy.model

data class Account(
    var id: Long,
    var uuid: String,
    var nickName: String,
    var isFavo: Boolean,
    var address: String,
    var baseChain: String,
    var hasPrivateKey: Boolean,
    var resource: String,
    var spec: String,
    var fromMnemonic: Boolean,
    var path: String,
    var isValidator: Boolean,
    var sequenceNumber: Int,
    var accountNumber: Int,
    var fetchTime: Long,
    var mSize: Int,
    var importTime: Long,
    var sortOrder: Long,
    var pushAlarm: Boolean,
    var newBip44: Boolean,
    var customPath: Int,
    var mnemonicId: Long
)

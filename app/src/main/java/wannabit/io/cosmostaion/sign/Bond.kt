package wannabit.io.cosmostaion.sign

import java.math.BigDecimal
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

//class Bond private constructor() {}

data class BondProps(
    val source: String, val validator: String, val amount: BigDecimal
) {
    fun serialize(): ByteArray {
        val byteBuffer = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN)

        val sourceBytes = source.toByteArray(StandardCharsets.UTF_8)
        byteBuffer.putInt(sourceBytes.size)
        byteBuffer.put(sourceBytes)

        val validatorBytes = validator.toByteArray(StandardCharsets.UTF_8)
        byteBuffer.putInt(validatorBytes.size)
        byteBuffer.put(validatorBytes)

        val amountBytes = amount.toPlainString().toByteArray(StandardCharsets.UTF_8)
        byteBuffer.putInt(amountBytes.size)
        byteBuffer.put(amountBytes)

        return byteBuffer.array().sliceArray(0 until byteBuffer.position())
    }
}

data class WrapperTx(
    val token: String,
    val feeAmount: BigDecimal,
    val gasLimit: BigDecimal,
    val chainId: String,
    val publicKey: String,
    val memo: String? = ""
) {
    fun serialize(): ByteArray {
        val byteBuffer = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN)

        val tokenBytes = token.toByteArray(StandardCharsets.UTF_8)
        byteBuffer.putInt(tokenBytes.size)
        byteBuffer.put(tokenBytes)

        val feeAmountBytes = feeAmount.toPlainString().toByteArray(StandardCharsets.UTF_8)
        byteBuffer.putInt(feeAmountBytes.size)
        byteBuffer.put(feeAmountBytes)

        val gasLimitBytes = gasLimit.toPlainString().toByteArray(StandardCharsets.UTF_8)
        byteBuffer.putInt(gasLimitBytes.size)
        byteBuffer.put(gasLimitBytes)

        val chainIdBytes = chainId.toByteArray(StandardCharsets.UTF_8)
        byteBuffer.putInt(chainIdBytes.size)
        byteBuffer.put(chainIdBytes)

        val publicKeyBytes = publicKey.toByteArray(StandardCharsets.UTF_8)
        byteBuffer.putInt(publicKeyBytes.size)
        byteBuffer.put(publicKeyBytes)

        val memoBytes = memo?.toByteArray(StandardCharsets.UTF_8)
        byteBuffer.putInt(memoBytes?.size ?: 0)
        byteBuffer.put(memoBytes)

        return byteBuffer.array().sliceArray(0 until byteBuffer.position())
    }
}

fun build_bond() {

}

data class TxMsgValue(
    val args: WrapperTx,
    val hash: String,
    val bytes: ByteArray
)

data class SigningDataMsgValue(
    val owner: String
)

fun bond_tx_args(bondMsg: ByteArray, txMsg: ByteArray): Result<BondArgs> {
    return try {
        val bondMsg = parseBondMsg(bondMsg)
        val source = bondMsg.source
        val validator = bondMsg.validator
        val amount = bondMsg.amount
        val tx = txMsgIntoArgs(txMsg)

        val args = BondArgs(
            tx = tx,
            validator = validator,
            amount = amount,
            source = source,
            txCodePath = "tx_bond.wasm"
        )

        Result.success(args)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

data class BondMsg(
    val source: String, val validator: String, val amount: String
)

data class Tx(val data: String)

fun txMsgIntoArgs(txMsg: ByteArray): Tx {
    return Tx(data = txMsg.decodeToString())
}

data class BondArgs(
    val tx: Tx,
    val validator: String,
    val amount: String,
    val source: String?,
    val txCodePath: String
)

fun parseBondMsg(bytes: ByteArray): BondMsg {
    val decodedString = bytes.decodeToString()
    val parts = decodedString.split(",")
    if (parts.size < 3) throw IllegalArgumentException("Invalid BondMsg format")
    return BondMsg(
        source = parts[0], validator = parts[1], amount = parts[2]
    )
}


data class GlobalArgs(
    val expiration: String?, // Optional expiration in ISO-8601 format
    val codeHash: String,    // Code hash
    val chainId: String      // Chain ID
)

//data class Tx(
//    val header: Header,
//    val section: Section
//)

//data class Tx(
//    val chainId: String,
//    val expiration: String?,
//    var timestamp: String,
//    var codeHash: String,
//    var codeTag: String,
//    var data: String? = null
//) {
//    companion object {
//        fun new(chainId: String, expiration: String?): Tx {
//            return Tx(
//                chainId = chainId,
//                expiration = expiration,
//                timestamp = "2000-01-01T00:00:00Z",
//                codeHash = "",
//                codeTag = "",
//                data = null
//            )
//        }
//    }
//
//    fun addCodeFromHash(codeHash: String, codeTag: String?) {
//        this.codeHash = codeHash
//        this.codeTag = codeTag ?: ""
//    }
//
//    fun addData(data: String) {
//        this.data = data
//    }
//}

data class Bond(
    val validator: String, val amount: String, val source: String?
) {
    companion object {
        const val TX_BOND_WASM = "tx_bond.wasm"

//        fun new(validator: String, amount: String, source: String?, args: GlobalArgs): Tx {
//            val bond = Bond(
//                validator = validator,
//                amount = amount,
//                source = source
//            )
//            val serializedData = bond.serialize()
////            return buildTx(args, serializedData, TX_BOND_WASM)
//        }
    }

    fun serialize(): String {
        return """{"validator":"$validator","amount":"$amount","source":"$source"}"""
    }
}

//fun buildTx(globalArgs: GlobalArgs, data: String, codeTag: String): Tx {
//    val tx = Tx.m(globalArgs.chainId, globalArgs.expiration)
//    tx.timestamp = "2000-01-01T00:00:00Z"
//    tx.addCodeFromHash(globalArgs.codeHash, codeTag)
//    tx.addData(data)
//    return tx
//}


//data class Bond(val args: GlobalArg, val bond: PosBond) {
//
//    companion object {
//        fun new(
//            validator: String,
//            amount: Long,
//            source: String?,
//            args: GlobalArg
//        ): Bond {
//            val bond = PosBond(
//                validator = validator,
//                amount = amount,
//                source = source ?: ""
//            )
//            return Bond(args = args, bond = bond, type = "tx_bond.wasm")
//        }
//    }
//}
//
//data class GlobalArg(
//    val expiration: String?, val code_hash: String? = "f1fc74460bd9bbd17140c88dfc0543440f066ffb84849c35c2bb0e331e51cf1c", val chain_id: String
//)
//
//data class PosBond(val validator: String, val amount: Long, val source: String)

//data class Bond(val transaction: Tx) {

//    companion object {
//        const val TX_BOND_WASM = "tx_bond.wasm"
//
//        fun new(
//            validator: String,
//            amount: Long,
//            source: String,
//            args: GlobalArgs
//        ): Bond {
//            val bond = PosBond(validator, amount, source)
//            return Bond(Transaction.buildTx(args, bond, TX_BOND_WASM))
//        }
//    }
//
//    fun getSignBytes(): List<Hash> {
//        return Transaction.getSignBytes(transaction)
//    }
//
//    fun attachSignatures(signer: PublicKey, signature: Signature): Bond {
//        val signedTx = Transaction.attachRawSignatures(transaction, signer, signature)
//        return Bond(signedTx)
//    }
//
//    fun toBytes(): ByteArray {
//        return transaction.toBytes()
//    }
//
//    fun payload(): Tx {
//        return transaction
//    }
//}

//data class GlobalArgs(
//    val expiration: DateTimeUtc?, val code_hash: String, val chain_id: String
//)

// Transaction 클래스
object Transaction {
//    fun buildTx(args: GlobalArgs, data: PosBond, code_tag: String): Tx {
//        val (expiration, codeHash, chainId) = args
//
//        // 새로운 트랜잭션 생성
//        val innerTx = Tx.new(chainId, expiration)
//
//        // 타임스탬프 초기화
//        innerTx.header.timestamp = DateTimeUtc(ZonedDateTime.parse("0000-01-01T00:00:00Z"))
//
//        // 코드 해시 및 태그 추가
//        innerTx.addCodeFromHash(codeHash, codeTag)
//
//        // 데이터 추가
//        innerTx.addData(data)
//
//        return innerTx
//    }
//
//    fun getSignBytes(tx: Tx): List<Hash> {
//        return listOf(tx.rawHeaderHash())
//    }
//
//    fun attachRawSignatures(tx: Tx, signer: PublicKey, signature: Signature): Tx {
//        // 서명을 트랜잭션에 첨부하는 로직
//        return tx
//    }
//
//    fun toBytes(tx: Tx): ByteArray {
//        TxProto.Tx.newBuilder().
//            // Protobuf로 변환하는 로직
//        return ByteArray(0)
//    }
}

//class Tx private constructor(
//    val sections: MutableList<Section>, val header: Header
//) {
//    companion object {
////        private val objectMapper =
////            ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
////
////        fun new(chain_id: String, expiration: DateTimeUtc?): Tx {
////            return Tx(
////                sections = mutableListOf(), header = Header.new(
////                    chain_id = chain_id, expiration = expiration, txType = TxType.Raw
////                )
////            )
////        }
////
////        fun fromJsonBytes(data: ByteArray): Tx? {
////            return try {
////                objectMapper.readValue(data, Tx::class.java)
////            } catch (e: Exception) {
////                null
////            }
////        }
////
////        fun fromBytes(data: ByteArray): Tx? {
////            return try {
////                objectMapper.writeValueAsString(this)
////            } catch (e: Exception) {
////                null
////            }
////        }
//    }

//    fun toWriterJson(writer: OutputStream) {
//        try {
//            writer.write(Json.encodeToString(this).toByteArray())
//        } catch (e: Exception) {
//            throw IllegalStateException("Failed to serialize to JSON", e)
//        }
//    }
//
//    // Serialize Tx to binary bytes
//    fun toBytes(): ByteArray {
//        return try {
//            Json.encodeToString(this).toByteArray() // Replace with binary serialization
//        } catch (e: Exception) {
//            throw IllegalStateException("Failed to serialize transaction", e)
//        }
//    }

// Add a new section to the transaction
//    fun addSection(section: Section): Section {
//        sections.add(section)
//        return sections.last()
//    }
//
//    // Get the hash of the transaction header
//    fun headerHash(): Hash {
//        return Section.HeaderSection(header).getHash()
//    }
//
//    fun sectionHashes(): List<Hash> {
//        return listOf(headerHash()) + sections.map { it.getHash() }
//    }
//
//    fun updateHeader(txType: TxType): Tx {
//        header.txType = txType
//        return this
//    }
//
//    // Get the data designated by the transaction data hash
//    fun getDataSection(dataHash: Hash): ByteArray? {
//        return sections.find { it is Section.DataSection && it.getHash() == dataHash }
//            ?.let { (it as Section.DataSection).data.content.toByteArray() }
//    }
//
//    fun setData(data: Data): Section {
//        val section = Section.DataSection(data)
//        sections.add(section)
//        return sections.last()
//    }
//
//    fun getHeader(): Header {
//        return header.copy()
//    }
//
//    fun rawHeaderHash(): Hash {
//        val rawHeader = this.getHeader().copy(txType = TxType.Raw)
//        return Section.HeaderSection(rawHeader).getHash()
//    }
//}

class DateTimeUtc(private val dateTime: ZonedDateTime) {

    companion object {
        val MIN_UTC: DateTimeUtc = DateTimeUtc(ZonedDateTime.parse("0000-01-01T00:00:00Z"))
    }

    fun toRfc3339(): String {
        return dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }

    override fun toString(): String {
        return toRfc3339()
    }
}

data class Header(
    val chain_id: String? = "",
    val expiration: String? = "",
    var timestamp: String? = "",
    val batch: HashSet<TxCommitments> = hashSetOf(),
    val atomic: Boolean = false,
    var tx_type: TxType
) {
    companion object {
        fun new(txType: TxType): Header {
            return Header(tx_type = txType)
        }
    }

    fun hash(hasher: MessageDigest): MessageDigest {
        hasher.update(this.serializeToByteArray())
        return hasher
    }

    private fun serializeToByteArray(): ByteArray {
        return "$chain_id$expiration$timestamp$batch$atomic$tx_type".toByteArray()
    }

//    fun wrapper(): WrapperTx? {
//        return if (txType is TxType.Wrapper) txType.payload else null
//    }
//
//    fun protocol(): ProtocolTx? {
//        return if (txType is TxType.Protocol) txType.payload else null
//    }
}

data class TxCommitments(
    val codeHash: Hash, val dataHash: Hash, val memoHash: Hash
) {
    fun hash(hasher: MessageDigest): MessageDigest {
        hasher.update(this.serializeToByteArray())
        return hasher
    }

    fun getHash(): Hash {
        val hasher = MessageDigest.getInstance("SHA-256")
        val finalHash = hash(hasher).digest()
        return Hash(finalHash)
    }

    private fun serializeToByteArray(): ByteArray {
        return "$codeHash$dataHash$memoHash".toByteArray()
    }
}

sealed class Section {

    abstract fun hash(hasher: MessageDigest): MessageDigest

    open fun data(): Data? = null
    open fun extraDataSec(): Code? = null
    open fun extraData(): ByteArray? = null
    open fun codeSec(): Code? = null
    open fun header(): Header? = null

    fun getHash(): Hash {
        val hasher = MessageDigest.getInstance("SHA-256")
        return Hash(hash(hasher).digest())
    }

    data class DataSection(val data: Data) : Section() {
        override fun hash(hasher: MessageDigest): MessageDigest {
            return data.hash(hasher)
        }

        override fun data(): Data? = data
    }

    data class ExtraDataSection(val extraData: Code) : Section() {
        override fun hash(hasher: MessageDigest): MessageDigest {
            return extraData.hash(hasher)
        }

        override fun extraDataSec(): Code? = extraData
        override fun extraData(): ByteArray? = extraData.code.id()
    }

    data class CodeSection(val code: Code) : Section() {
        override fun hash(hasher: MessageDigest): MessageDigest {
            return code.hash(hasher)
        }

        override fun codeSec(): Code? = code
    }

    data class AuthorizationSection(val authorization: Authorization) : Section() {
        override fun hash(hasher: MessageDigest): MessageDigest {
            return authorization.hash(hasher)
        }
    }

    data class HeaderSection(val header: Header) : Section() {
        override fun hash(hasher: MessageDigest): MessageDigest {
            return header.hash(hasher)
        }

        override fun header(): Header = header
    }
}

data class Hash(val value: ByteArray) {
    override fun toString(): String {
        return value.joinToString("") { "%02x".format(it) }
    }
}

interface Hashable {
    fun hash(hasher: MessageDigest): MessageDigest
}

data class Data(val content: String) : Hashable {
    override fun hash(hasher: MessageDigest): MessageDigest {
        hasher.update(content.toByteArray())
        return hasher
    }
}

data class Code(val code: CodeContent) : Hashable {
    override fun hash(hasher: MessageDigest): MessageDigest {
        return code.hash(hasher)
    }
}

data class CodeContent(val id: ByteArray) : Hashable {
    override fun hash(hasher: MessageDigest): MessageDigest {
        hasher.update(id) // `id` 바이트 배열을 해시에 추가
        return hasher
    }

    fun id(): ByteArray = id
}

data class Authorization(val signature: String) : Hashable {
    override fun hash(hasher: MessageDigest): MessageDigest {
        hasher.update(signature.toByteArray())
        return hasher
    }
}

//data class Transaction(val data: String) {
//    fun serializeToByteArray(): ByteArray {
//        return data.toByteArray()
//    }
//}
//

sealed class TxType {

    object Raw : TxType()

    data class Wrapper(val payload: WrapperTx) : TxType()

    data class Protocol(val payload: ProtocolTx) : TxType()

//    fun hash(hasher: MessageDigest): MessageDigest {
//        hasher.update(this.serializeToByteArray())
//        return hasher
//    }

//    private fun serializeToByteArray(): ByteArray {
//        return when (this) {
//            is Raw -> "Raw".toByteArray()
//            is Wrapper -> payload.serializeToByteArray()
//            is Protocol -> payload.serializeToByteArray()
//        }
//    }
}

//data class WrapperTx(val data: String) {
//    fun serializeToByteArray(): ByteArray {
//        return data.toByteArray()
//    }
//}

data class ProtocolTx(val data: String) {
    fun serializeToByteArray(): ByteArray {
        return data.toByteArray()
    }
}
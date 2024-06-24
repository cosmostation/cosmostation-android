package wannabit.io.cosmostaion.evm

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


class StructuredData {
    class Entry @JsonCreator constructor(
        @param:JsonProperty(value = "name") val name: String,
        @param:JsonProperty(value = "type") val type: String
    )

    class EIP712Domain @JsonCreator constructor(
        @param:JsonProperty(value = "name") val name: String,
        @param:JsonProperty(value = "version") val version: String,
        @param:JsonProperty(value = "chainId") val chainId: String,
        @param:JsonProperty(value = "verifyingContract") val verifyingContract: String,
        @param:JsonProperty(value = "salt") val salt: String?
    )

    class EIP712Message @JsonCreator constructor(
        @param:JsonProperty(value = "types") val types: HashMap<String, List<Entry>>,
        @param:JsonProperty(value = "primaryType") val primaryType: String,
        @param:JsonProperty(value = "message") val message: Any,
        @param:JsonProperty(value = "domain") val domain: EIP712Domain
    ) {

        override fun toString(): String {
            return ("EIP712Message{"
                    + "primaryType='"
                    + primaryType
                    + '\''
                    + ", message='"
                    + message
                    + '\''
                    + '}')
        }
    }
}
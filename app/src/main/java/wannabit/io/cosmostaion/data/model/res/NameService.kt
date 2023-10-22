package wannabit.io.cosmostaion.data.model.res

import java.io.Serializable

class NameService(
    var type: NameServiceType,
    var name: String,
    var address: String
) : Serializable {

    enum class NameServiceType {
        STARNAME, ICNS, STARGAZE, ARCHWAY, ICNS_STARGAZE, ICNS_ARCHWAY
    }
}
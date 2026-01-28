package wannabit.io.cosmostaion.data.model.res

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
class NameService(
    var type: NameServiceType, var name: String, var address: String
) : Serializable, Parcelable {

    enum class NameServiceType {
        ICNS, STARGAZE, ARCHWAY, ENS
    }
}
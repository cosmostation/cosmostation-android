package wannabit.io.cosmostaion.network.res.neutron

import android.os.Parcel
import android.os.Parcelable

data class ResPairData(
    val id: Int, val chain: String, val router_address: String, val factory_address: String, val contract_address: String, val total_share: String, val pairs: List<Pair>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(), parcel.readString()!!, parcel.readString()!!, parcel.readString()!!, parcel.readString()!!, parcel.readString()!!, parcel.createTypedArrayList(Pair)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(chain)
        parcel.writeString(router_address)
        parcel.writeString(factory_address)
        parcel.writeString(contract_address)
        parcel.writeString(total_share)
        parcel.writeTypedList(pairs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResPairData> {
        override fun createFromParcel(parcel: Parcel): ResPairData {
            return ResPairData(parcel)
        }

        override fun newArray(size: Int): Array<ResPairData?> {
            return arrayOfNulls(size)
        }
    }
}

data class Pair(
    val type: String,
    val address: String,
    val denom: String,
    val amount: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!, parcel.readString()!!, parcel.readString()!!, parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(address)
        parcel.writeString(denom)
        parcel.writeString(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pair> {
        override fun createFromParcel(parcel: Parcel): Pair {
            return Pair(parcel)
        }

        override fun newArray(size: Int): Array<Pair?> {
            return arrayOfNulls(size)
        }
    }
}

data class ResSwapRateData(val return_amount: String, val spread_amount: String, val commission_amount: String)

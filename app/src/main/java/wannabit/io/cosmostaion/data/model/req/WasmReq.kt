package wannabit.io.cosmostaion.data.model.req

class ICNSInfoReq(icns: String?) {
    var address_by_icns: AddressByIcns

    init {
        address_by_icns = AddressByIcns(icns)
    }

    inner class AddressByIcns(var icns: String?)
}
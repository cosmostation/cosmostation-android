package wannabit.io.cosmostaion.data.model.req

class ICNSInfoReq(icns: String?) {
    private var address_by_icns: AddressByIcns

    init {
        address_by_icns = AddressByIcns(icns)
    }

    inner class AddressByIcns(var icns: String?)
}

class NSStargazeInfoReq(name: String?) {
    var associated_address: AssociatedAddress

    init {
        associated_address = AssociatedAddress(name)
    }

    inner class AssociatedAddress(var name: String?)
}

data class NSArchwayReq(val resolve_record: ResolveRecord?)
data class ResolveRecord(val name: String?)
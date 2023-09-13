package wannabit.io.cosmostaion.data.model

class Cw20Balance(address: String?) {
    var balance: BalanceReq = BalanceReq(address)

    inner class BalanceReq(address: String?) {
        val address = address
    }
}
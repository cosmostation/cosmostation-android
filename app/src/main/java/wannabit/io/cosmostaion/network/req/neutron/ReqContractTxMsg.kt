package wannabit.io.cosmostaion.network.req.neutron

data class BondReq(val bond: Bond)
class Bond

data class UnbondReq(val unbond: Unbond)
data class Unbond(val amount: String?)

data class VoteReq(val vote: Vote)
data class Vote(val proposal_id: Int?, val vote: String?)

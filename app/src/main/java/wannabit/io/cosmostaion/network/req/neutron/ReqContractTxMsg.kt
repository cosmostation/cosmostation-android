package wannabit.io.cosmostaion.network.req.neutron

data class BondReq(val bond: Bond)
class Bond

data class UnbondReq(val unbond: Unbond)
data class Unbond(val amount: String?)

data class VoteReq(val vote: Vote)
data class Vote(val proposal_id: Int?, val vote: String?)

data class MultiVoteReq(val vote: MultiVote?)
data class MultiVote(val proposal_id: Int?, val vote: WeightVote?)
data class WeightVote(val option_id: Int?)

data class SwapReq(val swap: Swap)
data class Swap(val belief_price:String, val offer_asset: Offer)
data class Offer(val info: InfoData, val amount: String)
data class InfoData(val native_token: NativeData)
data class NativeData(val denom: String)

data class SwapMsg(val swap: SwapBelief)
data class SwapBelief(val belief_price: String)

data class ContractSwapReq(val send: Send)
data class Send(val amount: String, val contract: String, val msg: String)
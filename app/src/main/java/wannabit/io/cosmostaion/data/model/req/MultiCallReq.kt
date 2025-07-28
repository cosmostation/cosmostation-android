package wannabit.io.cosmostaion.data.model.req

import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.Bool
import org.web3j.abi.datatypes.DynamicBytes
import org.web3j.abi.datatypes.DynamicStruct

class Call(
    val target: Address,
    allowFailure: Bool,
    callData: DynamicBytes
) : DynamicStruct(target, allowFailure, callData)

class Result(
    val success: Bool,
    returnData: DynamicBytes
) : DynamicStruct(success, returnData)
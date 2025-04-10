package wannabit.io.cosmostaion.sign

import android.util.Base64.DEFAULT
import android.util.Base64.encode
import com.cosmos.bank.v1beta1.TxProto.MsgSend
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.crypto.secp256k1.KeysProto.PubKey
import com.cosmos.distribution.v1beta1.DistributionProto.DelegationDelegatorReward
import com.cosmos.distribution.v1beta1.TxProto.MsgSetWithdrawAddress
import com.cosmos.distribution.v1beta1.TxProto.MsgWithdrawDelegatorReward
import com.cosmos.gov.v1beta1.TxProto
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.cosmos.staking.v1beta1.TxProto.MsgBeginRedelegate
import com.cosmos.staking.v1beta1.TxProto.MsgCancelUnbondingDelegation
import com.cosmos.staking.v1beta1.TxProto.MsgDelegate
import com.cosmos.staking.v1beta1.TxProto.MsgUndelegate
import com.cosmos.tx.signing.v1beta1.SigningProto
import com.cosmos.tx.v1beta1.ServiceProto.BroadcastMode
import com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest
import com.cosmos.tx.v1beta1.ServiceProto.SimulateRequest
import com.cosmos.tx.v1beta1.TxProto.AuthInfo
import com.cosmos.tx.v1beta1.TxProto.Fee
import com.cosmos.tx.v1beta1.TxProto.ModeInfo
import com.cosmos.tx.v1beta1.TxProto.SignDoc
import com.cosmos.tx.v1beta1.TxProto.SignerInfo
import com.cosmos.tx.v1beta1.TxProto.TxBody
import com.cosmos.tx.v1beta1.TxProto.TxRaw
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.ethermint.crypto.v1.ethsecp256k1.KeysProto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.gno.bank.BankProto
import com.gno.vm.VmProto.MsgCall
import com.google.gson.Gson
import com.google.protobuf.Any
import com.google.protobuf.ByteString
import com.ibc.applications.transfer.v1.TxProto.MsgTransfer
import com.kava.cdp.v1beta1.TxProto.MsgCreateCDP
import com.kava.cdp.v1beta1.TxProto.MsgDeposit
import com.kava.cdp.v1beta1.TxProto.MsgDrawDebt
import com.kava.cdp.v1beta1.TxProto.MsgRepayDebt
import com.kava.cdp.v1beta1.TxProto.MsgWithdraw
import com.kava.hard.v1beta1.TxProto.MsgBorrow
import com.kava.hard.v1beta1.TxProto.MsgRepay
import com.kava.incentive.v1beta1.QueryProto.QueryRewardsResponse
import com.kava.incentive.v1beta1.TxProto.MsgClaimDelegatorReward
import com.kava.incentive.v1beta1.TxProto.MsgClaimEarnReward
import com.kava.incentive.v1beta1.TxProto.MsgClaimHardReward
import com.kava.incentive.v1beta1.TxProto.MsgClaimSwapReward
import com.kava.incentive.v1beta1.TxProto.MsgClaimUSDXMintingReward
import com.kava.incentive.v1beta1.TxProto.Selection
import com.tm2.tx.TxProto.Tx
import com.tm2.tx.TxProto.TxFee
import com.tm2.tx.TxProto.TxSignature
import net.i2p.crypto.eddsa.EdDSAEngine
import net.i2p.crypto.eddsa.EdDSAPrivateKey
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.Sha256Hash
import org.bouncycastle.jcajce.provider.digest.Blake2b
import org.bouncycastle.util.Strings
import org.bouncycastle.util.encoders.Base64
import org.bouncycastle.util.encoders.Base64.encode
import org.bouncycastle.util.encoders.Base64.toBase64String
import org.bouncycastle.util.encoders.Hex
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Sign
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainAtomone
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBabylon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainGovgen
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInjective
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Secp
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_REWARD_CONTRACT_ADDRESS
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.evmClass.ChainStratosEvm
import wannabit.io.cosmostaion.chain.fetcher.delegatorRewardDenoms
import wannabit.io.cosmostaion.chain.fetcher.earnRewardDenoms
import wannabit.io.cosmostaion.chain.fetcher.hardRewardDenoms
import wannabit.io.cosmostaion.chain.fetcher.hasUsdxMinting
import wannabit.io.cosmostaion.chain.fetcher.swapRewardDenoms
import wannabit.io.cosmostaion.chain.testnetClass.ChainArtelaTestnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.BaseConstant.COSMOS_AUTH_TYPE_STDTX
import wannabit.io.cosmostaion.common.BaseConstant.COSMOS_KEY_TYPE_PUBLIC
import wannabit.io.cosmostaion.common.BaseConstant.ETHERMINT_KEY_TYPE_PUBLIC
import wannabit.io.cosmostaion.common.BaseConstant.OK_MSG_TYPE_ADD_SHARES
import wannabit.io.cosmostaion.common.BaseConstant.OK_MSG_TYPE_DEPOSIT
import wannabit.io.cosmostaion.common.BaseConstant.OK_MSG_TYPE_TRANSFER
import wannabit.io.cosmostaion.common.BaseConstant.OK_MSG_TYPE_WITHDRAW
import wannabit.io.cosmostaion.common.ByteUtils.integerToBytes
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.data.model.req.BroadcastReq
import wannabit.io.cosmostaion.data.model.req.ClaimReq
import wannabit.io.cosmostaion.data.model.req.ClaimRewards
import wannabit.io.cosmostaion.data.model.req.LCoin
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.Msg
import wannabit.io.cosmostaion.data.model.req.Msgs
import wannabit.io.cosmostaion.data.model.req.SignPayload
import wannabit.io.cosmostaion.data.model.req.Signature
import wannabit.io.cosmostaion.data.model.req.StdSignMsg
import wannabit.io.cosmostaion.data.model.req.StdTx
import wannabit.io.cosmostaion.data.model.req.StdTxValue
import wannabit.io.cosmostaion.data.model.req.Value
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.TreeMap
import java.util.concurrent.TimeUnit

object Signer {

    suspend fun genBroadcast(
        msgs: MutableList<Any>, fee: Fee?, memo: String, selectedChain: BaseChain
    ): BroadcastTxRequest? {
        return signBroadcastTx(msgs, fee, memo, selectedChain)
    }

    suspend fun genSimulate(
        msgs: MutableList<Any>, fee: Fee?, memo: String, selectedChain: BaseChain
    ): SimulateRequest? {
        return signSimulTx(msgs, fee, memo, selectedChain)
    }

    fun sendMsg(msgSend: MsgSend?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/cosmos.bank.v1beta1.MsgSend")
                .setValue(msgSend?.toByteString()).build()
        )
        return msgAnys
    }

    fun thorchainSendMsg(msgSend: com.types.MsgSendProto.MsgSend?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/types.MsgSend").setValue(msgSend?.toByteString()).build()
        )
        return msgAnys
    }

    private fun gnoSendMsg(msgSend: BankProto.MsgSend?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/bank.MsgSend").setValue(msgSend?.toByteString()).build()
        )
        return msgAnys
    }

    suspend fun genIbcSendBroadcast(
        msgTransfer: MsgTransfer?, fee: Fee?, memo: String, selectedChain: BaseChain
    ): BroadcastTxRequest? {
        return signBroadcastTx(ibcSendMsg(msgTransfer), fee, memo, selectedChain)
    }

    suspend fun genIbcSendSimulate(
        msgTransfer: MsgTransfer?, fee: Fee?, memo: String, selectedChain: BaseChain
    ): SimulateRequest? {
        return signSimulTx(ibcSendMsg(msgTransfer), fee, memo, selectedChain)
    }

    fun ibcSendMsg(msgTransfer: MsgTransfer?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/ibc.applications.transfer.v1.MsgTransfer")
                .setValue(msgTransfer?.toByteString()).build()
        )
        return msgAnys
    }

    fun wasmMsg(msgWasms: MutableList<MsgExecuteContract?>?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgWasms?.forEach { msgWasm ->
            msgAnys.add(
                Any.newBuilder().setTypeUrl("/cosmwasm.wasm.v1.MsgExecuteContract")
                    .setValue(msgWasm?.toByteString()).build()
            )
        }
        return msgAnys
    }

    fun msgCallMsg(msgCall: MsgCall?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/vm.m_call").setValue(msgCall?.toByteString()).build()
        )
        return msgAnys
    }

    fun delegateMsg(msgDelegate: MsgDelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate")
                .setValue(msgDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun initiaDelegateMsg(msgDelegate: com.initia.mstaking.v1.TxProto.MsgDelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/initia.mstaking.v1.MsgDelegate")
                .setValue(msgDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun zenrockDelegateMsg(msgDelegate: com.zrchain.validation.TxProto.MsgDelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/zrchain.validation.MsgDelegate")
                .setValue(msgDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun babylonDelegateMsg(msgDelegate: com.babylon.epoching.v1.TxProto.MsgWrappedDelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/babylon.epoching.v1.MsgWrappedDelegate")
                .setValue(msgDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun unDelegateMsg(msgUndelegate: MsgUndelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgUndelegate")
                .setValue(msgUndelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun initiaUnDelegateMsg(msgUndelegate: com.initia.mstaking.v1.TxProto.MsgUndelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/initia.mstaking.v1.MsgUndelegate")
                .setValue(msgUndelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun zenrockUnDelegateMsg(msgUndelegate: com.zrchain.validation.TxProto.MsgUndelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/zrchain.validation.MsgUndelegate")
                .setValue(msgUndelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun babylonUnDelegateMsg(msgDelegate: com.babylon.epoching.v1.TxProto.MsgWrappedUndelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/babylon.epoching.v1.MsgWrappedUndelegate")
                .setValue(msgDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun reDelegateMsg(msgReDelegate: MsgBeginRedelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgBeginRedelegate")
                .setValue(msgReDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun initiaReDelegateMsg(msgReDelegate: com.initia.mstaking.v1.TxProto.MsgBeginRedelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/initia.mstaking.v1.MsgBeginRedelegate")
                .setValue(msgReDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun zenrockReDelegateMsg(msgReDelegate: com.zrchain.validation.TxProto.MsgBeginRedelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/zrchain.validation.MsgBeginRedelegate")
                .setValue(msgReDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun babylonReDelegateMsg(msgDelegate: com.babylon.epoching.v1.TxProto.MsgWrappedBeginRedelegate?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/babylon.epoching.v1.MsgWrappedBeginRedelegate")
                .setValue(msgDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun cancelUnbondingMsg(msgCancelUnbondingDelegation: MsgCancelUnbondingDelegation?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgCancelUnbondingDelegation")
                .setValue(msgCancelUnbondingDelegation?.toByteString()).build()
        )
        return msgAnys
    }

    fun initiaCancelUnbondingMsg(msgCancelUnbondingDelegation: com.initia.mstaking.v1.TxProto.MsgCancelUnbondingDelegation?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/initia.mstaking.v1.MsgCancelUnbondingDelegation")
                .setValue(msgCancelUnbondingDelegation?.toByteString()).build()
        )
        return msgAnys
    }

    fun zenrockCancelUnbondingMsg(msgCancelUnbondingDelegation: com.zrchain.validation.TxProto.MsgCancelUnbondingDelegation?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/zrchain.validation.MsgCancelUnbondingDelegation")
                .setValue(msgCancelUnbondingDelegation?.toByteString()).build()
        )
        return msgAnys
    }

    fun babylonCancelUnbondingMsg(msgDelegate: com.babylon.epoching.v1.TxProto.MsgWrappedCancelUnbondingDelegation?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/babylon.epoching.v1.MsgWrappedCancelUnbondingDelegation")
                .setValue(msgDelegate?.toByteString()).build()
        )
        return msgAnys
    }

    fun claimStakingRewardMsg(
        selectedChain: BaseChain,
        rewards: MutableList<DelegationDelegatorReward?>,
        isClaimAll: Boolean
    ): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()

        if (selectedChain is ChainBabylon && isClaimAll && selectedChain.babylonFetcher?.btcRewards?.isNotEmpty() == true) {
            val babylonIncentiveMsg =
                com.babylon.incentive.TxProto.MsgWithdrawReward.newBuilder().setType("BTC_STAKER")
                    .setAddress(selectedChain.address).build()
            val anyMsg = Any.newBuilder().setTypeUrl("/babylon.incentive.MsgWithdrawReward")
                .setValue(babylonIncentiveMsg.toByteString()).build()
            msgAnys.add(anyMsg)
        }

        rewards.forEach { reward ->
            val claimMsg =
                MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(selectedChain.address)
                    .setValidatorAddress(reward?.validatorAddress).build()
            val anyMsg = Any.newBuilder()
                .setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward")
                .setValue(claimMsg.toByteString()).build()
            msgAnys.add(anyMsg)
        }
        return msgAnys
    }

    fun compoundingMsg(
        selectedChain: BaseChain, rewards: MutableList<DelegationDelegatorReward?>
    ): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        rewards.forEach { reward ->
            val claimMsg =
                MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(selectedChain.address)
                    .setValidatorAddress(reward?.validatorAddress).build()
            val anyMsg = Any.newBuilder()
                .setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward")
                .setValue(claimMsg.toByteString()).build()
            msgAnys.add(anyMsg)

            val rewardCoin =
                reward?.rewardList?.firstOrNull { it.denom == selectedChain.stakeDenom }
            val delegateCoin = CoinProto.Coin.newBuilder().setDenom(rewardCoin?.denom).setAmount(
                rewardCoin?.amount?.toBigDecimal()?.movePointLeft(18)
                    ?.setScale(0, RoundingMode.DOWN)?.toPlainString()
            )

            val deleAnyMsg = when (selectedChain) {
                is ChainInitiaTestnet -> {
                    val delegateMsg = com.initia.mstaking.v1.TxProto.MsgDelegate.newBuilder()
                        .setDelegatorAddress(selectedChain.address)
                        .setValidatorAddress(reward?.validatorAddress).addAmount(delegateCoin)
                        .build()

                    Any.newBuilder().setTypeUrl("/initia.mstaking.v1.MsgDelegate")
                        .setValue(delegateMsg.toByteString()).build()
                }

                is ChainZenrock -> {
                    val delegateMsg = com.zrchain.validation.TxProto.MsgDelegate.newBuilder()
                        .setDelegatorAddress(selectedChain.address)
                        .setValidatorAddress(reward?.validatorAddress).setAmount(delegateCoin)
                        .build()

                    Any.newBuilder().setTypeUrl("/zrchain.validation.MsgDelegate")
                        .setValue(delegateMsg.toByteString()).build()
                }

                else -> {
                    val delegateMsg =
                        MsgDelegate.newBuilder().setDelegatorAddress(selectedChain.address)
                            .setValidatorAddress(reward?.validatorAddress).setAmount(delegateCoin)
                            .build()

                    if (selectedChain is ChainBabylon) {
                        val wrappedMsgDelegate =
                            com.babylon.epoching.v1.TxProto.MsgWrappedDelegate.newBuilder()
                                .setMsg(delegateMsg).build()
                        Any.newBuilder().setTypeUrl("/babylon.epoching.v1.MsgWrappedDelegate")
                            .setValue(wrappedMsgDelegate.toByteString()).build()
                    } else {
                        Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate")
                            .setValue(delegateMsg.toByteString()).build()
                    }
                }
            }
            msgAnys.add(deleAnyMsg)
        }
        return msgAnys
    }

    fun contractClaimReward(selectedChain: BaseChain, toValidator: Validator?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()

        val wasmMsgs = mutableListOf<MsgExecuteContract?>()
        val jsonData = Gson().toJson(ClaimReq(ClaimRewards()))
        val msg = ByteString.copyFromUtf8(jsonData)
        wasmMsgs.add(
            MsgExecuteContract.newBuilder().setSender(selectedChain.address)
                .setContract(NEUTRON_REWARD_CONTRACT_ADDRESS).setMsg(msg).build()
        )
        wasmMsgs.forEach { msgWasm ->
            msgAnys.add(
                Any.newBuilder().setTypeUrl("/cosmwasm.wasm.v1.MsgExecuteContract")
                    .setValue(msgWasm?.toByteString()).build()
            )
        }

        val delegateCoin = CoinProto.Coin.newBuilder().setDenom(selectedChain.stakeDenom).setAmount(
            (selectedChain as ChainNeutron).neutronFetcher()?.neutronRewards.toString()
        ).build()

        val msgDelegate = MsgDelegate.newBuilder().setDelegatorAddress(selectedChain.address)
            .setValidatorAddress(toValidator?.operatorAddress).setAmount(delegateCoin).build()
        val delegateMsg = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate")
            .setValue(msgDelegate.toByteString()).build()
        msgAnys.add(delegateMsg)

        return msgAnys
    }

    fun changeRewardAddress(msgSetWithdrawAddress: MsgSetWithdrawAddress?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgSetWithdrawAddress")
                .setValue(msgSetWithdrawAddress?.toByteString()).build()
        )
        return msgAnys
    }

    fun voteMsg(chain: BaseChain, msgVotes: MutableList<TxProto.MsgVote?>?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        val typeUrl = when (chain) {
            is ChainGovgen -> "/govgen.gov.v1beta1.MsgVote"
            is ChainAtomone -> "/atomone.gov.v1beta1.MsgVote"
            else -> "/cosmos.gov.v1beta1.MsgVote"
        }
        msgVotes?.forEach { msgVote ->
            val anyMsg =
                Any.newBuilder().setTypeUrl(typeUrl).setValue(msgVote?.toByteString()).build()
            msgAnys.add(anyMsg)
        }
        return msgAnys
    }

    fun claimIncentiveMsg(
        chain: BaseChain, incentive: QueryRewardsResponse
    ): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()

        if (incentive.hasUsdxMinting()) {
            val mintMsg = MsgClaimUSDXMintingReward.newBuilder().setSender(chain.address)
                .setMultiplierName("large").build()
            val anyMsg =
                Any.newBuilder().setTypeUrl("/kava.incentive.v1beta1.MsgClaimUSDXMintingReward")
                    .setValue(mintMsg.toByteString()).build()
            msgAnys.add(anyMsg)
        }

        if (incentive.hardRewardDenoms().size > 0) {
            val denomToClaims: MutableList<Selection> = mutableListOf()
            incentive.hardRewardDenoms().forEach { denom ->
                denomToClaims.add(
                    Selection.newBuilder().setDenom(denom).setMultiplierName("large").build()
                )
            }
            val hardMsg = MsgClaimHardReward.newBuilder().setSender(chain.address)
                .addAllDenomsToClaim(denomToClaims).build()
            val anyMsg = Any.newBuilder().setTypeUrl("/kava.incentive.v1beta1.MsgClaimHardReward")
                .setValue(hardMsg.toByteString()).build()
            msgAnys.add(anyMsg)
        }

        if (incentive.delegatorRewardDenoms().size > 0) {
            val denomToClaims: MutableList<Selection> = mutableListOf()
            incentive.delegatorRewardDenoms().forEach { denom ->
                denomToClaims.add(
                    Selection.newBuilder().setDenom(denom).setMultiplierName("large").build()
                )
            }
            val delegateMsg = MsgClaimDelegatorReward.newBuilder().setSender(chain.address)
                .addAllDenomsToClaim(denomToClaims).build()
            val anyMsg =
                Any.newBuilder().setTypeUrl("/kava.incentive.v1beta1.MsgClaimDelegatorReward")
                    .setValue(delegateMsg.toByteString()).build()
            msgAnys.add(anyMsg)
        }

        if (incentive.swapRewardDenoms().size > 0) {
            val denomToClaims: MutableList<Selection> = mutableListOf()
            incentive.swapRewardDenoms().forEach { denom ->
                denomToClaims.add(
                    Selection.newBuilder().setDenom(denom).setMultiplierName("large").build()
                )
            }
            val swapMsg = MsgClaimSwapReward.newBuilder().setSender(chain.address)
                .addAllDenomsToClaim(denomToClaims).build()
            val anyMsg = Any.newBuilder().setTypeUrl("/kava.incentive.v1beta1.MsgClaimSwapReward")
                .setValue(swapMsg.toByteString()).build()
            msgAnys.add(anyMsg)
        }

        if (incentive.earnRewardDenoms().size > 0) {
            val denomToClaims: MutableList<Selection> = mutableListOf()
            incentive.earnRewardDenoms().forEach { denom ->
                denomToClaims.add(
                    Selection.newBuilder().setDenom(denom).setMultiplierName("large").build()
                )
            }
            val earnMsg = MsgClaimEarnReward.newBuilder().setSender(chain.address)
                .addAllDenomsToClaim(denomToClaims).build()
            val anyMsg = Any.newBuilder().setTypeUrl("/kava.incentive.v1beta1.MsgClaimEarnReward")
                .setValue(earnMsg.toByteString()).build()
            msgAnys.add(anyMsg)
        }
        return msgAnys
    }

    fun mintCreateMsg(msgCreateMint: MsgCreateCDP?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgCreateCDP")
                .setValue(msgCreateMint?.toByteString()).build()
        )
        return msgAnys
    }

    fun mintDepositMsg(msgDeposit: MsgDeposit?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgDeposit")
                .setValue(msgDeposit?.toByteString()).build()
        )
        return msgAnys
    }

    fun mintWithdrawMsg(msgWithdraw: MsgWithdraw?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgWithdraw")
                .setValue(msgWithdraw?.toByteString()).build()
        )
        return msgAnys
    }

    fun mintBorrowMsg(msgDrawDebt: MsgDrawDebt?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgDrawDebt")
                .setValue(msgDrawDebt?.toByteString()).build()
        )
        return msgAnys
    }

    fun mintRepayMsg(msgRepayDebt: MsgRepayDebt?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgRepayDebt")
                .setValue(msgRepayDebt?.toByteString()).build()
        )
        return msgAnys
    }

    fun lendDepositMsg(msgDeposit: com.kava.hard.v1beta1.TxProto.MsgDeposit?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.hard.v1beta1.MsgDeposit")
                .setValue(msgDeposit?.toByteString()).build()
        )
        return msgAnys
    }

    fun lendWithdrawMsg(msgWithdraw: com.kava.hard.v1beta1.TxProto.MsgWithdraw?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.hard.v1beta1.MsgWithdraw")
                .setValue(msgWithdraw?.toByteString()).build()
        )
        return msgAnys
    }

    fun lendBorrowMsg(msgBorrow: MsgBorrow?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.hard.v1beta1.MsgBorrow")
                .setValue(msgBorrow?.toByteString()).build()
        )
        return msgAnys
    }

    fun lendRepayMsg(msgRepay: MsgRepay?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.hard.v1beta1.MsgRepay")
                .setValue(msgRepay?.toByteString()).build()
        )
        return msgAnys
    }

    fun poolDepositMsg(msgDeposit: com.kava.swap.v1beta1.TxProto.MsgDeposit?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.swap.v1beta1.MsgDeposit")
                .setValue(msgDeposit?.toByteString()).build()
        )
        return msgAnys
    }

    fun poolWithdrawMsg(msgWithdraw: com.kava.swap.v1beta1.TxProto.MsgWithdraw?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.swap.v1beta1.MsgWithdraw")
                .setValue(msgWithdraw?.toByteString()).build()
        )
        return msgAnys
    }

    fun earnDepositMsg(msgDeposit: com.kava.router.v1beta1.TxProto.MsgDelegateMintDeposit?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.router.v1beta1.MsgDelegateMintDeposit")
                .setValue(msgDeposit?.toByteString()).build()
        )
        return msgAnys
    }

    fun earnWithdrawMsg(msgWithdraw: com.kava.router.v1beta1.TxProto.MsgWithdrawBurn?): MutableList<Any> {
        val msgAnys: MutableList<Any> = mutableListOf()
        msgAnys.add(
            Any.newBuilder().setTypeUrl("/kava.router.v1beta1.MsgWithdrawBurn")
                .setValue(msgWithdraw?.toByteString()).build()
        )
        return msgAnys
    }

    fun setFee(position: Int, txFee: Fee?): Fee? {
        val feeDenom = txFee?.getAmount(0)?.denom
        val feeAmount = txFee?.getAmount(0)?.amount

        var result: Fee? = null
        txFee?.gasLimit?.let { gasLimit ->
            when (position) {
                0 -> result = Fee.newBuilder().setGasLimit(gasLimit).addAmount(
                    CoinProto.Coin.newBuilder().setDenom(feeDenom).setAmount(feeAmount).build()
                ).build()

                1 -> result = Fee.newBuilder().setGasLimit(gasLimit).addAmount(
                    CoinProto.Coin.newBuilder().setDenom(feeDenom).setAmount(
                        feeAmount?.toBigDecimal()?.multiply(BigDecimal("1.2"))
                            ?.setScale(0, RoundingMode.DOWN).toString()
                    ).build()
                ).build()

                2 -> result = Fee.newBuilder().setGasLimit(gasLimit).addAmount(
                    CoinProto.Coin.newBuilder().setDenom(feeDenom).setAmount(
                        feeAmount?.toBigDecimal()?.multiply(BigDecimal("1.5"))
                            ?.setScale(0, RoundingMode.DOWN).toString()
                    ).build()
                ).build()

                3 -> result = Fee.newBuilder().setGasLimit(gasLimit).addAmount(
                    CoinProto.Coin.newBuilder().setDenom(feeDenom).setAmount(
                        feeAmount?.toBigDecimal()?.multiply(BigDecimal("2"))
                            ?.setScale(0, RoundingMode.DOWN).toString()
                    ).build()
                ).build()
            }
        }
        return result
    }

    private fun generateGrpcPubKeyFromPriv(chain: BaseChain?, privateKey: String): Any {
        val ecKey = ECKey.fromPrivate(BigInteger(privateKey, 16))
        return if (chain is ChainGnoTestnet) {
            val pubKey = com.tm2.tx.TxProto.PubKeySecp256k1.newBuilder()
                .setKey(ByteString.copyFrom(ecKey.pubKey)).build()
            Any.newBuilder().setTypeUrl("/tm.PubKeySecp256k1").setValue(pubKey.toByteString())
                .build()

        } else if (chain is ChainInjective) {
            val pubKey = com.injective.crypto.v1beta1.ethsecp256k1.KeysProto.PubKey.newBuilder()
                .setKey(ByteString.copyFrom(ecKey.pubKey)).build()
            Any.newBuilder().setTypeUrl("/injective.crypto.v1beta1.ethsecp256k1.PubKey")
                .setValue(pubKey.toByteString()).build()

        } else if (chain is ChainArtelaTestnet) {
            val pubKey = com.artela.crypto.v1.ethsecp256k1.KeysProto.PubKey.newBuilder().setKey(
                ByteString.copyFrom(ecKey.pubKey)
            ).build()
            Any.newBuilder().setTypeUrl("/artela.crypto.v1.ethsecp256k1.PubKey")
                .setValue(pubKey.toByteString()).build()

        } else if (chain is ChainStratosEvm) {
            val pubKey = com.stratos.crypto.v1.ethsecp256k1.KeysProto.PubKey.newBuilder()
                .setKey(ByteString.copyFrom(ecKey.pubKey)).build()
            Any.newBuilder().setTypeUrl("/stratos.crypto.v1.ethsecp256k1.PubKey")
                .setValue(pubKey.toByteString()).build()

        } else if (chain?.accountKeyType?.pubkeyType == PubKeyType.ETH_KECCAK256) {
            val pubKey =
                KeysProto.PubKey.newBuilder().setKey(ByteString.copyFrom(ecKey.pubKey)).build()
            Any.newBuilder().setTypeUrl("/ethermint.crypto.v1.ethsecp256k1.PubKey")
                .setValue(pubKey.toByteString()).build()

        } else {
            val pubKey = PubKey.newBuilder().setKey(ByteString.copyFrom(ecKey.pubKey)).build()
            Any.newBuilder().setTypeUrl("/cosmos.crypto.secp256k1.PubKey")
                .setValue(pubKey.toByteString()).build()
        }
    }

    private fun grpcByteSignature(selectedChain: BaseChain?, toSignByte: ByteArray?): ByteArray {
        val sigData = ByteArray(64)
        if (selectedChain?.accountKeyType?.pubkeyType == PubKeyType.ETH_KECCAK256) {
            val sig = Sign.signMessage(toSignByte, ECKeyPair.create(selectedChain.privateKey))
            System.arraycopy(sig.r, 0, sigData, 0, 32)
            System.arraycopy(sig.s, 0, sigData, 32, 32)
        } else {
            val sha256Hash = Sha256Hash.hash(toSignByte)
            ECKey.fromPrivate(selectedChain?.privateKey)?.sign(Sha256Hash.wrap(sha256Hash))?.let {
                System.arraycopy(integerToBytes(it.r, 32), 0, sigData, 0, 32)
                System.arraycopy(integerToBytes(it.s, 32), 0, sigData, 32, 32)
            }
        }
        return sigData
    }

    private suspend fun signBroadcastTx(
        msgAnys: List<Any>?, fee: Fee?, memo: String, selectedChain: BaseChain
    ): BroadcastTxRequest? {
        selectedChain.cosmosFetcher()?.lastHeight()?.let { height ->
            val txBody = grpcTxBody(msgAnys, memo, height, selectedChain)
            val signerInfo = grpcSignerInfo(selectedChain)
            val authInfo = grpcAuthInfo(signerInfo, fee)
            val broadcastTx = grpcBroadcastTx(txBody, authInfo, selectedChain)
            return BroadcastTxRequest.newBuilder()
                .setModeValue(BroadcastMode.BROADCAST_MODE_SYNC.number)
                .setTxBytes(broadcastTx?.toByteString()).build()
        }
        return null
    }

    private suspend fun signSimulTx(
        msgAnys: List<Any>?, fee: Fee?, memo: String, selectedChain: BaseChain
    ): SimulateRequest? {
        selectedChain.cosmosFetcher()?.lastHeight()?.let { height ->
            val txBody = grpcTxBody(msgAnys, memo, height, selectedChain)
            val signerInfo = grpcSignerInfo(selectedChain)
            val authInfo = grpcAuthInfo(signerInfo, fee)
            val simulateTx = grpcSimulTx(txBody, authInfo)
            return SimulateRequest.newBuilder().setTxBytes(simulateTx?.toByteString()).build()
        }
        return null
    }

    fun signRpcSendBroadcastTx(
        msgSend: BankProto.MsgSend, fee: Fee?, memo: String, chain: BaseChain
    ): Tx {
        val msgs: MutableList<Msgs> = mutableListOf()
        val msg = Msgs(
            "/bank.MsgSend",
            from_address = chain.address,
            to_address = msgSend.toAddress,
            amount = msgSend.amount
        )
        msgs.add(msg)
        val txFee = TxFee.newBuilder().setGasWanted(fee?.gasLimit ?: 100000L)
            .setGasFee(fee?.getAmount(0)?.amount + fee?.getAmount(0)?.denom).build()
        val pubKey =
            generateGrpcPubKeyFromPriv(chain, ECKey.fromPrivate(chain.privateKey).privateKeyAsHex)

        val signPayload = SignPayload(
            chain.chainIdCosmos,
            (chain as ChainGnoTestnet).gnoRpcFetcher()?.gnoAccountNumber.toString(),
            chain.gnoRpcFetcher()?.gnoSequence.toString(),
            wannabit.io.cosmostaion.data.model.req.Fee(txFee.gasWanted.toString(), txFee.gasFee),
            msgs
        )

        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        val signPayloadDoc = mapper.writeValueAsString(
            mapper.readValue(signPayload.toJson(), TreeMap::class.java)
        )

        val sigByte = grpcByteSignature(chain, signPayloadDoc.toByteArray(StandardCharsets.UTF_8))
        val signatures =
            TxSignature.newBuilder().setPubKey(pubKey).setSignature(ByteString.copyFrom(sigByte))
                .build()

        val builder = Tx.newBuilder()
        gnoSendMsg(msgSend).forEach { msgAny ->
            builder.addMessages(msgAny)
        }
        return builder.setFee(txFee).addSignatures(signatures).setMemo(memo).build()
    }

    fun signRpcSendSimulateTx(
        msgSend: BankProto.MsgSend, fee: Fee?, memo: String, chain: BaseChain
    ): Tx {
        val msgs: MutableList<Msgs> = mutableListOf()
        val msg = Msgs(
            "/bank.MsgSend",
            from_address = chain.address,
            to_address = msgSend.toAddress,
            amount = msgSend.amount
        )
        msgs.add(msg)
        val txFee = TxFee.newBuilder().setGasWanted(fee?.gasLimit ?: 100000L)
            .setGasFee(fee?.getAmount(0)?.amount + fee?.getAmount(0)?.denom).build()
        val builder = Tx.newBuilder()
        gnoSendMsg(msgSend).forEach { msgAny ->
            builder.addMessages(msgAny)
        }

        return builder.setFee(txFee).setMemo(memo).addSignatures(TxSignature.newBuilder().build())
            .build()
    }

    fun signRpcCallBroadcastTx(
        msgCall: MsgCall?, fee: Fee?, memo: String, chain: BaseChain
    ): Tx {
        val msgs: MutableList<Msgs> = mutableListOf()
        val msg = Msgs(
            "/vm.m_call",
            send = "",
            caller = chain.address,
            pkg_path = msgCall?.pkgPath,
            func = "Transfer",
            args = listOf(msgCall?.getArgs(0).toString(), msgCall?.getArgs(1).toString())
        )
        msgs.add(msg)
        val txFee = TxFee.newBuilder().setGasWanted(fee?.gasLimit ?: 10000000L)
            .setGasFee(fee?.getAmount(0)?.amount + fee?.getAmount(0)?.denom).build()
        val pubKey =
            generateGrpcPubKeyFromPriv(chain, ECKey.fromPrivate(chain.privateKey).privateKeyAsHex)

        val signPayload = SignPayload(
            chain.chainIdCosmos,
            (chain as ChainGnoTestnet).gnoRpcFetcher()?.gnoAccountNumber.toString(),
            chain.gnoRpcFetcher()?.gnoSequence.toString(),
            wannabit.io.cosmostaion.data.model.req.Fee(txFee.gasWanted.toString(), txFee.gasFee),
            msgs
        )

        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        val signPayloadDoc = mapper.writeValueAsString(
            mapper.readValue(signPayload.toJson(), TreeMap::class.java)
        )

        val sigByte = grpcByteSignature(chain, signPayloadDoc.toByteArray(StandardCharsets.UTF_8))
        val signatures =
            TxSignature.newBuilder().setPubKey(pubKey).setSignature(ByteString.copyFrom(sigByte))
                .build()

        val builder = Tx.newBuilder()
        msgCallMsg(msgCall).forEach { msgAny ->
            builder.addMessages(msgAny)
        }
        return builder.setFee(txFee).addSignatures(signatures).setMemo(memo).build()
    }

    fun signRpcCallSimulateTx(
        msgCall: MsgCall?, fee: Fee?, memo: String, chain: BaseChain
    ): Tx {
        val msgs: MutableList<Msgs> = mutableListOf()
        val msg = Msgs(
            "/vm.m_call",
            send = "",
            caller = chain.address,
            pkg_path = msgCall?.pkgPath,
            func = "Transfer",
            args = listOf(msgCall?.getArgs(0).toString(), msgCall?.getArgs(1).toString())
        )
        msgs.add(msg)
        val txFee = TxFee.newBuilder().setGasWanted(fee?.gasLimit ?: 100000L)
            .setGasFee(fee?.getAmount(0)?.amount + fee?.getAmount(0)?.denom).build()
        val builder = Tx.newBuilder()
        msgCallMsg(msgCall).forEach { msgAny ->
            builder.addMessages(msgAny)
        }

        return builder.setFee(txFee).setMemo(memo).addSignatures(TxSignature.newBuilder().build())
            .build()
    }

    private fun grpcTxBody(
        msgsAny: List<Any>?, memo: String, height: Long, chain: BaseChain?
    ): TxBody? {
        val builder = TxBody.newBuilder()
        msgsAny?.forEach { msg ->
            builder.addMessages(msg)
        }
        return builder.setMemo(memo).setTimeoutHeight(height + chain!!.txTimeoutPadding()).build()
    }

    private fun grpcSignerInfo(
        chain: BaseChain?
    ): SignerInfo {
        val pubKey =
            generateGrpcPubKeyFromPriv(chain, ECKey.fromPrivate(chain?.privateKey).privateKeyAsHex)
        val singleMode =
            ModeInfo.Single.newBuilder().setMode(SigningProto.SignMode.SIGN_MODE_DIRECT).build()
        val modeInfo = ModeInfo.newBuilder().setSingle(singleMode).build()
        return SignerInfo.newBuilder().setPublicKey(pubKey).setModeInfo(modeInfo)
            .setSequence(chain?.cosmosFetcher()?.cosmosSequence ?: 0).build()
    }

    private fun grpcAuthInfo(signerInfo: SignerInfo?, fee: Fee?): AuthInfo? {
        fee?.getAmount(0)?.let {
            val feeCoin =
                CoinProto.Coin.newBuilder().setAmount(it.amount).setDenom(it.denom).build()
            val txFee = Fee.newBuilder().addAmount(feeCoin).setGasLimit(fee.gasLimit).build()
            return AuthInfo.newBuilder().setFee(txFee).addSignerInfos(signerInfo).build()
        }
        return null
    }

    private fun grpcBroadcastTx(
        txBody: TxBody?, authInfo: AuthInfo?, chain: BaseChain?
    ): TxRaw? {
        val signDoc = SignDoc.newBuilder().setBodyBytes(txBody?.toByteString())
            .setAuthInfoBytes(authInfo?.toByteString()).setChainId(chain?.chainIdCosmos)
            .setAccountNumber(
                chain?.cosmosFetcher()?.cosmosAccountNumber!!
            ).build()
        val sigByte = grpcByteSignature(chain, signDoc.toByteArray())
        return TxRaw.newBuilder().setBodyBytes(txBody?.toByteString())
            .setAuthInfoBytes(authInfo?.toByteString()).addSignatures(ByteString.copyFrom(sigByte))
            .build()
    }

    private fun grpcSimulTx(txBody: TxBody?, authInfo: AuthInfo?): TxRaw? {
        val sigByte = ByteArray(64)
        val txRawBuilder = TxRaw.newBuilder().setAuthInfoBytes(authInfo?.toByteString())
            .setBodyBytes(txBody?.toByteString())
        authInfo?.signerInfosList?.forEach { _ ->
            txRawBuilder.addSignatures(ByteString.copyFrom(sigByte))
        }
        return txRawBuilder.build()
    }

    private fun broadcast(
        msgs: MutableList<Msg>, fee: LFee, memo: String?, selectedChain: BaseChain
    ): BroadcastReq {
        (selectedChain as ChainOkt996Secp).apply {
            val toSign = genToSignMsg(
                chainIdCosmos,
                selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("account_number")?.asLong.toString(),
                selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("sequence")?.asLong.toString(),
                msgs,
                fee,
                memo
            )
            val sig = signature(this, toSign.toSignByte())
            val pubKey = wannabit.io.cosmostaion.data.model.req.PubKey(
                COSMOS_KEY_TYPE_PUBLIC, Strings.fromByteArray(encode(publicKey, DEFAULT))
            )
            val signature = Signature(
                pubKey,
                sig,
                selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("account_number")?.asLong.toString(),
                selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("sequence")?.asLong.toString(),
            )
            val signatures = mutableListOf<Signature>()
            signatures.add(signature)

            val signedTx = genStakeSignedTransferTx(msgs, fee, memo, signatures)
            return BroadcastReq("sync", signedTx.value)
        }
    }

    // Legacy Tx
    fun oktBroadcast(
        msgs: MutableList<Msg>, fee: LFee, memo: String?, selectedChain: BaseChain
    ): BroadcastReq? {
        when (selectedChain) {
            is ChainOkt996Secp -> {
                return broadcast(msgs, fee, memo, selectedChain)
            }

            is ChainOkt996Keccak -> {
                val toSign = genToSignMsg(
                    selectedChain.chainIdCosmos,
                    selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("account_number")?.asLong.toString(),
                    selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("sequence")?.asLong.toString(),
                    msgs,
                    fee,
                    memo
                )

                val sig = ethermintSignature(selectedChain, toSign.toSignByte())
                val pubKey = wannabit.io.cosmostaion.data.model.req.PubKey(
                    ETHERMINT_KEY_TYPE_PUBLIC,
                    Strings.fromByteArray(encode(selectedChain.publicKey, DEFAULT))
                )
                val signature = Signature(
                    pubKey,
                    sig,
                    selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("account_number")?.asLong.toString(),
                    selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("sequence")?.asLong.toString()
                )
                val signatures = mutableListOf<Signature>()
                signatures.add(signature)

                val signedTx = genStakeSignedTransferTx(msgs, fee, memo, signatures)
                return BroadcastReq("sync", signedTx.value)
            }

            is ChainOktEvm -> {
                val toSign = genToSignMsg(
                    selectedChain.chainIdCosmos,
                    selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("account_number")?.asLong.toString(),
                    selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("sequence")?.asLong.toString(),
                    msgs,
                    fee,
                    memo
                )
                val sig = ethermintSignature(selectedChain, toSign.toSignByte())
                val pubKey = wannabit.io.cosmostaion.data.model.req.PubKey(
                    ETHERMINT_KEY_TYPE_PUBLIC,
                    Strings.fromByteArray(encode(selectedChain.publicKey, DEFAULT))
                )
                val signature = Signature(
                    pubKey,
                    sig,
                    selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("account_number")?.asLong.toString(),
                    selectedChain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("sequence")?.asLong.toString(),
                )
                val signatures = mutableListOf<Signature>()
                signatures.add(signature)

                val signedTx = genStakeSignedTransferTx(msgs, fee, memo, signatures)
                return BroadcastReq("sync", signedTx.value)
            }

            else -> {
                return null
            }
        }
    }

    fun oktSendMsg(
        fromAddress: String, toAddress: String, toAmount: MutableList<LCoin>
    ): MutableList<Msg> {
        val result: MutableList<Msg> = mutableListOf()

        val req = Msg()
        req.type = OK_MSG_TYPE_TRANSFER
        req.value = Value()

        val value = req.value
        value?.from_address = fromAddress
        value?.to_address = toAddress
        value?.amount = toAmount

        result.add(req)
        return result
    }

    fun oktDepositMsg(address: String, depositCoin: LCoin): MutableList<Msg> {
        val result: MutableList<Msg> = mutableListOf()

        val req = Msg()
        req.type = OK_MSG_TYPE_DEPOSIT
        req.value = Value()

        val value = req.value
        value?.delegator_address = address
        value?.quantity = depositCoin

        result.add(req)
        return result
    }

    fun oktWithdrawMsg(address: String, withdrawCoin: LCoin): MutableList<Msg> {
        val result: MutableList<Msg> = mutableListOf()

        val req = Msg()
        req.type = OK_MSG_TYPE_WITHDRAW
        req.value = Value()

        val value = req.value
        value?.delegator_address = address
        value?.quantity = withdrawCoin

        result.add(req)
        return result
    }

    fun oktAddShareMsg(address: String, toValidators: MutableList<String?>): MutableList<Msg> {
        val result: MutableList<Msg> = mutableListOf()

        val req = Msg()
        req.type = OK_MSG_TYPE_ADD_SHARES
        req.value = Value()

        val value = req.value
        value?.delegator_address = address
        value?.validator_addresses = toValidators

        result.add(req)
        return result
    }

    private fun genToSignMsg(
        chainId: String,
        accountNumber: String?,
        sequence: String?,
        msgs: MutableList<Msg>,
        fee: LFee,
        memo: String?
    ): StdSignMsg {
        return StdSignMsg(chainId, accountNumber, sequence, fee, msgs, memo)
    }

    private fun genStakeSignedTransferTx(
        msgs: MutableList<Msg>, fee: LFee, memo: String?, signatures: MutableList<Signature>
    ): StdTx {
        return StdTx(COSMOS_AUTH_TYPE_STDTX, StdTxValue(msgs, fee, signatures, memo))
    }

    fun dAppSimulateGas(
        selectedChain: BaseChain, txBody: TxBody, authInfo: AuthInfo?
    ): AbciProto.GasInfo {
        val channel = selectedChain.cosmosFetcher()?.getChannel()
        val simulateStub = com.cosmos.tx.v1beta1.ServiceGrpc.newBlockingStub(channel)
            .withDeadlineAfter(8L, TimeUnit.SECONDS)
        val simulateTx = grpcSimulTx(txBody, authInfo)
        val simulateRequest =
            SimulateRequest.newBuilder().setTxBytes(simulateTx?.toByteString()).build()
        return simulateStub.simulate(simulateRequest).gasInfo
    }

    fun signature(selectedChain: BaseChain?, toSignByte: ByteArray?): String {
        if (selectedChain?.accountKeyType?.pubkeyType == PubKeyType.ETH_KECCAK256) {
            return ethermintSignature(selectedChain, toSignByte)
        } else {
            val sha256Hash = Sha256Hash.hash(toSignByte)
            val sigData = ByteArray(64)
            ECKey.fromPrivate(selectedChain?.privateKey)?.sign(Sha256Hash.wrap(sha256Hash))?.let {
                System.arraycopy(integerToBytes(it.r, 32), 0, sigData, 0, 32)
                System.arraycopy(integerToBytes(it.s, 32), 0, sigData, 32, 32)
                return toBase64String(sigData)
            } ?: run {
                return ""
            }
        }
    }

    private fun ethermintSignature(selectedChain: BaseChain?, toSignByte: ByteArray?): String {
        val sig = Sign.signMessage(toSignByte, ECKeyPair.create(selectedChain?.privateKey))
        val sigData = ByteArray(64)
        System.arraycopy(sig.r, 0, sigData, 0, 32)
        System.arraycopy(sig.s, 0, sigData, 32, 32)
        return String(encode(sigData), Charset.forName("UTF-8"))
    }

    fun suiSignature(selectedChain: BaseChain, txByte: String): MutableList<String> {
        val data = byteArrayOf(0, 0, 0) + Base64.decode(txByte)
        val blake2b = Blake2b.Blake2b256()
        blake2b.update(data)
        val spec: EdDSAParameterSpec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)
        val privateKeySpec =
            EdDSAPrivateKeySpec(Hex.decode(selectedChain.privateKey?.toHex()), spec)
        val signature = EdDSAEngine(MessageDigest.getInstance(spec.hashAlgorithm))
        signature.initSign(EdDSAPrivateKey(privateKeySpec))
        signature.update(blake2b.digest())
        return mutableListOf(toBase64String((byteArrayOf(0x00) + signature.sign() + selectedChain.publicKey!!)))
    }
}
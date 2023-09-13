package wannabit.io.cosmostaion.data.model

import android.content.Context
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONArray
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import java.util.regex.Pattern

@JsonClass(generateAdapter = true)
data class CosmosHistory(
    val header: HistoryHeader?,
    val data: HistoryData?
) {
    @JsonClass(generateAdapter = true)
    data class HistoryHeader(
        val id: Int,
        @Json(name = "chain_id") val chainId: String,
        @Json(name = "block_id") val blockId: Int,
        val timestamp: String
    )

    @JsonClass(generateAdapter = true)
    data class HistoryData(
        val height: String?,
        val txhash: String?,
        val code: Int?,
        val tx: Tx?,
        val timestamp: String?,
        var logs: MutableList<Any>
    )

    @JsonClass(generateAdapter = true)
    data class Tx(
        @Json(name = "@type") val type: String?,
        val body: Body?,
        val value: Body?
    )

    @JsonClass(generateAdapter = true)
    data class Body(
        val messages: MutableList<Any>?,
        val msg: MutableList<Any>?,
        val memo: String?
    )

    fun isSuccess(): Boolean {
        val rawCode = data?.code
        if (rawCode != 0) {
            return false
        }
        return true
    }

    private fun getMsgs(): JSONArray? {
        if (data?.tx?.body?.messages != null) {
            return JSONArray(data.tx.body.messages)
        } else if (data?.tx?.value?.msg != null) {
            return JSONArray(data.tx.value.msg)
        }
        return null
    }

    private fun getMsgCnt(): Int {
        if (getMsgs() != null) {
            return getMsgs()?.length() ?: 0
        }
        return 0
    }

    fun getMsgType(c: Context, address: String?): String {
        return if (getMsgCnt() == 0) {
            c.getString(R.string.tx_unknown)
        } else {
            try {
                var result = c.getString(R.string.tx_unknown)
                if (getMsgCnt() >= 2) {
                    var msgType0 = ""
                    var msgType1 = ""
                    getMsgs()?.let {
                        try {
                            msgType0 = it.getJSONObject(0).getString("@type")
                        } catch (_: Exception) { }
                        try {
                            msgType0 = it.getJSONObject(0).getString("type")
                        } catch (_: Exception) { }
                        try {
                            msgType1 = it.getJSONObject(1).getString("@type")
                        } catch (_: Exception) { }
                        try {
                            msgType1 = it.getJSONObject(1).getString("type")
                        } catch (_: Exception) { }
                    }

                    if (msgType0.contains("MsgWithdrawDelegatorReward") && msgType1.contains("MsgDelegate") ||
                        msgType0.contains("MsgWithdrawDelegationReward") && msgType1.contains("MsgDelegate")
                    ) {
                        return if (getMsgCnt() == 2) c.getString(R.string.tx_reinvest) else c.getString(
                            R.string.tx_reinvest
                        ) + " + " + (getMsgCnt() - 1) / 2
                    }
                    if (msgType1.contains("ibc") && msgType1.contains("MsgRecvPacket")) {
                        return c.getString(R.string.tx_ibc_receive)
                    }
                    if (msgType0.contains("pstake.") && msgType0.contains("liquidstakeibc") || msgType0.contains(
                            "pstake."
                        ) && msgType0.contains("lscosmos")
                    ) {
                        if (msgType0.contains("MsgLiquidStake")) {
                            result = c.getString(R.string.tx_persis_liquid_stake)
                        } else if (msgType0.contains("MsgLiquidUnstake")) {
                            result = c.getString(R.string.tx_persis_liquid_unstake)
                        } else if (msgType0.contains("MsgRedeem")) {
                            result = c.getString(R.string.tx_persis_liquid_redeem)
                        } else if (msgType0.contains("MsgClaim")) {
                            result = c.getString(R.string.tx_persis_liquid_claim)
                        }
                    }
                }
                var msgType = ""
                getMsgs()?.let {
                    try {
                        msgType = it.getJSONObject(0).getString("@type")
                    } catch (_: Exception) { }
                    try {
                        msgType = it.getJSONObject(0).getString("type")
                    } catch (_: Exception) { }

                    if (msgType.contains("cosmos.") && msgType.contains("staking")) {
                        if (msgType.contains("MsgCreateValidator")) {
                            result = c.getString(R.string.tx_create_validator)
                        } else if (msgType.contains("MsgEditValidator")) {
                            result = c.getString(R.string.tx_edit_validator)
                        } else if (msgType.contains("MsgDelegate")) {
                            result = c.getString(R.string.tx_delegate)
                        } else if (msgType.contains("MsgUndelegate")) {
                            result = c.getString(R.string.tx_undelegate)
                        } else if (msgType.contains("MsgBeginRedelegate")) {
                            result = c.getString(R.string.tx_redelegate)
                        } else if (msgType.contains("MsgCancelUnbondingDelegation")) {
                            result = c.getString(R.string.tx_cancel_undelegate)
                        }

                    } else if (msgType.contains("cosmos.") && msgType.contains("bank")) {
                        if (msgType.contains("MsgSend")) {
                            var toAddress = ""
                            var fromAddress = ""
                            try {
                                toAddress = getMsgs()!!.getJSONObject(0).getString("to_address")
                                fromAddress = getMsgs()!!.getJSONObject(0).getString("from_address")
                            } catch (_: Exception) { }
                            try {
                                toAddress = getMsgs()!!.getJSONObject(0).getJSONObject("value")
                                    .getString("to_address")
                                fromAddress = getMsgs()!!.getJSONObject(0).getJSONObject("value")
                                    .getString("from_address")
                            } catch (_: Exception) { }
                            result = if (toAddress == address) {
                                c.getString(R.string.tx_receive)
                            } else if (fromAddress == address) {
                                c.getString(R.string.tx_send)
                            } else {
                                c.getString(R.string.tx_transfer)
                            }
                        } else if (msgType.contains("MsgMultiSend")) {
                            result = c.getString(R.string.tx_transfer)
                        }

                    } else if (msgType.contains("cosmos.") && msgType.contains("distribution")) {
                        if (msgType.contains("MsgSetWithdrawAddress") || msgType.contains("MsgModifyWithdrawAddress")) {
                            result = c.getString(R.string.tx_change_reward_address)
                        } else if (msgType.contains("MsgWithdrawDelegatorReward") || msgType.contains("MsgWithdrawDelegationReward")) {
                            result = c.getString(R.string.tx_get_reward)
                        } else if (msgType.contains("MsgWithdrawValidatorCommission")) {
                            result = c.getString(R.string.tx_get_commission)
                        } else if (msgType.contains("MsgFundCommunityPool")) {
                            result = c.getString(R.string.tx_fund_pool)
                        }

                    } else if (msgType.contains("cosmos.") && msgType.contains("gov")) {
                        if (msgType.contains("MsgSubmitProposal")) {
                            result = c.getString(R.string.tx_submit_proposal)
                        } else if (msgType.contains("MsgDeposit")) {
                            result = c.getString(R.string.tx_deposit_proposal)
                        } else if (msgType.contains("MsgVote")) {
                            result = c.getString(R.string.tx_vote)
                        } else if (msgType.contains("MsgVoteWeighted")) {
                            result = c.getString(R.string.tx_vote_weighted)
                        }

                    } else if (msgType.contains("cosmos.") && msgType.contains("authz")) {
                        if (msgType.contains("MsgGrant")) {
                            result = c.getString(R.string.tx_authz_grant)
                        } else if (msgType.contains("MsgRevoke")) {
                            result = c.getString(R.string.tx_authz_revoke)
                        } else if (msgType.contains("MsgExec")) {
                            result = c.getString(R.string.tx_authz_exe)
                        }

                    } else if (msgType.contains("cosmos.") && msgType.contains("slashing")) {
                        if (msgType.contains("MsgUnjail")) {
                            result = c.getString(R.string.tx_unjail)
                        }

                    } else if (msgType.contains("cosmos.") && msgType.contains("feegrant")) {
                        if (msgType.contains("MsgGrantAllowance")) {
                            result = c.getString(R.string.tx_authz_grant_allow)
                        } else if (msgType.contains("MsgRevokeAllowance")) {
                            result = c.getString(R.string.tx_authz_grant_revoke)
                        }

                    } else if (msgType.contains("stride.") && msgType.contains("stakeibc")) {
                        if (msgType.contains("MsgLiquidStake")) {
                            result = c.getString(R.string.tx_stride_liquid_stake)
                        } else if (msgType.contains("MsgRedeemStake")) {
                            result = c.getString(R.string.tx_stride_redeem_stake)
                        }

                    } else if (msgType.contains("ibc.")) {
                        if (msgType.contains("MsgTransfer")) {
                            result = c.getString(R.string.tx_ibc_send)
                        } else if (msgType.contains("MsgUpdateClient")) {
                            result = c.getString(R.string.tx_ibc_update_client)
                        } else if (msgType.contains("MsgAcknowledgement")) {
                            result = c.getString(R.string.tx_ibc_acknowledgement)
                        } else if (msgType.contains("MsgRecvPacket")) {
                            result = c.getString(R.string.tx_ibc_receive)
                        }

                    } else if (msgType.contains("crescent.") && msgType.contains("liquidstaking")) {
                        if (msgType.contains("MsgLiquidStake")) {
                            result = c.getString(R.string.tx_crescent_liquid_stake)
                        } else if (msgType.contains("MsgLiquidUnstake")) {
                            result = c.getString(R.string.tx_crescent_liquid_unstake)
                        }

                    } else if (msgType.contains("crescent.") && msgType.contains("liquidity")) {
                        if (msgType.contains("MsgCreatePair")) {
                            result = c.getString(R.string.tx_crescent_liquidity_create_pair)
                        } else if (msgType.contains("MsgCreatePool")) {
                            result = c.getString(R.string.tx_crescent_liquidity_create_pool)
                        } else if (msgType.contains("MsgDeposit")) {
                            result = c.getString(R.string.tx_crescent_liquidity_deposit)
                        } else if (msgType.contains("MsgWithdraw")) {
                            result = c.getString(R.string.tx_crescent_liquidity_withdraw)
                        } else if (msgType.contains("MsgLimitOrder")) {
                            result = c.getString(R.string.tx_crescent_liquidity_limit_order)
                        } else if (msgType.contains("MsgMarketOrder")) {
                            result = c.getString(R.string.tx_crescent_liquidity_market_order)
                        } else if (msgType.contains("MsgCancelOrder")) {
                            result = c.getString(R.string.tx_crescent_liquidity_cancel_order)
                        } else if (msgType.contains("MsgCancelAllOrders")) {
                            result = c.getString(R.string.tx_crescent_liquidity_cancelall_order)
                        }

                    } else if (msgType.contains("crescent.") && msgType.contains("farming")) {
                        if (msgType.contains("MsgStake")) {
                            result = c.getString(R.string.tx_crescent_farming_stake)
                        } else if (msgType.contains("MsgUnstake")) {
                            result = c.getString(R.string.tx_crescent_farming_unstake)
                        } else if (msgType.contains("MsgHarvest")) {
                            result = c.getString(R.string.tx_crescent_farming_harvest)
                        } else if (msgType.contains("MsgCreateFixedAmountPlan")) {
                            result = c.getString(R.string.tx_crescent_farming_create_fixed_amount_plan)
                        } else if (msgType.contains("MsgCreateRatioPlan")) {
                            result = c.getString(R.string.tx_crescent_farming_create_ratio_plan)
                        } else if (msgType.contains("MsgRemovePlan")) {
                            result = c.getString(R.string.tx_crescent_farming_remove_plan)
                        } else if (msgType.contains("MsgAdvanceEpoch")) {
                            result = c.getString(R.string.tx_crescent_farming_advance_epoch)
                        }

                    } else if (msgType.contains("crescent.") && msgType.contains("claim")) {
                        if (msgType.contains("MsgClaim")) {
                            result = c.getString(R.string.tx_crescent_farming_claim)
                        }

                    } else if (msgType.contains("irismod.") && msgType.contains("nft")) {
                        if (msgType.contains("MsgMintNFT")) {
                            result = c.getString(R.string.tx_mint_nft)
                        } else if (msgType.contains("MsgTransferNFT")) {
                            val senderAddr = getMsgs()!!.getJSONObject(0).getString("sender")
                            val receiveAddr = getMsgs()!!.getJSONObject(0).getString("recipient")
                            result = if (senderAddr.equals(address, ignoreCase = true)) {
                                c.getString(R.string.tx_send_nft)
                            } else if (receiveAddr.equals(address, ignoreCase = true)) {
                                c.getString(R.string.tx_receive_nft)
                            } else {
                                c.getString(R.string.tx_transfer_nft)
                            }
                        } else if (msgType.contains("MsgEditNFT")) {
                            result = "NFT Edit"
                        } else if (msgType.contains("MsgIssueDenom")) {
                            result = c.getString(R.string.tx_issue_denom)
                        } else if (msgType.contains("MsgRequestRandom")) {
                            result = "Random Request"
                        }

                    } else if (msgType.contains("irismod") && msgType.contains("coinswap")) {
                        if (msgType.contains("MsgSwapOrder")) {
                            result = c.getString(R.string.tx_osmosis_coin_swap)
                        } else if (msgType.contains("MsgAddLiquidity")) {
                            result = c.getString(R.string.tx_osmosis_join_pool)
                        } else if (msgType.contains("MsgRemoveLiquidity")) {
                            result = c.getString(R.string.tx_osmosis_exit_pool)
                        }

                    } else if (msgType.contains("irismod") && msgType.contains("farm")) {
                        if (msgType.contains("MsgStake")) {
                            result = c.getString(R.string.tx_irismod_farm_stake)
                        } else if (msgType.contains("MsgHarvest")) {
                            result = c.getString(R.string.tx_irismod_farm_Harvest)
                        }

                    } else if (msgType.contains("chainmain.") && msgType.contains("nft")) {
                        if (msgType.contains("MsgMintNFT")) {
                            result = c.getString(R.string.tx_mint_nft)
                        } else if (msgType.contains("MsgTransferNFT")) {
                            val senderAddr = getMsgs()!!.getJSONObject(0).getString("sender")
                            val receiveAddr = getMsgs()!!.getJSONObject(0).getString("recipient")
                            result = if (senderAddr.equals(address, ignoreCase = true)) {
                                c.getString(R.string.tx_send_nft)
                            } else if (receiveAddr.equals(address, ignoreCase = true)) {
                                c.getString(R.string.tx_receive_nft)
                            } else {
                                c.getString(R.string.tx_transfer_nft)
                            }
                        } else if (msgType.contains("MsgEditNFT")) {
                            result = "NFT Edit"
                        } else if (msgType.contains("MsgIssueDenom")) {
                            result = c.getString(R.string.tx_issue_denom)
                        } else if (msgType.contains("MsgRequestRandom")) {
                            result = "Random Request"
                        }

                    } else if (msgType.contains("starnamed.") && msgType.contains("starname")) {
                        if (msgType.contains("RegisterDomain")) {
                            result = c.getString(R.string.tx_starname_registe_domain)
                        } else if (msgType.contains("RegisterAccount")) {
                            result = c.getString(R.string.tx_starname_registe_account)
                        } else if (msgType.contains("DeleteDomain")) {
                            result = c.getString(R.string.tx_starname_delete_domain)
                        } else if (msgType.contains("DeleteAccount")) {
                            result = c.getString(R.string.tx_starname_delete_account)
                        } else if (msgType.contains("RenewDomain")) {
                            result = c.getString(R.string.tx_starname_renew_domain)
                        } else if (msgType.contains("RenewAccount")) {
                            result = c.getString(R.string.tx_starname_renew_account)
                        } else if (msgType.contains("ReplaceAccountResources")) {
                            result = c.getString(R.string.tx_starname_update_resource)
                        }

                    } else if (msgType.contains("osmosis.") && msgType.contains("gamm") || msgType.contains(
                            "osmosis."
                        ) && msgType.contains("poolmanager")
                    ) {
                        if (msgType.contains("MsgSwapExactAmountIn")) {
                            result = c.getString(R.string.tx_osmosis_coin_swap)
                        } else if (msgType.contains("MsgSwapExactAmountOut")) {
                            result = c.getString(R.string.tx_osmosis_coin_swap)
                        } else if (msgType.contains("MsgJoinPool")) {
                            result = c.getString(R.string.tx_osmosis_join_pool)
                        } else if (msgType.contains("MsgExitPool")) {
                            result = c.getString(R.string.tx_osmosis_exit_pool)
                        } else if (msgType.contains("MsgJoinSwapExternAmountIn")) {
                            result = c.getString(R.string.tx_osmosis_coin_swap)
                        } else if (msgType.contains("MsgJoinSwapShareAmountOut")) {
                            result = c.getString(R.string.tx_osmosis_coin_swap)
                        } else if (msgType.contains("MsgExitSwapExternAmountOut")) {
                            result = c.getString(R.string.tx_osmosis_coin_swap)
                        } else if (msgType.contains("MsgExitSwapShareAmountIn")) {
                            result = c.getString(R.string.tx_osmosis_coin_swap)
                        } else if (msgType.contains("MsgCreatePool")) {
                            result = c.getString(R.string.tx_osmosis_create_pool)
                        } else if (msgType.contains("MsgCreateBalancerPool")) {
                            result = c.getString(R.string.tx_osmosis_create_pool)
                        }

                    } else if (msgType.contains("osmosis.") && msgType.contains("lockup")) {
                        if (msgType.contains("MsgLockTokens")) {
                            result = c.getString(R.string.tx_osmosis_token_lockup)
                        } else if (msgType.contains("MsgBeginUnlockingAll")) {
                            result = c.getString(R.string.tx_osmosis_begin_token_unlock_all)
                        } else if (msgType.contains("MsgBeginUnlocking")) {
                            result = c.getString(R.string.tx_osmosis_begin_token_unlock)
                        }

                    } else if (msgType.contains("panacea.") && msgType.contains("aol")) {
                        if (msgType.contains("MsgAddRecord")) {
                            result = c.getString(R.string.tx_add_record)
                        } else if (msgType.contains("MsgCreateTopic")) {
                            result = c.getString(R.string.tx_create_topic)
                        } else if (msgType.contains("MsgAddWriter")) {
                            result = c.getString(R.string.tx_add_writer)
                        }

                    } else if (msgType.contains("panacea.") && msgType.contains("did")) {
                        if (msgType.contains("MsgCreateDID")) {
                            result = c.getString(R.string.tx_create_did)
                        }

                    } else if (msgType.contains("akash.") && msgType.contains("market")) {
                        if (msgType.contains("MsgCreateBid")) {
                            result = c.getString(R.string.tx_create_bid)
                        } else if (msgType.contains("MsgCloseBid")) {
                            result = c.getString(R.string.tx_close_bid)
                        } else if (msgType.contains("MsgCreateLease")) {
                            result = c.getString(R.string.tx_create_lease)
                        } else if (msgType.contains("MsgWithdrawLease")) {
                            result = c.getString(R.string.tx_withdraw_lease)
                        } else if (msgType.contains("MsgCloseLease")) {
                            result = c.getString(R.string.tx_close_lease)
                        }
                    } else if (msgType.contains("akash.") && msgType.contains("deployment")) {
                        if (msgType.contains("MsgCreateDeployment")) {
                            result = c.getString(R.string.tx_create_deployment)
                        } else if (msgType.contains("MsgDepositDeployment")) {
                            result = c.getString(R.string.tx_deposit_deployment)
                        } else if (msgType.contains("MsgUpdateDeployment")) {
                            result = c.getString(R.string.tx_update_deployment)
                        } else if (msgType.contains("MsgCloseDeployment")) {
                            result = c.getString(R.string.tx_close_deployment)
                        } else if (msgType.contains("MsgCloseGroup")) {
                            result = c.getString(R.string.tx_close_group)
                        } else if (msgType.contains("MsgPauseGroup")) {
                            result = c.getString(R.string.tx_pause_group)
                        } else if (msgType.contains("MsgStartGroup")) {
                            result = c.getString(R.string.tx_start_group)
                        }

                    } else if (msgType.contains("akash.") && msgType.contains("cert")) {
                        if (msgType.contains("MsgCreateCertificate")) {
                            result = c.getString(R.string.tx_create_certificate)
                        } else if (msgType.contains("MsgRevokeCertificate")) {
                            result = c.getString(R.string.tx_revoke_certificate)
                        }

                    } else if (msgType.contains("desmos.") && msgType.contains("profiles")) {
                        if (msgType.contains("MsgSaveProfile")) {
                            result = c.getString(R.string.tx_save_profile)
                        } else if (msgType.contains("MsgDeleteProfile")) {
                            result = c.getString(R.string.tx_delete_profile)
                        } else if (msgType.contains("MsgCreateRelationship")) {
                            result = c.getString(R.string.tx_create_relation)
                        } else if (msgType.contains("MsgDeleteRelationship")) {
                            result = c.getString(R.string.tx_delete_relation)
                        } else if (msgType.contains("MsgBlockUser")) {
                            result = c.getString(R.string.tx_block_user)
                        } else if (msgType.contains("MsgUnblockUser")) {
                            result = c.getString(R.string.tx_unblock_user)
                        } else if (msgType.contains("MsgRequestDTagTransfer")) {
                        } else if (msgType.contains("MsgCancelDTagTransferRequest")) {
                        } else if (msgType.contains("MsgAcceptDTagTransferRequest")) {
                        } else if (msgType.contains("MsgRefuseDTagTransferRequest")) {
                        } else if (msgType.contains("MsgLinkChainAccount")) {
                            result = c.getString(R.string.tx_link_chain_account)
                        } else if (msgType.contains("MsgUnlinkChainAccount")) {
                        } else if (msgType.contains("MsgLinkApplication")) {
                        } else if (msgType.contains("MsgUnlinkApplication")) {
                        }

                    } else if (msgType.contains("cosmwasm.")) {
                        if (msgType.contains("MsgStoreCode")) {
                            result = c.getString(R.string.tx_cosmwasm_store_code)
                        } else if (msgType.contains("MsgInstantiateContract")) {
                            result = c.getString(R.string.tx_cosmwasm_instantiate)
                        } else if (msgType.contains("MsgExecuteContract")) {
                            result = c.getString(R.string.tx_cosmwasm_execontract)
                        } else if (msgType.contains("MsgMigrateContract")) {
                        } else if (msgType.contains("MsgUpdateAdmin")) {
                        } else if (msgType.contains("MsgClearAdmin")) {
                        } else if (msgType.contains("PinCodesProposal")) {
                        } else if (msgType.contains("UnpinCodesProposal")) {
                        } else if (msgType.contains("StoreCodeProposal")) {
                        } else if (msgType.contains("InstantiateContractProposal")) {
                        } else if (msgType.contains("MigrateContractProposal")) {
                        } else if (msgType.contains("UpdateAdminProposal")) {
                        } else if (msgType.contains("ClearAdminProposal")) {
                        }

                    } else if (msgType.contains("ethermint.evm")) {
                        if (msgType.contains("MsgEthereumTx")) {
                            result = c.getString(R.string.tx_ethermint_evm)
                        }

                    } else if (msgType.contains("kava.") && msgType.contains("auction")) {
                        if (msgType.contains("MsgPlaceBid")) {
                            result = c.getString(R.string.tx_kava_auction_bid)
                        }

                    } else if (msgType.contains("kava.") && msgType.contains("cdp")) {
                        if (msgType.contains("MsgCreateCDP")) {
                            result = c.getString(R.string.tx_kava_create_cdp)
                        } else if (msgType.contains("MsgDeposit")) {
                            result = c.getString(R.string.tx_kava_deposit_cdp)
                        } else if (msgType.contains("MsgWithdraw")) {
                            result = c.getString(R.string.tx_kava_withdraw_cdp)
                        } else if (msgType.contains("MsgDrawDebt")) {
                            result = c.getString(R.string.tx_kava_drawdebt_cdp)
                        } else if (msgType.contains("MsgRepayDebt")) {
                            result = c.getString(R.string.tx_kava_repaydebt_cdp)
                        } else if (msgType.contains("MsgLiquidate")) {
                            result = c.getString(R.string.tx_kava_liquidate_cdp)
                        }

                    } else if (msgType.contains("kava.") && msgType.contains("swap")) {
                        if (msgType.contains("MsgDeposit")) {
                            result = c.getString(R.string.tx_kava_swap_deposit)
                        } else if (msgType.contains("MsgWithdraw")) {
                            result = c.getString(R.string.tx_kava_swap_withdraw)
                        } else if (msgType.contains("MsgSwapExactForTokens")) {
                            result = c.getString(R.string.tx_kava_swap_token)
                        } else if (msgType.contains("MsgSwapForExactTokens")) {
                            result = c.getString(R.string.tx_kava_swap_token)
                        }

                    } else if (msgType.contains("kava.") && msgType.contains("hard")) {
                        if (msgType.contains("MsgDeposit")) {
                            result = c.getString(R.string.tx_kava_hard_deposit)
                        } else if (msgType.contains("MsgWithdraw")) {
                            result = c.getString(R.string.tx_kava_hard_withdraw)
                        } else if (msgType.contains("MsgBorrow")) {
                            result = c.getString(R.string.tx_kava_hard_borrow)
                        } else if (msgType.contains("MsgRepay")) {
                            result = c.getString(R.string.tx_kava_hard_repay)
                        } else if (msgType.contains("MsgLiquidate")) {
                            result = c.getString(R.string.tx_kava_hard_liquidate)
                        }

                    } else if (msgType.contains("kava.") && msgType.contains("savings")) {
                        if (msgType.contains("MsgDeposit")) {
                            result = c.getString(R.string.tx_kava_save_deposit)
                        } else if (msgType.contains("MsgWithdraw")) {
                            result = c.getString(R.string.tx_kava_save_withdraw)
                        }

                    } else if (msgType.contains("kava.") && msgType.contains("incentive")) {
                        if (msgType.contains("MsgClaimUSDXMintingReward")) {
                            result = c.getString(R.string.tx_kava_hard_mint_incentive)
                        } else if (msgType.contains("MsgClaimHardReward")) {
                            result = c.getString(R.string.tx_kava_hard_hard_incentive)
                        } else if (msgType.contains("MsgClaimDelegatorReward")) {
                            result = c.getString(R.string.tx_kava_hard_hard_incentive)
                        } else if (msgType.contains("MsgClaimSwapReward")) {
                            result = c.getString(R.string.tx_kava_save_withdraw)
                        } else if (msgType.contains("MsgClaimSavingsReward")) {
                            result = c.getString(R.string.tx_kava_hard_save_incentive)
                        } else if (msgType.contains("MsgClaimEarnReward")) {
                            result = c.getString(R.string.tx_kava_hard_earn_incentive)
                        }

                    } else if (msgType.contains("kava.") && msgType.contains("bep3")) {
                        if (msgType.contains("MsgCreateAtomicSwap")) {
                            result = c.getString(R.string.tx_kava_bep3_create)
                        } else if (msgType.contains("MsgClaimAtomicSwap")) {
                            result = c.getString(R.string.tx_kava_bep3_claim)
                        } else if (msgType.contains("MsgRefundAtomicSwap")) {
                            result = c.getString(R.string.tx_kava_bep3_refund)
                        }

                    } else if (msgType.contains("kava.") && msgType.contains("pricefeed")) {
                        if (msgType.contains("MsgPostPrice")) {
                            result = c.getString(R.string.tx_post_price)
                        }

                    } else if (msgType.contains("kava.") && msgType.contains("router")) {
                        if (msgType.contains("MsgDelegateMintDeposit")) {
                            result = c.getString(R.string.tx_kava_earn_delegateDeposit)
                        } else if (msgType.contains("MsgWithdrawBurn")) {
                            result = c.getString(R.string.tx_kava_earn_withdraw)
                        } else if (msgType.contains("MsgMintDeposit")) {
                            result = c.getString(R.string.tx_kava_earn_deposit)
                        }

                    } else if (msgType.contains("shentu.") && msgType.contains("oracle")) {
                        if (msgType.contains("MsgTaskResponse")) {
                            result = c.getString(R.string.tx_task_response)
                        } else if (msgType.contains("MsgCreateTask")) {
                            result = c.getString(R.string.tx_create_task)
                        }

                    } else if (msgType.contains("osmosis.") && msgType.contains("superfluid")) {
                        if (msgType.contains("MsgSuperfluidDelegate")) {
                            result = c.getString(R.string.tx_osmosis_super_fluid_delegate)
                        } else if (msgType.contains("MsgSuperfluidUndelegate")) {
                            result = c.getString(R.string.tx_osmosis_super_fluid_undelegate)
                        } else if (msgType.contains("MsgSuperfluidUnbondLock")) {
                            result = c.getString(R.string.tx_osmosis_super_fluid_unbondinglock)
                        } else if (msgType.contains("MsgLockAndSuperfluidDelegate")) {
                            result = c.getString(R.string.tx_osmosis_super_fluid_lockanddelegate)
                        }

                    } else if (msgType.contains("axelar.") && msgType.contains("reward")) {
                        if (msgType.contains("RefundMsgRequest")) {
                            result = c.getString(R.string.tx_axelar_refund_msg_request)
                        }

                    } else if (msgType.contains("axelar.") && msgType.contains("axelarnet")) {
                        if (msgType.contains("LinkRequest")) {
                            result = c.getString(R.string.tx_axelar_link_request)
                        } else if (msgType.contains("ConfirmDepositRequest")) {
                            result = c.getString(R.string.tx_axelar_confirm_deposit_request)
                        } else if (msgType.contains("RouteIBCTransfersRequest")) {
                            result = c.getString(R.string.tx_axelar_route_ibc_request)
                        } else if (msgType.contains("ExecutePendingTransfersRequest")) {
                        } else if (msgType.contains("RegisterIBCPathRequest")) {
                        } else if (msgType.contains("AddCosmosBasedChainRequest")) {
                        } else if (msgType.contains("RegisterAssetRequest")) {
                        } else if (msgType.contains("RegisterFeeCollectorRequest")) {
                        } else if (msgType.contains("RetryIBCTransferRequest")) {
                        }

                    } else if (msgType.contains("injective.") && msgType.contains("exchange")) {
                        if (msgType.contains("MsgBatchUpdateOrders")) {
                            result = c.getString(R.string.tx_injective_batch_update_order)
                        } else if (msgType.contains("MsgBatchCreateDerivativeLimitOrders") || msgType.contains(
                                "MsgCreateDerivativeLimitOrder"
                            )
                        ) {
                            result = c.getString(R.string.tx_injective_create_limit_order)
                        } else if (msgType.contains("MsgBatchCreateSpotLimitOrders") || msgType.contains(
                                "MsgCreateSpotLimitOrder"
                            )
                        ) {
                            result = c.getString(R.string.tx_injective_create_spot_order)
                        } else if (msgType.contains("MsgBatchCancelDerivativeOrders") || msgType.contains(
                                "MsgCancelDerivativeOrder"
                            )
                        ) {
                            result = c.getString(R.string.tx_injective_cancel_limit_order)
                        } else if (msgType.contains("MsgBatchCancelSpotOrder") || msgType.contains("MsgCancelSpotOrder")) {
                            result = c.getString(R.string.tx_injective_cancel_spot_order)
                        }

                    } else if (msgType.contains("pstake.") && msgType.contains("lscosmos") || msgType.contains(
                            "pstake."
                        ) && msgType.contains("liquidstakeibc")
                    ) {
                        if (msgType.contains("MsgLiquidStake")) {
                            result = c.getString(R.string.tx_persis_liquid_stake)
                        } else if (msgType.contains("MsgLiquidUnstake")) {
                            result = c.getString(R.string.tx_persis_liquid_unstake)
                        } else if (msgType.contains("MsgRedeem")) {
                            result = c.getString(R.string.tx_persis_liquid_redeem)
                        } else if (msgType.contains("MsgClaim")) {
                            result = c.getString(R.string.tx_persis_liquid_claim)
                        }
                    }

                    if (getMsgCnt() > 1) {
                        result = result + " + " + (getMsgCnt() - 1)
                    }
                    return result
                }
            } catch (_: Exception) { }
            c.getString(R.string.tx_unknown)
        }
    }

    fun getDpCoin(line: CosmosLine): MutableList<Coin>? {
        val result = mutableListOf<Coin>()
        if (getMsgCnt() > 0) {
            var allReward = true
            getMsgs()?.let {
                for (i in 0 until it.length()) {
                    val msgType: String = try {
                        getMsgs()?.getJSONObject(i)?.getString("@type") ?: getMsgs()?.getJSONObject(i)?.getString("type") ?: ""
                    } catch (e: Exception) {
                        ""
                    }
                    if (!msgType.contains("MsgWithdrawDelegatorReward")) {
                        allReward = false
                    }
                }
            }
            if (allReward) {
                if (data?.logs != null) {
                    for (i in data.logs.indices) {
                        for (j in 0 until JSONArray(data.logs).getJSONObject(i).getJSONArray("events").length()) {
                            val type = JSONArray(data.logs).getJSONObject(i).getJSONArray("events")
                                .getJSONObject(j).getString("type")
                            if (type.equals("transfer", true)) {
                                val attributes =
                                    JSONArray(data.logs).getJSONObject(i).getJSONArray("events")
                                        .getJSONObject(j).getJSONArray("attributes")
                                val value =
                                    attributes.getJSONObject(2).getString("value")
                                value.split(",").forEach { rawCoin ->
                                    val p = Pattern.compile("([0-9])+")
                                    val m = p.matcher(rawCoin)
                                    if (m.find()) {
                                        val amount = m.group()
                                        val denom: String = rawCoin.substring(m.end())
                                        val coin = Coin.newBuilder().setDenom(denom).setAmount(amount).build()
                                        result.add(coin)
                                    }
                                }
                            }
                        }
                    }
                }
                return sortedCoins(line, result)
            }

            var ibcReceived = false
            getMsgs()?.let {
                for (i in 0 until it.length()) {
                    val msgType: String = try {
                        getMsgs()?.getJSONObject(i)?.getString("@type") ?: getMsgs()?.getJSONObject(i)?.getString("type") ?: ""
                    } catch (e: Exception) {
                        ""
                    }
                    if (msgType.contains("ibc") && msgType.contains("MsgRecvPacket")) {
                        ibcReceived = true
                    }
                }
            }
            if (ibcReceived) {
                if (data?.logs != null) {
                    for (i in data.logs.indices) {
                        for (j in 0 until JSONArray(data.logs).getJSONObject(i).getJSONArray("events").length()) {
                            val type = JSONArray(data.logs).getJSONObject(i).getJSONArray("events")
                                .getJSONObject(j).getString("type")
                            if (type.equals("transfer", true)) {
                                val attributes =
                                    JSONArray(data.logs).getJSONObject(i).getJSONArray("events")
                                        .getJSONObject(j).getJSONArray("attributes")
                                if (attributes.getJSONObject(0)
                                        .getString("value") == line.address
                                ) {
                                    val value =
                                        attributes.getJSONObject(2).getString("value")
                                    value.split(",").forEach { rawCoin ->
                                        val p = Pattern.compile("([0-9])+")
                                        val m = p.matcher(rawCoin)
                                        if (m.find()) {
                                            val amount = m.group()
                                            val denom: String = rawCoin.substring(m.end())
                                            val coin = Coin.newBuilder().setDenom(denom).setAmount(amount).build()
                                            result.add(coin)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return sortedCoins(line, result)
            }
        }

        if (getMsgCnt() == 2) {
            val msgType0: String = try {
                getMsgs()?.getJSONObject(0)?.getString("@type") ?: getMsgs()?.getJSONObject(0)?.getString("type") ?: ""
            } catch (e: Exception) {
                ""
            }
            val msgType1: String = try {
                getMsgs()?.getJSONObject(1)?.getString("@type") ?: getMsgs()?.getJSONObject(1)?.getString("type") ?: ""
            } catch (e: Exception) {
                ""
            }
            if (msgType0.contains("MsgWithdrawDelegatorReward") && msgType1.contains("MsgDelegate")) {
                getMsgs()?.getJSONObject(1)?.getJSONObject("amount")?.let {
                    val coin = Coin.newBuilder().setDenom(it.getString("denom")).setAmount(it.getString("amount")).build()
                    result.add(coin)
                }
                return sortedCoins(line, result)
            }
        }

        if (getMsgCnt() == 0 || getMsgCnt() > 1) { return null }
        val msgType: String = try {
            getMsgs()?.getJSONObject(0)?.getString("@type") ?: getMsgs()?.getJSONObject(0)?.getString("type") ?: ""
        } catch (e: Exception) {
            ""
        }

        getMsgs()?.let {
            var denom: String? = ""
            var amount: String? = ""
            val msg = it.getJSONObject(0)

            if (msgType.contains("MsgSend")) {
                val msgObject = msg?.optJSONArray("amount")?.optJSONObject(0)
                    ?: msg?.optJSONObject("value")?.optJSONArray("amount")?.optJSONObject(0)

                denom = msgObject?.optString("denom")
                amount = msgObject?.optString("amount")
                if (denom?.isNotBlank() == true && amount?.isNotBlank() == true) {
                    val coin = Coin.newBuilder().setDenom(denom).setAmount(amount).build()
                    result.add(coin)
                }

            } else if (msgType.contains("MsgDelegate") || msgType.contains("MsgUndelegate") || msgType.contains("MsgBeginRedelegate")) {
                val msgObject = msg?.optJSONObject("amount")
                    ?: msg?.optJSONObject("value")?.getJSONObject("amount")

                denom = msgObject?.optString("denom")
                amount = msgObject?.optString("amount")
                if (denom?.isNotBlank() == true && amount?.isNotBlank() == true) {
                    val coin = Coin.newBuilder().setDenom(denom).setAmount(amount).build()
                    result.add(coin)
                }
            
            } else if (msgType.contains("ibc") && msgType.contains("MsgTransfer")) {
                denom = msg?.optJSONObject("token")?.optString("denom")
                amount = msg?.optJSONObject("token")?.optString("amount")
                if (denom?.isNotBlank() == true && amount?.isNotBlank() == true) {
                    val coin = Coin.newBuilder().setDenom(denom).setAmount(amount).build()
                    result.add(coin)
                }
            }
        }
        return sortedCoins(line, result)
    }

    fun getVoteOption(): String? {
        try {
            getMsgs()?.let { msgs ->
                msgs.getJSONObject(0).let { msg ->
                    val msgType = msg.optString("@type", msg.optString("type", ""))
                    val option = msg.optString("option", msg.optJSONObject("value")?.optString("option", ""))

                    if (msgType.contains("MsgVote")) {
                        return when (option) {
                            "VOTE_OPTION_YES", "Yes" -> "YES"
                            "VOTE_OPTION_NO", "No" -> "NO"
                            "VOTE_OPTION_ABSTAIN", "Abstain" -> "ABSTAIN"
                            "VOTE_OPTION_NO_WITH_VETO", "NoWithVeto" -> "VETO"
                            else -> null
                        }
                    }
                }
            }
        } catch (_: Exception) { }
        return null
    }

    private fun sortedCoins(line: CosmosLine, inputs: MutableList<Coin>?): MutableList<Coin> {
        var sorted = mutableListOf<Coin>()

        inputs?.forEach { coin ->
            val index = sorted.indexOfFirst { it.denom == coin.denom }

            if (index != -1) {
                val existing = sorted[index].amount.toBigDecimal()
                val added = existing.add(coin.amount.toBigDecimal())

                val tempSorted = sorted.toMutableList()
                tempSorted[index] = Coin.newBuilder().setDenom(coin.denom).setAmount(added.toString()).build()
                sorted = tempSorted
            } else {
                sorted.add(coin)
            }
        }

        sorted.sortWith { coin1, coin2 ->
            if (coin1.denom == line.stakeDenom && coin2.denom != line.stakeDenom) {
                -1
            } else {
                0
            }
        }
        return sorted
    }
}







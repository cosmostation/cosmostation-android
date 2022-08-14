package wannabit.io.cosmostaion.network.res;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class ResApiNewTxListCustom {
    @SerializedName("header")
    public Header header;

    @SerializedName("data")
    public Data data;

    public class Header {
        @SerializedName("id")
        public int id;

        @SerializedName("chain_id")
        public String chain_id;

        @SerializedName("block_id")
        public int block_id;
    }

    public class Data {
        @SerializedName("height")
        public String height;

        @SerializedName("txhash")
        public String txhash;

        @SerializedName("codespace")
        public String codespace;

        @SerializedName("code")
        public int code;

        @SerializedName("raw_log")
        public String raw_log;

        @SerializedName("info")
        public String info;

        @SerializedName("gas_wanted")
        public long gas_wanted;

        @SerializedName("gas_used")
        public long gas_used;

        @SerializedName("tx")
        public Tx tx;

        @SerializedName("timestamp")
        public String timestamp;

        @SerializedName("logs")
        public ArrayList<Object> logs;
    }

    public class Tx {
        @SerializedName("@type")
        public String type;

        @SerializedName("body")
        public Body body;

        @SerializedName("value")
        public Body value;

    }

    public class Body {
        @SerializedName("messages")
        public ArrayList<Object> messages;

        @SerializedName("msg")
        public ArrayList<Object> msg;

        @SerializedName("memo")
        public String memo;
    }


    public boolean isSuccess() {
        if (data.code > 0) {
            return false;
        }
        return true;
    }

    public JSONArray getMsgs() {
        if (data != null && data.tx != null && data.tx.body != null && data.tx.body.messages != null) {
            return new JSONArray(data.tx.body.messages);
        } else if  (data != null && data.tx != null && data.tx.value != null && data.tx.value.msg != null) {
            return new JSONArray(data.tx.value.msg);
        }
        return null;
    }

    public int getMsgCnt() {
        if (getMsgs() != null) {
            return getMsgs().length();
        }
        return 0;
    }

    public String getMsgType(Context c, String address) {
        if (getMsgCnt() == 0) {
            return c.getString(R.string.tx_known);
        } else {
            try {
                String result = c.getString(R.string.tx_known);

                if (getMsgCnt() >= 2) {
                    String msgType0 = "";
                    String msgType1 = "";
                    try {
                        msgType0 = getMsgs().getJSONObject(0).getString("@type");
                    } catch (Exception e) { }
                    try {
                        msgType0 = getMsgs().getJSONObject(0).getString("type");
                    } catch (Exception e) { }
                    try {
                        msgType1 = getMsgs().getJSONObject(1).getString("@type");
                    } catch (Exception e) { }
                    try {
                        msgType1 = getMsgs().getJSONObject(1).getString("type");
                    } catch (Exception e) { }

                    if (msgType0.contains("MsgWithdrawDelegatorReward") && msgType1.contains("MsgDelegate") ||
                            msgType0.contains("MsgWithdrawDelegationReward") && msgType1.contains("MsgDelegate")) {
                        if (getMsgCnt() == 2) return c.getString(R.string.tx_reinvest);
                        else return c.getString(R.string.tx_reinvest) + " + " + (getMsgCnt() - 1) / 2;
                    }

                    if (msgType1.contains("ibc") && msgType1.contains("MsgRecvPacket")) {
                        return c.getString(R.string.tx_ibc_receive);
                    }

                }
                String msgType = "";
                try {
                    msgType = getMsgs().getJSONObject(0).getString("@type");
                } catch (Exception e) { }
                try {
                    msgType = getMsgs().getJSONObject(0).getString("type");
                } catch (Exception e) { }

                // cosmos default msg type
                if (msgType.contains("cosmos.") && msgType.contains("staking")) {
                    if (msgType.contains("MsgCreateValidator")) {
                        result = c.getString(R.string.tx_create_validator);
                    } else if (msgType.contains("MsgEditValidator")) {
                        result = c.getString(R.string.tx_edit_validator);
                    } else if (msgType.contains("MsgDelegate")) {
                        result = c.getString(R.string.tx_delegate);
                    } else if (msgType.contains("MsgUndelegate")) {
                        result = c.getString(R.string.tx_undelegate);
                    } else if (msgType.contains("MsgBeginRedelegate")) {
                        result = c.getString(R.string.tx_redelegate);
                    } else if (msgType.contains("MsgCancelUnbondingDelegation")) {
                        result = c.getString(R.string.tx_cancel_undelegate);
                    }

                } else if (msgType.contains("cosmos.") && msgType.contains("bank")) {
                    if (msgType.contains("MsgSend")) {
                        String to_address = "";
                        String from_address = "";
                        try {
                            to_address = getMsgs().getJSONObject(0).getString("to_address");
                            from_address = getMsgs().getJSONObject(0).getString("from_address");
                        } catch (Exception e) { }
                        try {
                            to_address = getMsgs().getJSONObject(0).getJSONObject("value").getString("to_address");
                            from_address = getMsgs().getJSONObject(0).getJSONObject("value").getString("from_address");
                        } catch (Exception e) { }
                        if (to_address.equals(address)) {
                            result = c.getString(R.string.tx_receive);
                        } else if (from_address.equals(address)) {
                            result = c.getString(R.string.tx_send);
                        } else {
                            result = c.getString(R.string.tx_transfer);
                        }
                    } else if (msgType.contains("MsgMultiSend")) {
                        result = c.getString(R.string.tx_transfer);

                    }

                } else if (msgType.contains("cosmos.") && msgType.contains("distribution")) {
                    if (msgType.contains("MsgSetWithdrawAddress") || msgType.contains("MsgModifyWithdrawAddress")) {
                        result = c.getString(R.string.tx_change_reward_address);
                    } else if (msgType.contains("MsgWithdrawDelegatorReward") || msgType.contains("MsgWithdrawDelegationReward")) {
                        result = c.getString(R.string.tx_get_reward);
                    } else if (msgType.contains("MsgWithdrawValidatorCommission")) {
                        result = c.getString(R.string.tx_get_commission);
                    } else if (msgType.contains("MsgFundCommunityPool")) {
                        result = c.getString(R.string.tx_fund_pool);
                    }

                } else if (msgType.contains("cosmos.") && msgType.contains("gov")) {
                    if (msgType.contains("MsgSubmitProposal")) {
                        result = c.getString(R.string.tx_submit_proposal);

                    } else if (msgType.contains("MsgDeposit")) {
                        result = c.getString(R.string.tx_deposit_proposal);

                    } else if (msgType.contains("MsgVote")) {
                        result = c.getString(R.string.tx_vote);

                    } else if (msgType.contains("MsgVoteWeighted")) {
                        result = c.getString(R.string.tx_vote_weighted);

                    }

                } else if (msgType.contains("cosmos.") && msgType.contains("authz")) {
                    if (msgType.contains("MsgGrant")) {
                        result = c.getString(R.string.tx_authz_grant);
                    } else if (msgType.contains("MsgRevoke")) {
                        result = c.getString(R.string.tx_authz_revoke);
                    } else if (msgType.contains("MsgExec")) {
                        result = c.getString(R.string.tx_authz_exe);
                    }

                } else if (msgType.contains("cosmos.") && msgType.contains("slashing")) {
                    if (msgType.contains("MsgUnjail")) {
                        result = c.getString(R.string.tx_unjail);
                    }
                }

                // ibc msg type
                else if (msgType.contains("ibc.")) {
                    if (msgType.contains("MsgTransfer")) {
                        result = c.getString(R.string.tx_ibc_send);
                    } else if (msgType.contains("MsgUpdateClient")) {
                        result = c.getString(R.string.tx_ibc_update_client);
                    } else if (msgType.contains("MsgAcknowledgement")) {
                        result = c.getString(R.string.tx_ibc_acknowledgement);
                    } else if (msgType.contains("MsgRecvPacket")) {
                        result = c.getString(R.string.tx_ibc_receive);
                    }
                }

                // crescent msg type
                else if (msgType.contains("crescent.") && msgType.contains("liquidstaking")) {
                    if (msgType.contains("MsgLiquidStake")) {
                        result = c.getString(R.string.tx_crescent_liquid_stake);
                    } else if (msgType.contains("MsgLiquidUnstake")) {
                        result = c.getString(R.string.tx_crescent_liquid_unstake);
                    }
                } else if (msgType.contains("crescent.") && msgType.contains("liquidity")) {
                    if (msgType.contains("MsgCreatePair")) {
                        result = c.getString(R.string.tx_crescent_liquidity_create_pair);
                    } else if (msgType.contains("MsgCreatePool")) {
                        result = c.getString(R.string.tx_crescent_liquidity_create_pool);
                    } else if (msgType.contains("MsgDeposit")) {
                        result = c.getString(R.string.tx_crescent_liquidity_deposit);
                    } else if (msgType.contains("MsgWithdraw")) {
                        result = c.getString(R.string.tx_crescent_liquidity_withdraw);
                    } else if (msgType.contains("MsgLimitOrder")) {
                        result = c.getString(R.string.tx_crescent_liquidity_limit_order);
                    } else if (msgType.contains("MsgMarketOrder")) {
                        result = c.getString(R.string.tx_crescent_liquidity_market_order);
                    } else if (msgType.contains("MsgCancelOrder")) {
                        result = c.getString(R.string.tx_crescent_liquidity_cancel_order);
                    } else if (msgType.contains("MsgCancelAllOrders")) {
                        result = c.getString(R.string.tx_crescent_liquidity_cancelall_order);
                    }

                } else if (msgType.contains("crescent.") && msgType.contains("farming")) {
                    if (msgType.contains("MsgStake")) {
                        result = c.getString(R.string.tx_crescent_farming_stake);
                    } else if (msgType.contains("MsgUnstake")) {
                        result = c.getString(R.string.tx_crescent_farming_unstake);
                    } else if (msgType.contains("MsgHarvest")) {
                        result = c.getString(R.string.tx_crescent_farming_harvest);
                    } else if (msgType.contains("MsgCreateFixedAmountPlan")) {
                        result = c.getString(R.string.tx_crescent_farming_create_fixed_amount_plan);
                    } else if (msgType.contains("MsgCreateRatioPlan")) {
                        result = c.getString(R.string.tx_crescent_farming_create_ratio_plan);
                    } else if (msgType.contains("MsgRemovePlan")) {
                        result = c.getString(R.string.tx_crescent_farming_remove_plan);
                    } else if (msgType.contains("MsgAdvanceEpoch")) {
                        result = c.getString(R.string.tx_crescent_farming_advance_epoch);
                    }

                } else if (msgType.contains("crescent.") && msgType.contains("claim")) {
                    if (msgType.contains("MsgClaim")) {
                        result = c.getString(R.string.tx_crescent_farming_claim);
                    }
                }

                // irismod msg type
                else if (msgType.contains("irismod.") && msgType.contains("nft")) {
                    if (msgType.contains("MsgMintNFT")) {
                        result = c.getString(R.string.tx_mint_nft);
                    } else if (msgType.contains("MsgTransferNFT")) {
                        String senderAddr = getMsgs().getJSONObject(0).getString("sender");
                        String receiveAddr = getMsgs().getJSONObject(0).getString("recipient");
                        if (senderAddr.equalsIgnoreCase(address)) {
                            result = c.getString(R.string.tx_send_nft);
                        } else if (receiveAddr.equalsIgnoreCase(address)) {
                            result = c.getString(R.string.tx_receive_nft);
                        } else {
                            result = c.getString(R.string.tx_transfer_nft);
                        }
                    } else if (msgType.contains("MsgEditNFT")) {
                        result = "NFT Edit";
                    } else if (msgType.contains("MsgIssueDenom")) {
                        result = c.getString(R.string.tx_issue_denom);
                    } else if (msgType.contains("MsgRequestRandom")) {
                        result = "Random Request";
                    }
                }

                // crypto msg type
                else if (msgType.contains("chainmain.") && msgType.contains("nft")) {
                    if (msgType.contains("MsgMintNFT")) {
                        result = c.getString(R.string.tx_mint_nft);
                    } else if (msgType.contains("MsgTransferNFT")) {
                        String senderAddr = getMsgs().getJSONObject(0).getString("sender");
                        String receiveAddr = getMsgs().getJSONObject(0).getString("recipient");
                        if (senderAddr.equalsIgnoreCase(address)) {
                            result = c.getString(R.string.tx_send_nft);
                        } else if (receiveAddr.equalsIgnoreCase(address)) {
                            result = c.getString(R.string.tx_receive_nft);
                        } else {
                            result = c.getString(R.string.tx_transfer_nft);
                        }
                    } else if (msgType.contains("MsgEditNFT")) {
                        result = "NFT Edit";
                    } else if (msgType.contains("MsgIssueDenom")) {
                        result = c.getString(R.string.tx_issue_denom);
                    } else if (msgType.contains("MsgRequestRandom")) {
                        result = "Random Request";
                    }
                }

                // starname msg type
                else if (msgType.contains("starnamed.") && msgType.contains("starname")) {
                    if (msgType.contains("RegisterDomain")) {
                        result = c.getString(R.string.tx_starname_registe_domain);
                    } else if (msgType.contains("RegisterAccount")) {
                        result = c.getString(R.string.tx_starname_registe_account);
                    } else if (msgType.contains("DeleteDomain")) {
                        result = c.getString(R.string.tx_starname_delete_domain);
                    } else if (msgType.contains("DeleteAccount")) {
                        result = c.getString(R.string.tx_starname_delete_account);
                    } else if (msgType.contains("RenewDomain")) {
                        result = c.getString(R.string.tx_starname_renew_domain);
                    } else if (msgType.contains("RenewAccount")) {
                        result = c.getString(R.string.tx_starname_renew_account);
                    } else if (msgType.contains("ReplaceAccountResources")) {
                        result = c.getString(R.string.tx_starname_update_resource);
                    }
                }

                // osmosis msg type
                else if (msgType.contains("osmosis.") && msgType.contains("gamm")) {
                    if (msgType.contains("MsgSwapExactAmountIn")) {
                        result = c.getString(R.string.tx_osmosis_coin_swap);
                    } else if (msgType.contains("MsgSwapExactAmountOut")) {
                        result = c.getString(R.string.tx_osmosis_coin_swap);
                    } else if (msgType.contains("MsgJoinPool")) {
                        result = c.getString(R.string.tx_osmosis_join_pool);
                    } else if (msgType.contains("MsgExitPool")) {
                        result = c.getString(R.string.tx_osmosis_exit_pool);
                    } else if (msgType.contains("MsgJoinSwapExternAmountIn")) {
                        result = c.getString(R.string.tx_osmosis_coin_swap);
                    } else if (msgType.contains("MsgJoinSwapShareAmountOut")) {
                        result = c.getString(R.string.tx_osmosis_coin_swap);
                    } else if (msgType.contains("MsgExitSwapExternAmountOut")) {
                        result = c.getString(R.string.tx_osmosis_coin_swap);
                    } else if (msgType.contains("MsgExitSwapShareAmountIn")) {
                        result = c.getString(R.string.tx_osmosis_coin_swap);
                    } else if (msgType.contains("MsgCreatePool")) {
                        result = c.getString(R.string.tx_osmosis_create_pool);
                    } else if (msgType.contains("MsgCreateBalancerPool")) {
                        result = c.getString(R.string.tx_osmosis_create_pool);
                    }
                } else if (msgType.contains("osmosis.") && msgType.contains("lockup")) {
                    if (msgType.contains("MsgLockTokens")) {
                        result = c.getString(R.string.tx_osmosis_token_lockup);
                    } else if (msgType.contains("MsgBeginUnlockingAll")) {
                        result = c.getString(R.string.tx_osmosis_begin_token_unlock_all);
                    } else if (msgType.contains("MsgBeginUnlocking")) {
                        result = c.getString(R.string.tx_osmosis_begin_token_unlock);
                    }
                }

                // medi msg type
                else if (msgType.contains("panacea.") && msgType.contains("aol")) {
                    if (msgType.contains("MsgAddRecord")) {
                        result = c.getString(R.string.tx_add_record);
                    } else if (msgType.contains("MsgCreateTopic")) {
                        result = c.getString(R.string.tx_create_topic);
                    } else if (msgType.contains("MsgAddWriter")) {
                        result = c.getString(R.string.tx_add_writer);
                    }

                } else if (msgType.contains("panacea.") && msgType.contains("did")) {
                    if (msgType.contains("MsgCreateDID")) {
                        result = c.getString(R.string.tx_create_did);
                    }
                }

                // akash
                else if (msgType.contains("akash.") && msgType.contains("market")) {
                    if (msgType.contains("MsgCreateBid")) {
                        result = c.getString(R.string.tx_create_bid);
                    } else if (msgType.contains("MsgCloseBid")) {
                        result = c.getString(R.string.tx_close_bid);
                    } else if (msgType.contains("MsgCreateLease")) {
                        result = c.getString(R.string.tx_create_lease);
                    } else if (msgType.contains("MsgWithdrawLease")) {
                        result = c.getString(R.string.tx_withdraw_lease);
                    } else if (msgType.contains("MsgCloseLease")) {
                        result = c.getString(R.string.tx_close_lease);
                    }

                } else if (msgType.contains("akash.") && msgType.contains("deployment")) {
                    if (msgType.contains("MsgCreateDeployment")) {
                        result = c.getString(R.string.tx_create_deployment);
                    } else if (msgType.contains("MsgDepositDeployment")) {
                        result = c.getString(R.string.tx_deposit_deployment);
                    } else if (msgType.contains("MsgUpdateDeployment")) {
                        result = c.getString(R.string.tx_update_deployment);
                    } else if (msgType.contains("MsgCloseDeployment")) {
                        result = c.getString(R.string.tx_close_deployment);
                    } else if (msgType.contains("MsgCloseGroup")) {
                        result = c.getString(R.string.tx_close_group);
                    } else if (msgType.contains("MsgPauseGroup")) {
                        result = c.getString(R.string.tx_pause_group);
                    } else if (msgType.contains("MsgStartGroup")) {
                        result = c.getString(R.string.tx_start_group);
                    }

                } else if (msgType.contains("akash.") && msgType.contains("cert")) {
                    if (msgType.contains("MsgCreateCertificate")) {
                        result = c.getString(R.string.tx_create_certificate);
                    } else if (msgType.contains("MsgRevokeCertificate")) {
                        result = c.getString(R.string.tx_revoke_certificate);
                    }
                }

                // rizon msg type
                else if (msgType.contains("rizonworld.") && msgType.contains("tokenswap")) {
                    if (msgType.contains("MsgCreateTokenswapRequest")) {
                        result = c.getString(R.string.tx_rizon_create_Token_swap);
                    }
                }

                // gravity dex msg type
                else if (msgType.contains("tendermint.") && msgType.contains("liquidity")) {
                    if (msgType.contains("MsgDepositWithinBatch")) {
                        result = c.getString(R.string.tx_gravity_deposit_batch);
                    } else if (msgType.contains("MsgSwapWithinBatch")) {
                        result = c.getString(R.string.tx_gravity_swap_batch);
                    } else if (msgType.contains("MsgWithdrawWithinBatch")) {
                        result = c.getString(R.string.tx_gravity_withdraw_batch);
                    }
                }

                // sif msg type
                else if (msgType.contains("sifnode.") && msgType.contains("clp")) {
                    if (msgType.contains("MsgRemoveLiquidity")) {
                        result = c.getString(R.string.tx_sif_exit_pool);
                    } else if (msgType.contains("MsgCreatePool")) {
                        result = c.getString(R.string.tx_osmosis_create_pool);
                    } else if (msgType.contains("MsgAddLiquidity")) {
                        result = c.getString(R.string.tx_sif_join_pool);
                    } else if (msgType.contains("MsgSwap")) {
                        result = c.getString(R.string.tx_sif_swap);
                    } else if (msgType.contains("MsgDecommissionPool")) {

                    } else if (msgType.contains("MsgUnlockLiquidityRequest")) {

                    } else if (msgType.contains("MsgUpdateRewardsParamsRequest")) {

                    } else if (msgType.contains("MsgAddRewardPeriodRequest")) {

                    } else if (msgType.contains("MsgModifyPmtpRates")) {

                    } else if (msgType.contains("MsgUpdatePmtpParams")) {

                    } else if (msgType.contains("MsgUpdateStakingRewardParams")) {

                    }
                } else if (msgType.contains("sifnode.") && msgType.contains("dispensation")) {
                    if (msgType.contains("MsgCreateUserClaim")) {
                        result = c.getString(R.string.tx_create_user_claim);
                    } else if (msgType.contains("MsgRunDistribution")) {
                        result = c.getString(R.string.tx_create_distribution_run);
                    }

                } else if (msgType.contains("sifnode.") && msgType.contains("ethbridge")) {
                    if (msgType.contains("MsgCreateEthBridgeClaim")) {
                        result = c.getString(R.string.tx_create_ethereum_bridge);
                    }
                }

                // desmos msg type
                else if (msgType.contains("desmos.") && msgType.contains("profiles")) {
                    if (msgType.contains("MsgSaveProfile")) {
                        result = c.getString(R.string.tx_save_profile);
                    } else if (msgType.contains("MsgDeleteProfile")) {
                        result = c.getString(R.string.tx_delete_profile);
                    } else if (msgType.contains("MsgCreateRelationship")) {
                        result = c.getString(R.string.tx_create_relation);
                    } else if (msgType.contains("MsgDeleteRelationship")) {
                        result = c.getString(R.string.tx_delete_relation);
                    } else if (msgType.contains("MsgBlockUser")) {
                        result = c.getString(R.string.tx_block_user);
                    } else if (msgType.contains("MsgUnblockUser")) {
                        result = c.getString(R.string.tx_unblock_user);
                    } else if (msgType.contains("MsgRequestDTagTransfer")) {

                    } else if (msgType.contains("MsgCancelDTagTransferRequest")) {

                    } else if (msgType.contains("MsgAcceptDTagTransferRequest")) {

                    } else if (msgType.contains("MsgRefuseDTagTransferRequest")) {

                    } else if (msgType.contains("MsgLinkChainAccount")) {
                        result = c.getString(R.string.tx_link_chain_account);
                    } else if (msgType.contains("MsgUnlinkChainAccount")) {

                    } else if (msgType.contains("MsgLinkApplication")) {

                    } else if (msgType.contains("MsgUnlinkApplication")) {

                    } else if (msgType.contains("MsgUnlinkChainAccount")) {

                    }
                }

                // wasm msg type
                else if (msgType.contains("cosmwasm.")) {
                    if (msgType.contains("MsgStoreCode")) {
                        result = c.getString(R.string.tx_cosmwasm_store_code);
                    } else if (msgType.contains("MsgInstantiateContract")) {
                        result = c.getString(R.string.tx_cosmwasm_instantiate);
                    } else if (msgType.contains("MsgExecuteContract")) {
                        result = c.getString(R.string.tx_cosmwasm_execontract);

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
                }

                // kava msg type
                else if (msgType.contains("kava.") && msgType.contains("auction")) {
                    if (msgType.contains("MsgPlaceBid")) {
                        result = c.getString(R.string.tx_kava_auction_bid);
                    }

                } else if (msgType.contains("kava.") && msgType.contains("cdp")) {
                    if (msgType.contains("MsgCreateCDP")) {
                        result = c.getString(R.string.tx_kava_create_cdp);
                    } else if (msgType.contains("MsgDeposit")) {
                        result = c.getString(R.string.tx_kava_deposit_cdp);
                    } else if (msgType.contains("MsgWithdraw")) {
                        result = c.getString(R.string.tx_kava_withdraw_cdp);
                    } else if (msgType.contains("MsgDrawDebt")) {
                        result = c.getString(R.string.tx_kava_drawdebt_cdp);
                    } else if (msgType.contains("MsgRepayDebt")) {
                        result = c.getString(R.string.tx_kava_repaydebt_cdp);
                    } else if (msgType.contains("MsgLiquidate")) {
                        result = c.getString(R.string.tx_kava_liquidate_cdp);
                    }

                } else if (msgType.contains("kava.") && msgType.contains("swap")) {
                    if (msgType.contains("MsgDeposit")) {
                        result = c.getString(R.string.tx_kava_swap_deposit);
                    } else if (msgType.contains("MsgWithdraw")) {
                        result = c.getString(R.string.tx_kava_swap_withdraw);
                    } else if (msgType.contains("MsgSwapExactForTokens")) {
                        result = c.getString(R.string.tx_kava_swap_token);
                    } else if (msgType.contains("MsgSwapForExactTokens")) {
                        result = c.getString(R.string.tx_kava_swap_token);
                    }

                } else if (msgType.contains("kava.") && msgType.contains("hard")) {
                    if (msgType.contains("MsgDeposit")) {
                        result = c.getString(R.string.tx_kava_hard_deposit);
                    } else if (msgType.contains("MsgWithdraw")) {
                        result = c.getString(R.string.tx_kava_hard_withdraw);
                    } else if (msgType.contains("MsgBorrow")) {
                        result = c.getString(R.string.tx_kava_hard_borrow);
                    } else if (msgType.contains("MsgRepay")) {
                        result = c.getString(R.string.tx_kava_hard_repay);
                    } else if (msgType.contains("MsgLiquidate")) {
                        result = c.getString(R.string.tx_kava_hard_liquidate);
                    }

                } else if (msgType.contains("kava.") && msgType.contains("savings")) {
                    if (msgType.contains("MsgDeposit")) {
                        result = c.getString(R.string.tx_kava_save_deposit);
                    } else if (msgType.contains("MsgWithdraw")) {
                        result = c.getString(R.string.tx_kava_save_withdraw);
                    }

                } else if (msgType.contains("kava.") && msgType.contains("incentive")) {
                    if (msgType.contains("MsgClaimUSDXMintingReward")) {
                        result = c.getString(R.string.tx_kava_hard_mint_incentive);
                    } else if (msgType.contains("MsgClaimHardReward")) {
                        result = c.getString(R.string.tx_kava_hard_hard_incentive);
                    } else if (msgType.contains("MsgClaimDelegatorReward")) {
                        result = c.getString(R.string.tx_kava_hard_hard_incentive);
                    } else if (msgType.contains("MsgClaimSwapReward")) {
                        result = c.getString(R.string.tx_kava_save_withdraw);
                    } else if (msgType.contains("MsgClaimSavingsReward")) {
                        result = c.getString(R.string.tx_kava_hard_save_incentive);
                    }

                } else if (msgType.contains("kava.") && msgType.contains("bep3")) {
                    if (msgType.contains("MsgCreateAtomicSwap")) {
                        result = c.getString(R.string.tx_kava_bep3_create);
                    } else if (msgType.contains("MsgClaimAtomicSwap")) {
                        result = c.getString(R.string.tx_kava_bep3_claim);
                    } else if (msgType.contains("MsgRefundAtomicSwap")) {
                        result = c.getString(R.string.tx_kava_bep3_refund);
                    }

                }

                // certik msg
                else if (msgType.contains("shentu.") && msgType.contains("oracle")) {
                    if (msgType.contains("MsgTaskResponse")) {
                        result = c.getString(R.string.tx_task_response);
                    } else if (msgType.contains("MsgCreateTask")) {
                        result = c .getString(R.string.tx_create_task);
                    }
                }

                // osmosis superfluid
                else if (msgType.contains("osmosis.") && msgType.contains("superfluid")) {
                if (msgType.contains("MsgSuperfluidDelegate")) {
                    result = c.getString(R.string.tx_osmosis_super_fluid_delegate);
                } else if (msgType.contains("MsgSuperfluidUndelegate")) {
                    result = c.getString(R.string.tx_osmosis_super_fluid_undelegate);
                } else if (msgType.contains("MsgSuperfluidUnbondLock")) {
                    result = c.getString(R.string.tx_osmosis_super_fluid_unbondinglock);
                } else if (msgType.contains("MsgLockAndSuperfluidDelegate")) {
                    result = c.getString(R.string.tx_osmosis_super_fluid_lockanddelegate);
                }
            }


            if (getMsgCnt() > 1) {
                    result = result + " + " + (getMsgCnt() - 1);
                }
                return result;
            } catch (Exception e) {
                WLog.w("Exception " + e.getMessage());
            }
            return c.getString(R.string.tx_known);
        }
    }

    public Coin getDpCoin(BaseChain chain) {
        //display staking reward amount
        if (getMsgCnt() > 0) {
            boolean allReward = true;
            for (int i = 0; i < getMsgs().length(); i++) {
                String msgType = "";
                try {
                    msgType = getMsgs().getJSONObject(0).getString("@type");
                } catch (Exception e) { }
                try {
                    msgType = getMsgs().getJSONObject(0).getString("type");
                } catch (Exception e) { }
                if (!msgType.contains("MsgWithdrawDelegatorReward")) {
                    allReward = false;
                    break;
                }
            }
            if (allReward) {
                if (data != null && data.logs != null) {
                    BigDecimal totalRewardSum = BigDecimal.ZERO;
                    for (int i = 0; i < data.logs.size(); i ++) {
                        try {
                            for (int j = 0; j < new JSONArray(data.logs).getJSONObject(i).getJSONArray("events").length(); j++) {
                                if (new JSONArray(data.logs).getJSONObject(i).getJSONArray("events").getJSONObject(j).getString("type").equalsIgnoreCase("transfer")) {
                                    String value = new JSONArray(data.logs).getJSONObject(i).getJSONArray("events").getJSONObject(j).
                                                    getJSONArray("attributes").getJSONObject(2).getString("value");
                                    for (String rawCoin: value.split(",")) {
                                        if (rawCoin.contains(WDp.mainDenom(chain))) {
                                            totalRewardSum = totalRewardSum.add(new BigDecimal(rawCoin.replaceAll("[^0-9]", "")));
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) { }
                    }
                    return new Coin(WDp.mainDenom(chain), totalRewardSum.toString());
                }
            }
        }

        //display re-invset amount
        if (getMsgCnt() == 2) {
            String msgType0 = "";
            String msgType1 = "";
            try {
                msgType0 = getMsgs().getJSONObject(0).getString("@type");
            } catch (Exception e) { }
            try {
                msgType0 = getMsgs().getJSONObject(0).getString("type");
            } catch (Exception e) { }
            try {
                msgType1 = getMsgs().getJSONObject(1).getString("@type");
            } catch (Exception e) { }
            try {
                msgType1 = getMsgs().getJSONObject(1).getString("type");
            } catch (Exception e) { }
            if (msgType0.contains("MsgWithdrawDelegatorReward") && msgType1.contains("MsgDelegate") ||
                    msgType0.contains("MsgWithdrawDelegationReward") && msgType1.contains("MsgDelegate")) {
                String denom = "";
                String amount = "";
                try {
                    denom = getMsgs().getJSONObject(1).getJSONObject("amount").getString("denom");
                    amount = getMsgs().getJSONObject(1).getJSONObject("amount").getString("amount");
                } catch (JSONException e) { e.printStackTrace(); }
                try {
                    denom = getMsgs().getJSONObject(1).getJSONObject("value").getJSONObject("amount").getString("denom");
                    amount = getMsgs().getJSONObject(1).getJSONObject("value").getJSONObject("amount").getString("amount");
                } catch (JSONException e) { e.printStackTrace(); }
                return new Coin(denom, amount);
            }
        }

        if (getMsgCnt() == 0 || getMsgCnt() > 1) {
            return null;
        }
        String msgType = "";
        try {
            msgType = getMsgs().getJSONObject(0).getString("@type");
        } catch (Exception e) { }

        try {
            msgType = getMsgs().getJSONObject(0).getString("type");
        } catch (Exception e) { }

        String denom = "";
        String amount = "";
        if (msgType.contains("MsgSendToEth")) {

        } else if (msgType.contains("MsgSend")) {
            try {
                denom = getMsgs().getJSONObject(0).getJSONArray("amount").getJSONObject(0).getString("denom");
                amount = getMsgs().getJSONObject(0).getJSONArray("amount").getJSONObject(0).getString("amount");
            } catch (JSONException e) {  }
            try {
                denom = getMsgs().getJSONObject(0).getJSONObject("value").getJSONArray("amount").getJSONObject(0).getString("denom");
                amount = getMsgs().getJSONObject(0).getJSONObject("value").getJSONArray("amount").getJSONObject(0).getString("amount");
            } catch (JSONException e) {  }
            return new Coin(denom, amount);

        } else if (msgType.contains("MsgDelegate") || msgType.contains("MsgUndelegate") || msgType.contains("MsgBeginRedelegate")) {
            try {
                denom = getMsgs().getJSONObject(0).getJSONObject("amount").getString("denom");
                amount = getMsgs().getJSONObject(0).getJSONObject("amount").getString("amount");
            } catch (JSONException e) {   }
            try {
                denom = getMsgs().getJSONObject(0).getJSONObject("value").getJSONObject("amount").getString("denom");
                amount = getMsgs().getJSONObject(0).getJSONObject("value").getJSONObject("amount").getString("amount");
            } catch (JSONException e) {  }
            return new Coin(denom, amount);

        } else if (msgType.contains("ibc") && msgType.contains("MsgTransfer")) {
            try {
                denom = getMsgs().getJSONObject(0).getJSONObject("token").getString("denom");
                amount = getMsgs().getJSONObject(0).getJSONObject("token").getString("amount");
            } catch (JSONException e) {   }
            return new Coin(denom, amount);
        }
        return null;
    }

    public String getVoteOption() {
        try {
            String result = "";
            String msgType = "";
            try {
                msgType = getMsgs().getJSONObject(0).getString("@type");
            } catch (Exception e) { }
            try {
                msgType = getMsgs().getJSONObject(0).getString("type");
            } catch (Exception e) { }
            String option = "";
            try {
                option = getMsgs().getJSONObject(0).getString("option");
            } catch (Exception e) { }
            try {
                option = getMsgs().getJSONObject(0).getJSONObject("value").getString("option");
            } catch (Exception e) { }
            if (msgType.contains("MsgVote")) {
                if (option.equals("VOTE_OPTION_YES") || option.equals("Yes")) {
                    result = "YES";
                } else if (option.equals("VOTE_OPTION_NO") || option.equals("No")) {
                    result = "NO";
                } else if (option.equals("VOTE_OPTION_ABSTAIN") || option.equals("Abstain")) {
                    result = "ABSTAIN";
                } else if (option.equals("VOTE_OPTION_NO_WITH_VETO") || option.equals("NoWithVeto")) {
                    result = "VETO";
                }
            }
            return result;
        } catch (Exception e) { WLog.w("Exception : " + e.getMessage()); }
        return null;
    }


}

package wannabit.io.cosmostaion.cosmos;

import android.util.Base64;

import com.binance.dex.api.client.domain.broadcast.HtltReq;
import com.binance.dex.api.client.encoding.message.Token;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.crypto.DeterministicKey;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.IrisStdSignMsg;
import wannabit.io.cosmostaion.model.StarNameResource;
import wannabit.io.cosmostaion.model.StdSignMsg;
import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.model.type.Pub_key;
import wannabit.io.cosmostaion.model.type.Signature;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_TEST_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_TEST_BTC_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_TEST_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_TEST_BTC_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;



public class MsgGenerator {

    public static Msg genTransferMsg(String fromAddr, String toAddr, ArrayList<Coin> coins, BaseChain chain) {
        Msg         result      = new Msg();
        Msg.Value   value       = new Msg.Value();
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) ||
                chain.equals(BAND_MAIN) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) || chain.equals(AKASH_MAIN) || chain.equals(SECRET_MAIN)) {
            value.from_address = fromAddr;
            value.to_address = toAddr;
            value.amount = coins;

            result.type = BaseConstant.COSMOS_MSG_TYPE_TRANSFER2;
            result.value = value;

        } else if (chain.equals(IRIS_MAIN)) {
            ArrayList<Input> inputs     = new ArrayList<>();
            ArrayList<Output> outputs   = new ArrayList<>();
            Input input = new Input(fromAddr, coins);
            Output output = new Output(toAddr, coins);
            inputs.add(input);
            outputs.add(output);

            value.inputs = inputs;
            value.outputs = outputs;

            result.type = BaseConstant.IRIS_MSG_TYPE_TRANSFER;
            result.value = value;

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            value.from_address = fromAddr;
            value.to_address = toAddr;
            value.amount = coins;

            result.type = BaseConstant.OK_MSG_TYPE_TRANSFER;
            result.value = value;

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            value.from_address = fromAddr;
            value.to_address = toAddr;
            value.amount = coins;

            result.type = BaseConstant.CERTIK_MSG_TYPE_TRANSFER;
            result.value = value;

        }
        return result;
    }

    public static Msg genDelegateMsg(String fromAddr, String toValAddr, Coin toDeleagteAmout, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) ||
                chain.equals(BAND_MAIN) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(AKASH_MAIN) || chain.equals(SECRET_MAIN)) {
            value.delegator_address = fromAddr;
            value.validator_address = toValAddr;
            value.amount = toDeleagteAmout;

            result.type = BaseConstant.COSMOS_MSG_TYPE_DELEGATE;
            result.value = value;

        } else if (chain.equals(IRIS_MAIN)) {
            value.delegator_addr = fromAddr;
            value.validator_addr = toValAddr;
            value.delegation = toDeleagteAmout;

            result.type = BaseConstant.IRIS_MSG_TYPE_DELEGATE;
            result.value = value;

        }
        return result;
    }

    public static Msg genUnbondMsg(String requestAddr, String fromValAddr, Coin amount, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) ||
                chain.equals(BAND_MAIN) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(AKASH_MAIN) || chain.equals(SECRET_MAIN)) {
            value.delegator_address = requestAddr;
            value.validator_address = fromValAddr;
            value.amount = amount;

            result.type = BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE2;
            result.value = value;

        } else if (chain.equals(IRIS_MAIN)) {
            value.delegator_addr = requestAddr;
            value.validator_addr = fromValAddr;
            value.shares_amount = amount.amount + ".0000000000";

            result.type = BaseConstant.IRIS_MSG_TYPE_UNDELEGATE;
            result.value = value;
        }
        return result;
    }


    public static Msg genWithdrawDeleMsg(String requestAddr, String fromValAddr, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) ||
                chain.equals(BAND_MAIN) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(AKASH_MAIN) || chain.equals(SECRET_MAIN)) {
            value.delegator_address = requestAddr;
            value.validator_address = fromValAddr;

            result.type = BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL;
            result.value = value;

        } else if (chain.equals(IRIS_MAIN)) {
            value.delegator_addr = requestAddr;
            value.validator_addr = fromValAddr;

            result.type = BaseConstant.IRIS_MSG_TYPE_WITHDRAW;
            result.value = value;
        }
        return result;
    }

    public static Msg genWithdrawDeleAllMsg(String requestAddr, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.delegator_addr = requestAddr;

        result.type = BaseConstant.IRIS_MSG_TYPE_WITHDRAW_ALL;
        result.value = value;
        return result;
    }

    public static Msg genReDelegateMsg(String accountAddr, String fromValAddr, String toValAddr, Coin amount, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) ||
                chain.equals(BAND_MAIN) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(AKASH_MAIN) || chain.equals(SECRET_MAIN)) {
            value.delegator_address = accountAddr;
            value.validator_src_address = fromValAddr;
            value.validator_dst_address = toValAddr;
            value.amount = amount;

            result.type = BaseConstant.COSMOS_MSG_TYPE_REDELEGATE2;
            result.value = value;

        } else if (chain.equals(IRIS_MAIN)) {
            value.delegator_addr = accountAddr;
            value.validator_src_addr = fromValAddr;
            value.validator_dst_addr = toValAddr;
            //TODO need cal bal to shares.
//            value.shares = amount.amount + ".0000000000";
            value.shares_amount = amount.amount + ".0000000000";

            result.type = BaseConstant.IRIS_MSG_TYPE_REDELEGATE;
            result.value = value;
        }

        return result;
    }

    public static Msg genRewardAddressChange(String requestAddr, String newRewardAddr, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();

        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) ||
                chain.equals(BAND_MAIN) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(AKASH_MAIN) || chain.equals(SECRET_MAIN)) {
            value.delegator_address = requestAddr;
            value.withdraw_address = newRewardAddr;

            result.type = BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_MIDIFY;
            result.value = value;

        } else if (chain.equals(IRIS_MAIN)) {
            value.delegator_addr = requestAddr;
            value.withdraw_addr = newRewardAddr;

            result.type = BaseConstant.IRIS_MSG_TYPE_WITHDRAW_MIDIFY;
            result.value = value;
        }
        return result;
    }

    public static Msg genVoteMsg(String accountAddr, String proposalId, String opinion, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) ||
                chain.equals(BAND_MAIN) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(AKASH_MAIN) || chain.equals(SECRET_MAIN)) {
            value.proposal_id = proposalId;
            value.voter = accountAddr;
            value.option = opinion;

            result.type = BaseConstant.COSMOS_MSG_TYPE_VOTE;
            result.value = value;

        } else if (chain.equals(IRIS_MAIN)) {
            value.proposal_id = proposalId;
            value.voter = accountAddr;
            value.option = opinion;

            result.type = BaseConstant.IRIS_MSG_TYPE_VOTE;
            result.value = value;
        }

        return result;
    }


    public static Msg genCreateCdpMsg(String sender, Coin collateralCoin, Coin principalCoin, String collateralType, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.sender = sender;
        value.collateral = collateralCoin;
        value.principal = principalCoin;
        value.collateral_type = collateralType;

        result.type = BaseConstant.KAVA_MSG_TYPE_CREATE_CDP;
        result.value = value;
        return result;
    }

    public static Msg genRepayCdpMsg(String sender, Coin payment, String cdpDenom, String collateralType, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.sender = sender;
        value.payment = payment;
        value.collateral_type = collateralType;

        result.type = BaseConstant.KAVA_MSG_TYPE_REPAYDEBT_CDP;
        result.value = value;
        return result;
    }

    public static Msg genDrawDebtCdpMsg(String sender, Coin principal, String cdpDenom, String collateralType, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.sender = sender;
        value.principal = principal;
        value.collateral_type = collateralType;

        result.type = BaseConstant.KAVA_MSG_TYPE_DRAWDEBT_CDP;
        result.value = value;
        return result;
    }

    public static Msg genDepositCdpMsg(String owner, Coin collateral, String depositor, String collateralType, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.depositor = depositor;
        value.owner = owner;
        value.collateral = collateral;
        value.collateral_type = collateralType;

        result.type = BaseConstant.KAVA_MSG_TYPE_DEPOSIT_CDP;
        result.value = value;
        return result;
    }

    public static Msg genWithdrawCdpMsg(String owner, Coin collateral, String depositor, String collateralType, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.depositor = depositor;
        value.owner = owner;
        value.collateral = collateral;
        value.collateral_type = collateralType;

        result.type = BaseConstant.KAVA_MSG_TYPE_WITHDRAW_CDP;
        result.value = value;
        return result;
    }

    public static Msg genCreateSwapMsg(BaseChain fromChain, BaseChain toChain, Account fromAccount, Account toAccount, ArrayList<Coin> sendCoins, long timestamp, byte[] originData) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (fromChain.equals(KAVA_MAIN)) {
            if (sendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_BNB)) {
                value.to = KAVA_MAIN_BNB_DEPUTY;
                value.sender_other_chain = BINANCE_MAIN_BNB_DEPUTY;
            } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_BTCB)) {
                value.to = KAVA_MAIN_BTCB_DEPUTY;
                value.sender_other_chain = BINANCE_MAIN_BTCB_DEPUTY;
            } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_XRPB)) {
                value.to = KAVA_MAIN_XRPB_DEPUTY;
                value.sender_other_chain = BINANCE_MAIN_XRPB_DEPUTY;
            } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_BUSD)) {
                value.to = KAVA_MAIN_BUSD_DEPUTY;
                value.sender_other_chain = BINANCE_MAIN_BUSD_DEPUTY;
            }
            value.from = fromAccount.address;
            value.recipient_other_chain = toAccount.address;

            value.random_number_hash = WUtil.ByteArrayToHexString(Sha256.getSha256Digest().digest(originData)).toUpperCase();
            value.timestamp = String.valueOf(timestamp);
            value.amount = sendCoins;
            value.height_span = "250";

            result.type = BaseConstant.KAVA_MSG_TYPE_BEP3_CREATE_SWAP;
            result.value = value;

        } else if (fromChain.equals(KAVA_TEST)) {
            if (sendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_TEST_BNB)) {
                value.to = KAVA_TEST_BNB_DEPUTY;
                value.sender_other_chain = BINANCE_TEST_BNB_DEPUTY;
            } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_KAVA_TEST_BTC)) {
                value.to = KAVA_TEST_BTC_DEPUTY;
                value.sender_other_chain = BINANCE_TEST_BTC_DEPUTY;
            }
            value.from = fromAccount.address;
            value.recipient_other_chain = toAccount.address;

            value.random_number_hash = WUtil.ByteArrayToHexString(Sha256.getSha256Digest().digest(originData)).toUpperCase();
            value.timestamp = String.valueOf(timestamp);
            value.amount = sendCoins;
            value.height_span = "250";

            result.type = BaseConstant.KAVA_MSG_TYPE_BEP3_CREATE_SWAP;
            result.value = value;
        }
        return result;
    }

    public static Msg genClaimAtomicSwap(String from, String swapId, String randomNumber, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            value.from = from;
            value.swap_id = swapId.toUpperCase();
            value.random_number = randomNumber.toUpperCase();

            result.type = BaseConstant.KAVA_MSG_TYPE_BEP3_CLAM_SWAP;
            result.value = value;

        }
        return result;
    }


    public static Msg genRefundAtomicSwap(String from, String swapId, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            value.from = from;
            value.swap_id = swapId.toUpperCase();
            result.type = BaseConstant.KAVA_MSG_TYPE_BEP3_REFUND_SWAP;
            result.value = value;

        }
        return result;
    }

    public static HtltReq getBnbHtlcCreateMsg(BaseChain fromChain, BaseChain toChain, Account fromAccount, Account toAccount, ArrayList<Coin> sendCoins, long timestamp, byte[] originData) {
        HtltReq htltReq = new HtltReq();
        Coin toSendCoin = sendCoins.get(0);
        if (fromChain.equals(BaseChain.BNB_MAIN)) {
            if (toChain.equals(KAVA_MAIN)) {
                if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_BNB)) {
                    htltReq.setRecipient(BINANCE_MAIN_BNB_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_MAIN_BNB_DEPUTY);

                } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_BTCB)) {
                    htltReq.setRecipient(BINANCE_MAIN_BTCB_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_MAIN_BTCB_DEPUTY);

                } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_XRPB)) {
                    htltReq.setRecipient(BINANCE_MAIN_XRPB_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_MAIN_XRPB_DEPUTY);

                } else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_BUSD)) {
                    htltReq.setRecipient(BINANCE_MAIN_BUSD_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_MAIN_BUSD_DEPUTY);

                }

                htltReq.setRecipientOtherChain(toAccount.address);
                htltReq.setTimestamp(timestamp);
                htltReq.setRandomNumberHash(Sha256.getSha256Digest().digest(originData));
                Token token = new Token();
                token.setDenom(toSendCoin.denom);
                BigDecimal sendAmount = new BigDecimal(toSendCoin.amount).movePointRight(8);
                token.setAmount(sendAmount.longValue());
                htltReq.setOutAmount(Collections.singletonList(token));
                htltReq.setExpectedIncome(sendAmount.toPlainString() + ":" + toSendCoin.denom);
                htltReq.setHeightSpan(10001);
                htltReq.setCrossChain(true);

            } else if (toChain.equals(KAVA_TEST)) {
                //NO case
            }

        } else if (fromChain.equals(BaseChain.BNB_TEST)) {
            if (toChain.equals(KAVA_MAIN)) {
                //NO case

            } else if (toChain.equals(KAVA_TEST)) {
                if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_TEST_BNB)) {
                    htltReq.setRecipient(BINANCE_TEST_BNB_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_TEST_BNB_DEPUTY);
                }  else if (sendCoins.get(0).denom.equals(TOKEN_HTLC_BINANCE_TEST_BTC)) {
                    htltReq.setRecipient(BINANCE_TEST_BTC_DEPUTY);
                    htltReq.setSenderOtherChain(KAVA_TEST_BTC_DEPUTY);
                }
                htltReq.setRecipientOtherChain(toAccount.address);
                htltReq.setTimestamp(timestamp);
                htltReq.setRandomNumberHash(Sha256.getSha256Digest().digest(originData));
                Token token = new Token();
                token.setDenom(toSendCoin.denom);
                BigDecimal sendAmount = new BigDecimal(toSendCoin.amount).movePointRight(8);
                token.setAmount(sendAmount.longValue());
                htltReq.setOutAmount(Collections.singletonList(token));
                htltReq.setExpectedIncome(sendAmount.toPlainString() + ":" + toSendCoin.denom);
                htltReq.setHeightSpan(10001);
                htltReq.setCrossChain(true);
            }

        }

        return htltReq;
    }

    public static Msg genIncentiveReward(String from, String collateralType, String multiplierName, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            value.sender = from;
            value.collateral_type = collateralType;
            value.multiplier_name = multiplierName;
            result.type = BaseConstant.KAVA_MSG_TYPE_INCENTIVE_REWARD;
            result.value = value;

        }
        return result;
    }

    public static Msg genDepositHarvestMsg(String depositor, Coin depositCoin, String depositType, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.depositor = depositor;
        value.amount = depositCoin;
        value.deposit_type = depositType;
        result.type = BaseConstant.KAVA_MSG_TYPE_DEPOSIT_HAVEST;
        result.value = value;
        return result;
    }

    public static Msg genWithdrawHarvestMsg(String depositor, Coin depositCoin, String depositType, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.depositor = depositor;
        value.amount = depositCoin;
        value.deposit_type = depositType;
        result.type = BaseConstant.KAVA_MSG_TYPE_WITHDRAW_HAVEST;
        result.value = value;
        return result;
    }

    public static Msg genClaimHarvestMsg(String sender, String receiver, String depositDenom, String multiplierName, String depositType, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        value.sender = sender;
        value.receiver = receiver;
        value.deposit_denom = depositDenom;
        value.multiplier_name = multiplierName;
        value.deposit_type = depositType;
        result.type = BaseConstant.KAVA_MSG_TYPE_CLAIM_HAVEST;
        result.value = value;
        return result;
    }


    public static Msg genOkDeposit(String delegator, Coin coin, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            value.delegator_address = delegator;
            value.quantity = coin;

            result.type = BaseConstant.OK_MSG_TYPE_DEPOSIT;
            result.value = value;
        }
        return result;
    }

    public static Msg genOkWithdraw(String delegator, Coin coin, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            value.delegator_address = delegator;
            value.quantity = coin;

            result.type = BaseConstant.OK_MSG_TYPE_WITHDRAW;
            result.value = value;
        }
        return result;
    }

    public static Msg genOkVote(String delegator, ArrayList<String> toVals, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            value.delegator_address = delegator;
            value.validator_addresses = toVals;

            result.type = BaseConstant.OK_MSG_TYPE_DIRECT_VOTE;
            result.value = value;
        }
        return result;

    }

    public static Msg genDomainRegister(String domain, String admin, String type, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            value.domain = domain;
            value.admin = admin;
            value.type = type;
            value.broker = "";
            value.fee_payer = "";

            result.type = BaseConstant.IOV_MSG_TYPE_REGISTER_DOMAIN;
            result.value = value;
        }
        return result;
    }


    public static Msg genAccountRegister(String domain, String name, String owner, String registerer, ArrayList<StarNameResource> resources, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            value.domain = domain;
            value.name = name;
            value.owner = owner;
            value.registerer = registerer;
            value.resources = resources;
            value.broker = "";
            value.fee_payer = "";

            result.type = BaseConstant.IOV_MSG_TYPE_REGISTER_ACCOUNT;
            result.value = value;
        }
        return result;
    }

    public static Msg genDomainDelete(String domain, String owner, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            value.domain = domain;
            value.owner = owner;
            value.fee_payer = "";

            result.type = BaseConstant.IOV_MSG_TYPE_DELETE_DOMAIN;
            result.value = value;
        }
        return result;
    }

    public static Msg genAccountDelete(String domain, String name, String owner, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            value.domain = domain;
            value.name = name;
            value.owner = owner;
            value.fee_payer = "";

            result.type = BaseConstant.IOV_MSG_TYPE_DELETE_ACCOUNT;
            result.value = value;
        }
        return result;
    }

    public static Msg genDomainRenew(String domain, String signer, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            value.domain = domain;
            value.signer = signer;
            value.fee_payer = "";

            result.type = BaseConstant.IOV_MSG_TYPE_RENEW_DOMAIN;
            result.value = value;
        }
        return result;

    }

    public static Msg genAccountRenew(String domain, String name, String signer, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            value.domain = domain;
            value.name = name;
            value.signer = signer;
            value.fee_payer = "";

            result.type = BaseConstant.IOV_MSG_TYPE_RENEW_ACCOUNT;
            result.value = value;
        }
        return result;
    }

    public static Msg genReplaceStarName(String domain, String name, String owner, ArrayList<StarNameResource> resources, BaseChain chain) {
        Msg result  = new Msg();
        Msg.Value value = new Msg.Value();
        if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            value.domain = domain;
            value.name = name;
            value.owner = owner;
            value.new_resources = resources;
            value.fee_payer = "";

            result.type = BaseConstant.IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE;
            result.value = value;
        }
        return result;
    }



    public static StdTx genUnsignedTransferTx(ArrayList<Msg> msgs, Fee fee, String memo) {
        StdTx result = new StdTx();
        StdTx.Value value = new StdTx.Value();

        value.msg = msgs;
        value.fee = fee;
        value.signatures = null;
        value.memo = memo;

        result.type = BaseConstant.COSMOS_AUTH_TYPE_STDTX;
        result.value = value;
        return result;
    }

    public static StdTx genSignedTransferTx(ArrayList<Msg> msgs, Fee fee, String memo, ArrayList<Signature> signatures) {
        StdTx result = new StdTx();
        StdTx.Value value = new StdTx.Value();

        value.msg = msgs;
        value.fee = fee;
        value.signatures = signatures;
        value.memo = memo;

        result.type = BaseConstant.COSMOS_AUTH_TYPE_STDTX;
        result.value = value;
        return result;
    }

    public static StdTx genStakeSignedTransferTx(ArrayList<Msg> msgs, Fee fee, String memo, ArrayList<Signature> signatures) {
        StdTx result = new StdTx();
        StdTx.Value value = new StdTx.Value();

        value.msg = msgs;
        value.fee = fee;
        value.signatures = signatures;
        value.memo = memo;

        result.type = BaseConstant.COSMOS_AUTH_TYPE_STDTX;
        result.value = value;
        return result;
    }

    public static StdSignMsg genToSignMsg(String chainId, String accountNumber, String SequenceNumber, ArrayList<Msg> msgs, Fee fee, String memo) {
        StdSignMsg result = new StdSignMsg();
        result.chain_id = chainId;
        result.account_number = accountNumber;
        result.sequence = SequenceNumber;
        result.msgs = msgs;
        result.fee = fee;
        result.memo = memo;

        return result;
    }

    public static IrisStdSignMsg genIrisToSignMsg(String chainId, String accountNumber, String SequenceNumber, ArrayList<Msg> msgs, Fee fee, String memo) {
        IrisStdSignMsg result = new IrisStdSignMsg();
        result.chain_id = chainId;
        result.account_number = accountNumber;
        result.sequence = SequenceNumber;
        ArrayList<Msg.Value> tempMsgs = new ArrayList<>();
        for (Msg msg:msgs) {
            tempMsgs.add(msg.value);
        }
        result.msgs = tempMsgs;
        result.fee = fee;
        result.memo = memo;

        return result;
    }

    public static String getSignature(DeterministicKey key, byte[] toSignByte) {
        MessageDigest digest = Sha256.getSha256Digest();
        byte[] toSignHash = digest.digest(toSignByte);
        ECKey.ECDSASignature Signature = key.sign(Sha256Hash.wrap(toSignHash));
        byte[] sigData = new byte[64];
        System.arraycopy(integerToBytes(Signature.r, 32), 0, sigData, 0, 32);
        System.arraycopy(integerToBytes(Signature.s, 32), 0, sigData, 32, 32);
        String base64 = Base64.encodeToString(sigData, Base64.DEFAULT).replace("\n", "");
        return base64;
    }

    public static ReqBroadCast getBraodcaseReq(Account account, ArrayList<Msg> msgs, Fee fee, String memo, DeterministicKey key) {
        StdSignMsg tosign = genToSignMsg(
                BaseChain.getDpChain(account.baseChain),
                ""+account.accountNumber,
                ""+account.sequenceNumber,
                msgs,
                fee,
                memo);
//        WLog.w("tosign " + WUtil.prettyPrinter(tosign));

        String signatureTx = MsgGenerator.getSignature(key, tosign.getToSignByte());
//        WLog.w("signatureTx " + signatureTx);

        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;

        pubKey.value = WKey.getPubKeyValue(key);
        signature.pub_key = pubKey;
        signature.signature = signatureTx;
        signature.account_number = ""+account.accountNumber;
        signature.sequence = ""+account.sequenceNumber;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, fee, memo, signatures);
//        WLog.w("signedTx : " +  WUtil.prettyPrinter(signedTx));

        ReqBroadCast reqBroadCast = new ReqBroadCast();
        reqBroadCast.returns = "sync";
        reqBroadCast.tx = signedTx.value;

//        WLog.w("ReqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));


        return reqBroadCast;
    }


    public static ReqBroadCast getIrisBraodcaseReq(Account account, ArrayList<Msg> msgs, Fee fee, String memo, DeterministicKey key) {
        IrisStdSignMsg tosign = genIrisToSignMsg(
                BaseChain.getDpChain(account.baseChain),
                ""+account.accountNumber,
                ""+account.sequenceNumber,
                msgs,
                fee,
                memo);
        String signatureTx = MsgGenerator.getSignature(key, tosign.getToSignByte());
//        WLog.w("Iris Send signatureTx " + signatureTx);

        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = WKey.getPubKeyValue(key);
        signature.pub_key = pubKey;
        signature.signature = signatureTx;
        signature.account_number = ""+account.accountNumber;
        signature.sequence = ""+account.sequenceNumber;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, fee, memo, signatures);
//        WLog.w("Iris Send signedTx : " +  WUtil.prettyPrinter(signedTx));

        ReqBroadCast reqBroadCast = new ReqBroadCast();
        reqBroadCast.returns = "sync";
        reqBroadCast.tx = signedTx.value;

//        WLog.w("Iris Send ReqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));
        return reqBroadCast;
    }


    public static ReqBroadCast getIrisBraodcaseReq2(Account account, ArrayList<Msg> msgs, Fee fee, String memo, DeterministicKey key) {
        Msg tempMsg  = new Msg();
        Msg.Value tempValue = new Msg.Value();
        tempValue.delegator_addr = msgs.get(0).value.delegator_addr;
        tempValue.validator_src_addr = msgs.get(0).value.validator_src_addr;
        tempValue.validator_dst_addr = msgs.get(0).value.validator_dst_addr;
        tempValue.shares = msgs.get(0).value.shares_amount;
        tempMsg.type = BaseConstant.IRIS_MSG_TYPE_REDELEGATE;
        tempMsg.value = tempValue;

        ArrayList<Msg> tempMsgs= new ArrayList<>();
        tempMsgs.add(tempMsg);

        IrisStdSignMsg tosign = genIrisToSignMsg(
                BaseChain.getDpChain(account.baseChain),
                ""+account.accountNumber,
                ""+account.sequenceNumber,
                tempMsgs,
                fee,
                memo);
        String signatureTx = MsgGenerator.getSignature(key, tosign.getToSignByte());

        Signature signature = new Signature();
        Pub_key pubKey = new Pub_key();
        pubKey.type = BaseConstant.COSMOS_KEY_TYPE_PUBLIC;
        pubKey.value = WKey.getPubKeyValue(key);
        signature.pub_key = pubKey;
        signature.signature = signatureTx;
        signature.account_number = ""+account.accountNumber;
        signature.sequence = ""+account.sequenceNumber;

        ArrayList<Signature> signatures = new ArrayList<>();
        signatures.add(signature);

        StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, fee, memo, signatures);

        ReqBroadCast reqBroadCast = new ReqBroadCast();
        reqBroadCast.returns = "sync";
        reqBroadCast.tx = signedTx.value;

//        WLog.w("Iris Send ReqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));
        return reqBroadCast;
    }


    public static ReqBroadCast getOKexBraodcaseReq(Account account, ArrayList<Msg> msgs, Fee fee, String memo, DeterministicKey key) {
        if (!account.newBip44) {
            //using Tendermint type sig
            return getBraodcaseReq(account, msgs, fee, memo, key);
        } else {
            //using Ethermint type sig
            StdSignMsg tosign = genToSignMsg( BaseChain.getDpChain(account.baseChain), ""+account.accountNumber, ""+account.sequenceNumber, msgs, fee, memo);
            String sig = MsgGenerator.getEthermintSignature(key, tosign.getToSignByte());

            Signature signature = new Signature();
            Pub_key pubKey = new Pub_key();
            pubKey.type = BaseConstant.ETHERMINT_KEY_TYPE_PUBLIC;

            String pubHex = WKey.generatePubKeyHexFromPriv(key.getPrivateKeyAsHex());
            pubKey.value = Strings.fromByteArray(org.bouncycastle.util.encoders.Base64.encode(Hex.decode(pubHex)));
            signature.pub_key = pubKey;
            signature.signature = sig;
            signature.account_number = ""+account.accountNumber;
            signature.sequence = ""+account.sequenceNumber;

            ArrayList<Signature> signatures = new ArrayList<>();
            signatures.add(signature);

            StdTx signedTx = MsgGenerator.genStakeSignedTransferTx(msgs, fee, memo, signatures);
//            WLog.w("Ethermint signedTx : " +  WUtil.prettyPrinter(signedTx));

            ReqBroadCast reqBroadCast = new ReqBroadCast();
            reqBroadCast.returns = "sync";
            reqBroadCast.tx = signedTx.value;
//            WLog.w("Ethermint reqBroadCast : " +  WUtil.prettyPrinter(reqBroadCast));

            return reqBroadCast;
        }
    }


    public static String getEthermintSignature(DeterministicKey key, byte[] toSignByte) {
        BigInteger privKey = new BigInteger(key.getPrivateKeyAsHex(), 16);
        Sign.SignatureData sig = Sign.signMessage(toSignByte, ECKeyPair.create(privKey));
        return toBase64(sig);
    }

    public static String toBase64(Sign.SignatureData sig) {
        byte[] sigData = new byte[64];  // 32 bytes for R + 32 bytes for S
        System.arraycopy(sig.getR(), 0, sigData, 0, 32);
        System.arraycopy(sig.getS(), 0, sigData, 32, 32);
        return new String(org.bouncycastle.util.encoders.Base64.encode(sigData), Charset.forName("UTF-8"));
    }
}

package wannabit.io.cosmostaion.cosmos;

import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf2.Any;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;

import cosmos.auth.v1beta1.Auth;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.crypto.secp256k1.Keys;
import cosmos.gov.v1beta1.Gov;
import cosmos.gov.v1beta1.Tx;
import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmos.tx.v1beta1.TxOuterClass;
import cosmos.vesting.v1beta1.Vesting;
import desmos.profiles.v1beta1.ModelsChainLinks;
import desmos.profiles.v1beta1.ModelsProfile;
import desmos.profiles.v1beta1.MsgsChainLinks;
import desmos.profiles.v1beta1.MsgsProfile;
import ibc.core.client.v1.Client;
import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

import static cosmos.tx.signing.v1beta1.Signing.SignMode.SIGN_MODE_DIRECT;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

public class Signer {

    public static String onParseAddress(QueryOuterClass.QueryAccountResponse auth) {
        try {
            if (auth.getAccount().getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                Auth.BaseAccount account = Auth.BaseAccount.parseFrom(auth.getAccount().getValue());
                return account.getAddress();

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                Vesting.PeriodicVestingAccount account = Vesting.PeriodicVestingAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseVestingAccount().getBaseAccount().getAddress();

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
                Vesting.ContinuousVestingAccount account = Vesting.ContinuousVestingAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseVestingAccount().getBaseAccount().getAddress();

                // injective
            } else if (auth.getAccount().getTypeUrl().contains(injective.types.v1beta1.Account.EthAccount.getDescriptor().getFullName())) {
                injective.types.v1beta1.Account.EthAccount account = injective.types.v1beta1.Account.EthAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseAccount().getAddress();

                //desmos
            } else if (auth.getAccount().getTypeUrl().contains(ModelsProfile.Profile.getDescriptor().getFullName())) {
                ModelsProfile.Profile profile = ModelsProfile.Profile.parseFrom(auth.getAccount().getValue());

                if (profile.getAccount().getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                    Auth.BaseAccount account = Auth.BaseAccount.parseFrom(profile.getAccount().getValue());
                    return account.getAddress();

                } else if (profile.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                    Vesting.PeriodicVestingAccount account = Vesting.PeriodicVestingAccount.parseFrom(profile.getAccount().getValue());
                    return account.getBaseVestingAccount().getBaseAccount().getAddress();

                } else if (auth.getAccount().getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
                    Vesting.ContinuousVestingAccount account = Vesting.ContinuousVestingAccount.parseFrom(profile.getAccount().getValue());
                    return account.getBaseVestingAccount().getBaseAccount().getAddress();
                }
            }
        }catch (Exception e) {}
        return "";
    }

    public static long onParseAccountNumber(QueryOuterClass.QueryAccountResponse auth) {
        try {
            if (auth.getAccount().getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                Auth.BaseAccount account = Auth.BaseAccount.parseFrom(auth.getAccount().getValue());
                return account.getAccountNumber();

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                Vesting.PeriodicVestingAccount account = Vesting.PeriodicVestingAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseVestingAccount().getBaseAccount().getAccountNumber();

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
                Vesting.ContinuousVestingAccount account = Vesting.ContinuousVestingAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseVestingAccount().getBaseAccount().getAccountNumber();

                // injective
            } else if (auth.getAccount().getTypeUrl().contains(injective.types.v1beta1.Account.EthAccount.getDescriptor().getFullName())) {
                injective.types.v1beta1.Account.EthAccount account = injective.types.v1beta1.Account.EthAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseAccount().getAccountNumber();

                // desmos
            } else if (auth.getAccount().getTypeUrl().contains(ModelsProfile.Profile.getDescriptor().getFullName())) {
                ModelsProfile.Profile profile = ModelsProfile.Profile.parseFrom(auth.getAccount().getValue());

                if (profile.getAccount().getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                    Auth.BaseAccount account = Auth.BaseAccount.parseFrom(profile.getAccount().getValue());
                    return account.getAccountNumber();

                } else if (profile.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                    Vesting.PeriodicVestingAccount account = Vesting.PeriodicVestingAccount.parseFrom(profile.getAccount().getValue());
                    return account.getBaseVestingAccount().getBaseAccount().getAccountNumber();

                } else if (auth.getAccount().getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
                    Vesting.ContinuousVestingAccount account = Vesting.ContinuousVestingAccount.parseFrom(profile.getAccount().getValue());
                    return account.getBaseVestingAccount().getBaseAccount().getAccountNumber();
                }
            }

        }catch (Exception e) {}
        return 0;
    }

    public static long onParseSequenceNumber(QueryOuterClass.QueryAccountResponse auth) {
        try {
            if (auth.getAccount().getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                Auth.BaseAccount account = Auth.BaseAccount.parseFrom(auth.getAccount().getValue());
                return account.getSequence();

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                Vesting.PeriodicVestingAccount account = Vesting.PeriodicVestingAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseVestingAccount().getBaseAccount().getSequence();

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
                Vesting.ContinuousVestingAccount account = Vesting.ContinuousVestingAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseVestingAccount().getBaseAccount().getSequence();

                // injective
            } else if (auth.getAccount().getTypeUrl().contains(injective.types.v1beta1.Account.EthAccount.getDescriptor().getFullName())) {
                injective.types.v1beta1.Account.EthAccount account = injective.types.v1beta1.Account.EthAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseAccount().getSequence();

                // desmos
            } else if (auth.getAccount().getTypeUrl().contains(ModelsProfile.Profile.getDescriptor().getFullName())) {
                ModelsProfile.Profile profile = ModelsProfile.Profile.parseFrom(auth.getAccount().getValue());

                if (profile.getAccount().getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                    Auth.BaseAccount account = Auth.BaseAccount.parseFrom(profile.getAccount().getValue());
                    return account.getSequence();

                } else if (profile.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                    Vesting.PeriodicVestingAccount account = Vesting.PeriodicVestingAccount.parseFrom(profile.getAccount().getValue());
                    return account.getBaseVestingAccount().getBaseAccount().getSequence();

                } else if (auth.getAccount().getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
                    Vesting.ContinuousVestingAccount account = Vesting.ContinuousVestingAccount.parseFrom(profile.getAccount().getValue());
                    return account.getBaseVestingAccount().getBaseAccount().getSequence();
                }
            }
        }catch (Exception e) {}
        return 0;
    }

    //gRpc Singer
    public static ServiceOuterClass.BroadcastTxRequest getGrpcSendReq(QueryOuterClass.QueryAccountResponse auth, String toAddress, ArrayList<Coin> amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin toSendCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.get(0).amount).setDenom(amounts.get(0).denom).build();
        cosmos.bank.v1beta1.Tx.MsgSend msgSend = cosmos.bank.v1beta1.Tx.MsgSend.newBuilder().addAmount(toSendCoin).setFromAddress(onParseAddress(auth)).setToAddress(toAddress).build();
        Any msgSendAny = Any.newBuilder().setTypeUrl("/cosmos.bank.v1beta1.MsgSend").setValue(msgSend.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSendAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSendSimulateReq(QueryOuterClass.QueryAccountResponse auth, String toAddress, ArrayList<Coin> amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin toSendCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.get(0).amount).setDenom(amounts.get(0).denom).build();
        cosmos.bank.v1beta1.Tx.MsgSend msgSend = cosmos.bank.v1beta1.Tx.MsgSend.newBuilder().setFromAddress(onParseAddress(auth)).setToAddress(toAddress).addAmount(toSendCoin).build();
        Any msgSendAny = Any.newBuilder().setTypeUrl("/cosmos.bank.v1beta1.MsgSend").setValue(msgSend.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSendAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcDelegateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        Any msgDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcDelegateSimulateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        Any msgDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcUnDelegateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgUndelegate msgUnDelegate = cosmos.staking.v1beta1.Tx.MsgUndelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        Any msgUnDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgUndelegate").setValue(msgUnDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgUnDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcUnDelegateSimulateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgUndelegate msgUnDelegate = cosmos.staking.v1beta1.Tx.MsgUndelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        Any msgUnDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgUndelegate").setValue(msgUnDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgUnDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcClaimRewardsReq(QueryOuterClass.QueryAccountResponse auth, ArrayList<String> toValAddresses, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        for (String valAddr: toValAddresses) {
            cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddr).build();
            Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
            msgsAny.add(msgClaimRewardAny);
        }

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcClaimRewardsSimulateReq(QueryOuterClass.QueryAccountResponse auth, ArrayList<String> toValAddresses, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        for (String valAddr: toValAddresses) {
            cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddr).build();
            Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
            msgsAny.add(msgClaimRewardAny);
        }

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcReDelegateReq(QueryOuterClass.QueryAccountResponse auth, String fromValAddress, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin toReDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgBeginRedelegate msgReDelegate = cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorSrcAddress(fromValAddress).setValidatorDstAddress(toValAddress).setAmount(toReDelegateCoin).build();
        Any msgReDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgBeginRedelegate").setValue(msgReDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgReDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcReDelegateSimulateReq(QueryOuterClass.QueryAccountResponse auth, String fromValAddress, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin toReDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgBeginRedelegate msgReDelegate = cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorSrcAddress(fromValAddress).setValidatorDstAddress(toValAddress).setAmount(toReDelegateCoin).build();
        Any msgReDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgBeginRedelegate").setValue(msgReDelegate.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgReDelegateAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcReInvestReq(QueryOuterClass.QueryAccountResponse auth, String valAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddress).build();
        Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
        msgsAny.add(msgClaimRewardAny);

        CoinOuterClass.Coin toReinvsetCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddress).setAmount(toReinvsetCoin).build();
        Any msgDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build();
        msgsAny.add(msgDelegateAny);

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcReInvestSimulateReq(QueryOuterClass.QueryAccountResponse auth, String valAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddress).build();
        Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
        msgsAny.add(msgClaimRewardAny);

        CoinOuterClass.Coin toReinvsetCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddress).setAmount(toReinvsetCoin).build();
        Any msgDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build();
        msgsAny.add(msgDelegateAny);

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcRewardAddressChangeReq(QueryOuterClass.QueryAccountResponse auth, String newAddress, Fee fee, String memo, ECKey pKey, String chainId) {
        cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress msgSetWithdrawAddress = cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress.newBuilder().setDelegatorAddress(onParseAddress(auth)).setWithdrawAddress(newAddress).build();
        Any msgSetWithdrawAddressAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgSetWithdrawAddress").setValue(msgSetWithdrawAddress.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSetWithdrawAddressAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRewardAddressChangeSimulateReq(QueryOuterClass.QueryAccountResponse auth, String newAddress, Fee fee, String memo, ECKey pKey, String chainId) {
        cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress msgSetWithdrawAddress = cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress.newBuilder().setDelegatorAddress(onParseAddress(auth)).setWithdrawAddress(newAddress).build();
        Any msgSetWithdrawAddressAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgSetWithdrawAddress").setValue(msgSetWithdrawAddress.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSetWithdrawAddressAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcVoteReq(QueryOuterClass.QueryAccountResponse auth, String proposalId, String option, Fee fee, String memo, ECKey pKey, String chainId) {
        Gov.VoteOption msgOption = null;
        if (option.equals("Yes")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_YES;
        } else if (option.equals("No")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_NO;
        } else if (option.equals("NoWithVeto")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_NO_WITH_VETO;
        } else if (option.equals("Abstain")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_ABSTAIN;
        }
        Tx.MsgVote msgVote = Tx.MsgVote.newBuilder().setProposalId(Long.parseLong(proposalId)).setVoter(onParseAddress(auth)).setOption(msgOption).build();
        Any msgSendAny = Any.newBuilder().setTypeUrl("/cosmos.gov.v1beta1.MsgVote").setValue(msgVote.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSendAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcVoteSimulateReq(QueryOuterClass.QueryAccountResponse auth, String proposalId, String option, Fee fee, String memo, ECKey pKey, String chainId) {
        Gov.VoteOption msgOption = null;
        if (option.equals("Yes")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_YES;
        } else if (option.equals("No")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_NO;
        } else if (option.equals("NoWithVeto")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_NO_WITH_VETO;
        } else if (option.equals("Abstain")) {
            msgOption = Gov.VoteOption.VOTE_OPTION_ABSTAIN;
        }
        Tx.MsgVote msgVote = Tx.MsgVote.newBuilder().setProposalId(Long.parseLong(proposalId)).setVoter(onParseAddress(auth)).setOption(msgOption).build();
        Any msgSendAny = Any.newBuilder().setTypeUrl("/cosmos.gov.v1beta1.MsgVote").setValue(msgVote.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSendAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcRegisterDomainReq(QueryOuterClass.QueryAccountResponse auth, String domain, String admin, String type, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain msgRegisterDomain = starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain.newBuilder().setName(domain).setAdmin(admin).setDomainType(type).build();
        Any msgRegisterDomainAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRegisterDomain").setValue(msgRegisterDomain.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgRegisterDomainAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRegisterDomainSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String admin, String type, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain msgRegisterDomain = starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain.newBuilder().setName(domain).setAdmin(admin).setDomainType(type).build();
        Any msgRegisterDomainAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRegisterDomain").setValue(msgRegisterDomain.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgRegisterDomainAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcRegisterAccountReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, String registerer, ArrayList<Types.Resource> resources, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount msgRegisterAccount = starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount.newBuilder().setDomain(domain).setName(name).setOwner(owner).setRegisterer(registerer).addAllResources(resources).build();
        Any msgRegisterAccountAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRegisterAccount").setValue(msgRegisterAccount.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgRegisterAccountAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRegisterAccountSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, String registerer, ArrayList<Types.Resource> resources, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount msgRegisterAccount = starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount.newBuilder().setDomain(domain).setName(name).setOwner(owner).setRegisterer(registerer).addAllResources(resources).build();
        Any msgRegisterAccountAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRegisterAccount").setValue(msgRegisterAccount.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgRegisterAccountAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcDeleteDomainReq(QueryOuterClass.QueryAccountResponse auth, String domain, String owner, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain msgDeleteDomain = starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain.newBuilder().setDomain(domain).setOwner(owner).build();
        Any msgDeleteDomainAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgDeleteDomain").setValue(msgDeleteDomain.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDeleteDomainAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcDeleteDomainSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String owner, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain msgDeleteDomain = starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain.newBuilder().setDomain(domain).setOwner(owner).build();
        Any msgDeleteDomainAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgDeleteDomain").setValue(msgDeleteDomain.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDeleteDomainAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();

    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcDeleteAccountReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount msgDeleteAccount = starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount.newBuilder().setDomain(domain).setName(name).setOwner(owner).build();
        Any msgDeleteAccountAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgDeleteAccount").setValue(msgDeleteAccount.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDeleteAccountAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcDeleteAccountSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount msgDeleteAccount = starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount.newBuilder().setDomain(domain).setName(name).setOwner(owner).build();
        Any msgDeleteAccountAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgDeleteAccount").setValue(msgDeleteAccount.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDeleteAccountAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcRenewDomainReq(QueryOuterClass.QueryAccountResponse auth, String domain, String signer, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgRenewDomain msgRenewDomain = starnamed.x.starname.v1beta1.Tx.MsgRenewDomain.newBuilder().setDomain(domain).setSigner(signer).build();
        Any msgRenewDomainAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRenewDomain").setValue(msgRenewDomain.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgRenewDomainAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRenewDomainSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String signer, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgRenewDomain msgRenewDomain = starnamed.x.starname.v1beta1.Tx.MsgRenewDomain.newBuilder().setDomain(domain).setSigner(signer).build();
        Any msgRenewDomainAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRenewDomain").setValue(msgRenewDomain.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgRenewDomainAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcRenewAccountReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String signer, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgRenewAccount msgRenewAccount = starnamed.x.starname.v1beta1.Tx.MsgRenewAccount.newBuilder().setDomain(domain).setName(name).setSigner(signer).build();
        Any msgRenewAccountAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRenewAccount").setValue(msgRenewAccount.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgRenewAccountAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRenewAccountSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String signer, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgRenewAccount msgRenewAccount = starnamed.x.starname.v1beta1.Tx.MsgRenewAccount.newBuilder().setDomain(domain).setName(name).setSigner(signer).build();
        Any msgRenewAccountAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRenewAccount").setValue(msgRenewAccount.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgRenewAccountAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }


    public static ServiceOuterClass.BroadcastTxRequest getGrpcReplaceResourceReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, ArrayList<Types.Resource> resources, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources msgReplaceResource= starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources.newBuilder().setDomain(domain).setName(name).setOwner(owner).addAllNewResources(resources).build();
        Any msgReplaceResourceAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgReplaceAccountResources").setValue(msgReplaceResource.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgReplaceResourceAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcReplaceResourceSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, ArrayList<Types.Resource> resources, Fee fee, String memo, ECKey pKey, String chainId) {
        starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources msgReplaceResource= starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources.newBuilder().setDomain(domain).setName(name).setOwner(owner).addAllNewResources(resources).build();
        Any msgReplaceResourceAny = Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgReplaceAccountResources").setValue(msgReplaceResource.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgReplaceResourceAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSwapInReq(QueryOuterClass.QueryAccountResponse auth, osmosis.gamm.v1beta1.Tx.SwapAmountInRoute swapRoutes, String inputDenom, String inputAmount, String outputAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin inputCoin = CoinOuterClass.Coin.newBuilder().setDenom(inputDenom).setAmount(inputAmount).build();
        osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn msgSwapExactAmountIn = osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn.newBuilder().setSender(onParseAddress(auth)).addRoutes(swapRoutes).setTokenIn(inputCoin).setTokenOutMinAmount(outputAmount).build();
        Any msgSwapAny = Any.newBuilder().setTypeUrl("/osmosis.gamm.v1beta1.MsgSwapExactAmountIn").setValue(msgSwapExactAmountIn.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSwapAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSwapInSimulateReq(QueryOuterClass.QueryAccountResponse auth, osmosis.gamm.v1beta1.Tx.SwapAmountInRoute swapRoute, String inputDenom, String inputAmount, String outputAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin inputCoin = CoinOuterClass.Coin.newBuilder().setDenom(inputDenom).setAmount(inputAmount).build();
        osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn msgSwapExactAmountIn = osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn.newBuilder().setSender(onParseAddress(auth)).addRoutes(swapRoute).setTokenIn(inputCoin).setTokenOutMinAmount(outputAmount).build();
        Any msgSwapAny = Any.newBuilder().setTypeUrl("/osmosis.gamm.v1beta1.MsgSwapExactAmountIn").setValue(msgSwapExactAmountIn.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSwapAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcJoinPoolReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin, String shareAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin inputCoin0 = CoinOuterClass.Coin.newBuilder().setDenom(deposit0Coin.denom).setAmount(deposit0Coin.amount).build();
        CoinOuterClass.Coin inputCoin1 = CoinOuterClass.Coin.newBuilder().setDenom(deposit1Coin.denom).setAmount(deposit1Coin.amount).build();
        ArrayList<CoinOuterClass.Coin> tokenMax = new ArrayList<>();
        tokenMax.add(inputCoin0);
        tokenMax.add(inputCoin1);
        osmosis.gamm.v1beta1.Tx.MsgJoinPool msgJoinPool = osmosis.gamm.v1beta1.Tx.MsgJoinPool.newBuilder().setSender(onParseAddress(auth)).setPoolId(poolId).addAllTokenInMaxs(tokenMax).setShareOutAmount(shareAmount).build();
        Any msgJoinPoolAny = Any.newBuilder().setTypeUrl("/osmosis.gamm.v1beta1.MsgJoinPool").setValue(msgJoinPool.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgJoinPoolAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcJoinPoolSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin, String shareAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin inputCoin0 = CoinOuterClass.Coin.newBuilder().setDenom(deposit0Coin.denom).setAmount(deposit0Coin.amount).build();
        CoinOuterClass.Coin inputCoin1 = CoinOuterClass.Coin.newBuilder().setDenom(deposit1Coin.denom).setAmount(deposit1Coin.amount).build();
        ArrayList<CoinOuterClass.Coin> tokenMax = new ArrayList<>();
        tokenMax.add(inputCoin0);
        tokenMax.add(inputCoin1);
        osmosis.gamm.v1beta1.Tx.MsgJoinPool msgJoinPool = osmosis.gamm.v1beta1.Tx.MsgJoinPool.newBuilder().setSender(onParseAddress(auth)).setPoolId(poolId).addAllTokenInMaxs(tokenMax).setShareOutAmount(shareAmount).build();
        Any msgJoinPoolAny = Any.newBuilder().setTypeUrl("/osmosis.gamm.v1beta1.MsgJoinPool").setValue(msgJoinPool.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgJoinPoolAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcExitPoolReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin Withdraw0Coin, Coin Withdraw1Coin, String shareAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin OutputCoin0 = CoinOuterClass.Coin.newBuilder().setDenom(Withdraw0Coin.denom).setAmount(Withdraw0Coin.amount).build();
        CoinOuterClass.Coin OutputCoin1 = CoinOuterClass.Coin.newBuilder().setDenom(Withdraw1Coin.denom).setAmount(Withdraw1Coin.amount).build();
        ArrayList<CoinOuterClass.Coin> tokenMin = new ArrayList<>();
        tokenMin.add(OutputCoin0);
        tokenMin.add(OutputCoin1);
        osmosis.gamm.v1beta1.Tx.MsgExitPool msgExitPool = osmosis.gamm.v1beta1.Tx.MsgExitPool.newBuilder().setSender(onParseAddress(auth)).setPoolId(poolId).addAllTokenOutMins(tokenMin).setShareInAmount(shareAmount).build();
        Any msgExitPoolAny = Any.newBuilder().setTypeUrl("/osmosis.gamm.v1beta1.MsgExitPool").setValue(msgExitPool.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgExitPoolAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcExitPoolSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin Withdraw0Coin, Coin Withdraw1Coin, String shareAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin OutputCoin0 = CoinOuterClass.Coin.newBuilder().setDenom(Withdraw0Coin.denom).setAmount(Withdraw0Coin.amount).build();
        CoinOuterClass.Coin OutputCoin1 = CoinOuterClass.Coin.newBuilder().setDenom(Withdraw1Coin.denom).setAmount(Withdraw1Coin.amount).build();
        ArrayList<CoinOuterClass.Coin> tokenMin = new ArrayList<>();
        tokenMin.add(OutputCoin0);
        tokenMin.add(OutputCoin1);
        osmosis.gamm.v1beta1.Tx.MsgExitPool msgExitPool = osmosis.gamm.v1beta1.Tx.MsgExitPool.newBuilder().setSender(onParseAddress(auth)).setPoolId(poolId).addAllTokenOutMins(tokenMin).setShareInAmount(shareAmount).build();
        Any msgExitPoolAny = Any.newBuilder().setTypeUrl("/osmosis.gamm.v1beta1.MsgExitPool").setValue(msgExitPool.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgExitPoolAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcStartLockReq(QueryOuterClass.QueryAccountResponse auth, long duration, Coin lpCoin, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin lockupCoin = CoinOuterClass.Coin.newBuilder().setDenom(lpCoin.denom).setAmount(lpCoin.amount).build();
        ArrayList<CoinOuterClass.Coin> lockupTokens = new ArrayList<>();
        lockupTokens.add(lockupCoin);
        Duration OsmoDuration = Duration.newBuilder().setSeconds(duration).setNanos(0).build();
        osmosis.lockup.Tx.MsgLockTokens msgLockTokens = osmosis.lockup.Tx.MsgLockTokens.newBuilder().setOwner(onParseAddress(auth)).setDuration(OsmoDuration).addAllCoins(lockupTokens).build();
        Any msgStartLockAny = Any.newBuilder().setTypeUrl("/osmosis.lockup.MsgLockTokens").setValue(msgLockTokens.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgStartLockAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcStartLockSimulateReq(QueryOuterClass.QueryAccountResponse auth, long duration, Coin lpCoin, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin lockupCoin = CoinOuterClass.Coin.newBuilder().setDenom(lpCoin.denom).setAmount(lpCoin.amount).build();
        ArrayList<CoinOuterClass.Coin> lockupTokens = new ArrayList<>();
        lockupTokens.add(lockupCoin);
        Duration OsmoDuration = Duration.newBuilder().setSeconds(duration).setNanos(0).build();
        osmosis.lockup.Tx.MsgLockTokens msgLockTokens = osmosis.lockup.Tx.MsgLockTokens.newBuilder().setOwner(onParseAddress(auth)).setDuration(OsmoDuration).addAllCoins(lockupTokens).build();
        Any msgStartLockAny = Any.newBuilder().setTypeUrl("/osmosis.lockup.MsgLockTokens").setValue(msgLockTokens.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgStartLockAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcBeginUnbondingReq(QueryOuterClass.QueryAccountResponse auth, ArrayList<Long> ids, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        for (Long id: ids) {
            osmosis.lockup.Tx.MsgBeginUnlocking msgBeginUnlocking = osmosis.lockup.Tx.MsgBeginUnlocking.newBuilder().setOwner(onParseAddress(auth)).setID(id).build();
            Any msgBeginUnbondingAny = Any.newBuilder().setTypeUrl("/osmosis.lockup.MsgBeginUnlocking").setValue(msgBeginUnlocking.toByteString()).build();
            msgsAny.add(msgBeginUnbondingAny);
        }
        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcBeginUnbondingSimulateReq(QueryOuterClass.QueryAccountResponse auth, ArrayList<Long> ids, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        for (Long id: ids) {
            osmosis.lockup.Tx.MsgBeginUnlocking msgBeginUnlocking = osmosis.lockup.Tx.MsgBeginUnlocking.newBuilder().setOwner(onParseAddress(auth)).setID(id).build();
            Any msgBeginUnbondingAny = Any.newBuilder().setTypeUrl("/osmosis.lockup.MsgBeginUnlocking").setValue(msgBeginUnlocking.toByteString()).build();
            msgsAny.add(msgBeginUnbondingAny);
        }
        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcGravitySwapReq(QueryOuterClass.QueryAccountResponse auth, long poolId, int typeId, Coin offerCoin, String demandCoinDenom, Coin feeCoin, String orderPrice, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin inputCoin = CoinOuterClass.Coin.newBuilder().setDenom(offerCoin.denom).setAmount(offerCoin.amount).build();
        CoinOuterClass.Coin coinFee = CoinOuterClass.Coin.newBuilder().setDenom(feeCoin.denom).setAmount(feeCoin.amount).build();
        tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch msgSwapWithinBatch = tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch.newBuilder().setSwapRequesterAddress(onParseAddress(auth)).setPoolId(poolId).setSwapTypeId(typeId).setOfferCoin(inputCoin).setDemandCoinDenom(demandCoinDenom).setOfferCoinFee(coinFee).setOrderPrice(orderPrice).build();
        Any msgSwapAny = Any.newBuilder().setTypeUrl("/tendermint.liquidity.v1beta1.MsgSwapWithinBatch").setValue(msgSwapWithinBatch.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSwapAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcGravitySwapSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, int typeId, Coin offerCoin, String demandCoinDenom, Coin feeCoin, String orderPrice, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin inputCoin = CoinOuterClass.Coin.newBuilder().setDenom(offerCoin.denom).setAmount(offerCoin.amount).build();
        CoinOuterClass.Coin coinFee = CoinOuterClass.Coin.newBuilder().setDenom(feeCoin.denom).setAmount(feeCoin.amount).build();
        tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch msgSwapWithinBatch = tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch.newBuilder().setSwapRequesterAddress(onParseAddress(auth)).setPoolId(poolId).setSwapTypeId(typeId).setOfferCoin(inputCoin).setDemandCoinDenom(demandCoinDenom).setOfferCoinFee(coinFee).setOrderPrice(orderPrice).build();
        Any msgSwapAny = Any.newBuilder().setTypeUrl("/tendermint.liquidity.v1beta1.MsgSwapWithinBatch").setValue(msgSwapWithinBatch.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSwapAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcGDexDepositReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin inputCoin0 = CoinOuterClass.Coin.newBuilder().setDenom(deposit0Coin.denom).setAmount(deposit0Coin.amount).build();
        CoinOuterClass.Coin inputCoin1 = CoinOuterClass.Coin.newBuilder().setDenom(deposit1Coin.denom).setAmount(deposit1Coin.amount).build();
        ArrayList<CoinOuterClass.Coin> tokenMax = new ArrayList<>();
        tokenMax.add(inputCoin0);
        tokenMax.add(inputCoin1);
        tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch msgDepositWithinBatch = tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch.newBuilder().setDepositorAddress(onParseAddress(auth)).setPoolId(poolId).addAllDepositCoins(tokenMax).build();
        Any msgGDexDepositAny = Any.newBuilder().setTypeUrl("/tendermint.liquidity.v1beta1.MsgDepositWithinBatch").setValue(msgDepositWithinBatch.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgGDexDepositAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcGDexDepositSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin inputCoin0 = CoinOuterClass.Coin.newBuilder().setDenom(deposit0Coin.denom).setAmount(deposit0Coin.amount).build();
        CoinOuterClass.Coin inputCoin1 = CoinOuterClass.Coin.newBuilder().setDenom(deposit1Coin.denom).setAmount(deposit1Coin.amount).build();
        ArrayList<CoinOuterClass.Coin> tokenMax = new ArrayList<>();
        tokenMax.add(inputCoin0);
        tokenMax.add(inputCoin1);
        tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch msgDepositWithinBatch = tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch.newBuilder().setDepositorAddress(onParseAddress(auth)).setPoolId(poolId).addAllDepositCoins(tokenMax).build();
        Any msgGDexDepositAny = Any.newBuilder().setTypeUrl("/tendermint.liquidity.v1beta1.MsgDepositWithinBatch").setValue(msgDepositWithinBatch.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgGDexDepositAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcGDexWithdrawReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin WithdrawCoin, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin OutputCoin = CoinOuterClass.Coin.newBuilder().setDenom(WithdrawCoin.denom).setAmount(WithdrawCoin.amount).build();
        tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch msgWithdrawWithinBatch = tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch.newBuilder().setWithdrawerAddress(onParseAddress(auth)).setPoolId(poolId).setPoolCoin(OutputCoin).build();
        Any msgGDexWithdrawAny = Any.newBuilder().setTypeUrl("/tendermint.liquidity.v1beta1.MsgWithdrawWithinBatch").setValue(msgWithdrawWithinBatch.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgGDexWithdrawAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcGDexWithdrawSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin WithdrawCoin, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin OutputCoin = CoinOuterClass.Coin.newBuilder().setDenom(WithdrawCoin.denom).setAmount(WithdrawCoin.amount).build();
        tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch msgWithdrawWithinBatch = tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch.newBuilder().setWithdrawerAddress(onParseAddress(auth)).setPoolId(poolId).setPoolCoin(OutputCoin).build();
        Any msgGDexWithdrawAny = Any.newBuilder().setTypeUrl("/tendermint.liquidity.v1beta1.MsgWithdrawWithinBatch").setValue(msgWithdrawWithinBatch.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgGDexWithdrawAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcIbcTransferReq(QueryOuterClass.QueryAccountResponse auth, String sender, String receiver, String ibcSendDenom, String ibcSendAmount, String portId, String channelId, Client.Height lastHeight, Fee fee, String memo, ECKey pKey, String chainId) {
        Client.Height height = Client.Height.newBuilder().setRevisionNumber(lastHeight.getRevisionNumber()).setRevisionHeight(lastHeight.getRevisionHeight() + 500).build();
        CoinOuterClass.Coin token = CoinOuterClass.Coin.newBuilder().setAmount(ibcSendAmount).setDenom(ibcSendDenom).build();
        ibc.applications.transfer.v1.Tx.MsgTransfer msgIbcTransfer = ibc.applications.transfer.v1.Tx.MsgTransfer.newBuilder().setSender(sender).setReceiver(receiver).setSourcePort(portId).setSourceChannel(channelId).setToken(token).setTimeoutHeight(height).setTimeoutTimestamp(0).build();
        Any msgIbcTransferAny = Any.newBuilder().setTypeUrl("/ibc.applications.transfer.v1.MsgTransfer").setValue(msgIbcTransfer.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgIbcTransferAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcIbcTransferSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, String receiver, String ibcSendDenom, String ibcSendAmount, String portId, String channelId, Client.Height lastHeight, Fee fee, String memo, ECKey pKey, String chainId) {
        Client.Height height = Client.Height.newBuilder().setRevisionNumber(lastHeight.getRevisionNumber()).setRevisionHeight(lastHeight.getRevisionHeight() + 500).build();
        CoinOuterClass.Coin token = CoinOuterClass.Coin.newBuilder().setAmount(ibcSendAmount).setDenom(ibcSendDenom).build();
        ibc.applications.transfer.v1.Tx.MsgTransfer msgIbcTransfer = ibc.applications.transfer.v1.Tx.MsgTransfer.newBuilder().setSender(sender).setReceiver(receiver).setSourcePort(portId).setSourceChannel(channelId).setToken(token).setTimeoutHeight(height).setTimeoutTimestamp(0).build();
        Any msgIbcTransferAny = Any.newBuilder().setTypeUrl("/ibc.applications.transfer.v1.MsgTransfer").setValue(msgIbcTransfer.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgIbcTransferAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSifIncentiveReq(QueryOuterClass.QueryAccountResponse auth, String userClaimAddress, Fee fee, String memo, ECKey pKey, String chainId) {
        sifnode.dispensation.v1.Tx.MsgCreateUserClaim msgCreateUserClaim = sifnode.dispensation.v1.Tx.MsgCreateUserClaim.newBuilder().setUserClaimAddress(userClaimAddress).setUserClaimType(sifnode.dispensation.v1.Types.DistributionType.DISTRIBUTION_TYPE_LIQUIDITY_MINING).build();
        Any msgCreateUserClaimAny = Any.newBuilder().setTypeUrl("/sifnode.dispensation.v1.MsgCreateUserClaim").setValue(msgCreateUserClaim.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgCreateUserClaimAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSifIncentiveSimulateReq(QueryOuterClass.QueryAccountResponse auth, String userClaimAddress, Fee fee, String memo, ECKey pKey, String chainId) {
        sifnode.dispensation.v1.Tx.MsgCreateUserClaim msgCreateUserClaim = sifnode.dispensation.v1.Tx.MsgCreateUserClaim.newBuilder().setUserClaimAddress(userClaimAddress).setUserClaimType(sifnode.dispensation.v1.Types.DistributionType.DISTRIBUTION_TYPE_LIQUIDITY_MINING).build();
        Any msgCreateUserClaimAny = Any.newBuilder().setTypeUrl("/sifnode.dispensation.v1.MsgCreateUserClaim").setValue(msgCreateUserClaim.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgCreateUserClaimAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSifSwapReq(QueryOuterClass.QueryAccountResponse auth, String signer, String inputDenom, String inputAmount, String outputDenom, String outputAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        sifnode.clp.v1.Types.Asset inputAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(inputDenom).build();
        sifnode.clp.v1.Types.Asset outpuAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(outputDenom).build();
        sifnode.clp.v1.Tx.MsgSwap msgSwap = sifnode.clp.v1.Tx.MsgSwap.newBuilder().setSigner(signer).setSentAsset(inputAsset).setReceivedAsset(outpuAsset).setSentAmount(inputAmount).setMinReceivingAmount(outputAmount).build();
        Any msgSwapAny = Any.newBuilder().setTypeUrl("/sifnode.clp.v1.MsgSwap").setValue(msgSwap.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSwapAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSifSwapSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String inputDenom, String inputAmount, String outputDenom, String outputAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        sifnode.clp.v1.Types.Asset inputAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(inputDenom).build();
        sifnode.clp.v1.Types.Asset outpuAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(outputDenom).build();
        sifnode.clp.v1.Tx.MsgSwap msgSwap = sifnode.clp.v1.Tx.MsgSwap.newBuilder().setSigner(signer).setSentAsset(inputAsset).setReceivedAsset(outpuAsset).setSentAmount(inputAmount).setMinReceivingAmount(outputAmount).build();
        Any msgSwapAny = Any.newBuilder().setTypeUrl("/sifnode.clp.v1.MsgSwap").setValue(msgSwap.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSwapAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSifDepositReq(QueryOuterClass.QueryAccountResponse auth, String signer, String externalDenom, String nativeAmount, String externalAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        sifnode.clp.v1.Types.Asset externalAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(externalDenom).build();
        sifnode.clp.v1.Tx.MsgAddLiquidity msgAddLiquidity = sifnode.clp.v1.Tx.MsgAddLiquidity.newBuilder().setSigner(signer).setExternalAsset(externalAsset).setNativeAssetAmount(nativeAmount).setExternalAssetAmount(externalAmount).build();
        Any msgDepositAny = Any.newBuilder().setTypeUrl("/sifnode.clp.v1.MsgAddLiquidity").setValue(msgAddLiquidity.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDepositAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSifDepositSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String externalDenom, String nativeAmount, String externalAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        sifnode.clp.v1.Types.Asset externalAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(externalDenom).build();
        sifnode.clp.v1.Tx.MsgAddLiquidity msgAddLiquidity = sifnode.clp.v1.Tx.MsgAddLiquidity.newBuilder().setSigner(signer).setExternalAsset(externalAsset).setNativeAssetAmount(nativeAmount).setExternalAssetAmount(externalAmount).build();
        Any msgDepositAny = Any.newBuilder().setTypeUrl("/sifnode.clp.v1.MsgAddLiquidity").setValue(msgAddLiquidity.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgDepositAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSifWithdrawReq(QueryOuterClass.QueryAccountResponse auth, String signer, String externalDenom, String wBasisPoints, Fee fee, String memo, ECKey pKey, String chainId) {
        sifnode.clp.v1.Types.Asset externalAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(externalDenom).build();
        sifnode.clp.v1.Tx.MsgRemoveLiquidity msgRemoveLiquidity = sifnode.clp.v1.Tx.MsgRemoveLiquidity.newBuilder().setSigner(signer).setExternalAsset(externalAsset).setWBasisPoints(wBasisPoints).setAsymmetry("0").build();
        Any msgWithdrawAny = Any.newBuilder().setTypeUrl("/sifnode.clp.v1.MsgRemoveLiquidity").setValue(msgRemoveLiquidity.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgWithdrawAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSifWithdrawSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String externalDenom, String wBasisPoints, Fee fee, String memo, ECKey pKey, String chainId) {
        sifnode.clp.v1.Types.Asset externalAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(externalDenom).build();
        sifnode.clp.v1.Tx.MsgRemoveLiquidity msgRemoveLiquidity = sifnode.clp.v1.Tx.MsgRemoveLiquidity.newBuilder().setSigner(signer).setExternalAsset(externalAsset).setWBasisPoints(wBasisPoints).setAsymmetry("0").build();
        Any msgWithdrawAny = Any.newBuilder().setTypeUrl("/sifnode.clp.v1.MsgRemoveLiquidity").setValue(msgRemoveLiquidity.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgWithdrawAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcCreateNftIrisReq(QueryOuterClass.QueryAccountResponse auth, String signer, String denomId, String denomName, String id, String name, String uri, String data, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        irismod.nft.Tx.MsgIssueDenom msgIssueDenom = irismod.nft.Tx.MsgIssueDenom.newBuilder().setId(denomId).setName(denomName).setSchema("").setSender(signer).setMintRestricted(false).setUpdateRestricted(false).build();
        Any msgIssueDenomAny = Any.newBuilder().setTypeUrl("/irismod.nft.MsgIssueDenom").setValue(msgIssueDenom.toByteString()).build();
        msgsAny.add(msgIssueDenomAny);

        irismod.nft.Tx.MsgMintNFT msgMintNFT = irismod.nft.Tx.MsgMintNFT.newBuilder().setSender(signer).setRecipient(signer).setId(id).setDenomId(denomId).setName(name).setUri(uri).setData(data).build();
        Any msgMintNftAny = Any.newBuilder().setTypeUrl("/irismod.nft.MsgMintNFT").setValue(msgMintNFT.toByteString()).build();
        msgsAny.add(msgMintNftAny);

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcCreateNftIrisSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String denomId, String denomName, String id, String name, String uri, String data, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        irismod.nft.Tx.MsgIssueDenom msgIssueDenom = irismod.nft.Tx.MsgIssueDenom.newBuilder().setId(denomId).setName(denomName).setSchema("").setSender(signer).setMintRestricted(false).setUpdateRestricted(false).build();
        Any msgIssueDenomAny = Any.newBuilder().setTypeUrl("/irismod.nft.MsgIssueDenom").setValue(msgIssueDenom.toByteString()).build();
        msgsAny.add(msgIssueDenomAny);

        irismod.nft.Tx.MsgMintNFT msgMintNFT = irismod.nft.Tx.MsgMintNFT.newBuilder().setSender(signer).setRecipient(signer).setId(id).setDenomId(denomId).setName(name).setUri(uri).setData(data).build();
        Any msgMintNftAny = Any.newBuilder().setTypeUrl("/irismod.nft.MsgMintNFT").setValue(msgMintNFT.toByteString()).build();
        msgsAny.add(msgMintNftAny);

        TxOuterClass.TxBody txBody = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcCreateNftCroReq(QueryOuterClass.QueryAccountResponse auth, String signer, String denomId, String denomName, String id, String name, String uri, String data, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        chainmain.nft.v1.Tx.MsgIssueDenom msgIssueDenom = chainmain.nft.v1.Tx.MsgIssueDenom.newBuilder().setId(denomId).setName(denomName).setSchema("").setSender(signer).build();
        Any msgIssueDenomAny = Any.newBuilder().setTypeUrl("/chainmain.nft.v1.MsgIssueDenom").setValue(msgIssueDenom.toByteString()).build();
        msgsAny.add(msgIssueDenomAny);

        chainmain.nft.v1.Tx.MsgMintNFT msgMintNFT = chainmain.nft.v1.Tx.MsgMintNFT.newBuilder().setSender(signer).setRecipient(signer).setId(id).setDenomId(denomId).setName(name).setUri(uri).setData(data).build();
        Any msgMintNftAny = Any.newBuilder().setTypeUrl("/chainmain.nft.v1.MsgMintNFT").setValue(msgMintNFT.toByteString()).build();
        msgsAny.add(msgMintNftAny);

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcCreateNftCroSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String denomId, String denomName, String id, String name, String uri, String data, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        chainmain.nft.v1.Tx.MsgIssueDenom msgIssueDenom = chainmain.nft.v1.Tx.MsgIssueDenom.newBuilder().setId(denomId).setName(denomName).setSchema("").setSender(signer).build();
        Any msgIssueDenomAny = Any.newBuilder().setTypeUrl("/chainmain.nft.v1.MsgIssueDenom").setValue(msgIssueDenom.toByteString()).build();
        msgsAny.add(msgIssueDenomAny);

        chainmain.nft.v1.Tx.MsgMintNFT msgMintNFT = chainmain.nft.v1.Tx.MsgMintNFT.newBuilder().setSender(signer).setRecipient(signer).setId(id).setDenomId(denomId).setName(name).setUri(uri).setData(data).build();
        Any msgMintNftAny = Any.newBuilder().setTypeUrl("/chainmain.nft.v1.MsgMintNFT").setValue(msgMintNFT.toByteString()).build();
        msgsAny.add(msgMintNftAny);

        TxOuterClass.TxBody txBody = getGrpcTxBodys(msgsAny, memo);
        TxOuterClass.SignerInfo signerInfo = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSendNftIrisReq(QueryOuterClass.QueryAccountResponse auth, String sender, String recipient, String denomId, String id, irismod.nft.QueryOuterClass.QueryNFTResponse irisResponse, Fee fee, String memo, ECKey pKey, String chainId) {
        irismod.nft.Tx.MsgTransferNFT msgTransferNFT = irismod.nft.Tx.MsgTransferNFT.newBuilder().setId(id).setDenomId(denomId).setSender(sender).setRecipient(recipient).setName(irisResponse.getNft().getName()).setUri(irisResponse.getNft().getUri()).setData(irisResponse.getNft().getData()).build();
        Any msgTransferNftAny = Any.newBuilder().setTypeUrl("/irismod.nft.MsgTransferNFT").setValue(msgTransferNFT.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgTransferNftAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSendNftIrisSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, String recipient, String denomId, String id, irismod.nft.QueryOuterClass.QueryNFTResponse irisResponse, Fee fee, String memo, ECKey pKey, String chainId) {
        irismod.nft.Tx.MsgTransferNFT msgTransferNFT = irismod.nft.Tx.MsgTransferNFT.newBuilder().setId(id).setDenomId(denomId).setSender(sender).setRecipient(recipient).setName(irisResponse.getNft().getName()).setUri(irisResponse.getNft().getUri()).setData(irisResponse.getNft().getData()).build();
        Any msgTransferNftAny = Any.newBuilder().setTypeUrl("/irismod.nft.MsgTransferNFT").setValue(msgTransferNFT.toByteString()).build();

        TxOuterClass.TxBody txBody = getGrpcTxBody(msgTransferNftAny, memo);
        TxOuterClass.SignerInfo signerInfo = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSendNftCroReq(QueryOuterClass.QueryAccountResponse auth, String sender, String recipient, String denomId, String id, Fee fee, String memo, ECKey pKey, String chainId) {
        chainmain.nft.v1.Tx.MsgTransferNFT msgTransferNFT = chainmain.nft.v1.Tx.MsgTransferNFT.newBuilder().setId(id).setDenomId(denomId).setSender(sender).setRecipient(recipient).build();
        Any msgTransferNftAny = Any.newBuilder().setTypeUrl("/chainmain.nft.v1.MsgTransferNFT").setValue(msgTransferNFT.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgTransferNftAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSendNftCroSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, String recipient, String denomId, String id, Fee fee, String memo, ECKey pKey, String chainId) {
        chainmain.nft.v1.Tx.MsgTransferNFT msgTransferNFT = chainmain.nft.v1.Tx.MsgTransferNFT.newBuilder().setId(id).setDenomId(denomId).setSender(sender).setRecipient(recipient).build();
        Any msgTransferNftAny = Any.newBuilder().setTypeUrl("/chainmain.nft.v1.MsgTransferNFT").setValue(msgTransferNFT.toByteString()).build();

        TxOuterClass.TxBody txBody = getGrpcTxBody(msgTransferNftAny, memo);
        TxOuterClass.SignerInfo signerInfo = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcCreateProfileReq(QueryOuterClass.QueryAccountResponse auth, String dtag, String nickname, String bio, String profilePicture, String coverPicture, String creator, Fee fee, String memo, ECKey pKey, String chainId) {
        MsgsProfile.MsgSaveProfile msgSaveProfile = MsgsProfile.MsgSaveProfile.newBuilder().setDtag(dtag).setNickname(nickname).setBio(bio).setProfilePicture(profilePicture).setCoverPicture(coverPicture).setCreator(creator).build();
        Any msgSaveProfileAny = Any.newBuilder().setTypeUrl("/desmos.profiles.v1beta1.MsgSaveProfile").setValue(msgSaveProfile.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgSaveProfileAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcCreateProfileSimulateReq(QueryOuterClass.QueryAccountResponse auth, String dtag, String nickname, String bio, String profilePicture, String coverPicture, String creator, Fee fee, String memo, ECKey pKey, String chainId) {
        MsgsProfile.MsgSaveProfile msgSaveProfile = MsgsProfile.MsgSaveProfile.newBuilder().setDtag(dtag).setNickname(nickname).setBio(bio).setProfilePicture(profilePicture).setCoverPicture(coverPicture).setCreator(creator).build();
        Any msgSaveProfileAny = Any.newBuilder().setTypeUrl("/desmos.profiles.v1beta1.MsgSaveProfile").setValue(msgSaveProfile.toByteString()).build();

        TxOuterClass.TxBody txBody = getGrpcTxBody(msgSaveProfileAny, memo);
        TxOuterClass.SignerInfo signerInfo = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcLinkAccountReq(QueryOuterClass.QueryAccountResponse auth, String singer, BaseChain toChain, Account toAccount, ECKey toKey, Fee fee, String memo, ECKey pKey, String chainId) {
        byte[] sigbyte = null;
        String plainString = "Link Chain With Cosmostation";
        String hexString = "";
        try {
            hexString = Hex.toHexString(plainString.getBytes("utf-8"));
            sigbyte = getGrpcByteSingleSignature(auth, toKey, plainString.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ModelsChainLinks.Bech32Address desmosBech32 = ModelsChainLinks.Bech32Address.newBuilder().setValue(toAccount.address).setPrefix(WUtil.getDesmosPrefix(toChain)).build();
        Any chainAddress = Any.newBuilder().setTypeUrl("/desmos.profiles.v1beta1.Bech32Address").setValue(desmosBech32.toByteString()).build();
        Keys.PubKey toAccountPub = Keys.PubKey.newBuilder().setKey(ByteString.copyFrom(toKey.getPubKey())).build();
        Any toAccountPubKey = Any.newBuilder().setTypeUrl("/cosmos.crypto.secp256k1.PubKey").setValue(toAccountPub.toByteString()).build();
        ModelsChainLinks.Proof desmosProof = ModelsChainLinks.Proof.newBuilder().setSignature(WUtil.ByteArrayToHexString(sigbyte)).setPlainText(hexString).setPubKey(toAccountPubKey).build();
        ModelsChainLinks.ChainConfig desmosChainConfig = ModelsChainLinks.ChainConfig.newBuilder().setName(WUtil.getDesmosConfig(toChain)).build();
        MsgsChainLinks.MsgLinkChainAccount linkchain = MsgsChainLinks.MsgLinkChainAccount.newBuilder().setChainAddress(chainAddress).setProof(desmosProof).setChainConfig(desmosChainConfig).setSigner(singer).build();

        Any msgLinkAccountAny = Any.newBuilder().setTypeUrl("/desmos.profiles.v1beta1.MsgLinkChainAccount").setValue(linkchain.toByteString()).build();
        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgLinkAccountAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcLinkAccountSimulateReq(QueryOuterClass.QueryAccountResponse auth, String singer, BaseChain toChain, Account toAccount, ECKey toKey, Fee fee, String memo, ECKey pKey, String chainId) {
        byte[] sigbyte = null;
        String plainString = "Link Chain With Cosmostation";
        String hexString = "";
        try {
            hexString = Hex.toHexString(plainString.getBytes("utf-8"));
            sigbyte = getGrpcByteSingleSignature(auth, toKey, plainString.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ModelsChainLinks.Bech32Address desmosBech32 = ModelsChainLinks.Bech32Address.newBuilder().setValue(toAccount.address).setPrefix(WUtil.getDesmosPrefix(toChain)).build();
        Any chainAddress = Any.newBuilder().setTypeUrl("/desmos.profiles.v1beta1.Bech32Address").setValue(desmosBech32.toByteString()).build();
        Keys.PubKey toAccountPub = Keys.PubKey.newBuilder().setKey(ByteString.copyFrom(toKey.getPubKey())).build();
        Any toAccountPubKey = Any.newBuilder().setTypeUrl("/cosmos.crypto.secp256k1.PubKey").setValue(toAccountPub.toByteString()).build();
        ModelsChainLinks.Proof desmosProof = ModelsChainLinks.Proof.newBuilder().setSignature(WUtil.ByteArrayToHexString(sigbyte)).setPlainText(hexString).setPubKey(toAccountPubKey).build();
        ModelsChainLinks.ChainConfig desmosChainConfig = ModelsChainLinks.ChainConfig.newBuilder().setName(WUtil.getDesmosConfig(toChain)).build();
        MsgsChainLinks.MsgLinkChainAccount linkchain = MsgsChainLinks.MsgLinkChainAccount.newBuilder().setChainAddress(chainAddress).setProof(desmosProof).setChainConfig(desmosChainConfig).setSigner(singer).build();

        Any msgLinkAccountAny = Any.newBuilder().setTypeUrl("/desmos.profiles.v1beta1.MsgLinkChainAccount").setValue(linkchain.toByteString()).build();
        TxOuterClass.TxBody txBody = getGrpcTxBody(msgLinkAccountAny, memo);
        TxOuterClass.SignerInfo signerInfo = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaSwapReq(QueryOuterClass.QueryAccountResponse auth, String requester, Coin swapIn, Coin swapOut, String slippage, Long deadline, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin SwapIn = CoinOuterClass.Coin.newBuilder().setAmount(swapIn.amount).setDenom(swapIn.denom).build();
        CoinOuterClass.Coin SwapOut = CoinOuterClass.Coin.newBuilder().setAmount(swapOut.amount).setDenom(swapOut.denom).build();
        kava.swap.v1beta1.Tx.MsgSwapExactForTokens msgSwapExactForTokens = kava.swap.v1beta1.Tx.MsgSwapExactForTokens.newBuilder().setRequester(requester).setExactTokenA(SwapIn).setTokenB(SwapOut).setSlippage(slippage).setDeadline(deadline).build();
        Any msgKavaSwapAny = Any.newBuilder().setTypeUrl("/kava.swap.v1beta1.MsgSwapExactForTokens").setValue(msgSwapExactForTokens.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgKavaSwapAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaSwapSimulateReq(QueryOuterClass.QueryAccountResponse auth, String requester, Coin swapIn, Coin swapOut, String slippage, Long deadline, Fee fee, String memo, ECKey pKey, String chainId) {
        CoinOuterClass.Coin SwapIn = CoinOuterClass.Coin.newBuilder().setAmount(swapIn.amount).setDenom(swapIn.denom).build();
        CoinOuterClass.Coin SwapOut = CoinOuterClass.Coin.newBuilder().setAmount(swapOut.amount).setDenom(swapOut.denom).build();
        kava.swap.v1beta1.Tx.MsgSwapExactForTokens msgSwapExactForTokens = kava.swap.v1beta1.Tx.MsgSwapExactForTokens.newBuilder().setRequester(requester).setExactTokenA(SwapIn).setTokenB(SwapOut).setSlippage(slippage).setDeadline(deadline).build();
        Any msgKavaSwapAny = Any.newBuilder().setTypeUrl("/kava.swap.v1beta1.MsgSwapExactForTokens").setValue(msgSwapExactForTokens.toByteString()).build();

        TxOuterClass.TxBody txBody          = getGrpcTxBody(msgKavaSwapAny, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static TxOuterClass.TxBody getGrpcTxBody(Any msgAny, String memo) {
        return TxOuterClass.TxBody.newBuilder().addMessages(msgAny).setMemo(memo).build();
    }

    public static TxOuterClass.TxBody getGrpcTxBodys(ArrayList<Any> msgsAny, String memo) {
        TxOuterClass.TxBody.Builder builder = TxOuterClass.TxBody.newBuilder();
        for (Any msg: msgsAny) {
            builder.addMessages(msg);
        }
        return builder.setMemo(memo).build();
    }


    public static TxOuterClass.SignerInfo getGrpcSignerInfo(QueryOuterClass.QueryAccountResponse auth, ECKey pKey) {
        Any pubKey = null;
        if (auth.getAccount().getTypeUrl().contains("/injective.types.v1beta1.EthAccount")) {
            pubKey = WKey.generateGrpcEthPubKeyFromPriv(pKey.getPrivateKeyAsHex());
        } else {
            pubKey = WKey.generateGrpcPubKeyFromPriv(pKey.getPrivateKeyAsHex());
        }
        TxOuterClass.ModeInfo.Single singleMode = TxOuterClass.ModeInfo.Single.newBuilder().setMode(SIGN_MODE_DIRECT).build();
        TxOuterClass.ModeInfo modeInfo = TxOuterClass.ModeInfo.newBuilder().setSingle(singleMode).build();
        return  TxOuterClass.SignerInfo.newBuilder().setPublicKey(pubKey).setModeInfo(modeInfo).setSequence(onParseSequenceNumber(auth)).build();
    }

    public static TxOuterClass.AuthInfo getGrpcAuthInfo(TxOuterClass.SignerInfo signerInfo, Fee fee) {
        CoinOuterClass.Coin feeCoin = CoinOuterClass.Coin.newBuilder().setAmount(fee.amount.get(0).amount).setDenom(fee.amount.get(0).denom).build();
        TxOuterClass.Fee txFee = TxOuterClass.Fee.newBuilder().addAmount(feeCoin).setGasLimit(Long.parseLong(fee.gas)).build();
        return TxOuterClass.AuthInfo.newBuilder().setFee(txFee).addSignerInfos(signerInfo).build();
    }

    public static TxOuterClass.TxRaw getGrpcRawTx(QueryOuterClass.QueryAccountResponse auth, TxOuterClass.TxBody txBody, TxOuterClass.AuthInfo authInfo, ECKey pKey, String chainId) {
        TxOuterClass.SignDoc signDoc = TxOuterClass.SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chainId).setAccountNumber(onParseAccountNumber(auth)).build();
        byte[] sigbyte = Signer.getGrpcByteSingleSignature(auth, pKey, signDoc.toByteArray());
        return TxOuterClass.TxRaw.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).addSignatures(ByteString.copyFrom(sigbyte)).build();
    }

    public static TxOuterClass.Tx getGrpcSimulTx(QueryOuterClass.QueryAccountResponse auth, TxOuterClass.TxBody txBody, TxOuterClass.AuthInfo authInfo, ECKey pKey, String chainId) {
        TxOuterClass.SignDoc signDoc = TxOuterClass.SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chainId).setAccountNumber(onParseAccountNumber(auth)).build();
        byte[] sigbyte = Signer.getGrpcByteSingleSignature(auth, pKey, signDoc.toByteArray());
        return TxOuterClass.Tx.newBuilder().setAuthInfo(authInfo).setBody(txBody).addSignatures(ByteString.copyFrom(sigbyte)).build();
    }

    public static byte[] getGrpcByteSingleSignature(QueryOuterClass.QueryAccountResponse auth, ECKey key, byte[] toSignByte) {
        byte[] sigData = new byte[64];
        if (auth.getAccount().getTypeUrl().contains("/injective.types.v1beta1.EthAccount")) {
            BigInteger privateKey = new BigInteger(key.getPrivateKeyAsHex(), 16);
            Sign.SignatureData sig = Sign.signMessage(toSignByte, ECKeyPair.create(privateKey));
            System.arraycopy(sig.getR(), 0, sigData, 0, 32);
            System.arraycopy(sig.getS(), 0, sigData, 32, 32);
        } else {
            MessageDigest digest = Sha256.getSha256Digest();
            byte[] toSignHash = digest.digest(toSignByte);
            ECKey.ECDSASignature Signature = key.sign(Sha256Hash.wrap(toSignHash));
            System.arraycopy(integerToBytes(Signature.r, 32), 0, sigData, 0, 32);
            System.arraycopy(integerToBytes(Signature.s, 32), 0, sigData, 32, 32);
        }
        return sigData;
    }
}

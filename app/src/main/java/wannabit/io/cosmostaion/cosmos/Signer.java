package wannabit.io.cosmostaion.cosmos;

import static cosmos.tx.signing.v1beta1.Signing.SignMode.SIGN_MODE_DIRECT;
import static wannabit.io.cosmostaion.utils.WUtil.integerToBytes;

import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.protobuf.Duration;
import com.google.protobuf2.Any;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Map;

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
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Cw20TransferReq;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

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

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.DelayedVestingAccount.getDescriptor().getFullName())) {
                Vesting.DelayedVestingAccount account = Vesting.DelayedVestingAccount.parseFrom(auth.getAccount().getValue());
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

                // evmos
            } else if (auth.getAccount().getTypeUrl().contains(ethermint.types.v1.Account.EthAccount.getDescriptor().getFullName())) {
                ethermint.types.v1.Account.EthAccount account = ethermint.types.v1.Account.EthAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseAccount().getAddress();
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

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.DelayedVestingAccount.getDescriptor().getFullName())) {
                Vesting.DelayedVestingAccount account = Vesting.DelayedVestingAccount.parseFrom(auth.getAccount().getValue());
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

            // evmos
            else if (auth.getAccount().getTypeUrl().contains(ethermint.types.v1.Account.EthAccount.getDescriptor().getFullName())) {
                ethermint.types.v1.Account.EthAccount account = ethermint.types.v1.Account.EthAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseAccount().getAccountNumber();
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

            } else if (auth.getAccount().getTypeUrl().contains(Vesting.DelayedVestingAccount.getDescriptor().getFullName())) {
                Vesting.DelayedVestingAccount account = Vesting.DelayedVestingAccount.parseFrom(auth.getAccount().getValue());
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

            // evmos
            else if (auth.getAccount().getTypeUrl().contains(ethermint.types.v1.Account.EthAccount.getDescriptor().getFullName())) {
                ethermint.types.v1.Account.EthAccount account = ethermint.types.v1.Account.EthAccount.parseFrom(auth.getAccount().getValue());
                return account.getBaseAccount().getSequence();
            }
        }catch (Exception e) {}
        return 0;
    }

    //gRpc Singer
    public static ServiceOuterClass.BroadcastTxRequest getGrpcSendReq(QueryOuterClass.QueryAccountResponse auth, String toAddress, ArrayList<Coin> amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getSendMsg(auth, toAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSendSimulateReq(QueryOuterClass.QueryAccountResponse auth, String toAddress, ArrayList<Coin> amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getSendMsg(auth, toAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getSendMsg(QueryOuterClass.QueryAccountResponse auth, String toAddress, ArrayList<Coin> amounts) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin toSendCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.get(0).amount).setDenom(amounts.get(0).denom).build();
        cosmos.bank.v1beta1.Tx.MsgSend msgSend = cosmos.bank.v1beta1.Tx.MsgSend.newBuilder().setFromAddress(onParseAddress(auth)).setToAddress(toAddress).addAmount(toSendCoin).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/cosmos.bank.v1beta1.MsgSend").setValue(msgSend.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcDelegateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getDelegateMsg(auth, toValAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcDelegateSimulateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getDelegateMsg(auth, toValAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getDelegateMsg(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcUnDelegateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getUnDelegateMsg(auth, toValAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcUnDelegateSimulateReq(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getUnDelegateMsg(auth, toValAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getUnDelegateMsg(QueryOuterClass.QueryAccountResponse auth, String toValAddress, Coin amounts) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin toDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgUndelegate msgUnDelegate = cosmos.staking.v1beta1.Tx.MsgUndelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(toValAddress).setAmount(toDelegateCoin).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgUndelegate").setValue(msgUnDelegate.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcClaimRewardsReq(QueryOuterClass.QueryAccountResponse auth, ArrayList<String> toValAddresses, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getClaimRewardsMsg(auth, toValAddresses), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcClaimRewardsSimulateReq(QueryOuterClass.QueryAccountResponse auth, ArrayList<String> toValAddresses, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getClaimRewardsMsg(auth, toValAddresses), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getClaimRewardsMsg(QueryOuterClass.QueryAccountResponse auth, ArrayList<String> toValAddresses) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        for (String valAddr: toValAddresses) {
            cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddr).build();
            Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
            msgAnys.add(msgClaimRewardAny);
        }
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcReDelegateReq(QueryOuterClass.QueryAccountResponse auth, String fromValAddress, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getReDelegateMsg(auth, fromValAddress, toValAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcReDelegateSimulateReq(QueryOuterClass.QueryAccountResponse auth, String fromValAddress, String toValAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getReDelegateMsg(auth, fromValAddress, toValAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getReDelegateMsg(QueryOuterClass.QueryAccountResponse auth, String fromValAddress, String toValAddress, Coin amounts) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin toReDelegateCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgBeginRedelegate msgReDelegate = cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorSrcAddress(fromValAddress).setValidatorDstAddress(toValAddress).setAmount(toReDelegateCoin).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgBeginRedelegate").setValue(msgReDelegate.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcReInvestReq(QueryOuterClass.QueryAccountResponse auth, String valAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getReInvestMsg(auth, valAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcReInvestSimulateReq(QueryOuterClass.QueryAccountResponse auth, String valAddress, Coin amounts, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getReInvestMsg(auth, valAddress, amounts), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getReInvestMsg(QueryOuterClass.QueryAccountResponse auth, String valAddress, Coin amounts) {
        ArrayList<Any> msgsAny = new ArrayList<>();
        cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msgClaimReward = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddress).build();
        Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward").setValue(msgClaimReward.toByteString()).build();
        msgsAny.add(msgClaimRewardAny);

        CoinOuterClass.Coin toReinvsetCoin = CoinOuterClass.Coin.newBuilder().setAmount(amounts.amount).setDenom(amounts.denom).build();
        cosmos.staking.v1beta1.Tx.MsgDelegate msgDelegate = cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder().setDelegatorAddress(onParseAddress(auth)).setValidatorAddress(valAddress).setAmount(toReinvsetCoin).build();
        Any msgDelegateAny = Any.newBuilder().setTypeUrl("/cosmos.staking.v1beta1.MsgDelegate").setValue(msgDelegate.toByteString()).build();
        msgsAny.add(msgDelegateAny);
        return msgsAny;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcRewardAddressChangeReq(QueryOuterClass.QueryAccountResponse auth, String newAddress, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getRewardAddressChangeMsg(auth, newAddress), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRewardAddressChangeSimulateReq(QueryOuterClass.QueryAccountResponse auth, String newAddress, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getRewardAddressChangeMsg(auth, newAddress), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getRewardAddressChangeMsg(QueryOuterClass.QueryAccountResponse auth, String newAddress) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress msgSetWithdrawAddress = cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress.newBuilder().setDelegatorAddress(onParseAddress(auth)).setWithdrawAddress(newAddress).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/cosmos.distribution.v1beta1.MsgSetWithdrawAddress").setValue(msgSetWithdrawAddress.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcVoteReq(QueryOuterClass.QueryAccountResponse auth, Map<Integer,String> opinionMap, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getVoteMsg(auth, opinionMap), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcVoteSimulateReq(QueryOuterClass.QueryAccountResponse auth, Map<Integer,String> opinionMap, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getVoteMsg(auth, opinionMap), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getVoteMsg(QueryOuterClass.QueryAccountResponse auth, Map<Integer,String> opinionMap) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        opinionMap.forEach((id, opinion) -> {
            Tx.MsgVote msgVote = Tx.MsgVote.newBuilder().setProposalId(Long.parseLong(String.valueOf(id))).setVoter(onParseAddress(auth)).setOption(Gov.VoteOption.valueOf(opinion)).build();
            Any msgClaimRewardAny = Any.newBuilder().setTypeUrl("/cosmos.gov.v1beta1.MsgVote").setValue(msgVote.toByteString()).build();
            msgAnys.add(msgClaimRewardAny);
        });
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcRegisterDomainReq(QueryOuterClass.QueryAccountResponse auth, String domain, String admin, String type, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getRegisterDomainMsg(domain, admin, type), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRegisterDomainSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String admin, String type, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getRegisterDomainMsg(domain, admin, type), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getRegisterDomainMsg(String domain, String admin, String type) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain msgRegisterDomain = starnamed.x.starname.v1beta1.Tx.MsgRegisterDomain.newBuilder().setName(domain).setAdmin(admin).setDomainType(type).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRegisterDomain").setValue(msgRegisterDomain.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcRegisterAccountReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, String registerer, ArrayList<Types.Resource> resources, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getRegisterAccountMsg(domain, name, owner, registerer, resources), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRegisterAccountSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, String registerer, ArrayList<Types.Resource> resources, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getRegisterAccountMsg(domain, name, owner, registerer, resources), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getRegisterAccountMsg(String domain, String name, String owner, String registerer, ArrayList<Types.Resource> resources) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount msgRegisterAccount = starnamed.x.starname.v1beta1.Tx.MsgRegisterAccount.newBuilder().setDomain(domain).setName(name).setOwner(owner).setRegisterer(registerer).addAllResources(resources).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRegisterAccount").setValue(msgRegisterAccount.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcDeleteDomainReq(QueryOuterClass.QueryAccountResponse auth, String domain, String owner, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getDeleteDomainMsg(domain, owner), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcDeleteDomainSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String owner, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getDeleteDomainMsg(domain, owner), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getDeleteDomainMsg(String domain, String owner) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain msgDeleteDomain = starnamed.x.starname.v1beta1.Tx.MsgDeleteDomain.newBuilder().setDomain(domain).setOwner(owner).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgDeleteDomain").setValue(msgDeleteDomain.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcDeleteAccountReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getDeleteAccountMsg(domain, name, owner), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcDeleteAccountSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getDeleteAccountMsg(domain, name, owner), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getDeleteAccountMsg(String domain, String name, String owner) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount msgDeleteAccount = starnamed.x.starname.v1beta1.Tx.MsgDeleteAccount.newBuilder().setDomain(domain).setName(name).setOwner(owner).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgDeleteAccount").setValue(msgDeleteAccount.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcRenewDomainReq(QueryOuterClass.QueryAccountResponse auth, String domain, String signer, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getRenewDomainMsg(domain, signer), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRenewDomainSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String signer, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getRenewDomainMsg(domain, signer), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getRenewDomainMsg(String domain, String signer) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        starnamed.x.starname.v1beta1.Tx.MsgRenewDomain msgRenewDomain = starnamed.x.starname.v1beta1.Tx.MsgRenewDomain.newBuilder().setDomain(domain).setSigner(signer).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRenewDomain").setValue(msgRenewDomain.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcRenewAccountReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String signer, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getRenewAccountMsg(domain, name, signer), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcRenewAccountSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String signer, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getRenewAccountMsg(domain, name, signer), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getRenewAccountMsg(String domain, String name, String signer) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        starnamed.x.starname.v1beta1.Tx.MsgRenewAccount msgRenewAccount = starnamed.x.starname.v1beta1.Tx.MsgRenewAccount.newBuilder().setDomain(domain).setName(name).setSigner(signer).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgRenewAccount").setValue(msgRenewAccount.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcReplaceResourceReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, ArrayList<Types.Resource> resources, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getReplaceResourcetMsg(domain, name, owner, resources), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcReplaceResourceSimulateReq(QueryOuterClass.QueryAccountResponse auth, String domain, String name, String owner, ArrayList<Types.Resource> resources, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getReplaceResourcetMsg(domain, name, owner, resources), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getReplaceResourcetMsg(String domain, String name, String owner, ArrayList<Types.Resource> resources) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources msgReplaceResource= starnamed.x.starname.v1beta1.Tx.MsgReplaceAccountResources.newBuilder().setDomain(domain).setName(name).setOwner(owner).addAllNewResources(resources).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/starnamed.x.starname.v1beta1.MsgReplaceAccountResources").setValue(msgReplaceResource.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSwapInReq(QueryOuterClass.QueryAccountResponse auth, osmosis.gamm.v1beta1.Tx.SwapAmountInRoute swapRoute, String inputDenom, String inputAmount, String outputAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getSwapInMsg(auth, swapRoute, inputDenom, inputAmount, outputAmount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSwapInSimulateReq(QueryOuterClass.QueryAccountResponse auth, osmosis.gamm.v1beta1.Tx.SwapAmountInRoute swapRoute, String inputDenom, String inputAmount, String outputAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getSwapInMsg(auth, swapRoute, inputDenom, inputAmount, outputAmount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getSwapInMsg(QueryOuterClass.QueryAccountResponse auth, osmosis.gamm.v1beta1.Tx.SwapAmountInRoute swapRoute, String inputDenom, String inputAmount, String outputAmount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin inputCoin = CoinOuterClass.Coin.newBuilder().setDenom(inputDenom).setAmount(inputAmount).build();
        osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn msgSwapExactAmountIn = osmosis.gamm.v1beta1.Tx.MsgSwapExactAmountIn.newBuilder().setSender(onParseAddress(auth)).addRoutes(swapRoute).setTokenIn(inputCoin).setTokenOutMinAmount(outputAmount).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/osmosis.gamm.v1beta1.MsgSwapExactAmountIn").setValue(msgSwapExactAmountIn.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcJoinPoolReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin, String shareAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getJoinPoolMsg(auth, poolId, deposit0Coin, deposit1Coin, shareAmount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcJoinPoolSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin, String shareAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getJoinPoolMsg(auth, poolId, deposit0Coin, deposit1Coin, shareAmount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getJoinPoolMsg(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin, String shareAmount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin inputCoin0 = CoinOuterClass.Coin.newBuilder().setDenom(deposit0Coin.denom).setAmount(deposit0Coin.amount).build();
        CoinOuterClass.Coin inputCoin1 = CoinOuterClass.Coin.newBuilder().setDenom(deposit1Coin.denom).setAmount(deposit1Coin.amount).build();
        ArrayList<CoinOuterClass.Coin> tokenMax = new ArrayList<>();
        tokenMax.add(inputCoin0);
        tokenMax.add(inputCoin1);
        osmosis.gamm.v1beta1.Tx.MsgJoinPool msgJoinPool = osmosis.gamm.v1beta1.Tx.MsgJoinPool.newBuilder().setSender(onParseAddress(auth)).setPoolId(poolId).addAllTokenInMaxs(tokenMax).setShareOutAmount(shareAmount).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/osmosis.gamm.v1beta1.MsgJoinPool").setValue(msgJoinPool.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcExitPoolReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin Withdraw0Coin, Coin Withdraw1Coin, String shareAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getExitPoolMsg(auth, poolId, Withdraw0Coin, Withdraw1Coin, shareAmount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcExitPoolSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin Withdraw0Coin, Coin Withdraw1Coin, String shareAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getExitPoolMsg(auth, poolId, Withdraw0Coin, Withdraw1Coin, shareAmount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getExitPoolMsg(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin Withdraw0Coin, Coin Withdraw1Coin, String shareAmount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin OutputCoin0 = CoinOuterClass.Coin.newBuilder().setDenom(Withdraw0Coin.denom).setAmount(Withdraw0Coin.amount).build();
        CoinOuterClass.Coin OutputCoin1 = CoinOuterClass.Coin.newBuilder().setDenom(Withdraw1Coin.denom).setAmount(Withdraw1Coin.amount).build();
        ArrayList<CoinOuterClass.Coin> tokenMin = new ArrayList<>();
        tokenMin.add(OutputCoin0);
        tokenMin.add(OutputCoin1);
        osmosis.gamm.v1beta1.Tx.MsgExitPool msgExitPool = osmosis.gamm.v1beta1.Tx.MsgExitPool.newBuilder().setSender(onParseAddress(auth)).setPoolId(poolId).addAllTokenOutMins(tokenMin).setShareInAmount(shareAmount).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/osmosis.gamm.v1beta1.MsgExitPool").setValue(msgExitPool.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcStartLockReq(QueryOuterClass.QueryAccountResponse auth, long duration, Coin lpCoin, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getStartLockMsg(auth, duration, lpCoin), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcStartLockSimulateReq(QueryOuterClass.QueryAccountResponse auth, long duration, Coin lpCoin, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getStartLockMsg(auth, duration, lpCoin), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getStartLockMsg(QueryOuterClass.QueryAccountResponse auth, long duration, Coin lpCoin) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin lockupCoin = CoinOuterClass.Coin.newBuilder().setDenom(lpCoin.denom).setAmount(lpCoin.amount).build();
        ArrayList<CoinOuterClass.Coin> lockupTokens = new ArrayList<>();
        lockupTokens.add(lockupCoin);
        Duration OsmoDuration = Duration.newBuilder().setSeconds(duration).setNanos(0).build();
        osmosis.lockup.Tx.MsgLockTokens msgLockTokens = osmosis.lockup.Tx.MsgLockTokens.newBuilder().setOwner(onParseAddress(auth)).setDuration(OsmoDuration).addAllCoins(lockupTokens).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/osmosis.lockup.MsgLockTokens").setValue(msgLockTokens.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcBeginUnbondingReq(QueryOuterClass.QueryAccountResponse auth, ArrayList<Long> ids, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getBeginUnbondingMsg(auth, ids), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcBeginUnbondingSimulateReq(QueryOuterClass.QueryAccountResponse auth, ArrayList<Long> ids, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getBeginUnbondingMsg(auth, ids), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getBeginUnbondingMsg(QueryOuterClass.QueryAccountResponse auth, ArrayList<Long> ids) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        for (Long id: ids) {
            osmosis.lockup.Tx.MsgBeginUnlocking msgBeginUnlocking = osmosis.lockup.Tx.MsgBeginUnlocking.newBuilder().setOwner(onParseAddress(auth)).setID(id).build();
            Any msgBeginUnbondingAny = Any.newBuilder().setTypeUrl("/osmosis.lockup.MsgBeginUnlocking").setValue(msgBeginUnlocking.toByteString()).build();
            msgAnys.add(msgBeginUnbondingAny);
        }
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcGravitySwapReq(QueryOuterClass.QueryAccountResponse auth, long poolId, int typeId, Coin offerCoin, String demandCoinDenom, Coin feeCoin, String orderPrice, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getGravitySwapMsg(auth, poolId, typeId, offerCoin, demandCoinDenom, feeCoin, orderPrice), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcGravitySwapSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, int typeId, Coin offerCoin, String demandCoinDenom, Coin feeCoin, String orderPrice, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getGravitySwapMsg(auth, poolId, typeId, offerCoin, demandCoinDenom, feeCoin, orderPrice), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getGravitySwapMsg(QueryOuterClass.QueryAccountResponse auth, long poolId, int typeId, Coin offerCoin, String demandCoinDenom, Coin feeCoin, String orderPrice) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin inputCoin = CoinOuterClass.Coin.newBuilder().setDenom(offerCoin.denom).setAmount(offerCoin.amount).build();
        CoinOuterClass.Coin coinFee = CoinOuterClass.Coin.newBuilder().setDenom(feeCoin.denom).setAmount(feeCoin.amount).build();
        tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch msgSwapWithinBatch = tendermint.liquidity.v1beta1.Tx.MsgSwapWithinBatch.newBuilder().setSwapRequesterAddress(onParseAddress(auth)).setPoolId(poolId).setSwapTypeId(typeId).setOfferCoin(inputCoin).setDemandCoinDenom(demandCoinDenom).setOfferCoinFee(coinFee).setOrderPrice(orderPrice).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/tendermint.liquidity.v1beta1.MsgSwapWithinBatch").setValue(msgSwapWithinBatch.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcGDexDepositReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getGDexDepositMsg(auth, poolId, deposit0Coin, deposit1Coin), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcGDexDepositSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getGDexDepositMsg(auth, poolId, deposit0Coin, deposit1Coin), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getGDexDepositMsg(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin deposit0Coin, Coin deposit1Coin) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin inputCoin0 = CoinOuterClass.Coin.newBuilder().setDenom(deposit0Coin.denom).setAmount(deposit0Coin.amount).build();
        CoinOuterClass.Coin inputCoin1 = CoinOuterClass.Coin.newBuilder().setDenom(deposit1Coin.denom).setAmount(deposit1Coin.amount).build();
        ArrayList<CoinOuterClass.Coin> tokenMax = new ArrayList<>();
        tokenMax.add(inputCoin0);
        tokenMax.add(inputCoin1);
        tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch msgDepositWithinBatch = tendermint.liquidity.v1beta1.Tx.MsgDepositWithinBatch.newBuilder().setDepositorAddress(onParseAddress(auth)).setPoolId(poolId).addAllDepositCoins(tokenMax).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/tendermint.liquidity.v1beta1.MsgDepositWithinBatch").setValue(msgDepositWithinBatch.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcGDexWithdrawReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin WithdrawCoin, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getGDexWithdrawMsg(auth, poolId, WithdrawCoin), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcGDexWithdrawSimulateReq(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin WithdrawCoin, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getGDexWithdrawMsg(auth, poolId, WithdrawCoin), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getGDexWithdrawMsg(QueryOuterClass.QueryAccountResponse auth, long poolId, Coin WithdrawCoin) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin OutputCoin = CoinOuterClass.Coin.newBuilder().setDenom(WithdrawCoin.denom).setAmount(WithdrawCoin.amount).build();
        tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch msgWithdrawWithinBatch = tendermint.liquidity.v1beta1.Tx.MsgWithdrawWithinBatch.newBuilder().setWithdrawerAddress(onParseAddress(auth)).setPoolId(poolId).setPoolCoin(OutputCoin).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/tendermint.liquidity.v1beta1.MsgWithdrawWithinBatch").setValue(msgWithdrawWithinBatch.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcIbcTransferReq(QueryOuterClass.QueryAccountResponse auth, String sender, String receiver, String ibcSendDenom, String ibcSendAmount, String portId, String channelId, Client.Height lastHeight, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getIbcTransferMsg(sender, receiver, ibcSendDenom, ibcSendAmount, portId, channelId, lastHeight), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcIbcTransferSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, String receiver, String ibcSendDenom, String ibcSendAmount, String portId, String channelId, Client.Height lastHeight, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getIbcTransferMsg(sender, receiver, ibcSendDenom, ibcSendAmount, portId, channelId, lastHeight), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getIbcTransferMsg(String sender, String receiver, String ibcSendDenom, String ibcSendAmount, String portId, String channelId, Client.Height lastHeight) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        Client.Height height = Client.Height.newBuilder().setRevisionNumber(lastHeight.getRevisionNumber()).setRevisionHeight(lastHeight.getRevisionHeight() + 1000).build();
        CoinOuterClass.Coin token = CoinOuterClass.Coin.newBuilder().setAmount(ibcSendAmount).setDenom(ibcSendDenom).build();
        ibc.applications.transfer.v1.Tx.MsgTransfer msgIbcTransfer = ibc.applications.transfer.v1.Tx.MsgTransfer.newBuilder().setSender(sender).setReceiver(receiver).setSourcePort(portId).setSourceChannel(channelId).setToken(token).setTimeoutHeight(height).setTimeoutTimestamp(0).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/ibc.applications.transfer.v1.MsgTransfer").setValue(msgIbcTransfer.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSifIncentiveReq(QueryOuterClass.QueryAccountResponse auth, String userClaimAddress, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth,getSifIncentiveMsg(userClaimAddress), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSifIncentiveSimulateReq(QueryOuterClass.QueryAccountResponse auth, String userClaimAddress, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getSifIncentiveMsg(userClaimAddress), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getSifIncentiveMsg(String userClaimAddress) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        sifnode.dispensation.v1.Tx.MsgCreateUserClaim msgCreateUserClaim = sifnode.dispensation.v1.Tx.MsgCreateUserClaim.newBuilder().setUserClaimAddress(userClaimAddress).setUserClaimType(sifnode.dispensation.v1.Types.DistributionType.DISTRIBUTION_TYPE_LIQUIDITY_MINING).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/sifnode.dispensation.v1.MsgCreateUserClaim").setValue(msgCreateUserClaim.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSifSwapReq(QueryOuterClass.QueryAccountResponse auth, String signer, String inputDenom, String inputAmount, String outputDenom, String outputAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth,getSifSwapMsg(signer, inputDenom, inputAmount, outputDenom, outputAmount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSifSwapSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String inputDenom, String inputAmount, String outputDenom, String outputAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getSifSwapMsg(signer, inputDenom, inputAmount, outputDenom, outputAmount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getSifSwapMsg(String signer, String inputDenom, String inputAmount, String outputDenom, String outputAmount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        sifnode.clp.v1.Types.Asset inputAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(inputDenom).build();
        sifnode.clp.v1.Types.Asset outpuAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(outputDenom).build();
        sifnode.clp.v1.Tx.MsgSwap msgSwap = sifnode.clp.v1.Tx.MsgSwap.newBuilder().setSigner(signer).setSentAsset(inputAsset).setReceivedAsset(outpuAsset).setSentAmount(inputAmount).setMinReceivingAmount(outputAmount).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/sifnode.clp.v1.MsgSwap").setValue(msgSwap.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSifDepositReq(QueryOuterClass.QueryAccountResponse auth, String signer, String externalDenom, String nativeAmount, String externalAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getSifDepositMsg(signer, externalDenom, nativeAmount, externalAmount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSifDepositSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String externalDenom, String nativeAmount, String externalAmount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getSifDepositMsg(signer, externalDenom, nativeAmount, externalAmount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getSifDepositMsg(String signer, String externalDenom, String nativeAmount, String externalAmount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        sifnode.clp.v1.Types.Asset externalAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(externalDenom).build();
        sifnode.clp.v1.Tx.MsgAddLiquidity msgAddLiquidity = sifnode.clp.v1.Tx.MsgAddLiquidity.newBuilder().setSigner(signer).setExternalAsset(externalAsset).setNativeAssetAmount(nativeAmount).setExternalAssetAmount(externalAmount).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/sifnode.clp.v1.MsgAddLiquidity").setValue(msgAddLiquidity.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSifWithdrawReq(QueryOuterClass.QueryAccountResponse auth, String signer, String externalDenom, String wBasisPoints, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getSifWithdrawMsg(signer, externalDenom, wBasisPoints), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSifWithdrawSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String externalDenom, String wBasisPoints, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getSifWithdrawMsg(signer, externalDenom, wBasisPoints), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getSifWithdrawMsg(String signer, String externalDenom, String wBasisPoints) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        sifnode.clp.v1.Types.Asset externalAsset = sifnode.clp.v1.Types.Asset.newBuilder().setSymbol(externalDenom).build();
        sifnode.clp.v1.Tx.MsgRemoveLiquidity msgRemoveLiquidity = sifnode.clp.v1.Tx.MsgRemoveLiquidity.newBuilder().setSigner(signer).setExternalAsset(externalAsset).setWBasisPoints(wBasisPoints).setAsymmetry("0").build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/sifnode.clp.v1.MsgRemoveLiquidity").setValue(msgRemoveLiquidity.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcCreateNftIrisReq(QueryOuterClass.QueryAccountResponse auth, String signer, String denomId, String denomName, String id, String name, String uri, String data, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getCreateNftIrisMsg(signer, denomId, denomName, id, name, uri, data), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcCreateNftIrisSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String denomId, String denomName, String id, String name, String uri, String data, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getCreateNftIrisMsg(signer, denomId, denomName, id, name, uri, data), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getCreateNftIrisMsg(String signer, String denomId, String denomName, String id, String name, String uri, String data) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        irismod.nft.Tx.MsgIssueDenom msgIssueDenom = irismod.nft.Tx.MsgIssueDenom.newBuilder().setId(denomId).setName(denomName).setSchema("").setSender(signer).setMintRestricted(false).setUpdateRestricted(false).build();
        Any msgIssueDenomAny = Any.newBuilder().setTypeUrl("/irismod.nft.MsgIssueDenom").setValue(msgIssueDenom.toByteString()).build();
        msgAnys.add(msgIssueDenomAny);

        irismod.nft.Tx.MsgMintNFT msgMintNFT = irismod.nft.Tx.MsgMintNFT.newBuilder().setSender(signer).setRecipient(signer).setId(id).setDenomId(denomId).setName(name).setUri(uri).setData(data).build();
        Any msgMintNftAny = Any.newBuilder().setTypeUrl("/irismod.nft.MsgMintNFT").setValue(msgMintNFT.toByteString()).build();
        msgAnys.add(msgMintNftAny);
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcCreateNftCroReq(QueryOuterClass.QueryAccountResponse auth, String signer, String denomId, String denomName, String id, String name, String uri, String data, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getCreateNftCroMsg(signer, denomId, denomName, id, name, uri, data), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcCreateNftCroSimulateReq(QueryOuterClass.QueryAccountResponse auth, String signer, String denomId, String denomName, String id, String name, String uri, String data, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getCreateNftCroMsg(signer, denomId, denomName, id, name, uri, data), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getCreateNftCroMsg(String signer, String denomId, String denomName, String id, String name, String uri, String data) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        chainmain.nft.v1.Tx.MsgIssueDenom msgIssueDenom = chainmain.nft.v1.Tx.MsgIssueDenom.newBuilder().setId(denomId).setName(denomName).setSchema("").setSender(signer).build();
        Any msgIssueDenomAny = Any.newBuilder().setTypeUrl("/chainmain.nft.v1.MsgIssueDenom").setValue(msgIssueDenom.toByteString()).build();
        msgAnys.add(msgIssueDenomAny);

        chainmain.nft.v1.Tx.MsgMintNFT msgMintNFT = chainmain.nft.v1.Tx.MsgMintNFT.newBuilder().setSender(signer).setRecipient(signer).setId(id).setDenomId(denomId).setName(name).setUri(uri).setData(data).build();
        Any msgMintNftAny = Any.newBuilder().setTypeUrl("/chainmain.nft.v1.MsgMintNFT").setValue(msgMintNFT.toByteString()).build();
        msgAnys.add(msgMintNftAny);
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSendNftIrisReq(QueryOuterClass.QueryAccountResponse auth, String sender, String recipient, String denomId, String id, irismod.nft.QueryOuterClass.QueryNFTResponse irisResponse, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getSendNftIrisMsg(sender, recipient, denomId, id, irisResponse), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSendNftIrisSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, String recipient, String denomId, String id, irismod.nft.QueryOuterClass.QueryNFTResponse irisResponse, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getSendNftIrisMsg(sender, recipient, denomId, id, irisResponse), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getSendNftIrisMsg(String sender, String recipient, String denomId, String id, irismod.nft.QueryOuterClass.QueryNFTResponse irisResponse) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        irismod.nft.Tx.MsgTransferNFT msgTransferNFT = irismod.nft.Tx.MsgTransferNFT.newBuilder().setId(id).setDenomId(denomId).setSender(sender).setRecipient(recipient).setName(irisResponse.getNft().getName()).setUri(irisResponse.getNft().getUri()).setData(irisResponse.getNft().getData()).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/irismod.nft.MsgTransferNFT").setValue(msgTransferNFT.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcSendNftCroReq(QueryOuterClass.QueryAccountResponse auth, String sender, String recipient, String denomId, String id, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getSendNftCroMsg(sender, recipient, denomId, id), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcSendNftCroSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, String recipient, String denomId, String id, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getSendNftCroMsg(sender, recipient, denomId, id), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getSendNftCroMsg(String sender, String recipient, String denomId, String id) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        chainmain.nft.v1.Tx.MsgTransferNFT msgTransferNFT = chainmain.nft.v1.Tx.MsgTransferNFT.newBuilder().setId(id).setDenomId(denomId).setSender(sender).setRecipient(recipient).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/chainmain.nft.v1.MsgTransferNFT").setValue(msgTransferNFT.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcCreateProfileReq(QueryOuterClass.QueryAccountResponse auth, String dtag, String nickname, String bio, String profilePicture, String coverPicture, String creator, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getCreateProfileMsg(dtag, nickname, bio, profilePicture, coverPicture, creator), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcCreateProfileSimulateReq(QueryOuterClass.QueryAccountResponse auth, String dtag, String nickname, String bio, String profilePicture, String coverPicture, String creator, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getCreateProfileMsg(dtag, nickname, bio, profilePicture, coverPicture, creator), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getCreateProfileMsg(String dtag, String nickname, String bio, String profilePicture, String coverPicture, String creator) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        MsgsProfile.MsgSaveProfile msgSaveProfile = MsgsProfile.MsgSaveProfile.newBuilder().setDtag(dtag).setNickname(nickname).setBio(bio).setProfilePicture(profilePicture).setCoverPicture(coverPicture).setCreator(creator).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/desmos.profiles.v1beta1.MsgSaveProfile").setValue(msgSaveProfile.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcLinkAccountReq(QueryOuterClass.QueryAccountResponse auth, String singer, BaseChain toChain, Account toAccount, ECKey toKey, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getLinkAccountMsg(auth, singer, toChain, toAccount, toKey), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcLinkAccountSimulateReq(QueryOuterClass.QueryAccountResponse auth, String singer, BaseChain toChain, Account toAccount, ECKey toKey, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getLinkAccountMsg(auth, singer, toChain, toAccount, toKey), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getLinkAccountMsg(QueryOuterClass.QueryAccountResponse auth, String singer, BaseChain toChain, Account toAccount, ECKey toKey) {
        ArrayList<Any> msgAnys = new ArrayList<>();
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

        msgAnys.add(Any.newBuilder().setTypeUrl("/desmos.profiles.v1beta1.MsgLinkChainAccount").setValue(linkchain.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaSwapReq(QueryOuterClass.QueryAccountResponse auth, String requester, Coin swapIn, Coin swapOut, String slippage, Long deadline, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaSwapMsg(requester, swapIn, swapOut, slippage, deadline), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaSwapSimulateReq(QueryOuterClass.QueryAccountResponse auth, String requester, Coin swapIn, Coin swapOut, String slippage, Long deadline, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaSwapMsg(requester, swapIn, swapOut, slippage, deadline), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaSwapMsg(String requester, Coin swapIn, Coin swapOut, String slippage, Long deadline) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin SwapIn = CoinOuterClass.Coin.newBuilder().setAmount(swapIn.amount).setDenom(swapIn.denom).build();
        CoinOuterClass.Coin SwapOut = CoinOuterClass.Coin.newBuilder().setAmount(swapOut.amount).setDenom(swapOut.denom).build();
        kava.swap.v1beta1.Tx.MsgSwapExactForTokens msgSwapExactForTokens = kava.swap.v1beta1.Tx.MsgSwapExactForTokens.newBuilder().setRequester(requester).setExactTokenA(SwapIn).setTokenB(SwapOut).setSlippage(slippage).setDeadline(deadline).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.swap.v1beta1.MsgSwapExactForTokens").setValue(msgSwapExactForTokens.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaDepositReq(QueryOuterClass.QueryAccountResponse auth, String depositor, Coin tokenA, Coin tokenB, String slippage, Long deadline, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaDepositPoolMsg(depositor, tokenA, tokenB, slippage, deadline), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaDepositPoolSimulateReq(QueryOuterClass.QueryAccountResponse auth, String depositor, Coin tokenA, Coin tokenB, String slippage, Long deadline, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaDepositPoolMsg(depositor, tokenA, tokenB, slippage, deadline), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaDepositPoolMsg(String depositor, Coin tokenA, Coin tokenB, String slippage, Long deadline) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin TokenA = CoinOuterClass.Coin.newBuilder().setAmount(tokenA.amount).setDenom(tokenA.denom).build();
        CoinOuterClass.Coin TokenB = CoinOuterClass.Coin.newBuilder().setAmount(tokenB.amount).setDenom(tokenB.denom).build();
        kava.swap.v1beta1.Tx.MsgDeposit msgDeposit = kava.swap.v1beta1.Tx.MsgDeposit.newBuilder().setDepositor(depositor).setTokenA(TokenA).setTokenB(TokenB).setSlippage(slippage).setDeadline(deadline).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.swap.v1beta1.MsgDeposit").setValue(msgDeposit.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaWithdrawReq(QueryOuterClass.QueryAccountResponse auth, String from, String shares, Coin minTokenA, Coin minTokenB, Long deadline, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaWithdrawPoolMsg(from, shares, minTokenA, minTokenB, deadline), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaWithdrawPoolSimulateReq(QueryOuterClass.QueryAccountResponse auth, String from, String shares, Coin minTokenA, Coin minTokenB, Long deadline, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaWithdrawPoolMsg(from, shares, minTokenA, minTokenB, deadline), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaWithdrawPoolMsg(String from, String shares, Coin minTokenA, Coin minTokenB, Long deadline) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin MinTokenA = CoinOuterClass.Coin.newBuilder().setAmount(minTokenA.amount).setDenom(minTokenA.denom).build();
        CoinOuterClass.Coin MinTokenB = CoinOuterClass.Coin.newBuilder().setAmount(minTokenB.amount).setDenom(minTokenB.denom).build();
        kava.swap.v1beta1.Tx.MsgWithdraw msgWithdraw = kava.swap.v1beta1.Tx.MsgWithdraw.newBuilder().setFrom(from).setShares(shares).setMinTokenA(MinTokenA).setMinTokenB(MinTokenB).setDeadline(deadline).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.swap.v1beta1.MsgWithdraw").setValue(msgWithdraw.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaCreateCdpReq(QueryOuterClass.QueryAccountResponse auth, String sender, Coin collateral, Coin principal, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaCreateCdpMsg(sender, collateral, principal, collateralType), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaCreateCdpSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, Coin collateral, Coin principal, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaCreateCdpMsg(sender, collateral, principal, collateralType), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaCreateCdpMsg(String sender, Coin collateral, Coin principal, String collateralType) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin Collateral = CoinOuterClass.Coin.newBuilder().setAmount(collateral.amount).setDenom(collateral.denom).build();
        CoinOuterClass.Coin Principal = CoinOuterClass.Coin.newBuilder().setAmount(principal.amount).setDenom(principal.denom).build();
        kava.cdp.v1beta1.Tx.MsgCreateCDP msgCreateCDP = kava.cdp.v1beta1.Tx.MsgCreateCDP.newBuilder().setSender(sender).setCollateral(Collateral).setPrincipal(Principal).setCollateralType(collateralType).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgCreateCDP").setValue(msgCreateCDP.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaDepositCdpReq(QueryOuterClass.QueryAccountResponse auth, String owner, String depositor, Coin collateral, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaDepositCdpMsg(owner, depositor, collateral, collateralType), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaDepositCdpSimulateReq(QueryOuterClass.QueryAccountResponse auth, String owner, String depositor, Coin collateral, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaDepositCdpMsg(owner, depositor, collateral, collateralType), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaDepositCdpMsg(String owner, String depositor, Coin collateral, String collateralType) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin Collateral = CoinOuterClass.Coin.newBuilder().setAmount(collateral.amount).setDenom(collateral.denom).build();
        kava.cdp.v1beta1.Tx.MsgDeposit msgDepositCdp = kava.cdp.v1beta1.Tx.MsgDeposit.newBuilder().setOwner(owner).setDepositor(depositor).setCollateral(Collateral).setCollateralType(collateralType).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgDeposit").setValue(msgDepositCdp.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaWithdrawCdpReq(QueryOuterClass.QueryAccountResponse auth, String owner, String depositor, Coin collateral, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaWithdrawCdpMsg(owner, depositor, collateral, collateralType), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaWithdrawCdpSimulateReq(QueryOuterClass.QueryAccountResponse auth, String owner, String depositor, Coin collateral, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaWithdrawCdpMsg(owner, depositor, collateral, collateralType), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaWithdrawCdpMsg(String owner, String depositor, Coin collateral, String collateralType) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin Collateral = CoinOuterClass.Coin.newBuilder().setAmount(collateral.amount).setDenom(collateral.denom).build();
        kava.cdp.v1beta1.Tx.MsgWithdraw msgDepositCdp = kava.cdp.v1beta1.Tx.MsgWithdraw.newBuilder().setOwner(owner).setDepositor(depositor).setCollateral(Collateral).setCollateralType(collateralType).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgWithdraw").setValue(msgDepositCdp.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaDrawDebtCdpReq(QueryOuterClass.QueryAccountResponse auth, String sender, Coin principal, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaDrawDebtwCdpMsg(sender, principal, collateralType), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaDrawDebtCdpSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, Coin principal, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaDrawDebtwCdpMsg(sender, principal, collateralType), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaDrawDebtwCdpMsg(String sender, Coin principal, String collateralType) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin Principal = CoinOuterClass.Coin.newBuilder().setAmount(principal.amount).setDenom(principal.denom).build();
        kava.cdp.v1beta1.Tx.MsgDrawDebt msgDrawDebt = kava.cdp.v1beta1.Tx.MsgDrawDebt.newBuilder().setSender(sender).setCollateralType(collateralType).setPrincipal(Principal).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgDrawDebt").setValue(msgDrawDebt.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaRepayCdpReq(QueryOuterClass.QueryAccountResponse auth, String sender, Coin payment, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaRepayCdpMsg(sender, payment, collateralType), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaRepayCdpSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, Coin payment, String collateralType, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaRepayCdpMsg(sender, payment, collateralType), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaRepayCdpMsg(String sender, Coin payment, String collateralType) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin Payment = CoinOuterClass.Coin.newBuilder().setAmount(payment.amount).setDenom(payment.denom).build();
        kava.cdp.v1beta1.Tx.MsgRepayDebt msgRepayDebt = kava.cdp.v1beta1.Tx.MsgRepayDebt.newBuilder().setSender(sender).setCollateralType(collateralType).setPayment(Payment).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.cdp.v1beta1.MsgRepayDebt").setValue(msgRepayDebt.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaDepositHardReq(QueryOuterClass.QueryAccountResponse auth, String depositer, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaDepositHardMsg(depositer, amount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaDepositHardSimulateReq(QueryOuterClass.QueryAccountResponse auth, String depositer, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaDepositHardMsg(depositer, amount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaDepositHardMsg(String depositer, ArrayList<Coin> amount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin DepositCoin = CoinOuterClass.Coin.newBuilder().setAmount(amount.get(0).amount).setDenom(amount.get(0).denom).build();
        kava.hard.v1beta1.Tx.MsgDeposit msgDeposit = kava.hard.v1beta1.Tx.MsgDeposit.newBuilder().setDepositor(depositer).addAmount(DepositCoin).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.hard.v1beta1.MsgDeposit").setValue(msgDeposit.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaWithdrawHardReq(QueryOuterClass.QueryAccountResponse auth, String depositer, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaWithdrawHardMsg(depositer, amount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaWithdrawHardSimulateReq(QueryOuterClass.QueryAccountResponse auth, String depositer, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaWithdrawHardMsg(depositer, amount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaWithdrawHardMsg(String depositer, ArrayList<Coin> amount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin WithdrawCoin = CoinOuterClass.Coin.newBuilder().setAmount(amount.get(0).amount).setDenom(amount.get(0).denom).build();
        kava.hard.v1beta1.Tx.MsgWithdraw msgWithdraw = kava.hard.v1beta1.Tx.MsgWithdraw.newBuilder().setDepositor(depositer).addAmount(WithdrawCoin).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.hard.v1beta1.MsgWithdraw").setValue(msgWithdraw.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaBorrowHardReq(QueryOuterClass.QueryAccountResponse auth, String borrower, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaBorrowHardMsg(borrower, amount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaBorrowHardSimulateReq(QueryOuterClass.QueryAccountResponse auth, String borrower, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaBorrowHardMsg(borrower, amount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaBorrowHardMsg(String borrower, ArrayList<Coin> amount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin BorrowCoin = CoinOuterClass.Coin.newBuilder().setAmount(amount.get(0).amount).setDenom(amount.get(0).denom).build();
        kava.hard.v1beta1.Tx.MsgBorrow msgBorrow = kava.hard.v1beta1.Tx.MsgBorrow.newBuilder().setBorrower(borrower).addAmount(BorrowCoin).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.hard.v1beta1.MsgBorrow").setValue(msgBorrow.toByteString()).build());
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaRepayHardReq(QueryOuterClass.QueryAccountResponse auth, String sender, String owner, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaRepayHardMsg(sender, owner, amount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaRepayHardSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, String owner, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaRepayHardMsg(sender, owner, amount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaRepayHardMsg(String sender, String owner, ArrayList<Coin> amount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin RepayCoin = CoinOuterClass.Coin.newBuilder().setAmount(amount.get(0).amount).setDenom(amount.get(0).denom).build();
        kava.hard.v1beta1.Tx.MsgRepay msgRepay = kava.hard.v1beta1.Tx.MsgRepay.newBuilder().setSender(sender).setOwner(owner).addAmount(RepayCoin).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.hard.v1beta1.MsgRepay").setValue(msgRepay.toByteString()).build());
        return msgAnys;
    }

    public static Any getKavaIncentiveUSDXMinting(String sender, String multiplier_name) {
        kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward msgClaimUSDXMintingReward = kava.incentive.v1beta1.Tx.MsgClaimUSDXMintingReward.newBuilder().setSender(sender).setMultiplierName(multiplier_name).build();
        Any msgKavaClaimUsdxAny = Any.newBuilder().setTypeUrl("/kava.incentive.v1beta1.MsgClaimUSDXMintingReward").setValue(msgClaimUSDXMintingReward.toByteString()).build();
        return msgKavaClaimUsdxAny;
    }

    public static Any getKavaIncentiveHard(String sender, ArrayList<kava.incentive.v1beta1.Tx.Selection> denoms_to_claims) {
        kava.incentive.v1beta1.Tx.MsgClaimHardReward msgClaimHardReward = kava.incentive.v1beta1.Tx.MsgClaimHardReward.newBuilder().setSender(sender).addAllDenomsToClaim(denoms_to_claims).build();
        Any msgKavaClaimHardAny = Any.newBuilder().setTypeUrl("/kava.incentive.v1beta1.MsgClaimHardReward").setValue(msgClaimHardReward.toByteString()).build();
        return msgKavaClaimHardAny;
    }

    public static Any getKavaIncentiveDelegator(String sender, ArrayList<kava.incentive.v1beta1.Tx.Selection> denoms_to_claims) {
        kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward msgClaimDelegatorReward = kava.incentive.v1beta1.Tx.MsgClaimDelegatorReward.newBuilder().setSender(sender).addAllDenomsToClaim(denoms_to_claims).build();
        Any msgKavaClaimDelegateAny = Any.newBuilder().setTypeUrl("/kava.incentive.v1beta1.MsgClaimDelegatorReward").setValue(msgClaimDelegatorReward.toByteString()).build();
        return msgKavaClaimDelegateAny;
    }

    public static Any getKavaIncentiveSwap(String sender, ArrayList<kava.incentive.v1beta1.Tx.Selection> denoms_to_claims) {
        kava.incentive.v1beta1.Tx.MsgClaimSwapReward msgClaimSwapReward = kava.incentive.v1beta1.Tx.MsgClaimSwapReward.newBuilder().setSender(sender).addAllDenomsToClaim(denoms_to_claims).build();
        Any msgKavaClaimSwapAny = Any.newBuilder().setTypeUrl("/kava.incentive.v1beta1.MsgClaimSwapReward").setValue(msgClaimSwapReward.toByteString()).build();
        return msgKavaClaimSwapAny;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaIncentiveAllReq(QueryOuterClass.QueryAccountResponse auth, String sender, String multiplier_name, BaseData baseData, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getKavaIncentiveAllMsg(sender, multiplier_name, baseData), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcKavaIncentiveAllSimulateReq(QueryOuterClass.QueryAccountResponse auth, String sender, String multiplier_name, BaseData baseData, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getKavaIncentiveAllMsg(sender, multiplier_name, baseData), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getKavaIncentiveAllMsg(String sender, String multiplier_name, BaseData baseData) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        IncentiveReward incentiveRewards = baseData.mIncentiveRewards;
        if (incentiveRewards.getMintingRewardAmount().compareTo(BigDecimal.ZERO) > 0) {
            msgAnys.add(getKavaIncentiveUSDXMinting(sender, multiplier_name));
        }
        if (incentiveRewards.getHardRewardDenoms().size() > 0) {
            ArrayList<kava.incentive.v1beta1.Tx.Selection> denoms_to_claims = new ArrayList<>();
            for (String denom: incentiveRewards.getHardRewardDenoms()) {
                denoms_to_claims.add(kava.incentive.v1beta1.Tx.Selection.newBuilder().setDenom(denom).setMultiplierName(multiplier_name).build());
            }
            msgAnys.add(getKavaIncentiveHard(sender, denoms_to_claims));
        }
        if (incentiveRewards.getDelegatorRewardDenoms().size() > 0) {
            ArrayList<kava.incentive.v1beta1.Tx.Selection> denoms_to_claims = new ArrayList<>();
            for (String denom: incentiveRewards.getDelegatorRewardDenoms()) {
                denoms_to_claims.add(kava.incentive.v1beta1.Tx.Selection.newBuilder().setDenom(denom).setMultiplierName(multiplier_name).build());
            }
            msgAnys.add(getKavaIncentiveDelegator(sender, denoms_to_claims));
        }
        if (incentiveRewards.getSwapRewardDenoms().size() > 0) {
            ArrayList<kava.incentive.v1beta1.Tx.Selection> denoms_to_claims = new ArrayList<>();
            for (String denom: incentiveRewards.getSwapRewardDenoms()) {
                denoms_to_claims.add(kava.incentive.v1beta1.Tx.Selection.newBuilder().setDenom(denom).setMultiplierName(multiplier_name).build());
            }
            msgAnys.add(getKavaIncentiveSwap(sender, denoms_to_claims));
        }
        return msgAnys;
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaCreateHTLCSwapReq(QueryOuterClass.QueryAccountResponse auth, String from, String to, ArrayList<Coin> sendCoin, long timtsStamp, String randomNumberHash, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        CoinOuterClass.Coin Amount = CoinOuterClass.Coin.newBuilder().setAmount(sendCoin.get(0).amount).setDenom(sendCoin.get(0).denom).build();
        kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap msgCreateAtomicSwap = kava.bep3.v1beta1.Tx.MsgCreateAtomicSwap.newBuilder().setFrom(from).setTo(WUtil.getDuputyKavaAddress(sendCoin.get(0).denom)).setSenderOtherChain(WUtil.getDuputyBnbAddress(sendCoin.get(0).denom)).
                setRecipientOtherChain(to).setRandomNumberHash(randomNumberHash).setTimestamp(timtsStamp).addAmount(Amount).setHeightSpan(24686).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.bep3.v1beta1.MsgCreateAtomicSwap").setValue(msgCreateAtomicSwap.toByteString()).build());

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgAnys, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcKavaClaimHTLCSwapReq(QueryOuterClass.QueryAccountResponse auth, String from, String swapId, String randomNumber, Fee fee, String memo, ECKey pKey, String chainId) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap msgClaimAtomicSwap = kava.bep3.v1beta1.Tx.MsgClaimAtomicSwap.newBuilder().setFrom(from).setSwapId(swapId).setRandomNumber(randomNumber).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/kava.bep3.v1beta1.MsgClaimAtomicSwap").setValue(msgClaimAtomicSwap.toByteString()).build());

        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgAnys, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.BroadcastTxRequest getGrpcCw20SendReq(QueryOuterClass.QueryAccountResponse auth, String fromAddress, String toAddress, String contractAddress, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignTx(auth, getCw20SendMsg(fromAddress, toAddress, contractAddress, amount), fee, memo, pKey, chainId);
    }

    public static ServiceOuterClass.SimulateRequest getGrpcCw20SendSimulateReq(QueryOuterClass.QueryAccountResponse auth, String fromAddress, String toAddress, String contractAddress, ArrayList<Coin> amount, Fee fee, String memo, ECKey pKey, String chainId) {
        return getSignSimulTx(auth, getCw20SendMsg(fromAddress, toAddress, contractAddress, amount), fee, memo, pKey, chainId);
    }

    public static ArrayList<Any> getCw20SendMsg(String fromAddress, String toAddress, String contractAddress, ArrayList<Coin> amount) {
        ArrayList<Any> msgAnys = new ArrayList<>();
        Cw20TransferReq req = new Cw20TransferReq(toAddress, amount.get(0).amount);
        String jsonData = new Gson().toJson(req);
        ByteString msg = ByteString.copyFromUtf8(jsonData);
        cosmwasm.wasm.v1.Tx.MsgExecuteContract msgExecuteContract = cosmwasm.wasm.v1.Tx.MsgExecuteContract.newBuilder().setSender(fromAddress).setContract(contractAddress).setMsg(msg).build();
        msgAnys.add(Any.newBuilder().setTypeUrl("/cosmwasm.wasm.v1.MsgExecuteContract").setValue(msgExecuteContract.toByteString()).build());
        return msgAnys;
    }

    public static TxOuterClass.TxBody getGrpcTxBodys(ArrayList<Any> msgsAny, String memo) {
        TxOuterClass.TxBody.Builder builder = TxOuterClass.TxBody.newBuilder();
        for (Any msg: msgsAny) {
            builder.addMessages(msg);
        }
        return builder.setMemo(memo).build();
    }

    public static TxOuterClass.SignerInfo getGrpcSignerInfo(QueryOuterClass.QueryAccountResponse auth, ECKey pKey) {
        Any pubKey = WKey.generateGrpcPubKeyFromPriv(auth, pKey.getPrivateKeyAsHex());
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

    public static ServiceOuterClass.BroadcastTxRequest getSignTx(QueryOuterClass.QueryAccountResponse auth, ArrayList<Any> msgAnys, Fee fee, String memo, ECKey pKey, String chainId) {
        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgAnys, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.TxRaw rawTx            = getGrpcRawTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.BroadcastTxRequest.newBuilder().setModeValue(ServiceOuterClass.BroadcastMode.BROADCAST_MODE_SYNC.getNumber()).setTxBytes(rawTx.toByteString()).build();
    }

    public static ServiceOuterClass.SimulateRequest getSignSimulTx(QueryOuterClass.QueryAccountResponse auth, ArrayList<Any> msgAnys, Fee fee, String memo, ECKey pKey, String chainId) {
        TxOuterClass.TxBody txBody          = getGrpcTxBodys(msgAnys, memo);
        TxOuterClass.SignerInfo signerInfo  = getGrpcSignerInfo(auth, pKey);
        TxOuterClass.AuthInfo authInfo      = getGrpcAuthInfo(signerInfo, fee);
        TxOuterClass.Tx simulateTx          = getGrpcSimulTx(auth, txBody, authInfo, pKey, chainId);
        return ServiceOuterClass.SimulateRequest.newBuilder().setTx(simulateTx).build();
    }

    public static byte[] getGrpcByteSingleSignature(QueryOuterClass.QueryAccountResponse auth, ECKey key, byte[] toSignByte) {
        byte[] sigData = new byte[64];
        if (auth.getAccount().getTypeUrl().contains("/injective.types.v1beta1.EthAccount") || auth.getAccount().getTypeUrl().contains("/ethermint.types.v1.EthAccount")) {
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

package wannabit.io.cosmostaion.widget.authz;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.protobuf.InvalidProtocolBufferException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import cosmos.authz.v1beta1.Authz;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzDelegateActivity;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzDetailActivity;
import wannabit.io.cosmostaion.activities.txs.wc.WalletConnectActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzExecuteInfoHolder extends RecyclerView.ViewHolder {
    private CardView mGrantLayer;
    private ImageView mGrantIcon;
    private TextView mGrantTitle;
    private TextView mGrantImportTime;
    private TextView mLimitedAmount;
    private TextView mLimitedAddress;

    private String mStakingDenom;
    private int mDivideDecimal;

    public AuthzExecuteInfoHolder(@NonNull View itemView) {
        super(itemView);
        mGrantLayer = itemView.findViewById(R.id.card_root);
        mGrantIcon = itemView.findViewById(R.id.grant_icon);
        mGrantTitle = itemView.findViewById(R.id.grant_title);
        mGrantImportTime = itemView.findViewById(R.id.grant_import_time);
        mLimitedAmount = itemView.findViewById(R.id.limited_amount);
        mLimitedAddress = itemView.findViewById(R.id.limited_address);
    }

    public void onBindGrantsInfoHolder(AuthzDetailActivity activity, BaseData baseData, ChainConfig chainConfig, int position) {
        if (position == 0) {
            onBindSendItem(activity, baseData, chainConfig, activity.getSendAuthz());
        } else if (position == 1) {
            onBindDelegateItem(activity, baseData, chainConfig, activity.getDelegateAuthz());
        } else if (position == 2) {
            onBindUndelegateItem(activity, baseData, chainConfig, activity.getUndelegateAuthz());
        } else if (position == 3) {
            onBindRedelegateItem(activity, baseData, chainConfig, activity.getRedelegateAuthz());
        } else if (position == 4) {
            onBindRewardItem(activity, chainConfig, activity.getRewardAuthz());
        } else if (position == 5) {
            onBindCommissionItem(activity, chainConfig, activity.getCommissionAuthz());
        } else if (position == 6) {
            onBindVoteItem(activity, chainConfig, activity.getVoteAuthz());
        }
    }

    private void onBindSendItem(Context c, BaseData baseData, ChainConfig chainConfig, Authz.Grant grant) {
        if (chainConfig == null) return;
        mStakingDenom = chainConfig.mainDenom();
        mDivideDecimal = WDp.getDenomDecimal(baseData, chainConfig, mStakingDenom);

        mGrantIcon.setImageResource(R.drawable.icon_authz_send);
        mGrantTitle.setText("Send");

        if (grant != null) {
            setColor(c, chainConfig, true);
            mGrantImportTime.setText(WDp.getGapTime(c, grant.getExpiration().getSeconds() * 1000));

            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                mLimitedAmount.setText("-");
                mLimitedAddress.setText("-");
            }
            if (grant.getAuthorization().getTypeUrl().contains(cosmos.bank.v1beta1.Authz.SendAuthorization.getDescriptor().getFullName())) {
                try {
                    cosmos.bank.v1beta1.Authz.SendAuthorization transAuth = cosmos.bank.v1beta1.Authz.SendAuthorization.parseFrom(grant.getAuthorization().getValue());
                    String maxAmount = getSpendMax(transAuth);
                    if (maxAmount != null) {
                        mLimitedAmount.setText(WDp.getDpAmount2(c, new BigDecimal(maxAmount), mDivideDecimal, 6));
                    } else {
                        mLimitedAmount.setText("-");
                    }
                    mLimitedAddress.setText("-");
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        } else {
            setColor(c, chainConfig, false);
        }
    }

    private void onBindDelegateItem(AuthzDetailActivity c, BaseData baseData, ChainConfig chainConfig, Authz.Grant grant) {
        if (chainConfig == null) return;
        mStakingDenom = chainConfig.mainDenom();
        mDivideDecimal = WDp.getDenomDecimal(baseData, chainConfig, mStakingDenom);

        mGrantIcon.setImageResource(R.drawable.icon_authz_staking);
        mGrantTitle.setText("Delegate");

        if (grant != null) {
            setColor(c, chainConfig, true);
            mGrantImportTime.setText(WDp.getGapTime(c, grant.getExpiration().getSeconds() * 1000));

            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                mLimitedAmount.setText("-");
                mLimitedAddress.setText("-");
            }
            if (grant.getAuthorization().getTypeUrl().contains(cosmos.staking.v1beta1.Authz.StakeAuthorization.getDescriptor().getFullName())) {
                try {
                    cosmos.staking.v1beta1.Authz.StakeAuthorization stakeAuth = cosmos.staking.v1beta1.Authz.StakeAuthorization.parseFrom(grant.getAuthorization().getValue());
                    String maxAmount = getMaxToken(stakeAuth);
                    if (maxAmount != null) {
                        mLimitedAmount.setText(WDp.getDpAmount2(c, new BigDecimal(maxAmount), mDivideDecimal, 6));
                    } else {
                        mLimitedAmount.setText("-");
                    }

                    String moniker = getMonikerName(baseData, stakeAuth);
                    if (moniker != null) {
                        mLimitedAddress.setText(moniker);
                    } else {
                        mLimitedAddress.setText("-");
                    }

                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        } else {
            setColor(c, chainConfig, false);
        }

        mGrantLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.onStartAuthzDelegate();
            }
        });
    }

    private void onBindUndelegateItem(Context c, BaseData baseData, ChainConfig chainConfig, Authz.Grant grant) {
        if (chainConfig == null) return;
        mStakingDenom = chainConfig.mainDenom();
        mDivideDecimal = WDp.getDenomDecimal(baseData, chainConfig, mStakingDenom);

        mGrantIcon.setImageResource(R.drawable.icon_authz_staking);
        mGrantTitle.setText("Undelegate");

        if (grant != null) {
            setColor(c, chainConfig, true);
            mGrantImportTime.setText(WDp.getGapTime(c, grant.getExpiration().getSeconds() * 1000));

            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                mLimitedAmount.setText("-");
                mLimitedAddress.setText("-");
            }
            if (grant.getAuthorization().getTypeUrl().contains(cosmos.staking.v1beta1.Authz.StakeAuthorization.getDescriptor().getFullName())) {
                try {
                    cosmos.staking.v1beta1.Authz.StakeAuthorization stakeAuth = cosmos.staking.v1beta1.Authz.StakeAuthorization.parseFrom(grant.getAuthorization().getValue());
                    String maxAmount = getMaxToken(stakeAuth);
                    if (maxAmount != null) {
                        mLimitedAmount.setText(WDp.getDpAmount2(c, new BigDecimal(maxAmount), mDivideDecimal, 6));
                    } else {
                        mLimitedAmount.setText("-");
                    }

                    String moniker = getMonikerName(baseData, stakeAuth);
                    if (moniker != null) {
                        mLimitedAddress.setText(moniker);
                    } else {
                        mLimitedAddress.setText("-");
                    }

                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        } else {
            setColor(c, chainConfig, false);
        }
    }

    private void onBindRedelegateItem(Context c, BaseData baseData, ChainConfig chainConfig, Authz.Grant grant) {
        if (chainConfig == null) return;
        mStakingDenom = chainConfig.mainDenom();
        mDivideDecimal = WDp.getDenomDecimal(baseData, chainConfig, mStakingDenom);

        mGrantIcon.setImageResource(R.drawable.icon_authz_staking);
        mGrantTitle.setText("Redelegate");

        if (grant != null) {
            setColor(c, chainConfig, true);
            mGrantImportTime.setText(WDp.getGapTime(c, grant.getExpiration().getSeconds() * 1000));

            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                mLimitedAmount.setText("-");
                mLimitedAddress.setText("-");
            }
            if (grant.getAuthorization().getTypeUrl().contains(cosmos.staking.v1beta1.Authz.StakeAuthorization.getDescriptor().getFullName())) {
                try {
                    cosmos.staking.v1beta1.Authz.StakeAuthorization stakeAuth = cosmos.staking.v1beta1.Authz.StakeAuthorization.parseFrom(grant.getAuthorization().getValue());
                    String maxAmount = getMaxToken(stakeAuth);
                    if (maxAmount != null) {
                        mLimitedAmount.setText(WDp.getDpAmount2(c, new BigDecimal(maxAmount), mDivideDecimal, 6));
                    } else {
                        mLimitedAmount.setText("-");
                    }

                    String moniker = getMonikerName(baseData, stakeAuth);
                    if (moniker != null) {
                        mLimitedAddress.setText(moniker);
                    } else {
                        mLimitedAddress.setText("-");
                    }

                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        } else {
            setColor(c, chainConfig, false);
        }
    }

    private void onBindRewardItem(Context c, ChainConfig chainConfig, Authz.Grant grant) {
        mGrantIcon.setImageResource(R.drawable.icon_authz_reward);
        mGrantTitle.setText("Claim Reward");

        if (grant != null) {
            setColor(c, chainConfig, true);
            mGrantImportTime.setText(WDp.getGapTime(c, grant.getExpiration().getSeconds() * 1000));
            mLimitedAmount.setText("-");
            mLimitedAddress.setText("-");
        } else {
            setColor(c, chainConfig, false);
        }
    }

    private void onBindCommissionItem(Context c, ChainConfig chainConfig, Authz.Grant grant) {
        mGrantIcon.setImageResource(R.drawable.icon_authz_commission);
        mGrantTitle.setText("Claim Commission");

        if (grant != null) {
            setColor(c, chainConfig, true);
            mGrantImportTime.setText(WDp.getGapTime(c, grant.getExpiration().getSeconds() * 1000));
            mLimitedAmount.setText("-");
            mLimitedAddress.setText("-");
        } else {
            setColor(c, chainConfig, false);
        }
    }

    private void onBindVoteItem(Context c, ChainConfig chainConfig, Authz.Grant grant) {
        mGrantIcon.setImageResource(R.drawable.icon_authz_vote);
        mGrantTitle.setText("Vote");

        if (grant != null) {
            setColor(c, chainConfig, true);
            mGrantImportTime.setText(WDp.getGapTime(c, grant.getExpiration().getSeconds() * 1000));
            mLimitedAmount.setText("-");
            mLimitedAddress.setText("-");
        } else {
            setColor(c, chainConfig, false);
        }
    }

    private void setColor(Context c, ChainConfig chainConfig, boolean isGrant) {
        if (isGrant) {
            mGrantIcon.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
            mGrantTitle.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            mGrantImportTime.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            mLimitedAmount.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            mLimitedAddress.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else {
            mGrantIcon.setColorFilter(ContextCompat.getColor(c, R.color.colorGray1), android.graphics.PorterDuff.Mode.SRC_IN);
            mGrantTitle.setTextColor(ContextCompat.getColor(c, R.color.colorGray1));
            mGrantImportTime.setText("");
            mLimitedAmount.setText("");
            mLimitedAddress.setText("");
        }
    }

    private String getSpendMax(cosmos.bank.v1beta1.Authz.SendAuthorization transAuthz) {
        if (transAuthz.getSpendLimitList().size() > 0) {
            for (CoinOuterClass.Coin coin : transAuthz.getSpendLimitList()) {
                if (coin.getDenom().equalsIgnoreCase(mStakingDenom)) {
                    return coin.getAmount();
                }
            }
        }
        return null;
    }

    private String getMaxToken(cosmos.staking.v1beta1.Authz.StakeAuthorization stakeAuthz) {
        if (stakeAuthz.hasMaxTokens()) {
            return new BigDecimal(stakeAuthz.getMaxTokens().getAmount()).toPlainString();
        }
        return null;
    }

    private String getMonikerName(BaseData baseData, cosmos.staking.v1beta1.Authz.StakeAuthorization stakeAuthz) {
        ArrayList<String> opAddresses = new ArrayList<>();
        for (String opAddress : stakeAuthz.getAllowList().getAddressList()) {
            opAddresses.add(opAddress);
        }
        for (String opAddress : stakeAuthz.getDenyList().getAddressList()) {
            opAddresses.add(opAddress);
        }
        if (opAddresses.size() == 0) {
            return null;
        }

        String moniker = "";
        for (Staking.Validator validator : baseData.mGRpcAllValidators) {
            if (validator.getOperatorAddress().equalsIgnoreCase(opAddresses.get(0))) {
                moniker = validator.getDescription().getMoniker();
            }
        }
        if (moniker.isEmpty()) {
            moniker = "known";
        }
        if (opAddresses.size() > 1) {
            return moniker + " + " + String.valueOf(opAddresses.size() - 1);
        } else {
            return moniker;
        }
    }
}

package wannabit.io.cosmostaion.widget.mainWallet;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.activities.VoteListActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameListActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;

public class WalletStarnameHolder extends BaseHolder {
    public TextView mTvIovTotal, mTvIovValue, mTvIovAvailable, mTvIovDelegated, mTvIovUnBonding, mTvIovRewards;
    public RelativeLayout mBtnStake, mBtnVote, mBtnStarname;

    public WalletStarnameHolder(@NonNull View itemView) {
        super(itemView);
        mTvIovTotal         = itemView.findViewById(R.id.iov_total_amount);
        mTvIovValue         = itemView.findViewById(R.id.iov_total_value);
        mTvIovAvailable     = itemView.findViewById(R.id.iov_available);
        mTvIovDelegated     = itemView.findViewById(R.id.iov_delegate);
        mTvIovUnBonding     = itemView.findViewById(R.id.iov_unbonding);
        mTvIovRewards       = itemView.findViewById(R.id.iov_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_iov_stake);
        mBtnVote            = itemView.findViewById(R.id.btn_iov_vote);
        mBtnStarname        = itemView.findViewById(R.id.btn_iov_name_service);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.availableAmount(denom);
        final BigDecimal delegateAmount = baseData.delegatedSumAmount();
        final BigDecimal unbondingAmount = baseData.unbondingSumAmount();
        final BigDecimal rewardAmount = baseData.rewardAmount(denom);
        final BigDecimal totalAmount = baseData.getAllMainAssetOld(denom);

        mTvIovTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvIovAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvIovDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvIovUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvIovRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvIovValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
                mainActivity.startActivity(validators);
            }
        });
        mBtnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proposals = new Intent(mainActivity, VoteListActivity.class);
                mainActivity.startActivity(proposals);
            }
        });
        mBtnStarname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, StarNameListActivity.class);
                mainActivity.startActivity(intent);
            }
        });
    }
}

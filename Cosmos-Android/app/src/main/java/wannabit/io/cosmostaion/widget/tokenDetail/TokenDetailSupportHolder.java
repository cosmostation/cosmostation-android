package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ION;

public class TokenDetailSupportHolder extends BaseHolder {
    private CardView            mAmountView;
    private TextView            mTvTotal;
    private TextView            mTvAvailable;
    private RelativeLayout      mLockedLayout;
    private TextView            mTvLocked;
    private RelativeLayout      mFrozenLayout;
    private TextView            mTvFrozen;

    private int                 dpDecimal = 6;
    private BigDecimal          mTotalAmount = BigDecimal.ZERO;
    private BigDecimal          mAvailableAmount = BigDecimal.ZERO;

    public TokenDetailSupportHolder(@NonNull View itemView) {
        super(itemView);
        mAmountView         = itemView.findViewById(R.id.card_root);
        mTvTotal            = itemView.findViewById(R.id.total_amount);
        mTvAvailable        = itemView.findViewById(R.id.available_amount);
        mLockedLayout       = itemView.findViewById(R.id.locked_layout);
        mTvLocked           = itemView.findViewById(R.id.locked_amount);
        mFrozenLayout       = itemView.findViewById(R.id.frozen_layout);
        mTvFrozen           = itemView.findViewById(R.id.frozen_amount);
    }

    public void onBindNativeTokengRPC(Context c, BaseChain baseChain, BaseData baseData, String denom) {
        if (baseChain.equals(BaseChain.OSMOSIS_MAIN)) {
            if (denom.equalsIgnoreCase(TOKEN_ION)) {
                dpDecimal = 6;

                mAvailableAmount = baseData.getAvailable(denom);
                mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
                mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
            }
        }
    }

    public void onBindPoolToken(Context c, BaseChain baseChain, BaseData baseData, String denom) {
        if (baseChain.equals(BaseChain.OSMOSIS_MAIN)) {
            dpDecimal = 18;

            mAvailableAmount = baseData.getAvailable(denom);
            mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
            mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
        }
    }

    public void onBindKavaToken(Context c, BaseData baseData, String denom) {
        dpDecimal = WUtil.getKavaCoinDecimal(denom);
        mAvailableAmount = baseData.availableAmount(denom);
        if (denom.equalsIgnoreCase(TOKEN_HARD)) {
            mAmountView.setBackgroundColor(c.getResources().getColor(R.color.colorTransBghard));
        }
        mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
        mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
    }

    public void onBindBNBTokens(Context c, BaseData baseData, String denom) {
        BnbToken bnbToken = baseData.getBnbToken(denom);
        if (bnbToken != null) {
            mLockedLayout.setVisibility(View.VISIBLE);
            mFrozenLayout.setVisibility(View.VISIBLE);
        }
        mAvailableAmount = baseData.availableAmount(denom);
        final BigDecimal lockedAmount = baseData.lockedAmount(denom);
        final BigDecimal frozenAmount = baseData.frozenAmount(denom);
        mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, 0, 8));
        mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, 0, 8));
        mTvLocked.setText(WDp.getDpAmount2(c, lockedAmount, 0, 8));
        mTvFrozen.setText(WDp.getDpAmount2(c, frozenAmount, 0, 8));
    }

    public void onBindOKTokens(Context c, BaseData baseData, String denom) {
        final OkToken okToken   = baseData.okToken(denom);
        if (okToken != null) {
            mLockedLayout.setVisibility(View.VISIBLE);
        }
        mAvailableAmount = baseData.availableAmount(denom);
        final BigDecimal lockedAmount = baseData.lockedAmount(denom);
        final BigDecimal totalAmount = mAvailableAmount.add(lockedAmount);

        mTvTotal.setText(WDp.getDpAmount2(c, totalAmount, 0, 18));
        mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, 0, 18));
        mTvLocked.setText(WDp.getDpAmount2(c, lockedAmount, 0, 18));
    }

    public void onBindBridgeToken(Context c, BaseChain baseChain, BaseData baseData, String denom) {
        if (baseChain.equals(BaseChain.SIF_MAIN)) {
            dpDecimal = WUtil.getSifCoinDecimal(denom);

            mAvailableAmount = baseData.getAvailable(denom);
            mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
            mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
        }
    }
}

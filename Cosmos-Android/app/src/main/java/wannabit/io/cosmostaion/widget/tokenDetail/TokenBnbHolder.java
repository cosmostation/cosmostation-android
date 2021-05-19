package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class TokenBnbHolder extends BaseHolder {

    private TextView        mTvBnbTotal, mTvBnbValue, mTvBnbBalance, mTvBnbLocked, mTvBnbFrozen;

    public TokenBnbHolder(View v) {
        super(v);
        mTvBnbTotal             = itemView.findViewById(R.id.dash_bnb_amount);
        mTvBnbValue             = itemView.findViewById(R.id.dash_bnb_value);
        mTvBnbBalance           = itemView.findViewById(R.id.dash_bnb_balance);
        mTvBnbLocked            = itemView.findViewById(R.id.dash_bnb_locked);
        mTvBnbFrozen            = itemView.findViewById(R.id.dash_bnb_frozen);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.availableAmount(denom);
        final BigDecimal lockedAmount = baseData.lockedAmount(denom);
        final BigDecimal frozenAmount = baseData.frozenAmount(denom);
        final BigDecimal totalAmount = availableAmount.add(lockedAmount).add(frozenAmount);

        mTvBnbTotal.setText(WDp.getDpAmount2(c, totalAmount, 0, 8));
        mTvBnbBalance.setText(WDp.getDpAmount2(c, availableAmount, 0, 8));
        mTvBnbLocked.setText(WDp.getDpAmount2(c, lockedAmount, 0, 8));
        mTvBnbFrozen.setText(WDp.getDpAmount2(c, frozenAmount, 0, 8));
        mTvBnbValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 0));

    }
}

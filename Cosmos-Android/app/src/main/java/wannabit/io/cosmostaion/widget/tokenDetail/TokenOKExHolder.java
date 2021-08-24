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

public class TokenOKExHolder extends BaseHolder {
    private TextView        mOkTotalAmount, mOkAvailable, mOkLocked, mOkDeposit, mOkWithdrawing;

    public TokenOKExHolder(View v) {
        super(v);
        mOkTotalAmount          = itemView.findViewById(R.id.ok_total_amount);
        mOkAvailable            = itemView.findViewById(R.id.ok_available);
        mOkLocked               = itemView.findViewById(R.id.ok_locked);
        mOkDeposit              = itemView.findViewById(R.id.ok_deposit);
        mOkWithdrawing          = itemView.findViewById(R.id.ok_withdrawing);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.availableAmount(denom);
        final BigDecimal lockedAmount = baseData.lockedAmount(denom);
        final BigDecimal depositAmount = baseData.okDepositAmount();
        final BigDecimal withdrawAmount = baseData.okWithdrawAmount();
        final BigDecimal totalAmount = availableAmount.add(lockedAmount).add(depositAmount).add(withdrawAmount);

        mOkTotalAmount.setText(WDp.getDpAmount2(c, totalAmount, 0, 18));
        mOkAvailable.setText(WDp.getDpAmount2(c, availableAmount, 0, 18));
        mOkLocked.setText(WDp.getDpAmount2(c, lockedAmount, 0, 18));
        mOkDeposit.setText(WDp.getDpAmount2(c, depositAmount, 0, 18));
        mOkWithdrawing.setText(WDp.getDpAmount2(c, withdrawAmount, 0, 18));

    }
}
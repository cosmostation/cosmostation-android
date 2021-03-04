package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;

public class TokenDetailBaseHolder extends BaseHolder {
    private ImageView   mIvToken;
    private TextView    mTvTokenTitle;
    private TextView    mTvTokenTotal, mTvTokenValue, mTvTokenAvailable;

    public TokenDetailBaseHolder(@NonNull View itemView) {
        super(itemView);
        mIvToken            = itemView.findViewById(R.id.token_icon);
        mTvTokenTitle       = itemView.findViewById(R.id.token_title);
        mTvTokenTotal       = itemView.findViewById(R.id.token_amount);
        mTvTokenValue       = itemView.findViewById(R.id.token_value);
        mTvTokenAvailable   = itemView.findViewById(R.id.token_available);
    }

    public void onBindKavaToken(Context context, BaseChain chain, BaseData baseData, String denom) {
        final int dpDecimal                     = WUtil.getKavaCoinDecimal(denom);
        final BigDecimal availableTokenAmount   = WDp.getAvailableCoin(baseData.mBalances, denom);
        final BigDecimal tokenTotalValue        = WDp.kavaTokenDollorValue(chain, baseData, denom, availableTokenAmount);
        final BigDecimal convertedToKava        = tokenTotalValue.divide(baseData.getLastKavaDollorTic(), 6, RoundingMode.DOWN);

        mTvTokenTitle.setText(denom.toUpperCase());
        mTvTokenAvailable.setText(WDp.getDpAmount2(context, availableTokenAmount, dpDecimal, dpDecimal));
        mTvTokenTotal.setText(WDp.getDpAmount2(context, availableTokenAmount, dpDecimal, dpDecimal));
        mTvTokenValue.setText(WDp.getValueOfKava(context, baseData, convertedToKava.movePointRight(6)));
        try {
            Picasso.get().load(KAVA_COIN_IMG_URL+denom+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mIvToken);

        } catch (Exception e) { }
    }
}

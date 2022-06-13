package wannabit.io.cosmostaion.widget.mainWallet;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.SUPPORT_MOONPAY;

public class WalletPriceHolder extends BaseHolder {
    private CardView        itemRoot;
    private TextView        itemPerPrice, itemUpDownPrice;
    private ImageView       itemUpDownImg;
    private LinearLayout    itemBuyLayer;
    private RelativeLayout  itemBuyCoinBtn;
    private TextView        itemBuyCoinTv;

    public WalletPriceHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot            = itemView.findViewById(R.id.card_root);
        itemPerPrice        = itemView.findViewById(R.id.per_price);
        itemUpDownPrice     = itemView.findViewById(R.id.dash_price_updown_tx);
        itemUpDownImg       = itemView.findViewById(R.id.ic_price_updown);
        itemBuyLayer        = itemView.findViewById(R.id.buy_layer);
        itemBuyCoinBtn      = itemView.findViewById(R.id.btn_buy_coin);
        itemBuyCoinTv       = itemView.findViewById(R.id.tv_buy_coin);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData data = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);

        itemPerPrice.setText(WDp.dpPerUserCurrencyValue(data, denom));
        itemUpDownPrice.setText(WDp.dpValueChange(data, denom));
        final BigDecimal lastUpDown = WDp.valueChange(data, denom);
        if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
            itemUpDownImg.setVisibility(View.VISIBLE);
            itemUpDownImg.setImageDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.ic_price_up));
        } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
            itemUpDownImg.setVisibility(View.VISIBLE);
            itemUpDownImg.setImageDrawable(ContextCompat.getDrawable(mainActivity,R.drawable.ic_price_down));
        } else {
            itemUpDownImg.setVisibility(View.INVISIBLE);
        }

        if (SUPPORT_MOONPAY && mainActivity.mBaseChain.equals(COSMOS_MAIN)) {
            itemBuyLayer.setVisibility(View.VISIBLE);
            itemBuyCoinTv.setText(R.string.str_buy_atom);

        } else if (SUPPORT_MOONPAY && mainActivity.mBaseChain.equals(BNB_MAIN)) {
            itemBuyLayer.setVisibility(View.VISIBLE);
            itemBuyCoinTv.setText(R.string.str_buy_bnb);

        } else if (SUPPORT_MOONPAY && mainActivity.mBaseChain.equals(KAVA_MAIN)) {
            itemBuyLayer.setVisibility(View.VISIBLE);
            itemBuyCoinTv.setText(R.string.str_buy_kava);

        } else {
            itemBuyLayer.setVisibility(View.GONE);
        }

        itemBuyCoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.mAccount.hasPrivateKey) {
                    mainActivity.onShowBuySelectFiat();
                } else {
                    mainActivity.onShowBuyWarnNoKey();
                }
            }
        });

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WUtil.getCoingekoIntent(mainActivity);
            }
        });

    }
}

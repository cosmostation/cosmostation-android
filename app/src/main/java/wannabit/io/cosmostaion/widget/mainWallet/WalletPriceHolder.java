package wannabit.io.cosmostaion.widget.mainWallet;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KUJIRA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.SUPPORT_PAY;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
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
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletPriceHolder extends BaseHolder {
    private CardView itemRoot;
    private TextView itemPerPrice, itemUpDownPrice;
    private LinearLayout itemBuyLayer;
    private RelativeLayout itemBuyCoinBtn;
    private TextView itemBuyCoinTv;

    public WalletPriceHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_root);
        itemPerPrice = itemView.findViewById(R.id.per_price);
        itemUpDownPrice = itemView.findViewById(R.id.dash_price_updown_tx);
        itemBuyLayer = itemView.findViewById(R.id.buy_layer);
        itemBuyCoinBtn = itemView.findViewById(R.id.btn_buy_coin);
        itemBuyCoinTv = itemView.findViewById(R.id.tv_buy_coin);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData data = mainActivity.getBaseDao();
        final ChainConfig chainConfig = ChainFactory.getChain(mainActivity.mBaseChain);
        final String denom = chainConfig.mainDenom();

        itemPerPrice.setText(WDp.dpPrice(data, denom));
        valueChangeStatus(mainActivity, data, denom, itemUpDownPrice);

        if (SUPPORT_PAY) {
            itemBuyLayer.setVisibility(View.VISIBLE);
            if (mainActivity.mBaseChain.equals(COSMOS_MAIN)) {
                itemBuyCoinTv.setText(R.string.str_buy_atom);
            } else if (mainActivity.mBaseChain.equals(BNB_MAIN)) {
                itemBuyCoinTv.setText(R.string.str_buy_bnb);
            } else if (mainActivity.mBaseChain.equals(KAVA_MAIN)) {
                itemBuyCoinTv.setText(R.string.str_buy_kava);
            } else if (mainActivity.mBaseChain.equals(OSMOSIS_MAIN)) {
                itemBuyCoinTv.setText(R.string.str_buy_osmosis);
            } else if (mainActivity.mBaseChain.equals(JUNO_MAIN)) {
                itemBuyCoinTv.setText(R.string.str_buy_juno);
            } else if (mainActivity.mBaseChain.equals(KUJIRA_MAIN)) {
                itemBuyCoinTv.setText(R.string.str_buy_kujira);
            }
        } else {
            itemBuyLayer.setVisibility(View.GONE);
        }

        itemBuyCoinBtn.setOnClickListener(v -> {
            if (mainActivity.mAccount.hasPrivateKey) {
                if (mainActivity.mBaseChain.equals(COSMOS_MAIN) || mainActivity.mBaseChain.equals(BNB_MAIN) || mainActivity.mBaseChain.equals(KAVA_MAIN)) {
                    mainActivity.onShowBuySelectFiat();
                } else {
                    mainActivity.onShowBuyKado();
                }
            } else {
                if (mainActivity.mBaseChain.equals(COSMOS_MAIN) || mainActivity.mBaseChain.equals(BNB_MAIN) || mainActivity.mBaseChain.equals(KAVA_MAIN)) {
                    mainActivity.onShowBuyWarnNoKeyMoonPay();
                } else {
                    mainActivity.onShowBuyWarnNoKeyKado();
                }
            }
        });

        itemRoot.setOnClickListener(v -> {
            if (!chainConfig.coingeckoLink().isEmpty())
                mainActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(chainConfig.coingeckoLink())));
        });
    }

    private void valueChangeStatus(Context c, BaseData baseData, String denom, TextView changeTxt) {
        BigDecimal lastUpDown = WDp.priceChange(baseData, denom);
        if (BigDecimal.ZERO.compareTo(lastUpDown) > 0) {
            if (baseData.getPriceColorOption() == 1) {
                changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteNo));
            } else {
                changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteYes));
            }
            changeTxt.setText(lastUpDown + "%");
        } else if (BigDecimal.ZERO.compareTo(lastUpDown) < 0) {
            if (baseData.getPriceColorOption() == 1) {
                changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteYes));
            } else {
                changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteNo));
            }
            changeTxt.setText("+" + lastUpDown + "%");
        } else {
            changeTxt.setText("");
        }
    }
}

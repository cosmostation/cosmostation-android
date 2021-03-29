package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
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
        final BigDecimal lastPrice = data.getLastPriceTic(mainActivity.mBaseChain);
        final BigDecimal lastUpDown = data.getLastPriceUpDown(mainActivity.mBaseChain);

        itemPerPrice.setText(WDp.getPriceDp(mainActivity, lastPrice, data.getCurrencySymbol(), data.getCurrency()));
        itemUpDownPrice.setText(WDp.getPriceUpDown(lastUpDown));
        if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
            itemUpDownImg.setVisibility(View.VISIBLE);
            itemUpDownImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.ic_price_up));

        } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
            itemUpDownImg.setVisibility(View.VISIBLE);
            itemUpDownImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.ic_price_down));

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
                if (mainActivity.mBaseChain.equals(COSMOS_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/cosmos")));

                } else if (mainActivity.mBaseChain.equals(IRIS_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/irisnet")));

                } else if (mainActivity.mBaseChain.equals(IOV_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/starname")));

                } else if (mainActivity.mBaseChain.equals(BNB_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/binancecoin")));

                } else if (mainActivity.mBaseChain.equals(KAVA_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/kava")));

                } else if (mainActivity.mBaseChain.equals(BAND_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/band-protocol")));

                } else if (mainActivity.mBaseChain.equals(CERTIK_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/certik")));

                } else if (mainActivity.mBaseChain.equals(SECRET_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/secret")));

                } else if (mainActivity.mBaseChain.equals(AKASH_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/akash-network")));

                } else if (mainActivity.mBaseChain.equals(OKEX_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/okexchain")));

                } else if (mainActivity.mBaseChain.equals(SENTINEL_MAIN)) {
                    mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/sentinel-group")));

                }
            }
        });

    }
}

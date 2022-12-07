package wannabit.io.cosmostaion.widget.mainWallet;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletPriceHolder extends BaseHolder {
    private RelativeLayout itemRoot, itemBuyCoinBtn;
    private TextView itemPerPrice, itemUpDownPrice;
    private LinearLayout itemBuyLayer;
    private TextView itemBuyCoinTv;

    public WalletPriceHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.price_layer);
        itemPerPrice = itemView.findViewById(R.id.per_price);
        itemUpDownPrice = itemView.findViewById(R.id.dash_price_updown_tx);
        itemBuyLayer = itemView.findViewById(R.id.buy_layer);
        itemBuyCoinBtn = itemView.findViewById(R.id.btn_buy_coin);
        itemBuyCoinTv = itemView.findViewById(R.id.tv_buy_coin);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData data = mainActivity.getBaseDao();
        final ChainConfig chainConfig = ChainFactory.getChain(mainActivity.mBaseChain);

        itemPerPrice.setText(WDp.dpPrice(data, WDp.getGeckoId(data, chainConfig)));
        WDp.valueChangeStatus(mainActivity, data, WDp.getGeckoId(data, chainConfig), itemUpDownPrice);

        if (chainConfig.moonPaySupport() || chainConfig.kadoMoneySupport()) {
            itemBuyLayer.setVisibility(View.VISIBLE);
            if (chainConfig.moonPaySupport()) {
                itemBuyCoinTv.setText(mainActivity.getString(R.string.str_buy_coin, chainConfig.mainSymbol()));
            } else {
                itemBuyCoinTv.setText(mainActivity.getString(R.string.str_buy_crypto));
            }
        } else {
            itemBuyLayer.setVisibility(View.GONE);
        }

        itemBuyCoinBtn.setOnClickListener(v -> {
            if (mainActivity.mAccount.hasPrivateKey) {
                mainActivity.onShowCryptoPay();
            } else {
                mainActivity.onShowBuyWarnNoKey();
            }
        });

        itemRoot.setOnClickListener(v -> {
            if (!chainConfig.coingeckoLink().isEmpty())
                mainActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(chainConfig.coingeckoLink())));
        });
    }
}

package wannabit.io.cosmostaion.dialog;

import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_TOKEN_IMG_URL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.binance.dex.api.client.encoding.message.NewOrderMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.wc.WalletConnectActivity;
import wannabit.io.cosmostaion.utils.WDp;

public class Dialog_Wc_Trade extends DialogFragment {

    public static Dialog_Wc_Trade newInstance(Bundle bundle) {
        Dialog_Wc_Trade frag = new Dialog_Wc_Trade();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wc_trade, null);
        TextView side_tv = view.findViewById(R.id.wc_trade_side);
        TextView symbol_tv = view.findViewById(R.id.wc_trade_symbol);
        TextView price_tv = view.findViewById(R.id.wc_trade_price);
        TextView price_denom_tv = view.findViewById(R.id.wc_trade_price_denom);
        ImageView fromCoinImg = view.findViewById(R.id.from_coin_icon);
        TextView fromCoinSymbol = view.findViewById(R.id.from_coin_symbol);
        TextView fromCoinAmount = view.findViewById(R.id.from_coin_amount);
        ImageView toCoinImg = view.findViewById(R.id.to_coin_icon);
        TextView toCoinSymbol = view.findViewById(R.id.to_coin_symbol);
        TextView toCoinAmount = view.findViewById(R.id.to_coin_amount);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);

        JsonObject json = new Gson().fromJson(getArguments().getString("param"), JsonObject.class);
        JsonObject rawMsg = new Gson().fromJson(json.getAsJsonArray("msgs").get(0), JsonObject.class);
        NewOrderMessage msg = new Gson().fromJson(json.getAsJsonArray("msgs").get(0), NewOrderMessage.class);

        String[] pair_denom = msg.getSymbol().split("_");
        symbol_tv.setText(pair_denom[0].split("-")[0]);
        price_denom_tv.setText(pair_denom[1].split("-")[0]);

        BigDecimal dpPrice = new BigDecimal(msg.getPrice()).movePointLeft(8);
        BigDecimal dpAmount = new BigDecimal(msg.getQuantity()).movePointLeft(8);
        price_tv.setText(WDp.getDpAmount2(getContext(), new BigDecimal(msg.getPrice()), 8, 8));

        if (rawMsg.get("side").getAsLong() == 1L) {
            side_tv.setText("BUY");
            side_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_bnbBuy));

            Picasso.get().load(BINANCE_TOKEN_IMG_URL + pair_denom[1].split("-")[0] + ".png")
                    .fit().placeholder(R.drawable.token_default).error(R.drawable.token_default)
                    .into(fromCoinImg);
            Picasso.get().load(BINANCE_TOKEN_IMG_URL + pair_denom[0].split("-")[0] + ".png")
                    .fit().placeholder(R.drawable.token_default).error(R.drawable.token_default)
                    .into(toCoinImg);

            fromCoinSymbol.setText(pair_denom[1].split("-")[0]);
            toCoinSymbol.setText(pair_denom[0].split("-")[0]);

            fromCoinAmount.setText(WDp.getDpAmount2(getContext(), dpAmount.multiply(dpPrice), 0, 8));
            toCoinAmount.setText(WDp.getDpAmount2(getContext(), dpAmount, 0, 8));

        } else if (rawMsg.get("side").getAsLong() == 2L) {
            side_tv.setText("SELL");
            side_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_bnbSell));

            Picasso.get().load(BINANCE_TOKEN_IMG_URL + pair_denom[0].split("-")[0] + ".png")
                    .fit().placeholder(R.drawable.token_default).error(R.drawable.token_default)
                    .into(fromCoinImg);
            Picasso.get().load(BINANCE_TOKEN_IMG_URL + pair_denom[1].split("-")[0] + ".png")
                    .fit().placeholder(R.drawable.token_default).error(R.drawable.token_default)
                    .into(toCoinImg);

            fromCoinSymbol.setText(pair_denom[0].split("-")[0]);
            toCoinSymbol.setText(pair_denom[1].split("-")[0]);

            fromCoinAmount.setText(WDp.getDpAmount2(getContext(), dpAmount, 0, 8));
            toCoinAmount.setText(WDp.getDpAmount2(getContext(), dpAmount.multiply(dpPrice), 0, 8));

        }

        btn_negative.setOnClickListener(v -> getDialog().dismiss());

        btn_positive.setOnClickListener(v -> {
            ((WalletConnectActivity) getActivity()).onBnbSign(getArguments().getLong("id"));
            dismiss();
        });

        return view;
    }
}
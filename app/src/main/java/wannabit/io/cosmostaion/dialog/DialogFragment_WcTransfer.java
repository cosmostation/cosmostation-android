package wannabit.io.cosmostaion.dialog;

import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_TOKEN_IMG_URL;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.binance.dex.api.client.encoding.message.TransferMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.WalletConnectActivity;
import wannabit.io.cosmostaion.utils.WDp;

public class DialogFragment_WcTransfer extends DialogFragment {

    public static DialogFragment_WcTransfer newInstance(Bundle bundle) {
        DialogFragment_WcTransfer frag = new DialogFragment_WcTransfer();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return inflater.inflate(R.layout.dialog_wc_transfer, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView to_address = view.findViewById(R.id.wc_recipient_address);
        ImageView send_coin_icon = view.findViewById(R.id.wc_send_coin_icon);
        TextView send_coin_symbol = view.findViewById(R.id.wc_send_coin_symbol);
        TextView send_coin_amount = view.findViewById(R.id.wc_send_amount);
        TextView to_memo = view.findViewById(R.id.wc_memo);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);

        JsonObject json = new Gson().fromJson(getArguments().getString("param"), JsonObject.class);
        TransferMessage msg =  new Gson().fromJson(json.getAsJsonArray("msgs").get(0), TransferMessage.class);

        //TODO only show single transfer.
        if (msg.getInputs() != null && msg.getInputs().size() > 0 &&
            msg.getOutputs() != null && msg.getOutputs().size() > 0) {
            to_address.setText(msg.getOutputs().get(0).getAddress());

            String dpDenom = msg.getOutputs().get(0).getCoins().get(0).getDenom().split("-")[0];
            BigDecimal dpAmount = new BigDecimal(msg.getOutputs().get(0).getCoins().get(0).getAmount()).movePointLeft(8);

            Picasso.get().load(BINANCE_TOKEN_IMG_URL +dpDenom+".png")
                    .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic)
                    .into(send_coin_icon);
            send_coin_symbol.setText(dpDenom);
            send_coin_amount.setText(WDp.getDpAmount2(getContext(), dpAmount, 0, 8));
            to_memo.setText(getArguments().getString("memo"));
        }

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WalletConnectActivity)getActivity()).onBnbSign(getArguments().getLong("id"));
                getDialog().dismiss();
            }
        });
    }
}
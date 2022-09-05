package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.binance.dex.api.client.encoding.message.CancelOrderMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.wc.WalletConnectActivity;

public class Dialog_Wc_Cancel extends DialogFragment {

    public static Dialog_Wc_Cancel newInstance(Bundle bundle) {
        Dialog_Wc_Cancel frag = new Dialog_Wc_Cancel();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wc_cancel, null);

        TextView symbol_tv = view.findViewById(R.id.wc_cancel_symbol);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);

        JsonObject json = new Gson().fromJson(getArguments().getString("param"), JsonObject.class);
        CancelOrderMessage msg = new Gson().fromJson(json.getAsJsonArray("msgs").get(0), CancelOrderMessage.class);
        symbol_tv.setText(msg.getSymbol());

        btn_negative.setOnClickListener(v -> getDialog().dismiss());

        btn_positive.setOnClickListener(v -> {
            ((WalletConnectActivity) getActivity()).onBnbSign(getArguments().getLong("id"));
            getDialog().dismiss();
        });

        return view;
    }
}
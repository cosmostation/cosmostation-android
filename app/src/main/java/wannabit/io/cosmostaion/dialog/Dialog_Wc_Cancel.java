package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.binance.dex.api.client.encoding.message.CancelOrderMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.WalletConnectActivity;

public class Dialog_Wc_Cancel extends DialogFragment {

    public static Dialog_Wc_Cancel newInstance(Bundle bundle) {
        Dialog_Wc_Cancel frag = new Dialog_Wc_Cancel();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wc_cancel, null);
        TextView symbol_tv = view.findViewById(R.id.wc_cancel_symbol);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);

        JsonObject json = new Gson().fromJson(getArguments().getString("param"), JsonObject.class);
        CancelOrderMessage msg =  new Gson().fromJson(json.getAsJsonArray("msgs").get(0), CancelOrderMessage.class);
        symbol_tv.setText(msg.getSymbol());

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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
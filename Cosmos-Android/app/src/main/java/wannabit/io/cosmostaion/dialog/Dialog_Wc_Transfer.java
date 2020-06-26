package wannabit.io.cosmostaion.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import wannabit.io.cosmostaion.R;

public class Dialog_Wc_Transfer extends DialogFragment {

    public static Dialog_Wc_Transfer newInstance(Bundle bundle) {
        Dialog_Wc_Transfer frag = new Dialog_Wc_Transfer();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wc_transfer, null);
        TextView to_address = view.findViewById(R.id.wc_recipient_address);
        ImageView send_coin_icon = view.findViewById(R.id.wc_send_coin_icon);
        TextView send_coin_symbol = view.findViewById(R.id.wc_send_coin_symbol);
        TextView send_coin_amount = view.findViewById(R.id.wc_send_amount);
        ImageView to_memo = view.findViewById(R.id.wc_memo);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);

        //TODO show trade info
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
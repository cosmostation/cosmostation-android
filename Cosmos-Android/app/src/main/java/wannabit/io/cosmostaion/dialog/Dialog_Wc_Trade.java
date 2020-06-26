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

public class Dialog_Wc_Trade extends DialogFragment {

    public static Dialog_Wc_Trade newInstance(Bundle bundle) {
        Dialog_Wc_Trade frag = new Dialog_Wc_Trade();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wc_trade, null);
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
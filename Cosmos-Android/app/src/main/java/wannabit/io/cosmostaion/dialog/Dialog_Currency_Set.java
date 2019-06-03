package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.utils.WLog;

public class Dialog_Currency_Set extends DialogFragment {

    public static Dialog_Currency_Set newInstance(Bundle bundle) {
        Dialog_Currency_Set frag = new Dialog_Currency_Set();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_currency_set, null);

        LinearLayout usd_layer = view.findViewById(R.id.usd_layer);
        LinearLayout eur_layer = view.findViewById(R.id.eur_layer);
        LinearLayout krw_layer = view.findViewById(R.id.krw_layer);
        LinearLayout jpy_layer = view.findViewById(R.id.jpy_layer);
        LinearLayout cny_layer = view.findViewById(R.id.cny_layer);
        LinearLayout btc_layer = view.findViewById(R.id.btc_layer);

        usd_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WLog.w("usd_layer");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 0);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();

            }
        });

        eur_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WLog.w("eur_layer");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 1);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();

            }
        });

        krw_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WLog.w("krw_layer");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 2);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });

        jpy_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WLog.w("jpy_layer");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 3);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();

            }
        });

        cny_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WLog.w("cny_layer");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 4);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();

            }
        });

        btc_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WLog.w("btc_layer");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 5);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}

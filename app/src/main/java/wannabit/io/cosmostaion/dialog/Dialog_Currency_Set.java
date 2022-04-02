package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;

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
        LinearLayout rub_layer = view.findViewById(R.id.rub_layer);
        LinearLayout gbp_layer = view.findViewById(R.id.gbp_layer);
        LinearLayout inr_layer = view.findViewById(R.id.inr_layer);
        LinearLayout brl_layer = view.findViewById(R.id.brl_layer);
        LinearLayout idr_layer = view.findViewById(R.id.idr_layer);
        LinearLayout dkk_layer = view.findViewById(R.id.dkk_layer);
        LinearLayout nok_layer = view.findViewById(R.id.nok_layer);
        LinearLayout sek_layer = view.findViewById(R.id.sek_layer);
        LinearLayout chf_layer = view.findViewById(R.id.chf_layer);
        LinearLayout aud_layer = view.findViewById(R.id.aud_layer);
        LinearLayout cad_layer = view.findViewById(R.id.cad_layer);
        LinearLayout myr_layer = view.findViewById(R.id.myr_layer);

        usd_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 0);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        eur_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 1);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        krw_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 2);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();
            }
        });

        jpy_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 3);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        cny_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 4);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        rub_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 5);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        gbp_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 6);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        inr_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 7);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        brl_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 8);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        idr_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 9);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        dkk_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 10);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        nok_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 11);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        sek_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 12);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        chf_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 13);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        aud_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 14);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        cad_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 15);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        myr_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", 16);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}

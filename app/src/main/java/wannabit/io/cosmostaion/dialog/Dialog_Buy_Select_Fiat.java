package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class Dialog_Buy_Select_Fiat extends DialogFragment {

    public static Dialog_Buy_Select_Fiat newInstance() {
        Dialog_Buy_Select_Fiat frag = new Dialog_Buy_Select_Fiat();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_but_select_fiat, null);

        LinearLayout usd_layer = view.findViewById(R.id.usd_layer);
        LinearLayout eur_layer = view.findViewById(R.id.eur_layer);
        LinearLayout gbp_layer = view.findViewById(R.id.gbp_layer);
        usd_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().onStartMoonpaySignature("usd");
                getDialog().dismiss();

            }
        });

        eur_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().onStartMoonpaySignature("eur");
                getDialog().dismiss();

            }
        });

        gbp_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().onStartMoonpaySignature("gbp");
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

}

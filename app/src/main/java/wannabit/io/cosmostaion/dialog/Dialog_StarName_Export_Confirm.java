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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameWalletConnectActivity;

public class Dialog_StarName_Export_Confirm extends DialogFragment {

    public static Dialog_StarName_Export_Confirm newInstance(Bundle bundle) {
        Dialog_StarName_Export_Confirm frag = new Dialog_StarName_Export_Confirm();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_starname_export_confirm, null);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);
        TextView exportMsg = view.findViewById(R.id.tv_export_msg);
        String msg = String.format(getString(R.string.str_starname_walletconnect_alert_msg2), getArguments().getString("msg"));
        exportMsg.setText(msg);


        btn_negative.setOnClickListener(v -> getDialog().dismiss());

        btn_positive.setOnClickListener(v -> {
            ((StarNameWalletConnectActivity) getActivity()).onExportAddresses(getArguments().getString("jsonData"));
            getDialog().dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }


}
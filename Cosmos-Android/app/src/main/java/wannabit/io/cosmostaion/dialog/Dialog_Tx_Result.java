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
import android.widget.TextView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseActivity;

public class Dialog_Tx_Result extends DialogFragment {

    public static Dialog_Tx_Result newInstance(Bundle bundle) {
        Dialog_Tx_Result frag = new Dialog_Tx_Result();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_tx_result, null);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);
        TextView tx_hash    = view.findViewById(R.id.result_hash);
        tx_hash.setText(getArguments().getString("hash"));

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PasswordCheckActivity)getActivity()).onStartMainActivity();
                getDialog().dismiss();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).getBaseDao().setUsingFingerprint(true);
                ((PasswordCheckActivity)getActivity()).onStartMainActivity();
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}

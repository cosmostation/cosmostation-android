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
import wannabit.io.cosmostaion.activities.HtlcResultActivity;

public class Dialog_Htlc_Error extends DialogFragment {

    public static Dialog_Htlc_Error newInstance(Bundle bundle) {
        Dialog_Htlc_Error frag = new Dialog_Htlc_Error();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_htlc_error, null);
        TextView msg = view.findViewById(R.id.dialog_msg);
        TextView error = view.findViewById(R.id.dialog_error);

        msg.setText(getArguments().getString("msg"));
        error.setText(getArguments().getString("error"));

        Button btn_negative = view.findViewById(R.id.negativeButton);
        btn_negative.setOnClickListener(v -> {
            getTopActivity().onFinishWithError();
            dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private HtlcResultActivity getTopActivity() {
        return (HtlcResultActivity) getActivity();
    }
}
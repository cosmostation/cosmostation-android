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
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;

public class Dialog_Htlc_Warning extends DialogFragment {

    public static Dialog_Htlc_Warning newInstance() {
        Dialog_Htlc_Warning frag = new Dialog_Htlc_Warning();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_htlc_warning, null);
        Button btn_negative = view.findViewById(R.id.negativeButton);
        Button btn_positive = view.findViewById(R.id.positiveButton);

        btn_negative.setOnClickListener(v -> dismiss());

        btn_positive.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
            dismiss();

        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }


}

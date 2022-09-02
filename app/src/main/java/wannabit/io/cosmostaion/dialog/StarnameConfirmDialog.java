package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;

public class StarnameConfirmDialog extends DialogFragment {

    public static StarnameConfirmDialog newInstance(Bundle bundle) {
        StarnameConfirmDialog frag = new StarnameConfirmDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_starname_confirm, null);
        TextView starname_info = view.findViewById(R.id.starname_info);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_posi = view.findViewById(R.id.btn_posi);

        starname_info.setText(String.format(getString(R.string.str_starname_confirm_msg), getArguments().getString("starname"), getArguments().getString("originAddress")));
        btn_negative.setOnClickListener(v -> getDialog().dismiss());

        btn_posi.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("starname", getArguments().getString("starname"));
            resultIntent.putExtra("originAddress", getArguments().getString("originAddress"));
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
            getDialog().dismiss();
        });

        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).setCancelable(true).create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        return dialog;
    }
}
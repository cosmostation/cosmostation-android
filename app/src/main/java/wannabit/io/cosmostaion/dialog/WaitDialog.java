package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;


public class WaitDialog extends DialogFragment {

    public static WaitDialog newInstance() {
        WaitDialog frag = new WaitDialog();
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wait, null);

        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).setCancelable(true).create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        dialog.getWindow().setDimAmount(0.2f);
        return dialog;
    }

}
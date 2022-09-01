package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        setCancelable(true);
        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        dialog.getWindow().setDimAmount(0.2f);
        return dialog;
    }

}
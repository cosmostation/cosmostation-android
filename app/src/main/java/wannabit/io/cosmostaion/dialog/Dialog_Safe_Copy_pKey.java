package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PrivateKeyCheckActivity;

public class Dialog_Safe_Copy_pKey extends DialogFragment {

    public static Dialog_Safe_Copy_pKey newInstance() {
        return new Dialog_Safe_Copy_pKey();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_safe_copy_pkey, null);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);

        btn_negative.setOnClickListener(v -> {
            ((PrivateKeyCheckActivity) getActivity()).onRawCopy();
            getDialog().dismiss();
        });

        btn_positive.setOnClickListener(v -> {
            ((PrivateKeyCheckActivity) getActivity()).onSafeCopy();
            getDialog().dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}

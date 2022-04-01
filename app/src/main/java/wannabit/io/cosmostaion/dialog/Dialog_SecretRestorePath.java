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
import wannabit.io.cosmostaion.activities.RestoreActivity;

public class Dialog_SecretRestorePath extends DialogFragment {

    private LinearLayout mOldPath, mNewPath;

    public static Dialog_SecretRestorePath newInstance(Bundle bundle) {
        Dialog_SecretRestorePath frag = new Dialog_SecretRestorePath();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_secret_restore_path, null);

        mOldPath = view.findViewById(R.id.old_path);
        mNewPath = view.findViewById(R.id.new_path);

        mOldPath.setOnClickListener(v -> {
            ((RestoreActivity) getActivity()).onUsingCustomPath(0);
            getDialog().dismiss();
        });

        mNewPath.setOnClickListener(v -> {
            ((RestoreActivity) getActivity()).onUsingCustomPath(1);
            getDialog().dismiss();
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}

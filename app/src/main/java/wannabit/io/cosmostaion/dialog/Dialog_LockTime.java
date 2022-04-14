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
import wannabit.io.cosmostaion.activities.AppLockSetActivity;

public class Dialog_LockTime extends DialogFragment {

    public static Dialog_LockTime newInstance() {
        return new Dialog_LockTime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_locktime, null);

        LinearLayout lockTime_immediately = view.findViewById(R.id.lockTime_immediately);
        LinearLayout lockTime_10sec = view.findViewById(R.id.lockTime_10sec);
        LinearLayout lockTime_30sec = view.findViewById(R.id.lockTime_30sec);
        LinearLayout lockTime_60sec = view.findViewById(R.id.lockTime_60sec);

        lockTime_immediately.setOnClickListener(v -> {
            ((AppLockSetActivity) getActivity()).onUpdateLockTime(0);
            dismiss();

        });

        lockTime_10sec.setOnClickListener(v -> {
            ((AppLockSetActivity) getActivity()).onUpdateLockTime(1);
            dismiss();

        });

        lockTime_30sec.setOnClickListener(v -> {
            ((AppLockSetActivity) getActivity()).onUpdateLockTime(2);
            dismiss();
        });

        lockTime_60sec.setOnClickListener(v -> {
            ((AppLockSetActivity) getActivity()).onUpdateLockTime(3);
            dismiss();

        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}

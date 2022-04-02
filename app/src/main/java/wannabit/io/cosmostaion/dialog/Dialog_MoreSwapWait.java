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
import wannabit.io.cosmostaion.activities.HtlcResultActivity;

public class Dialog_MoreSwapWait extends DialogFragment {

    public static Dialog_MoreSwapWait newInstance(Bundle bundle) {
        Dialog_MoreSwapWait frag = new Dialog_MoreSwapWait();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_more_swap_wait, null);
        Button btn_negative = view.findViewById(R.id.negativeButton);
        Button btn_positive = view.findViewById(R.id.positiveButton);

        btn_negative.setOnClickListener(v -> {
            getTopActivity().onFinishWithError();
            dismiss();
        });

        btn_positive.setOnClickListener(v -> {
            getTopActivity().onWaitSwapMore();
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
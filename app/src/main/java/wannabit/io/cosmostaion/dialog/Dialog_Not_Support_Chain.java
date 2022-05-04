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

public class Dialog_Not_Support_Chain extends DialogFragment {

    public static Dialog_Not_Support_Chain newInstance(Bundle bundle) {
        Dialog_Not_Support_Chain frag = new Dialog_Not_Support_Chain();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_empty_chain, null);
        TextView errorMsg = view.findViewById(R.id.error_msg);
        Button btn_negative = view.findViewById(R.id.btn_nega);

        errorMsg.setText(String.format(getActivity().getString(R.string.str_error_not_support_msg),
                getArguments().getString("chainId")));
        btn_negative.setOnClickListener(v -> {
            getDialog().dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}

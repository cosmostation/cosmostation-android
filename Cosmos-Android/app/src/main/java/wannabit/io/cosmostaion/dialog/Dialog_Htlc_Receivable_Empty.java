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

public class Dialog_Htlc_Receivable_Empty extends DialogFragment {

    public static Dialog_Htlc_Receivable_Empty newInstance(Bundle bundle) {
        Dialog_Htlc_Receivable_Empty frag = new Dialog_Htlc_Receivable_Empty();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_htlc_receivable_empty, null);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        TextView title = view.findViewById(R.id.dialog_title);
        TextView msg = view.findViewById(R.id.dialog_msg);

        title.setText(getArguments().getString("title"));
        msg.setText(getArguments().getString("msg"));

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
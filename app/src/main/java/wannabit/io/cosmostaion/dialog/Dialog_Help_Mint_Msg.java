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

public class Dialog_Help_Mint_Msg extends DialogFragment {

    public static Dialog_Help_Mint_Msg newInstance(Bundle bundle) {
        Dialog_Help_Mint_Msg frag = new Dialog_Help_Mint_Msg();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_help_mint_msg, null);
        TextView msg1 = view.findViewById(R.id.dialog_msg1);
        TextView msg2 = view.findViewById(R.id.dialog_msg2);
        TextView msg3 = view.findViewById(R.id.dialog_msg3);
        TextView msg4 = view.findViewById(R.id.dialog_msg4);
        msg1.setText(getArguments().getString("msg1"));
        msg2.setText(getArguments().getString("msg2"));
        msg3.setText(getArguments().getString("msg3"));
        msg4.setText(getArguments().getString("msg4"));

        Button btn_negative = view.findViewById(R.id.negativeButton);
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}

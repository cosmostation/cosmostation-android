package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;

public class Dialog_Fee_Description extends DialogFragment {

    public static Dialog_Fee_Description newInstance(Bundle bundle) {
        Dialog_Fee_Description frag = new Dialog_Fee_Description();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fee_description, null);
        TextView title = view.findViewById(R.id.fee_description_title);
        TextView msg = view.findViewById(R.id.fee_description_msg);
        Button btn_negative = view.findViewById(R.id.btn_nega);

        if (getArguments().getInt("speed", 0) == 0) {
            title.setText(getString(R.string.str_fee_description_title_0));
            msg.setText(getString(R.string.str_fee_description_msg_0));

        } else if (getArguments().getInt("speed", 0) == 1) {
            title.setText(getString(R.string.str_fee_description_title_1));
            msg.setText(getString(R.string.str_fee_description_msg_1));

        } else {
            title.setText(getString(R.string.str_fee_description_title_2));
            msg.setText(getString(R.string.str_fee_description_msg_2));

        }
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
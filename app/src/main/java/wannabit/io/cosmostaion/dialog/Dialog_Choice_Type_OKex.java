package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.RestoreKeyActivity;

public class Dialog_Choice_Type_OKex extends DialogFragment {

    public static Dialog_Choice_Type_OKex newInstance() {
        Dialog_Choice_Type_OKex frag = new Dialog_Choice_Type_OKex();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choice_type_okex, null);
        LinearLayout mOldAddress = view.findViewById(R.id.old_address);
        LinearLayout mNewAddress = view.findViewById(R.id.new_address);

        mOldAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RestoreKeyActivity) getActivity()).onCheckOecAddressType(0);
                getDialog().dismiss();
            }
        });

        mNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RestoreKeyActivity) getActivity()).onCheckOecAddressType(1);
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
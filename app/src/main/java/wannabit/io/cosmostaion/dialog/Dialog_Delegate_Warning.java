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
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;

public class Dialog_Delegate_Warning extends DialogFragment {

    public static Dialog_Delegate_Warning newInstance(Bundle bundle) {
        Dialog_Delegate_Warning frag = new Dialog_Delegate_Warning();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delegate_warn, null);
        ImageView dateImg = view.findViewById(R.id.date_img);
        Button btn_negative = view.findViewById(R.id.negativeButton);
        Button btn_positive = view.findViewById(R.id.positiveButton);

        int dpDay = getArguments().getInt("day", 21);
        switch (dpDay) {
            case 21:
                dateImg.setImageResource(R.drawable.img_delegate_warning);
                break;
            case 3:
                dateImg.setImageResource(R.drawable.img_delegate_3_warning);
                break;
            case 14:
                dateImg.setImageResource(R.drawable.img_delegate_14_warning);
                break;
            case 28:
                dateImg.setImageResource(R.drawable.img_delegate_28_warning);
                break;
            case 7:
                dateImg.setImageResource(R.drawable.img_delegate_7_warning);
                break;
        }


        btn_negative.setOnClickListener(v -> dismiss());

        btn_positive.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
            dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}

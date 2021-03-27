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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delegate_warn, null);
        ImageView dateImg = view.findViewById(R.id.date_img);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);

        int dpDay = getArguments().getInt("day", 21);
        if (dpDay == 21) {
            dateImg.setImageDrawable(getResources().getDrawable(R.drawable.img_delegate_warning));
        } else if (dpDay == 3) {
            dateImg.setImageDrawable(getResources().getDrawable(R.drawable.img_delegate_3_warning));
        } else if (dpDay == 14) {
            dateImg.setImageDrawable(getResources().getDrawable(R.drawable.img_delegate_14_warning));
        } else if (dpDay == 28) {
            dateImg.setImageDrawable(getResources().getDrawable(R.drawable.img_delegate_14_warning));
        }


        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }


}

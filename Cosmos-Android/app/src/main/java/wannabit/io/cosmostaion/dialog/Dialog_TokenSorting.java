package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import wannabit.io.cosmostaion.R;

import static wannabit.io.cosmostaion.fragment.MainTokensFragment.ORDER_AMOUNT;
import static wannabit.io.cosmostaion.fragment.MainTokensFragment.ORDER_NAME;
import static wannabit.io.cosmostaion.fragment.MainTokensFragment.ORDER_VALUE;

public class Dialog_TokenSorting extends BottomSheetDialogFragment {

    public static Dialog_TokenSorting getInstance() {
        return new Dialog_TokenSorting();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_token_sorting, container, false);
        Button name      = view.findViewById(R.id.btn_name);
        Button amount      = view.findViewById(R.id.btn_amount);
        Button value      = view.findViewById(R.id.btn_value);


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("sorting", ORDER_NAME);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();
            }
        });

        amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("sorting", ORDER_AMOUNT);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();
            }
        });

        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("sorting", ORDER_VALUE);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();
            }
        });

        return view;
    }

}
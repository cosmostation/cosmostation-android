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

public class Dialog_My_ValidatorSorting extends BottomSheetDialogFragment {

    public static Dialog_My_ValidatorSorting getInstance() {
        return new Dialog_My_ValidatorSorting();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_my_validator_sorting, container, false);
        Button name = view.findViewById(R.id.btn_name);
        Button delegated = view.findViewById(R.id.btn_delegated);
        Button reward = view.findViewById(R.id.btn_reward);

        name.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("sorting", 0);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
            dismiss();
        });

        delegated.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("sorting", 1);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
            dismiss();
        });

        reward.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("sorting", 2);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
            dismiss();
        });

        return view;
    }

}

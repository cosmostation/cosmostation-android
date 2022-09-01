package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;

public class NumberPickerDialog extends DialogFragment {
    public SelectListener selectListener;

    public static NumberPickerDialog newInstance(Bundle bundle) {
        NumberPickerDialog frag = new NumberPickerDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_number_picker, null);

        Button btnCancel = view.findViewById(R.id.btn_nega);
        Button btnConfirm = view.findViewById(R.id.btn_posi);

        final NumberPicker numberPicker = view.findViewById(R.id.numberPicker);

        btnCancel.setOnClickListener(view12 -> getDialog().dismiss());

        btnConfirm.setOnClickListener(view1 -> {
            if (selectListener != null) {
                selectListener.onSelectValue(numberPicker.getValue());
            }
            getDialog().dismiss();
        });

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(9);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        setCancelable(false);
        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        return dialog;
    }

    public interface SelectListener {
        void onSelectValue(int value);
    }
}

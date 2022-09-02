package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AccountDetailActivity;
import wannabit.io.cosmostaion.activities.setting.MnemonicDetailActivity;

public class ChangeNickNameDialog extends DialogFragment {

    private TextView mDialogTitle;
    private Button btn_nega, btn_posi;
    private EditText mNameInput;

    public static ChangeNickNameDialog newInstance(Bundle bundle) {
        ChangeNickNameDialog frag = new ChangeNickNameDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_nickname, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        btn_nega = view.findViewById(R.id.btn_nega);
        btn_posi = view.findViewById(R.id.btn_posi);
        mNameInput = view.findViewById(R.id.et_nickname);

        String title = getString(getArguments().getInt("title"));
        mDialogTitle.setText(title);
        if (!TextUtils.isEmpty(getArguments().getString("name")))
            mNameInput.setText(getArguments().getString("name"));

        btn_nega.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) mNameInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(mNameInput.getWindowToken(), 0);
            }
            getDialog().dismiss();
        });

        btn_posi.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) mNameInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(mNameInput.getWindowToken(), 0);
            }
            if (!TextUtils.isEmpty(mNameInput.getText().toString().trim())) {
                if (title.equalsIgnoreCase(getString(R.string.str_change_mnemonic_nickname))) {
                    ((MnemonicDetailActivity) getActivity()).onChangeNickName(mNameInput.getText().toString().trim());
                } else {
                    ((AccountDetailActivity) getActivity()).onChangeNickName(mNameInput.getText().toString().trim());
                }
            }
            getDialog().dismiss();
        });

        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).setCancelable(true).create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        return dialog;
    }
}

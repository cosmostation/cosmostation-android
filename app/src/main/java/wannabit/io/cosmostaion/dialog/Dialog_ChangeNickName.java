package wannabit.io.cosmostaion.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AccountDetailActivity;

public class Dialog_ChangeNickName extends DialogFragment {

    private Button btn_nega, btn_posi;
    private EditText mNameInput;


    public static Dialog_ChangeNickName newInstance(Bundle bundle) {
        Dialog_ChangeNickName frag = new Dialog_ChangeNickName();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_nickname, null);
        btn_nega = view.findViewById(R.id.btn_nega);
        btn_posi = view.findViewById(R.id.btn_posi);
        mNameInput = view.findViewById(R.id.et_nickname);

        if (!TextUtils.isEmpty(getArguments().getString("name")))
            mNameInput.setText(getArguments().getString("name"));

        btn_nega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) mNameInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(mNameInput.getWindowToken(), 0);
                }
                getDialog().dismiss();
            }
        });

        btn_posi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) mNameInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(mNameInput.getWindowToken(), 0);
                }
                if (!TextUtils.isEmpty(mNameInput.getText().toString().trim())) {
                    ((AccountDetailActivity) getActivity()).onChangeNickName(mNameInput.getText().toString().trim());
                }
                getDialog().dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }


}

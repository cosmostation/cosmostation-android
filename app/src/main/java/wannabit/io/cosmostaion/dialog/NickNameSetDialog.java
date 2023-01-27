package wannabit.io.cosmostaion.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.MWords;

public class NickNameSetDialog extends DialogFragment {
    public NickNameSetListener listener = null;
    public static int ACCOUNT_CHANGE_NICKNAME = 8507;
    public static int MNEMONIC_CHANGE_NICKNAME = 8508;
    public static int MNEMONIC_CREATE_VALUE = 8509;

    public final static String CHANGE_NICK_NAME_BUNDLE_KEY = "changeNickName";

    private TextView mDialogTitle;
    private Button btn_nega, btn_posi;
    private EditText mNameInput;

    private int keyValue;

    public static NickNameSetDialog newInstance(Bundle bundle) {
        NickNameSetDialog dialog = new NickNameSetDialog();
        dialog.setArguments(bundle);
        dialog.setCancelable(false);
        return dialog;
    }

    public void setNickNameListener(NickNameSetListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_nickname, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        btn_nega = view.findViewById(R.id.btn_nega);
        btn_posi = view.findViewById(R.id.btn_posi);
        mNameInput = view.findViewById(R.id.et_nickname);

        keyValue = getArguments().getInt(CHANGE_NICK_NAME_BUNDLE_KEY);
        if (keyValue == ACCOUNT_CHANGE_NICKNAME) {
            mDialogTitle.setText(getString(R.string.str_change_account_nickname));
            Account account = getSActivity().getBaseDao().onSelectAccount(String.valueOf(getArguments().getLong("id")));
            mNameInput.setText(account.getName(getActivity()));
        } else if (keyValue == MNEMONIC_CHANGE_NICKNAME) {
            mDialogTitle.setText(getString(R.string.str_change_mnemonic_nickname));
            MWords mWords = getSActivity().getBaseDao().onSelectMnemonicById(getArguments().getLong("id"));
            mNameInput.setText(mWords.getName());
        } else {
            mDialogTitle.setText(getString(R.string.str_set_mnemonic_nickname));
        }

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mNameInput.getText().toString())) {
                    mNameInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_vote_voted));
                } else {
                    mNameInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_red));
                }
            }
        });

        btn_nega.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) mNameInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(mNameInput.getWindowToken(), 0);
            }
            dismiss();
        });

        btn_posi.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) mNameInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(mNameInput.getWindowToken(), 0);
            }

            if (!TextUtils.isEmpty(mNameInput.getText().toString()) && listener != null) {
                listener.confirm(String.valueOf(mNameInput.getText()).trim());
                dismiss();
            } else {
                mNameInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_red));
            }
        });
        return view;
    }

    public interface NickNameSetListener {
        void confirm(String nickName);
    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }
}

package wannabit.io.cosmostaion.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AccountDetailActivity;
import wannabit.io.cosmostaion.activities.setting.MnemonicCreateActivity;
import wannabit.io.cosmostaion.activities.setting.MnemonicDetailActivity;
import wannabit.io.cosmostaion.activities.setting.WalletDeriveActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dao.MWords;

public class ChangeNickNameDialog extends DialogFragment {

    public final static String CHANGE_NICK_NAME_BUNDLE_KEY = "changeNickName";

    private TextView mDialogTitle;
    private Button btn_nega, btn_posi;
    private EditText mNameInput;
    private MWords mWords;

    public static ChangeNickNameDialog newInstance(Bundle bundle) {
        ChangeNickNameDialog frag = new ChangeNickNameDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_nickname, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        btn_nega = view.findViewById(R.id.btn_nega);
        btn_posi = view.findViewById(R.id.btn_posi);
        mNameInput = view.findViewById(R.id.et_nickname);

        String title = getString(getArguments().getInt("title"));
        mDialogTitle.setText(title);
        if (TextUtils.isEmpty(getArguments().getString("name"))) {
            mNameInput.setText(getString(R.string.str_my_wallet) + getArguments().getLong("id"));
        } else {
            mNameInput.setText(getArguments().getString("name"));
        }

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
            if (!TextUtils.isEmpty(String.valueOf(mNameInput.getText()).trim())) {
                mWords = getSActivity().getBaseDao().onSelectMnemonicById(getArguments().getLong("id"));
                mWords.nickName = String.valueOf(mNameInput.getText()).trim();
                getSActivity().getBaseDao().onUpdateMnemonic(mWords);

                Intent checkIntent = new Intent(getActivity(), WalletDeriveActivity.class);
                checkIntent.putExtra("id", getArguments().getLong("id"));
                startActivity(checkIntent);
            }
            dismiss();
        });

        return view;
    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }
}

package wannabit.io.cosmostaion.fragment.chains.kava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.bitcoinj.crypto.MnemonicCode;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.WithDrawPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Mnemonics_Warning;
import wannabit.io.cosmostaion.utils.WUtil;

public class WithdrawPoolStep1Fragment extends BaseFragment implements View.OnClickListener{

    public final static int AGAIN_MEMO = 9500;

    private EditText mMemo;
    private TextView mMemoCnt;

    private Button mBeforeBtn, mNextBtn;

    public static WithdrawPoolStep1Fragment newInstance(Bundle bundle) {
        WithdrawPoolStep1Fragment fragment = new WithdrawPoolStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tx_step_memo, container, false);
        mMemo       = rootView.findViewById(R.id.et_memo);
        mMemoCnt    = rootView.findViewById(R.id.tv_memoCnt);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        mMemoCnt.setText("0" + "/" + WUtil.getMaxMemoSize(getSActivity().mBaseChain) + " byte");

        mMemo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String memo = mMemo.getText().toString().trim();
                if (WUtil.getCharSize(memo) < WUtil.getMaxMemoSize(getSActivity().mBaseChain)) {
                    mMemo.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mMemoCnt.setTextColor(getResources().getColor(R.color.colorGray1));
                } else {
                    mMemo.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mMemoCnt.setTextColor(getResources().getColor(R.color.colorRed));
                }
                mMemoCnt.setText("" + WUtil.getCharSize(memo) + "/" + WUtil.getMaxMemoSize(getSActivity().mBaseChain) + " byte");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String memo = mMemo.getText().toString().trim();
                if (WUtil.getCharSize(memo) < WUtil.getMaxMemoSize(getSActivity().mBaseChain)) {
                    mMemo.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mMemoCnt.setTextColor(getResources().getColor(R.color.colorGray1));
                } else {
                    mMemo.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mMemoCnt.setTextColor(getResources().getColor(R.color.colorRed));
                }
                mMemoCnt.setText("" + WUtil.getCharSize(memo) + "/" + WUtil.getMaxMemoSize(getSActivity().mBaseChain) + " byte");
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            String memo = mMemo.getText().toString().trim();
            if (WUtil.getCharSize(memo) < WUtil.getMaxMemoSize(getSActivity().mBaseChain)) {
                if (!isMemohasMenomic(memo)) {
                    getSActivity().mTxMemo = mMemo.getText().toString().trim();
                    getSActivity().onNextStep();
                } else {
                    Dialog_Mnemonics_Warning warning = Dialog_Mnemonics_Warning.newInstance();
                    warning.setCancelable(true);
                    warning.setTargetFragment(this, AGAIN_MEMO);
                    getFragmentManager().beginTransaction().add(warning, "dialog").commitNowAllowingStateLoss();
                }
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_memo, Toast.LENGTH_SHORT).show();
            }

        }

    }

    private WithDrawPoolActivity getSActivity() { return (WithDrawPoolActivity)getBaseActivity(); }

    public boolean isMemohasMenomic(String memo) {
        Boolean result = false;
        int matchedCnt = 0;
        ArrayList<String> mAllMnemonic = new ArrayList<String>(MnemonicCode.INSTANCE.getWordList());
        String userMemo = memo.replace(" ", "");

        for (int i = 0; i < mAllMnemonic.size(); i++) {
            if (userMemo.contains(mAllMnemonic.get(i))) {
                matchedCnt++;
            }
        }
        if (matchedCnt > 10) {
            result = true;
        }

        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == AGAIN_MEMO && resultCode == Activity.RESULT_OK) {
            if(data.getIntExtra("memo" , -1) ==0 ){
                mMemo.setText("");
            }else if(data.getIntExtra("memo" , -1) == 1){
                getSActivity().mTxMemo = mMemo.getText().toString().trim();
                getSActivity().onNextStep();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

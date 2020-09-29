package wannabit.io.cosmostaion.fragment.chains.ok;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.ok.StakeDepositActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WUtil;

public class StakeDepositFragmentStep1 extends BaseFragment implements View.OnClickListener {

    private EditText mMemo;
    private TextView mMemoCnt;

    private Button mBeforeBtn, mNextBtn;

    public static StakeDepositFragmentStep1 newInstance(Bundle bundle) {
        StakeDepositFragmentStep1 fragment = new StakeDepositFragmentStep1();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
    public void onRefreshTab() {
        super.onRefreshTab();
    }


    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            String memo = mMemo.getText().toString().trim();
            if (WUtil.getCharSize(memo) < WUtil.getMaxMemoSize(getSActivity().mBaseChain)) {
                getSActivity().mMemo = mMemo.getText().toString().trim();
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_memo, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private StakeDepositActivity getSActivity() {
        return (StakeDepositActivity)getBaseActivity();
    }
}


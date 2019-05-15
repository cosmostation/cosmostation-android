package wannabit.io.cosmostaion.fragment;

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
import wannabit.io.cosmostaion.activities.RewardAddressChangeActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class RewardAddressChangeStep1Fragment extends BaseFragment implements View.OnClickListener {

    private EditText mMemo;
    private TextView mMemoCnt;
    private Button mBeforeBtn, mNextBtn;

    public static RewardAddressChangeStep1Fragment newInstance(Bundle bundle) {
        RewardAddressChangeStep1Fragment fragment = new RewardAddressChangeStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reward_address_change_step1, container, false);
        mMemo       = rootView.findViewById(R.id.et_memo);
        mMemoCnt    = rootView.findViewById(R.id.tv_memoCnt);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        mMemo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int memoSize = mMemo.getText().toString().trim().length();
                if(memoSize <= 256) {
                    mMemo.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mMemoCnt.setTextColor(getResources().getColor(R.color.colorGray1));
                } else {
                    mMemo.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mMemoCnt.setTextColor(getResources().getColor(R.color.colorRed));
                }
                mMemoCnt.setText("" + memoSize + "/256");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if(mMemo.getText().toString().trim().length() <= 256) {
                getSActivity().mMemo = mMemo.getText().toString().trim();
                getSActivity().onNextStep();

            } else {
                Toast.makeText(getContext(), R.string.error_invalid_memo, Toast.LENGTH_SHORT).show();
            }

        }
    }



    private RewardAddressChangeActivity getSActivity() {
        return (RewardAddressChangeActivity)getBaseActivity();
    }


}
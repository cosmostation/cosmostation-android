package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class DelegateStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancel, mNextBtn;
    private EditText mAmountInput;
    private TextView mAvailableAmount;


    private String mToDelegateAmount;


    public static DelegateStep0Fragment newInstance(Bundle bundle) {
        DelegateStep0Fragment fragment = new DelegateStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delegate_step0, container, false);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mAmountInput = rootView.findViewById(R.id.et_amount_coin);
        mAvailableAmount = rootView.findViewById(R.id.tv_max_coin);

        mNextBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);


        mAmountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
//                WLog.w("amount " + s.toString().trim());
//                mToSendCoins.get(position).amount = s.toString().trim();
            }
        });
        onInitView();
        return rootView;
    }

    private void onInitView() {
        mAvailableAmount.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getAtomBalance(), 6));

    }



    @Override
    public void onClick(View v) {
        if (v.equals(mCancel)) {
            //TODO add ask dialog
            getSActivity().finish();

        } else if (v.equals(mNextBtn)) {
            if(isValidateDelegateAmount()) {
                getSActivity().mToDelegateAmount = mAmountInput.getText().toString().trim();
                getSActivity().onNextStep();
            }

        }

    }

    //TODO check add validatae logic
    private boolean isValidateDelegateAmount() {
        if(TextUtils.isEmpty(mAmountInput.getText().toString().trim())) {
            return false;
        }

        return true;
    }


    private DelegateActivity getSActivity() {
        return (DelegateActivity)getBaseActivity();
    }
}

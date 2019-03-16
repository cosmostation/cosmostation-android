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
import android.widget.Toast;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.UndelegateActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class UndelegateStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancel, mNextBtn;
    private EditText mAmountInput;
    private TextView mAvailableAmount;
    private TextView    mAtomTitle;

    public static UndelegateStep0Fragment newInstance(Bundle bundle) {
        UndelegateStep0Fragment fragment = new UndelegateStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_undelegate_step0, container, false);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mAmountInput = rootView.findViewById(R.id.et_amount_coin);
        mAvailableAmount = rootView.findViewById(R.id.tv_max_coin);
        mAtomTitle = rootView.findViewById(R.id.tv_symbol_coin);
        mCancel.setOnClickListener(this);
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
        mAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
        if(getSActivity().mBondingState != null)
            mAvailableAmount.setText(WDp.getDpAmount(getContext(), getSActivity().mBondingState.shares, 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if(isValidateDelegateAmount()) {
                getSActivity().mUnDelegateAmount = mAmountInput.getText().toString().trim();
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
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

    private UndelegateActivity getSActivity() {
        return (UndelegateActivity)getBaseActivity();
    }
}

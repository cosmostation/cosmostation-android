package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.DelegateActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class DelegateStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button      mCancel, mNextBtn;
    private EditText    mAmountInput;
    private TextView    mAvailableAmount;
    private TextView    mAtomTitle, mTvError;

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
        mAtomTitle = rootView.findViewById(R.id.tv_symbol_coin);
        mTvError = rootView.findViewById(R.id.tv_atom_error);
        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);


        mAmountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().replace(",","").trim();
                if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                    if(es == null || es.length() == 0) {
                        mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    } else if (es.startsWith(".")) {
                        mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        mAmountInput.setText("");
                    } else if (es.endsWith(".")) {
                        mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                        mAmountInput.setVisibility(View.VISIBLE);
                    } else if(es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                        mAmountInput.setText("0");
                        mAmountInput.setSelection(1);
                    } else if(es.equals("0.000000")) {
                        mAmountInput.setText("0.00000");
                        mAmountInput.setSelection(7);
                    } else {
                        try {
                            final BigDecimal inputAmount = new BigDecimal(es);
                            if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                                mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                                mTvError.setVisibility(View.VISIBLE);
                                return;
                            }
                            BigDecimal checkPosition = inputAmount.movePointRight(6);
                            try {
                                Long.parseLong(checkPosition.toPlainString());
                            } catch (Exception e) {
                                String recover = es.substring(0, es.length() - 1);
                                mAmountInput.setText(recover);
                                mAmountInput.setSelection(recover.length());
                                return;
                            }
                            if(inputAmount.compareTo(getSActivity().mAccount.getAtomBalance().movePointLeft(6).setScale(6, RoundingMode.CEILING)) > 0) {
                                mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                                mTvError.setVisibility(View.VISIBLE);
                            } else {
                                mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                                mTvError.setVisibility(View.GONE);
                            }
                        } catch (Exception e) { }
                    }

                } else {
                    if(TextUtils.isEmpty(es)) {
                        mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                        mTvError.setVisibility(View.VISIBLE);
                    } else if (es.contains(".")) {
                        es = es.replace(".", "");
                        mAmountInput.setText(es);
                        mAmountInput.setSelection(es.length());
                    } else if (es.startsWith("0") && es.length() > 1) {
                        String recover = es.substring(1, 2);
                        mAmountInput.setText(recover);
                        mAmountInput.setSelection(recover.length());
                        return;
                    } else {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if(inputAmount.compareTo(getSActivity().mAccount.getAtomBalance()) > 0) {
                            mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            mTvError.setVisibility(View.VISIBLE);
                        } else {
                            mAmountInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                            mTvError.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });
//        onInitView();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isAdded() || getSActivity() == null || getSActivity().mAccount == null) getSActivity().onBackPressed();
        mAtomTitle.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            mAmountInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            mAvailableAmount.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getAtomBalance(), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        } else {
            mAmountInput.setInputType(InputType.TYPE_CLASS_NUMBER);
            mAvailableAmount.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getAtomBalance(), 0, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        }


    }



    @Override
    public void onClick(View v) {
        if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if(isValidateDelegateAmount()) {
                WLog.w("coin : denom : " + getSActivity().mToDelegateAmount.denom + "    amount : " + getSActivity().mToDelegateAmount.amount);
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amounts, Toast.LENGTH_SHORT).show();
            }

        }

    }

    private boolean isValidateDelegateAmount() {
        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            try {
                BigDecimal atomTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if(atomTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if(atomTemp.compareTo(getSActivity().mAccount.getAtomBalance().movePointLeft(6).setScale(6, RoundingMode.CEILING)) > 0) return false;
                Coin atom = new Coin(BaseConstant.COSMOS_ATOM, atomTemp.multiply(new BigDecimal("1000000")).setScale(0).toPlainString());
                getSActivity().mToDelegateAmount = atom;
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            try {

                BigDecimal muonTemp = new BigDecimal(mAmountInput.getText().toString().trim());
                if(muonTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if(muonTemp.compareTo(getSActivity().mAccount.getAtomBalance()) > 0) return false;
                Coin atom = new Coin(BaseConstant.COSMOS_MUON, muonTemp.toPlainString());
                getSActivity().mToDelegateAmount = atom;
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }


    private DelegateActivity getSActivity() {
        return (DelegateActivity)getBaseActivity();
    }
}

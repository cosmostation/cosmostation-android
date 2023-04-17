package wannabit.io.cosmostaion.fragment.txs.authz;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.protobuf.InvalidProtocolBufferException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import cosmos.authz.v1beta1.Authz;
import cosmos.base.v1beta1.CoinOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzSendActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.SelectChainListDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzSendStep1Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBefore, mNextBtn;
    private RelativeLayout mSelectCoinBtn;
    private ImageView mSendCoinImg;
    private TextView mSendCoinDenom;
    private EditText mAmountInput;
    private TextView mAvailableAmount;
    private TextView mDenomTitle;
    private ImageView mClearAll;
    private Button mAdd01, mAdd1, mAdd10, mAdd100, mAddHalf, mAddMax;
    private BigDecimal mMaxAvailable = BigDecimal.ZERO;

    private Authz.Grant mGrant;
    private ArrayList<Coin> mGrantAvailable = new ArrayList<>();
    private Coin mSelectedCoin;

    private int mDpDecimal = 6;
    private String mDecimalChecker, mDecimalSetter;

    public static AuthzSendStep1Fragment newInstance() {
        return new AuthzSendStep1Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_send_step1, container, false);
        mBefore = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mSelectCoinBtn = rootView.findViewById(R.id.btn_select_coin);
        mSendCoinImg = rootView.findViewById(R.id.send_coin_img);
        mSendCoinDenom = rootView.findViewById(R.id.send_coin_denom);
        mAmountInput = rootView.findViewById(R.id.et_amount_coin);
        mAvailableAmount = rootView.findViewById(R.id.tv_max_coin);
        mDenomTitle = rootView.findViewById(R.id.tv_symbol_coin);
        mClearAll = rootView.findViewById(R.id.clearAll);
        mAdd01 = rootView.findViewById(R.id.btn_add_01);
        mAdd1 = rootView.findViewById(R.id.btn_add_1);
        mAdd10 = rootView.findViewById(R.id.btn_add_10);
        mAdd100 = rootView.findViewById(R.id.btn_add_100);
        mAddHalf = rootView.findViewById(R.id.btn_add_half);
        mAddMax = rootView.findViewById(R.id.btn_add_all);

        mGrant = getSActivity().mGrant;
        mGrantAvailable = getSActivity().mGrantAvailable;

        mBefore.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mSelectCoinBtn.setOnClickListener(this);
        mClearAll.setOnClickListener(this);
        mAdd01.setOnClickListener(this);
        mAdd1.setOnClickListener(this);
        mAdd10.setOnClickListener(this);
        mAdd100.setOnClickListener(this);
        mAddHalf.setOnClickListener(this);
        mAddMax.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isAdded() || getSActivity() == null || getSActivity().mAccount == null) {
            getSActivity().onBackPressed();
        }

        mGrantAvailable.sort((o1, o2) -> {
            if (o1.denom.equalsIgnoreCase(getSActivity().mChainConfig.mainDenom())) return -1;
            if (o2.denom.equalsIgnoreCase(getSActivity().mChainConfig.mainDenom())) return 1;
            else return 0;
        });
        mSelectedCoin = mGrantAvailable.get(0);
        onUpdateView();
    }

    private void onUpdateView() {
        mDpDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mSelectedCoin.denom);
        mMaxAvailable = new BigDecimal(mSelectedCoin.amount);
        if (mGrant.getAuthorization().getTypeUrl().contains(cosmos.bank.v1beta1.Authz.SendAuthorization.getDescriptor().getFullName())) {
            try {
                cosmos.bank.v1beta1.Authz.SendAuthorization transAuthz = cosmos.bank.v1beta1.Authz.SendAuthorization.parseFrom(mGrant.getAuthorization().getValue());
                for (CoinOuterClass.Coin coin : transAuthz.getSpendLimitList()) {
                    if (coin.getDenom().equalsIgnoreCase(mSelectedCoin.denom)) {
                        BigDecimal limitAmount = new BigDecimal(coin.getAmount());
                        if (limitAmount.compareTo(mMaxAvailable) <= 0) {
                            mMaxAvailable = limitAmount;
                        }
                    }
                }
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mSelectedCoin.denom, mSendCoinImg);
        WDp.setDpSymbol(getActivity(), getBaseDao(), getSActivity().mChainConfig, mSelectedCoin.denom, mSendCoinDenom);
        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, mSelectedCoin.denom, mMaxAvailable.toPlainString(), mDenomTitle, mAvailableAmount);

        setDisplayDecimals(mDpDecimal);
        onAddAmountWatcher();
    }


    private void onAddAmountWatcher() {
        mAmountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().trim();
                if (TextUtils.isEmpty(es)) {
                    mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                } else if (es.startsWith(".")) {
                    mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mAmountInput.setText("");
                } else if (es.endsWith(".")) {
                    mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mAmountInput.setVisibility(View.VISIBLE);
                } else if (es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mAmountInput.setText("0");
                    mAmountInput.setSelection(1);
                }

                if (es.equals(mDecimalChecker)) {
                    mAmountInput.setText(mDecimalSetter);
                    mAmountInput.setSelection(mDpDecimal + 1);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0) {
                            mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                            return;
                        }

                        BigDecimal checkPosition = inputAmount.movePointRight(mDpDecimal);
                        BigDecimal checkMax = checkPosition.setScale(0, RoundingMode.DOWN);
                        if (checkPosition.compareTo(checkMax) != 0 || !checkPosition.equals(checkMax)) {
                            String recover = es.substring(0, es.length() - 1);
                            mAmountInput.setText(recover);
                            mAmountInput.setSelection(recover.length());
                            return;
                        }

                        if (inputAmount.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0) {
                            mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                        } else {
                            mAmountInput.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                        }
                    } catch (Exception e) { }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (isValidateSendAmount()) {
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mSelectCoinBtn) && !getSActivity().isFinishing()) {
            Bundle bundleData = new Bundle();
            bundleData.putSerializable(SelectChainListDialog.SEND_COIN_LIST_BUNDLE_KEY, mGrantAvailable);
            bundleData.putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_SEND_COIN_VALUE);
            SelectChainListDialog dialog = SelectChainListDialog.newInstance(bundleData);
            dialog.show(getParentFragmentManager(), SelectChainListDialog.class.getName());
            getParentFragmentManager().setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, this, (requestKey, bundle) -> {
                int result = bundle.getInt(BaseConstant.POSITION);
                mSelectedCoin = mGrantAvailable.get(result);
                mAmountInput.setText("");
                onUpdateView();
            });

        } else if (v.equals(mAdd01)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("0.1")).toPlainString());

        } else if (v.equals(mAdd1)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("1")).toPlainString());

        } else if (v.equals(mAdd10)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("10")).toPlainString());

        } else if (v.equals(mAdd100)) {
            BigDecimal existed = BigDecimal.ZERO;
            String es = mAmountInput.getText().toString().trim();
            if (es.length() > 0) {
                existed = new BigDecimal(es);
            }
            mAmountInput.setText(existed.add(new BigDecimal("100")).toPlainString());

        } else if (v.equals(mAddHalf)) {
            BigDecimal half = mMaxAvailable.movePointLeft(mDpDecimal).divide(new BigDecimal("2"), mDpDecimal, RoundingMode.DOWN);
            mAmountInput.setText(half.toPlainString());

        } else if (v.equals(mAddMax)) {
            mAmountInput.setText(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.DOWN).toPlainString());

        } else if (v.equals(mClearAll)) {
            mAmountInput.setText("");

        }

    }

    private boolean isValidateSendAmount() {
        try {
            BigDecimal sendTemp = new BigDecimal(mAmountInput.getText().toString().trim());
            if (sendTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if (sendTemp.compareTo(mMaxAvailable.movePointLeft(mDpDecimal).setScale(mDpDecimal, RoundingMode.CEILING)) > 0)
                return false;
            Coin toSendCoin = new Coin(mSelectedCoin.denom, sendTemp.movePointRight(mDpDecimal).setScale(0).toPlainString());
            getSActivity().mAmount = toSendCoin;
            return true;

        } catch (Exception e) {
            mSelectedCoin = null;
            return false;
        }
    }

    private void setDisplayDecimals(int decimals) {
        mDecimalChecker = "0.";
        mDecimalSetter = "0.";
        for (int i = 0; i < decimals; i++) {
            mDecimalChecker = mDecimalChecker + "0";
        }
        for (int i = 0; i < decimals - 1; i++) {
            mDecimalSetter = mDecimalSetter + "0";
        }
    }

    private AuthzSendActivity getSActivity() {
        return (AuthzSendActivity) getBaseActivity();
    }
}

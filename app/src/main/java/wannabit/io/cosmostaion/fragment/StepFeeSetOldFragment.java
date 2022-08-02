package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;

public class StepFeeSetOldFragment extends BaseFragment implements View.OnClickListener {

    private CardView mFeeTotalCard;
    private TextView mFeeDenom, mFeeAmount, mFeeValue;

    private Button mBtnBefore, mBtnNext;

    private Account mAccount;
    private BaseChain mBaseChain;
    private ChainConfig mChainConfig;

    private BigDecimal mFee = BigDecimal.ZERO;

    public static StepFeeSetOldFragment newInstance(Bundle bundle) {
        StepFeeSetOldFragment fragment = new StepFeeSetOldFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tx_step_fee_old, container, false);
        mFeeTotalCard = rootView.findViewById(R.id.card_fee_total);
        mFeeDenom = rootView.findViewById(R.id.fee_denom);
        mFeeAmount = rootView.findViewById(R.id.fee_amount);
        mFeeValue = rootView.findViewById(R.id.fee_value);

        mBtnBefore = rootView.findViewById(R.id.btn_before);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);

        mFeeTotalCard.setCardBackgroundColor(ContextCompat.getColor(getActivity(), mChainConfig.chainBgColor()));
        onUpdateView();

        mBtnBefore.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        return rootView;
    }

    private void onUpdateView() {
        mFee = WDp.getMainDenomFee(getActivity(), mChainConfig);
        WDp.setDpCoin(getActivity(), getBaseDao(), mChainConfig, mChainConfig.mainDenom(), mFee.toPlainString(), mFeeDenom, mFeeAmount);
        mFeeAmount.setText(WDp.getDpAmount2(getContext(), mFee, WDp.getDenomDecimal(getBaseDao(), mChainConfig, mChainConfig.mainDenom()), WDp.mainDisplayDecimal(getSActivity().mBaseChain)));
        mFeeValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mChainConfig.mainDenom(), mFee, WDp.getDenomDecimal(getBaseDao(), mChainConfig, mChainConfig.mainDenom())));
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            onSetFee();
            getSActivity().onNextStep();
        }
    }

    private void onSetFee() {
        if (mBaseChain.equals(BaseChain.OKEX_MAIN)) {
            Coin gasCoin = new Coin(mChainConfig.mainDenom(), mFee.setScale(WDp.mainDisplayDecimal(mChainConfig.baseChain())).toPlainString());
            ArrayList<Coin> amount = new ArrayList<>();
            amount.add(gasCoin);

            Fee fee = new Fee();
            fee.amount = amount;
            fee.gas = BaseConstant.BASE_GAS_AMOUNT;
            getSActivity().mTxFee = fee;

        } else {
            Coin gasCoin = new Coin(mChainConfig.mainDenom(), mFee.toPlainString());
            ArrayList<Coin> amount = new ArrayList<>();
            amount.add(gasCoin);

            Fee fee = new Fee();
            fee.amount = amount;
            fee.gas = BaseConstant.BASE_GAS_AMOUNT;
            getSActivity().mTxFee = fee;
        }
    }

    private BaseBroadCastActivity getSActivity() {
        return (BaseBroadCastActivity) getBaseActivity();
    }
}

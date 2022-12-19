package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EVM_TRANSFER;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.common.collect.Lists;

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
import wannabit.io.cosmostaion.task.gRpcTask.simulate.SimulErc20SendGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class StepFeeSetOldFragment extends BaseFragment implements View.OnClickListener {

    private CardView mFeeTotalCard;
    private TextView mFeeDenom, mFeeAmount, mFeeValue;

    private Button mBtnBefore, mBtnNext;

    private Account mAccount;
    private BaseChain mBaseChain;
    private ChainConfig mChainConfig;

    private BigDecimal mFeeGasAmount = BigDecimal.ZERO;
    private boolean mSimulPassed = true;

    public static StepFeeSetOldFragment newInstance() {
        return new StepFeeSetOldFragment();
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

        mBtnBefore.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        return rootView;
    }

    private void onUpdateView() {
        mFeeValue.setText(WDp.dpAssetValue(getBaseDao(), WDp.getGeckoId(getBaseDao(), mChainConfig), mFeeGasAmount, WDp.getDenomDecimal(getBaseDao(), mChainConfig, mChainConfig.mainDenom())));
        WDp.setDpCoin(getActivity(), getBaseDao(), mChainConfig, mChainConfig.mainDenom(), mFeeGasAmount.toPlainString(), mFeeDenom, mFeeAmount);
    }

    @Override
    public void onRefreshTab() {
        if (getSActivity().mTxType == CONST_PW_TX_EVM_TRANSFER) {
            onCalculEvmFee();
        } else {
            onCalculFee();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (!mSimulPassed) {
                Toast.makeText(getActivity(), getString(R.string.error_simul_error), Toast.LENGTH_SHORT).show();
                return;
            }
            getSActivity().onNextStep();
        }
    }

    private void onCalculFee() {
        mFeeGasAmount = WDp.getMainDenomFee(getActivity(), getBaseDao(), mChainConfig);
        if (mBaseChain.equals(BaseChain.OKEX_MAIN)) {
            Coin gasCoin = new Coin(mChainConfig.mainDenom(), mFeeGasAmount.setScale(WDp.mainDisplayDecimal(mChainConfig.baseChain())).toPlainString());
            ArrayList<Coin> amount = new ArrayList<>();
            amount.add(gasCoin);

            getSActivity().mTxFee = new Fee("5000000", amount);

        } else {
            Coin gasCoin = new Coin(mChainConfig.mainDenom(), mFeeGasAmount.toPlainString());
            ArrayList<Coin> amount = new ArrayList<>();
            amount.add(gasCoin);

            getSActivity().mTxFee = new Fee(BaseConstant.BASE_GAS_AMOUNT, amount);
        }
        onUpdateView();
    }

    private void onCalculEvmFee() {
        getSActivity().onShowWaitDialog();
        new SimulErc20SendGrpcTask(getBaseApplication(), result -> {
            getSActivity().onHideWaitDialog();
            if (result.isSuccess) {
                BigDecimal gasLimit = new BigDecimal((String) result.resultData);
                BigDecimal gasPrice = new BigDecimal(result.resultData2);
                getSActivity().mHexValue = result.resultData3;

                mFeeGasAmount = gasLimit.multiply(gasPrice).movePointLeft(WDp.mainDisplayDecimal(mChainConfig.baseChain()));
                getSActivity().mTxFee = new Fee(gasLimit.toPlainString(), Lists.newArrayList(new Coin(mChainConfig.mainDenom(), mFeeGasAmount.toPlainString())));
                mSimulPassed = true;

                getActivity().runOnUiThread(() -> onUpdateView());

            } else {
                View layout = getLayoutInflater().inflate(R.layout.item_toast_msg, getView().findViewById(R.id.toast_layout));
                TextView textView = layout.findViewById(R.id.toast_msg);
                textView.setText(result.errorMsg);

                Toast toast = new Toast(getContext());
                toast.setView(layout);
                toast.show();
                mSimulPassed = false;
            }
        }, getSActivity().mAccount, getSActivity().mBaseChain, getSActivity().mMintscanToken, getSActivity().mToAddress, getSActivity().mAmounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private BaseBroadCastActivity getSActivity() {
        return (BaseBroadCastActivity) getBaseActivity();
    }
}

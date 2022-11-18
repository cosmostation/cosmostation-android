package wannabit.io.cosmostaion.widget.txDetail.kava;

import static kava.router.v1beta1.Tx.MsgDelegateMintDeposit;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Optional;

import cosmos.staking.v1beta1.Staking;
import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.router.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxLiquidityHolder extends TxHolder {
    TextView mEarnTitle, mEarnAccountTitle;
    TextView mEarnDelegator, mEarnValidator, mEarnAmount, mEarnDenom;

    public TxLiquidityHolder(@NonNull View itemView) {
        super(itemView);
        mEarnTitle = itemView.findViewById(R.id.earn_title);
        mEarnAccountTitle = itemView.findViewById(R.id.earn_account_title);
        mEarnDelegator = itemView.findViewById(R.id.tx_earn_delegator);
        mEarnValidator = itemView.findViewById(R.id.tx_earn_validator);
        mEarnAmount = itemView.findViewById(R.id.tx_earn_amount);
        mEarnDenom = itemView.findViewById(R.id.tx_earn_amount_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        if (response.getTx().getBody().getMessages(position).getTypeUrl().contains("MsgDelegateMintDeposit")) {
            try {
                MsgDelegateMintDeposit msg = MsgDelegateMintDeposit.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                mEarnTitle.setText(c.getString(R.string.tx_kava_earn_delegateDeposit));
                mEarnAccountTitle.setText("Depositor");
                mEarnDelegator.setText(msg.getDepositor());

                Optional<Staking.Validator> validator = baseData.mGRpcAllValidators.stream().filter(item -> item.getOperatorAddress().equalsIgnoreCase(msg.getValidator())).findFirst();
                if (validator.isPresent()) {
                    mEarnValidator.setText("(" + validator.get().getDescription().getMoniker() + ")");
                }
                Coin coin = new Coin(msg.getAmount().getDenom(), msg.getAmount().getAmount());
                WDp.setDpCoin(c, baseData, chainConfig, coin, mEarnDenom, mEarnAmount);
                return;

            } catch (Exception e) { }

        } else {
            try {
                Tx.MsgWithdrawBurn msg = Tx.MsgWithdrawBurn.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                mEarnTitle.setText(c.getString(R.string.tx_kava_earn_withdraw));
                mEarnAccountTitle.setText("From");
                mEarnDelegator.setText(msg.getFrom());

                Optional<Staking.Validator> validator = baseData.mGRpcAllValidators.stream().filter(item -> item.getOperatorAddress().equalsIgnoreCase(msg.getValidator())).findFirst();
                if (validator.isPresent()) {
                    mEarnValidator.setText("(" + validator.get().getDescription().getMoniker() + ")");
                }
                Coin coin = new Coin(msg.getAmount().getDenom(), msg.getAmount().getAmount());
                WDp.setDpCoin(c, baseData, chainConfig, coin, mEarnDenom, mEarnAmount);

            } catch (Exception e) { }
        }
    }
}

package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.VoteListActivity;
import wannabit.io.cosmostaion.activities.chains.ok.OKStakingActivity;
import wannabit.io.cosmostaion.activities.chains.ok.OKUnbondingActivity;
import wannabit.io.cosmostaion.activities.chains.ok.OKValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.FEE_OK_GAS_AMOUNT_STAKE_MUX;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_OK_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;

public class WalletOkexHolder extends WalletHolder {
    private TextView            mOkTotalAmount, mOkTotalValue, mOkAvailable, mOkLocked, mOkDeposit, mOkWithdrawing;
    private RelativeLayout      mBtnOkDeposit, mBtnOkWithdraw, mBtnOkVoteForVali, mBtnOkVote;

    public WalletOkexHolder(@NonNull View itemView) {
        super(itemView);
        mOkTotalAmount      = itemView.findViewById(R.id.ok_total_amount);
        mOkTotalValue       = itemView.findViewById(R.id.ok_total_value);
        mOkAvailable        = itemView.findViewById(R.id.ok_available);
        mOkLocked           = itemView.findViewById(R.id.ok_locked);
        mOkDeposit          = itemView.findViewById(R.id.ok_deposit);
        mOkWithdrawing      = itemView.findViewById(R.id.ok_withdrawing);
        mBtnOkDeposit       = itemView.findViewById(R.id.btn_ok_deposit);
        mBtnOkWithdraw      = itemView.findViewById(R.id.btn_ok_withdraw);
        mBtnOkVoteForVali   = itemView.findViewById(R.id.btn_ok_vote_for_validator);
        mBtnOkVote          = itemView.findViewById(R.id.btn_ok_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        BigDecimal availableAmount = WDp.getAvailableCoin(baseData.mBalances, TOKEN_OK);
        BigDecimal lockedAmount = WDp.getLockedCoin(baseData.mBalances, TOKEN_OK);
        BigDecimal depositAmount = WDp.getOkDepositCoin(baseData.mOkStaking);
        BigDecimal withdrawAmount = WDp.getOkWithdrawingCoin(baseData.mOkUnbonding);
        BigDecimal totalAmount = availableAmount.add(lockedAmount).add(depositAmount).add(withdrawAmount);

        mOkTotalAmount.setText(WDp.getDpAmount2(mainActivity, totalAmount, 0, 6));
        mOkAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 0, 6));
        mOkLocked.setText(WDp.getDpAmount2(mainActivity, lockedAmount, 0, 6));
        mOkDeposit.setText(WDp.getDpAmount2(mainActivity, depositAmount, 0, 6));
        mOkWithdrawing.setText(WDp.getDpAmount2(mainActivity, withdrawAmount, 0, 6));
        mOkTotalValue.setText(WDp.getValueOfOk(mainActivity, baseData, totalAmount));

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnOkDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.getBaseDao().mTopValidators == null && mainActivity.getBaseDao().mTopValidators.size() == 0) return;
                if (!mainActivity.mAccount.hasPrivateKey) {
                    Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                    add.setCancelable(true);
                    mainActivity.getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                    return;
                }
                int myValidatorCnt = 0;
                if (mainActivity.getBaseDao().mOkStaking != null && mainActivity.getBaseDao().mOkStaking.validator_address != null) {
                    myValidatorCnt = mainActivity.getBaseDao().mOkStaking.validator_address.size();
                }
                BigDecimal estimateGasAmount = (new BigDecimal(FEE_OK_GAS_AMOUNT_STAKE_MUX).multiply(new BigDecimal(""+myValidatorCnt))).add(new BigDecimal(BaseConstant.FEE_OK_GAS_AMOUNT_STAKE));
                BigDecimal feeAmount = estimateGasAmount.multiply(new BigDecimal(FEE_OK_GAS_RATE_AVERAGE));
                if (availableAmount.compareTo(feeAmount) <= 0) {
                    Toast.makeText(mainActivity, R.string.error_not_enough_to_deposit, Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(mainActivity, OKStakingActivity.class);
                mainActivity.startActivity(intent);
            }
        });
        mBtnOkWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.getBaseDao().mTopValidators == null && mainActivity.getBaseDao().mTopValidators.size() == 0) return;
                if (!mainActivity.mAccount.hasPrivateKey) {
                    Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                    add.setCancelable(true);
                    mainActivity.getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                    return;
                }

                if (WDp.getOkDepositCoin(mainActivity.getBaseDao().mOkStaking).compareTo(BigDecimal.ZERO) <= 0) {
                    Toast.makeText(mainActivity, R.string.error_not_enough_to_withdraw, Toast.LENGTH_SHORT).show();
                    return;
                }

                int myValidatorCnt = 0;
                if (mainActivity.getBaseDao().mOkStaking != null && mainActivity.getBaseDao().mOkStaking.validator_address != null) {
                    myValidatorCnt = mainActivity.getBaseDao().mOkStaking.validator_address.size();
                }
                BigDecimal estimateGasAmount = (new BigDecimal(FEE_OK_GAS_AMOUNT_STAKE_MUX).multiply(new BigDecimal(""+myValidatorCnt))).add(new BigDecimal(BaseConstant.FEE_OK_GAS_AMOUNT_STAKE));
                BigDecimal feeAmount = estimateGasAmount.multiply(new BigDecimal(FEE_OK_GAS_RATE_AVERAGE));
                if (availableAmount.compareTo(feeAmount) <= 0) {
                    Toast.makeText(mainActivity, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(mainActivity, OKUnbondingActivity.class);
                mainActivity.startActivity(intent);
            }
        });
        mBtnOkVoteForVali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, OKValidatorListActivity.class);
                mainActivity.startActivity(intent);
            }
        });
        mBtnOkVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mainActivity.startActivity(new Intent(mainActivity, VoteListActivity.class));
                Toast.makeText(mainActivity, R.string.error_prepare, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

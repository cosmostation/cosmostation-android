package wannabit.io.cosmostaion.widget.mainWallet;

import static wannabit.io.cosmostaion.base.BaseConstant.OK_GAS_AMOUNT_STAKE_MUX;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_GAS_RATE_AVERAGE;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.chains.ok.OKStakingActivity;
import wannabit.io.cosmostaion.activities.chains.ok.OKUnbondingActivity;
import wannabit.io.cosmostaion.activities.chains.ok.OKValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletOkexHolder extends BaseHolder {
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
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.availableAmount(denom);
        final BigDecimal lockedAmount = baseData.lockedAmount(denom);
        final BigDecimal depositAmount = baseData.okDepositAmount();
        final BigDecimal withdrawAmount = baseData.okWithdrawAmount();
        final BigDecimal totalAmount = availableAmount.add(lockedAmount).add(depositAmount).add(withdrawAmount);


        mOkTotalAmount.setText(WDp.getDpAmount2(mainActivity, totalAmount, 0, 6));
        mOkAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 0, 6));
        mOkLocked.setText(WDp.getDpAmount2(mainActivity, lockedAmount, 0, 6));
        mOkDeposit.setText(WDp.getDpAmount2(mainActivity, depositAmount, 0, 6));
        mOkWithdrawing.setText(WDp.getDpAmount2(mainActivity, withdrawAmount, 0, 6));
        mOkTotalValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 0));

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnOkDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.getBaseDao().mTopValidators == null && mainActivity.getBaseDao().mTopValidators.size() == 0) return;
                if (!mainActivity.mAccount.hasPrivateKey) {
<<<<<<< HEAD
                    AlertDialogUtils.showDoubleButtonDialog(mainActivity, mainActivity.getString(R.string.str_only_observe_title), mainActivity.getString(R.string.str_only_observe_msg),
=======
                    AlertDialogUtils.showDoubleButtonDialog(mainActivity.getBaseContext(), mainActivity.getString(R.string.str_only_observe_title), mainActivity.getString(R.string.str_only_observe_msg),
>>>>>>> 27499f47969bdd4f525f7669fabb913fb236b984
                            mainActivity.getString(R.string.str_add_mnemonics), view -> mainActivity.onAddMnemonicForAccount(),
                            mainActivity.getString(R.string.str_close), null);
                    return;
                }
                int myValidatorCnt = 0;
                if (mainActivity.getBaseDao().mOkStaking != null && mainActivity.getBaseDao().mOkStaking.validator_address != null) {
                    myValidatorCnt = mainActivity.getBaseDao().mOkStaking.validator_address.size();
                }
                BigDecimal estimateGasAmount = (new BigDecimal(OK_GAS_AMOUNT_STAKE_MUX).multiply(new BigDecimal(""+myValidatorCnt))).add(new BigDecimal(BaseConstant.OK_GAS_AMOUNT_STAKE));
                BigDecimal feeAmount = estimateGasAmount.multiply(new BigDecimal(OK_GAS_RATE_AVERAGE));
                if (availableAmount.compareTo(feeAmount) <= 0) {
                    Toast.makeText(mainActivity, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (availableAmount.compareTo(new BigDecimal("0.01")) < 0) {
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
<<<<<<< HEAD
                    AlertDialogUtils.showDoubleButtonDialog(mainActivity, mainActivity.getString(R.string.str_only_observe_title), mainActivity.getString(R.string.str_only_observe_msg),
=======
                    AlertDialogUtils.showDoubleButtonDialog(mainActivity.getBaseContext(), mainActivity.getString(R.string.str_only_observe_title), mainActivity.getString(R.string.str_only_observe_msg),
>>>>>>> 27499f47969bdd4f525f7669fabb913fb236b984
                            mainActivity.getString(R.string.str_add_mnemonics), view -> mainActivity.onAddMnemonicForAccount(),
                            mainActivity.getString(R.string.str_close), null);
                    return;
                }

                if ((mainActivity.getBaseDao().okDepositAmount()).compareTo(BigDecimal.ZERO) <= 0) {
                    Toast.makeText(mainActivity, R.string.error_not_enough_to_withdraw, Toast.LENGTH_SHORT).show();
                    return;
                }

                int myValidatorCnt = 0;
                if (mainActivity.getBaseDao().mOkStaking != null && mainActivity.getBaseDao().mOkStaking.validator_address != null) {
                    myValidatorCnt = mainActivity.getBaseDao().mOkStaking.validator_address.size();
                }
                BigDecimal estimateGasAmount = (new BigDecimal(OK_GAS_AMOUNT_STAKE_MUX).multiply(new BigDecimal(""+myValidatorCnt))).add(new BigDecimal(BaseConstant.OK_GAS_AMOUNT_STAKE));
                BigDecimal feeAmount = estimateGasAmount.multiply(new BigDecimal(OK_GAS_RATE_AVERAGE));
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

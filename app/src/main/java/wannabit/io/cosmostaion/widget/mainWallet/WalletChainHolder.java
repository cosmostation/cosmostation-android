package wannabit.io.cosmostaion.widget.mainWallet;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.QRcodeActivity;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.activities.txs.common.VoteListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletChainHolder extends BaseHolder {
    public CardView mTvChainCard;
    public ImageView mTvChainIcon;
    public TextView mTvChainDenom;
    public TextView mTvChainTotal, mTvChainValue, mTvChainAvailable, mTvChainDelegated, mTvChainUnBonding, mTvChainRewards;
    public RelativeLayout mChainVestingLayer;
    public TextView mTvChainVesting;
    public RelativeLayout mBtnStake, mBtnVote, mBtnDex, mBtnWalletConnect;
    public TextView mBtnDexTitle;

    public WalletChainHolder(@NonNull View itemView) {
        super(itemView);
        mTvChainCard = itemView.findViewById(R.id.card_root);
        mTvChainIcon = itemView.findViewById(R.id.chain_icon);
        mTvChainDenom = itemView.findViewById(R.id.chain_denom);
        mTvChainTotal = itemView.findViewById(R.id.chain_amount);
        mTvChainValue = itemView.findViewById(R.id.chain_value);
        mTvChainAvailable = itemView.findViewById(R.id.chain_available);
        mTvChainDelegated = itemView.findViewById(R.id.chain_delegate);
        mTvChainUnBonding = itemView.findViewById(R.id.chain_unbonding);
        mTvChainRewards = itemView.findViewById(R.id.chain_reward);

        mChainVestingLayer = itemView.findViewById(R.id.chain_vesting_layer);
        mTvChainVesting = itemView.findViewById(R.id.chain_vesting);

        mBtnStake = itemView.findViewById(R.id.btn_delegate);
        mBtnVote = itemView.findViewById(R.id.btn_vote);
        mBtnDex = itemView.findViewById(R.id.btn_dex);
        mBtnDexTitle = itemView.findViewById(R.id.dex_title);
        mBtnWalletConnect = itemView.findViewById(R.id.btn_wallet_connect);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final ChainConfig chainConfig = ChainFactory.getChain(mainActivity.mBaseChain);
        final String denom = chainConfig.mainDenom();
        final int decimal = WDp.getDenomDecimal(baseData, chainConfig, denom);

        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal vestingAmount = baseData.getVesting(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvChainCard.setCardBackgroundColor(ContextCompat.getColor(mainActivity, chainConfig.chainBgColor()));
        mTvChainIcon.setImageResource(chainConfig.mainDenomImg());
        mTvChainDenom.setText(chainConfig.mainSymbol());
        mTvChainDenom.setTextColor(ContextCompat.getColor(mainActivity, chainConfig.chainColor()));

        mTvChainTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, decimal, 6));
        mTvChainAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, decimal, 6));
        mTvChainVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, decimal, 6));
        mTvChainDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, decimal, 6));
        mTvChainUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, decimal, 6));
        mTvChainRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, decimal, 6));
        mTvChainValue.setText(WDp.dpAssetValue(baseData, WDp.getGeckoId(baseData, chainConfig), totalAmount, decimal));

        if (!vestingAmount.equals(BigDecimal.ZERO)) {
            mChainVestingLayer.setVisibility(View.VISIBLE);
        } else {
            mChainVestingLayer.setVisibility(View.GONE);
        }

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnStake.setOnClickListener(v -> {
            if (mainActivity.mBaseChain.equals(BaseChain.STAFI_MAIN)) {
                Toast.makeText(mainActivity, mainActivity.getString(R.string.error_no_staking), Toast.LENGTH_SHORT).show();
                return;
            }
            Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
            mainActivity.startActivity(validators);
        });
        mBtnVote.setOnClickListener(v -> {
            Intent proposals = new Intent(mainActivity, VoteListActivity.class);
            mainActivity.startActivity(proposals);
        });

        if (chainConfig.dexSupport()) {
            mBtnDex.setVisibility(View.VISIBLE);
        } else {
            mBtnDex.setVisibility(View.GONE);
        }
        WUtil.setDexTitle(mainActivity, chainConfig, mBtnDexTitle);
        mBtnDex.setOnClickListener(v -> mainActivity.startActivity(WUtil.getDexIntent(mainActivity, chainConfig)));

        if (chainConfig.wcSupport() && !mainActivity.mAccount.isLedger()) {
            mBtnWalletConnect.setVisibility(View.VISIBLE);
        } else {
            mBtnWalletConnect.setVisibility(View.GONE);
        }
        mBtnWalletConnect.setOnClickListener(v -> {
            if (!mainActivity.mAccount.hasPrivateKey) {
                mainActivity.onInsertKeyDialog();
                return;
            } else {
                new TedPermission(mainActivity).setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        IntentIntegrator integrator = new IntentIntegrator(mainActivity);
                        integrator.setOrientationLocked(true);
                        integrator.setCaptureActivity(QRcodeActivity.class);
                        mainActivity.walletConnectResultLauncher.launch(integrator.createScanIntent());
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(mainActivity, R.string.error_permission, Toast.LENGTH_SHORT).show();
                    }
                }).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE).setRationaleMessage(mainActivity.getString(R.string.str_permission_qr)).check();
            }
        });
    }
}

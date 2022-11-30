package wannabit.io.cosmostaion.fragment.txs.liquidstaking;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.QRcodeActivity;
import wannabit.io.cosmostaion.activities.txs.liquidstaking.StrideLiquidActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.IBCReceiveAccountsDialog;
import wannabit.io.cosmostaion.utils.WDp;

public class StrideLiquidStep1Fragment extends BaseFragment implements View.OnClickListener {

    private EditText mAddressInput;
    private Button mCancel, mNextBtn;
    private LinearLayout mBtnWallet, mBtnQr, mBtnPaste;

    private ChainConfig mRecipientChainConfig;
    private ArrayList<Account> mRecipientableAccounts;

    public static StrideLiquidStep1Fragment newInstance() {
        return new StrideLiquidStep1Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_liquid_step1, container, false);
        mAddressInput = rootView.findViewById(R.id.receiver_account);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mCancel = rootView.findViewById(R.id.btn_cancel);

        mBtnQr = rootView.findViewById(R.id.btn_qr);
        mBtnPaste = rootView.findViewById(R.id.btn_paste);
        mBtnWallet = rootView.findViewById(R.id.btn_wallet);

        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);
        mBtnWallet.setOnClickListener(this);

        mRecipientChainConfig = ChainFactory.SUPPRT_CONFIG().stream().filter(it -> getSActivity().mChainId.startsWith(it.chainIdPrefix())).findFirst().get();

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mNextBtn)) {
            String userInput = String.valueOf(mAddressInput.getText()).trim();

            if (!WDp.isValidChainAddress(mRecipientChainConfig, userInput)) {
                Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                return;
            }
            getSActivity().mToAddress = userInput;
            getSActivity().onNextStep();

        } else if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnWallet)) {
            mRecipientableAccounts = getBaseDao().onSelectAccountsByChain(mRecipientChainConfig.baseChain());

            if (mRecipientChainConfig == null || mRecipientableAccounts.size() <= 0) {
                Toast.makeText(getSActivity(), getString(R.string.error_no_wallet_this_chain), Toast.LENGTH_SHORT).show();
                return;

            } else {
                Bundle bundleData = new Bundle();
                bundleData.putSerializable(IBCReceiveAccountsDialog.ACCOUNTS_BUNDLE_KEY, mRecipientableAccounts);
                IBCReceiveAccountsDialog dialog = IBCReceiveAccountsDialog.newInstance(bundleData);
                dialog.show(getParentFragmentManager(), IBCReceiveAccountsDialog.class.getName());
                getParentFragmentManager().setFragmentResultListener(IBCReceiveAccountsDialog.IBC_RECEIVE_ACCOUNTS_BUNDLE_KEY, this, (requestKey, bundle) -> {
                    int result = bundle.getInt(BaseConstant.POSITION);
                    mAddressInput.setText(mRecipientableAccounts.get(result).address);
                });
            }

        } else if (v.equals(mBtnQr)) {
            IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
            integrator.setOrientationLocked(true);
            integrator.setCaptureActivity(QRcodeActivity.class);
            qrCodeResultLauncher.launch(integrator.createScanIntent());

        } else if (v.equals(mBtnPaste)) {
            ClipboardManager clipboard = (ClipboardManager) getSActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                String userPaste = String.valueOf(clipboard.getPrimaryClip().getItemAt(0).coerceToText(getSActivity())).trim();
                if (TextUtils.isEmpty(userPaste)) {
                    Toast.makeText(getSActivity(), R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                    return;
                }
                mAddressInput.setText(userPaste);
                mAddressInput.setSelection(mAddressInput.getText().length());

            } else {
                Toast.makeText(getSActivity(), R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private StrideLiquidActivity getSActivity() {
        return (StrideLiquidActivity) getBaseActivity();
    }

    private final ActivityResultLauncher<Intent> qrCodeResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            mAddressInput.setText(result.getData().getStringExtra(Intents.Scan.RESULT).trim());
            mAddressInput.setSelection(mAddressInput.getText().length());
        }
    });
}

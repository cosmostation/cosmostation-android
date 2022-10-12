package wannabit.io.cosmostaion.fragment.txs.authz;

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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzSendActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzSendStep0Fragment extends BaseFragment implements View.OnClickListener {

    private EditText mAddressInput;
    private Button mCancel, mNextBtn;
    private LinearLayout mBtnQr, mBtnPaste, mBtnHistory;

    public static AuthzSendStep0Fragment newInstance() {
        return new AuthzSendStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_send_step0, container, false);
        mAddressInput = rootView.findViewById(R.id.receiver_account);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mCancel = rootView.findViewById(R.id.btn_cancel);

        mBtnQr = rootView.findViewById(R.id.btn_qr);
        mBtnPaste = rootView.findViewById(R.id.btn_paste);
        mBtnHistory = rootView.findViewById(R.id.btn_history);
        mBtnHistory.setVisibility(View.GONE);

        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);
        mBtnHistory.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mNextBtn)) {
            String userInput = String.valueOf(mAddressInput.getText()).trim();

            if (getSActivity().mGranter.equals(userInput)) {
                Toast.makeText(getContext(), R.string.error_self_sending, Toast.LENGTH_SHORT).show();
                return;
            }

            if (WDp.isValidChainAddress(ChainFactory.getChain(getSActivity().mBaseChain), userInput)) {
                getSActivity().mToAddress = userInput;
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnQr)) {
            IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
            integrator.setOrientationLocked(true);
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

    private final ActivityResultLauncher<Intent> qrCodeResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            mAddressInput.setText(result.getData().getStringExtra(Intents.Scan.RESULT).trim());
            mAddressInput.setSelection(mAddressInput.getText().length());
        }
    });

    private AuthzSendActivity getSActivity() {
        return (AuthzSendActivity) getBaseActivity();
    }
}

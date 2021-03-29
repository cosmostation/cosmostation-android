package wannabit.io.cosmostaion.fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.RewardAddressChangeActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WKey;

import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;

public class RewardAddressChangeStep0Fragment extends BaseFragment implements View.OnClickListener {

    private EditText        mAddressInput;
    private LinearLayout    mBtnQr, mBtnPaste;
    private TextView        mCurrentAddress;
    private Button          mCancel, mNextBtn;

    public static RewardAddressChangeStep0Fragment newInstance(Bundle bundle) {
        RewardAddressChangeStep0Fragment fragment = new RewardAddressChangeStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView   = inflater.inflate(R.layout.fragment_reward_address_change_step0, container, false);
        mAddressInput   = rootView.findViewById(R.id.reward_account);
        mCurrentAddress = rootView.findViewById(R.id.current_address);
        mBtnQr          = rootView.findViewById(R.id.btn_qr);
        mBtnPaste       = rootView.findViewById(R.id.btn_paste);
        mNextBtn        = rootView.findViewById(R.id.btn_next);
        mCancel         = rootView.findViewById(R.id.btn_cancel);

        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);

        mAddressInput.setHint(getString(R.string.str_new_reward_address));
        mCurrentAddress.setText(getSActivity().mCurrentRewardAddress);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mNextBtn)) {
            String targetAddress = mAddressInput.getText().toString().trim();
            if (getSActivity().mCurrentRewardAddress.equals(targetAddress)) {
                Toast.makeText(getContext(), R.string.error_same_reward_address, Toast.LENGTH_SHORT).show();
                return;
            }

            if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN) || getSActivity().mBaseChain.equals(BaseChain.COSMOS_TEST)) {
                if (targetAddress.startsWith("cosmos1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN) || getSActivity().mBaseChain.equals(BaseChain.IRIS_TEST)) {
                if (targetAddress.startsWith("iaa1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                if (targetAddress.startsWith("kava1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BaseChain.BAND_MAIN)) {
                if (targetAddress.startsWith("band1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BaseChain.IOV_MAIN) || getSActivity().mBaseChain.equals(BaseChain.IOV_TEST)) {
                if (targetAddress.startsWith("star1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BaseChain.CERTIK_MAIN) || getSActivity().mBaseChain.equals(BaseChain.CERTIK_TEST)) {
                if (targetAddress.startsWith("certik1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BaseChain.SECRET_MAIN)) {
                if (targetAddress.startsWith("secret1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BaseChain.AKASH_MAIN)) {
                if (targetAddress.startsWith("akash1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(PERSIS_MAIN)) {
                if (targetAddress.startsWith("persistence1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(SENTINEL_MAIN)) {
                if (targetAddress.startsWith("sent1") && WKey.isValidBech32(targetAddress)) {
                    getSActivity().mNewRewardAddress = targetAddress;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnQr)) {
            IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();

        } else if (v.equals(mBtnPaste)) {
            ClipboardManager clipboard = (ClipboardManager)getSActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            if(clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(getSActivity()).toString().trim();
                if(TextUtils.isEmpty(userPaste)) {
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

    private RewardAddressChangeActivity getSActivity() {
        return (RewardAddressChangeActivity)getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                mAddressInput.setText(result.getContents().trim());
                mAddressInput.setSelection(mAddressInput.getText().length());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

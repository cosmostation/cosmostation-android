package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_StarName_Confirm;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResIovOriginAddress;
import wannabit.io.cosmostaion.utils.WKey;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;

public class SendStep0Fragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_STAR_NAME_ADDRESS = 9102;

    private EditText        mAddressInput;
    private Button          mCancel, mNextBtn;
    private LinearLayout    mBtnQr, mBtnPaste, mBtnHistory;

    public static SendStep0Fragment newInstance(Bundle bundle) {
        SendStep0Fragment fragment = new SendStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_step0, container, false);
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
            String userInput = mAddressInput.getText().toString().trim();
            if (getSActivity().mAccount.address.equals(userInput)) {
                Toast.makeText(getContext(), R.string.error_self_sending, Toast.LENGTH_SHORT).show();
                return;
            }

            //Check Support IOV starname service
            if (BaseChain.SUPPORT_CHAINS().contains(IOV_MAIN) && userInput.contains("*")) {
                getSActivity().onShowWaitDialog();
                ApiClient.getIovChain(getSActivity()).getOriginAddress(userInput).enqueue(new Callback<ResIovOriginAddress>() {
                    @Override
                    public void onResponse(Call<ResIovOriginAddress> call, Response<ResIovOriginAddress> response) {
                        getSActivity().onHideWaitDialog();
                        if(response.isSuccessful() && response.body() != null) {
                            String originAddress = response.body().getOriginAddress(getSActivity().mBaseChain);
                            if (!TextUtils.isEmpty(originAddress)) {
                                Bundle bundle = new Bundle();
                                bundle.putString("starname", userInput);
                                bundle.putString("originAddress", originAddress);
                                Dialog_StarName_Confirm dialog = Dialog_StarName_Confirm.newInstance(bundle);
                                dialog.setCancelable(true);
                                dialog.setTargetFragment(SendStep0Fragment.this, SELECT_STAR_NAME_ADDRESS);
                                getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

                            } else {
                                Toast.makeText(getContext(), R.string.error_invalid_star_name, Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getContext(), R.string.error_invalid_star_name, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResIovOriginAddress> call, Throwable t) {
                        getSActivity().onHideWaitDialog();
                        Toast.makeText(getContext(), R.string.error_invalid_star_name, Toast.LENGTH_SHORT).show();
                    }
                });
                return;
            }


            if (getSActivity().mBaseChain.equals(COSMOS_MAIN)) {
                if (userInput.startsWith("cosmos") && WKey.isValidBech32(userInput)) {
                    getSActivity().mTagetAddress = userInput;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_cosmos_address, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(IRIS_MAIN)) {
                if (userInput.startsWith("iaa") && WKey.isValidBech32(userInput)) {
                    getSActivity().mTagetAddress = userInput;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_iris_address, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BNB_MAIN)) {
                if (userInput.startsWith("bnb") && WKey.isValidBech32(userInput)) {
                    getSActivity().mTagetAddress = userInput;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_bnb_address, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
                if (userInput.startsWith("kava") && WKey.isValidBech32(userInput)) {
                    getSActivity().mTagetAddress = userInput;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_kava_address, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST)) {
                if (userInput.startsWith("star") && WKey.isValidBech32(userInput)) {
                    getSActivity().mTagetAddress = userInput;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_iov_address, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BNB_TEST)) {
                if (userInput.startsWith("tbnb") && WKey.isValidBech32(userInput)) {
                    getSActivity().mTagetAddress = userInput;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_bnb_address, Toast.LENGTH_SHORT).show();
                }

            } else if (getSActivity().mBaseChain.equals(BAND_MAIN)) {
                if (userInput.startsWith("band") && WKey.isValidBech32(userInput)) {
                    getSActivity().mTagetAddress = userInput;
                    getSActivity().onNextStep();
                } else {
                    Toast.makeText(getContext(), R.string.error_invalid_band_address, Toast.LENGTH_SHORT).show();
                }

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


        } else if (v.equals(mBtnHistory)) {
            Toast.makeText(getSActivity(), R.string.error_prepare, Toast.LENGTH_SHORT).show();

        }
    }

    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_STAR_NAME_ADDRESS) {
            if (resultCode == Activity.RESULT_OK) {
                getSActivity().mStarName = data.getStringExtra("starname");
                getSActivity().mTagetAddress = data.getStringExtra("originAddress");
                getSActivity().onNextStep();
            }

        } else {
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
}
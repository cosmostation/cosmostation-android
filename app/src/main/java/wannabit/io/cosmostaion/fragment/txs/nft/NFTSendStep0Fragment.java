package wannabit.io.cosmostaion.fragment.txs.nft;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.concurrent.TimeUnit;

import io.grpc.stub.StreamObserver;
import starnamed.x.starname.v1beta1.QueryGrpc;
import starnamed.x.starname.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.nft.NFTSendActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.StarNameConfirmDialog;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class NFTSendStep0Fragment extends BaseFragment implements View.OnClickListener {

    private EditText mAddressInput;
    private Button mCancel, mNextBtn;
    private LinearLayout mStarNameLayer;
    private LinearLayout mBtnQr, mBtnPaste;

    public static NFTSendStep0Fragment newInstance() {
        return new NFTSendStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_nft_step0, container, false);
        mAddressInput = rootView.findViewById(R.id.receiver_account);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mStarNameLayer = rootView.findViewById(R.id.starname_layer);

        mBtnQr = rootView.findViewById(R.id.btn_qr);
        mBtnPaste = rootView.findViewById(R.id.btn_paste);

        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private boolean alreadyOpen;
            private final int defaultKeyboardHeightDP = 100;
            private final int EstimatedKeyboardDP = defaultKeyboardHeightDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
            private final Rect rect = new Rect();

            @Override
            public void onGlobalLayout() {
                int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, rootView.getResources().getDisplayMetrics());
                rootView.getWindowVisibleDisplayFrame(rect);
                int heightDiff = rootView.getRootView().getHeight() - (rect.bottom - rect.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;
                if (isShown == alreadyOpen) {
                    return;
                }
                alreadyOpen = isShown;
                if (alreadyOpen) {
                    mStarNameLayer.setVisibility(View.GONE);
                } else {
                    new Handler().postDelayed(() -> mStarNameLayer.setVisibility(View.VISIBLE), 100);
                }
            }
        });
        mStarNameLayer.setVisibility(View.VISIBLE);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mNextBtn)) {
            String userInput = String.valueOf(mAddressInput.getText()).trim();

            if (getSActivity().mAccount.address.equals(userInput)) {
                Toast.makeText(getContext(), R.string.error_self_sending, Toast.LENGTH_SHORT).show();
                return;
            }

            if (WUtil.isValidStarName(userInput.toLowerCase())) {
                onCheckNameService(userInput.toLowerCase(), ChainFactory.getChain(getSActivity().mBaseChain));
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

    private NFTSendActivity getSActivity() {
        return (NFTSendActivity) getBaseActivity();
    }

    private final ActivityResultLauncher<Intent> qrCodeResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            mAddressInput.setText(result.getData().getStringExtra(Intents.Scan.RESULT).trim());
            mAddressInput.setSelection(mAddressInput.getText().length());
        }
    });

    private void onCheckNameService(String userInput, ChainConfig chainConfig) {
        QueryGrpc.QueryStub mStub = QueryGrpc.newStub(ChannelBuilder.getChain(IOV_MAIN)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
        QueryOuterClass.QueryStarnameRequest request = QueryOuterClass.QueryStarnameRequest.newBuilder().setStarname(userInput).build();
        mStub.starname(request, new StreamObserver<QueryOuterClass.QueryStarnameResponse>() {
            @Override
            public void onNext(QueryOuterClass.QueryStarnameResponse value) {
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    final String matchAddress = WUtil.checkStarnameWithResource(chainConfig, value.getAccount().getResourcesList());
                    if (TextUtils.isEmpty(matchAddress)) {
                        Toast.makeText(getContext(), R.string.error_no_mattched_starname, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (getSActivity().mAccount.address.equals(matchAddress)) {
                        Toast.makeText(getContext(), R.string.error_starname_self_send, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Bundle bundleData = new Bundle();
                    bundleData.putString(StarNameConfirmDialog.STAR_NAME_BUNDLE_KEY, userInput);
                    bundleData.putString(StarNameConfirmDialog.STAR_NAME_ORIGIN_ADDRESS_BUNDLE_KEY, matchAddress);
                    if (!getSActivity().isFinishing()) {
                        StarNameConfirmDialog dialog = StarNameConfirmDialog.newInstance(bundleData);
                        dialog.show(getParentFragmentManager(), StarNameConfirmDialog.class.getName());
                        getParentFragmentManager().setFragmentResultListener(StarNameConfirmDialog.STAR_NAME_CONFIRM_BUNDLE_KEY, NFTSendStep0Fragment.this, (requestKey, bundle) -> {
                            String originAddress = bundle.getString(StarNameConfirmDialog.STAR_NAME_ORIGIN_ADDRESS_BUNDLE_KEY);
                            getSActivity().mToAddress = originAddress;
                            getSActivity().onNextStep();
                        });
                    }
                }, 0);
            }

            @Override
            public void onError(Throwable t) {
                new Handler(Looper.getMainLooper()).postDelayed(() -> Toast.makeText(getContext(), R.string.error_invalide_starname, Toast.LENGTH_SHORT).show(), 0);
            }

            @Override
            public void onCompleted() {
            }
        });
    }
}

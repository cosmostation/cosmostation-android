package wannabit.io.cosmostaion.fragment.chains.ibc;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.grpc.stub.StreamObserver;
import starnamed.x.starname.v1beta1.QueryGrpc;
import starnamed.x.starname.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.ibc.IBCSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_IBC_Receivable_Accouts;
import wannabit.io.cosmostaion.dialog.Dialog_StarName_Confirm;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class IBCSendStep1Fragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    public final static int SELECT_ACCOUNT = 9101;
    public final static int SELECT_STAR_NAME_ADDRESS = 9102;

    private TextView mDesitination;
    private EditText mAddressInput;
    private Button mCancel, mNextBtn;
    private LinearLayout mStarNameLayer;
    private LinearLayout mBtnQr, mBtnPaste, mBtnWallet;

    private BaseChain mTochain;
    private ArrayList<Account> mToAccountList;
    private Account mToAccount;

    public static IBCSendStep1Fragment newInstance(Bundle bundle) {
        IBCSendStep1Fragment fragment = new IBCSendStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ibc_send_step1, container, false);
        mDesitination = rootView.findViewById(R.id.desitination_chain);
        mAddressInput = rootView.findViewById(R.id.receiver_account);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mStarNameLayer = rootView.findViewById(R.id.starname_layer);

        mBtnQr = rootView.findViewById(R.id.btn_qr);
        mBtnPaste = rootView.findViewById(R.id.btn_paste);
        mBtnWallet = rootView.findViewById(R.id.btn_wallet);

        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);
        mBtnWallet.setOnClickListener(this);

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
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mStarNameLayer.setVisibility(View.VISIBLE);
                        }
                    }, 100);
                }
            }
        });
        mStarNameLayer.setVisibility(View.VISIBLE);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mTochain = WDp.getChainTypeByChainId(getSActivity().mIbcSelectedRelayer.chain_id);
        mToAccountList = getBaseDao().onSelectAccountsByChain(mTochain);
        WDp.getChainHint(mTochain, mDesitination);
        mDesitination.setTextColor(WDp.getChainColor(getSActivity(), mTochain));
        String userInput = mAddressInput.getText().toString().trim();
        if (!WDp.isValidChainAddress(mTochain, userInput, false)) {
            mAddressInput.setText("");
        }
    }

    private void onUpdateView() {
        if (mToAccount == null) {
            getSActivity().onBeforeStep();
        }
        mAddressInput.setText(mToAccount.address);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mNextBtn)) {
            String userInput = mAddressInput.getText().toString().trim();

            if (WUtil.isValidStarName(userInput.toLowerCase())) {
                onCheckNameService(userInput.toLowerCase(), mTochain);
                return;
            }

            if (getSActivity().account.address.equals(userInput)) {
                Toast.makeText(getContext(), R.string.error_self_sending, Toast.LENGTH_SHORT).show();
                return;
            }
            if (WDp.isValidChainAddress(mTochain, userInput)) {
                getSActivity().mToAddress = userInput;
                getSActivity().mRecipientAccount = mToAccount;
                getSActivity().onNextStep();
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnWallet)) {
            if (mTochain == null || mToAccountList.size() <= 0) {
                Toast.makeText(getSActivity(), getString(R.string.error_no_wallet_this_chain), Toast.LENGTH_SHORT).show();
                return;
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("chainName", mTochain.getChain());
                Dialog_IBC_Receivable_Accouts dialog = Dialog_IBC_Receivable_Accouts.newInstance(bundle);
                dialog.setTargetFragment(this, SELECT_ACCOUNT);
                showDialog(dialog);
            }

        } else if (v.equals(mBtnQr)) {
            IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();

        } else if (v.equals(mBtnPaste)) {
            ClipboardManager clipboard = (ClipboardManager) getSActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(getSActivity()).toString().trim();
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

    private IBCSendActivity getSActivity() {
        return (IBCSendActivity) getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SELECT_ACCOUNT && resultCode == Activity.RESULT_OK) {
            mToAccount = mToAccountList.get(data.getIntExtra("position", 0));
            onUpdateView();

        } else if (requestCode == SELECT_STAR_NAME_ADDRESS && resultCode == Activity.RESULT_OK) {
            getSActivity().mToAddress = data.getStringExtra("originAddress");
            getSActivity().onNextStep();

        } else {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() != null) {
                    mAddressInput.setText(result.getContents().trim());
                    mAddressInput.setSelection(mAddressInput.getText().length());
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void onCheckNameService(String userInput, BaseChain chain) {
        QueryGrpc.QueryStub mStub = QueryGrpc.newStub(ChannelBuilder.getChain(IOV_MAIN)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
        QueryOuterClass.QueryStarnameRequest request = QueryOuterClass.QueryStarnameRequest.newBuilder().setStarname(userInput).build();
        mStub.starname(request, new StreamObserver<QueryOuterClass.QueryStarnameResponse>() {
            @Override
            public void onNext(QueryOuterClass.QueryStarnameResponse value) {
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    final String matchAddress = WUtil.checkStarnameWithResource(chain, value.getAccount().getResourcesList());
                    if (TextUtils.isEmpty(matchAddress)) {
                        Toast.makeText(getContext(), R.string.error_no_mattched_starname, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (getSActivity().account.address.equals(matchAddress)) {
                        Toast.makeText(getContext(), R.string.error_starname_self_send, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("starname", userInput);
                    bundle.putString("originAddress", matchAddress);
                    Dialog_StarName_Confirm dialog = Dialog_StarName_Confirm.newInstance(bundle);
                    dialog.setTargetFragment(IBCSendStep1Fragment.this, SELECT_STAR_NAME_ADDRESS);
                    showDialog(dialog);
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

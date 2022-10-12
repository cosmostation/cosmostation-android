package wannabit.io.cosmostaion.fragment.txs.common;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
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
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.grpc.stub.StreamObserver;
import starnamed.x.starname.v1beta1.QueryGrpc;
import starnamed.x.starname.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.Cw20Asset;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.dialog.IBCReceiveAccountsDialog;
import wannabit.io.cosmostaion.dialog.SelectChainListDialog;
import wannabit.io.cosmostaion.dialog.StarNameConfirmDialog;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class SendStep0Fragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mToChainList;
    private ImageView mToChainImg;
    private TextView mToChainTxt;
    private EditText mAddressInput;
    private Button mCancel, mNextBtn;
    private LinearLayout mIbcLayer;
    private LinearLayout mBtnWallet, mBtnQr, mBtnPaste;

    private ArrayList<ChainConfig> mToSendableChains = new ArrayList<>();
    private ChainConfig mToSendChainConfig;
    private ArrayList<Account> mToAccountList;
    private Asset mAsset;
    private Cw20Asset mCw20Asset;

    public static SendStep0Fragment newInstance() {
        return new SendStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_step0, container, false);
        mToChainList = rootView.findViewById(R.id.btn_to_chain_list);
        mToChainImg = rootView.findViewById(R.id.img_to_chain);
        mToChainTxt = rootView.findViewById(R.id.txt_to_chain);
        mAddressInput = rootView.findViewById(R.id.receiver_account);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mIbcLayer = rootView.findViewById(R.id.ibc_layer);

        mBtnQr = rootView.findViewById(R.id.btn_qr);
        mBtnPaste = rootView.findViewById(R.id.btn_paste);
        mBtnWallet = rootView.findViewById(R.id.btn_wallet);

        mToChainList.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);
        mBtnWallet.setOnClickListener(this);

        mAsset = getBaseDao().getAsset(getSActivity().mChainConfig, getSActivity().mDenom);
        mCw20Asset = getBaseDao().getCw20Asset(getSActivity().mDenom);
        mToSendableChains.add(getSActivity().mChainConfig);

        ArrayList<ChainConfig> allChainConfig = ChainFactory.SUPPRT_CONFIG();
        for (Asset asset : getBaseDao().mAssets) {
            if (mAsset != null) {
                if (asset.chain.equalsIgnoreCase(getSActivity().mChainConfig.chainName()) && asset.denom.equalsIgnoreCase(getSActivity().mDenom)) {
                    for (ChainConfig chainConfig : allChainConfig) {
                        if (chainConfig.chainName().equalsIgnoreCase(asset.beforeChain(getSActivity().mChainConfig)) && !mToSendableChains.contains(chainConfig)) {
                            mToSendableChains.add(chainConfig);
                        }
                    }

                } else if (asset.counter_party != null && asset.counter_party.denom.equalsIgnoreCase(getSActivity().mDenom)) {
                    for (ChainConfig chainConfig : allChainConfig) {
                        if (chainConfig.chainName().equalsIgnoreCase(asset.chain) && !mToSendableChains.contains(chainConfig)) {
                            mToSendableChains.add(chainConfig);
                        }
                    }
                }

            } else if (mCw20Asset != null) {
                if (asset.counter_party != null && asset.counter_party.denom.equalsIgnoreCase(mCw20Asset.contract_address)) {
                    for (ChainConfig chainConfig : allChainConfig) {
                        if (chainConfig.chainName().equalsIgnoreCase(asset.chain)) {
                            mToSendableChains.add(chainConfig);
                        }
                    }
                }
            }
        }

        onSortToChain();
        mToSendChainConfig = mToSendableChains.get(0);
        mToAccountList = getBaseDao().onSelectAccountsExceptSelfByChain(mToSendChainConfig.baseChain(), getSActivity().mAccount);
        onUpdateChainView();

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
                    mIbcLayer.setVisibility(View.GONE);
                } else {
                    new Handler().postDelayed(() -> {
                        if (mToSendChainConfig.baseChain().equals(getSActivity().mBaseChain))
                            mIbcLayer.setVisibility(View.GONE);
                        else mIbcLayer.setVisibility(View.VISIBLE);

                    }, 100);
                }
            }
        });
        return rootView;
    }

    private void onUpdateChainView() {
        mToChainImg.setImageResource(mToSendChainConfig.chainImg());
        mToChainTxt.setText(mToSendChainConfig.chainTitleToUp());
        mToChainTxt.setTextColor(ContextCompat.getColor(getActivity(), mToSendChainConfig.chainColor()));
        mAddressInput.setText("");
        if (mToSendChainConfig.baseChain().equals(getSActivity().mBaseChain))
            mIbcLayer.setVisibility(View.GONE);
        else mIbcLayer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mToChainList)) {
            Bundle bundleData = new Bundle();
            bundleData.putSerializable(SelectChainListDialog.TO_SENDABLE_CHAIN_CONFIG_BUNDLE_KEY, mToSendableChains);
            bundleData.putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_IBC_CHAIN_VALUE);
            SelectChainListDialog dialog = SelectChainListDialog.newInstance(bundleData);
            dialog.show(getParentFragmentManager(), SelectChainListDialog.class.getName());
            getParentFragmentManager().setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, this, (requestKey, bundle) -> {
                int result = bundle.getInt(BaseConstant.POSITION);
                mToSendChainConfig = mToSendableChains.get(result);
                mToAccountList = getBaseDao().onSelectAccountsExceptSelfByChain(mToSendChainConfig.baseChain(), getSActivity().mAccount);
                onUpdateChainView();
            });
            getSActivity().onHideKeyboard();

        } else if (v.equals(mNextBtn)) {
            String userInput = String.valueOf(mAddressInput.getText()).trim();

            if (getSActivity().mAccount.address.equals(userInput)) {
                Toast.makeText(getContext(), R.string.error_self_sending, Toast.LENGTH_SHORT).show();
                return;
            }

            if (WUtil.isValidStarName(userInput.toLowerCase())) {
                onPathSetting();
                onCheckNameService(userInput.toLowerCase(), mToSendChainConfig);
                return;
            }

            if (WDp.isValidChainAddress(mToSendChainConfig, userInput)) {
                if (!isExchangeAddress(userInput)) {
                    CommonAlertDialog.showSingleButton(getActivity(), Html.fromHtml("<font color=\"#f31963\">" + getString(R.string.str_empty_warnning_title) + "</font>", Html.FROM_HTML_MODE_COMPACT),
                            getString(R.string.error_exchange_address_msg), getString(R.string.str_confirm), null, false);
                    return;
                }
                onPathSetting();
                getSActivity().mToAddress = userInput;
                getSActivity().onNextStep();

            } else {
                Toast.makeText(getContext(), R.string.error_invalid_address_target, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnWallet)) {
            if (mToSendChainConfig == null || mToAccountList.size() <= 0) {
                Toast.makeText(getSActivity(), getString(R.string.error_no_wallet_this_chain), Toast.LENGTH_SHORT).show();
                return;

            } else {
                Bundle bundleData = new Bundle();
                bundleData.putSerializable(IBCReceiveAccountsDialog.ACCOUNTS_BUNDLE_KEY, mToAccountList);
                IBCReceiveAccountsDialog dialog = IBCReceiveAccountsDialog.newInstance(bundleData);
                dialog.show(getParentFragmentManager(), IBCReceiveAccountsDialog.class.getName());
                getParentFragmentManager().setFragmentResultListener(IBCReceiveAccountsDialog.IBC_RECEIVE_ACCOUNTS_BUNDLE_KEY, this, (requestKey, bundle) -> {
                    int result = bundle.getInt(BaseConstant.POSITION);
                    mAddressInput.setText(mToAccountList.get(result).address);
                });
            }

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

    private void onPathSetting() {
        if (getSActivity().mBaseChain.equals(mToSendChainConfig.baseChain())) {
            if (mAsset != null) getSActivity().mTxType = CONST_PW_TX_SIMPLE_SEND;
            else if (mCw20Asset != null) getSActivity().mTxType = CONST_PW_TX_EXECUTE_CONTRACT;
            else getSActivity().mTxType = CONST_PW_TX_SIMPLE_SEND;

        } else {
            if (mAsset != null) {
                getSActivity().mTxType = CONST_PW_TX_IBC_TRANSFER;
            } else if (mCw20Asset != null) {
                getSActivity().mTxType = CONST_PW_TX_IBC_CONTRACT;
            }
            getSActivity().mAssetPath = WDp.getAssetPath(getBaseDao(), getSActivity().mChainConfig, mToSendChainConfig, getSActivity().mDenom);
        }
        getSActivity().mAsset = mAsset;
        getSActivity().mCw20Asset = mCw20Asset;
    }

    private boolean isExchangeAddress(String userInput) {
        if (WUtil.getExchangeAddressList().contains(userInput) && !getSActivity().mChainConfig.addressPrefix().equalsIgnoreCase("cosmos")) {
            return false;
        } else {
            return true;
        }
    }

    private SendActivity getSActivity() {
        return (SendActivity) getBaseActivity();
    }

    private final ActivityResultLauncher<Intent> qrCodeResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            mAddressInput.setText(result.getData().getStringExtra(Intents.Scan.RESULT).trim());
            mAddressInput.setSelection(mAddressInput.getText().length());
        }
    });

    private void onCheckNameService(String userInput, ChainConfig chainConfig) {
        QueryGrpc.QueryStub mStub = QueryGrpc.newStub(ChannelBuilder.getChain(BaseChain.IOV_MAIN)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
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
                        getParentFragmentManager().setFragmentResultListener(StarNameConfirmDialog.STAR_NAME_CONFIRM_BUNDLE_KEY, SendStep0Fragment.this, (requestKey, bundle) -> {
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

    private void onSortToChain() {
        mToSendableChains.sort((o1, o2) -> {
            if (o1.baseChain().equals(getSActivity().mBaseChain)) return -1;
            if (o2.baseChain().equals(getSActivity().mBaseChain)) return 1;
            if (o1.baseChain().equals(BaseChain.COSMOS_MAIN)) return -1;
            if (o2.baseChain().equals(BaseChain.COSMOS_MAIN)) return 1;
            if (o1.baseChain().equals(BaseChain.OSMOSIS_MAIN)) return -1;
            if (o2.baseChain().equals(BaseChain.OSMOSIS_MAIN)) return 1;
            return 0;
        });
    }
}
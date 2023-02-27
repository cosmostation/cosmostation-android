package wannabit.io.cosmostaion.fragment.txs.common;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EVM_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_ICNS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARGAZE_NS;
import static wannabit.io.cosmostaion.dao.NameService.NameServiceType;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
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
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentResultListener;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.grpc.stub.StreamObserver;
import starnamed.x.starname.v1beta1.QueryGrpc;
import starnamed.x.starname.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.QRcodeActivity;
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.MintscanToken;
import wannabit.io.cosmostaion.dao.NameService;
import wannabit.io.cosmostaion.databinding.FragmentSendStep0Binding;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.dialog.IBCReceiveAccountsDialog;
import wannabit.io.cosmostaion.dialog.NameConfirmDialog;
import wannabit.io.cosmostaion.dialog.SelectChainListDialog;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.OsmosisCheckIcnsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.StargazeCheckNSGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class SendStep0Fragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private FragmentSendStep0Binding fragmentSendStep0Binding;

    private ArrayList<ChainConfig> mToSendableChains = new ArrayList<>();
    private ChainConfig mToSendChainConfig;
    private ArrayList<Account> mToAccountList;
    private Asset mAsset;
    private MintscanToken mMintscanToken;

    private String mUserInput;

    public static SendStep0Fragment newInstance() {
        return new SendStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentSendStep0Binding = fragmentSendStep0Binding.inflate(inflater, container, false);

        fragmentSendStep0Binding.btnToChainList.setOnClickListener(this);
        fragmentSendStep0Binding.btnCancel.setOnClickListener(this);
        fragmentSendStep0Binding.btnNext.setOnClickListener(this);
        fragmentSendStep0Binding.btnQr.setOnClickListener(this);
        fragmentSendStep0Binding.btnPaste.setOnClickListener(this);
        fragmentSendStep0Binding.btnWallet.setOnClickListener(this);

        mAsset = getBaseDao().getAsset(getSActivity().mChainConfig, getSActivity().mDenom);
        mMintscanToken = getBaseDao().getCw20Asset(getSActivity().mChainConfig, getSActivity().mDenom);
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

            } else if (mMintscanToken != null) {
                if (asset.counter_party != null && asset.counter_party.denom.equalsIgnoreCase(mMintscanToken.address)) {
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

        fragmentSendStep0Binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private boolean alreadyOpen;
            private final int defaultKeyboardHeightDP = 100;
            private final int EstimatedKeyboardDP = defaultKeyboardHeightDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
            private final Rect rect = new Rect();

            @Override
            public void onGlobalLayout() {
                int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, fragmentSendStep0Binding.getRoot().getResources().getDisplayMetrics());
                fragmentSendStep0Binding.getRoot().getWindowVisibleDisplayFrame(rect);
                int heightDiff = fragmentSendStep0Binding.getRoot().getRootView().getHeight() - (rect.bottom - rect.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;
                if (isShown == alreadyOpen) {
                    return;
                }
                alreadyOpen = isShown;
                if (alreadyOpen) {
                    fragmentSendStep0Binding.ibcLayer.setVisibility(View.GONE);
                } else {
                    new Handler().postDelayed(() -> {
                        if (mToSendChainConfig.baseChain().equals(getSActivity().mBaseChain))
                            fragmentSendStep0Binding.ibcLayer.setVisibility(View.GONE);
                        else fragmentSendStep0Binding.ibcLayer.setVisibility(View.VISIBLE);

                    }, 100);
                }
            }
        });
        return fragmentSendStep0Binding.getRoot();
    }

    private void onUpdateChainView() {
        fragmentSendStep0Binding.imgToChain.setImageResource(mToSendChainConfig.chainImg());
        fragmentSendStep0Binding.txtToChain.setText(mToSendChainConfig.chainTitleToUp());
        fragmentSendStep0Binding.txtToChain.setTextColor(ContextCompat.getColor(getActivity(), mToSendChainConfig.chainColor()));
        fragmentSendStep0Binding.receiverAccount.setText("");
        if (mToSendChainConfig.baseChain().equals(getSActivity().mBaseChain))
            fragmentSendStep0Binding.ibcLayer.setVisibility(View.GONE);
        else fragmentSendStep0Binding.ibcLayer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefreshTab() {
        String userInput = String.valueOf(fragmentSendStep0Binding.receiverAccount.getText()).trim();
        if (userInput.contains(".")) {
            fragmentSendStep0Binding.receiverAccount.setText(userInput.replaceFirst(".$", ""));
            fragmentSendStep0Binding.receiverAccount.setSelection(userInput.replaceFirst(".$", "").length());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fragmentSendStep0Binding.btnToChainList)) {
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

        } else if (v.equals(fragmentSendStep0Binding.btnNext)) {
            String userInput = String.valueOf(fragmentSendStep0Binding.receiverAccount.getText()).trim();
            mNameServices.clear();

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
                if (userInput.contains("." + mToSendChainConfig.addressPrefix())) {
                    mUserInput = userInput;
                } else if (userInput.contains(".")) {
                    mUserInput = userInput + mToSendChainConfig.addressPrefix();
                } else {
                    mUserInput = userInput + "." + mToSendChainConfig.addressPrefix();
                }
                onFetchCheckICNS(mUserInput);
                onPathSetting();
            }

        } else if (v.equals(fragmentSendStep0Binding.btnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(fragmentSendStep0Binding.btnWallet)) {
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
                    fragmentSendStep0Binding.receiverAccount.setText(mToAccountList.get(result).address);
                });
            }

        } else if (v.equals(fragmentSendStep0Binding.btnQr)) {
            IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
            integrator.setOrientationLocked(true);
            integrator.setCaptureActivity(QRcodeActivity.class);
            qrCodeResultLauncher.launch(integrator.createScanIntent());

        } else if (v.equals(fragmentSendStep0Binding.btnPaste)) {
            ClipboardManager clipboard = (ClipboardManager) getSActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                String userPaste = String.valueOf(clipboard.getPrimaryClip().getItemAt(0).coerceToText(getSActivity())).trim();
                if (TextUtils.isEmpty(userPaste)) {
                    Toast.makeText(getSActivity(), R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                    return;
                }
                fragmentSendStep0Binding.receiverAccount.setText(userPaste);
                fragmentSendStep0Binding.receiverAccount.setSelection(fragmentSendStep0Binding.receiverAccount.getText().length());

            } else {
                Toast.makeText(getSActivity(), R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onPathSetting() {
        if (getSActivity().mBaseChain.equals(mToSendChainConfig.baseChain())) {
            if (mMintscanToken == null) getSActivity().mTxType = CONST_PW_TX_SIMPLE_SEND;
            else {
                if (mMintscanToken.address.startsWith("0x")) {
                    getSActivity().mTxType = CONST_PW_TX_EVM_TRANSFER;
                } else {
                    getSActivity().mTxType = CONST_PW_TX_EXECUTE_CONTRACT;
                }
            }

        } else {
            if (mAsset != null) {
                getSActivity().mTxType = CONST_PW_TX_IBC_TRANSFER;
            } else if (mMintscanToken != null) {
                getSActivity().mTxType = CONST_PW_TX_IBC_CONTRACT;
            }
            getSActivity().mAssetPath = WDp.getAssetPath(getBaseDao(), getSActivity().mChainConfig, mToSendChainConfig, getSActivity().mDenom);
        }
        getSActivity().mAsset = mAsset;
        getSActivity().mMintscanToken = mMintscanToken;
    }

    private boolean isExchangeAddress(String userInput) {
        if (WUtil.getExchangeAddressList().contains(userInput) && !userInput.startsWith(getSActivity().mChainConfig.addressPrefix())) {
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
            fragmentSendStep0Binding.receiverAccount.setText(result.getData().getStringExtra(Intents.Scan.RESULT).trim());
            fragmentSendStep0Binding.receiverAccount.setSelection(fragmentSendStep0Binding.receiverAccount.getText().length());
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
                    mNameServices.add(new NameService(NameServiceType.STARNAME, userInput, matchAddress));

                    Bundle bundleData = new Bundle();
                    bundleData.putSerializable(NameConfirmDialog.MATCH_NAME_SERVICE_BUNDLE_KEY, mNameServices);

                    if (!getSActivity().isFinishing()) {
                        NameConfirmDialog dialog = NameConfirmDialog.newInstance(bundleData);
                        dialog.show(getParentFragmentManager(), NameConfirmDialog.class.getName());
                        getParentFragmentManager().setFragmentResultListener(NameConfirmDialog.CONFIRM_BUNDLE_KEY, SendStep0Fragment.this, (requestKey, bundle) -> {
                            if (getSActivity().mAccount.address.equalsIgnoreCase(matchAddress)) {
                                Toast.makeText(getContext(), R.string.error_self_sending, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                return;
                            }

                            if (!WDp.isValidChainAddress(mToSendChainConfig, matchAddress)) {
                                Toast.makeText(getContext(), R.string.error_invalid_icns_address, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                return;
                            }

                            int result = bundle.getInt(BaseConstant.POSITION);
                            String originAddress = mNameServices.get(result).address;
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

    private int mTaskCount = 0;
    private ArrayList<NameService> mNameServices = new ArrayList<>();
    private void onFetchCheckICNS(String userInput) {
        if (mToSendChainConfig.baseChain().equals(BaseChain.STARGAZE_MAIN)) {
            mTaskCount = 2;
            new OsmosisCheckIcnsGrpcTask(getBaseApplication(), this, mToSendChainConfig, userInput).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new StargazeCheckNSGrpcTask(getBaseApplication(), this, mToSendChainConfig, userInput.substring(0, userInput.indexOf("."))).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            mTaskCount = 1;
            new OsmosisCheckIcnsGrpcTask(getBaseApplication(), this, mToSendChainConfig, userInput).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_OSMOSIS_ICNS) {
            if (result.isSuccess && result.resultData != null) {
                String matchAddress = (String) result.resultData;
                if (!TextUtils.isEmpty(matchAddress)) {
                    mNameServices.add(new NameService(NameServiceType.ICNS, mUserInput, matchAddress));
                }
            }

        } else if (result.taskType == TASK_GRPC_FETCH_STARGAZE_NS) {
            if (result.isSuccess && result.resultData != null) {
                String matchAddress = (String) result.resultData;
                if (!TextUtils.isEmpty(matchAddress)) {
                    mNameServices.add(new NameService(NameServiceType.STARGAZE, mUserInput, matchAddress));
                }
            }
        }

        if (mTaskCount == 0) {
            if (mNameServices.size() <= 0) {
                Toast.makeText(getContext(), R.string.error_invalid_icns, Toast.LENGTH_SHORT).show();
                return;
            }
            if (mNameServices.size() == 2 && mNameServices.get(0).address.equalsIgnoreCase(mNameServices.get(1).address)) {
                mNameServices.get(0).type = NameServiceType.ICNS_STARGAZE;
                mNameServices.remove(mNameServices.size() - 1);
            }

            Bundle bundleData = new Bundle();
            bundleData.putSerializable(NameConfirmDialog.MATCH_NAME_SERVICE_BUNDLE_KEY, mNameServices);

            if (!getSActivity().isFinishing()) {
                NameConfirmDialog dialog = NameConfirmDialog.newInstance(bundleData);
                dialog.show(getParentFragmentManager(), NameConfirmDialog.class.getName());
                getParentFragmentManager().setFragmentResultListener(NameConfirmDialog.CONFIRM_BUNDLE_KEY, SendStep0Fragment.this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                        int position = bundle.getInt(BaseConstant.POSITION);
                        String matchAddress = mNameServices.get(position).address;

                        if (SendStep0Fragment.this.getSActivity().mAccount.address.equalsIgnoreCase(matchAddress)) {
                            Toast.makeText(SendStep0Fragment.this.getContext(), R.string.error_self_sending, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            return;
                        }

                        if (!WDp.isValidChainAddress(mToSendChainConfig, matchAddress)) {
                            Toast.makeText(SendStep0Fragment.this.getContext(), R.string.error_invalid_icns_address, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            return;
                        }

                        SendStep0Fragment.this.getSActivity().mToAddress = matchAddress;
                        SendStep0Fragment.this.getSActivity().onNextStep();
                    }
                });
            }
        }
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
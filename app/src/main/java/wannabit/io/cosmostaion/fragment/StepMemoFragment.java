package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

import org.bitcoinj.crypto.MnemonicCode;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.utils.WUtil;

public class StepMemoFragment extends BaseFragment implements View.OnClickListener {

    private EditText mMemo;
    private TextView mMemoCnt;
    private LinearLayout mMemoWranLayer;

    private Button mBeforeBtn, mNextBtn;
    private LinearLayout mBtnQr, mBtnPaste;

    private View rootView;

    public static StepMemoFragment newInstance() {
        return new StepMemoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tx_step_memo, container, false);
        mMemo = rootView.findViewById(R.id.et_memo);
        mMemoCnt = rootView.findViewById(R.id.tv_memoCnt);
        mMemoWranLayer = rootView.findViewById(R.id.memo_warn_layer);

        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBtnQr = rootView.findViewById(R.id.btn_qr);
        mBtnPaste = rootView.findViewById(R.id.btn_paste);

        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);

        mMemoCnt.setText("0" + "/" + WUtil.getMaxMemoSize(getSActivity().mBaseChain) + " byte");

        mMemo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String memo = String.valueOf(mMemo.getText()).trim();
                if (WUtil.getCharSize(memo) < WUtil.getMaxMemoSize(getSActivity().mBaseChain)) {
                    mMemo.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mMemoCnt.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorGray1));
                } else {
                    mMemo.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mMemoCnt.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorRed));
                }
                mMemoCnt.setText("" + WUtil.getCharSize(memo) + "/" + WUtil.getMaxMemoSize(getSActivity().mBaseChain) + " byte");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String memo = String.valueOf(mMemo.getText()).trim();
                if (WUtil.getCharSize(memo) < WUtil.getMaxMemoSize(getSActivity().mBaseChain)) {
                    mMemo.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box));
                    mMemoCnt.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorGray1));
                } else {
                    mMemo.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.edittext_box_error));
                    mMemoCnt.setTextColor(ContextCompat.getColor(getSActivity(), R.color.colorRed));
                }
                mMemoCnt.setText("" + WUtil.getCharSize(memo) + "/" + WUtil.getMaxMemoSize(getSActivity().mBaseChain) + " byte");
            }
        });
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        viewSetting(rootView);
    }

    private void viewSetting(View rootView) {
        if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_SEND || getSActivity().mTxType == CONST_PW_TX_IBC_TRANSFER ||
                getSActivity().mTxType == CONST_PW_TX_EXECUTE_CONTRACT || getSActivity().mTxType == CONST_PW_TX_IBC_CONTRACT) {
            mMemoWranLayer.setVisibility(View.VISIBLE);
            mBtnQr.setVisibility(View.VISIBLE);
            mBtnPaste.setVisibility(View.VISIBLE);

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
                        mMemoWranLayer.setVisibility(View.GONE);
                    } else {
                        new Handler().postDelayed(() -> mMemoWranLayer.setVisibility(View.VISIBLE), 100);
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            String memo = String.valueOf(mMemo.getText()).trim();
            if (getSActivity().mToAddress != null && memo.isEmpty()) {
                if (!isExchangeAddressMemo()) {
                    CommonAlertDialog.showSingleButton(getActivity(), Html.fromHtml("<font color=\"#f31963\">" + getString(R.string.str_empty_warnning_title) + "</font>", Html.FROM_HTML_MODE_COMPACT),
                            getString(R.string.error_exchange_address_memo_msg), getString(R.string.str_confirm), null, false);
                } else {
                    getSActivity().mTxMemo = String.valueOf(mMemo.getText()).trim();
                    getSActivity().onNextStep();
                }

            } else if (WUtil.getCharSize(memo) < WUtil.getMaxMemoSize(getSActivity().mBaseChain)) {
                if (!isMemohasMnemonic(memo)) {
                    getSActivity().mTxMemo = String.valueOf(mMemo.getText()).trim();
                    getSActivity().onNextStep();
                } else {
                    CommonAlertDialog.showHeaderImageDoubleButton(getSActivity(), CommonAlertDialog.highlightingText(getString(R.string.str_mnemonics_warning_title)),
                            getString(R.string.str_mnemonics_warning_msg),
                            CommonAlertDialog.highlightingText(getString(R.string.str_enter_again)),
                            View -> mMemo.setText(""),
                            getString(R.string.str_Ignore), View -> {
                                getSActivity().mTxMemo = String.valueOf(mMemo.getText()).trim();
                                getSActivity().onNextStep();
                            }, R.drawable.img_mnemonic_warning);
                }

            } else {
                Toast.makeText(getContext(), R.string.error_invalid_memo, Toast.LENGTH_SHORT).show();
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
                mMemo.setText(userPaste);
                mMemo.setSelection(mMemo.getText().length());

            } else {
                Toast.makeText(getSActivity(), R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private BaseBroadCastActivity getSActivity() {
        return (BaseBroadCastActivity) getBaseActivity();
    }

    public boolean isMemohasMnemonic(String memo) {
        Boolean result = false;
        int matchedCnt = 0;
        ArrayList<String> mAllMnemonic = new ArrayList<String>(MnemonicCode.INSTANCE.getWordList());
        String userMemo = memo.replace(" ", "");

        for (int i = 0; i < mAllMnemonic.size(); i++) {
            if (userMemo.contains(mAllMnemonic.get(i))) {
                matchedCnt++;
            }
        }
        if (matchedCnt > 10) {
            result = true;
        }

        return result;
    }

    private boolean isExchangeAddressMemo() {
        if (WUtil.getExchangeAddressList().contains(getSActivity().mToAddress)) {
            return false;
        } else {
            return true;
        }
    }

    private final ActivityResultLauncher<Intent> qrCodeResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            mMemo.setText(result.getData().getStringExtra(Intents.Scan.RESULT).trim());
            mMemo.setSelection(mMemo.getText().length());
        }
    });
}

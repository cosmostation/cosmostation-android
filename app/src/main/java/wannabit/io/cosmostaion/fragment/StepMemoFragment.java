package wannabit.io.cosmostaion.fragment;

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

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.bitcoinj.crypto.MnemonicCode;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.utils.WUtil;

public class StepMemoFragment extends BaseFragment implements View.OnClickListener {

    public final static int AGAIN_MEMO = 9500;

    private EditText mMemo;
    private TextView mMemoCnt;
    private LinearLayout mMemoWranLayer;

    private Button mBeforeBtn, mNextBtn;
    private LinearLayout mBtnQr, mBtnPaste;

    public static StepMemoFragment newInstance(Bundle bundle) {
        StepMemoFragment fragment = new StepMemoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tx_step_memo, container, false);
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
                String memo = mMemo.getText().toString().trim();
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
                String memo = mMemo.getText().toString().trim();
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

        if (isTransfer()) {
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
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mMemoWranLayer.setVisibility(View.VISIBLE);
                            }
                        }, 100);
                    }
                }
            });
        }
        return rootView;
    }

    public boolean isTransfer() {
        boolean result;
        if (getSActivity().mTxType == CONST_PW_TX_SIMPLE_SEND) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();
        } else if (v.equals(mNextBtn)) {
            String memo = mMemo.getText().toString().trim();
            if (WUtil.getCharSize(memo) < WUtil.getMaxMemoSize(getSActivity().mBaseChain)) {
                if (!isMemohasMenomic(memo)) {
                    getSActivity().mTxMemo = mMemo.getText().toString().trim();
                    getSActivity().onNextStep();
                } else {
                    AlertDialogUtils.showHeaderImageDoubleButtonDialog(getSActivity(), AlertDialogUtils.highlightingText(getString(R.string.str_mnemonics_warning_title)),
                            getString(R.string.str_mnemonics_warning_msg),
                            AlertDialogUtils.highlightingText(getString(R.string.str_enter_again)), View -> {
                                Intent resultIntent = new Intent();
                                resultIntent.putExtra("memo", 0);
                                onActivityResult(AGAIN_MEMO, Activity.RESULT_OK, resultIntent);
                            },
                            getString(R.string.str_Ignore), View -> {
                                Intent resultIntent = new Intent();
                                resultIntent.putExtra("memo", 1);
                                onActivityResult(AGAIN_MEMO, Activity.RESULT_OK, resultIntent);
                            }, R.drawable.img_mnemonic_warning);
                }
            } else {
                Toast.makeText(getContext(), R.string.error_invalid_memo, Toast.LENGTH_SHORT).show();
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

    public boolean isMemohasMenomic(String memo) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                mMemo.setText(result.getContents().trim());
                mMemo.setSelection(mMemo.getText().length());
            }

        } else if (requestCode == AGAIN_MEMO && resultCode == Activity.RESULT_OK) {
            if (data.getIntExtra("memo", -1) == 0) {
                mMemo.setText("");
            } else if (data.getIntExtra("memo", -1) == 1) {
                getSActivity().mTxMemo = mMemo.getText().toString().trim();
                getSActivity().onNextStep();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

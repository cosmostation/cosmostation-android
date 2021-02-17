package wannabit.io.cosmostaion.activities;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_Event_Confirm;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_GAS_AMOUNT_HALF;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_KAVA_GAS_AMOUNT_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_COSMOS_EVENT_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_KAVA_EVENT_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class EventStakeDropActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mRootView;
    private Toolbar mToolbar;
    private ImageView mTitleImg;
    private EditText mMemo;
    private Button mCancelBtn, mConfirmBtn;
    private LinearLayout mBtnQr, mBtnPaste;
    private TextView mFeeAmount, mFeeAmountDenom, mSendAmount, mSendAmountDenom, mRecipient;
    private LinearLayout mWarnLayer;

    public ArrayList<Coin> mSendCoins = new ArrayList<>();
    public Fee mSendFee;
    public String mSendAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_stakedrop);
        mRootView = findViewById(R.id.root_view);
        mToolbar = findViewById(R.id.tool_bar);
        mTitleImg = findViewById(R.id.img_title);
        mMemo = findViewById(R.id.et_memo);
        mCancelBtn = findViewById(R.id.btn_cancel);
        mConfirmBtn = findViewById(R.id.btn_confirm);
        mBtnQr = findViewById(R.id.btn_qr);
        mBtnPaste = findViewById(R.id.btn_paste);
        mFeeAmount = findViewById(R.id.fees_amount);
        mFeeAmountDenom = findViewById(R.id.fees_denom);
        mSendAmount = findViewById(R.id.send_amount);
        mSendAmountDenom = findViewById(R.id.send_amount_denom);
        mRecipient = findViewById(R.id.recipient_address);
        mWarnLayer = findViewById(R.id.warn_layer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mBtnQr.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        setInitEventData();
        onInitView();

        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private boolean alreadyOpen;
            private final int defaultKeyboardHeightDP = 100;
            private final int EstimatedKeyboardDP = defaultKeyboardHeightDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
            private final Rect rect = new Rect();

            @Override
            public void onGlobalLayout() {
                int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, mRootView.getResources().getDisplayMetrics());
                mRootView.getWindowVisibleDisplayFrame(rect);
                int heightDiff = mRootView.getRootView().getHeight() - (rect.bottom - rect.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;
                if (isShown == alreadyOpen) {
                    return;
                }
                alreadyOpen = isShown;
                if (alreadyOpen) {
                    mWarnLayer.setVisibility(View.GONE);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mWarnLayer.setVisibility(View.VISIBLE);
                        }
                    },100);
                }
            }
        });
        mWarnLayer.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onInitView() {
        WDp.showCoinDp(getBaseContext(), mSendCoins.get(0), mSendAmountDenom, mSendAmount, mBaseChain);
        WDp.showCoinDp(getBaseContext(), mSendFee.amount.get(0), mFeeAmountDenom, mFeeAmount, mBaseChain);
        mRecipient.setText(mSendAddress);
    }



    private void setInitEventData() {
        if (mBaseChain.equals(COSMOS_MAIN)) {
            mSendAddress = PERSISTENCE_COSMOS_EVENT_ADDRESS;

            mSendCoins.clear();
            Coin atom = new Coin(TOKEN_ATOM, "1000");
            mSendCoins.add(atom);

            Fee fee = new Fee();
            Coin gasCoin = new Coin();
            gasCoin.denom = TOKEN_ATOM;
            gasCoin.amount = "2500";
            ArrayList<Coin> amount = new ArrayList<>();
            amount.add(gasCoin);
            fee.amount = amount;
            fee.gas = FEE_GAS_AMOUNT_HALF;
            mSendFee = fee;

        } else if (mBaseChain.equals(KAVA_MAIN)) {
            mSendAddress = PERSISTENCE_KAVA_EVENT_ADDRESS;

            mSendCoins.clear();
            Coin atom = new Coin(TOKEN_KAVA, "1000");
            mSendCoins.add(atom);

            Fee fee = new Fee();
            Coin gasCoin = new Coin();
            gasCoin.denom = TOKEN_KAVA;
            gasCoin.amount = "5000";
            ArrayList<Coin> amount = new ArrayList<>();
            amount.add(gasCoin);
            fee.amount = amount;
            fee.gas = FEE_KAVA_GAS_AMOUNT_SEND;
            mSendFee = fee;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnQr)) {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();

        } else if (v.equals(mBtnPaste)) {
            ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            if(clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(getBaseContext()).toString().trim();
                if(TextUtils.isEmpty(userPaste)) {
                    Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                    return;
                }
                mMemo.setText(userPaste);
                mMemo.setSelection(mMemo.getText().length());

            } else {
                Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
            }

        } else if (v.equals(mCancelBtn)) {
            onBackPressed();

        } else if (v.equals(mConfirmBtn)) {
            String userInput = mMemo.getText().toString().trim();
            if (userInput.length() > 40 && userInput.startsWith("0x")) {
                Bundle bundle = new Bundle();
                bundle.putString("eventAddress", userInput);
                Dialog_Event_Confirm dialog = Dialog_Event_Confirm.newInstance(bundle);
                dialog.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

            } else {
                Toast.makeText(this, R.string.error_invalid_address_eth_target, Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void onStartEvent() {
        Intent intent = new Intent(EventStakeDropActivity.this, PasswordCheckActivity.class);
        intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_TX_SIMPLE_SEND);
        intent.putExtra("toAddress", mSendAddress);
        intent.putParcelableArrayListExtra("amount", mSendCoins);
        intent.putExtra("memo", mMemo.getText().toString().trim());
        intent.putExtra("fee", mSendFee);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                mMemo.setText(result.getContents().trim());
                mMemo.setSelection(mMemo.getText().length());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

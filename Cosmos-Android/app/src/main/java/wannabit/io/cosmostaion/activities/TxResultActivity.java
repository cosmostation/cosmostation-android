package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_MoreWait;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbTxInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;

public class TxResultActivity extends BaseActivity implements View.OnClickListener {

    private int                         mTxType;
    private boolean                     mIsSuccess;
    private int                         mErrorCode;
    private String                      mErrorMsg;
    private String                      mTxHash;

    private ResTxInfo                   mResTxInfo;
    private ResBnbTxInfo                mResBnbTxInfo;

    private ImageView                   mChainBg;

    private TextView                    mToolbarTitle;
    private ImageView                   mToolbarClose;

    private Button                      mBtnDismiss;
    private LinearLayout                mBottomAfterLayer;
    private Button                      mBtnScan, mBtnShare, mBtnOk;

    private NestedScrollView            mScrollLayer;

    private TextView                    mTvtxType, mTvTxHash, mTxTime, mTxBlockHeight;

    private LinearLayout                mSendLayer;
    private TextView                    mSendAmount, mSendFee, mRecipientAddress, mSendMemo;

    private LinearLayout                mDelegateLayer;
    private TextView                    mDelegateAmount, mDelegateFee, mDelegateValidator, mDelegateMemo;

    private LinearLayout                mUndelegateLayer;
    private TextView                    mUndelegateAmount, mUndelegateFee, mUndelegateFrom, mExpectedDate, mUndelegateMemo;

    private LinearLayout                mRewardLayer;
    private TextView                    mRewardFee, mRewardFrom, mRewardMemo;

    private LinearLayout                mRedelegateLayer;
    private TextView                    mRedelegateAmount, mRedelegateFee, mRedelegateFrom, mRedelegateTo, mRedelegateMemo;

    private LinearLayout                mRewardAddressChangeLayer;
    private TextView                    mRewardAddressChangeFee, mNewRewardAddress, mRewardAddressChangeMemo;

    private LinearLayout                mReinvestLayer;
    private TextView                    mReinvestAmount, mReinvestFee, mReinvestAddress, mReinvestMemo;

    private CardView                    mErrorCard;
    private TextView                    mErrorDetails;

    private RelativeLayout              mLoading;

    private TextView                    mDenomSendAmount, mDenomSendFee, mDenomDelegateAmount, mDenomDelegateFee,
                                        mDenomRedelegateAmount, mDenomRedelegateFee, mDenomUndelegateAmount, mDenomUndelegateFee,
                                        mDenomRewardFee, mDenomAddressChangeFee, mDenomReinvestAmount, mDenomReinvestFee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx_result);
        mChainBg                        = findViewById(R.id.chain_bg);

        mToolbarTitle                   = findViewById(R.id.toolbar_title);
        mToolbarClose                   = findViewById(R.id.toolbar_close);

        mBtnDismiss                     = findViewById(R.id.btn_dismiss);
        mBottomAfterLayer               = findViewById(R.id.control_after);
        mBtnScan                        = findViewById(R.id.btn_scan);
        mBtnShare                       = findViewById(R.id.btn_share);
        mBtnOk                          = findViewById(R.id.btn_ok);

        mScrollLayer                    = findViewById(R.id.scroll_layer);

        mTvtxType                       = findViewById(R.id.tx_type);
        mTvTxHash                       = findViewById(R.id.tx_hash);
        mTxTime                         = findViewById(R.id.tx_time);
        mTxBlockHeight                  = findViewById(R.id.tx_block_height);

        mSendLayer                      = findViewById(R.id.send_layer);
        mSendAmount                     = findViewById(R.id.send_amount);
        mSendFee                        = findViewById(R.id.send_fees);
        mRecipientAddress               = findViewById(R.id.send_recipient_address);
        mSendMemo                       = findViewById(R.id.send_memo);

        mDelegateLayer                  = findViewById(R.id.delegate_layer);
        mDelegateAmount                 = findViewById(R.id.delegate_amount);
        mDelegateFee                    = findViewById(R.id.delegate_fees);
        mDelegateValidator              = findViewById(R.id.delegate_moniker);
        mDelegateMemo                   = findViewById(R.id.delegate_memo);

        mUndelegateLayer                = findViewById(R.id.undelegate_layer);
        mUndelegateAmount               = findViewById(R.id.undelegate_amount);
        mUndelegateFee                  = findViewById(R.id.undelegate_fees);
        mUndelegateFrom                 = findViewById(R.id.undelegate_moniker);
        mExpectedDate                   = findViewById(R.id.undelegate_time);
        mUndelegateMemo                 = findViewById(R.id.undelegate_memo);

        mRewardLayer                    = findViewById(R.id.reward_layer);
        mRewardFee                      = findViewById(R.id.reward_fees);
        mRewardFrom                     = findViewById(R.id.reward_moniker);
        mRewardMemo                     = findViewById(R.id.reward_memo);

        mRedelegateLayer                = findViewById(R.id.redelegate_layer);
        mRedelegateAmount               = findViewById(R.id.redelegate_amount);
        mRedelegateFee                  = findViewById(R.id.redelegate_fees);
        mRedelegateFrom                 = findViewById(R.id.from_redelegate_moniker);
        mRedelegateTo                   = findViewById(R.id.to_redelegate_moniker);
        mRedelegateMemo                 = findViewById(R.id.redelegate_memo);

        mRewardAddressChangeLayer       = findViewById(R.id.reward_address_change_layer);
        mRewardAddressChangeFee         = findViewById(R.id.reward_address_change_fees);
        mNewRewardAddress               = findViewById(R.id.new_reward_address);
        mRewardAddressChangeMemo        = findViewById(R.id.reward_address_change_memo);

        mReinvestLayer                  = findViewById(R.id.reinvest_layer);
        mReinvestAmount                 = findViewById(R.id.reinvest_atom);
        mReinvestFee                    = findViewById(R.id.reinvest_fees);
        mReinvestAddress                = findViewById(R.id.reinvest_moniker);
        mReinvestMemo                   = findViewById(R.id.reinvest_memo);

        mErrorCard                      = findViewById(R.id.error_Card);
        mErrorDetails                   = findViewById(R.id.error_details);

        mLoading                        = findViewById(R.id.loading_layer);

        mDenomSendAmount                = findViewById(R.id.send_amount_title);
        mDenomSendFee                   = findViewById(R.id.send_fee_title);
        mDenomDelegateAmount            = findViewById(R.id.delegate_amount_title);
        mDenomDelegateFee               = findViewById(R.id.delegate_fee_title);
        mDenomRedelegateAmount          = findViewById(R.id.redelegate_amount_title);
        mDenomRedelegateFee             = findViewById(R.id.redelegate_fee_title);
        mDenomUndelegateAmount          = findViewById(R.id.undelegate_amount_title);
        mDenomUndelegateFee             = findViewById(R.id.undelegate_fee_title);
        mDenomRewardFee                 = findViewById(R.id.reward_fees_title);
        mDenomAddressChangeFee          = findViewById(R.id.reward_address_change_fee_title);
        mDenomReinvestAmount            = findViewById(R.id.reinvest_amount_title);
        mDenomReinvestFee               = findViewById(R.id.reinvest_fee_title);

        mToolbarClose.setOnClickListener(this);
        mBtnDismiss.setOnClickListener(this);
        mBtnScan.setOnClickListener(this);
        mBtnShare.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccount    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain  = BaseChain.getChain(mAccount.baseChain);
        mTxType     = getIntent().getIntExtra("txType", BaseConstant.TASK_GEN_TX_SIMPLE_SEND);
        mIsSuccess  = getIntent().getBooleanExtra("isSuccess", false);
        mErrorCode  = getIntent().getIntExtra("errorCode", BaseConstant.ERROR_CODE_UNKNOWN);
        mErrorMsg   = getIntent().getStringExtra("errorMsg");
        mTxHash     = getIntent().getStringExtra("txHash");

        if(TextUtils.isEmpty(mTxHash)) {
            WLog.w("Empty hash error");
            onBackPressed();
            return;
        }

        if(mIsSuccess) {
            onFetchTx(mTxHash);
            mToolbarTitle.setText(getString(R.string.str_tx_success));

        } else {
            mLoading.setVisibility(View.GONE);
            if(mErrorCode == ERROR_CODE_BROADCAST) {
                mErrorDetails.setText(getString(R.string.error_network));
            } else {
                mErrorDetails.setText("error code : " + mErrorCode + "\n" + mErrorMsg);
            }
            mErrorCard.setVisibility(View.VISIBLE);
            mToolbarTitle.setText(getString(R.string.str_tx_failed));
        }

//        WDp.DpMainDenom(this, mAccount.baseChain, mDenomSendAmount);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomSendFee);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomDelegateAmount);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomDelegateFee);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomRedelegateAmount);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomRedelegateFee);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomUndelegateAmount);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomUndelegateFee);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomRewardFee);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomAddressChangeFee);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomReinvestAmount);
        WDp.DpMainDenom(this, mAccount.baseChain, mDenomReinvestFee);

    }

    @Override
    public void onBackPressed() {
        onStartMainActivity();
    }

    private void onUpdateView() {
        WLog.w("onUpdateTx");

        if(mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_SEND && mResTxInfo != null) {
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mSendLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_send);
            WDp.DpMainDenom(this, mAccount.baseChain, mDenomSendAmount);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.txhash);
                mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                mTxBlockHeight.setText(mResTxInfo.height);

                for (Coin coin: mResTxInfo.tx.value.msg.get(0).value.getCoins()) {
                    if (coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mSendAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                    }
                }

                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if (coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mSendFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                    }
                }

                mRecipientAddress.setText(mResTxInfo.tx.value.msg.get(0).value.to_address);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.hash);
                mTxTime.setText("-");
                mTxBlockHeight.setText(mResTxInfo.height);

                for(Coin coin: mResTxInfo.tx.value.msg.get(0).value.inputs.get(0).coins) {
                    if(coin.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                        mSendAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                }

                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                        mSendFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                }

                mRecipientAddress.setText(mResTxInfo.tx.value.msg.get(0).value.outputs.get(0).address);
            }
            mSendMemo.setText(mResTxInfo.tx.value.memo);
            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);


        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE && mResTxInfo != null) {
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mDelegateLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_delegate);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.txhash);
                mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                mTxBlockHeight.setText(mResTxInfo.height);
                mDelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResTxInfo.tx.value.msg.get(0).value.getCoins().get(0).amount), 6, BaseChain.getChain(mAccount.baseChain)));
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mDelegateFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mDelegateValidator.setText(mResTxInfo.tx.value.msg.get(0).value.validator_address);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.hash);
                mTxTime.setText("-");
                mTxBlockHeight.setText(mResTxInfo.height);
                mDelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResTxInfo.tx.value.msg.get(0).value.delegation.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                        mDelegateFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mDelegateValidator.setText(mResTxInfo.tx.value.msg.get(0).value.validator_addr);

            }
            mDelegateMemo.setText(mResTxInfo.tx.value.memo);
            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);


        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE && mResTxInfo != null) {
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mUndelegateLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_undelegate);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.txhash);
                mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                mTxBlockHeight.setText(mResTxInfo.height);
                mUndelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResTxInfo.tx.value.msg.get(0).value.getCoins().get(0).amount), 6, BaseChain.getChain(mAccount.baseChain)));
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mUndelegateFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mUndelegateFrom.setText(mResTxInfo.tx.value.msg.get(0).value.validator_address);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.hash);
                mTxTime.setText("-");
                mTxBlockHeight.setText(mResTxInfo.height);
                mUndelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResTxInfo.tx.value.msg.get(0).value.shares_amount), 18, BaseChain.getChain(mAccount.baseChain)));
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                        mUndelegateFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mUndelegateFrom.setText(mResTxInfo.tx.value.msg.get(0).value.validator_addr);
            }
            mUndelegateMemo.setText(mResTxInfo.tx.value.memo);
            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);


        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_REWARD && mResTxInfo != null){
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mRewardLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_get_reward);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.txhash);
                mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                mTxBlockHeight.setText(mResTxInfo.height);
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mRewardFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                String from = "";
                for(Msg msg: mResTxInfo.tx.value.msg) {
                    if(TextUtils.isEmpty(from)) {
                        from = msg.value.validator_address;
                    } else {
                        from = from + "\n" + msg.value.validator_address;
                    }
                }
                mRewardFrom.setText(from);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.hash);
                mTxTime.setText("-");
                mTxBlockHeight.setText(mResTxInfo.height);
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                        mRewardFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                if (mResTxInfo.tx.value.msg.get(0).type.equals(BaseConstant.IRIS_MSG_TYPE_WITHDRAW_ALL)) {
                    mTvtxType.setText(R.string.tx_get_reward_all);
                    mRewardFrom.setText(R.string.str_from_all_my_val);
                } else if (mResTxInfo.tx.value.msg.get(0).type.equals(BaseConstant.IRIS_MSG_TYPE_WITHDRAW)) {
                    mRewardFrom.setText(mResTxInfo.tx.value.msg.get(0).value.validator_addr);
                }
            }
            mRewardMemo.setText(mResTxInfo.tx.value.memo);
            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);


        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_REDELEGATE && mResTxInfo != null){
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mRedelegateLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_redelegate);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.txhash);
                mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                mTxBlockHeight.setText(mResTxInfo.height);
                mRedelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResTxInfo.tx.value.msg.get(0).value.getCoins().get(0).amount), 6, BaseChain.getChain(mAccount.baseChain)));
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mRedelegateFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mRedelegateFrom.setText(mResTxInfo.tx.value.msg.get(0).value.validator_src_address);
                mRedelegateTo.setText(mResTxInfo.tx.value.msg.get(0).value.validator_dst_address);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.hash);
                mTxTime.setText("-");
                mTxBlockHeight.setText(mResTxInfo.height);
                mRedelegateAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResTxInfo.tx.value.msg.get(0).value.shares_amount), 18, BaseChain.getChain(mAccount.baseChain)));
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                        mRedelegateFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mRedelegateFrom.setText(mResTxInfo.tx.value.msg.get(0).value.validator_src_addr);
                mRedelegateTo.setText(mResTxInfo.tx.value.msg.get(0).value.validator_dst_addr);
            }
            mRedelegateMemo.setText(mResTxInfo.tx.value.memo);
            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);


        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_REWARD_ADDRESS_CHANGE && mResTxInfo != null) {
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mRewardAddressChangeLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_change_reward_address);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.txhash);
                mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                mTxBlockHeight.setText(mResTxInfo.height);
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mRewardAddressChangeFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mNewRewardAddress.setText(mResTxInfo.tx.value.msg.get(0).value.withdraw_address);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.hash);
                mTxTime.setText("-");
                mTxBlockHeight.setText(mResTxInfo.height);
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                        mRewardAddressChangeFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mNewRewardAddress.setText(mResTxInfo.tx.value.msg.get(0).value.withdraw_addr);

            }
            mRewardAddressChangeMemo.setText(mResTxInfo.tx.value.memo);
            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);


        } else if (mTxType == BaseConstant.TASK_GEN_TX_REINVEST && mResTxInfo != null) {
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mReinvestLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_reinvest);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.txhash);
                mTxTime.setText(WDp.getTimeTxformat(getBaseContext(), mResTxInfo.timestamp));
                mTxBlockHeight.setText(mResTxInfo.height);
                mReinvestAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResTxInfo.tx.value.msg.get(1).value.getCoins().get(0).amount), 6, BaseChain.getChain(mAccount.baseChain)));
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        mReinvestFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mReinvestAddress.setText(mResTxInfo.tx.value.msg.get(0).value.validator_address);

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                mTvTxHash.setText(mResTxInfo.hash);
                mTxTime.setText("-");
                mTxBlockHeight.setText(mResTxInfo.height);
                mReinvestAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResTxInfo.tx.value.msg.get(1).value.delegation.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                    if(coin.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                        mReinvestFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                }
                mReinvestAddress.setText(mResTxInfo.tx.value.msg.get(0).value.validator_addr);
            }
            mReinvestMemo.setText(mResTxInfo.tx.value.memo);
            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);

        } else if (mTxType == BaseConstant.TASK_GEN_TX_BNB_SIMPLE_SEND && mResBnbTxInfo != null) {
            mLoading.setVisibility(View.GONE);
            mScrollLayer.setVisibility(View.VISIBLE);
            mSendLayer.setVisibility(View.VISIBLE);

            mTvtxType.setText(R.string.tx_send);
            mTvTxHash.setText(mResBnbTxInfo.hash);
            mTxTime.setText("-");
            mTxBlockHeight.setText(mResBnbTxInfo.height);

            Coin sendToken = mResBnbTxInfo.tx.value.msg.get(0).value.inputs.get(0).coins.get(0);
            mDenomSendAmount.setText(sendToken.denom.toUpperCase());
            if (sendToken.denom.equals(COSMOS_BNB)) {
                mDenomSendAmount.setTextColor(getResources().getColor(R.color.colorBnb));
            }
            mSendAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(sendToken.amount).divide(new BigDecimal("100000000"), 8, RoundingMode.DOWN), 8, BaseChain.getChain(mAccount.baseChain)));
            mSendFee.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(FEE_BNB_SEND), 8, BaseChain.getChain(mAccount.baseChain)));

            mRecipientAddress.setText(mResBnbTxInfo.tx.value.msg.get(0).value.outputs.get(0).address);
            mSendMemo.setText(mResBnbTxInfo.tx.value.memo);
            mBtnDismiss.setVisibility(View.GONE);
            mBottomAfterLayer.setVisibility(View.VISIBLE);
        }

    }

    private void onShowMoreWait() {
        Dialog_MoreWait waitMore = Dialog_MoreWait.newInstance(null);
        waitMore.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(waitMore, "dialog").commitNowAllowingStateLoss();

    }

    public void onWaitMore() {
        FetchCnt = 0;
        onFetchTx(mTxHash);
    }


    private int FetchCnt = 0;
    private void onFetchTx(String hash) {
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            ApiClient.getCosmosChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        onUpdateView();

                    } else {
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 6000);
                        } else {
                            onShowMoreWait();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    if(isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            ApiClient.getIrisChain(getBaseContext()).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
                @Override
                public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResTxInfo = response.body();
                        onUpdateView();

                    } else {
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 6000);
                        } else {
                            onShowMoreWait();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResTxInfo> call, Throwable t) {
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    if(isFinishing()) return;
                }
            });

        } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            ApiClient.getBnbChain(getBaseContext()).getSearchTx(hash, "json").enqueue(new Callback<ResBnbTxInfo>() {
                @Override
                public void onResponse(Call<ResBnbTxInfo> call, Response<ResBnbTxInfo> response) {
                    if(isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if(response.isSuccessful() && response.body() != null) {
                        mResBnbTxInfo = response.body();
                        onUpdateView();

                    } else {
                        if(mIsSuccess && FetchCnt < 10) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 6000);
                        } else {
                            onShowMoreWait();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResBnbTxInfo> call, Throwable t) {
                    WLog.w("BNB onFailure");
                    if(BaseConstant.IS_SHOWLOG) t.printStackTrace();
                    if(isFinishing()) return;
                }
            });
        }



    }

    @Override
    public void onClick(View v) {
        if (v.equals(mToolbarClose) || v.equals(mBtnOk) || v.equals(mBtnDismiss)) {
            onBackPressed();

        } else if (v.equals(mBtnScan)) {
            Intent webintent = new Intent(this, WebActivity.class);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                webintent.putExtra("txid", mResTxInfo.txhash);
            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                webintent.putExtra("txid", mResTxInfo.hash);
            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                webintent.putExtra("txid", mResBnbTxInfo.hash);
            }
            webintent.putExtra("chain", mBaseChain.getChain());
            webintent.putExtra("goMain", true);
            startActivity(webintent);


        } else if (v.equals(mBtnShare)) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.mintscan.io/txs/" + mResTxInfo.txhash);
            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://irishub.mintscan.io/txs/" + mResTxInfo.hash);
            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://irishub.mintscan.io/txs/" + mResBnbTxInfo.hash);
            }
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        }
    }

}

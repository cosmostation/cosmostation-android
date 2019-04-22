package wannabit.io.cosmostaion.activities;

import android.app.Activity;
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

import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBlockInfo;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResStakeTxInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class TxResultActivity extends BaseActivity implements View.OnClickListener {

//    private boolean                     mTimeout;
//    private ResBroadTx                  mResBroadTx;
//    private ResTxInfo                   mResTxInfo;
//    private ResBlockInfo                mResBlockInfo;
    private Account                     mAccount;
    private int                         mTxType;
    private boolean                     mIsSuccess;
    private int                         mErrorCode;
    private String                      mTxHash;

    private ResTxInfo                   mResTxInfo;
    private ResStakeTxInfo              mResStakeTxInfo;
    private ResBlockInfo                mResBlockInfo;

//    public ArrayList<Balance>           mBalances = new ArrayList<>();


    private CardView                    mRootCard;
    private NestedScrollView            mScrollLayer;
    private TextView                    mToolbarTitle;
    private ImageView                   mToolbarClose;
    private Button                      mBtnScan, mBtnShare, mBtnOk;
    private TextView                    mBlockTime, mTxError;

    private TextView                    mTvtxType, mTvTxHash;

    private LinearLayout                mSendLayer;
    private RelativeLayout              mSendAtomLayer, mSendPhotonLayer, mFeeAtomLayer, mFeePhotonLayer;
    private TextView                    mTxFrom, mTxTo, mSendAtomTitle, mSendAtom, mSendPhotonTitle, mSendPhoton,
                                        mFeeAtomTitle, mFeeAtom, mFeePhotonTitle, mFeePhoton, mFeeFree;

    private LinearLayout                mDelegateLayer;
    private TextView                    mDelegateTo, mDelegateAtomTitle, mDelegateAtom;

    private LinearLayout                mRewardLayer;
    private TextView                    mRewardFrom;

    private LinearLayout                mUndelegateLayer;
    private TextView                    mUndelegateFrom, mUndelegateAtomTitle, mUndelegateAtom;


    private TextView                    mMemo;

    private RelativeLayout              mLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx_result);

        mToolbarTitle           = findViewById(R.id.toolbar_title);
        mToolbarClose           = findViewById(R.id.toolbar_close);
        mBtnScan                = findViewById(R.id.btn_scan);
        mBtnShare               = findViewById(R.id.btn_share);
        mBtnOk                  = findViewById(R.id.btn_ok);
        mBlockTime              = findViewById(R.id.tv_block_time);
        mTxError                = findViewById(R.id.tv_block_error);

        mScrollLayer            = findViewById(R.id.scroll_layer);
        mRootCard               = findViewById(R.id.root_card);

        mTvtxType               = findViewById(R.id.tx_type);
        mTvTxHash               = findViewById(R.id.tx_hash);


        mSendLayer              = findViewById(R.id.send_content_layer);
        mTxFrom                 = findViewById(R.id.tx_from);
        mTxTo                   = findViewById(R.id.tx_to);
        mSendAtomLayer          = findViewById(R.id.send_atom_layer);
        mSendAtomTitle          = findViewById(R.id.send_atom_title);
        mSendAtom               = findViewById(R.id.send_atom);
        mSendPhotonLayer        = findViewById(R.id.send_photon_layer);
        mSendPhotonTitle        = findViewById(R.id.send_photon_title);
        mSendPhoton             = findViewById(R.id.send_photon);
        mFeeAtomLayer           = findViewById(R.id.fee_atom_layer);
        mFeeAtomTitle           = findViewById(R.id.fee_atom_title);
        mFeeAtom                = findViewById(R.id.fee_atom);
        mFeePhotonLayer         = findViewById(R.id.fee_photon_layer);
        mFeePhotonTitle         = findViewById(R.id.fee_photon_title);
        mFeePhoton              = findViewById(R.id.fee_photon);
        mFeeFree                = findViewById(R.id.fee_free);


        mDelegateLayer          = findViewById(R.id.delegate_content_layer);
        mDelegateTo             = findViewById(R.id.tx_delegateTo);
        mDelegateAtomTitle      = findViewById(R.id.delegate_atom_title);
        mDelegateAtom           = findViewById(R.id.delegate_atom);

        mRewardLayer            = findViewById(R.id.reward_content_layer);
        mRewardFrom             = findViewById(R.id.tx_reward_from);


        mUndelegateLayer        = findViewById(R.id.undelegate_content_layer);
        mUndelegateFrom         = findViewById(R.id.tx_undelegate_from);
        mUndelegateAtomTitle    = findViewById(R.id.undelegate_atom_title);
        mUndelegateAtom         = findViewById(R.id.undelegate_atom);


        mMemo                   = findViewById(R.id.tx_memo);

        mLoading                = findViewById(R.id.loading_layer);

        mToolbarClose.setOnClickListener(this);
        mBtnScan.setOnClickListener(this);
        mBtnShare.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccount    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());


        mTxType     = getIntent().getIntExtra("txType", BaseConstant.TASK_GEN_TX_SIMPLE_SEND);
        mIsSuccess  = getIntent().getBooleanExtra("isSuccess", false);
        mErrorCode  = getIntent().getIntExtra("errorCode", BaseConstant.ERROR_CODE_UNKNOWN);
        mTxHash     = getIntent().getStringExtra("txHash");

        WLog.w("mTxType : " + mTxType);
        WLog.w("mIsSuccess : " + mIsSuccess);
        WLog.w("mErrorCode : " + mErrorCode);
//        if(!TextUtils.isEmpty(mTxHash))
//            WLog.w("mTxHash : " + mTxHash);
//
////        mTxHash = "BC3AA65165833693BB6177DFA835260E7B73A6554EFD471C6892CF904A278E12";
//
//        if(!TextUtils.isEmpty(mTxHash)) {
//            WLog.w("mTxHash : " + mTxHash);
//            onFetchTx(mTxHash);
//
//        } else {
//            // TODO no hash
//        }
        if(mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE ||
                mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE) {
            onStakeFetchTx(mTxHash);
        } else {
            onFetchTx(mTxHash);
        }


        if(mIsSuccess) {
            mToolbarTitle.setText(getString(R.string.str_tx_success));
        } else {
            mToolbarTitle.setText(getString(R.string.str_tx_failed));
        }

        mDelegateAtomTitle.setText(WDp.DpAtom(getBaseContext(), mAccount.baseChain));
        mUndelegateAtomTitle.setText(WDp.DpAtom(getBaseContext(), mAccount.baseChain));

    }

    @Override
    public void onBackPressed() {
        onStartMainActivity();
    }


    //    private void onUpdateViews() {
//        WLog.w("onUpdateViews");
////        String hash = "5FD8F3AE1DDDFCDE4D45331862341C40A527B3F648AC93E2BF83DDC24F570902";
//    }

    private void onUpdateTimes() {
        WLog.w("onUpdateTimes");
        mBlockTime.setText(getString(R.string.str_tx_block_height) + " : " + mResBlockInfo.block_meta.header.height +
                "      " + getString(R.string.str_tx_block_time) + " : " + WDp.getTimeformat(getBaseContext(), mResBlockInfo.block_meta.header.time));

        mLoading.setVisibility(View.GONE);
        mScrollLayer.setVisibility(View.VISIBLE);
        mBtnScan.setClickable(true);
        mBtnShare.setClickable(true);
        mBtnOk.setClickable(true);


    }

    private void onUpdateBalances() {
        WLog.w("onUpdateBalances");
//        mBalances = getBaseDao().onSelectBalance(mAccount.id);
//        mRemindAtom.setText(WDp.getDpAtomBalance(getBaseContext(), mBalances));
//        mRemindPhoton.setText(WDp.getDpPhotonBalance(getBaseContext(), mBalances));
    }

    private void onUpdateTx() {
        WLog.w("onUpdateTx : ");
//        if(mResTxInfo == null) {
//            return;
//        }
        if(mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_SEND) {
            mSendLayer.setVisibility(View.VISIBLE);
            mTvtxType.setText(R.string.tx_send);

            mTxFrom.setText(mResTxInfo.tx.value.msg.get(0).value.from_address);
            mTxTo.setText(mResTxInfo.tx.value.msg.get(0).value.to_address);


            for(Coin coin: mResTxInfo.tx.value.msg.get(0).value.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM) || coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                    mSendAtomLayer.setVisibility(View.VISIBLE);
                    mSendAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
                if(coin.denom.equals(BaseConstant.COSMOS_PHOTON) || coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                    mSendPhotonLayer.setVisibility(View.VISIBLE);
                    mSendPhoton.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }

            mTvTxHash.setText(mResTxInfo.txhash);
            mMemo.setText(mResTxInfo.tx.value.memo);

            for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM) || coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                    mFeeAtomLayer.setVisibility(View.VISIBLE);
                    mFeeAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
                if(coin.denom.equals(BaseConstant.COSMOS_PHOTON)|| coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                    mFeePhotonLayer.setVisibility(View.VISIBLE);
                    mFeePhoton.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }


        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE){
            mDelegateLayer.setVisibility(View.VISIBLE);
            mTvtxType.setText(R.string.tx_delegate);
            mDelegateTo.setText(mResStakeTxInfo.tx.value.msg.get(0).value.validator_address);
            mDelegateAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResStakeTxInfo.tx.value.msg.get(0).value.amount.amount), 6, BaseChain.getChain(mAccount.baseChain)));
//            mDelegateAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResTxInfo.tx.value.msg.get(0).value.value.amount), 6, BaseChain.getChain(mAccount.baseChain)));

            mTvTxHash.setText(mResStakeTxInfo.txhash);
            mMemo.setText(mResStakeTxInfo.tx.value.memo);

            for(Coin coin: mResStakeTxInfo.tx.value.fee.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM) || coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                    mFeeAtomLayer.setVisibility(View.VISIBLE);
                    mFeeAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
                if(coin.denom.equals(BaseConstant.COSMOS_PHOTON)|| coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                    mFeePhotonLayer.setVisibility(View.VISIBLE);
                    mFeePhoton.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }

        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_REWARD){
            mRewardLayer.setVisibility(View.VISIBLE);
            mTvtxType.setText(R.string.tx_get_reward);
            mRewardFrom.setText(mResTxInfo.tx.value.msg.get(0).value.validator_address);

            mTvTxHash.setText(mResTxInfo.txhash);
            mMemo.setText(mResTxInfo.tx.value.memo);

            for(Coin coin: mResTxInfo.tx.value.fee.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM) || coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                    mFeeAtomLayer.setVisibility(View.VISIBLE);
                    mFeeAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
                if(coin.denom.equals(BaseConstant.COSMOS_PHOTON)|| coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                    mFeePhotonLayer.setVisibility(View.VISIBLE);
                    mFeePhoton.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }

        } else if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE){
            mUndelegateLayer.setVisibility(View.VISIBLE);
            mTvtxType.setText(R.string.tx_undelegate);
            mUndelegateFrom.setText(mResStakeTxInfo.tx.value.msg.get(0).value.validator_address);
            mUndelegateAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mResStakeTxInfo.tx.value.msg.get(0).value.amount.amount), 6, BaseChain.getChain(mAccount.baseChain)));

            mTvTxHash.setText(mResStakeTxInfo.txhash);
            mMemo.setText(mResStakeTxInfo.tx.value.memo);

            for(Coin coin: mResStakeTxInfo.tx.value.fee.amount) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM) || coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                    mFeeAtomLayer.setVisibility(View.VISIBLE);
                    mFeeAtom.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
                if(coin.denom.equals(BaseConstant.COSMOS_PHOTON)|| coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                    mFeePhotonLayer.setVisibility(View.VISIBLE);
                    mFeePhoton.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(coin.amount), 6, BaseChain.getChain(mAccount.baseChain)));
                }
            }

        }

    }


    private int FetchCnt = 0;
    private void onFetchTx(String hash) {
        ApiClient.getWannabitChain(getBaseContext(), BaseChain.getChain(mAccount.baseChain)).getSearchTx(hash).enqueue(new Callback<ResTxInfo>() {
            @Override
            public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                if(isFinishing()) return;
                WLog.w("onFetchTx " + response.toString());
                if(response.isSuccessful()) {
                    if(response.body() == null) {
                        if(mIsSuccess && FetchCnt < 5) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 2500);
                        } else {
                            //TODO finish
                            WLog.w("Looop");
                        }
                        return;
                    }
                    WLog.w("getSearchTx : " + response.body().height);
                    mResTxInfo = response.body();
                    onFetchBlock(mResTxInfo.height);
                    onUpdateTx();

                } else {
                    if(mIsSuccess && FetchCnt < 5) {
                        WLog.w("retry : " + FetchCnt + " " + mIsSuccess);
                        if(mIsSuccess && FetchCnt < 5) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onFetchTx(mTxHash);
                                }
                            }, 8000);
                        } else {
                            //TODO finish
                            WLog.w("Looop");
                        }
                        return;
                    } else {
                        WLog.w("retry : " + FetchCnt + " " + mIsSuccess);
                        onBackPressed();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResTxInfo> call, Throwable t) {
                WLog.w("onFailure " + t.getMessage());
                t.printStackTrace();
                if(isFinishing()) return;
            }
        });
    }

    private void onStakeFetchTx(String hash) {
        ApiClient.getWannabitChain(getBaseContext(), BaseChain.getChain(mAccount.baseChain)).getStakeSearchTx(hash).enqueue(new Callback<ResStakeTxInfo>() {
            @Override
            public void onResponse(Call<ResStakeTxInfo> call, Response<ResStakeTxInfo> response) {
                if(isFinishing()) return;
                WLog.w("onStakeFetchTx " + response.toString());
                if(response.isSuccessful()) {
                    if(response.body() == null) {
                        if(mIsSuccess && FetchCnt < 5) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onStakeFetchTx(mTxHash);
                                }
                            }, 2500);
                        } else {
                            //TODO finish
                            WLog.w("Looop");
                        }
                        return;
                    }
                    WLog.w("getSearchTx : " + response.body().height);
                    mResStakeTxInfo = response.body();
                    onFetchBlock(mResStakeTxInfo.height);
                    onUpdateTx();

                } else {
                    if(mIsSuccess && FetchCnt < 5) {
                        WLog.w("retry : " + FetchCnt + " " + mIsSuccess);
                        if(mIsSuccess && FetchCnt < 5) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    FetchCnt++;
                                    onStakeFetchTx(mTxHash);
                                }
                            }, 8000);
                        } else {
                            //TODO finish
                            WLog.w("Looop");
                        }
                        return;
                    } else {
                        WLog.w("retry : " + FetchCnt + " " + mIsSuccess);
                        onBackPressed();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResStakeTxInfo> call, Throwable t) {
                WLog.w("onFailure " + t.getMessage());
                t.printStackTrace();
                if(isFinishing()) return;
            }
        });
    }

    private void onFetchBlock(String height) {
        ApiClient.getWannabitChain(getBaseContext(), BaseChain.getChain(mAccount.baseChain)).getSearchBlock(height).enqueue(new Callback<ResBlockInfo>() {
            @Override
            public void onResponse(Call<ResBlockInfo> call, Response<ResBlockInfo> response) {
                if(isFinishing()) return;
                if (response.isSuccessful() && response.body() != null) {
                    WLog.w("onFetchBlock : " + response.body().block_meta.header.time);
                    mResBlockInfo = response.body();
                    onUpdateTimes();
                }
            }

            @Override
            public void onFailure(Call<ResBlockInfo> call, Throwable t) {
                if(isFinishing()) return;
            }
        });
    }

    private void onFetchSingleAccount() {
        ApiClient.getWannabitChain(getBaseContext(), BaseChain.getChain(mAccount.baseChain)).getAccountInfo(mAccount.address).enqueue(new Callback<ResLcdAccountInfo>() {
            @Override
            public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                if(response != null) {
                    getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, response.body()));
                    getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, response.body()));
                    onUpdateBalances();
                }
            }

            @Override
            public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) { }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mToolbarClose) || v.equals(mBtnOk)) {
            onBackPressed();

        } else if (v.equals(mBtnScan)) {
            if(mResStakeTxInfo == null || mResTxInfo == null) {
                return;
            }

            if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE || mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE){
                Intent webintent = new Intent(this, WebActivity.class);
                webintent.putExtra("txid", mResStakeTxInfo.txhash);
                webintent.putExtra("goMain", true);
                startActivity(webintent);
            } else {
                Intent webintent = new Intent(this, WebActivity.class);
                webintent.putExtra("txid", mResTxInfo.txhash);
                webintent.putExtra("goMain", true);
                startActivity(webintent);
            }

        } else if (v.equals(mBtnShare)) {
            if(mResStakeTxInfo == null || mResTxInfo == null) {
                return;
            }
            if (mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE || mTxType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE){
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.mintscan.io/txs/" + mResStakeTxInfo.txhash);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "send"));
            } else {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.mintscan.io/txs/" + mResTxInfo.txhash);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "send"));
            }
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        WLog.w("requestCode : " + requestCode + "   " + resultCode);
//        if (requestCode == REQ_SHARE_TX && resultCode == Activity.RESULT_OK) {
//            onStartMainActivity();
//        }
//    }

}

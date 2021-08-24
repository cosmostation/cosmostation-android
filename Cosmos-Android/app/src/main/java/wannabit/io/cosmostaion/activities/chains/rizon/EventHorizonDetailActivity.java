package wannabit.io.cosmostaion.activities.chains.rizon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.hdac.HdacTxInfo;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_SHOWLOG;

public class EventHorizonDetailActivity extends BaseBroadCastActivity implements View.OnClickListener, TaskListener {

    private Toolbar         mToolbar;
    private RelativeLayout  mLoadingLayer;
    private CardView        mCardViewLayer;
    private LinearLayout    mControlLayer;
    private TextView        mTxBlockHeight, mTxBlockTime;
    private TextView        mTxBlockHash, mTxTransHash, mTxHdacBurnAmount, mTxMintRecipientAddress;

    private Button          mDoneBtn, mExplorerBtn;

    private String          mTxHash;
    private boolean         mIsSuccess;
    private HdacTxInfo      mHdacTxInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_horizon_detail);
        mToolbar                    = findViewById(R.id.tool_bar);
        mTxBlockHeight              = findViewById(R.id.tx_block_height);
        mTxBlockTime                = findViewById(R.id.tx_block_time);
        mTxBlockHash                = findViewById(R.id.tx_block_hash);
        mTxTransHash                = findViewById(R.id.tx_transaction_hash);
        mTxHdacBurnAmount           = findViewById(R.id.tx_hdac_burn_amount);
        mTxMintRecipientAddress     = findViewById(R.id.tx_mint_recipient_address);

        mLoadingLayer               = findViewById(R.id.loadingLayer);
        mCardViewLayer              = findViewById(R.id.card_status_root);
        mControlLayer               = findViewById(R.id.control_later);

        mExplorerBtn                = findViewById(R.id.btn_explorer);
        mDoneBtn                    = findViewById(R.id.btn_done);

        mAccount                    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain                  = BaseChain.getChain(mAccount.baseChain);
        mTxHash                     = getIntent().getStringExtra("txHash");
        mIsSuccess                  = getIntent().getBooleanExtra("isSuccess", false);

        if (mIsSuccess) {
            onFetchTx(mTxHash);
        }

        mExplorerBtn.setOnClickListener(this);
        mDoneBtn.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onStartMainActivity(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onUpdateView() {
        mLoadingLayer.setVisibility(View.GONE);
        mCardViewLayer.setVisibility(View.VISIBLE);
        mControlLayer.setVisibility(View.VISIBLE);

        mTxBlockHeight.setText("" + mHdacTxInfo.blockheight);
        mTxBlockTime.setText(WDp.getDpTime(EventHorizonDetailActivity.this, mHdacTxInfo.time * 1000));

        mTxBlockHash.setText(mHdacTxInfo.blockhash);
        mTxTransHash.setText(mTxHash);
        mTxHdacBurnAmount.setText("" + mHdacTxInfo.valueOut);
        mTxMintRecipientAddress.setText(mAccount.address);
    }

    @Override
    public void onClick(View v) {
         if (v.equals(mDoneBtn)) {
             onStartMainActivity(0);

        } else if (v.equals(mExplorerBtn)) {
             String url = "";
             if (mBaseChain.equals(BaseChain.RIZON_TEST)) {
                 url = WUtil.getTxExplorer(mBaseChain, mTxHash);
             } else {
                 url = WUtil.getTxExplorer(mBaseChain, mTxHash);
             }
             Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
             startActivity(intent);
        }
    }

    private int FetchCnt = 5;

    private void onFetchTx(String hash) {
        WLog.w("hash " + hash);
        if (mBaseChain.equals(RIZON_TEST)) {
            ApiClient.getTestHdac(getBaseContext()).gethdacTxDetail(mTxHash).enqueue(new Callback<HdacTxInfo>() {
                @Override
                public void onResponse(Call<HdacTxInfo> call, Response<HdacTxInfo> response) {
                    if (isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if (response.isSuccessful() && response.body() != null) {
                        mHdacTxInfo = response.body();
                    }
                    if (mHdacTxInfo == null && FetchCnt > 0) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                FetchCnt--;
                                onFetchTx(mTxHash);
                            }
                        }, 6000);
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onUpdateView();
                            }
                        }, 500);
                    }
                }

                @Override
                public void onFailure(Call<HdacTxInfo> call, Throwable t) {
                    WLog.w("Hdac onFailure");
                }
            });

        } else {
            ApiClient.getMainHdac(getBaseContext()).gethdacTxDetail(mTxHash).enqueue(new Callback<HdacTxInfo>() {
                @Override
                public void onResponse(Call<HdacTxInfo> call, Response<HdacTxInfo> response) {
                    if (isFinishing()) return;
                    WLog.w("onFetchTx " + response.toString());
                    if (response.isSuccessful() && response.body() != null) {
                        mHdacTxInfo = response.body();
                    }
                    if (mHdacTxInfo == null && FetchCnt > 0) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                FetchCnt--;
                                onFetchTx(mTxHash);
                            }
                        }, 6000);
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onUpdateView();
                            }
                        }, 500);
                    }
                }

                @Override
                public void onFailure(Call<HdacTxInfo> call, Throwable t) {
                    WLog.w("Hdac onFailure");
                }
            });
        }
    }

}

package wannabit.io.cosmostaion.activities.tokenDetail;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import irismod.nft.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.nft.NFTSendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;

public class NFTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ImageView mNftImg;
    private RelativeLayout mBtnIbcSend, mBtnSend;

    private RecyclerView mRecyclerView;
    private NFTDetailAdapter mAdapter;

    private QueryOuterClass.QueryNFTResponse mIrisResponse;
    private chainmain.nft.v1.Nft.BaseNFT myCryptoNftInfo;

    private String mDenomId;
    private String mTokenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_nft);
        mToolbar = findViewById(R.id.tool_bar);
        mNftImg = findViewById(R.id.nft_img);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnIbcSend = findViewById(R.id.btn_ibc_send);
        mBtnSend = findViewById(R.id.btn_send);

        Window window = this.getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);;

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mIrisResponse = (QueryOuterClass.QueryNFTResponse) getIntent().getSerializableExtra("irisResponse");
        } else if (mBaseChain.equals(BaseChain.CRYPTO_MAIN)) {
            myCryptoNftInfo = (chainmain.nft.v1.Nft.BaseNFT) getIntent().getSerializableExtra("myNftInfo");
        }
        mDenomId = getIntent().getStringExtra("mDenomId");
        mTokenId = getIntent().getStringExtra("mTokenId");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NFTDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);

        onUpdateView();
        mNftImg.setOnClickListener(this);
        mBtnIbcSend.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
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

    private void onUpdateView() {
        try {
            if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                Glide.with(this).load(mIrisResponse.getNft().getUri()).diskCacheStrategy(DiskCacheStrategy.ALL).
                        placeholder(R.drawable.nft_none_img).error(R.drawable.nft_none_img).fitCenter().into(mNftImg);
            } else if (mBaseChain.equals(BaseChain.CRYPTO_MAIN)) {
                Glide.with(this).load(WUtil.getNftImgUrl(myCryptoNftInfo.getData())).diskCacheStrategy(DiskCacheStrategy.ALL).
                        placeholder(R.drawable.nft_none_img).error(R.drawable.nft_none_img).fitCenter().into(mNftImg);
            }

        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mNftImg)) {
            if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mIrisResponse.getNft().getUri()));
                startActivity(intent);
            } else if (mBaseChain.equals(BaseChain.CRYPTO_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(WUtil.getNftImgUrl(myCryptoNftInfo.getData())));
                startActivity(intent);
            }

        } else if (v.equals(mBtnIbcSend)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            } else {
                Toast.makeText(NFTokenDetailActivity.this, R.string.error_prepare, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getBaseContext(), NFTSendActivity.class);
            intent.putExtra("mDenomId", mDenomId);
            intent.putExtra("mTokenId", mTokenId);
            intent.putExtra("mIrisResponse", mIrisResponse);
            startActivity(intent);
        }
    }

    private class NFTDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_INFO = 1;
        private static final int TYPE_RAW = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_INFO) {
                return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_nft_detail, viewGroup, false));
            } else if (viewType == TYPE_RAW) {
                return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_nft_raw_data, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_INFO) {
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                holder.onBindNftInfo(NFTokenDetailActivity.this, mBaseChain, mIrisResponse, myCryptoNftInfo, mDenomId, mTokenId);
            } else if (getItemViewType(position) == TYPE_RAW) {
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                holder.onBindNftRawData(NFTokenDetailActivity.this, mBaseChain, mIrisResponse, myCryptoNftInfo);
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) return TYPE_INFO;
            else return TYPE_RAW;
        }
    }
}

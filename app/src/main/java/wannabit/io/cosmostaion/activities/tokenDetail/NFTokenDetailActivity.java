package wannabit.io.cosmostaion.activities.tokenDetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import irismod.nft.Nft;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;

public class NFTokenDetailActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar             mToolbar;
    private ImageView           mNftImg;
    private RelativeLayout      mBtnIbcSend, mBtnSend;

    private RecyclerView        mRecyclerView;
    private NFTDetailAdapter    mAdapter;

    public Nft.BaseNFT                      myIrisNftInfo;
    public chainmain.nft.v1.Nft.BaseNFT     myCryptoNftInfo;
    public NFTListActivity.NFTCollectionId  mMyNftId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_nft);
        mToolbar                = findViewById(R.id.tool_bar);
        mNftImg                 = findViewById(R.id.nft_img);
        mRecyclerView           = findViewById(R.id.recycler);
        mBtnIbcSend             = findViewById(R.id.btn_ibc_send);
        mBtnSend                = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        if (mBaseChain.equals(IRIS_MAIN)) {
            myIrisNftInfo = (Nft.BaseNFT) getIntent().getSerializableExtra("myNftInfo");
        } else if (mBaseChain.equals(CRYPTO_MAIN)) {
            myCryptoNftInfo = (chainmain.nft.v1.Nft.BaseNFT) getIntent().getSerializableExtra("myNftInfo");
        }
        mMyNftId = getIntent().getParcelableExtra("myNftInfoId");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NFTDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mNftImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBaseChain.equals(IRIS_MAIN)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myIrisNftInfo.getUri()));
                    startActivity(intent);
                } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(WUtil.getNftImgUrl(myCryptoNftInfo.getData())));
                    startActivity(intent);
                }
            }
        });
        onUpdateView();
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
//            if (mBaseChain.equals(IRIS_MAIN)) {
//                Picasso.get().load(myIrisNftInfo.getUri()).fit().placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).into(mNftImg);
//            } else if (mBaseChain.equals(CRYPTO_MAIN)) {
//                Picasso.get().load(WUtil.getNftImgUrl(myCryptoNftInfo.getData())).fit().placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).into(mNftImg);
//            }
            if (mBaseChain.equals(IRIS_MAIN)) {
                Glide.with(this).load(myIrisNftInfo.getUri()).diskCacheStrategy(DiskCacheStrategy.ALL).
                        placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).fitCenter().into(mNftImg);
            } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                Glide.with(this).load(WUtil.getNftImgUrl(myCryptoNftInfo.getData())).diskCacheStrategy(DiskCacheStrategy.ALL).
                        placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).fitCenter().into(mNftImg);
            }

        } catch (Exception e){}
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnIbcSend)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }
        }
    }

    private class NFTDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_INFO                  = 1;
        private static final int TYPE_RAW                   = 2;

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
                holder.onBindNftInfo(NFTokenDetailActivity.this, mBaseChain, myIrisNftInfo, myCryptoNftInfo, mMyNftId);
            } else if (getItemViewType(position) == TYPE_RAW){
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                holder.onBindNftRawData(NFTokenDetailActivity.this, mBaseChain, myIrisNftInfo, myCryptoNftInfo);
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

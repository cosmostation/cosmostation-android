package wannabit.io.cosmostaion.activities.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SEND_NFT;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.math.BigDecimal;

import irismod.nft.Nft;
import irismod.nft.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTSendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;

public class NFTokenDetailActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar             mToolbar;
    private ImageView           mNftImg;
    private RelativeLayout      mBtnIbcSend, mBtnSend;

    private RecyclerView        mRecyclerView;
    private NFTDetailAdapter    mAdapter;

    private Nft.BaseNFT                      myIrisNftInfo;
    private QueryOuterClass.QueryNFTResponse mIrisResponse;
    private chainmain.nft.v1.Nft.BaseNFT     myCryptoNftInfo;

    private String mDenomId;
    private String mTokenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_nft);
        mToolbar                = findViewById(R.id.tool_bar);
        mNftImg                 = findViewById(R.id.nft_img);
        mRecyclerView           = findViewById(R.id.recycler);
        mBtnIbcSend             = findViewById(R.id.btn_ibc_send);
        mBtnSend                = findViewById(R.id.btn_send);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        if (mBaseChain.equals(IRIS_MAIN)) {
            mIrisResponse = (QueryOuterClass.QueryNFTResponse) getIntent().getSerializableExtra("irisResponse");
        } else if (mBaseChain.equals(CRYPTO_MAIN)) {
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
            if (mBaseChain.equals(IRIS_MAIN)) {
                Glide.with(this).load(mIrisResponse.getNft().getUri()).diskCacheStrategy(DiskCacheStrategy.ALL).
                        placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).fitCenter().into(mNftImg);
            } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                Glide.with(this).load(WUtil.getNftImgUrl(myCryptoNftInfo.getData())).diskCacheStrategy(DiskCacheStrategy.ALL).
                        placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).fitCenter().into(mNftImg);
            }

        } catch (Exception e){}
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mNftImg)) {
            if (mBaseChain.equals(IRIS_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mIrisResponse.getNft().getUri()));
                startActivity(intent);
            } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(WUtil.getNftImgUrl(myCryptoNftInfo.getData())));
                startActivity(intent);
            }

        } else if (v.equals(mBtnIbcSend)) {
            if (!mAccount.hasPrivateKey) {
                AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                        getString(R.string.str_add_mnemonics), view -> onAddMnemonicForAccount(),
                        getString(R.string.str_close), null);
                return;
            } else {
                Toast.makeText(NFTokenDetailActivity.this, R.string.error_prepare, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                        getString(R.string.str_add_mnemonics), view -> onAddMnemonicForAccount(),
                        getString(R.string.str_close), null);
                return;

            } else {
                Intent intent = new Intent(getBaseContext(), NFTSendActivity.class);
                BigDecimal mainAvailable = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SEND_NFT, 0);
                if (mainAvailable.compareTo(feeAmount) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("mDenomId", mDenomId);
                intent.putExtra("mTokenId", mTokenId);
                intent.putExtra("mIrisResponse", mIrisResponse);
                startActivity(intent);
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
                holder.onBindNftInfo(NFTokenDetailActivity.this, mBaseChain, mIrisResponse, myCryptoNftInfo, mDenomId, mTokenId);
            } else if (getItemViewType(position) == TYPE_RAW){
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

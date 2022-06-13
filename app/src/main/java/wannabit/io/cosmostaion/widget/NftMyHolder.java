package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import irismod.nft.Nft;
import irismod.nft.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTListActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NFTokenDetailActivity;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.NFTokenInfoGrpcTask;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;

public class NftMyHolder extends RecyclerView.ViewHolder {
    CardView  itemRoot;
    ImageView itemMyNftImg;
    TextView  itemMyNftTitle, itemMyNftContent;

    public NftMyHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot            = itemView.findViewById(R.id.card_nft);
        itemMyNftImg        = itemView.findViewById(R.id.nft_img);
        itemMyNftTitle      = itemView.findViewById(R.id.nft_title);
        itemMyNftContent    = itemView.findViewById(R.id.nft_content);
    }

    public void onBindNFT(NFTListActivity activity, String denomId, String tokenId) {
        itemRoot.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorTransBgIris));
        itemMyNftImg.setClipToOutline(true);
        new NFTokenInfoGrpcTask(activity.getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                if (result.isSuccess) {
                    if (activity.mBaseChain.equals(IRIS_MAIN)) {
                        QueryOuterClass.QueryNFTResponse irisResponse = (QueryOuterClass.QueryNFTResponse) result.resultData;
                        if (irisResponse != null) {
                            try {
                                Glide.with(activity).load(irisResponse.getNft().getUri()).diskCacheStrategy(DiskCacheStrategy.ALL).
                                        placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).fitCenter().into(itemMyNftImg);
                            } catch (Exception e) { }
                            itemMyNftTitle.setText(irisResponse.getNft().getName());
                            itemMyNftContent.setText(WUtil.getNftDescription(irisResponse.getNft().getData()));

                            itemRoot.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(activity, NFTokenDetailActivity.class);
                                    intent.putExtra("irisResponse", irisResponse);
                                    intent.putExtra("mDenomId", denomId);
                                    intent.putExtra("mTokenId", tokenId);
                                    activity.startActivity(intent);
                                }
                            });
                        }
                    } else if (activity.mBaseChain.equals(CRYPTO_MAIN)) {
                        chainmain.nft.v1.Nft.BaseNFT myCryptoNftInfo = (chainmain.nft.v1.Nft.BaseNFT) result.resultData;
                        if (myCryptoNftInfo != null) {
                            try {
                                Glide.with(activity).load(WUtil.getNftImgUrl(myCryptoNftInfo.getData())).diskCacheStrategy(DiskCacheStrategy.ALL).
                                        placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).into(itemMyNftImg);
                            } catch (Exception e) { }
                            itemMyNftTitle.setText(myCryptoNftInfo.getName());
                            itemMyNftContent.setText(WUtil.getNftDescription(myCryptoNftInfo.getData()));

                            itemRoot.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(activity, NFTokenDetailActivity.class);
                                    intent.putExtra("myNftInfo", myCryptoNftInfo);
                                    intent.putExtra("mDenomId", denomId);
                                    intent.putExtra("mTokenId", tokenId);
                                    activity.startActivity(intent);
                                }
                            });
                        }
                    }
                }
            }
        }, activity.mBaseChain, denomId, tokenId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}

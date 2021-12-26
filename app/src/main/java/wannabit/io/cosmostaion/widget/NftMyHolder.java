package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import irismod.nft.Nft;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTListActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NFTokenDetailActivity;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.NFTokenInfoGrpcTask;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;

public class NftMyHolder extends BaseHolder {
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

    @Override
    public void onBindNFT(Context context, NFTListActivity activity, NFTListActivity.NFTCollectionId myNftId) {
        itemRoot.setCardBackgroundColor(context.getResources().getColor(R.color.colorTransBgIris));
        itemMyNftImg.setClipToOutline(true);
        new NFTokenInfoGrpcTask(activity.getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                if (result.isSuccess) {
                    if (activity.mBaseChain.equals(IRIS_MAIN)) {
                        Nft.BaseNFT myIrisNftInfo = (Nft.BaseNFT) result.resultData;
                        if (myIrisNftInfo != null) {
                            try {
//                                Picasso.get().load(myIrisNftInfo.getUri()).fit().placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).fit().into(itemMyNftImg);
                                Glide.with(activity).load(myIrisNftInfo.getUri()).diskCacheStrategy(DiskCacheStrategy.ALL).
                                        placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).fitCenter().into(itemMyNftImg);
                            } catch (Exception e) { }
                            itemMyNftTitle.setText(myIrisNftInfo.getName());
                            itemMyNftContent.setText(WUtil.getNftDescription(myIrisNftInfo.getData()));

                            itemRoot.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(activity, NFTokenDetailActivity.class);
                                    intent.putExtra("myNftInfo", myIrisNftInfo);
                                    intent.putExtra("myNftInfoId", myNftId);
                                    activity.startActivity(intent);
                                }
                            });
                        }
                    } else if (activity.mBaseChain.equals(CRYPTO_MAIN)) {
                        chainmain.nft.v1.Nft.BaseNFT myCryptoNftInfo = (chainmain.nft.v1.Nft.BaseNFT) result.resultData;
                        if (myCryptoNftInfo != null) {
                            try {
//                                Picasso.get().load(WUtil.getNftImgUrl(myCryptoNftInfo.getData())).fit().placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).into(itemMyNftImg);
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
                                    intent.putExtra("myNftInfoId", myNftId);
                                    activity.startActivity(intent);
                                }
                            });
                        }
                    }
                }
            }
        }, activity.mBaseChain, myNftId.denom_id, myNftId.token_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}

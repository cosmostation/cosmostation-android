package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import irismod.nft.Nft;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTListActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NFTokenDetailActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.NFTokenInfoGrpcTask;
import wannabit.io.cosmostaion.utils.WUtil;

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
    public void onBindNFT(Context context, NFTListActivity activity, BaseData baseData, NFTListActivity.NFTCollectionId myNft) {
        itemRoot.setCardBackgroundColor(context.getResources().getColor(R.color.colorTransBgIris));
        itemMyNftImg.setClipToOutline(true);
        new NFTokenInfoGrpcTask(activity.getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                if (result.isSuccess) {
                    Nft.BaseNFT myyNftInfo = (Nft.BaseNFT) result.resultData;
                    if (myyNftInfo != null) {
                        try {
                            Picasso.get().load(myyNftInfo.getUri()).fit().placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).into(itemMyNftImg);
                        } catch (Exception e){}
                        itemMyNftTitle.setText(myyNftInfo.getName());
                        itemMyNftContent.setText(WUtil.getNftDescription(myyNftInfo.getData()));
                    }
                }

            }
        }, activity.mBaseChain, myNft.denom_id, myNft.token_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, NFTokenDetailActivity.class);
                activity.startActivity(intent);
            }
        });
    }
}

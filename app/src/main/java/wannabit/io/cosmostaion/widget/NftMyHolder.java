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
    public void onBindNFT(Context context, NFTListActivity activity, NFTListActivity.NFTCollectionId myNftId) {
        itemRoot.setCardBackgroundColor(context.getResources().getColor(R.color.colorTransBgIris));
        itemMyNftImg.setClipToOutline(true);
        new NFTokenInfoGrpcTask(activity.getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                if (result.isSuccess) {
                    Nft.BaseNFT myNftInfo = (Nft.BaseNFT) result.resultData;
                    if (myNftInfo != null) {
                        try {
                            Picasso.get().load(myNftInfo.getUri()).fit().placeholder(R.drawable.icon_nft_none).error(R.drawable.icon_nft_none).into(itemMyNftImg);
                        } catch (Exception e) {
                        }
                        itemMyNftTitle.setText(myNftInfo.getName());
                        itemMyNftContent.setText(WUtil.getNftDescription(myNftInfo.getData()));

                        itemRoot.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(activity, NFTokenDetailActivity.class);
                                intent.putExtra("myNftInfo", myNftInfo);
                                intent.putExtra("myNftInfoId", myNftId);
                                activity.startActivity(intent);
                            }
                        });
                    }
                }

            }
        }, activity.mBaseChain, myNftId.denom_id, myNftId.token_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}

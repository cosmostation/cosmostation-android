package wannabit.io.cosmostaion.widget.mainWallet;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.chain.ChainFactory;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletGuideHolder extends BaseHolder {
    public CardView     itemRoot;
    public ImageView    itemGuideImg;
    public TextView     itemGuideTitle;
    public TextView     itemGuideMsg;
    public Button       itemBtnGuide1;
    public Button       itemBtnGuide2;

    public WalletGuideHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot            = itemView.findViewById(R.id.card_root);
        itemGuideImg        = itemView.findViewById(R.id.img_guide);
        itemGuideTitle      = itemView.findViewById(R.id.title_guide);
        itemGuideMsg        = itemView.findViewById(R.id.msg_guide);
        itemBtnGuide1       = itemView.findViewById(R.id.btn_guide1);
        itemBtnGuide2       = itemView.findViewById(R.id.btn_guide2);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        itemBtnGuide1.setText(R.string.str_home);
        itemBtnGuide2.setText(R.string.str_blog);
        ChainFactory.getChain(mainActivity.mBaseChain).setGuideInfo(mainActivity, itemGuideImg, itemGuideTitle, itemGuideMsg, itemBtnGuide1, itemBtnGuide2);

        itemBtnGuide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChainFactory.getChain(mainActivity.mBaseChain).setMainIntent(mainActivity, 2);
            }
        });
        itemBtnGuide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChainFactory.getChain(mainActivity.mBaseChain).setMainIntent(mainActivity, 3);
            }
        });
    }
}

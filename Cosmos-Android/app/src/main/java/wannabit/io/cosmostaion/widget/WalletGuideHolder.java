package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;

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
        if (mainActivity.mBaseChain.equals(COSMOS_MAIN) || mainActivity.mBaseChain.equals(COSMOS_TEST)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.guide_img));
            itemGuideTitle.setText(R.string.str_front_guide_title);
            itemGuideMsg.setText(R.string.str_front_guide_msg);
            itemBtnGuide1.setText(R.string.str_guide);
            itemBtnGuide2.setText(R.string.str_faq);

        } else if (mainActivity.mBaseChain.equals(IRIS_MAIN) || mainActivity.mBaseChain.equals(IRIS_TEST)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.irisnet_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_iris);
            itemGuideMsg.setText(R.string.str_front_guide_msg_iris);
            itemBtnGuide1.setText(R.string.str_faq_iris);
            itemBtnGuide2.setText(R.string.str_guide_iris);

        } else if (mainActivity.mBaseChain.equals(BNB_MAIN) || mainActivity.mBaseChain.equals(BNB_TEST)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.binance_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_binance);
            itemGuideMsg.setText(R.string.str_front_guide_msg_bnb);
            itemBtnGuide1.setText(R.string.str_faq_bnb);
            itemBtnGuide2.setText(R.string.str_guide_bnb);

        } else if (mainActivity.mBaseChain.equals(KAVA_MAIN) || mainActivity.mBaseChain.equals(KAVA_TEST)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.kavamain_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_kava);
            itemGuideMsg.setText(R.string.str_front_guide_msg_kava);
            itemBtnGuide1.setText(R.string.str_faq_kava);
            itemBtnGuide2.setText(R.string.str_guide_kava);

        } else if (mainActivity.mBaseChain.equals(IOV_MAIN) || mainActivity.mBaseChain.equals(IOV_TEST)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.iov_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_iov);
            itemGuideMsg.setText(R.string.str_front_guide_msg_iov);
            itemBtnGuide1.setText(R.string.str_faq_iov);
            itemBtnGuide2.setText(R.string.str_guide_iov);

        } else if (mainActivity.mBaseChain.equals(BAND_MAIN)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.bandprotocol_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_band);
            itemGuideMsg.setText(R.string.str_front_guide_msg_band);
            itemBtnGuide1.setText(R.string.str_faq_band);
            itemBtnGuide2.setText(R.string.str_guide_band);

        } else if (mainActivity.mBaseChain.equals(OKEX_MAIN) || mainActivity.mBaseChain.equals(OK_TEST)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.okex_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_ok);
            itemGuideMsg.setText(R.string.str_front_guide_msg_ok);
            itemBtnGuide1.setText(R.string.str_faq_ok);
            itemBtnGuide2.setText(R.string.str_guide_ok);

        } else if (mainActivity.mBaseChain.equals(CERTIK_MAIN) || mainActivity.mBaseChain.equals(CERTIK_TEST)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.certik_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_certik);
            itemGuideMsg.setText(R.string.str_front_guide_msg_certik);
            itemBtnGuide1.setText(R.string.str_faq_certik);
            itemBtnGuide2.setText(R.string.str_guide_certik);

        } else if (mainActivity.mBaseChain.equals(AKASH_MAIN)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.akash_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_akash);
            itemGuideMsg.setText(R.string.str_front_guide_msg_akash);
            itemBtnGuide1.setText(R.string.str_faq_akash);
            itemBtnGuide2.setText(R.string.str_guide_akash);

        } else if (mainActivity.mBaseChain.equals(SECRET_MAIN)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.secret_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_secret);
            itemGuideMsg.setText(R.string.str_front_guide_msg_secret);
            itemBtnGuide1.setText(R.string.str_faq_secret);
            itemBtnGuide2.setText(R.string.str_guide_secret);

        } else if (mainActivity.mBaseChain.equals(PERSIS_MAIN)) {
            itemGuideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.persistence_img));
            itemGuideTitle.setText(R.string.str_front_guide_title_persis);
            itemGuideMsg.setText(R.string.str_front_guide_msg_persis);
            itemBtnGuide1.setText(R.string.str_faq_persis);
            itemBtnGuide2.setText(R.string.str_guide_persis);

        }

        itemBtnGuide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startActivity(WUtil.getGuide1Intent(mainActivity.mBaseChain));
            }
        });
        itemBtnGuide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startActivity(WUtil.getGuide2Intent(mainActivity.mBaseChain));
            }
        });
    }
}

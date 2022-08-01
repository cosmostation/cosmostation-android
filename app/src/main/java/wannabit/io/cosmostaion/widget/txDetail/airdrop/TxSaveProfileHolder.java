package wannabit.io.cosmostaion.widget.txDetail.airdrop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import desmos.profiles.v1beta1.MsgsProfile;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxSaveProfileHolder extends TxHolder {
    ImageView   itemSaveProfileImg;
    TextView    itemSaveProfileDtag, itemSaveProfileNick, itemSaveProfileBio, itemSaveProfileUrl, itemSaveProfileCoverUrl;

    public TxSaveProfileHolder(@NonNull View itemView) {
        super(itemView);
        itemSaveProfileImg          = itemView.findViewById(R.id.tx_save_profile_icon);
        itemSaveProfileDtag         = itemView.findViewById(R.id.tx_save_profile_dtag);
        itemSaveProfileNick         = itemView.findViewById(R.id.tx_save_profile_nickname);
        itemSaveProfileBio          = itemView.findViewById(R.id.tx_save_profile_bio);
        itemSaveProfileUrl          = itemView.findViewById(R.id.tx_save_profile_url);
        itemSaveProfileCoverUrl     = itemView.findViewById(R.id.tx_save_profile_cover_url);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            MsgsProfile.MsgSaveProfile msg = MsgsProfile.MsgSaveProfile.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSaveProfileDtag.setText(msg.getDtag());
            itemSaveProfileNick.setText(msg.getNickname());
            itemSaveProfileBio.setText(msg.getBio());
            itemSaveProfileUrl.setText(msg.getProfilePicture());
            itemSaveProfileCoverUrl.setText(msg.getCoverPicture());
        } catch (Exception e) { }
    }
}

package wannabit.io.cosmostaion.widget.txDetail.airdrop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;

import cosmos.tx.v1beta1.ServiceOuterClass;
import desmos.profiles.v1beta1.ModelsChainLinks;
import desmos.profiles.v1beta1.MsgsChainLinks;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqDesmosAirDrop;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxLinkAccountHolder extends TxHolder {
    ImageView itemLinkAccountImg;
    TextView itemLinkAccountAddress, itemLinkAccountSigner;

    public TxLinkAccountHolder(@NonNull View itemView) {
        super(itemView);
        itemLinkAccountImg = itemView.findViewById(R.id.tx_save_profile_icon);
        itemLinkAccountAddress = itemView.findViewById(R.id.tx_link_account_address);
        itemLinkAccountSigner = itemView.findViewById(R.id.tx_link_account_signer);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemLinkAccountImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            MsgsChainLinks.MsgLinkChainAccount msg = MsgsChainLinks.MsgLinkChainAccount.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            ModelsChainLinks.Bech32Address chainAddress = ModelsChainLinks.Bech32Address.parseFrom(msg.getChainAddress().getValue());
            itemLinkAccountAddress.setText(chainAddress.getValue());
            itemLinkAccountSigner.setText(msg.getSigner());

            onDesmosClaimAirdrop(c, msg.getSigner());
        } catch (Exception e) {
        }
    }

    public void onDesmosClaimAirdrop(Context context, String address) {
        ReqDesmosAirDrop reqDesmosAirDrop = new ReqDesmosAirDrop(address);
        if (reqDesmosAirDrop != null) {
            ApiClient.getAirDrop(context).ClaimAirDrop(reqDesmosAirDrop).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        WLog.w("onDesmosClaimAirdrop : " + response.body());
                    } else {
                        try {
                            WLog.w("onDesmosClaimAirdrop_error : " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    WLog.w("errer : " + t.getMessage());
                }
            });
        }
    }
}

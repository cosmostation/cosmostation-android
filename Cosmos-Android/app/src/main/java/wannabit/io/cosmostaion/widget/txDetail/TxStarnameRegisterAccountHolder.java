package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.List;

import cosmos.tx.v1beta1.ServiceOuterClass;
import starnamed.x.starname.v1beta1.Tx;
import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class TxStarnameRegisterAccountHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemMsgTitle;
    TextView itemStarname, itemOwner, itemRegister, itemStarnameFee, itemAddressCnt;
    View itemResBar;
    LinearLayout itemResLayer;
    LinearLayout[] itemAddessLayer = new LinearLayout[11];
    ImageView[] itemAddressImg = new ImageView[11];
    TextView[] itemChain = new TextView[11];
    TextView[] itemAddess = new TextView[11];


    public TxStarnameRegisterAccountHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemStarname = itemView.findViewById(R.id.tx_starname);
        itemOwner = itemView.findViewById(R.id.tx_owner);
        itemRegister = itemView.findViewById(R.id.tx_register);
        itemStarnameFee = itemView.findViewById(R.id.tx_starname_fee_amount);
        itemResBar = itemView.findViewById(R.id.tx_resource_bar);
        itemResLayer = itemView.findViewById(R.id.tx_resource_layer);
        itemAddressCnt = itemView.findViewById(R.id.tx_address_cnt);
        for (int i = 0; i < itemAddessLayer.length; i++) {
            itemAddessLayer[i] = itemView.findViewById(itemView.getResources().getIdentifier("tx_resource_layer_" + i, "id", itemView.getContext().getPackageName()));
            itemAddressImg[i] = itemView.findViewById(itemView.getResources().getIdentifier("tx_resource_icon_" + i, "id", itemView.getContext().getPackageName()));
            itemChain[i] = itemView.findViewById(itemView.getResources().getIdentifier("tx_resource_chain_" + i, "id", itemView.getContext().getPackageName()));
            itemAddess[i] = itemView.findViewById(itemView.getResources().getIdentifier("tx_resource_address_" + i, "id", itemView.getContext().getPackageName()));
        }
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        try {
            Tx.MsgRegisterAccount msg = Tx.MsgRegisterAccount.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemStarname.setText(msg.getName() + "*" + msg.getDomain());
            itemOwner.setText(msg.getOwner());
            itemRegister.setText(msg.getRegisterer());

//            BigDecimal starnameFee = baseData.mGrpcStarNameFee
//            itemStarnameFee.setText(WDp.getDpAmount2(itemView.getContext(), starnameFee, 6, 6));

            List<Types.Resource> resources = msg.getResourcesList();
            if (resources != null && resources.size() > 0) {
                itemResBar.setVisibility(View.VISIBLE);
                itemResLayer.setVisibility(View.VISIBLE);
                itemAddressCnt.setText("" + resources.size());
                for (int i = 0; i < resources.size(); i++) {
                    itemAddessLayer[i].setVisibility(View.VISIBLE);
                    itemChain[i].setText(WUtil.getStarNameChainName2(resources.get(i)));
                    itemAddess[i].setText(resources.get(i).getResource());
                    itemAddressImg[i].setImageDrawable(WUtil.getStarNameChainImg2(c, resources.get(i)));
                }
            }
        } catch (Exception e) {}
    }
}

package wannabit.io.cosmostaion.widget.txDetail.ibc;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.math.BigDecimal;

import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.core.channel.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxIBCReceiveHolder extends TxHolder {
    ImageView itemIbcReceiveImg;
    TextView itemIbcFromAddress, itemIbcToAddress;
    RelativeLayout itemIbcSingleCoinLayer;
    TextView itemIbcAmount, itemIbcAmountDenom;

    int divideDecimal = 6;

    public TxIBCReceiveHolder(@NonNull View itemView) {
        super(itemView);
        itemIbcReceiveImg = itemView.findViewById(R.id.tx_ibc_receive_icon);
        itemIbcToAddress = itemView.findViewById(R.id.tx_ibc_receive_to_address);
        itemIbcFromAddress = itemView.findViewById(R.id.tx_ibc_receive_from_address);

        itemIbcSingleCoinLayer = itemView.findViewById(R.id.tx_ibc_receive_single_coin_layer);
        itemIbcAmount = itemView.findViewById(R.id.tx_ibc_receive_amount);
        itemIbcAmountDenom = itemView.findViewById(R.id.tx_ibc_receive_amount_symbol);

    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemIbcReceiveImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        JSONObject jsonObject = null;
        try {
            itemIbcSingleCoinLayer.setVisibility(View.VISIBLE);
            Tx.MsgRecvPacket msg = Tx.MsgRecvPacket.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            jsonObject = new JSONObject(msg.getPacket().getData().toStringUtf8());
            itemIbcToAddress.setText(jsonObject.getString("receiver"));
            itemIbcFromAddress.setText(jsonObject.getString("sender"));

            String denom = jsonObject.getString("denom");
            if (denom.startsWith("transfer/")) {
                String ibcDenom = denom.split("/")[2];
                divideDecimal = WDp.mainDivideDecimal(ibcDenom);
                itemIbcAmountDenom.setText(ibcDenom.toUpperCase());
            } else if (denom.startsWith("c")) {
                divideDecimal = WUtil.getSifCoinDecimal(denom);
                itemIbcAmountDenom.setText(denom.toUpperCase());
            } else if (denom.startsWith("gravity")) {
                divideDecimal = WUtil.getGBridgeCoinDecimal(denom);
                itemIbcAmountDenom.setText(denom);
            } else {
                divideDecimal = WDp.mainDivideDecimal(denom);
                itemIbcAmountDenom.setText(denom.toUpperCase());
            }
            itemIbcAmount.setText(WDp.getDpAmount2(new BigDecimal(jsonObject.getString("amount")), divideDecimal, divideDecimal));

        } catch (Exception e) {
        }
    }
}

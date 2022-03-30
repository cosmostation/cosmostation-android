package wannabit.io.cosmostaion.widget.txDetail.contract;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmwasm.wasm.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxExecuteContractHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemContractType, itemContractExecutor, itemContractAddress, itemContractMsg, itemContractAmount, itemContractDenom;

    public TxExecuteContractHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemContractType = itemView.findViewById(R.id.contract_type);
        itemContractExecutor = itemView.findViewById(R.id.contract_executor);
        itemContractAddress = itemView.findViewById(R.id.contract_address);
        itemContractMsg = itemView.findViewById(R.id.contract_msg);
        itemContractAmount = itemView.findViewById(R.id.contract_amount);
        itemContractDenom = itemView.findViewById(R.id.contract_denom);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgExecuteContract msg = Tx.MsgExecuteContract.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemContractExecutor.setText(msg.getSender());
            itemContractAddress.setText(msg.getContract());

            JSONObject json = new JSONObject(msg.getMsg().toStringUtf8());
            if (json != null) {
                itemContractType.setText(json.keys().next());
                itemContractMsg.setText(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(json.getString(json.keys().next()))));
            }
            if (msg.getFundsList().size() > 0) {
                WDp.showCoinDp(c, baseData, msg.getFunds(0).getDenom(), msg.getFunds(0).getAmount(), itemContractDenom, itemContractAmount, baseChain);
            } else {
                itemContractAmount.setText("");
                itemContractDenom.setText("");
            }

        } catch (Exception e) {
        }
    }
}

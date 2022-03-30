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

public class TxInstantContractHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemContractAdmin, itemContractExecutor, itemContractCodeId, itemContractLabel, itemContractMsg, itemContractAmount, itemContractDenom;

    public TxInstantContractHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemContractAdmin = itemView.findViewById(R.id.contract_admin);
        itemContractExecutor = itemView.findViewById(R.id.contract_executor);
        itemContractCodeId = itemView.findViewById(R.id.contract_code_id);
        itemContractLabel = itemView.findViewById(R.id.contract_label);
        itemContractMsg = itemView.findViewById(R.id.contract_msg);
        itemContractAmount = itemView.findViewById(R.id.contract_amount);
        itemContractDenom = itemView.findViewById(R.id.contract_denom);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgInstantiateContract msg = Tx.MsgInstantiateContract.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemContractAdmin.setText(msg.getAdmin());
            itemContractExecutor.setText(msg.getSender());
            itemContractCodeId.setText("" + msg.getCodeId());
            itemContractLabel.setText(msg.getLabel());

            JSONObject json = new JSONObject(msg.getMsg().toStringUtf8());
            if (json != null) {
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

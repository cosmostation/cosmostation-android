package wannabit.io.cosmostaion.widget.txDetail.certik;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import shentu.oracle.v1alpha1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxCreateTaskHolder extends TxHolder {
    private ImageView itemCreateTaskImg;
    private TextView itemCreateTaskCreator;
    private TextView itemCreateTaskContract;
    private TextView itemCreateTaskBountryAmount, itemCreateTaskBountrySymbol;

    public TxCreateTaskHolder(@NonNull View itemView) {
        super(itemView);
        itemCreateTaskImg = itemView.findViewById(R.id.tx_create_task_icon);
        itemCreateTaskCreator = itemView.findViewById(R.id.tx_create_task_creator);
        itemCreateTaskContract = itemView.findViewById(R.id.tx_create_task_contract);
        itemCreateTaskBountryAmount = itemView.findViewById(R.id.tx_create_task_bountry_amount);
        itemCreateTaskBountrySymbol = itemView.findViewById(R.id.tx_create_task_bountry_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemCreateTaskImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgCreateTask msg = Tx.MsgCreateTask.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemCreateTaskCreator.setText(msg.getCreator());
            itemCreateTaskContract.setText(msg.getContract());
            Coin coin = null;
            if (msg.getBountyList() != null) {
                coin = new Coin(msg.getBounty(0).getDenom(), msg.getBounty(0).getAmount());
            }
            WDp.showCoinDp(c, baseData, coin, itemCreateTaskBountrySymbol, itemCreateTaskBountryAmount, BaseChain.CERTIK_MAIN);
        } catch (Exception e) {
        }
    }
}

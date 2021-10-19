package wannabit.io.cosmostaion.widget.txDetail.gravity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import cosmos.tx.v1beta1.ServiceOuterClass;
import tendermint.liquidity.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxGravityCreatePoolHolder extends TxHolder {
    ImageView itemGravityCreatePoolImg;
    TextView itemGravityCreateAddress, itemGravityCreatePoolId,
            itemGravityCreatePoolDepositAmount1, itemGravityCreatePoolDepositSymbol1, itemGravityCreatePoolDepositAmount2, itemGravityCreatePoolDepositSymbol2;

    public TxGravityCreatePoolHolder(@NonNull View itemView) {
        super(itemView);
        itemGravityCreatePoolImg = itemView.findViewById(R.id.tx_gravity_create_pool_icon);
        itemGravityCreateAddress = itemView.findViewById(R.id.tx_gravity_create_pool_address);
        itemGravityCreatePoolId = itemView.findViewById(R.id.tx_gravity_create_pool_id);
        itemGravityCreatePoolDepositAmount1 = itemView.findViewById(R.id.tx_gravity_create_pool_deposit_amount1);
        itemGravityCreatePoolDepositSymbol1 = itemView.findViewById(R.id.tx_gravity_create_pool_deposit_symbol1);
        itemGravityCreatePoolDepositAmount2 = itemView.findViewById(R.id.tx_gravity_create_pool_deposit_amount2);
        itemGravityCreatePoolDepositSymbol2 = itemView.findViewById(R.id.tx_gravity_create_pool_deposit_symbol2);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemGravityCreatePoolImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgCreatePool msg = Tx.MsgCreatePool.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemGravityCreateAddress.setText(msg.getPoolCreatorAddress());
            itemGravityCreatePoolId.setText("" + msg.getPoolTypeId());

            Coin coin0 = new Coin(msg.getDepositCoins(0).getDenom(), msg.getDepositCoins(0).getAmount());
            Coin coin1 = new Coin(msg.getDepositCoins(1).getDenom(), msg.getDepositCoins(1).getAmount());

            WDp.showCoinDp(c, coin0, itemGravityCreatePoolDepositSymbol1, itemGravityCreatePoolDepositAmount1, baseChain);
            WDp.showCoinDp(c, coin1, itemGravityCreatePoolDepositSymbol2, itemGravityCreatePoolDepositAmount2, baseChain);

        } catch (Exception e) { }
    }
}

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

public class TxGravityDepositHolder extends TxHolder {
    ImageView itemGravityDeositImg;
    TextView itemGravityDeositAddress, itemGravityDeositId,
            itemGravityDepositAmount1, itemGravityDepositSymbol1, itemGravityDepositAmount2, itemGravityDepositSymbol2;

    public TxGravityDepositHolder(@NonNull View itemView) {
        super(itemView);
        itemGravityDeositImg = itemView.findViewById(R.id.tx_gravity_deposit_batch_icon);
        itemGravityDeositAddress = itemView.findViewById(R.id.tx_gravity_deposit_batch_address);
        itemGravityDeositId = itemView.findViewById(R.id.tx_gravity_deposit_batch_id);
        itemGravityDepositAmount1 = itemView.findViewById(R.id.tx_gravity_deposit_batch_amount1);
        itemGravityDepositSymbol1 = itemView.findViewById(R.id.tx_gravity_deposit_batch_symbol1);
        itemGravityDepositAmount2 = itemView.findViewById(R.id.tx_gravity_deposit_batch_amount2);
        itemGravityDepositSymbol2 = itemView.findViewById(R.id.tx_gravity_deposit_batch_symbol2);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemGravityDeositImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgDepositWithinBatch msg = Tx.MsgDepositWithinBatch.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemGravityDeositAddress.setText(msg.getDepositorAddress());
            itemGravityDeositId.setText("" + msg.getPoolId());

            Coin coin0 = new Coin(msg.getDepositCoins(0).getDenom(), msg.getDepositCoins(0).getAmount());
            Coin coin1 = new Coin(msg.getDepositCoins(1).getDenom(), msg.getDepositCoins(1).getAmount());

            WDp.showCoinDp(c, baseData, coin0, itemGravityDepositSymbol1, itemGravityDepositAmount1, baseChain);
            WDp.showCoinDp(c, baseData, coin1, itemGravityDepositSymbol2, itemGravityDepositAmount2, baseChain);

        } catch (Exception e) {
        }
    }
}

package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.gamm.poolmodels.balancer.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxCreatePoolHolder extends TxHolder {
    ImageView itemCreatePoolImg;
    TextView itemCreateSender, itemCreateSwapFee, itemCreateExitFee,
             itemCreatePoolAssetAmount1, itemCreatePoolAssetSymbol1, itemCreatePoolAssetAmount2, itemCreatePoolAssetSymbol2, itemCreatePoolFutureGovernor;

    public TxCreatePoolHolder(@NonNull View itemView) {
        super(itemView);
        itemCreatePoolImg = itemView.findViewById(R.id.tx_create_pool_icon);
        itemCreateSender = itemView.findViewById(R.id.tx_create_pool_sender);
        itemCreateSwapFee = itemView.findViewById(R.id.tx_create_pool_swap_fee);
        itemCreateExitFee = itemView.findViewById(R.id.tx_create_pool_exit_fee);
        itemCreatePoolAssetAmount1 = itemView.findViewById(R.id.tx_create_pool_asset_amount1);
        itemCreatePoolAssetSymbol1 = itemView.findViewById(R.id.tx_create_pool_asset_symbol1);
        itemCreatePoolAssetAmount2 = itemView.findViewById(R.id.tx_create_pool_asset_amount2);
        itemCreatePoolAssetSymbol2 = itemView.findViewById(R.id.tx_create_pool_asset_symbol2);
        itemCreatePoolFutureGovernor = itemView.findViewById(R.id.tx_create_pool_future_governor);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemCreatePoolImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgCreateBalancerPool msg = Tx.MsgCreateBalancerPool.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemCreateSender.setText(msg.getSender());
            itemCreateSwapFee.setText("swapFee : " + new BigDecimal(msg.getPoolParams().getSwapFee()).movePointLeft(18).setScale(2, RoundingMode.FLOOR));
            itemCreateExitFee.setText("exitFee : " + new BigDecimal(msg.getPoolParams().getExitFee()).movePointLeft(18).setScale(2, RoundingMode.FLOOR));

            Coin coin0 = new Coin(msg.getPoolAssets(0).getToken().getDenom(), msg.getPoolAssets(0).getToken().getAmount());
            Coin coin1 = new Coin(msg.getPoolAssets(1).getToken().getDenom(), msg.getPoolAssets(1).getToken().getAmount());

            WDp.showCoinDp(c, baseData, coin0, itemCreatePoolAssetSymbol1, itemCreatePoolAssetAmount1, baseChain);
            WDp.showCoinDp(c, baseData, coin1, itemCreatePoolAssetSymbol2, itemCreatePoolAssetAmount2, baseChain);

            itemCreatePoolFutureGovernor.setText(msg.getFuturePoolGovernor());
        } catch (Exception e) { }
    }
}

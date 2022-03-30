package wannabit.io.cosmostaion.widget.txDetail.sif;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxAddLiquidityHolder extends TxHolder {
    ImageView itemALImg;
    TextView itemALSigner,
            itemALNativeAssetAmount, itemALNativeAssetSymbol, itemALExternalAssetlAmount, itemALExternalAssetSymbol;

    public TxAddLiquidityHolder(@NonNull View itemView) {
        super(itemView);
        itemALImg = itemView.findViewById(R.id.tx_add_liquidity_icon);
        itemALSigner = itemView.findViewById(R.id.tx_add_liquidity_signer);
        itemALNativeAssetAmount = itemView.findViewById(R.id.tx_liquidity_native_amount);
        itemALNativeAssetSymbol = itemView.findViewById(R.id.tx_liquidity_native_symbol);
        itemALExternalAssetlAmount = itemView.findViewById(R.id.tx_liquidity_exteranl_amount);
        itemALExternalAssetSymbol = itemView.findViewById(R.id.tx_liquidity_exteranl_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemALImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            sifnode.clp.v1.Tx.MsgAddLiquidity msg = sifnode.clp.v1.Tx.MsgAddLiquidity.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemALSigner.setText(msg.getSigner());

            Coin nativeCoin = new Coin(BaseConstant.TOKEN_SIF, msg.getNativeAssetAmount());
            Coin externalCoin = new Coin(msg.getExternalAsset().getSymbol(), msg.getExternalAssetAmount());

            WDp.showCoinDp(c, baseData, nativeCoin, itemALNativeAssetSymbol, itemALNativeAssetAmount, baseChain);
            WDp.showCoinDp(c, baseData, externalCoin, itemALExternalAssetSymbol, itemALExternalAssetlAmount, baseChain);

        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}

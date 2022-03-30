package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.cdp.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxWithdrawCdpHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemMsgTitle;
    TextView itemOwner, itemDepositor, itemCollateralAmount, itemCollateralDenom;

    public TxWithdrawCdpHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemOwner = itemView.findViewById(R.id.tx_cdp_owner);
        itemDepositor = itemView.findViewById(R.id.tx_cdp_depositor);
        itemCollateralAmount = itemView.findViewById(R.id.tx_collateral_amount);
        itemCollateralDenom = itemView.findViewById(R.id.tx_collateral_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgWithdraw msg = Tx.MsgWithdraw.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemOwner.setText(msg.getOwner());
            itemDepositor.setText(msg.getDepositor());
            WDp.showCoinDp(c, baseData, msg.getCollateral().getDenom(), msg.getCollateral().getAmount(), itemCollateralDenom, itemCollateralAmount, BaseChain.KAVA_MAIN);

        } catch (Exception e) {
        }
    }
}

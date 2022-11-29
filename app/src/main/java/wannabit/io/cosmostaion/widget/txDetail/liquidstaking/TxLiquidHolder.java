package wannabit.io.cosmostaion.widget.txDetail.liquidstaking;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import cosmos.tx.v1beta1.ServiceOuterClass;
import stride.stakeibc.Tx;
import stride.stakeibc.Tx.MsgLiquidStake;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxLiquidHolder extends TxHolder {
    private ImageView mLiquidImg;
    private TextView mLiquidTitle;
    private LinearLayout mLiquidReceiverLayer;
    private TextView mLiquidCreator, mLiquidReceiver;
    private TextView mLiquidAmount, mLiquidDenom;

    public TxLiquidHolder(@NonNull View itemView) {
        super(itemView);
        mLiquidImg = itemView.findViewById(R.id.liquid_img);
        mLiquidTitle = itemView.findViewById(R.id.liquid_title);
        mLiquidReceiverLayer = itemView.findViewById(R.id.liquid_receiver_layer);
        mLiquidCreator = itemView.findViewById(R.id.tx_liquid_creator);
        mLiquidReceiver = itemView.findViewById(R.id.tx_liquid_receiver);
        mLiquidAmount = itemView.findViewById(R.id.tx_liquid_amount);
        mLiquidDenom = itemView.findViewById(R.id.tx_liquid_amount_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        mLiquidImg.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

        if (response.getTx().getBody().getMessages(position).getTypeUrl().contains("MsgLiquidStake")) {
            try {
                MsgLiquidStake msg = MsgLiquidStake.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                mLiquidReceiverLayer.setVisibility(View.GONE);
                mLiquidTitle.setText(c.getString(R.string.tx_stride_liquid_stake));
                mLiquidCreator.setText(msg.getCreator());

                ChainConfig recipientChainConfig = ChainFactory.SUPPRT_CONFIG().stream().filter(item -> item.mainDenom().equalsIgnoreCase(msg.getHostDenom())).findFirst().get();
                if (recipientChainConfig != null) {
                    WDp.setDpCoin(c, baseData, recipientChainConfig, recipientChainConfig.mainDenom(), String.valueOf(msg.getAmount()), mLiquidDenom, mLiquidAmount);
                }

            } catch (Exception e) { }

        } else {
            try {
                Tx.MsgRedeemStake msg = Tx.MsgRedeemStake.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                mLiquidReceiverLayer.setVisibility(View.VISIBLE);
                mLiquidTitle.setText(c.getString(R.string.tx_stride_redeem_stake));
                mLiquidCreator.setText(msg.getCreator());
                mLiquidReceiver.setText(msg.getReceiver());

                ChainConfig recipientChainConfig = ChainFactory.SUPPRT_CONFIG().stream().filter(item -> msg.getHostZone().startsWith(item.chainIdPrefix())).findFirst().get();
                if (recipientChainConfig != null) {
                    WDp.setDpCoin(c, baseData, recipientChainConfig, recipientChainConfig.mainDenom(), String.valueOf(msg.getAmount()), mLiquidDenom, mLiquidAmount);
                }
            } catch (Exception e) { }
        }
    }
}


package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import cosmos.authz.v1beta1.Tx;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;

public class TxAuthzExecHolder extends TxHolder {
    ImageView itemImg;
    TextView itemGrantee, itemMessage;

    public TxAuthzExecHolder(@NonNull View itemView) {
        super(itemView);
        itemImg = itemView.findViewById(R.id.tx_icon);
        itemGrantee = itemView.findViewById(R.id.tx_grantee);
        itemMessage = itemView.findViewById(R.id.tx_message);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        itemImg.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgExec msg = Tx.MsgExec.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemGrantee.setText(msg.getGrantee());

            String message = "";
            String typeUrl = msg.getMsgs(0).getTypeUrl();
            if (typeUrl.contains(cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.getDescriptor().getFullName())) {
                message = c.getString(R.string.tx_get_reward);

            } else if (typeUrl.contains(cosmos.distribution.v1beta1.Tx.MsgWithdrawValidatorCommission.getDescriptor().getFullName())) {
                message = c.getString(R.string.tx_get_commission);

            } else if (typeUrl.contains(cosmos.gov.v1beta1.Tx.MsgVote.getDescriptor().getFullName())) {
                message = c.getString(R.string.tx_vote);

            } else if (typeUrl.contains(cosmos.staking.v1beta1.Tx.MsgDelegate.getDescriptor().getFullName())) {
                message = c.getString(R.string.tx_delegate);

            } else if (typeUrl.contains(cosmos.staking.v1beta1.Tx.MsgUndelegate.getDescriptor().getFullName())) {
                message = c.getString(R.string.tx_undelegate);

            } else if (typeUrl.contains(cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.getDescriptor().getFullName())) {
                message = c.getString(R.string.tx_redelegate);

            } else if (typeUrl.contains(cosmos.bank.v1beta1.Tx.MsgSend.getDescriptor().getFullName())) {
                message = c.getString(R.string.tx_transfer);

            } else {
                message = "Etc";
            }

            if (msg.getMsgsCount() > 1) {
                message = message + " + " + String.valueOf(msg.getMsgsCount() - 1);
            }
            itemMessage.setText(message);
        } catch (Exception e) {}
    }
}

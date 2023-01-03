package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;

public class TxEvmTransferHolder extends RecyclerView.ViewHolder {
    private ImageView itemEvmTransferImg;
    private ImageView itemStatusImg;
    private TextView itemStatusTxt;
    private TextView itemblockHeight;
    private TextView itemgasInfo;
    private TextView itemtxType;
    private TextView itemContAddress;
    private TextView itemtxData;

    public TxEvmTransferHolder(@NonNull View itemView) {
        super(itemView);
        itemEvmTransferImg = itemView.findViewById(R.id.tx_evm_transfer_icon);
        itemStatusImg = itemView.findViewById(R.id.tx_status_img);
        itemStatusTxt = itemView.findViewById(R.id.tx_status);
        itemblockHeight = itemView.findViewById(R.id.tx_block_height);
        itemgasInfo = itemView.findViewById(R.id.tx_gas_info);
        itemtxType = itemView.findViewById(R.id.tx_type);
        itemContAddress = itemView.findViewById(R.id.tx_contract_address);
        itemtxData = itemView.findViewById(R.id.tx_data);
    }

    public void onBindEvmMsg(Context c, ChainConfig chainConfig, Transaction tx, TransactionReceipt txRecp) {
        itemEvmTransferImg.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
        if (tx == null && txRecp == null) return;

        if (txRecp.isStatusOK()) {
            itemStatusImg.setImageResource(R.drawable.success_ic);
            itemStatusTxt.setText(R.string.str_success_c);
        } else {
            itemStatusImg.setImageResource(R.drawable.fail_ic);
            itemStatusTxt.setText(R.string.str_failed_c);
        }

        itemblockHeight.setText(txRecp.getBlockNumber().toString());
        itemgasInfo.setText(txRecp.getGasUsed().toString() + "/" + tx.getGas().toString());
        if (BaseChain.isGRPC(chainConfig.baseChain())) {
            if (tx.getType().equalsIgnoreCase("0x2")) itemtxType.setText("EIP-1559");
            else itemtxType.setText(tx.getType());
        } else {
            itemtxType.setText("LEGACY");
        }
        itemContAddress.setText(tx.getTo());
        itemtxData.setText(tx.getInput());
    }
}

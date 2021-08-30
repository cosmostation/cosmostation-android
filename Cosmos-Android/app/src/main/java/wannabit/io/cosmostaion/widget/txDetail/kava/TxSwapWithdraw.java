package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxSwapWithdraw extends TxHolder {

    public TxSwapWithdraw(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(Context c, BaseChain baseChain, ResTxInfo res, Msg msg) {

    }
}

package wannabit.io.cosmostaion.widget.mainWallet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dialog.Dialog_Help_Mint_Msg;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletMintHolder extends BaseHolder {
    public CardView     mAprCard;
    public TextView     mInflation, mAPR;

    public WalletMintHolder(@NonNull View itemView) {
        super(itemView);
        mAprCard            = itemView.findViewById(R.id.apr_card);
        mInflation          = itemView.findViewById(R.id.mint_inflation);
        mAPR                = itemView.findViewById(R.id.mint_apr);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final ChainParam.Params param = mainActivity.getBaseDao().mChainParam;
        final BaseChain baseChain = mainActivity.mBaseChain;
        if (param != null) {
            mInflation.setText(WDp.getPercentDp(param.getDpInflation(baseChain)));
            if (param == null || param.getDpApr(baseChain).equals(BigDecimal.ZERO)) {
                mAPR.setText("-");
            } else {
                mAPR.setText(WDp.getPercentDp(param.getDpApr(baseChain)));
            }
        }
        mAprCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("msg1" , mainActivity.getString(R.string.str_apr_help_onchain_msg));
                if (param == null|| param.getDpApr(baseChain).equals(BigDecimal.ZERO)) {
                    bundle.putString("msg2" , "-");
                } else {
                    bundle.putString("msg2" , "" + WDp.getPercentDp(param.getDpApr(baseChain)));
                }
                bundle.putString("msg3" , mainActivity.getString(R.string.str_apr_help_real_msg));
                if (param == null || param.getDpRealApr(baseChain).equals(BigDecimal.ZERO)) {
                    bundle.putString("msg4" , "-");
                } else {
                    bundle.putString("msg4" , "" + WDp.getPercentDp(param.getDpRealApr(baseChain)));
                }
                Dialog_Help_Mint_Msg dialog = Dialog_Help_Mint_Msg.newInstance(bundle);
                dialog.setCancelable(true);
                mainActivity.getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
            }
        });
    }
}

package wannabit.io.cosmostaion.widget.mainWallet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.kenai.jffi.Main;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
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
            if (param.getDpApr(baseChain).equals(BigDecimal.ZERO)) {
                mAPR.setText("0.00%");
            } else {
                mAPR.setText(WDp.getPercentDp(param.getDpApr(baseChain)));
            }
        } else {
            mInflation.setText("-");
            mAPR.setText("-");
        }
        mAprCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg2;
                String msg4;
                if (param == null || param.getDpApr(baseChain).equals(BigDecimal.ZERO)) {
                    msg2 = "0%";
                } else {
                    msg2 = "" + WDp.getPercentDp(param.getDpApr(baseChain));
                }
                if (param == null || param.getDpRealApr(baseChain).equals(BigDecimal.ZERO)) {
                    msg4 = "N/A";
                } else {
                    msg4 = "" + WDp.getPercentDp(param.getDpRealApr(baseChain));
                }
                CommonAlertDialog.getInstance(mainActivity)
                        .showDefaultDialog(mainActivity.getString(R.string.str_apr_help_onchain_msg),
                                msg2,
                                mainActivity.getString(R.string.str_apr_help_real_msg),
                                msg4);
            }
        });
    }
}

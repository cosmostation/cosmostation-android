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
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dialog.Dialog_Help_Msg;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;

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
        final BaseData baseData = mainActivity.getBaseDao();
        if (mainActivity.mBaseChain.equals(COSMOS_MAIN) || mainActivity.mBaseChain.equals(AKASH_MAIN) || mainActivity.mBaseChain.equals(PERSIS_MAIN) ||
                mainActivity.mBaseChain.equals(CRYPTO_MAIN) || mainActivity.mBaseChain.equals(COSMOS_TEST)) {
            if (baseData.mGrpcInflation != null) mInflation.setText(WDp.getPercentDp(baseData.mGrpcInflation.multiply(new BigDecimal("100"))));
            mAPR.setText(WDp.getDpEstApr(mainActivity.getBaseDao(), mainActivity.mBaseChain));

        } else if (mainActivity.mBaseChain.equals(IRIS_MAIN) || mainActivity.mBaseChain.equals(IRIS_TEST)) {
            if (baseData.mGrpcIrisParamMint != null) {
                BigDecimal inflation = new BigDecimal(baseData.mGrpcIrisParamMint.getInflation()).movePointLeft(18);
                mInflation.setText(WDp.getPercentDp(inflation.multiply(new BigDecimal("100"))));
            }
            mAPR.setText(WDp.getDpEstApr(mainActivity.getBaseDao(), mainActivity.mBaseChain));

        } else {
            mInflation.setText(WDp.getPercentDp(baseData.mInflation.multiply(new BigDecimal("100"))));
            mAPR.setText(WDp.getDpEstApr(mainActivity.getBaseDao(), mainActivity.mBaseChain));
        }

        mAprCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", mainActivity.getString(R.string.str_apr_help_title));
                bundle.putString("msg", mainActivity.getString(R.string.str_apr_help_msg));
                Dialog_Help_Msg dialog = Dialog_Help_Msg.newInstance(bundle);
                dialog.setCancelable(true);
                mainActivity.getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
            }
        });
    }
}

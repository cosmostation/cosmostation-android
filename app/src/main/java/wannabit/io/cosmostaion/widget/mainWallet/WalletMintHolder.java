package wannabit.io.cosmostaion.widget.mainWallet;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Param;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletMintHolder extends BaseHolder {
    public CardView mAprCard;
    public TextView mInflation, mAPR;

    public WalletMintHolder(@NonNull View itemView) {
        super(itemView);
        mAprCard = itemView.findViewById(R.id.apr_card);
        mInflation = itemView.findViewById(R.id.mint_inflation);
        mAPR = itemView.findViewById(R.id.mint_apr);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final ChainConfig chainConfig = ChainFactory.getChain(mainActivity.mBaseChain);
        final Param param = mainActivity.getBaseDao().mParam;

        if (param != null) {
            mInflation.setText(WDp.getPercentDp(param.getDpInflation(chainConfig)));
            if (param.getDpApr(chainConfig).equals(BigDecimal.ZERO)) mAPR.setText("0.00%");
            else mAPR.setText(WDp.getPercentDp(param.getDpApr(chainConfig)));
        } else {
            mInflation.setText("-");
            mAPR.setText("-");
        }

        mAprCard.setOnClickListener(v -> {
            String apr;
            String realApr;
            if (param == null) {
                apr = "N/A";
                realApr = "N/A";
            } else {
                if (BigDecimal.ZERO.equals(param.getDpApr(chainConfig))) apr = "0%";
                else apr = String.valueOf(WDp.getPercentDp(param.getDpApr(chainConfig)));

                if (BigDecimal.ZERO.equals(param.getRealApr(chainConfig))) realApr = "N/A";
                else realApr = String.valueOf(WDp.getPercentDp(param.getDpRealApr(chainConfig)));
            }
            Spanned msg = Html.fromHtml("<small>" + mainActivity.getString(R.string.str_apr_help_onchain_msg) + "<br/>"
                    + apr + "<br/><br/>"
                    + mainActivity.getString(R.string.str_apr_help_real_msg) + "<br/>"
                    + realApr + "</small>", Html.FROM_HTML_MODE_COMPACT);
            CommonAlertDialog.showSingleButton(mainActivity, msg, null, mainActivity.getString(R.string.str_ok), null);
        });
    }
}

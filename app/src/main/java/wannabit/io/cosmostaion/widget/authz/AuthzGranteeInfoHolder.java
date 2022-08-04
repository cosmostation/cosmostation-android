package wannabit.io.cosmostaion.widget.authz;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzGranteeInfoHolder extends RecyclerView.ViewHolder {
    private CardView granteeCardView;
    private TextView granteeAddress;
    private TextView granteeAvailable;
    
    public AuthzGranteeInfoHolder(@NonNull View itemView) {
        super(itemView);
        granteeCardView = itemView.findViewById(R.id.card_root);
        granteeAddress = itemView.findViewById(R.id.grantee_address);
        granteeAvailable = itemView.findViewById(R.id.grantee_available);
    }

    public void onBindGranteeInfo(Context c, BaseData baseData, ChainConfig chainConfig, Account account) {
        granteeCardView.setCardBackgroundColor(ContextCompat.getColor(c, chainConfig.chainBgColor()));
        granteeAddress.setText(account.address);

        final int divideDecimal = WDp.getDenomDecimal(baseData, chainConfig, chainConfig.mainDenom());
        granteeAvailable.setText(WDp.getDpAmount2(c, baseData.getAvailable(chainConfig.mainDenom()), divideDecimal, 6));
    }
}

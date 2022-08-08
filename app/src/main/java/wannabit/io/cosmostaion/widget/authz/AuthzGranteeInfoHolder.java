package wannabit.io.cosmostaion.widget.authz;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
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
    private CardView mGranteeCardView;
    private TextView mGranteeAddress;
    private TextView mGranteeAvailable;
    private ImageView mBtnExplorer;

    public AuthzGranteeInfoHolder(@NonNull View itemView) {
        super(itemView);
        mGranteeCardView = itemView.findViewById(R.id.card_root);
        mGranteeAddress = itemView.findViewById(R.id.grantee_address);
        mGranteeAvailable = itemView.findViewById(R.id.grantee_available);
        mBtnExplorer = itemView.findViewById(R.id.btn_explorer);
    }

    public void onBindGranteeInfo(BaseData baseData, ChainConfig chainConfig, Account account) {
        mGranteeCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), chainConfig.chainBgColor()));
        mGranteeAddress.setText(account.address);

        final int divideDecimal = WDp.getDenomDecimal(baseData, chainConfig, chainConfig.mainDenom());
        mGranteeAvailable.setText(WDp.getDpAmount2(baseData.getAvailable(chainConfig.mainDenom()), divideDecimal, 6));

        mBtnExplorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = chainConfig.explorerAccountLink() + account.address;
                itemView.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
    }
}

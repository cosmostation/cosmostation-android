package wannabit.io.cosmostaion.widget.authz;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;

public class AuthzGranterInfoHolder extends RecyclerView.ViewHolder {
    private TextView granterAddress;
    private TextView granterTotalAmount, granterTotalValue;
    private TextView granterAvailable;
    private TextView granterDelegated;

    public AuthzGranterInfoHolder(@NonNull View itemView) {
        super(itemView);
        granterAddress = itemView.findViewById(R.id.granter_address);
        granterTotalAmount = itemView.findViewById(R.id.granter_total_amount);
        granterTotalValue = itemView.findViewById(R.id.granter_total_value);
        granterAvailable = itemView.findViewById(R.id.granter_available);
        granterDelegated = itemView.findViewById(R.id.granter_delegate);
    }

    public void onBindGranterInfo(Context c, BaseData baseData, ChainConfig chainConfig, String granter) {
        granterAddress.setText(granter);
    }
}

package wannabit.io.cosmostaion.widget.mainWallet;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletAuthzHolder extends BaseHolder {

    private ImageView mAuthzTitleImg;
    private RelativeLayout mBtnAuthz;

    public WalletAuthzHolder(@NonNull View itemView) {
        super(itemView);
        mAuthzTitleImg = itemView.findViewById(R.id.authz_title_img);
        mBtnAuthz = itemView.findViewById(R.id.btn_authz);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final ChainConfig chainConfig = ChainFactory.getChain(mainActivity.mBaseChain);
        mAuthzTitleImg.setColorFilter(ContextCompat.getColor(mainActivity, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

        mBtnAuthz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

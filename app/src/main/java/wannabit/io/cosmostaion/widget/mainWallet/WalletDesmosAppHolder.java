package wannabit.io.cosmostaion.widget.mainWallet;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletDesmosAppHolder extends BaseHolder {
    public RelativeLayout mBtnDownloadApp;

    public WalletDesmosAppHolder(@NonNull View itemView) {
        super(itemView);
        mBtnDownloadApp = itemView.findViewById(R.id.btn_download_app);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        mBtnDownloadApp.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dpm.desmos.network/"));
            mainActivity.startActivity(intent);
        });
    }
}

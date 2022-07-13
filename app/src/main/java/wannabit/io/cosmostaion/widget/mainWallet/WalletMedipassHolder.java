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

public class WalletMedipassHolder extends BaseHolder {
    public RelativeLayout mBtnDownloadApp;

    public WalletMedipassHolder(@NonNull View itemView) {
        super(itemView);
        mBtnDownloadApp = itemView.findViewById(R.id.btn_download_app);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        mBtnDownloadApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.medipass.me/"));
                mainActivity.startActivity(intent);
            }
        });
    }
}

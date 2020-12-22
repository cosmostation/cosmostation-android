package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;

public class WalletAddressHolder extends WalletHolder {
    public CardView    itemRoot;
    public ImageView   itemKeyStatus;
    public ImageView   itemBtnWebLink;
    public ImageView   itemBtnCopy;
    public TextView    itemAddressTv;

    public WalletAddressHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot            = itemView.findViewById(R.id.card_root);
        itemKeyStatus       = itemView.findViewById(R.id.img_account);
        itemBtnWebLink      = itemView.findViewById(R.id.web_detail);
        itemBtnCopy         = itemView.findViewById(R.id.address_detail);
        itemAddressTv       = itemView.findViewById(R.id.account_Address);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        if (mainActivity == null) return;
        itemAddressTv.setText(mainActivity.mAccount.address);
        if (mainActivity.mAccount.hasPrivateKey) {
            itemKeyStatus.setColorFilter(WDp.getChainColor(mainActivity, mainActivity.mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            itemKeyStatus.setColorFilter(ContextCompat.getColor(mainActivity, R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        itemBtnWebLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.mBaseChain.equals(IOV_TEST)) { return; }
                Intent webintent = new Intent(mainActivity, WebActivity.class);
                webintent.putExtra("address", mainActivity.mAccount.address);
                webintent.putExtra("chain", mainActivity.mBaseChain.getChain());
                webintent.putExtra("goMain", false);
                mainActivity.startActivity(webintent);
            }
        });
        itemBtnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("address", mainActivity.mAccount.address);
                if (TextUtils.isEmpty(mainActivity.mAccount.nickName))
                    bundle.putString("title", mainActivity.getString(R.string.str_my_wallet) + mainActivity.mAccount.id);
                else
                    bundle.putString("title", mainActivity.mAccount.nickName);
                Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
                show.setCancelable(true);
                mainActivity.getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();
            }
        });
    }
}

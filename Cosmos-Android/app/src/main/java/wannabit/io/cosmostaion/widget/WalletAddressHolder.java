package wannabit.io.cosmostaion.widget;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;


public class WalletAddressHolder extends BaseHolder {
    public CardView     itemRoot;
    public ImageView    itemKeyStatus;
    public ImageView    itemBtnCopy;
    public TextView     itemAddressTv;
    public TextView     itemOKAddressTv;
    private String      shareAddress;

    public WalletAddressHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot            = itemView.findViewById(R.id.card_root);
        itemKeyStatus       = itemView.findViewById(R.id.img_account);
        itemBtnCopy         = itemView.findViewById(R.id.address_detail);
        itemAddressTv       = itemView.findViewById(R.id.account_Address);
        itemOKAddressTv     = itemView.findViewById(R.id.switch_Address);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        if (mainActivity == null) return;
        try {
            if (mainActivity.mBaseChain.equals(OKEX_MAIN) || mainActivity.mBaseChain.equals(OK_TEST)) {
                shareAddress = WKey.convertAddressOkexToEth(mainActivity.mAccount.address);
                itemAddressTv.setText(shareAddress);
                itemOKAddressTv.setText(mainActivity.mAccount.address);
                itemOKAddressTv.setVisibility(View.VISIBLE);
            } else {
                shareAddress = mainActivity.mAccount.address;
                itemAddressTv.setText(mainActivity.mAccount.address);
                itemOKAddressTv.setVisibility(View.GONE);
            }
        } catch (Exception e) {}

        if (mainActivity.mAccount.hasPrivateKey) {
            itemKeyStatus.setColorFilter(WDp.getChainColor(mainActivity, mainActivity.mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            itemKeyStatus.setColorFilter(ContextCompat.getColor(mainActivity, R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        itemBtnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("address", shareAddress);
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

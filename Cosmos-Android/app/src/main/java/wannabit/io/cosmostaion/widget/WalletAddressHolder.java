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

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;


public class WalletAddressHolder extends BaseHolder {
    public CardView     itemRoot;
    public ImageView    itemKeyStatus;
    public ImageView    itemBtnCopy;
    public TextView     itemAddressTv;
    public TextView     itemSwiAddressTv;

    public WalletAddressHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot            = itemView.findViewById(R.id.card_root);
        itemKeyStatus       = itemView.findViewById(R.id.img_account);
        itemBtnCopy         = itemView.findViewById(R.id.address_detail);
        itemAddressTv       = itemView.findViewById(R.id.account_Address);
        itemSwiAddressTv    = itemView.findViewById(R.id.switch_Address);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        if (mainActivity == null) return;
        if (mainActivity.mBaseChain.equals(OKEX_MAIN)) {
            itemSwiAddressTv.setVisibility(View.VISIBLE);
            itemSwiAddressTv.setText(mainActivity.mAccount.address);
        }else {
            itemSwiAddressTv.setVisibility(View.GONE);
            itemAddressTv.setText(mainActivity.mAccount.address);
        }
        if (mainActivity.mAccount.hasPrivateKey) {
            itemKeyStatus.setColorFilter(WDp.getChainColor(mainActivity, mainActivity.mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            itemKeyStatus.setColorFilter(ContextCompat.getColor(mainActivity, R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        }
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

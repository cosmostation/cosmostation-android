package wannabit.io.cosmostaion.widget.mainWallet;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.WalletSwitchActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.ChainAccounts;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class ManageChainSwitchHolder extends BaseHolder {
    private final CardView accountCard;
    private final RelativeLayout accountSelect;
    private final ImageView accountChainImg;
    private final TextView accountChainName;
    private final TextView accountWalletCnt;
    private final LinearLayout hiddenView;

    private final CardView walletCard0;
    private final RelativeLayout walletLayout0;
    private final ImageView walletKeyState0;
    private final TextView walletName0;
    private final TextView walletAddress0;
    private final TextView walletAvailable0;
    private final TextView walletDenom0;

    private final CardView walletCard1;
    private final RelativeLayout walletLayout1;
    private final ImageView walletKeyState1;
    private final TextView walletName1;
    private final TextView walletAddress1;
    private final TextView walletAvailable1;
    private final TextView walletDenom1;

    private final CardView walletCard2;
    private final RelativeLayout walletLayout2;
    private final ImageView walletKeyState2;
    private final TextView walletName2;
    private final TextView walletAddress2;
    private final TextView walletAvailable2;
    private final TextView walletDenom2;

    private final CardView walletCard3;
    private final RelativeLayout walletLayout3;
    private final ImageView walletKeyState3;
    private final TextView walletName3;
    private final TextView walletAddress3;
    private final TextView walletAvailable3;
    private final TextView walletDenom3;

    private final CardView walletCard4;
    private final RelativeLayout walletLayout4;
    private final ImageView walletKeyState4;
    private final TextView walletName4;
    private final TextView walletAddress4;
    private final TextView walletAvailable4;
    private final TextView walletDenom4;

    public ManageChainSwitchHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        accountCard = itemView.findViewById(R.id.card_chain);
        accountSelect = itemView.findViewById(R.id.chain_layer);
        accountChainImg = itemView.findViewById(R.id.chain_img);
        accountChainName = itemView.findViewById(R.id.chain_name);
        accountWalletCnt = itemView.findViewById(R.id.wallet_cnt);
        hiddenView = itemView.findViewById(R.id.hidden_view);

        walletCard0 = itemView.findViewById(R.id.walletCard0);
        walletLayout0 = itemView.findViewById(R.id.wallet_layout0);
        walletKeyState0 = itemView.findViewById(R.id.walletKeyState0);
        walletName0 = itemView.findViewById(R.id.walletName0);
        walletAddress0 = itemView.findViewById(R.id.walletAddress0);
        walletAvailable0 = itemView.findViewById(R.id.walletAvailable0);
        walletDenom0 = itemView.findViewById(R.id.walletDenom0);

        walletCard1 = itemView.findViewById(R.id.walletCard1);
        walletLayout1 = itemView.findViewById(R.id.wallet_layout1);
        walletKeyState1 = itemView.findViewById(R.id.walletKeyState1);
        walletName1 = itemView.findViewById(R.id.walletName1);
        walletAddress1 = itemView.findViewById(R.id.walletAddress1);
        walletAvailable1 = itemView.findViewById(R.id.walletAvailable1);
        walletDenom1 = itemView.findViewById(R.id.walletDenom1);

        walletCard2 = itemView.findViewById(R.id.walletCard2);
        walletLayout2 = itemView.findViewById(R.id.wallet_layout2);
        walletKeyState2 = itemView.findViewById(R.id.walletKeyState2);
        walletName2 = itemView.findViewById(R.id.walletName2);
        walletAddress2 = itemView.findViewById(R.id.walletAddress2);
        walletAvailable2 = itemView.findViewById(R.id.walletAvailable2);
        walletDenom2 = itemView.findViewById(R.id.walletDenom2);

        walletCard3 = itemView.findViewById(R.id.walletCard3);
        walletLayout3 = itemView.findViewById(R.id.wallet_layout3);
        walletKeyState3 = itemView.findViewById(R.id.walletKeyState3);
        walletName3 = itemView.findViewById(R.id.walletName3);
        walletAddress3 = itemView.findViewById(R.id.walletAddress3);
        walletAvailable3 = itemView.findViewById(R.id.walletAvailable3);
        walletDenom3 = itemView.findViewById(R.id.walletDenom3);

        walletCard4 = itemView.findViewById(R.id.walletCard4);
        walletLayout4 = itemView.findViewById(R.id.wallet_layout4);
        walletKeyState4 = itemView.findViewById(R.id.walletKeyState4);
        walletName4 = itemView.findViewById(R.id.walletName4);
        walletAddress4 = itemView.findViewById(R.id.walletAddress4);
        walletAvailable4 = itemView.findViewById(R.id.walletAvailable4);
        walletDenom4 = itemView.findViewById(R.id.walletDenom4);
    }

    public void onBindChainSwitch(@NotNull WalletSwitchActivity switchActivity, ChainAccounts data, Account currentAccount) {
        accountCard.setCardBackgroundColor(WDp.getChainBgColor(switchActivity, data.baseChain));
        accountChainImg.setImageResource(data.baseChain.getChainIcon());
        accountChainName.setText(data.baseChain.getChainAlterTitle());
        accountWalletCnt.setText(String.format(Locale.ENGLISH, "%d / %d", data.accounts.size(), 5));

        accountSelect.setOnClickListener(v -> {
            data.opened = !data.opened;
            if (hiddenView.getVisibility() == View.VISIBLE) {
                hiddenView.setVisibility(View.GONE);
            } else {
                TransitionManager.beginDelayedTransition(accountCard, new AutoTransition());
                hiddenView.setVisibility(View.VISIBLE);
            }
            onSelectedChain(switchActivity, data, currentAccount);
        });
        onSelectedChain(switchActivity, data, currentAccount);
    }

    public void onSelectedChain(WalletSwitchActivity switchActivity, ChainAccounts data, Account currentAccount) {
        if (data.opened && data.accounts.size() > 0) {
            hiddenView.setVisibility(View.VISIBLE);
            walletCard0.setVisibility(View.VISIBLE);
            onBindChainWallet(switchActivity, data.accounts.get(0), currentAccount, walletLayout0, walletKeyState0, walletName0, walletAddress0, walletAvailable0, walletDenom0);

            if (data.accounts.size() > 1) {
                walletCard1.setVisibility(View.VISIBLE);
                onBindChainWallet(switchActivity, data.accounts.get(1), currentAccount, walletLayout1, walletKeyState1, walletName1, walletAddress1, walletAvailable1, walletDenom1);
            } else {
                walletCard1.setVisibility(View.GONE);
            }
            if (data.accounts.size() > 2) {
                walletCard2.setVisibility(View.VISIBLE);
                onBindChainWallet(switchActivity, data.accounts.get(2), currentAccount, walletLayout2, walletKeyState2, walletName2, walletAddress2, walletAvailable2, walletDenom2);
            } else {
                walletCard2.setVisibility(View.GONE);
            }
            if (data.accounts.size() > 3) {
                walletCard3.setVisibility(View.VISIBLE);
                onBindChainWallet(switchActivity, data.accounts.get(3), currentAccount, walletLayout3, walletKeyState3, walletName3, walletAddress3, walletAvailable3, walletDenom3);
            } else {
                walletCard3.setVisibility(View.GONE);
            }
            if (data.accounts.size() > 4) {
                walletCard4.setVisibility(View.VISIBLE);
                onBindChainWallet(switchActivity, data.accounts.get(4), currentAccount, walletLayout4, walletKeyState4, walletName4, walletAddress4, walletAvailable4, walletDenom4);
            } else {
                walletCard4.setVisibility(View.GONE);
            }
        } else {
            hiddenView.setVisibility(View.GONE);
        }
    }

    public void onBindChainWallet(WalletSwitchActivity switchActivity, Account dpAccount, Account currentAccount, RelativeLayout layout,
                                  ImageView keyImg, TextView walletName, TextView address, TextView amount, TextView denom) {

        if (dpAccount.id.equals(currentAccount.id)) {
            layout.setBackground(switchActivity.getResources().getDrawable(R.drawable.box_round_seleted_white));
        } else {
            layout.setBackground(switchActivity.getResources().getDrawable(R.drawable.box_round_darkgray));
        }
        WDp.DpMainDenom(dpAccount.baseChain, denom);
        address.setText(dpAccount.address);
        amount.setText(dpAccount.getLastTotal(switchActivity, BaseChain.getChain(dpAccount.baseChain)));
        keyImg.setColorFilter(ContextCompat.getColor(switchActivity, R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (dpAccount.hasPrivateKey) {
            keyImg.setColorFilter(WDp.getChainColor(switchActivity, BaseChain.getChain(dpAccount.baseChain)), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        walletName.setText(dpAccount.getAccountTitle(walletName.getContext()));

        layout.setOnClickListener(v -> {
            if (dpAccount.id.equals(currentAccount.id)) {
                switchActivity.finish();
                return;
            }
            switchActivity.onChangeWallet(dpAccount.id);
        });
    }
}

package wannabit.io.cosmostaion.widget.mainWallet;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;

public class ManageChainSwitchHolder extends BaseHolder {
    private CardView                    accountCard;
    private RelativeLayout              accountSelect;
    private ImageView                   accountChainImg;
    private TextView                    accountChainName, accountWalletCnt;
    private LinearLayout                hiddenView;

    private CardView                    walletCard0;
    private RelativeLayout              walletLayout0;
    private ImageView                   walletKeyState0;
    private TextView                    walletName0;
    private TextView                    walletAddress0;
    private TextView                    walletAvailable0;
    private TextView                    walletDenom0;

    private CardView                    walletCard1;
    private RelativeLayout              walletLayout1;
    private ImageView                   walletKeyState1;
    private TextView                    walletName1;
    private TextView                    walletAddress1;
    private TextView                    walletAvailable1;
    private TextView                    walletDenom1;

    private CardView                    walletCard2;
    private RelativeLayout              walletLayout2;
    private ImageView                   walletKeyState2;
    private TextView                    walletName2;
    private TextView                    walletAddress2;
    private TextView                    walletAvailable2;
    private TextView                    walletDenom2;

    private CardView                    walletCard3;
    private RelativeLayout              walletLayout3;
    private ImageView                   walletKeyState3;
    private TextView                    walletName3;
    private TextView                    walletAddress3;
    private TextView                    walletAvailable3;
    private TextView                    walletDenom3;

    private CardView                    walletCard4;
    private RelativeLayout              walletLayout4;
    private ImageView                   walletKeyState4;
    private TextView                    walletName4;
    private TextView                    walletAddress4;
    private TextView                    walletAvailable4;
    private TextView                    walletDenom4;

    public ManageChainSwitchHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        accountCard         = itemView.findViewById(R.id.card_chain);
        accountSelect       = itemView.findViewById(R.id.chain_layer);
        accountChainImg     = itemView.findViewById(R.id.chain_img);
        accountChainName    = itemView.findViewById(R.id.chain_name);
        accountWalletCnt    = itemView.findViewById(R.id.wallet_cnt);
        hiddenView          = itemView.findViewById(R.id.hidden_view);

        walletCard0         = itemView.findViewById(R.id.walletCard0);
        walletLayout0       = itemView.findViewById(R.id.wallet_layout0);
        walletKeyState0     = itemView.findViewById(R.id.walletKeyState0);
        walletName0         = itemView.findViewById(R.id.walletName0);
        walletAddress0      = itemView.findViewById(R.id.walletAddress0);
        walletAvailable0    = itemView.findViewById(R.id.walletAvailable0);
        walletDenom0        = itemView.findViewById(R.id.walletDenom0);

        walletCard1         = itemView.findViewById(R.id.walletCard1);
        walletLayout1       = itemView.findViewById(R.id.wallet_layout1);
        walletKeyState1     = itemView.findViewById(R.id.walletKeyState1);
        walletName1         = itemView.findViewById(R.id.walletName1);
        walletAddress1      = itemView.findViewById(R.id.walletAddress1);
        walletAvailable1    = itemView.findViewById(R.id.walletAvailable1);
        walletDenom1        = itemView.findViewById(R.id.walletDenom1);

        walletCard2         = itemView.findViewById(R.id.walletCard2);
        walletLayout2       = itemView.findViewById(R.id.wallet_layout2);
        walletKeyState2     = itemView.findViewById(R.id.walletKeyState2);
        walletName2         = itemView.findViewById(R.id.walletName2);
        walletAddress2      = itemView.findViewById(R.id.walletAddress2);
        walletAvailable2    = itemView.findViewById(R.id.walletAvailable2);
        walletDenom2        = itemView.findViewById(R.id.walletDenom2);

        walletCard3         = itemView.findViewById(R.id.walletCard3);
        walletLayout3       = itemView.findViewById(R.id.wallet_layout3);
        walletKeyState3     = itemView.findViewById(R.id.walletKeyState3);
        walletName3         = itemView.findViewById(R.id.walletName3);
        walletAddress3      = itemView.findViewById(R.id.walletAddress3);
        walletAvailable3    = itemView.findViewById(R.id.walletAvailable3);
        walletDenom3        = itemView.findViewById(R.id.walletDenom3);

        walletCard4         = itemView.findViewById(R.id.walletCard4);
        walletLayout4       = itemView.findViewById(R.id.wallet_layout4);
        walletKeyState4     = itemView.findViewById(R.id.walletKeyState4);
        walletName4         = itemView.findViewById(R.id.walletName4);
        walletAddress4      = itemView.findViewById(R.id.walletAddress4);
        walletAvailable4    = itemView.findViewById(R.id.walletAvailable4);
        walletDenom4        = itemView.findViewById(R.id.walletDenom4);
    }

    public void onBindChainSwitch(@NotNull MainActivity mainActivity, MainActivity.ChainAccounts data, Account currentAccount) {
        accountCard.setCardBackgroundColor(WDp.getChainBgColor(mainActivity, data.baseChain));
        WDp.getChainImg(mainActivity, data.baseChain, accountChainImg);
        WDp.getChainTitle2(mainActivity, data.baseChain, accountChainName);
        accountWalletCnt.setText(data.accounts.size() + " / 5");

        accountSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.opened = !data.opened;
                if (hiddenView.getVisibility() == View.VISIBLE) {
                    hiddenView.setVisibility(View.GONE);
                } else {
                    TransitionManager.beginDelayedTransition(accountCard, new AutoTransition());
                    hiddenView.setVisibility(View.VISIBLE);
                }
                onSelectedChain(mainActivity, data, currentAccount);
            }
        });
        onSelectedChain(mainActivity, data, currentAccount);
    }

    public void onSelectedChain(MainActivity mainActivity, MainActivity.ChainAccounts data, Account currentAccount) {
        if (data.opened && data.accounts.size() > 0) {
            hiddenView.setVisibility(View.VISIBLE);
            walletCard0.setVisibility(View.VISIBLE);
            onBindChainWallet(mainActivity, data.accounts.get(0), currentAccount, walletLayout0, walletKeyState0, walletName0, walletAddress0, walletAvailable0, walletDenom0);

            if (data.accounts.size() > 1) {
                walletCard1.setVisibility(View.VISIBLE);
                onBindChainWallet(mainActivity, data.accounts.get(1), currentAccount, walletLayout1, walletKeyState1, walletName1, walletAddress1, walletAvailable1, walletDenom1);
            } else {
                walletCard1.setVisibility(View.GONE);
            }
            if (data.accounts.size() > 2) {
                walletCard2.setVisibility(View.VISIBLE);
                onBindChainWallet(mainActivity, data.accounts.get(2), currentAccount, walletLayout2, walletKeyState2, walletName2, walletAddress2, walletAvailable2, walletDenom2);
            } else {
                walletCard2.setVisibility(View.GONE);
            }
            if (data.accounts.size() > 3) {
                walletCard3.setVisibility(View.VISIBLE);
                onBindChainWallet(mainActivity, data.accounts.get(3), currentAccount, walletLayout3, walletKeyState3, walletName3, walletAddress3, walletAvailable3, walletDenom3);
            } else {
                walletCard3.setVisibility(View.GONE);
            }
            if (data.accounts.size() > 4) {
                walletCard4.setVisibility(View.VISIBLE);
                onBindChainWallet(mainActivity, data.accounts.get(4), currentAccount, walletLayout4, walletKeyState4, walletName4, walletAddress4, walletAvailable4, walletDenom4);
            } else {
                walletCard4.setVisibility(View.GONE);
            }
        } else {
            hiddenView.setVisibility(View.GONE);
        }
    }

    public void onBindChainWallet(MainActivity mainActivity, Account dpAccount, Account currentAccount, RelativeLayout layout,
                                  ImageView keyImg, TextView walletName, TextView address, TextView amount, TextView denom) {

        if (dpAccount.id.equals(currentAccount.id)) {
            layout.setBackground(mainActivity.getResources().getDrawable(R.drawable.box_round_seleted_white));
        } else {
            layout.setBackground(mainActivity.getResources().getDrawable(R.drawable.box_round_darkgray));
        }
        WDp.DpMainDenom(mainActivity, dpAccount.baseChain, denom);
        String mAddress = dpAccount.address;
        if (BaseChain.getChain(dpAccount.baseChain).equals(OKEX_MAIN)) {
            try {
                if (mAddress.startsWith("ex1")) {
                    mAddress = WKey.convertAddressOkexToEth(dpAccount.address);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        address.setText(mAddress);
        amount.setText(dpAccount.getLastTotal(mainActivity, BaseChain.getChain(dpAccount.baseChain)));
        keyImg.setColorFilter(ContextCompat.getColor(mainActivity, R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (dpAccount.hasPrivateKey) {
            keyImg.setColorFilter(WDp.getChainColor(mainActivity, BaseChain.getChain(dpAccount.baseChain)), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        if (TextUtils.isEmpty(dpAccount.nickName)){
            walletName.setText(mainActivity.getString(R.string.str_my_wallet) + dpAccount.id);
        } else {
            walletName.setText(dpAccount.nickName);
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dpAccount.id == currentAccount.id) {
                    mainActivity.onHideTopAccountsView();
                    return;
                } else {
                    mainActivity.onHideTopAccountsView();
                    mainActivity.onShowWaitDialog();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity.getBaseDao().setLastUser(dpAccount.id);
                            mainActivity.onAccountSwitched();
                        }
                    },200);
                }
            }
        });
    }
}

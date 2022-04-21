package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_SIF;
import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.SIFCHAIN_UNKNOWN_RELAYER;
import static wannabit.io.cosmostaion.base.BaseConstant.SIF_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.SIF_GAS_RATE_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.SIF_GAS_RATE_TINY;
import static wannabit.io.cosmostaion.base.BaseConstant.SIF_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;
import static wannabit.io.cosmostaion.utils.WDp.getDpAmount2;
import static wannabit.io.cosmostaion.utils.WKey.bech32Decode;
import static wannabit.io.cosmostaion.utils.WKey.bech32Encode;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.chains.sif.SifDexListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.HistoryApi;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class Sif extends Chain {

    @Override
    public BaseChain getChain() { return SIF_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(SIF_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_SIF;
    }

    @Override
    public int mainDecimal() { return 18; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_SIF; }

    @Override
    public String getExplorer() { return EXPLORER_SIF_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("sif".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("sif".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) { return KEY_PATH + String.valueOf(position); }

    @Override
    public void setShowCoinDp(Context c, BaseData baseData, String symbol, String amount, TextView denomTv, TextView amountTv) {
        int decimal = WUtil.getSifCoinDecimal(symbol);
        if (symbol.equalsIgnoreCase(TOKEN_SIF)) {
            setDpMainDenom(c, denomTv);
        } else if (symbol.startsWith("c")) {
            denomTv.setText(symbol.substring(1).toUpperCase());
            denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
        } else {
            denomTv.setText(symbol.toUpperCase());
            denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
        }
        amountTv.setText(getDpAmount2(c, new BigDecimal(amount), decimal, decimal));
    }

    @Override
    public void setDpMainDenom(Context c, TextView denomTxt) {
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorSif));
        denomTxt.setText(c.getString(R.string.str_sif_c));
    }

    @Override
    public void setCoinMainList(Context c, BaseData baseData, String denom, TextView symbol, TextView fullName, ImageView imageView, TextView balance, TextView value) {
        setDpMainDenom(c, symbol);
        fullName.setText("Sif Staking Coin");
        setInfoImg(imageView, 1);

        BigDecimal totalAmount = baseData.getAllMainAsset(denom);
        balance.setText(getDpAmount2(c, totalAmount, mainDecimal(), 6));
        value.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, mainDecimal()));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_sif_net));
        } else {
            chainName.setText(c.getString(R.string.str_sif_main));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.chainsifchain);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.tokensifchain);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return SIF_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "sifchain";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("sif1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return SIFCHAIN_UNKNOWN_RELAYER; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorSif));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_sif));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorSif);
        } else {
            return c.getResources().getColor(R.color.colorTransBgSif);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_sif);
        } else {
            return c.getResources().getColorStateList(R.color.colorSif);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.sifchain_img));
        guideTitle.setText(R.string.str_front_guide_title_sif);
        guideMsg.setText(R.string.str_front_guide_msg_sif);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.tokensifchain));
        coinDenom.setText(R.string.str_sif_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_sif);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.VISIBLE);
        dexTitle.setCompoundDrawablesWithIntrinsicBounds(mainActivity.getResources().getDrawable(R.drawable.icon_sifdex), null, null, null);
        dexTitle.setText(R.string.str_sif_dex_title);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 0) {
            mainActivity.startActivity(new Intent(mainActivity, SifDexListActivity.class));
        } else if (sequence == 1) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(COINGECKO_SIF_MAIN)));
        } else if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://sifchain.finance/")));
        } else if (sequence == 3 ) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/sifchain-finance")));
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        return new BigDecimal("100000000000000000");
    }

    @Override
    public BigDecimal setGasRate(int position) {
        if (position == 0) {
            return new BigDecimal(SIF_GAS_RATE_TINY);
        } else if (position == 1) {
            return new BigDecimal(SIF_GAS_RATE_LOW);
        }
        return new BigDecimal(SIF_GAS_RATE_AVERAGE);
    }

    @Override
    public HistoryApi getHistoryApi(Context c) { return ApiClient.getSifApi(c); }
}

package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_HTLS_REFUND;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_GAS_RATE_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_GAS_RATE_TINY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_UNKNOWN_RELAYER;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_NEW_KAVA_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;
import static wannabit.io.cosmostaion.utils.WKey.bech32Decode;
import static wannabit.io.cosmostaion.utils.WKey.bech32Encode;
import static wannabit.io.cosmostaion.utils.WUtil.getEstimateGasAmount;

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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class Kava extends Chain {

    @Override
    public BaseChain getChain() { return KAVA_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(KAVA_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_KAVA;
    }

    @Override
    public int mainDecimal() { return 6; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_KAVA; }

    @Override
    public String getExplorer() { return EXPLORER_KAVA_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        if (customPath == 0) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        } else {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(459, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        }
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("kava".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("kava".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) {
        if (customPath == 0) {
            return KEY_PATH + String.valueOf(position);
        } else {
            return KEY_NEW_KAVA_PATH + String.valueOf(position);
        }
    }

    @Override
    public void setShowCoinDp(Context c, BaseData baseData, String symbol, String amount, TextView denomTv, TextView amountTv) {
        if (symbol.equals(getMainDenom())) {
            setDpMainDenom(c, denomTv);
        } else if (symbol.equals(TOKEN_HARD)) {
            denomTv.setText(symbol.toUpperCase());
            denomTv.setTextColor(c.getResources().getColor(R.color.colorHard));
        } else if (symbol.equals(TOKEN_USDX)) {
            denomTv.setText(symbol.toUpperCase());
            denomTv.setTextColor(c.getResources().getColor(R.color.colorUsdx));
        } else if (symbol.equals(TOKEN_SWP)) {
            denomTv.setText(symbol.toUpperCase());
            denomTv.setTextColor(c.getResources().getColor(R.color.colorSwp));
        } else {
            denomTv.setText(symbol.toUpperCase());
            denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
        }
        amountTv.setText(WDp.getDpAmount2(c, new BigDecimal(amount), WUtil.getKavaCoinDecimal(baseData, symbol), WUtil.getKavaCoinDecimal(baseData, symbol)));
    }

    @Override
    public void setDpMainDenom(Context c, TextView denomTxt) {
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorKava));
        denomTxt.setText(c.getString(R.string.s_kava));
    }

    @Override
    public void setCoinMainDenom(Context c, TextView symbol, TextView fullName, ImageView imageView) {
        symbol.setText(c.getString(R.string.str_kava_c));
        fullName.setText("Kava Staking Coin");
        imageView.setImageDrawable(c.getResources().getDrawable(R.drawable.kava_token_img));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_kava_net));
        } else {
            chainName.setText(c.getString(R.string.str_kava));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.kava_img);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.kava_token_img);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return KAVA_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "kava";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("kava1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return KAVA_UNKNOWN_RELAYER; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorKava));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_kava));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorKava);
        } else {
            return c.getResources().getColor(R.color.colorTransBgKava);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_kava);
        } else {
            return c.getResources().getColorStateList(R.color.colorKava);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.kavamain_img));
        guideTitle.setText(R.string.str_front_guide_title_kava);
        guideMsg.setText(R.string.str_front_guide_msg_kava);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.kava_token_img));
        coinDenom.setText(R.string.str_kava_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_kava);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.VISIBLE);
        dexTitle.setCompoundDrawablesWithIntrinsicBounds(mainActivity.getResources().getDrawable(R.drawable.cdp_s_ic), null, null, null);
        dexTitle.setText(R.string.str_kava_dapp);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 0) {
            mainActivity.startActivity(new Intent(mainActivity, DAppsList5Activity.class));
        } else if (sequence == 1) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(COINGECKO_KAVA_MAIN)));
        } else if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.kava.io/registration/")));
        } else if (sequence == 3) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/kava-labs/")));
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        if (txType == CONST_PW_TX_HTLS_REFUND) {
            return new BigDecimal("12500");
        }
        BigDecimal gasRate = new BigDecimal(KAVA_GAS_RATE_TINY);
        BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
        return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal setGasRate(int position) {
        if (position == 0) {
            return new BigDecimal(KAVA_GAS_RATE_TINY);
        } else if (position == 1) {
            return new BigDecimal(KAVA_GAS_RATE_LOW);
        }
        return new BigDecimal(KAVA_GAS_RATE_AVERAGE);
    }
}

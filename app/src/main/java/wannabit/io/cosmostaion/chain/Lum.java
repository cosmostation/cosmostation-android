package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.LUM_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_LUM;
import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_LUM_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_LUM_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_LUM_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.LUM_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.LUM_GAS_RATE_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.LUM_GAS_RATE_TINY;
import static wannabit.io.cosmostaion.base.BaseConstant.LUM_UNKNOWN_RELAYER;
import static wannabit.io.cosmostaion.base.BaseConstant.LUM_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_LUM;
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
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class Lum extends Chain {

    @Override
    public BaseChain getChain() { return LUM_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(LUM_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_LUM;
    }

    @Override
    public int mainDecimal() { return 6; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_LUM; }

    @Override
    public String getExplorer() { return EXPLORER_LUM_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        if (customPath == 0) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        } else {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(880, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        }
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("lum".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("lum".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) {
        if (customPath == 0) {
            return KEY_PATH + String.valueOf(position);
        } else {
            return KEY_LUM_PATH + String.valueOf(position);
        }
    }

    @Override
    public void setShowCoinDp(Context c, BaseData baseData, String symbol, String amount, TextView denomTv, TextView amountTv) {
        if (symbol.equals(getMainDenom())) {
            setDpMainDenom(c, denomTv);
        } else {
            denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            denomTv.setText(symbol.toUpperCase());
        }
        amountTv.setText(WDp.getDpAmount2(c, new BigDecimal(amount), mainDecimal(), mainDecimal()));
    }

    @Override
    public void setDpMainDenom(Context c, TextView denomTxt) {
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorLum));
        denomTxt.setText(c.getString(R.string.str_lum_c));
    }

    @Override
    public void setCoinMainList(Context c, BaseData baseData, String denom, TextView symbol, TextView fullName, ImageView imageView, TextView balance, TextView value) {
        setDpMainDenom(c, symbol);
        fullName.setText("Lum Network Staking Coin");
        setInfoImg(imageView, 1);

        BigDecimal totalAmount = baseData.getAllMainAsset(denom);
        balance.setText(WDp.getDpAmount2(c, totalAmount, mainDecimal(), 6));
        value.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, mainDecimal()));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_lum_net));
        } else {
            chainName.setText(c.getString(R.string.str_lum_main));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.chain_lumnetwork);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.token_lum);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return LUM_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "lum";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("lum1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return LUM_UNKNOWN_RELAYER; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorLum));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_lum));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorLum);
        } else {
            return c.getResources().getColor(R.color.colorTransBgLum);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_lum);
        } else {
            return c.getResources().getColorStateList(R.color.colorLum);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_lumnetwork));
        guideTitle.setText(R.string.str_front_guide_title_lum);
        guideMsg.setText(R.string.str_front_guide_msg_lum);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.token_lum));
        coinDenom.setText(R.string.str_lum_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_lum);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.GONE);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 1) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(COINGECKO_LUM_MAIN)));
        } else if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://lum.network/")));
        } else if (sequence == 3 ) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/lum-network/")));
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        BigDecimal gasRate = new BigDecimal(LUM_GAS_RATE_AVERAGE);
        BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
        return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal setGasRate(int position) {
        if (position == 0) {
            return new BigDecimal(LUM_GAS_RATE_TINY);
        } else if (position == 1) {
            return new BigDecimal(LUM_GAS_RATE_LOW);
        }
        return new BigDecimal(LUM_GAS_RATE_AVERAGE);
    }
}

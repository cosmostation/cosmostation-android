package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_EVMOS;
import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EVMOS_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.EVMOS_GAS_RATE_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.EVMOS_GAS_RATE_TINY;
import static wannabit.io.cosmostaion.base.BaseConstant.EVMOS_UNKNOWN_RELAYER;
import static wannabit.io.cosmostaion.base.BaseConstant.EVMOS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_ETH_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_EVMOS;
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

public class Evmos extends Chain {

    @Override
    public BaseChain getChain() { return EVMOS_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(EVMOS_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_EVMOS;
    }

    @Override
    public int mainDecimal() { return 18; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_EVMOS; }

    @Override
    public String getExplorer() { return EXPLORER_EVMOS_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("evmos".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("evmos".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) { return KEY_ETH_PATH + String.valueOf(position); }

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
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorEvmos));
        denomTxt.setText(c.getString(R.string.s_evmos));
    }

    @Override
    public void setCoinMainDenom(Context c, TextView symbol, TextView fullName, ImageView imageView) {
        symbol.setText(c.getString(R.string.str_evmos_c));
        fullName.setText("Evmos Staking Coin");
        imageView.setImageDrawable(c.getResources().getDrawable(R.drawable.token_evmos));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_evmos_net));
        } else {
            chainName.setText(c.getString(R.string.str_evmos_main));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.chain_evmos);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.token_evmos);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return EVMOS_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "evmos";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("evmos1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return EVMOS_UNKNOWN_RELAYER; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorBlack));
        floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorEvmos));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_evmos));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorEvmos);
        } else {
            return c.getResources().getColor(R.color.colorTransBgEvmos);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_evmos);
        } else {
            return c.getResources().getColorStateList(R.color.colorEvmos);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_evmos));
        guideTitle.setText(R.string.str_front_guide_title_evmos);
        guideMsg.setText(R.string.str_front_guide_msg_evmos);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.token_evmos));
        coinDenom.setText(R.string.str_evmos_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_evmos);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.GONE);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 1) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(COINGECKO_EVMOS_MAIN)));
        } else if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://evmos.org/")));
        } else if (sequence == 3 ) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://evmos.blog/")));
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        BigDecimal gasRate = new BigDecimal(EVMOS_GAS_RATE_AVERAGE);
        BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
        return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal setGasRate(int position) {
        if (position == 0) {
            return new BigDecimal(EVMOS_GAS_RATE_TINY);
        } else if (position == 1) {
            return new BigDecimal(EVMOS_GAS_RATE_LOW);
        }
        return new BigDecimal(EVMOS_GAS_RATE_AVERAGE);
    }
}

package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.ASSETMANTLE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.ASSETMANTLE_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_MANTLE;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_MANTLE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.MANTLE_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.MANTLE_GAS_RATE_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.MANTLE_GAS_RATE_TINY;
import static wannabit.io.cosmostaion.base.BaseConstant.MANTLE_UNKNOWN_RELAYER;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_MANTLE;
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

public class AssetMantle extends Chain {

    @Override
    public BaseChain getChain() { return ASSETMANTLE_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(ASSETMANTLE_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_MANTLE;
    }

    @Override
    public int mainDecimal() { return 6; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_MANTLE; }

    @Override
    public String getExplorer() { return EXPLORER_MANTLE_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("mantle".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("mantle".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) { return KEY_PATH + String.valueOf(position); }

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
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorMantle));
        denomTxt.setText(c.getString(R.string.str_mantle_c));
    }

    @Override
    public void setCoinMainDenom(Context c, TextView symbol, TextView fullName, ImageView imageView) {
        symbol.setText(c.getString(R.string.str_mantle_c));
        fullName.setText("AssetMantle Staking Coin");
        imageView.setImageDrawable(c.getResources().getDrawable(R.drawable.token_assetmantle));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_mantle_net));
        } else {
            chainName.setText(c.getString(R.string.str_mantle_main));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.chain_assetmantle);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.token_assetmantle);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return ASSETMANTLE_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "asset-mantle";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("mantle1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return MANTLE_UNKNOWN_RELAYER; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorMantle));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_mantle));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorMantle);
        } else {
            return c.getResources().getColor(R.color.colorTransBgMantle);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_mantle);
        } else {
            return c.getResources().getColorStateList(R.color.colorMantle);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_assetmantle));
        guideTitle.setText(R.string.str_front_guide_title_mantle);
        guideMsg.setText(R.string.str_front_guide_msg_mantle);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.token_assetmantle));
        coinDenom.setText(R.string.str_mantle_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_mantle);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.GONE);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://assetmantle.one/")));
        } else if (sequence == 3) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://blog.assetmantle.one/")));
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        BigDecimal gasRate = new BigDecimal(MANTLE_GAS_RATE_AVERAGE);
        BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
        return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal setGasRate(int position) {
        if (position == 0) {
            return new BigDecimal(MANTLE_GAS_RATE_TINY);
        } else if (position == 1) {
            return new BigDecimal(MANTLE_GAS_RATE_LOW);
        }
        return new BigDecimal(MANTLE_GAS_RATE_AVERAGE);
    }
}

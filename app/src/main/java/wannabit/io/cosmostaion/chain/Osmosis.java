package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_OSMOSIS;
import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.OSMOSIS_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.OSMOSIS_GAS_RATE_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.OSMOSIS_GAS_RATE_TINY;
import static wannabit.io.cosmostaion.base.BaseConstant.OSMOSIS_UNKNOWN_RELAYER;
import static wannabit.io.cosmostaion.base.BaseConstant.OSMOSIS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ION;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;
import static wannabit.io.cosmostaion.utils.WDp.getDpAmount2;
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
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WUtil;

public class Osmosis extends Chain {

    @Override
    public BaseChain getChain() { return OSMOSIS_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(OSMOSIS_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_OSMOSIS;
    }

    @Override
    public int mainDecimal() { return 6; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_OSMOSIS; }

    @Override
    public String getExplorer() { return EXPLORER_OSMOSIS_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("osmo".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("osmo".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) { return KEY_PATH + String.valueOf(position); }

    @Override
    public void setShowCoinDp(Context c, BaseData baseData, String symbol, String amount, TextView denomTv, TextView amountTv) {
        int maindecimal = WUtil.getOsmosisCoinDecimal(baseData, symbol);
        if (symbol.equals(getMainDenom())) {
            setDpMainDenom(c, denomTv);

        } else if (symbol.equals(TOKEN_ION)) {
            denomTv.setText("ION");
            denomTv.setTextColor(c.getResources().getColor(R.color.colorIon));

        } else if (symbol.startsWith("gamm/pool/")) {
            String[] value = symbol.split("/");
            denomTv.setText("GAMM-" + value[value.length - 1]);
            denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));

        } else {
            denomTv.setText(symbol.toUpperCase());
            denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
        }
        amountTv.setText(getDpAmount2(c, new BigDecimal(amount), maindecimal, maindecimal));
    }

    @Override
    public void setDpMainDenom(Context c, TextView denomTxt) {
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorOsmosis));
        denomTxt.setText(c.getString(R.string.str_osmosis_c));
    }

    @Override
    public void setCoinMainDenom(Context c, TextView symbol, TextView fullName, ImageView imageView) {
        symbol.setText(c.getString(R.string.str_osmosis_c));
        fullName.setText("Osmosis Staking Coin");
        imageView.setImageDrawable(c.getResources().getDrawable(R.drawable.token_osmosis));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_osmosis_net));
        } else {
            chainName.setText(c.getString(R.string.str_osmosis_main));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.chain_osmosis);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.token_osmosis);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return OSMOSIS_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "osmosis";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("osmo1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return OSMOSIS_UNKNOWN_RELAYER; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorOsmosis));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_osmosis));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorOsmosis);
        } else {
            return c.getResources().getColor(R.color.colorTransBgOsmosis);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_osmosis);
        } else {
            return c.getResources().getColorStateList(R.color.colorOsmosis);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_osmosis));
        guideTitle.setText(R.string.str_front_guide_title_osmosis);
        guideMsg.setText(R.string.str_front_guide_msg_osmosis);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.token_osmosis));
        coinDenom.setText(R.string.str_osmosis_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_osmosis);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.VISIBLE);
        dexTitle.setCompoundDrawablesWithIntrinsicBounds(mainActivity.getResources().getDrawable(R.drawable.icon_osmosislab), null, null, null);
        dexTitle.setText(R.string.str_osmosis_defi_lab);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 0) {
            mainActivity.startActivity(new Intent(mainActivity, LabsListActivity.class));
        } else if (sequence == 1) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(COINGECKO_OSMOSIS_MAIN)));
        } else if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://osmosis.zone/")));
        } else if (sequence == 3 ) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/osmosis")));
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        BigDecimal gasRate = new BigDecimal(OSMOSIS_GAS_RATE_TINY);
        BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
        return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal setGasRate(int position) {
        if (position == 0) {
            return new BigDecimal(OSMOSIS_GAS_RATE_TINY);
        } else if (position == 1) {
            return new BigDecimal(OSMOSIS_GAS_RATE_LOW);
        }
        return new BigDecimal(OSMOSIS_GAS_RATE_AVERAGE);
    }
}

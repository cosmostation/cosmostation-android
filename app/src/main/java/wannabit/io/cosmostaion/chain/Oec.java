package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_OKEX;
import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_ETH_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_NEW_OK_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.OK_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
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
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.utils.WDp;

public class Oec extends Chain {

    @Override
    public BaseChain getChain() { return OKEX_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(OKEX_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_OK;
    }

    @Override
    public int mainDecimal() { return 0; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_OKEX; }

    @Override
    public String getExplorer() { return EXPLORER_OKEX_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        if (customPath == 0 || customPath == 1) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(996, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        } else {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        }
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return "";
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return "";
    }

    @Override
    public String setPath(int position, int customPath) {
        if (customPath == 0) {
            return KEY_NEW_OK_PATH + String.valueOf(position) + (" (Tendermint Type) ");
        } else if (customPath == 1) {
            return KEY_NEW_OK_PATH + String.valueOf(position) + (" (Ethermint Type) ");
        } else {
            return KEY_ETH_PATH + String.valueOf(position) + (" (Ethereum Type) ");
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
        amountTv.setText(WDp.getDpAmount2(c, new BigDecimal(amount), mainDecimal(), 18));
    }

    @Override
    public void setDpMainDenom(Context c, TextView denomTxt) {
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorOK));
        denomTxt.setText(c.getString(R.string.str_ok_c));
    }

    @Override
    public void setCoinMainList(Context c, BaseData baseData, String denom, TextView symbol, TextView fullName, ImageView imageView, TextView balance, TextView value) {
        final OkToken okToken = baseData.okToken(denom);
        symbol.setText(okToken.original_symbol.toUpperCase());
        fullName.setText("OEC Staking Coin");
        setInfoImg(imageView, 1);
        symbol.setTextColor(setChainColor(c, 0));


        BigDecimal totalAmount = baseData.getAllExToken(denom);
        balance.setText(WDp.getDpAmount2(c, totalAmount, mainDecimal(), 6));
        value.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, mainDecimal()));

    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_ok_net));
        } else {
            chainName.setText(c.getString(R.string.str_okex_main));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.chain_okx);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.token_okx);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return OKEX_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "okex";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (baseChain.equals(getChain()) && address.startsWith("0x")) {
            return true;
        }
        return false;
    }

    @Override
    public String getDefaultRelayerImg() { return ""; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorOK));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_okex));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorOK);
        } else {
            return c.getResources().getColor(R.color.colorTransBgOkex);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_ok);
        } else {
            return c.getResources().getColorStateList(R.color.colorOK);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.okex_validatornone));
        guideTitle.setText(R.string.str_front_guide_title_ok);
        guideMsg.setText(R.string.str_front_guide_msg_ok);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.token_okx));
        coinDenom.setText(R.string.str_ok_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_ok);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.GONE);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 1) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(COINGECKO_OKEX_MAIN)));
        } else if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.okex.com/")));
        } else if (sequence == 3 ) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.okex.com/community/")));
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        BigDecimal gasRate = new BigDecimal(OK_GAS_RATE_AVERAGE);
        BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
        return gasRate.multiply(gasAmount).setScale(18, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal setGasRate(int position) {
        return new BigDecimal(OK_GAS_RATE_AVERAGE);
    }
}

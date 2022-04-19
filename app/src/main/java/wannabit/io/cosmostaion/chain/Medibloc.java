package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_MEDI;
import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_MEDI_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.MEDI_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.MEDI_GAS_RATE_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.MEDI_GAS_RATE_TINY;
import static wannabit.io.cosmostaion.base.BaseConstant.MEDI_UNKNOWN_RELAYER;
import static wannabit.io.cosmostaion.base.BaseConstant.MEDI_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_MEDI;
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
import java.util.Locale;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class Medibloc extends Chain {

    @Override
    public BaseChain getChain() { return MEDI_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(MEDI_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_MEDI;
    }

    @Override
    public int mainDecimal() { return 6; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_MEDI; }

    @Override
    public String getExplorer() { return EXPLORER_MEDI_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(371, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("panacea".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("panacea".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) { return KEY_MEDI_PATH + String.valueOf(position); }

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
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorMedi));
        denomTxt.setText(c.getString(R.string.s_medi));
    }

    @Override
    public void setCoinMainDenom(Context c, TextView symbol, TextView fullName, ImageView imageView) {
        symbol.setText(c.getString(R.string.str_medi_c));
        fullName.setText("Medibloc Staking Coin");
        imageView.setImageDrawable(c.getResources().getDrawable(R.drawable.tokenmedibloc));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_medi_net));
        } else {
            chainName.setText(c.getString(R.string.str_medi_main));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.chainmedibloc);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.tokenmedibloc);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return MEDI_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "medibloc";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("panacea1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return MEDI_UNKNOWN_RELAYER; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorWhite));
        floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorMedi));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_medi));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorMedi);
        } else {
            return c.getResources().getColor(R.color.colorTransBgMedi);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_med);
        } else {
            return c.getResources().getColorStateList(R.color.colorMedi);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.medibloc_img));
        guideTitle.setText(R.string.str_front_guide_title_medi);
        guideMsg.setText(R.string.str_front_guide_msg_medi);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.tokenmedibloc));
        coinDenom.setText(R.string.str_medi_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_medi);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.GONE);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 1) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(COINGECKO_MEDI_MAIN)));
        } else if (sequence == 2) {
            if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://medibloc.com")));
            } else {
                mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://medibloc.com/en/ ")));
            }
        } else if (sequence == 3 ) {
            if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://blog.medibloc.org/")));
            } else {
                mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/medibloc")));
            }
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        BigDecimal gasRate = new BigDecimal(MEDI_GAS_RATE_AVERAGE);
        BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
        return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

    }

    @Override
    public BigDecimal setGasRate(int position) {
        if (position == 0) {
            return new BigDecimal(MEDI_GAS_RATE_TINY);
        } else if (position == 1) {
            return new BigDecimal(MEDI_GAS_RATE_LOW);
        }
        return new BigDecimal(MEDI_GAS_RATE_AVERAGE);
    }
}

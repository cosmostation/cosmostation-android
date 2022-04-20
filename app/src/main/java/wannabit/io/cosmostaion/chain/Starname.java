package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_STARNAME_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_IOV_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.STARNAME_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.STARNAME_GAS_RATE_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.STARNAME_GAS_RATE_TINY;
import static wannabit.io.cosmostaion.base.BaseConstant.STARNAME_UNKNOWN_RELAYER;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
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
import wannabit.io.cosmostaion.activities.chains.starname.StarNameListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class Starname extends Chain {

    @Override
    public BaseChain getChain() { return IOV_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(IOV_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_IOV;
    }

    @Override
    public int mainDecimal() { return 6; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_IOV; }

    @Override
    public String getExplorer() { return EXPLORER_IOV_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(234, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("star".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("star".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) {
        return KEY_IOV_PATH + String.valueOf(position);
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
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorIov));
        denomTxt.setText(c.getString(R.string.str_iov_c));
    }

    @Override
    public void setCoinMainDenom(Context c, TextView symbol, TextView fullName, ImageView imageView) {
        symbol.setText(c.getString(R.string.str_iov_c));
        symbol.setTextColor(WDp.getChainColor(c, getChain()));
        fullName.setText("Starname Staking Coin");
        imageView.setImageDrawable(c.getResources().getDrawable(R.drawable.token_starname));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_iov_net));
        } else {
            chainName.setText(c.getString(R.string.str_iov));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.chain_starname);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.token_starname);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return IOV_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "starname";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("star1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return STARNAME_UNKNOWN_RELAYER; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorIov));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_iov));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorIov);
        } else {
            return c.getResources().getColor(R.color.colorTransBgStarname);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_iov);
        } else {
            return c.getResources().getColorStateList(R.color.colorIov);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.starname_img));
        guideTitle.setText(R.string.str_front_guide_title_iov);
        guideMsg.setText(R.string.str_front_guide_msg_iov);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.token_starname));
        coinDenom.setText(R.string.str_iov_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_iov);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.VISIBLE);
        dexTitle.setCompoundDrawablesWithIntrinsicBounds(mainActivity.getResources().getDrawable(R.drawable.name_ic), null, null, null);
        dexTitle.setText(R.string.str_starname_service);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 0) {
            mainActivity.startActivity(new Intent(mainActivity, StarNameListActivity.class));
        } else if (sequence == 1) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(COINGECKO_STARNAME_MAIN)));
        } else if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.starname.me/")));
        } else if (sequence == 3 ) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/iov-internet-of-values")));
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        BigDecimal gasRate = new BigDecimal(STARNAME_GAS_RATE_AVERAGE);
        BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
        return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);
    }

    @Override
    public BigDecimal setGasRate(int position) {
        if (position == 0) {
            return new BigDecimal(STARNAME_GAS_RATE_TINY);
        } else if (position == 1) {
            return new BigDecimal(STARNAME_GAS_RATE_LOW);
        }
        return new BigDecimal(STARNAME_GAS_RATE_AVERAGE);
    }
}

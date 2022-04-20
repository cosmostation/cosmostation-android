package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.PROVENANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_PROVENANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_PROVENANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_PROVENANCE_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.PROVENANCE_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.PROVENANCE_GAS_RATE_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.PROVENANCE_GAS_RATE_TINY;
import static wannabit.io.cosmostaion.base.BaseConstant.PROVENANCE_UNKNOWN_RELAYER;
import static wannabit.io.cosmostaion.base.BaseConstant.PROVENANCE_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HASH;
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

public class Provenance extends Chain {

    @Override
    public BaseChain getChain() { return PROVENANCE_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(PROVENANCE_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_HASH;
    }

    @Override
    public int mainDecimal() { return 9; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_PROVENANCE; }

    @Override
    public String getExplorer() { return EXPLORER_PROVENANCE_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(505, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("pb".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("pb".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) { return KEY_PROVENANCE_PATH + String.valueOf(position); }

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
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorProvenance));
        denomTxt.setText(c.getString(R.string.str_provenance_c));
    }

    @Override
    public void setCoinMainDenom(Context c, TextView symbol, TextView fullName, ImageView imageView) {
        symbol.setText(c.getString(R.string.str_provenance_c));
        fullName.setText("Provenance Staking Coin");
        imageView.setImageDrawable(c.getResources().getDrawable(R.drawable.token_hash));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_provenance_net));
        } else {
            chainName.setText(c.getString(R.string.str_provenance_main));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.chain_provenance);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.token_hash);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return PROVENANCE_VAL_URL + opAddress + ".png";
    }

    @Override
    public String getChainName() {
        return "provenance";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("pb1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return PROVENANCE_UNKNOWN_RELAYER; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorProvenance));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_provenance));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorProvenance);
        } else {
            return c.getResources().getColor(R.color.colorTransBgProvenance);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_provenance);
        } else {
            return c.getResources().getColorStateList(R.color.colorProvenance);
        }

    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_provenance));
        guideTitle.setText(R.string.str_front_guide_title_provenance);
        guideMsg.setText(R.string.str_front_guide_msg_provenance);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.token_hash));
        coinDenom.setText(R.string.str_provenance_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_provenance);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.GONE);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.provenance.io/")));
        } else if (sequence == 3 ) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.provenance.io/blog/")));
        }
    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        BigDecimal gasRate = new BigDecimal(PROVENANCE_GAS_RATE_AVERAGE);
        BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
        return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

    }

    @Override
    public BigDecimal setGasRate(int position) {
        if (position == 0) {
            return new BigDecimal(PROVENANCE_GAS_RATE_TINY);
        } else if (position == 1) {
            return new BigDecimal(PROVENANCE_GAS_RATE_LOW);
        }
        return new BigDecimal(PROVENANCE_GAS_RATE_AVERAGE);
    }
}

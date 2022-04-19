package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BINANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.KEY_BNB_PATH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
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
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class Bnb extends Chain {

    @Override
    public BaseChain getChain() { return BNB_MAIN; }

    @Override
    public ArrayList<BaseChain> getChains() { return Lists.newArrayList(BNB_MAIN); }

    @Override
    public String getMainDenom() {
        return TOKEN_BNB;
    }

    @Override
    public int mainDecimal() { return 0; }

    @Override
    public BigDecimal getRealBlockTime() { return BLOCK_TIME_BNB; }

    @Override
    public String getExplorer() { return EXPLORER_BINANCE_MAIN; }

    @Override
    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(714, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    @Override
    public String getDpAddress(byte[] converted) {
        return bech32Encode("bnb".getBytes(), converted);
    }

    @Override
    public String convertDpOpAddressToDpAddress(String dpOpAddress) {
        return bech32Encode("bnb".getBytes(), bech32Decode(dpOpAddress).data);
    }

    @Override
    public String setPath(int position, int customPath) { return KEY_BNB_PATH + String.valueOf(position); }

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
        denomTxt.setTextColor(c.getResources().getColor(R.color.colorBnb));
        denomTxt.setText(c.getString(R.string.s_bnb));
    }

    @Override
    public void setCoinMainDenom(Context c, TextView symbol, TextView fullName, ImageView imageView) {
        symbol.setText(c.getString(R.string.str_bnb_c));
        fullName.setText("Binance Chain Native Coin");
        imageView.setImageDrawable(c.getResources().getDrawable(R.drawable.bnb_token_img));
    }

    @Override
    public void setChainTitle(Context c, TextView chainName, int type) {
        if (type == 0) {
            chainName.setText(c.getString(R.string.str_binance_net));
        } else {
            chainName.setText(c.getString(R.string.str_binance));
        }
    }

    @Override
    public void setInfoImg(ImageView imageView, int type) {
        if (type == 0) {
            imageView.setImageResource(R.drawable.binance_ch_img);
        } else if (type == 1) {
            imageView.setImageResource(R.drawable.bnb_token_img);
        }
    }

    @Override
    public String setMonikerImgUrl(String opAddress) {
        return "";
    }

    @Override
    public String getChainName() {
        return "bnb";
    }

    @Override
    public boolean isValidChainAddress(String address, BaseChain baseChain) {
        if (address.startsWith("bnb1") && baseChain.equals(getChain())) { return true; }
        else { return false; }
    }

    @Override
    public String getDefaultRelayerImg() { return ""; }

    @Override
    public void setFloatBtn(Context c, FloatingActionButton floatBtn) {
        floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorBnb));
    }

    @Override
    public void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer) {
        wordsLayer[length].setBackground(c.getDrawable(R.drawable.box_round_bnb));
    }

    @Override
    public int setChainColor(Context c, int type) {
        if (type == 0) {
            return c.getResources().getColor(R.color.colorBnb);
        } else {
            return c.getResources().getColor(R.color.colorTransBgBinance);
        }
    }

    @Override
    public ColorStateList setChainTabColor(Context c, int type) {
        return null;
    }

    @Override
    public void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.binance_img));
        guideTitle.setText(R.string.str_front_guide_title_binance);
        guideMsg.setText(R.string.str_front_guide_msg_bnb);
        guideBtn1.setText(R.string.str_faq_bnb);
        guideBtn2.setText(R.string.str_guide_bnb);
    }

    @Override
    public void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom) {
        coinImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.bnb_token_img));
        coinDenom.setText(R.string.str_bnb_c);
        coinDenom.setTextAppearance(R.style.font_ss_14_bnb);
    }

    @Override
    public void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle) {
        mBtnDex.setVisibility(View.GONE);
    }

    @Override
    public void setMainIntent(MainActivity mainActivity, int sequence) {
        if (sequence == 1) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(COINGECKO_BNB_MAIN)));
        } else if (sequence == 2) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.binance.org")));
        } else if (sequence == 3) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/@binance")));
        }

    }

    @Override
    public BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType, int valCnt) {
        return new BigDecimal(FEE_BNB_SEND).setScale(8);
    }

    @Override
    public BigDecimal setGasRate(int position) {
        return BigDecimal.ZERO.setScale(3);
    }
}

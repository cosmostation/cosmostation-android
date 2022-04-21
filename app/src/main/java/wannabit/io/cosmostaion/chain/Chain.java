package wannabit.io.cosmostaion.chain;

import android.content.Context;
import android.content.res.ColorStateList;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.network.HistoryApi;

public abstract class Chain {

    // Get Chain
    public abstract BaseChain getChain();

    // Get Chain List
    public abstract ArrayList<BaseChain> getChains();

    // Get Main Denom
    public abstract String getMainDenom();

    // Get Coin Decimal
    public abstract int mainDecimal();

    // Get Real Block Time
    public abstract BigDecimal getRealBlockTime();

    // Get MintScan Url
    public abstract String getExplorer();

    // Set Chain Parent Path
    public abstract List<ChildNumber> setParentPath(int customPath);

    // Get Chain Address
    public abstract String getDpAddress(byte[] converted);

    // Get Chain OpAddress
    public abstract String convertDpOpAddressToDpAddress(String dpOpAddress);

    // Set Path with Account Detail
    public abstract String setPath(int position, int customPath);

    // Set Show CoinDp (String)
    public abstract void setShowCoinDp(Context c, BaseData baseData, String symbol, String amount, TextView denomTv, TextView amountTv);

    // Set Main Denom Text
    public abstract void setDpMainDenom(Context c, TextView denomTxt);

    // Set Coin Main Denom(Token Fragment)
    public abstract void setCoinMainList(Context c, BaseData baseData, String denom, TextView symbol, TextView fullName, ImageView imageView, TextView balance, TextView value);

    // Set Chain Title
    public abstract void setChainTitle(Context c, TextView chainName, int type);

    // Set Chain Staking Coin Image
    public abstract void setInfoImg(ImageView coinImg, int type);

    // Set Chain Moniker ImageUrl
    public abstract String setMonikerImgUrl(String opAddress);

    // ChainName
    public abstract String getChainName();

    // Check Address
    public abstract boolean isValidChainAddress(String address, BaseChain baseChain);

    // Default Relayer Image
    public abstract String getDefaultRelayerImg();

    // Set Chain Float Button Color
    public abstract void setFloatBtn(Context c, FloatingActionButton floatBtn);

    // Set Chain LayoutColor
    public abstract void setLayoutColor(Context c, int length, LinearLayout[] wordsLayer);

    // Set Chain Type Color ( 0 = ChainColor, 1 = BgColor)
    public abstract int setChainColor(Context c, int type);

    // Set Chain TabColor ( 0 = tab, 1 = tint)
    public abstract ColorStateList setChainTabColor(Context c, int type);

    // Set Chain Guide
    public abstract void setGuideInfo(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2);

    // Set Chain Wallet Data
    public abstract void setWalletData(MainActivity mainActivity, ImageView coinImg, TextView coinDenom);

    // Set Chain Dex Title
    public abstract void setDexTitle(MainActivity mainActivity, RelativeLayout mBtnDex, TextView dexTitle);

    // Set Chain Main Intent ( 0 = Dex, 1 = Coingecko, 2 = Home, 3 = Blog)
    public abstract void setMainIntent(MainActivity mainActivity, int sequence);

    // Set Chain Gas Fee Amount
    public abstract BigDecimal setEstimateGasFeeAmount(Context c, BaseChain basechain, int txType,  int valCnt);

    // Set Chain Gas Rate
    public abstract BigDecimal setGasRate(int position);

    // Get History Api
    public abstract HistoryApi getHistoryApi(Context c);
}

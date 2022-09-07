package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Binance extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.BNB_MAIN; }
    public int chainImg() { return R.drawable.chain_binance; }
    public int chainInfoImg() { return R.drawable.infoicon_binance; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_binance; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_binance; }
    public int chainColor() { return R.color.color_binance; }
    public int chainBgColor() { return R.color.colorTransBgBinance; }
    public int chainTabColor() { return -1; }
    public String chainName() { return "binance"; }
    public String chainIdPrefix() { return "Binance-Chain-Tigris"; }

    public int mainDenomImg() { return R.drawable.token_binance; }
    public String mainDenom() { return "BNB"; }
    public String mainSymbol() { return "BNB"; }
    public int decimal() {
        return 0;
    }
    public String addressPrefix() { return "bnb"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String lcdUrl() { return "https://dex.binance.org/"; }
    public String apiUrl() { return "https://dex.binance.org/"; }

    public BigDecimal blockTime() { return new BigDecimal("0.4124"); }
    public String explorerUrl() { return "https://binance.mintscan.io/"; }
    public String monikerUrl() { return ""; }
    public String homeInfoLink() { return  "https://www.bnbchain.org/en"; }
    public String blogInfoLink() { return  "https://www.bnbchain.org/en/blog/"; }
    public String coingeckoLink() { return  COINGECKO_URL + "binancecoin"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0BNB");
    }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(714, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/714'/0'/0/X");
    }

    public String coinFullName(String denom) { return "Binance Chain Native Coin"; }


    public static String BINANCE_COIN_IMG_URL = RESOURCE_BASE_URL + "coin_image/binance/";
}

package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class LikeCoin extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.LIKECOIN_MAIN; }
    public int chainImg() { return R.drawable.chain_likecoin; }
    public int chainInfoImg() { return R.drawable.infoicon_likecoin; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_likecoin; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_likecoin; }
    public int chainColor() { return R.color.color_likecoin; }
    public int chainBgColor() { return R.color.colorTransBgLikeCoin; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_likecoin; }
    public String chainName() { return "likecoin"; }
    public String chainIdPrefix() { return "likecoin-mainnet-"; }

    public int mainDenomImg() { return R.drawable.token_likecoin; }
    public String mainDenom() { return "nanolike"; }
    public String mainSymbol() {return "LIKE"; }
    public int decimal() { return 9; }
    public String addressPrefix() { return "like"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "lcd-likecoin-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-likecoin.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.028"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "likecoin/"; }
    public String monikerUrl() { return MONIKER_URL + "likecoin/"; }
    public String homeInfoLink() { return  "https://about.like.co/"; }
    public String blogInfoLink() { return  "https://medium.com/likecoin"; }
    public String coingeckoLink() { return  COINGECKO_URL + "likecoin"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("1nanolike");
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

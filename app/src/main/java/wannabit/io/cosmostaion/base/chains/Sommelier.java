package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Sommelier extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.SOMMELIER_MAIN; }
    public int chainImg() { return R.drawable.chain_sommelier; }
    public int chainInfoImg() { return R.drawable.infoicon_sommelier; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_sommelier; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_sommelier; }
    public int chainColor() { return R.color.color_sommelier; }
    public int chainBgColor() { return R.color.colorTransBgSommelier; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_sommelier; }
    public String chainName() { return "sommelier"; }
    public String chainIdPrefix() { return "sommelier-"; }

    public int mainDenomImg() { return R.drawable.token_sommelier; }
    public String mainDenom() { return "usomm"; }
    public String addressPrefix() { return "somm"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-sommelier-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-sommelier.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.1000"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "sommelier/"; }
    public String monikerUrl() { return MONIKER_URL + "sommelier/"; }
    public String homeInfoLink() { return  "https://sommelier.finance/"; }
    public String blogInfoLink() { return  "https://medium.com/@sommelierfinance"; }
    public String coingeckoLink() { return  COINGECKO_URL + "sommelier"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0usomm");
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

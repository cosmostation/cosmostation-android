package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Cerberus extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.CERBERUS_MAIN; }
    public int chainImg() { return R.drawable.chain_cerberus; }
    public int chainInfoImg() { return R.drawable.infoicon_cerberus; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_cerberus; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_cerberus; }
    public int chainColor() { return R.color.color_cerberus; }
    public int chainBgColor() { return R.color.colorTransBgCerberus; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_cerberus; }
    public String chainName() { return "cerberus"; }
    public String chainIdPrefix() { return "cerberus-"; }

    public int mainDenomImg() { return R.drawable.token_cerberus; }
    public String mainDenom() { return "ucrbrus"; }
    public String addressPrefix() { return "cerberus"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-cerberus-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-cerberus.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.9666"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "cerberus/"; }
    public String monikerUrl() { return MONIKER_URL + "cerberus/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "cerberus/relay-cerberus-unknown.png"; }
    public String homeInfoLink() { return  "https://cerberus.zone"; }
    public String blogInfoLink() { return  "https://medium.com/@cerberus_zone"; }
    public String coingeckoLink() { return  COINGECKO_URL + "cerberus"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0ucrbrus");
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

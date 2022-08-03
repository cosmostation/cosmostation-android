package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Sentinel extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.SENTINEL_MAIN; }
    public int chainImg() { return R.drawable.chain_sentinel; }
    public int chainInfoImg() { return R.drawable.infoicon_sentinel; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_sentinel; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_sentinel; }
    public int chainColor() { return R.color.color_sentinel; }
    public int chainBgColor() { return R.color.colorTransBgSentinel; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_sentinel; }
    public String chainName() { return "sentinel"; }
    public String chainIdPrefix() { return "sentinelhub-"; }

    public int mainDenomImg() { return R.drawable.token_sentinel; }
    public String mainDenom() { return "udvpn"; }
    public String addressPrefix() { return "sent"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-sentinel-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-sentinel.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.3113"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "sentinel/"; }
    public String monikerUrl() { return MONIKER_URL + "sentinel/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "sentinel/relay-sentinel-unknown.png"; }
    public String homeInfoLink() { return  "https://sentinel.co"; }
    public String blogInfoLink() { return  "https://medium.com/sentinel"; }
    public String coingeckoLink() { return  COINGECKO_URL + "sentinel"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.01udvpn", "0.1udvpn");
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

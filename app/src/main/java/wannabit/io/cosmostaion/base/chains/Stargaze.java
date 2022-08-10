package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Stargaze extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.STARGAZE_MAIN; }
    public int chainImg() { return R.drawable.chain_stargaze; }
    public int chainInfoImg() { return R.drawable.infoicon_stargaze; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_stargaze; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_stargaze; }
    public int chainColor() { return R.color.color_stargaze; }
    public int chainBgColor() { return R.color.colorTransBgStargaze; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_stargaze; }
    public String chainName() { return "stargaze"; }
    public String chainIdPrefix() { return "stargaze-"; }

    public int mainDenomImg() { return R.drawable.token_stargaze; }
    public String mainDenom() { return "ustars"; }
    public String addressPrefix() { return "stars"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "lcd-stargaze-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-stargaze.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.8129"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "stargaze/"; }
    public String monikerUrl() { return MONIKER_URL + "stargaze/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "stargaze/relay-stargaze-unknown.png"; }
    public String homeInfoLink() { return  "https://stargaze.zone"; }
    public String blogInfoLink() { return  "https://mirror.xyz/stargazezone.eth"; }
    public String coingeckoLink() { return  COINGECKO_URL + "stargaze"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0ustars", "0.0025ustars");
    }

    public int gasDefault() { return 1; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

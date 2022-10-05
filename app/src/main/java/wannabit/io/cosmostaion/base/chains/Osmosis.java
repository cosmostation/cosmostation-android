package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Osmosis extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.OSMOSIS_MAIN; }
    public int chainImg() { return R.drawable.chain_osmosis; }
    public int chainInfoImg() { return R.drawable.infoicon_osmosis; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_osmosis; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_osmosis; }
    public int chainColor() { return R.color.color_osmosis; }
    public int chainBgColor() { return R.color.colorTransBgOsmosis; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_osmosis; }
    public String chainName() { return "osmosis"; }
    public String chainKoreanName() { return "오스모시스"; }
    public String chainIdPrefix() { return "osmosis-"; }

    public int mainDenomImg() { return R.drawable.token_osmosis; }
    public String mainDenom() { return "uosmo"; }
    public String addressPrefix() { return "osmo"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return true; }

    public String grpcUrl() { return "lcd-osmosis-app-and.cosmostation.io"; }
    public String apiUrl() { return "https://api-osmosis.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.5324"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "osmosis/"; }
    public String monikerUrl() { return MONIKER_URL + "osmosis/"; }
    public String homeInfoLink() { return  "https://osmosis.zone"; }
    public String blogInfoLink() { return  "https://medium.com/osmosis"; }
    public String coingeckoLink() { return  COINGECKO_URL + "osmosis"; }


    public static String OSMOSIS_ION_DENOM = "uion";
}

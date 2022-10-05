package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Crescent extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.CRESCENT_MAIN; }
    public int chainImg() { return R.drawable.chain_crescent; }
    public int chainInfoImg() { return R.drawable.infoicon_crescent; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_crescent; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_crescent; }
    public int chainColor() { return R.color.color_crescent; }
    public int chainBgColor() { return R.color.colorTransBgCrescent; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_crescent; }
    public String chainName() { return "crescent"; }
    public String chainKoreanName() { return "크레센트"; }
    public String chainIdPrefix() { return "crescent-"; }

    public int mainDenomImg() { return R.drawable.token_crescent; }
    public String mainDenom() { return "ucre"; }
    public int sendImgColor() { return chainColor(); }
    public int sendBgColor() { return R.color.color_crescent3; }
    public String addressPrefix() { return "cre"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return true; }

    public String grpcUrl() { return "lcd-crescent-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-crescent.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.355"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "crescent/"; }
    public String monikerUrl() { return MONIKER_URL + "crescent/"; }
    public String homeInfoLink() { return  "https://crescent.network"; }
    public String blogInfoLink() { return  "https://crescentnetwork.medium.com"; }
    public String coingeckoLink() { return  COINGECKO_URL + "crescent-network"; }


    public static String CRESCENT_BCRE_DENOM = "ubcre";
}

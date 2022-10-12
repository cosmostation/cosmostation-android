package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Ki extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.KI_MAIN; }
    public int chainImg() { return R.drawable.chain_ki; }
    public int chainInfoImg() { return R.drawable.infoicon_ki; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_ki; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_ki; }
    public int chainColor() { return R.color.color_ki; }
    public int chainBgColor() { return R.color.colorTransBgKi; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_ki; }
    public String chainName() { return "kichain"; }
    public String chainKoreanName() { return "키"; }
    public String chainTitle() { return "(KiChain)"; }
    public String chainTitleToUp() { return "KI"; }
    public String chainIdPrefix() { return "kichain-"; }

    public int mainDenomImg() { return R.drawable.token_ki; }
    public String mainDenom() { return "uxki"; }
    public String addressPrefix() { return "ki"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "lcd-kichain-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-kichain.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.7571"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "ki-chain/"; }
    public String monikerUrl() { return MONIKER_URL + "ki/"; }
    public String homeInfoLink() { return  "https://foundation.ki"; }
    public String blogInfoLink() { return  "https://medium.com/ki-foundation"; }
    public String coingeckoLink() { return  COINGECKO_URL + "ki"; }
}

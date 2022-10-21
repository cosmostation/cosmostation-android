package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Teritori extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.TERITORI_MAIN; }
    public int chainImg() { return R.drawable.chain_teritori; }
    public int chainInfoImg() { return R.drawable.infoicon_teritori; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_teritori; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_teritori; }
    public int chainColor() { return R.color.color_teritori; }
    public int chainBgColor() { return R.color.colorTransBgTeritori; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_teritori; }
    public String chainName() { return "teritori"; }
    public String chainKoreanName() { return "테리토리"; }
    public String chainIdPrefix() { return "teritori-"; }

    public int mainDenomImg() { return R.drawable.token_teritori; }
    public String mainDenom() { return "utori"; }
    public String addressPrefix() { return "tori"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "lcd-teritori-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-teritori.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.723"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "teritori/"; }
    public String monikerUrl() { return MONIKER_URL + "teritori/"; }
    public String homeInfoLink() { return  "https://teritori.com/"; }
    public String blogInfoLink() { return  "https://medium.com/teritori"; }
    public String coingeckoLink() { return  COINGECKO_URL + "teritori"; }
}

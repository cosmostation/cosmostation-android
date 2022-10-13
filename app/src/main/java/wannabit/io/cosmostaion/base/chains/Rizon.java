package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Rizon extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.RIZON_MAIN; }
    public int chainImg() { return R.drawable.chain_rizon; }
    public int chainInfoImg() { return R.drawable.infoicon_rizon; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_rizon; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_rizon; }
    public int chainColor() { return R.color.color_rizon; }
    public int chainBgColor() { return R.color.colorTransBgRizon; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_rizon; }
    public String chainName() { return "rizon"; }
    public String chainKoreanName() { return "라이즌"; }
    public String chainIdPrefix() { return "titan-"; }

    public int mainDenomImg() { return R.drawable.token_rizon; }
    public String mainDenom() { return "uatolo"; }
    public String addressPrefix() { return "rizon"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "lcd-rizon-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-rizon.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.8850"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "rizon/"; }
    public String monikerUrl() { return MONIKER_URL + "rizon/"; }
    public String homeInfoLink() { return  "https://rizon.world"; }
    public String blogInfoLink() { return  "https://medium.com/@hdac-rizon"; }
    public String coingeckoLink() { return  COINGECKO_URL + "rizon"; }
}

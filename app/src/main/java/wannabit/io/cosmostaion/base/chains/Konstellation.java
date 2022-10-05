package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Konstellation extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.KONSTELL_MAIN; }
    public int chainImg() { return R.drawable.chain_konstellation; }
    public int chainInfoImg() { return R.drawable.infoicon_konstellation; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_konstellation; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_konstellation; }
    public int chainColor() { return R.color.color_konstellation; }
    public int chainBgColor() { return R.color.colorTransBgKonstellation; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_konstellation; }
    public String chainName() { return "konstellation"; }
    public String chainKoreanName() { return "콘스텔레이션"; }
    public String chainIdPrefix() { return "darchub"; }

    public int mainDenomImg() { return R.drawable.token_konstellation; }
    public String mainDenom() { return "udarc"; }
    public String addressPrefix() { return "darc"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-konstellation-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-konstellation.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.376"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "konstellation/"; }
    public String monikerUrl() { return MONIKER_URL + "konstellation/"; }
    public String homeInfoLink() { return  "https://konstellation.tech"; }
    public String blogInfoLink() { return  "https://konstellation.medium.com"; }
    public String coingeckoLink() { return  COINGECKO_URL + "konstellation"; }
}

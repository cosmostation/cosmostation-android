package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Shentu extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.CERTIK_MAIN; }
    public int chainImg() { return R.drawable.chain_shentu; }
    public int chainInfoImg() { return R.drawable.infoicon_shentu; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_shentu; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_shentu; }
    public int chainColor() { return R.color.color_shentu; }
    public int chainBgColor() { return R.color.colorTransBgShentu; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_shentu; }
    public String chainName() { return "shentu"; }
    public String chainKoreanName() { return "센츄"; }
    public String chainIdPrefix() { return "shentu-"; }

    public int mainDenomImg() { return R.drawable.token_shentu; }
    public String mainDenom() { return "uctk"; }
    public String addressPrefix() { return "certik"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-certik-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-certik.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.9740"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "shentu/"; }
    public String monikerUrl() { return MONIKER_URL + "shentu/"; }
    public String homeInfoLink() { return  "https://www.certik.foundation"; }
    public String blogInfoLink() { return  "https://medium.com/shentu-foundation"; }
    public String coingeckoLink() { return  COINGECKO_URL + "shentu"; }
}

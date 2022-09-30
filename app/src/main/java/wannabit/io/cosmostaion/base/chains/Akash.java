package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Akash extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.AKASH_MAIN; }
    public int chainImg() { return R.drawable.chain_akash; }
    public int chainInfoImg() { return R.drawable.infoicon_akash; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_akash; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_akash; }
    public int chainColor() { return R.color.color_akash; }
    public int chainBgColor() { return R.color.colorTransBgAkash; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_akash; }
    public String chainName() { return "akash"; }
    public String chainKoreanName() { return "아카시"; }
    public String chainIdPrefix() { return "akashnet-"; }

    public int mainDenomImg() { return R.drawable.token_akash; }
    public String mainDenom() { return "uakt"; }
    public String addressPrefix() { return "akash"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-akash-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-akash.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.4526"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "akash/"; }
    public String monikerUrl() { return MONIKER_URL + "akash/"; }
    public String homeInfoLink() { return  "https://akash.network"; }
    public String blogInfoLink() { return  "https://akash.network/blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "akash-network"; }
}

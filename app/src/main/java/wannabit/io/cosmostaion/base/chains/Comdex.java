package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Comdex extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.COMDEX_MAIN; }
    public int chainImg() { return R.drawable.chain_comdex; }
    public int chainInfoImg() { return R.drawable.infoicon_comdex; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_comdex; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_comdex; }
    public int chainColor() { return R.color.color_comdex; }
    public int chainBgColor() { return R.color.colorTransBgComdex; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_comdex; }
    public String chainName() { return "comdex"; }
    public String chainKoreanName() { return "컴덱스"; }
    public String chainIdPrefix() { return "comdex-"; }

    public int mainDenomImg() { return R.drawable.token_comdex; }
    public String mainDenom() { return "ucmdx"; }
    public String addressPrefix() { return "comdex"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-comdex-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-comdex.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.1746"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "comdex/"; }
    public String monikerUrl() { return MONIKER_URL + "comdex/"; }
    public String homeInfoLink() { return  "https://comdex.one"; }
    public String blogInfoLink() { return  "https://blog.comdex.one"; }
    public String coingeckoLink() { return  COINGECKO_URL + "comdex"; }
}

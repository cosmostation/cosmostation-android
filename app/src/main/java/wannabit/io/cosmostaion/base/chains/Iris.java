package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Iris extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.IRIS_MAIN; }
    public int chainImg() { return R.drawable.chain_iris; }
    public int chainInfoImg() { return R.drawable.infoicon_iris; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_iris; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_iris; }
    public int chainColor() { return R.color.color_iris; }
    public int chainBgColor() { return R.color.colorTransBgIris; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_iris; }
    public String chainName() { return "iris"; }
    public String chainKoreanName() { return "아이리스"; }
    public String chainIdPrefix() { return "irishub-"; }

    public int mainDenomImg() { return R.drawable.token_iris; }
    public String mainDenom() { return "uiris"; }
    public String addressPrefix() { return "iaa"; }

    public boolean bridgeCoinSupport() { return true; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-iris-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-iris.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.7884"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "iris/"; }
    public String monikerUrl() { return MONIKER_URL + "irishub/"; }
    public String homeInfoLink() { return "https://www.irisnet.org"; }
    public String blogInfoLink() { return "https://medium.com/irisnet-blog"; }
    public String coingeckoLink() { return COINGECKO_URL + "irisnet"; }
}

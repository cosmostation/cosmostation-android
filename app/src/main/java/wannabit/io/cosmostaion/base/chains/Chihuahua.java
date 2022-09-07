package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Chihuahua extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.CHIHUAHUA_MAIN; }
    public int chainImg() { return R.drawable.chain_chihuahua; }
    public int chainInfoImg() { return R.drawable.infoicon_chihuahua; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_chihuahua; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_chihuahua; }
    public int chainColor() { return R.color.color_chihuahua; }
    public int chainBgColor() { return R.color.colorTransBgChihuahua; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_chihuahua; }
    public String chainName() { return "chihuahua"; }
    public String chainIdPrefix() { return "chihuahua-"; }

    public int mainDenomImg() { return R.drawable.token_chihuahua; }
    public String mainDenom() { return "uhuahua"; }
    public String addressPrefix() { return "chihuahua"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-chihuahua-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-chihuahua.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.8172"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "chihuahua/"; }
    public String monikerUrl() { return MONIKER_URL + "chihuahua/"; }
    public String homeInfoLink() { return  "https://chi.huahua.wtf"; }
    public String blogInfoLink() { return  "https://chi.huahua.wtf"; }
    public String coingeckoLink() { return  COINGECKO_URL + "chihuahua-chain"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.00035uhuahua", "0.0035uhuahua", "0.035uhuahua");
    }

    public int gasDefault() { return 1; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

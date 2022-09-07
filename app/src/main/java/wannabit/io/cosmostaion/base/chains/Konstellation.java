package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

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

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0001udarc", "0.001udarc", "0.01udarc");
    }

    public int gasDefault() { return 1; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

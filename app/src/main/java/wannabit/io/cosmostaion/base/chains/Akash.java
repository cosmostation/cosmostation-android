package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

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
    public String chainIdPrefix() { return "akashnet-"; }

    public int mainDenomImg() { return R.drawable.token_akash; }
    public String mainDenom() { return "uakt"; }
    public String addressPrefix() { return "akash"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-akash-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-akash.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.4526"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "akash/"; }
    public String monikerUrl() { return MONIKER_URL + "akash/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "akash/relay-akash-unknown.png"; }
    public String homeInfoLink() { return  "https://akash.network"; }
    public String blogInfoLink() { return  "https://akash.network/blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "akash-network"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.00025uakt", "0.0025uakt", "0.025uakt");
    }

    public int gasDefault() { return 1; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

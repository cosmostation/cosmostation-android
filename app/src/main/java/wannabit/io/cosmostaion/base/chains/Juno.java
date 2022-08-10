package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.HistoryApi;

public class Juno extends ChainConfig{

    public BaseChain baseChain() { return BaseChain.JUNO_MAIN; }
    public int chainImg() { return R.drawable.chain_juno; }
    public int chainInfoImg() { return R.drawable.infoicon_juno; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_juno; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_juno; }
    public int chainColor() { return R.color.color_juno; }
    public int chainBgColor() { return R.color.colorTransBgJuno; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_juno; }
    public String chainName() { return "juno"; }
    public String chainIdPrefix() { return "juno-"; }

    public int mainDenomImg() { return R.drawable.token_juno; }
    public String mainDenom() { return "ujuno"; }
    public String addressPrefix() { return "juno"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "lcd-juno-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-juno.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.3104"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "juno/"; }
    public String monikerUrl() { return MONIKER_URL + "juno/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "juno/relay-juno-unknown.png"; }
    public String homeInfoLink() { return  "https://junochain.com"; }
    public String blogInfoLink() { return  "https://medium.com/@JunoNetwork"; }
    public String coingeckoLink() { return  COINGECKO_URL + "juno-network"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0025ujuno", "0.005ujuno", "0.025ujuno");
    }

    public int gasDefault() { return 1; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

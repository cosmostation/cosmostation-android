package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class GravityBridge extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.GRABRIDGE_MAIN; }
    public int chainImg() { return R.drawable.chain_gravitybridge; }
    public int chainInfoImg() { return R.drawable.infoicon_gravitybridge; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_grabridge; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_grabridge; }
    public int chainColor() { return R.color.color_grabridge; }
    public int chainBgColor() { return R.color.colorTransBgGraBridge; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_grabridge; }
    public String chainName() { return "gravity-bridge"; }
    public String chainIdPrefix() { return "gravity-bridge-"; }

    public int mainDenomImg() { return R.drawable.token_gravitybridge; }
    public String mainDenom() { return "ugraviton"; }
    public String addressPrefix() { return "gravity"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-gravity-bridge-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-gravity-bridge.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.4500"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "gravity-bridge/"; }
    public String monikerUrl() { return MONIKER_URL + "gravity-bridge/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "gravity-bridge/relay-gravitybridge-unknown.png"; }
    public String homeInfoLink() { return  "https://www.gravitybridge.net"; }
    public String blogInfoLink() { return  "https://www.gravitybridge.net/blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "graviton"; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}


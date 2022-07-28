package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Cosmos extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.COSMOS_MAIN; }
    public int chainImg() { return R.drawable.chain_cosmos; }
    public int chainInfoImg() { return R.drawable.infoicon_cosmos; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_cosmos; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_cosmos; }
    public int chainColor() { return R.color.color_cosmos; }
    public int chainBgColor() { return R.color.colorTransBgCosmos; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_cosmos; }
    public String chainName() { return "cosmos"; }
    public String chainIdPrefix() { return "cosmoshub-"; }

    public int mainDenomImg() { return R.drawable.token_cosmos; }
    public String mainDenom() { return "uatom"; }
    public String addressPrefix() { return "cosmos"; }

    public boolean pushSupport() { return true; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-cosmos-app-and.cosmostation.io"; }
    public String apiUrl() { return "https://api.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("7.6597"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "cosmos/"; }
    public String monikerUrl() { return MONIKER_URL + "cosmoshub/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "cosmos/relay-cosmos-unknown.png"; }
    public String homeInfoLink() { return  "https://cosmos.network"; }
    public String blogInfoLink() { return  "https://blog.cosmos.network"; }
    public String coingeckoLink() { return  COINGECKO_URL + "cosmos"; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Axelar extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.AXELAR_MAIN; }
    public int chainImg() { return R.drawable.chain_axelar; }
    public int chainInfoImg() { return R.drawable.infoicon_axelar; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_axelar; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_axelar; }
    public int chainColor() { return R.color.color_axelar; }
    public int chainBgColor() { return R.color.colorTransBgAxelar; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_axelar; }
    public String chainName() { return "axelar"; }
    public String chainIdPrefix() { return "axelar-"; }

    public int mainDenomImg() { return R.drawable.token_axelar; }
    public String mainDenom() { return "uaxl"; }
    public String addressPrefix() { return "axelar"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-axelar-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-axelar.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.5596"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "axelar/"; }
    public String monikerUrl() { return MONIKER_URL + "axelar/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "axelar/relay-axelar-unknown.png"; }
    public String homeInfoLink() { return  "https://axelar.network"; }
    public String blogInfoLink() { return  "https://axelar.network/blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "axelar-network"; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

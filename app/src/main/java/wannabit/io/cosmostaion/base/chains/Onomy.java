package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Onomy extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.ONOMY_MAIN; }
    public int chainImg() { return R.drawable.chain_onomy; }
    public int chainInfoImg() { return R.drawable.infoicon_onomy; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_onomy; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_onomy; }
    public int chainColor() { return R.color.color_onomy; }
    public int chainBgColor() { return R.color.colorTransBgOnomy; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_onomy; }
    public String chainName() { return "onomy-protocol"; }
    public String chainKoreanName() { return "오노미"; }
    public String chainTitleToUp() { return "ONOMY"; }
    public String chainIdPrefix() { return "onomy-mainnet-"; }

    public int mainDenomImg() { return R.drawable.token_onomy; }
    public String mainDenom() { return "anom"; }
    public int decimal() { return 18; }
    public String addressPrefix() { return "onomy"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "grpc-onomy-protocol.cosmostation.io"; }
    public String apiUrl() { return "https://api-onomy-protocol.cosmostation.io/"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "onomy-protocol/"; }
    public String monikerUrl() { return MONIKER_URL + "onomy-protocol/"; }
    public String homeInfoLink() { return "https://onomy.io/"; }
    public String blogInfoLink() { return "https://medium.com/onomy-protocol"; }
    public String coingeckoLink() { return  ""; }
}

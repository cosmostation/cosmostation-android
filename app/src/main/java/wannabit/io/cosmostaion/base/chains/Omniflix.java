package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.UNKNOWN_RELAYER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Omniflix extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.OMNIFLIX_MAIN; }
    public int chainImg() { return R.drawable.chain_omniflix; }
    public int chainInfoImg() { return R.drawable.infoicon_omniflix; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_omniflix; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_omniflix; }
    public int chainColor() { return R.color.color_omniflix; }
    public int chainBgColor() { return R.color.colorTransBgOmniflix; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_omniflix; }
    public String chainName() { return "omniflix"; }
    public String chainIdPrefix() { return "omniflixhub-"; }

    public int mainDenomImg() { return R.drawable.token_omniflix; }
    public String mainDenom() { return "uflix"; }
    public String addressPrefix() { return "omniflix"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-omniflix-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-omniflix.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.7970"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "omniflix/"; }
    public String monikerUrl() { return MONIKER_URL + "omniflix/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "omniflix/relay-omniflix-unknown.png"; }
    public String homeInfoLink() { return  "https://www.omniflix.network"; }
    public String blogInfoLink() { return  "https://blog.omniflix.network"; }
    public String coingeckoLink() { return  ""; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.001uflix");
    }

    public int gasDefault() { return 0; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

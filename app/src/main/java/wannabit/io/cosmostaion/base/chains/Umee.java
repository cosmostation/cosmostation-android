package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Umee extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.UMEE_MAIN; }
    public int chainImg() { return R.drawable.chain_umee; }
    public int chainInfoImg() { return R.drawable.infoicon_umee; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_umee; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_umee; }
    public int chainColor() { return R.color.color_umee; }
    public int chainBgColor() { return R.color.colorTransBgUmee; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_umee; }
    public String chainName() { return "umee"; }
    public String chainIdPrefix() { return "umee-"; }

    public int mainDenomImg() { return R.drawable.token_umee; }
    public String mainDenom() { return "uumee"; }
    public String addressPrefix() { return "umee"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-umee-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-umee.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.658"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "umee/"; }
    public String monikerUrl() { return MONIKER_URL + "umee/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "umee/relay-umee-unknown.png"; }
    public String homeInfoLink() { return  "https://www.umee.cc"; }
    public String blogInfoLink() { return  "https://medium.com/umeeblog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "umee"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.000uumee", "0.001uumee", "0.005uumee");
    }

    public int gasDefault() { return 1; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}


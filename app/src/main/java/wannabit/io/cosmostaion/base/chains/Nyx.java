package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Nyx extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.NYX_MAIN; }
    public int chainImg() { return R.drawable.chain_nyx; }
    public int chainInfoImg() { return R.drawable.infoicon_nyx; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_nyx; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_nyx; }
    public int chainColor() { return R.color.color_nyx; }
    public int chainBgColor() { return R.color.colorTransBgNyx; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_nyx; }
    public String chainName() { return "nyx"; }
    public String chainIdPrefix() { return "nyx"; }

    public int mainDenomImg() { return R.drawable.token_nyx; }
    public String mainDenom() { return "unyx"; }
    public String addressPrefix() { return "n"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-nym-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-nym.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.685"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "nyx/"; }
    public String monikerUrl() { return MONIKER_URL + "nyx/"; }
    public String homeInfoLink() { return  "https://nymtech.net"; }
    public String blogInfoLink() { return  "https://nymtech.net/blog"; }
    public String coingeckoLink() { return  ""; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.025unym");
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }


    public static String NYX_NYM_DENOM = "unym";
}

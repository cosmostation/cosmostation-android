package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.UNKNOWN_RELAYER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Emoney extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.EMONEY_MAIN; }
    public int chainImg() { return R.drawable.chain_emoney; }
    public int chainInfoImg() { return R.drawable.infoicon_emoney; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_emoney; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_emoney; }
    public int chainColor() { return R.color.color_emoney; }
    public int chainBgColor() { return R.color.colorTransBgEmoney; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_emoney; }
    public String chainName() { return "emoney"; }
    public String chainTitle() { return "(E-Money)"; }
    public String chainTitleToUp() { return "E-MONEY"; }
    public String chainIdPrefix() { return "emoney-"; }

    public int mainDenomImg() { return R.drawable.token_emoney; }
    public String mainDenom() { return "ungm"; }
    public String addressPrefix() { return "emoney"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-emoney-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-emoney.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("24.8486"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "emoney/"; }
    public String monikerUrl() { return MONIKER_URL + "emoney/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "emoney/relay-emoney-unknown.png"; }
    public String homeInfoLink() { return  "https://www.e-money.com"; }
    public String blogInfoLink() { return  "https://medium.com/e-money-com"; }
    public String coingeckoLink() { return  COINGECKO_URL + "e-money"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.1ungm", "0.3ungm", "1ungm");
    }

    public int gasDefault() { return 1; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }


    public static String EMONEY_EUR_DENOM = "eeur";
    public static String EMONEY_CHF_DENOM = "echf";
    public static String EMONEY_DKK_DENOM = "edkk";
    public static String EMONEY_NOK_DENOM = "enok";
    public static String EMONEY_SEK_DENOM = "esek";
}

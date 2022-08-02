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

public class Regen extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.REGEN_MAIN; }
    public int chainImg() { return R.drawable.chain_regen; }
    public int chainInfoImg() { return R.drawable.infoicon_regen; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_regen; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_regen; }
    public int chainColor() { return R.color.color_regen; }
    public int chainBgColor() { return R.color.colorTransBgRegen; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_regen; }
    public String chainName() { return "regen"; }
    public String chainIdPrefix() { return "regen-"; }

    public int mainDenomImg() { return R.drawable.token_regen; }
    public String mainDenom() { return "uregen"; }
    public String addressPrefix() { return "regen"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-regen-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-regen.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.2491"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "regen/"; }
    public String monikerUrl() { return MONIKER_URL + "regen/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "regen/relay-regen-unknown.png"; }
    public String homeInfoLink() { return  "https://www.regen.network"; }
    public String blogInfoLink() { return  "https://medium.com/regen-network"; }
    public String coingeckoLink() { return  COINGECKO_URL + "regen"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.00025uregen", "0.0025uregen", "0.025uregen");
    }

    public int gasDefault() { return 1; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

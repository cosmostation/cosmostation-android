package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Sif extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.SIF_MAIN; }
    public int chainImg() { return R.drawable.chain_sif; }
    public int chainInfoImg() { return R.drawable.infoicon_sif; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_sif; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_sif; }
    public int chainColor() { return R.color.color_sif; }
    public int chainBgColor() { return R.color.colorTransBgSif; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_sif; }
    public String chainName() { return "sifchain"; }
    public String chainTitle() { return "(SifChain)"; }
    public String chainTitleToUp() { return "SIF"; }
    public String chainIdPrefix() { return "sifchain-"; }

    public int mainDenomImg() { return R.drawable.token_sif; }
    public String mainDenom() { return "rowan"; }
    public String mainSymbol() { return "ROWAN"; }
    public int decimal() { return 18; }
    public String addressPrefix() { return "sif"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return true; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-sifchain-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-sifchain.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.7246"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "sifchain/"; }
    public String monikerUrl() { return MONIKER_URL + "sif/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "sifchain/relay-sifchain-unknown.png"; }
    public String homeInfoLink() { return  "https://sifchain.finance"; }
    public String blogInfoLink() { return  "https://medium.com/sifchain-finance"; }
    public String coingeckoLink() { return  COINGECKO_URL + "sifchain"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0rowan");
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

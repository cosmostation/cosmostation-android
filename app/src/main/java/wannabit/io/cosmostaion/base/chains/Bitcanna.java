package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Bitcanna extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.BITCANNA_MAIN; }
    public int chainImg() { return R.drawable.chain_bitcanna; }
    public int chainInfoImg() { return R.drawable.infoicon_bitcanna; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_bitcanna; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_bitcanna; }
    public int chainColor() { return R.color.color_bitcanna; }
    public int chainBgColor() { return R.color.colorTransBgBitcanna; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_bitcanna; }
    public String chainName() { return "bitcanna"; }
    public String chainIdPrefix() { return "bitcanna-"; }

    public int mainDenomImg() { return R.drawable.token_bitcanna; }
    public String mainDenom() { return "ubcna"; }
    public String addressPrefix() { return "bcna"; }

        public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-bitcanna-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-bitcanna.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.0256"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "bitcanna/"; }
    public String monikerUrl() { return MONIKER_URL + "bitcanna/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "bitcanna/relay-bitcanna-unknown.png"; }
    public String homeInfoLink() { return  "https://www.bitcanna.io"; }
    public String blogInfoLink() { return  "https://medium.com/@BitCannaGlobal"; }
    public String coingeckoLink() { return  COINGECKO_URL + "bitcanna"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.025ubcna");
    }

    public int gasDefault() { return 0; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}

package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Cudos extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.CUDOS_MAIN; }
    public int chainImg() { return R.drawable.chain_cudos; }
    public int chainInfoImg() { return R.drawable.infoicon_cudos; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_cudos; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_cudos; }
    public int chainColor() { return R.color.color_cudos; }
    public int chainBgColor() { return R.color.colorTransBgCudos; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_cudos; }
    public String chainName() { return "cudos"; }
    public String chainIdPrefix() { return "cudos-"; }

    public int mainDenomImg() { return R.drawable.token_cudos; }
    public String mainDenom() { return "acudos"; }
    public int decimal() {
        return 18;
    }
    public String addressPrefix() { return "cudos"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-cudos-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-cudos.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.060"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "cudos/"; }
    public String monikerUrl() { return MONIKER_URL + "cudos/"; }
    public String homeInfoLink() { return  "https://www.cudos.org"; }
    public String blogInfoLink() { return  "https://www.cudos.org/blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "cudos"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("5000000000000acudos");
    }
}

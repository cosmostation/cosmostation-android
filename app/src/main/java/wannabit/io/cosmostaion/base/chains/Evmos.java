package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import com.google.common.collect.ImmutableList;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Evmos extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.EVMOS_MAIN; }
    public int chainImg() { return R.drawable.chain_evmos; }
    public int chainInfoImg() { return R.drawable.infoicon_evmos; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_evmos; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_evmos; }
    public int chainColor() { return R.color.color_evmos; }
    public int chainBgColor() { return R.color.colorTransBgEvmos; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_evmos; }
    public String chainName() { return "evmos"; }
    public String chainKoreanName() { return "이비모스"; }
    public String chainIdPrefix() { return "evmos_"; }

    public int mainDenomImg() { return R.drawable.token_evmos; }
    public String mainDenom() { return "aevmos"; }
    public int decimal() {
        return 18;
    }
    public String addressPrefix() { return "evmos"; }
    public boolean ethAccountType() { return true; }

    public boolean evmSupport() { return true; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return true; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "lcd-evmos-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-evmos.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.824"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "evmos/"; }
    public String monikerUrl() { return MONIKER_URL + "evmos/"; }
    public String homeInfoLink() { return  "https://evmos.org"; }
    public String blogInfoLink() { return  "https://evmos.blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "evmos"; }

    public String defaultPath() { return "m/44'/60'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }
}

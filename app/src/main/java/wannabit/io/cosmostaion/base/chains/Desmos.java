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

public class Desmos extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.DESMOS_MAIN; }
    public int chainImg() { return R.drawable.chain_desmos; }
    public int chainInfoImg() { return R.drawable.infoicon_desmos; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_desmos; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_desmos; }
    public int chainColor() { return R.color.color_desmos; }
    public int chainBgColor() { return R.color.colorTransBgDesmos; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_desmos; }
    public String chainName() { return "desmos"; }
    public String chainKoreanName() { return "데스모스"; }
    public String chainIdPrefix() { return "desmos-"; }

    public int mainDenomImg() { return R.drawable.token_desmos; }
    public String mainDenom() { return "udsm"; }
    public String addressPrefix() { return "desmos"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-desmos-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-desmos.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.1605"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "desmos/"; }
    public String monikerUrl() { return MONIKER_URL + "desmos/"; }
    public String homeInfoLink() { return  "https://www.desmos.network"; }
    public String blogInfoLink() { return  "https://medium.com/desmosnetwork"; }
    public String coingeckoLink() { return  COINGECKO_URL + "desmos"; }

    public String defaultPath() { return "m/44'/852'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(852, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }
}

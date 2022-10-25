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

public class Injective extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.INJ_MAIN; }
    public int chainImg() { return R.drawable.chain_injective; }
    public int chainInfoImg() { return R.drawable.infoicon_injective; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_injective; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_injective; }
    public int chainColor() { return R.color.color_injective; }
    public int chainBgColor() { return R.color.colorTransBgInjective; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_injective; }
    public String chainName() { return "injective"; }
    public String chainKoreanName() { return "인젝티브"; }
    public String chainIdPrefix() { return "injective-"; }

    public int mainDenomImg() { return R.drawable.token_injective; }
    public String mainDenom() { return "inj"; }
    public String mainSymbol() { return "INJ"; }
    public int decimal() { return 18; }
    public String addressPrefix() { return "inj"; }
    public boolean ethAccountType() { return true; }

    public boolean bridgeCoinSupport() { return true; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "lcd-inj-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-inj.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("2.4865"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "injective/"; }
    public String monikerUrl() { return MONIKER_URL + "injective/"; }
    public String homeInfoLink() { return  "https://injectiveprotocol.com"; }
    public String blogInfoLink() { return  "https://blog.injectiveprotocol.com"; }
    public String coingeckoLink() { return  COINGECKO_URL + "injective-protocol"; }

    public String defaultPath() { return "m/44'/60'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }
}


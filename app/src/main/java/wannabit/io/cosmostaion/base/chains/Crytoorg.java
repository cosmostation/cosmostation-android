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

public class Crytoorg extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.CRYPTO_MAIN; }
    public int chainImg() { return R.drawable.chaincrypto; }
    public int chainInfoImg() { return R.drawable.cryptochain_img; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_crypto; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_crypto; }
    public int chainColor() { return R.color.color_cryto; }
    public int chainBgColor() { return R.color.colorTransBgCryto; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_cryto; }
    public String chainName() { return "cryptoorg"; }
    public String chainKoreanName() { return "크립토"; }
    public String chainTitle() { return "(Crypto.org)"; }
    public String chainTitleToUp() { return "CRYPTO.ORG"; }
    public String chainIdPrefix() { return "crypto-org-"; }

    public int mainDenomImg() { return R.drawable.token_crypto; }
    public String mainDenom() { return "basecro"; }
    public String mainSymbol() { return "CRO"; }
    public int decimal() {
        return 8;
    }
    public String addressPrefix() { return "cro"; }
    public String validatorPrefix() { return "crocncl"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-cryptocom-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-cryptocom.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.1939"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "crypto-org/"; }
    public String monikerUrl() { return MONIKER_URL + "cryto/"; }
    public String homeInfoLink() { return  "https://crypto.org"; }
    public String blogInfoLink() { return  "https://blog.crypto.com"; }
    public String coingeckoLink() { return  COINGECKO_URL + "cronos"; }

    public String defaultPath() { return "m/44'/394'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(394, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }
}

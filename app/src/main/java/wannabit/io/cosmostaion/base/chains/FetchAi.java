package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class FetchAi extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.FETCHAI_MAIN; }
    public int chainImg() { return R.drawable.chain_fetchai; }
    public int chainInfoImg() { return R.drawable.infoicon_fetchai; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_fetch; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_fetch; }
    public int chainColor() { return R.color.color_fetch; }
    public int chainBgColor() { return R.color.colorTransBgFetch; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_fetch; }
    public String chainName() { return "fetchai"; }
    public String chainIdPrefix() { return "fetchhub-"; }

    public int mainDenomImg() { return R.drawable.token_fetchai; }
    public String mainDenom() { return "afet"; }
    public int decimal() {
        return 18;
    }
    public String addressPrefix() { return "fetch"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-fetchai-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-fetchai.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.0678"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "fetchai/"; }
    public String monikerUrl() { return MONIKER_URL + "fetchai/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "fetchai/relay-fetchai-unknown.png"; }
    public String homeInfoLink() { return  "https://fetch.ai"; }
    public String blogInfoLink() { return  "https://fetch.ai/blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "fetch-ai"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0afet");
    }

    public List<ChildNumber> setParentPath(int customPath) {
        if (customPath == 0) {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        } else if (customPath == 1) {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        } else if (customPath == 2) {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true));
        } else {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED);
        }
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X", "m/44'/60'/0'/0/X", "m/44'/60'/X'/0/0", "m/44'/60'/0'/X");
    }
}

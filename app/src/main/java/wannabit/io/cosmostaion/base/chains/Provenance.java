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

public class Provenance extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.PROVENANCE_MAIN; }
    public int chainImg() { return R.drawable.chain_provenance; }
    public int chainInfoImg() { return R.drawable.infoicon_provenance; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_provenance; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_provenance; }
    public int chainColor() { return R.color.color_provenance; }
    public int chainBgColor() { return R.color.colorTransBgProvenance; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_provenance; }
    public String chainName() { return "provenance"; }
    public String chainIdPrefix() { return "pio-mainnet-"; }

    public int mainDenomImg() { return R.drawable.token_provenance; }
    public String mainDenom() { return "nhash"; }
    public int decimal() { return 9; }
    public String addressPrefix() { return "pb"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-provenance-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-provenance.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.3061"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "provenance/"; }
    public String monikerUrl() { return MONIKER_URL + "provenance/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "provenance/relay-provenance-unknown.png"; }
    public String homeInfoLink() { return  "https://www.provenance.io"; }
    public String blogInfoLink() { return  "https://www.provenance.io/blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "provenance-blockchain"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("2000nhash");
    }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(505, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/505'/0'/0/X");
    }
}

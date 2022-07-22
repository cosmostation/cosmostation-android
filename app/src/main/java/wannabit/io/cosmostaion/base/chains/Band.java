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

public class Band extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.BAND_MAIN; }
    public int chainImg() { return R.drawable.chain_band; }
    public int chainInfoImg() { return R.drawable.infoicon_band; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_band; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_band; }
    public int chainColor() { return R.color.color_band; }
    public int chainBgColor() { return R.color.colorTransBgBand; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_band; }
    public String chainName() { return "band"; }
    public String chainIdPrefix() { return "laozi-mainnet"; }

    public int mainDenomImg() { return R.drawable.token_band; }
    public String mainDenom() { return "uband"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public String addressPrefix() { return "band"; }

    public String grpcUrl() { return "lcd-band-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-band.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("3.0236"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "band/"; }
    public String monikerUrl() { return MONIKER_URL + "bandprotocol/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "band/relay-band-unknown.png"; }
    public String homeInfoLink() { return  "https://bandprotocol.com"; }
    public String blogInfoLink() { return  "https://medium.com/bandprotocol"; }
    public String coingeckoLink() { return  COINGECKO_URL + "band-protocol"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(494, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/494'/0'/0/X");
    }
}

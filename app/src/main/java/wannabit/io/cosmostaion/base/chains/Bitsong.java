package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.UNKNOWN_RELAYER_URL;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Bitsong extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.BITSONG_MAIN; }
    public int chainImg() { return R.drawable.chain_bitsong; }
    public int chainInfoImg() { return R.drawable.infoicon_bitsong; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_bitsong; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_bitsong; }
    public int chainColor() { return R.color.color_bitsong; }
    public int chainBgColor() { return R.color.colorTransBgBitsong; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_bitsong; }
    public String chainName() { return "bitsong"; }
    public String chainIdPrefix() { return "bitsong-"; }

    public int mainDenomImg() { return R.drawable.token_bitsong; }
    public String mainDenom() { return "ubtsg"; }
    public String addressPrefix() { return "bitsong"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-bitsong-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-bitsong.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.9040"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "bitsong/"; }
    public String monikerUrl() { return MONIKER_URL + "bitsong/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "bitsong/relay-bitsong-unknown.png"; }
    public String homeInfoLink() { return  "https://bitsong.io"; }
    public String blogInfoLink() { return  "https://bitsongofficial.medium.com"; }
    public String coingeckoLink() { return  COINGECKO_URL + "bitsong"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.025ubtsg");
    }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(639, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/639'/0'/0/X");
    }
}


package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannel;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.network.HistoryApi;

public class Crescent extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.CRESCENT_MAIN; }
    public int chainImg() { return R.drawable.chain_crescent; }
    public int chainInfoImg() { return R.drawable.infoicon_crescent; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_crescent; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_crescent; }
    public int chainColor() { return R.color.color_crescent; }
    public int chainBgColor() { return R.color.colorTransBgCrescent; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_crescent; }
    public String chainName() { return "crescent"; }
    public String chainIdPrefix() { return "crescent-"; }

    public int mainDenomImg() { return R.drawable.token_crescent; }
    public String mainDenom() { return "ucre"; }
    public String mainSymbol() { return "CRE"; }
    public int sendImgColor() { return chainColor(); }
    public int sendBgColor() { return R.color.color_crescent3; }

    public String addressPrefix() { return "cre"; }
    public String addressHdPath0() { return "m/44'/118'/0'/0/X"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return true; }

    public String grpcUrl() { return "lcd-crescent-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-crescent.cosmostation.io/"; }
    public ManagedChannel getConnection() { return ChannelBuilder.getCrescentMain(baseChain()); }
    public HistoryApi getHistoryApi() { return ApiClient.getCrescentApi(baseChain()); }

    public BigDecimal blockTime() { return new BigDecimal("6.355"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "crescent/"; }
    public String monikerUrl() { return MONIKER_URL + "crescent/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "crescent/relay-crescent-unknown.png"; }
    public String homeInfoLink() { return  "https://crescent.network"; }
    public String blogInfoLink() { return  "https://crescentnetwork.medium.com"; }
    public String coingeckoLink() { return  COINGECKO_URL + "crescent-network"; }


    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList(addressHdPath0());
    }

    public String getHdPath(int customPath, String path) {
        return supportHdPaths().get(customPath).replace("X", path);
    }


    public static String CRESCENT_BCRE_DENOM = "ubcre";
}

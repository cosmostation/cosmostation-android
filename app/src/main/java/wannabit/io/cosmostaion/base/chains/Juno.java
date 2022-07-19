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

import io.grpc.ManagedChannel;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.network.HistoryApi;

public class Juno extends ChainConfig{

    public BaseChain baseChain() { return BaseChain.JUNO_MAIN; }
    public int chainImg() { return R.drawable.chain_juno; }
    public int chainInfoImg() { return R.drawable.infoicon_juno; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_juno; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_juno; }
    public int chainColor() { return R.color.color_juno; }
    public int chainBgColor() { return R.color.colorTransBgJuno; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_juno; }
    public String chainName() { return "juno"; }
    public String chainIdPrefix() { return "juno-"; }

    public int mainDenomImg() { return R.drawable.token_juno; }
    public String mainDenom() { return "ujuno"; }
    public String mainSymbol() { return "JUNO"; }

    public String addressPrefix() { return "juno"; }
    public String addressHdPath0() { return "m/44'/118'/0'/0/X"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-juno-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-juno.cosmostation.io/"; }
    public ManagedChannel getConnection() { return ChannelBuilder.getJunoMain(baseChain()); }
    public HistoryApi getHistoryApi() { return ApiClient.getJunoApi(baseChain()); }

    public BigDecimal blockTime() { return new BigDecimal("6.3104"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "juno/"; }
    public String monikerUrl() { return MONIKER_URL + "juno/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "juno/relay-juno-unknown.png"; }
    public String homeInfoLink() { return  "https://junochain.com"; }
    public String blogInfoLink() { return  "https://medium.com/@JunoNetwork"; }
    public String coingeckoLink() { return  COINGECKO_URL + "juno-network"; }


    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList(addressHdPath0());
    }

    public String getHdPath(int customPath, String path) {
        return supportHdPaths().get(customPath).replace("X", path);
    }
}

package wannabit.io.cosmostaion.base.chains;

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

public class ChainConfig {

    public BaseChain baseChain() { return null; }
    public int chainImg() { return R.drawable.chain_default; }
    public int chainInfoImg() { return 0; }
    public int chainInfoTitle() { return 0; }
    public int chainInfoMsg() { return 0; }
    public int chainColor() { return 0; }
    public int chainBgColor() { return R.color.colorTransBg; }
    public int chainTabColor() { return 0; }
    public String chainName() { return "unknown"; }
    public String chainTitle() { return "(" + chainName().substring(0, 1).toUpperCase() + chainName().substring(1) + ")"; }
    public String chainTitleToUp() { return chainName().toUpperCase(); }
    public String chainIdPrefix() { return ""; }

    public int mainDenomImg() { return 0; }
    public String mainDenom() { return ""; }
    public String mainSymbol() { return ""; }
    public int sendImgColor() { return R.color.colorWhite; }
    public int sendBgColor() { return chainColor(); }

    public String addressPrefix() { return ""; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return ""; }
    public int grpcPort() { return 9090; }
    public String lcdUrl() { return ""; }
    public String apiUrl() { return ""; }
    public ManagedChannel getConnection() { return null; }
    public HistoryApi getHistoryApi() { return null; }

    public BigDecimal blockTime() { return BigDecimal.ZERO; }
    public String explorerUrl() { return ""; }
    public String monikerUrl() { return ""; }
    public String relayerImgUrl() { return ""; }
    public String homeInfoLink() { return  ""; }
    public String blogInfoLink() { return  ""; }
    public String coingeckoLink() { return  ""; }


    public List<ChildNumber> setParentPath(int customPath) {return null;}
    public ArrayList<String> supportHdPaths() { return null; }
    public String getHdPath(int customPath, String path) { return ""; }

}

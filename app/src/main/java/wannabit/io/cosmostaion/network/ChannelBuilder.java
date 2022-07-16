package wannabit.io.cosmostaion.network;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;

public class ChannelBuilder {
    private final static int PORT_MAIN = 9090;
//    private final static String GRPC_COSMOS_MAIN = "lcd-cosmos-app-and.cosmostation.io";
//    private final static int PORT_COSMOS_MAIN = 9090;
//
//    private final static String GRPC_IRIS_MAIN = "lcd-iris-app.cosmostation.io";
//    private final static int PORT_IRIS_MAIN = 9090;
//
//    private final static String GRPC_AKASH_MAIN = "lcd-akash-app.cosmostation.io";
//    private final static int PORT_AKASH_MAIN = 9090;
//
//    private final static String GRPC_SENTINEL_MAIN = "lcd-sentinel-app.cosmostation.io";
//    private final static int PORT_SENTINEL_MAIN = 9090;
//
//    private final static String GRPC_PERSIS_MAIN = "lcd-persistence-app.cosmostation.io";
//    private final static int PORT_PERSIS_MAIN = 9090;
//
//    private final static String GRPC_CRYPTO_MAIN = "lcd-cryptocom-app.cosmostation.io";
//    private final static int PORT_CRYPTO_MAIN = 9090;
//
//    private final static String GRPC_OSMOSIS_MAIN = "lcd-osmosis-app-and.cosmostation.io";
//    private final static int PORT_OSMOSIS_MAIN = 9090;
//
//    private final static String GRPC_STARNAME_MAIN = "lcd-iov-app.cosmostation.io";
//    private final static int PORT_STARNAME_MAIN = 9090;
//
//    private final static String GRPC_SIF_MAIN = "lcd-sifchain-app.cosmostation.io";
//    private final static int PORT_SIF_MAIN = 9090;
//
//    private final static String GRPC_MEDI_MAIN = "lcd-medibloc-app.cosmostation.io";
//    private final static int PORT_MEDI_MAIN = 9090;
//
//    private final static String GRPC_CERTIK_MAIN = "lcd-certik-app.cosmostation.io";
//    private final static int PORT_CERTIK_MAIN = 9090;
//
//    private final static String GRPC_EMONEY_MAIN = "lcd-emoney-app.cosmostation.io";
//    private final static int PORT_EMONEY_MAIN = 9090;
//
//    private final static String GRPC_FETCH_MAIN = "lcd-fetchai-app.cosmostation.io";
//    private final static int PORT_FETCH_MAIN = 9090;
//
//    private final static String GRPC_BAND_MAIN = "lcd-band-app.cosmostation.io";
//    private final static int PORT_BAND_MAIN = 9090;
//
//    private final static String GRPC_RIZON_MAIN = "lcd-rizon-app.cosmostation.io";
//    private final static int PORT_RIZON_MAIN = 9090;
//
//    private final static String GRPC_JUNO_MAIN = "lcd-juno-app.cosmostation.io";
//    private final static int PORT_JUNO_MAIN = 9090;
//
//    private final static String GRPC_REGEN_MAIN = "lcd-regen-app.cosmostation.io";
//    private final static int PORT_REGEN_MAIN = 9090;
//
//    private final static String GRPC_BITCANNA_MAIN = "lcd-bitcanna-app.cosmostation.io";
//    private final static int PORT_BITCANNA_MAIN = 9090;
//
//    private final static String GRPC_ALTHEA_MAIN = "lcd-althea-app.cosmostation.io";
//    private final static int PORT_ALTHEA_MAIN = 9090;
//
//    private final static String GRPC_STARGAZE_MAIN = "lcd-stargaze-app.cosmostation.io";
//    private final static int PORT_STARGAZE_MAIN = 9090;
//
//    private final static String GRPC_KI_MAIN = "lcd-kichain-app.cosmostation.io";
//    private final static int PORT_KI_MAIN = 9090;
//
//    private final static String GRPC_SECRET_MAIN = "lcd-secret.cosmostation.io";
//    private final static int PORT_SECRET_MAIN = 9090;
//
//    private final static String GRPC_INJ_MAIN = "lcd-inj-app.cosmostation.io";
//    private final static int PORT_INJ_MAIN = 9090;
//
//    private final static String GRPC_COMDEX_MAIN = "lcd-comdex-app.cosmostation.io";
//    private final static int PORT_COMDEX_MAIN = 9090;
//
//    private final static String GRPC_BITSONG_MAIN = "lcd-bitsong-app.cosmostation.io";
//    private final static int PORT_BITSONG_MAIN = 9090;
//
//    private final static String GRPC_DESMOS_MAIN = "lcd-desmos-app.cosmostation.io";
//    private final static int PORT_DESMOS_MAIN = 9090;
//
//    private final static String GRPC_GRABRIDGE_MAIN = "lcd-gravity-bridge-app.cosmostation.io";
//    private final static int PORT_GRABRIDGE_MAIN = 9090;
//
//    private final static String GRPC_LUM_MAIN = "lcd-lum-app.cosmostation.io";
//    private final static int PORT_LUM_MAIN = 9090;
//
//    private final static String GRPC_CHIHUAHUA_MAIN = "lcd-chihuahua-app.cosmostation.io";
//    private final static int PORT_CHIHUAHUA_MAIN = 9090;
//
//    private final static String GRPC_KAVA_MAIN = "lcd-kava-app.cosmostation.io";
//    private final static int PORT_KAVA_MAIN = 9090;
//
//    private final static String GRPC_AXELAR_MAIN = "lcd-axelar-app.cosmostation.io";
//    private final static int PORT_AXELAR_MAIN = 9090;
//
//    private final static String GRPC_KONSTELL_MAIN = "lcd-konstellation-app.cosmostation.io";
//    private final static int PORT_KONSTELL_MAIN = 9090;
//
//    private final static String GRPC_UMEE_MAIN = "lcd-umee-app.cosmostation.io";
//    private final static int PORT_UMEE_MAIN = 9090;
//
//    private final static String GRPC_EVMOS_MAIN = "lcd-evmos-app.cosmostation.io";
//    private final static int PORT_EVMOS_MAIN = 9090;
//
//    private final static String GRPC_CUDOS_MAIN = "lcd-cudos-app.cosmostation.io";
//    private final static int PORT_CUDOS_MAIN = 9090;
//
//    private final static String GRPC_PROVENANCE_MAIN = "lcd-provenance-app.cosmostation.io";
//    private final static int PORT_PROVENANCE_MAIN = 9090;
//
//    private final static String GRPC_CERBERUS_MAIN = "lcd-cerberus-app.cosmostation.io";
//    private final static int PORT_CERBERUS_MAIN = 9090;
//
//    private final static String GRPC_OMNIFLIX_MAIN = "lcd-omniflix-app.cosmostation.io";
//    private final static int PORT_OMNIFLIX_MAIN = 9090;
//
//    private final static String GRPC_CRESCENT_MAIN = "lcd-crescent-app.cosmostation.io";
//    private final static int PORT_CRESCENT_MAIN = 9090;
//
//    private final static String GRPC_MANTLE_MAIN = "lcd-asset-mantle-app.cosmostation.io";
//    private final static int PORT_MANTLE_MAIN = 9090;
//
//    private final static String GRPC_STATION_TEST = "lcd-office.cosmostation.io";
//    private final static int PORT_STATION_TEST = 10400;
//
//    private final static String GRPC_NYX_MAIN = "lcd-nym-app.cosmostation.io";
//    private final static int PORT_NYX_MAIN = 9090;
//
//    private final static String GRPC_COSMOS_TEST = "lcd-office.cosmostation.io";
//    private final static int PORT_COSMOS_TEST = 10300;
//
//    private final static String GRPC_IRIS_TEST = "lcd-office.cosmostation.io";
//    private final static int PORT_IRIS_TEST = 9095;
//
//    private final static String GRPC_ALTHEA_TEST = "lcd-office.cosmostation.io";
//    private final static int PORT_ALTHEA_TEST = 20100;
//
//    private final static String GRPC_CRESCENT_TEST = "lcd-office.cosmostation.io";
//    private final static int PORT_CRESCENT_TEST = 20400;

    public final static int TIME_OUT = 8;


    public static ManagedChannel getChain(BaseChain chain) {
        if (chain != null) {
            ChainConfig chainConfig = ChainFactory.getChain(chain);
            return getChannelMain(chainConfig);
        }
        return null;
    }

    private static ManagedChannel channel_main = null;
    public static ManagedChannel getChannelMain(ChainConfig chainConfig) {
        synchronized (ChannelBuilder.class) {
            channel_main = ManagedChannelBuilder.forAddress(chainConfig.grpcUrl(), PORT_MAIN)
                    .usePlaintext()
                    .build();
        }
        return channel_main;
    }
}

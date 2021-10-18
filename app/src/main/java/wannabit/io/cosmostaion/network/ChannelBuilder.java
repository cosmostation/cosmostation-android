package wannabit.io.cosmostaion.network;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import wannabit.io.cosmostaion.base.BaseChain;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_TEST;

public class ChannelBuilder {
    private final static String GRPC_COSMOS_MAIN = "lcd-cosmos-app.cosmostation.io";
    private final static int PORT_COSMOS_MAIN = 9090;

    private final static String GRPC_IRIS_MAIN = "lcd-iris-app.cosmostation.io";
    private final static int PORT_IRIS_MAIN = 9090;

    private final static String GRPC_AKASH_MAIN = "lcd-akash-app.cosmostation.io";
    private final static int PORT_AKASH_MAIN = 9090;

    private final static String GRPC_SENTINEL_MAIN = "lcd-sentinel-app.cosmostation.io";
    private final static int PORT_SENTINEL_MAIN = 9090;

    private final static String GRPC_PERSIS_MAIN = "lcd-persistence-app.cosmostation.io";
    private final static int PORT_PERSIS_MAIN = 9090;

    private final static String GRPC_CRYPTO_MAIN = "lcd-cryptocom-app.cosmostation.io";
    private final static int PORT_CRYPTO_MAIN = 9090;

    private final static String GRPC_OSMOSIS_MAIN = "lcd-osmosis-app.cosmostation.io";
    private final static int PORT_OSMOSIS_MAIN = 9090;

    private final static String GRPC_STARNAME_MAIN = "lcd-iov-app.cosmostation.io";
    private final static int PORT_STARNAME_MAIN = 9090;

    private final static String GRPC_SIF_MAIN = "lcd-sifchain-app.cosmostation.io";
    private final static int PORT_SIF_MAIN = 9090;

    private final static String GRPC_MEDI_MAIN = "lcd-medibloc-app.cosmostation.io";
    private final static int PORT_MEDI_MAIN = 9090;

    private final static String GRPC_CERTIK_MAIN = "lcd-certik-app.cosmostation.io";
    private final static int PORT_CERTIK_MAIN = 9090;

    private final static String GRPC_EMONEY_MAIN = "lcd-emoney-app.cosmostation.io";
    private final static int PORT_EMONEY_MAIN = 9090;

    private final static String GRPC_FETCH_MAIN = "lcd-fetchai-app.cosmostation.io";
    private final static int PORT_FETCH_MAIN = 9090;

    private final static String GRPC_BAND_MAIN = "lcd-band-app.cosmostation.io";
    private final static int PORT_BAND_MAIN = 9090;

    private final static String GRPC_RIZON_MAIN = "lcd-rizon-app.cosmostation.io";
    private final static int PORT_RIZON_MAIN = 9090;

    private final static String GRPC_JUNO_MAIN = "lcd-juno-app.cosmostation.io";
    private final static int PORT_JUNO_MAIN = 9090;


    private final static String GRPC_COSMOS_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_COSMOS_TEST = 10300;

    private final static String GRPC_IRIS_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_IRIS_TEST = 9095;

    private final static String GRPC_ALTHEA_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_ALTHEA_TEST = 20100;

    private final static String GRPC_RIZON_TEST = "lcd-rizon-testnet.cosmostation.io";
    private final static int PORT_RIZON_TEST = 9090;

    private final static String GRPC_UMEE_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_UMEE_TEST = 40800;

    private final static String GRPC_AXELAR_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_AXELAR_TEST = 40600;


    public final static int TIME_OUT = 8;


    public static ManagedChannel getChain(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            return getCosmosMain();
        } else if (chain.equals(IRIS_MAIN)) {
            return getIrisMain();
        } else if (chain.equals(AKASH_MAIN)) {
            return getAkashMain();
        } else if (chain.equals(SENTINEL_MAIN)) {
            return getSentinelMain();
        } else if (chain.equals(PERSIS_MAIN)) {
            return getPersisMain();
        } else if (chain.equals(CRYPTO_MAIN)) {
            return getCryptoMain();
        } else if (chain.equals(OSMOSIS_MAIN)) {
            return getOsmosisMain();
        } else if (chain.equals(IOV_MAIN)) {
            return getStarnameMain();
        } else if (chain.equals(SIF_MAIN)) {
            return getSifMain();
        } else if (chain.equals(MEDI_MAIN)) {
            return getMediMain();
        } else if (chain.equals(CERTIK_MAIN)) {
            return getCertikMain();
        } else if (chain.equals(EMONEY_MAIN)) {
            return getEmoneyMain();
        } else if (chain.equals(FETCHAI_MAIN)) {
            return getFetchMain();
        } else if (chain.equals(BAND_MAIN)) {
            return getBandMain();
        } else if (chain.equals(RIZON_MAIN)) {
            return getRizonMain();
        } else if (chain.equals(JUNO_MAIN)) {
            return getJunoMain();

        } else if (chain.equals(COSMOS_TEST)) {
            return getCosmosTest();
        } else if (chain.equals(IRIS_TEST)) {
            return getIrisTest();
        } else if (chain.equals(RIZON_TEST)) {
            return getRizonTest();
        } else if (chain.equals(ALTHEA_TEST)) {
            return getAltheaTest();
        } else if (chain.equals(UMEE_TEST)) {
            return getUmeeTest();
        } else if (chain.equals(AXELAR_TEST)) {
            return getAxelarTest();
        }
        return null;
    }



    //Channel for cosmos main
    private static ManagedChannel channel_cosmos_main = null;
    public static ManagedChannel getCosmosMain() {
        if (channel_cosmos_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_cosmos_main = ManagedChannelBuilder.forAddress(GRPC_COSMOS_MAIN, PORT_COSMOS_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_cosmos_main;
    }

    //Channel for iris main
    private static ManagedChannel channel_iris_main = null;
    public static ManagedChannel getIrisMain() {
        if (channel_iris_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_iris_main = ManagedChannelBuilder.forAddress(GRPC_IRIS_MAIN, PORT_IRIS_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_iris_main;
    }

    //Channel for akash main
    private static ManagedChannel channel_akash_main = null;
    public static ManagedChannel getAkashMain() {
        if (channel_akash_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_akash_main = ManagedChannelBuilder.forAddress(GRPC_AKASH_MAIN, PORT_AKASH_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_akash_main;
    }

    //Channel for sentinel main
    private static ManagedChannel channel_sentinel_main = null;
    public static ManagedChannel getSentinelMain() {
        if (channel_sentinel_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_sentinel_main = ManagedChannelBuilder.forAddress(GRPC_SENTINEL_MAIN, PORT_SENTINEL_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_sentinel_main;
    }

    //Channel for persistence main
    private static ManagedChannel channel_persis_main = null;
    public static ManagedChannel getPersisMain() {
        if (channel_persis_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_persis_main = ManagedChannelBuilder.forAddress(GRPC_PERSIS_MAIN, PORT_PERSIS_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_persis_main;
    }

    //Channel for cryto.org main
    private static ManagedChannel channel_crypto_main = null;
    public static ManagedChannel getCryptoMain() {
        if (channel_crypto_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_crypto_main = ManagedChannelBuilder.forAddress(GRPC_CRYPTO_MAIN, PORT_CRYPTO_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_crypto_main;
    }

    //Channel for osmosis main
    private static ManagedChannel channel_osmosis_main = null;
    public static ManagedChannel getOsmosisMain() {
        if (channel_osmosis_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_osmosis_main = ManagedChannelBuilder.forAddress(GRPC_OSMOSIS_MAIN, PORT_OSMOSIS_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_osmosis_main;
    }

    //Channel for starname main
    private static ManagedChannel channel_starname_main = null;
    public static ManagedChannel getStarnameMain() {
        if (channel_starname_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_starname_main = ManagedChannelBuilder.forAddress(GRPC_STARNAME_MAIN, PORT_STARNAME_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_starname_main;
    }

    //Channel for sif main
    private static ManagedChannel channel_sif_main = null;
    public static ManagedChannel getSifMain() {
        if (channel_sif_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_sif_main = ManagedChannelBuilder.forAddress(GRPC_SIF_MAIN, PORT_SIF_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_sif_main;
    }

    //Channel for medibloc main
    private static ManagedChannel channel_medi_main = null;
    public static ManagedChannel getMediMain() {
        if (channel_medi_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_medi_main = ManagedChannelBuilder.forAddress(GRPC_MEDI_MAIN, PORT_MEDI_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_medi_main;
    }

    //Channel for certik main
    private static ManagedChannel channel_certik_main = null;
    public static ManagedChannel getCertikMain() {
        if (channel_certik_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_certik_main = ManagedChannelBuilder.forAddress(GRPC_CERTIK_MAIN, PORT_CERTIK_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_certik_main;
    }

    //Channel for emoney main
    private static ManagedChannel channel_emoney_main = null;
    public static ManagedChannel getEmoneyMain() {
        if (channel_emoney_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_emoney_main = ManagedChannelBuilder.forAddress(GRPC_EMONEY_MAIN, PORT_EMONEY_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_emoney_main;
    }

    //Channel for fetchai main
    private static ManagedChannel channel_fetch_main = null;
    public static ManagedChannel getFetchMain() {
        if (channel_fetch_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_fetch_main = ManagedChannelBuilder.forAddress(GRPC_FETCH_MAIN, PORT_FETCH_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_fetch_main;
    }

    //Channel for band main
    private static ManagedChannel channel_band_main = null;
    public static ManagedChannel getBandMain() {
        if (channel_band_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_band_main = ManagedChannelBuilder.forAddress(GRPC_BAND_MAIN, PORT_BAND_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_band_main;
    }

    //Channel for rizon main
    private static ManagedChannel channel_rizon_main = null;
    public static ManagedChannel getRizonMain() {
        if (channel_rizon_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_rizon_main = ManagedChannelBuilder.forAddress(GRPC_RIZON_MAIN, PORT_RIZON_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_rizon_main;
    }

    //Channel for juno main
    private static ManagedChannel channel_juno_main = null;
    public static ManagedChannel getJunoMain() {
        if (channel_juno_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_juno_main = ManagedChannelBuilder.forAddress(GRPC_JUNO_MAIN, PORT_JUNO_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_juno_main;
    }


    //Channel for stargate testnet
    private static ManagedChannel channel_cosmos_test = null;
    public static ManagedChannel getCosmosTest() {
        if (channel_cosmos_test == null) {
            synchronized (ChannelBuilder.class) {
                channel_cosmos_test = ManagedChannelBuilder.forAddress(GRPC_COSMOS_TEST, PORT_COSMOS_TEST)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_cosmos_test;
    }

    //Channel for bifrost testnet
    private static ManagedChannel channel_iris_test = null;
    public static ManagedChannel getIrisTest() {
        if (channel_iris_test == null) {
            synchronized (ChannelBuilder.class) {
                channel_iris_test = ManagedChannelBuilder.forAddress(GRPC_IRIS_TEST, PORT_IRIS_TEST)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_iris_test;
    }

    //Channel for rizon testnet
    private static ManagedChannel channel_rizon_test = null;
    public static ManagedChannel getRizonTest() {
        if (channel_rizon_test == null) {
            synchronized (ChannelBuilder.class) {
                channel_rizon_test = ManagedChannelBuilder.forAddress(GRPC_RIZON_TEST, PORT_RIZON_TEST)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_rizon_test;
    }

    //Channel for althea testnet
    private static ManagedChannel channel_althea_test = null;
    public static ManagedChannel getAltheaTest() {
        if (channel_althea_test == null) {
            synchronized (ChannelBuilder.class) {
                channel_althea_test = ManagedChannelBuilder.forAddress(GRPC_ALTHEA_TEST, PORT_ALTHEA_TEST)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_althea_test;
    }

    //Channel for umee testnet
    private static ManagedChannel channel_umee_test = null;
    public static ManagedChannel getUmeeTest() {
        if (channel_umee_test == null) {
            synchronized (ChannelBuilder.class) {
                channel_umee_test = ManagedChannelBuilder.forAddress(GRPC_UMEE_TEST, PORT_UMEE_TEST)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_umee_test;
    }

    //Channel for axelar testnet
    private static ManagedChannel channel_axelar_test = null;
    public static ManagedChannel getAxelarTest() {
        if (channel_axelar_test == null) {
            synchronized (ChannelBuilder.class) {
                channel_axelar_test = ManagedChannelBuilder.forAddress(GRPC_AXELAR_TEST, PORT_AXELAR_TEST)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_axelar_test;
    }
}

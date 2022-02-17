package wannabit.io.cosmostaion.network;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITCANNA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITSONG_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CHIHUAHUA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COMDEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.DESMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.GRABRIDGE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.INJ_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KONSTELL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.LUM_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.REGEN_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STARGAZE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_MAIN;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import wannabit.io.cosmostaion.base.BaseChain;

public class ChannelBuilder {
    private final static String GRPC_COSMOS_MAIN = "lcd-cosmos-app-and.cosmostation.io";
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

    private final static String GRPC_OSMOSIS_MAIN = "lcd-osmosis-app-and.cosmostation.io";
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

    private final static String GRPC_REGEN_MAIN = "lcd-regen-app.cosmostation.io";
    private final static int PORT_REGEN_MAIN = 9090;

    private final static String GRPC_BITCANNA_MAIN = "lcd-bitcanna-app.cosmostation.io";
    private final static int PORT_BITCANNA_MAIN = 9090;

    private final static String GRPC_ALTHEA_MAIN = "lcd-althea-app.cosmostation.io";
    private final static int PORT_ALTHEA_MAIN = 9090;

    private final static String GRPC_STARGAZE_MAIN = "lcd-stargaze-app.cosmostation.io";
    private final static int PORT_STARGAZE_MAIN = 9090;

    private final static String GRPC_KI_MAIN = "lcd-kichain-app.cosmostation.io";
    private final static int PORT_KI_MAIN = 9090;

    private final static String GRPC_SECRET_MAIN = "lcd-secret.cosmostation.io";
    private final static int PORT_SECRET_MAIN = 9090;

    private final static String GRPC_INJ_MAIN = "lcd-inj-app.cosmostation.io";
    private final static int PORT_INJ_MAIN = 9090;

    private final static String GRPC_COMDEX_MAIN = "lcd-comdex-app.cosmostation.io";
    private final static int PORT_COMDEX_MAIN = 9090;

    private final static String GRPC_BITSONG_MAIN = "lcd-bitsong-app.cosmostation.io";
    private final static int PORT_BITSONG_MAIN = 9090;

    private final static String GRPC_DESMOS_MAIN = "lcd-desmos-app.cosmostation.io";
    private final static int PORT_DESMOS_MAIN = 9090;

    private final static String GRPC_GRABRIDGE_MAIN = "lcd-gravity-bridge-app.cosmostation.io";
    private final static int PORT_GRABRIDGE_MAIN = 9090;

    private final static String GRPC_LUM_MAIN = "lcd-lum-app.cosmostation.io";
    private final static int PORT_LUM_MAIN = 9090;

    private final static String GRPC_CHIHUAHUA_MAIN = "lcd-chihuahua-app.cosmostation.io";
    private final static int PORT_CHIHUAHUA_MAIN = 9090;

    private final static String GRPC_KAVA_MAIN = "lcd-kava-app.cosmostation.io";
    private final static int PORT_KAVA_MAIN = 9090;

    private final static String GRPC_AXELAR_MAIN = "lcd-axelar-app.cosmostation.io";
    private final static int PORT_AXELAR_MAIN = 9090;

    private final static String GRPC_KONSTELL_MAIN = "lcd-konstellation-app.cosmostation.io";
    private final static int PORT_KONSTELL_MAIN = 9090;

    private final static String GRPC_UMEE_MAIN = "lcd-umee-app.cosmostation.io";
    private final static int PORT_UMEE_MAIN = 9090;

    private final static String GRPC_EVMOS_MAIN = "lcd-office.cosmostation.io";
    private final static int PORT_EVMOS_MAIN = 54100;



    private final static String GRPC_COSMOS_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_COSMOS_TEST = 10300;

    private final static String GRPC_IRIS_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_IRIS_TEST = 9095;

    private final static String GRPC_ALTHEA_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_ALTHEA_TEST = 20100;

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
        } else if (chain.equals(REGEN_MAIN)) {
            return getRegenMain();
        } else if (chain.equals(BITCANNA_MAIN)) {
            return getBitcannaMain();
        } else if (chain.equals(ALTHEA_MAIN)) {
            return getAltheaMain();
        } else if (chain.equals(STARGAZE_MAIN)) {
            return getStargazeMain();
        } else if (chain.equals(GRABRIDGE_MAIN)) {
            return getGraBridgeMain();
        } else if (chain.equals(KI_MAIN)) {
            return getKiMain();
        } else if (chain.equals(COMDEX_MAIN)) {
            return getComdexMain();
        } else if (chain.equals(SECRET_MAIN)) {
            return getSecretMain();
        } else if (chain.equals(INJ_MAIN)) {
            return getInjMain();
        } else if (chain.equals(BITSONG_MAIN)) {
            return getBitsongMain();
        } else if (chain.equals(DESMOS_MAIN)) {
            return getDesmosMain();
        } else if (chain.equals(LUM_MAIN)) {
            return getLumMain();
        } else if (chain.equals(CHIHUAHUA_MAIN)) {
            return getChihuahuaMain();
        } else if (chain.equals(KAVA_MAIN)) {
            return getKavaMain();
        } else if (chain.equals(AXELAR_MAIN)) {
            return getAxelarMain();
        } else if (chain.equals(KONSTELL_MAIN)) {
            return getKonstellMain();
        } else if (chain.equals(UMEE_MAIN)) {
            return getUmeeMain();
        } else if (chain.equals(EVMOS_MAIN)) {
            return getEvmosMain();
        }

        else if (chain.equals(COSMOS_TEST)) {
            return getCosmosTest();
        } else if (chain.equals(IRIS_TEST)) {
            return getIrisTest();
        } else if (chain.equals(ALTHEA_TEST)) {
            return getAltheaTest();
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

    //Channel for regen main
    private static ManagedChannel channel_regen_main = null;
    public static ManagedChannel getRegenMain() {
        if (channel_regen_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_regen_main = ManagedChannelBuilder.forAddress(GRPC_REGEN_MAIN, PORT_REGEN_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_regen_main;
    }

    //Channel for bitcanna main
    private static ManagedChannel channel_bitcanna_main = null;
    public static ManagedChannel getBitcannaMain() {
        if (channel_bitcanna_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_bitcanna_main = ManagedChannelBuilder.forAddress(GRPC_BITCANNA_MAIN, PORT_BITCANNA_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_bitcanna_main;
    }

    //Channel for althea main
    private static ManagedChannel channel_althea_main = null;
    public static ManagedChannel getAltheaMain() {
        if (channel_althea_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_althea_main = ManagedChannelBuilder.forAddress(GRPC_ALTHEA_MAIN, PORT_ALTHEA_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_althea_main;
    }

    //Channel for stargaze main
    private static ManagedChannel channel_stargaze_main = null;
    public static ManagedChannel getStargazeMain() {
        if (channel_stargaze_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_stargaze_main = ManagedChannelBuilder.forAddress(GRPC_STARGAZE_MAIN, PORT_STARGAZE_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_stargaze_main;
    }

    //Channel for ki main
    private static ManagedChannel channel_ki_main = null;
    public static ManagedChannel getKiMain() {
        if (channel_ki_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_ki_main = ManagedChannelBuilder.forAddress(GRPC_KI_MAIN, PORT_KI_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_ki_main;
    }

    //Channel for gravity bridge main
    private static ManagedChannel channel_grabridge_main = null;
    public static ManagedChannel getGraBridgeMain() {
        if (channel_grabridge_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_grabridge_main = ManagedChannelBuilder.forAddress(GRPC_GRABRIDGE_MAIN, PORT_GRABRIDGE_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_grabridge_main;
    }

    //Channel for comdex main
    private static ManagedChannel channel_comdex_main = null;
    public static ManagedChannel getComdexMain() {
        if (channel_comdex_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_comdex_main = ManagedChannelBuilder.forAddress(GRPC_COMDEX_MAIN, PORT_COMDEX_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_comdex_main;
    }

    //Channel for secret main
    private static ManagedChannel channel_secret_main = null;
    public static ManagedChannel getSecretMain() {
        if (channel_secret_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_secret_main = ManagedChannelBuilder.forAddress(GRPC_SECRET_MAIN, PORT_SECRET_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_secret_main;
    }

    //Channel for injective main
    private static ManagedChannel channel_inj_main = null;
    public static ManagedChannel getInjMain() {
        if (channel_inj_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_inj_main = ManagedChannelBuilder.forAddress(GRPC_INJ_MAIN, PORT_INJ_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_inj_main;
    }

    //Channel for bitsong main
    private static ManagedChannel channel_bitsong_main = null;
    public static ManagedChannel getBitsongMain() {
        if (channel_bitsong_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_bitsong_main = ManagedChannelBuilder.forAddress(GRPC_BITSONG_MAIN, PORT_BITSONG_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_bitsong_main;
    }

    //Channel for desmos main
    private static ManagedChannel channel_desmos_main = null;
    public static ManagedChannel getDesmosMain() {
        if (channel_desmos_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_desmos_main = ManagedChannelBuilder.forAddress(GRPC_DESMOS_MAIN, PORT_DESMOS_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_desmos_main;
    }

    //Channel for lum main
    private static ManagedChannel channel_lum_main = null;
    public static ManagedChannel getLumMain() {
        if (channel_lum_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_lum_main = ManagedChannelBuilder.forAddress(GRPC_LUM_MAIN, PORT_LUM_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_lum_main;
    }

    //Channel for chihuahua main
    private static ManagedChannel channel_chihuahua_main = null;
    public static ManagedChannel getChihuahuaMain() {
        if (channel_chihuahua_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_chihuahua_main = ManagedChannelBuilder.forAddress(GRPC_CHIHUAHUA_MAIN, PORT_CHIHUAHUA_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_chihuahua_main;
    }

    //Channel for kava main
    private static ManagedChannel channel_kava_main = null;
    public static ManagedChannel getKavaMain() {
        if (channel_kava_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_kava_main = ManagedChannelBuilder.forAddress(GRPC_KAVA_MAIN, PORT_KAVA_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_kava_main;
    }

    //Channel for axelar main
    private static ManagedChannel channel_axelar_main = null;
    public static ManagedChannel getAxelarMain() {
        if (channel_axelar_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_axelar_main = ManagedChannelBuilder.forAddress(GRPC_AXELAR_MAIN, PORT_AXELAR_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_axelar_main;
    }

    //Channel for konstellation main
    private static ManagedChannel channel_konstell_main = null;
    public static ManagedChannel getKonstellMain() {
        if (channel_konstell_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_konstell_main = ManagedChannelBuilder.forAddress(GRPC_KONSTELL_MAIN, PORT_KONSTELL_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_konstell_main;
    }

    //Channel for umee main
    private static ManagedChannel channel_umee_main = null;
    public static ManagedChannel getUmeeMain() {
        if (channel_umee_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_umee_main = ManagedChannelBuilder.forAddress(GRPC_UMEE_MAIN, PORT_UMEE_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_umee_main;
    }

    //Channel for evmos main
    private static ManagedChannel channel_evmos_main = null;
    public static ManagedChannel getEvmosMain() {
        if (channel_evmos_main == null) {
            synchronized (ChannelBuilder.class) {
                channel_evmos_main = ManagedChannelBuilder.forAddress(GRPC_EVMOS_MAIN, PORT_EVMOS_MAIN)
                        .usePlaintext()
                        .build();
            }
        }
        return channel_evmos_main;
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
}

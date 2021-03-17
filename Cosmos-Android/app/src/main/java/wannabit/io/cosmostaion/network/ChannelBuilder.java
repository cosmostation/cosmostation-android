package wannabit.io.cosmostaion.network;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;

public class ChannelBuilder {
    private final static String GRPC_COSMOS_MAIN = "lcd-cosmos-app.cosmostation.io";
    private final static int PORT_COSMOS_MAIN = 9090;

    private final static String GRPC_IRIS_MAIN = "lcd-iris-app.cosmostation.io";
    private final static int PORT_IRIS_MAIN = 9090;

    private final static String GRPC_AKASH_MAIN = "lcd-akash-app.cosmostation.io";
    private final static int PORT_AKASH_MAIN = 9090;

    private final static String GRPC_COSMOS_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_COSMOS_TEST = 9090;

    private final static String GRPC_IRIS_TEST = "lcd-office.cosmostation.io";
    private final static int PORT_IRIS_TEST = 9095;


    public static ManagedChannel getChain(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            return getCosmosMain();
        } else if (chain.equals(IRIS_MAIN)) {
            return getIrisMain();
        }else if (chain.equals(AKASH_MAIN)) {
            return getAkashMain();
        }

        else if (chain.equals(COSMOS_TEST)) {
            return getCosmosTest();
        } else if (chain.equals(IRIS_TEST)) {
            return getIrisTest();
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
}

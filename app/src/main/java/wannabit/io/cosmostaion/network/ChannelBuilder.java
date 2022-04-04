package wannabit.io.cosmostaion.network;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.HashMap;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import wannabit.io.cosmostaion.base.BaseChain;

public class ChannelBuilder {

    public final static int TIME_OUT = 8;
    private final static HashMap<String, ManagedChannel> channelHashMap = new HashMap<>();

    @Nullable
    public static ManagedChannel getChain(BaseChain chain) {
        ManagedChannel result;
        result = channelHashMap.get(chain.getChain());
        if (result == null) {
            synchronized (ChannelBuilder.class) {
                final String grpcApiUrl = chain.getGrpcApiUrl();
                final int grpcApiPort = chain.getGrpcApiPort();
                if (!TextUtils.isEmpty(grpcApiUrl)) {
                    result = ManagedChannelBuilder.forAddress(grpcApiUrl, grpcApiPort)
                            .usePlaintext()
                            .build();
                    channelHashMap.put(chain.getChain(), result);
                }
            }
        }
        return result;
    }
}

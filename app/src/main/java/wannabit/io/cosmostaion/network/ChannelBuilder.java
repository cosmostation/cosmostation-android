package wannabit.io.cosmostaion.network;

import com.google.common.collect.Maps;

import java.util.Map;

import io.grpc.ManagedChannel;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;

public class ChannelBuilder {
    public final static int TIME_OUT = 8;

    static Map<String, ManagedChannel> channelMap = Maps.newHashMap();

    public static ManagedChannel getChain(BaseChain baseChain) {
        if (channelMap.containsKey(baseChain.getChain())) {
            return channelMap.get(baseChain.getChain());
        } else {
            ChainConfig chainConfig = ChainFactory.getChain(baseChain);
            ManagedChannel channel = chainConfig.channelMain();
            channelMap.put(baseChain.getChain(), channel);
            return channel;
        }
    }
}

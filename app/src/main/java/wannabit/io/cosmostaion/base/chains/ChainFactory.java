package wannabit.io.cosmostaion.base.chains;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseChain;

public class ChainFactory {

    public static ChainConfig getChain(BaseChain baseChain) {
        if (baseChain != null) {
            switch (baseChain) {
                case COSMOS_LEGACY1:
                case COSMOS_LEGACY2:
                case COSMOS_LEGACY3:
                case COSMOS_LEGACY4:
                case COSMOS_MAIN:
                    return new Cosmos();

                case IRIS_LEGACY1:
                case IRIS_LEGACY2:
                case IRIS_MAIN:
                    return new Iris();

                case JUNO_MAIN:
                    return new Juno();
            }
        }
//        return null;
        return new Cosmos();
    }

    public static ArrayList<ChainConfig> SUPPRT_CONFIG() {
        ArrayList<ChainConfig> result = new ArrayList<>();
        for (BaseChain baseChain : BaseChain.SUPPORT_CHAINS()) {
            ChainConfig chainConfig = getChain(baseChain);
            if (chainConfig != null) {
                result.add(chainConfig);
            }
        }
        return result;
    }
}

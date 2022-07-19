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

                case CRESCENT_MAIN:
                    return new Crescent();

                case INJ_MAIN:
                    return new Injective();

                case JUNO_MAIN:
                    return new Juno();

                case KAVA_LEGACY1:
                case KAVA_LEGACY2:
                case KAVA_LEGACY3:
                case KAVA_LEGACY4:
                case KAVA_LEGACY5:
                case KAVA_LEGACY6:
                case KAVA_MAIN:
                    return new Kava();

                case OSMOSIS_MAIN:
                    return new Osmosis();
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

package wannabit.io.cosmostaion.base;

import static wannabit.io.cosmostaion.base.BaseConstant.IS_TEST;

public enum BaseChain {
    COSMOS_LEGACY1("cosmoshub-1"),
    COSMOS_LEGACY2("cosmoshub-2"),
    COSMOS_MAIN("cosmoshub-3"),
    IRIS_MAIN("irishub"),
//    BNB_MAIN("Binance-Chain-Tigris");
    BNB_MAIN("Binance-Chain-Nile");

    private final String chainName;

    BaseChain(final String chainname) {
        this.chainName = chainname;
    }

    public String getChain() {
        return chainName;
    }

    public static BaseChain getChain(String chainName) {
        if (chainName.equals(BaseChain.COSMOS_LEGACY1.chainName) ||
                chainName.equals(BaseChain.COSMOS_LEGACY2.chainName) ||
                chainName.equals(BaseChain.COSMOS_MAIN.chainName)) {
            return COSMOS_MAIN;
        }
        if (chainName.equals(BaseChain.IRIS_MAIN.chainName)) {
            return IRIS_MAIN;
        }
        if (chainName.equals(BaseChain.BNB_MAIN.chainName)) {
            return BNB_MAIN;
        }
        return COSMOS_MAIN;
    }

    public static String getDpChain(String chain) {
        if (chain.equals(COSMOS_MAIN.getChain()) ||
                chain.equals(COSMOS_LEGACY1.getChain()) ||
                chain.equals(COSMOS_MAIN.getChain())) {
            if(IS_TEST) {
                return "gaia-13006";
            } else {
                return COSMOS_MAIN.getChain();
            }

        }
        if (chain.equals(IRIS_MAIN.getChain())) {
            return IRIS_MAIN.getChain();
        }
        if (chain.equals(BNB_MAIN.getChain())) {
            return BNB_MAIN.getChain();
        }
        return COSMOS_MAIN.getChain();
    }
}

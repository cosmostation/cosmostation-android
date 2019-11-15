package wannabit.io.cosmostaion.base;

import static wannabit.io.cosmostaion.base.BaseConstant.IS_TEST;

public enum BaseChain {
    COSMOS_LEGACY1("cosmoshub-1"),
    COSMOS_LEGACY2("cosmoshub-2"),
    COSMOS_LEGACY3("cosmoshub-3"),
    COSMOS_MAIN("cosmoshub-2"),
    IRIS_MAIN("irishub"),
    IOV_MAIN("iov-mainnet"),
    BNB_MAIN("Binance-Chain-Tigris"),
    KAVA_LEGACY1("kava-1"),
    KAVA_MAIN("kava-2");

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
                chainName.equals(BaseChain.COSMOS_LEGACY3.chainName) ||
                chainName.equals(BaseChain.COSMOS_MAIN.chainName)) {
            return COSMOS_MAIN;
        }
        if (chainName.equals(BaseChain.IRIS_MAIN.chainName)) {
            return IRIS_MAIN;
        }
        if (chainName.equals(BaseChain.BNB_MAIN.chainName)) {
            return BNB_MAIN;
        }
        if (chainName.equals(BaseChain.IOV_MAIN.chainName)) {
            return IOV_MAIN;
        }
        if (chainName.equals(BaseChain.KAVA_LEGACY1.chainName) ||
                chainName.equals(BaseChain.KAVA_MAIN.chainName)) {
            return KAVA_MAIN;
        }
        return COSMOS_MAIN;
    }

    public static String getDpChain(String chain) {
        if (chain.equals(COSMOS_MAIN.getChain()) ||
                chain.equals(COSMOS_LEGACY1.getChain()) ||
                chain.equals(COSMOS_LEGACY2.getChain()) ||
                chain.equals(COSMOS_LEGACY3.getChain())) {
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
        if (chain.equals(IOV_MAIN.getChain())) {
            return IOV_MAIN.getChain();
        }
        if (chain.equals(KAVA_LEGACY1.getChain()) ||
                chain.equals(KAVA_MAIN.getChain())) {
            return KAVA_MAIN.getChain();
        }
        return COSMOS_MAIN.getChain();
    }
}

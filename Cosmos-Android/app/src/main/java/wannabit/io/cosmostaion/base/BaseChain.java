package wannabit.io.cosmostaion.base;

public enum BaseChain {
//    WANNABIT_NET("wannabit"),
//    GAME_OF_STAKE("game_of_stake_3"),
//    GAIA_12K("gaia-12001"),
//    GAIA_13K("gaia-13001"),
//    COSMOS_MAIN("gaia-13003"),
    COSMOS_MAIN("cosmoshub-2"),
    IRIS_MAIN("irishub");

    private final String chainName;

    BaseChain(final String chainname) {
        this.chainName = chainname;
    }

    public String getChain() {
        return chainName;
    }


    public static BaseChain getChain(String chainName) {
        if (chainName.equals(BaseChain.COSMOS_MAIN.chainName)) {
            return COSMOS_MAIN;
        }if (chainName.equals(BaseChain.IRIS_MAIN.chainName)) {
            return IRIS_MAIN;
        }
        return COSMOS_MAIN;
    }
}

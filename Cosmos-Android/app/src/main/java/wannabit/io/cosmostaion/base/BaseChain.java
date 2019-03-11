package wannabit.io.cosmostaion.base;

public enum BaseChain {
    WANNABIT_NET("wannabit"),
    GAME_OF_STAKE("game_of_stake_3"),
    GAIA_12K("gaia-12001"),
    GAIA_13K("gaia-13001");

    private final String chainName;

    BaseChain(final String chainname) {
        this.chainName = chainname;
    }

    public String getChain() {
        return chainName;
    }

//    public static BaseChain get(String name) {
//        return BaseChain.get(name);
//    }

    public static BaseChain getChain(String chainName) {
        if (chainName.equals(BaseChain.GAIA_12K.chainName)) {
            return GAIA_12K;
        } else if (chainName.equals(BaseChain.GAIA_13K.chainName)) {
            return GAIA_13K;
        }
        return GAIA_12K;
    }
}

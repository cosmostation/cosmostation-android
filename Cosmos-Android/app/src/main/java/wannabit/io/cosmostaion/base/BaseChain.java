package wannabit.io.cosmostaion.base;

public enum BaseChain {
    WANNABIT_NET("wannabit"),
    GAME_OF_STAKE("game_of_stake_3"),
    GAIA_12K("gaia-1200");

    private final String chainName;

    private BaseChain(final String chainname) {
        this.chainName = chainname;
    }

    public String getChain() {
        return chainName;
    }

}

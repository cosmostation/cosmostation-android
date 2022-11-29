package wannabit.io.cosmostaion.dao;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.chains.ChainConfig;

public class ChainAccounts {
    public boolean opened = false;
    public ChainConfig chainConfig;
    public ArrayList<Account> accounts;

    public ChainAccounts(boolean opened, ChainConfig baseChain, ArrayList<Account> accounts) {
        this.opened = opened;
        this.chainConfig = baseChain;
        this.accounts = accounts;
    }
}

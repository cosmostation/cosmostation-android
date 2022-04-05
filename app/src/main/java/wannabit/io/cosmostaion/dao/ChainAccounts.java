package wannabit.io.cosmostaion.dao;

import java.util.List;

import wannabit.io.cosmostaion.base.BaseChain;

public class ChainAccounts {
    public boolean opened = false;
    public BaseChain baseChain;
    public List<Account> accounts;

    public ChainAccounts(boolean opened, BaseChain baseChain, List<Account> accounts) {
        this.opened = opened;
        this.baseChain = baseChain;
        this.accounts = accounts;
    }
}

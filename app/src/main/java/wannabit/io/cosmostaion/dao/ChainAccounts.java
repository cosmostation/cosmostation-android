package wannabit.io.cosmostaion.dao;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseChain;

public class ChainAccounts {
    public boolean opened = false;
    public BaseChain baseChain;
    public ArrayList<Account> accounts;

    public ChainAccounts(boolean opened, BaseChain baseChain, ArrayList<Account> accounts) {
        this.opened = opened;
        this.baseChain = baseChain;
        this.accounts = accounts;
    }
}

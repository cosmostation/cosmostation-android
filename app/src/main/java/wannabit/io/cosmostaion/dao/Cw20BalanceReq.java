package wannabit.io.cosmostaion.dao;

public class Cw20BalanceReq {
    public BalanceReq balance;

    public Cw20BalanceReq(String address) {
        this.balance = new BalanceReq(address);
    }

    public static class BalanceReq {
        public String address;

        public BalanceReq(String address) {
            this.address = address;
        }
    }
}

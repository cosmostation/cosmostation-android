package wannabit.io.cosmostaion.dao;

public class Cw20IbcTransferReq {
    public Send send;

    public Cw20IbcTransferReq(String contract, String amount, String msg) {
        this.send = new Send(contract, amount, msg);
    }

    public class Send{
        public String contract;
        public String amount;
        public String msg;

        public Send(String contract, String amount, String msg) {
            this.contract = contract;
            this.amount = amount;
            this.msg = msg;
        }
    }

}

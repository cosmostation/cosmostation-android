package wannabit.io.cosmostaion.dao;

public class Cw20TransferReq {
    public TransferReq transfer;

    public Cw20TransferReq(String recipient, String amount) {
        this.transfer = new TransferReq(recipient, amount);
    }

    public class TransferReq {
        public String recipient;
        public String amount;

        public TransferReq(String recipient, String amount) {
            this.recipient = recipient;
            this.amount = amount;
        }
    }
}

package wannabit.io.cosmostaion.dao;

public class ICNSAddressInfoReq {
    public PrimaryName primary_name;

    public ICNSAddressInfoReq(String address) {
        this.primary_name = new PrimaryName(address);
    }

    public class PrimaryName {
        public String address;

        public PrimaryName(String address) {
            this.address= address;
        }
    }
}

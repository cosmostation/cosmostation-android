package wannabit.io.cosmostaion.dao;

public class ICNSInfoReq {
    public AddressByIcns address_by_icns;

    public ICNSInfoReq(String icns) {
        this.address_by_icns = new AddressByIcns(icns);
    }

    public class AddressByIcns {
        public String icns;

        public AddressByIcns(String icns) {
            this.icns= icns;
        }
    }
}

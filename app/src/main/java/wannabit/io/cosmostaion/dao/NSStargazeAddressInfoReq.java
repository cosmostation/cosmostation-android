package wannabit.io.cosmostaion.dao;

public class NSStargazeAddressInfoReq {
    public Name name;

    public NSStargazeAddressInfoReq(String address) {
        this.name = new Name(address);
    }

    public class Name {
        public String address;

        public Name(String address) {
            this.address= address;
        }
    }
}

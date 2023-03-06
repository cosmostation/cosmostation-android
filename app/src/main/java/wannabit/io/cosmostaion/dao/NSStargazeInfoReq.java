package wannabit.io.cosmostaion.dao;

public class NSStargazeInfoReq {
    public AssociatedAddress associated_address;

    public NSStargazeInfoReq(String name) {
        this.associated_address = new AssociatedAddress(name);
    }

    public class AssociatedAddress {
        public String name;

        public AssociatedAddress(String name) {
            this.name= name;
        }
    }
}

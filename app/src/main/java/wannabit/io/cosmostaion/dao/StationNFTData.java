package wannabit.io.cosmostaion.dao;

public class StationNFTData {
    public String issuerAddr;
    public String name;
    public String description;
    public String denom_id;
    public String imgurl;

    public StationNFTData(String issuerAddr, String name, String description, String denom_id, String imgurl) {
        this.issuerAddr = issuerAddr;
        this.name = name;
        this.description = description;
        this.denom_id = denom_id;
        this.imgurl = imgurl;
    }
}

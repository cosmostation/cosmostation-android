package wannabit.io.cosmostaion.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeeInfo {
    public String title = "";
    public String msg = "";
    public ArrayList<FeeData> feeDatas = new ArrayList<>();

    public FeeInfo(String data) {
        if (data == null) return;
        for (String rawData : data.split(",")) {
            feeDatas.add(new FeeData(rawData));
        }
    }

    public class FeeData {
        public String denom;
        public BigDecimal gasRate;

        public FeeData(String data) {
            Pattern p = Pattern.compile("([0-9])*\\.?[0-9]*");
            Matcher m = p.matcher(data);
            if (m.find()) {
                String rawGasRate = m.group();
                String denom = data.substring(m.end());
                this.denom = denom;
                this.gasRate = new BigDecimal(rawGasRate);
            }
        }
    }
}

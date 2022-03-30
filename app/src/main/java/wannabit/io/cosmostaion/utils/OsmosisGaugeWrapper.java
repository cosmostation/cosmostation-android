package wannabit.io.cosmostaion.utils;

import java.io.Serializable;
import java.util.ArrayList;

import osmosis.incentives.GaugeOuterClass;

public class OsmosisGaugeWrapper implements Serializable {
    public ArrayList<GaugeOuterClass.Gauge> array;

    public OsmosisGaugeWrapper(ArrayList<GaugeOuterClass.Gauge> a) {
        array = a;
    }
}

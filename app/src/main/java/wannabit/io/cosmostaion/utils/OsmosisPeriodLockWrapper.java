package wannabit.io.cosmostaion.utils;

import java.io.Serializable;
import java.util.ArrayList;

import osmosis.lockup.Lock;

public class OsmosisPeriodLockWrapper implements Serializable {
    public ArrayList<Lock.PeriodLock> array;

    public OsmosisPeriodLockWrapper(ArrayList<Lock.PeriodLock> a){
        array = a;
    }
}

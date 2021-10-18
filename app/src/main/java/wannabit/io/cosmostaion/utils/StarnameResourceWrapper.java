package wannabit.io.cosmostaion.utils;

import java.io.Serializable;
import java.util.ArrayList;

import starnamed.x.starname.v1beta1.Types;

public class StarnameResourceWrapper implements Serializable {
    public ArrayList<Types.Resource> array;

    public StarnameResourceWrapper(ArrayList<Types.Resource> a){
        array = a;
    }
}

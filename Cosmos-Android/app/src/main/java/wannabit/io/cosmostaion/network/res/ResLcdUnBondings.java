package wannabit.io.cosmostaion.network.res;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Result;

public class ResLcdUnBondings {

    @SerializedName("delegator_addr")
    public String delegator_addr;

    @SerializedName("validator_addr")
    public String validator_addr;

    @SerializedName("entries")
    public ArrayList<Entry> entries;

    public class Entry {
        @SerializedName("creation_height")
        public String creation_height;

        @SerializedName("completion_time")
        public String completion_time;

//        @SerializedName("initial_balance")
//        public Coin initial_balance;
//
//        @SerializedName("balance")
//        public Coin balance;



        @SerializedName("initial_balance")
        @Expose
        private Object initial_balance;

        @SerializedName("balance")
        @Expose
        private Object balance;

        public String getinitial_balance() {
            String result = "";
            try {
                Coin temp = new Gson().fromJson(new Gson().toJson(initial_balance), Coin.class);
                result = temp.amount;

            } catch (Exception e) {

            }
            try {
                String temp = new Gson().fromJson(new Gson().toJson(initial_balance), String.class);
                result = temp;

            } catch (Exception e) {

            }
            return result;
        }

        public String getbalance() {
            String result = "";
            try {
                Coin temp = new Gson().fromJson(new Gson().toJson(balance), Coin.class);
                result = temp.amount;

            } catch (Exception e) {

            }
            try {
                String temp = new Gson().fromJson(new Gson().toJson(balance), String.class);
                result = temp;

            } catch (Exception e) {

            }
            return result;
        }
    }
}

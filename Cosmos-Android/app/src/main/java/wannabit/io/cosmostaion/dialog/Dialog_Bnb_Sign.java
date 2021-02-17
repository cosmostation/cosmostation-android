package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.WalletConnectActivity;
import wannabit.io.cosmostaion.model.type.BnbParam;

public class Dialog_Bnb_Sign extends DialogFragment {

    public static Dialog_Bnb_Sign newInstance(Bundle bundle) {
        Dialog_Bnb_Sign frag = new Dialog_Bnb_Sign();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_bnb_sign, null);
        TextView tv_request = view.findViewById(R.id.tv_requset_msg);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);


        try {
            BnbParam bnbParam = new Gson().fromJson(getArguments().getString("param").replace("memo=, " ,""), BnbParam.class);
            String showMsg = "";
            if (!TextUtils.isEmpty(bnbParam.msgs.get(0).refid)) {
                showMsg = getString(R.string.str_wc_cancel_order) + "\n" +
                        "Symbol : " + bnbParam.msgs.get(0).symbol + "\n";

            } else if (!TextUtils.isEmpty(bnbParam.msgs.get(0).id)) {
                BigDecimal price = new BigDecimal(bnbParam.msgs.get(0).price).movePointLeft(8).setScale(8, RoundingMode.DOWN);
                BigDecimal quantity = new BigDecimal(bnbParam.msgs.get(0).quantity).movePointLeft(8).setScale(6, RoundingMode.DOWN);
                showMsg = "Symbol : " + bnbParam.msgs.get(0).symbol + "\n" +
                        "Price : " + price.toPlainString() + " \n" +
                        "quantity : " + quantity.toPlainString();

            }
            tv_request.setText(showMsg);

        } catch (Exception e) {
            String param = getArguments().getString("param");
            tv_request.setText(param);

        }

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WalletConnectActivity)getActivity()).onBnbSign(getArguments().getLong("id"));
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
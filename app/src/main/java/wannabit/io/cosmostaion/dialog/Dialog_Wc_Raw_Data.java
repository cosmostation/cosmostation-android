package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ConnectWalletActivity;

public class Dialog_Wc_Raw_Data extends DialogFragment {

    public static Dialog_Wc_Raw_Data newInstance(Bundle bundle) {
        Dialog_Wc_Raw_Data frag = new Dialog_Wc_Raw_Data();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wc_raw_data, null);
        TextView raw_data = view.findViewById(R.id.wc_raw_data);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);

        String transaction = getArguments().getString("transaction");
        Long id = getArguments().getLong("id");
        int type = getArguments().getInt("type");
        raw_data.setText(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(transaction)));

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == ConnectWalletActivity.TYPE_TRUST_WALLET) {
                    ((ConnectWalletActivity)getActivity()).approveTrustRequest(id, transaction);
                } else if (type == ConnectWalletActivity.TYPE_KEPLR_WALLET) {
                    ((ConnectWalletActivity)getActivity()).approveKeplrRequest(id);
                }
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}

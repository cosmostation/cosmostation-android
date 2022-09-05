package wannabit.io.cosmostaion.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.gson.JsonArray;
import com.trustwallet.walletconnect.models.ethereum.WCEthereumSignMessage;
import com.trustwallet.walletconnect.models.ethereum.WCEthereumTransaction;

import wannabit.io.cosmostaion.R;

public class Dialog_Wc_Raw_Data_Evmos extends DialogFragment {
    public WcEvmosSignRawDataListener listener = null;
    private LinearLayout wcRawDataLayout;
    private TextView chainNameTv, chainUrlTv, wcRawDataTv, addressDetailTv;
    private Button btnNegative, btnPositive;
    private String transaction, txAddress, message;

    public static Dialog_Wc_Raw_Data_Evmos newInstance(Bundle bundle, WcEvmosSignRawDataListener listener) {
        Dialog_Wc_Raw_Data_Evmos dialog = new Dialog_Wc_Raw_Data_Evmos();
        dialog.setArguments(bundle);
        dialog.listener = listener;
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = settingViews();

        assert getArguments() != null;
        transaction = getArguments().getString("transaction");
        txAddress = getArguments().getString("address");
        message = getArguments().getString("message");
        Long id = getArguments().getLong("id");
        int type = getArguments().getInt("type");
        String url = getArguments().getString("url");

        chainUrlTv.setText(url);

        try {
            fillTxData(transaction, txAddress);
        } catch (Exception e) {
            defaultTxView(transaction);
        }

        btnNegative.setOnClickListener(v -> {
            if (listener != null) {
                listener.reject(id);
            }
            getDialog().dismiss();
        });

        btnPositive.setOnClickListener(v -> {
            if (listener != null) {
                listener.sign(type, id, transaction, message);
            }
            getDialog().dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    @NonNull
    private View settingViews() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wc_raw_data_evmos, null);
        wcRawDataLayout = view.findViewById(R.id.layout_wc_raw_data);
        chainNameTv = view.findViewById(R.id.chain_name);
        chainUrlTv = view.findViewById(R.id.chain_url);
        wcRawDataTv = view.findViewById(R.id.wc_raw_data);
        addressDetailTv = view.findViewById(R.id.address_detail);
        btnNegative = view.findViewById(R.id.btn_nega);
        btnPositive = view.findViewById(R.id.btn_posi);
        return view;
    }

    private void defaultTxView(String transaction) {
        chainNameTv.setText(getString(R.string.str_wc_sign_title));
        wcRawDataTv.setText(transaction);
    }

    private void fillTxData(String transaction, String txAddress) {
        chainNameTv.setText(getString(R.string.str_wc_sign_title));
        wcRawDataTv.setText(transaction);
        addressDetailTv.setText(txAddress);
    }

    public interface WcEvmosSignRawDataListener {
        void sign(int type, Long id, String transaction, String message);

        void reject(Long id);
    }

}

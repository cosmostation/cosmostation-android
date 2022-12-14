package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.apache.commons.lang3.StringUtils;

import wannabit.io.cosmostaion.R;

public class Dialog_Wc_Raw_Data_Evmos extends DialogFragment {
    public WcEvmosSignRawDataListener listener = null;
    private TextView chainNameTv, chainUrlTv, wcRawDataTv, addressDetailTv;
    private Button btnNegative, btnPositive;
    private String data, address;

    public static Dialog_Wc_Raw_Data_Evmos newInstance(Bundle bundle, WcEvmosSignRawDataListener listener) {
        Dialog_Wc_Raw_Data_Evmos dialog = new Dialog_Wc_Raw_Data_Evmos();
        dialog.setArguments(bundle);
        dialog.listener = listener;
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        View view = settingViews();

        assert getArguments() != null;
        data = getArguments().getString("data");
        address = getArguments().getString("address");
        Long id = getArguments().getLong("id");
        String url = getArguments().getString("url");

        chainUrlTv.setText(url);
        fillTxData();

        btnNegative.setOnClickListener(v -> {
            if (listener != null) {
                listener.reject(id);
            }
            dismiss();
        });

        btnPositive.setOnClickListener(v -> {
            if (listener != null) {
                listener.sign(id);
            }
            dismiss();
        });

        return view;
    }

    @NonNull
    private View settingViews() {
        View view = getLayoutInflater().inflate(R.layout.dialog_wc_raw_data_evmos, null);
        chainNameTv = view.findViewById(R.id.chain_name);
        chainUrlTv = view.findViewById(R.id.chain_url);
        wcRawDataTv = view.findViewById(R.id.wc_raw_data);
        addressDetailTv = view.findViewById(R.id.address_detail);
        btnNegative = view.findViewById(R.id.btn_nega);
        btnPositive = view.findViewById(R.id.btn_posi);
        return view;
    }

    private void fillTxData() {
        chainNameTv.setText(getString(R.string.str_wc_sign_title));
        wcRawDataTv.setText(data);
        if (StringUtils.isEmpty(address)) {
            addressDetailTv.setText(address);
        }
    }

    public interface WcEvmosSignRawDataListener {
        void sign(Long id);

        void reject(Long id);
    }
}

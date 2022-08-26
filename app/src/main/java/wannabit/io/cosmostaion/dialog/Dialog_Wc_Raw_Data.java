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

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import wannabit.io.cosmostaion.R;

public class Dialog_Wc_Raw_Data extends DialogFragment {
    public WcSignRawDataListener listener = null;

    public static Dialog_Wc_Raw_Data newInstance(Bundle bundle, WcSignRawDataListener listener) {
        Dialog_Wc_Raw_Data dialog = new Dialog_Wc_Raw_Data();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wc_raw_data, null);
        LinearLayout wcRawDetailLayout = view.findViewById(R.id.layout_wc_detail);
        LinearLayout wcRawDataLayout = view.findViewById(R.id.layout_wc_raw_data);
        TextView chainHomepage = view.findViewById(R.id.chain_homepage);
        TextView wcDetail = view.findViewById(R.id.wc_detail);
        TextView wcRawData = view.findViewById(R.id.wc_raw_data);
        TextView addressDetail = view.findViewById(R.id.address_detail);
        TextView memoDetail = view.findViewById(R.id.memo_detail);
        TextView totalFeeAmount = view.findViewById(R.id.total_fee_amount);
        Button btnDetail = view.findViewById(R.id.btn_detail);
        Button btnData = view.findViewById(R.id.btn_data);
        Button btnNegative = view.findViewById(R.id.btn_nega);
        Button btnPositive = view.findViewById(R.id.btn_posi);

        String transaction = getArguments().getString("transaction");
        Long id = getArguments().getLong("id");
        int type = getArguments().getInt("type");

        chainHomepage.setText("https://osmosis.com");
        addressDetail.setText("addressDetail");
        memoDetail.setText("memoDetail");
        totalFeeAmount.setText("totalFeeAmount");

        wcDetail.setText("TEST");
        btnDetail.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPhotonDayNight));
        btnDetail.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.box_sign_selected));

        btnDetail.setOnClickListener(v -> {
            wcRawDataLayout.setVisibility(View.GONE);
            wcRawDetailLayout.setVisibility(View.VISIBLE);
            btnDetail.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.box_sign_selected));
            btnData.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.box_sign_unselected));
            btnDetail.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPhotonDayNight));
            btnData.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorGrayDayNight));
            wcDetail.setText("TEST");
        });

        btnData.setOnClickListener(v -> {
            wcRawDataLayout.setVisibility(View.VISIBLE);
            wcRawDetailLayout.setVisibility(View.GONE);
            btnData.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.box_sign_selected));
            btnDetail.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.box_sign_unselected));
            btnData.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPhotonDayNight));
            btnDetail.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorGrayDayNight));
            wcRawData.setText(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(transaction)));
        });

        btnNegative.setOnClickListener(v -> {
            if (listener != null) {
                listener.reject(id);
            }
            getDialog().dismiss();
        });

        btnPositive.setOnClickListener(v -> {
            if (listener != null) {
                listener.sign(type, id, transaction);
            }
            getDialog().dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    public interface WcSignRawDataListener {
        void sign(int type, Long id, String transaction);

        void reject(Long id);
    }
}

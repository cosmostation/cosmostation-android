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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import wannabit.io.cosmostaion.R;

public class Dialog_Wc_Raw_Data extends DialogFragment {
    public WcSignRawDataListener listener = null;
    LinearLayout wcRawDetailLayout;
    LinearLayout wcRawDataLayout;
    LinearLayout buttonWrapLayout;
    LinearLayout infoWrapLayout;
    TextView chainName;
    TextView chainHomepage;
    TextView wcDetail;
    TextView wcRawData;
    TextView addressDetail;
    TextView memoDetail;
    TextView totalFeeAmount;
    Button btnDetail;
    Button btnData;
    Button btnNegative;
    Button btnPositive;

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
        View view = settingViews();

        assert getArguments() != null;
        String transaction = getArguments().getString("transaction");
        Long id = getArguments().getLong("id");
        int type = getArguments().getInt("type");
        String url = getArguments().getString("url");
        
        chainHomepage.setText(url);

        try {
            fillTxData(transaction);
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
                listener.sign(type, id, transaction);
            }
            getDialog().dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    @NonNull
    private View settingViews() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wc_raw_data, null);
        wcRawDetailLayout = view.findViewById(R.id.layout_wc_detail);
        wcRawDataLayout = view.findViewById(R.id.layout_wc_raw_data);
        buttonWrapLayout = view.findViewById(R.id.btn_wrap_layout);
        infoWrapLayout = view.findViewById(R.id.info_wrap_layout);
        chainName = view.findViewById(R.id.chain_name);
        chainHomepage = view.findViewById(R.id.chain_homepage);
        wcDetail = view.findViewById(R.id.wc_detail);
        wcRawData = view.findViewById(R.id.wc_raw_data);
        addressDetail = view.findViewById(R.id.address_detail);
        memoDetail = view.findViewById(R.id.memo_detail);
        totalFeeAmount = view.findViewById(R.id.total_fee_amount);
        btnDetail = view.findViewById(R.id.btn_detail);
        btnData = view.findViewById(R.id.btn_data);
        btnNegative = view.findViewById(R.id.btn_nega);
        btnPositive = view.findViewById(R.id.btn_posi);
        return view;
    }

    private void defaultTxView(String transaction) {
        chainName.setText(getString(R.string.str_wc_sign_title));
        buttonWrapLayout.setVisibility(View.GONE);
        infoWrapLayout.setVisibility(View.GONE);
        wcRawDataLayout.setVisibility(View.VISIBLE);
        wcRawDetailLayout.setVisibility(View.GONE);
        String prettierTxString = new GsonBuilder().setPrettyPrinting().create().toJson(JsonParser.parseString(transaction));
        wcRawData.setText(prettierTxString);
    }

    private void fillTxData(String transaction) {
        JsonArray txJsonArray = JsonParser.parseString(transaction).getAsJsonArray();
        String chainId = txJsonArray.get(0).getAsString();
        String address = txJsonArray.get(1).getAsString();
        JsonObject txJsonObject = txJsonArray.get(2).getAsJsonObject();
        JsonArray msgJsonArray = txJsonObject.getAsJsonArray("msgs");
        chainName.setText(StringUtils.isNotEmpty(chainId) ? chainId : getString(R.string.str_wc_sign_title));
        addressDetail.setText(address);
        memoDetail.setText(txJsonObject.get("memo").getAsString());
        totalFeeAmount.setText(makeFeeString(txJsonObject));

        wcRawData.setText(new GsonBuilder().setPrettyPrinting().create().toJson(txJsonObject));
        wcDetail.setText(new GsonBuilder().setPrettyPrinting().create().toJson(msgJsonArray));

        enableTab(0);

        btnDetail.setOnClickListener(v -> {
            enableTab(0);
        });

        btnData.setOnClickListener(v -> {
            enableTab(1);
        });
    }

    private String makeFeeString(JsonObject txJsonObject) {
        JsonObject feeJson = txJsonObject.getAsJsonObject("fee");
        JsonArray amountArray = feeJson.getAsJsonArray("amount");
        List<String> fees = Lists.newArrayList();
        for (JsonElement element : amountArray) {
            JsonObject elementObject = element.getAsJsonObject();
            String amount = elementObject.get("amount").getAsString();
            String denom = elementObject.get("denom").getAsString();
            fees.add(amount + " " + denom);
        }
        return String.join("\n", fees);
    }

    private void enableTab(int index) {
        settingTab(index == 0, btnDetail, wcRawDetailLayout);
        settingTab(index == 1, btnData, wcRawDataLayout);
    }

    private void settingTab(boolean enable, Button button, LinearLayout textLayout) {
        textLayout.setVisibility(enable ? View.VISIBLE : View.GONE);
        button.setBackground(ContextCompat.getDrawable(getActivity(), enable ? R.drawable.box_sign_selected : R.drawable.box_sign_unselected));
        button.setTextColor(ContextCompat.getColor(getActivity(), enable ? R.color.colorPhotonDayNight : R.color.colorGrayDayNight));
    }

    public interface WcSignRawDataListener {
        void sign(int type, Long id, String transaction);

        void reject(Long id);
    }
}

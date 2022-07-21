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
import androidx.fragment.app.DialogFragment;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class SafeScoreStatusDialog extends DialogFragment {

    public static SafeScoreStatusDialog newInstance(Bundle bundle) {
        SafeScoreStatusDialog frag = new SafeScoreStatusDialog();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_safe_score_status, null);
        LinearLayout risk_layer = view.findViewById(R.id.risk_layer);
        TextView risk_rate = view.findViewById(R.id.risk_rate);
        TextView risk_score = view.findViewById(R.id.risk_score);

        TextView current_price_title = view.findViewById(R.id.current_price_title);
        TextView current_price = view.findViewById(R.id.current_price);
        TextView liquidation_price_title = view.findViewById(R.id.liquidation_price_title);
        TextView liquidation_price = view.findViewById(R.id.liquidation_price);

        WUtil.DpRiskRate2(getContext(), new BigDecimal(getArguments().getString("riskRate")), risk_rate, risk_score, risk_layer);

        current_price_title.setText(String.format(getString(R.string.str_current_title3), getArguments().getString("denom").toUpperCase()));
        current_price.setText(WDp.getDpRawDollor(getContext(), new BigDecimal(getArguments().getString("currentPrice")), 4));

        liquidation_price_title.setText(String.format(getString(R.string.str_liquidation_title3), getArguments().getString("denom").toUpperCase()));
        liquidation_price.setText(WDp.getDpRawDollor(getContext(), new BigDecimal(getArguments().getString("liquidationPrice")), 4));

        Button btn_negative = view.findViewById(R.id.btn_nega);
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
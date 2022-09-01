package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class SafeScoreConfirmDialog extends DialogFragment {

    public static SafeScoreConfirmDialog newInstance(Bundle bundle) {
        SafeScoreConfirmDialog frag = new SafeScoreConfirmDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_safe_score_confirm, null);
        LinearLayout before_risk_layer = view.findViewById(R.id.before_risk_layer);
        TextView before_risk_rate = view.findViewById(R.id.before_risk_rate);
        TextView before_risk_score = view.findViewById(R.id.before_risk_score);
        LinearLayout after_risk_layer = view.findViewById(R.id.after_risk_layer);
        TextView after_risk_rate = view.findViewById(R.id.after_risk_rate);
        TextView after_risk_score = view.findViewById(R.id.after_risk_score);

        TextView current_price_title = view.findViewById(R.id.current_price_title);
        TextView current_price = view.findViewById(R.id.current_price);
        TextView before_liquidation_price_title = view.findViewById(R.id.before_liquidation_price_title);
        TextView before_liquidation_price = view.findViewById(R.id.before_liquidation_price);
        TextView after_liquidation_price_title = view.findViewById(R.id.after_liquidation_price_title);
        TextView after_liquidation_price = view.findViewById(R.id.after_liquidation_price);

        WUtil.DpRiskRate2(getContext(), new BigDecimal(getArguments().getString("beforeRiskRate")), before_risk_rate, before_risk_score, before_risk_layer);
        WUtil.DpRiskRate2(getContext(), new BigDecimal(getArguments().getString("afterRiskRate")), after_risk_rate, after_risk_score, after_risk_layer);

        current_price_title.setText(String.format(getString(R.string.str_current_title3), getArguments().getString("denom").toUpperCase()));
        current_price.setText(WDp.getDpRawDollor(getContext(), new BigDecimal(getArguments().getString("currentPrice")), 4));

        before_liquidation_price_title.setText(String.format(getString(R.string.str_before_liquidation_title2), getArguments().getString("denom").toUpperCase()));
        before_liquidation_price.setText(WDp.getDpRawDollor(getContext(), new BigDecimal(getArguments().getString("beforeLiquidationPrice")), 4));

        after_liquidation_price_title.setText(String.format(getString(R.string.str_after_liquidation_title2), getArguments().getString("denom").toUpperCase()));
        after_liquidation_price.setText(WDp.getDpRawDollor(getContext(), new BigDecimal(getArguments().getString("afterLiquidationPrice")), 4));


        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);
        btn_negative.setOnClickListener(v -> getDialog().dismiss());
        btn_positive.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
            getDialog().dismiss();
        });

        setCancelable(true);
        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        return dialog;
    }
}
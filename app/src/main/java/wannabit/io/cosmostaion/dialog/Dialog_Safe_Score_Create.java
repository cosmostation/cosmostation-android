package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WDp;

public class Dialog_Safe_Score_Create extends DialogFragment {

    public static Dialog_Safe_Score_Create newInstance(Bundle bundle) {
        Dialog_Safe_Score_Create frag = new Dialog_Safe_Score_Create();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return inflater.inflate(R.layout.dialog_safe_score_create, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout risk_layer = view.findViewById(R.id.risk_layer);
        TextView risk_rate = view.findViewById(R.id.risk_rate);
        TextView risk_score = view.findViewById(R.id.risk_score);

        TextView current_price_title = view.findViewById(R.id.current_price_title);
        TextView current_price = view.findViewById(R.id.current_price);
        TextView liquidation_price_title = view.findViewById(R.id.liquidation_price_title);
        TextView liquidation_price = view.findViewById(R.id.liquidation_price);

        WDp.DpRiskRate2(getContext(), new BigDecimal(getArguments().getString("riskRate")), risk_rate, risk_score, risk_layer);

        current_price_title.setText(String.format(getString(R.string.str_current_title3), getArguments().getString("denom").toUpperCase()));
        current_price.setText(WDp.getDpRawDollor(getContext(), new BigDecimal(getArguments().getString("currentPrice")).movePointLeft(18) , 4));

        liquidation_price_title.setText(String.format(getString(R.string.str_liquidation_title3), getArguments().getString("denom").toUpperCase()));
        liquidation_price.setText(WDp.getDpRawDollor(getContext(), new BigDecimal(getArguments().getString("liquidationPrice")) , 4));

        Button btn_negative = view.findViewById(R.id.btn_nega);
        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        Button btn_positive = view.findViewById(R.id.btn_posi);
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });
    }
}
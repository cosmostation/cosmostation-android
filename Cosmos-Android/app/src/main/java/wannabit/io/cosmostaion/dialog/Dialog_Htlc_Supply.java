package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.res.ResKavaSwapSupply;
import wannabit.io.cosmostaion.utils.WDp;

public class Dialog_Htlc_Supply extends DialogFragment {

    public static Dialog_Htlc_Supply newInstance(Bundle bundle) {
        Dialog_Htlc_Supply frag = new Dialog_Htlc_Supply();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_htlc_supply, null);
        Button btn_posi             = view.findViewById(R.id.btn_posi);
        TextView dialogTitle        = view.findViewById(R.id.bep3_title);
        TextView dialogMsg          = view.findViewById(R.id.bep3_msg);

        TextView system_max_amount  = view.findViewById(R.id.system_max_amount);
        TextView system_max_denom   = view.findViewById(R.id.system_max_denom);
        TextView current_cap_amount = view.findViewById(R.id.current_cap_amount);
        TextView current_cap_denom  = view.findViewById(R.id.current_cap_denom);
        TextView remain_cap_amount  = view.findViewById(R.id.remain_cap_amount);
        TextView remain_cap_denom   = view.findViewById(R.id.remain_cap_denom);

        ResKavaSwapSupply.KavaSwapSupply supply = getArguments().getParcelable("supply");
        WDp.showCoinDp(getContext(), supply.supply_limit, system_max_denom, system_max_amount, BaseChain.BNB_MAIN);
        WDp.showCoinDp(getContext(), supply.getCurrentCap(), current_cap_denom, current_cap_amount, BaseChain.BNB_MAIN);
        WDp.showCoinDp(getContext(), supply.getRemainCap(), remain_cap_denom, remain_cap_amount, BaseChain.BNB_MAIN);

        if (supply.getRemainAmount().compareTo(BigDecimal.ZERO) <= 0) {
            dialogTitle.setText(R.string.str_bep3_unavailable_title);
            dialogMsg.setText(R.string.str_bep3_bnb_cap_over);
        }  else {
            dialogTitle.setText(R.string.str_bep3_available_title);
            dialogMsg.setText(String.format(getString(R.string.str_bep3_bnb_cap), remain_cap_amount.getText().toString()));
        }

        btn_posi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
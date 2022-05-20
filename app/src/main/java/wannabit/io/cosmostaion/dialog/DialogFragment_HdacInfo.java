package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WDp;

public class DialogFragment_HdacInfo extends DialogFragment {

    public static DialogFragment_HdacInfo newInstance(Bundle bundle) {
        DialogFragment_HdacInfo frag = new DialogFragment_HdacInfo();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return inflater.inflate(R.layout.dialog_hdac_info, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView hdac_address = view.findViewById(R.id.hdac_address);
        TextView hdac_amount = view.findViewById(R.id.hdac_amount);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_positive = view.findViewById(R.id.btn_posi);

        String mHdacAddress = getArguments().getString("hdacAddress");
        String mHdacBalance = getArguments().getString("hdacBalance");
        hdac_address.setText(mHdacAddress);
        hdac_amount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(mHdacBalance), 8, 8));

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("hdac", 0);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("hdac", 1);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();

            }
        });
    }
}

package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.EarningDetailActivity;

public class Dialog_Osmo_Lockup_Duration extends BottomSheetDialogFragment {

    public static Dialog_Osmo_Lockup_Duration getInstance() {
        return new Dialog_Osmo_Lockup_Duration();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_osmo_lockup_duration, container, false);
        Button day1 = view.findViewById(R.id.btn_1day);
        Button day7 = view.findViewById(R.id.btn_7day);
        Button day14 = view.findViewById(R.id.btn_14day);

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EarningDetailActivity) getActivity()).onStartNewEarning(86400l);
                dismiss();
            }
        });

        day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EarningDetailActivity) getActivity()).onStartNewEarning(604800);
                dismiss();
            }
        });

        day14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EarningDetailActivity) getActivity()).onStartNewEarning(1209600l);
                dismiss();
            }
        });

        return view;
    }

}

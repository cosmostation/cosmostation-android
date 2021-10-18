package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.math.BigDecimal;
import java.util.ArrayList;

import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.EarningDetailActivity;
import wannabit.io.cosmostaion.utils.OsmosisPeriodLockWrapper;
import wannabit.io.cosmostaion.utils.WLog;

public class Dialog_Osmo_Unbonding_All extends BottomSheetDialogFragment {

    public static Dialog_Osmo_Unbonding_All getInstance(Bundle bundle) {
        Dialog_Osmo_Unbonding_All frag = new Dialog_Osmo_Unbonding_All();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_osmo_unbonding_all, container, false);
        Button btnAll      = view.findViewById(R.id.btn_all);
        Button btnOne      = view.findViewById(R.id.btn_one);
        TextView msgTv       = view.findViewById(R.id.lock_ids);

        OsmosisPeriodLockWrapper lockupWrapper = (OsmosisPeriodLockWrapper)getArguments().getSerializable("all");
        ArrayList<Lock.PeriodLock> lockups = lockupWrapper.array;

        String msg = "";
        for (Lock.PeriodLock lock: lockups) {
            msg = msg + "# " + lock.getID() + "  ";
        }
//        String amount = new BigDecimal(getArguments().getString("amount")).movePointLeft(18).toPlainString();
//        msg = msg + "\n" + amount;
        msgTv.setText(msg);

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OsmosisPeriodLockWrapper lockupWrapper = (OsmosisPeriodLockWrapper)getArguments().getSerializable("all");
                ArrayList<Lock.PeriodLock> lockups = lockupWrapper.array;
                ((EarningDetailActivity)getActivity()).onStartUnbonding(lockups);
                dismiss();
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Lock.PeriodLock lockup = Lock.PeriodLock.parseFrom(getArguments().getByteArray("single"));
                    ArrayList<Lock.PeriodLock> lockups = new ArrayList<>();
                    lockups.add(lockup);
                    ((EarningDetailActivity)getActivity()).onStartUnbonding(lockups);

                } catch (Exception e){
                    WLog.w("e " + e.getMessage());
                }
                dismiss();
            }
        });
        return view;
    }
}

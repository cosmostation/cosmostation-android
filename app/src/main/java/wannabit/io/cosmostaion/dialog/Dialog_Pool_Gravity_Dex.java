package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.cosmos.GravityListActivity;

public class Dialog_Pool_Gravity_Dex extends BottomSheetDialogFragment {

    private Button mJoinPool, mExitPool;

    public static Dialog_Pool_Gravity_Dex getInstance() {
        return new Dialog_Pool_Gravity_Dex();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_choice_pool, container, false);

        mJoinPool = view.findViewById(R.id.join_pool);
        mExitPool = view.findViewById(R.id.exit_pool);

        mJoinPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GravityListActivity)getActivity()).onCheckStartDepositPool(getArguments().getLong("poolId"));
                getDialog().dismiss();
            }
        });

        mExitPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GravityListActivity)getActivity()).onCheckStartWithdrawPool(getArguments().getLong("poolId"));
                getDialog().dismiss();
            }
        });

        return view;
    }

}

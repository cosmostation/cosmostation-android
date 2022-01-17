package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import kava.swap.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;

public class Dialog_Pool_Kava extends BottomSheetDialogFragment {

    private Button mJoinPool, mExitPool;

    public static Dialog_Pool_Kava getInstance() {
        return new Dialog_Pool_Kava();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_choice_pool, container, false);

        mJoinPool = view.findViewById(R.id.join_pool);
        mExitPool = view.findViewById(R.id.exit_pool);
        mJoinPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DAppsList5Activity)getActivity()).onCheckStartJoinPool((QueryOuterClass.PoolResponse) getArguments().getSerializable("mKavaPool"));
                getDialog().dismiss();
            }
        });

        mExitPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DAppsList5Activity)getActivity()).onCheckStartExitPool(getArguments().getParcelable("mKavaPool"), getArguments().getParcelable("mKavaDeposit"));
                getDialog().dismiss();
            }
        });

        return view;
    }

}

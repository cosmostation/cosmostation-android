package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;

public class Dialog_Pool_Osmosis extends BottomSheetDialogFragment {

    private Button mJoinPool, mExitPool;

    public static Dialog_Pool_Osmosis getInstance() {
        return new Dialog_Pool_Osmosis();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_choice_pool, container, false);

        mJoinPool = view.findViewById(R.id.join_pool);
        mExitPool = view.findViewById(R.id.exit_pool);

        mJoinPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LabsListActivity)getActivity()).onCheckStartJoinPool(getArguments().getLong("poolId"));
                getDialog().dismiss();
            }
        });

        mExitPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LabsListActivity)getActivity()).onCheckStartExitPool(getArguments().getLong("poolId"));
                getDialog().dismiss();
            }
        });

        return view;
    }

}

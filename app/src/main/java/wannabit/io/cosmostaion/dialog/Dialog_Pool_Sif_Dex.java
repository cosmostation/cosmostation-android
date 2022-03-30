package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import sifnode.clp.v1.Querier;
import sifnode.clp.v1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.sif.SifDexListActivity;

public class Dialog_Pool_Sif_Dex extends BottomSheetDialogFragment {

    private Button mJoinPool, mExitPool;

    public static Dialog_Pool_Sif_Dex getInstance() {
        return new Dialog_Pool_Sif_Dex();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_choice_pool, container, false);

        mJoinPool = view.findViewById(R.id.join_pool);
        mExitPool = view.findViewById(R.id.exit_pool);
        Types.Pool pool = (Types.Pool) getArguments().getSerializable("pool");
        Querier.LiquidityProviderRes myProvider = (Querier.LiquidityProviderRes) getArguments().getSerializable("myProvider");

        mJoinPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SifDexListActivity) getActivity()).onCheckStartDepositPool(pool);
                getDialog().dismiss();
            }
        });

        mExitPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SifDexListActivity) getActivity()).onCheckStartWithdrawPool(pool, myProvider);
                getDialog().dismiss();
            }
        });

        return view;
    }

}

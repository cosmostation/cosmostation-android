package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;

public class Dialog_Pool_Kava extends DialogFragment {

    private LinearLayout mJoinPool, mExitPool;

    public static Dialog_Pool_Kava newInstance(Bundle bundle) {
        Dialog_Pool_Kava frag = new Dialog_Pool_Kava();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choice_pool, null);

        mJoinPool = view.findViewById(R.id.join_pool);
        mExitPool = view.findViewById(R.id.exit_pool);

        mJoinPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DAppsList5Activity)getActivity()).onCheckStartJoinPool(getArguments().getParcelable("mKavaPool"));
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}

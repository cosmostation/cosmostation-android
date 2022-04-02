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
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;

public class Dialog_Choice_Cosmos extends DialogFragment {

    private LinearLayout mCosmos, mCosmosTest;

    public static Dialog_Choice_Cosmos newInstance(Bundle bundle) {
        Dialog_Choice_Cosmos frag = new Dialog_Choice_Cosmos();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choice_cosmos, null);

        mCosmos = view.findViewById(R.id.cosmos_net);
        mCosmosTest = view.findViewById(R.id.cosmos_test_net);

        mCosmos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).onChoiceNet(BaseChain.COSMOS_MAIN);
                dismiss();
            }
        });

        mCosmosTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).onChoiceNet(BaseChain.COSMOS_TEST);
                dismiss();
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}
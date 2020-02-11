package wannabit.io.cosmostaion.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;

public class Dialog_Choice_Kava extends DialogFragment {

    private LinearLayout mKava, mKavaTest;

    public static Dialog_Choice_Kava newInstance(Bundle bundle) {
        Dialog_Choice_Kava frag = new Dialog_Choice_Kava();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choice_kava, null);

        mKava = view.findViewById(R.id.kava_net);
        mKavaTest = view.findViewById(R.id.kava_test_net);

        mKava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.KAVA_MAIN);
                getDialog().dismiss();
            }
        });

        mKavaTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.KAVA_TEST);
                getDialog().dismiss();
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}
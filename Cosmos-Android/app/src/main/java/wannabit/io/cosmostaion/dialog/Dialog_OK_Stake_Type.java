package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import wannabit.io.cosmostaion.R;

public class Dialog_OK_Stake_Type extends DialogFragment {

    private LinearLayout mDirect, mIndirect;

    public static Dialog_OK_Stake_Type newInstance(Bundle bundle) {
        Dialog_OK_Stake_Type frag = new Dialog_OK_Stake_Type();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_ok_stake_type, null);

        mDirect = view.findViewById(R.id.vote_layer);
        mIndirect = view.findViewById(R.id.agent_layer);

        mDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("type", 0);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });

        mIndirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra("type", 1);
//                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
//                getDialog().dismiss();
                Toast.makeText(getContext(), R.string.error_prepare, Toast.LENGTH_SHORT).show();
                return;
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
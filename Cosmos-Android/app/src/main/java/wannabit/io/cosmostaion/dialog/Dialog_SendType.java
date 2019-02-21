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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.utils.WLog;

public class Dialog_SendType extends DialogFragment {

    private LinearLayout mAtom, mPhoton;
    private View mLine;

    public static Dialog_SendType newInstance(Bundle bundle) {
        Dialog_SendType frag = new Dialog_SendType();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_sendtype, null);

        mAtom = view.findViewById(R.id.send_atom);
        mPhoton = view.findViewById(R.id.send_photon);
        mLine = view.findViewById(R.id.send_line);


        if(getArguments().getBoolean(BaseConstant.COSMOS_ATOM)) {
            WLog.w("Atom ok");
            mAtom.setVisibility(View.VISIBLE);
        } else {
            WLog.w("Atom no");
            mAtom.setVisibility(View.GONE);
        }

        if(getArguments().getBoolean(BaseConstant.COSMOS_PHOTON)) {
            WLog.w("photon ok");
            mPhoton.setVisibility(View.VISIBLE);
        } else {
            WLog.w("photon no");
            mPhoton.setVisibility(View.GONE);
        }

        if(mAtom.getVisibility() == View.GONE || mPhoton.getVisibility() == View.GONE) {
            mLine.setVisibility(View.GONE);
        }

        mAtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("coin", BaseConstant.COSMOS_ATOM);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });

        mPhoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("coin", BaseConstant.COSMOS_PHOTON);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}

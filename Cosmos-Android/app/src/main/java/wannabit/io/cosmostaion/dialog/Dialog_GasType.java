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
import android.widget.TextView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.utils.WDp;

public class Dialog_GasType extends DialogFragment {

    private LinearLayout mAtom, mMuon, mPhotino;
    private LinearLayout mBtnAtom, mBtnMuon, mBtnPhotino;

    public static Dialog_GasType newInstance(Bundle bundle) {
        Dialog_GasType frag = new Dialog_GasType();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_sendtype, null);

        mAtom = view.findViewById(R.id.atom_layer);
        mMuon = view.findViewById(R.id.muon_layer);
        mPhotino = view.findViewById(R.id.photino_layer);
        mBtnAtom = view.findViewById(R.id.send_atom);
        mBtnMuon = view.findViewById(R.id.send_muon);
        mBtnPhotino = view.findViewById(R.id.send_photino);

        if (BaseChain.getChain(getArguments().getString("chain")).equals(BaseChain.COSMOS_MAIN)) {
            mAtom.setVisibility(View.VISIBLE);
            mMuon.setVisibility(View.GONE);
            mPhotino.setVisibility(View.GONE);

        } else {
            mAtom.setVisibility(View.GONE);
            mMuon.setVisibility(View.VISIBLE);
            mBtnPhotino.setVisibility(View.VISIBLE);

        }

        mBtnAtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("coin", BaseConstant.TOKEN_ATOM);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });

        mBtnMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("coin", BaseConstant.TOKEN_MUON);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });

        mBtnPhotino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("coin", BaseConstant.COSMOS_PHOTINO);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
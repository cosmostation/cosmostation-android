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
import wannabit.io.cosmostaion.activities.RestoreActivity;

public class Dialog_LumRestorePath extends DialogFragment {

    private LinearLayout mBasicPath, mAirdropPath;

    public static Dialog_LumRestorePath newInstance(Bundle bundle) {
        Dialog_LumRestorePath frag = new Dialog_LumRestorePath();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_lum_restore_path, null);

        mBasicPath      = view.findViewById(R.id.basic_path);
        mAirdropPath    = view.findViewById(R.id.airdrop_path);

        mBasicPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RestoreActivity)getActivity()).onUsingCustomPath(1);
                getDialog().dismiss();
            }
        });

        mAirdropPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RestoreActivity)getActivity()).onUsingCustomPath(0);
                getDialog().dismiss();
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}

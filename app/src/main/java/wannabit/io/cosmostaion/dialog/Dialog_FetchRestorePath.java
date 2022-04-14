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

public class Dialog_FetchRestorePath extends DialogFragment {

    public static Dialog_FetchRestorePath newInstance(Bundle bundle) {
        Dialog_FetchRestorePath frag = new Dialog_FetchRestorePath();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fetch_restore_type, null);

        LinearLayout mCosmosPath = view.findViewById(R.id.fetch_cosmos_path);
        LinearLayout mETHNonLedgerPath = view.findViewById(R.id.fetch_non_ledger_path);
        LinearLayout mETHLedgerLivePAth = view.findViewById(R.id.fetch_eth_live_path);
        LinearLayout mETHLegacyPath = view.findViewById(R.id.fetch_eth_legacy_path);

        mCosmosPath.setOnClickListener(v -> {
            ((RestoreActivity) getActivity()).onUsingCustomPath(0);
            dismiss();
        });

        mETHNonLedgerPath.setOnClickListener(v -> {
            ((RestoreActivity) getActivity()).onUsingCustomPath(1);
            dismiss();
        });

        mETHLedgerLivePAth.setOnClickListener(v -> {
            ((RestoreActivity) getActivity()).onUsingCustomPath(2);
            dismiss();
        });

        mETHLegacyPath.setOnClickListener(v -> {
            ((RestoreActivity) getActivity()).onUsingCustomPath(3);
            dismiss();
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}

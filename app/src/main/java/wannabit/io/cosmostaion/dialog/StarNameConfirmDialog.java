package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;

public class StarNameConfirmDialog extends DialogFragment {

    public final static String STAR_NAME_BUNDLE_KEY = "starName";
    public final static String STAR_NAME_ORIGIN_ADDRESS_BUNDLE_KEY = "originAddress";
    public final static String STAR_NAME_CONFIRM_BUNDLE_KEY = "starNameConfirm";

    public static StarNameConfirmDialog newInstance(Bundle bundle) {
        StarNameConfirmDialog frag = new StarNameConfirmDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_starname_confirm, null);
        TextView starname_info = view.findViewById(R.id.starname_info);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_posi = view.findViewById(R.id.btn_posi);

        starname_info.setText(String.format(getString(R.string.str_starname_confirm_msg), getArguments().getString(StarNameConfirmDialog.STAR_NAME_BUNDLE_KEY), getArguments().getString(StarNameConfirmDialog.STAR_NAME_ORIGIN_ADDRESS_BUNDLE_KEY)));
        btn_negative.setOnClickListener(v -> getDialog().dismiss());

        btn_posi.setOnClickListener(v -> {
            Bundle result = new Bundle();
            result.putString(StarNameConfirmDialog.STAR_NAME_BUNDLE_KEY, getArguments().getString(StarNameConfirmDialog.STAR_NAME_BUNDLE_KEY));
            result.putString(StarNameConfirmDialog.STAR_NAME_ORIGIN_ADDRESS_BUNDLE_KEY, getArguments().getString(StarNameConfirmDialog.STAR_NAME_ORIGIN_ADDRESS_BUNDLE_KEY));
            getParentFragmentManager().setFragmentResult(StarNameConfirmDialog.STAR_NAME_CONFIRM_BUNDLE_KEY, result);
            dismiss();
        });

        return view;
    }
}
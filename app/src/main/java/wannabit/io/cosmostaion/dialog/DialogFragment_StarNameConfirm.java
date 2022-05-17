package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;

public class DialogFragment_StarNameConfirm extends DialogFragment {

    public static DialogFragment_StarNameConfirm newInstance(Bundle bundle) {
        DialogFragment_StarNameConfirm frag = new DialogFragment_StarNameConfirm();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return inflater.inflate(R.layout.dialog_starname_confirm, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_posi = view.findViewById(R.id.btn_posi);

        TextView starnameTv = view.findViewById(R.id.tv_startname);
        TextView addressTv = view.findViewById(R.id.tv_address);
        starnameTv.setText(getArguments().getString("starname"));
        addressTv.setText(getArguments().getString("originAddress"));

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        btn_posi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("starname", getArguments().getString("starname"));
                resultIntent.putExtra("originAddress", getArguments().getString("originAddress"));
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            }
        });
    }
}
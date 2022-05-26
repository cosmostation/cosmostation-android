package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class Dialog_Currency_Set extends DialogFragment {

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private CurrencySetAdapter mCurrencySetAdapter;

    private String[] mSymbolList = new String[16];
    private String[] mUnitList = new String[16];

    public static Dialog_Currency_Set newInstance(Bundle bundle) {
        Dialog_Currency_Set frag = new Dialog_Currency_Set();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_choice, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mDialogTitle.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mCurrencySetAdapter = new CurrencySetAdapter();
        mRecyclerView.setAdapter(mCurrencySetAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class CurrencySetAdapter extends RecyclerView.Adapter<CurrencySetAdapter.CurrencyHolder> {

        @NonNull
        @Override
        public CurrencySetAdapter.CurrencyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new CurrencySetAdapter.CurrencyHolder(getLayoutInflater().inflate(R.layout.item_dialog_currency_set, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CurrencySetAdapter.CurrencyHolder holder, int position) {
            mSymbolList = getResources().getStringArray(R.array.currency_symbol_array);
            mUnitList = getResources().getStringArray(R.array.currency_unit_array);

            holder.currencyImg.setText(mSymbolList[position]);
            holder.currencyName.setText(mUnitList[position]);
            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("currency", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mSymbolList == null ? 0 : mSymbolList.length;
        }

        public class CurrencyHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            TextView currencyImg;
            TextView currencyName;

            public CurrencyHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                currencyImg = itemView.findViewById(R.id.currencyImg);
                currencyName = itemView.findViewById(R.id.currencyName);
            }
        }

    }
}

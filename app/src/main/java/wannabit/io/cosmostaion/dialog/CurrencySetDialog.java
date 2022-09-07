package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import wannabit.io.cosmostaion.R;

public class CurrencySetDialog extends DialogFragment {

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private CurrencySetAdapter mCurrencySetAdapter;

    public static CurrencySetDialog newInstance(Bundle bundle) {
        CurrencySetDialog frag = new CurrencySetDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_choice, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mDialogTitle.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mCurrencySetAdapter = new CurrencySetAdapter();

        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        mRecyclerView.setAdapter(mCurrencySetAdapter);
        return view;
    }

    private class CurrencySetAdapter extends RecyclerView.Adapter<CurrencySetAdapter.CurrencyHolder> {

        @NonNull
        @Override
        public CurrencySetAdapter.CurrencyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new CurrencySetAdapter.CurrencyHolder(getLayoutInflater().inflate(R.layout.item_dialog_currency_set, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CurrencySetAdapter.CurrencyHolder viewHolder, int position) {
            onCurrencyItem(viewHolder, position);
        }

        private void onCurrencyItem(CurrencyHolder holder, int position) {
            String[] mUnitList = getResources().getStringArray(R.array.currency_unit_array);

            holder.currencyName.setText(mUnitList[position]);

            holder.rootLayer.setOnClickListener(v -> {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("currency", position);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
            });
        }

        @Override
        public int getItemCount() {
            return getResources().getStringArray(R.array.currency_unit_array).length;
        }

        public class CurrencyHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            TextView currencyName;

            public CurrencyHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                currencyName = itemView.findViewById(R.id.currencyName);
            }
        }
    }

    public static class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = ContextCompat.getDrawable(context, R.drawable.box_line_divider);
        }

        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}

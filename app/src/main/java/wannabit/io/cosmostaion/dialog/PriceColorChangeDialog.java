package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;

public class PriceColorChangeDialog extends DialogFragment {

    private TextView mDialogTitle;
    private RecyclerView mRecyclerView;
    private PriceColorOptionListAdapter mPriceColorOptionListAdapter;

    public static PriceColorChangeDialog newInstance(Bundle bundle) {
        PriceColorChangeDialog frag = new PriceColorChangeDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);

        mDialogTitle = view.findViewById(R.id.dialog_title);
        mDialogTitle.setText(R.string.str_price_color_option_title);

        mRecyclerView = view.findViewById(R.id.recycler);
        mPriceColorOptionListAdapter = new PriceColorOptionListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mPriceColorOptionListAdapter);
        return view;
    }

    private class PriceColorOptionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new priceColorChangeHolder(getLayoutInflater().inflate(R.layout.item_dialog_price_color_option, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            onBindSelectedOptionItemViewHolder(holder, position);
        }

        private void onBindSelectedOptionItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final priceColorChangeHolder holder = (priceColorChangeHolder) viewHolder;
            holder.optionNumber.setText(String.valueOf(position + 1));
            if (position == 0) {
                holder.iconPriceColorUp.setImageResource(R.drawable.icon_pricegreen);
                holder.iconPriceColorDown.setImageResource(R.drawable.icon_pricered);
                holder.rootLayer.setOnClickListener(v -> {
                    Bundle result = new Bundle();
                    result.putInt(BaseConstant.POSITION, position + 1);
                    getParentFragmentManager().setFragmentResult(BaseConstant.PRE_PRICE_COLOR, result);
                    getSActivity().getBaseDao().setPriceColorOption(position + 1);
                    dismiss();
                });
            } else {
                holder.iconPriceColorUp.setImageResource(R.drawable.icon_pricered);
                holder.iconPriceColorDown.setImageResource(R.drawable.icon_pricegreen);
                holder.rootLayer.setOnClickListener(v -> {
                    Bundle result = new Bundle();
                    result.putInt(BaseConstant.POSITION, position + 1);
                    getParentFragmentManager().setFragmentResult(BaseConstant.PRE_PRICE_COLOR, result);
                    getSActivity().getBaseDao().setPriceColorOption(position + 1);
                    dismiss();
                });
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        public class priceColorChangeHolder extends RecyclerView.ViewHolder {
            FrameLayout rootLayer;
            ImageView iconPriceColorUp, iconPriceColorDown;
            TextView optionNumber;

            public priceColorChangeHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                iconPriceColorUp = itemView.findViewById(R.id.icon_price_color_up);
                iconPriceColorDown = itemView.findViewById(R.id.icon_price_color_down);
                optionNumber = itemView.findViewById(R.id.option_number);
            }
        }
    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }

}



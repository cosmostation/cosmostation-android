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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dao.FeeInfo;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class SwapCoinListDialog extends DialogFragment {

    private ConstraintLayout mDialogLayout;
    private TextView mDialogTitle;
    private RecyclerView mRecyclerView;
    private SwapChainListAdapter mSwapChainListAdapter;

    private ArrayList<String> mSwapCoinList;
    private ArrayList<FeeInfo.FeeData> mFeeDataList;
    private ArrayList<Coin> mSendCoinList;

    public static SwapCoinListDialog newInstance(Bundle bundle) {
        SwapCoinListDialog frag = new SwapCoinListDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mSwapCoinList = getArguments().getStringArrayList("denoms");
        mFeeDataList = (ArrayList<FeeInfo.FeeData>) getArguments().getSerializable("feeDatas");
        mSendCoinList = (ArrayList<Coin>) getArguments().getSerializable("sendCoins");
        mDialogLayout = view.findViewById(R.id.dialog_layout);
        mDialogTitle = view.findViewById(R.id.dialog_title);

        mDialogLayout.setBackgroundResource(R.drawable.dialog_bg_colorwhite2daynight);
        if (getTargetRequestCode() == 8500) {
            mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_coin_swap_in));
        } else if (getTargetRequestCode() == 8501) {
            mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_coin_swap_out));
        } else if (getTargetRequestCode() == 8502) {
            mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_fee_denom));
        } else {
            mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_to_send_coin));
        }
        mRecyclerView = view.findViewById(R.id.recycler);
        mSwapChainListAdapter = new SwapChainListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mSwapChainListAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class SwapChainListAdapter extends RecyclerView.Adapter<SwapChainListAdapter.SwapChainHolder> {
        private static final int TYPE_SWAP_LIST = 0;
        private static final int TYPE_FEE_LIST = 1;
        private static final int TYPE_SEND_COIN_LIST = 2;

        @NonNull
        @Override
        public SwapChainListAdapter.SwapChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new SwapChainListAdapter.SwapChainHolder(getLayoutInflater().inflate(R.layout.item_dialog_swap_coin, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SwapChainListAdapter.SwapChainHolder holder, int position) {
            if (getItemViewType(position) == TYPE_SWAP_LIST) {
                onBindSwapListItemViewHolder(holder, position);
            } else if (getItemViewType(position) == TYPE_FEE_LIST) {
                onBindFeeListItemViewHolder(holder, position);
            } else if (getItemViewType(position) == TYPE_SEND_COIN_LIST) {
                onBindSendListItemViewHolder(holder, position);
            }
        }

        private void onBindSwapListItemViewHolder(SwapChainHolder holder, int position) {
            final String inputCoin = mSwapCoinList.get(position);
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, inputCoin, holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, inputCoin, holder.coinName);

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selectedDenom", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        private void onBindFeeListItemViewHolder(SwapChainHolder holder, int position) {
            String denom = mFeeDataList.get(position).denom;
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinName);

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("position", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        private void onBindSendListItemViewHolder(SwapChainHolder holder, int position) {
            String denom = mSendCoinList.get(position).denom;
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinName);

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("position", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            if (getTargetRequestCode() == 8502) {
                return mFeeDataList.size();
            } else if (getTargetRequestCode() == 8503) {
                return mSendCoinList.size();
            } else {
                return mSwapCoinList.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (getTargetRequestCode() == 8502) {
                return TYPE_FEE_LIST;
            } else if (getTargetRequestCode() == 8503) {
                return TYPE_SEND_COIN_LIST;
            } else {
                return TYPE_SWAP_LIST;
            }
        }

        public class SwapChainHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            ImageView coinImg;
            TextView coinName;

            public SwapChainHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                coinImg = itemView.findViewById(R.id.coinImg);
                coinName = itemView.findViewById(R.id.coinName);
            }
        }
    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }
}



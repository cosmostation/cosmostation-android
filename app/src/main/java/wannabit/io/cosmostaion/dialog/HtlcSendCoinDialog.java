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
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.utils.WDp;

public class HtlcSendCoinDialog extends DialogFragment {

    private ConstraintLayout mDialogLayout;
    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private ToSwapCoinListAdapter mToSwapCoinListAdapter;
    private BaseChain mBaseChain;
    private ArrayList<String> mSwappableCoinList;

    public static HtlcSendCoinDialog newInstance(Bundle bundle) {
        HtlcSendCoinDialog frag = new HtlcSendCoinDialog();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogLayout = view.findViewById(R.id.dialog_layout);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);
        mBaseChain = BaseChain.getChain(getArguments().getString("chainName"));
        mSwappableCoinList = BaseChain.getHtlcSwappableCoin(mBaseChain);

        mDialogLayout.setBackgroundResource(R.drawable.dialog_bg_colorwhite2daynight);
        mDialogTitle.setText(R.string.str_select_to_send_coin);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mToSwapCoinListAdapter = new ToSwapCoinListAdapter();
        mRecyclerView.setAdapter(mToSwapCoinListAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class ToSwapCoinListAdapter extends RecyclerView.Adapter<ToSwapCoinListAdapter.ToSwapCoinHolder> {

        @NonNull
        @Override
        public ToSwapCoinHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ToSwapCoinHolder(getLayoutInflater().inflate(R.layout.item_dialog_swap_coin, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull ToSwapCoinHolder holder, int position) {
            onBindBep3CoinItemViewHolder(holder, position);
        }

        private void onBindBep3CoinItemViewHolder(ToSwapCoinHolder holder, int position) {
            final String tosendCoin = mSwappableCoinList.get(position);
            if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                if (tosendCoin.equals(BaseConstant.TOKEN_HTLC_BINANCE_BNB)) {
                    holder.coinImg.setImageResource(getSActivity().mChainConfig.mainDenomImg());
                    holder.coinName.setText("BNB");
                } else {
                    WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, tosendCoin, holder.coinImg);
                    WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, tosendCoin, holder.coinName);
                }

            } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                if (tosendCoin.equals(BaseConstant.TOKEN_HTLC_KAVA_BNB)) {
                    holder.coinImg.setImageResource(R.drawable.bnb_on_kava);
                    holder.coinName.setText("BNB");
                } else {
                    WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, tosendCoin, holder.coinImg);
                    WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, tosendCoin, holder.coinName);
                }
            }

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("position", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mSwappableCoinList.size();
        }

        public class ToSwapCoinHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            ImageView coinImg;
            TextView coinName;

            public ToSwapCoinHolder(@NonNull View itemView) {
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

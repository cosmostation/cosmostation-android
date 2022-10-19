package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    public final static String HTLC_LIST_BUNDLE_KEY = "htlclist";

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);
        mBaseChain = BaseChain.getChain(getArguments().getString("chainName"));
        mSwappableCoinList = BaseChain.getHtlcSwappableCoin(mBaseChain);

        mDialogTitle.setText(R.string.str_select_to_send_coin);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mToSwapCoinListAdapter = new ToSwapCoinListAdapter();
        mRecyclerView.setAdapter(mToSwapCoinListAdapter);

        return view;
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
                    holder.coinImg.setImageResource(R.drawable.token_binance);
                    holder.coinName.setText("BNB");
                } else {
                    WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, tosendCoin, holder.coinImg);
                    WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, tosendCoin, holder.coinName);
                }
            }

            holder.rootLayer.setOnClickListener(v -> {
                Bundle result = new Bundle();
                result.putInt(BaseConstant.POSITION, position);
                getParentFragmentManager().setFragmentResult(HTLC_LIST_BUNDLE_KEY, result);
                dismiss();
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

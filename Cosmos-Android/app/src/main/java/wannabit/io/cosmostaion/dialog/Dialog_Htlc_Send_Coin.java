package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BTC;

public class Dialog_Htlc_Send_Coin extends DialogFragment {

    private RecyclerView mRecyclerView;
    private ToSwapCoinListAdapter mToSwapCoinListAdapter;
    private BaseChain mBaseChain;
    private ArrayList<String> mSwappableCoinList;

    public static Dialog_Htlc_Send_Coin newInstance(Bundle bundle) {
        Dialog_Htlc_Send_Coin frag = new Dialog_Htlc_Send_Coin();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_htlc_send_coin, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mBaseChain = BaseChain.getChain(getArguments().getString("chainName"));
        mSwappableCoinList = BaseChain.getHtlcSwappableCoin(mBaseChain);
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
            final String tosendCoin = mSwappableCoinList.get(position);
            if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                if (tosendCoin.equals(TOKEN_HTLC_BINANCE_BNB)) {
                    holder.coinImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_token_img));
                    holder.coinName.setText(getString(R.string.str_bnb_c));
                }

            } else if (mBaseChain.equals(BaseChain.BNB_TEST)) {
                if (tosendCoin.equals(TOKEN_HTLC_BINANCE_TEST_BNB)) {
                    holder.coinImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_token_img));
                    holder.coinName.setText(getString(R.string.str_bnb_c));
                } else if (tosendCoin.equals(TOKEN_HTLC_BINANCE_TEST_BTC)) {
                    holder.coinImg.setImageDrawable(getResources().getDrawable(R.drawable.btc_img));
                    holder.coinName.setText(getString(R.string.str_btc_c));
                }

            } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                if (tosendCoin.equals(TOKEN_HTLC_KAVA_BNB)) {
                    holder.coinImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_on_kava));
                    holder.coinName.setText(getString(R.string.str_bnb_c));
                }

            } else if (mBaseChain.equals(BaseChain.KAVA_TEST)) {
                if (tosendCoin.equals(TOKEN_HTLC_KAVA_TEST_BNB)) {
                    holder.coinImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_on_kava));
                    holder.coinName.setText(getString(R.string.str_bnb_c));
                } else if (tosendCoin.equals(TOKEN_HTLC_KAVA_TEST_BTC)) {
                    holder.coinImg.setImageDrawable(getResources().getDrawable(R.drawable.btc_on_kava));
                    holder.coinName.setText(getString(R.string.str_btc_c));
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
            RelativeLayout rootLayer;
            ImageView coinImg;
            TextView coinName;
            public ToSwapCoinHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer   = itemView.findViewById(R.id.rootLayer);
                coinImg    = itemView.findViewById(R.id.coinImg);
                coinName   = itemView.findViewById(R.id.coinName);
            }
        }
    }
}

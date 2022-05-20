package wannabit.io.cosmostaion.dialog;

import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Dialog_Htlc_Send_Coin extends DialogFragment {

    private RecyclerView          mRecyclerView;
    private TextView              mDialogTitle;
    private ToSwapCoinListAdapter mToSwapCoinListAdapter;
    private BaseChain             mBaseChain;
    private ArrayList<String>     mSwappableCoinList;

    public static Dialog_Htlc_Send_Coin newInstance(Bundle bundle) {
        Dialog_Htlc_Send_Coin frag = new Dialog_Htlc_Send_Coin();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return inflater.inflate(R.layout.dialog_recycler_dialog_template, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mDialogTitle.setText(R.string.str_select_to_send_coin);
        mRecyclerView = view.findViewById(R.id.recycler);
        mBaseChain = BaseChain.getChain(getArguments().getString("chainName"));
        mSwappableCoinList = BaseChain.getHtlcSwappableCoin(mBaseChain);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mToSwapCoinListAdapter = new ToSwapCoinListAdapter();
        mRecyclerView.setAdapter(mToSwapCoinListAdapter);
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
                } else if (tosendCoin.equals(TOKEN_HTLC_BINANCE_BTCB)) {
                    holder.coinName.setText("BTC");
                    try {
                        Picasso.get().load(BINANCE_TOKEN_IMG_URL + "BTCB.png").into(holder.coinImg);
                    } catch (Exception e){}

                } else if (tosendCoin.equals(TOKEN_HTLC_BINANCE_XRPB)) {
                    holder.coinName.setText("XRP");
                    try {
                        Picasso.get().load(BINANCE_TOKEN_IMG_URL + "XRP.png").into(holder.coinImg);
                    } catch (Exception e){}

                } else if (tosendCoin.equals(TOKEN_HTLC_BINANCE_BUSD)) {
                    holder.coinName.setText("BUSD");
                    try {
                        Picasso.get().load(BINANCE_TOKEN_IMG_URL + "BUSD.png").into(holder.coinImg);
                    } catch (Exception e){}

                }

            } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                if (tosendCoin.equals(TOKEN_HTLC_KAVA_BNB)) {
                    holder.coinImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_on_kava));
                    holder.coinName.setText(getString(R.string.str_bnb_c));
                } else if (tosendCoin.equals(TOKEN_HTLC_KAVA_BTCB)) {
                    holder.coinName.setText("BTC");
                    try {
                        Picasso.get().load(KAVA_COIN_IMG_URL + "btcb.png").into(holder.coinImg);
                    } catch (Exception e){}

                } else if (tosendCoin.equals(TOKEN_HTLC_KAVA_XRPB)) {
                    holder.coinName.setText("XRP");
                    try {
                        Picasso.get().load(KAVA_COIN_IMG_URL + "xrpb.png").into(holder.coinImg);
                    } catch (Exception e){}

                } else if (tosendCoin.equals(TOKEN_HTLC_KAVA_BUSD)) {
                    holder.coinName.setText("BUSD");
                    try {
                        Picasso.get().load(KAVA_COIN_IMG_URL + "busd.png").into(holder.coinImg);
                    } catch (Exception e){}

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

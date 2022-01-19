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
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dao.Assets;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.ASSET_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ION;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;

public class Dialog_Swap_Coin_List extends DialogFragment {

    private TextView mDialogTitle;
    private RecyclerView mRecyclerView;
    private SwapChainListAdapter mSwapChainListAdapter;
    private ArrayList<String> mSwapCoinList;

    public static Dialog_Swap_Coin_List newInstance(Bundle bundle) {
        Dialog_Swap_Coin_List frag = new Dialog_Swap_Coin_List();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_swap_coin_list, null);
        mSwapCoinList = getArguments().getStringArrayList("denoms");
        mDialogTitle = view.findViewById(R.id.dialog_swap_title);
        if (getTargetRequestCode() == 8500) { mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_coin_swap_in)); }
        else { mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_coin_swap_out)); }
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

        @NonNull
        @Override
        public SwapChainListAdapter.SwapChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new SwapChainListAdapter.SwapChainHolder(getLayoutInflater().inflate(R.layout.item_dialog_swap_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SwapChainListAdapter.SwapChainHolder holder, int position) {
            final String inputCoin = mSwapCoinList.get(position);
            IbcToken ibcToken = getSActivity().getBaseDao().getIbcToken(inputCoin);
            if (inputCoin.startsWith("ibc/")) {
                if (ibcToken.auth) {
                    holder.chainName.setText(ibcToken.display_denom.toUpperCase());
                } else {
                    holder.chainName.setText("UNKNOWN");
                }
                try {
                    Picasso.get().load(ibcToken.moniker).fit().placeholder(R.drawable.token_default_ibc).error(R.drawable.token_default_ibc).into(holder.chainImg);
                } catch (Exception e) { }
            } else if (getSActivity().mBaseChain.equals(KAVA_MAIN)) {
                try {
                    Picasso.get().load(KAVA_COIN_IMG_URL + mSwapCoinList.get(position) + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.chainImg);
                    String baseDenom = WDp.getKavaBaseDenom(mSwapCoinList.get(position));
                    if (baseDenom.equalsIgnoreCase(TOKEN_KAVA)) {
                        holder.chainName.setText(getString(R.string.str_kava_c));
                    } else {
                        holder.chainName.setText(mSwapCoinList.get(position).toUpperCase());
                    }
                } catch (Exception e) { }
            } else if (inputCoin.equals(TOKEN_ATOM)) {
                holder.chainName.setText(getString(R.string.str_atom_c));
                Picasso.get().cancelRequest(holder.chainImg);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.atom_ic));
            } else if (inputCoin.equals(TOKEN_OSMOSIS)) {
                holder.chainName.setText(getString(R.string.str_osmosis_c));
                Picasso.get().cancelRequest(holder.chainImg);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.token_osmosis));
            } else if (inputCoin.equals(TOKEN_SIF)) {
                holder.chainName.setText(getString(R.string.str_sif_c));
                Picasso.get().cancelRequest(holder.chainImg);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensifchain));
            } else if (inputCoin.equals(TOKEN_ION)) {
                holder.chainName.setText(getString(R.string.str_uion_c));
                Picasso.get().cancelRequest(holder.chainImg);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ion));
            } else if (inputCoin.startsWith("c")) {
                final Assets assets = getSActivity().getBaseDao().getAsset(inputCoin);
                if (assets != null) {
                    Picasso.get().load(ASSET_IMG_URL + assets.origin_chain + "/" + assets.logo).fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.chainImg);
                    holder.chainName.setText(assets.origin_symbol);
                }
            } else {
                holder.chainName.setText("UNKNOWN");
                Picasso.get().cancelRequest(holder.chainImg);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));
            }

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

        @Override
        public int getItemCount() { return mSwapCoinList.size(); }

        public class SwapChainHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            ImageView chainImg;
            TextView chainName;

            public SwapChainHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                chainImg = itemView.findViewById(R.id.chainImg);
                chainName = itemView.findViewById(R.id.chainName);
            }
        }

    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }
}



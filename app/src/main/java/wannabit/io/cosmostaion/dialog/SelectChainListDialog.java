package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Sets;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.FeeInfo;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

public class SelectChainListDialog extends DialogFragment {

    public static int SELECT_INPUT_CHAIN_VALUE = 8500;
    public static int SELECT_OUTPUT_CHAIN_VALUE = 8501;
    public static int SELECT_FEE_DENOM_VALUE = 8502;
    public static int SELECT_SEND_COIN_VALUE = 8503;
    public static int SELECT_IBC_CHAIN_VALUE = 8504;

    public final static String SELECT_CHAIN_LIST_BUNDLE_KEY = "selectChainList";
    public final static String TO_SENDABLE_CHAIN_CONFIG_BUNDLE_KEY = "toSendCoins";
    public final static String WATCH_ADDRESS_BUNDLE_KEY = "watchAddress";
    public final static String SEND_COIN_LIST_BUNDLE_KEY = "sendCoins";
    public final static String FEE_DATA_LIST_BUNDLE_KEY = "feeDatas";
    public final static String SWAP_COIN_LIST_BUNDLE_KEY = "denoms";

    private int keyValue;

    private OnSelectChainsDialogResult mSelectChainsDialogResult;

    private TextView mDialogTitle;
    private RecyclerView mRecyclerView;
    private SwapChainListAdapter mSwapChainListAdapter;
    private LinearLayout mBtnLayer;
    private Button mBtnLeft, mBtnRight;

    private ArrayList<String> mSwapCoinList;
    private ArrayList<FeeInfo.FeeData> mFeeDataList;
    private ArrayList<Coin> mSendCoinList;
    private String mWatchAddress = "";
    private ArrayList<ChainConfig> mToSendableChainConfig;

    private Set<BaseChain> selectedSet = Sets.newHashSet();

    public static SelectChainListDialog newInstance(Bundle bundle) {
        SelectChainListDialog frag = new SelectChainListDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mSwapCoinList = getArguments().getStringArrayList(SelectChainListDialog.SWAP_COIN_LIST_BUNDLE_KEY);
        mFeeDataList = (ArrayList<FeeInfo.FeeData>) getArguments().getSerializable(SelectChainListDialog.FEE_DATA_LIST_BUNDLE_KEY);
        mSendCoinList = (ArrayList<Coin>) getArguments().getSerializable(SelectChainListDialog.SEND_COIN_LIST_BUNDLE_KEY);
        mWatchAddress = getArguments().getString(WATCH_ADDRESS_BUNDLE_KEY);
        mToSendableChainConfig = (ArrayList<ChainConfig>) getArguments().getSerializable(SelectChainListDialog.TO_SENDABLE_CHAIN_CONFIG_BUNDLE_KEY);

        keyValue = getArguments().getInt(SELECT_CHAIN_LIST_BUNDLE_KEY);

        mDialogTitle = view.findViewById(R.id.dialog_title);
        mBtnLayer = view.findViewById(R.id.btn_layer);
        mBtnLeft = view.findViewById(R.id.btn_left);
        mBtnRight = view.findViewById(R.id.btn_right);

        if (keyValue == SelectChainListDialog.SELECT_INPUT_CHAIN_VALUE) {
            mDialogTitle.setText(R.string.str_select_coin_swap_in);
        } else if (keyValue == SelectChainListDialog.SELECT_OUTPUT_CHAIN_VALUE) {
            mDialogTitle.setText(R.string.str_select_coin_swap_out);
        } else if (keyValue == SelectChainListDialog.SELECT_FEE_DENOM_VALUE) {
            mDialogTitle.setText(R.string.str_select_fee_denom);
        } else if (keyValue == SelectChainListDialog.SELECT_SEND_COIN_VALUE) {
            mDialogTitle.setText(R.string.str_select_to_send_coin);
        } else if (keyValue == SelectChainListDialog.SELECT_IBC_CHAIN_VALUE) {
            mDialogTitle.setText(R.string.str_select_to_send_chain);
        } else {
            mDialogTitle.setText(getSActivity().getString(R.string.str_select_chains));
            mBtnLayer.setVisibility(View.VISIBLE);
            mBtnLeft.setOnClickListener(View -> dismiss());
            mBtnRight.setOnClickListener(View -> {
                if (selectedSet.size() >= 1) {
                    mSelectChainsDialogResult.SelectedChains(new Gson().toJson(selectedSet));
                    dismiss();
                    Toast.makeText(getContext(), R.string.str_imported_chains, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), R.string.error_no_select_chains, Toast.LENGTH_SHORT).show();
                }
            });
        }
        mRecyclerView = view.findViewById(R.id.recycler);
        mSwapChainListAdapter = new SwapChainListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mSwapChainListAdapter);
        return view;
    }

    private class SwapChainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_SWAP_LIST = 0;
        private static final int TYPE_FEE_LIST = 1;
        private static final int TYPE_SEND_COIN_LIST = 2;
        private static final int TYPE_WATCHING_ADDRESS_LIST = 3;
        private static final int TYPE_SEND_CHAIN_LIST = 4;


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_SEND_CHAIN_LIST) {
                return new SendChainHolder(getLayoutInflater().inflate(R.layout.item_dialog_receive_chian, viewGroup, false));
            } else {
                return new SwapChainHolder(getLayoutInflater().inflate(R.layout.item_dialog_swap_coin, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == TYPE_SWAP_LIST) {
                onBindSwapListItemViewHolder(holder, position);
            } else if (getItemViewType(position) == TYPE_FEE_LIST) {
                onBindFeeListItemViewHolder(holder, position);
            } else if (getItemViewType(position) == TYPE_SEND_COIN_LIST) {
                onBindSendListItemViewHolder(holder, position);
            } else if (getItemViewType(position) == TYPE_WATCHING_ADDRESS_LIST) {
                onBindSelectedChainListItemViewHolder(holder, position);
            } else if (getItemViewType(position) == TYPE_SEND_CHAIN_LIST) {
                onBindRecipientChainListItemViewHolder(holder, position);
            }
        }

        private void onBindSwapListItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final SwapChainHolder holder = (SwapChainHolder) viewHolder;
            final String inputCoin = mSwapCoinList.get(position);
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, inputCoin, holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, inputCoin, holder.coinName);

            holder.rootLayer.setOnClickListener(v -> {
                Bundle result = new Bundle();
                result.putInt(BaseConstant.POSITION, position);
                getParentFragmentManager().setFragmentResult(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, result);
                dismiss();
            });
        }

        private void onBindFeeListItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final SwapChainHolder holder = (SwapChainHolder) viewHolder;
            String denom = mFeeDataList.get(position).denom;
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinName);

            holder.rootLayer.setOnClickListener(view -> {
                Bundle result = new Bundle();
                result.putInt(BaseConstant.POSITION, position);
                getParentFragmentManager().setFragmentResult(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, result);
                dismiss();
            });
        }

        private void onBindSendListItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final SwapChainHolder holder = (SwapChainHolder) viewHolder;
            String denom = mSendCoinList.get(position).denom;
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinName);

            holder.rootLayer.setOnClickListener(view -> {
                Bundle result = new Bundle();
                result.putInt(BaseConstant.POSITION, position);
                getParentFragmentManager().setFragmentResult(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, result);
                dismiss();
            });
        }

        private void onBindSelectedChainListItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final SwapChainHolder holder = (SwapChainHolder) viewHolder;
            BaseChain baseChain = WDp.getChainsFromAddress(mWatchAddress).get(position);
            ChainConfig chainConfig = ChainFactory.getChain(baseChain);
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), chainConfig, chainConfig.mainDenom(), holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), chainConfig, chainConfig.mainDenom(), holder.coinName);
            bindChainSelect(holder, position, baseChain);

            ArrayList<Account> watchAddressAccounts = getSActivity().getBaseDao().onSelectAccountsByChain(baseChain);
            for (Account account : watchAddressAccounts) {
                if ((chainConfig.baseChain().equals(BaseChain.OKEX_MAIN) && account.address.equalsIgnoreCase(mWatchAddress))
                        || account.address.equalsIgnoreCase(WKey.convertAddressEthToTender(chainConfig.baseChain(), mWatchAddress))) {
                    holder.rootLayer.setClickable(false);
                    holder.rootLayer.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_gray));
                    holder.rootDimLayer.setVisibility(View.VISIBLE);
                    holder.rootDimLayer.setAlpha(0.5f);
                } else {
                    bindChainSelect(holder, position, baseChain);
                }
            }
        }

        private void bindChainSelect(SwapChainHolder holder, int position, BaseChain item) {
            if (selectedSet.contains(item)) {
                holder.itemCheck.setVisibility(View.VISIBLE);
                holder.rootLayer.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_account_selected_photon));
                holder.rootLayer.setOnClickListener(v -> {
                    selectedSet.remove(item);
                    mSwapChainListAdapter.notifyItemChanged(position);
                });
            } else {
                holder.itemCheck.setVisibility(View.GONE);
                holder.rootLayer.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_vote_quorum));
                holder.rootLayer.setOnClickListener(v -> {
                    selectedSet.add(item);
                    mSwapChainListAdapter.notifyItemChanged(position);
                });
            }
        }

        private void onBindRecipientChainListItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final SendChainHolder holder = (SendChainHolder) viewHolder;
            final ChainConfig chainConfig = mToSendableChainConfig.get(position);
            holder.itemChainImg.setImageResource(chainConfig.chainImg());
            holder.itemChainName.setText(chainConfig.chainTitleToUp());
            holder.itemChainName.setTextColor(ContextCompat.getColor(getSActivity(), chainConfig.chainColor()));

            holder.rootLayer.setOnClickListener(view -> {
                Bundle result = new Bundle();
                result.putInt(BaseConstant.POSITION, position);
                getParentFragmentManager().setFragmentResult(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, result);
                dismiss();
            });
        }

        @Override
        public int getItemCount() {
            if (keyValue == SelectChainListDialog.SELECT_INPUT_CHAIN_VALUE || keyValue == SelectChainListDialog.SELECT_OUTPUT_CHAIN_VALUE) return mSwapCoinList.size();
            else if (keyValue == SelectChainListDialog.SELECT_FEE_DENOM_VALUE) return mFeeDataList.size();
            else if (keyValue == SelectChainListDialog.SELECT_SEND_COIN_VALUE) return mSendCoinList.size();
            else if (keyValue == SelectChainListDialog.SELECT_IBC_CHAIN_VALUE) return mToSendableChainConfig.size();
            else return WDp.getChainsFromAddress(mWatchAddress).size();
        }

        @Override
        public int getItemViewType(int position) {
            if (keyValue == SelectChainListDialog.SELECT_INPUT_CHAIN_VALUE || keyValue == SelectChainListDialog.SELECT_OUTPUT_CHAIN_VALUE) return TYPE_SWAP_LIST;
            else if (keyValue == SelectChainListDialog.SELECT_FEE_DENOM_VALUE) return TYPE_FEE_LIST;
            else if (keyValue == SelectChainListDialog.SELECT_SEND_COIN_VALUE) return TYPE_SEND_COIN_LIST;
            else if (keyValue == SelectChainListDialog.SELECT_IBC_CHAIN_VALUE) return TYPE_SEND_CHAIN_LIST;
            else return TYPE_WATCHING_ADDRESS_LIST;
        }

        public class SwapChainHolder extends RecyclerView.ViewHolder {
            FrameLayout rootDimLayer;
            LinearLayout rootLayer;
            ImageView coinImg, itemCheck;
            TextView coinName;

            public SwapChainHolder(@NonNull View itemView) {
                super(itemView);
                rootDimLayer = itemView.findViewById(R.id.dim_layer);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                coinImg = itemView.findViewById(R.id.coinImg);
                coinName = itemView.findViewById(R.id.coinName);
                itemCheck = itemView.findViewById(R.id.check);
            }
        }

        public class SendChainHolder extends RecyclerView.ViewHolder {
            RelativeLayout rootLayer;
            ImageView itemChainImg;
            TextView itemChainName;

            public SendChainHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                itemChainImg = itemView.findViewById(R.id.chainImg);
                itemChainName = itemView.findViewById(R.id.chainName);
            }
        }
    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }

    public void setSelectChainsDialogResult(OnSelectChainsDialogResult dialogResult) {
        mSelectChainsDialogResult = dialogResult;
    }

    public interface OnSelectChainsDialogResult {
        void SelectedChains(String selectedChain);
    }
}



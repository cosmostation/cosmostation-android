package wannabit.io.cosmostaion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_SendType;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class SendStep1Fragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_COIN_DIALOG = 6000;

    private RecyclerView            mRecyclerView;
    private Button                  mBeforeBtn, mNextBtn;
    private ArrayList<Coin>         mToSendCoins = new ArrayList<>();

    private SendCoinAdapter         mSendCoinAdapter;


    public static SendStep1Fragment newInstance(Bundle bundle) {
        SendStep1Fragment fragment = new SendStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_step1, container, false);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mSendCoinAdapter = new SendCoinAdapter();
        mRecyclerView.setAdapter(mSendCoinAdapter);

        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(mToSendCoins == null || mToSendCoins.size() == 0) {
            onShowSendTypeDialog();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if(isValidateSendAmount()) {
                getSActivity().mTargetCoins = mToSendCoins;
                getSActivity().onNextStep();

            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void onShowSendTypeDialog() {
        Bundle bundle = new Bundle();
        boolean alreadyAtom = false;
        boolean alreadyPhoton = false;
        for (Coin coin:mToSendCoins) {
            if(coin.denom.contains(BaseConstant.COSMOS_ATOM)) alreadyAtom = true;
            if(coin.denom.contains(BaseConstant.COSMOS_PHOTON)) alreadyPhoton = true;
        }
        if (!alreadyAtom) bundle.putBoolean(BaseConstant.COSMOS_ATOM, true);
        if (!alreadyPhoton) bundle.putBoolean(BaseConstant.COSMOS_PHOTON, true);
        bundle.putString("chain", getSActivity().mAccount.baseChain);
        Dialog_SendType dialog = Dialog_SendType.newInstance(bundle);
        dialog.setCancelable(false);
        dialog.setTargetFragment(this, SELECT_COIN_DIALOG);
        dialog.show(getFragmentManager().beginTransaction(), "dialog");

    }


    private void onRemoveCoinType(int position) {
        mToSendCoins.remove(position);
        mSendCoinAdapter.notifyDataSetChanged();
    }


    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }

    //TODO check validate logic be fore add
    private boolean isValidateSendAmount() {
        if(mToSendCoins == null || mToSendCoins.size() == 0) {
            return false;
        }
        for(Coin coin:mToSendCoins) {
            BigDecimal temp = new BigDecimal(coin.amount);
            if(temp.compareTo(BigDecimal.ZERO) <= 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_COIN_DIALOG) {
            if (data.getStringExtra("coin").equals(BaseConstant.COSMOS_ATOM)) {
                Coin atom = new Coin(BaseConstant.COSMOS_ATOM, "0");
                mToSendCoins.add(atom);
            } else if (data.getStringExtra("coin").equals(BaseConstant.COSMOS_PHOTON)) {
                Coin photon = new Coin(BaseConstant.COSMOS_PHOTON, "0");
                mToSendCoins.add(photon);
            }
            mSendCoinAdapter.notifyDataSetChanged();
        }
    }

    private class SendCoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_COIN              = 0;
        private static final int TYPE_ADD               = 1;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_COIN) {
                return new SendCoinHolder(getLayoutInflater().inflate(R.layout.item_send_coin, viewGroup, false));
            } else if (viewType == TYPE_ADD) {
                return new SendAddHolder(getLayoutInflater().inflate(R.layout.item_send_add, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
            WLog.w("onBindViewHolder : " + position + "   " + getItemViewType(position));
            if (getItemViewType(position) == TYPE_ADD) {
                final SendAddHolder holder = (SendAddHolder)viewHolder;
                holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onShowSendTypeDialog();
                    }
                });
            } else {
                final SendCoinHolder holder = (SendCoinHolder)viewHolder;
                final Coin coin = mToSendCoins.get(position);
                holder.btnRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.etAmountCoin.setText("");
                        onRemoveCoinType(position);
                    }
                });
                if (coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                    holder.tvSymbolCoin.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
                    holder.tvSymbolCoin.setTextColor(getResources().getColor(R.color.colorAtom));
                    holder.tvMaxCoin.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getAtomBalance(), 6));

                } else if (coin.denom.equals(BaseConstant.COSMOS_PHOTON)) {
                    holder.tvSymbolCoin.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
                    holder.tvSymbolCoin.setTextColor(getResources().getColor(R.color.colorPhoton));
                    holder.tvMaxCoin.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getPhotonBalance(), 6));
                }

                holder.etAmountCoin.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }

                    @Override
                    public void afterTextChanged(Editable s) {
                        WLog.w("amount " + position + " " + s.toString().trim());
                        mToSendCoins.get(position).amount = s.toString().trim();
                    }
                });
            }
        }


        @Override
        public int getItemViewType(int position) {
            if (mToSendCoins.size() == 0) {
                return TYPE_ADD;
            } else if (mToSendCoins.size() == 1) {
                if(position == 0) return  TYPE_COIN;
                else return TYPE_ADD;
            } else {
                return TYPE_COIN;
            }
        }

        @Override
        public int getItemCount() {
            if(mToSendCoins.size() == 0)
                return 1;
            else
                return 2;
        }


        public class SendCoinHolder extends RecyclerView.ViewHolder {
            ImageView btnRemove;
            EditText etAmountCoin;
            TextView tvSymbolCoin, tvMaxCoin;

            public SendCoinHolder(View v) {
                super(v);
                btnRemove       = itemView.findViewById(R.id.btn_remove_coin);
                etAmountCoin    = itemView.findViewById(R.id.et_amount_coin);
                tvSymbolCoin    = itemView.findViewById(R.id.tv_symbol_coin);
                tvMaxCoin       = itemView.findViewById(R.id.tv_max_coin);
            }
        }


        public class SendAddHolder extends RecyclerView.ViewHolder {
            LinearLayout btnAdd;

            public SendAddHolder(View v) {
                super(v);
                btnAdd = itemView.findViewById(R.id.btnAdd);
            }
        }
    }
}
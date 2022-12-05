package wannabit.io.cosmostaion.fragment.txs.kava;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_ADD_LIQUIDITY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import cosmos.staking.v1beta1.Staking;
import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.LiquidityActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class LiquidityStep0Fragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private KavaValidatorAdapter mAdapter;
    private RelativeLayout mBtnCancel, mBtnNext;

    private BaseChain mBaseChain;

    private ArrayList<Staking.Validator> mValidators = new ArrayList<>();

    public static LiquidityStep0Fragment newInstance() {
        return new LiquidityStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_liquidity_step0, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new KavaValidatorAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mBaseChain = getSActivity().mBaseChain;

        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        onInitData();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            Toast.makeText(getContext(), R.string.str_ok_direct_vote_0, Toast.LENGTH_SHORT).show();
        }
    }

    private void onInitData() {
        mValidators = getBaseDao().mGRpcTopValidators;
        mValidators.sort((o1, o2) -> {
            if (o1.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return -1;
            if (o2.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return 1;
            if (o1.getJailed() && !o2.getJailed()) return 1;
            if (!o1.getJailed() && o2.getJailed()) return -1;
            if (Double.parseDouble(o1.getTokens()) > Double.parseDouble(o2.getTokens())) return -1;
            if (Double.parseDouble(o1.getTokens()) < Double.parseDouble(o2.getTokens())) return 1;
            else return 0;
        });
    }

    private class KavaValidatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new LiquidityValidatorHolder(getLayoutInflater().inflate(R.layout.item_earn_validator, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getSActivity().mTxType == CONST_PW_TX_ADD_LIQUIDITY) {
                onBindValidatorHolder(viewHolder, mValidators.get(position), getSActivity().mKavaEarnDeposits);
            } else {
                onBindDepositedHolder(viewHolder, getSActivity().mKavaEarnDeposits.get(position));
            }
        }

        private void onBindValidatorHolder(RecyclerView.ViewHolder viewHolder, Staking.Validator validator, ArrayList<Coin> mEarnDeposits) {
            final LiquidityValidatorHolder holder = (LiquidityValidatorHolder) viewHolder;
            final ChainConfig chainConfig = ChainFactory.getChain(mBaseChain);
            try {
                Picasso.get().load(WDp.getMonikerImgUrl(chainConfig, validator.getOperatorAddress())).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.mAvatar);
            } catch (Exception e) { }
            holder.mMoniker.setText(validator.getDescription().getMoniker());

            for (Coin coin : getBaseDao().mParam.mParams.mBankSupply.supply) {
                if (coin.denom.equalsIgnoreCase("bkava-" + validator.getOperatorAddress())) {
                    holder.mLiquidityDeposited.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), 6, 6));
                }
            }

            Optional<Coin> mathced = mEarnDeposits.stream().filter(item -> item.denom.contains(validator.getOperatorAddress())).findFirst();
            if (mathced.isPresent()) {
                holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getActivity(), chainConfig.chainBgColor()));
                holder.mMyDeposited.setText(WDp.getDpAmount2(new BigDecimal(mathced.get().amount), 6, 6));
            } else {
                holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorTransBg));
                holder.mMyDeposited.setText(WDp.getDpAmount2(new BigDecimal("0"), 6, 6));
            }

            holder.itemRoot.setOnClickListener(v -> {
                getSActivity().mToValAddress = validator.getOperatorAddress();
                getSActivity().onNextStep();
            });
        }

        private void onBindDepositedHolder(RecyclerView.ViewHolder viewHolder, Coin mKavaEarnDeposit) {
            final LiquidityValidatorHolder holder = (LiquidityValidatorHolder) viewHolder;
            final ChainConfig chainConfig = ChainFactory.getChain(mBaseChain);

            holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getActivity(), chainConfig.chainBgColor()));
            String valOpAddress = mKavaEarnDeposit.denom.replace("bkava-", "");
            Staking.Validator validator = getBaseDao().mGRpcAllValidators.stream().filter(item -> item.getOperatorAddress().equalsIgnoreCase(valOpAddress)).findFirst().get();
            try {
                Picasso.get().load(WDp.getMonikerImgUrl(chainConfig, validator.getOperatorAddress())).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.mAvatar);
            } catch (Exception e) { }
            holder.mMoniker.setText(validator.getDescription().getMoniker());

            Coin totalBKava = getBaseDao().mParam.mParams.mBankSupply.supply.stream().filter(item -> item.denom.equalsIgnoreCase(mKavaEarnDeposit.denom)).findFirst().get();
            holder.mLiquidityDeposited.setText(WDp.getDpAmount2(new BigDecimal(totalBKava.amount), 6, 6));
            holder.mMyDeposited.setText(WDp.getDpAmount2(new BigDecimal(mKavaEarnDeposit.amount), 6, 6));

            holder.itemRoot.setOnClickListener(v -> {
                getSActivity().mToValAddress = validator.getOperatorAddress();
                getSActivity().onNextStep();
            });
        }

        @Override
        public int getItemCount() {
            if (getSActivity().mTxType == CONST_PW_TX_ADD_LIQUIDITY) {
                return mValidators.size();
            } else {
                return getSActivity().mKavaEarnDeposits.size();
            }
        }

        public class LiquidityValidatorHolder extends RecyclerView.ViewHolder {
            private CardView itemRoot;
            private CircleImageView mAvatar;
            private TextView mMoniker;
            private TextView mLiquidityDeposited;
            private TextView mMyDeposited;

            public LiquidityValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                mAvatar = itemView.findViewById(R.id.avatar_validator);
                mMoniker = itemView.findViewById(R.id.moniker_validator);
                mLiquidityDeposited = itemView.findViewById(R.id.liquidity_deposited);
                mMyDeposited = itemView.findViewById(R.id.my_deposited);
            }
        }
    }

    private LiquidityActivity getSActivity() {
        return (LiquidityActivity) getBaseActivity();
    }
}

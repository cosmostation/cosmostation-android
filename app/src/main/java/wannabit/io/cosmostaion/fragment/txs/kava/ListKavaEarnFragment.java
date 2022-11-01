package wannabit.io.cosmostaion.fragment.txs.kava;

import static kava.earn.v1beta1.QueryOuterClass.DepositResponse;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.staking.v1beta1.Staking;
import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.KavaEarnMyDepositGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class ListKavaEarnFragment extends BaseFragment implements TaskListener, View.OnClickListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private KavaEarnListAdapter mAdapter;
    private RelativeLayout mBtnRemoveLiquidity, mBtnAddLiquidity;

    private Account mAccount;
    private BaseChain mBaseChain;

    private ArrayList<Coin> mEarnDeposits = new ArrayList<>();

    public static ListKavaEarnFragment newInstance() {
        return new ListKavaEarnFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_earn_list, container, false);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mBtnRemoveLiquidity = rootView.findViewById(R.id.btn_remove_liquidity);
        mBtnAddLiquidity = rootView.findViewById(R.id.btn_add_liquidity);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getSActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(this::onFetchEarnInfo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new KavaEarnListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAccount = getSActivity().mAccount;
        mBaseChain = getSActivity().mBaseChain;
        onFetchEarnInfo();

        mBtnRemoveLiquidity.setOnClickListener(this);
        mBtnAddLiquidity.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnRemoveLiquidity)) {
            getSActivity().onCheckStartRemoveLiquidity(mEarnDeposits);

        } else if (v.equals(mBtnAddLiquidity)) {
            getSActivity().onCheckStartAddLiquidity(mEarnDeposits);
        }
    }

    private int mTaskCount = 0;
    public void onFetchEarnInfo() {
        mTaskCount = 1;
        mEarnDeposits.clear();
        new KavaEarnMyDepositGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (!isAdded()) return;
        mTaskCount--;
        if (result.isSuccess && result.resultData != null) {
            for (DepositResponse deposit : (List<DepositResponse>) result.resultData) {
                for (CoinOuterClass.Coin rawCoin : deposit.getValueList()) {
                    if (rawCoin.getDenom().startsWith("bkava-")) {
                        mEarnDeposits.add(new Coin(rawCoin.getDenom(), rawCoin.getAmount()));
                    }
                }
            }
        }
        if (mTaskCount == 0) {
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
            mEarnDeposits.sort(new Comparator<Coin>() {
                @Override
                public int compare(Coin o1, Coin o2) {
                    if (o1.denom.equalsIgnoreCase("bkava-kavavaloper140g8fnnl46mlvfhygj3zvjqlku6x0fwu6lgey7")) return -1;
                    if (o2.denom.equalsIgnoreCase("bkava-kavavaloper140g8fnnl46mlvfhygj3zvjqlku6x0fwu6lgey7")) return 1;
                    else return 0;
                }
            });
        }
    }

    private class KavaEarnListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_LIQUIDITY_STATUS = 1;
        private static final int TYPE_LIQUIDITY_VALIDATOR = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_LIQUIDITY_STATUS) {
                return new LiquidityMyStatusHolder(getLayoutInflater().inflate(R.layout.item_earn_my_status, viewGroup, false));
            } else {
                return new LiquidityValidatorHolder(getLayoutInflater().inflate(R.layout.item_earn_validator, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_LIQUIDITY_STATUS) {
                onBindMyLiquitiyStatus(viewHolder, mEarnDeposits);
            } else if (getItemViewType(position) == TYPE_LIQUIDITY_VALIDATOR) {
                onBindValidator(viewHolder, mEarnDeposits, position - 1);
            }
        }

        private void onBindMyLiquitiyStatus(RecyclerView.ViewHolder viewHolder, ArrayList<Coin> mEarnDeposits) {
            final LiquidityMyStatusHolder holder = (LiquidityMyStatusHolder) viewHolder;
            final ChainConfig chainConfig = ChainFactory.getChain(mBaseChain);

            BigDecimal sum = BigDecimal.ZERO;
            for (Coin coin : mEarnDeposits) {
                sum = sum.add(new BigDecimal(coin.amount));
            }
            holder.mLiquidityTotal.setText(WDp.getDpAmount2(sum, 6,6));
            holder.mLiquidityAvailable.setText(WDp.getDpAmount2(getBaseDao().getAvailable(chainConfig.mainDenom()), 6, 6));
        }

        private void onBindValidator(RecyclerView.ViewHolder viewHolder, ArrayList<Coin> mEarnDeposits, int position) {
            final LiquidityValidatorHolder holder = (LiquidityValidatorHolder) viewHolder;
            final ChainConfig chainConfig = ChainFactory.getChain(mBaseChain);
            final Coin deposit = mEarnDeposits.get(position);

            holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getActivity(), chainConfig.chainBgColor()));
            String valOpAddress = deposit.denom.replace("bkava-", "");
            Staking.Validator validator = getBaseDao().mGRpcAllValidators.stream().filter(item -> item.getOperatorAddress().equalsIgnoreCase(valOpAddress)).findFirst().get();
            try {
                Picasso.get().load(chainConfig.monikerUrl() + validator.getOperatorAddress() + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.mAvatar);
            } catch (Exception e) { }
            holder.mMoniker.setText(validator.getDescription().getMoniker());

            Coin totalBKava = getBaseDao().mParam.mParams.mBankSupply.supply.stream().filter(item -> item.denom.equalsIgnoreCase(deposit.denom)).findFirst().get();
            holder.mLiquidityDeposited.setText(WDp.getDpAmount2(new BigDecimal(totalBKava.amount), 6, 6));
            holder.mMyDeposited.setText(WDp.getDpAmount2(new BigDecimal(deposit.amount), 6, 6));
        }

        @Override
        public int getItemCount() {
            if (mEarnDeposits == null || mEarnDeposits.size() < 1) {
                return 1;
            } else {
                return mEarnDeposits.size() + 1;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_LIQUIDITY_STATUS;
            } else {
                return TYPE_LIQUIDITY_VALIDATOR;
            }
        }

        public class LiquidityMyStatusHolder extends RecyclerView.ViewHolder {
            private TextView mLiquidityTotal, mLiquidityAvailable;

            public LiquidityMyStatusHolder(@NonNull View itemView) {
                super(itemView);
                mLiquidityTotal = itemView.findViewById(R.id.liquidity_total);
                mLiquidityAvailable = itemView.findViewById(R.id.liquidity_available);
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

    private DAppsList5Activity getSActivity() {
        return (DAppsList5Activity) getBaseActivity();
    }
}

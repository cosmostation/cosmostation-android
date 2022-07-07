package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_REDELEGATIONS_FROM_TO;

import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.List;

import cosmos.staking.v1beta1.Staking;
import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.RedelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.ReDelegationsFromToGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class RedelegateStep1Fragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private Button mBefore, mNextBtn;
    private RecyclerView mRecyclerView;
    private ToValidatorAdapter mToValidatorAdapter;

    private ArrayList<Staking.Validator> mGRpcTopValidators = new ArrayList<>();
    private Staking.Validator mCheckedGRpcValidator = null;

    public static RedelegateStep1Fragment newInstance(Bundle bundle) {
        RedelegateStep1Fragment fragment = new RedelegateStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGRpcTopValidators = getSActivity().mGRpcTopValidators;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_redelegate_step1, container, false);
        mBefore = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mBefore.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(100);
        mRecyclerView.setDrawingCacheEnabled(true);
        mToValidatorAdapter = new ToValidatorAdapter();
        mRecyclerView.setAdapter(mToValidatorAdapter);
        return rootView;
    }


    private RedelegateActivity getSActivity() {
        return (RedelegateActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (mCheckedGRpcValidator == null) {
                Toast.makeText(getContext(), R.string.error_no_to_validator, Toast.LENGTH_SHORT).show();
            } else {
                new ReDelegationsFromToGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount,
                        getSActivity().mValAddress, mCheckedGRpcValidator.getOperatorAddress()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (!isAdded()) return;
        if (result.taskType == TASK_GRPC_FETCH_REDELEGATIONS_FROM_TO) {
            List<Staking.RedelegationResponse> redelegates = (List<Staking.RedelegationResponse>) result.resultData;
            if (redelegates != null && redelegates.size() > 0 && redelegates.get(0).getEntriesCount() >= 7) {
                Toast.makeText(getContext(), R.string.error_redelegate_cnt_over, Toast.LENGTH_SHORT).show();
                return;

            } else {
                getSActivity().mToValAddress = mCheckedGRpcValidator.getOperatorAddress();
                getSActivity().onNextStep();
            }

        }
    }

    private class ToValidatorAdapter extends RecyclerView.Adapter<ToValidatorAdapter.ToValidatorHolder> {

        @NonNull
        @Override
        public ToValidatorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ToValidatorHolder(getLayoutInflater().inflate(R.layout.item_redelegate_validator, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull final ToValidatorHolder holder, final int position) {
            final Staking.Validator mGrpcValidator = mGRpcTopValidators.get(position);
            holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(mGrpcValidator.getTokens()), WDp.mainDivideDecimal(getSActivity().mBaseChain), 6));
            holder.itemTvYieldRate.setText(WDp.getDpEstAprCommission(getBaseDao(), getSActivity().mBaseChain, new BigDecimal(mGrpcValidator.getCommission().getCommissionRates().getRate()).movePointLeft(18)));
            try {
                Picasso.get().load(WDp.getMonikerImgUrl(getSActivity().mBaseChain, mGrpcValidator.getOperatorAddress())).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
            } catch (Exception e) {
            }

            holder.itemTvMoniker.setText(mGrpcValidator.getDescription().getMoniker());
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCheckedGRpcValidator = mGrpcValidator;
                    notifyDataSetChanged();
                }
            });
            if (mGrpcValidator.getJailed()) {
                holder.itemAvatar.setBorderColor(ContextCompat.getColor(getSActivity(), R.color.colorRed));
                holder.itemRevoked.setVisibility(View.VISIBLE);
            } else {
                holder.itemAvatar.setBorderColor(ContextCompat.getColor(getSActivity(), R.color.colorGray3));
                holder.itemRevoked.setVisibility(View.GONE);
            }
            if (mCheckedGRpcValidator != null && mGrpcValidator.getOperatorAddress().equals(mCheckedGRpcValidator.getOperatorAddress())) {
                holder.itemChecked.setColorFilter(WDp.getChainColor(getContext(), getSActivity().mBaseChain), PorterDuff.Mode.SRC_IN);
                holder.itemCheckedBorder.setVisibility(View.VISIBLE);
            } else {
                holder.itemCheckedBorder.setVisibility(View.GONE);
                holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getSActivity(), R.color.colorTransBg));
            }
        }

        @Override
        public int getItemCount() {
            return mGRpcTopValidators.size();
        }

        public class ToValidatorHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            CircleImageView itemAvatar;
            ImageView itemRevoked;
            ImageView itemFree;
            ImageView itemChecked;
            TextView itemTvMoniker;
            TextView itemTvVotingPower;
            TextView itemTvYieldRate;
            View itemCheckedBorder;

            public ToValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                itemAvatar = itemView.findViewById(R.id.avatar_validator);
                itemRevoked = itemView.findViewById(R.id.avatar_validator_revoke);
                itemFree = itemView.findViewById(R.id.avatar_validator_free);
                itemChecked = itemView.findViewById(R.id.checked_validator);
                itemTvMoniker = itemView.findViewById(R.id.moniker_validator);
                itemTvVotingPower = itemView.findViewById(R.id.delegate_power_validator);
                itemTvYieldRate = itemView.findViewById(R.id.delegate_yield_commission);
                itemCheckedBorder = itemView.findViewById(R.id.check_border);
            }
        }
    }
}

package wannabit.io.cosmostaion.fragment.txs.common;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_REDELEGATIONS_FROM_TO;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.RedelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.databinding.FragmentRedelegateStep1Binding;
import wannabit.io.cosmostaion.databinding.ItemRedelegateValidatorBinding;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.ReDelegationsFromToGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class RedelegateStep1Fragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private FragmentRedelegateStep1Binding fragmentRedelegateStep1Binding;

    private ArrayList<Staking.Validator> mGRpcTopValidators = new ArrayList<>();
    private Staking.Validator mCheckedGRpcValidator = null;

    public static RedelegateStep1Fragment newInstance() {
        return new RedelegateStep1Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGRpcTopValidators = getSActivity().mGRpcTopValidators;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentRedelegateStep1Binding = FragmentRedelegateStep1Binding.inflate(getLayoutInflater());
        fragmentRedelegateStep1Binding.btnBefore.setOnClickListener(this);
        fragmentRedelegateStep1Binding.btnNext.setOnClickListener(this);
        fragmentRedelegateStep1Binding.recycler.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        fragmentRedelegateStep1Binding.recycler.setHasFixedSize(true);
        fragmentRedelegateStep1Binding.recycler.setItemViewCacheSize(100);
        fragmentRedelegateStep1Binding.recycler.setDrawingCacheEnabled(true);
        fragmentRedelegateStep1Binding.recycler.setAdapter(new ToValidatorAdapter());
        return fragmentRedelegateStep1Binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentRedelegateStep1Binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fragmentRedelegateStep1Binding.btnBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(fragmentRedelegateStep1Binding.btnNext)) {
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
            return new ToValidatorHolder(ItemRedelegateValidatorBinding.inflate(getLayoutInflater()));
        }

        @Override
        public void onBindViewHolder(@NonNull final ToValidatorHolder holder, final int position) {
            final Staking.Validator mGrpcValidator = mGRpcTopValidators.get(position);
            final ChainConfig chainConfig = ChainFactory.getChain(getSActivity().mBaseChain);
            holder.redelegateValidatorBinding.delegatePowerValidator.setText(WDp.getDpAmount2(getContext(), new BigDecimal(mGrpcValidator.getTokens()), WDp.getDenomDecimal(getBaseDao(), chainConfig, chainConfig.mainDenom()), 6));
            holder.redelegateValidatorBinding.delegateYieldCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getSActivity().mBaseChain, new BigDecimal(mGrpcValidator.getCommission().getCommissionRates().getRate()).movePointLeft(18)));
            try {
                Picasso.get().load(WDp.getMonikerImgUrl(chainConfig, mGrpcValidator.getOperatorAddress())).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.redelegateValidatorBinding.avatarValidator);
            } catch (Exception e) {
            }

            holder.redelegateValidatorBinding.monikerValidator.setText(mGrpcValidator.getDescription().getMoniker());
            holder.redelegateValidatorBinding.cardValidator.setOnClickListener(v -> {
                mCheckedGRpcValidator = mGrpcValidator;
                notifyDataSetChanged();
            });
            if (mGrpcValidator.getJailed()) {
                holder.redelegateValidatorBinding.avatarValidator.setBorderColor(ContextCompat.getColor(getSActivity(), R.color.colorRed));
                holder.redelegateValidatorBinding.avatarValidatorRevoke.setVisibility(View.VISIBLE);
            } else {
                holder.redelegateValidatorBinding.avatarValidator.setBorderColor(ContextCompat.getColor(getSActivity(), R.color.colorGray3));
                holder.redelegateValidatorBinding.avatarValidatorRevoke.setVisibility(View.GONE);
            }
            holder.redelegateValidatorBinding.checkedValidator.setColorFilter(null);
            if (mCheckedGRpcValidator != null && mGrpcValidator.getOperatorAddress().equals(mCheckedGRpcValidator.getOperatorAddress())) {
                Drawable roundBackground = ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_selected);
                roundBackground = DrawableCompat.wrap(roundBackground);
                DrawableCompat.setTint(roundBackground, ContextCompat.getColor(getSActivity(), chainConfig.chainColor()));
                holder.redelegateValidatorBinding.checkBorder.setBackground(roundBackground);
                holder.redelegateValidatorBinding.checkBorder.setVisibility(View.VISIBLE);
                holder.redelegateValidatorBinding.checkedValidator.setColorFilter(ContextCompat.getColor(getSActivity(), chainConfig.chainColor()), PorterDuff.Mode.SRC_IN);
            } else {
                holder.redelegateValidatorBinding.checkBorder.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return mGRpcTopValidators.size();
        }

        public class ToValidatorHolder extends RecyclerView.ViewHolder {

            private ItemRedelegateValidatorBinding redelegateValidatorBinding;

            public ToValidatorHolder(@NonNull ItemRedelegateValidatorBinding binding) {
                super(binding.getRoot());
                redelegateValidatorBinding = binding;
            }
        }
    }

    private RedelegateActivity getSActivity() {
        return (RedelegateActivity) getBaseActivity();
    }
}

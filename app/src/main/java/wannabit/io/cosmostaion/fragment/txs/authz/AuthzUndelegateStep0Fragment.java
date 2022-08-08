package wannabit.io.cosmostaion.fragment.txs.authz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;

import cosmos.authz.v1beta1.Authz;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzUndelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.widget.MyValidatorHolder;

public class AuthzUndelegateStep0Fragment extends BaseFragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private MyValidatorAdapter mMyValidatorAdapter;

    private Button mCancel, mNextBtn;

    private Authz.Grant mGrant;
    private ArrayList<Staking.DelegationResponse> mGranterDelegations = new ArrayList<>();
    private ArrayList<Staking.UnbondingDelegation> mGranterUndelegations = new ArrayList<>();
    private ArrayList<Distribution.DelegationDelegatorReward> mGranterRewards = new ArrayList<>();
    private ArrayList<Staking.Validator> mMyValidator = new ArrayList<>();

    public static AuthzUndelegateStep0Fragment newInstance() {
        return new AuthzUndelegateStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_undelegate_step0, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mMyValidatorAdapter = new MyValidatorAdapter();
        mRecyclerView.setAdapter(mMyValidatorAdapter);

        mGrant = getSActivity().mGrant;
        mGranterDelegations = getSActivity().mGranterDelegations;
        mGranterUndelegations = getSActivity().mGranterUndelegations;
        mGranterRewards = getSActivity().mGranterRewards;

        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        onUpdateView();
        return rootView;
    }

    private void onUpdateView() {
        mMyValidator.clear();

        for (Staking.Validator validator : getBaseDao().mGRpcAllValidators) {
            boolean mine = false;
            for (Staking.DelegationResponse delegation : mGranterDelegations) {
                if (delegation.getDelegation().getValidatorAddress().equalsIgnoreCase(validator.getOperatorAddress())) {
                    mine = true;
                    break;
                }
            }
            for (Staking.UnbondingDelegation unbonding : mGranterUndelegations) {
                if (unbonding.getValidatorAddress().equalsIgnoreCase(validator.getOperatorAddress())) {
                    mine = true;
                    break;
                }
            }
            if (mine) mMyValidator.add(validator);
        }

        if (mGrant.getAuthorization().getTypeUrl().contains(cosmos.staking.v1beta1.Authz.StakeAuthorization.getDescriptor().getFullName())) {
            try {
                cosmos.staking.v1beta1.Authz.StakeAuthorization stakeAuth = cosmos.staking.v1beta1.Authz.StakeAuthorization.parseFrom(mGrant.getAuthorization().getValue());
                ArrayList<Staking.Validator> filteredValidators = new ArrayList<>();
                if (stakeAuth.getAllowList().getAddressCount() > 0) {
                    for (Staking.Validator validator : getBaseDao().mGRpcAllValidators) {
                        if (stakeAuth.getAllowList().getAddressList().contains(validator.getOperatorAddress())) {
                            filteredValidators.add(validator);
                        }
                    }

                } else if (stakeAuth.getDenyList().getAddressCount() > 0) {
                    for (Staking.Validator validator : getBaseDao().mGRpcAllValidators) {
                        if (!stakeAuth.getDenyList().getAddressList().contains(validator.getOperatorAddress())) {
                            filteredValidators.add(validator);
                        }
                    }
                }

                mMyValidator = filteredValidators;
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
        mMyValidatorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            Toast.makeText(getActivity(), getString(R.string.str_authz_select_msg), Toast.LENGTH_LONG).show();
            return;
        }
    }

    private class MyValidatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new MyValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_my_validator, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            MyValidatorHolder holder = (MyValidatorHolder) viewHolder;
            holder.onBindAuthzValidatorList(getBaseDao(), getSActivity().mChainConfig, mMyValidator.get(position), mGranterDelegations, mGranterUndelegations, mGranterRewards);

            holder.itemView.findViewById(R.id.card_validator).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSActivity().mValAddress = mMyValidator.get(position).getOperatorAddress();
                    getSActivity().onNextStep();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMyValidator.size();
        }
    }

    private AuthzUndelegateActivity getSActivity() {
        return (AuthzUndelegateActivity) getBaseActivity();
    }
}

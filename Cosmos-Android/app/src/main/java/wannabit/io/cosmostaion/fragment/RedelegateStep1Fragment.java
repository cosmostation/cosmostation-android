package wannabit.io.cosmostaion.fragment;

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
import wannabit.io.cosmostaion.model.Validator_V1;
import wannabit.io.cosmostaion.model.type.Redelegate;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResRedelegations_V1;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleAllRedelegateState;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.V1Task.RedelegationFromToTask_V1;
import wannabit.io.cosmostaion.task.gRpcTask.ReDelegationsFromToGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BAND_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.CERTIK_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.SECRET_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_SINGLE_ALL_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_REDELEGATIONS_FROM_TO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_REDELEGATION_FROM_TO;

public class RedelegateStep1Fragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private Button                  mBefore, mNextBtn;
    private RecyclerView            mRecyclerView;
    private ToValidatorAdapter      mToValidatorAdapter;
    private ArrayList<Validator>    mToValidators = new ArrayList<>();
    private Validator               mCheckedValidator = null;

    //roll back
    private ArrayList<Validator_V1>         mToValidators_V1 = new ArrayList<>();
    private Validator_V1                    mCheckedValidator_V1 = null;

    //gRpc
    private ArrayList<Staking.Validator>     mGRpcTopValidators = new ArrayList<>();
    private Staking.Validator                mCheckedGRpcValidator = null;

    public static RedelegateStep1Fragment newInstance(Bundle bundle) {
        RedelegateStep1Fragment fragment = new RedelegateStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToValidators = getSActivity().mToValidators;
        mGRpcTopValidators = getSActivity().mGRpcTopValidators;
        mToValidators_V1 = getSActivity().mToValidators_V1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_redelegate_step1, container, false);
        mBefore                 = rootView.findViewById(R.id.btn_before);
        mNextBtn                = rootView.findViewById(R.id.btn_next);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
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
        return (RedelegateActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(IRIS_MAIN) || getSActivity().mBaseChain.equals(AKASH_MAIN)) {
                if (mCheckedValidator_V1 == null) {

                } else {
                    new RedelegationFromToTask_V1(getBaseApplication(), this, getSActivity().mAccount, getSActivity().mValOpAddress, mCheckedValidator_V1.operator_address)
                            .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                }

            } else  if (getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
                if (mCheckedGRpcValidator == null) {

                } else {
                    new ReDelegationsFromToGrpcTask(getBaseApplication(), this, getSActivity().mBaseChain, getSActivity().mAccount,
                            getSActivity().mValOpAddress, mCheckedGRpcValidator.getOperatorAddress()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

            } else {
                if (mCheckedValidator == null) {
                    Toast.makeText(getContext(), R.string.error_no_to_validator, Toast.LENGTH_SHORT).show();
                } else {
                    new SingleAllRedelegateState(getBaseApplication(), this, getSActivity().mAccount,
                            getSActivity().mFromValidator.operator_address,
                            mCheckedValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        if (result.taskType == TASK_FETCH_SINGLE_ALL_REDELEGATE ) {
            if (result.isSuccess) {
                ArrayList<Redelegate> redelegates = (ArrayList<Redelegate>) result.resultData;
                if(redelegates.size() > 0 && redelegates.get(0) != null && redelegates.get(0).entries.size() >= 7 ) {
                    Toast.makeText(getContext(), R.string.error_redelegate_cnt_over, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    getSActivity().mToValidator = mCheckedValidator;
                    getSActivity().onNextStep();
                }

            } else {
                getSActivity().mToValidator = mCheckedValidator;
                getSActivity().onNextStep();
            }

        } else if (result.taskType == TASK_GRPC_FETCH_REDELEGATIONS_FROM_TO ) {
            List<Staking.RedelegationResponse> redelegates = (List<Staking.RedelegationResponse>)result.resultData;
            if (redelegates != null && redelegates.size() > 0 && redelegates.get(0).getEntriesCount() >= 7 ) {
                Toast.makeText(getContext(), R.string.error_redelegate_cnt_over, Toast.LENGTH_SHORT).show();
                return;

            } else {
                getSActivity().mToValOpAddress = mCheckedGRpcValidator.getOperatorAddress();
                getSActivity().onNextStep();
            }

        } else if (result.taskType == TASK_V1_FETCH_REDELEGATION_FROM_TO ) {
            if (result.isSuccess) {
                ArrayList<ResRedelegations_V1.RedelegationResponses_V1> redelegates = (ArrayList<ResRedelegations_V1.RedelegationResponses_V1>)result.resultData;
                if (redelegates.size() > 0 && redelegates.get(0) != null && redelegates.get(0).entries.size() >= 7 ) {
                    Toast.makeText(getContext(), R.string.error_redelegate_cnt_over, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    getSActivity().mToValidator_V1 = mCheckedValidator_V1;
                    getSActivity().onNextStep();
                }

            } else {
                getSActivity().mToValidator_V1 = mCheckedValidator_V1;
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
            if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(IRIS_MAIN) || getSActivity().mBaseChain.equals(AKASH_MAIN)) {
                final Validator_V1 validator  = mToValidators_V1.get(position);
                holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.tokens), 6, 6));
                holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getSActivity().mBaseChain, validator.getCommission()));
                try {
                    Picasso.get().load(WDp.getMonikerImgUrl(getSActivity().mBaseChain, validator.operator_address)).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                } catch (Exception e){}

                holder.itemTvMoniker.setText(validator.description.moniker);
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCheckedValidator_V1 = validator;
                        notifyDataSetChanged();
                    }
                });
                if(validator.jailed) {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }
                holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGray0), PorterDuff.Mode.SRC_IN);
                if (mCheckedValidator_V1 != null && validator.operator_address.equals(mCheckedValidator_V1.operator_address)) {
                    holder.itemChecked.setColorFilter(WDp.getChainColor(getContext(), getSActivity().mBaseChain), PorterDuff.Mode.SRC_IN);
                    holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
                } else {
                    holder.itemCheckedBorder.setVisibility(View.GONE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
                }

            } else if (getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
                final Staking.Validator mGrpcValidator  = mGRpcTopValidators.get(position);
                holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(mGrpcValidator.getTokens()), 6, 6));
                holder.itemTvCommission.setText(WDp.getDpCommissionGrpcRate(mGrpcValidator));
                try {
                    Picasso.get().load(WDp.getMonikerImgUrl(getSActivity().mBaseChain, mGrpcValidator.getOperatorAddress())).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
                } catch (Exception e){}

                holder.itemTvMoniker.setText(mGrpcValidator.getDescription().getMoniker());
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCheckedGRpcValidator = mGrpcValidator;
                        notifyDataSetChanged();
                    }
                });
                if (mGrpcValidator.getJailed()) {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }
                holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGray0), PorterDuff.Mode.SRC_IN);
                if (mCheckedGRpcValidator != null && mGrpcValidator.getOperatorAddress().equals(mCheckedGRpcValidator.getOperatorAddress())) {
                    holder.itemChecked.setColorFilter(WDp.getChainColor(getContext(), getSActivity().mBaseChain), PorterDuff.Mode.SRC_IN);
                    holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
                } else {
                    holder.itemCheckedBorder.setVisibility(View.GONE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
                }

            } else {
                final Validator validator  = mToValidators.get(position);
                if (getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getSActivity().mBaseChain));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getSActivity().mBaseChain, validator.getCommission()));
                    try {
                        Picasso.get().load(KAVA_VAL_URL+validator.operator_address+".png")
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}

                } else if (getSActivity().mBaseChain.equals(BAND_MAIN)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getSActivity().mBaseChain));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getSActivity().mBaseChain, validator.getCommission()));
                    try {
                        Picasso.get().load(BAND_VAL_URL+validator.operator_address+".png")
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}

                } else if (getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getSActivity().mBaseChain));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getSActivity().mBaseChain, validator.getCommission()));
                    try {
                        Picasso.get().load(IOV_VAL_URL+validator.operator_address+".png")
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}

                } else if (getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getSActivity().mBaseChain));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getSActivity().mBaseChain, validator.getCommission()));
                    try {
                        Picasso.get().load(CERTIK_VAL_URL+validator.operator_address+".png")
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}

                } else if (getSActivity().mBaseChain.equals(SECRET_MAIN)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getSActivity().mBaseChain));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getSActivity().mBaseChain, validator.getCommission()));
                    try {
                        Picasso.get().load(SECRET_VAL_URL+validator.operator_address+".png")
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}

                }
                holder.itemTvMoniker.setText(validator.description.moniker);
                holder.itemFree.setVisibility(View.GONE);
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCheckedValidator = validator;
                        notifyDataSetChanged();
                    }
                });

                if(validator.jailed) {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }

                holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGray0), PorterDuff.Mode.SRC_IN);
                if ((getSActivity().mBaseChain.equals(KAVA_MAIN) || getSActivity().mBaseChain.equals(KAVA_TEST)) && mCheckedValidator != null && validator.operator_address.equals(mCheckedValidator.operator_address)) {
                    holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorKava), PorterDuff.Mode.SRC_IN);
                    holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
                } else if (getSActivity().mBaseChain.equals(BAND_MAIN) && mCheckedValidator != null && validator.operator_address.equals(mCheckedValidator.operator_address)) {
                    holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorBand), PorterDuff.Mode.SRC_IN);
                    holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
                } else if ((getSActivity().mBaseChain.equals(IOV_MAIN) || getSActivity().mBaseChain.equals(IOV_TEST)) && mCheckedValidator != null && validator.operator_address.equals(mCheckedValidator.operator_address)) {
                    holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIov), PorterDuff.Mode.SRC_IN);
                    holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
                } else if ((getSActivity().mBaseChain.equals(CERTIK_MAIN) || getSActivity().mBaseChain.equals(CERTIK_TEST)) && mCheckedValidator != null && validator.operator_address.equals(mCheckedValidator.operator_address)) {
                    holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorCertik), PorterDuff.Mode.SRC_IN);
                    holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
                } else if (getSActivity().mBaseChain.equals(SECRET_MAIN) && mCheckedValidator != null && validator.operator_address.equals(mCheckedValidator.operator_address)) {
                    holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorSecret), PorterDuff.Mode.SRC_IN);
                    holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
                } else {
                    holder.itemCheckedBorder.setVisibility(View.GONE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
                }
            }
        }

        @Override
        public int getItemCount() {
            if (getSActivity().mBaseChain.equals(COSMOS_MAIN) || getSActivity().mBaseChain.equals(IRIS_MAIN) || getSActivity().mBaseChain.equals(AKASH_MAIN)) {
                return mToValidators_V1.size();

            } else if (getSActivity().mBaseChain.equals(COSMOS_TEST) || getSActivity().mBaseChain.equals(IRIS_TEST)) {
                return mGRpcTopValidators.size();

            } else {
                return mToValidators.size();
            }

        }

        public class ToValidatorHolder extends RecyclerView.ViewHolder {
            CardView        itemRoot;
            CircleImageView itemAvatar;
            ImageView       itemRevoked;
            ImageView       itemFree;
            ImageView       itemChecked;
            TextView        itemTvMoniker;
            TextView        itemTvVotingPower;
            TextView        itemTvCommission;
            View            itemCheckedBorder;

            public ToValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_validator);
                itemAvatar          = itemView.findViewById(R.id.avatar_validator);
                itemRevoked         = itemView.findViewById(R.id.avatar_validator_revoke);
                itemFree            = itemView.findViewById(R.id.avatar_validator_free);
                itemChecked         = itemView.findViewById(R.id.checked_validator);
                itemTvMoniker       = itemView.findViewById(R.id.moniker_validator);
                itemTvVotingPower   = itemView.findViewById(R.id.delegate_power_validator);
                itemTvCommission    = itemView.findViewById(R.id.delegate_commission_validator);
                itemCheckedBorder   = itemView.findViewById(R.id.check_border);
            }
        }
    }
}

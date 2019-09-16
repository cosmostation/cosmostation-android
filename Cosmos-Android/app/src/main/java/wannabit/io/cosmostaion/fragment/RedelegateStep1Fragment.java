package wannabit.io.cosmostaion.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.RedelegateActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKeyBaseUser;
import wannabit.io.cosmostaion.network.res.ResLcdIrisRedelegate;
import wannabit.io.cosmostaion.network.res.ResLcdRedelegate;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleAllRedelegateState;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;

public class RedelegateStep1Fragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private Button                  mBefore, mNextBtn;
    private RecyclerView            mRecyclerView;
    private ToValidatorAdapter      mToValidatorAdapter;
    private ArrayList<Validator>    mToValidators = new ArrayList<>();

    private Validator               mCheckedValidator = null;

    public static RedelegateStep1Fragment newInstance(Bundle bundle) {
        RedelegateStep1Fragment fragment = new RedelegateStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToValidators = getSActivity().mToValidators;
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
            if(mCheckedValidator == null) {
                Toast.makeText(getContext(), R.string.error_no_to_validator, Toast.LENGTH_SHORT).show();
            } else {
                if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                    new SingleAllRedelegateState(getBaseApplication(), this, getSActivity().mAccount,
                            getSActivity().mFromValidator.operator_address,
                            mCheckedValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                    if (getSActivity().mIrisRedelegateState != null && getSActivity().mIrisRedelegateState.size() > 0) {
                        for (ResLcdIrisRedelegate state:getSActivity().mIrisRedelegateState) {
                            if (mCheckedValidator.operator_address.equals(state.validator_dst_addr) &&
                                getSActivity().mFromValidator.operator_address.equals(state.validator_src_addr)) {
                                Toast.makeText(getContext(), R.string.error_redelegate_cnt_over, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                    getSActivity().mToValidator = mCheckedValidator;
                    getSActivity().onNextStep();
                }
            }
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_ALL_REDELEGATE ) {
            if(result.isSuccess) {
                ArrayList<ResLcdRedelegate> redelegates = (ArrayList<ResLcdRedelegate>) result.resultData;
                if(redelegates != null && redelegates.get(0) != null && redelegates.get(0).entries.size() >= 7 ) {
                    Toast.makeText(getContext(), R.string.error_redelegate_cnt_over, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    getSActivity().mToValidator = mCheckedValidator;
                    getSActivity().onNextStep();
                }

            } else {
                Toast.makeText(getContext(), R.string.error_network, Toast.LENGTH_SHORT).show();
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
            final Validator validator  = mToValidators.get(position);
            if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getSActivity().mBaseChain));
                if(getSActivity().mBondedToken != null && getSActivity().mProvisions != null) {
                    holder.itemTvCommission.setText(WDp.getYieldString(getSActivity().mBondedToken, getSActivity().mProvisions, new BigDecimal(validator.commission.commission_rates.rate)));
                }

            } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens).movePointRight(18), 6, getSActivity().mBaseChain));
                holder.itemTvCommission.setText(WDp.getIrisYieldString(getSActivity().mIrisPool, new BigDecimal(validator.commission.rate)));
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

            holder.itemAvatar.setTag("imgv" + position);
            if (validator.keybaseInfo == null) {
                holder.itemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.validator_none_img));
                if(!TextUtils.isEmpty(validator.description.identity)) {
                    ApiClient.getKeybaseService(getSActivity()).getUserInfo("pictures", validator.description.identity).enqueue(new Callback<ResKeyBaseUser>() {
                        @Override
                        public void onResponse(Call<ResKeyBaseUser> call, final Response<ResKeyBaseUser> response) {
                            validator.keybaseInfo = response.body();
                            if(isAdded() && holder.itemAvatar.getTag().equals("imgv" + position)) {
                                try {
                                    Picasso.get()
                                            .load(response.body().getUrl())
                                            .fit()
                                            .placeholder(R.drawable.validator_none_img)
                                            .into(holder.itemAvatar);
                                }catch (Exception e) {}
                            }
                        }
                        @Override
                        public void onFailure(Call<ResKeyBaseUser> call, Throwable t) {}
                    });
                }

            } else {
                if(isAdded() && holder.itemAvatar.getTag().equals("imgv" + position)) {
                    try {
                        Picasso.get()
                                .load(validator.keybaseInfo.getUrl())
                                .fit()
                                .placeholder(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    }catch (Exception e) {}
                }

            }

            if(validator.jailed) {
                holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                holder.itemRevoked.setVisibility(View.VISIBLE);
            } else {
                holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                holder.itemRevoked.setVisibility(View.GONE);
            }

            holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
            if (getSActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN) && mCheckedValidator != null && validator.operator_address.equals(mCheckedValidator.operator_address)) {
                holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAtom), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
            } else if (getSActivity().mBaseChain.equals(BaseChain.IRIS_MAIN) && mCheckedValidator != null && validator.operator_address.equals(mCheckedValidator.operator_address)) {
                holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIris), android.graphics.PorterDuff.Mode.SRC_IN);
                holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
            } else {
                holder.itemCheckedBorder.setVisibility(View.GONE);
                holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            }
        }

        @Override
        public int getItemCount() {
            return mToValidators.size();
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

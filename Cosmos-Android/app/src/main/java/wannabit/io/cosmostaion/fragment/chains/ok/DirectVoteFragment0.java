package wannabit.io.cosmostaion.fragment.chains.ok;

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

import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.ok.OKVoteDirectActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;

public class DirectVoteFragment0 extends BaseFragment implements View.OnClickListener {

    private Button                  mBefore, mNextBtn;
    private RecyclerView            mRecyclerView;
    private TextView                mSelectedValidatorCnt;
    private ToValidatorAdapter      mToValidatorAdapter;

    public static DirectVoteFragment0 newInstance(Bundle bundle) {
        DirectVoteFragment0 fragment = new DirectVoteFragment0();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_direct_vote_0, container, false);
        mBefore                 = rootView.findViewById(R.id.btn_before);
        mNextBtn                = rootView.findViewById(R.id.btn_next);
        mSelectedValidatorCnt   = rootView.findViewById(R.id.selected_cnt);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mBefore.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(100);
        mRecyclerView.setDrawingCacheEnabled(true);
        mToValidatorAdapter = new ToValidatorAdapter();
        mRecyclerView.setAdapter(mToValidatorAdapter);

        onUpdateCnt();
        return rootView;
    }

    private void onUpdateCnt() {
        mSelectedValidatorCnt.setText(""+getSActivity().mValAddesses.size());
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private OKVoteDirectActivity getSActivity() {
        return (OKVoteDirectActivity)getBaseActivity();
    }


    private class ToValidatorAdapter extends RecyclerView.Adapter<ToValidatorAdapter.ToValidatorHolder> {

        @NonNull
        @Override
        public ToValidatorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ToValidatorHolder(getLayoutInflater().inflate(R.layout.item_redelegate_validator, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull ToValidatorHolder holder, int position) {
            final Validator validator  = getSActivity().mAllValidators.get(position);
            if (getSActivity().mBaseChain.equals(OK_TEST)) {
                holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.delegator_shares), 0, 8));
                holder.itemTvCommission.setText(WDp.getCommissionRate(validator.commission.commission_rates.rate));

                String imgUrl = validator.description.identity;
                if (!TextUtils.isEmpty(imgUrl) && imgUrl.startsWith("logo|||")) {
                    imgUrl = imgUrl.replace("logo|||" , "");
                    try {
                        Picasso.get().load(imgUrl)
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}
                } else {
                    holder.itemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.validator_none_img));
                }

                holder.itemTvMoniker.setText(validator.description.moniker);
                holder.itemFree.setVisibility(View.GONE);

                if(validator.jailed) {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }

                holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
                if (getSActivity().mValAddesses.contains(validator.operator_address)) {
                    holder.itemChecked.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorOK), android.graphics.PorterDuff.Mode.SRC_IN);
                    holder.itemCheckedBorder.setVisibility(View.VISIBLE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTrans));
                } else {
                    holder.itemCheckedBorder.setVisibility(View.GONE);
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
                }

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getSActivity().mValAddesses.contains(validator.operator_address)) {
                            if (getSActivity().mValAddesses.size() == 1) {
                                Toast.makeText(getContext(), R.string.error_min_1_validator, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            getSActivity().mValAddesses.remove(validator.operator_address);
                        } else {
                            if (getSActivity().mValAddesses.size() > 29) {
                                Toast.makeText(getContext(), R.string.error_max_30_validator, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            getSActivity().mValAddesses.add(validator.operator_address);
                        }
                        onUpdateCnt();
                        notifyItemChanged(position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return getSActivity().mAllValidators.size();
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

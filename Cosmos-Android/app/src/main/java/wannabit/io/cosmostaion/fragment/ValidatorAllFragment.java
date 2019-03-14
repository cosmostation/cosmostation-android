package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_ValidatorSorting;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKeyBaseUser;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class ValidatorAllFragment extends BaseFragment {

    public final static int SELECT_SORTING = 6002;

    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;
    private AllValidatorAdapter     mAllValidatorAdapter;
    private TextView                mSorting;

    private ArrayList<Validator>        mMyValidators = new ArrayList<>();
    private ArrayList<Validator>        mAllValidators = new ArrayList<>();

    public static ValidatorAllFragment newInstance(Bundle bundle) {
        ValidatorAllFragment fragment = new ValidatorAllFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_validator_all, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mSorting                = rootView.findViewById(R.id.sorting);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!getMainActivity().onFetchAccountInfo()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAllValidatorAdapter = new AllValidatorAdapter();
        mRecyclerView.setAdapter(mAllValidatorAdapter);

        mSorting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WLog.w("sorting");
                Dialog_ValidatorSorting bottomSheetDialog = Dialog_ValidatorSorting.getInstance();
                bottomSheetDialog.setArguments(null);
                bottomSheetDialog.setTargetFragment(ValidatorAllFragment.this, SELECT_SORTING);
                bottomSheetDialog.show(getFragmentManager(), "dialog");
            }
        });

        return rootView;
    }


    @Override
    public void onRefreshTab() {
        WLog.w("ValidatorAllFragment onRefreshTab");
        if(!isAdded()) return;
        mAllValidators  = getMainActivity().mAllValidators;
        mMyValidators   = getMainActivity().mMyValidators;
//        OnSortByName(mAllValidators);
//        onSortingByAmount(mAllValidators);
//        onSortingByCommission(mAllValidators);
        onSortValidator();

        mAllValidatorAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
        WLog.w("ValidatorAllFragment mAllValidators " + mAllValidators.size());
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_SORTING && resultCode == Activity.RESULT_OK) {
            getBaseDao().setValSorting(data.getIntExtra("sorting", 1));
            onSortValidator();
            mAllValidatorAdapter.notifyDataSetChanged();
        }
    }


    private class AllValidatorAdapter extends RecyclerView.Adapter<AllValidatorAdapter.AllValidatorHolder> {


        @NonNull
        @Override
        public AllValidatorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AllValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_validator, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull final AllValidatorHolder holder, int position) {
            final Validator validator  = mAllValidators.get(position);
            holder.itemTvMoniker.setText(validator.description.moniker);
            holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
            holder.itemTvCommission.setText(WDp.getCommissionRate(validator.commission.rate));
            holder.itemTvCommission.setTextColor(getResources().getColor(WDp.getCommisionColor(validator.commission.rate)));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getBaseDao().setValidator(validator);
                    getMainActivity().onStartValidatorDetail(validator);
                }
            });
            holder.itemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.validator_none_img));
            if(!TextUtils.isEmpty(validator.description.identity)) {
                ApiClient.getKeybaseService(getMainActivity()).getUserInfo("pictures", validator.description.identity).enqueue(new Callback<ResKeyBaseUser>() {
                    @Override
                    public void onResponse(Call<ResKeyBaseUser> call, final Response<ResKeyBaseUser> response) {
                        if(isAdded()) {
                            try {
                                Picasso.get()
                                        .load(response.body().getUrl())
                                        .placeholder(R.drawable.validator_none_img)
                                        .into(holder.itemAvatar);
                            }catch (Exception e) {}

                        }
                    }
                    @Override
                    public void onFailure(Call<ResKeyBaseUser> call, Throwable t) {}
                });
            }
            if(validator.jailed) {
                holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                holder.itemRevoked.setVisibility(View.VISIBLE);
            } else {
                holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                holder.itemRevoked.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return mAllValidators.size();
        }

        public class AllValidatorHolder extends RecyclerView.ViewHolder {
            CardView        itemRoot;
            CircleImageView itemAvatar;
            ImageView       itemRevoked;
            TextView        itemTvMoniker;
            TextView        itemTvVotingPower;
            TextView        itemTvCommission;

            public AllValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_validator);
                itemAvatar          = itemView.findViewById(R.id.avatar_validator);
                itemRevoked         = itemView.findViewById(R.id.avatar_validator_revoke);
                itemTvMoniker       = itemView.findViewById(R.id.moniker_validator);
                itemTvVotingPower = itemView.findViewById(R.id.delegate_power_validator);
                itemTvCommission    = itemView.findViewById(R.id.delegate_commission_validator);
            }
        }
    }


    public void onSortValidator() {
        if(getBaseDao().getValSorting() == 2){
            onSortingByCommission(mAllValidators);
            mSorting.setText(getString(R.string.str_sorting_by_commission));
        } else if (getBaseDao().getValSorting() == 0){
            onSortByName(mAllValidators);
            mSorting.setText(getString(R.string.str_sorting_by_name));
        } else {
            onSortingByAmount(mAllValidators);
            mSorting.setText(getString(R.string.str_sorting_by_power));
        }
    }

    public void onSortByName(ArrayList<Validator> validators) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equals("Cosmostation")) return -1;
                if(o2.description.moniker.equals("Cosmostation")) return 1;
                return o1.description.moniker.compareTo(o2.description.moniker);
            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }

    public void onSortingByAmount(ArrayList<Validator> validators) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equals("Cosmostation")) return -1;
                if(o2.description.moniker.equals("Cosmostation")) return 1;

                if (Long.parseLong(o1.tokens) > Long.parseLong(o2.tokens)) return -1;
                else if (Long.parseLong(o1.tokens) < Long.parseLong(o2.tokens)) return 1;
                else return 0;
            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }

    public void onSortingByCommission(ArrayList<Validator> validators) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equals("Cosmostation")) return -1;
                if(o2.description.moniker.equals("Cosmostation")) return 1;

                if (Float.parseFloat(o1.commission.rate) > Float.parseFloat(o2.commission.rate)) return 1;
                else if (Float.parseFloat(o1.commission.rate) < Float.parseFloat(o2.commission.rate)) return -1;
                else return 0;
            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }
}
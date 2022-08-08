package wannabit.io.cosmostaion.fragment.txs.authz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Sets;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.Set;

import cosmos.authz.v1beta1.Authz;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzDelegateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.widget.AllValidatorHolder;
import wannabit.io.cosmostaion.widget.MyValidatorHolder;

public class AuthzDelegateStep0Fragment extends BaseFragment implements View.OnClickListener {
    private static final int SECTION_MY_VALIDATOR = 1;
    private static final int SECTION_ALL_VALIDATOR = 2;

    private RecyclerView mRecyclerView;
    private ValidatorAdapter mValidatorAdapter;
    private RecyclerViewHeader mRecyclerViewHeader;

    private int mSection;

    private Button mCancel, mNextBtn;

    private Authz.Grant mGrant;
    private ArrayList<Staking.DelegationResponse> mGranterDelegations = new ArrayList<>();
    private ArrayList<Staking.UnbondingDelegation> mGranterUndelegations = new ArrayList<>();
    private ArrayList<Distribution.DelegationDelegatorReward> mGranterRewards = new ArrayList<>();

    private ArrayList<Staking.Validator> mMyValidator = new ArrayList<>();
    private ArrayList<Staking.Validator> mOtherValidators = new ArrayList<>();

    public static AuthzDelegateStep0Fragment newInstance() {
        return new AuthzDelegateStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_authz_delegate_step0, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mValidatorAdapter = new ValidatorAdapter();
        mRecyclerView.setAdapter(mValidatorAdapter);

        mRecyclerViewHeader = new RecyclerViewHeader(getActivity(), true, getSectionCall());
        mRecyclerView.addItemDecoration(mRecyclerViewHeader);

        mGrant = getSActivity().mGrant;
        mGranterDelegations = getSActivity().mGranterDelegations;
        mGranterUndelegations = getSActivity().mGranterUndelegations;
        mGranterRewards = getSActivity().mGranterRewards;

        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        onUpdateView();
        return rootView;
    }

    public void onUpdateView() {
        mMyValidator.clear();
        mOtherValidators.clear();

        if (mGrant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
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
                else mOtherValidators.add(validator);
            }

        } else if (mGrant.getAuthorization().getTypeUrl().contains(cosmos.staking.v1beta1.Authz.StakeAuthorization.getDescriptor().getFullName())) {
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

                for (Staking.Validator validator : filteredValidators) {
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
                    else mOtherValidators.add(validator);
                }
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
        mValidatorAdapter.notifyDataSetChanged();
    }

    private SectionCallback getSectionCall() {
        return new SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0 || position == mMyValidator.size() || position == mMyValidator.size() + mOtherValidators.size();
            }

            @Override
            public String getSectionValidatorHeader(ArrayList<Staking.Validator> validators, int section) {
                if (section == SECTION_MY_VALIDATOR) {
                    return getString(R.string.str_authz_myvalidator_title);
                } else if (section == SECTION_ALL_VALIDATOR) {
                    return getString(R.string.str_authz_othervalidator_title);
                }
                return null;
            }
        };
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

    private class ValidatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == SECTION_MY_VALIDATOR) {
                return new MyValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_my_validator, viewGroup, false));
            } else {
                return new AllValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_validator, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
            if (getItemViewType(position) == SECTION_MY_VALIDATOR) {
                MyValidatorHolder holder = (MyValidatorHolder) viewHolder;
                holder.onBindAuthzValidatorList(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mMyValidator.get(position), mGranterDelegations, mGranterUndelegations, mGranterRewards);
            } else {
                AllValidatorHolder holder = (AllValidatorHolder) viewHolder;
                holder.onBindAuthzAllValidatorList(getSActivity(), getBaseDao(), getSActivity().mChainConfig, mOtherValidators.get(position - mMyValidator.size()));
            }
        }

        @Override
        public int getItemCount() {
            return mMyValidator.size() + mOtherValidators.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mMyValidator.size()) {
                return SECTION_MY_VALIDATOR;
            } else if (position < (mMyValidator.size() + mOtherValidators.size())) {
                return SECTION_ALL_VALIDATOR;
            }
            return -1;
        }
    }

    private AuthzDelegateActivity getSActivity() {
        return (AuthzDelegateActivity) getBaseActivity();
    }

    // Section Header
    public class RecyclerViewHeader extends RecyclerView.ItemDecoration {
        private final int topPadding;

        private final boolean sticky;
        private final SectionCallback sectionCallback;

        private View headerView;
        private TextView mHeaderTitle;
        private TextView mItemCnt;

        public RecyclerViewHeader(Context context, boolean sticky, @NonNull SectionCallback sectionCallback) {
            this.sticky = sticky;
            this.sectionCallback = sectionCallback;

            topPadding = dpToPx(context, 40);
        }

        // dp -> pixel 단위로 변경
        private int dpToPx(Context context, int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            if (headerView == null) {
                headerView = inflateHeaderView(parent);
                mHeaderTitle = (TextView) headerView.findViewById(R.id.header_title);
                mItemCnt = (TextView) headerView.findViewById(R.id.recycler_cnt);
                fixLayoutSize(headerView, parent);
            }

            Set<String> headerTitleSet = Sets.newHashSet();
            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                final int position = parent.getChildAdapterPosition(child);
                if (position == RecyclerView.NO_POSITION) {
                    return;
                }

                String title = "";
                mSection = parent.getAdapter().getItemViewType(position);
                if (mSection == SECTION_MY_VALIDATOR) {
                    title = sectionCallback.getSectionValidatorHeader(mMyValidator, mSection);
                    mItemCnt.setText("" + mMyValidator.size());
                } else if (mSection == SECTION_ALL_VALIDATOR) {
                    title = sectionCallback.getSectionValidatorHeader(mOtherValidators, mSection);
                    mItemCnt.setText("" + mOtherValidators.size());
                }
                mHeaderTitle.setText(title);
                if (!headerTitleSet.contains(title) || sectionCallback.isSection(position)) {
                    drawHeader(c, child, headerView);
                    headerTitleSet.add(title);
                }
            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);
            if (sectionCallback.isSection(position)) {
                outRect.top = topPadding;
            }
        }

        private void drawHeader(Canvas c, View child, View headerView) {
            c.save();
            if (sticky) {
                c.translate(0, Math.max(0, child.getTop() - headerView.getHeight()));
            } else {
                c.translate(0, child.getTop() - headerView.getHeight());
            }
            headerView.draw(c);
            c.restore();
        }

        private View inflateHeaderView(RecyclerView parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticky_header, parent, false);
        }

        private void fixLayoutSize(View view, ViewGroup parent) {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
                    View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(),
                    View.MeasureSpec.UNSPECIFIED);

            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    parent.getPaddingLeft() + parent.getPaddingRight(),
                    view.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(),
                    view.getLayoutParams().height);

            view.measure(childWidth, childHeight);

            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    public interface SectionCallback {
        boolean isSection(int position);
        String getSectionValidatorHeader(ArrayList<Staking.Validator> validators, int section);
    }
}

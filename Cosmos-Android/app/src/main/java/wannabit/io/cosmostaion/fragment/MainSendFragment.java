package wannabit.io.cosmostaion.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.GuideListActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.test.TestAdapter;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;


public class MainSendFragment extends BaseFragment implements View.OnClickListener {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private NestedScrollView    mNestedScrollView;

    private ImageView           mBtnWebDetail, mBtnAddressDetail;
    private ImageView           mKeyState;
    private TextView            mAddress;
    private TextView            mTvAtomTotal, mTvAtomUndelegated,
                                mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;
    private TextView            mTvPhotonTotal, mTvPhotonBalance, mTvPhotonRewards;
    private TextView            mTvAtomTitle, mTvPhotonTitle;

    private TextView            mAtomPrice;

    private CardView            mAtomCard, mPhotonCard, mPriceCard;

    private TextView            mAtomPerPrice, mAtomUpDownPrice;
    private ImageView           matomUpDownImg;

    private Button              mGuideBtn, mFaqBtn;



    public static MainSendFragment newInstance(Bundle bundle) {
        MainSendFragment fragment = new MainSendFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_send, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mNestedScrollView       = rootView.findViewById(R.id.layer_scrollview);
        mBtnWebDetail           = rootView.findViewById(R.id.web_detail);
        mBtnAddressDetail       = rootView.findViewById(R.id.address_detail);
        mKeyState               = rootView.findViewById(R.id.img_account);
        mAddress                = rootView.findViewById(R.id.account_Address);
        mTvAtomTotal            = rootView.findViewById(R.id.dash_atom_amount);
        mTvAtomUndelegated      = rootView.findViewById(R.id.dash_atom_undelegate);
        mTvAtomDelegated        = rootView.findViewById(R.id.dash_atom_delegate);
        mTvAtomUnBonding        = rootView.findViewById(R.id.dash_atom_unbonding);
        mTvAtomRewards          = rootView.findViewById(R.id.dash_atom_reward);
        mTvPhotonTotal          = rootView.findViewById(R.id.dash_photon_amount);
        mTvPhotonBalance        = rootView.findViewById(R.id.dash_photon_balance);
        mTvPhotonRewards        = rootView.findViewById(R.id.dash_photon_reward);
        mTvAtomTitle            = rootView.findViewById(R.id.dash_atom_title);
        mTvPhotonTitle          = rootView.findViewById(R.id.dash_photon_title);
        mAtomCard               = rootView.findViewById(R.id.card_atom);
        mPhotonCard             = rootView.findViewById(R.id.card_photon);
        mAtomPrice              = rootView.findViewById(R.id.dash_atom_price);

        mPriceCard              = rootView.findViewById(R.id.card_price);
        mAtomPerPrice           = rootView.findViewById(R.id.dash_atom_per_price);
        mAtomUpDownPrice        = rootView.findViewById(R.id.dash_atom_price_updown_tx);
        matomUpDownImg          = rootView.findViewById(R.id.ic_price_updown);


        mGuideBtn               = rootView.findViewById(R.id.btn_guide);
        mFaqBtn                 = rootView.findViewById(R.id.btn_faq);


        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!getMainActivity().onFetchAccountInfo()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        mNestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    if (getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.hide();
                    }
                }
                if (scrollY < oldScrollY) {
                    if (!getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.show();
                    }
                }
            }
        });

        mBtnWebDetail.setOnClickListener(this);
        mBtnAddressDetail.setOnClickListener(this);
        mGuideBtn.setOnClickListener(this);
        mFaqBtn.setOnClickListener(this);
        onUpdateView();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_accounts :
                getMainActivity().onShowTopAccountsView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
        onUpdateView();
    }

    private void onUpdateView() {
        if(getMainActivity() == null || getMainActivity().mAccount == null) return;


        mTvAtomTitle.setText(WDp.DpAtom(getContext(), getMainActivity().mAccount.baseChain));
        mTvPhotonTitle.setText(WDp.DpPoton(getContext(), getMainActivity().mAccount.baseChain));

        mAddress.setText(getMainActivity().mAccount.address);

        if(getMainActivity().mAccount.hasPrivateKey) {
            mKeyState.setImageDrawable(getResources().getDrawable(R.drawable.key_on));
        } else {
            mKeyState.setImageDrawable(getResources().getDrawable(R.drawable.key_off));
        }

        mTvAtomTotal.setText(WDp.getDpAllAtom(getContext(), getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mRewards, getMainActivity().mTopValidators, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvAtomUndelegated.setText(WDp.getDpAtomBalance(getContext(), getMainActivity().mBalances, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvAtomDelegated.setText(WDp.getDpAllDelegatedAmount(getContext(), getMainActivity().mBondings, getMainActivity().mAllValidators, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvAtomUnBonding.setText(WDp.getDpAllUnbondingAmount(getContext(), getMainActivity().mUnbondings, getMainActivity().mAllValidators, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvAtomRewards.setText(WDp.getDpAllAtomRewardAmount(getContext(), getMainActivity().mRewards, BaseChain.getChain(getMainActivity().mAccount.baseChain)));

        mTvPhotonTotal.setText(WDp.getDpAllPhoton(getContext(), getMainActivity().mBalances, getMainActivity().mRewards, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvPhotonBalance.setText(WDp.getDpPhotonBalance(getContext(), getMainActivity().mBalances, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvPhotonRewards.setText(WDp.getDpAllPhotonRewardAmount(getContext(), getMainActivity().mRewards, BaseChain.getChain(getMainActivity().mAccount.baseChain)));

        mPhotonCard.setVisibility(View.GONE);
        mAtomPrice.setVisibility(View.VISIBLE);
        mPriceCard.setVisibility(View.VISIBLE);

        try {
            BigDecimal totalAmount = WDp.getAllAtom(getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mRewards, getMainActivity().mAllValidators);
            BigDecimal totalPrice = totalAmount.multiply(new BigDecimal(""+getBaseDao().getLastAtomTic())).divide(new BigDecimal("1000000")).setScale(2, RoundingMode.DOWN);
            mAtomPrice.setText("$ " +  WDp.getDolor(getContext(), totalPrice));
            mAtomPerPrice.setText("$ " + new BigDecimal(""+getBaseDao().getLastAtomTic()).setScale(2, RoundingMode.HALF_UP));
            mAtomUpDownPrice.setText("" + new BigDecimal(""+getBaseDao().getLastAtomUpDown()).setScale(2, RoundingMode.HALF_UP) + "% (24h)");
            if(getBaseDao().getLastAtomUpDown() > 0) {
                matomUpDownImg.setVisibility(View.VISIBLE);
                matomUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
            } else if (getBaseDao().getLastAtomUpDown() < 0){
                matomUpDownImg.setVisibility(View.VISIBLE);
                matomUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
            } else {
                matomUpDownImg.setVisibility(View.GONE);
            }


        } catch (Exception e) {
            mAtomPrice.setText("???");
            mAtomPerPrice.setText("???");
            mAtomUpDownPrice.setText("???");
            matomUpDownImg.setVisibility(View.GONE);
        }
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBtnAddressDetail)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", getMainActivity().mAccount.address);
            if(TextUtils.isEmpty(getMainActivity().mAccount.nickName)) bundle.putString("title", getString(R.string.str_my_wallet) + getMainActivity().mAccount.id);
            else bundle.putString("title", getMainActivity().mAccount.nickName);
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            show.show(getFragmentManager().beginTransaction(), "dialog");

        } else if (v.equals(mBtnWebDetail)) {
            Intent webintent = new Intent(getMainActivity(), WebActivity.class);
            webintent.putExtra("address", getMainActivity().mAccount.address);
            webintent.putExtra("goMain", false);
            startActivity(webintent);

        } else if (v.equals(mGuideBtn)) {
            if(Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.cosmostation.io/files/guide_KO.pdf"));
                startActivity(guideIntent);
            } else {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.cosmostation.io/files/guide_EN.pdf"));
                startActivity(guideIntent);
            }

        } else if (v.equals(mFaqBtn)) {
            startActivity(new Intent(getBaseActivity(), GuideListActivity.class));
        }
    }
}


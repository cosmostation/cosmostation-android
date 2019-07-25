package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AccountListActivity;
import wannabit.io.cosmostaion.activities.AppLockSetActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Currency_Set;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResCmcTic;
import wannabit.io.cosmostaion.utils.WUtil;

public class MainSettingFragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_CURRENCY = 9034;

    private FrameLayout mBtnWallet, mBtnAlaram, mBtnAppLock, mBtnCurrency, mBtnBasePrice,
                        mBtnGuide, mBtnTelegram, mBtnExplore, mBtnHomepage,
                        mBtnTerm, mBtnGithub, mBtnVersion;

    private TextView    mTvAppLock, mTvCurrency, mTvBasePrice, mTvVersion;

    public static MainSettingFragment newInstance(Bundle bundle) {
        MainSettingFragment fragment = new MainSettingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_setting, container, false);
        mBtnWallet = rootView.findViewById(R.id.card_wallet);
        mBtnAlaram = rootView.findViewById(R.id.card_alaram);
        mBtnAppLock = rootView.findViewById(R.id.card_applock);
        mBtnCurrency = rootView.findViewById(R.id.card_currency);
        mBtnBasePrice = rootView.findViewById(R.id.card_base_price);
        mBtnGuide = rootView.findViewById(R.id.card_guide);
        mBtnTelegram = rootView.findViewById(R.id.card_telegram);
        mBtnExplore = rootView.findViewById(R.id.card_explore);
        mBtnHomepage = rootView.findViewById(R.id.card_homepage);
        mBtnTerm = rootView.findViewById(R.id.card_term);
        mBtnGithub = rootView.findViewById(R.id.card_github);
        mBtnVersion = rootView.findViewById(R.id.card_version);
        mTvAppLock = rootView.findViewById(R.id.applock_text);
        mTvCurrency = rootView.findViewById(R.id.currency_text);
        mTvBasePrice = rootView.findViewById(R.id.base_price_text);
        mTvVersion = rootView.findViewById(R.id.version_text);
        mBtnWallet.setOnClickListener(this);
        mBtnAlaram.setOnClickListener(this);
        mBtnAppLock.setOnClickListener(this);
        mBtnCurrency.setOnClickListener(this);
        mBtnBasePrice.setOnClickListener(this);
        mBtnGuide.setOnClickListener(this);
        mBtnTelegram.setOnClickListener(this);
        mBtnExplore.setOnClickListener(this);
        mBtnHomepage.setOnClickListener(this);
        mBtnTerm.setOnClickListener(this);
        mBtnGithub.setOnClickListener(this);
        mBtnVersion.setOnClickListener(this);

        mTvVersion.setText("v" + BuildConfig.VERSION_NAME);

        mBtnAlaram.setVisibility(View.GONE);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mTvCurrency.setText(getBaseDao().getCurrencyString());
        if(getBaseDao().getUsingAppLock()) {
            mTvAppLock.setText(R.string.str_app_applock_enabled);
        } else {
            mTvAppLock.setText(R.string.str_app_applock_diabeld);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnWallet)) {
            startActivity(new Intent(getBaseActivity(), AccountListActivity.class));

        } else if (v.equals(mBtnAlaram)) {
            Toast.makeText(getBaseActivity(), R.string.str_preparing, Toast.LENGTH_SHORT).show();

        } else if (v.equals(mBtnAppLock)) {
            startActivity(new Intent(getBaseActivity(), AppLockSetActivity.class));

        } else if (v.equals(mBtnCurrency)) {
            Dialog_Currency_Set cyrrency_dialog = Dialog_Currency_Set.newInstance(null);
            cyrrency_dialog.setCancelable(true);
            cyrrency_dialog.setTargetFragment(this, SELECT_CURRENCY);
            getFragmentManager().beginTransaction().add(cyrrency_dialog, "dialog").commitNowAllowingStateLoss();
            return;

        } else if (v.equals(mBtnBasePrice)) {
            return;

        } else if (v.equals(mBtnGuide)) {
            if(Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://guide.cosmostation.io/app_wallet_ko.html"));
                startActivity(guideIntent);
            } else {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://guide.cosmostation.io/app_wallet_en.html"));
                startActivity(guideIntent);
            }

        } else if (v.equals(mBtnTelegram)) {
            Intent telegram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://t.me/cosmostation"));
            startActivity(telegram);

        } else if (v.equals(mBtnExplore)) {
            if (getMainActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mintscan.io/"));
                startActivity(intent);
            } else if (getMainActivity().mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://irishub.mintscan.io/"));
                startActivity(intent);
            }


        } else if (v.equals(mBtnHomepage)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/"));
            startActivity(intent);

        } else if (v.equals(mBtnTerm)) {
            if(Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/service_ko.html"));
                startActivity(intent);
            }  else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/service_en.html"));
                startActivity(intent);
            }

        } else if (v.equals(mBtnGithub)) {
            Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://github.com/cosmostation/cosmostation-mobile"));
            startActivity(intent);

        } else if (v.equals(mBtnVersion)) {

        }

    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_CURRENCY && resultCode == Activity.RESULT_OK) {
            getBaseDao().setCurrency(data.getIntExtra("currency", 0));
            mTvCurrency.setText(getBaseDao().getCurrencyString());
            onPriceTic(BaseChain.getChain(getMainActivity().mAccount.baseChain));
        }
    }

    private void onPriceTic(final BaseChain chain) {
        ApiClient.getCMCClient(getMainActivity()).getPriceTic(WUtil.getCMCId(chain), getBaseDao().getCurrencyString()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(!isAdded()) return;
                try {
                    if(response.isSuccessful() && chain.equals(BaseChain.COSMOS_MAIN)) {
                        ResCmcTic mResCmcTic = new Gson().fromJson(response.body(), ResCmcTic.class);
                        getBaseDao().setLastAtomTic(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPrice());
                        getBaseDao().setLastAtomUpDown(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPercent_change_24h());

                    } else if (response.isSuccessful() && chain.equals(BaseChain.IRIS_MAIN)) {
                        ResCmcTic mResCmcTic = new Gson().fromJson(response.body(), ResCmcTic.class);
                        getBaseDao().setLastIrisTic(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPrice());
                        getBaseDao().setLastIrisUpDown(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPercent_change_24h());

                    }

                } catch (Exception e) {
                    if (chain.equals(BaseChain.COSMOS_MAIN)) {
                        getBaseDao().setLastAtomTic(0d);
                        getBaseDao().setLastAtomUpDown(0d);

                    } else if (chain.equals(BaseChain.IRIS_MAIN)) {
                        getBaseDao().setLastIrisTic(0d);
                        getBaseDao().setLastIrisUpDown(0d);

                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (chain.equals(BaseChain.COSMOS_MAIN)) {
                    getBaseDao().setLastAtomTic(0d);
                    getBaseDao().setLastAtomUpDown(0d);

                } else if (chain.equals(BaseChain.IRIS_MAIN)) {
                    getBaseDao().setLastIrisTic(0d);
                    getBaseDao().setLastIrisUpDown(0d);

                }

            }
        });
    }
}

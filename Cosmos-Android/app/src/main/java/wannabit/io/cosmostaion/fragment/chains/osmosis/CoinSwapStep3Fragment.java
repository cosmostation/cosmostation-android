package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.base.BaseFragment;

public class CoinSwapStep3Fragment extends BaseFragment {

    public static CoinSwapStep3Fragment newInstance(Bundle bundle) {
        CoinSwapStep3Fragment fragment = new CoinSwapStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

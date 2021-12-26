package wannabit.io.cosmostaion.fragment.chains.nft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTCreateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class NFTCreateStep3Fragment extends BaseFragment {

    public static NFTCreateStep3Fragment newInstance(Bundle bundle) {
        NFTCreateStep3Fragment fragment = new NFTCreateStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swap_step3, container, false);

        return rootView;
    }

    private NFTCreateActivity getSActivity() {
        return (NFTCreateActivity)getBaseActivity();
    }
}

package wannabit.io.cosmostaion.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class BaseFragment extends Fragment {

    protected BaseApplication               mApplication;
    protected BaseData                      mData;

    protected BaseApplication getBaseApplication() {
        if (mApplication == null)
            mApplication = getBaseActivity().getBaseApplication();
        return mApplication;

    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity)getActivity();
    }

    protected BaseData getBaseDao() {
        if (getBaseActivity() != null && getBaseActivity().getBaseDao() != null) {
            return getBaseActivity().getBaseDao();

        }  else {
            return getBaseApplication().getBaseDao();

        }
    }

    protected FragmentTransaction getTransaction() {
        return getFragmentManager().beginTransaction();
    }

    public void onRefreshTab() { }

}

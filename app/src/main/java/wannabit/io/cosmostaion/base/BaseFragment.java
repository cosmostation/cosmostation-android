package wannabit.io.cosmostaion.base;


import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected BaseApplication mApplication;

    protected BaseApplication getBaseApplication() {
        if (mApplication == null)
            mApplication = getBaseActivity().getBaseApplication();
        return mApplication;

    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected BaseData getBaseDao() {
        if (getBaseActivity() != null && getBaseActivity().getBaseDao() != null) {
            return getBaseActivity().getBaseDao();

        } else {
            return getBaseApplication().getBaseDao();

        }
    }

    public void showDialog(DialogFragment dialogFragment) {
        showDialog(dialogFragment, "dialog", true);
    }

    public void showDialog(DialogFragment dialogFragment, String tag, boolean cancelable) {
        dialogFragment.setCancelable(cancelable);
        getParentFragmentManager()    // maybe we have to use  getChildFragmentManager()
                .beginTransaction()
                .add(dialogFragment, tag)
                .commitNowAllowingStateLoss();
    }
}

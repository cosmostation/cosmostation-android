package wannabit.io.cosmostaion.task;

import android.os.AsyncTask;

import wannabit.io.cosmostaion.base.BaseApplication;

public class CommonTask extends AsyncTask<String, Void, TaskResult> {

    protected BaseApplication mApp;
    protected TaskListener mListener;
    protected TaskResult mResult;

    public CommonTask(BaseApplication app, TaskListener listener) {
        this.mApp = app;
        this.mListener = listener;
        this.mResult = new TaskResult();
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
        super.onPostExecute(taskResult);
        if (mListener != null)
            mListener.onTaskResponse(taskResult);
    }
}

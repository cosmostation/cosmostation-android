package wannabit.io.cosmostaion.task;

import android.os.AsyncTask;

import wannabit.io.cosmostaion.base.BaseApplication;

public class CommonTask extends AsyncTask<String, Void, TaskResult> {

    protected BaseApplication context;
    protected TaskListener mListener;
    protected TaskResult result;

    public CommonTask(BaseApplication app, TaskListener listener) {
        this.context = app;
        this.mListener = listener;
        this.result = new TaskResult();
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

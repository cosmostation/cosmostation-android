package wannabit.io.cosmostaion.task;

import android.os.AsyncTask;

import wannabit.io.cosmostaion.base.BaseApplication;

public class CommonProgressTask extends AsyncTask<String, Integer, TaskResult> {

    protected BaseApplication           mApp;
    protected ProgressTaskListener      mListener;
    protected TaskResult                mResult;

    public CommonProgressTask(BaseApplication app, ProgressTaskListener listener) {
        this.mApp       = app;
        this.mListener  = listener;
        this.mResult    = new TaskResult();
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (mListener != null)
            mListener.onTaskProgress(values[0]);

    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
        super.onPostExecute(taskResult);
        if(mListener != null)
            mListener.onTaskResponse(taskResult);
    }
}
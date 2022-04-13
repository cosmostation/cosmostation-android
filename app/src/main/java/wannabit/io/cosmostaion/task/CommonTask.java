package wannabit.io.cosmostaion.task;

import android.os.AsyncTask;

import com.joom.lightsaber.Injector;

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

    protected Injector getInjector() {
        return context.getInjector();
    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
        super.onPostExecute(taskResult);
        if (mListener != null)
            mListener.onTaskResponse(taskResult);
    }
}

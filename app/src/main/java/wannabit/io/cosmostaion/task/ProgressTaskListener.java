package wannabit.io.cosmostaion.task;

public interface ProgressTaskListener {
    public abstract void onTaskResponse(TaskResult result);

    public abstract void onTaskProgress(Integer progress);
}

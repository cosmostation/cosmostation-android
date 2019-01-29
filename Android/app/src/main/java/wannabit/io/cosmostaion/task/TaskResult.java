package wannabit.io.cosmostaion.task;

public class TaskResult {
    public boolean      isSuccess;

    public int          errorCode;
    public String       errorMsg;

    public TaskResult() {
        this.isSuccess = false;
    }
}

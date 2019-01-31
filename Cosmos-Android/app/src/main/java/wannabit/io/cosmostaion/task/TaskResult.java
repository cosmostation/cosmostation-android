package wannabit.io.cosmostaion.task;

public class TaskResult {
    public int          taskType;
    public boolean      isSuccess;

    public int          errorCode;
    public String       errorMsg;

    public Object       resultData;

    public TaskResult() {
        this.isSuccess = false;
    }
}

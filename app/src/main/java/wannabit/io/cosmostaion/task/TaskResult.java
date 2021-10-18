package wannabit.io.cosmostaion.task;

import wannabit.io.cosmostaion.base.BaseConstant;

public class TaskResult {
    public int          taskType;
    public boolean      isSuccess;

    public int          errorCode;
    public String       errorMsg;

    public Object       resultData;
    public String       resultData2;
    public String       resultData3;

    public TaskResult() {
        this.isSuccess = false;
        this.errorCode = BaseConstant.ERROR_CODE_UNKNOWN;
        this.errorMsg = "";
    }
}

package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import wannabit.io.cosmostaion.R;

public class TokenIrisHolder extends BaseHolder {

    private LinearLayout    mIrisTransfer;
    private RelativeLayout  mBtnSendIris, mBtnReceiveIris;
    private TextView        mTvIrisTotal, mTvIrisValue, mTvIrisAvailable,
                            mTvIrisDelegated, mTvIrisUnBonding, mTvIrisRewards;

    public TokenIrisHolder(@NonNull View itemView) {
        super(itemView);
        mTvIrisTotal            = itemView.findViewById(R.id.dash_iris_amount);
        mTvIrisValue            = itemView.findViewById(R.id.dash_iris_value);
        mTvIrisAvailable        = itemView.findViewById(R.id.dash_iris_undelegate);
        mTvIrisDelegated        = itemView.findViewById(R.id.dash_iris_delegate);
        mTvIrisUnBonding        = itemView.findViewById(R.id.dash_iris_unbonding);
        mTvIrisRewards          = itemView.findViewById(R.id.dash_iris_reward);

        mIrisTransfer           = itemView.findViewById(R.id.layer_iris_transfer);
        mBtnReceiveIris         = itemView.findViewById(R.id.btn_iris_receive);
        mBtnSendIris            = itemView.findViewById(R.id.btn_iris_send);
        mIrisTransfer.setVisibility(View.GONE);

    }
}

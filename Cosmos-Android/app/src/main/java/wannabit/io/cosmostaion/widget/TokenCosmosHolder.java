package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import wannabit.io.cosmostaion.R;

public class TokenCosmosHolder extends BaseHolder {

    private LinearLayout    mCosmosTransfer;
    private RelativeLayout  mBtnSendAtom, mBtnReceiveAtom;
    private TextView        mTvAtomTotal, mTvAtomValue, mTvAtomAvailable,
                            mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;

    public TokenCosmosHolder(@NonNull View itemView) {
        super(itemView);
        mTvAtomTotal            = itemView.findViewById(R.id.dash_atom_amount);
        mTvAtomValue            = itemView.findViewById(R.id.dash_atom_value);
        mTvAtomAvailable        = itemView.findViewById(R.id.dash_atom_undelegate);
        mTvAtomDelegated        = itemView.findViewById(R.id.dash_atom_delegate);
        mTvAtomUnBonding        = itemView.findViewById(R.id.dash_atom_unbonding);
        mTvAtomRewards          = itemView.findViewById(R.id.dash_atom_reward);

        mCosmosTransfer         = itemView.findViewById(R.id.layer_cosmos_transfer);
        mBtnSendAtom            = itemView.findViewById(R.id.btn_atom_send);
        mBtnReceiveAtom         = itemView.findViewById(R.id.btn_atom_receive);
        mCosmosTransfer.setVisibility(View.GONE);

    }
}

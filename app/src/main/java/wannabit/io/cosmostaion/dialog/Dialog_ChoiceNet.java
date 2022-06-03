package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WDp;

public class Dialog_ChoiceNet extends DialogFragment {

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private ChoiceChainAdapter mChoiceChainAdapter;

    private boolean mIsAdd = false;

    public static Dialog_ChoiceNet newInstance(Bundle bundle) {
        Dialog_ChoiceNet frag = new Dialog_ChoiceNet();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_choice, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mDialogTitle.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mChoiceChainAdapter = new ChoiceChainAdapter();
        mRecyclerView.setAdapter(mChoiceChainAdapter);

        if (getArguments() != null) {
            mIsAdd = true;
        } else {
            mIsAdd = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class ChoiceChainAdapter extends RecyclerView.Adapter<ChoiceChainAdapter.ChainListHolder> {

        @NonNull
        @Override
        public ChoiceChainAdapter.ChainListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ChoiceChainAdapter.ChainListHolder(getLayoutInflater().inflate(R.layout.item_dialog_choice_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChoiceChainAdapter.ChainListHolder holder, int position) {
            final BaseChain baseChain = BaseChain.SUPPORT_CHAINS().get(position);
            WDp.getChainImg(getSActivity(), baseChain, holder.chainImg);
            WDp.getChainTitle2(getSActivity(), baseChain, holder.chainName);

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        (getSActivity()).onChainSelected(baseChain);
                    } else {
                        (getSActivity()).onChoiceNet(baseChain);
                    }
                    getDialog().dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return BaseChain.SUPPORT_CHAINS().size();
        }

        public class ChainListHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            ImageView chainImg;
            TextView chainName;

            public ChainListHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                chainImg = itemView.findViewById(R.id.chainImg);
                chainName = itemView.findViewById(R.id.chainName);
            }
        }

    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }
}

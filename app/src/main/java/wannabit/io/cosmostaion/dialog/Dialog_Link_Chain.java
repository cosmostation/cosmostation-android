package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
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

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class Dialog_Link_Chain extends DialogFragment {

    private RecyclerView         mRecyclerView;
    private LinkChainAdapter     mLinkChainAdapter;
    private ArrayList<BaseChain> mLinkChainList = new ArrayList<>();

    public static Dialog_Link_Chain newInstance(Bundle bundle) {
        Dialog_Link_Chain frag = new Dialog_Link_Chain();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_link_chain, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mLinkChainList = WUtil.getDesmosAirDropChains();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mLinkChainAdapter = new LinkChainAdapter();
        mRecyclerView.setAdapter(mLinkChainAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class LinkChainAdapter extends RecyclerView.Adapter<LinkChainAdapter.RelayerListHolder> {

        @NonNull
        @Override
        public LinkChainAdapter.RelayerListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new LinkChainAdapter.RelayerListHolder(getLayoutInflater().inflate(R.layout.item_dialog_link_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull LinkChainAdapter.RelayerListHolder holder, int position) {
            final BaseChain baseChain = mLinkChainList.get(position);
            WDp.getChainImg(getSActivity(), baseChain, holder.chainImg);
            WDp.getChainTitle2(getSActivity(), baseChain, holder.chainName);

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("position", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mLinkChainList.size();
        }

        public class RelayerListHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            ImageView chainImg;
            TextView chainName;
            public RelayerListHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer   = itemView.findViewById(R.id.rootLayer);
                chainImg    = itemView.findViewById(R.id.chainImg);
                chainName   = itemView.findViewById(R.id.chainName);
            }
        }

    }

    private BaseActivity getSActivity() {
        return (BaseActivity)getActivity();
    }

}

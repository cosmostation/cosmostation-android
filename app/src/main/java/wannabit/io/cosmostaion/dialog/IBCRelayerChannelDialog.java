package wannabit.io.cosmostaion.dialog;

import android.annotation.SuppressLint;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.dao.IbcPath;

public class IBCRelayerChannelDialog extends DialogFragment {

    private ConstraintLayout mDialogLayout;
    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private RelayerListAdapter mRelayerListAdapter;
    private ArrayList<IbcPath.Path> mIbcSendablePaths = new ArrayList<>();

    public static IBCRelayerChannelDialog newInstance(Bundle bundle) {
        IBCRelayerChannelDialog frag = new IBCRelayerChannelDialog();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogLayout = view.findViewById(R.id.dialog_layout);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);
        mIbcSendablePaths = (ArrayList<IbcPath.Path>) getArguments().getSerializable("channel");

        mDialogLayout.setBackgroundResource(R.drawable.layout_trans_with_border);
        mDialogTitle.setText(R.string.str_select_ibc_relayer);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRelayerListAdapter = new RelayerListAdapter();
        mRecyclerView.setAdapter(mRelayerListAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class RelayerListAdapter extends RecyclerView.Adapter<RelayerListAdapter.RelayerListHolder> {

        @NonNull
        @Override
        public RelayerListAdapter.RelayerListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new RelayerListAdapter.RelayerListHolder(getLayoutInflater().inflate(R.layout.item_dialog_ibc_relayer_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RelayerListAdapter.RelayerListHolder holder, @SuppressLint("RecyclerView") int position) {
            final IbcPath.Path path = mIbcSendablePaths.get(position);
            holder.channelTitle.setText(path.channel_id);
            if (path.auth == null) {
                holder.channelStatus.setImageResource(R.drawable.unknown);
            } else if (path.auth) {
                holder.channelStatus.setImageResource(R.drawable.wellknown);
            }
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
            return mIbcSendablePaths.size();
        }

        public class RelayerListHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            TextView channelTitle;
            ImageView channelStatus;

            public RelayerListHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                channelTitle = itemView.findViewById(R.id.channel_title);
                channelStatus = itemView.findViewById(R.id.channel_status);
            }
        }

    }
}

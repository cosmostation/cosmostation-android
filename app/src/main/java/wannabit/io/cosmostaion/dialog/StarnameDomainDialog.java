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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class StarnameDomainDialog extends DialogFragment {

    private ConstraintLayout mDialogLayout;
    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private DomainListAdapter mDomainListAdapter;
    private ArrayList<String> mStarnameDomain = new ArrayList<>();

    public static StarnameDomainDialog newInstance(Bundle bundle) {
        StarnameDomainDialog frag = new StarnameDomainDialog();
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
        mStarnameDomain = getArguments().getStringArrayList("domain");

        mDialogLayout.setBackgroundResource(R.drawable.layout_trans_with_border);
        mDialogTitle.setText(R.string.str_select_starname_domain);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mDomainListAdapter = new DomainListAdapter();
        mRecyclerView.setAdapter(mDomainListAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class DomainListAdapter extends RecyclerView.Adapter<DomainListAdapter.DomainListHolder> {

        @NonNull
        @Override
        public DomainListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new DomainListHolder(getLayoutInflater().inflate(R.layout.item_dialog_starname_domain_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DomainListHolder holder, @SuppressLint("RecyclerView") int position) {
            final String domain = mStarnameDomain.get(position);
            holder.domainName.setText(domain);
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
            return mStarnameDomain.size();
        }

        public class DomainListHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            TextView domainName;

            public DomainListHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                domainName = itemView.findViewById(R.id.domainName);
            }
        }

    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }

}

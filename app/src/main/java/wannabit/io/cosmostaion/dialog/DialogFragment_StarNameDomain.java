package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class DialogFragment_StarNameDomain extends DialogFragment {

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private DomainListAdapter mDomainListAdapter;
    private ArrayList<String> mStarnameDomain = new ArrayList<>();

    public static DialogFragment_StarNameDomain newInstance(Bundle bundle) {
        DialogFragment_StarNameDomain frag = new DialogFragment_StarNameDomain();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return inflater.inflate(R.layout.dialog_recycler_dialog_template, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mDialogTitle.setText(R.string.str_select_starname_domain);
        mRecyclerView = view.findViewById(R.id.recycler);
        mStarnameDomain = getArguments().getStringArrayList("domain");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mDomainListAdapter = new DomainListAdapter();
        mRecyclerView.setAdapter(mDomainListAdapter);
    }

    private class DomainListAdapter extends RecyclerView.Adapter<DomainListAdapter.DomainListHolder> {

        @NonNull
        @Override
        public DomainListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new DomainListHolder(getLayoutInflater().inflate(R.layout.item_dialog_starname_domain_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DomainListHolder holder, int position) {
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

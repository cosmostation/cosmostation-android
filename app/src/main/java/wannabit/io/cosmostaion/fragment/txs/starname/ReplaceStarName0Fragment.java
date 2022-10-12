package wannabit.io.cosmostaion.fragment.txs.starname;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.starname.ReplaceStarNameActivity;
import wannabit.io.cosmostaion.activities.txs.starname.StarNameResourceAddActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.StarNameResourceDialog;
import wannabit.io.cosmostaion.utils.StarnameAssets;
import wannabit.io.cosmostaion.utils.StarnameResourceWrapper;

public class ReplaceStarName0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mNextBtn;
    private RecyclerView mRecyclerView;

    private ResourceAdapter mResourceAdapter;
    public ArrayList<Types.Resource> mResources = new ArrayList<>();

    public static String STARNAME = "asset:iov";

    public static ReplaceStarName0Fragment newInstance() {
        return new ReplaceStarName0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_replace_starname_0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

        mResourceAdapter = new ResourceAdapter();
        mRecyclerView.setAdapter(mResourceAdapter);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mResources = new ArrayList<>(getSActivity().mAccountResolve_gRPC.getResourcesList());
        if (mResources == null || mResources.size() == 0) {
            mResources = new ArrayList();
            Types.Resource initData = Types.Resource.newBuilder().setUri(STARNAME).setResource(getSActivity().mAccount.address).build();
            mResources.add(initData);
        }
        mResourceAdapter.notifyDataSetChanged();
    }

    private ReplaceStarNameActivity getSActivity() {
        return (ReplaceStarNameActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            ArrayList<Types.Resource> tempResources = new ArrayList();
            for (Types.Resource resource : mResources) {
                if (!TextUtils.isEmpty(resource.getResource()) && !TextUtils.isEmpty(resource.getUri())) {
                    tempResources.add(resource);
                }
            }
            if (tempResources.size() == 0) {
                Toast.makeText(getSActivity(), R.string.error_no_address_added, Toast.LENGTH_SHORT).show();
                return;
            }
            getSActivity().mStarNameResources = tempResources;
            getSActivity().onNextStep();
        }
    }

    private final ActivityResultLauncher<Intent> starNameResourceAddResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            try {
                Types.Resource temp = Types.Resource.parseFrom(result.getData().getByteArrayExtra("resource"));
                int position = -1;
                for (int i = 0; i < mResources.size(); i++) {
                    if (mResources.get(i).getUri().equals(temp.getUri())) {
                        position = i;
                        break;
                    }
                }

                if (position >= 0) {
                    mResources.set(position, temp);
                } else {
                    mResources.add(temp);
                }
                mResourceAdapter.notifyDataSetChanged();

            } catch (Exception e) {
            }
        }
    });

    private class ResourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_RESOURCE = 1;
        private static final int TYPE_ADD = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_RESOURCE) {
                return new ResourceHolder(getLayoutInflater().inflate(R.layout.item_manage_starname_resource, viewGroup, false));
            } else if (viewType == TYPE_ADD) {
                return new ResourceAddHolder(getLayoutInflater().inflate(R.layout.item_manage_starname_add, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
            if (getItemViewType(position) == TYPE_RESOURCE) {
                onBindResourceItemViewHolder(viewHolder, position);
            } else if (getItemViewType(position) == TYPE_ADD) {
                onBindAddItemViewHolder(viewHolder);
            }
        }

        private void onBindResourceItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final Types.Resource resource = mResources.get(position);
            final ResourceHolder holder = (ResourceHolder) viewHolder;
            Picasso.get().load(StarnameAssets.getStarNameChainImgUrl(resource.getUri())).fit().into(holder.itemChainImg);
            holder.itemChainName.setText(StarnameAssets.getStarNameChainName(resource.getUri()));
            holder.itemChainAddress.setText(resource.getResource());
            holder.itemRoot.setOnClickListener(v -> {
                Intent intent = new Intent(getSActivity(), StarNameResourceAddActivity.class);
                intent.putExtra("resource", resource.toByteArray());
                starNameResourceAddResultLauncher.launch(intent);
                getSActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
            });
            if (mResources.size() <= 1) {
                holder.itemBtnRemove.setVisibility(View.GONE);
            } else {
                holder.itemBtnRemove.setVisibility(View.VISIBLE);
            }

            holder.itemBtnRemove.setOnClickListener(v -> {
                mResources.remove(position);
                mResourceAdapter.notifyDataSetChanged();
            });
        }

        private void onBindAddItemViewHolder(RecyclerView.ViewHolder viewHolder) {
            final ResourceAddHolder holder = (ResourceAddHolder) viewHolder;
            holder.itemBtnAdd.setOnClickListener(v -> {
                Bundle bundleData = new Bundle();
                StarnameResourceWrapper wrapper = new StarnameResourceWrapper(mResources);
                bundleData.putSerializable("resources", wrapper);
                if (!getSActivity().isFinishing()) {
                    StarNameResourceDialog dialog = StarNameResourceDialog.newInstance(bundleData);
                    dialog.show(getParentFragmentManager(), "dialog");
                    getParentFragmentManager().setFragmentResultListener("starNameResource", ReplaceStarName0Fragment.this, (requestKey, bundle) -> {
                        try {
                            StarnameAssets asset = bundle.getParcelable("resource");
                            Intent intent = new Intent(getSActivity(), StarNameResourceAddActivity.class);
                            intent.putExtra("asset", asset);
                            starNameResourceAddResultLauncher.launch(intent);
                            getSActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                        } catch (Exception e) {
                        }
                    });
                }
            });
        }

        @Override
        public int getItemCount() {
            if (mResources.size() >= 14) {
                return mResources.size();
            } else {
                return mResources.size() + 1;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mResources.size() >= 14) {
                return TYPE_RESOURCE;
            } else {
                if (position < mResources.size()) {
                    return TYPE_RESOURCE;
                } else {
                    return TYPE_ADD;
                }
            }
        }

        public class ResourceAddHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            Button itemBtnAdd;

            public ResourceAddHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_root);
                itemBtnAdd = itemView.findViewById(R.id.btn_add);
            }
        }

        public class ResourceHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemChainImg;
            TextView itemChainName, itemChainAddress, itemBtnRemove;

            public ResourceHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_root);
                itemChainImg = itemView.findViewById(R.id.chain_img);
                itemChainName = itemView.findViewById(R.id.chain_name);
                itemChainAddress = itemView.findViewById(R.id.chain_address);
                itemBtnRemove = itemView.findViewById(R.id.btn_remove);
            }
        }
    }
}

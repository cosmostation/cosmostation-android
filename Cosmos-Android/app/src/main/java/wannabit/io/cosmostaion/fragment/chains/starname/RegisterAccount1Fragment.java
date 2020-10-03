package wannabit.io.cosmostaion.fragment.chains.starname;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterAccountActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_StarName_Resource;
import wannabit.io.cosmostaion.dialog.Dialog_Wallet_for_Starname;
import wannabit.io.cosmostaion.model.StarNameResource;
import wannabit.io.cosmostaion.utils.WUtil;

public class RegisterAccount1Fragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_ADD_RESOURCE = 9700;
    public final static int SELECT_WALLET       = 9701;

    private Button mBefore, mNextBtn;
    private RecyclerView mRecyclerView;

    private ResourceAdapter mResourceAdapter;
    private ArrayList<StarNameResource> mResources = new ArrayList();
    private int mQrPosition;

    public static RegisterAccount1Fragment newInstance(Bundle bundle) {
        RegisterAccount1Fragment fragment = new RegisterAccount1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView   = inflater.inflate(R.layout.fragment_register_account1, container, false);
        mBefore         = rootView.findViewById(R.id.btn_before);
        mNextBtn        = rootView.findViewById(R.id.btn_next);
        mRecyclerView   = rootView.findViewById(R.id.recycler);
        mBefore.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

        mResources = WUtil.getInitStarnameResource();
        mResourceAdapter = new ResourceAdapter();
        mRecyclerView.setAdapter(mResourceAdapter);


        return rootView;
    }

    private RegisterAccountActivity getSActivity() {
        return (RegisterAccountActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBefore)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            ArrayList<StarNameResource> tempResources = new ArrayList();
            for (StarNameResource resource:mResources) {
                if (!TextUtils.isEmpty(resource.resource) && !TextUtils.isEmpty(resource.uri)){
                    tempResources.add(resource);
                }
            }
            getSActivity().mResources = tempResources;
            getSActivity().onNextStep();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_ADD_RESOURCE && resultCode == Activity.RESULT_OK) {
            mResources.add(data.getParcelableExtra("resource"));
            mResourceAdapter.notifyDataSetChanged();

        } else if (requestCode == SELECT_WALLET && resultCode == Activity.RESULT_OK) {
            mResources.get(data.getIntExtra("position", -1)).resource = data.getStringExtra("accountAddress");
            mResourceAdapter.notifyDataSetChanged();

        } else {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if(result != null) {
                if(result.getContents() != null) {
                    mResources.get(mQrPosition).resource = result.getContents().trim();
                    mResourceAdapter.notifyDataSetChanged();

                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }


    private class ResourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_RESOURCE          = 1;
        private static final int TYPE_ADD               = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_RESOURCE) {
                return new ResourceHolder(getLayoutInflater().inflate(R.layout.item_manage_starname_resource, viewGroup, false));
            } else if(viewType == TYPE_ADD) {
                return new ResourceAddHolder(getLayoutInflater().inflate(R.layout.item_manage_starname_add, viewGroup, false));
            }
            return null;

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_RESOURCE) {
                final StarNameResource resource = mResources.get(position);
                final ResourceHolder holder = (ResourceHolder)viewHolder;
                holder.itemChainImg.setImageDrawable(resource.getChainImg(getContext()));
                holder.itemChainName.setText(resource.getChainName());
                holder.itemAddress.setText(resource.resource);
                holder.itemBtnQr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mQrPosition = position;
                        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(RegisterAccount1Fragment.this);
                        integrator.setOrientationLocked(true);
                        integrator.initiateScan();

                    }
                });
                holder.itemBtnWallet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (WUtil.getBaseChainWithUri(resource.uri) == null) {
                            Toast.makeText(getSActivity(), R.string.error_not_support_cosmostation, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (getSActivity().getBaseDao().onSelectAccountsByChain(WUtil.getBaseChainWithUri(resource.uri)).size() == 0) {
                            Toast.makeText(getSActivity(), R.string.error_no_wallet_this_chain, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Bundle bundle = new Bundle();
                        bundle.putInt("position", position);
                        bundle.putString("chainUri", resource.uri);
                        Dialog_Wallet_for_Starname dialog = Dialog_Wallet_for_Starname.newInstance(bundle);
                        dialog.setCancelable(true);
                        dialog.setTargetFragment(RegisterAccount1Fragment.this, SELECT_WALLET);
                        getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

                    }
                });
                holder.itemBtnPaste.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager clipboard = (ClipboardManager)getSActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        if(clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                            String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(getSActivity()).toString().trim();
                            if(TextUtils.isEmpty(userPaste)) {
                                Toast.makeText(getSActivity(), R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                                return;
                            }
                            mResources.get(mQrPosition).resource = userPaste;
                            mResourceAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(getSActivity(), R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            } else if (getItemViewType(position) == TYPE_ADD) {
                final ResourceAddHolder holder = (ResourceAddHolder)viewHolder;
                holder.itemBtnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("resources", WUtil.getAddableStarnameResource(mResources));
                        Dialog_StarName_Resource dialog = Dialog_StarName_Resource.newInstance(bundle);
                        dialog.setCancelable(true);
                        dialog.setTargetFragment(RegisterAccount1Fragment.this, SELECT_ADD_RESOURCE);
                        getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

                    }
                });
            }
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
            TextView itemChainName;
            EditText itemAddress;
            LinearLayout itemBtnQr, itemBtnWallet, itemBtnPaste;

            public ResourceHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot        = itemView.findViewById(R.id.card_root);
                itemChainImg    = itemView.findViewById(R.id.chain_img);
                itemChainName   = itemView.findViewById(R.id.chain_name);
                itemAddress     = itemView.findViewById(R.id.chain_address);
                itemBtnQr       = itemView.findViewById(R.id.btn_qr);
                itemBtnWallet   = itemView.findViewById(R.id.btn_wallet);
                itemBtnPaste    = itemView.findViewById(R.id.btn_paste);
            }
        }
    }
}
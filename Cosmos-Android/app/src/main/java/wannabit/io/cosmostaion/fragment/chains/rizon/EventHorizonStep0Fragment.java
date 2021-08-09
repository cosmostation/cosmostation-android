package wannabit.io.cosmostaion.fragment.chains.rizon;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.MnemonicCode;

import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.PasswordSetActivity;
import wannabit.io.cosmostaion.activities.RestoreActivity;
import wannabit.io.cosmostaion.activities.chains.rizon.EventHorizonActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_FetchRestorePath;
import wannabit.io.cosmostaion.dialog.Dialog_Hdac_info;
import wannabit.io.cosmostaion.dialog.Dialog_KavaRestorePath;
import wannabit.io.cosmostaion.dialog.Dialog_OkexRestoreType;
import wannabit.io.cosmostaion.dialog.Dialog_SecretRestorePath;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.hdac.HdacUtil;

import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;

public class EventHorizonStep0Fragment extends BaseFragment implements View.OnClickListener{

    public final static int             HDAC_INFO = 9500;

    private Button                      mPaste, mBtnConfirm;
    private Button[]                    mAlphabetBtns = new Button[26];
    private ImageButton                 mBtnDelete;
    private Button                      mBtnSpace;
    private RecyclerView                mRecyclerView;

    private static EditText[]           mEtMnemonics = new EditText[24];
    private static int                  mMnemonicPosition = 0;

    private ArrayList<String>           mAllMnemonic;
    private static MnemonicAdapter      mMnemonicAdapter;
    private ArrayList<String>           mWords = new ArrayList<>();

    public static EventHorizonStep0Fragment newInstance(Bundle bundle) {
        EventHorizonStep0Fragment fragment = new EventHorizonStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_horizon_step0, container, false);
        mPaste          = rootView.findViewById(R.id.btn_paste);
        mBtnConfirm     = rootView.findViewById(R.id.btn_confirm);
        mBtnDelete      = rootView.findViewById(R.id.password_back);
        mBtnSpace       = rootView.findViewById(R.id.btn_next);
        mRecyclerView   = rootView.findViewById(R.id.recycler);

        mPaste.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnSpace.setOnClickListener(this);

        getSActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mAllMnemonic = new ArrayList<String>(MnemonicCode.INSTANCE.getWordList());
        for(int i = 0; i < mAlphabetBtns.length; i++) {
            mAlphabetBtns[i] = rootView.findViewById(getResources().getIdentifier("password_char" + i , "id", getSActivity().getPackageName()));
            mAlphabetBtns[i].setOnClickListener(this);
        }

        for(int i = 0; i < mEtMnemonics.length; i++) {
            final int position = i;
            mEtMnemonics[i] = rootView.findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", getSActivity().getPackageName()));
            mEtMnemonics[i].setShowSoftInputOnFocus(false);
            mEtMnemonics[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus) {
                        mMnemonicPosition = position;
                    }
                }
            });
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getSActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setHasFixedSize(true);
        mMnemonicAdapter = new MnemonicAdapter();
        mRecyclerView.setAdapter(mMnemonicAdapter);

        mEtMnemonics[0].requestFocus();

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mPaste)) {
            onClearAll();

            if (getBaseDao().mCopySalt != null && getBaseDao().mCopyEncResult != null) {
                String words = CryptoHelper.doDecryptData(getBaseDao().mCopySalt, getBaseDao().mCopyEncResult.getEncDataString(), getBaseDao().mCopyEncResult.getIvDataString());
                if(TextUtils.isEmpty(words)) {
                    return;
                }
                ArrayList<String> newinsert = new ArrayList<>(Arrays.asList(words.split("\\s+")));
                for (int i = 0; i < mEtMnemonics.length; i++) {
                    if(newinsert.size() > i) {
                        String toinsert = newinsert.get(i).replace(" ", "");
                        mEtMnemonics[i].setText(toinsert);
                    }
                }
                if (newinsert.size() < 23) {
                    mEtMnemonics[newinsert.size()].requestFocus();
                } else {
                    mEtMnemonics[23].requestFocus();
                }
                mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
                mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());

            } else {
                ClipboardManager clipboard = (ClipboardManager) getSActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                    String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(getSActivity().getBaseContext()).toString().trim();
                    if (TextUtils.isEmpty(userPaste)) {
                        Toast.makeText(getSActivity(), R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ArrayList<String> newinsert = new ArrayList<>(Arrays.asList(userPaste.split("\\s+")));
                    for (int i = 0; i < mEtMnemonics.length; i++) {
                        if (newinsert.size() > i) {
                            String toinsert = newinsert.get(i).replace(" ", "");
                            mEtMnemonics[i].setText(toinsert);
                        }
                    }
                    if (newinsert.size() < 23) {
                        mEtMnemonics[newinsert.size()].requestFocus();
                    } else {
                        mEtMnemonics[23].requestFocus();
                    }
                    mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
                    mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());

                } else {
                    Toast.makeText(getSActivity(), R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                }
                return;
            }

        } else if (v.equals(mBtnConfirm)) {
            mWords.clear();
            for(int i = 0; i < mEtMnemonics.length; i++) {
                if(!TextUtils.isEmpty(mEtMnemonics[i].getText().toString().trim())) {
                    mWords.add(mEtMnemonics[i].getText().toString().trim());
                } else {
                    break;
                }
            }

            if (isValidWords()) {
                Bundle bundle = new Bundle();
                bundle.putString("mHdacAddress", onHdacAddress(getSActivity().mBaseChain));
                Dialog_Hdac_info hdacInfo = Dialog_Hdac_info.newInstance(bundle);
                hdacInfo.setCancelable(true);
                hdacInfo.setTargetFragment(this, HDAC_INFO);
                getFragmentManager().beginTransaction().add(hdacInfo, "dialog").commitNowAllowingStateLoss();
            } else {
                Toast.makeText(getSActivity(), R.string.error_invalid_mnemonic_count, Toast.LENGTH_SHORT).show();
            }
            return;

        } else if (v.equals(mBtnDelete)) {
            String existed = mEtMnemonics[mMnemonicPosition].getText().toString().trim();
            if(TextUtils.isEmpty(existed)) {
                onBeforeWord();
            } else {
                mEtMnemonics[mMnemonicPosition].setText(existed.substring(0, existed.length() - 1));
                mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
            }
            mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
            return;

        } else if (v.equals(mBtnSpace)) {
            onNextWord();
            return;

        } if(v instanceof Button) {
            String input = ((Button) v).getText().toString();
            mEtMnemonics[mMnemonicPosition].setText(mEtMnemonics[mMnemonicPosition].getText().toString() + input);
            mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
            mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
            return;
        }
    }

    public static void onClearAll() {
        for(int i = 0; i < mEtMnemonics.length; i++) {
            mEtMnemonics[i].setText("");
            mEtMnemonics[0].requestFocus();
        }
        onBeforeWord();
    }

    private static void onBeforeWord() {
        if(mMnemonicPosition > 0) {
            mEtMnemonics[mMnemonicPosition - 1].requestFocus();
            mEtMnemonics[mMnemonicPosition].setSelection(mEtMnemonics[mMnemonicPosition].getText().length());
        }
        mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
    }

    private void onNextWord() {
        if(mMnemonicPosition < 23) {
            mEtMnemonics[mMnemonicPosition + 1].requestFocus();
        }
        mMnemonicAdapter.getFilter().filter(mEtMnemonics[mMnemonicPosition].getText().toString().trim());
    }

    private boolean isValidWords() {
        if (mWords.size() >= 3 && WKey.isMnemonicWords(mWords) && onHdacAddress(getSActivity().mBaseChain) != null) {
            return true;
        } else {
            return false;
        }
    }

    private String onHdacAddress(BaseChain baseChain) {
        boolean mainnet = true;
        String address = null;
        if (getSActivity().mBaseChain.equals(RIZON_TEST)) {
            mainnet = false;
        }
        HdacUtil hdacUtil = new HdacUtil(mWords);
        address = hdacUtil.getAddress(mainnet);
        return address;
    }

    private EventHorizonActivity getSActivity() { return (EventHorizonActivity)getBaseActivity(); }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == HDAC_INFO && resultCode == Activity.RESULT_OK) {
            if(data.getIntExtra("hdac" , -1) == 0 ){
                onClearAll();

            } else if(data.getIntExtra("hdac" , -1) == 1){
                getSActivity().onNextStep();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public class MnemonicAdapter extends RecyclerView.Adapter<MnemonicAdapter.MnemonicHolder> implements Filterable {

        private ArrayList<String>   mFilteredMnemonic = new ArrayList<>();

        @NonNull
        @Override
        public MnemonicAdapter.MnemonicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MnemonicHolder(getLayoutInflater().inflate(R.layout.item_suggest_menmonic, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MnemonicAdapter.MnemonicHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.itemMnemonic.setText(mFilteredMnemonic.get(position));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEtMnemonics[mMnemonicPosition].setText(mFilteredMnemonic.get(position));
                    onNextWord();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mFilteredMnemonic.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String charString = constraint.toString();
                    if (charString.isEmpty()) {
                        mFilteredMnemonic.clear();

                    } else {
                        ArrayList<String> filteredList = new ArrayList<>();
                        for (String word : mAllMnemonic) {
                            if (word.startsWith(charString.toLowerCase()) && !word.equals(charString.toLowerCase())) {
                                filteredList.add(word);
                            }
                        }
                        mFilteredMnemonic = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mFilteredMnemonic;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    mFilteredMnemonic = (ArrayList<String>) results.values;
                    notifyDataSetChanged();
                }
            };
        }

        public class MnemonicHolder extends RecyclerView.ViewHolder {
            RelativeLayout itemRoot;
            TextView itemMnemonic;

            public MnemonicHolder(View v) {
                super(v);
                itemRoot    = itemView.findViewById(R.id.root_mnemonic);
                itemMnemonic    = itemView.findViewById(R.id.tv_mnemonic);
            }
        }
    }
}

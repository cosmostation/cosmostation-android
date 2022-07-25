package wannabit.io.cosmostaion.fragment.txs.rizon;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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


import org.bitcoinj.crypto.MnemonicCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.rizon.EventHorizonActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dialog.Dialog_Hdac_info;
import wannabit.io.cosmostaion.model.hdac.HdacUtxo;
import wannabit.io.cosmostaion.task.FetchTask.HdacUtxoTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.hdac.HdacUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_HDAC_UTXO;

public class EventHorizonStep0Fragment extends BaseFragment implements View.OnClickListener, TaskListener {

    public final static int             HDAC_INFO_DIALOG = 9500;

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

    private ArrayList<HdacUtxo>         mUtxo;
    private BigDecimal                  mBalance;

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
                    String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(getSActivity()).toString().trim();
                    if(TextUtils.isEmpty(userPaste)) {
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
            }
            return;

        } else if (v.equals(mBtnConfirm)) {
            mUtxo = null;
            mBalance = null;
            mWords.clear();
            for(int i = 0; i < mEtMnemonics.length; i++) {
                if(!TextUtils.isEmpty(mEtMnemonics[i].getText().toString().trim())) {
                    mWords.add(mEtMnemonics[i].getText().toString().trim());
                } else {
                    break;
                }
            }

            if (isValidWords()) {
                onHdacInfo();
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
        HdacUtil hdacUtil = new HdacUtil(mWords);
        String mAddress = hdacUtil.getAddress(baseChain);
        return mAddress;
    }

    private int mTaskCount;
    public void onHdacInfo() {
        getSActivity().onShowWaitDialog();
        mTaskCount = 1;
        new HdacUtxoTask(getBaseApplication(), this, getSActivity().mBaseChain, onHdacAddress(getSActivity().mBaseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_HDAC_UTXO) {
            if (result.isSuccess && result.resultData != null) {
                mUtxo = (ArrayList<HdacUtxo>) result.resultData;

                HdacUtil hdacUtil = new HdacUtil(mWords);
                mBalance = hdacUtil.getBalance(getSActivity().mBaseChain, mUtxo);
                Bundle bundle = new Bundle();
                bundle.putString("hdacAddress", onHdacAddress(getSActivity().mBaseChain));
                bundle.putString("hdacBalance", mBalance.toPlainString());
                Dialog_Hdac_info hdacInfo = Dialog_Hdac_info.newInstance(bundle);
                hdacInfo.setCancelable(true);
                hdacInfo.setTargetFragment(this, HDAC_INFO_DIALOG);
                getFragmentManager().beginTransaction().add(hdacInfo, "dialog").commitNowAllowingStateLoss();
            }
        }
        if (mTaskCount == 0) {
            getSActivity().onHideWaitDialog();
        }
    }

    private EventHorizonActivity getSActivity() { return (EventHorizonActivity)getBaseActivity(); }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == HDAC_INFO_DIALOG && resultCode == Activity.RESULT_OK) {
            if (data.getIntExtra("hdac" , -1) == 0 ) {
                mUtxo = null;

            } else if(data.getIntExtra("hdac" , -1) == 1) {
                getSActivity().mHdacWords = mWords;
                getSActivity().mHdacUtxo = mUtxo;
                getSActivity().mHdacBalance = mBalance;
                if (mBalance.subtract(new BigDecimal("0.1").movePointRight(8)).compareTo(BigDecimal.ZERO) <= 0) {
                    Toast.makeText(getSActivity(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }
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

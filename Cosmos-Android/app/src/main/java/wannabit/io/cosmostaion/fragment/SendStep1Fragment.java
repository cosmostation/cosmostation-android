package wannabit.io.cosmostaion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_SendType;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class SendStep1Fragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout            mMainnetLayer, mTestnetLayer;
    private EditText                mAtomInput;
    private TextView                mAtomMax, mAtomError;
    private EditText                mMuonInput, mPhotinoInput;
    private TextView                mMuonMax, mMuonError, mPhotinoMax, mPhotinoError;
    private Button                  mBeforeBtn, mNextBtn;

    private ArrayList<Coin>         mToSendCoins = new ArrayList<>();

    public static SendStep1Fragment newInstance(Bundle bundle) {
        SendStep1Fragment fragment = new SendStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_step1, container, false);
        mMainnetLayer   = rootView.findViewById(R.id.main_net_layer);
        mTestnetLayer   = rootView.findViewById(R.id.test_net_layer);
        mAtomInput      = rootView.findViewById(R.id.et_amount_atom);
        mAtomMax        = rootView.findViewById(R.id.tv_max_atom);
        mAtomError      = rootView.findViewById(R.id.tv_atom_error);
        mMuonInput      = rootView.findViewById(R.id.et_amount_muon);
        mMuonMax        = rootView.findViewById(R.id.tv_max_muon);
        mMuonError      = rootView.findViewById(R.id.tv_moun_error);
        mPhotinoInput   = rootView.findViewById(R.id.et_amount_photino);
        mPhotinoMax     = rootView.findViewById(R.id.tv_max_photino);
        mPhotinoError   = rootView.findViewById(R.id.tv_photino_error);
        mBeforeBtn      = rootView.findViewById(R.id.btn_before);
        mNextBtn        = rootView.findViewById(R.id.btn_next);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        mAtomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().replace(",","").trim();
                if(es == null || es.length() == 0) {
                    mAtomInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                } else if (es.startsWith(".")) {
                    mAtomInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                    mAtomInput.setText("");
                } else if (es.endsWith(".")) {
                    mAtomInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                    mAtomError.setVisibility(View.VISIBLE);
                } else if(es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                    mAtomInput.setText("0");
                    mAtomInput.setSelection(1);
                } else if(es.equals("0.000000")) {
                    mAtomInput.setText("0.00000");
                    mAtomInput.setSelection(7);
                } else {
                    try {
                        final BigDecimal inputAmount = new BigDecimal(es);
                        if (BigDecimal.ZERO.compareTo(inputAmount) >= 0 ){
                            mAtomInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            mAtomError.setVisibility(View.VISIBLE);
                            return;
                        }
                        BigDecimal checkPosition = inputAmount.movePointRight(6);
                        try {
                            Long.parseLong(checkPosition.toPlainString());
                        } catch (Exception e) {
                            String recover = es.substring(0, es.length() - 1);
                            mAtomInput.setText(recover);
                            mAtomInput.setSelection(recover.length());
                            return;
                        }
                        if(inputAmount.compareTo(getSActivity().mAccount.getAtomBalance().movePointLeft(6).setScale(6, RoundingMode.CEILING)) > 0) {
                            mAtomInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            mAtomError.setVisibility(View.VISIBLE);
                        } else {
                            mAtomInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                            mAtomError.setVisibility(View.GONE);
                        }
                    } catch (Exception e) { }
                }

            }
        });

        mMuonInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().replace(",","").trim();
                if(TextUtils.isEmpty(es)) {
                    return;
                } else if (es.contains(".")) {
                    es = es.replace(".", "");
                    mMuonInput.setText(es);
                    mMuonInput.setSelection(es.length());
                } else if (es.startsWith("0") && es.length() > 1) {
                    String recover = es.substring(1, 2);
                    mMuonInput.setText(recover);
                    mMuonInput.setSelection(recover.length());
                    return;
                } else {
                    final BigDecimal inputAmount = new BigDecimal(es);
                    if(inputAmount.compareTo(getSActivity().mAccount.getAtomBalance()) > 0) {
                        mMuonInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                        mMuonError.setVisibility(View.VISIBLE);
                    } else {
                        mMuonInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        mMuonError.setVisibility(View.GONE);
                    }
                }
            }
        });

        mPhotinoInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable et) {
                String es = et.toString().replace(",","").trim();
                if(TextUtils.isEmpty(es)) {
                    return;
                } else if (es.contains(".")) {
                    es = es.replace(".", "");
                    mPhotinoInput.setText(es);
                    mPhotinoInput.setSelection(es.length());
                } else if (es.startsWith("0") && es.length() > 1) {
                    String recover = es.substring(1, 2);
                    mPhotinoInput.setText(recover);
                    mPhotinoInput.setSelection(recover.length());
                    return;
                } else {
                    final BigDecimal inputAmount = new BigDecimal(es);
                    if(inputAmount.compareTo(getSActivity().mAccount.getPhotonBalance()) > 0) {
                        mPhotinoInput.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                        mPhotinoError.setVisibility(View.VISIBLE);
                    } else {
                        mPhotinoInput.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                        mPhotinoError.setVisibility(View.GONE);
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isAdded() || getSActivity() == null || getSActivity().mAccount == null) getSActivity().onBackPressed();
        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            mMainnetLayer.setVisibility(View.VISIBLE);
            mTestnetLayer.setVisibility(View.GONE);
            mAtomMax.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getAtomBalance(), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        } else {
            mMainnetLayer.setVisibility(View.GONE);
            mTestnetLayer.setVisibility(View.VISIBLE);
            mMuonMax.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getAtomBalance(), 0, BaseChain.getChain(getSActivity().mAccount.baseChain)));
            mPhotinoMax.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getPhotonBalance(), 0, BaseChain.getChain(getSActivity().mAccount.baseChain)));
        }


        onUpdateView();
    }

    private void onUpdateView() {

    }

//    @Override
//    public void onRefreshTab() {
//        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
////            mToSendCoins.clear();
////            Coin atom = new Coin(BaseConstant.COSMOS_ATOM, "0");
////            mToSendCoins.add(atom);
////            mSendCoinAdapter.notifyDataSetChanged();
//
//        } else  {
////            if(mToSendCoins == null || mToSendCoins.size() == 0) {
////                onShowSendTypeDialog();
////            }
//        }
//
//    }

    private boolean isValidateSendAmount() {
        mToSendCoins.clear();
        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            try {
                BigDecimal atomTemp = new BigDecimal(mAtomInput.getText().toString().trim());
                if(atomTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if(atomTemp.compareTo(getSActivity().mAccount.getAtomBalance().movePointLeft(6).setScale(6, RoundingMode.CEILING)) > 0) return false;
                Coin atom;
                if(BaseConstant.IS_TEST) {
                    atom = new Coin(BaseConstant.COSMOS_MUON, atomTemp.multiply(new BigDecimal("1000000")).setScale(0).toPlainString());
                } else {
                    atom = new Coin(BaseConstant.COSMOS_ATOM, atomTemp.multiply(new BigDecimal("1000000")).setScale(0).toPlainString());
                }

                mToSendCoins.add(atom);
                return true;
            } catch (Exception e) {
                return false;
            }

        } else {
            try {
                String muonS = mMuonInput.getText().toString().trim();
                String photinoS = mPhotinoInput.getText().toString().trim();
                BigDecimal muonTemp = BigDecimal.ZERO;
                BigDecimal photinoTemp = BigDecimal.ZERO;

                if(!TextUtils.isEmpty(muonS)) muonTemp = new BigDecimal(muonS);
                if(!TextUtils.isEmpty(photinoS)) photinoTemp = new BigDecimal(photinoS);

                if(muonTemp.compareTo(BigDecimal.ZERO) <= 0 && photinoTemp.compareTo(BigDecimal.ZERO) <= 0) return false;
                if(muonTemp.compareTo(getSActivity().mAccount.getAtomBalance()) > 0) return false;
                if(photinoTemp.compareTo(getSActivity().mAccount.getPhotonBalance()) > 0) return false;
                if(muonTemp.compareTo(BigDecimal.ZERO) > 0) {
                    Coin muon = new Coin(BaseConstant.COSMOS_MUON, muonTemp.toPlainString());
                    mToSendCoins.add(muon);
                }
                if(photinoTemp.compareTo(BigDecimal.ZERO) > 0) {
                    Coin photino = new Coin(BaseConstant.COSMOS_PHOTINO, photinoTemp.toPlainString());
                    mToSendCoins.add(photino);
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }


    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
//            for(Coin coin:mToSendCoins) {
//                WLog.w("coin : denom : " + coin.denom + "    amount : " + coin.amount);
//            }

            if(isValidateSendAmount()) {
                getSActivity().mTargetCoins = mToSendCoins;
                getSActivity().onNextStep();

                WLog.w(" alal " + getSActivity().mTargetCoins.get(0).denom + "   " + getSActivity().mTargetCoins.get(0).amount);


            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
/*
public class SendStep1Fragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_COIN_DIALOG = 6000;

    private RecyclerView            mRecyclerView;
    private Button                  mBeforeBtn, mNextBtn;
    private ArrayList<Coin>         mToSendCoins = new ArrayList<>();
    private SendCoinAdapter         mSendCoinAdapter;

    public static SendStep1Fragment newInstance(Bundle bundle) {
        SendStep1Fragment fragment = new SendStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_step1, container, false);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);


        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isAdded() || getSActivity() == null || getSActivity().mAccount == null) return;
        mSendCoinAdapter = new SendCoinAdapter(BaseChain.getChain(getSActivity().mAccount.baseChain));
        mRecyclerView.setAdapter(mSendCoinAdapter);
    }

    @Override
    public void onRefreshTab() {
        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            mToSendCoins.clear();
            Coin atom = new Coin(BaseConstant.COSMOS_ATOM, "0");
            mToSendCoins.add(atom);
            mSendCoinAdapter.notifyDataSetChanged();

        } else  {
            if(mToSendCoins == null || mToSendCoins.size() == 0) {
                onShowSendTypeDialog();
            }
        }

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            for(Coin coin:mToSendCoins) {
                WLog.w("coin : denom : " + coin.denom + "    amount : " + coin.amount);
            }


            if(isValidateSendAmount()) {
                getSActivity().mTargetCoins = mToSendCoins;
                getSActivity().onNextStep();

            } else {
                Toast.makeText(getContext(), R.string.error_invalid_amount, Toast.LENGTH_SHORT).show();
            }
        }

    }

    //Only show multicoin
    private void onShowSendTypeDialog() {
        Bundle bundle = new Bundle();
        boolean alreadyMuon = false;
        boolean alreadyPhotino = false;
        for (Coin coin:mToSendCoins) {
            if(coin.denom.contains(BaseConstant.COSMOS_MUON)) alreadyMuon = true;
            if(coin.denom.contains(BaseConstant.COSMOS_PHOTINO)) alreadyPhotino = true;
        }
        if (!alreadyMuon) bundle.putBoolean(BaseConstant.COSMOS_MUON, true);
        if (!alreadyPhotino) bundle.putBoolean(BaseConstant.COSMOS_PHOTINO, true);
        bundle.putString("chain", getSActivity().mAccount.baseChain);
        Dialog_SendType dialog = Dialog_SendType.newInstance(bundle);
        dialog.setCancelable(false);
        dialog.setTargetFragment(this, SELECT_COIN_DIALOG);
        dialog.show(getFragmentManager().beginTransaction(), "dialog");

    }


    private void onRemoveCoinType(int position) {
        mToSendCoins.remove(position);
        mSendCoinAdapter.notifyDataSetChanged();
    }


    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }

    //TODO check validate logic be fore add
    private boolean isValidateSendAmount() {
        if(mToSendCoins == null || mToSendCoins.size() == 0) {
            return false;
        }

        if(getSActivity().mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            if(mToSendCoins.size() != 1) return false;
            BigDecimal temp = new BigDecimal(mToSendCoins.get(0).amount);
            if(temp.compareTo(BigDecimal.ZERO) <= 0) return false;
            if(temp.compareTo(getSActivity().mAccount.getAtomBalance().movePointLeft(6).setScale(6, RoundingMode.CEILING)) > 0) return false;
        }


//        for(Coin coin:mToSendCoins) {
//            BigDecimal temp = new BigDecimal(coin.amount);
//            if(temp.compareTo(BigDecimal.ZERO) <= 0) {
//                return false;
//            }
//        }
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_COIN_DIALOG) {
            if (data.getStringExtra("coin").equals(BaseConstant.COSMOS_ATOM)) {
                Coin atom = new Coin(BaseConstant.COSMOS_ATOM, "0");
                mToSendCoins.add(atom);
            } else if (data.getStringExtra("coin").equals(BaseConstant.COSMOS_MUON)) {
                Coin photon = new Coin(BaseConstant.COSMOS_MUON, "0");
                mToSendCoins.add(photon);
            } else if (data.getStringExtra("coin").equals(BaseConstant.COSMOS_PHOTINO)) {
                Coin photon = new Coin(BaseConstant.COSMOS_PHOTINO, "0");
                mToSendCoins.add(photon);
            }
            mSendCoinAdapter.notifyDataSetChanged();
        }
    }

    private class SendCoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_COIN              = 0;
        private static final int TYPE_ADD               = 1;
        private BaseChain chainType;

        public SendCoinAdapter(BaseChain chainType) {
            this.chainType = chainType;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_COIN) {
                return new SendCoinHolder(getLayoutInflater().inflate(R.layout.item_send_coin, viewGroup, false));
            } else if (viewType == TYPE_ADD) {
                return new SendAddHolder(getLayoutInflater().inflate(R.layout.item_send_add, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
            WLog.w("onBindViewHolder : " + position + "   " + getItemViewType(position));
            if (getItemViewType(position) == TYPE_ADD) {
                final SendAddHolder holder = (SendAddHolder)viewHolder;
                holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onShowSendTypeDialog();
                    }
                });
            } else {
                final SendCoinHolder holder = (SendCoinHolder)viewHolder;
                final Coin coin = mToSendCoins.get(position);

                if(chainType.equals(BaseChain.COSMOS_MAIN)) {
                    holder.btnRemove.setVisibility(View.GONE);
                } else {
                    holder.btnRemove.setVisibility(View.VISIBLE);
                    holder.btnRemove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.etAmountCoin.setText("");
                            onRemoveCoinType(position);
                        }
                    });
                }

                if (coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                    holder.tvSymbolCoin.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
                    holder.tvSymbolCoin.setTextColor(getResources().getColor(R.color.colorAtom));
//                    WLog.w("ATOM getSActivity().mAccount.getAtomBalance() " + getSActivity().mAccount.getAtomBalance().toPlainString());
                    holder.tvMaxCoin.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getAtomBalance(), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));

                } else if (coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                    holder.tvSymbolCoin.setText(WDp.DpAtom(getContext(), getSActivity().mAccount.baseChain));
                    holder.tvSymbolCoin.setTextColor(getResources().getColor(R.color.colorAtom));
                    holder.tvMaxCoin.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getAtomBalance(), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));

                } else if (coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                    holder.tvSymbolCoin.setText(WDp.DpPoton(getContext(), getSActivity().mAccount.baseChain));
                    holder.tvSymbolCoin.setTextColor(getResources().getColor(R.color.colorPhoton));
                    holder.tvMaxCoin.setText(WDp.getDpAmount(getContext(), getSActivity().mAccount.getPhotonBalance(), 6, BaseChain.getChain(getSActivity().mAccount.baseChain)));
                }

                holder.etAmountCoin.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                        WLog.w("beforeTextChanged : " + s);
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        WLog.w("onTextChanged : " + s);
//                        String enteredString = s.toString();
//                        if(TextUtils.isEmpty(s) || s.toString().startsWith(".")) return;
//                        if (enteredString.startsWith("00")) {
//                            if (enteredString.length() > 0) {
//                                holder.etAmountCoin.setText(enteredString.substring(1));
//                                holder.etAmountCoin.setSelection(1);
//                            } else {
//                                holder.etAmountCoin.setText("");
//                            }
//                        }
//
//                        BigDecimal enterDecimal = BigDecimal.ZERO;
//                        try {
//                            enterDecimal = new BigDecimal(enteredString);
//                        } catch (Exception e) {
//                            holder.etAmountCoin.setText("");
//                            return ;
//                        }
//
//                        if(chainType.equals(BaseChain.COSMOS_MAIN)) {
//                            enterDecimal = enterDecimal.movePointRight(6);
//                        }
//
//                        try {
//                            Integer.parseInt(enterDecimal.toPlainString());
//                        } catch (Exception e) {
//                            holder.etAmountCoin.setText(s.toString().substring(0, s.toString().length()-1));
//                            holder.etAmountCoin.setSelection(s.toString().length()-1);
//                        }




                    }

                    @Override
                    public void afterTextChanged(Editable et) {
                        WLog.w("afterTextChanged : " + et);
                        String es = et.toString().replace(",","").trim();
                        if(es == null || es.length() == 0) {
                            holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            mToSendCoins.get(position).amount = "0";
                        } else if (es.startsWith(".")) {
                            holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                            holder.etAmountCoin.setText("");
                            mToSendCoins.get(position).amount = "0";
                        } else if (es.endsWith(".")) {
                            holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                            holder.tvError.setVisibility(View.VISIBLE);
                            mToSendCoins.get(position).amount = "0";
                        } else if(es.length() > 1 && es.startsWith("0") && !es.startsWith("0.")) {
                            holder.etAmountCoin.setText("0");
                            holder.etAmountCoin.setSelection(1);
                        } else {
                            try {
                                final BigDecimal inputAmout = new BigDecimal(es);
                                if (coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                                    if(es.equals("0.000000")) {
                                        holder.etAmountCoin.setText("0.00000");
                                        holder.etAmountCoin.setSelection(7);
                                        mToSendCoins.get(position).amount = "0";
                                        return;
                                    }
                                    if (BigDecimal.ZERO.compareTo(inputAmout) >= 0 ){
                                        holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                                        holder.tvError.setVisibility(View.VISIBLE);
                                        mToSendCoins.get(position).amount = "0";
                                        return;
                                    }
                                    BigDecimal checkPosition = inputAmout.movePointRight(6);
                                    try {
                                        Long.parseLong(checkPosition.toPlainString());
                                    } catch (Exception e) {
                                        String recover = es.substring(0, es.length() - 1);
                                        holder.etAmountCoin.setText(recover);
                                        holder.etAmountCoin.setSelection(recover.length());
                                        mToSendCoins.get(position).amount = es.substring(0, es.length()-1);
                                        return;
                                    }
                                    if(inputAmout.compareTo(getSActivity().mAccount.getAtomBalance().movePointLeft(6).setScale(6, RoundingMode.CEILING)) > 0) {
                                        holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                                        holder.tvError.setVisibility(View.VISIBLE);
                                    } else {
                                        holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                                        holder.tvError.setVisibility(View.GONE);
                                    }
                                } else if (coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                                    if(inputAmout.compareTo(getSActivity().mAccount.getAtomBalance()) > 0) {
                                        holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                                        holder.tvError.setVisibility(View.VISIBLE);
                                    } else {
                                        holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                                        holder.tvError.setVisibility(View.GONE);
                                    }

                                } else if (coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                                    if(inputAmout.compareTo(getSActivity().mAccount.getPhotonBalance()) > 0) {
                                        holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box_error));
                                        holder.tvError.setVisibility(View.VISIBLE);
                                    } else {
                                        holder.etAmountCoin.setBackground(getResources().getDrawable(R.drawable.edittext_box));
                                        holder.tvError.setVisibility(View.GONE);
                                    }
                                }
                                mToSendCoins.get(position).amount = inputAmout.toPlainString();
                            } catch (Exception e) {
                                WLog.w("err");
                                mToSendCoins.get(position).amount = "0";
                            }
                            WLog.w("finally : " + mToSendCoins.get(position).amount );
                        }
                    }
                });
            }
        }


        @Override
        public int getItemViewType(int position) {
            //only atom support mainnet.
            if(chainType.equals(BaseChain.COSMOS_MAIN))
                return TYPE_COIN;

            if (mToSendCoins.size() == 0) {
                return TYPE_ADD;
            } else if (mToSendCoins.size() == 1) {
                if(position == 0) return  TYPE_COIN;
                else return TYPE_ADD;
            } else {
                return TYPE_COIN;
            }
        }

        @Override
        public int getItemCount() {
            if(chainType.equals(BaseChain.COSMOS_MAIN))
                return mToSendCoins.size();
                        ;
            if(mToSendCoins.size() == 0)
                return 1;
            else
                return 2;
        }


        public class SendCoinHolder extends RecyclerView.ViewHolder {
            ImageView btnRemove;
            EditText etAmountCoin;
            TextView tvSymbolCoin, tvMaxCoin, tvError;

            public SendCoinHolder(View v) {
                super(v);
                btnRemove       = itemView.findViewById(R.id.btn_remove_coin);
                etAmountCoin    = itemView.findViewById(R.id.et_amount_coin);
                tvSymbolCoin    = itemView.findViewById(R.id.tv_symbol_coin);
                tvMaxCoin       = itemView.findViewById(R.id.tv_max_coin);
                tvError         = itemView.findViewById(R.id.tv_error);
            }
        }


        public class SendAddHolder extends RecyclerView.ViewHolder {
            LinearLayout btnAdd;

            public SendAddHolder(View v) {
                super(v);
                btnAdd = itemView.findViewById(R.id.btnAdd);
            }
        }
    }
}
*/
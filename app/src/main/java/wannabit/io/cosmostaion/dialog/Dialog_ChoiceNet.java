package wannabit.io.cosmostaion.dialog;

import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;

public class Dialog_ChoiceNet extends DialogFragment {


    private LinearLayout mCosmosTestLayer, mIrisTestLayer;
    private LinearLayout mMain, mImversed, mIris, mBinance, mOkex, mKava, mIov;
    private LinearLayout mBand, mPersis, mCertik, mAkash, mSentinel, mFetch, mCryto, mSifchain, mKichain, mOsmosis, mMedi,
                         mEmoney, mRegen, mRizon, mJuno, mBitCanna, mAlthea, mStargaze, mGraBridge, mComdex, mBitsong, mInj,
                         mSecret, mDesmos, mLum, mChihuahua, mAxelar, mKonstellation, mUmee, mEvmos, mCudos, mProvenance, mCerberus, mOmniflix;
    private LinearLayout mAltheaTestLayer;
    private LinearLayout mCosmosTest, mIrisTest, mAltheaTest;

    private boolean      mIsAdd = false;

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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_choice_net, null);

        mMain = view.findViewById(R.id.main_net);
        mImversed = view.findViewById(R.id.imversed_net);
        mIris = view.findViewById(R.id.iris_net);
        mBinance = view.findViewById(R.id.binance_net);
        mOkex = view.findViewById(R.id.okex_net);
        mKava = view.findViewById(R.id.kava_net);
        mIov = view.findViewById(R.id.iov_net);

        mCosmosTestLayer = view.findViewById(R.id.cosmos_test_layer);
        mCosmosTest = view.findViewById(R.id.cosmos_test_net);
        mIrisTestLayer = view.findViewById(R.id.iris_test_layer);
        mIrisTest = view.findViewById(R.id.iris_test_net);

        mBand = view.findViewById(R.id.band_chain);
        mCertik = view.findViewById(R.id.certik_chain);
        mAkash = view.findViewById(R.id.akash_chain);
        mSecret = view.findViewById(R.id.secret_chain);
        mPersis = view.findViewById(R.id.persis_chain);
        mSentinel = view.findViewById(R.id.sentinel_chain);
        mFetch = view.findViewById(R.id.fetch_chain);
        mCryto = view.findViewById(R.id.cryto_chain);
        mSifchain = view.findViewById(R.id.sif_chain);
        mKichain = view.findViewById(R.id.ki_chain);
        mOsmosis = view.findViewById(R.id.osmosis_chain);
        mMedi = view.findViewById(R.id.medi_chain);
        mEmoney = view.findViewById(R.id.emoney_chain);
        mRizon = view.findViewById(R.id.rizon_chain);
        mJuno = view.findViewById(R.id.juno_chain);
        mRegen = view.findViewById(R.id.regen_chain);
        mBitCanna = view.findViewById(R.id.bitcanna_chain);
        mAlthea = view.findViewById(R.id.althea_chain);
        mStargaze = view.findViewById(R.id.stargaze_chain);
        mGraBridge = view.findViewById(R.id.grabridge_chain);
        mComdex = view.findViewById(R.id.comdex_chain);
        mBitsong = view.findViewById(R.id.bitsong_chain);
        mInj = view.findViewById(R.id.inj_chain);
        mDesmos = view.findViewById(R.id.desmos_chain);
        mLum = view.findViewById(R.id.lum_chain);
        mChihuahua = view.findViewById(R.id.chihuahua_chain);
        mAxelar = view.findViewById(R.id.axelar_chain);
        mKonstellation = view.findViewById(R.id.konstellation_chain);
        mUmee = view.findViewById(R.id.umee_chain);
        mEvmos = view.findViewById(R.id.evmos_chain);
        mCudos = view.findViewById(R.id.cudos_chain);
        mProvenance = view.findViewById(R.id.provenance_chain);
        mCerberus = view.findViewById(R.id.cerberus_chain);
        mOmniflix = view.findViewById(R.id.omniflix_chain);

        mAltheaTestLayer = view.findViewById(R.id.althea_test_layer);
        mAltheaTest = view.findViewById(R.id.althea_test_net);

        if (getArguments() != null) {
            mIsAdd = true;
        } else {
            mIsAdd = false;
        }

        mMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.COSMOS_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.COSMOS_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mImversed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.IMVERSED_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.IMVERSED_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mIris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.IRIS_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.IRIS_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mBinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.BNB_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.BNB_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mOkex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.OKEX_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.OKEX_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mKava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.KAVA_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.KAVA_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mIov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.IOV_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.IOV_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mBand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.BAND_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.BAND_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mPersis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.PERSIS_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.PERSIS_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mCertik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.CERTIK_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.CERTIK_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mAkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.AKASH_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.AKASH_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mSentinel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.SENTINEL_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.SENTINEL_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.FETCHAI_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.FETCHAI_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mCryto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.CRYPTO_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.CRYPTO_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mSifchain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.SIF_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.SIF_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mKichain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.KI_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.KI_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mOsmosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.OSMOSIS_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.OSMOSIS_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mMedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.MEDI_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.MEDI_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mEmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.EMONEY_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.EMONEY_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mRizon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.RIZON_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.RIZON_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.SECRET_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.SECRET_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mJuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.JUNO_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.JUNO_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mRegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.REGEN_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.REGEN_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mBitCanna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.BITCANNA_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.BITCANNA_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mAlthea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.ALTHEA_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.ALTHEA_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mStargaze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.STARGAZE_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.STARGAZE_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mGraBridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.GRABRIDGE_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.GRABRIDGE_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mComdex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.COMDEX_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.COMDEX_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mBitsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.BITSONG_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.BITSONG_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mInj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.INJ_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.INJ_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mDesmos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.DESMOS_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.DESMOS_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mLum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.LUM_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.LUM_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mChihuahua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.CHIHUAHUA_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.CHIHUAHUA_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mAxelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.AXELAR_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.AXELAR_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mKonstellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.KONSTELL_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.KONSTELL_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mUmee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.UMEE_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.UMEE_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mEvmos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.EVMOS_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.EVMOS_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mCudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.CUDOS_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.CUDOS_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mProvenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.PROVENANCE_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.PROVENANCE_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mCerberus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.CERBERUS_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.CERBERUS_MAIN);
                }
                getDialog().dismiss();
            }
        });

        mOmniflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAdd) {
                    ((BaseActivity)getActivity()).onChainSelected(BaseChain.OMNIFLIX_MAIN);
                } else {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.OMNIFLIX_MAIN);
                }
                getDialog().dismiss();
            }
        });

        if (BaseChain.SUPPORT_CHAINS().contains(COSMOS_TEST)) {
            mCosmosTestLayer.setVisibility(View.VISIBLE);
            mCosmosTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.COSMOS_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.COSMOS_TEST);
                    }
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(IRIS_TEST)) {
            mIrisTestLayer.setVisibility(View.VISIBLE);
            mIrisTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.IRIS_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.IRIS_TEST);
                    }
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(ALTHEA_TEST)) {
            mAltheaTestLayer.setVisibility(View.VISIBLE);
            mAltheaTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.ALTHEA_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.ALTHEA_TEST);
                    }
                    getDialog().dismiss();
                }
            });
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
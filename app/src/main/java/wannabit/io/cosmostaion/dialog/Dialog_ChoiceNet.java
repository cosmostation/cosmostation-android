package wannabit.io.cosmostaion.dialog;

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

import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_TEST;

public class Dialog_ChoiceNet extends DialogFragment {


    private LinearLayout mBinanaceTestLayer, mIovTestLayer, mOKTestLayer, mCertikTestLayer, mCosmosTestLayer, mIrisTestLayer;
    private LinearLayout mMain, mIris, mBinance, mOkex, mKava, mIov, mBinanaceTest, mIovTest, mOKTest, mCertikTest, mTest12k, mTest13k;
    private LinearLayout mBand, mPersis, mCertik, mAkash, mSentinel, mFetch, mCryto, mSifchain, mKichain, mOsmosis, mMedi,
                         mEmoney, mRegen, mRizon, mJuno, mBitCanna, mAlthea, mStargaze, mGraBridge, mComdex, mBitsong, mInj, mSecret, mDesmos;
    private LinearLayout mRizonTestLayer, mMediTestLayer, mAltheaTestLayer, mUmeeTestLayer, mAxelarTestLayer;
    private LinearLayout mCosmosTest, mIrisTest, mRizonTest, mMediTest, mAltheaTest, mUmeeTest, mAxelarTest;

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
        mIris = view.findViewById(R.id.iris_net);
        mBinance = view.findViewById(R.id.binance_net);
        mOkex = view.findViewById(R.id.okex_net);
        mKava = view.findViewById(R.id.kava_net);
        mIov = view.findViewById(R.id.iov_net);

        mCosmosTestLayer = view.findViewById(R.id.cosmos_test_layer);
        mCosmosTest = view.findViewById(R.id.cosmos_test_net);
        mIrisTestLayer = view.findViewById(R.id.iris_test_layer);
        mIrisTest = view.findViewById(R.id.iris_test_net);
        mBinanaceTestLayer = view.findViewById(R.id.bnb_test_layer);
        mBinanaceTest = view.findViewById(R.id.bnb_test_net);

        mIovTestLayer = view.findViewById(R.id.iov_test_layer);
        mIovTest = view.findViewById(R.id.iov_test_net);
        mOKTestLayer = view.findViewById(R.id.ok_test_layer);
        mOKTest = view.findViewById(R.id.ok_test_net);
        mCertikTestLayer = view.findViewById(R.id.certik_test_layer);
        mCertikTest = view.findViewById(R.id.certik_test_net);
        mTest12k = view.findViewById(R.id.gaia_12k);
        mTest13k = view.findViewById(R.id.gaia_13k);

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

        mRizonTestLayer = view.findViewById(R.id.rizon_test_layer);
        mRizonTest = view.findViewById(R.id.rizon_test_net);
        mMediTestLayer = view.findViewById(R.id.medi_test_layer);
        mMediTest = view.findViewById(R.id.medi_test_net);
        mAltheaTestLayer = view.findViewById(R.id.althea_test_layer);
        mAltheaTest = view.findViewById(R.id.althea_test_net);
        mUmeeTestLayer = view.findViewById(R.id.umee_test_layer);
        mUmeeTest = view.findViewById(R.id.umee_test_net);
        mAxelarTestLayer = view.findViewById(R.id.axelar_test_layer);
        mAxelarTest = view.findViewById(R.id.axelar_test_net);

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

        if (BaseChain.SUPPORT_CHAINS().contains(BNB_TEST)) {
            mBinanaceTestLayer.setVisibility(View.VISIBLE);
            mBinanaceTest.setOnClickListener(new View.OnClickListener() {
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
        }

        if (BaseChain.SUPPORT_CHAINS().contains(IOV_TEST)) {
            mIovTestLayer.setVisibility(View.VISIBLE);
            mIovTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.IOV_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.IOV_TEST);
                    }
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(OK_TEST)) {
            mOKTestLayer.setVisibility(View.VISIBLE);
            mOKTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.OK_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.OK_TEST);
                    }
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(CERTIK_TEST)) {
            mCertikTestLayer.setVisibility(View.VISIBLE);
            mCertikTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.CERTIK_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.CERTIK_TEST);
                    }
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(RIZON_TEST)) {
            mRizonTestLayer.setVisibility(View.VISIBLE);
            mRizonTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.RIZON_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.RIZON_TEST);
                    }
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(MEDI_TEST)) {
            mMediTestLayer.setVisibility(View.VISIBLE);
            mMediTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.MEDI_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.MEDI_TEST);
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

        if (BaseChain.SUPPORT_CHAINS().contains(UMEE_TEST)) {
            mUmeeTestLayer.setVisibility(View.VISIBLE);
            mUmeeTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.UMEE_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.UMEE_TEST);
                    }
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(AXELAR_TEST)) {
            mAxelarTestLayer.setVisibility(View.VISIBLE);
            mAxelarTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsAdd) {
                        ((BaseActivity)getActivity()).onChainSelected(BaseChain.AXELAR_TEST);
                    } else {
                        ((BaseActivity)getActivity()).onChoiceNet(BaseChain.AXELAR_TEST);
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
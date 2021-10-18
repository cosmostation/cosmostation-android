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

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_TEST;

public class Dialog_ChoiceNet extends DialogFragment {


    private LinearLayout mKavaTestLayer, mBinanaceTestLayer, mIovTestLayer, mOKTestLayer, mCertikTestLayer, mCosmosTestLayer, mIrisTestLayer;
    private LinearLayout mMain, mIris, mBinance, mOkex, mKava, mIov, mBinanaceTest, mKavaTest, mIovTest, mOKTest, mCertikTest, mTest12k, mTest13k;
    private LinearLayout mBand, mPersis, mCertik, mAkash, mSentinel, mFetch, mCryto, mSifchain, mKichain, mOsmosis, mMedi, mEmoney, mRizon, mJuno, mSecret, mCosmosTest, mIrisTest;
    private LinearLayout mRizonTestLayer, mMediTestLayer, mAltheaTestLayer, mUmeeTestLayer, mAxelarTestLayer;
    private LinearLayout mRizonTest, mMediTest, mAltheaTest, mUmeeTest, mAxelarTest;

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
        mKavaTestLayer = view.findViewById(R.id.kava_test_layer);
        mKavaTest = view.findViewById(R.id.kava_test_net);
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




        mMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.COSMOS_MAIN);
                getDialog().dismiss();
            }
        });

        mIris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.IRIS_MAIN);
                getDialog().dismiss();
            }
        });

        mBinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.BNB_MAIN);
                getDialog().dismiss();
            }
        });

        mOkex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.OKEX_MAIN);
                getDialog().dismiss();
            }
        });

        mKava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.KAVA_MAIN);
                getDialog().dismiss();
            }
        });

        mIov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.IOV_MAIN);
                getDialog().dismiss();
            }
        });

        mBand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.BAND_MAIN);
                getDialog().dismiss();
            }
        });

        mPersis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(PERSIS_MAIN);
                getDialog().dismiss();
            }
        });

        mCertik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.CERTIK_MAIN);
                getDialog().dismiss();
            }
        });

        mAkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(AKASH_MAIN);
                getDialog().dismiss();
            }
        });

        mSentinel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(SENTINEL_MAIN);
                getDialog().dismiss();
            }
        });

        mFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(FETCHAI_MAIN);
                getDialog().dismiss();
            }
        });

        mCryto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(CRYPTO_MAIN);
                getDialog().dismiss();
            }
        });

        mSifchain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(SIF_MAIN);
                getDialog().dismiss();
            }
        });

        mKichain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(KI_MAIN);
                getDialog().dismiss();
            }
        });

        mOsmosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.OSMOSIS_MAIN);
                getDialog().dismiss();
            }
        });

        mMedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.MEDI_MAIN);
                getDialog().dismiss();
            }
        });

        mEmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.EMONEY_MAIN);
                getDialog().dismiss();
            }
        });

        mRizon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.RIZON_MAIN);
                getDialog().dismiss();
            }
        });

        mSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.SECRET_MAIN);
                getDialog().dismiss();
            }
        });

        mJuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onChoiceNet(BaseChain.JUNO_MAIN);
                getDialog().dismiss();
            }
        });


        if (BaseChain.SUPPORT_CHAINS().contains(COSMOS_TEST)) {
            mCosmosTestLayer.setVisibility(View.VISIBLE);
            mCosmosTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(COSMOS_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(IRIS_TEST)) {
            mIrisTestLayer.setVisibility(View.VISIBLE);
            mIrisTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(IRIS_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(BNB_TEST)) {
            mBinanaceTestLayer.setVisibility(View.VISIBLE);
            mBinanaceTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.BNB_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(KAVA_TEST)) {
            mKavaTestLayer.setVisibility(View.VISIBLE);
            mKavaTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(BaseChain.KAVA_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(IOV_TEST)) {
            mIovTestLayer.setVisibility(View.VISIBLE);
            mIovTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(IOV_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(OK_TEST)) {
            mOKTestLayer.setVisibility(View.VISIBLE);
            mOKTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(OK_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(CERTIK_TEST)) {
            mCertikTestLayer.setVisibility(View.VISIBLE);
            mCertikTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(CERTIK_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(RIZON_TEST)) {
            mRizonTestLayer.setVisibility(View.VISIBLE);
            mRizonTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(RIZON_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(MEDI_TEST)) {
            mMediTestLayer.setVisibility(View.VISIBLE);
            mMediTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(MEDI_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(ALTHEA_TEST)) {
            mAltheaTestLayer.setVisibility(View.VISIBLE);
            mAltheaTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(ALTHEA_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(UMEE_TEST)) {
            mUmeeTestLayer.setVisibility(View.VISIBLE);
            mUmeeTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(UMEE_TEST);
                    getDialog().dismiss();
                }
            });
        }

        if (BaseChain.SUPPORT_CHAINS().contains(AXELAR_TEST)) {
            mAxelarTestLayer.setVisibility(View.VISIBLE);
            mAxelarTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceNet(AXELAR_TEST);
                    getDialog().dismiss();
                }
            });
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

}
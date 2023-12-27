package wannabit.io.cosmostaion.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.CosmostationConstants.BINANCE_BUY_URL
import wannabit.io.cosmostaion.common.CosmostationConstants.EXPLORER_BASE_URL
import wannabit.io.cosmostaion.common.CosmostationConstants.KADO_MONEY_URL
import wannabit.io.cosmostaion.common.CosmostationConstants.KADO_PUBLIC_KEY
import wannabit.io.cosmostaion.common.CosmostationConstants.MOON_PAY_PUBLIC_KEY
import wannabit.io.cosmostaion.common.CosmostationConstants.MOON_PAY_URL
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentServiceBinding
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomFragment
import wannabit.io.cosmostaion.ui.tx.step.SwapFragment
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory
import java.net.URLEncoder

class ServiceFragment : Fragment() {

    private var _binding: FragmentServiceBinding? = null
    private val binding get() = _binding!!

    private val walletViewModel: WalletViewModel by activityViewModels()

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        responseMoonPay()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            listOf(
                mintscanView, coinSwapView, dappView, buyView
            ).forEach { it.setBackgroundResource(R.drawable.item_bg) }
        }
    }

    private fun openMoonPay() {
        val query = "?apiKey=$MOON_PAY_PUBLIC_KEY"
        walletViewModel.moonPay(MoonPayReq(query))
    }

    private fun responseMoonPay() {
        walletViewModel.moonPayResult.observe(viewLifecycleOwner) { signature ->
            val en = URLEncoder.encode(signature, "UTF-8")
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("$MOON_PAY_URL?apiKey=$MOON_PAY_PUBLIC_KEY&signature$en")
            )
            startActivity(intent)
        }

        walletViewModel.moonPayDataErrorMessage.observe(viewLifecycleOwner) {
            requireContext().makeToast(R.string.error_network_error)
            return@observe
        }
    }

    private fun openKado() {
        val query = "?apiKey=$KADO_PUBLIC_KEY"
        val urlKadoMoney = KADO_MONEY_URL + query
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlKadoMoney)))
        }, 500)
    }

    private fun openBinance() {
        val binanceUrl = BINANCE_BUY_URL
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(binanceUrl)))
        }, 500)
    }

    private fun clickAction() {
        binding.apply {
            mintscanView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_BASE_URL)))
            }

            coinSwapView.setOnClickListener {
                SwapFragment().show(
                    parentFragmentManager, SwapFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            dappView.setOnClickListener {

            }

            buyView.setOnClickListener {
                SettingBottomFragment(SettingType.BUY_CRYPTO).show(
                    parentFragmentManager, SettingBottomFragment::class.java.name
                )
                parentFragmentManager.setFragmentResultListener(
                    "crypto", this@ServiceFragment
                ) { _, bundle ->
                    when (bundle.getInt("crypto")) {
                        0 -> {
                            openMoonPay()
                        }

                        1 -> {
                            openKado()
                        }

                        else -> {
                            openBinance()
                        }
                    }
                }
                setClickableOnce(isClickable)
            }
        }
    }

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
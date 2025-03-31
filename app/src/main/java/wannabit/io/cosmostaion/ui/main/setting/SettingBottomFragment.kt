package wannabit.io.cosmostaion.ui.main.setting

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding
import wannabit.io.cosmostaion.ui.main.SettingType


class SettingBottomFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private var fromChain: BaseChain? = null
    private lateinit var settingType: SettingType

    private lateinit var settingAdapter: SettingBottomAdapter

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain?, settingType: SettingType
        ): SettingBottomFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putSerializable("settingType", settingType)
            }
            val fragment = SettingBottomFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonBottomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initRecyclerView()
    }

    private fun initView() {
        binding.apply {
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable("fromChain", BaseChain::class.java)?.let {
                        fromChain = it
                    }
                    getSerializable(
                        "settingType", SettingType::class.java
                    )?.let { settingType = it }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                    (getSerializable("settingType") as? SettingType)?.let {
                        settingType = it
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            when (settingType) {
                SettingType.LANGUAGE -> {
                    selectTitle.text = getString(R.string.str_select_language)
                    val languageList = listOf(
                        getString(R.string.str_system),
                        getString(R.string.title_language_en),
                        getString(R.string.title_language_kr),
                        getString(R.string.title_language_ja)
                    )

                    settingAdapter = SettingBottomAdapter(SettingType.LANGUAGE)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireActivity())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(languageList)

                    settingAdapter.setOnItemClickListener {
                        Prefs.language = it
                        Prefs.foreToBack = false
                        dismiss()
                        requireActivity().recreate()
                    }
                }

                SettingType.CURRENCY -> {
                    selectTitle.text = getString(R.string.str_select_currency)
                    val currencyList = resources.getStringArray(R.array.currency_unit_array)

                    settingAdapter = SettingBottomAdapter(SettingType.CURRENCY)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireActivity())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(currencyList.toList())

                    settingAdapter.setOnItemClickListener {
                        if (Prefs.currency != it) {
                            Prefs.currency = it
                        }
                        val bundle = Bundle()
                        bundle.putInt("currency", it)
                        parentFragmentManager.setFragmentResult("currency", bundle)
                        dismiss()
                    }
                }

                SettingType.PRICE_STATUS -> {
                    selectTitle.text = getString(R.string.title_price_change_color)

                    settingAdapter = SettingBottomAdapter(SettingType.PRICE_STATUS)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireActivity())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(listOf("Style1", "Style2"))

                    settingAdapter.setOnItemClickListener {
                        val bundle = Bundle()
                        bundle.putInt("priceStyle", it)
                        parentFragmentManager.setFragmentResult("priceStyle", bundle)
                        dismiss()
                    }
                }

                SettingType.BUY_CRYPTO -> {
                    selectTitle.text = getString(R.string.title_buy_crypto)
                    val buyCryptoList = listOf("MOONPAY", "KADO", "BINANCE")

                    settingAdapter = SettingBottomAdapter(SettingType.BUY_CRYPTO)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireActivity())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(buyCryptoList)

                    settingAdapter.setOnItemClickListener {
                        val bundle = Bundle()
                        bundle.putInt("crypto", it)
                        parentFragmentManager.setFragmentResult("crypto", bundle)
                        dismiss()
                    }
                }

                SettingType.DAPP_SORT_OPTION -> {
                    selectTitle.text = getString(R.string.title_select_option)
                    val buyCryptoList = listOf("Alphabet", "Multi")

                    settingAdapter = SettingBottomAdapter(
                        SettingType.DAPP_SORT_OPTION
                    )
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireActivity())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(buyCryptoList)

                    settingAdapter.setOnItemClickListener {
                        val bundle = Bundle()
                        bundle.putInt("sort", it)
                        parentFragmentManager.setFragmentResult("sort", bundle)
                        dismiss()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
package wannabit.io.cosmostaion.ui.main.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding
import wannabit.io.cosmostaion.ui.main.SettingType

class SettingBottomFragment(private val settingType: SettingType) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var settingAdapter: SettingBottomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonBottomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
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

                    settingAdapter = SettingBottomAdapter(requireContext(), SettingType.LANGUAGE)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(languageList)

                    settingAdapter.setOnItemClickListener {
                        Prefs.language = it
                        dismiss()
                        requireActivity().recreate()
                    }
                }

                SettingType.CURRENCY -> {
                    selectTitle.text = getString(R.string.str_select_currency)
                    val currencyList = resources.getStringArray(R.array.currency_unit_array)

                    settingAdapter = SettingBottomAdapter(requireContext(), SettingType.CURRENCY)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
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

                    settingAdapter =
                        SettingBottomAdapter(requireContext(), SettingType.PRICE_STATUS)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
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

                    settingAdapter = SettingBottomAdapter(requireContext(), SettingType.BUY_CRYPTO)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(buyCryptoList)

                    settingAdapter.setOnItemClickListener {
                        val bundle = Bundle()
                        bundle.putInt("crypto", it)
                        parentFragmentManager.setFragmentResult("crypto", bundle)
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
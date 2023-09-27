package wannabit.io.cosmostaion.ui.main.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding
import wannabit.io.cosmostaion.ui.main.MainActivity
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
                    val languageList = listOf(getString(R.string.str_system), getString(R.string.str_language_en), getString(R.string.str_language_kr))

                    settingAdapter = SettingBottomAdapter(requireContext(), SettingType.LANGUAGE)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(languageList)

                    settingAdapter.setOnItemClickListener {
                        BaseUtils.updateResources(requireContext())
                        Prefs.language = it
                        dismiss()
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
                    selectTitle.text = getString(R.string.str_price_change_color)
                    val priceStyleList = listOf("Style1", "Style2")

                    settingAdapter = SettingBottomAdapter(requireContext(), SettingType.PRICE_STATUS)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(priceStyleList)

                    settingAdapter.setOnItemClickListener {
                        if (Prefs.priceStyle != it) {
                            Prefs.priceStyle = it
                        }
                        val bundle = Bundle()
                        bundle.putInt("price", it)
                        parentFragmentManager.setFragmentResult("price", bundle)
                        dismiss()
                    }
                }

                SettingType.AUTO_PASS -> {
                    selectTitle.text = getString(R.string.str_auto_pass)
                    val autoPassList = listOf(getString(R.string.str_never), getString(R.string.str_5_min), getString(R.string.str_10_min), getString(R.string.str_30_min))

                    settingAdapter = SettingBottomAdapter(requireContext(), SettingType.AUTO_PASS)
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = LinearLayoutManager(requireContext())
                    recycler.adapter = settingAdapter
                    settingAdapter.submitList(autoPassList)

                    settingAdapter.setOnItemClickListener {
                        if (Prefs.autoPass != it) {
                            Prefs.autoPass = it
                        }
                        val bundle = Bundle()
                        bundle.putInt("autoPass", it)
                        parentFragmentManager.setFragmentResult("autoPass", bundle)
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
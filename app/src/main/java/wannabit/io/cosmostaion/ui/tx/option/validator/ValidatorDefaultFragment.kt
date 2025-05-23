package wannabit.io.cosmostaion.ui.tx.option.validator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.staking.v1beta1.StakingProto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInitia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.fetcher.moveValidatorName
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding


interface ValidatorDefaultListener {
    fun select(validatorAddress: String)
}

class ValidatorDefaultFragment(
    private val selectedChain: BaseChain,
    private val fromValidator: StakingProto.Validator? = null,
    private val fromInitiaValidator: com.initia.mstaking.v1.StakingProto.Validator? = null,
    private val fromZenrockValidator: com.zrchain.validation.HybridValidationProto.ValidatorHV? = null,
    private val suiFromValidator: MutableList<JsonObject>? = null,
    private val iotaFromValidator: MutableList<JsonObject>? = null,
    private val finalityProvider: MutableList<FinalityProvider>? = null,
    val listener: ValidatorDefaultListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var validatorDefaultAdapter: ValidatorDefaultAdapter
    private lateinit var suiValidatorDefaultAdapter: SuiValidatorAdapter
    private lateinit var iotaValidatorDefaultAdapter: IotaValidatorAdapter
    private lateinit var finalityProviderAdapter: FinalityProviderAdapter

    private var searchValidators: MutableList<StakingProto.Validator> = mutableListOf()
    private var searchInitiaValidators: MutableList<com.initia.mstaking.v1.StakingProto.Validator> =
        mutableListOf()
    private var searchZenrockValidators: MutableList<com.zrchain.validation.HybridValidationProto.ValidatorHV> =
        mutableListOf()
    private var searchSuiValidators: MutableList<JsonObject> = mutableListOf()
    private var searchIotaValidators: MutableList<JsonObject> = mutableListOf()
    private var searchProviders: MutableList<FinalityProvider> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonBottomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initSearchView()
    }

    private fun initView() {
        binding.apply {
            if (selectedChain is ChainBitCoin86) {
                selectTitle.text = getString(R.string.title_select_Provider)
                searchView.queryHint = getString(R.string.str_search_provider)
            } else {
                selectTitle.text = getString(R.string.title_select_validator)
                searchView.queryHint = getString(R.string.str_search_validator)
            }
            searchBar.visibility = View.VISIBLE

            when (selectedChain) {
                is ChainInitia -> selectedChain.initiaFetcher()?.initiaValidators?.filterNot { it == fromInitiaValidator }
                    ?.let {
                        searchInitiaValidators.addAll(it)
                    }

                is ChainZenrock -> selectedChain.zenrockFetcher()?.zenrockValidators?.filterNot { it == fromZenrockValidator }
                    ?.let { searchZenrockValidators.addAll(it) }

                else -> selectedChain.cosmosFetcher?.cosmosValidators?.filterNot { it == fromValidator }
                    ?.let { searchValidators.addAll(it) }
            }
            if (suiFromValidator != null) {
                searchSuiValidators.addAll(suiFromValidator)
            }
            if (iotaFromValidator != null) {
                searchIotaValidators.addAll(iotaFromValidator)
            }
            if (finalityProvider != null) {
                searchProviders.addAll(finalityProvider)
            }

            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            if (suiFromValidator != null) {
                suiValidatorDefaultAdapter = SuiValidatorAdapter()
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = suiValidatorDefaultAdapter
                suiValidatorDefaultAdapter.submitList(searchSuiValidators)

                suiValidatorDefaultAdapter.setOnItemClickListener {
                    listener.select(it)
                    dismiss()
                }

            } else if (iotaFromValidator != null) {
                iotaValidatorDefaultAdapter = IotaValidatorAdapter(requireContext())
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = iotaValidatorDefaultAdapter
                iotaValidatorDefaultAdapter.submitList(searchIotaValidators)

                iotaValidatorDefaultAdapter.setOnItemClickListener {
                    listener.select(it)
                    dismiss()
                }

            } else if (finalityProvider != null) {
                finalityProviderAdapter = FinalityProviderAdapter(selectedChain)
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = finalityProviderAdapter
                finalityProviderAdapter.submitList(searchProviders)

                finalityProviderAdapter.setOnItemClickListener {
                    listener.select(it)
                    dismiss()
                }

            } else {
                validatorDefaultAdapter = ValidatorDefaultAdapter(selectedChain)
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = validatorDefaultAdapter

                when (selectedChain) {
                    is ChainInitia -> {
                        validatorDefaultAdapter.submitList(searchInitiaValidators as List<Any>?)
                        validatorDefaultAdapter.setOnItemClickListener {
                            listener.select(it)
                            dismiss()
                        }
                    }

                    is ChainZenrock -> {
                        validatorDefaultAdapter.submitList(searchZenrockValidators as List<Any>?)
                        validatorDefaultAdapter.setOnItemClickListener {
                            listener.select(it)
                            dismiss()
                        }
                    }

                    else -> {
                        validatorDefaultAdapter.submitList(searchValidators as List<Any>?)
                        validatorDefaultAdapter.setOnItemClickListener {
                            listener.select(it)
                            dismiss()
                        }
                    }
                }
            }
        }
    }

    private fun initSearchView() {
        binding.apply {
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchValidators.clear()
                    searchInitiaValidators.clear()
                    searchZenrockValidators.clear()
                    searchSuiValidators.clear()
                    searchIotaValidators.clear()

                    if (StringUtils.isEmpty(newText)) {
                        if (suiFromValidator != null) {
                            searchSuiValidators.addAll(suiFromValidator)
                            suiValidatorDefaultAdapter.notifyDataSetChanged()

                        } else if (iotaFromValidator != null) {
                            searchIotaValidators.addAll(iotaFromValidator)
                            iotaValidatorDefaultAdapter.notifyDataSetChanged()

                        } else {
                            when (selectedChain) {
                                is ChainInitia -> {
                                    selectedChain.initiaFetcher()?.initiaValidators?.filterNot { it == fromInitiaValidator }
                                        ?.let {
                                            searchInitiaValidators.addAll(it)
                                        }
                                }

                                is ChainZenrock -> {
                                    selectedChain.zenrockFetcher()?.zenrockValidators?.filterNot { it == fromZenrockValidator }
                                        ?.let { searchZenrockValidators.addAll(it) }
                                }

                                else -> {
                                    selectedChain.cosmosFetcher?.cosmosValidators?.filterNot { it == fromValidator }
                                        ?.let { searchValidators.addAll(it) }

                                }
                            }
                            validatorDefaultAdapter.notifyDataSetChanged()
                        }

                    } else {
                        newText?.let { searchTxt ->
                            if (suiFromValidator != null) {
                                suiFromValidator.filter { validator ->
                                    validator.moveValidatorName()
                                        .contains(searchTxt, ignoreCase = true)
                                }.let { searchSuiValidators.addAll(it) }
                                suiValidatorDefaultAdapter.notifyDataSetChanged()

                            } else if (iotaFromValidator != null) {
                                iotaFromValidator.filter { validator ->
                                    validator.moveValidatorName()
                                        .contains(searchTxt, ignoreCase = true)
                                }.let { searchIotaValidators.addAll(it) }
                                iotaValidatorDefaultAdapter.notifyDataSetChanged()

                            } else {
                                when (selectedChain) {
                                    is ChainInitia -> {
                                        selectedChain.initiaFetcher()?.initiaValidators?.filterNot { it == fromInitiaValidator }
                                            ?.filter { validator ->
                                                validator.description.moniker.contains(
                                                    searchTxt, ignoreCase = true
                                                )
                                            }?.let { searchInitiaValidators.addAll(it) }

                                    }

                                    is ChainZenrock -> {
                                        selectedChain.zenrockFetcher()?.zenrockValidators?.filterNot { it == fromZenrockValidator }
                                            ?.filter { validator ->
                                                validator.description.moniker.contains(
                                                    searchTxt, ignoreCase = true
                                                )
                                            }?.let { searchZenrockValidators.addAll(it) }
                                    }

                                    else -> {
                                        selectedChain.cosmosFetcher?.cosmosValidators?.filterNot { it == fromValidator }
                                            ?.filter { validator ->
                                                validator.description.moniker.contains(
                                                    searchTxt, ignoreCase = true
                                                )
                                            }?.let { searchValidators.addAll(it) }
                                    }
                                }
                                validatorDefaultAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                    return true
                }
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
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
import wannabit.io.cosmostaion.chain.fetcher.suiValidatorName
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding


interface ValidatorDefaultListener {
    fun select(validatorAddress: String)
}

class ValidatorDefaultFragment(
    private val selectedChain: BaseChain,
    private val fromValidator: StakingProto.Validator?,
    private val fromInitiaValidator: com.initia.mstaking.v1.StakingProto.Validator?,
    private val suiFromValidator: MutableList<JsonObject>?,
    val listener: ValidatorDefaultListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var validatorDefaultAdapter: ValidatorDefaultAdapter
    private lateinit var suiValidatorDefaultAdapter: SuiValidatorAdapter

    private var searchValidators: MutableList<StakingProto.Validator> = mutableListOf()
    private var searchInitiaValidators: MutableList<com.initia.mstaking.v1.StakingProto.Validator> =
        mutableListOf()
    private var searchSuiValidators: MutableList<JsonObject> = mutableListOf()

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
            selectTitle.text = getString(R.string.title_select_validator)
            searchBar.visibility = View.VISIBLE
            searchView.queryHint = getString(R.string.str_search_validator)
            if (selectedChain is ChainInitiaTestnet) {
                selectedChain.initiaFetcher()?.initiaValidators?.filterNot { it == fromInitiaValidator }
                    ?.let {
                        searchInitiaValidators.addAll(it)
                    }
            } else {
                selectedChain.cosmosFetcher?.cosmosValidators?.filterNot { it == fromValidator }
                    ?.let { searchValidators.addAll(it) }
            }
            if (suiFromValidator != null) {
                searchSuiValidators.addAll(suiFromValidator)
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

            } else {
                validatorDefaultAdapter = ValidatorDefaultAdapter(selectedChain)
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = validatorDefaultAdapter

                if (selectedChain is ChainInitiaTestnet) {
                    validatorDefaultAdapter.submitList(searchInitiaValidators as List<Any>?)
                    validatorDefaultAdapter.setOnItemClickListener {
                        listener.select(it)
                        dismiss()
                    }

                } else {
                    validatorDefaultAdapter.submitList(searchValidators as List<Any>?)
                    validatorDefaultAdapter.setOnItemClickListener {
                        listener.select(it)
                        dismiss()
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
                    searchSuiValidators.clear()

                    if (StringUtils.isEmpty(newText)) {
                        if (suiFromValidator != null) {
                            searchSuiValidators.addAll(suiFromValidator)
                            suiValidatorDefaultAdapter.notifyDataSetChanged()
                        } else {
                            selectedChain.cosmosFetcher?.cosmosValidators?.filterNot { it == fromValidator }
                                ?.let { searchValidators.addAll(it) }
                            validatorDefaultAdapter.notifyDataSetChanged()
                        }

                    } else {
                        newText?.let { searchTxt ->
                            if (suiFromValidator != null) {
                                suiFromValidator.filter { validator ->
                                    validator.suiValidatorName()
                                        .contains(searchTxt, ignoreCase = true)
                                }.let { searchSuiValidators.addAll(it) }
                                suiValidatorDefaultAdapter.notifyDataSetChanged()

                            } else {
                                selectedChain.cosmosFetcher?.cosmosValidators?.filterNot { it == fromValidator }
                                    ?.filter { validator ->
                                        validator.description.moniker.contains(
                                            searchTxt, ignoreCase = true
                                        )
                                    }?.let { searchValidators.addAll(it) }
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
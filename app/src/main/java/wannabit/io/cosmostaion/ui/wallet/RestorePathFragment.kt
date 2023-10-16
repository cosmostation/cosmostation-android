package wannabit.io.cosmostaion.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.FragmentRestorePathBinding
import wannabit.io.cosmostaion.ui.dialog.path.HdPathSelectDialog
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel

class RestorePathFragment(val name: String, val mnemonic: String) : Fragment() {

    private var _binding: FragmentRestorePathBinding? = null
    private val binding get() = _binding!!

    private lateinit var restorePathAdapter: RestorePathAdapter

    private val accountViewModel: AccountViewModel by activityViewModels()

    private var lastHDPath = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestorePathBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        updateView()
        checkCreate()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            val wordList = mnemonic.split(" ")
            recycler.setBackgroundResource(R.drawable.item_bg)
            pathView.setBackgroundResource(R.drawable.item_bg)
            backdropLayout.visibility = View.GONE

            restorePathAdapter = RestorePathAdapter(requireContext())
            recycler.setHasFixedSize(true)
            recycler.layoutManager = GridLayoutManager(requireContext(), 3)
            recycler.adapter = restorePathAdapter
            restorePathAdapter.submitList(wordList)
        }
    }

    private fun updateView() {
        binding.apply {
            hdPath.text = lastHDPath

            val cosmosHdPath = "m/44'/118'/0'/0/X"
            cosmosLastHdPath.text = cosmosHdPath
            val dpCosmosHdPath = cosmosHdPath.replace("X", lastHDPath)
            cosmosLastHdPath.text = dpCosmosHdPath

            val etherHdPath = "m/44'/60'/0'/0/X"
            etherLastHdPath.text = etherHdPath
            val dpEtherHdPath = etherHdPath.replace("X", lastHDPath)
            etherLastHdPath.text = dpEtherHdPath

            val suiHdPath = "m/44'/784'/0'/0'/X'"
            suiLastHdPath.text = suiHdPath
            val dpSuiHdPath = suiHdPath.replace("X", lastHDPath)
            suiLastHdPath.text = dpSuiHdPath
        }
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            pathLayout.setOnClickListener {
                val numberPickerDialog = HdPathSelectDialog { selectedValue ->
                    lastHDPath = selectedValue.toString()
                    updateView()
                }
                numberPickerDialog.show(parentFragmentManager, HdPathSelectDialog::class.java.name)
            }

            btnCreateAccount.setOnClickListener {
                requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.color_background_dialog)
                backdropLayout.visibility = View.VISIBLE
                accountViewModel.createByMnemonic(name, mnemonic, lastHDPath)
            }
        }
    }

    private fun checkCreate() {
        accountViewModel.create.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                delay(2000)
                withContext(Dispatchers.Main) {
                    binding.backdropLayout.visibility = View.GONE
                    requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.color_transparent)
                    ApplicationViewModel.shared.currentAccount()

                    requireActivity().finish()
                    requireActivity().overridePendingTransition(0, 0)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
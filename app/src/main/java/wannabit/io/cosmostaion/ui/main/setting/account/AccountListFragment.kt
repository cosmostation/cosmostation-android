package wannabit.io.cosmostaion.ui.main.setting.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentAccountListBinding
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel

class AccountListFragment : Fragment() {

    private var _binding: FragmentAccountListBinding? = null
    private val binding get() = _binding!!

    private lateinit var accountListAdapter: AccountListAdapter

    private val accountViewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        clickAction()
        checkAccountData()
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val appDatabase = AppDatabase.getInstance()
                val selectAllAccount = appDatabase.baseAccountDao().selectAll()

                withContext(Dispatchers.Main) {
                    accountListAdapter =
                        AccountListAdapter(requireContext(), popupClickAction)
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = accountListAdapter
                    accountListAdapter.submitList(selectAllAccount)
                }
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnAddAccount.setOnClickListener {

            }
        }
    }

    private fun checkAccountData() {
        accountViewModel.baseAccounts.observe(viewLifecycleOwner) { result ->
            accountListAdapter = AccountListAdapter(requireContext(), popupClickAction)
            binding.recycler.setHasFixedSize(true)
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())
            binding.recycler.adapter = accountListAdapter
            accountListAdapter.submitList(result)
        }
    }

    private val popupClickAction = object : AccountListAdapter.ClickListener {
        override fun checkMnemonicAction(account: BaseAccount) {
            ApplicationViewModel.shared.checkPwMnemonicResult.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    delay(300)
                    withContext(Dispatchers.Main) {
                        requireActivity().toMoveFragment(this@AccountListFragment, MnemonicCheckFragment(account), "MnemonicCheck")
                    }
                }
            }
        }

        override fun checkPrivateAction(account: BaseAccount) {
            ApplicationViewModel.shared.checkPwPrivateResult.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    delay(300)
                    withContext(Dispatchers.Main) {
                        requireActivity().toMoveFragment(this@AccountListFragment, PrivateCheckFragment(account), "PrivateCheck")
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
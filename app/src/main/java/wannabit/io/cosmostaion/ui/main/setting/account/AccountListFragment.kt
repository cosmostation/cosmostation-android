package wannabit.io.cosmostaion.ui.main.setting.account

import android.os.Bundle
import android.os.Handler
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
import wannabit.io.cosmostaion.database.model.BaseAccountType
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
                val mnemonicAccounts = selectAllAccount.filter { it.type == BaseAccountType.MNEMONIC }
                val privateAccounts = selectAllAccount.filter { it.type == BaseAccountType.PRIVATE_KEY }

                withContext(Dispatchers.Main) {
                    accountListAdapter =
                        AccountListAdapter(requireContext(), mnemonicAccounts, privateAccounts, popupClickAction)
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
            var isClickable = true
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnAddAccount.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    val bottomSheet = AccountInitFragment(this@AccountListFragment)
                    bottomSheet.show(parentFragmentManager, AccountInitFragment::class.java.name)

                    Handler().postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }
        }
    }

    private fun checkAccountData() {
        accountViewModel.baseAccounts.observe(viewLifecycleOwner) { result ->
            val mnemonicAccounts = result.filter { it.type == BaseAccountType.MNEMONIC }
            val privateAccounts = result.filter { it.type == BaseAccountType.PRIVATE_KEY }

            accountListAdapter = AccountListAdapter(requireContext(), mnemonicAccounts, privateAccounts, popupClickAction)
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
package wannabit.io.cosmostaion.ui.wallet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.databinding.FragmentCreateMnemonicBinding
import wannabit.io.cosmostaion.databinding.PopupWordMenuBinding
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel

class CreateMnemonicFragment : Fragment() {

    private var _binding: FragmentCreateMnemonicBinding? = null
    private val binding get() = _binding!!

    private var initType: Int = 0

    private lateinit var createMnemonicAdapter: CreateMnemonicAdapter

    private val accountViewModel: AccountViewModel by activityViewModels()

    private var mnemonic: String = ""

    companion object {
        @JvmStatic
        fun newInstance(
            initType: Int
        ): CreateMnemonicFragment {
            val args = Bundle().apply {
                putInt("initType", initType)
            }
            val fragment = CreateMnemonicFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateMnemonicBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
        checkCreate()
    }

    private fun initView() {
        binding.apply {
            cautionView.setBackgroundResource(R.drawable.item_bg)
            recycler.setBackgroundResource(R.drawable.item_bg)
            wordCnt.text = getString(R.string.str_24_words)
            updateView(32)
            arguments?.getInt("initType")?.let { initType = it }
        }
    }

    private fun updateView(size: Int) {
        binding.apply {
            val entropy = BaseKey.getEntropy(size)
            val wordList = BaseKey.getRandomMnemonic(entropy)
            mnemonic = Utils.bytesToHex(entropy)

            createMnemonicAdapter = CreateMnemonicAdapter(requireContext())
            recycler.setHasFixedSize(true)
            recycler.layoutManager = GridLayoutManager(requireContext(), 3)
            recycler.adapter = createMnemonicAdapter
            createMnemonicAdapter.submitList(wordList)
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                if (initType == 0) {
                    requireActivity().onBackPressed()
                } else {
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }

            btnWords.setOnClickListener {
                showPopupMenu(it, wordCnt)
            }

            btnCreateAccount.setOnClickListener {
                CreateNameFragment().show(
                    parentFragmentManager, CreateNameFragment::class.java.name
                )
                parentFragmentManager.setFragmentResultListener(
                    "create", this@CreateMnemonicFragment
                ) { _, bundle ->
                    bundle.getString("create")?.let { name ->
                        requireActivity().runOnUiThread {
                            requireActivity().window.statusBarColor = ContextCompat.getColor(
                                requireContext(), R.color.color_background_dialog
                            )
                            backdropLayout.visibility = View.VISIBLE
                            accountViewModel.createByMnemonic(name, mnemonic, "0")
                        }
                    }
                }
            }
        }
    }

    private fun showPopupMenu(view: View, wordTxt: TextView) {
        val inflater =
            view.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = PopupWordMenuBinding.inflate(inflater)
        val popupView = binding.root

        val popupWindow = PopupWindow(
            popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true
        popupWindow.showAsDropDown(view)

        binding.apply {
            btn12Words.setOnClickListener {
                wordTxt.text = getString(R.string.str_12_words)
                popupWindow.dismiss()
                updateView(16)
            }

            btn18Words.setOnClickListener {
                wordTxt.text = getString(R.string.str_18_words)
                popupWindow.dismiss()
                updateView(24)
            }

            btn24Words.setOnClickListener {
                wordTxt.text = getString(R.string.str_24_words)
                popupWindow.dismiss()
                updateView(32)
            }
        }
    }

    private fun checkCreate() {
        accountViewModel.create.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                delay(2000)
                withContext(Dispatchers.Main) {
                    binding.backdropLayout.visibility = View.GONE
                    requireActivity().window.statusBarColor =
                        ContextCompat.getColor(requireContext(), R.color.color_transparent)
                    ApplicationViewModel.shared.currentAccount(BaseData.baseAccount, true)

                    startToActivity()
                }
            }
        }
    }

    private fun startToActivity() {
        if (initType == BaseConstant.CONST_NEW_ACCOUNT) {
            requireActivity().overridePendingTransition(0, 0)
            requireActivity().finish()
        } else {
            Intent(requireActivity(), MainActivity::class.java).apply {
                startActivity(this)
            }
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
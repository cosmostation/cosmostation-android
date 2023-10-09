package wannabit.io.cosmostaion.ui.dialog.account

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.trustwallet.walletconnect.extensions.toHex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentPrivateCheckBinding

class PrivateCheckFragment(private val account: BaseAccount) :  Fragment() {

    private var _binding: FragmentPrivateCheckBinding? = null
    private val binding get() = _binding!!

    private lateinit var privateAdapter: PrivateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrivateCheckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
    }

    private fun initView() {
        binding.apply {
            accountNameView.setBackgroundResource(R.drawable.item_bg)
            privateKeyLayout.setBackgroundResource(R.drawable.item_bg)

            account.apply {
                accountName.text = name

                CoroutineScope(Dispatchers.IO).launch {
                    if (account.type == BaseAccountType.MNEMONIC) {
                        privateKeyLayout.visibility = View.GONE
                        tapMsg.visibility = View.GONE
                        recycler.visibility = View.VISIBLE

                        privateAdapter = PrivateAdapter(account)
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = privateAdapter
                        privateAdapter.submitList(account.initOnlyKeyData() as List<Any>?)

                    } else {
                        privateKeyLayout.visibility = View.VISIBLE
                        tapMsg.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                        requireActivity().runOnUiThread {
                            accountPrivateKey.text = "0x" + privateKey?.toHex()
                        }
                    }
                }
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            privateKeyLayout.setOnClickListener {
                val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("address", "0x" + account.privateKey?.toHex())
                clipboard.setPrimaryClip(clip)
                requireActivity().makeToast(R.string.str_msg_mnemonic_copied)
            }

            btnConfirm.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.FragmentAddressBookManageOptionBinding

class AddressBookManageOptionFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBookManageOptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var addressBook: AddressBook

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(addressBook: AddressBook): AddressBookManageOptionFragment {
            val args = Bundle().apply {
                putParcelable("addressBook", addressBook)
            }
            val fragment = AddressBookManageOptionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBookManageOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("addressBook", AddressBook::class.java)
                ?.let { addressBook = it }

        } else {
            (arguments?.getParcelable("addressBook") as? AddressBook)?.let {
                addressBook = it
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            editLayout.setOnClickListener {
                val toChain = allChains().firstOrNull { it.tag == addressBook.chainName }
                handleOneClickWithDelay(
                    SetAddressFragment.newInstance(
                        addressBook,
                        toChain,
                        "",
                        "",
                        AddressBookType.ManualEdit
                    )
                )
            }

            deleteLayout.setOnClickListener {
                handleOneClickWithDelay(
                    DeleteAddressBookFragment(addressBook)
                )
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
        dismiss()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
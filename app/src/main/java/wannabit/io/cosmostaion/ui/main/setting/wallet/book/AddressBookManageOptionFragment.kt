package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.FragmentAddressBookManageOptionBinding

class AddressBookManageOptionFragment(
    private val addressBook: AddressBook
) : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBookManageOptionBinding? = null
    private val binding get() = _binding!!

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBookManageOptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickAction()
    }

    private fun setUpClickAction() {
        binding.apply {
            editLayout.setOnClickListener {
                SetAddressFragment(addressBook, null, "", "").show(
                    requireActivity().supportFragmentManager, SetAddressFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            deleteLayout.setOnClickListener {
                DeleteAddressBookFragment(addressBook).show(
                    requireActivity().supportFragmentManager, SetAddressFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }
        }
    }

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
        dismiss()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
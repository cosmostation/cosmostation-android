package wannabit.io.cosmostaion.ui.init

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.hideKeyboard
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.databinding.FragmentCreateNameBinding

class CreateNameFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCreateNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickAction()
    }

    private fun setUpClickAction() {
        binding.apply {
            btnConfirm.setOnClickListener {
                accountName.text.toString().trim().let { name ->
                    if (name.isEmpty()) {
                        requireActivity().makeToast(R.string.error_account_name)
                        return@setOnClickListener
                    }
                    requireActivity().hideKeyboard(btnConfirm)

                    CoroutineScope(Dispatchers.IO).launch {
                        val appDatabase = AppDatabase.getInstance()
                        val selectAllAccount = appDatabase.baseAccountDao().selectAll()
                        if (selectAllAccount.firstOrNull { it.name.equals(name, true) } != null) {
                            withContext(Dispatchers.Main) {
                                requireActivity().makeToast(R.string.error_exist_account_name)
                                return@withContext
                            }

                        } else {
                            val bundle = Bundle()
                            bundle.putString("create", name)
                            parentFragmentManager.setFragmentResult("create", bundle)
                            dismiss()
                        }
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
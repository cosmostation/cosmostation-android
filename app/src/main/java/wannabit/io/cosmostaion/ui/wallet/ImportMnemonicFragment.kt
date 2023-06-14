package wannabit.io.cosmostaion.ui.wallet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import wannabit.io.cosmostaion.databinding.FragmentWalletCreateBinding
import wannabit.io.cosmostaion.ui.main.DashboardActivity

class ImportMnemonicFragment : Fragment() {
    private lateinit var binding: FragmentWalletCreateBinding
    private lateinit var adapter: WalletCreateMnemonicAdapter
    private val viewModel: WalletCreateViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWalletCreateBinding.inflate(layoutInflater, container, false)
        setupViews()
        setupRecyclerView()
        setupViewModels()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.generateMnemonic()
    }

    private fun setupRecyclerView() {
        context?.let {
            adapter = WalletCreateMnemonicAdapter(it)
            binding.recycler.layoutManager = GridLayoutManager(it, 3)
            binding.recycler.adapter = adapter
        }
    }

    private fun setupViewModels() {
        viewModel.mnemonic.observe(viewLifecycleOwner) {
            adapter.words.clear()
            adapter.words.addAll(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.generated.observe(viewLifecycleOwner) { generated ->
            if (!generated) {
                return@observe
            }

            activity?.let {
                startActivity(Intent(it, DashboardActivity::class.java))
                it.finish()
            }
        }
    }

    private fun setupViews() {
//        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                viewModel.mnemonic.value?.let { mnemonic ->
//                    viewModel.createByMnemonic(
//                        binding.name.text.toString(), mnemonic
//                    )
//                }
//            }
//        }
//            if (binding.name.text?.isEmpty() == true) {
//                Toast.makeText(this, "Empty !", Toast.LENGTH_LONG).show()
//                return@setOnClickListener
//            }
//            resultLauncher.launch(Intent(this, PinActivity::class.java))

        binding.nextBtn.setOnClickListener {
            viewModel.create("Test")
        }
    }
}
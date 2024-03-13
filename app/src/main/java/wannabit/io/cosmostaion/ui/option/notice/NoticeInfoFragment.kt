package wannabit.io.cosmostaion.ui.option.notice

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.databinding.FragmentNoticeInfoBinding

class NoticeInfoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNoticeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): NoticeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = NoticeInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticeInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", CosmosLine::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? CosmosLine)?.let {
                selectedChain = it
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnGithub.setOnClickListener {
                val githubUrl = if (selectedChain is EthereumLine) {
                    "https://github.com/cosmostation/chainlist/blob/main/chain/" + selectedChain.apiName + "/erc20.json"
                } else {
                    "https://github.com/cosmostation/chainlist/blob/main/chain/" + selectedChain.apiName + "/cw20.json"
                }
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl)))
            }

            btnConfirm.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
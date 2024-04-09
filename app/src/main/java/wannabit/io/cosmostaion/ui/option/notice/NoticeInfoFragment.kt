package wannabit.io.cosmostaion.ui.option.notice

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.databinding.FragmentNoticeInfoBinding

class NoticeInfoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNoticeInfoBinding? = null
    private val binding get() = _binding!!

    private var selectedChain: CosmosLine? = null
    private lateinit var noticeType: NoticeType

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine?, noticeType: NoticeType): NoticeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("noticeType", noticeType)
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
        arguments?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getParcelable("selectedChain", CosmosLine::class.java)?.let { selectedChain = it }
                getSerializable(
                    "noticeType", NoticeType::class.java
                )?.let { noticeType = it }

            } else {
                (getParcelable("selectedChain") as? CosmosLine)?.let {
                    selectedChain = it
                }
                (getSerializable("noticeType") as? NoticeType)?.let {
                    noticeType = it
                }
            }
        }

        binding.apply {
            when (noticeType) {
                NoticeType.NODE_DOWN_GUIDE -> {
                    dialogTitle.text = ""
                    nodeLayout.visibility = View.VISIBLE
                    btnGithub.visibility = View.GONE
                    binding.dialogMsg.text = getString(R.string.str_node_down_msg)
                }

                NoticeType.TOKEN_GITHUB -> {
                    dialogTitle.text = getString(R.string.str_token_github)
                    nodeLayout.visibility = View.GONE
                    btnGithub.visibility = View.VISIBLE
                    btnGithub.text = getString(R.string.title_github)
                    binding.dialogMsg.text = getString(R.string.str_token_github_msg)
                }

                NoticeType.CHAIN_SUNSET -> {
                    dialogTitle.text = getString(R.string.title_sunset)
                    nodeLayout.visibility = View.GONE
                    btnGithub.visibility = View.VISIBLE
                    btnGithub.text = "Link"
                    binding.dialogMsg.text = getString(R.string.str_sunset_msg)
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnGithub.setOnClickListener {
                when (noticeType) {
                    NoticeType.NODE_DOWN_GUIDE -> {
                        return@setOnClickListener
                    }

                    NoticeType.TOKEN_GITHUB -> {
                        val githubUrl = if (selectedChain is EthereumLine) {
                            "https://github.com/cosmostation/chainlist/blob/main/chain/" + selectedChain?.apiName + "/erc20.json"
                        } else {
                            "https://github.com/cosmostation/chainlist/blob/main/chain/" + selectedChain?.apiName + "/cw20.json"
                        }
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl)))
                    }

                    NoticeType.CHAIN_SUNSET -> {
                        val link = if (selectedChain is ChainBinanceBeacon) {
                            "https://www.bnbchain.org/en/bnb-chain-fusion"
                        } else {
                            "https://crescentnetwork.medium.com/flip-announcement-af24c8ab7e7f"
                        }
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
                    }
                }
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

enum class NoticeType { TOKEN_GITHUB, NODE_DOWN_GUIDE, CHAIN_SUNSET }
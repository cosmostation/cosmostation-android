package wannabit.io.cosmostaion.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.FragmentNoticeInfoBinding

interface NodeDownSelectListener {
    fun select(tag: String?)

    fun changeEndpoint(tag: String?)
}

class NoticeInfoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNoticeInfoBinding? = null
    private val binding get() = _binding!!

    private var selectedChain: BaseChain? = null
    private lateinit var noticeType: NoticeType

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain?, noticeType: NoticeType, listener: NodeDownSelectListener?): NoticeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("noticeType", noticeType)
            }
            val fragment = NoticeInfoFragment()
            fragment.arguments = args
            fragment.nodeDownSelectListener = listener
            return fragment
        }
    }

    private var nodeDownSelectListener: NodeDownSelectListener? = null

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
                getParcelable("selectedChain", BaseChain::class.java)?.let { selectedChain = it }
                getSerializable(
                    "noticeType", NoticeType::class.java
                )?.let { noticeType = it }

            } else {
                (getParcelable("selectedChain") as? BaseChain)?.let {
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
                    btnGithub.text = getString(R.string.str_change_endpoint)
                    btnConfirm.text = getString(R.string.str_retry)
                    binding.dialogMsg.text = getString(R.string.str_node_down_msg)
                }

                NoticeType.TOKEN_GITHUB -> {
                    dialogTitle.text = getString(R.string.str_token_github)
                    nodeLayout.visibility = View.GONE
                    btnGithub.visibility = View.VISIBLE
                    btnGithub.text = getString(R.string.title_github)
                    btnConfirm.text = getString(R.string.str_confirm)
                    binding.dialogMsg.text = getString(R.string.str_token_github_msg)
                }

                NoticeType.CHAIN_SUNSET -> {
                    dialogTitle.text = getString(R.string.title_sunset)
                    nodeLayout.visibility = View.GONE
                    btnGithub.visibility = View.VISIBLE
                    btnGithub.text = "Link"
                    btnConfirm.text = getString(R.string.str_confirm)
                    binding.dialogMsg.text = getString(R.string.str_sunset_msg)
                }

                NoticeType.TOKEN_NFT_GITHUB -> {
                    dialogTitle.text = getString(R.string.str_nft_github)
                    nodeLayout.visibility = View.GONE
                    btnGithub.visibility = View.VISIBLE
                    btnGithub.text = getString(R.string.title_github)
                    btnConfirm.text = getString(R.string.str_confirm)
                    binding.dialogMsg.text = getString(R.string.str_nft_github_msg)
                }

                NoticeType.CHAIN_DELIST -> {
                    dialogTitle.text = getString(R.string.title_delist)
                    nodeLayout.visibility = View.GONE
                    btnGithub.visibility = View.GONE
                    btnConfirm.text = getString(R.string.str_confirm)
                    binding.dialogMsg.text = getString(R.string.str_delist_msg)
                }

                NoticeType.LEGACY_PATH -> {
                    dialogTitle.text = getString(R.string.title_legacy_path)
                    nodeLayout.visibility = View.GONE
                    btnGithub.visibility = View.GONE
                    btnConfirm.text = getString(R.string.str_confirm)
                    binding.dialogMsg.text = getString(R.string.str_legacy_path_msg)
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnGithub.setOnClickListener {
                when (noticeType) {
                    NoticeType.NODE_DOWN_GUIDE -> {
                        nodeDownSelectListener?.changeEndpoint(selectedChain?.tag)
                        dismiss()
                    }

                    NoticeType.TOKEN_NFT_GITHUB -> {
                        val githubUrl =
                            "https://github.com/cosmostation/chainlist/blob/main/chain/" + selectedChain?.apiName + "/cw721.json"
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl)))
                    }

                    else -> {}
                }
            }

            btnConfirm.setOnClickListener {
                if (noticeType == NoticeType.NODE_DOWN_GUIDE) {
                    nodeDownSelectListener?.select(selectedChain?.tag)
                    dismiss()

                } else {
                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class NoticeType { TOKEN_GITHUB, NODE_DOWN_GUIDE, CHAIN_SUNSET, TOKEN_NFT_GITHUB, CHAIN_DELIST, LEGACY_PATH }
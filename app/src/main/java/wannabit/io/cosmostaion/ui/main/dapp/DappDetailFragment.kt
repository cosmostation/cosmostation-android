package wannabit.io.cosmostaion.ui.main.dapp

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.CustomDappNetworkBinding
import wannabit.io.cosmostaion.databinding.FragmentDappDetailBinding

interface DappPinSelectListener {
    fun pinned(id: Int)
}

class DappDetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDappDetailBinding? = null
    private val binding get() = _binding!!

    private var selectChain: String? = "All Network"
    private lateinit var ecosystem: JsonObject
    private var isPinned = false

    companion object {
        @JvmStatic
        fun newInstance(
            selectChain: String, ecosystem: String, listener: DappPinSelectListener
        ): DappDetailFragment {
            val args = Bundle().apply {
                putString("selectChain", selectChain)
                putString("ecosystem", ecosystem)
            }
            val fragment = DappDetailFragment()
            fragment.arguments = args
            fragment.dappPinSelectListener = listener
            return fragment
        }
    }

    private var dappPinSelectListener: DappPinSelectListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDappDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            arguments?.apply {
                selectChain = getString("selectChain")
                ecosystem = Gson().fromJson(getString("ecosystem"), JsonObject::class.java)
            }

            btnHideView.setBackgroundResource(R.drawable.button_enable_select_bg)
            btnDapp.setBackgroundResource(R.drawable.button_common_bg)
            btnClose.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(
                    requireContext(), R.color.color_base03
                ), PorterDuff.Mode.SRC_IN
            )

            if (Prefs.getPinnedDapps().contains(ecosystem["id"].asInt)) {
                isPinned = true
                btnPin.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_accent_yellow
                    ), PorterDuff.Mode.SRC_IN
                )

            } else {
                isPinned = false
                btnPin.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base05
                    ), PorterDuff.Mode.SRC_IN
                )
            }

            val url = ecosystem["thumbnail"].asString
            dappImg.post {
                val width = dappImg.width
                val height = (width * 3) / 4
                dappImg.layoutParams.height = height
                dappImg.requestLayout()
                Picasso.get().load(url).error(R.drawable.icon_default_dapp).into(dappImg)
                dappImg.clipToOutline = true
            }

            val link = ecosystem["link"].asString
            val urlLink = if (link.contains("https://")) {
                link.replace("https://", "")
            } else {
                link
            }
            dappUrl.text = urlLink
            dappName.text = ecosystem["name"].asString
            dappTypeBadge.text = ecosystem["type"].asString
            dappDescription.text = ecosystem["description"].asString

            supportNetworkView.removeAllViews()
            val networkInflater = LayoutInflater.from(requireContext())
            val filteredEcosystems = JsonArray().apply {
                ecosystem["chains"].asJsonArray
                    .map { it.asString }
                    .filter { chainName ->
                        allChains().any { it.apiName == chainName }
                    }
                    .forEach { add(JsonPrimitive(it)) }
            }

            filteredEcosystems.forEach { supportChain ->
                val view =
                    CustomDappNetworkBinding.inflate(networkInflater, supportNetworkView, false)
                allChains().firstOrNull { it.apiName == supportChain.asString }?.let { chain ->
                    view.chainImg.setChainLogo(chain)
                    view.chainName.text = chain.name.uppercase()
                }
                supportNetworkView.addView(view.root)
            }

            listOf(btnGithub, btnTelegram, btnTwitter, btnDiscord, btnReddit).forEach {
                it.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_base04
                    ), PorterDuff.Mode.SRC_IN
                )
            }
            btnGithub.visibleOrGone(ecosystem["socials"].asJsonObject.has("github"))
            btnTelegram.visibleOrGone(ecosystem["socials"].asJsonObject.has("telegram"))
            btnTwitter.visibleOrGone(ecosystem["socials"].asJsonObject.has("twitter"))
            btnDiscord.visibleOrGone(ecosystem["socials"].asJsonObject.has("discord"))
            btnReddit.visibleOrGone(ecosystem["socials"].asJsonObject.has("reddit"))
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnClose.setOnClickListener {
                dappPinSelectListener?.pinned(ecosystem["id"].asInt)
                dismiss()
            }

            btnPin.setOnClickListener {
                isPinned = !isPinned
                if (isPinned) {
                    btnPin.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(
                            requireContext(), R.color.color_accent_yellow
                        ), PorterDuff.Mode.SRC_IN
                    )

                } else {
                    btnPin.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(
                            requireContext(), R.color.color_base05
                        ), PorterDuff.Mode.SRC_IN
                    )
                }

                val id = ecosystem["id"].asInt
                val pinnedDapps = Prefs.getPinnedDapps()
                if (pinnedDapps.contains(id)) {
                    pinnedDapps.removeIf { it == id }
                } else {
                    pinnedDapps.add(id)
                }
                Prefs.setPinnedDapps(pinnedDapps)
            }

            btnGithub.setOnClickListener {
                val github = ecosystem["socials"].asJsonObject["github"].asString
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(github)))
            }

            btnTelegram.setOnClickListener {
                val telegram = ecosystem["socials"].asJsonObject["telegram"].asString
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(telegram)))
            }

            btnTwitter.setOnClickListener {
                val twitter = ecosystem["socials"].asJsonObject["twitter"].asString
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(twitter)))
            }

            btnDiscord.setOnClickListener {
                val discord = ecosystem["socials"].asJsonObject["discord"].asString
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(discord)))
            }

            btnReddit.setOnClickListener {
                val reddit = ecosystem["socials"].asJsonObject["reddit"].asString
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(reddit)))
            }

            btnHideView.setOnClickListener {
                val id = ecosystem["id"].asInt
                Prefs.setDappHideTime(id)
                dappPinSelectListener?.pinned(id)
                dismiss()
            }

            btnDapp.setOnClickListener {
                Intent(requireActivity(), DappActivity::class.java).apply {
                    val chain = if (ecosystem["chains"].asJsonArray.size() == 1) {
                        allChains().firstOrNull { it.apiName == ecosystem["chains"].asJsonArray[0].asString }
                    } else if (selectChain?.uppercase() != "All Network".uppercase()) {
                        allChains().firstOrNull { it.apiName == selectChain }
                    } else {
                        allChains().firstOrNull { chain -> chain.apiName == ecosystem["chains"].asJsonArray.first().asString }
                    }

                    putExtra("selectedChain", chain as Parcelable)
                    putExtra("dapp", ecosystem["link"].asString)
                    startActivity(this)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = bottomSheetDialogDefaultHeight(windowHeight())
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun bottomSheetDialogDefaultHeight(windowHeight: Int): Int {
        return windowHeight * 17 / 20
    }

    private fun windowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let { sheet ->
            val behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = true

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> dismiss()
                        BottomSheetBehavior.STATE_COLLAPSED, BottomSheetBehavior.STATE_DRAGGING -> {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }

                        else -> {}
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
package wannabit.io.cosmostaion.fragment.txs.common

import android.app.Activity
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.QRcodeActivity
import wannabit.io.cosmostaion.activities.txs.common.SendActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.dao.Asset
import wannabit.io.cosmostaion.dao.MintscanToken
import wannabit.io.cosmostaion.dao.NameService
import wannabit.io.cosmostaion.dao.NameService.NameServiceType
import wannabit.io.cosmostaion.databinding.FragmentSendStep0Binding
import wannabit.io.cosmostaion.dialog.CommonAlertDialog
import wannabit.io.cosmostaion.dialog.IBCReceiveAccountsDialog
import wannabit.io.cosmostaion.dialog.NameConfirmDialog
import wannabit.io.cosmostaion.dialog.SelectChainListDialog
import wannabit.io.cosmostaion.model.SendViewModel
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WUtil
import wannabit.io.cosmostaion.utils.makeToast
import wannabit.io.cosmostaion.utils.visibleOrGone

class SendStep0Fragment : BaseFragment() {

    private var _binding: FragmentSendStep0Binding? = null
    private val binding get() = _binding!!
    private val sendViewModel: SendViewModel by viewModels()

    private val mToSendableChains = ArrayList<ChainConfig>()
    private var mToSendChainConfig: ChainConfig? = null
    private var mToAccountList: ArrayList<Account>? = null
    private var mAsset: Asset? = null
    private var mMintscanToken: MintscanToken? = null

    private val mNameServices = ArrayList<NameService>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSendStep0Binding.inflate(layoutInflater, container, false)

        mAsset = baseDao.getAsset(getSActivity().mChainConfig, getSActivity().mDenom)
        mMintscanToken = baseDao.getCw20Asset(getSActivity().mChainConfig, getSActivity().mDenom)
        mToSendableChains.add(getSActivity().mChainConfig)

        val allChainConfig = ChainFactory.SUPPRT_CONFIG()
        for (asset in baseDao.mAssets) {
            if (mAsset != null) {
                if (asset.chain.equals(getSActivity().mChainConfig.chainName(), ignoreCase = true) && asset.denom.equals(getSActivity().mDenom, ignoreCase = true)) {
                    for (chainConfig in allChainConfig) {
                        if (chainConfig.chainName().equals(asset.beforeChain(getSActivity().mChainConfig), ignoreCase = true) && !mToSendableChains.contains(chainConfig)) {
                            mToSendableChains.add(chainConfig)
                        }
                    }
                } else if (asset.counter_party != null && asset.counter_party.denom.equals(getSActivity().mDenom, ignoreCase = true)) {
                    for (chainConfig in allChainConfig) {
                        if (chainConfig.chainName().equals(asset.chain, ignoreCase = true) && !mToSendableChains.contains(chainConfig)) {
                            mToSendableChains.add(chainConfig)
                        }
                    }
                }

            } else if (mMintscanToken != null) {
                if (asset.counter_party != null && asset.counter_party.denom.equals(mMintscanToken!!.address, ignoreCase = true)) {
                    for (chainConfig in allChainConfig) {
                        if (chainConfig.chainName().equals(asset.chain, ignoreCase = true)) {
                            mToSendableChains.add(chainConfig)
                        }
                    }
                }
            }
        }
        onSortToChain()
        onLoadObserve()

        mToSendChainConfig = mToSendableChains[0]
        mToSendChainConfig?.let {
            mToAccountList = baseDao.onSelectAccountsExceptSelfByChain(it.baseChain(), getSActivity().mAccount)
        }
        return binding.root
    }

    private fun onSetIbcImgLayout() {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            private var alreadyOpen: Boolean = false
            private val defaultKeyboardHeightDP: Int = 100
            private val EstimatedKeyboardDP = defaultKeyboardHeightDP + if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) 48 else 0
            private val rect = Rect()
            override fun onGlobalLayout() {
                val estimatedKeyboardHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP.toFloat(), binding.root.resources.displayMetrics).toInt()
                binding.root.getWindowVisibleDisplayFrame(rect)
                val heightDiff: Int = binding.root.rootView.height - (rect.bottom - rect.top)
                val isShown = heightDiff >= estimatedKeyboardHeight
                if (isShown == alreadyOpen) return

                alreadyOpen = isShown
                if (alreadyOpen) {
                    binding.ibcLayer.visibility = View.GONE
                } else {
                    Handler().postDelayed({
                        mToSendChainConfig?.let {
                            binding.ibcLayer.visibleOrGone(!it.baseChain().equals(getSActivity().mBaseChain))
                        }
                    }, 100)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onUpdateChainView()
        onSetIbcImgLayout()
        onClick()
    }

    private fun onUpdateChainView() {
        mToSendChainConfig?.let { chainConfig ->
            binding.apply {
                imgToChain.setImageResource(chainConfig.chainImg())
                txtToChain.text = chainConfig.chainTitleToUp()
                txtToChain.setTextColor(ContextCompat.getColor(requireContext(), chainConfig.chainColor()))
                receiverAccount.setText("")
                ibcLayer.visibleOrGone(!chainConfig.baseChain().equals(getSActivity().mBaseChain))

//                if (!chainConfig.baseChain().equals(getSActivity().mBaseChain)) {
//                    val assetPath = WDp.getAssetPath(baseDao, getSActivity().mChainConfig, mToSendChainConfig, getSActivity().mDenom)
//                    sendViewModel.loadPendingTotal(getSActivity().mChainConfig, assetPath)
//                    sendViewModel.loadLastPing(requireContext(), assetPath, baseDao.chainIdGrpc)
//                }
            }
        }
    }

    private fun onClick() {
        binding.apply {
            btnToChainList.setOnClickListener {
                Bundle().apply {
                    putSerializable(SelectChainListDialog.TO_SENDABLE_CHAIN_CONFIG_BUNDLE_KEY, mToSendableChains)
                    putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_IBC_CHAIN_VALUE)
                    SelectChainListDialog.newInstance(this).show(parentFragmentManager, SelectChainListDialog::class.java.name)

                    parentFragmentManager.setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, requireActivity()) { _, result ->
                        mToSendChainConfig = mToSendableChains[result.getInt(BaseConstant.POSITION)]
                        mToSendChainConfig?.let {
                            mToAccountList = baseDao.onSelectAccountsExceptSelfByChain(it.baseChain(), getSActivity().mAccount)
                            onUpdateChainView()
                        }
                    }
                }
                getSActivity().onHideKeyboard()
            }

            btnWallet.setOnClickListener {
                if (mToSendChainConfig == null || (mToAccountList?.size?: 0) <= 0) {
                    requireContext().makeToast(R.string.error_no_wallet_this_chain)
                    return@setOnClickListener
                } else {
                    Bundle().apply {
                        putSerializable(IBCReceiveAccountsDialog.ACCOUNTS_BUNDLE_KEY, mToAccountList)
                        IBCReceiveAccountsDialog.newInstance(this).show(parentFragmentManager, IBCReceiveAccountsDialog::class.java.name)

                        parentFragmentManager.setFragmentResultListener(IBCReceiveAccountsDialog.IBC_RECEIVE_ACCOUNTS_BUNDLE_KEY, requireActivity()) { _, result ->
                            mToAccountList?.let {
                                binding.receiverAccount.setText(it[result.getInt(BaseConstant.POSITION)].address)
                            }
                        }
                    }
                }
            }

            btnQr.setOnClickListener {
                val integrator = IntentIntegrator.forSupportFragment(this@SendStep0Fragment)
                integrator.setOrientationLocked(true)
                integrator.captureActivity = QRcodeActivity::class.java
                qrCodeResultLauncher.launch(integrator.createScanIntent())
            }

            btnPaste.setOnClickListener {
                val clipboard = getSActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                if (clipboard.primaryClip != null && clipboard.primaryClip!!.itemCount > 0) {
                    val userPaste = clipboard.primaryClip!!.getItemAt(0).coerceToText(requireContext()).toString().trim()
                    if (userPaste.isEmpty()) {
                        requireContext().makeToast(R.string.error_clipboard_no_data)
                        return@setOnClickListener
                    } else {
                        receiverAccount.setText(userPaste)
                        receiverAccount.setSelection(receiverAccount.text.length)
                    }
                } else {
                    requireContext().makeToast(R.string.error_clipboard_no_data)
                    return@setOnClickListener
                }
            }

            btnCancel.setOnClickListener {
                getSActivity().onBeforeStep()
            }

            btnNext.setOnClickListener {
                mNameServices.clear()
                receiverAccount.text.trim().toString().apply {
                    if (getSActivity().mAccount.address.equals(this, ignoreCase = true)) {
                        requireContext().makeToast(R.string.error_self_sending)
                        return@setOnClickListener
                    }

                    if (WUtil.isValidStarName(this.lowercase())) {
                        mToSendChainConfig?.let {
                            onPathSetting()
                            sendViewModel.loadResources(it, this.lowercase())
                            return@setOnClickListener
                        }
                    }

                    if (WDp.isValidChainAddress(mToSendChainConfig, this)) {
                        if (!isExchangeAddress(this)) {
                            CommonAlertDialog.showSingleButton(
                                activity,
                                Html.fromHtml("<font color=\"#f31963\">" + getString(R.string.str_empty_warnning_title) + "</font>", Html.FROM_HTML_MODE_COMPACT),
                                getString(R.string.error_exchange_address_msg),
                                getString(R.string.str_confirm),
                                null,
                                false
                            )
                            return@setOnClickListener
                        }
                        onPathSetting()
                        getSActivity().mToAddress = this
                        getSActivity().onNextStep()
                    } else {
                        mToSendChainConfig?.let {
                            sendViewModel.loadIcnsAddress(it, userInput(it))
                        }
                        onPathSetting()
                    }
                }
            }
        }
    }

    private val qrCodeResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            binding.receiverAccount.setText(result.data?.getStringExtra(Intents.Scan.RESULT)?.trim())
            binding.receiverAccount.setSelection(binding.receiverAccount.text.length)
        }
    }

    private fun onLoadObserve() {
        sendViewModel.resource.observe(requireActivity()) { resources ->
            mToSendChainConfig?.let {
                val matchAddress = resources[0].address
                if (matchAddress.isEmpty()) {
                    requireContext().makeToast(R.string.error_no_mattched_starname)
                    return@observe
                }
                onSetICNS(it, resources, matchAddress)
            }
        }

        sendViewModel.nameServices.observe(requireActivity()) { nameServices ->
            mToSendChainConfig?.let {
                if (nameServices.size <= 0) {
                    requireContext().makeToast(R.string.error_invalid_icns)
                    return@observe
                }
                if (nameServices.size == 2 && nameServices[0].address.equals(nameServices[1].address, ignoreCase = true)) {
                    nameServices[0].type = NameServiceType.ICNS_STARGAZE
                    nameServices.removeAt(nameServices.size - 1)
                }
                onSetICNS(it, nameServices, nameServices[0].address)
            }
        }

//        sendViewModel.lastPing.observe(requireActivity()) {
//            binding.lastPingUpdate.text = WDp.getTimeGap(requireContext(), it)
//        }
//
//        sendViewModel.pendingTotal.observe(requireActivity()) {
//            binding.pendingCount.text = it.toString()
//        }
    }

    private fun onPathSetting() {
        mToSendChainConfig?.let { chainConfig ->
            if (getSActivity().mBaseChain == chainConfig.baseChain()) {
                if (mMintscanToken == null) getSActivity().mTxType = BaseConstant.CONST_PW_TX_SIMPLE_SEND
                else {
                    mMintscanToken?.let {
                        if (it.address.startsWith("0x")) getSActivity().mTxType = BaseConstant.CONST_PW_TX_EVM_TRANSFER
                        else getSActivity().mTxType = BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT
                    }
                }

            } else {
                if (mAsset != null) getSActivity().mTxType = BaseConstant.CONST_PW_TX_IBC_TRANSFER
                else if (mMintscanToken != null) getSActivity().mTxType = BaseConstant.CONST_PW_TX_IBC_CONTRACT

                getSActivity().mAssetPath = WDp.getAssetPath(baseDao, getSActivity().mChainConfig, mToSendChainConfig, getSActivity().mDenom)
            }
        }
        getSActivity().mAsset = mAsset
        getSActivity().mMintscanToken = mMintscanToken
    }

    private fun isExchangeAddress(userInput: String): Boolean {
        return !(WUtil.getExchangeAddressList().contains(userInput) && !userInput.startsWith(getSActivity().mChainConfig.addressPrefix()))
    }

    private fun userInput(chainConfig: ChainConfig): String {
        chainConfig.let {
            binding.receiverAccount.text.trim().toString().let { userInput ->
                if (userInput.contains("." + it.addressPrefix())) {
                    return userInput
                } else if (userInput.contains(".")) {
                    return userInput + it.addressPrefix()
                } else {
                    return userInput + "." + it.addressPrefix()
                }
            }
        }
    }

    private fun onSetICNS(chainConfig: ChainConfig, nameServiceList: ArrayList<NameService>, matchAddress: String) {
        Bundle().apply {
            putSerializable(NameConfirmDialog.MATCH_NAME_SERVICE_BUNDLE_KEY, nameServiceList)
            val dialog = NameConfirmDialog.newInstance(this)
            dialog.show(parentFragmentManager, NameConfirmDialog::class.java.name)

            parentFragmentManager.setFragmentResultListener(NameConfirmDialog.CONFIRM_BUNDLE_KEY, requireActivity()) { _, result ->
                if (getSActivity().mAccount.address.equals(matchAddress, ignoreCase = true)) {
                    requireContext().makeToast(R.string.error_self_sending)
                    dialog.dismiss()
                    return@setFragmentResultListener
                }

                if (!WDp.isValidChainAddress(chainConfig, matchAddress)) {
                    requireContext().makeToast(R.string.error_invalid_icns_address)
                    dialog.dismiss()
                    return@setFragmentResultListener
                }

                getSActivity().mToAddress = nameServiceList[result.getInt(BaseConstant.POSITION)].address
                getSActivity().onNextStep()
            }
        }
    }

    private fun onSortToChain() {
        mToSendableChains.sortWith(Comparator { o1, o2 ->
            if (o1.baseChain() == getSActivity().mBaseChain) return@Comparator -1
            if (o2.baseChain() == getSActivity().mBaseChain) return@Comparator 1
            if (o1.baseChain() == BaseChain.COSMOS_MAIN) return@Comparator -1
            if (o2.baseChain() == BaseChain.COSMOS_MAIN) return@Comparator 1
            if (o1.baseChain() == BaseChain.OSMOSIS_MAIN) return@Comparator -1
            if (o2.baseChain() == BaseChain.OSMOSIS_MAIN) return@Comparator 1
            0
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getSActivity(): SendActivity {
        return baseActivity as SendActivity
    }
}
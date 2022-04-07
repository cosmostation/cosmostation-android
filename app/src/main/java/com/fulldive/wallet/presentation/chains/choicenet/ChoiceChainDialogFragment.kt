package com.fulldive.wallet.presentation.chains.choicenet

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fulldive.wallet.extensions.orFalse
import com.fulldive.wallet.extensions.unsafeLazy
import com.fulldive.wallet.interactors.ScreensInteractor
import com.fulldive.wallet.interactors.accounts.AccountsInteractor
import com.fulldive.wallet.presentation.accounts.AddAccountDialogFragment
import com.fulldive.wallet.presentation.base.BaseMvpDialogFragment
import com.joom.lightsaber.getInstance
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain


class ChoiceChainDialogFragment : BaseMvpDialogFragment() {
    private val isAddNet by unsafeLazy { arguments?.getBoolean(KEY_ADD, false).orFalse() }
    private val requestCode by unsafeLazy { arguments?.getString(KEY_REQUEST_CODE).orEmpty() }
    private val chains: List<String> by unsafeLazy {
        arguments?.getStringArrayList(KEY_CHAINS).orEmpty()
    }

    private var resultSent = false
    private var adapter: ChainListAdapter? = null
    private lateinit var screensInteractor: ScreensInteractor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
        screensInteractor = getInjector().getInstance()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_list, null)
        val items = BaseChain.values().toList()
            .filter { chain ->
                chain.isSupported && (chains.isEmpty() || chains.contains(chain.chain))
            }

        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.findViewById<RecyclerView?>(R.id.recyclerView).also { recyclerView ->
            recyclerView.layoutManager = linearLayout
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    linearLayout.orientation
                )
            )
            recyclerView.setHasFixedSize(true)
            adapter = ChainListAdapter(items, ::onChainClicked)
            recyclerView.adapter = adapter
        }
        return AlertDialog.Builder(activity)
            .setView(view)
            .create()
    }

    override fun onDestroy() {
        sendResult(Unit)
        super.onDestroy()
    }

    private fun sendResult(result: Any) {
        if (!resultSent) {
            resultSent = true
            screensInteractor.sendResult(requestCode, result)
        }
    }

    private fun onChainClicked(chain: BaseChain) {
        sendResult(chain)
        (activity as? BaseActivity)?.apply {
            if (isAddNet) {
                onChainSelected(chain)
            } else {
                onChoiceNet(chain)
            }
        } ?: let {
            if (isAddNet) {
                val accountsInteractor = getInjector().getInstance<AccountsInteractor>()
                if (accountsInteractor.getAccountsByChain(chain).size >= 5) {
                    showMessage(R.string.error_max_account_number)
                    return
                } else {
                    showDialog(
                        AddAccountDialogFragment.newInstance(
                            chain.chain
                        )
                    )
                }
            }
        }
        dialog?.dismiss()
    }

    companion object {
        private const val KEY_ADD = "KEY_ADD"
        private const val KEY_REQUEST_CODE = "KEY_REQUEST_CODE"
        private const val KEY_CHAINS = "KEY_CHAINS"

        @JvmOverloads
        fun newInstance(
            isAdd: Boolean = true,
            requestCode: String = "",
            chains: List<String> = emptyList()
        ) = ChoiceChainDialogFragment().apply {
            arguments = bundleOf(
                KEY_ADD to isAdd,
                KEY_REQUEST_CODE to requestCode,
                KEY_CHAINS to chains
            )
        }
    }
}
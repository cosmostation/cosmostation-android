package com.fulldive.wallet.presentation.chains.choicenet

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain


class ChoiceNetDialogFragment : DialogFragment() {
    private var isAddNet = false
    private var adapter: ChainListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_list, null)
        isAddNet = arguments != null
        val items = BaseChain.values().toList().filter(BaseChain::isSupported)
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
        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        return builder.create()
    }

    private fun onChainClicked(chain: BaseChain) {
        (requireActivity() as? BaseActivity)?.apply {
            if (isAdded) {
                onChainSelected(chain)
            } else {
                onChoiceNet(chain)
            }
        }
        dialog?.dismiss()
    }

    companion object {
        fun newInstance(bundle: Bundle?) = ChoiceNetDialogFragment().apply {
            arguments = bundle
        }
    }
}
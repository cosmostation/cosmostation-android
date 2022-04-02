package com.fulldive.wallet.presentation.chains.osmo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import com.fulldive.wallet.extensions.safe
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import osmosis.lockup.Lock.PeriodLock
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.chains.osmosis.EarningDetailActivity
import wannabit.io.cosmostaion.utils.OsmosisPeriodLockWrapper

class OsmoUnbondingAllDialogFragment : BottomSheetDialogFragment() {
    private val single by lazy(LazyThreadSafetyMode.NONE) {
        arguments?.getByteArray(KEY_SINGLE)
            ?: throw IllegalStateException("argument single can't be null")
    }

    private val data by lazy(LazyThreadSafetyMode.NONE) {
        arguments?.getSerializable(KEY_DATA)
            ?: throw IllegalStateException("argument data can't be null")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_osmo_unbonding_all, container, false)
        var msg = ""

        val lockupWrapper = data as OsmosisPeriodLockWrapper
        val lockups = lockupWrapper.array
        for (lock in lockups) {
            msg = msg + "# " + lock.id + "  "
        }

        view.findViewById<TextView>(R.id.lock_ids).text = msg
        view.findViewById<Button>(R.id.btn_all).setOnClickListener {
            (activity as? EarningDetailActivity)?.onStartUnbonding(lockups)
            dismiss()
        }
        view.findViewById<Button>(R.id.btn_one).setOnClickListener { v: View? ->
            safe {
                val lockup = PeriodLock.parseFrom(single)
                (activity as? EarningDetailActivity)?.onStartUnbonding(arrayListOf(lockup))
            }
            dismiss()
        }
        return view
    }

    companion object {
        private const val KEY_DATA = "data"
        private const val KEY_SINGLE = "single"

        fun netInstance(
            data: OsmosisPeriodLockWrapper,
            single: ByteArray
        ): OsmoUnbondingAllDialogFragment {
            return OsmoUnbondingAllDialogFragment().apply {
                arguments = bundleOf(
                    KEY_DATA to data,
                    KEY_SINGLE to single
                )
            }
        }
    }
}
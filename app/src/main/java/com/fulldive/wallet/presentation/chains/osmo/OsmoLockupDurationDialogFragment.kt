package com.fulldive.wallet.presentation.chains.osmo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.chains.osmosis.EarningDetailActivity

class OsmoLockupDurationDialogFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_osmo_lockup_duration, container, false)

        view.findViewById<Button>(R.id.btn_1day).setOnClickListener {
            startNewEarning(DURATION_1DAY)
        }
        view.findViewById<Button>(R.id.btn_7day).setOnClickListener {
            startNewEarning(DURATION_7DAY)
        }
        view.findViewById<Button>(R.id.btn_14day).setOnClickListener {
            startNewEarning(DURATION_14DAY)
        }
        return view
    }

    private fun startNewEarning(duration: Long) {
        (activity as? EarningDetailActivity)?.onStartNewEarning(duration)
        dismiss()
    }

    companion object {
        private const val DURATION_1DAY = 86400L
        private const val DURATION_7DAY = 604800L
        private const val DURATION_14DAY = 1209600L

        fun newInstance() = OsmoLockupDurationDialogFragment()
    }
}
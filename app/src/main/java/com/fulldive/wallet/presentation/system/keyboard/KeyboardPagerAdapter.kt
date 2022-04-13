package com.fulldive.wallet.presentation.system.keyboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import wannabit.io.cosmostaion.fragment.AlphabetKeyBoardFragment
import wannabit.io.cosmostaion.fragment.KeyboardFragment
import wannabit.io.cosmostaion.fragment.NumberKeyBoardFragment
import wannabit.io.cosmostaion.utils.WLog

class KeyboardPagerAdapter(
    fragmentManager: FragmentManager,
    listener: KeyboardListener
) :
    FragmentPagerAdapter(fragmentManager) {
    val fragments: List<KeyboardFragment>

    init {
        WLog.w("KeyboardPagerAdapter.init")
        fragments = listOf(
            NumberKeyBoardFragment.newInstance().apply {
                setListener(listener)
            },
            AlphabetKeyBoardFragment.newInstance().apply {
                setListener(listener)
            }
        )
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}
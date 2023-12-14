package wannabit.io.cosmostaion.ui.main.setting.account

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.database.model.AddressBook

class DeleteAddressBookFragment(
    private val addressBook: AddressBook?,
    private val recipientLine: CosmosLine?,
    private val recipientAddress: String?,
    private val memo: String?,
    val listener: AddressBookRegisterListener
) : BottomSheetDialogFragment() {

}

package wannabit.io.cosmostaion.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cosmos.bank.v1beta1.QueryGrpc
import com.cosmos.bank.v1beta1.QueryProto
import com.cosmos.base.query.v1beta1.PaginationProto
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bitcoinj.core.Bech32
import org.bitcoinj.core.ECKey
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import org.bitcoinj.crypto.MnemonicCode
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.Chain
import wannabit.io.cosmostaion.database.model.ChainConfig
import java.math.BigInteger
import java.util.concurrent.TimeUnit

class DashboardViewModel : ViewModel() {
    val balances = MutableLiveData<List<String>>()

    fun loadBalances() = CoroutineScope(Dispatchers.IO).launch {

    }
}
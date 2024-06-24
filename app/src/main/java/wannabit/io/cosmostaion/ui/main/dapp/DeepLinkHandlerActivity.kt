package wannabit.io.cosmostaion.ui.main.dapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.ui.intro.IntroActivity

class DeepLinkHandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BaseData.isBackGround) {
            Intent(this, DappActivity::class.java).apply {
                putExtra("dappUrl", intent.data.toString())
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(this)
            }

        } else {
            if (BaseData.appSchemeUrl.isEmpty()) {
                Intent(this, IntroActivity::class.java).apply {
                    BaseData.appSchemeUrl = intent.data.toString()
                    putExtra("dappUrl", intent.data.toString())
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(this)
                }
            } else {
                Intent(this, DappActivity::class.java).apply {
                    putExtra("dappUrl", intent.data.toString())
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(this)
                }
            }
        }
        finish()
    }
}
package wannabit.io.cosmostaion.activities.txs.neutron

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import wannabit.io.cosmostaion.databinding.ActivityDaoProposalDetailBinding
import wannabit.io.cosmostaion.databinding.ActivityVaultListBinding

class DaoProposalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDaoProposalDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaoProposalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
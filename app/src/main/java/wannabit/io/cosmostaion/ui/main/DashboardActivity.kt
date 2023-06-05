package wannabit.io.cosmostaion.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import wannabit.io.cosmostaion.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
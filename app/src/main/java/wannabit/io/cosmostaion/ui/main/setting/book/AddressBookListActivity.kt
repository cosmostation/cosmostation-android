package wannabit.io.cosmostaion.ui.main.setting.book

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.data.repository.address.AddressRepositoryImpl
import wannabit.io.cosmostaion.databinding.ActivityAddressBookListBinding
import wannabit.io.cosmostaion.ui.viewmodel.address.AddressBookViewModel
import wannabit.io.cosmostaion.ui.viewmodel.address.AddressBookViewModelProviderFactory

class AddressBookListActivity : BaseActivity() {

    private lateinit var binding: ActivityAddressBookListBinding

    private lateinit var addressBookViewModel: AddressBookViewModel

    private lateinit var setAddressBookAdapter: SetAddressBookAdapter

    private var isClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initRecyclerView()
        updateData()
        setUpClickAction()
    }

    private fun initViewModel() {
        val addressRepository = AddressRepositoryImpl()
        val addressBookViewModelProviderFactory =
            AddressBookViewModelProviderFactory(addressRepository)
        addressBookViewModel = ViewModelProvider(
            this, addressBookViewModelProviderFactory
        )[AddressBookViewModel::class.java]
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }

    private fun initRecyclerView() {
        binding.apply {
            addressBookViewModel.selectAllAddressBook()

            setAddressBookAdapter = SetAddressBookAdapter()
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(this@AddressBookListActivity)
            recycler.adapter = setAddressBookAdapter

            setAddressBookAdapter.setOnItemClickListener { addressBook ->
                AddressBookManageOptionFragment(addressBook).show(
                    supportFragmentManager, AddressBookManageOptionFragment::class.java.name
                )
            }
            initData()
        }
    }

    private fun initData() {
        binding.apply {
            addressBookViewModel.addressBookAllResult.observe(this@AddressBookListActivity) { response ->
                if (response.isNotEmpty()) {
                    recycler.visibility = View.VISIBLE
                    emptyLayout.visibility = View.GONE
                    setAddressBookAdapter.submitList(response)

                } else {
                    recycler.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun updateData() {
        addressBookViewModel.addressBookUpdateResult.observe(this@AddressBookListActivity) {
            setAddressBookAdapter.notifyItemChanged(it)
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnAddAddress.setOnClickListener {
                SetAddressFragment(null, null, "", "").show(
                    supportFragmentManager, SetAddressFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }
        }
    }

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }
}
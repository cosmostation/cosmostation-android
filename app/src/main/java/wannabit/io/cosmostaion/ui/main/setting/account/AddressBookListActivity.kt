package wannabit.io.cosmostaion.ui.main.setting.account

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.data.repository.address.AddressRepositoryImpl
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ActivityAddressBookListBinding
import wannabit.io.cosmostaion.ui.tx.step.kava.Bep3Fragment
import wannabit.io.cosmostaion.ui.viewmodel.address.AddressBookViewModel
import wannabit.io.cosmostaion.ui.viewmodel.address.AddressBookViewModelProviderFactory

class AddressBookListActivity : BaseActivity() {

    private lateinit var binding: ActivityAddressBookListBinding

    private lateinit var addressBookViewModel: AddressBookViewModel

    private lateinit var setAddressBookAdapter: SetAddressBookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initRecyclerView()
        clickAction()
    }

    private fun initViewModel() {
        val addressRepository = AddressRepositoryImpl()
        val addressBookViewModelProviderFactory = AddressBookViewModelProviderFactory(addressRepository)
        addressBookViewModel = ViewModelProvider(
            this,
            addressBookViewModelProviderFactory
        )[AddressBookViewModel::class.java]
    }

    private fun initRecyclerView() {
        binding.apply {
            addressBookViewModel.selectAllAddressBook()

            setAddressBookAdapter = SetAddressBookAdapter(popupClickAction)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(this@AddressBookListActivity)
            recycler.adapter = setAddressBookAdapter

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
                    setAddressBookAdapter.notifyDataSetChanged()

                } else {
                    recycler.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    private val popupClickAction = object : SetAddressBookAdapter.ClickListener {
        var isClickable = true
        override fun editAddressBook(book: AddressBook, position: Int) {
            if (isClickable) {
                isClickable = false
                SetAddressFragment(
                    book,
                    null,
                    "",
                    "",
                    object : AddressBookRegisterListener {
                        override fun register() {
                            setAddressBookAdapter.notifyItemChanged(position)
                        }

                    }).show(
                    supportFragmentManager,
                    SetAddressFragment::class.java.name
                )

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 1000)
            }
        }

        override fun deleteAddressBook(book: AddressBook) {
            TODO("Not yet implemented")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnAddAddress.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    SetAddressFragment(
                        null,
                        null,
                        "",
                        "",
                        object : AddressBookRegisterListener {
                            override fun register() {
                                setAddressBookAdapter.notifyDataSetChanged()
                            }

                        }).show(
                        supportFragmentManager,
                        SetAddressFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }
        }
    }
}
package wannabit.io.cosmostaion.ui.main.setting.wallet.book

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.toMoveBack
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.address.AddressRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.address.AddressBookViewModel
import wannabit.io.cosmostaion.data.viewmodel.address.AddressBookViewModelProviderFactory
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.databinding.ActivityAddressBookListBinding

class AddressBookListActivity : BaseActivity() {

    private lateinit var binding: ActivityAddressBookListBinding

    private lateinit var addressBookViewModel: AddressBookViewModel

    private lateinit var setAddressBookAdapter: SetAddressBookAdapter

    private var addressBooks: MutableList<AddressBook> = mutableListOf()
    private var searchAddressBooks: MutableList<AddressBook> = mutableListOf()

    private var isClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.parentLayout.setBackgroundResource(Prefs.background)

        initViewModel()
        initSearchView()
        initData()
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

        addressBookViewModel.selectAllAddressBook()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        toMoveBack()
    }

    private fun initData() {
        binding.apply {
            addressBookViewModel.addressBookAllResult.observe(this@AddressBookListActivity) { response ->
                addressBooks.clear()
                searchAddressBooks.clear()
                addressBooks.addAll(response)
                addressBooks.let { searchAddressBooks.addAll(it) }

                searchBar.visibleOrGone(searchAddressBooks.size > 15)

                searchView.post {
                    val searchHint =
                        searchView.findViewById<AutoCompleteTextView>(androidx.appcompat.R.id.search_src_text)
                    searchHint.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                    searchHint.setHintTextColor(
                        ContextCompat.getColor(
                            this@AddressBookListActivity, R.color.color_base04
                        )
                    )

                    val searchNewSizeInPx = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 20f, resources.displayMetrics
                    ).toInt()

                    val searchBtn =
                        searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
                    val searchLayoutParams = searchBtn.layoutParams
                    searchLayoutParams.width = searchNewSizeInPx
                    searchLayoutParams.height = searchNewSizeInPx
                    searchBtn.layoutParams = searchLayoutParams
                    searchBtn.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(
                            this@AddressBookListActivity, R.color.color_base04
                        ), PorterDuff.Mode.SRC_IN
                    )

                    val closeNewSizeInPx = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 35f, resources.displayMetrics
                    ).toInt()

                    val closeBtn =
                        searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
                    val closeLayoutParams = closeBtn.layoutParams
                    closeLayoutParams.width = closeNewSizeInPx
                    closeLayoutParams.height = closeNewSizeInPx
                    closeBtn.layoutParams = closeLayoutParams
                    closeBtn.colorFilter = PorterDuffColorFilter(
                        ContextCompat.getColor(
                            this@AddressBookListActivity, R.color.color_base04
                        ), PorterDuff.Mode.SRC_IN
                    )
                }

                initRecyclerView()
            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            if (searchAddressBooks.isNotEmpty()) {
                recycler.visibility = View.VISIBLE
                emptyLayout.visibility = View.GONE

                setAddressBookAdapter = SetAddressBookAdapter(searchAddressBooks)
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(this@AddressBookListActivity)
                recycler.adapter = setAddressBookAdapter

                setAddressBookAdapter.setOnItemClickListener { addressBook ->
                    AddressBookManageOptionFragment.newInstance(addressBook).show(
                        supportFragmentManager, AddressBookManageOptionFragment::class.java.name
                    )
                }

            } else {
                recycler.visibility = View.GONE
                emptyLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun initSearchView() {
        binding.apply {
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchAddressBooks.clear()
                    addressBooks.let {
                        if (StringUtils.isEmpty(newText)) {
                            searchAddressBooks.addAll(it)
                        } else {
                            newText?.let { searchTxt ->
                                searchAddressBooks.addAll(it.filter { addressBook ->
                                    addressBook.bookName.contains(
                                        searchTxt, ignoreCase = true
                                    ) || addressBook.address.contains(searchTxt, ignoreCase = true)
                                })
                            }
                        }
                        setAddressBookAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }

    private fun updateData() {
        addressBookViewModel.addressBookUpdateResult.observe(this@AddressBookListActivity) {
            setAddressBookAdapter.notifyItemChanged(it + 1)
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnAddAddress.setOnClickListener {
                if (isClickable) {
                    isClickable = false

                    SetAddressFragment.newInstance(null, null, "", "", AddressBookType.ManualNew)
                        .show(
                            supportFragmentManager, SetAddressFragment::class.java.name
                        )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 300)
                }
            }
        }
    }
}
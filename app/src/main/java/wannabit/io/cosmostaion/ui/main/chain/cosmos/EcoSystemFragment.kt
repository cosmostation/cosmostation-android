package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentEcoSystemBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.EcoSystemAdapter.Companion.VIEW_TYPE_DAPP_HEADER
import wannabit.io.cosmostaion.ui.main.chain.cosmos.EcoSystemAdapter.Companion.VIEW_TYPE_INJECT_HEADER
import wannabit.io.cosmostaion.ui.main.dapp.DappActivity

class EcoSystemFragment : Fragment() {

    private var _binding: FragmentEcoSystemBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var ecoSystemAdapter: EcoSystemAdapter

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): EcoSystemFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = EcoSystemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEcoSystemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }

        updateView()
    }

    private fun initRecyclerView(infos: MutableList<JsonObject>?) {
        binding.apply {
            loading.visibility = View.GONE
            recycler.visibility = View.VISIBLE

            ecoSystemAdapter = EcoSystemAdapter(requireContext(), selectedChain)
            ecoSystemAdapter.submitList(infos)
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            if (selectedChain is ChainCosmos) {
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (recycler.adapter?.getItemViewType(position)) {
                            VIEW_TYPE_INJECT_HEADER, VIEW_TYPE_DAPP_HEADER -> gridLayoutManager.spanCount
                            else -> 1
                        }
                    }
                }
            }
            recycler.setHasFixedSize(true)
            recycler.layoutManager = gridLayoutManager
            recycler.adapter = ecoSystemAdapter

            ecoSystemAdapter.setOnItemClickListener {
                Intent(requireActivity(), DappActivity::class.java).apply {
                    putExtra("selectedChain", selectedChain.tag)
                    putExtra("dapp", it)
                    startActivity(this)
                }
            }
        }
    }

    private fun updateView() {
        val infos = BaseData.originEcosystems?.filter { ecosystem ->
            ecosystem["chains"].asJsonArray?.mapNotNull { it.asString }
                ?.contains(selectedChain.apiName) == true
        }
            ?.sortedWith(compareByDescending<JsonObject> {
                Prefs.getPinnedDapps().contains(it["id"]?.asInt)
            }.thenByDescending { it["is_default"]?.asBoolean == true }
                .thenBy { it["name"]?.asString?.uppercase() ?: "" })
            ?.toMutableList()

        if (selectedChain is ChainCosmos) {
            val inject = JsonObject().apply {
                addProperty("name", "Injection Example")
                addProperty(
                    "description",
                    "This page offers examples and guidance for integrating and using the Cosmostation app in applications."
                )
                addProperty(
                    "thumbnail",
                    "https://raw.githubusercontent.com/cosmostation/chainlist/master/wallet/resource/injection.png"
                )
                addProperty(
                    "link", "https://cosmostation.github.io/cosmostation-app-injection-example/"
                )
                addProperty("type", "Develop Tool")
            }

            val github = JsonObject().apply {
                addProperty("name", "Injection Github")
                addProperty(
                    "description",
                    "This GitHub provides sample code and guides for integrating Cosmostation Wallet with DApps."
                )
                addProperty(
                    "thumbnail",
                    "https://raw.githubusercontent.com/cosmostation/chainlist/master/wallet/resource/github.png"
                )
                addProperty(
                    "link", "https://github.com/cosmostation/cosmostation-app-injection-example"
                )
                addProperty("type", "Github")
            }
            infos?.add(0, inject)
            infos?.add(1, github)
        }

        runCatching {
            if (infos?.isNotEmpty() == true) {
                initRecyclerView(infos)
            }
        }.onFailure {
            it.printStackTrace()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import starnamed.x.starname.v1beta1.Types
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.DialogTemplateRecyclerBinding
import wannabit.io.cosmostaion.databinding.ItemDialogDestinationChainBinding
import wannabit.io.cosmostaion.dialog.StarNameResourceDialog.ChainForResourceHolderAdapter.ChainForResourceHolder
import wannabit.io.cosmostaion.utils.StarnameAssets
import wannabit.io.cosmostaion.utils.StarnameResourceWrapper

class StarNameResourceDialog : BottomSheetDialogFragment() {
    private var dialogTemplateRecyclerBinding: DialogTemplateRecyclerBinding? = null
    private var mAlreadyChains: ArrayList<Types.Resource>? = null
    private var mAllChains: ArrayList<StarnameAssets>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogTemplateRecyclerBinding =
            DialogTemplateRecyclerBinding.inflate(inflater, container, false)
        dialogTemplateRecyclerBinding!!.root.setBackgroundResource(R.color.colorWhite2DayNight)
        dialogTemplateRecyclerBinding!!.dialogTitle.setText(R.string.str_select_chain_for_address)
        val wrapper =
            arguments!!.getSerializable(STAR_NAME_RESOURCE_WRAPPER_BUNDLE_KEY) as StarnameResourceWrapper?
        mAlreadyChains = wrapper!!.array
        mAllChains = StarnameAssets.getStarnameAssets()
        dialogTemplateRecyclerBinding!!.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dialogTemplateRecyclerBinding!!.recycler.setHasFixedSize(true)
        dialogTemplateRecyclerBinding!!.recycler.adapter = ChainForResourceHolderAdapter()
        return dialogTemplateRecyclerBinding!!.root
    }

    private fun alreadyHave(toInsert: StarnameAssets): Boolean {
        for (already in mAlreadyChains!!) {
            if (already.uri == toInsert.url) {
                return true
            }
        }
        return false
    }

    private inner class ChainForResourceHolderAdapter :
        RecyclerView.Adapter<ChainForResourceHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ChainForResourceHolder {
            return ChainForResourceHolder(
                ItemDialogDestinationChainBinding.inflate(
                    layoutInflater
                )
            )
        }

        override fun onBindViewHolder(holder: ChainForResourceHolder, position: Int) {
            val resource = mAllChains!![position]
            if (alreadyHave(resource)) {
                holder.itemDialogDestinationChainBinding.rootLayer.background =
                    ContextCompat.getDrawable(
                        activity!!, R.drawable.box_et_gary
                    )
            } else {
                holder.itemDialogDestinationChainBinding.rootLayer.background =
                    ContextCompat.getDrawable(
                        activity!!, R.drawable.box_et_chain
                    )
            }
            Picasso.get().load(StarnameAssets.getStarNameChainImgUrl(resource.url)).fit()
                .placeholder(R.drawable.token_default).error(R.drawable.token_default)
                .into(holder.itemDialogDestinationChainBinding.chainImg)
            holder.itemDialogDestinationChainBinding.chainName.text =
                StarnameAssets.getStarNameChainName(resource.url)
            holder.itemDialogDestinationChainBinding.rootLayer.setOnClickListener {
                if (!alreadyHave(resource)) {
                    val result = Bundle()
                    result.putParcelable("resource", resource)
                    parentFragmentManager.setFragmentResult(STAR_NAME_RESOURCE_BUNDLE_KEY, result)
                    dismiss()
                }
            }
        }

        override fun getItemCount(): Int {
            return mAllChains!!.size
        }

        inner class ChainForResourceHolder(val itemDialogDestinationChainBinding: ItemDialogDestinationChainBinding) :
            RecyclerView.ViewHolder(
                itemDialogDestinationChainBinding.root
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogTemplateRecyclerBinding = null
    }

    companion object {
        const val STAR_NAME_RESOURCE_BUNDLE_KEY = "starNameResource"
        const val STAR_NAME_RESOURCE_WRAPPER_BUNDLE_KEY = "resources"

        @JvmStatic
        fun newInstance(bundle: Bundle?): StarNameResourceDialog {
            val frag = StarNameResourceDialog()
            frag.arguments = bundle
            return frag
        }
    }
}
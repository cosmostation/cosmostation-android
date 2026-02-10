package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.databinding.DialogNoticeBinding
import wannabit.io.cosmostaion.databinding.FragmentChainNoticeBinding
import java.time.Instant

class ChainNoticeFragment : Fragment() {

    private var _binding: FragmentChainNoticeBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainNoticeAdapter: ChainNoticeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainNoticeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setUpClickAction()
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            chainNoticeAdapter = ChainNoticeAdapter()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chainNoticeAdapter
            chainNoticeAdapter.submitList(BaseData.ads?.sortedByDescending {
                Instant.parse(it.startAt).toEpochMilli()
            })

            chainNoticeAdapter.setOnItemClickListener { id ->
                showNoticeDetail(id)
            }
        }
    }

    private fun showNoticeDetail(id: String) {
        val inflater =
            requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogNoticeBinding.inflate(inflater)
        val alertDialog =
            AlertDialog.Builder(requireContext(), R.style.AppTheme_AlertDialogTheme)
                .setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()

        binding.apply {
            noticeView.setBackgroundResource(R.drawable.dialog_transparent_bg)

            val adInfo = BaseData.ads?.first { it.id == id }
            Picasso.get().load(adInfo?.images?.mobile).error(R.drawable.icon_default_dapp)
                .into(noticeImg)
            if (adInfo?.view_detail?.isEmpty() == true) {
                btnExplorer.visibility = View.GONE

            } else {
                viewDetail.text = adInfo?.view_detail
                val color = adInfo?.color?.toColorInt() ?: "#FFFFFF".toColorInt()
                btnExplorer.setCardBackgroundColor(color)

                btnExplorer.setOnClickListener {
                    dialog.dismiss()
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            adInfo?.linkUrl?.toUri()
                        )
                    )
                }
            }

            btnClose.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    private fun setUpClickAction() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
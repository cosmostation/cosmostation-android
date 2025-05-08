package wannabit.io.cosmostaion.ui.tx.option.validator

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.databinding.ItemValidatorDefaultBinding

class IotaValidatorAdapter(private val context: Context) :
    ListAdapter<JsonObject, ValidatorDefaultViewHolder>(ValidatorDefaultDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    private val coilImageLoader by lazy {
        ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidatorDefaultViewHolder {
        val binding =
            ItemValidatorDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ValidatorDefaultViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ValidatorDefaultViewHolder, position: Int) {
        val validator = currentList[position]
        holder.iotaBind(validator, coilImageLoader)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(validator["iotaAddress"].asString)
            }
        }
    }

    private class ValidatorDefaultDiffCallback : DiffUtil.ItemCallback<JsonObject>() {

        override fun areItemsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: JsonObject, newItem: JsonObject): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
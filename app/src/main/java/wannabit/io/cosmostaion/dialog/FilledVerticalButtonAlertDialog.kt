package wannabit.io.cosmostaion.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.DialogTemplateFilledverticalBinding

class FilledVerticalButtonAlertDialog(context: Context?) : AlertDialog(context) {
    var filledVerticalBinding: DialogTemplateFilledverticalBinding

    companion object {
        @JvmStatic
        fun showNoButton(
            context: Context,
            title: CharSequence,
            message: CharSequence,
            cancelable: Boolean?
        ) {
            val dialog = makeNoButton(context, title, message)
            if (!(context as Activity).isFinishing) {
                dialog.setCancelable(cancelable!!)
                dialog.create()
                dialog.show()
            }
        }

        @JvmOverloads
        fun showDoubleButton(
            context: Context,
            title: CharSequence,
            message: CharSequence,
            firstButtonTitle: CharSequence,
            firstButtonListener: View.OnClickListener,
            firstButtonImage: Drawable?,
            secondButtonTitle: CharSequence,
            secondButtonListener: View.OnClickListener,
            secondButtonImage: Drawable?,
            cancelable: Boolean? = true
        ) {
            val dialog = makeDoubleButton(
                context,
                title,
                message,
                firstButtonTitle,
                firstButtonListener,
                firstButtonImage,
                secondButtonTitle,
                secondButtonListener,
                secondButtonImage
            )
            if (!(context as Activity).isFinishing) {
                dialog.setCancelable(cancelable!!)
                dialog.create()
                dialog.show()
            }
        }

        @JvmOverloads
        fun showTripleButton(
            context: Context,
            title: CharSequence?,
            message: CharSequence?,
            firstButtonTitle: CharSequence,
            firstButtonListener: View.OnClickListener,
            firstButtonImage: Drawable?,
            secondButtonTitle: CharSequence,
            secondButtonListener: View.OnClickListener,
            secondButtonImage: Drawable?,
            thirdButtonTitle: CharSequence,
            thirdButtonListener: View.OnClickListener,
            thirdButtonImage: Drawable?,
            cancelable: Boolean? = true
        ) {
            val dialog = makeTripleButton(
                context,
                title,
                message,
                firstButtonTitle,
                firstButtonListener,
                firstButtonImage,
                secondButtonTitle,
                secondButtonListener,
                secondButtonImage,
                thirdButtonTitle,
                thirdButtonListener,
                thirdButtonImage
            )
            if (!(context as Activity).isFinishing) {
                dialog.setCancelable(cancelable!!)
                dialog.create()
                dialog.show()
            }
        }

        @JvmOverloads
        fun showQuadrupleButton(
            context: Context,
            title: CharSequence?,
            message: CharSequence?,
            firstButtonTitle: CharSequence,
            firstButtonListener: View.OnClickListener,
            firstButtonImage: Drawable?,
            secondButtonTitle: CharSequence,
            secondButtonListener: View.OnClickListener,
            secondButtonImage: Drawable?,
            thirdButtonTitle: CharSequence,
            thirdButtonListener: View.OnClickListener,
            thirdButtonImage: Drawable?,
            quadrupleButtonTitle: CharSequence,
            quadrupleButtonListener: View.OnClickListener,
            quadrupleButtonImage: Drawable?,
            cancelable: Boolean? = true
        ) {
            val dialog = makeQuadrupleButton(
                context,
                title,
                message,
                firstButtonTitle,
                firstButtonListener,
                firstButtonImage,
                secondButtonTitle,
                secondButtonListener,
                secondButtonImage,
                thirdButtonTitle,
                thirdButtonListener,
                thirdButtonImage,
                quadrupleButtonTitle,
                quadrupleButtonListener,
                quadrupleButtonImage
            )
            if (!(context as Activity).isFinishing) {
                dialog.setCancelable(cancelable!!)
                dialog.create()
                dialog.show()
            }
        }

        private fun makeNoButton(
            context: Context,
            title: CharSequence,
            message: CharSequence
        ): FilledVerticalButtonAlertDialog {
            val dialog = FilledVerticalButtonAlertDialog(context)
            if (StringUtils.isEmpty(title)) {
                dialog.filledVerticalBinding.dialogTitle2.visibility = View.GONE
            } else {
                dialog.filledVerticalBinding.dialogTitle2.text = title
                dialog.filledVerticalBinding.dialogTitle2.visibility = View.VISIBLE
            }
            if (StringUtils.isEmpty(message)) {
                dialog.filledVerticalBinding.dialogMsg2.visibility = View.GONE
            } else {
                dialog.filledVerticalBinding.dialogMsg2.text = message
                dialog.filledVerticalBinding.dialogMsg2.visibility = View.VISIBLE
            }
            dialog.filledVerticalBinding.btnOne.visibility = View.GONE
            dialog.filledVerticalBinding.btnTwo.visibility = View.GONE
            return dialog
        }

        private fun makeDoubleButton(
            context: Context,
            title: CharSequence?,
            message: CharSequence?,
            firstButtonTitle: CharSequence,
            firstButtonListener: View.OnClickListener,
            firstButtonImage: Drawable?,
            secondButtonTitle: CharSequence,
            secondButtonListener: View.OnClickListener,
            secondButtonImage: Drawable?
        ): FilledVerticalButtonAlertDialog {
            val dialog = FilledVerticalButtonAlertDialog(context)
            if (StringUtils.isEmpty(title)) {
                dialog.filledVerticalBinding.dialogTitle.visibility = View.GONE
            } else {
                dialog.filledVerticalBinding.dialogTitle.text = title
                dialog.filledVerticalBinding.dialogTitle.visibility = View.VISIBLE
            }
            if (StringUtils.isEmpty(message)) {
                dialog.filledVerticalBinding.dialogMsg.visibility = View.GONE
            } else {
                dialog.filledVerticalBinding.dialogMsg.text = message
                dialog.filledVerticalBinding.dialogMsg.visibility = View.VISIBLE
            }
            if (firstButtonImage == null && secondButtonImage == null) {
                dialog.filledVerticalBinding.btnOne.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    null,
                    null
                )
                dialog.filledVerticalBinding.btnTwo.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    null,
                    null
                )
            } else {
                dialog.filledVerticalBinding.btnOne.setCompoundDrawablesWithIntrinsicBounds(
                    firstButtonImage,
                    null,
                    null,
                    null
                )
                dialog.filledVerticalBinding.btnTwo.setCompoundDrawablesWithIntrinsicBounds(
                    secondButtonImage,
                    null,
                    null,
                    null
                )
            }
            dialog.filledVerticalBinding.btnOne.text = firstButtonTitle
            dialog.filledVerticalBinding.btnOne.setOnClickListener { view: View ->
                firstButtonListener.onClick(view)
                dialog.dismiss()
            }
            dialog.filledVerticalBinding.btnTwo.text = secondButtonTitle
            dialog.filledVerticalBinding.btnTwo.setOnClickListener { view: View ->
                secondButtonListener.onClick(view)
                dialog.dismiss()
            }
            dialog.filledVerticalBinding.btnLine2.visibility = View.VISIBLE
            dialog.filledVerticalBinding.btnThree.visibility = View.GONE
            return dialog
        }

        private fun makeTripleButton(
            context: Context,
            title: CharSequence?,
            message: CharSequence?,
            firstButtonTitle: CharSequence,
            firstButtonListener: View.OnClickListener,
            firstButtonImage: Drawable?,
            secondButtonTitle: CharSequence,
            secondButtonListener: View.OnClickListener,
            secondButtonImage: Drawable?,
            thirdButtonTitle: CharSequence,
            thirdButtonListener: View.OnClickListener,
            thirdButtonImage: Drawable?
        ): FilledVerticalButtonAlertDialog {
            val dialog = makeDoubleButton(
                context,
                title,
                message,
                firstButtonTitle,
                firstButtonListener,
                firstButtonImage,
                secondButtonTitle,
                secondButtonListener,
                secondButtonImage
            )
            if (thirdButtonImage == null) {
                dialog.filledVerticalBinding.btnThree.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    null,
                    null
                )
            } else {
                dialog.filledVerticalBinding.btnThree.setCompoundDrawablesWithIntrinsicBounds(
                    thirdButtonImage,
                    null,
                    null,
                    null
                )
            }
            if (!StringUtils.isEmpty(message)) dialog.filledVerticalBinding.btnLine.visibility =
                View.VISIBLE
            dialog.filledVerticalBinding.btnThree.text = thirdButtonTitle
            dialog.filledVerticalBinding.btnThree.visibility = View.VISIBLE
            dialog.filledVerticalBinding.btnLine2.visibility = View.VISIBLE
            dialog.filledVerticalBinding.btnLine3.visibility = View.VISIBLE
            dialog.filledVerticalBinding.btnThree.setOnClickListener { view: View ->
                thirdButtonListener.onClick(view)
                dialog.dismiss()
            }
            return dialog
        }

        private fun makeQuadrupleButton(
            context: Context,
            title: CharSequence?,
            message: CharSequence?,
            firstButtonTitle: CharSequence,
            firstButtonListener: View.OnClickListener,
            firstButtonImage: Drawable?,
            secondButtonTitle: CharSequence,
            secondButtonListener: View.OnClickListener,
            secondButtonImage: Drawable?,
            thirdButtonTitle: CharSequence,
            thirdButtonListener: View.OnClickListener,
            thirdButtonImage: Drawable?,
            quadrupleButtonTitle: CharSequence,
            quadrupleButtonListener: View.OnClickListener,
            quadrupleButtonImage: Drawable?
        ): FilledVerticalButtonAlertDialog {
            val dialog = makeTripleButton(
                context,
                title,
                message,
                firstButtonTitle,
                firstButtonListener,
                firstButtonImage,
                secondButtonTitle,
                secondButtonListener,
                secondButtonImage,
                thirdButtonTitle,
                thirdButtonListener,
                thirdButtonImage
            )
            if (quadrupleButtonImage == null) {
                dialog.filledVerticalBinding.btnFour.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    null,
                    null
                )
            } else {
                dialog.filledVerticalBinding.btnFour.setCompoundDrawablesWithIntrinsicBounds(
                    quadrupleButtonImage,
                    null,
                    null,
                    null
                )
            }
            if (StringUtils.isEmpty(message)) {
                dialog.filledVerticalBinding.dialogMsg.visibility = View.GONE
            } else {
                dialog.filledVerticalBinding.dialogMsg.text = message
                dialog.filledVerticalBinding.dialogMsg.visibility = View.VISIBLE
                dialog.filledVerticalBinding.btnLine.visibility = View.VISIBLE
            }
            dialog.filledVerticalBinding.btnLine2.visibility = View.VISIBLE
            dialog.filledVerticalBinding.btnLine3.visibility = View.VISIBLE
            dialog.filledVerticalBinding.btnLine4.visibility = View.VISIBLE
            dialog.filledVerticalBinding.btnFour.text = quadrupleButtonTitle
            dialog.filledVerticalBinding.btnFour.visibility = View.VISIBLE
            dialog.filledVerticalBinding.btnFour.setOnClickListener { view: View ->
                quadrupleButtonListener.onClick(view)
                dialog.dismiss()
            }
            return dialog
        }
    }

    init {
        window!!.setBackgroundDrawableResource(R.drawable.layout_trans_with_border)
        filledVerticalBinding = DialogTemplateFilledverticalBinding.inflate(layoutInflater)
        setView(filledVerticalBinding.root)
    }
}
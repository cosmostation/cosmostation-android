package wannabit.io.cosmostaion.utils

import android.text.Html
import android.text.Spanned

fun String.colorIn(colorString: String): Spanned {
    return Html.fromHtml("<font color=\"$colorString\">$this</font>")
}
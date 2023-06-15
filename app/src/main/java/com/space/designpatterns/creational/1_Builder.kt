package com.space.designpatterns.creational

import android.content.DialogInterface.OnClickListener

/** [CustomAlertDialog] to make its [Builder] and implement.
Dialog with builder pattern has to have private constructor
to prevent it from creating its references outside the class
and Builder class can create its instance, because it is nested in it.
Class has to have private values of its parameters while its Builder class
has the same variables with default values and are mutable. */
class CustomAlertDialog private constructor(
    private val title: String?,
    private val message: String?,
    private val positiveButtonLabel: String?,
    private val negativeButtonLabel: String?,
    private val positiveButtonListener: OnClickListener?,
    private val negativeButtonListener: OnClickListener?
) {
    /** Builder class has to have private mutable variables with default values and each one has to have
       setter functions to set values, which returns Builder object to chain its setters.
       Finally it has to have build() or create() method, that sets values to its class values and returns
       its reference. */
    class Builder {
        private var title: String? = null
        private var message: String? = null
        private var positiveButtonLabel: String? = null
        private var positiveButtonListener: OnClickListener? = null
        private var negativeButtonLabel: String? = null
        private var negativeButtonListener: OnClickListener? = null

        fun setTitle(title: String) = apply { this.title = title }
        fun setMessage(message: String) = apply { this.message = message }
        fun setPositiveButton(positiveButtonLabel: String, listener: () -> Unit) = apply {
            this.positiveButtonLabel = positiveButtonLabel
            this.positiveButtonListener = OnClickListener { dialog, which -> listener() }
        }

        fun setNegativeButton(negativeButtonLabel: String, listener: () -> Unit) = apply {
            this.negativeButtonLabel = negativeButtonLabel
            this.negativeButtonListener = OnClickListener { dialog, which -> listener() }
        }

        fun build(): CustomAlertDialog = CustomAlertDialog(
            title,
            message,
            positiveButtonLabel,
            negativeButtonLabel,
            positiveButtonListener,
            negativeButtonListener
        )
    }

    fun show() {
        println(
            "Title:          $title \n" +
                    "Message:        $message \n" +
                    "PositiveButton: $positiveButtonLabel \n" +
                    "NegativeButton: $negativeButtonLabel \n"
        )
        setPositiveButtonListener()
        setNegativeButtonListener()
    }

    /** Pretend to set button listeners */
    private fun setPositiveButtonListener(){
        positiveButtonListener // TODO - Positive Button Listener
    }
    private fun setNegativeButtonListener(){
        negativeButtonListener // TODO - Negative Button Listener
    }
}

/** Now we can implement our dialog by accessing [CustomAlertDialog.Builder] class and its methods */
fun main() {
    val dialog: CustomAlertDialog = CustomAlertDialog.Builder()
        .setTitle("Dialog Title")
        .setMessage("Dialog Message")
        .setPositiveButton("Continue") {
            println("Positive Button Clicked")
        }.setNegativeButton("Cancel") {
            println("Negative Button Clicked")
        }.build()

    dialog.show()
}

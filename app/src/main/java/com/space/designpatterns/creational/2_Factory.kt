package com.space.designpatterns.creational

/** [DialogView] abstraction to extract its functionality from its children classes */
abstract class DialogView {
    abstract fun setDialogContent(title: String, content: String)
    fun show() {
        println("Dialog is shown")
    }
}

/** [DialogView] Implementations to [AlertDialogView] and [PromptDialogView] */
class AlertDialogView : DialogView() {
    override fun setDialogContent(title: String, content: String) {
        println(
            "Title:   $title \n" +
                    "Content: $content \n"
        )
    }

    fun showAlertDialog() = show()

    // TODO - You can add more functionality here based on its purpose
}

class PromptDialogView : DialogView() {
    override fun setDialogContent(title: String, content: String) {
        println(
            "Title:   $title \n" +
                    "Content: $content \n"
        )
    }

    fun showPromptDialog() = show()

    // TODO - You can add more functionality here based on its purpose
}

/** Dialog Factory, that is responsible of dialog typification.
It has to have several mandatory things:
 * * Static function to create Factory's child object;
 * * Non-static function to create destination's instances */
abstract class DialogFactory {
    abstract fun makeDialog(): DialogView

    companion object {
        inline fun <reified DIALOG : DialogView> createDialog(): DialogFactory {
            return when (DIALOG::class) {
                AlertDialogView::class -> AlertDialogFactory()
                PromptDialogView::class -> PromptDialogFactory()
                else -> throw IllegalArgumentException()
            }
        }
    }
}

/** Factories for each dialogs, that implement non-static function to create destination's instances */
class AlertDialogFactory : DialogFactory() {
    override fun makeDialog(): DialogView = AlertDialogView()
}

class PromptDialogFactory : DialogFactory() {
    override fun makeDialog(): DialogView = PromptDialogView()
}

/** Using Factory pattern follows the flow:
 * * We call [DialogFactory.createDialog] static method, that gets reified type parameter and returns its Factory reference,
 * * Dialog Factory reference's [DialogFactory.makeDialog] method is called, which returns [DialogView] reference,
 * * We cast [DialogView] reference to its corresponding child class */
fun main() {
    val alertDialog = DialogFactory.createDialog<AlertDialogView>().makeDialog() as AlertDialogView
    val promptDialog =
        DialogFactory.createDialog<PromptDialogView>().makeDialog() as PromptDialogView

    alertDialog.showAlertDialog()
    alertDialog.setDialogContent("Alert Dialog", "Alert Dialog Content")

    promptDialog.showPromptDialog()
    promptDialog.setDialogContent("Prompt Dialog", "Prompt Dialog Content")
}

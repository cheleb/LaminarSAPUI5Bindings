package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}
import org.scalajs.dom.FileList
import be.doeraene.webcomponents.ui5.configkeys.ValueState
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail

/** The ui5-file-uploader opens a file explorer dialog and enables users to upload files. The component consists of
  * input field, but you can provide an HTML element by your choice to trigger the file upload, by using the default
  * slot. Furthermore, you can set the property "hideInput" to "true" to hide the input field. To get all selected
  * files, you can simply use the read-only "files" property. To restrict the types of files the user can select, you
  * can use the "accept" property. And, similar to all input based components, the FileUploader supports "valueState",
  * "placeholder", "name", and "disabled" properties. For the ui5-file-uploader
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/FileUploader/">the doc</a> for more
  *   information.
  */
object FileUploader extends HasName with HasValue {

  @js.native
  trait RawElement extends js.Object {
    @JSName("files")
    def filesJS: FileList = js.native
  }

  object RawElement {
    extension (element: RawElement)
      def files: List[dom.File] = (0 until element.filesJS.length).map(element.filesJS.item).toList
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/FileUploader.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = FileUploader.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-file-uploader")

  val id: ReactiveProp[String, String] = idAttr

  val accept: ReactiveHtmlAttr[List[String]] = customHtmlAttr("accept", ListCodec(StringAsIsCodec))

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  val hideInput: ReactiveHtmlAttr[Boolean] = customHtmlAttr("hide-input", BooleanAsAttrPresenceCodec)

  val multiple: ReactiveHtmlAttr[Boolean] = customHtmlAttr("multiple", BooleanAsAttrPresenceCodec)

  val placeholder: ReactiveHtmlAttr[String] = customHtmlAttr("placeholder", StringAsIsCodec)

  val valueState: ReactiveHtmlAttr[ValueState] = customHtmlAttr("value-state", ValueState.AsStringCodec)

  object slots {
    val valueStateMessage: Slot = new Slot("valueStateMessage")
  }

  object events {

    trait HasFiles extends js.Object {
      @JSName("files")
      def filesJS: FileList
    }

    object HasFiles {
      extension (element: HasFiles)
        def files: List[dom.File] = (0 until element.filesJS.length).map(element.filesJS.item).toList
    }

    val onChange: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasFiles]] = new EventProp("change")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(FileUploader)): _*)

}

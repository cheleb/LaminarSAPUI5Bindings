package be.doeraene.webcomponents.ui5.eventtypes

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

trait HasSelectedItems[ExtraType] extends js.Object {
  @JSName("selectedItems")
  def selectedItemsJS: js.Array[dom.HTMLElement & ExtraType]
}

object HasSelectedItems {
  extension [ExtraType](has: HasSelectedItems[ExtraType]) {
    def selectedItems: Vector[dom.HTMLElement & ExtraType] = has.selectedItemsJS.toVector
  }
}

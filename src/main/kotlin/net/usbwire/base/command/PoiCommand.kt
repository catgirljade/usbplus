package net.usbwire.base.command

import gg.essential.api.commands.Command
import gg.essential.api.commands.Options
import gg.essential.api.commands.Greedy
import gg.essential.api.commands.DefaultHandler

import net.usbwire.base.BaseMod
import net.usbwire.base.util.Util
import net.usbwire.base.features.Poi
import net.usbwire.base.config.VigilanceConfig

object PoiCommand : Command("poi") {
  @DefaultHandler
  fun handle(@Greedy poiString: String) {
    if (VigilanceConfig.poiEnabled == false) return
    var string = Util.trimString(poiString)
    if (string.length <= 3) {
      Util.chat("'$poiString': Too short!")
      return
    }
    val validPoi = BaseMod.poi.searchPoi(poiString)
    if (validPoi == null) {
      Util.chat("'$poiString': No POI found!")
    } else {
      for (poi in validPoi) {
        BaseMod.poi.responsePoi(poiString, poi)
      }
    }
  }
}
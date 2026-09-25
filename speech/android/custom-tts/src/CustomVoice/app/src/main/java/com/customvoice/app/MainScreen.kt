package com.customvoice.app

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.ScreenManager
import androidx.car.app.model.CarIcon
import androidx.car.app.model.GridItem
import androidx.car.app.model.GridTemplate
import androidx.car.app.model.ItemList
import androidx.car.app.model.OnClickListener
import androidx.car.app.model.Template

class MainScreen(carContext: CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template {

        val voiceSamples = VoiceSamples()
        val itemListBuilder = ItemList.Builder()
        voiceSamples.Samples.groupBy { it.type }.forEach { type ->
            val row = GridItem.Builder()
                .setTitle("${type.key}")
                .setImage(CarIcon.APP_ICON)
                .setOnClickListener(OnClickListener {
                    carContext.getCarService(ScreenManager::class.java)
                        .push(VoiceSampleScreen(carContext, type.key, type.value))
                })
                .build()
            itemListBuilder.addItem(row)
        }

        val itemList = itemListBuilder.build()
        val template = GridTemplate.Builder()
            .setSingleList(itemList)
            .setTitle("Embeeded Custom Voice Testing")
            .build()

        return template
    }
}

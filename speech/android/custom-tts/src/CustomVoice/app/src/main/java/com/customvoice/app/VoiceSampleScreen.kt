package com.customvoice.app

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.OnClickListener
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import com.customvoice.tts.Text2SpeechHandler

class VoiceSampleScreen(carContext: CarContext, val type: String, val samples: List<VoiceSample>) : Screen(carContext) {

    private val tts = Text2SpeechHandler(carContext)

    override fun onGetTemplate(): Template {

        val itemListBuilder = ItemList.Builder()
        samples.sortedBy { it.type }.forEach { sample ->
            val row = Row.Builder()
                .setTitle("${sample.type}: ${sample.name}")
                .addText(sample.description)
                .setOnClickListener(OnClickListener {
                    tts.speak(sample.ssml)
                })
                .build()
            itemListBuilder.addItem(row)
        }

        val itemList = itemListBuilder.build()
        val template = ListTemplate.Builder()
            .setSingleList(itemList)
            .setTitle("$type Voice Samples")
            .setHeaderAction(Action.BACK)
            .build()

        return template
    }
}

package com.effectivelab1.heroapp.ui.components

import android.content.Context
import org.xmlpull.v1.XmlPullParser
import com.effectivelab1.heroapp.R
import com.effectivelab1.heroapp.model.Hero
object DataProvider {
    fun loadHeroes(context: Context): List<Hero> {
        val heroes = mutableListOf<Hero>()
        val parser = context.resources.getXml(R.xml.heroes_data)

        var eventType = parser.eventType
        var currentHero: Hero? = null
        var currentTag: String? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    currentTag = parser.name
                    if (currentTag == "hero") {
                        currentHero = Hero("", "", "", "")
                    }
                }
                XmlPullParser.TEXT -> {
                    currentHero?.let { hero ->
                        when (currentTag) {
                            "id" -> hero.id = parser.text
                            "name" -> hero.name = context.getString(context.resources.getIdentifier(parser.text, "string", context.packageName))
                            "imageUrl" -> hero.imageUrl = parser.text
                            "description" -> hero.description = context.getString(context.resources.getIdentifier(parser.text, "string", context.packageName))
                        }
                    }
                }
                XmlPullParser.END_TAG -> {
                    if (parser.name == "hero") {
                        currentHero?.let { heroes.add(it) }
                    }
                    currentTag = null
                }
            }
            eventType = parser.next()
        }
        return heroes
    }
}
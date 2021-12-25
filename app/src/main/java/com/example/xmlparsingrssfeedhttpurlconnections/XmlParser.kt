package com.example.xmlparsingrssfeedhttpurlconnections

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class XmlParser {
    private val list = ArrayList<Apology>()

    private var text = ""

    private var apology = Apology("", "")

    fun parse(): ArrayList<Apology>{
        try{
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            val url = URL("https://rss.art19.com/apology-line/")
            parser.setInput(url.openStream(), null)
            var eventType = parser.eventType
            while(eventType != XmlPullParser.END_DOCUMENT){
                val tagName = parser.name
                when(eventType){
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("title", true) -> {
                            Log.d("HELP", text)
                            apology.title = text
                        }
                        tagName.equals("itunes:summary", true) -> {
                            apology.summary = text
                            list.add(apology.copy())
                        }
                        else -> {}
                    }
                    else -> {}
                }
                eventType = parser.next()
            }
        }catch(e: XmlPullParserException){
            e.printStackTrace()
        }catch(e: IOException){
            e.printStackTrace()
        }
        return list
    }
}
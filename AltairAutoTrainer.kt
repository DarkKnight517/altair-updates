package com.sultan.altair.trainer

import com.sultan.altair.core.AltairMemory
import com.example.sultanarlite.network.GptApi

object AltairAutoTrainer {
    fun trainOnce() {
        val memory = AltairMemory.load()
        val goals = memory.getWeakSpots()
        if (goals.isEmpty()) return

        val request = "Научи ИИ улучшить: ${goals.first()}. Дай пример, код и стратегию."
        val reply = GptApi.sendPrompt(request)

        memory.savePatch(reply)
        AltairMemory.save(memory)
    }
}

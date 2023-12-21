package org.waste.of.time.event.serializable

import net.minecraft.world.level.storage.LevelStorage
import org.waste.of.time.WorldTools.config
import org.waste.of.time.event.Storeable
import org.waste.of.time.serializer.StatisticSerializer
import org.waste.of.time.storage.CustomRegionBasedStorage

class StatisticStorable : Storeable {
    override fun toString() = "Statistics"

    override fun shouldStore() = config.capture.statistics

    override val message: String
        get() = "<lang:worldtools.capture.saved.statistics>"

    override fun store(session: LevelStorage.Session, cachedStorages: MutableMap<String, CustomRegionBasedStorage>) {
        StatisticSerializer.writeStats(session)
    }
}
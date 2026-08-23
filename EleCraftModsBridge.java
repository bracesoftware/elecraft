/*

        EleCraft TerraFirmaCraft SMP
        Brace Software Co.

        Server skripta

        by DEntisT_
        Helper: Wolfie

*/

package net.bracesoftware.elecraft;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EleCraftModsBridge
{
    public EleCraftModsBridge()
    {
        NeoForge.EVENT_BUS.register(this);
    }

    public class CustomOre
    {
        String identifier;
        String stoneOre;
        String deepslateOre;
        Integer minY;
        Integer maxY;
        //...
    }
}
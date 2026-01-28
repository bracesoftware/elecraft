/*

        EleCraft TerraFirmaCraft SMP
        Brace Software Co.

        Server skripta

        by DEntisT_
        Helper: Wolfie

*/

package net.bracesoftware.elecraft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(EleCraftServerScript.MODID)
public class EleCraftServerScript
{
    public static final String MODID = "elecraftserverscript";
   
    public EleCraftServerScript(IEventBus modEventBus, ModContainer modContainer)
    {
        new Implementation();

        Implementation.loadImplementation();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Implementation.unloadImplementation();
        }));
        return;
    }
}

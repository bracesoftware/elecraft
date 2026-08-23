/*

        EleCraft TerraFirmaCraft SMP
        Brace Software Co.

        Server skripta

        by DEntisT_
        Helper: Wolfie

*/

package net.bracesoftware.elecraft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.ModContainer;

import net.bracesoftware.elecraft.__PrivateCodeData.JavaTerminal;

@Mod(EleCraftServerScript.MODID)
public class EleCraftServerScript
{
    public static final String MODID = "elecraftserverscript";
       
    public EleCraftServerScript(IEventBus modEventBus, ModContainer modContainer)
    {
        new Implementation();
        
        JavaTerminal terminal = null;
        if(Utils.False())
        {
            terminal = new JavaTerminal("EleCraft Terminal");
            terminal.printText("Console je spreman!");
        }

        Implementation.loadImplementation();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Implementation.unloadImplementation();
            //if(terminal != null) terminal.close();
        }));

        return;
    }
}

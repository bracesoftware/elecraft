/*

        EleCraft MegaMods SMP
        Brace Software Co.

        Server skripta

        by DEntisT_
        Helper: Wolfie

*/

package net.bracesoftware.elecraft;
import java.io.InputStream;
import java.util.Optional;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.awt.event.ItemEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/////////////////////////////////
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/////////////////////////////////////////////////////////////////////
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Boolean;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/////////////////////////////////////////////////////////////////////
import com.google.gson.reflect.TypeToken;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.SimpleContainer;
/////////////////////////////////////////////////////////////////////
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.bracesoftware.elecraft.Implementation.BossBarManager;
import net.bracesoftware.elecraft.Implementation.Const.ServerGamemode;
import net.bracesoftware.elecraft.Implementation.House;
import net.bracesoftware.elecraft.Implementation.MainMenuSystem.MenuOption;
import net.bracesoftware.elecraft.Implementation.MenuTag;
import net.bracesoftware.elecraft.Implementation.PlayerData;
import net.bracesoftware.elecraft.Implementation.SettingsSystem;
import net.bracesoftware.elecraft.Implementation.SettingsSystem.PlayerBooleanSetting;
import net.bracesoftware.elecraft.Implementation.ShopSystem;
import net.bracesoftware.elecraft.Implementation.ShopSystem.ShopItem;
import net.bracesoftware.elecraft.Utils.HouseResult;
import net.bracesoftware.elecraft.Implementation.SettingsSystem.PlayerBooleanSetting;
import net.bracesoftware.elecraft.Implementation.SidebarManager;
import net.bracesoftware.elecraft.Implementation.TeleportSystem.TeleportLocation;
import net.bracesoftware.elecraft.Implementation.TemporaryPlayerData;

import net.bracesoftware.elecraft.Implementation.BossBarManager;
import net.bracesoftware.elecraft.Implementation.House;
import net.bracesoftware.elecraft.Implementation.PlayerData;
import net.bracesoftware.elecraft.Implementation.SidebarManager;
import net.bracesoftware.elecraft.Implementation.TemporaryPlayerData;
import net.minecraft.ChatFormatting;
/////////////////////////////////////////////////////////////////////
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.commands.data.DataCommands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerAdvancements;
//////////////////////////////////////////////////
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.neoforged.bus.api.EventPriority;
/////////////////////////////////////////////////////////////////////
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.CommandEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
///////////////////////////////////////////////////////
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
//import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
import net.minecraft.network.protocol.common.ClientboundResourcePackPushPacket;
////////////////////////////////////////
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
////////////////////////////////
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.LevelVersion;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.component.DataComponents;

import java.util.List;
/////////////////////////////
import net.minecraft.server.network.Filterable;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.*;

////////////////////////
public class Implementation
{
    private static final UUID PACK_UUID = UUID.nameUUIDFromBytes(Const.SystemInfo.DATABASE_LOCATION.getBytes());
    public static final ResourceKey<Level> LOBBY_LEVEL_KEY = ResourceKey.create(
            Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(EleCraftServerScript.MODID, "lobby")
    );
    public volatile boolean AsyncRunning = true;
    public Implementation()
    {
        NeoForge.EVENT_BUS.register(this);
        CustomEvents.betterOnServerStarted = false;

        for(int i = 0; i < SERVER_TICK_DIMENSION; ++i)
        {
            serverTick[i] = 0;
        }

        Async(() -> {
            while(AsyncRunning)
            {
                for(var entry : tempPlayerData.entrySet())
                {
                    String playerName = entry.getKey();
                    TemporaryPlayerData data = entry.getValue();

                    if(data.ulogovan == null || !data.ulogovan)
                    {
                        continue;
                    }

                    if(data.hpos[0] == null || data.hpos[1] == null || data.hpos[2] == null)
                    {
                        continue;
                    }
                    if(data.hdimension == null)
                    {
                        continue;
                    }

                    HouseResult r = Utils.isCoordNearHouseComplex(data.hpos, data.hdimension);
                    data.hid = r.id;
                    data.hprog = r.factor;
                }
                try
                {
                    Thread.sleep(150);
                }
                catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            elecraft.utils.write("Asinhrona pretraga UGASENA!");
            return;
        });
    }
    ////text style////////////////////////////////////////////////
    public static void Async(Runnable task)
    {
        CompletableFuture.runAsync(task).exceptionally(ex -> {
            System.err.println("Error in async -> " + ex.getMessage());
            ex.printStackTrace();
            return null;
        });
        return;
    }
    public class Text
    {
        private static class InternalClass
        {
            public Integer Code = 0;
            public char Char = '?';

            public InternalClass(Integer c, char c2)
            {
                this.Code = c;
                this.Char = c2;
            }

            public Integer get()
            {
                return this.Code;
            }
        }
        public class Col
        {
            public static final InternalClass BLACK = new InternalClass(1, '0');//
            public static final InternalClass DARK_BLUE = new InternalClass(2, '1');//2;//'1';
            public static final InternalClass DARK_GREEN = new InternalClass(3, '2');//'2';
            public static final InternalClass DARK_AQUA = new InternalClass(4, '3');//4;//'3';
            public static final InternalClass DARK_RED = new InternalClass(5,'4');//5;//'4';
            public static final InternalClass DARK_PURPLE = new InternalClass(6,'5');//6;//'5';
            public static final InternalClass GOLD = new InternalClass(7,'6');//7;//'6';
            public static final InternalClass GRAY = new InternalClass(8,'7');//8;//'7';
            public static final InternalClass DARK_GRAY = new InternalClass(9,'8');//9;//'8';
            public static final InternalClass BLUE = new InternalClass(10,'9');//10;//'9';
            public static final InternalClass GREEN = new InternalClass(11,'a');//11;//'a';
            public static final InternalClass AQUA = new InternalClass(12,'b');//12;//'b';
            public static final InternalClass RED = new InternalClass(13,'c');//13;//'c';
            public static final InternalClass LIGHT_PURPLE = new InternalClass(14,'d');//14;//'d';
            public static final InternalClass YELLOW = new InternalClass(15,'e');//15;//'e';
            public static final InternalClass WHITE = new InternalClass(16,'f');//16;//'f';

            public static final List<InternalClass> LIST = List.of(
                BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE,
                GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE,
                YELLOW, WHITE
            );
        }
        public class Style
        {
            public static final InternalClass OBFUSCATED = new InternalClass(17,'k');//17;//'k';
            public static final InternalClass BOLD = new InternalClass(18,'l');//18;//'l';
            public static final InternalClass STRIKETHROUGH = new InternalClass(19,'m');//19;//'m';
            public static final InternalClass UNDERLINE = new InternalClass(20,'n');//20;//'n';
            public static final InternalClass ITALIC = new InternalClass(21,'o');//21;//'o';
            public static final InternalClass RESET = new InternalClass(22, 'r');//22;//'r';

            public static final List<InternalClass> LIST = List.of(
                OBFUSCATED, BOLD, STRIKETHROUGH, UNDERLINE, ITALIC, RESET
            );
        }
        public static String Format(InternalClass... l)
        {
            List<Integer> g = new ArrayList<>();
            for(InternalClass item : l)
            {
                g.add(item.Code);
            }
            return Format(g);
        }
        public static String Format(Integer... l)
        {
            return Format(List.of(l));
        }
        public static String Format(List<Integer> l)
        {
            String result = "";
            if(l.isEmpty())
            {
                return result;
            }
            //firstly colors
            for(int i = 0; i < l.size(); ++i)
            {
                Integer p = l.get(i);
                for(int k = 0; k < Col.LIST.size(); ++k)
                {
                    if(p == Col.LIST.get(k).Code)
                    {
                        result += Const.Misc.FORMAT_SYMBOL + Col.LIST.get(k).Char;
                    }
                }
            }
            //then styles
            for(int i = 0; i < l.size(); ++i)
            {
                Integer p = l.get(i);
                for(int k = 0; k < Style.LIST.size(); ++k)
                {
                    if(p == Style.LIST.get(k).Code)
                    {
                        result += Const.Misc.FORMAT_SYMBOL + Style.LIST.get(k).Char;
                    }
                }
            }
            return result;
        }
    }
    ////////////////////////////////////////////////////
    public static boolean serverSettings$particles = false;
    
    public static boolean serverBlocked = false;
    public static Integer serverShutdown = -1;

    public static Boolean SHOP_SALE = false;
    public static Double LOBBY_SPAWN_POS_X = 0.0; 
    public static Double LOBBY_SPAWN_POS_Y = 0.0; 
    public static Double LOBBY_SPAWN_POS_Z = 0.0; 
    
    public class Const
    {
        public static final String ServerType = "MegaMods";
        public static final int MaxLoc = 30000000;

        public class SystemInfo
        {
            public static final Integer BUILD_NUMBER = 6;
            public static final String RESOURCES_LINK = "https://bracesoftware.github.io/web/external_resources/elecraft.zip";
            public static final String ASCII_WELCOME =
                "\n$$$$$$$$\\ $$\\            $$$$$$\\                      $$$$$$\\    $$\\     \n" +
                "$$  _____|$$ |          $$  __$$\\                    $$  __$$\\   $$ |    \n" +
                "$$ |      $$ | $$$$$$\\  $$ /  \\__| $$$$$$\\  $$$$$$\\  $$ /  \\__|$$$$$$\\   \n" +
                "$$$$$\\    $$ |$$  __$$\\ $$ |      $$  __$$\\ \\____$$\\ $$$$\\     \\_$$  _|  \n" +
                "$$  __|   $$ |$$$$$$$$ |$$ |      $$ |  \\__|$$$$$$$ |$$  _|      $$ |    \n" +
                "$$ |      $$ |$$   ____|$$ |  $$\\ $$ |     $$  __$$ |$$ |        $$ |$$\\ \n" +
                "$$$$$$$$\\ $$ |\\$$$$$$$\\ \\$$$$$$  |$$ |     \\$$$$$$$ |$$ |        \\$$$$  |\n" +
                "\\________|\\__| \\_______| \\______/ \\__|      \\_______|\\__|         \\____/ \n" +
                "                                                                         \n" +
                "                                  VERZIJA: " + BUILD_NUMBER.toString() + "\n" +
                "                         Uspjesno ucitano!!! By DEntisT_\n"
            ;
            public static final String DATABASE_LOCATION = "elecraft_data";
            public static final List<Class<? extends Block>> RESTRICTED_BLOCKS = List.of(
                DecoratedPotBlock.class, ComposterBlock.class, LeverBlock.class,
                ButtonBlock.class, BedBlock.class, CakeBlock.class,
                FenceGateBlock.class, TrapDoorBlock.class, SignBlock.class,
                PressurePlateBlock.class, CommandBlock.class
            );
        }

        public class CommandArgumentNames
        {
            public static final String PLAYER_ARGUMENT = "igrač";
            public static final String AMOUNT_ARGUMENT = "količina";
        }
        public class CommandNames
        {
            public static final String HELP = "pomoc";
            public static final String SERVER_OFF = "ugasiserver";
            public static final String DB_SAVE = "savedb";
            public static final String SHOP_SALE = "shopsale";
            public static final String LOBBY = "lobby";
            public static final String SMP = "smp";
            public static final String SET_LOBBY_SPAWN = "setlobbyspawn";
            public static final String MAIN_MENU = "mainmenu";
        }
        public class Misc
        {
            public static final String FORMAT_SYMBOL = "§";
            public static final Float NOTIFY_SOUND_VOLUME = 1.5f;
            public static final Integer CHEST_COLUMNS = 9;
            public static final Integer MENU_ROWS = 2;
            public static final Integer ANIM_FRAME_MS = 70;
        }
        public class Emojis 
        {
            public static final String SKULL = "💀";
            public static final String HUMAN = "👤";
            public static final String MONEY_BAG = "💰";
            public static final String SIGNAL = "📶";
            public static final String CALENDAR = "📅";
            public static final String SMALL_STAR = "✧";
            public static final String CROWN = "♛";
        }
        public class ServerBooks
        {
            public static final Integer COMMAND_HELP = 1;
            public static final Integer ADMIN_COMMAND_HELP = 2;
        }
        public class ServerMenus
        {
            public static final Integer SHOP_MENU = 1;
            public static final Integer SETTINGS_MENU = 2;
            public static final Integer TELEPORT_MENU = 3;
            public static final Integer MAIN_MENU = 4;

            public static final String EMPTY_SLOT_STR = "Prazno";
            public class Buttons
            {
                public static final String NEXT_PAGE = "Slijedeća strana";
                public static final String PREVIOUS_PAGE = "Prethodna strana";

                public static final String PREVIOUS_PAGE_HEAD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGExZDU1YjNmOTg5NDEwYTM0NzUyNjUwZTI0OGM5YjZjMTc4M2E3ZWMyYWEzZmQ3Nzg3YmRjNGQwZTYzN2QzOSJ9fX0=";
                public static final String NEXT_PAGE_HEAD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmE4N2UzZDk2ZTFjZmViOWNjZmIzYmE1M2EyMTdmYWY1MjQ5ZTI4NTUzM2IyNzFhMmZiMjg0YzMwZGJkOTgyOSJ9fX0=";
            }
        }
        public class SoundEventPitch
        {
            public static final Float EXPERIENCE_ORB_PICKUP = 1f;
        }
        public class ServerDimensions
        {
            public static final int END = 0;
            public static final int NETHER = 1;
            public static final int OVERWORLD = 2;
            public static final int LOBBY = 3;
        }
        public class ServerGamemode
        {
            public static final Integer SURVIVAL = 1;
            public static final Integer CREATIVE = 2;
            public static final Integer SPECTATOR = 3;
            public static final Integer ADVENTURE = 4;
        }
        public class CustomHeads
        {
            public static final String SMP_HEAD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjkxMjA5YTIxMzAyZjc2NGIzNzA2MGM4NjA2MmE1MTVmNTMzYjFhNGY5MDU5MzY1M2FiMjNiMzNiZmQ3YTA2OCJ9fX0=";
            public static final String LOBBY_HEAD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODdkNjZmOTM5MDlhNmQ0NjQxYzY1MzA4MmUwNDc0OTY5MWRlODJjZjc3MjMyYmQyMGFiMzJhZGY0ZiJ9fX0=";
            public static final String SETTINGS_HEAD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQxOWM2ODQ2MTY2NmFhY2Q3NjI4ZTM0YTFlMmFkMzlmZTRmMmJkZTMyZTIzMTk2M2VmM2IzNTUzMyJ9fX0=";
            public static final String NETHER_PORTAL_HEAD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBiZmMyNTc3ZjZlMjZjNmM2ZjczNjVjMmM0MDc2YmNjZWU2NTMxMjQ5ODkzODJjZTkzYmNhNGZjOWUzOWIifX19";
            public static final String HOUSE_HEAD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTUwNjJlMzUyODE0ODMzZGJjYTU5ZTk1M2M4ODFjYzA5ZWM4N2I3NTQyNjVhYTMwMTIwMTA2NzY5YTdlZjNkMiJ9fX0=";
            public static final String SHOP_HEAD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjRkYTgzMDYwOTJjYzU0YWNlZDYyY2UyNjNmZjFmNTc0YTFmODkwZWE1OGRjNDMwMzBiYTUwNzk3MjZiYWIzOSJ9fX0=";
            public static final String OBSCURION = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY4ODQ2MGM3MGUxNTlkZDk1YmZmMzgxZTA2ZGNhYzAwZWEzZWNjOGRkMmYwMWRmZDE3MzdhYzRlZjE1NzcxMCJ9fX0=";
        }
    }

    //sistem postavki
    public class SettingsSystem
    {
        public static class PlayerBooleanSetting 
        {
            public String name;
            public String description;
            public Consumer<ServerPlayer> toggle;
            public Predicate<ServerPlayer> get;
            public Consumer<ServerPlayer> doSomething = null;

            public PlayerBooleanSetting(String name, String desc, Consumer<ServerPlayer> tog, Predicate<ServerPlayer> g)
            {
                this.name = name;
                this.description = desc;
                this.toggle = tog;
                this.get = g;
            }
            public PlayerBooleanSetting(String name, String desc, Consumer<ServerPlayer> tog, Predicate<ServerPlayer> g, Consumer<ServerPlayer> s)
            {
                this.name = name;
                this.description = desc;
                this.toggle = tog;
                this.get = g;
                this.doSomething = s;
            }
        }

        public static final PlayerBooleanSetting DYNAMIC_SIDEBOARD = new PlayerBooleanSetting(
            "Dinamički Sideboard", "Pokreće automatsko osvježavanje postraničnog menija!",
            (ServerPlayer p) -> {
                playerData.get(getToken(p)).dynamicScoreboard = !playerData.get(getToken(p)).dynamicScoreboard;
            },
            (ServerPlayer p) -> {
                return playerData.get(getToken(p)).dynamicScoreboard;
            }
        );
        public static final PlayerBooleanSetting SERVER_TIPS = new PlayerBooleanSetting(
            "Server Tips", "Igraču prikazuje server tips!",
            (ServerPlayer p) -> {
                playerData.get(getToken(p)).tips = !playerData.get(getToken(p)).tips;
            },
            (ServerPlayer p) -> {
                return playerData.get(getToken(p)).tips;
            }
        );
        public static final PlayerBooleanSetting SIDEBOARD = new PlayerBooleanSetting(
            "Sideboard", "Igraču prikazuje postranični meni!",
            (ServerPlayer p) -> {
                playerData.get(getToken(p)).sideboard = !playerData.get(getToken(p)).sideboard;
            },
            (ServerPlayer p) -> {
                return playerData.get(getToken(p)).sideboard;
            },
            (ServerPlayer p) -> {
                SidebarManager.hideSidebar(p);
                prikaziInterfejs(p);
            }
        );
        public static final PlayerBooleanSetting BOSSBAR = new PlayerBooleanSetting(
            "Server Bossbar", "Igraču prikazuje server bossbar!",
            (ServerPlayer p) -> {
                playerData.get(getToken(p)).bossbar = !playerData.get(getToken(p)).bossbar;
            },
            (ServerPlayer p) -> {
                return playerData.get(getToken(p)).bossbar;
            },
            (ServerPlayer p) -> {
                SERVER_BOSSBAR.hideBossBar(p);
                prikaziInterfejs(p);
            }
        );

        public static final List<PlayerBooleanSetting> Settings = List.of(
            DYNAMIC_SIDEBOARD, SIDEBOARD, BOSSBAR, SERVER_TIPS
        );

        public static Integer getSettingCount()
        {
            return Settings.size();
        }
    }

    public class MainMenuSystem
    {
        public static class MenuOption
        {
            public String name;
            public String description;
            public Consumer<ServerPlayer> select = null;
            public String headTexture = null;

            public MenuOption(String name, String desc, Consumer<ServerPlayer> s, String texture)
            {
                this.name = name;
                this.description = desc;
                this.select = s;
                this.headTexture = texture;
            }
        }

        public static final MenuOption SETTINGS = new MenuOption(
            "Postavke", "Manipulišete osnovnim podešavanjima naloga.",
            (ServerPlayer p) -> {
                showMenu(p, Const.ServerMenus.SETTINGS_MENU);
                return;
            }, Const.CustomHeads.SETTINGS_HEAD
        );

        public static final MenuOption SHOP = new MenuOption(
            "Market", "Kupovina osnovnih potrebština za preživljavanje.",
            (ServerPlayer p) -> {
                showMenu(p, Const.ServerMenus.SHOP_MENU);
                return;
            }, Const.CustomHeads.SHOP_HEAD
        );

        public static final MenuOption TELEPORT = new MenuOption(
            "Teleportacija", "Teleportacija po određenim lokacijama.",
            (ServerPlayer p) -> {
                showMenu(p, Const.ServerMenus.TELEPORT_MENU);
                return;
            }, Const.CustomHeads.NETHER_PORTAL_HEAD
        );

        public static final List<MenuOption> Options = List.of(
            SETTINGS, SHOP, TELEPORT
        );

        public static Integer getOptionCount()
        {
            return Options.size();
        }
    }

    //sistem postavki
    public class TeleportSystem
    {
        public static class TeleportLocation 
        {
            public String name;
            public String description;
            public Consumer<ServerPlayer> tp;
            public Predicate<ServerPlayer> can;
            public String headTexture = null;

            public TeleportLocation(String name, String desc, Consumer<ServerPlayer> t, Predicate<ServerPlayer> c, String texture)
            {
                this.name = name;
                this.description = desc;
                this.tp = t;
                this.can = c;
                this.headTexture = texture;
            }
        }

        public static final TeleportLocation SMP = new TeleportLocation(
            "SMP", "Teleportujte se u svijet za preživljavanje!",
            (ServerPlayer p) -> {
                Utils.teleport(p, playerData.get(getToken(p)).x,playerData.get(getToken(p)).y,playerData.get(getToken(p)).z,playerData.get(getToken(p)).d);
                sendInfo(p, "Uspješno ste teleportovani u SMP.");
                return;
            },
            (ServerPlayer p) -> {
                if(GetDimension(p) != Const.ServerDimensions.LOBBY)
                {
                    sendError(p, "Nalazite se već u SMP-u.");
                    return false;
                }
                return true;
            }, Const.CustomHeads.SMP_HEAD
        );

        public static final TeleportLocation LOBBY = new TeleportLocation(
            "LOBBY", "Teleportujte se u LOBBY svijet!",
            (ServerPlayer p) -> {
                playerData.get(getToken(p)).x = p.getX();
                playerData.get(getToken(p)).y = p.getY();
                playerData.get(getToken(p)).z = p.getZ();
                playerData.get(getToken(p)).d = GetDimension(p);

                Utils.teleport(p, LOBBY_SPAWN_POS_X, LOBBY_SPAWN_POS_Y, LOBBY_SPAWN_POS_Z, Const.ServerDimensions.LOBBY);
                sendInfo(p, "Uspješno ste teleportovani u LOBBY.");
                return;
            },
            (ServerPlayer p) -> {
                if(GetDimension(p) == Const.ServerDimensions.LOBBY)
                {
                    sendError(p, "Nalazite se već u LOBBY-u.");
                    return false;
                }
                return true;
            }, Const.CustomHeads.LOBBY_HEAD
        );

        public static final TeleportLocation HOME = new TeleportLocation(
            "HOME", "Teleportujte se kući!\n" + Const.Emojis.MONEY_BAG + " 10",
            (ServerPlayer p) -> {
                playerData.get(getToken(p)).coins -= 10;
                int house = playerData.get(getToken(p)).house;
                Utils.teleport(p, houses[house].x, houses[house].y, houses[house].z, houses[house].dimension);
                sendInfo(p, "Uspješno ste teleportali do kuće.");
                return;
            },
            (ServerPlayer p) -> {
                if(playerData.get(getToken(p)).house == INVALID_HOUSE)
                {
                    sendError(p, "Vi nemate označenu kuću.");
                    return false;
                }
                if(playerData.get(getToken(p)).coins < 10)
                {
                    sendError(p, "Nemate dovoljno novca za ovu radnju (10 " + Const.Emojis.MONEY_BAG + ").");
                    return false;
                }
                return true;
            }, Const.CustomHeads.HOUSE_HEAD
        );

        public static final List<TeleportLocation> Teleports = List.of(
            SMP, LOBBY, HOME
        );

        public static Integer getTpCount()
        {
            return Teleports.size();
        }
    }

    //sistem shopa
    public class ShopSystem
    {
        public static class ShopItem
        {
            public String name;
            public Integer amount;
            public Integer price;
            public Item MinecraftItem;

            public ShopItem(String n, Integer a, Integer p, Item m)
            {
                this.name = n;
                this.amount = a;
                this.price = p;
                this.MinecraftItem = m;
            }
        }

        public static final ShopItem STRING = new ShopItem("Paučina", 1, 10, Items.STRING);
        public static final ShopItem CLAY_BALL = new ShopItem("Loptica gline", 1, 5, Items.CLAY_BALL);
        public static final ShopItem COAL = new ShopItem("Ugalj", 5, 20, Items.COAL);
        public static final ShopItem COOKED_BEEF = new ShopItem("Pečena teletina", 5, 50, Items.COOKED_BEEF);
        public static final ShopItem OAK_WOOD = new ShopItem("Drvo hrasta", 10, 100, Items.OAK_WOOD);

        public static final ShopItem COBBLESTONE = new ShopItem("Kaldrma", 64, 200, Items.COBBLESTONE);
        public static final ShopItem TORCH = new ShopItem("Baklja", 32, 150, Items.TORCH);
        public static final ShopItem BLAZE_POWDER = new ShopItem("Prašak Blejza", 5, 300, Items.BLAZE_POWDER);
        public static final ShopItem NETHERITE_UPGRADE = new ShopItem("Nederitna Nadogradnja", 1, 2000, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        public static final ShopItem DIAMOND = new ShopItem("Dijamant", 10, 1500, Items.DIAMOND);

        public static final ShopItem ENDER_PEARL = new ShopItem("Ender Loptice", 2, 200, Items.ENDER_PEARL);
        public static final ShopItem IRON_INGOT = new ShopItem("Željezo", 10, 500, Items.IRON_INGOT);
        public static final ShopItem COPPER_INGOT = new ShopItem("Bakar", 10, 250, Items.COPPER_INGOT);
        public static final ShopItem EXPERIENCE_BOTTLE = new ShopItem("Čaša Iskustva", 64, 1200, Items.EXPERIENCE_BOTTLE);
        public static final ShopItem CRYING_OBSIDIAN = new ShopItem("Plaučući Obsidijan", 1, 150, Items.CRYING_OBSIDIAN);

        public static final ShopItem HEAVY_CORE = new ShopItem("Teško Jezgro", 1, 5000, Items.HEAVY_CORE);
        public static final ShopItem ELYTRA = new ShopItem("Krila", 1, 6000, Items.ELYTRA);
        public static final ShopItem TRIDENT = new ShopItem("Trozubac", 1, 540, Items.TRIDENT);
        public static final ShopItem SPONGE = new ShopItem("Spužva", 5, 350, Items.SPONGE);
        public static final ShopItem NAME_TAG = new ShopItem("Oznaka za ime", 5, 350, Items.NAME_TAG);

        public static final ShopItem HEART_OF_THE_SEA = new ShopItem("Srce mora", 1, 4320, Items.HEART_OF_THE_SEA);
        public static final ShopItem TOTEM_OF_UNDYING = new ShopItem("Totem neumiranja", 1, 2300, Items.TOTEM_OF_UNDYING);
        public static final ShopItem NOTCH_APPLE = new ShopItem("`Notch` zlatna jabuka", 6, 3460, Items.ENCHANTED_GOLDEN_APPLE);
        public static final ShopItem BREEZE_ROD = new ShopItem("`Breeze` štap", 2, 2084, Items.BREEZE_ROD);
        public static final ShopItem BONE = new ShopItem("Kosti", 15, 30, Items.BONE);

        public static final ShopItem GLASS = new ShopItem("Staklo", 5, 100, Items.GLASS);
        public static final ShopItem FIREWORKS = new ShopItem("Rakete za vatromet", 25, 760, Items.FIREWORK_ROCKET);

        public static final List<List<ShopItem>> ShopItems = List.of(
            List.of(
                STRING, CLAY_BALL, COAL, COOKED_BEEF, OAK_WOOD,//
                COBBLESTONE, TORCH, BLAZE_POWDER, DIAMOND, NETHERITE_UPGRADE,//
                ENDER_PEARL, IRON_INGOT, COPPER_INGOT, EXPERIENCE_BOTTLE, CRYING_OBSIDIAN//
            ),
            List.of(
                HEAVY_CORE, ELYTRA, TRIDENT, SPONGE, NAME_TAG,//
                HEART_OF_THE_SEA, TOTEM_OF_UNDYING, NOTCH_APPLE, BREEZE_ROD, BONE,//
                GLASS, FIREWORKS
            )
        );

        public static Integer getShopPagesCount()
        {
            return ShopItems.size();
        }
        public static Integer getPageSize(int page)
        {
            if(
                page < 0 ||
                page >= ShopItems.size()
            )
            {
                return null;
            }
            return ShopItems.get(page).size();
        }
        public static List<ShopItem> getPage(int page)
        {
            if(
                page < 0 ||
                page >= ShopItems.size()
            )
            {
                return null;
            }
            return ShopItems.get(page);
        }
    }

    /////////////////////////////////////////////////////////////
    public static class playerToken
    {
        String internalToken;
        public playerToken(String token)
        {
            this.internalToken = token;
        }

        @Override
        public String toString()
        {
            return internalToken;
        }

        @Override
        public boolean equals(Object o)
        {
            if(this == o)
            {
                return true;
            }
            if(o == null || getClass() != o.getClass())
            {
                return false;
            }
            return internalToken.equals(((playerToken)o).internalToken);
        }

        @Override
        public int hashCode()
        {
            return internalToken.hashCode();
        }
    }
    private static final Map<String, playerToken> tokens = new HashMap<>();
    public static String getTokenLiteral(ServerPlayer player)
    {
        String token;
        token = tokens.get(player.getName().getString()).internalToken;
        return token;
    }
    public static playerToken getToken(ServerPlayer player)
    {
        playerToken token = new playerToken("");
        token = tokens.get(player.getName().getString());
        return token;
    }

    private static final Map<String, Integer> ulogovan = new HashMap<>();
    private static final Map<playerToken, String> lozinke = new HashMap<>();
    private static final Set<String> frozenPlayers = new HashSet<>();

    private static final Map<ServerPlayer, Integer> openedMenu = new HashMap<>();
    private boolean isFrozen(ServerPlayer player)
    {
        return frozenPlayers.contains(player.getName().getString());
    }
    ////////////////////INFORMACIJE O ACCOUNTU///////////////////
    private static final Map<playerToken, Integer> smrti = new HashMap<>();
    
    public static class PlayerData
    {
        public Integer logins = 0;
        public Integer coins = 0;
        public Boolean dynamicScoreboard = false;
        public Boolean tips = true;
        public Integer house = INVALID_HOUSE;
        public Boolean bossbar = true;
        public Boolean sideboard = true;
        public Boolean moderator = false;

        public Double x = 0.0;
        public Double y = 0.0;
        public Double z = 0.0;
        public Integer d = 0;

        public PlayerData() {}

        public PlayerData(Integer logins, Integer coins, Boolean dynamicScoreboard, Boolean tips)
        {
            this.logins = logins;
            this.coins = coins;
            this.dynamicScoreboard = dynamicScoreboard;
            this.tips = tips;
        }
    }


    public static class TemporaryPlayerData
    {
        public Double[] pos = new Double[3];
        public int dimension;
        public Boolean sidebar = false;
        public Integer houseLoading = 0;
        public Boolean ulogovan = false;

        public Integer streak = 0;
        public Boolean streak_cd = false;
        public Boolean streak_cd_sound = false;

        public Double[] hpos = new Double[3];
        public Integer hdimension = 0;
        public Integer hid = INVALID_HOUSE;
        public Float hprog = 0f;

        public Boolean CommandCooldown = false;
        public BossBarManager HouseBossbar = null;
        public Integer ShopPage = 0;

        public TemporaryPlayerData()
        {
            this.sidebar = false;
        }
    }

    private static final Map<playerToken, PlayerData> playerData = new HashMap<>();
    private static final Map<String, TemporaryPlayerData> tempPlayerData = new HashMap<>();
    public static boolean isAdmin(ServerPlayer player)
    {
        if(player.hasPermissions(2))
        {
            return true;
        }
        return false;
    }
    public static boolean isMod(ServerPlayer player)
    {
        if(isAdmin(player)) return true;
        if(playerData.containsKey(getToken(player))) if(playerData.get(getToken(player)).moderator)
        {
            return true;
        }
        return false;
    }
    ////////////////////////////////////////////////////////////
    //private static final Gson gson = new Gson();
    
    private static final com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
        .enableComplexMapKeySerialization() // KLJUČNA STVAR: Omogućava objekte kao ključeve
        .setPrettyPrinting()               // Opciono: da JSON bude čitljiv ljudima
        .create();
        
    private static final Path filePath = Paths.get(Const.SystemInfo.DATABASE_LOCATION + "/deaths.json");
    private static final Path filePathTokeni = Paths.get(Const.SystemInfo.DATABASE_LOCATION + "/account_tokens.json");
    private static final Path filePathAcc = Paths.get(Const.SystemInfo.DATABASE_LOCATION + "/accounts.json");
    private static final Path filePathAccData = Paths.get(Const.SystemInfo.DATABASE_LOCATION + "/account_data.json");
    private static final Path filePathHouse = Paths.get(Const.SystemInfo.DATABASE_LOCATION + "/houses.json");
    private static final Path fileShopSale = Paths.get(Const.SystemInfo.DATABASE_LOCATION + "/shopsale.sys");
    private static final Path lobbyPos1 = Paths.get(Const.SystemInfo.DATABASE_LOCATION + "/x.sys");
    private static final Path lobbyPos2 = Paths.get(Const.SystemInfo.DATABASE_LOCATION + "/y.sys");
    private static final Path lobbyPos3 = Paths.get(Const.SystemInfo.DATABASE_LOCATION + "/z.sys");

    public class elecraft
    {
        public class utils
        {
            public static void write(String text)
            {
                System.out.println("\n\t{EleCraft} -> System: " + text);
                return;
            }
            public static void giveItem(ServerPlayer player, Item item, int kolicina)
            {
                ItemStack stack = new ItemStack(item, kolicina);
                
                boolean y = player.getInventory().add(stack);
                
                if(!y && !stack.isEmpty())
                {
                    player.drop(stack, false);
                    player.sendSystemMessage(Component.literal(
                        Text.Format(Text.Col.RED.get()) +
                        "Inventar pun! Item je bačen na pod."
                    ));
                }
                return;
            }
        }
    }
    //////////////KUCE SISTEM////////////////////////
    public static final Integer MAX_HOUSE = 1000;

    public static final Integer INVALID_HOUSE = -1;

    public static final Double HOUSE_RANGE_BLOCK = 30.0;

    public static final String HOUSE_LABEL_TAG = "net.bracesoftware.elecraft.HouseLabelTag.SYS";

    public static class House
    {
        Double x = 0.0;
        Double y = 0.0;
        Double z = 0.0;
        Integer dimension = Const.ServerDimensions.OVERWORLD;
        
        transient ArmorStand[] t = null;
        transient ServerLevel level = null;

        public House()
        {
            this.t = new ArmorStand[3];
        }
    }
    public static House[] houses = new House[MAX_HOUSE];
    public static Integer createHouse(ServerPlayer player, Double x, Double y, Double z, Integer d)
    {
        Integer id = INVALID_HOUSE;
        for(Integer i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] == null)
            {
                id = i;
                break;
            }
        }
        if(id == INVALID_HOUSE)
        {
            return id;
        }

        houses[id] = new House();
        houses[id].x = x;
        houses[id].y = y;
        houses[id].z = z;
        houses[id].dimension = d;

        if(houses[id].dimension == Const.ServerDimensions.OVERWORLD)
        {
            houses[id].level = player.getServer().getLevel(Level.OVERWORLD);
        }
        else if(houses[id].dimension == Const.ServerDimensions.END)
        {
            houses[id].level = player.getServer().getLevel(Level.END);
        }
        else if(houses[id].dimension == Const.ServerDimensions.NETHER)
        {
            houses[id].level = player.getServer().getLevel(Level.NETHER);
        }
        createHouseLabel(id);

        playerData.get(getToken(player)).house = id;
        return id;
    }
    public static void deleteHouse(ServerPlayer player, Integer id)
    {
        for(Integer i = 0; i < 3; ++i)
        {
            //if(houses[id].t[i] != null)
            houses[id].t[i].discard();
        }
        houses[id] = null;
        playerData.get(getToken(player)).house = INVALID_HOUSE;
        return;
    }
    /*
    public static void createHouseLabel$deprecated(Integer id)
    {
        if (houses[id].level == null) return;

        String[] text = new String[3];
        text[0] = "§2§lMjesto prebivalista";
        text[1] = "§r§6Adresa: §o" + id.toString();
        text[2] = "§oDa sklonite zastitu [§l/delhome§r]";

        // AKO NE POSTOJE STANDOVI VEC U SVIJETU
        houses[id].t[0] = EntityType.ARMOR_STAND.create(houses[id].level);
        houses[id].t[1] = EntityType.ARMOR_STAND.create(houses[id].level);
        houses[id].t[2] = EntityType.ARMOR_STAND.create(houses[id].level);

        Double offset = 0.0;
        for(Integer i = 0; i < 3; ++i)
        {
            houses[id].t[i].moveTo(houses[id].x, houses[id].y + offset - 1.0, houses[id].z);
            houses[id].t[i].setNoGravity(true);
            houses[id].t[i].setInvisible(true);

            houses[id].t[i].setCustomNameVisible(true);
            houses[id].t[i].setCustomName(Component.literal(text[i]).withStyle(style -> style.withFont(ResourceLocation.withDefaultNamespace("uniform"))));


            houses[id].level.addFreshEntity(houses[id].t[i]);
            offset -= 0.3;

            houses[id].t[i].addTag(HOUSE_LABEL_TAG);
        }
        return;
    }*/

    public class HouseLabel extends ArmorStand
    {
        public HouseLabel(EntityType<? extends ArmorStand> type, Level level)
        {
            super(type, level);
        }

        @Override
        public void tick()
        {
            super.tick();
        }

        @Override
        public boolean isAffectedByFluids()
        {
            return false;
        }

        @Override
        public boolean isPushable()
        {
            return false;
        }
    }
    public static void deleteHouseLabel(Integer id)
    {
        if(houses[id] == null)
        {
            return;
        }

        for(Integer i = 0; i < 3; ++i)
        {
            if(houses[id].t[i] != null)
            {
                houses[id].t[i].discard();
            }
        }
        return;
    }
    public static void createHouseLabel(Integer id)
    {
        //createHouseLabel$new(id);
        return;
    }
    public static void createHouseLabel$new(Integer id)
    {
        if(houses[id].level == null) return;

        AABB area = new AABB(
            houses[id].x - Const.MaxLoc, houses[id].y - Const.MaxLoc, houses[id].z - Const.MaxLoc,
            houses[id].x + Const.MaxLoc, houses[id].y + Const.MaxLoc, houses[id].z + Const.MaxLoc
        );

        List<ArmorStand> pronadjeni = houses[id].level.getEntitiesOfClass(ArmorStand.class, area,
            e -> e.getTags().contains(HOUSE_LABEL_TAG));

        pronadjeni.sort((a, b) -> Double.compare(b.getY(), a.getY()));

        if(pronadjeni.size() > 3)
        {
            for(int j = 3; j < pronadjeni.size(); j++)
            {
                pronadjeni.get(j).discard();
            }
        }

        String[] text = new String[3];
        
        String ITALICC = Text.Format(Text.Style.ITALIC.get());
        String BOLDD = Text.Format(Text.Style.BOLD.get());
        String RESETT = Text.Format(Text.Style.RESET.get());

        text[0] = Text.Format(Text.Col.DARK_GREEN.get(), Text.Style.BOLD.get()) + "Mjesto prebivališta";
        text[1] = RESETT + Text.Format(Text.Col.GOLD.get()) + "Adresa: " + ITALICC + id.toString();
        text[2] = ITALICC + "Da sklonite zaštitu [" + BOLDD + "/delhome" + RESETT + "]";

        Double offset = 0.0;
        for(int i = 0; i < 3; ++i)
        {
            if(i < pronadjeni.size())
            {
                houses[id].t[i] = pronadjeni.get(i);
            }
            else
            {
                houses[id].t[i] = EntityType.ARMOR_STAND.create(houses[id].level);
                if(houses[id].t[i] != null)
                {
                    houses[id].level.addFreshEntity(houses[id].t[i]);
                }
            }

            if(houses[id].t[i] != null)
            {
                houses[id].t[i].moveTo(houses[id].x, houses[id].y + offset - 1.0, houses[id].z);
                houses[id].t[i].setNoGravity(true);
                houses[id].t[i].setInvisible(true);

                houses[id].t[i].setCustomNameVisible(true);
                houses[id].t[i].setCustomName(Component.literal(text[i])
                    .withStyle(style -> style.withFont(ResourceLocation.withDefaultNamespace("uniform"))));

                if(!houses[id].t[i].getTags().contains(HOUSE_LABEL_TAG))
                {
                    houses[id].t[i].addTag(HOUSE_LABEL_TAG);
                }
            }
            offset -= 0.3;
        }
    }
    public void deleteEntityWithTag(ServerLevel level, String tag)
    {
        if(Utils.False())
        {
            elecraft.utils.write("Attempting to delete entities...");
            Integer count = 0;
            for(Entity entity : level.getAllEntities())
            {
                count++;
                if (entity.getTags().contains(tag))
                {
                    elecraft.utils.write("Uspjesno obrisan entity: "  + entity.getName() + "@" + tag);
                    entity.discard();
                }
            }
        }

        elecraft.utils.write("Attempting to delete entities...");
        Integer count = 0;
        for(ArmorStand stand : level.getEntitiesOfClass
        (
            ArmorStand.class,
            new AABB(-30000000, -64, -30000000, 30000000, 320, 30000000),
            e -> e.getTags().contains(tag)
        ))
        {
            stand.discard();
            count++;
        }

        elecraft.utils.write("Deleted " + count + " entities.");
        return;
    }
    /*{
        houses[id].t = (Display.TextDisplay)EntityType.TEXT_DISPLAY.create(houses[id].level);
        houses[id].t.moveTo(houses[id].x, houses[id].y, houses[id].z);

        String msg = "§e§lMjesto prebivalista\n§r§7Adresa: " + id.toString() + "\n\nDa obrisete kucu, kucajte: §l/delhome";

        Component text = Component.literal(msg);
        CompoundTag tag = new CompoundTag();
        tag.putString("text", Component.Serializer.toJson(text, houses[id].level.registryAccess()));

        tag.putString("billboard", "center");
        tag.putBoolean("see_through", true);
        
        tag.putBoolean("NoGravity", true);

        houses[id].t.load(tag);
    
        houses[id].level.addFreshEntity(houses[id].t);
        return;
    }*/
  
    ////KNJIGA SISTEM////////////////////////////

    public static final String headerStyle = "§c§l";
    public static final String bodyStyle = "§o§5";
    
    public void OpenBook(ServerPlayer player, Integer book)
    {
        ResourceLocation bookFont = ResourceLocation.withDefaultNamespace("uniform");
        
        if(book == Const.ServerBooks.COMMAND_HELP)
        {
            ItemStack knjiga = new ItemStack(Items.WRITTEN_BOOK);

            // Lista stranica (svaka stranica je jedan Component)
            List<Filterable<Component>> stranice = List.of(
                Filterable.passThrough(Component.literal(headerStyle + "Dobrodošli na EleCraft TFC SMP!\n\n" + bodyStyle + "Ovo je EleCraft TFC.\n\nU ovoj knjizi ćeš naći osnovna uputstva za preživljavanje.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "Lista komandi:\n\n" + bodyStyle + "/login\n/changepassword\n/pomoc\n/adminhelp\n/smrti\n/postavke\n/shop\n/gohome").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/login\n\n" + bodyStyle + "Ova komanda sluzi za prijavu na Vas račun.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/changepassword\n\n" + bodyStyle + "Ova komanda omogucava da postavite novu lozinku za Vas račun.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/pomoc\n\n" + bodyStyle + "Ova komanda otvara ovaj vodic.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/adminhelp\n\n" + bodyStyle + "Ova komanda ispisuje komande dostupne iskljucivo operatorima!").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/smrti\n\n" + bodyStyle + "Ova komanda prikazuje koliko puta ste umrli na serveru.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/postavke\n\n" + bodyStyle + "Ova komanda otvara meni za podesavanja Vaseg računa.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/shop\n\n" + bodyStyle + "Ova komanda otvara market.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/gohome\n\n" + bodyStyle + "Pomoću ove komande možete da se teleportujete do svoje kuce. Jedna teleportacija kosta 10 novcica.").withStyle(style -> style.withFont(bookFont)))
            );

            // Postavljanje podataka knjige (1.21.1 sistem)
            knjiga.set(DataComponents.WRITTEN_BOOK_CONTENT, new WrittenBookContent(
                Filterable.passThrough("Pomoć oko komandi"), 
                player.getName().getString(), 
                0, 
                stranice, 
                true
            ));

            int slot = player.getInventory().selected;
            ItemStack stariItem = player.getInventory().getItem(slot);

            player.getInventory().setItem(slot, knjiga);

            player.connection.send(new net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket(
                0,
                player.containerMenu.getStateId(),
                slot + 36,
                knjiga
            ));

            player.openItemGui(knjiga, InteractionHand.MAIN_HAND);

            player.getInventory().setItem(slot, stariItem);

            player.connection.send(new net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket(
                0, 
                player.containerMenu.getStateId(),
                slot + 36, 
                stariItem
            ));
            return;
        }
        if(book == Const.ServerBooks.ADMIN_COMMAND_HELP)
        {
            ItemStack knjiga = new ItemStack(Items.WRITTEN_BOOK);

            List<Filterable<Component>> stranice = List.of(
                Filterable.passThrough(Component.literal(headerStyle + "Dobrodošli na EleCraft TFC SMP!\n\n" + bodyStyle + "Ovo je EleCraft TFC.\n\nU ovoj knjizi ćeš naći osnovna uputstva za preživljavanje.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "Lista admin komandi:\n\n" + bodyStyle + "/dajnovac\n/goto\n/gethere\n/ugasiserver\n/makemod\n/savedb\n/shopsale\n/setlobbyspawn").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/dajnovac\n\n").append(bodyStyle + "Ova komanda sluzi za dodavanje novca igraču!").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/goto\n\n").append(bodyStyle + "Ovom komandom se možete teleportovati do drugog igrača!").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/gethere\n\n").append(bodyStyle + "Ovom komandom možete teleportovati igrača do sebe!").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/ugasiserver\n\n").append(bodyStyle + "Ovom komandom možete ugasiti server! Standardna `/stop` komanda je zamijenjena.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/makemod\n\n").append(bodyStyle + "Ovom komandom igraču dajete status moderatora.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/savedb\n\n").append(bodyStyle + "Ovom komandom pokrećete sistem za asinhrono čuvanje podataka.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/shopsale\n\n").append(bodyStyle + "Ovom komandom pokrećete 50% shop sale.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/setlobbyspawn\n\n").append(bodyStyle + "Ovom komandom postavljate koordinate LOBBY SPAWN-a.").withStyle(style -> style.withFont(bookFont)))
            );

            knjiga.set(DataComponents.WRITTEN_BOOK_CONTENT, new WrittenBookContent(
                Filterable.passThrough("Pomoć oko admin komandi"), 
                player.getName().getString(), 
                0, 
                stranice, 
                true
            ));

            int slot = player.getInventory().selected;
            ItemStack stariItem = player.getInventory().getItem(slot);

            player.getInventory().setItem(slot, knjiga);

            player.connection.send(new net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket(
                0,
                player.containerMenu.getStateId(),
                slot + 36,
                knjiga
            ));

            player.openItemGui(knjiga, InteractionHand.MAIN_HAND);

            player.getInventory().setItem(slot, stariItem);

            player.connection.send(new net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket(
                0, 
                player.containerMenu.getStateId(),
                slot + 36, 
                stariItem
            ));
            return;
        }
        return;
    }

    /////////////////////////////
    private final List<String> serverTips = List.of(
        "Pozovite prijatelje na server!",
        "Prvi izbor za izgradnju krova kuće je `Hay Bale`!",
        "Poštujte pravila servera.",
        "Budite aktivni na serveru da zaradite " + Const.Emojis.MONEY_BAG + " Coins!",
        "Kucajte `/postavke` da manipulišete podešavanjima.",
        "Igrajte na pošten način!",
        "Dobro se dobrim vraća!"
    );

    /////////////////
    public static final String scoreboardName = "EleCraftSB";
    public class SidebarManager
    {
        public static void updateScoreboard(ServerPlayer player)
        {
            if(!playerData.get(getToken(player)).sideboard)
            {
                return;
            }
            if(!playerData.get(getToken(player)).dynamicScoreboard)
            {
                elecraft.utils.write("Igraču `" + player.getName().getString() + "` pokrenuto osvježavanje sidebara.");
            }
            String objName = scoreboardName;
            Component title = Component.literal("§6§l" + Const.ServerType + " SMP");

            player.connection.send(new ClientboundSetObjectivePacket(
                new Objective(null, objName, ObjectiveCriteria.DUMMY, title, ObjectiveCriteria.RenderType.INTEGER, false, null), 
                1 // Mode 1 = REMOVE
            ));

            Objective dummyObj = new Objective(null, objName, ObjectiveCriteria.DUMMY, title, ObjectiveCriteria.RenderType.INTEGER, false, null);
            player.connection.send(new ClientboundSetObjectivePacket(dummyObj, 0)); // Mode 0 = CREATE

            player.connection.send(new ClientboundSetDisplayObjectivePacket(DisplaySlot.SIDEBAR, dummyObj));

            final String _line_ = "§7------------------";

            sendLine(player, objName, _line_, 8);
            sendLine(player, objName, Const.Emojis.HUMAN + " Ime: " + Text.Format(Text.Col.GREEN.get()) + player.getScoreboardName(), 7);
            
            int deathCount = smrti.getOrDefault(getToken(player), 0);
            sendLine(player, objName, "§f" + Const.Emojis.SKULL + " Smrti: §e" + deathCount, 6);
            
            int coins = 0;
            int logs = 0;
            if(playerData.containsKey(getToken(player)))
            {
                coins = playerData.get(getToken(player)).coins;
                logs = playerData.get(getToken(player)).logins;
            }
            
            sendLine(player, objName, "§f" + Const.Emojis.MONEY_BAG + " Novac: §e" + coins, 5);
            sendLine(player, objName, "§f" + Const.Emojis.SMALL_STAR + " Log count: §ex" + logs, 4);
            sendLine(player, objName, " ", 3);
            sendLine(player, objName, Const.Emojis.SIGNAL + " Ping: " + player.connection.latency(), 2);
            sendLine(player, objName, _line_, 1);
            sendLine(player, objName, "§ewww.EleCraft.net", 0);
        }

        private static void sendLine(ServerPlayer player, String objName, String text, int score)
        {
            if(!playerData.get(getToken(player)).sideboard)
            {
                return;
            }
            Component displayName = Component.literal(text);
            String scoreHolder = "line" + score;

            player.connection.send(new ClientboundSetScorePacket(
                scoreHolder,
                objName,
                score,
                java.util.Optional.of(displayName), 
                java.util.Optional.empty() 
            ));
        }

        public static void updateTitleOnly(ServerPlayer player)
        {
            if(!playerData.get(getToken(player)).sideboard)
            {
                return;
            }
            //elecraft.utils.write("Igraču `" + player.getName().getString() + "` pokrenuto osvjezavanje sidebar naslova!");
            String objName = scoreboardName;
            String playerName = player.getName().getString();
            
            long seconds = player.level().getGameTime() / 20;
            List<String> colors = List.of("§6§l", "§e§l", "§a§l", "§b§l", "§d§l", "§c§l");
            String currentStyle = colors.get((int) (seconds % colors.size()));
            
            Component newTitle = Component.literal("");
            if(tempPlayerData.get(playerName).sidebar == false)
            {
                newTitle = Component.literal(currentStyle + Const.ServerType + " SMP");
            }
            if(tempPlayerData.get(playerName).sidebar == true)
            {
                newTitle = Component.literal(currentStyle + Const.Emojis.CALENDAR + " Dan: " + Utils.getDays(player));
            }

            Objective updateObj = new Objective(null, objName, ObjectiveCriteria.DUMMY, newTitle, ObjectiveCriteria.RenderType.INTEGER, false, null);
            
            player.connection.send(new ClientboundSetObjectivePacket(updateObj, 2)); 
        }

        public static void hideSidebar(ServerPlayer player)
        {
            if(playerData.get(getToken(player)).sideboard)
            {
                return;
            }
            String objName = scoreboardName;
            
            Objective dummyObj = new Objective(null, objName, ObjectiveCriteria.DUMMY, Component.literal(""), ObjectiveCriteria.RenderType.INTEGER, false, null);

            player.connection.send(new ClientboundSetObjectivePacket(dummyObj, 1));
            
            elecraft.utils.write("Sidebar sakriven za igrača: " + player.getName().getString());

            return;
        }

        public static void hideRaw(ServerPlayer player)
        {
            Scoreboard scoreboard = player.getScoreboard();

            for(DisplaySlot slot : DisplaySlot.values())
            {
                scoreboard.setDisplayObjective(slot, null);
            }
            return;
        }
    }
    public static class BossBarManager
    {
        private CustomBossEvent serverBar;
        public final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        public ScheduledFuture<?> currentAnimation;
        public ScheduledFuture<?> currentProgressAnimation;
        ResourceLocation barId = null;
        public String name = "";
        public Boolean cfg1 = false;

        public BossBarManager(String name)
        {
            this.name = name;
            barId = ResourceLocation.fromNamespaceAndPath("elecraft_bossbar", name);
        }

        public void destroy(MinecraftServer server)
        {
            if(currentAnimation != null && !currentAnimation.isCancelled())
            {
                currentAnimation.cancel(true);
            }
            if(currentProgressAnimation != null && !currentProgressAnimation.isCancelled())
            {
                currentProgressAnimation.cancel(true);
            }

            scheduler.shutdownNow();

            if(serverBar != null)
            {
                serverBar.removeAllPlayers();
                if(server != null)
                {
                    server.getCustomBossEvents().remove(serverBar);
                }
                serverBar = null;
            }
        }

        public CustomBossEvent get()
        {
            return serverBar;
        }

        public void showBossBar(ServerPlayer player)
        {
            serverBar = player.server.getCustomBossEvents().get(barId);
            if(serverBar == null)
            {
                serverBar = player.server.getCustomBossEvents().create(barId, Component.literal("§bDobrodošli na §5§lEleCraft Server"));
                serverBar.setColor(BossEvent.BossBarColor.BLUE);
            }

            serverBar.addPlayer(player);
            serverBar.setOverlay(BossEvent.BossBarOverlay.NOTCHED_12);
            if(!cfg1) serverBar.setProgress(1.0f);
            cfg1 = true;
            return;
        }

        public void changeProgress(Float prog)
        {
            if(serverBar == null)
            {
                return;
            }

            serverBar.setProgress(prog);
            return;
        }

        public void hideBossBar(ServerPlayer player)
        {
            if(serverBar == null)
            {
                return;
            }

            if(serverBar.getPlayers().contains(player))
            {
                serverBar.removePlayer(player);
            }
            return;
        }

        public void changeColor(BossEvent.BossBarColor color)
        {
            if(serverBar == null)
            {
                return;
            }
            serverBar.setColor(color);
            return;
        }

        public void changeOverlay(BossEvent.BossBarOverlay o)
        {
            if(serverBar == null)
            {
                return;
            }
            serverBar.setOverlay(o);
            return;
        }

        public void changeText(String text)
        {
            if(serverBar == null)
            {
                return;
            }
            serverBar.setName(Component.literal(text));
            return;
        }
        public void animateText(String text)
        {
            if(serverBar == null)
            {
                return;
            }

            if(currentAnimation != null && !currentAnimation.isCancelled()) 
            {
                currentAnimation.cancel(true);
            }

            int[] index = {0}; 

            currentAnimation = scheduler.scheduleAtFixedRate(() -> {
                if(index[0] < text.length())
                {
                    while(index[0] < text.length() && text.charAt(index[0]) == Const.Misc.FORMAT_SYMBOL.charAt(0))
                    {
                        index[0] += 2;
                    }
                    
                    if(index[0] < text.length())
                    {
                        index[0]++;
                    }
                    
                    if(index[0] > text.length())
                    {
                        index[0] = text.length();
                    }

                    String currentText = text.substring(0, index[0]);
                    changeText(currentText);
                }
                else
                {
                    if(currentAnimation != null)
                    {
                        currentAnimation.cancel(false);
                    }
                }
            }, 0, Const.Misc.ANIM_FRAME_MS, TimeUnit.MILLISECONDS);
            return;
        }

        public void animateProgress(float targetProgress, int durationMs)
        {
            MinecraftServer server = net.neoforged.neoforge.server.ServerLifecycleHooks.getCurrentServer();
            if(server == null) return;

            server.execute(() -> {
                if(serverBar == null) return;

                if(currentProgressAnimation != null && !currentProgressAnimation.isCancelled())
                {
                    currentProgressAnimation.cancel(true);
                }

                float startProgress = serverBar.getProgress();
                int steps = 20;
                float stepChange = (targetProgress - startProgress) / steps;
                long interval = Math.max(10, durationMs / steps);

                int[] currentStep = {0};

                currentProgressAnimation = scheduler.scheduleAtFixedRate(() -> {
                    server.execute(() -> {
                        if(serverBar == null) return;
                        
                        currentStep[0]++;
                        if(currentStep[0] <= steps)
                        {
                            float newProgress = startProgress + (stepChange * currentStep[0]);
                            serverBar.setProgress(Math.clamp(newProgress, 0.0f, 1.0f));
                        }
                        else
                        {
                            serverBar.setProgress(Math.clamp(targetProgress, 0.0f, 1.0f));
                            if(currentProgressAnimation != null)
                            {
                                currentProgressAnimation.cancel(false);
                            }
                        }
                    });
                }, 0, interval, TimeUnit.MILLISECONDS);
            });
            return;
        }
    }

    public static BossBarManager SERVER_BOSSBAR = new BossBarManager("server_bar");

    private static void updateHouseBar(ServerPlayer p)
    {
        var data = tempPlayerData.get(p.getName().getString());
        if(data.HouseBossbar == null) return;
        if(data.hid == INVALID_HOUSE)
        {
            data.HouseBossbar.hideBossBar(p);
        }
        else
        {
            //elecraft.utils.write("data.hprog[" + p.getName().getString() + "]: " + data.hprog.toString());
            data.HouseBossbar.showBossBar(p);
            data.HouseBossbar.changeProgress(data.hprog);
            data.HouseBossbar.changeOverlay(BossEvent.BossBarOverlay.PROGRESS);
            if(data.hid == playerData.get(getToken(p)).house)
            {
                data.HouseBossbar.changeColor(BossEvent.BossBarColor.GREEN);
                data.HouseBossbar.changeText(
                    Text.Format(Text.Col.WHITE) + "Nalazite se u okolici " +
                    Text.Format(Text.Style.BOLD, Text.Col.GREEN) + "SVOJE" +
                    Text.Format(Text.Col.WHITE) + " kuće!"
                );
            }
            else
            {
                data.HouseBossbar.changeColor(BossEvent.BossBarColor.RED);
                data.HouseBossbar.changeText(
                    Text.Format(Text.Col.GRAY) + "Nalazite se u okolici " +
                    Text.Format(Text.Style.BOLD, Text.Col.RED) + "TUĐE" +
                    Text.Format(Text.Col.GRAY) + " kuće!"
                );
            }
        }
        return;
    }

    public static void updateTabList(ServerPlayer player)
    {
        Component header = Component.literal("\n§5§lEleCraft\n§7Dobrodošli, §f" + player.getScoreboardName() + "\n");
        Component footer = Component.literal("\n§eWeb: §fwww.elecraft.net\n§7Uživajte u preživljavanju!\n" + Const.ServerType + " Survival");
        
        player.setTabListHeaderFooter(header, footer);
    }

    public static void prikaziInterfejs(ServerPlayer player)
    {
        if(playerData.get(getToken(player)).sideboard)
        {
            SidebarManager.updateScoreboard(player);
        }
        if(playerData.get(getToken(player)).bossbar)
        {
            SERVER_BOSSBAR.showBossBar(player);
        }
        updateTabList(player);
    }

    public int bossbarColor = 0;
    public void jednaSekunda()
    {
        if(bossbarColor == 0)
        {
            SERVER_BOSSBAR.changeColor(BossEvent.BossBarColor.GREEN);
        }
        if(bossbarColor == 1)
        {
            SERVER_BOSSBAR.changeColor(BossEvent.BossBarColor.RED);
        }
        if(bossbarColor == 2)
        {
            SERVER_BOSSBAR.changeColor(BossEvent.BossBarColor.BLUE);
        }
        if(bossbarColor == 3)
        {
            SERVER_BOSSBAR.changeColor(BossEvent.BossBarColor.WHITE);
        }
        if(bossbarColor == 4)
        {
            SERVER_BOSSBAR.changeColor(BossEvent.BossBarColor.PURPLE);
        }
        if(bossbarColor == 5)
        {
            SERVER_BOSSBAR.changeColor(BossEvent.BossBarColor.YELLOW);
        }
        if(bossbarColor == 6)
        {
            SERVER_BOSSBAR.changeColor(BossEvent.BossBarColor.PINK);
        }

        bossbarColor++;
        if(bossbarColor == 7)
        {
            bossbarColor = 0;
        }
        return;
    }
    public int bossbarName = 0;
    public void updateBossbarTextAuto()
    {
        if(SERVER_BOSSBAR.get() != null)
        {
            SERVER_BOSSBAR.get().setProgress(0.0f);
            SERVER_BOSSBAR.animateProgress(1f, 2000);
        }
        String TEXT = "";
        if(bossbarName == 0)
        {
            SERVER_BOSSBAR.animateText("§b" + Const.Emojis.CROWN + "Dobrodošli na §5§lEleCraft Server" + Const.Emojis.CROWN);
        }
        if(bossbarName == 1)
        {
            TEXT = Text.Format(Text.Col.YELLOW) + Const.Emojis.CROWN + Const.ServerType + " Preživljavanje" + Const.Emojis.CROWN;
            SERVER_BOSSBAR.animateText(TEXT);
        }
        if(bossbarName == 2) if(Implementation.SHOP_SALE)
        {
            TEXT = Text.Format(Text.Col.YELLOW, Text.Style.ITALIC) +
                "Kucajte `/shop` >> " +
                Text.Format(Text.Col.DARK_PURPLE, Text.Style.BOLD) + "50% " +
                Text.Format(Text.Col.DARK_RED, Text.Style.UNDERLINE, Text.Style.BOLD) + "MARKET SALE!"
            ;
            SERVER_BOSSBAR.animateText(TEXT);
        }
        bossbarName++;
        if(bossbarName == (Implementation.SHOP_SALE ? 3 : 2))
        {
            bossbarName = 0;
        }
        return;
    }

    //private int globalniSekund = 0

    public static final Integer SERVER_TICK_DIMENSION = 3;
    public Integer[] serverTick = new Integer[SERVER_TICK_DIMENSION];

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinLevelEvent e)
    {
        if(e.getLevel().dimension() == LOBBY_LEVEL_KEY)
        {
            if(!(e.getEntity() instanceof Player))
            {
                e.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onServerTick(net.neoforged.neoforge.event.tick.ServerTickEvent.Post event)
    {
        //ServerLevel lobby = event.getServer().getLevel(LOBBY_LEVEL_KEY);
        //lobby.setDayTime(20000);

        for(int i = 0; i < SERVER_TICK_DIMENSION; ++i)
        {
            serverTick[i] ++;
        }

        if(serverTick[0] >= 20)
        {
            serverTick[0] = 0;
            jednaSekunda();
        }

        if(serverTick[1] >= 60)
        {
            serverTick[1] = 0;
            triSekunde();
        }

        if(serverTick[2] >= 100)
        {
            serverTick[2] = 0;
            updateBossbarTextAuto();
        }



        if(serverShutdown == 30)
        {
            event.getServer().halt(false);
        }
        if(event.getServer().getTickCount() % 20 == 0)
        {
            if(serverShutdown != -1)
            {
                serverShutdown++;
            }
        }
        return;
    }
    public void triSekunde()
    {
        return;
    }
    ////////////////////////////////////

    public static Integer isLoggedIn(ServerPlayer player)
    {
        return ulogovan.get(player.getName().getString());
    }
            
    private static void ucitajPodatke()
    {
        if (!Files.exists(filePathAccData)) return;

        try (Reader reader = Files.newBufferedReader(filePathAccData))
        {
            // Objašnjavamo GSON-u da učitava Mapu u kojoj je PlayerData
            Type type = new TypeToken<Map<playerToken, PlayerData>>() {}.getType();
            Map<playerToken, PlayerData> ucitanaMapa = gson.fromJson(reader, type);

            if (ucitanaMapa != null)
            {
                playerData.clear();
                playerData.putAll(ucitanaMapa);
            }
        }
        catch (IOException e)
        {
            System.err.println("Greška pri učitavanju fajla: " + e.getMessage());
        }
    }

    private static void sacuvajPodatke()
    {
        try (Writer writer = Files.newBufferedWriter(filePathAccData))
        {
            gson.toJson(playerData, writer);
        }
        catch (IOException e)
        {
            System.err.println("Greška pri čuvanju fajla: " + e.getMessage());
        }
        return;
    }

    /////////////////////////////////
    
    private static void ucitajSmrti()
    {
        if (Files.exists(filePath)) {
            try (Reader reader = Files.newBufferedReader(filePath)) {
                Type type = new TypeToken<Map<playerToken, Integer>>() {}.getType();
                Map<playerToken, Integer> map = gson.fromJson(reader, type);

                // pretvori string UUID u UUID
                smrti.clear();
                for (Map.Entry<playerToken, Integer> entry : map.entrySet()) {
                    smrti.put(entry.getKey(), entry.getValue());
                }
            } catch (IOException e) {
                System.err.println("Ne mogu ucitati podatke smrti: " + e.getMessage());
            }
        }
        return;
    }


    private static void sacuvajSmrti()
    {
        try
        {
            Map<playerToken, Integer> map = new HashMap<>();
            for (Map.Entry<playerToken, Integer> entry : smrti.entrySet())
            {
                map.put(entry.getKey(), entry.getValue());
            }

            // kreira folder ako ne postoji
            if(!Files.exists(filePath.getParent()))
            {
                Files.createDirectories(filePath.getParent());
            }

            try(Writer writer = Files.newBufferedWriter(filePath))
            {
                gson.toJson(map, writer);
            }
        }
        catch (IOException e)
        {
            System.err.println("Ne mogu sacuvati podatke smrti: " + e.getMessage());
        }
        return;
    }

    private static void ucitajLozinke()
    {
        if(Files.exists(filePathAcc))
        {
            try (Reader reader = Files.newBufferedReader(filePathAcc))
            {
                Type type = new TypeToken<Map<playerToken, String>>() {}.getType();
                Map<playerToken, String> map = gson.fromJson(reader, type);

                // pretvori string UUID u UUID
                lozinke.clear();
                for (Map.Entry<playerToken, String> entry : map.entrySet())
                {
                    lozinke.put(entry.getKey(), entry.getValue());
                }
            }
            catch (IOException e)
            {
                System.err.println("Ne mogu ucitati podatke lozinke: " + e.getMessage());
            }
        }
    }

    private static void sacuvajLozinke()
    {
        try
        {
            Map<playerToken, String> map = new HashMap<>();
            for (Map.Entry<playerToken, String> entry : lozinke.entrySet())
            {
                map.put(entry.getKey(), entry.getValue());
            }

            // kreira folder ako ne postoji
            if (!Files.exists(filePathAcc.getParent())) {
                Files.createDirectories(filePathAcc.getParent());
            }

            try (Writer writer = Files.newBufferedWriter(filePathAcc)) {
                gson.toJson(map, writer);
            }
        }
        catch (IOException e)
        {
            System.err.println("Ne mogu sacuvati podatke lozinki: " + e.getMessage());
        }
        return;
    }

    private static void ucitajTokene()
    {
        if(Files.exists(filePathTokeni))
        {
            try (Reader reader = Files.newBufferedReader(filePathTokeni))
            {
                Type type = new TypeToken<Map<String, playerToken>>() {}.getType();
                Map<String, playerToken> map = gson.fromJson(reader, type);

                // pretvori string UUID u UUID
                tokens.clear();
                for (Map.Entry<String, playerToken> entry : map.entrySet())
                {
                    tokens.put(entry.getKey(), entry.getValue());
                }
            } catch (IOException e) {
                System.err.println("Ne mogu ucitati podatke lozinke: " + e.getMessage());
            }
        }
    }

    private static void sacuvajTokene()
    {
        try {
            Map<String, playerToken> map = new HashMap<>();
            for (Map.Entry<String, playerToken> entry : tokens.entrySet())
            {
                map.put(entry.getKey(), entry.getValue());
            }

            // kreira folder ako ne postoji
            if (!Files.exists(filePathTokeni.getParent()))
            {
                Files.createDirectories(filePathTokeni.getParent());
            }

            try (Writer writer = Files.newBufferedWriter(filePathTokeni))
            {
                gson.toJson(map, writer);
            }
        } catch (IOException e) {
            System.err.println("Ne mogu sacuvati podatke lozinki: " + e.getMessage());
        }
        return;
    }

    public static void ucitajKuce()
    {
        if(Files.exists(filePathHouse))
        {
            try (Reader reader = Files.newBufferedReader(filePathHouse))
            {
                Type type = new TypeToken<House[]>() {}.getType();
                houses = gson.fromJson(reader, type);

                if(houses == null)
                {
                    houses = new House[MAX_HOUSE];
                }
            }
            catch (IOException e)
            {
                System.err.println("Ne mogu ucitati podatke lozinke: " + e.getMessage());
            }
        }
        return;
    }
    public static void sacuvajKuce()
    {
        try
        {
            if (!Files.exists(filePathHouse.getParent()))
            {
                Files.createDirectories(filePathHouse.getParent());
            }

            try (Writer writer = Files.newBufferedWriter(filePathHouse))
            {
                gson.toJson(houses, writer);
            }
        }
        catch (IOException e)
        {
            System.err.println("Ne mogu sacuvati podatke kuca: " + e.getMessage());
        }
        return;
    }

    public static void loadImplementation()
    {
        // Ispis ASCII samo jednom pri startu
        System.out.println(Const.SystemInfo.ASCII_WELCOME);
        Utils.load();
        ResourcePackManager.downloadAndComputeHash();

        SafeLoad();
        return;
    }

    private static void SafeLoad()
    {
        Implementation.SHOP_SALE = Utils.getBoolean(fileShopSale.toString());
        ucitajTokene();
        ucitajSmrti();
        ucitajLozinke();
        ucitajPodatke();
        ucitajKuce();

        LOBBY_SPAWN_POS_X = Utils.getDouble(lobbyPos1.toString());
        LOBBY_SPAWN_POS_Y = Utils.getDouble(lobbyPos2.toString());
        LOBBY_SPAWN_POS_Z = Utils.getDouble(lobbyPos3.toString());

        elecraft.utils.write("Podaci ucitani iz databaze.");
        return;
    }

    private static void SafeSave()
    {
        elecraft.utils.write("Cuvanje podataka...");
        Utils.saveBoolean(fileShopSale.toString(), Implementation.SHOP_SALE);
        sacuvajTokene();
        sacuvajSmrti();
        sacuvajLozinke();
        sacuvajPodatke();
        sacuvajKuce();

        Utils.saveDouble(lobbyPos1.toString(), LOBBY_SPAWN_POS_X);
        Utils.saveDouble(lobbyPos2.toString(), LOBBY_SPAWN_POS_Y);
        Utils.saveDouble(lobbyPos3.toString(), LOBBY_SPAWN_POS_Z);

        elecraft.utils.write("Podaci sacuvani u databazu!");
        return;
    }

    public static void unloadImplementation()
    {
        elecraft.utils.write("Server se gasi - spremanje podataka u toku...");
        SafeSave();
        return;
    }

    // Helper metoda
    private static void sendPlayerMessage(ServerPlayer player, String text)
    {
        player.sendSystemMessage(
                Component.literal(
                    Text.Format(List.of(Text.Col.GOLD.Code, Text.Style.BOLD.Code)) +
                    "[EleCraft]: " +
                    Text.Format(List.of(Text.Col.YELLOW.Code, Text.Style.ITALIC.Code)) +
                    text
                )
                //("§6§l[EleCraft]: §e§o" + text)
        );
    }
    private static void sendError(ServerPlayer player, String text) {
        player.sendSystemMessage(
                Component.literal(
                    Text.Format(List.of(Text.Col.RED.Code, Text.Style.BOLD.Code)) +
                    "Error: " +
                    Text.Format(List.of(Text.Col.GRAY.Code, Text.Style.ITALIC.Code)) +
                    text
                )//("§c§lError: §7§o" + text)
        );
    }
    private static void sendac(ServerPlayer player, String text)
    {
        player.sendSystemMessage(
                Component.literal(
                    Text.Format(Text.Col.GRAY) + "[" + Text.Format(Text.Col.DARK_GREEN, Text.Style.BOLD) +
                    "Anti-Cheat" + Text.Format(Text.Col.GRAY) + "]: " +
                    Text.Format(Text.Style.ITALIC, Text.Col.YELLOW) +
                    text
                )//("§c§lError: §7§o" + text)
        );
    }
    private static void sendInfo(ServerPlayer player, String text) {
        player.sendSystemMessage(
                Component.literal(
                    Text.Format(List.of(Text.Style.BOLD.Code, Text.Col.GREEN.Code)) +
                    "Info: " +
                    Text.Format(List.of(Text.Style.ITALIC.Code, Text.Col.WHITE.Code)) +
                    text)
        );
    }
    private static void sendSmth(ServerPlayer player, String header, String text) {
        String format1 = Text.Format(List.of(Text.Style.BOLD.Code, Text.Col.GREEN.Code));
        player.sendSystemMessage(
                Component.literal(
                    format1 +
                    "[" +
                    Text.Format(Text.Col.GRAY.get()) +
                    header +
                    format1 + 
                    "]: " +
                    Text.Format(List.of(Text.Style.ITALIC.Code, Text.Col.WHITE.Code)) +
                    text
                )
        );
    }

    public static Integer GetGamemode(ServerPlayer p)
    {
        GameType gameType = p.gameMode.getGameModeForPlayer();

        return switch (gameType)
        {
            case SURVIVAL -> Const.ServerGamemode.SURVIVAL;
            case CREATIVE -> Const.ServerGamemode.CREATIVE;
            case SPECTATOR -> Const.ServerGamemode.SPECTATOR;
            case ADVENTURE -> Const.ServerGamemode.ADVENTURE;
        };
    }

    public static int GetDimension(ServerPlayer p)
    {
        var d = p.level().dimension();
        if(d == Level.OVERWORLD)
        {
            return Const.ServerDimensions.OVERWORLD;
        }
        else if(d == Level.NETHER)
        {
            return Const.ServerDimensions.NETHER;
        }
        else if(d == Level.END)
        {
            return Const.ServerDimensions.END;
        }
        else if(d == LOBBY_LEVEL_KEY)
        {
            return Const.ServerDimensions.LOBBY;
        }
        return -1;
    }

    private static void sendAdminInfo(String text)
    {
        MinecraftServer s = net.neoforged.neoforge.server.ServerLifecycleHooks.getCurrentServer();
        for(ServerLevel l : s.getAllLevels())
        {
            l.getAllEntities().forEach(e -> {
                if(e instanceof ServerPlayer p)
                {
                    if(!isMod(p)) return;
                    sendSmth(p, "STAFF INFO", text);
                }
            });
        }
    }

    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event)
    {
        String line1 = "§b§lEleCraft §6§lNetwork §7§nNeoForge§r§c [§e1.21.1§c]";
        String line2 = "§2§lINFO: §f§oDobrodošli na naš server!";

        // Ručno centriranje pomoću razmaka
        // Napomena: Broj razmaka zavisi od dužine tvog teksta
        String centeredLine1 = "      " + line1; 
        String centeredLine2 = "          " + line2;

        String finalMotd = centeredLine1 + "\n" + centeredLine2;
        
        event.getServer().setMotd(finalMotd);
        return;
    }

    public void betterOnServerStarted_(ServerPlayer player)
    {
        if(CustomEvents.betterOnServerStarted == true)
        {
            return;
        }
        CustomEvents.betterOnServerStarted = true;
        
        MinecraftServer s = net.neoforged.neoforge.server.ServerLifecycleHooks.getCurrentServer();
        for(ServerLevel l : s.getAllLevels())
        {
            l.getAllEntities().forEach(e -> {
                if(e instanceof ArmorStand)
                {
                    if(e.getTags().contains(HOUSE_LABEL_TAG))
                    {
                        e.discard();
                    }
                }
            });
        }
        return;
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // change game rules
        event.getServer().getGameRules().getRule(GameRules.RULE_SENDCOMMANDFEEDBACK).set(false, event.getServer());
        event.getServer().getGameRules().getRule(GameRules.RULE_SHOWDEATHMESSAGES).set(false, event.getServer());
        event.getServer().getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).set(true, event.getServer());
        
        if(Utils.False()) // dead code just for idk, exploring available functions
        {
            net.minecraft.core.BlockPos pos = event.getServer().getLevel(Level.OVERWORLD).getSharedSpawnPos();
            pos.getX();
            pos.getY();
            pos.getZ();
        }

        elecraft.utils.write("Pokrece se server -> " + event.toString());
        return;
    }
    
    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event)
    {
        if(Utils.False()) for(int i = 0; i < MAX_HOUSE; i++)
        {
            if(houses[i] != null)
            {
                for(Integer j = 0; j < 3; ++j)
                {
                    if(houses[i].t[j] != null) houses[i].t[j].discard();
                }
            }
        }
        if(Utils.False()) elecraft.utils.write("Sve kucne labele su obrisane...");
        return;
    }
    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event)
    {
        if(event.getEntity() instanceof ServerPlayer player)
        {
            var data = tempPlayerData.get(player.getName().getString());
            if(data.HouseBossbar != null)
            {
                data.HouseBossbar.destroy(player.getServer());
                data.HouseBossbar = null;
            }
        }
        return;
    }

    @SubscribeEvent
    public void onEntityJoin(EntityJoinLevelEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if(Implementation.serverBlocked)
            {
                event.setCanceled(true);
                
                player.connection.disconnect(Component.literal("§c§lServer je trenutno zaključan!\nPokusajte ponovo kasnije..."));
                return;
            }
        }
    }

    // Player join
    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            betterOnServerStarted_(player);
            String hash = ResourcePackManager.getCurrentHash();
            if(!hash.isEmpty())
            {
                ClientboundResourcePackPushPacket p = new ClientboundResourcePackPushPacket(
                    PACK_UUID,
                    Const.SystemInfo.RESOURCES_LINK,
                    hash, true,
                    Optional.of(Component.literal("Molimo prihvatite pack za igru na našem serveru."))
                );
                player.connection.send(p);
            }

            ///token generatori
            if(!tokens.containsKey(player.getName().getString()))
            {
                //one time token gen
                tokens.put(player.getName().getString(), new playerToken(Utils.generateToken(player.getName().getString())));
            }
            ///ostale stvari
            String format1 = Text.Format(Text.Col.GRAY.get());
            sendPlayerMessage(player, "Dobrodosao na server!");
            sendPlayerMessage(player, "§7Ukucaj §e`/" + Const.CommandNames.HELP + "` §7za komande.");
            sendInfo(player, "Za otvaranje glavnog menija, kucajte /" + Const.CommandNames.MAIN_MENU + ".");
            sendPlayerMessage(player, "§7Da bi ste nastavili sa igrom ili napravili račun, kucajte §e`/login <lozinka>` §7.");

            Component customMessage = Component.literal("§6[EleCraft]: §7Igrač §a" + player.getScoreboardName() + " §7je usao na server!");
            
            player.getServer().getPlayerList().broadcastSystemMessage(customMessage, false);

            frozenPlayers.add(player.getName().getString());
            ulogovan.put(player.getName().getString(), 0);
            String playerName = player.getName().getString();

            if(!playerData.containsKey(getToken(player)))
            {
                playerData.put(getToken(player), new PlayerData());
            }
            if(!smrti.containsKey(getToken(player)))
            {
                smrti.put(getToken(player), 0);
            }

            if(!tempPlayerData.containsKey(player.getName().getString()))
            {
                tempPlayerData.put(player.getName().getString(), new TemporaryPlayerData());
            }
            ///////////////////////
            playerData.get(getToken(player)).logins++;

            prikaziInterfejs(player);

            tempPlayerData.get(playerName).pos[0] = player.getX();
            tempPlayerData.get(playerName).pos[1] = player.getY();
            tempPlayerData.get(playerName).pos[2] = player.getZ();
            tempPlayerData.get(playerName).dimension = GetDimension(player);

            tempPlayerData.get(playerName).ulogovan = false;
            tempPlayerData.get(playerName).houseLoading = INVALID_HOUSE;
            tempPlayerData.get(playerName).streak = 0;
            tempPlayerData.get(playerName).streak_cd = false;
            tempPlayerData.get(playerName).streak_cd_sound = false;

            if(playerData.get(getToken(player)).house != INVALID_HOUSE)
            {
                houses[playerData.get(getToken(player)).house].level = player.getServer().getLevel(Level.OVERWORLD);
            }

            if(playerData.get(getToken(player)).sideboard == false)
            {
                SidebarManager.hideRaw(player);
            }

            tempPlayerData.get(playerName).HouseBossbar = new BossBarManager("housebar_" + Utils.hash(playerName));
            if(GetDimension(player) == Const.ServerDimensions.LOBBY)
            {
                Utils.teleport(player, LOBBY_SPAWN_POS_X, LOBBY_SPAWN_POS_Y, LOBBY_SPAWN_POS_Z, Const.ServerDimensions.LOBBY);
            }

            //everythin else
            player.refreshDisplayName();
            player.refreshTabListName();
            return;
        }
    }

    @SubscribeEvent
    public void onNameFormat(PlayerEvent.NameFormat event)
    {
        Entity ent = event.getEntity();
        String prefix = "";
        ChatFormatting col = ChatFormatting.WHITE;
        if(ent instanceof ServerPlayer player)
        {
            //gledamo štae player
            if(isAdmin(player))
            {
                prefix = "ADMIN";
                col = ChatFormatting.BLACK;
            }
            if(isMod(player) && !isAdmin(player))
            {
                prefix = "MODERATOR";
                col = ChatFormatting.RED;
            }
            //postavljamo promenu gg
            if(!prefix.isEmpty())
            {
                Component c = Component.literal(prefix + " ").withStyle(col).withStyle(ChatFormatting.BOLD).append(
                    Component.literal(player.getName().getString()).withStyle(ChatFormatting.WHITE).withStyle(ChatFormatting.ITALIC)
                );
                event.setDisplayname(c);
            }
        }
        return;
    }
    
    @SubscribeEvent
    public void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            Component customMessage = Component.literal("§6[EleCraft]: §7Igrač §a" + player.getScoreboardName() + " §7je izasao sa servera!");
        
            player.getServer().getPlayerList().broadcastSystemMessage(customMessage, false);
        }
        return;
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event)
    {
        if(event.getEntity() instanceof ServerPlayer player)
        {
            sendPlayerMessage(player, "Umrli ste!");
            smrti.merge(getToken(player), 1, Integer::sum);
            prikaziInterfejs(player);
            Component customMessage = Component.literal("§6[SMRT]: §7Igrač §a" + player.getScoreboardName() + " §7je umro! Respawnovan je!");
        
            player.getServer().getPlayerList().broadcastSystemMessage(customMessage, false);
            return;
        }
    }

    // Registracija komandi
    @SubscribeEvent
    public void onCommandRegister(RegisterCommandsEvent event)
    {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
            Commands.literal(Const.CommandNames.HELP)
                .executes(this::helpCommand)
        );

        dispatcher.register(
            Commands.literal("smrti")
                .executes(this::smrtiKomanda)
        );


        dispatcher.register(
            Commands.literal("login")
                .then(
                    Commands.argument("lozinka", StringArgumentType.string())
                        .executes(this::loginKomanda)
                )
        );

        dispatcher.register(
            Commands.literal("changepassword")
                .then(
                    Commands.argument("lozinka", StringArgumentType.string())
                        .executes(this::changePasswordKomanda)
                )
        );

        dispatcher.register(
            Commands.literal("dajnovac")
                //.requires(source -> source.hasPermission(2)) // Samo za OP (Admina)
                .then(
                    Commands.argument(Const.CommandArgumentNames.PLAYER_ARGUMENT, EntityArgument.player()) // Prvi argument: igrač
                        .then(
                            Commands.argument(Const.CommandArgumentNames.AMOUNT_ARGUMENT, IntegerArgumentType.integer(1)) // Drugi argument: broj
                                .executes(this::dajCoinsKomanda) // Poziva funkciju na kraju
                        )
                )
        );

        dispatcher.register(
            Commands.literal("goto")
                //.requires(source -> source.hasPermission(2)) // Samo za OP (Admina)
                .then(
                    Commands.argument(Const.CommandArgumentNames.PLAYER_ARGUMENT, EntityArgument.player()) // Prvi argument: igrač
                        .executes(this::gotoKomanda) // Poziva funkciju na kraju
                )
        );

        dispatcher.register(
            Commands.literal("makemod").then(
                Commands.argument(Const.CommandArgumentNames.PLAYER_ARGUMENT, EntityArgument.player()).executes(
                    this::makeModKomanda
                )
            )
        );

        dispatcher.register(
            Commands.literal("gethere")
                //.requires(source -> source.hasPermission(2)) // Samo za OP (Admina)
                .then(
                    Commands.argument(Const.CommandArgumentNames.PLAYER_ARGUMENT, EntityArgument.player()) // Prvi argument: igrač
                        .executes(this::getHereKomanda) // Poziva funkciju na kraju
                )
        );

        dispatcher.register(
            Commands.literal("adminhelp")
                .executes(this::adminHelpKomanda)
        );

        dispatcher.register(
            Commands.literal("postavke")
                .executes(this::settingsKomanda)
        );

        dispatcher.register(
            Commands.literal("shop")
                .executes(this::shopKomanda)
        );

        dispatcher.register(
            Commands.literal("sethome")
                .executes(this::setHomeKomanda)
        );

        dispatcher.register(
            Commands.literal("gohome").executes(
                this::goHomeKomanda
            )
        );

        dispatcher.register(
            Commands.literal("delhome")
                .executes(this::delHomeKomanda)
        );

        dispatcher.register(
            Commands.literal("partiklez")
                .executes(this::PARTIKLEZ_CMD)
        );

        dispatcher.register(
            Commands.literal(Const.CommandNames.SERVER_OFF)
                .executes(this::gasiServerCmd)
        );

        dispatcher.register(
            Commands.literal(
                Const.CommandNames.DB_SAVE
            ).executes(
                this::saveDatabaseCmd
            )

        );

        dispatcher.register(
            Commands.literal(
                Const.CommandNames.SHOP_SALE
            ).executes(
                this::shopSaleCmd
            )
        );

        dispatcher.register(
            Commands.literal(
                Const.CommandNames.SMP
            ).executes(
                this::smpKomanda
            )
        );

        dispatcher.register(
            Commands.literal(
                Const.CommandNames.LOBBY
            ).executes(
                this::lobbyKomanda
            )
        );

        dispatcher.register(
            Commands.literal(
                Const.CommandNames.SET_LOBBY_SPAWN
            ).executes(
                this::setlobbyspawnKomanda
            )
        );

        dispatcher.register(
            Commands.literal(
                Const.CommandNames.MAIN_MENU
            ).executes(
                this::MainMenuCommand
            )
        );
        
        return;
    }

    @SubscribeEvent
    public void onCommandExecute(CommandEvent event)
    {
        String commandText = event.getParseResults().getReader().getString();

        CommandSourceStack e = event.getParseResults().getContext().getSource();
        ServerPlayer p = e.getPlayer();

        if(e.isSilent())
        {
            return;
        }
        
        if(commandText.equals("stop"))
        {
            event.setCanceled(true);
            
            e.sendFailure(Component.literal("§c[Greška]: §eOva komanda je isključena na ovom serveru!"));
        }

        if(p != null)
        {
            String pn = p.getName().getString();
            if(tempPlayerData.get(pn).CommandCooldown)
            {
                event.setCanceled(true);
                sendac(p, "Stop! Komande možete koristiti samo svake 2 sekunde.");
                return;
            }
            tempPlayerData.get(pn).CommandCooldown = true;
        }
        return;
    }

    private int setlobbyspawnKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer p = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(p)) return 1;
            if(!isAdmin(p))
            {
                sendError(p, "Niste ovlašteni za upotrebu ove komande!");
                return 1;
            }
            
            String pn = p.getName().getString();
            if(GetDimension(p) != Const.ServerDimensions.LOBBY)
            {
                sendError(p, "Morate se nalaziti u LOBBY-u.");
                return 1;
            }

            LOBBY_SPAWN_POS_X = p.getX();
            LOBBY_SPAWN_POS_Y = p.getY();
            LOBBY_SPAWN_POS_Z = p.getZ();

            sendInfo(p, "Uspješno ste postavili koordinate LOBBY SPAWN-a.");
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    private int saveDatabaseCmd(CommandContext<CommandSourceStack> ctx)
    {
        CommandSourceStack source = ctx.getSource();
        MinecraftServer server = source.getServer();
        boolean DOSAVE = false;
        if(source.getPlayer() != null)
        {
            ServerPlayer player = source.getPlayer();
            if(LoginCheckFail(player)) return 1;

            if(!isAdmin(player))
            {
                sendError(player, "Niste ovlašteni za upotrebu ove komande!");
                return 1;
            }
            DOSAVE = true;
        }
        else
        {
            DOSAVE = true;
        }

        if(DOSAVE)
        {
            elecraft.utils.write("Pokrenut je sistem za čuvanje podataka servera!");
            Async(() -> {
                elecraft.utils.write("ASYNC...");
                SafeSave();
            });
        }
        return 1;
    }
    private int shopSaleCmd(CommandContext<CommandSourceStack> ctx)
    {
        CommandSourceStack source = ctx.getSource();
        MinecraftServer server = source.getServer();
        boolean DOSHIT = false;
        if(source.getPlayer() != null)
        {
            ServerPlayer player = source.getPlayer();
            if(LoginCheckFail(player)) return 1;

            if(!isAdmin(player))
            {
                sendError(player, "Niste ovlašteni za upotrebu ove komande!");
                return 1;
            }
            DOSHIT = true;

            if(Implementation.SHOP_SALE)
            {
                sendInfo(player, "Uspješno ste isključili sniženje cijena u marketu!");
            }
            else
            {
                sendInfo(player, "Uspješno ste uključili 50% sniženje cijena u marketu!");
            }
        }
        else
        {
            DOSHIT = true;
            if(Implementation.SHOP_SALE)
            {
                elecraft.utils.write("Uspješno ste isključili sniženje cijena u marketu!");
            }
            else
            {
                elecraft.utils.write("Uspješno ste uključili 50% sniženje cijena u marketu!");
            }
        }

        if(DOSHIT)
        {
            Implementation.SHOP_SALE = !Implementation.SHOP_SALE;
        }
        return 1;
    }

    private int gasiServerCmd(CommandContext<CommandSourceStack> ctx)
    {
        Async(() -> {
            elecraft.utils.write("ASINHRONI SISTEM CUVANJA PODATAKA POKRENUT!");
            SafeSave();
        });
        CommandSourceStack source = ctx.getSource();
        MinecraftServer server = source.getServer();
        if(source.getPlayer() != null)
        {
            ServerPlayer player = source.getPlayer();
            if(LoginCheckFail(player)) return 1;

            if(!isAdmin(player))
            {
                sendError(player, "Niste ovlašteni za upotrebu ove komande!");
                return 1;
            }

            ServerStop(server);
            return 1;
        }
        if(source.getPlayer() == null)
        {
            ServerStop(server);
        }
        return 1;
    }

    private void ServerStop(MinecraftServer server)
    {
        elecraft.utils.write("Pokrenut je sistem za gašenje servera!");

        AsyncRunning = false;
        SafeSave();

        Implementation.serverShutdown = 0;
        Implementation.serverBlocked = true;

        //
        try
        {
            server.getPlayerList().getPlayers().forEach(player -> 
                player.connection.disconnect(Component.literal("Server se gasi ili je u toku održavanje."))
            );
        }
        catch(Exception e)
        {
            elecraft.utils.write("players.foreach.disconnect(): ERROR!" + e.getMessage());
        }
        return;
    }
    
    private int PARTIKLEZ_CMD(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(player)) return 1;

            if(!isAdmin(player))
            {
                sendError(player, "Niste ovlašteni za upotrebu ove komande!");
                return 1;
            }

            serverSettings$particles = !serverSettings$particles;
            if(serverSettings$particles)
            {
                sendInfo(player, "UKLJUCENO!");
            }
            if(!serverSettings$particles)
            {
                sendInfo(player, "ISKLJUCENO!");
            }

            
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    public int shopKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(player)) return 1;

            sendac(player, "Koristite komandu /" + Const.CommandNames.MAIN_MENU + ".");
            return 1;
        }
        catch(Exception e)
        {
            elecraft.utils.write("Greška u komandi za market -> " + e.getMessage());
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    private int goHomeKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(player)) return 1;

            sendac(player, "Koristite komandu /" + Const.CommandNames.MAIN_MENU + ".");
            return 1;
        }
        catch(Exception e)
        {
            elecraft.utils.write("Greška u komandi za gohome -> " + e.getMessage());
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    private int MainMenuCommand(CommandContext<CommandSourceStack> ctx)
    {
        try {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            if(LoginCheckFail(player)) return 1;

            showMenu(player, Const.ServerMenus.MAIN_MENU);
            
            return 1;
        } catch (Exception e) {
            elecraft.utils.write("Greška u komandi za gohome -> " + e.getMessage());
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    public int setHomeKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(player)) return 1;

            if(playerData.get(getToken(player)).house != INVALID_HOUSE)
            {
                sendError(player, "Vi vec imate kucu, prvo staru obrisite iz sistema.");
                return 1;
            }

            Integer h = Utils.isPlayerInHouseBase(player);
            if(h != INVALID_HOUSE)
            {
                sendError(player, "Nalazite se u bazi nečije kuće.");
                return 1;
            }
            
            h = Utils.isPlayerNearHouse(player);
            if(h != INVALID_HOUSE)
            {
                sendError(player, "Nalazite se u okolici nečije kuće! Udaljite se!");
                return 1;
            }

            if(GetDimension(player) == Const.ServerDimensions.LOBBY)
            {
                sendError(player, "Ne možete kreirati kuću u LOBBY-u.");
                return 1;
            }

            Integer house = 0;
            house = createHouse(player, player.getX(), player.getY(), player.getZ(), GetDimension(player));
            if(house == INVALID_HOUSE)
            {
                sendError(player, "Trenutno nije moguće kreirati kuće na serveru.");
                return 1;
            }
            playerData.get(getToken(player)).house = house;

            sendInfo(player, "Uspješno ste postavili zaštitu za kuću.");
            return 1;
        }
        catch(Exception e)
        {
            elecraft.utils.write("Greška u komandi za sethome -> " + e.getMessage());
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    public int lobbyKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer p = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(p)) return 1;
           
            sendac(p, "Koristite komandu /" + Const.CommandNames.MAIN_MENU + ".");
            return 1;
        }
        catch(Exception e)
        {
            elecraft.utils.write("Greška u komandi za LOBBY -> " + e.getMessage());
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }
    public int smpKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer p = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(p)) return 1;
            
            sendac(p, "Koristite komandu /" + Const.CommandNames.MAIN_MENU + ".");
            return 1;
        }
        catch(Exception e)
        {
            elecraft.utils.write("Greška u komandi za SMP -> " + e.getMessage());
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    public int delHomeKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(player)) return 1;

            if(playerData.get(getToken(player)).house == INVALID_HOUSE)
            {
                sendError(player, "Vi nemate postavljenu kuću!");
                return 1;
            }

            Integer id = playerData.get(getToken(player)).house;
            if(!Utils.isPlayerInHouseBase(player, id))
            {
                sendError(player, "Morate stajati u okolici svoje kuće!");
                return 1;
            }

            deleteHouse(player, id);
            sendInfo(player, "Uspješno ste uklonili zaštitu za kuću.");
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }


    // 

    private int adminHelpKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(player)) return 1;

            if(!isAdmin(player)) //op admin privilegia
            {
                sendError(player, "Nemate dozvolu za upotrebu ove komande!");
                return 1;
            }

            /*sendPlayerMessage(player, "Lista admin komandi:\n" +
                "\t/dajnovac" 
            );*/
            OpenBook(player, Const.ServerBooks.ADMIN_COMMAND_HELP);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    private int dajCoinsKomanda(CommandContext<CommandSourceStack> ctx)
    {
        ServerPlayer player = null;
        try
        {
            player = ctx.getSource().getPlayerOrException();
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(Component.literal("Ovu komandu može koristiti samo igrač!"));
            return 1;
        }

        if(LoginCheckFail(player)) return 1;

        if(!isAdmin(player)) //op admin privilegia
        {
            sendError(player, "Nemate dozvolu za upotrebu ove komande!");
            return 1;
        }

        ServerPlayer target = null;
        try
        {
            target = EntityArgument.getPlayer(ctx, Const.CommandArgumentNames.PLAYER_ARGUMENT);
        }
        catch(Exception e)
        {
            sendError(player, "Došlo je do greške prilikom dohvaćanja argumenta za targetiranog igrača!");
            return 1;
        }
        Integer amount = null;
        try
        {
            amount = IntegerArgumentType.getInteger(ctx, Const.CommandArgumentNames.AMOUNT_ARGUMENT);
        }
        catch(Exception e)
        {
            sendError(player, "Došlo je do greške prilikom dohvaćanja argumenta za količinu novca!");
            return 1;
        }

        playerData.get(getToken(target)).coins += amount;
        sendInfo(player, "Dali ste igraču " + target.getName().getString() + " " + amount.toString() + " novcica.");
        sendInfo(target, "Dobili ste " + amount.toString() + " novcica od administracije!");

        prikaziInterfejs(target);
        return 1;
    }
    private int gotoKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            String playerName = player.getName().getString();

            if(LoginCheckFail(player)) return 1;

            if(!isMod(player)) //op admin privilegia
            {
                sendError(player, "Nemate dozvolu za upotrebu ove komande!");
                return 1;
            }

            ServerPlayer target = EntityArgument.getPlayer(ctx, Const.CommandArgumentNames.PLAYER_ARGUMENT);
            String targetName = target.getName().getString();

            sendInfo(target, "Admin " + playerName  + " se teleportovao do Vas.");
            sendInfo(player, "Teleportovali ste se do igrača " + targetName + ".");

            Double x = target.getX();
            Double y = target.getY();
            Double z = target.getZ();
            Integer d = GetDimension(target);

            Utils.teleport(player, x,y,z,d);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }
    private int makeModKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            String playerName = player.getName().getString();

            if(LoginCheckFail(player)) return 1;

            if(!isAdmin(player)) //op admin privilegia
            {
                sendError(player, "Nemate dozvolu za upotrebu ove komande!");
                return 1;
            }

            ServerPlayer target = EntityArgument.getPlayer(ctx, Const.CommandArgumentNames.PLAYER_ARGUMENT);
            String targetName = target.getName().getString();

            if(isAdmin(target))
            {
                sendInfo(player, "Taj igrač je administrator!");
                return 1;
            }

            if(!playerData.get(getToken(target)).moderator)
            {
                sendInfo(target, "Admin " + playerName  + " Vam je postavio moderatora.");
                sendInfo(player, "Postavili ste moderatora igraču " + targetName + ".");

                playerData.get(getToken(target)).moderator = true;
                return 1;
            }
            sendInfo(target, "Admin " + playerName  + " Vam je uklonio poziciju moderatora.");
            sendInfo(player, "Sklonili ste moderatora igraču " + targetName + ".");

            playerData.get(getToken(target)).moderator = false;
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }
    private int getHereKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            String playerName = player.getName().getString();

            if(LoginCheckFail(player)) return 1;

            if(!isMod(player)) //op admin privilegia
            {
                sendError(player, "Nemate dozvolu za upotrebu ove komande!");
                return 1;
            }

            ServerPlayer target = EntityArgument.getPlayer(ctx, Const.CommandArgumentNames.PLAYER_ARGUMENT);
            String targetName = target.getName().getString();

            sendInfo(target, "Admin " + playerName  + " Vas teleportovao je teleportovao do sebe.");
            sendInfo(player, "Teleportovali ste igrača " + targetName + " do sebe.");

            Double x = player.getX();
            Double y = player.getY();
            Double z = player.getZ();
            Integer d = GetDimension(player);

            Utils.teleport(target, x,y,z,d);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }
    
    private int helpCommand(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            OpenBook(player, Const.ServerBooks.COMMAND_HELP);
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }
    private int settingsKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            
            if(LoginCheckFail(player)) return 1;

            showMenu(player, Const.ServerMenus.SETTINGS_MENU);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    private static boolean LoginCheckFail(ServerPlayer player)
    {
        if(isLoggedIn(player) == 0)
        {
            sendError(player, "Niste prijavljeni na Vaš račun!");
            return true;
        }
        return false;
    }

    // death cmd
    private int smrtiKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(LoginCheckFail(player)) return 1;

            int broj = smrti.getOrDefault(getToken(player), 0);
            sendPlayerMessage(player, "Umrli ste " + broj + " puta.");
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }
    // login cmd
    private int loginKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            Integer ulogovan_local = ulogovan.get(player.getName().getString());
            String playerName = player.getName().getString();

            String __lozinka = StringArgumentType.getString(ctx, "lozinka");

            if(ulogovan_local == 1)
            {
                sendPlayerMessage(player, "Vi ste vec ulogovani!");
                return 1;
            }
            if(ulogovan_local == 0)
            {
                String lozinka = lozinke.get(getToken(player));
                if(lozinka == null)
                {
                    if(__lozinka.length() < 8)
                    {
                        sendError(player, "Duzina Vase lozinke ne smije biti kraca od 8 karaktera!");
                        return 1;
                    }
                    lozinke.put(getToken(player), __lozinka);
                    ulogovan.put(playerName, 1);
                    frozenPlayers.remove(playerName);
                    sendPlayerMessage(player, "Uspjesno ste se registrovali!");
                    sendInfo(player, "Vasa lozinka je: §s" + __lozinka);

                    tempPlayerData.get(playerName).ulogovan = true;
                    sendAdminInfo("Igrač " + player.getName().getString() + " se registrovao na server!");
                    return 1;
                }
                if(!lozinka.equals(__lozinka))
                {
                    sendPlayerMessage(player, "Unesena lozinka nije tacna.");
                    //sendPlayerMessage(player, "lozinka: `" + lozinka + "`, __lozinka: `" + __lozinka + "`");
                    return 1;
                }
                ulogovan.put(playerName, 1);
                frozenPlayers.remove(playerName);
                sendPlayerMessage(player, "Uspjesno ste se prijavili na Vas račun!");

                sendAdminInfo("Igrač " + player.getName().getString() + " se prijavio na server!");
                tempPlayerData.get(playerName).ulogovan = true;

                Integer house = playerData.get(getToken(player)).house;
                if(house != INVALID_HOUSE)
                {
                    tempPlayerData.get(playerName).houseLoading = 0;
                    Utils.teleport(player, houses[house].x, houses[house].y, houses[house].z, houses[house].dimension);
                    Utils.sendTitle(player, "Učitavanje...");
                    frozenPlayers.add(playerName);
                    createHouseLabel(house);
                }
                return 1;
            }
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }
    //change pw
    private int changePasswordKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            Integer ulogovan_local = ulogovan.get(player.getName().getString());
            //String imeigrača = player.getName().getString();

            String __lozinka = StringArgumentType.getString(ctx, "lozinka");

            if(ulogovan_local == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas račun da biste promjenili lozinku.");
                return 1;
            }
            if(ulogovan_local == 1)
            {
                if(__lozinka.length() < 8)
                {
                    sendError(player, "Duzina Vase lozinke ne smije biti kraca od 8 karaktera!");
                    return 1;
                }
                lozinke.put(getToken(player), __lozinka);
                sendInfo(player, "Uspjesno ste promjenili Vasu lozinku!");
                sendAdminInfo("Igrač " + player.getName().getString() + " je promjenio svoju lozinku!");
                return 1;
            }
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu može koristiti samo igrač!")
            );
        }
        return 1;
    }

    /////// FREEZE SISTEM ///////
    public static class MenuTag extends ChestMenu
    {
        public MenuTag(int id, Inventory inv, int r)
        {
            super(g(r), id, inv, new SimpleContainer(9 * r), r);
        }
        
        private static MenuType<ChestMenu> g(int rows) 
        {
            return switch (rows) {
                case 1 -> MenuType.GENERIC_9x1;
                case 2 -> MenuType.GENERIC_9x2;
                case 3 -> MenuType.GENERIC_9x3;
                case 4 -> MenuType.GENERIC_9x4;
                case 5 -> MenuType.GENERIC_9x5;
                default -> MenuType.GENERIC_9x6;
            };
        }

        @Override
        public void clicked(int slotId, int button, ClickType clickType, Player player)
        {
            super.clicked(slotId, button, clickType, player);
        }

        @Override
        public ItemStack quickMoveStack(Player player, int index)
        {
            return ItemStack.EMPTY; 
        }

        @Override
        public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
        {
            return false;
        }
    }

    public static void showMenu(ServerPlayer player, Integer menu_)
    {
        //player.playNotifySound(SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.MASTER, Const.Misc.NOTIFY_SOUND_VOLUME, 1f);
        openedMenu.put(player, menu_);
        //////////settings
        if(menu_ == Const.ServerMenus.SETTINGS_MENU)
        {
            sendAdminInfo("Igrač " + player.getName().getString() + " podešava svoj nalog!");
            player.openMenu(new SimpleMenuProvider((id, inventory, p) -> {
                Integer SettingCount = SettingsSystem.getSettingCount();
                Integer ROWS = Const.Misc.MENU_ROWS;
                final Integer SLOTS = Const.Misc.MENU_ROWS * Const.Misc.CHEST_COLUMNS;
                
                MenuTag menu = new MenuTag(id, inventory, ROWS);

                ItemStack[] item = new ItemStack[SLOTS];

                for(int i = 0; i < SLOTS; ++i)
                {
                    if(i >= SettingCount)
                    {
                        item[i] = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);
                        item[i].set(DataComponents.CUSTOM_NAME, Component.literal(
                            Text.Format(Text.Col.GRAY, Text.Style.ITALIC) + Const.ServerMenus.EMPTY_SLOT_STR
                        ));
                    }
                    else
                    {
                        PlayerBooleanSetting s = SettingsSystem.Settings.get(i);
                        boolean status = s.get.test(player);
                        item[i] = new ItemStack(status ? Items.LIME_STAINED_GLASS_PANE : Items.RED_STAINED_GLASS_PANE);
                        item[i].set(DataComponents.CUSTOM_NAME, Component.literal(
                            status ? Text.Format(Text.Col.GREEN, Text.Style.UNDERLINE) + s.name + ":" + Text.Format(Text.Style.BOLD.Code) + " UKLJUČENO" :
                            Text.Format(Text.Col.RED, Text.Style.UNDERLINE) + s.name + ":" + Text.Format(Text.Style.BOLD.Code) + " ISKLJUČENO")
                        );
                        item[i].set(DataComponents.LORE, new ItemLore(List.of(
                            Component.literal(Text.Format(Text.Col.GRAY.Code) + s.description),
                            Component.literal(""),
                            Component.literal(Text.Format(Text.Col.YELLOW.Code) + "Klikni da promijeniš!")
                        )));
                    }
                }

                for(Integer i = 0; i < SLOTS; ++i)
                {
                    menu.getSlot(i).set(item[i]);
                }
                
                return menu;
            }, Component.literal(Text.Format(Text.Style.BOLD, Text.Col.DARK_GREEN) + "Postavke")));
        }
        /////////////////shop
        else if(menu_ == Const.ServerMenus.SHOP_MENU)
        {
            sendAdminInfo("Igrač " + player.getName().getString() + " vrsi kupovinu!");
            player.openMenu(new SimpleMenuProvider((id, inventory, p) -> {
                String pn = player.getName().getString();
                List<ShopItem> SHOP_PAGE = ShopSystem.getPage(tempPlayerData.get(pn).ShopPage);
                Integer ShopItemCount = ShopSystem.getPageSize(tempPlayerData.get(pn).ShopPage);
                Integer ROWS = Utils.getShopRows(ShopItemCount);
                Integer PAGES = ShopSystem.getShopPagesCount();
                final Integer SLOTS = ROWS * Const.Misc.CHEST_COLUMNS;
                MenuTag menu = new MenuTag(id, inventory, ROWS);
                ItemStack[] item = new ItemStack[ShopItemCount];
                if(ShopItemCount > 15)
                {
                    throw new IllegalArgumentException("Previše shop itema na stranici, MAX 15");
                }
        
                for(int i = 0; i < ShopItemCount; ++i)
                {
                    /* 
                    if(i >= ShopItemCount)
                    {
                        item[i] = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);
                        item[i].set(DataComponents.CUSTOM_NAME, Component.literal(
                            Text.Format(Text.Col.GRAY, Text.Style.ITALIC) + Const.ServerMenus.EMPTY_SLOT_STR
                        ));
                    }*/
                 
                    ShopItem LOCAL_ITEM = SHOP_PAGE.get(i);
                    String PRICE_TAG = Implementation.SHOP_SALE ?
                        (Text.Format(Text.Col.RED, Text.Style.STRIKETHROUGH) + Const.Emojis.MONEY_BAG + " " + LOCAL_ITEM.price + Text.Format(Text.Col.WHITE) + " " +
                        Text.Format(Text.Col.YELLOW) + Const.Emojis.MONEY_BAG + " " + (LOCAL_ITEM.price / 2)) :
                        (Text.Format(Text.Col.YELLOW.Code) + Const.Emojis.MONEY_BAG + " " + LOCAL_ITEM.price)
                    ;
                    item[i] = new ItemStack(LOCAL_ITEM.MinecraftItem, 1);
                    item[i].set(DataComponents.CUSTOM_NAME, Component.literal(Text.Format(Text.Col.GREEN.Code, Text.Style.UNDERLINE.Code) + LOCAL_ITEM.name));
                    item[i].set(DataComponents.LORE, new ItemLore(List.of(
                        Component.literal(Text.Format(Text.Col.GRAY.Code) + "Artikal: " + Text.Format(Text.Style.BOLD.Code) + LOCAL_ITEM.name),
                        Component.literal(Text.Format(Text.Col.GRAY.Code) + "Količina: " + Text.Format(Text.Style.BOLD.Code) + LOCAL_ITEM.amount + "x"),
                        Component.literal(""),
                        Component.literal(Text.Format(Text.Col.GRAY.Code) + "Cijena: "),
                        Component.literal(PRICE_TAG),
                        Component.literal(Text.Format(Text.Col.YELLOW) + "Kliknite za kupovinu!")
                    )));
                }

                //itemi

                Supplier<ItemStack> e = () -> {
                    ItemStack i = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);

                    i.set(
                        DataComponents.CUSTOM_NAME,
                        Component.literal(
                            Text.Format(Text.Col.GRAY, Text.Style.ITALIC) + Const.ServerMenus.EMPTY_SLOT_STR
                        )
                    );

                    return i;
                };

                for(int i = 0; i < SLOTS; ++i)
                {
                    menu.getSlot(i).set(e.get());
                }
                ItemLore f = new ItemLore(List.of(
                    Component.literal(
                        Text.Format(Text.Col.YELLOW) +
                        "Strana: " + Text.Format(Text.Col.BLUE) +
                        Integer.valueOf(tempPlayerData.get(pn).ShopPage + 1).toString() +
                        Text.Format(Text.Col.GRAY) + "/" + Text.Format(Text.Col.BLUE) +
                        Integer.valueOf(PAGES).toString()
                    )
                ));
                ItemStack prev = Implementation.CreateCustomHead(Const.ServerMenus.Buttons.PREVIOUS_PAGE_HEAD);
                prev.set(
                    DataComponents.CUSTOM_NAME,
                    Component.literal(Text.Format(Text.Col.DARK_PURPLE) + Const.ServerMenus.Buttons.PREVIOUS_PAGE)
                );
                prev.set(DataComponents.LORE,f);

                ItemStack next = Implementation.CreateCustomHead(Const.ServerMenus.Buttons.NEXT_PAGE_HEAD);
                next.set(
                    DataComponents.CUSTOM_NAME,
                    Component.literal(Text.Format(Text.Col.DARK_PURPLE) + Const.ServerMenus.Buttons.NEXT_PAGE)
                );
                next.set(DataComponents.LORE, f);

                Utils.placeShopItemsCentered(menu, List.of(item), ROWS);
                Utils.placeNavigationArrows(List.of(prev, next), menu, SLOTS);
                return menu;
            },
            Component.literal(
                Text.Format(Text.Col.DARK_GREEN, Text.Style.ITALIC, Text.Style.UNDERLINE) +
                "Market" +
                (Implementation.SHOP_SALE ? Text.Format(Text.Col.GRAY) + " - " + Text.Format(Text.Col.DARK_PURPLE, Text.Style.BOLD) + "50% OFF!" : "")
            )));
        }
        /////////////////tš
        else if(menu_ == Const.ServerMenus.TELEPORT_MENU)
        {
            sendAdminInfo("Igrač " + player.getName().getString() + " ulazi u meni za teleportaciju!");
            player.openMenu(new SimpleMenuProvider((id, inventory, p) -> {
                Integer TeleportLocationCount = TeleportSystem.getTpCount();
                Integer ROWS = Utils.getShopRows(TeleportLocationCount);
                final Integer SLOTS = ROWS * Const.Misc.CHEST_COLUMNS;
                MenuTag menu = new MenuTag(id, inventory, ROWS);
                ItemStack[] item = new ItemStack[TeleportLocationCount];
        
                for(int i = 0; i < TeleportLocationCount; ++i)
                {
              
                    TeleportLocation TP_LOCATION = TeleportSystem.Teleports.get(i);
                    item[i] = CreateCustomHead(TP_LOCATION.headTexture);
                    item[i].set(DataComponents.CUSTOM_NAME, Component.literal(Text.Format(Text.Col.GOLD, Text.Style.BOLD, Text.Style.ITALIC) + TP_LOCATION.name));
                    item[i].set(DataComponents.LORE, new ItemLore(List.of(
                        Component.literal(Text.Format(Text.Col.DARK_GREEN) + TP_LOCATION.description),
                        Component.literal(""),
                        Component.literal(Text.Format(Text.Col.GRAY) + "Klikni da se teleportuješ!")
                    )));
                }

                //itemi

                Supplier<ItemStack> e = () -> {
                    ItemStack i = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);

                    i.set(
                        DataComponents.CUSTOM_NAME,
                        Component.literal(
                            Text.Format(Text.Col.GRAY, Text.Style.ITALIC) + Const.ServerMenus.EMPTY_SLOT_STR
                        )
                    );

                    return i;
                };

                for(int i = 0; i < SLOTS; ++i)
                {
                    menu.getSlot(i).set(e.get());
                }

                Utils.placeShopItemsCentered(menu, List.of(item), ROWS);
                
                return menu;
            },
            Component.literal(
                Text.Format(Text.Col.DARK_GREEN, Text.Style.ITALIC, Text.Style.UNDERLINE) + "Teleportacija"
                )));
        }
        /*//////////////////main menuz */
        else if(menu_ == Const.ServerMenus.MAIN_MENU)
        {
            sendAdminInfo("Igrač " + player.getName().getString() + " ulazi u glavni meni!");
            player.openMenu(new SimpleMenuProvider((id, inventory, p) -> {
                Integer MainMenuOptionCount = MainMenuSystem.getOptionCount();
                Integer ROWS = Utils.getShopRows(MainMenuOptionCount);
                final Integer SLOTS = ROWS * Const.Misc.CHEST_COLUMNS;
                MenuTag menu = new MenuTag(id, inventory, ROWS);
                ItemStack[] item = new ItemStack[MainMenuOptionCount];
        
                for(int i = 0; i < MainMenuOptionCount; ++i)
                {
              
                    MenuOption MENU_OPTION = MainMenuSystem.Options.get(i);
                    item[i] = CreateCustomHead(MENU_OPTION.headTexture);
                    item[i].set(DataComponents.CUSTOM_NAME, Component.literal(Text.Format(Text.Col.RED, Text.Style.BOLD, Text.Style.ITALIC) + MENU_OPTION.name));
                    item[i].set(DataComponents.LORE, new ItemLore(List.of(
                        Component.literal(Text.Format(Text.Col.GRAY) + MENU_OPTION.description),
                        Component.literal(""),
                        Component.literal(Text.Format(Text.Col.YELLOW) + "Klikni da otvoriš meni!")
                    )));
                }

                //itemi

                Supplier<ItemStack> e = () -> {
                    ItemStack i = new ItemStack(Items.GRAY_STAINED_GLASS_PANE);

                    i.set(
                        DataComponents.CUSTOM_NAME,
                        Component.literal(
                            Text.Format(Text.Col.GRAY, Text.Style.ITALIC) + Const.ServerMenus.EMPTY_SLOT_STR
                        )
                    );

                    return i;
                };

                for(int i = 0; i < SLOTS; ++i)
                {
                    menu.getSlot(i).set(e.get());
                }

                Utils.placeShopItemsCentered(menu, List.of(item), ROWS);
                
                return menu;
            },
            Component.literal(
                Text.Format(Text.Col.DARK_AQUA, Text.Style.ITALIC, Text.Style.UNDERLINE) + "Glavni Meni"
                )));
        }
        return;
    }

    private void SendHotbarMessage(ServerPlayer p, String text)
    {
        p.displayClientMessage(Component.literal(text), true);
    }

    public void morateBitiUlogovani(ServerPlayer player)
    {
        SendHotbarMessage(player, "§c§lMorate se prijaviti na račun!    §r§eKomanda: /login <lozinka>");
        return;
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event)
    {
        // PlayerTickEvent.Post je zamena za stari TickEvent.PlayerTickEvent
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if(isAdmin(player))
            {
                if(serverSettings$particles)
                {
                    Utils.addParticles(player);
                }
            }
            String playerName = player.getName().getString();
            if(tempPlayerData.get(playerName).houseLoading == 6)
            {
                tempPlayerData.get(playerName).houseLoading = INVALID_HOUSE;
                frozenPlayers.remove(playerName);
                Utils.teleport(
                    player,
                    tempPlayerData.get(playerName).pos[0],
                    tempPlayerData.get(playerName).pos[1],
                    tempPlayerData.get(playerName).pos[2],
                    tempPlayerData.get(playerName).dimension
                );
            }
            if(player.tickCount % 60 == 0)
            {
                tempPlayerData.get(playerName).sidebar = !tempPlayerData.get(playerName).sidebar;

                tempPlayerData.get(playerName).hpos[0] = player.getX();
                tempPlayerData.get(playerName).hpos[1] = player.getY();
                tempPlayerData.get(playerName).hpos[2] = player.getZ();
                tempPlayerData.get(playerName).hdimension = GetDimension(player);
                tempPlayerData.get(playerName).CommandCooldown = false;

                updateHouseBar(player);
            }
            // Unutar tvog onPlayerTick...
            if(playerData.get(getToken(player)).tips)
            if (player.tickCount % 12000 == 0)
            { // Svakih 10 minuta za svakog igrača posebno
                String tip = serverTips.get(new java.util.Random().nextInt(serverTips.size()));
                //player.displayClientMessage(Component.literal(tip), true); // 'true' šalje u Action Bar
                Utils.sendLongHotbarMsg(player, "§6(TIP) §f" + tip);
            }
            if (player.tickCount % 10000 == 0)
            {
                player.displayClientMessage(Component.literal("§e+1 Coin " + Const.Emojis.MONEY_BAG), true);
                playerData.get(getToken(player)).coins += 1;
            }
            if(playerData.get(getToken(player)).dynamicScoreboard)
            if(player.tickCount % 60 == 0)
            {
                prikaziInterfejs(player);
            }
            if(player.tickCount % 20 == 0)
            {
                if(tempPlayerData.get(playerName).houseLoading != INVALID_HOUSE) tempPlayerData.get(playerName).houseLoading++;
                SidebarManager.updateTitleOnly(player);
            }
            if(player.tickCount % 100 == 0)
            {
                if(tempPlayerData.get(playerName).streak_cd)
                {
                    player.playNotifySound(
                        SoundEvents.EXPERIENCE_ORB_PICKUP,
                        SoundSource.MASTER,
                        Const.Misc.NOTIFY_SOUND_VOLUME,
                        Const.SoundEventPitch.EXPERIENCE_ORB_PICKUP
                    );
                }
                tempPlayerData.get(playerName).streak_cd_sound = false;
                tempPlayerData.get(playerName).streak_cd = false;
            }
            if(player.tickCount % 160 == 0)
            {
                if(tempPlayerData.get(playerName).streak != 0)
                {
                    Integer coins = tempPlayerData.get(playerName).streak * 5;
                    tempPlayerData.get(playerName).streak = 0;
                    playerData.get(getToken(player)).coins += coins;
                    prikaziInterfejs(player);
                    SendHotbarMessage(
                        player,
                        Text.Format(Text.Col.DARK_AQUA.Code) +
                        "Dobili ste " + Const.Emojis.MONEY_BAG + " " + coins.toString() + " na HIT STREAK!"
                    );
                    player.playNotifySound(
                        SoundEvents.ARROW_HIT_PLAYER,
                        SoundSource.MASTER,
                        Const.Misc.NOTIFY_SOUND_VOLUME,
                        1f
                    );
                    tempPlayerData.get(playerName).streak_cd = true;
                }
            }
            if(tempPlayerData.get(playerName).ulogovan == false) if (isFrozen(player))
            {
                player.setDeltaMovement(0, 0, 0);
                player.hurtMarked = true;
                Double x = tempPlayerData.get(playerName).pos[0];
                Double y = tempPlayerData.get(playerName).pos[1];
                Double z = tempPlayerData.get(playerName).pos[2];
                Integer d = tempPlayerData.get(playerName).dimension;
                Utils.teleport(player, x,y,z,d);
                //player.closeContainer();
                morateBitiUlogovani(player);
            }
            if(tempPlayerData.get(playerName).ulogovan == true)
            {
                if(isFrozen(player))
                {
                    Integer house = playerData.get(getToken(player)).house;
                    if(house != -1)
                    {
                        Utils.teleport(player, houses[house].x, houses[house].y, houses[house].z, houses[house].dimension);
                    }
                }
            }
            //////SETTINGS///////
            AbstractContainerMenu menu = player.containerMenu;
            ItemStack MouseCursor = menu.getCarried();

            if(openedMenu.get(player) == Const.ServerMenus.SETTINGS_MENU) if(!MouseCursor.isEmpty() && MouseCursor.has(DataComponents.CUSTOM_NAME))
            {
                String ItemName = MouseCursor.getHoverName().getString();
                Integer SettingCount = SettingsSystem.getSettingCount();
                for(int i = 0; i < SettingCount; ++i)
                {
                    PlayerBooleanSetting setting = SettingsSystem.Settings.get(i);
                    if(ItemName.contains(setting.name))
                    {
                        menu.setCarried(ItemStack.EMPTY);
                        player.playNotifySound(SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.MASTER, Const.Misc.NOTIFY_SOUND_VOLUME, 1f);
                        setting.toggle.accept(player);

                        if(setting.doSomething != null)
                        {
                            setting.doSomething.accept(player);
                        }

                        showMenu(player, Const.ServerMenus.SETTINGS_MENU);
                        break;
                    }
                    else if(ItemName.contains(Const.ServerMenus.EMPTY_SLOT_STR))
                    {
                        menu.setCarried(ItemStack.EMPTY);
                        showMenu(player, Const.ServerMenus.SETTINGS_MENU);
                        break;
                    }
                }
            }
            ///////shop///////////
            if(openedMenu.get(player) == Const.ServerMenus.SHOP_MENU) if(!MouseCursor.isEmpty() && MouseCursor.has(DataComponents.CUSTOM_NAME))
            {
                String pn = player.getName().getString();
                String ItemName = MouseCursor.getHoverName().getString();
                Integer page = tempPlayerData.get(pn).ShopPage;
                List<ShopItem> SHOP_PAGE = ShopSystem.getPage(page);
                Integer ShopItemCount = ShopSystem.getPageSize(page);
                for(int i = 0; i < ShopItemCount; ++i)
                {
                    ShopItem item = SHOP_PAGE.get(i);
                    if(ItemName.contains(item.name))
                    {
                        menu.setCarried(ItemStack.EMPTY);
                        Integer PRICE = Implementation.SHOP_SALE ? (item.price / 2) : item.price;
                        if(playerData.get(getToken(player)).coins < item.price)
                        {
                            sendError(player, "Nemate dovoljno novca! Potrebno Vam je " + PRICE + " " + Const.Emojis.MONEY_BAG + ".");
                            player.playNotifySound(
                                SoundEvents.ANVIL_LAND,
                                SoundSource.MASTER,
                                Const.Misc.NOTIFY_SOUND_VOLUME,
                                1.0f
                            );
                            showMenu(player, Const.ServerMenus.SHOP_MENU);
                            break;
                        }
                        elecraft.utils.giveItem(player, item.MinecraftItem, item.amount);
                        playerData.get(getToken(player)).coins -= PRICE;
                        SidebarManager.updateScoreboard(player);
                        player.playNotifySound(
                            SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.MASTER,
                            Const.Misc.NOTIFY_SOUND_VOLUME, 1f
                        );

                        sendInfo(player, "Uspješno ste kupili " + item.amount + " " + Text.Format(Text.Style.BOLD) + item.name + Text.Format(Text.Col.WHITE) + " za " + PRICE + " " + Const.Emojis.MONEY_BAG + '.');
                        showMenu(player, Const.ServerMenus.SHOP_MENU);
                        break;
                    }
                    else if(ItemName.contains(Const.ServerMenus.EMPTY_SLOT_STR))
                    {
                        menu.setCarried(ItemStack.EMPTY);
                        showMenu(player, Const.ServerMenus.SHOP_MENU);
                        break;
                    }
                    else if(ItemName.contains(Const.ServerMenus.Buttons.NEXT_PAGE))
                    {
                        menu.setCarried(ItemStack.EMPTY);
                        tempPlayerData.get(pn).ShopPage++;
                        if(tempPlayerData.get(pn).ShopPage >= ShopSystem.getShopPagesCount())
                        {
                            tempPlayerData.get(pn).ShopPage = 0;
                        }
                        showMenu(player, Const.ServerMenus.SHOP_MENU);
                        player.playNotifySound(
                            SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.MASTER,
                            Const.Misc.NOTIFY_SOUND_VOLUME, 1f
                        );
                        break;
                    }
                    else if(ItemName.contains(Const.ServerMenus.Buttons.PREVIOUS_PAGE))
                    {
                        menu.setCarried(ItemStack.EMPTY);
                        tempPlayerData.get(pn).ShopPage--;
                        if(tempPlayerData.get(pn).ShopPage < 0)
                        {
                            tempPlayerData.get(pn).ShopPage = ShopSystem.getShopPagesCount() - 1;
                        }
                        showMenu(player, Const.ServerMenus.SHOP_MENU);
                        player.playNotifySound(
                            SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.MASTER,
                            Const.Misc.NOTIFY_SOUND_VOLUME, 1f
                        );
                        break;
                    }
                }
            }
            //tp
            if(openedMenu.get(player) == Const.ServerMenus.TELEPORT_MENU) if(!MouseCursor.isEmpty() && MouseCursor.has(DataComponents.CUSTOM_NAME))
            {
                String TeleportLocationName = MouseCursor.getHoverName().getString();
                Integer TeleportLocationCount = TeleportSystem.getTpCount();
                for(int i = 0; i < TeleportLocationCount; ++i)
                {
                    TeleportLocation location = TeleportSystem.Teleports.get(i);
                    if(TeleportLocationName.contains(location.name))
                    {
                        menu.setCarried(ItemStack.EMPTY);
                        boolean r = location.can.test(player);
                        player.closeContainer();
                        if(r)
                        {
                            player.playNotifySound(
                                SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.MASTER,
                                Const.Misc.NOTIFY_SOUND_VOLUME, 1f
                            );
                            location.tp.accept(player);
                        }
                        break;
                    }
                    else if(TeleportLocationName.contains(Const.ServerMenus.EMPTY_SLOT_STR))
                    {
                        menu.setCarried(ItemStack.EMPTY);
                        showMenu(player, Const.ServerMenus.TELEPORT_MENU);
                        break;
                    }
                }
            }
            //mainmenu
            if(openedMenu.get(player) == Const.ServerMenus.MAIN_MENU) if(!MouseCursor.isEmpty() && MouseCursor.has(DataComponents.CUSTOM_NAME))
            {
                String MainMenuOptionName = MouseCursor.getHoverName().getString();
                Integer MainMenuOptionCount = MainMenuSystem.getOptionCount();
                for(int i = 0; i < MainMenuOptionCount; ++i)
                {
                    MenuOption option = MainMenuSystem.Options.get(i);
                    if(MainMenuOptionName.contains(option.name))
                    {
                        player.playNotifySound(
                            SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.MASTER,
                            Const.Misc.NOTIFY_SOUND_VOLUME, 1f
                        );
                        menu.setCarried(ItemStack.EMPTY);
                        option.select.accept(player);
                        break;
                    }
                    else if(MainMenuOptionName.contains(Const.ServerMenus.EMPTY_SLOT_STR))
                    {
                        menu.setCarried(ItemStack.EMPTY);
                        showMenu(player, Const.ServerMenus.MAIN_MENU);
                        break;
                    }
                }
            }
        
        }
        return;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerAttackMob(AttackEntityEvent ev)
    {
        Entity e = ev.getEntity();
        if(e instanceof ServerPlayer player)
        {
            String playerName = player.getName().getString();
            if(isLoggedIn(player) == 0) return;
            boolean d = LobbySafeEvent(player, "Ne možete ubijati živi svijet u LOBBY-u.");
            boolean k = HouseSafeEvent(player, "Ne možete ubijati živi svijet u blizini tuđe kuće.");
            if(k || d)
            {
                ev.setCanceled(true);
                return;
            }

            if(!tempPlayerData.get(playerName).streak_cd)
            {
                player.playNotifySound(
                    SoundEvents.NOTE_BLOCK_BELL.value(),
                    SoundSource.MASTER,
                    Const.Misc.NOTIFY_SOUND_VOLUME,
                    1.5f
                );
                tempPlayerData.get(playerName).streak++;
                SendHotbarMessage(
                    player, Text.Format(Text.Style.BOLD.Code, Text.Col.RED.Code) + "HIT STREAK " + Text.Format(Text.Col.GOLD.Code) + "x" + tempPlayerData.get(playerName).streak.toString()
                );
            }
            else
            {
                if(!tempPlayerData.get(playerName).streak_cd_sound)
                {
                    tempPlayerData.get(playerName).streak_cd_sound = true;

                    player.playNotifySound(
                        SoundEvents.NOTE_BLOCK_DIDGERIDOO.value(),
                        SoundSource.MASTER,
                        Const.Misc.NOTIFY_SOUND_VOLUME,
                        1.5f
                    );
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onContainerOpen(PlayerContainerEvent.Open event)
    {
        if(event.getEntity() instanceof ServerPlayer player)
        {
            if(isFrozen(player))
            {
                player.closeContainer();
                morateBitiUlogovani(player);
            }

            /////////LMAO KIDARA///////////
            boolean k = HouseSafeEvent(player, "Ne možete otvarati stvari u blizini tuđe kuće.");
            boolean d = LobbySafeEvent(player, "Ne možete otvarati stvari u LOBBY-u.");
            if(k || d) if(!(event.getContainer() instanceof MenuTag))
            {
                player.closeContainer();
            }
        }
        return;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onContainerClose(PlayerContainerEvent.Close event)
    {
        if(event.getEntity() instanceof ServerPlayer p)
        {
        }
        return;
    }

    private boolean HouseSafeEvent(ServerPlayer p, String text)
    {
        Integer house = Utils.isPlayerInHouseBase(p);
        if(house != INVALID_HOUSE) if(playerData.get(getToken(p)).house != house)
        {
            sendac(p, text);
            return true;
        }
        return false;
    }
    private boolean LobbySafeEvent(ServerPlayer p, String text)
    {
        if(GetGamemode(p) != Const.ServerGamemode.CREATIVE && GetDimension(p) == Const.ServerDimensions.LOBBY)
        {
            sendError(p, text);
            return true;
        }
        return false;
    }

    @Mixin(Entity.class)
    public abstract class EntityPushMixin
    {
        @Inject(method = "push(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
        private void onEntityPush(Entity targetEntity, CallbackInfo ci)
        {
            Entity currentEntity = (Entity) (Object) this;

            if(currentEntity instanceof ServerPlayer player && !(targetEntity instanceof ServerPlayer))
            {
                if(isLoggedIn(player) == 0) return;
                boolean k = HouseSafeEvent(player, "Ne možete voditi interakciju sa entitetima u ovoj kući.");
                boolean d = LobbySafeEvent(player, "Ne možete voditi interakciju sa entitetima u LOBBY-u.");
                if(k || d)
                {
                    ci.cancel();
                    return;
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerInteractWithEntity(PlayerInteractEvent.EntityInteract ev)
    {
        if(ev.getEntity() instanceof ServerPlayer player)
        {
            if(isLoggedIn(player) == 0) return;
            boolean k = HouseSafeEvent(player, "Ne možete voditi interakciju sa entitetima u ovoj kući.");
            boolean d = LobbySafeEvent(player, "Ne možete voditi interakciju sa entitetima u LOBBY-u.");
            if(k || d)
            {
                ev.setCanceled(true);
            }
        }
        return;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock e)
    {
        var l = e.getLevel();
        var pos = e.getPos();
        var state = l.getBlockState(pos);

        var block = state.getBlock();

        if(e.getEntity() instanceof ServerPlayer p)
        {
            if(isFrozen(p))
            {
                e.setCanceled(true);
                morateBitiUlogovani(p);
            }

            boolean Restricted = Const.SystemInfo.RESTRICTED_BLOCKS.stream().anyMatch(c -> c.isInstance(block));
            if(Restricted)
            {
                boolean d = LobbySafeEvent(p, "Ne možete koristiti ovaj blok u LOBBY-u.");
                boolean k = HouseSafeEvent(p, "Ne možete koristiti ovaj blok u ovoj kući.");
                
                if(k || d)
                {
                    e.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRightClick(PlayerInteractEvent.RightClickItem event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if (isFrozen(player))
            {
                event.setCanceled(true);
                morateBitiUlogovani(player);
            }
        }
    }
/*
    @SubscribeEvent
    public void onAchievement(AdvancementEvent.Grant event)
    {
        Entity e = event.getEntity();
        if(e instanceof ServerPlayer player)
        {
            playerData.get(getToken(player)).coins += 100;
            sendInfo(player, "Dobili ste 100 novcica zbog napretka.");
            SidebarManager.updateScoreboard(player);
        }
    }
*/
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockEvent.BreakEvent event)
    {
        if (event.getPlayer() instanceof ServerPlayer player)
        {
            if (isFrozen(player))
            {
                event.setCanceled(true);
                morateBitiUlogovani(player);
            }
            //
            boolean k = HouseSafeEvent(player, "Ne možete uništavati blokove u blizini tuđe kuće.");
            boolean d = LobbySafeEvent(player, "Ne možete uništavati blokove u LOBBY-u.");
            if(k || d) event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockEvent.FarmlandTrampleEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if (isFrozen(player))
            {
                event.setCanceled(true);
                morateBitiUlogovani(player);
            }
            //
            boolean k = HouseSafeEvent(player, "Ne možete uništavati blokove u blizini tuđe kuće.");
            boolean d = LobbySafeEvent(player, "Ne možete uništavati blokove u LOBBY-u.");
            if(k || d) event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onBlockBreak2345(EntityTeleportEvent.EnderPearl event)
    {
        if (event.getPlayer() instanceof ServerPlayer player)
        {
            if (isFrozen(player))
            {
                event.setCanceled(true);
                morateBitiUlogovani(player);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onBlockBreak345(EntityTeleportEvent.ChorusFruit event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if (isFrozen(player))
            {
                event.setCanceled(true);
                morateBitiUlogovani(player);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onBlockBreak345(EntityTeleportEvent.EnderEntity event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if (isFrozen(player))
            {
                event.setCanceled(true);
                morateBitiUlogovani(player);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onBlockBreak243(EntityTeleportEvent.TeleportCommand event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if (isFrozen(player))
            {
                event.setCanceled(true);
                morateBitiUlogovani(player);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockEvent.EntityPlaceEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if (isFrozen(player))
            {
                event.setCanceled(true);
                player.containerMenu.broadcastChanges();
                morateBitiUlogovani(player);
            }
            //
            boolean k = HouseSafeEvent(player, "Ne možete postavljati blokove u blizini tuđe kuće.");
            boolean d = LobbySafeEvent(player, "Ne možete postavljati blokove u LOBBY-u.");
            if(k || d) event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if (isFrozen(player))
            {
                event.setCanceled(true);
                player.containerMenu.broadcastChanges();
                morateBitiUlogovani(player);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDrop(ItemTossEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            if(isFrozen(player))
            {
                event.setCanceled(true);
                player.getInventory().add(event.getEntity().getItem());
                player.containerMenu.broadcastChanges();
                morateBitiUlogovani(player);
            }
            if(GetDimension(player) == Const.ServerDimensions.LOBBY)
            {
                sendError(player, "Ne možete bacati stvari u LOBBY-u.");
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onEntityTakeDamage(LivingIncomingDamageEvent e)
    {
        if(e.getEntity() instanceof ServerPlayer p)
        {
            if(GetDimension(p) == Const.ServerDimensions.LOBBY)
            {
                e.setCanceled(true);
            }
            if(isFrozen(p))
            {
                e.setCanceled(true);
                var k = e.getSource().getEntity();
                if(k instanceof ServerPlayer attacker)
                {
                    sendAdminInfo("Igrač " + attacker.getName().getString() + " pokušava napasti igrača " + p.getName().getString() + "!");
                }
            }
        }
    }

    public class ResourcePackManager
    {
        private static String currentHash = "";
        private static final Path CACHE_PATH = Path.of(Const.SystemInfo.DATABASE_LOCATION + "/elecraft.zip");

        public static void downloadAndComputeHash()
        {
            elecraft.utils.write("Pokrenuto skidanje resource packa -> " + Const.SystemInfo.RESOURCES_LINK);
            try
            {
                Files.createDirectories(CACHE_PATH.getParent());
                if(Files.exists(CACHE_PATH))
                {
                    Files.delete(CACHE_PATH);
                }

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(Const.SystemInfo.RESOURCES_LINK)).GET().build();
                HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

                if (response.statusCode() == 200)
                {
                    try(InputStream in = response.body())
                    {
                        Files.copy(in, CACHE_PATH, StandardCopyOption.REPLACE_EXISTING);
                    }

                    MessageDigest digest = MessageDigest.getInstance("SHA-1");
                    byte[] fileBytes = Files.readAllBytes(CACHE_PATH);
                    byte[] hashBytes = digest.digest(fileBytes);

                    StringBuilder hexString = new StringBuilder();
                    for(byte b : hashBytes)
                    {
                        hexString.append(String.format("%02x", b));
                    }
                    
                    currentHash = hexString.toString();
                    elecraft.utils.write("USPJESNO skidanje resource packa -> " + Const.SystemInfo.RESOURCES_LINK);
                }
            }
            catch (Exception e)
            {
                elecraft.utils.write("NEUSPJESNO skidanje resource packa -> " + Const.SystemInfo.RESOURCES_LINK);
                e.printStackTrace();
            }

            elecraft.utils.write("Pokusaj downloada zavrsen.");
            return;
        }

        public static String getCurrentHash()
        {
            return currentHash;
        }
    }

    public static ItemStack CreateCustomHead(String texture)
    {
        ItemStack head = new ItemStack(Items.PLAYER_HEAD);
        var f = UUID.randomUUID();
        GameProfile p = new GameProfile(f, "");
        p.getProperties().put("textures",
            new Property(
                "textures",
                texture
            )
        );
        head.set(
            DataComponents.PROFILE,
            new ResolvableProfile(p)
        );
        return head;
    }
}
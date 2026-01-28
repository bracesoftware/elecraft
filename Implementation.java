/*

        EleCraft TerraFirmaCraft SMP
        Brace Software Co.

        Server skripta

        by DEntisT_
        Helper: Wolfie

*/

package net.bracesoftware.elecraft;
/////////////////////////////////
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/////////////////////////////////////////////////////////////////////
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/////////////////////////////////////////////////////////////////////
import com.google.gson.reflect.TypeToken;
/////////////////////////////////////////////////////////////////////
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
/////////////////////////////////////////////////////////////////////
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
//////////////////////////////////////////////////
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
/////////////////////////////////////////////////////////////////////
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.CommandEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
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
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
////////////////////////////////////////
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
////////////////////////////////
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.component.DataComponents;

import java.util.List;
/////////////////////////////
import net.minecraft.server.network.Filterable;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.*;

import net.neoforged.neoforge.event.entity.player.PlayerEvent.*;

////////////////////////
public class Implementation
{
    public Implementation()
    {
        NeoForge.EVENT_BUS.register(this);
        CustomEvents.betterOnServerStarted = false;
    }
    ////////////////////////////////////////////////////
    public static boolean serverSettings$particles = false;
    
    public static boolean serverBlocked = false;
    public static Integer serverShutdown = -1;
    
    public class emojis
    {
        public static final String skull = "💀";
        public static final String human = "👤";
        public static final String moneyBag = "💰";
        public static final String signal = "📶";
        public static final String calendar = "📅";
    }

    public class postavkeOpcije
    {
        public static final String serverTips = "Server Tips";
        public static final String dynamicScoreboard = "Dinamicki Ping";
        public static final String bossbar = "Bossbar";
        public static final String sideboard = "Sideboard";
    }

    public class shopOpcije
    {
        public static final Integer numberOfOptions = 3;

        public static final String string_ = "String";
        public static final String clayBall = "Clay Ball";
        public static final String coal = "Coal";
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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return internalToken.equals(((playerToken) o).internalToken);
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

    public class serverMenu
    {
        public static final Integer settings = 0;
        public static final Integer shop = 1;
    }
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
        public Boolean sidebar = false;
        public Integer houseLoading = 0;
        public Boolean ulogovan = false;

        public TemporaryPlayerData()
        {
            this.sidebar = false;
        }
    }

    private static final Map<playerToken, PlayerData> playerData = new HashMap<>();
    private static final Map<String, TemporaryPlayerData> tempPlayerData = new HashMap<>();
    ////////////////////////////////////////////////////////////
    //private static final Gson gson = new Gson();
    
    private static final com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
        .enableComplexMapKeySerialization() // KLJUČNA STVAR: Omogućava objekte kao ključeve
        .setPrettyPrinting()               // Opciono: da JSON bude čitljiv ljudima
        .create();
        
    private static final Path filePath = Paths.get("elecraft_data/deaths.json");
    private static final Path filePathTokeni = Paths.get("elecraft_data/account_tokens.json");
    private static final Path filePathAcc = Paths.get("elecraft_data/accounts.json");
    private static final Path filePathAccData = Paths.get("elecraft_data/account_data.json");
    private static final Path filePathHouse = Paths.get("elecraft_data/houses.json");

    public class elecraft
    {
        public class utils
        {
            public static void write(String text)
            {
                System.out.println("{EleCraft} -> System: " + text);
                return;
            }
            public static void giveItem(ServerPlayer player, Item item, int kolicina)
            {
                ItemStack stack = new ItemStack(item, kolicina);
                
                // Pokušaj dodavanja u inventar
                boolean uspesno = player.getInventory().add(stack);
                
                // Ako nije uspelo (puni slotovi), baci item na pod ispred igrača
                if (!uspesno && !stack.isEmpty()) {
                    player.drop(stack, false); // false znači da ne baca daleko
                    player.sendSystemMessage(Component.literal("§cInventar pun! Item je bačen na pod."));
                }
                return;
            }
        }
    }
    //////////////KUCE SISTEM////////////////////////
    public static final Integer MAX_HOUSE = 1000;

    public static final Integer INVALID_HOUSE = -1;

    public static final Double HOUSE_RANGE_BLOCK = 25.0;

    public static final String HOUSE_LABEL_TAG = "net.bracesoftware.elecraft.HouseLabelTag.SYS";

    public static class House
    {
        Double x = 0.0;
        Double y = 0.0;
        Double z = 0.0;
        
        transient ArmorStand[] t = null;
        transient ServerLevel level = null;

        public House()
        {
            this.t = new ArmorStand[3];
        }
    }
    public static House[] houses = new House[MAX_HOUSE];
    public static Integer createHouse(ServerPlayer player, Double x, Double y, Double z)
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

        houses[id].level = player.getServer().getLevel(Level.OVERWORLD);
        createHouseLabel(id);

        playerData.get(getToken(player)).house = id;
        return id;
    }
    public static void deleteHouse(ServerPlayer player, Integer id)
    {
        for(Integer i = 0; i < 3; ++i)
        {
            if(houses[id].t[i] != null) houses[id].t[i].discard();
        }
        houses[id] = null;
        playerData.get(getToken(player)).house = INVALID_HOUSE;
        return;
    }
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
        createHouseLabel$deprecated(id);
        return;
    }
    public static void createHouseLabel$new(Integer id)
    {
        if (houses[id].level == null) return;

        AABB area = new AABB(
            houses[id].x - 1.0, houses[id].y - 2.0, houses[id].z - 1.0,
            houses[id].x + 1.0, houses[id].y + 1.0, houses[id].z + 1.0
        );

        List<ArmorStand> pronadjeni = houses[id].level.getEntitiesOfClass(ArmorStand.class, area,
            e -> e.getTags().contains(HOUSE_LABEL_TAG));

        pronadjeni.sort((a, b) -> Double.compare(b.getY(), a.getY()));

        String[] text = new String[3];
        text[0] = "§2§lMjesto prebivalista";
        text[1] = "§r§6Adresa: §o" + id.toString();
        text[2] = "§oDa sklonite zastitu [§l/delhome§r]";

        Double offset = 0.0;
        for(int i = 0; i < 3; ++i)
        {
            if (i < pronadjeni.size()) {
                houses[id].t[i] = pronadjeni.get(i);
            } else {
                houses[id].t[i] = EntityType.ARMOR_STAND.create(houses[id].level);
                if (houses[id].t[i] != null) {
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

                // Osiguraj da entitet ima tag (bitno za naknadna učitavanja)
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
                    entity.discard(); // Trenutno uklanja entitet bez dropova ili animacija smrti
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
    public class bookTypes
    {
        public static final Integer helpKomande = 1;
        public static final Integer adminKomande = 2;
    }
    public static final String headerStyle = "§c§l";
    public static final String bodyStyle = "§o§5";
    public void otvoriVodic(ServerPlayer player, Integer book)
    {
        ResourceLocation bookFont = ResourceLocation.withDefaultNamespace("uniform");
        
        if(book == bookTypes.helpKomande)
        {
            ItemStack knjiga = new ItemStack(Items.WRITTEN_BOOK);

            // Lista stranica (svaka stranica je jedan Component)
            List<Filterable<Component>> stranice = List.of(
                Filterable.passThrough(Component.literal(headerStyle + "Dobrodosli na EleCraft TFC SMP!\n\n" + bodyStyle + "Ovo je EleCraft TFC.\n\nU ovoj knjizi ćeš naći osnovna uputstva za preživljavanje.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "Lista komandi:\n\n" + bodyStyle + "/login\n/changepassword\n/pomoc\n/adminhelp\n/smrti\n/postavke\n/shop").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/login\n\n" + bodyStyle + "Ova komanda sluzi za prijavu na Vas racun.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/changepassword\n\n" + bodyStyle + "Ova komanda omogucava da postavite novu lozinku za Vas racun.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/pomoc\n\n" + bodyStyle + "Ova komanda otvara ovaj vodic.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/adminhelp\n\n" + bodyStyle + "Ova komanda ispisuje komande dostupne iskljucivo operatorima!").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/smrti\n\n" + bodyStyle + "Ova komanda prikazuje koliko puta ste umrli na serveru.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/postavke\n\n" + bodyStyle + "Ova komanda otvara meni za podesavanja Vaseg racuna.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/shop\n\n" + bodyStyle + "Ova komanda otvara market.").withStyle(style -> style.withFont(bookFont)))
            );

            // Postavljanje podataka knjige (1.21.1 sistem)
            knjiga.set(DataComponents.WRITTEN_BOOK_CONTENT, new WrittenBookContent(
                Filterable.passThrough("Pomoc oko komandi"), 
                player.getName().getString(), 
                0, 
                stranice, 
                true
            ));

            int slot = player.getInventory().selected;
            ItemStack stariItem = player.getInventory().getItem(slot);

            player.getInventory().setItem(slot, knjiga);

            player.connection.send(new net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket(
                0, // 0 je ID za igračev inventar
                player.containerMenu.getStateId(),
                slot + 36, // Offset za hotbar
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
        if(book == bookTypes.adminKomande)
        {
            ItemStack knjiga = new ItemStack(Items.WRITTEN_BOOK);

            // Lista stranica (svaka stranica je jedan Component)
            List<Filterable<Component>> stranice = List.of(
                Filterable.passThrough(Component.literal(headerStyle + "Dobrodosli na EleCraft TFC SMP!\n\n" + bodyStyle + "Ovo je EleCraft TFC.\n\nU ovoj knjizi ćeš naći osnovna uputstva za preživljavanje.").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "Lista admin komandi:\n\n" + bodyStyle + "/dajnovac\n/goto\n/gethere\n/ugasiserver").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/dajnovac\n\n").append(bodyStyle + "Ova komanda sluzi za dodavanje novca igracu!").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/goto\n\n").append(bodyStyle + "Ovom komandom se mozete teleportovati do drugog igraca!").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/gethere\n\n").append(bodyStyle + "Ovom komandom mozete teleportovati igraca do sebe!").withStyle(style -> style.withFont(bookFont))),
                Filterable.passThrough(Component.literal(headerStyle + "/ugasiserver\n\n").append(bodyStyle + "Ovom komandom mozete ugasiti server! Standardna `/stop` komanda je zamijenjena.").withStyle(style -> style.withFont(bookFont)))
            );

            // Postavljanje podataka knjige (1.21.1 sistem)
            knjiga.set(DataComponents.WRITTEN_BOOK_CONTENT, new WrittenBookContent(
                Filterable.passThrough("Pomoc oko admin komandi"), 
                player.getName().getString(), 
                0, 
                stranice, 
                true
            ));

            int slot = player.getInventory().selected;
            ItemStack stariItem = player.getInventory().getItem(slot);

            player.getInventory().setItem(slot, knjiga);

            player.connection.send(new net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket(
                0, // 0 je ID za igračev inventar
                player.containerMenu.getStateId(),
                slot + 36, // Offset za hotbar
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
        "§6(TIP) §fPozovite prijatelje!",
        "§6(TIP) §fPrvi izbor za izgradnju krova kuce je §e`Thatch Block` §f.",
        "§6(TIP) §fPostujte pravila servera.",
        "§6(TIP) §fBudite aktivni na serveru da zaradite " + emojis.moneyBag + " Coins!",
        "§6(TIP) §fKamenje skupljate desnim klikom na zemlju.",
        "§6(TIP) §fVodu možete piti direktno iz voda stajacica, ali je pametnije prokuhati je!",
        "§6(TIP) §fIskucajte §e/postavke §fda manipulisete podesavanjima.",
        "§6(TIP) §fGlinu ćete naći tamo gde raste §e`Goldenrod` §fcveće."
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
            elecraft.utils.write("Igracu `" + player.getName().getString() + "` pokrenuto osvjezavanje sidebara.");
            String objName = scoreboardName;
            Component title = Component.literal("§6§lTerraFirmaCraft SMP");

            player.connection.send(new ClientboundSetObjectivePacket(
                new Objective(null, objName, ObjectiveCriteria.DUMMY, title, ObjectiveCriteria.RenderType.INTEGER, false, null), 
                1 // Mode 1 = REMOVE
            ));

            Objective dummyObj = new Objective(null, objName, ObjectiveCriteria.DUMMY, title, ObjectiveCriteria.RenderType.INTEGER, false, null);
            player.connection.send(new ClientboundSetObjectivePacket(dummyObj, 0)); // Mode 0 = CREATE

            player.connection.send(new ClientboundSetDisplayObjectivePacket(DisplaySlot.SIDEBAR, dummyObj));

            final String _line_ = "§7------------------";

            sendLine(player, objName, _line_, 8);
            sendLine(player, objName, emojis.human + " Ime: §a" + player.getScoreboardName(), 7);
            
            int deathCount = smrti.getOrDefault(getToken(player), 0);
            sendLine(player, objName, "§f" + emojis.skull + " Smrti: §e" + deathCount, 6);
            
            int coins = 0;
            int logs = 0;
            if(playerData.containsKey(getToken(player)))
            {
                coins = playerData.get(getToken(player)).coins;
                logs = playerData.get(getToken(player)).logins;
            }
            
            sendLine(player, objName, "§f" + emojis.moneyBag + " Novac: §e" + coins, 5);
            sendLine(player, objName, "§fLog count: §ex" + logs, 4);
            sendLine(player, objName, " ", 3);
            sendLine(player, objName, emojis.signal + " Ping: " + player.connection.latency(), 2);
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
            //elecraft.utils.write("Igracu `" + player.getName().getString() + "` pokrenuto osvjezavanje sidebar naslova!");
            String objName = scoreboardName;
            String playerName = player.getName().getString();
            
            // Logika za boju (menja se svaku sekundu)
            long seconds = player.level().getGameTime() / 20;
            List<String> colors = List.of("§6§l", "§e§l", "§a§l", "§b§l", "§d§l", "§c§l");
            String currentStyle = colors.get((int) (seconds % colors.size()));
            
            Component newTitle = Component.literal("");
            if(tempPlayerData.get(playerName).sidebar == false)
            {
                newTitle = Component.literal(currentStyle + "TerraFirmaCraft SMP");
            }
            if(tempPlayerData.get(playerName).sidebar == true)
            {
                newTitle = Component.literal(currentStyle + emojis.calendar + " Dan: " + Utils.getDays(player));
            }

            // Mode 2 = UPDATE (Samo menja naslov, ne dira linije)
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
            
            elecraft.utils.write("Sidebar sakriven za igraca: " + player.getName().getString());

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
    public class BossBarManager
    {
        private static CustomBossEvent serverBar;

        public static void showBossBar(ServerPlayer player)
        {
            ResourceLocation barId = ResourceLocation.fromNamespaceAndPath("elecraft_bossbar", "server_bar");
            
            // Dohvati ili kreiraj BossBar
            serverBar = player.server.getCustomBossEvents().get(barId);
            if(serverBar == null)
            {
                serverBar = player.server.getCustomBossEvents().create(barId, Component.literal("§bDobrodošli na §5§lEleCraft Server"));
                serverBar.setColor(BossEvent.BossBarColor.BLUE);
                serverBar.setOverlay(BossEvent.BossBarOverlay.PROGRESS);
            }

            // Dodaj igrača da vidi bar
            serverBar.addPlayer(player);
            
            // Primer: Postavi progres na 50%
            serverBar.setProgress(1.0f);
        }

        public static void hideBossBar(ServerPlayer player)
        {
            if(serverBar.getPlayers().contains(player))
            {
                serverBar.removePlayer(player);
            }
            return;
        }

        public static void changeColor(BossEvent.BossBarColor color)
        {
            if(serverBar == null)
            {
                return;
            }
            serverBar.setColor(color);
            return;
        }

        public static void changeText(String text)
        {
            if(serverBar == null)
            {
                return;
            }
            serverBar.setName(Component.literal(text));
            return;
        }
    }

    public void updateTabList(ServerPlayer player)
    {
        Component header = Component.literal("\n§5§lEleCraft\n§7Dobrodošli, §f" + player.getScoreboardName() + "\n");
        Component footer = Component.literal("\n§eWeb: §fwww.elecraft.net\n§7Uživajte u preživljavanju!\nTerraFirmaCraft Survival");
        
        player.setTabListHeaderFooter(header, footer);
    }

    public void prikaziInterfejs(ServerPlayer player)
    {
        if(playerData.get(getToken(player)).sideboard)
        {
            SidebarManager.updateScoreboard(player);
        }
        if(playerData.get(getToken(player)).bossbar)
        {
            BossBarManager.showBossBar(player);
        }
        updateTabList(player);
    }

    public int bossbarColor = 0;
    public void jednaSekunda()
    {
        if(bossbarColor == 0)
        {
            BossBarManager.changeColor(BossEvent.BossBarColor.GREEN);
        }
        if(bossbarColor == 1)
        {
            BossBarManager.changeColor(BossEvent.BossBarColor.RED);
        }
        if(bossbarColor == 2)
        {
            BossBarManager.changeColor(BossEvent.BossBarColor.BLUE);
        }
        if(bossbarColor == 3)
        {
            BossBarManager.changeColor(BossEvent.BossBarColor.WHITE);
        }
        if(bossbarColor == 4)
        {
            BossBarManager.changeColor(BossEvent.BossBarColor.PURPLE);
        }
        if(bossbarColor == 5)
        {
            BossBarManager.changeColor(BossEvent.BossBarColor.YELLOW);
        }
        if(bossbarColor == 6)
        {
            BossBarManager.changeColor(BossEvent.BossBarColor.PINK);
        }

        bossbarColor++;
        if(bossbarColor == 7)
        {
            bossbarColor = 0;
        }
        return;
    }
    public int bossbarName = 0;
    public void triSekunde()
    {
        if(bossbarName == 0)
        {
            BossBarManager.changeText("§bDobrodošli na §5§lEleCraft Server");
        }
        if(bossbarName == 1)
        {
            BossBarManager.changeText("§eTerraFirmaCraft Prezivljavanje");
        }
        bossbarName++;
        if(bossbarName == 2)
        {
            bossbarName = 0;
        }
        return;
    }

    //private int globalniSekund = 0;

    public int serverTick = 0;
    public int serverTick2 = 0;
    @SubscribeEvent
    public void onServerTick(net.neoforged.neoforge.event.tick.ServerTickEvent.Post event)
    {
        // Ovde nema 'player', ovo je nivo celog servera
        // Koristimo statičku varijablu ili polje u klasi da brojimo
        serverTick++;
        serverTick2++;

        if (serverTick >= 20)
        {
            // Izvršava se svake sekunde
            //globalniSekund++;
            serverTick = 0;
            // uradi nešto...
            jednaSekunda();
        }

        if(serverTick2 >= 60)
        {
            serverTick2 = 0;
            triSekunde();
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
    ////////////////////////////////////


    // ASCII welcome
    private static final String ASCII_WELCOME =
            "\n$$$$$$$$\\ $$\\            $$$$$$\\                      $$$$$$\\    $$\\     \n" +
            "$$  _____|$$ |          $$  __$$\\                    $$  __$$\\   $$ |    \n" +
            "$$ |      $$ | $$$$$$\\  $$ /  \\__| $$$$$$\\  $$$$$$\\  $$ /  \\__|$$$$$$\\   \n" +
            "$$$$$\\    $$ |$$  __$$\\ $$ |      $$  __$$\\ \\____$$\\ $$$$\\     \\_$$  _|  \n" +
            "$$  __|   $$ |$$$$$$$$ |$$ |      $$ |  \\__|$$$$$$$ |$$  _|      $$ |    \n" +
            "$$ |      $$ |$$   ____|$$ |  $$\\ $$ |     $$  __$$ |$$ |        $$ |$$\\ \n" +
            "$$$$$$$$\\ $$ |\\$$$$$$$\\ \\$$$$$$  |$$ |     \\$$$$$$$ |$$ |        \\$$$$  |\n" +
            "\\________|\\__| \\_______| \\______/ \\__|      \\_______|\\__|         \\____/ \n" +
            "                                                                         \n" +
            "                               VERZIJA: 1.0.0\n" +
            "                         Uspjesno ucitano!!! By DEntisT_\n";

    public Integer isLoggedIn(ServerPlayer player)
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
        System.out.println(ASCII_WELCOME);
        Utils.load();

        // Obrada podataka
        ucitajTokene();
        ucitajSmrti();
        ucitajLozinke();
        ucitajPodatke();
        ucitajKuce();
        return;
    }

    public static void unloadImplementation()
    {
        elecraft.utils.write("Server se gasi - spremanje podataka u toku...");
            
        sacuvajTokene();
        sacuvajSmrti();
        sacuvajLozinke();
        sacuvajPodatke();
        sacuvajKuce();
        return;
    }

    // Helper metoda
    private void sendPlayerMessage(ServerPlayer player, String text)
    {
        player.sendSystemMessage(
                Component.literal("§6§l[EleCraft]: §e§o" + text)
        );
    }
    private void sendError(ServerPlayer player, String text) {
        player.sendSystemMessage(
                Component.literal("§c§lError: §7§o" + text)
        );
    }
    private void sendInfo(ServerPlayer player, String text) {
        player.sendSystemMessage(
                Component.literal("§a§lInfo: §f§o" + text)
        );
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
        //deleteEntityWithTag(player.getServer().getLevel(Level.OVERWORLD), HOUSE_LABEL_TAG);
        /*for(Integer i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] != null)
            {
                houses[i].level = player.getServer().getLevel(Level.OVERWORLD);
                createHouseLabel(i);
            }
        }*/
        return;
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // change game rules
        event.getServer().getGameRules().getRule(GameRules.RULE_SENDCOMMANDFEEDBACK).set(false, event.getServer());
        event.getServer().getGameRules().getRule(GameRules.RULE_SHOWDEATHMESSAGES).set(false, event.getServer());

        if(Utils.False()) // dead code just for idk, exploring available functions
        {
            net.minecraft.core.BlockPos pos = event.getServer().getLevel(Level.OVERWORLD).getSharedSpawnPos();
            pos.getX();
            pos.getY();
            pos.getZ();
        }

        //event.getServer().getGameRules().getRule(GameRules.RULE_).set(false, event.getServer());
        /*for(Integer i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] != null)
            {
                houses[i].level = event.getServer().getLevel(Level.OVERWORLD);
                createHouseLabel(i);
            }
        } */
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
            if(playerData.get(getToken(player)).house != INVALID_HOUSE)
            {
                elecraft.utils.write("Brisem labele igraca...");
                deleteHouseLabel(playerData.get(getToken(player)).house);
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

            ///token generatori
            if(!tokens.containsKey(player.getName().getString()))
            {
                //one time token gen
                tokens.put(player.getName().getString(), new playerToken(Utils.generateToken(player.getName().getString())));
            }
            ///ostale stvari
            sendPlayerMessage(player, "Dobrodosao na server!");
            sendPlayerMessage(player, "§7Ukucaj §e`/pomoc` §7za komande.");
            sendPlayerMessage(player, "§7Da bi ste nastavili sa igrom ili napravili racun, kucajte §e`/login <lozinka>` §7.");

            Component customMessage = Component.literal("§6[EleCraft]: §7Igrac §a" + player.getScoreboardName() + " §7je usao na server!");
            
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

            tempPlayerData.get(playerName).ulogovan = false;
            tempPlayerData.get(playerName).houseLoading = INVALID_HOUSE;

            if(playerData.get(getToken(player)).house != INVALID_HOUSE)
            {
                houses[playerData.get(getToken(player)).house].level = player.getServer().getLevel(Level.OVERWORLD);
            }

            if(playerData.get(getToken(player)).sideboard == false)
            {
                SidebarManager.hideRaw(player);
            }
            return;
        }
    }
    
    @SubscribeEvent
    public void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent event)
    {
        if (event.getEntity() instanceof ServerPlayer player)
        {
            Component customMessage = Component.literal("§6[EleCraft]: §7Igrac §a" + player.getScoreboardName() + " §7je izasao sa servera!");
        
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
            Component customMessage = Component.literal("§6[SMRT]: §7Igrac §a" + player.getScoreboardName() + " §7je umro! Respawnovan je!");
        
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
            Commands.literal("pomoc")
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
                    Commands.argument("igrac", EntityArgument.player()) // Prvi argument: igrač
                        .then(
                            Commands.argument("kolicina", IntegerArgumentType.integer(1)) // Drugi argument: broj
                                .executes(this::dajCoinsKomanda) // Poziva funkciju na kraju
                        )
                )
        );

        dispatcher.register(
            Commands.literal("goto")
                //.requires(source -> source.hasPermission(2)) // Samo za OP (Admina)
                .then(
                    Commands.argument("igrac", EntityArgument.player()) // Prvi argument: igrač
                        .executes(this::gotoKomanda) // Poziva funkciju na kraju
                )
        );

        dispatcher.register(
            Commands.literal("gethere")
                //.requires(source -> source.hasPermission(2)) // Samo za OP (Admina)
                .then(
                    Commands.argument("igrac", EntityArgument.player()) // Prvi argument: igrač
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
            Commands.literal("delhome")
                .executes(this::delHomeKomanda)
        );

        dispatcher.register(
            Commands.literal("partiklez")
                .executes(this::PARTIKLEZ_CMD)
        );

        dispatcher.register(
            Commands.literal("ugasiserver")
                .executes(this::gasiServerCmd)
        );
        
        return;
    }

    @SubscribeEvent
    public void onCommandExecute(CommandEvent event)
    {
        // Dobijamo puni tekst komande (npr. "gamemode creative")
        String commandText = event.getParseResults().getReader().getString();

        // Provjeravamo da li komanda počinje sa onim što želimo blokirati
        // Napomena: Komanda ovdje ne sadrži početni "/"
        if(commandText.equals("stop"))
        {
            // Otkazujemo izvršavanje
            event.setCanceled(true);
            
            // Šaljemo povratnu informaciju (opcionalno)
            event.getParseResults().getContext().getSource()
                .sendFailure(Component.literal("§c[Greska]: §eOva komanda je iskljucena na ovom serveru!"));
        }
        return;
    }

    private int gasiServerCmd(CommandContext<CommandSourceStack> ctx)
    {
        CommandSourceStack source = ctx.getSource();
        MinecraftServer server = source.getServer();
        if(source.getPlayer() != null)
        {
            ServerPlayer player = source.getPlayer();
            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            if(!player.hasPermissions(2))
            {
                sendError(player, "Niste ovlasteni za upotrebu ove komande!");
                return 1;
            }

            Implementation.serverShutdown = 0;
            Implementation.serverBlocked = true;

            server.getPlayerList().getPlayers().forEach(p -> 
                p.connection.disconnect(Component.literal("Server se gasi ili je u toku održavanje."))
            );
            return 1;
        }
        if(source.getPlayer() == null)
        {
            Implementation.serverShutdown = 0;
            Implementation.serverBlocked = true;

            server.getPlayerList().getPlayers().forEach(player -> 
                player.connection.disconnect(Component.literal("Server se gasi ili je u toku održavanje."))
            );
        }
        return 1;
    }

    private int PARTIKLEZ_CMD(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            if(!player.hasPermissions(2))
            {
                sendError(player, "Niste ovlasteni za upotrebu ove komande!");
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
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
            );
        }
        return 1;
    }

    public int shopKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            showMenu(player, serverMenu.shop);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
            );
        }
        return 1;
    }

    public int setHomeKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            if(playerData.get(getToken(player)).house != INVALID_HOUSE)
            {
                sendError(player, "Vi vec imate kucu, prvo staru obrisite iz sistema.");
                return 1;
            }

            Integer house = 0;
            house = createHouse(player, player.getX(), player.getY(), player.getZ());
            playerData.get(getToken(player)).house = house;

            sendInfo(player, "Uspjesno ste postavili zastitu za kucu.");
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
            );
        }
        return 1;
    }

    public int delHomeKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            if(playerData.get(getToken(player)).house == INVALID_HOUSE)
            {
                sendError(player, "Vi nemate postavljenu kucu!");
                return 1;
            }

            Integer id = playerData.get(getToken(player)).house;
            if(!Utils.isPlayerInRange(player, 2.0, houses[id].x, houses[id].y, houses[id].z))
            {
                sendError(player, "Morate stajati u blizini teksta za kucu!");
                return 1;
            }

            deleteHouse(player, id);
            sendInfo(player, "Uspjesno ste uklonili zastitu za kucu.");
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
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

            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            if(!ctx.getSource().hasPermission(2)) //op admin privilegia
            {
                sendError(player, "Nemate dozvolu za upotrebu ove komande!");
                return 1;
            }

            /*sendPlayerMessage(player, "Lista admin komandi:\n" +
                "\t/dajnovac" 
            );*/
            otvoriVodic(player, bookTypes.adminKomande);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
            );
        }
        return 1;
    }
    private int dajCoinsKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            if(!ctx.getSource().hasPermission(2)) //op admin privilegia
            {
                sendError(player, "Nemate dozvolu za upotrebu ove komande!");
                return 1;
            }

            ServerPlayer target = EntityArgument.getPlayer(ctx, "igrac");
            Integer iznos = IntegerArgumentType.getInteger(ctx, "kolicina");

            playerData.get(getToken(target)).coins += iznos;
            sendInfo(player, "Dali ste igracu " + target.getName().getString() + " " + iznos.toString() + " novcica.");
            sendInfo(target, "Dobili ste " + iznos.toString() + " novcica od administracije!");

            prikaziInterfejs(target);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
            );
        }
        return 1;
    }
    private int gotoKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            String playerName = player.getName().getString();

            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            if(!ctx.getSource().hasPermission(2)) //op admin privilegia
            {
                sendError(player, "Nemate dozvolu za upotrebu ove komande!");
                return 1;
            }

            ServerPlayer target = EntityArgument.getPlayer(ctx, "igrac");
            String targetName = target.getName().getString();

            sendInfo(target, "Admin " + playerName  + " se teleportovao do Vas.");
            sendInfo(player, "Teleportovali ste se do igraca " + targetName + ".");

            Double x = target.getX();
            Double y = target.getY();
            Double z = target.getZ();

            player.teleportTo(x,y,z);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
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

            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            if(!ctx.getSource().hasPermission(2)) //op admin privilegia
            {
                sendError(player, "Nemate dozvolu za upotrebu ove komande!");
                return 1;
            }

            ServerPlayer target = EntityArgument.getPlayer(ctx, "igrac");
            String targetName = target.getName().getString();

            sendInfo(target, "Admin " + playerName  + " Vas teleportovao je teleportovao do sebe.");
            sendInfo(player, "Teleportovali ste igraca " + targetName + " do sebe.");

            Double x = player.getX();
            Double y = player.getY();
            Double z = player.getZ();

            target.teleportTo(x,y,z);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
            );
        }
        return 1;
    }
    
    private int helpCommand(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            otvoriVodic(player, bookTypes.helpKomande);
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
            );
        }
        return 1;
    }
    private int settingsKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            
            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na racun!");
                return 1;
            }
            showMenu(player, serverMenu.settings);
            return 1;
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
            );
        }
        return 1;
    }
    // death cmd
    private int smrtiKomanda(CommandContext<CommandSourceStack> ctx)
    {
        try
        {
            ServerPlayer player = ctx.getSource().getPlayerOrException();

            if(isLoggedIn(player) == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun.");
                return 1;
            }

            int broj = smrti.getOrDefault(player.getName().getString(), 0);
            sendPlayerMessage(player, "Umrli ste " + broj + " puta.");
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
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
                sendPlayerMessage(player, "Uspjesno ste se prijavili na Vas racun!");

                tempPlayerData.get(playerName).ulogovan = true;

                Integer house = playerData.get(getToken(player)).house;
                if(house != -1)
                {
                    tempPlayerData.get(playerName).houseLoading = 0;
                    player.teleportTo(houses[house].x, houses[house].y, houses[house].z);
                    Utils.sendTitle(player, "Ucitavanje...");
                    frozenPlayers.add(playerName);
                    createHouseLabel(house);
                }
                return 1;
            }
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
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
            //String imeigraca = player.getName().getString();

            String __lozinka = StringArgumentType.getString(ctx, "lozinka");

            if(ulogovan_local == 0)
            {
                sendError(player, "Morate biti prijavljeni na Vas racun da biste promjenili lozinku.");
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
                return 1;
            }
        }
        catch(Exception e)
        {
            ctx.getSource().sendFailure(
                    Component.literal("Ovu komandu moze koristiti samo igrac!")
            );
        }
        return 1;
    }

    /////// FREEZE SISTEM ///////

    public void showMenu(ServerPlayer player, Integer menu_)
    {
        openedMenu.put(player, menu_);
        //////////settings
        if(menu_ == serverMenu.settings)
        {
            player.openMenu(new SimpleMenuProvider((id, inventory, p) -> {
                ChestMenu menu = ChestMenu.oneRow(id, inventory);
                
                final Integer numberOfOptions = 4;

                Boolean[] status = new Boolean[numberOfOptions];
                ItemStack[] item = new ItemStack[numberOfOptions];
            
                status[0] = playerData.get(getToken(player)).dynamicScoreboard;
                status[1] = playerData.get(getToken(player)).tips;
                status[2] = playerData.get(getToken(player)).bossbar;
                status[3] = playerData.get(getToken(player)).sideboard;

                item[0] = new ItemStack(status[0] ? Items.LIME_STAINED_GLASS_PANE : Items.RED_STAINED_GLASS_PANE);
                item[0].set(DataComponents.CUSTOM_NAME, Component.literal(status[0] ? "§a" + postavkeOpcije.dynamicScoreboard + ": §lUkljucen" : "§c" + postavkeOpcije.dynamicScoreboard + ": §lIskljucen"));
                item[0].set(DataComponents.LORE, new ItemLore(List.of(
                    Component.literal("§7Azurira ping na scoreboardu"),
                    Component.literal("§7u stvarnom vremenu."),
                    Component.literal(""),
                    Component.literal("§eKlikni da promjenis!")
                )));

                item[1] = new ItemStack(status[1] ? Items.LIME_STAINED_GLASS_PANE : Items.RED_STAINED_GLASS_PANE);
                item[1].set(DataComponents.CUSTOM_NAME, Component.literal(status[1] ? "§a" + postavkeOpcije.serverTips + ": §lUkljucen" : "§c" + postavkeOpcije.serverTips + ": §lIskljucen"));
                item[1].set(DataComponents.LORE, new ItemLore(List.of(
                    Component.literal("§7Salje tzv. server tips"),
                    Component.literal("§7igracu."),
                    Component.literal(""),
                    Component.literal("§eKlikni da promjenis!")
                )));

                item[2] = new ItemStack(status[2] ? Items.LIME_STAINED_GLASS_PANE : Items.RED_STAINED_GLASS_PANE);
                item[2].set(DataComponents.CUSTOM_NAME, Component.literal(status[2] ? "§a" + postavkeOpcije.bossbar + ": §lUkljucen" : "§c" + postavkeOpcije.bossbar + ": §lIskljucen"));
                item[2].set(DataComponents.LORE, new ItemLore(List.of(
                    Component.literal("§7Prikazuje server bossbar na"),
                    Component.literal("§7vrhu Vaseg ekrana."),
                    Component.literal(""),
                    Component.literal("§eKlikni da promjenis!")
                )));

                item[3] = new ItemStack(status[3] ? Items.LIME_STAINED_GLASS_PANE : Items.RED_STAINED_GLASS_PANE);
                item[3].set(DataComponents.CUSTOM_NAME, Component.literal(status[3] ? "§a" + postavkeOpcije.sideboard + ": §lUkljucen" : "§c" + postavkeOpcije.sideboard + ": §lIskljucen"));
                item[3].set(DataComponents.LORE, new ItemLore(List.of(
                    Component.literal("§7Prikazuje server sideboard na"),
                    Component.literal("§7desnoj strani Vaseg ekrana."),
                    Component.literal(""),
                    Component.literal("§eKlikni da promjenis!")
                )));

                for(Integer i = 0; i < numberOfOptions; ++i)
                {
                    menu.getSlot(i).set(item[i]);
                }
                
                return menu;
            }, Component.literal("Postavke")));
        }
        /////////////////shop
        if(menu_ == serverMenu.shop)
        {
            player.openMenu(new SimpleMenuProvider((id, inventory, p) -> {
                ChestMenu menu = ChestMenu.oneRow(id, inventory);
                ItemStack[] item = new ItemStack[shopOpcije.numberOfOptions];
                //item = null;
                //string
                item[0] = new ItemStack(Items.STRING, 1);
                item[0].set(DataComponents.CUSTOM_NAME, Component.literal("§a" + shopOpcije.string_));
                item[0].set(DataComponents.LORE, new ItemLore(List.of(
                    Component.literal("§7Kupovina artikla: §lString"),
                    Component.literal("§7Kolicina: §l1x"),
                    Component.literal(""),
                    Component.literal("§7Cijena:"),
                    Component.literal("§e" + emojis.moneyBag + "10")
                )));
                //clay ball
                item[1] = new ItemStack(Items.CLAY_BALL, 1);
                item[1].set(DataComponents.CUSTOM_NAME, Component.literal("§a" + shopOpcije.clayBall));
                item[1].set(DataComponents.LORE, new ItemLore(List.of(
                    Component.literal("§7Kupovina artikla: §lClay Ball"),
                    Component.literal("§7Kolicina: §l1x"),
                    Component.literal(""),
                    Component.literal("§7Cijena:"),
                    Component.literal("§e" + emojis.moneyBag + "5")
                )));
                //coal
                item[2] = new ItemStack(Items.COAL, 1);
                item[2].set(DataComponents.CUSTOM_NAME, Component.literal("§a" + shopOpcije.coal));
                item[2].set(DataComponents.LORE, new ItemLore(List.of(
                    Component.literal("§7Kupovina artikla: §lCoal"),
                    Component.literal("§7Kolicina: §l5x"),
                    Component.literal(""),
                    Component.literal("§7Cijena:"),
                    Component.literal("§e" + emojis.moneyBag + "20")
                )));

                //itemi
                for(int i = 0; i < shopOpcije.numberOfOptions; ++i)
                {
                    menu.getSlot(i).set(item[i]);
                }
                
                return menu;
            }, Component.literal("Market Servera")));
        }
        return;
    }

    public void morateBitiUlogovani(ServerPlayer player)
    {
        player.displayClientMessage(Component.literal("§c§lMorate se prijaviti na racun!    §r§eKomanda: /login <lozinka>"), true);
        return;
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event)
    {
        // PlayerTickEvent.Post je zamena za stari TickEvent.PlayerTickEvent
        if (event.getEntity() instanceof ServerPlayer player)
        {
            if(player.hasPermissions(2))
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
                player.teleportTo(tempPlayerData.get(playerName).pos[0], tempPlayerData.get(playerName).pos[1], tempPlayerData.get(playerName).pos[2]);
            }
            if(player.tickCount % 60 == 0)
            {
                tempPlayerData.get(playerName).sidebar = !tempPlayerData.get(playerName).sidebar;
            }
            // Unutar tvog onPlayerTick...
            if(playerData.get(getToken(player)).tips)
            if (player.tickCount % 12000 == 0)
            { // Svakih 10 minuta za svakog igrača posebno
                String tip = serverTips.get(new java.util.Random().nextInt(serverTips.size()));
                //player.displayClientMessage(Component.literal(tip), true); // 'true' šalje u Action Bar
                Utils.sendLongHotbarMsg(player, tip);
            }
            if (player.tickCount % 10000 == 0)
            {
                player.displayClientMessage(Component.literal("§e+1 Coin " + emojis.moneyBag), true); // 'true' šalje u Action Bar
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
            if(tempPlayerData.get(playerName).ulogovan == false) if (isFrozen(player))
            {
                player.setDeltaMovement(0, 0, 0);
                player.hurtMarked = true;
                Double x = tempPlayerData.get(playerName).pos[0];
                Double y = tempPlayerData.get(playerName).pos[1];
                Double z = tempPlayerData.get(playerName).pos[2];
                player.teleportTo(x, y, z);
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
                        player.teleportTo(houses[house].x, houses[house].y, houses[house].z);
                    }
                }
            }
            //////SETTINGS///////
            AbstractContainerMenu menu = player.containerMenu;
            ItemStack naMisu = menu.getCarried();

            if(openedMenu.get(player) == serverMenu.settings) if(!naMisu.isEmpty() && naMisu.has(DataComponents.CUSTOM_NAME))
            {
                String imeItema = naMisu.getHoverName().getString();

                if(imeItema.contains(postavkeOpcije.dynamicScoreboard + ":"))
                {
                    menu.setCarried(ItemStack.EMPTY);
                    player.playNotifySound(SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.MASTER, 1f, 1f);
                    playerData.get(getToken(player)).dynamicScoreboard = !playerData.get(getToken(player)).dynamicScoreboard;

                    showMenu(player, serverMenu.settings);
                    return;
                }
                if(imeItema.contains(postavkeOpcije.serverTips + ":"))
                {
                    menu.setCarried(ItemStack.EMPTY);
                    player.playNotifySound(SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.MASTER, 1f, 1f);
                    playerData.get(getToken(player)).tips = !playerData.get(getToken(player)).tips;

                    showMenu(player, serverMenu.settings);
                    return;
                }
                if(imeItema.contains(postavkeOpcije.bossbar + ":"))
                {
                    menu.setCarried(ItemStack.EMPTY);
                    player.playNotifySound(SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.MASTER, 1f, 1f);
                    playerData.get(getToken(player)).bossbar = !playerData.get(getToken(player)).bossbar;

                    showMenu(player, serverMenu.settings);
                    
                    BossBarManager.hideBossBar(player);
                    prikaziInterfejs(player);
                    return;
                }
                if(imeItema.contains(postavkeOpcije.sideboard + ":"))
                {
                    menu.setCarried(ItemStack.EMPTY);
                    player.playNotifySound(SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.MASTER, 1f, 1f);
                    playerData.get(getToken(player)).sideboard = !playerData.get(getToken(player)).sideboard;

                    showMenu(player, serverMenu.settings);

                    SidebarManager.hideSidebar(player);
                    prikaziInterfejs(player);
                    return;
                }
            }
            ///////shop///////////
            if(openedMenu.get(player) == serverMenu.shop) if(!naMisu.isEmpty() && naMisu.has(DataComponents.CUSTOM_NAME))
            {
                String imeItema = naMisu.getHoverName().getString();

                if(imeItema.contains(shopOpcije.string_))
                {
                    menu.setCarried(ItemStack.EMPTY);
                    player.playNotifySound(SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.MASTER, 1f, 1f);
                
                    if(playerData.get(getToken(player)).coins < 10)
                    {
                        sendError(player, "Nemate dovoljno novca!");
                        return;
                    }

                    elecraft.utils.giveItem(player, Items.STRING, 1);
                    playerData.get(getToken(player)).coins -= 10;
                    SidebarManager.updateScoreboard(player);

                    showMenu(player, serverMenu.shop);
                    return;
                }
                if(imeItema.contains(shopOpcije.clayBall))
                {
                    menu.setCarried(ItemStack.EMPTY);
                    player.playNotifySound(SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.MASTER, 1f, 1f);
                
                    if(playerData.get(getToken(player)).coins < 5)
                    {
                        sendError(player, "Nemate dovoljno novca!");
                        return;
                    }

                    elecraft.utils.giveItem(player, Items.CLAY_BALL, 1);
                    playerData.get(getToken(player)).coins -= 5;
                    SidebarManager.updateScoreboard(player);

                    showMenu(player, serverMenu.shop);
                    return;
                }
                if(imeItema.contains(shopOpcije.coal))
                {
                    menu.setCarried(ItemStack.EMPTY);
                    player.playNotifySound(SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.MASTER, 1f, 1f);
                
                    if(playerData.get(getToken(player)).coins < 20)
                    {
                        sendError(player, "Nemate dovoljno novca!");
                        return;
                    }

                    elecraft.utils.giveItem(player, Items.COAL, 5);
                    playerData.get(getToken(player)).coins -= 20;
                    SidebarManager.updateScoreboard(player);

                    showMenu(player, serverMenu.shop);
                    return;
                }
            }
        }
        return;
    }

    @SubscribeEvent
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
            Integer house = Utils.isPlayerNearHouse(player);
            if(house != INVALID_HOUSE) if(playerData.get(getToken(player)).house != house)
            {
                player.closeContainer();
                sendError(player, "Ne mozete otvarati stvari u tudjoj kuci.");
            }
        }
        return;
    }

    @SubscribeEvent
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

    @SubscribeEvent
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
            Integer house = Utils.isPlayerNearHouse(player);
            if(house != INVALID_HOUSE) if(playerData.get(getToken(player)).house != house)
            {
                event.setCanceled(true);
                sendError(player, "Ne mozete unistavati blokove u blizini tudje kuce.");
            }
        }
    }

    @SubscribeEvent
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
            Integer house = Utils.isPlayerNearHouse(player);
            if(house != INVALID_HOUSE) if(playerData.get(getToken(player)).house != house)
            {
                event.setCanceled(true);
                sendError(player, "Ne mozete postavljati blokove u blizini tudje kuce.");
            }
        }
    }

    @SubscribeEvent
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

    @SubscribeEvent
    public void onDrop(ItemTossEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            if (isFrozen(player)) {
                event.setCanceled(true);
                // Vraćamo item u inventory jer ga je klijent već "izbacio"
                player.getInventory().add(event.getEntity().getItem());
                // Osvežavamo inventory klijentu da vidi da mu se item vratio
                player.containerMenu.broadcastChanges();
                // Slanje poruke u Action Bar (true na kraju označava overlay/actionbar)
                morateBitiUlogovani(player);
            }
        }
    }
    ///settings meni
    // Početno stanje (0 kod tebe, ovde boolean false)
    //public static boolean Postavka = false; 


}
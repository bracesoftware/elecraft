/*

        EleCraft TerraFirmaCraft SMP
        Brace Software Co.

        Server skripta

        by DEntisT_
        Helper: Wolfie

*/

package net.bracesoftware.elecraft;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.joml.Vector3f;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;
import net.minecraft.server.commands.data.DataCommands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.bracesoftware.elecraft.Implementation.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.references.Items;

import java.util.Base64;
import java.util.List;

///////////REAL SHIT
import static net.bracesoftware.elecraft.Implementation.INVALID_HOUSE;
import static net.bracesoftware.elecraft.Implementation.LOBBY_LEVEL_KEY;
import static net.bracesoftware.elecraft.Implementation.MAX_HOUSE;
import static net.bracesoftware.elecraft.Implementation.houses;
import static net.bracesoftware.elecraft.Implementation.GetDimension;
import static net.bracesoftware.elecraft.Implementation.HOUSE_RANGE_BLOCK;
import static net.bracesoftware.elecraft.Implementation.Const.ServerDimensions.*;

import static net.bracesoftware.elecraft.__PrivateCodeData.TOKEN_GENERATOR;


public class Utils
{
    private static final byte[] serverSecret = TOKEN_GENERATOR.getBytes(StandardCharsets.UTF_8);

    public static void load()
    {
        Implementation.elecraft.utils.write("(elecraftExtra): Successfully loaded.");
        return;
    }

    public static long getDays(ServerPlayer player)
    {
        // 24000 tickova je jedan pun krug (dan + noć)
        // getGameTime() se koristi jer se ne resetuje spavanjem
        return player.level().getGameTime() / 24000;
    }


    public static void sendLongHotbarMsg(ServerPlayer player, String text)
    {
        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 200, 10));
        player.connection.send(new ClientboundSetActionBarTextPacket(Component.literal(text)));
    }

    public static void addParticles(ServerPlayer player)
    {
        ServerLevel level = player.serverLevel();

        long time = level.getGameTime(); // stabilniji od System.currentTimeMillis()

        double radius = 0.7;
        double height = 1.8;
        double speed = 0.25;

        double angle = time * speed;

        double yOffset = (time % 40) / 40.0 * height;

        double x = player.getX() + Math.cos(angle) * radius;
        double z = player.getZ() + Math.sin(angle) * radius;
        double y = player.getY() + yOffset;

        float r = (float) (Math.sin(angle) * 0.5f + 0.5f);
        float g = (float) (Math.sin(angle + 2) * 0.5f + 0.5f);
        float b = (float) (Math.sin(angle + 4) * 0.5f + 0.5f);

        DustParticleOptions dust = new DustParticleOptions(
                new Vector3f(r, g, b),
                1.0f // veličina
        );

        level.sendParticles(
                dust,
                x, y, z,
                1,      // count
                0, 0, 0,
                0
        );

        return;
    }

    public static String generateToken(String username)
    {
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            byte[] randomBytes = new byte[32]; // 256-bit
            random.nextBytes(randomBytes);

            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            sha.update(serverSecret);
            sha.update(username.getBytes(StandardCharsets.UTF_8));
            sha.update(ByteBuffer.allocate(Long.BYTES)
                    .putLong(System.nanoTime()).array());
            sha.update(randomBytes);

            byte[] hash = sha.digest();
            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(hash);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static boolean isPlayerInDimension(ServerPlayer p, Integer i)
    {
        return GetDimension(p) == i;
    }

    public static boolean isPlayerInRange(ServerPlayer player, double range, double x, double y, double z)
    {
        Vec3 playerPos = player.position();
        
        Vec3 targetPos = new Vec3(x, y, z);
        
        double distanceSq = playerPos.distanceToSqr(targetPos);
        
        return distanceSq <= (range * range);
    }

    public static boolean VecSize(Double[] pos, Double range, Double[] pos2)
    {
        Vec3 POS1 = new Vec3(pos[0], pos[1], pos[2]);
        
        Vec3 POS2 = new Vec3(pos2[0], pos2[1], pos2[2]);
        
        double distanceSq = POS1.distanceToSqr(POS2);
        
        return distanceSq <= (range * range);
    }

    public static Integer isCoordNearHouse(Double[] pos, int dimension)
    {
        for(Integer i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] == null) continue;
            if(houses[i].dimension != dimension) continue;

            if(VecSize(pos, HOUSE_RANGE_BLOCK, new Double[] {houses[i].x, houses[i].y, houses[i].z}))
            {
                return i;
            }
        }
        return INVALID_HOUSE;
    }

    public static double getDistance(Double[] pos, Double[] pos2) {
        Vec3 POS1 = new Vec3(pos[0], pos[1], pos[2]);
        Vec3 POS2 = new Vec3(pos2[0], pos2[1], pos2[2]);
        return POS1.distanceTo(POS2);
    }

    public static class HouseResult
    {
        public Integer id = INVALID_HOUSE;
        public Float factor = 0f;

        public HouseResult(Integer b, float f)
        {
            id = b;
            factor = f;
        }
    }

    public static HouseResult isCoordNearHouseComplex(Double[] pos, int dimension)
    {
        for(int i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] == null) continue;
            if(houses[i].dimension != dimension) continue;

            double distance = getDistance(pos, new Double[] {houses[i].x, houses[i].y, houses[i].z});

            if(distance <= HOUSE_RANGE_BLOCK)
            {
                float factor = (float) (1.0 - (distance / HOUSE_RANGE_BLOCK));
                factor = Math.max(0.0f, Math.min(1.0f, factor));
                return new HouseResult(i, factor);
            }
        }

        return new HouseResult(INVALID_HOUSE, 0.0f);
    }

    public static Integer isPlayerInHouseBase(ServerPlayer player, Double range)
    {
        for(Integer i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] == null) continue;
            if(houses[i].dimension != GetDimension(player)) continue;

            if(isPlayerInRange(player, range, houses[i].x, houses[i].y, houses[i].z))
            {
                return i;
            }
        }
        return INVALID_HOUSE;
    }
    public static Integer isPlayerInHouseBase(ServerPlayer player)
    {
        for(Integer i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] == null) continue;
            if(houses[i].dimension != GetDimension(player)) continue;
            
            if(isPlayerInRange(player, HOUSE_RANGE_BLOCK, houses[i].x, houses[i].y, houses[i].z))
            {
                return i;
            }
        }
        return INVALID_HOUSE;
    }
    public static Boolean isPlayerInHouseBase(ServerPlayer player, Integer i)
    {
        if(houses[i] == null) return false;
        if(houses[i].dimension != GetDimension(player)) return false;
        
        if(isPlayerInRange(player, HOUSE_RANGE_BLOCK, houses[i].x, houses[i].y, houses[i].z))
        {
            return true;
        }
        return false;
    }
    public static Integer isPlayerNearHouse(ServerPlayer player)
    {
        for(Integer i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] == null) continue;
            if(houses[i].dimension != GetDimension(player)) continue;
            
            if(isPlayerInRange(player, HOUSE_RANGE_BLOCK + HOUSE_RANGE_BLOCK / 2, houses[i].x, houses[i].y, houses[i].z))
            {
                return i;
            }
        }
        return INVALID_HOUSE;
    }

    public static boolean False()
    {
        return false;
    }

    public static void sendTitle(ServerPlayer player, String text)
    {
        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 40, 10));

        player.connection.send(new ClientboundSetTitleTextPacket(Component.literal("§6" + text)));
        return;
    }
   
    public static void teleport(ServerPlayer player, double x, double y, double z, int dimension)
    {
        if (player == null || player.getServer() == null) return;

        ServerLevel targetLevel = null;
        
        if(dimension == Const.ServerDimensions.NETHER)
        {
            targetLevel = player.getServer().getLevel(Level.NETHER);
        }
        else if(dimension == Const.ServerDimensions.END)
        {
            targetLevel = player.getServer().getLevel(Level.END);
        }
        else if(dimension == Const.ServerDimensions.OVERWORLD)
        {
            targetLevel = player.getServer().getLevel(Level.OVERWORLD);
        }
        else if(dimension == Const.ServerDimensions.LOBBY)
        {
            targetLevel = player.getServer().getLevel(LOBBY_LEVEL_KEY);
        }

        if(targetLevel == null)
        {
            targetLevel = player.getServer().overworld();
        }

        player.teleportTo(
            targetLevel,
            x,
            y,
            z,
            java.util.Collections.emptySet(),
            player.getYRot(),
            player.getXRot()
        );
    }
    public static void saveBoolean(String filename, boolean value)
    {
        try
        {
            Files.writeString(Paths.get(filename), String.valueOf(value));
        }
        catch (IOException e)
        {
            System.err.println("Greška pri spašavanju: " + e.getMessage());
        }
    }

    public static void saveDouble(String filename, double value)
    {
        try
        {
            Files.writeString(Paths.get(filename), String.valueOf(value));
        }
        catch (IOException e)
        {
            System.err.println("Greška pri spašavanju: " + e.getMessage());
        }
    }

    public static boolean getBoolean(String filename)
    {
        try
        {
            String content = Files.readString(Paths.get(filename)).trim();
            return Boolean.parseBoolean(content);
        }
        catch(IOException e)
        {
            System.err.println("Greška pri čitanju: " + e.getMessage());
            return false;
        }
    }
    public static Double getDouble(String filename)
    {
        try
        {
            String content = Files.readString(Paths.get(filename)).trim();
            return Double.parseDouble(content);
        }
        catch(IOException e)
        {
            System.err.println("Greška pri čitanju: " + e.getMessage());
            return 0.0;
        }
    }
    public static String hash(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            
            BigInteger number = new BigInteger(1, hash);
            return number.toString(36); 
        } catch (Exception e) {
            return String.valueOf(input.hashCode());
        }
    }
    public static int getShopRows(int itemCount) {
        int itemr = (itemCount + 4) / 5;
        return itemr + 2;
    }
    public static void placeNavigationArrows(List<ItemStack> p, MenuTag menu, int slots)
    {
        if(p.size() != 2)
        {
            throw new IllegalArgumentException("Koga ti zajebavaŠ???? -> " + Integer.valueOf(p.size()).toString());
        }
        
        menu.getSlot(slots - Const.Misc.CHEST_COLUMNS).set(p.get(0));
        menu.getSlot(slots - 1).set(p.get(1));
        return;
    }
    public static void placeShopItemsCentered(
            MenuTag menu,
            List<ItemStack> items,
            int chestRows
    )
    {
        final int COLUMNS = 9;
        final int MAX_PER_ROW = 5;

        int itemCount = items.size();

        if (itemCount == 0) {
            return;
        }

        int shopRows = getShopRows(itemCount) - 2;

        if (shopRows > chestRows - 2) {
            throw new IllegalArgumentException(
                    "Previše itema za chest od " + chestRows + " redova!"
            );
        }

        int startRow = 1;

        int itemIndex = 0;

        for (int row = 0; row < shopRows; row++) {

            int remaining = itemCount - itemIndex;

            int itemsInRow = Math.min(MAX_PER_ROW, remaining);

            int startColumn = (COLUMNS - itemsInRow) / 2;

            for (int column = 0; column < itemsInRow; column++) {

                int slot =
                        (startRow + row) * COLUMNS
                        + startColumn
                        + column;

                menu.getSlot(slot).set(
                        items.get(itemIndex++)
                );
            }
        }
    }
    public static void placeShopItemsCentered_OLD(
        MenuTag menu,
        List<ItemStack> items,
        int rows
    )
    {
        final int columns = 9;
        final int itemCount = items.size();

        if (itemCount == 0) {
            return;
        }

        int usedRows = (int) Math.ceil(
                (double) itemCount / columns
        );

        int startRow = (rows - usedRows) / 2;

        int itemIndex = 0;

        for (int row = 0; row < usedRows; row++) {

            int remaining = itemCount - itemIndex;

            int rowsLeft = usedRows - row;

            int itemsInRow = (int) Math.ceil(
                    (double) remaining / rowsLeft
            );

            itemsInRow = Math.min(itemsInRow, columns);

            int startColumn = (columns - itemsInRow) / 2;

            for (int column = 0; column < itemsInRow; column++) {

                int slot =
                        (startRow + row) * columns
                        + startColumn
                        + column;

                menu.getSlot(slot).set(
                        items.get(itemIndex++)
                );
            }
        }
    }
}
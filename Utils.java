/*

        EleCraft TerraFirmaCraft SMP
        Brace Software Co.

        Server skripta

        by DEntisT_
        Helper: Wolfie

*/

package net.bracesoftware.elecraft;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.joml.Vector3f;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;

import java.util.Base64;
///////////REAL SHIT
import static net.bracesoftware.elecraft.Implementation.INVALID_HOUSE;
import static net.bracesoftware.elecraft.Implementation.MAX_HOUSE;
import static net.bracesoftware.elecraft.Implementation.houses;
import static net.bracesoftware.elecraft.Implementation.HOUSE_RANGE_BLOCK;

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

        // spiralni ugao
        double angle = time * speed;

        // visina spirale (loop)
        double yOffset = (time % 40) / 40.0 * height;

        double x = player.getX() + Math.cos(angle) * radius;
        double z = player.getZ() + Math.sin(angle) * radius;
        double y = player.getY() + yOffset;

        // 🌈 Rainbow boje (sin wave)
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

    public static boolean isPlayerInRange(ServerPlayer player, double range, double x, double y, double z)
    {
        Vec3 playerPos = player.position();
        
        Vec3 targetPos = new Vec3(x, y, z);
        
        double distanceSq = playerPos.distanceToSqr(targetPos);
        
        return distanceSq <= (range * range);
    }

    public static Integer isPlayerNearHouse(ServerPlayer player, Double range)
    {
        for(Integer i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] == null) continue;

            if(isPlayerInRange(player, range, houses[i].x, houses[i].y, houses[i].z))
            {
                return i;
            }
        }
        return INVALID_HOUSE;
    }
    public static Integer isPlayerNearHouse(ServerPlayer player)
    {
        for(Integer i = 0; i < MAX_HOUSE; ++i)
        {
            if(houses[i] == null) continue;
            
            if(isPlayerInRange(player, HOUSE_RANGE_BLOCK, houses[i].x, houses[i].y, houses[i].z))
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

}
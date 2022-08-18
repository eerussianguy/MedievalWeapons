package net.medievalweapons.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.medievalweapons.access.PlayerAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class PlayerPacket {

    public static final ResourceLocation DUAL_PACKET = new ResourceLocation("medievalweapons", "dual_weapon");

    public static Packet<?> attackPacket(Entity entity) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getId());
        return ClientPlayNetworking.createC2SPacket(DUAL_PACKET, buf);
    }

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(DUAL_PACKET, (server, player, handler, buffer, sender) -> {
            int entityId = buffer.readInt();
            server.execute(() -> {
                player.resetLastActionTime();
                if (player.level.getEntity(entityId) != null)
                    ((PlayerAccess) player).doOffhandAttack(player.level.getEntity(entityId));
            });

        });

    }
}

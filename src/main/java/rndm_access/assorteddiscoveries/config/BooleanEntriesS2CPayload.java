package rndm_access.assorteddiscoveries.config;

import io.netty.buffer.ByteBuf;
import org.jspecify.annotations.NonNull;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record BooleanEntriesS2CPayload(Map<String, Boolean> configMap) implements CustomPacketPayload {
    public static final Identifier CONFIG_PAYLOAD_ID = AssortedDiscoveries.makeModId("config");
    public static final CustomPacketPayload.Type<BooleanEntriesS2CPayload> ID = new CustomPacketPayload.Type<>(CONFIG_PAYLOAD_ID);
    public static final StreamCodec<ByteBuf, Map<String, Boolean>> PACKET_CODEC = new StreamCodec<>() {
        public @NonNull Map<String, Boolean> decode(ByteBuf byteBuf) {
            int size = byteBuf.readInt();
            Map<String, Boolean> hashMap = new HashMap<>();

            for (int i = 0; i < size; i++) {
                String str = ((FriendlyByteBuf) byteBuf).readUtf();
                boolean bool = byteBuf.readBoolean();
                hashMap.put(str, bool);
            }
            return hashMap;
        }

        public void encode(ByteBuf byteBuf, Map<String, Boolean> configMap) {
            byteBuf.writeInt(configMap.size());
            for (String key : configMap.keySet()) {
                ((FriendlyByteBuf) byteBuf).writeUtf(key);
                byteBuf.writeBoolean(configMap.get(key));
            }
        }
    };
    public static final StreamCodec<RegistryFriendlyByteBuf, BooleanEntriesS2CPayload> CODEC
            = StreamCodec.composite(PACKET_CODEC, BooleanEntriesS2CPayload::configMap, BooleanEntriesS2CPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}

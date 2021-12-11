package net.andre.emct.mixin;

import net.andre.emct.EarthMCTweaks;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientPlayNetworkHandler.class})
public class MixinClientPlayNetworkHandler {
    @Shadow
    private MinecraftClient client;

    @Inject(method = {"onGameMessage"}, at = {@At("HEAD")})
    public void onChatMessage(GameMessageS2CPacket packet, CallbackInfo info) {
        if (this.client.isOnThread())
            EarthMCTweaks.handleChat(packet);
    }
}

package net.andre.emct;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ChatUtil;

public class EarthMCTweaks implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("emct");

	public static boolean isAfk(String string) {
		return string.matches("You are now AFK.");
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}

	public static void handleChat(GameMessageS2CPacket packet) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (isAfk(ChatUtil.stripTextFormat(packet.getMessage().getString()))) {
			for (int i = 0; i < 3; i++) {
				assert client.player != null;
				client.player.playSound(SoundEvents.BLOCK_BELL_USE, 1.0F, 1.0F);
			}
		}
	}
}

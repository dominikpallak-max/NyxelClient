package dev.nyxel;

import dev.nyxel.core.Commands;
import dev.nyxel.core.Config;
import dev.nyxel.core.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NyxelClient implements ClientModInitializer {
    public static final String NAME = "Nyxel";
    public static final String VERSION = "1.0.0";
    public static final Logger LOG = LoggerFactory.getLogger(NAME);
    public static boolean toggleMessages = true;

    @Override
    public void onInitializeClient() {
        ModuleManager.init();
        Config.load("default", false);
        Commands.register();
        ClientTickEvents.END_CLIENT_TICK.register(c -> ModuleManager.tick());
        HudRenderCallback.EVENT.register((ctx, tickCounter) -> ModuleManager.renderHud(ctx));
        ClientLifecycleEvents.CLIENT_STOPPING.register(c -> Config.save("default"));
    }
}

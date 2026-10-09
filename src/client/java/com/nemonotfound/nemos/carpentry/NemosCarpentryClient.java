package com.nemonotfound.nemos.carpentry;

import com.nemonotfound.nemos.carpentry.entity.CarpentryEntities;
import com.nemonotfound.nemos.carpentry.entity.renderer.ChairEntityRenderer;
import com.nemonotfound.nemos.carpentry.screen.CarpentryScreen;
import net.fabricmc.api.ClientModInitializer;
import com.nemonotfound.nemos.carpentry.client.recipebook.ClientCarpentryRecipeManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;

import static com.nemonotfound.nemos.carpentry.screen.CarpentryMenuTypes.CARPENTRY_SCREEN_HANDLER;

@Environment(EnvType.CLIENT)
public class NemosCarpentryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCarpentryRecipeManager.register();
        MenuScreens.register(CARPENTRY_SCREEN_HANDLER, CarpentryScreen::new);

        EntityRendererRegistry.register(CarpentryEntities.CHAIR_ENTITY, ChairEntityRenderer::new);
    }
}

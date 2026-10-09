package com.nemonotfound.nemos.carpentry.screen;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;
import static com.nemonotfound.nemos.carpentry.NemosCarpentry.log;

public class CarpentryMenuTypes {

    public static final MenuType<CarpentryMenu> CARPENTRY_SCREEN_HANDLER = Registry.register(BuiltInRegistries.MENU,
            Identifier.withDefaultNamespace(MOD_ID), new MenuType<>(CarpentryMenu::new, FeatureFlags.VANILLA_SET));

    public static void registerScreenHandlerTypes() {
        log.info("Registering screen handlers");
    }

}

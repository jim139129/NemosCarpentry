package com.nemonotfound.nemos.carpentry.client.recipebook;

import com.nemonotfound.nemos.carpentry.network.CarpentryRecipesPayload;
import com.nemonotfound.nemos.carpentry.recipe.CarpentryRecipeService;
import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import com.nemonotfound.nemos.carpentry.screen.CarpentryMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public final class ClientCarpentryRecipeManager {
    private static CarpentryRecipeDisplay.Grouping recipes = CarpentryRecipeDisplay.Grouping.empty();
    private static Consumer<CarpentryRecipeDisplay.Grouping> listener = grouping -> {};

    private ClientCarpentryRecipeManager() {}

    public static void register() {
        CarpentryRecipeService.setClientRecipeSource(ClientCarpentryRecipeManager::recipes);
        // Fabric invokes play receivers on the client thread, after decoding the complete payload.
        ClientPlayNetworking.registerGlobalReceiver(CarpentryRecipesPayload.TYPE, (payload, context) -> {
            if (payload.recipes().revision() > recipes.revision()) {
                replace(payload.recipes(), context.client());
            }
        });
        ClientPlayConnectionEvents.INIT.register((handler, client) -> replace(CarpentryRecipeDisplay.Grouping.empty(), client));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> replace(CarpentryRecipeDisplay.Grouping.empty(), client));
    }

    private static void replace(CarpentryRecipeDisplay.Grouping replacement, Minecraft client) {
        recipes = replacement;
        if (client.player != null && client.player.containerMenu instanceof CarpentryMenu menu) {
            menu.refreshRecipes();
        }
        listener.accept(replacement);
    }

    public static CarpentryRecipeDisplay.Grouping recipes() {
        return recipes;
    }

    /** Optional integrations register themselves; the base mod never loads their classes. */
    public static void setListener(Consumer<CarpentryRecipeDisplay.Grouping> replacementListener) {
        listener = replacementListener;
    }
}

package com.nemonotfound.nemos.carpentry.network;

import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;

public record CarpentryRecipesPayload(CarpentryRecipeDisplay.Grouping recipes) implements CustomPacketPayload {
    public static final Type<CarpentryRecipesPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(MOD_ID, "update_recipes"));
    public static final StreamCodec<RegistryFriendlyByteBuf, CarpentryRecipesPayload> CODEC =
            CarpentryRecipeDisplay.Grouping.codec().map(CarpentryRecipesPayload::new, CarpentryRecipesPayload::recipes);

    @Override
    public Type<CarpentryRecipesPayload> type() {
        return TYPE;
    }
}

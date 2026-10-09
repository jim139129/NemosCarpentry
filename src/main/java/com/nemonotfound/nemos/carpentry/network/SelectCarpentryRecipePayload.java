package com.nemonotfound.nemos.carpentry.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;

/** A revision prevents a click queued before reload from selecting a different recipe. */
public record SelectCarpentryRecipePayload(int containerId, long revision, Identifier recipeId) implements CustomPacketPayload {
    public static final Type<SelectCarpentryRecipePayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(MOD_ID, "select_recipe"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SelectCarpentryRecipePayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, SelectCarpentryRecipePayload::containerId,
            ByteBufCodecs.VAR_LONG, SelectCarpentryRecipePayload::revision,
            Identifier.STREAM_CODEC, SelectCarpentryRecipePayload::recipeId,
            SelectCarpentryRecipePayload::new);

    @Override
    public Type<SelectCarpentryRecipePayload> type() {
        return TYPE;
    }
}

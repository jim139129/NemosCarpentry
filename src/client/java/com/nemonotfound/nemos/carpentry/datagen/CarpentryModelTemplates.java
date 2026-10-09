package com.nemonotfound.nemos.carpentry.datagen;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;

import java.util.Optional;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;

public class CarpentryModelTemplates {

    public static final ModelTemplate PARK_BENCH = item("seats/park_bench_full", TextureSlot.LAYER0);
    public static final ModelTemplate PARK_BENCH_LEFT = block("seats/park_bench_left", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate PARK_BENCH_RIGHT = block("seats/park_bench_right", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CHAIR_LUKAS = block("seats/chair_lukas", TextureSlot.ALL, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate CHAIR_TOMMY = block("seats/chair_tommy", TextureSlot.ALL, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate CHAIR_GREGORY = block("seats/chair_gregory", TextureSlot.ALL, TextureSlot.BOTTOM, TextureSlot.TEXTURE, TextureSlot.PARTICLE);
    public static final ModelTemplate CHAIR_LUKAS_NATURAL = block("seats/chair_lukas_natural", TextureSlot.ALL, TextureSlot.TEXTURE, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate CHAIR_TOMMY_NATURAL = block("seats/chair_tommy_natural", TextureSlot.ALL, TextureSlot.TEXTURE, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate CHAIR_PASCAL = block("seats/chair_pascal", TextureSlot.TEXTURE, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate CHAIR_PASCAL_WITH_WOOL = block("seats/chair_pascal_with_wool", TextureSlot.ALL, TextureSlot.TEXTURE, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate BARBER_CHAIR = block("seats/barber_chair", TextureSlot.TEXTURE, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate BAR_CHAIR = block("seats/bar_chair", TextureSlot.TEXTURE, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate TABLE_THE_CLASSIC = block("tables/table_the_classic", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate COFFEE_TABLE_PASCAL = block("tables/coffee_table_pascal", TextureSlot.TEXTURE, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate STANDING_TABLE = block("tables/standing_table", TextureSlot.ALL, TextureSlot.TEXTURE, TextureSlot.PARTICLE);
    public static final ModelTemplate CHAIR = block("seats/chair", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate CHAIR_FELIX = block("seats/chair_felix", TextureSlot.ALL, TextureSlot.PARTICLE);
    public static final ModelTemplate LADDER = block("ladders/ladder", TextureSlot.TEXTURE, TextureSlot.PARTICLE);
    public static final ModelTemplate COFFEE_TABLE = block("tables/coffee_table", TextureSlot.ALL, TextureSlot.TEXTURE, TextureSlot.BOTTOM, TextureSlot.PARTICLE);
    public static final ModelTemplate BARREL_SEAT = block("seats/barrel_seat", TextureSlot.TEXTURE, TextureSlot.SIDE, TextureSlot.PARTICLE);
    public static final ModelTemplate LOG_SEAT = block("seats/log_seat", TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.PARTICLE);

    private static ModelTemplate block(String parent, TextureSlot... requiredTextureSlots) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureSlots);
    }

    private static ModelTemplate item(String parent, TextureSlot ... requiredTextureSlots) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(MOD_ID, "item/" + parent)), Optional.empty(), requiredTextureSlots);
    }

    private static ModelTemplate block(String parent, String variant, TextureSlot ... requiredTextureSlots) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(MOD_ID, "block/" + parent)), Optional.of(variant), requiredTextureSlots);
    }
}

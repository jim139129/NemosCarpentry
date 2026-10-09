package com.nemonotfound.nemos.carpentry.jei;

import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import mezz.jei.api.recipe.types.IRecipeType;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;

public final class NemosCarpentryJeiRecipeTypes {

    public static final IRecipeType<CarpentryRecipeDisplay.GroupEntry> CARPENTRY =
            IRecipeType.create(MOD_ID, "carpentry", CarpentryRecipeDisplay.GroupEntry.class);

    private NemosCarpentryJeiRecipeTypes() {
    }

    public static IRecipeType<CarpentryRecipeDisplay.GroupEntry> carpentry() {
        return CARPENTRY;
    }
}

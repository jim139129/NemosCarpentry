package com.nemonotfound.nemos.carpentry;

import com.nemonotfound.nemos.carpentry.entity.CarpentryEntities;
import com.nemonotfound.nemos.carpentry.item.CarpentryCreativeModeTabs;
import com.nemonotfound.nemos.carpentry.recipe.CarpentryRecipeSerializer;
import com.nemonotfound.nemos.carpentry.recipe.CarpentryRecipeService;
import com.nemonotfound.nemos.carpentry.recipe.CarpentryRecipeTypes;
import com.nemonotfound.nemos.carpentry.recipe.book.CarpentryRecipeBookCategory;
import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplays;
import com.nemonotfound.nemos.carpentry.screen.CarpentryMenuTypes;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NemosCarpentry implements ModInitializer {

	public static final String MOD_ID = "nemos-carpentry";
    public static final Logger log = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		log.info("Thank you for using Nemo's Carpentry!");
		CarpentryRecipeDisplays.registerRecipeDisplays();
		CarpentryMenuTypes.registerScreenHandlerTypes();
		CarpentryRecipeTypes.registerRecipeTypes();
		CarpentryRecipeSerializer.registerRecipeSerializer();
		CarpentryCreativeModeTabs.registerItemGroups();
		CarpentryEntities.registerEntities();
		CarpentryRecipeBookCategory.registerRecipeBookCategories();
        CarpentryRecipeService.register();
	}
}
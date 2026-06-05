package com.nemonotfound.nemos.carpentry.mixin;

import com.nemonotfound.nemos.carpentry.client.recipebook.ClientCarpentryRecipeManager;
import com.nemonotfound.nemos.carpentry.interfaces.MinecraftGetter;
import com.nemonotfound.nemos.carpentry.interfaces.CarpentryRecipeManagerGetter;
import com.nemonotfound.nemos.carpentry.network.listener.CarpentryClientGamePacketListener;
import com.nemonotfound.nemos.carpentry.network.packet.s2c.play.ClientboundUpdateRecipesPacket;
import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.CommonListenerCookie;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin extends ClientCommonPacketListenerImpl implements ClientGamePacketListener, CarpentryClientGamePacketListener, CarpentryRecipeManagerGetter {

    @Unique
    private ClientCarpentryRecipeManager modRecipeManager = new ClientCarpentryRecipeManager(CarpentryRecipeDisplay.Grouping.empty());

    protected ClientPacketListenerMixin(Minecraft minecraft, Connection connection, CommonListenerCookie commonListenerCookie) {
        super(minecraft, connection, commonListenerCookie);
    }

    @Override
    public ClientCarpentryRecipeManager nemo_sCarpentry$getModRecipeManager() {
        return this.modRecipeManager;
    }

    @Override
    public void nemo_sCarpentry$onSynchronizeModRecipes(ClientboundUpdateRecipesPacket packet) {
        Minecraft minecraft = ((MinecraftGetter) this).nemo_sCarpentry$getMinecraft();
        PacketUtils.ensureRunningOnSameThread(packet, this, minecraft.packetProcessor());
        this.modRecipeManager = new ClientCarpentryRecipeManager(packet.carpentryRecipes());
        nemo_sCarpentry$syncJeiRecipes(packet.carpentryRecipes());
    }

    @Unique
    private static void nemo_sCarpentry$syncJeiRecipes(CarpentryRecipeDisplay.Grouping grouping) {
        try {
            Class<?> pluginClass = Class.forName("com.nemonotfound.nemos.carpentry.jei.NemosCarpentryJeiPlugin");
            pluginClass.getMethod("onCarpentryRecipesSynced", CarpentryRecipeDisplay.Grouping.class).invoke(null, grouping);
        } catch (ReflectiveOperationException | LinkageError ignored) {
        }
    }
}

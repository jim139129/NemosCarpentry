package com.nemonotfound.nemos.carpentry.entity.renderer;


import com.nemonotfound.nemos.carpentry.entity.ChairEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class ChairEntityRenderer extends EntityRenderer<ChairEntity, EntityRenderState> {

    public ChairEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }


}

package com.finallion.graveyard.blockentities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blockentities.BrazierBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BrazierModel extends AnimatedGeoModel<BrazierBlockEntity> {
    @Override
    public Identifier getAnimationResource(BrazierBlockEntity entity) {
        return new Identifier(TheGraveyard.MOD_ID,"animations/brazier.animation.json");
    }

    @Override
    public Identifier getModelResource(BrazierBlockEntity animatable) {
        return new Identifier(TheGraveyard.MOD_ID, "geo/brazier.geo.json");
    }

    @Override
    public Identifier getTextureResource(BrazierBlockEntity entity) {
        return new Identifier(TheGraveyard.MOD_ID, "textures/block/brazier.png");
    }
}

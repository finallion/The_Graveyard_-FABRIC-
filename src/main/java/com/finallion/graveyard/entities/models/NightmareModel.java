package com.finallion.graveyard.entities.models;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.entities.NightmareEntity;
import com.finallion.graveyard.entities.RevenantEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NightmareModel extends AnimatedGeoModel<NightmareEntity> {

    @Override
    public Identifier getModelLocation(NightmareEntity object) {
        return new Identifier(TheGraveyard.MOD_ID, "geo/nightmare.geo.json");
    }

    @Override
    public Identifier getTextureLocation(NightmareEntity object) {
        return new Identifier(TheGraveyard.MOD_ID, "textures/entity/nightmare.png");
    }

    @Override
    public Identifier getAnimationFileLocation(NightmareEntity animatable) {
        return new Identifier(TheGraveyard.MOD_ID, "animations/nightmare/nightmare.animation.json");
    }
}

package com.finallion.graveyard.world.biomes;

import com.finallion.graveyard.init.TGConfiguredFeatures;
import com.finallion.graveyard.init.TGEntities;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

public class HauntedForestBiomes {

    public static Biome createHauntedForest() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(TGEntities.GHOUL, 45, 2, 5));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(TGEntities.SKELETON_CREEPER, 40, 1, 2));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 3));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 3, 5));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 10, 3, 5));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 3, 5));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CAT, 1, 1, 1));
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        //generationSettings.surfaceBuilder(TGSurfaceBuilders.HAUNTED_FOREST_SURFACE_CONFIG);
        // TODO: vanilla structures

        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addMossyRocks(generationSettings);
        DefaultBiomeFeatures.addTaigaGrass(generationSettings);
        DefaultBiomeFeatures.addGiantTaigaGrass(generationSettings);
        DefaultBiomeFeatures.addLargeFerns(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);

        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_PUMPKIN);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.HAUNTED_FOREST_TREES_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.MOSS_CARPET_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.COBWEB_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.GRAVESTONE_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.BUSH_PLACED_FEATURE);




        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.FOREST)
                .temperature(0.6F)
                .downfall(0.9F)
                .effects((new BiomeEffects.Builder())
                        .grassColor(0x6F932A)
                        .foliageColor(0x6F932A)
                        .waterColor(0xAEC1BE)
                        .waterFogColor(0xC9DDDA)
                        .fogColor(0x878787)
                        .skyColor(0x878787)
                        .music(MusicType.GAME)
                        .loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                        .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    public static Biome createHauntedLakes() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(TGEntities.GHOUL, 25, 2, 5));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(TGEntities.SKELETON_CREEPER, 15, 1, 2));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 3));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 3, 5));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 10, 3, 5));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 3, 5));
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        //generationSettings.surfaceBuilder(TGSurfaceBuilders.HAUNTED_FOREST_SURFACE_CONFIG);

        DefaultBiomeFeatures.addSwampFeatures(generationSettings);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addMossyRocks(generationSettings);
        DefaultBiomeFeatures.addTaigaGrass(generationSettings);
        DefaultBiomeFeatures.addGiantTaigaGrass(generationSettings);
        DefaultBiomeFeatures.addLargeFerns(generationSettings);

        DefaultBiomeFeatures.addClayDisk(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);
        DefaultBiomeFeatures.addSeagrassOnStone(generationSettings);
        DefaultBiomeFeatures.addSwampVegetation(generationSettings);

        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_SWAMP);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_NORMAL);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY);

        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.HAUNTED_FOREST_TREES_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.MOSS_CARPET_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.COBWEB_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.GRAVESTONE_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.BUSH_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.SOUL_LIGHT_PLACED_FEATURE);


        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.FOREST)
                .temperature(0.6F)
                .downfall(0.9F)
                .effects((new BiomeEffects.Builder())
                        .grassColor(0x7EA530)
                        .foliageColor(0x7EA530)
                        .waterColor(0x9B091C)
                        .waterFogColor(0xD80D28)
                        .fogColor(0xB2B2B2)
                        .skyColor(0xB2B2B2)
                        .music(MusicType.GAME)
                        .loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

    }

    public static Biome createErodedHauntedForest() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(TGEntities.GHOUL, 10, 2, 5));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(TGEntities.REAPER, 1, 1, 1));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(TGEntities.SKELETON_CREEPER, 10, 1, 2));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 2, 3));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 8, 1, 2));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 1, 1));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CAT, 1, 1, 1));

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        //generationSettings.surfaceBuilder(TGSurfaceBuilders.ERODED_HAUNTED_FOREST_SURFACE_CONFIG);
        //generationSettings.structureFeature(ConfiguredStructureFeatures.NETHER_FOSSIL);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addMossyRocks(generationSettings);
        DefaultBiomeFeatures.addTaigaGrass(generationSettings);
        DefaultBiomeFeatures.addGiantTaigaGrass(generationSettings);
        DefaultBiomeFeatures.addLargeFerns(generationSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);

        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_PUMPKIN);

        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.ERODED_HAUNTED_FOREST_TREES_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.COBWEB_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.GRAVESTONE_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, TGConfiguredFeatures.BUSH_PLACED_FEATURE);



        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.FOREST)
                .temperature(0.4F)
                .downfall(0.4F)
                .effects((new BiomeEffects.Builder())
                        .grassColor(0x77AB2F)
                        .foliageColor(0x77AB2F)
                        .waterColor(0xAEC1BE)
                        .waterFogColor(0xC9DDDA)
                        .fogColor(0x6B6B6B)
                        .skyColor(0x6B6B6B)
                        .music(MusicType.GAME)
                        .loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                        .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

    }
}
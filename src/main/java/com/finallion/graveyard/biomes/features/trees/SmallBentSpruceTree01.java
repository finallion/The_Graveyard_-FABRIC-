package com.finallion.graveyard.biomes.features.trees;

import com.finallion.graveyard.biomes.features.TGTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class SmallBentSpruceTree01 extends BaseSpruceTree {


    public SmallBentSpruceTree01(Codec<TGTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<TGTreeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(blockPos);
        BlockState wood = context.getConfig().woodState;
        BlockState leaf = context.getConfig().leafState;
        Random random = context.getRandom();
        int offsetTrunk = random.nextInt(3);
        Direction.Axis axis = random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
        Direction direction = Direction.from(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);

        if (world.getBlockState(blockPos.down()) != Blocks.MOSS_BLOCK.getDefaultState()) return false;

        // straight half of the trunk
        for (int i = 0; i < 12 - 6 + offsetTrunk; i++) {
            world.setBlockState(blockPos.up(i), wood, 2);
        }

        // bent half of the trunk
        for (int i = 6 + offsetTrunk; i < 12  + offsetTrunk; i++) {
            world.setBlockState(blockPos.up(i).offset(direction), wood, 2);
        }

        // start generating leaves from the top
        // shift leaf layer in bending direction
        mutable.move(0, 12 + offsetTrunk, 0);
        mutable.move(direction);

        // single tree top
        world.setBlockState(mutable.add(0, 3, 0), leaf, 2);
        world.setBlockState(mutable.add(0, 2, 0), leaf, 2);
        world.setBlockState(mutable.add(0, 1, 0), leaf, 2);
        world.setBlockState(mutable.add(0, 0, 0), leaf, 2);

        generateOneStar(context, mutable.add(0, 0, 0), false);
        generateOneStar(context, mutable.add(0, -1, 0), false);

        // second layer
        generateOneStar(context, mutable.add(0, -3, 0), false);
        generateTwoStar(context, mutable.add(0, -4, 0), false);

        world.setBlockState(mutable.add(1, -5, 0), leaf, 2);
        world.setBlockState(mutable.add(0, -5, 1), leaf, 2);
        world.setBlockState(mutable.add(-1, -5, -1), leaf, 2);

        // third layer
        generateTwoStar(context, mutable.add(0, -6, 0), false);
        generateThreeStar(context, mutable.add(0, -7, 0), true);

        // revert bending direction
        world.setBlockState(mutable.add(1, -8, 0).offset(direction.getOpposite()), leaf, 2);
        world.setBlockState(mutable.add(0, -8, 1).offset(direction.getOpposite()), leaf, 2);
        world.setBlockState(mutable.add(0, -8, -1).offset(direction.getOpposite()), leaf, 2);

        // forth layer
        generateTwoStar(context, mutable.add(0, -9, 0).offset(direction.getOpposite()), false);
        generateThreeStar(context, mutable.add(0, -10, 0).offset(direction.getOpposite()), false);




        return false;
    }
}
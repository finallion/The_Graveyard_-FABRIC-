package com.finallion.graveyard.blocks;

import com.finallion.graveyard.blockentities.GravestoneBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.loot.context.LootContext;
import net.minecraft.network.packet.s2c.play.SignEditorOpenS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;


public class GravestoneBlock extends SignBlock implements BlockEntityProvider {
    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty FLOOR = Properties.BOTTOM;
    private final Identifier texture;

    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    public GravestoneBlock(Identifier texture) {
        super(FabricBlockSettings.of(Material.STONE).noCollision().nonOpaque().sounds(BlockSoundGroup.DEEPSLATE_BRICKS).strength(1.5F), SignType.OAK);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(FLOOR, true).with(WATERLOGGED, false));
        this.texture = texture;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, FLOOR, WATERLOGGED, ROTATION);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }


    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GravestoneBlockEntity(pos, state);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        boolean bl = item instanceof DyeItem;
        boolean bl2 = itemStack.isOf(Items.GLOW_INK_SAC);
        boolean bl3 = itemStack.isOf(Items.INK_SAC);
        boolean bl4 = (bl2 || bl || bl3) && player.getAbilities().allowModifyWorld;
        if (world.isClient) {
            return bl4 ? ActionResult.SUCCESS : ActionResult.CONSUME;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (!(blockEntity instanceof GravestoneBlockEntity)) {
                return ActionResult.PASS;
            } else {
                GravestoneBlockEntity signBlockEntity = (GravestoneBlockEntity) blockEntity;
                boolean bl5 = signBlockEntity.isGlowingText();
                if ((!bl2 || !bl5) && (!bl3 || bl5)) {
                    if (bl4) {
                        boolean bl8;
                        if (bl2) {
                            world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_GLOW_INK_SAC_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            bl8 = signBlockEntity.setGlowingText(true);
                            if (player instanceof ServerPlayerEntity) {
                                Criteria.ITEM_USED_ON_BLOCK.test((ServerPlayerEntity) player, pos, itemStack);
                            }
                        } else if (bl3) {
                            world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_INK_SAC_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            bl8 = signBlockEntity.setGlowingText(false);
                        } else {
                            world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            bl8 = signBlockEntity.setTextColor(((DyeItem) item).getColor());
                        }

                        if (bl8) {
                            if (!player.isCreative()) {
                                itemStack.decrement(1);
                            }

                            player.incrementStat(Stats.USED.getOrCreateStat(item));
                        }
                    }

                    return signBlockEntity.onActivate((ServerPlayerEntity) player) ? ActionResult.SUCCESS : ActionResult.PASS;
                } else {
                    return ActionResult.PASS;
                }
            }
        }
    }


    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (placer != null && placer instanceof PlayerEntity) {
            GravestoneBlockEntity sign = (GravestoneBlockEntity) world.getBlockEntity(pos);
            if (!world.isClient) {
                sign.setEditor(placer.getUuid());
                ((ServerPlayerEntity) placer).networkHandler.connection.send(new SignEditorOpenS2CPacket(pos));
            }
            else
                sign.setEditable(true);
        }
    }



    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction direction = ctx.getPlayerFacing();
        return (BlockState)((BlockState)this.getDefaultState().with(FACING, direction.getOpposite()).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER));
    }


    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(this));
    }


    public Identifier getTexture() {
        return texture;
    }


    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }
}
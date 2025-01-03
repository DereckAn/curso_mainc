package net.dereckan.cursopago.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FluoriteLampBlock extends Block {
    public static final BooleanProperty CLICKDED = BooleanProperty.of("clicked");

    public FluoriteLampBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient) {
            world.setBlockState(pos, state.cycle(CLICKDED));
        }
        return ActionResult.SUCCESS;
    }

    //Este metodo es super importante. Aun no se para que
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CLICKDED);
    }
}

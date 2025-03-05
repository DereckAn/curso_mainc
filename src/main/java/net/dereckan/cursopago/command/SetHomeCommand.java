package net.dereckan.cursopago.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.dereckan.cursopago.util.IEntityDataSaver;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class SetHomeCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess registryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("home")
                .then(CommandManager.literal("set")
                        .executes(SetHomeCommand::run)));

    }

    private static int run(CommandContext<ServerCommandSource> context) {
        IEntityDataSaver player = (IEntityDataSaver) context.getSource().getEntity();
        BlockPos pos = Objects.requireNonNull(context.getSource().getPlayer()).getBlockPos();
        String positionString = "(" + pos.getX() + "," + pos.getY() + "," + pos.getZ() + ")";

        assert player != null;
        player.getPersistantData().putIntArray("cursopago.homepos", new int[]{pos.getX(), pos.getY(), pos.getZ()});

        context.getSource().sendFeedback(()-> Text.literal("Home set to " + positionString), true);
        return 1;
    }
}

package org.waste.of.time.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.waste.of.time.ChestHandler;
import org.waste.of.time.event.Events;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Nullable public Screen currentScreen;

    @Inject(method = "tick", at = @At("HEAD"))
    public void tickHead(final CallbackInfo ci) {
        Events.INSTANCE.onClientTickStart();
    }

    @Inject(method = "setScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;removed()V", shift = At.Shift.AFTER))
    private void onScreenRemove(@Nullable Screen screen, CallbackInfo ci) {
        if (currentScreen != null) {
            ChestHandler.INSTANCE.onScreenRemoved(currentScreen);
        }
    }
}
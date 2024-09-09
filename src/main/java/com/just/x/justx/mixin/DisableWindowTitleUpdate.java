package com.just.x.justx.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This mixin class is used to modify the behavior of the Minecraft client.
 * Specifically, it cancels the updateTitle method to prevent the window title from being updated.
 */
@Mixin(Minecraft.class)
public class DisableWindowTitleUpdate {

    /**
     * Injects into the updateTitle method at the head, cancelling the method call.
     *
     * @param info CallbackInfo provided by the Mixin framework to control the method execution.
     */
    @Inject(method = "updateTitle", at = @At("HEAD"), cancellable = true)
    private void updateTitle(CallbackInfo info) {
        info.cancel();
    }
}
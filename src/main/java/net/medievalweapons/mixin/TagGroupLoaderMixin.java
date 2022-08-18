package net.medievalweapons.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.medievalweapons.compat.CompatItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagLoader;

@Mixin(TagLoader.class)
public class TagGroupLoaderMixin<T> {

    @Inject(method = "buildGroup", at = @At("HEAD"))
    private void buildGroupMixin(Map<ResourceLocation, Tag.Builder> tags, CallbackInfoReturnable<Map<ResourceLocation, Tag<T>>> info) {
        if (!CompatItems.isDragonLootLoaded) {
            tags.remove(new ResourceLocation("dragonloot", "explosion_resistant"));
        }
    }
}

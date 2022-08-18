package net.medievalweapons.mixin;

import java.util.Map;

import com.google.gson.JsonElement;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.medievalweapons.compat.CompatItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

@Mixin(ServerAdvancementManager.class)
public class ServerAdvancementLoaderMixin {

    @Inject(method = "apply", at = @At("HEAD"))
    protected void applyMixin(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo info) {
        if (!CompatItems.isBetterEndLoaded) {
            map.remove(new ResourceLocation("medievalweapons", "medieval_ruby_nether_recipes"));
            map.remove(new ResourceLocation("medievalweapons", "medieval_cincinnasite_nether_recipes"));
            map.remove(new ResourceLocation("medievalweapons", "medieval_cincinnasite_diamond_nether_recipes"));
        }
        if (!CompatItems.isBetterNetherLoaded) {
            map.remove(new ResourceLocation("medievalweapons", "medieval__aternium_end_recipes"));
            map.remove(new ResourceLocation("medievalweapons", "medieval__terminite_end_recipes"));
            map.remove(new ResourceLocation("medievalweapons", "medieval__thallasium_end_recipes"));
        }
        if (!CompatItems.isDragonLootLoaded) {
            map.remove(new ResourceLocation("medievalweapons", "medieval_dragon_recipes"));
        }
        if (!CompatItems.isBYGLoaded) {
            map.remove(new ResourceLocation("medievalweapons", "medieval_pendorite_recipes"));
        }
    }
}
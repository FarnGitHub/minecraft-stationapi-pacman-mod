package farn.pacman_arcade;

import farn.pacman_arcade.block.PacmanArcadeBlock;
import farn.pacman_arcade.block.PacmanArcadeBlockTop;
import farn.pacman_arcade.block.entity.PacManBlockEntity;
import farn.pacman_arcade.packet.OpenScreenPacket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.MessageListenerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.template.item.TemplateSecondaryBlockItem;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.SideUtil;

@SuppressWarnings("unused")
public class PacmanMain {
	@Entrypoint.Namespace
	public static Namespace NAMESPACE;

	public static Block arcadeBottom;
	public static Block arcadeTop;
	public static Item arcadeBlockItem;
	public static Item coins;
	public static Material metalMaterial = new Material(MapColor.LIGHT_GRAY);

	@EventListener
	public void registerBlock(BlockRegistryEvent event) {
		arcadeBottom = new PacmanArcadeBlock(NAMESPACE.id("pacman_arcade_block_bottom"));
		arcadeTop = new PacmanArcadeBlockTop(NAMESPACE.id("pacman_arcade_block_top"));
	}

	@EventListener
	public void registerItem(ItemRegistryEvent event) {
		arcadeBlockItem = (new TemplateSecondaryBlockItem(NAMESPACE.id("arcade_item"), arcadeBottom)).setTranslationKey(NAMESPACE, "pacman_arcade_item");
		coins = new TemplateItem(NAMESPACE.id("coins")).setTranslationKey(NAMESPACE, "coins");
	}

	@EventListener
	public void registerRecipe(RecipeRegisterEvent event) {
		if(event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
			CraftingRegistry.addShapedRecipe(new ItemStack(arcadeBlockItem), "WWW", "YGY", "WRW", 'W', Block.PLANKS, 'Y', new ItemStack(Item.DYE, 1, 11), 'G', Block.GLASS, 'R', Item.REDSTONE);
		} else if(event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())) {
			CraftingRegistry.addShapelessRecipe(new ItemStack(coins, 8), Item.GOLD_INGOT, Item.GOLD_INGOT);
			CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLD_INGOT), coins, coins, coins, coins);
		}
	}

	@EventListener
	public void registerBlockEntity(BlockEntityRegisterEvent event) {
		event.register(PacManBlockEntity.class, NAMESPACE.id("arcade_block_entity").toString());
	}

	@EventListener
	public void registerPacket(MessageListenerRegistryEvent event) {
		event.register(NAMESPACE.id("arcade_open"), (player, msg) -> SideUtil.run(OpenScreenPacket::run, () -> {}));
	}
}

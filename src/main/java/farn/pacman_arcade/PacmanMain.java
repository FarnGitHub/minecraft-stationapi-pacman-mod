package farn.pacman_arcade;

import farn.pacman_arcade.block.PacmanArcadeBlock;
import farn.pacman_arcade.block.PacmanArcadeBlockTop;
import farn.pacman_arcade.client.entity.PacManBlockEntityRenderer;
import farn.pacman_arcade.block.entity.PacManBlockEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.achievement.Achievement;
import net.minecraft.achievement.Achievements;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.client.event.block.entity.BlockEntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.item.TemplateSecondaryBlockItem;
import net.modificationstation.stationapi.api.util.Namespace;

public class PacmanMain {
	@Entrypoint.Namespace
	public static Namespace NAMESPACE;

	public static Block pacmanArcadeBottom;
	public static Block pacmanArcadeTop;
	public static Item pacmanItem;
	public static Achievement openPacman;
	public static Material metalMaterial = new Material(MapColor.LIGHT_GRAY);

	@EventListener
	public void registerBlock(BlockRegistryEvent event) {
		pacmanArcadeBottom = (new PacmanArcadeBlock(NAMESPACE.id("pacman_arcade_block_bottom"), 0)).setHardness(0.1F).setTranslationKey(NAMESPACE, "pacman_arcade").disableAutoItemRegistration();
		pacmanArcadeTop = (new PacmanArcadeBlockTop(NAMESPACE.id("pacman_arcade_block_top"), 0)).setHardness(0.1F).setTranslationKey(NAMESPACE, "pacman_arcade").disableAutoItemRegistration();
	}

	@EventListener
	public void registerItem(ItemRegistryEvent event) {
		pacmanItem = (new TemplateSecondaryBlockItem(NAMESPACE.id("arcade_item"), pacmanArcadeBottom)).setTranslationKey(NAMESPACE, "pacman_arcade_item");
		StationAPI.LOGGER.info("Pacman item id: " + NAMESPACE.id("arcade_item"));
	}

	@EventListener
	public void registerAchievement(AchievementRegisterEvent event) {
		event.achievements.add(openPacman = (new Achievement(NAMESPACE.id("pacman_open_arcade").hashCode(), NAMESPACE.id("pacman_open_arcade").toString(), 10, 2, pacmanItem, Achievements.OPEN_INVENTORY)).addStat());
	}

	@EventListener
	public void registerBlockEntity(BlockEntityRegisterEvent event) {
		event.register(PacManBlockEntity.class, NAMESPACE.id("arcade_block_entity").toString());
	}

	@EventListener
	public void registerBlockRenderer(BlockEntityRendererRegisterEvent event) {
		event.renderers.put(PacManBlockEntity.class, new PacManBlockEntityRenderer());
	}
}

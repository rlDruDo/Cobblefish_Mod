package elbrunanzo.cobble.fish;

import elbrunanzo.cobble.fish.mixin.SpawnRestrictionAccessor;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;




public class cobblefish implements ModInitializer {

	public static final String abc = "cobblefish";


	public static final EntityType<cobblefishEntity> COBBLEFISH_ENTITY = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(abc, "cobblefish"),
			FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, cobblefishEntity::new).specificSpawnBlocks(Blocks.WATER).dimensions(EntityDimensions.fixed(0.5f, 0.3f)).build());


	public static final Item cobblefish_item = new Item(new Item.Settings().group(ItemGroup.FOOD).food(MyFoodComponents.cobblestone_fish_item));
	public static final Item stone_fish = new Item(new Item.Settings().group(ItemGroup.FOOD).food(MyFoodComponents.stone_fish));
	public static final Item better_stone_fish = new Item(new Item.Settings().group(ItemGroup.FOOD).food(MyFoodComponents.better_stone_fish));
	public static Item cobblefish_bucket;

	private static final Enchantment swimming_stone = Registry.register(
		Registry.ENCHANTMENT,
		new Identifier(abc, "swimming_stone"),
		new swimmingstoneenchantment());
	@Override
	public void onInitialize() {

		FabricDefaultAttributeRegistry.register(COBBLEFISH_ENTITY, cobblefishEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1D).add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D));


		Registry.register(Registry.ITEM, new Identifier(abc, "cobblefish_item"), cobblefish_item);
		Registry.register(Registry.ITEM, new Identifier(abc, "stone_fish"), stone_fish);
		Registry.register(Registry.ITEM, new Identifier(abc, "better_stone_fish"), better_stone_fish);
		cobblefish_bucket = Registry.register(Registry.ITEM, new Identifier(abc, "cobblefish_bucket"), new FishBucketItem(cobblefish.COBBLEFISH_ENTITY, Fluids.WATER, new Item.Settings().group(ItemGroup.MISC).maxCount(1)));
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.FROZEN_OCEAN,
		BiomeKeys.WARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.DEEP_WARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN), SpawnGroup.WATER_AMBIENT, COBBLEFISH_ENTITY, 20, 4, 10);
		SpawnRestrictionAccessor.callRegister(COBBLEFISH_ENTITY, SpawnRestriction.Location.IN_WATER, Heightmap.Type.OCEAN_FLOOR, cobblefishEntity::canSSpawn);
		// weight might need adjustemnts in the future
	}




	}


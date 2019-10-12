/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.common.data;

import blusunrize.immersiveengineering.common.blocks.EnumMetals;
import blusunrize.immersiveengineering.common.blocks.IEBlocks;
import blusunrize.immersiveengineering.common.blocks.IEBlocks.MetalDecoration;
import blusunrize.immersiveengineering.common.blocks.IEBlocks.Metals;
import blusunrize.immersiveengineering.common.blocks.IEBlocks.StoneDecoration;
import blusunrize.immersiveengineering.common.blocks.IEBlocks.WoodenDecoration;
import blusunrize.immersiveengineering.common.blocks.metal.MetalScaffoldingType;
import blusunrize.immersiveengineering.common.blocks.wooden.TreatedWoodStyles;
import blusunrize.immersiveengineering.common.data.model.ModelFile;
import blusunrize.immersiveengineering.common.data.model.ModelFile.GeneratedModelFile;
import blusunrize.immersiveengineering.common.data.model.ModelGenerator;
import blusunrize.immersiveengineering.common.data.model.ModelHelper;
import blusunrize.immersiveengineering.common.data.model.ModelHelper.BasicStairsShape;
import blusunrize.immersiveengineering.common.items.IEItems;
import blusunrize.immersiveengineering.common.items.IEItems.Ingredients;
import blusunrize.immersiveengineering.common.items.IEItems.Misc;
import blusunrize.immersiveengineering.common.items.IEItems.Tools;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

import static blusunrize.immersiveengineering.common.data.IEDataGenerator.rl;

public class Models extends ModelGenerator
{
	final Map<EnumMetals, MetalModels> metalModels = new HashMap<>();
	final Map<Block, ModelFile> simpleBlocks = new HashMap<>();
	final Map<Block, Map<SlabType, ModelFile>> slabs = new HashMap<>();
	final Map<Block, Map<BasicStairsShape, ModelFile>> stairs = new HashMap<>();
	final Map<MetalScaffoldingType, Map<BasicStairsShape, ModelFile>> aluScaffoldingStairs = new HashMap<>();
	final Map<MetalScaffoldingType, Map<BasicStairsShape, ModelFile>> steelScaffoldingStairs = new HashMap<>();
	final GeneratedModelFile treatedFencePost = ModelHelper.createFencePost(rl("block/wooden_decoration/treated_wood_horizontal"),
			rl("block/wooden_decoration/treated_fence_post"));
	final GeneratedModelFile steelFencePost = ModelHelper.createFencePost(rl("block/metal/storage_steel"),
			rl("block/steel_fence_post"));
	final GeneratedModelFile aluFencePost = ModelHelper.createFencePost(rl("block/metal/storage_aluminum"),
			rl("block/alu_fence_post"));
	final GeneratedModelFile treatedFenceSide = ModelHelper.createFenceSide(rl("block/wooden_decoration/treated_wood_horizontal"),
			rl("block/wooden_decoration/treated_fence_side"));
	final GeneratedModelFile steelFenceSide = ModelHelper.createFenceSide(rl("block/metal/storage_steel"),
			rl("block/steel_fence_side"));
	final GeneratedModelFile aluFenceSide = ModelHelper.createFenceSide(rl("block/metal/storage_aluminum"),
			rl("block/alu_fence_side"));

	public Models(DataGenerator gen)
	{
		super(gen);
		for(EnumMetals m : EnumMetals.values())
			metalModels.put(m, new MetalModels(m));
	}

	@Override
	protected void registerModels(Consumer<GeneratedModelFile> out)
	{
		for(MetalModels mm : metalModels.values())
			mm.register(out);
		out.accept(treatedFencePost);
		out.accept(treatedFenceSide);
		out.accept(steelFencePost);
		out.accept(steelFenceSide);
		out.accept(aluFencePost);
		out.accept(aluFenceSide);

		addSimpleBlockModel(StoneDecoration.cokebrick, rl("block/stone_decoration/cokebrick"), out);
		addSimpleBlockModel(StoneDecoration.blastbrick, rl("block/stone_decoration/blastbrick"), out);
		addSimpleBlockModel(StoneDecoration.blastbrickReinforced, rl("block/stone_decoration/blastbrick_reinforced"), out);
		addSimpleBlockModel(StoneDecoration.coke, rl("block/stone_decoration/coke"), out);
		addSimpleBlockModel(StoneDecoration.concrete, rl("block/stone_decoration/concrete"), out);
		addSimpleBlockModel(StoneDecoration.concreteLeaded, rl("block/stone_decoration/concrete_leaded"), out);
		addSimpleBlockModel(StoneDecoration.concreteTile, rl("block/stone_decoration/concrete_tile"), out);
		addSimpleBlockModel(StoneDecoration.hempcrete, rl("block/stone_decoration/hempcrete"), out);
		addSimpleBlockModel(StoneDecoration.insulatingGlass, rl("block/stone_decoration/insulating_glass"), out);
		addSimpleBlockModel(StoneDecoration.alloybrick, rl("block/stone_decoration/alloybrick"), out);

		for(TreatedWoodStyles style : TreatedWoodStyles.values())
			addSimpleBlockModel(WoodenDecoration.treatedWood.get(style), rl("block/wooden_decoration/treated_wood_"+style.name().toLowerCase(Locale.ENGLISH)), out);
		addScaffoldingModel(WoodenDecoration.treatedScaffolding, rl("block/wooden_decoration/scaffolding"), rl("block/wooden_decoration/scaffolding_top"), out);

		addSimpleBlockModel(MetalDecoration.lvCoil, rl("block/metal_decoration/coil_lv_side"), rl("block/metal_decoration/coil_lv_top"), out);
		addSimpleBlockModel(MetalDecoration.mvCoil, rl("block/metal_decoration/coil_mv_side"), rl("block/metal_decoration/coil_mv_top"), out);
		addSimpleBlockModel(MetalDecoration.hvCoil, rl("block/metal_decoration/coil_hv_side"), rl("block/metal_decoration/coil_hv_top"), out);
		addSimpleBlockModel(MetalDecoration.engineeringRS, rl("block/metal_decoration/redstone_engineering"), out);
		addSimpleBlockModel(MetalDecoration.engineeringHeavy, rl("block/metal_decoration/heavy_engineering"), out);
		addSimpleBlockModel(MetalDecoration.engineeringLight, rl("block/metal_decoration/light_engineering"), out);
		addSimpleBlockModel(MetalDecoration.generator, rl("block/metal_decoration/generator"), out);
		addSimpleBlockModel(MetalDecoration.radiator, rl("block/metal_decoration/radiator"), out);
		ResourceLocation aluSide = rl("block/metal_decoration/aluminum_scaffolding");
		ResourceLocation steelSide = rl("block/metal_decoration/steel_scaffolding");
		for(MetalScaffoldingType type : MetalScaffoldingType.values())
		{
			String suffix = "_"+type.name().toLowerCase(Locale.ENGLISH);
			ResourceLocation aluTop = rl("block/metal_decoration/aluminum_scaffolding_top"+suffix);
			ResourceLocation steelTop = rl("block/metal_decoration/steel_scaffolding_top"+suffix);
			addScaffoldingModel(MetalDecoration.aluScaffolding.get(type), aluSide, aluTop, out);
			addScaffoldingModel(MetalDecoration.steelScaffolding.get(type), steelSide, steelTop, out);
			addSlabModel(MetalDecoration.aluScaffolding.get(type), aluSide, aluTop, aluSide, out);
			addSlabModel(MetalDecoration.steelScaffolding.get(type), steelSide, steelTop, steelSide, out);
			addStairModel(MetalDecoration.aluScaffoldingStair.get(type), "metal_decoration/stairs_alu_scaffolding"+suffix, aluSide, aluTop, aluSide, out);
			addStairModel(MetalDecoration.steelScaffoldingStair.get(type), "metal_decoration/stairs_steel_scaffolding"+suffix, steelSide, steelTop, steelSide, out);

//			Map<BasicStairsShape, ModelFile> aluStairs = new HashMap<>();
//			Map<BasicStairsShape, ModelFile> steelStairs = new HashMap<>();

//			for(BasicStairsShape s : BasicStairsShape.values())
//			{
//
//
//				String stairSuffix = suffix+"_"+s.name().toLowerCase(Locale.ENGLISH);
//				GeneratedModelFile aluModel = ModelHelper.createStairs(s, aluSide, aluTop,
//						aluSide, rl("block/metal_decoration/stairs_alu_scaffolding"+stairSuffix));
//				aluStairs.put(s, aluModel);
//				out.accept(aluModel);
//				GeneratedModelFile steelModel = ModelHelper.createStairs(s, steelSide, steelTop,
//						steelSide, rl("block/metal_decoration/"+stairSuffix));
//				steelStairs.put(s, steelModel);
//				out.accept(steelModel);
//				if(s==BasicStairsShape.STRAIGHT)
//				{
//					out.accept(aluModel.createChild(rl("item/stairs_aluminum_scaffolding"+suffix)));
//					out.accept(steelModel.createChild(rl("item/stairs_steel_scaffolding"+suffix)));
//				}
//			}
//			stairs.put(MetalDecoration.aluScaffoldingStair.get(type), aluStairs);
//			stairs.put(MetalDecoration.steelScaffoldingStair.get(type), steelStairs);
//			aluScaffoldingStairs.put(type, aluStairs);
//			steelScaffoldingStairs.put(type, steelStairs);
		}

		/* SLABS */
		addSlabModel(StoneDecoration.cokebrick, rl("block/stone_decoration/cokebrick"), out);
		addSlabModel(StoneDecoration.blastbrick, rl("block/stone_decoration/blastbrick"), out);
		addSlabModel(StoneDecoration.blastbrickReinforced, rl("block/stone_decoration/blastbrick_reinforced"), out);
		addSlabModel(StoneDecoration.coke, rl("block/stone_decoration/coke"), out);
		addSlabModel(StoneDecoration.concrete, rl("block/stone_decoration/concrete"), out);
		addSlabModel(StoneDecoration.concreteTile, rl("block/stone_decoration/concrete_tile"), out);
		addSlabModel(StoneDecoration.concreteLeaded, rl("block/stone_decoration/concrete_leaded"), out);
		addSlabModel(StoneDecoration.hempcrete, rl("block/stone_decoration/hempcrete"), out);
		addSlabModel(StoneDecoration.insulatingGlass, rl("block/stone_decoration/insulating_glass"), out);
		addSlabModel(StoneDecoration.alloybrick, rl("block/stone_decoration/alloybrick"), out);
		for(TreatedWoodStyles style : TreatedWoodStyles.values())
			addSlabModel(WoodenDecoration.treatedWood.get(style), rl("block/wooden_decoration/treated_wood_"+style.name().toLowerCase(Locale.ENGLISH)), out);

		/* STAIRS */
		addStairModel(StoneDecoration.hempcreteStairs, "stone_decoration/stairs_hempcrete", rl("block/stone_decoration/hempcrete"), out);
		addStairModel(StoneDecoration.concreteStairs[0], "stone_decoration/stairs_concrete", rl("block/stone_decoration/concrete"), out);
		addStairModel(StoneDecoration.concreteStairs[1], "stone_decoration/stairs_concrete_tile", rl("block/stone_decoration/concrete_tile"), out);
		addStairModel(StoneDecoration.concreteStairs[2], "stone_decoration/stairs_concrete_leaded", rl("block/stone_decoration/concrete_leaded"), out);
		for(TreatedWoodStyles style : TreatedWoodStyles.values())
			addStairModel(WoodenDecoration.treatedStairs.get(style), "wooden_decoration/stairs_treated_wood_"+style.name().toLowerCase(Locale.ENGLISH), rl("block/wooden_decoration/treated_wood_"+style.name().toLowerCase(Locale.ENGLISH)), out);

		/* ITEMS */
		addItemModels("metal_", out, IEItems.Metals.ingots.values().toArray(new Item[IEItems.Metals.ingots.size()]));
		addItemModels("metal_", out, IEItems.Metals.nuggets.values().toArray(new Item[IEItems.Metals.ingots.size()]));
		addItemModels("metal_", out, IEItems.Metals.dusts.values().toArray(new Item[IEItems.Metals.ingots.size()]));
		addItemModels("metal_", out, IEItems.Metals.plates.values().toArray(new Item[IEItems.Metals.ingots.size()]));

		addItemModels("material_", out, Ingredients.stickTreated, Ingredients.stickIron, Ingredients.stickSteel, Ingredients.stickAluminum,
				Ingredients.hempFiber, Ingredients.hempFabric, Ingredients.coalCoke, Ingredients.slag,
				Ingredients.componentIron, Ingredients.componentSteel, Ingredients.waterwheelSegment, Ingredients.windmillBlade, Ingredients.windmillSail,
				Ingredients.woodenGrip, Ingredients.gunpartBarrel, Ingredients.gunpartDrum, Ingredients.gunpartHammer,
				Ingredients.dustCoke, Ingredients.dustHopGraphite, Ingredients.ingotHopGraphite,
				Ingredients.wireCopper, Ingredients.wireElectrum, Ingredients.wireAluminum, Ingredients.wireSteel,
				Ingredients.dustSaltpeter, Ingredients.dustSulfur, Ingredients.electronTube, Ingredients.circuitBoard);

		addItemModels("tool_", out, Tools.hammer, Tools.wirecutter, Tools.manual, Tools.steelPick, Tools.steelShovel, Tools.steelAxe, Tools.steelSword);
		addItemModels("wirecoil_", out, Misc.wireCoils.values().toArray(new Item[Misc.wireCoils.size()]));
//		NYI:
//		addItemModels("toolupgrade_", out, Misc.toolUpgrades.values().toArray(new Item[Misc.toolUpgrades.size()]));
//		addItemModels("mold_", out, Molds.moldPlate, Molds.moldGear, Molds.moldRod, Molds.moldBulletCasing, Molds.moldWire, Molds.moldPacking4, Molds.moldPacking9, Molds.moldUnpacking);
		addItemModels("bullet_", out, Ingredients.emptyCasing, Ingredients.emptyShell);

	}

	private void addScaffoldingModel(Block block, ResourceLocation side, ResourceLocation top, Consumer<GeneratedModelFile> out)
	{
		addSimpleBlockModel(block, ModelHelper.createScaffolding(side, top, block.getRegistryName()), out);
	}

	private void addSlabModel(Block block, ResourceLocation texture, Consumer<GeneratedModelFile> out)
	{
		addSlabModel(block, texture, texture, texture, out);
	}

	private void addSlabModel(Block block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, Consumer<GeneratedModelFile> out)
	{
		Map<SlabType, ModelFile> map = new HashMap<>();
		GeneratedModelFile blockModel = (GeneratedModelFile)simpleBlocks.get(block);
		String defaultPath = blockModel.getUncheckedLocation().getPath();
		GeneratedModelFile bottomModel = ModelHelper.createSlab(SlabType.BOTTOM, side, top, bottom, rl(defaultPath+"_slab"));
		GeneratedModelFile topModel = ModelHelper.createSlab(SlabType.TOP, side, top, bottom, rl(defaultPath+"_slab_top"));
		out.accept(topModel);
		out.accept(bottomModel);
		out.accept(bottomModel.createChild(locForItemModel(Item.getItemFromBlock(IEBlocks.toSlab.get(block)))));
		map.put(SlabType.TOP, topModel);
		map.put(SlabType.BOTTOM, bottomModel);
		map.put(SlabType.DOUBLE, blockModel);
		slabs.put(IEBlocks.toSlab.get(block), map);
	}

	private void addStairModel(Block stairBlock, String path, ResourceLocation texture, Consumer<GeneratedModelFile> out)
	{
		addStairModel(stairBlock, path, texture, texture, texture, out);
	}

	private void addStairModel(Block stairBlock, String path, ResourceLocation side, ResourceLocation top, ResourceLocation bottom, Consumer<GeneratedModelFile> out)
	{
		Map<BasicStairsShape, ModelFile> map = new HashMap<>();
		for(BasicStairsShape s : BasicStairsShape.values())
		{
			String stairSuffix = "_"+s.name().toLowerCase(Locale.ENGLISH);
			GeneratedModelFile model = ModelHelper.createStairs(s, side, top, bottom, rl("block/"+path+stairSuffix));
			map.put(s, model);
			out.accept(model);
			if(s==BasicStairsShape.STRAIGHT)
				out.accept(model.createChild(rl("item/"+path.substring(path.lastIndexOf("/")+1))));
		}
		stairs.put(stairBlock, map);
	}

	private void addSimpleBlockModel(Block b, ResourceLocation side, ResourceLocation topAndBottom,
									 Consumer<GeneratedModelFile> out)
	{
		addSimpleBlockModel(b, side, topAndBottom, topAndBottom, out);
	}

	private void addSimpleBlockModel(Block b, ResourceLocation side, ResourceLocation top, ResourceLocation bottom,
									 Consumer<GeneratedModelFile> out)
	{
		GeneratedModelFile model = ModelHelper.createBasicCube(side, top, bottom, b.getRegistryName());
		addSimpleBlockModel(b, model, out);
	}

	private void addSimpleBlockModel(Block b, ResourceLocation texture, Consumer<GeneratedModelFile> out)
	{
		GeneratedModelFile model = ModelHelper.createBasicCube(texture);
		addSimpleBlockModel(b, model, out);
	}

	private void addSimpleBlockModel(Block b, GeneratedModelFile model, Consumer<GeneratedModelFile> out)
	{
		out.accept(model);
//		out.accept(model.withLoc(locForItemModel(Item.getItemFromBlock(b))));
		out.accept(model.createChild(locForItemModel(Item.getItemFromBlock(b))));
		Preconditions.checkState(simpleBlocks.put(b, model)==null);
	}

	private void addItemModels(String texturePrefix, Consumer<GeneratedModelFile> out, Item... items)
	{
		for(Item item : items)
		{
			ResourceLocation path = locForItemModel(item);
			ResourceLocation texture = texturePrefix==null?path: new ResourceLocation(path.getNamespace(), "item/"+texturePrefix+item.getRegistryName().getPath());
			out.accept(ModelHelper.createBasicItem(texture, path));
		}
	}

	private static ResourceLocation locForItemModel(Item item)
	{
		ResourceLocation itemName = item.getRegistryName();
		return new ResourceLocation(itemName.getNamespace(), "item/"+itemName.getPath());
	}

	public static class MetalModels
	{
		EnumMetals metal;
		GeneratedModelFile ore;
		GeneratedModelFile storage;
		GeneratedModelFile sheetmetal;

		public MetalModels(EnumMetals metal)
		{
			this.metal = metal;
			String name = metal.tagName();
			if(metal.shouldAddOre())
				ore = ModelHelper.createBasicCube(rl("block/metal/ore_"+name));
			if(!metal.isVanillaMetal())
			{
				ResourceLocation defaultName = rl("block/metal/storage_"+name);
				if(metal==EnumMetals.URANIUM)
				{
					ResourceLocation side = rl("block/metal/storage_"+name+"_side");
					ResourceLocation top = rl("block/metal/storage_"+name+"_top");
					storage = ModelHelper.createBasicCube(side, top, top, defaultName);
				}
				else
				{
					storage = ModelHelper.createBasicCube(defaultName);
				}
			}
			ResourceLocation sheetmetalName = rl("block/metal/sheetmetal_"+name);
			sheetmetal = ModelHelper.createBasicCube(sheetmetalName);
		}

		void register(Consumer<GeneratedModelFile> out)
		{
			if(ore!=null)
			{
				out.accept(ore);
				out.accept(ore.createChild(locForItemModel(Item.getItemFromBlock(Metals.ores.get(metal)))));
			}
			if(storage!=null)
			{
				out.accept(storage);
				out.accept(storage.createChild(locForItemModel(Item.getItemFromBlock(Metals.storage.get(metal)))));
			}
			out.accept(sheetmetal);
			out.accept(sheetmetal.createChild(locForItemModel(Item.getItemFromBlock(Metals.sheetmetal.get(metal)))));
		}
	}
}
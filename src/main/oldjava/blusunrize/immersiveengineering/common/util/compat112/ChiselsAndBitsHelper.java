/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.common.util.compat112;

import blusunrize.immersiveengineering.api.IEProperties;
import blusunrize.immersiveengineering.common.IEContent;
import com.google.common.base.Function;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import javax.annotation.Nullable;
import java.util.List;

public class ChiselsAndBitsHelper extends IECompatModule implements Function<List, Boolean>
{
	@Override
	public void preInit()
	{
		FMLInterModComms.sendFunctionMessage("chiselsandbits", "forcestatecompatibility", this.getClass().getName());
	}

	@Override
	public void registerRecipes()
	{
	}

	@Override
	public void init()
	{
	}

	@Override
	public void postInit()
	{
	}

	@Nullable
	@Override
	public Boolean apply(@Nullable List list)
	{
		list.add(IEContent.blockMetalDevice0.getStateFromMeta(BlockTypes_MetalDevice0.CAPACITOR_LV.getMeta()));
		list.add(IEContent.blockMetalDevice0.getStateFromMeta(BlockTypes_MetalDevice0.CAPACITOR_MV.getMeta()));
		list.add(IEContent.blockMetalDevice0.getStateFromMeta(BlockTypes_MetalDevice0.CAPACITOR_HV.getMeta()));
		list.add(IEContent.blockMetalDevice0.getStateFromMeta(BlockTypes_MetalDevice0.CAPACITOR_CREATIVE.getMeta()));
		list.add(IEContent.blockMetalDevice0.getStateFromMeta(BlockTypes_MetalDevice0.BARREL.getMeta()));
		for(Direction f : Direction.values())
		{
			list.add(IEContent.blockMetalDevice1.getStateFromMeta(BlockTypes_MetalDevice1.FURNACE_HEATER.getMeta()).with(IEProperties.FACING_ALL, f));
			if(f.getAxis()!=Axis.Y)
				list.add(IEContent.blockMetalDevice1.getStateFromMeta(BlockTypes_MetalDevice1.DYNAMO.getMeta()).with(IEProperties.FACING_ALL, f));
//			if(f==EnumFacing.NORTH)
//				list.add(IEContent.blockMetalDevice1.getStateFromMeta(BlockTypes_MetalDevice1.THERMOELECTRIC_GEN.getMeta()).with(IEProperties.FACING_ALL,f));
		}
		list.add(IEContent.blockMetalDevice1.getStateFromMeta(BlockTypes_MetalDevice1.THERMOELECTRIC_GEN.getMeta()).with(IEProperties.FACING_ALL, Direction.UP));
		list.add(IEContent.blockMetalDevice1.getStateFromMeta(BlockTypes_MetalDevice1.THERMOELECTRIC_GEN.getMeta()).with(IEProperties.FACING_ALL, Direction.DOWN));
		list.add(IEContent.blockMetalDevice1.getStateFromMeta(BlockTypes_MetalDevice1.THERMOELECTRIC_GEN.getMeta()).with(IEProperties.FACING_ALL, Direction.NORTH));


		list.add(IEContent.blockWoodenDevice0.getStateFromMeta(BlockTypes_WoodenDevice0.CRATE.getMeta()));
		list.add(IEContent.blockWoodenDevice0.getStateFromMeta(BlockTypes_WoodenDevice0.BARREL.getMeta()));
		list.add(IEContent.blockWoodenDevice0.getStateFromMeta(BlockTypes_WoodenDevice0.SORTER.getMeta()));
		list.add(IEContent.blockWoodenDevice0.getStateFromMeta(BlockTypes_WoodenDevice0.GUNPOWDER_BARREL.getMeta()));
		list.add(IEContent.blockWoodenDevice0.getStateFromMeta(BlockTypes_WoodenDevice0.REINFORCED_CRATE.getMeta()));
		return true;
	}
}
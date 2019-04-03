package com.psychokiller.common.tileentity;

import com.psychokiller.revivalcore.RevivalCore;

public interface ICraftSystem
{
	default void slotChanged(TileEntitySH tileEntity)
	{
		RevivalCore.logger.info("slotChanged method got called from {}", tileEntity);
	}
}

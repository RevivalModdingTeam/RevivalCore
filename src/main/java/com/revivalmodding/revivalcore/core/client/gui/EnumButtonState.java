package com.revivalmodding.revivalcore.core.client.gui;

public enum EnumButtonState {
	
	AVAILABLE(0xFFFFFF),
	READY_TO_REMOVE(0xFFFFFF),
	ACTIVE(0xFFFFFF),
	PURCHASABLE(0xFFFFFF),
	INACTIVE(0xFFFFFF),
	PURCHASABLE_HOVERED(0xFFFFFF);
	
	public final int color;
	
	private EnumButtonState(int color) {
		this.color = color;
	}
}

package com.revivalmodding.revivalcore.core.client.gui;

public enum EnumButtonState {
	
	/** When ready to activate **/
	AVAILABLE(0xFFFFFF),
	/** Ability can be removed from active abilities **/
	READY_TO_REMOVE(0xFFFFFF),
	/** Ability is active already, but unhovered **/
	ACTIVE(0xFFFFFF),
	/** Ability is ready to be purchased, unhovered **/
	PURCHASABLE(0xFFFFFF),
	/** Player cannot afford purchase of this ability **/
	INACTIVE(0xFFFFFF),
	/** Ability is ready to be purchased **/
	PURCHASABLE_HOVERED(0xFFFFFF),
	/**  **/
	READY_TO_ACTIVATE(0xFFFFFF);
	
	public final int color;
	
	private EnumButtonState(int color) {
		this.color = color;
	}
}

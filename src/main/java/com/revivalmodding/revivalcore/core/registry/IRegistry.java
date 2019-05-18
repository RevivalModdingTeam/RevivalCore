package com.revivalmodding.revivalcore.core.registry;

import java.util.Collection;
import java.util.HashSet;

import javax.annotation.Nonnull;

public interface IRegistry<T extends IRegistryEntry>
{
	@Nonnull
	HashSet<T> getRegistry();
	
	void register(T toRegister);
	
	void registerAll(T[] toRegister);
	
	void registerAll(Collection<T> toRegister);
	
	boolean containsObject(T object);
}

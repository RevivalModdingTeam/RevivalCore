package com.revivalmodding.revivalcore.core.abilities;

import com.google.common.base.Preconditions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Class which handles simple ability creation
 *
 * Created by Toma, 8.1.2020
 */
public class AbilityBuilder {

    /** Registry name is for registry, obviously; Display name is name in GUI*/
    protected String registryName, displayName;
    /** What you see when you hover over this ability in GUI */
    @Nullable
    protected String[] hover;
    /** What happens on keybind press */
    protected Consumer<AbilityUseContex> useAction;
    /** Actions based on ability manipulation*/
    protected Consumer<EntityPlayer> activateAction = p -> {}, deactivateAction = p -> {}, tickUpdate = p -> {};
    /** If player can actually enable this ability, could be useful for abilities depending on each other */
    protected Predicate<EntityPlayer> activateValidator = p -> true;
    /** Amount of levels required to unlock this ability */
    protected int price;
    /** .png image for display */
    protected ResourceLocation iconLocation;
    /** Cooldown left on ability (ticks) */
    protected int cooldown;

    /** Private constructor, use the .create() function */
    private AbilityBuilder() {
    }

    /** Creates new AbilityBuilder instance */
    public static AbilityBuilder create() {
        return new AbilityBuilder();
    }

    /** REQUIRED: Set registry and display name */
    public AbilityBuilder name(final String registryName, final String fullDisplayName) {
        this.registryName = registryName;
        this.displayName = fullDisplayName;
        return this;
    }

    /** REQUIRED: What happens when keybind is activated */
    public AbilityBuilder onUse(final Consumer<AbilityUseContex> useAction) {
        this.useAction = useAction;
        return this;
    }

    /** OPTIONAL: When ability is selected as active */
    public AbilityBuilder onActivate(final Consumer<EntityPlayer> activateAction) {
        this.activateAction = activateAction;
        return this;
    }

    /** OPTIONAL: When ability is disabled from active selection */
    public AbilityBuilder onDeactivate(final Consumer<EntityPlayer> deactivateAction) {
        this.deactivateAction = deactivateAction;
        return this;
    }

    /** OPTIONAL: Called every tick */
    public AbilityBuilder onTick(final Consumer<EntityPlayer> tickUpdate) {
        this.tickUpdate = tickUpdate;
        return this;
    }

    /** OPTIONAL: When something else is required to activate the ability */
    public AbilityBuilder canActivate(final Predicate<EntityPlayer> predicate) {
        this.activateValidator = predicate;
        return this;
    }

    /** OPTIONAL: Unlock price, default = 0*/
    public AbilityBuilder price(final int priceInLevels) {
        this.price = priceInLevels;
        return this;
    }

    /** REQUIRED: Icon for GUI */
    public AbilityBuilder guiIcon(final ResourceLocation location) {
        this.iconLocation = location;
        return this;
    }

    /** OPTIONAL: Sets max cooldown for ability */
    public AbilityBuilder cooldown(final int cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    /** Creates new Ability object */
    public Ability build() {
        checkNotNullAndEmpty(registryName);
        checkNotNullAndEmpty(displayName);
        Preconditions.checkNotNull(activateAction, "Action cannot be null!");
        Preconditions.checkNotNull(deactivateAction, "Action cannot be null!");
        Preconditions.checkNotNull(tickUpdate, "Action cannot be null!");
        Preconditions.checkNotNull(useAction, "Action cannot be null!");
        Preconditions.checkState(price >= 0, "Ability price cannot be negative number!");
        Preconditions.checkNotNull(iconLocation, "Every ability has to have it's own icon!");
        return new Ability(this);
    }

    protected static void checkNotNullAndEmpty(String s) {
        Preconditions.checkNotNull(s, "String cannot be null!");
        Preconditions.checkState(!s.isEmpty(), "String cannot be empty!");
    }
}

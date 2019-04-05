package com.revivalcore.superpowerbase.suitsets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.List;

import com.revivalcore.superpowerbase.abilities.PowerBase;
import com.revivalcore.superpowerbase.abilities.suppliers.IIPowerProvider;
import com.RevivalCore.superpowerbase.effects.Effect;
import com.RevivalCore.superpowerbase.effects.EffectVibrating;
import com.RevivalCore.superpowerbase.models.ModelBipedSuitSet;
import com.RevivalCore.util.creativetabs.CreativeTabRegistry;
import com.RevivalCore.util.helper.StringHelper;
import com.RevivalCore.util.render.ModelCache;

public class SuitSet extends IForgeRegistryEntry.Impl<SuitSet> implements IIPowerProvider {

    public static final RegistryNamespaced<ResourceLocation, SuitSet> REGISTRY = new RegistryNamespaced<>();

    private String name;
    protected Item helmet;
    protected Item chestplate;
    protected Item legs;
    protected Item boots;

    public SuitSet(String name) {
        this.name = name;
    }

    public void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(helmet = createItem(this, EntityEquipmentSlot.HEAD));
        e.getRegistry().register(chestplate = createItem(this, EntityEquipmentSlot.CHEST));
        e.getRegistry().register(legs = createItem(this, EntityEquipmentSlot.LEGS));
        e.getRegistry().register(boots = createItem(this, EntityEquipmentSlot.FEET));
    }

    public ItemSuitSetArmor createItem(SuitSet suitSet, EntityEquipmentSlot slot) {
        return (ItemSuitSetArmor) new ItemSuitSetArmor(suitSet, slot).setRegistryName(suitSet.getRegistryName().getNamespace(), suitSet.getRegistryName().getPath() + (slot == EntityEquipmentSlot.HEAD ? "_helmet" : slot == EntityEquipmentSlot.CHEST ? "_chest" : slot == EntityEquipmentSlot.LEGS ? "_legs" : "_boots"));
    }

    @SideOnly(Side.CLIENT)
    public void registerModels() {
        registerModel(getHelmet(), EntityEquipmentSlot.HEAD);
        registerModel(getChestplate(), EntityEquipmentSlot.CHEST);
        registerModel(getLegs(), EntityEquipmentSlot.LEGS);
        registerModel(getBoots(), EntityEquipmentSlot.FEET);
    }

    @SideOnly(Side.CLIENT)
    public void registerModel(Item item, EntityEquipmentSlot slot) {
        if (item != null)
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(this.getRegistryName().getNamespace(), this.getRegistryName().getPath() + "_suit"), slot.toString().toLowerCase()));
    }

    public String getUnlocalizedName() {
        return name;
    }

    public boolean canOpenArmor(EntityEquipmentSlot slot) {
        return false;
    }

    public void onArmorToggled(Entity entity, ItemStack stack, EntityEquipmentSlot slot, boolean open) {
        if (entity instanceof EntityPlayerMP)
            ((EntityPlayerMP) entity).connection.sendPacket(new SPacketCustomSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON.getRegistryName().toString(), entity.getSoundCategory(), entity.posX, entity.posY, entity.posZ, 1F, 1F));
    }

    public String getDisplayName() {
        return StringHelper.translateToLocal(getModId().toLowerCase() + ".suit." + getUnlocalizedName() + ".name");
    }

    @Deprecated
    public String getModId() {
        return this.getRegistryName().getNamespace();
    }

    public String getDisplayNameForItem(Item item, ItemStack stack, EntityEquipmentSlot armorType, String origName) {
        return origName;
    }

    public String getArmorTexturePath(ItemStack stack, Entity entity, EntityEquipmentSlot slot, boolean light, boolean smallArms, boolean open) {
        String tex = slot == EntityEquipmentSlot.HEAD ? "helmet" : slot == EntityEquipmentSlot.CHEST ? "chestplate" : slot == EntityEquipmentSlot.LEGS ? "legs" : "boots";

        if (slot == EntityEquipmentSlot.CHEST && smallArms)
            tex = tex + "_smallarms";
        if (this.canOpenArmor(slot) && open)
            tex = tex + "_open";
        if (light)
            tex = tex + "_lights";

        return getModId() + ":textures/models/armor/" + this.getRegistryName().getPath() + "/" + tex + ".png";
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(SuitSet suitSet, ItemStack stack, Entity entity, EntityEquipmentSlot slot, boolean light, boolean smallArms, boolean open) {
        String key = suitSet.getRegistryName().toString() + "_" + suitSet.getArmorModelScale(slot) + "_" + suitSet.getArmorTexturePath(stack, entity, slot, false, smallArms, open) + "_" + suitSet.getArmorTexturePath(stack, entity, slot, true, smallArms, open) + "_" + slot.toString() + "_" + smallArms + "_" + EffectVibrating.isVibrating(entity);
        ModelBase model = ModelCache.getModel(key);
        if (model != null && model instanceof ModelBiped)
            return (ModelBiped) model;
        else
            return (ModelBiped) ModelCache.storeModel(key, new ModelBipedSuitSet(suitSet.getArmorModelScale(slot), suitSet.getArmorTexturePath(stack, entity, slot, false, smallArms, open), suitSet.getArmorTexturePath(stack, entity, slot, true, smallArms, open), suitSet, slot, smallArms, EffectVibrating.isVibrating(entity)));
    }

    @SideOnly(Side.CLIENT)
    public void bindArmorTexture(SuitSet suitSet, Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ResourceLocation normalTex, ResourceLocation glowTex, boolean glow, EntityEquipmentSlot slot, boolean smallArms) {
        Minecraft.getMinecraft().renderEngine.bindTexture(glow ? glowTex : normalTex);
    }

    public ArmorMaterial getArmorMaterial(EntityEquipmentSlot slot) {
        return ArmorMaterial.IRON;
    }

    public boolean hasGlowyThings(EntityLivingBase entity, EntityEquipmentSlot slot) {
        return false;
    }

    public float getGlowOpacity(SuitSet suitSet, EntityLivingBase entity, EntityEquipmentSlot slot) {
        return 1F;
    }

    public boolean showInCreativeTab() {
        return getCreativeTab() != null;
    }

    public CreativeTabs getCreativeTab() {
        return CreativeTabRegistry.getOrCreateCreativeTab("Suits", getRepresentativeItem());
    }

    public boolean hasArmorOn(EntityLivingBase entity) {
        boolean hasArmorOn = true;

        if (getHelmet() != null && (entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() || entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() != getHelmet()))
            hasArmorOn = false;

        if (getLegs() != null && (entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty() || entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() != getLegs()))
            hasArmorOn = false;

        if (getBoots() != null && (entity.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty() || entity.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() != getBoots()))
            hasArmorOn = false;

        return hasArmorOn;
    }

    public Item getHelmet() {
        return helmet;
    }

    public Item getChestplate() {
        return chestplate;
    }

    public Item getLegs() {
        return legs;
    }

    public Item getBoots() {
        return boots;
    }

    public ItemStack getRepresentativeItem() {
        return new ItemStack(this.getChestplate());
    }

    public List<Effect> getEffects() {
        return null;
    }

    public NBTTagCompound getData() {
        return null;
    }

    public float getArmorModelScale(EntityEquipmentSlot armorSlot) {
        if (armorSlot == EntityEquipmentSlot.HEAD)
            return 0.5F;
        else if (armorSlot == EntityEquipmentSlot.CHEST || armorSlot == EntityEquipmentSlot.FEET)
            return 0.252F;
        return 0.25F;
    }

    public boolean hasExtraDescription(ItemStack stack) {
        return getExtraDescription(stack) != null && getExtraDescription(stack).size() > 0;
    }

    public List<String> getExtraDescription(ItemStack stack) {
        return null;
    }

    public static SuitSet getSuitSet(EntityLivingBase entity) {
        return hasSuitSetOn(entity) ? getSuitSet(entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST)) : null;
    }

    @Override
    public PowerBase.AbilityMap addDefaultAbilities(EntityLivingBase entity, PowerBase.AbilityMap abilities, PowerBase.EnumAbilityContext context) {
        return abilities;
    }

    public void onEquip(SuitSet suitSet, EntityLivingBase player) {

    }

    public void onUnequip(SuitSet suitSet, EntityLivingBase player) {

    }

    public void onUpdate(SuitSet suitSet, EntityLivingBase player) {

    }

    public static SuitSet getSuitSet(ItemStack stack) {
        if (stack.getItem() != null && stack.getItem() instanceof ItemSuitSetArmor) {
            return ((ItemSuitSetArmor) stack.getItem()).getSuitSet();
        }

        return null;
    }

    public static boolean hasSuitSetOn(EntityLivingBase entity) {
        if (!entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty() && entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemSuitSetArmor) {
            return ((ItemSuitSetArmor) entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem()).getSuitSet().hasArmorOn(entity);
        }

        return false;
    }

    public ItemStack getRepairItem(ItemStack toRepair) {
        return ItemStack.EMPTY;
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if (!getRepairItem(toRepair).isEmpty())
            return getRepairItem(toRepair).getItem() == repair.getItem() && getRepairItem(toRepair).getItemDamage() == repair.getItemDamage();

        return false;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

    }
}

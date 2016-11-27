package me.kev3200.itemp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class blockListener implements Listener {
	private Material[] tools_list = {
    Material.DIAMOND_SWORD, Material.DIAMOND_PICKAXE, 
    Material.DIAMOND_AXE, 
    Material.DIAMOND_SPADE, 
    Material.DIAMOND_HOE, 
    Material.IRON_SWORD, 
    Material.IRON_PICKAXE, 
    Material.IRON_AXE, 
    Material.IRON_SPADE, 
    Material.IRON_HOE, 
    Material.GOLD_SWORD, 
    Material.GOLD_PICKAXE, 
    Material.GOLD_AXE, 
    Material.GOLD_SPADE, 
    Material.GOLD_HOE, 
    Material.STONE_SWORD, 
    Material.STONE_PICKAXE, 
    Material.STONE_AXE, 
    Material.STONE_SPADE, 
    Material.STONE_HOE, 
    Material.WOOD_SWORD, 
    Material.WOOD_PICKAXE, 
    Material.WOOD_AXE, 
    Material.WOOD_SPADE, 
    Material.WOOD_HOE };
	private Material[] hoe_list = { 
	Material.DIAMOND_HOE, 
    Material.IRON_HOE, 
    Material.GOLD_HOE, 
    Material.STONE_HOE, 
    Material.WOOD_HOE };
	private Material[] armor_list = { 
	Material.LEATHER_HELMET, 
    Material.LEATHER_CHESTPLATE, 
    Material.LEATHER_LEGGINGS, 
    Material.LEATHER_BOOTS, 
    Material.IRON_HELMET, 
    Material.IRON_CHESTPLATE, 
    Material.IRON_LEGGINGS, 
    Material.IRON_BOOTS, 
    Material.GOLD_HELMET, 
    Material.GOLD_CHESTPLATE, 
    Material.GOLD_LEGGINGS, 
    Material.GOLD_BOOTS, 
    Material.DIAMOND_HELMET, 
    Material.DIAMOND_CHESTPLATE, 
    Material.DIAMOND_LEGGINGS, 
    Material.DIAMOND_BOOTS };
	String genericMessage = "You don't have permission to ";
	String noUseMessage = genericMessage + "use that!";
	String noPlaceMessage = genericMessage + "place that!";
	String noBreakMessage = genericMessage + "break that!";
	String noAttackMessage = genericMessage + "attack";
	String noArmorMessage = genericMessage + "wear that type of armor!";
  
	@EventHandler
	public void wandClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Material item = p.getItemInHand().getType();
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (item == Material.GOLD_AXE) && (p.hasPermission("itemp.admin"))) {
			Material block = e.getClickedBlock().getType();
			int id = e.getClickedBlock().getTypeId();
			int type = e.getClickedBlock().getData();
			p.sendMessage("Block Name: " + block + " \nID: " + id + " \nType: " + type);
		}
	}
  
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Material block = e.getBlock().getType();
		int id = e.getBlock().getTypeId();
		int type = e.getBlock().getData();
		Player p = e.getPlayer();
    
		String blockPermWithoutData = "itemp.place." + block;
		String blockPermWithData = "itemp.place." + block + ":" + type;
		String idPermWithData = "itemp.place." + id + ":" + type;
		String idPermWithoutData = "itemp.place." + id;
		if ((!p.hasPermission(blockPermWithData)) && (!p.hasPermission(blockPermWithoutData)) &&  (!p.hasPermission("itemp.admin")) &&  (!p.hasPermission(idPermWithData)) && (!p.hasPermission(idPermWithoutData))) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.DARK_RED + "[IP]" + ChatColor.RED + noPlaceMessage);
		}
	}
  
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Material block = e.getBlock().getType();
		int id = e.getBlock().getTypeId();
		int type = e.getBlock().getData() % 4;
		Player p = e.getPlayer();
    
		String blockPermWithoutData = "itemp.break." + block;
		String blockPermWithData = "itemp.break." + block + ":" + type;
		String idPermWithData = "itemp.break." + id + ":" + type;
		String idPermWithoutData = "itemp.break." + id;
		if ((!p.hasPermission(blockPermWithData)) && (!p.hasPermission(blockPermWithoutData)) && (!p.hasPermission("itemp.admin")) &&  (!p.hasPermission(idPermWithData)) && (!p.hasPermission(idPermWithoutData))) {
			e.setCancelled(true);
		}
	}
  
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if ((e.getDamager() instanceof Player)) {
			Player p = (Player)e.getDamager();
			if ((e.getEntity() instanceof Player)) {
				if ((!p.hasPermission("itemp.attack.player")) && (!p.hasPermission("itemp.admin"))) {
					e.setCancelled(true);
				}
			}
			else {
				String mob = e.getEntity().getType().getName();
				if ((!p.hasPermission("itemp.attack." + mob)) && (!p.hasPermission("itemp.admin"))) {
					e.setCancelled(true);
				}
			}
		}
	}
  
	@EventHandler
	public void onBedUse(PlayerBedEnterEvent e) {
		Block block = e.getBed();
		int type = block.getTypeId();
		Player p = e.getPlayer();
    
		String blockPerm = "itemp.use." + block;
		String idPerm = "itemp.use." + type;
		if ((!p.hasPermission(blockPerm)) && (!p.hasPermission(idPerm)) && (!p.hasPermission("itemp.admin"))) {
			e.setCancelled(true);
		}
	}
  
	@EventHandler
	public void onToolUse(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Material item = p.getItemInHand().getType();
		int id = p.getItemInHand().getTypeId();
    
		String toolPerm = "itemp.use." + item;
		String toolidPerm = "itemp.use." + id;
		String armorPerm = "itemp.armor." + item;
		String armoridPerm = "itemp.armor." + id;
		Material[] arrayOfMaterial;
		int j;
		int i;
		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			j = (arrayOfMaterial = tools_list).length;
			for (i = 0; i < j; i++) {
				Material tool = arrayOfMaterial[i];
				if ((tool == item) && (!p.hasPermission(toolPerm)) && (!p.hasPermission(toolidPerm)) && (!p.hasPermission("itemp.admin"))) {
					e.setCancelled(true);
				}
			}
		}
		else if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			if ((item == Material.BOW) && (!p.hasPermission(toolPerm)) && (!p.hasPermission(toolidPerm)) && (!p.hasPermission("itemp.admin"))) {
				e.setCancelled(true);
				p.updateInventory();
			}
			j = (arrayOfMaterial = armor_list).length;
			for (i = 0; i < j; i++) {
				Material armor = arrayOfMaterial[i];
				if ((armor == item) && (!p.hasPermission(armorPerm)) && (!p.hasPermission(armoridPerm)) && (!p.hasPermission("itemp.admin"))) {
					e.setCancelled(true);
					p.updateInventory();
				}
			}
		}	
		else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			j = (arrayOfMaterial = hoe_list).length;
			for (i = 0; i < j; i++) {
				Material tool = arrayOfMaterial[i];
				if ((tool == item) && (!p.hasPermission(toolPerm)) && (!p.hasPermission(toolidPerm)) && (!p.hasPermission("itemp.admin"))) {
					e.setCancelled(true);
				}
			}
		}
	}
  
	@EventHandler
	public void onArmorChange(InventoryClickEvent e) {
	
		if(e.getCurrentItem() == null) {
			return;
		}
		
		Player p = (Player)e.getWhoClicked();
		Material[] arrayOfMaterial;
		int j = (arrayOfMaterial = armor_list).length;
		for (int i = 0; i < j; i++) {
			Material armor = arrayOfMaterial[i];
			String armorPerm = "itemp.armor." + e.getCurrentItem().getType();
			String armoridPerm = "itemp.armor." + e.getCurrentItem().getTypeId();
			if (e.isShiftClick()) {
				if ((e.getCurrentItem().getType().equals(armor)) && (!p.hasPermission(armorPerm)) && (!p.hasPermission(armoridPerm)) && (!p.hasPermission("itemp.admin"))) {
					e.setCancelled(true);
				}
			}
			else if ((e.isLeftClick()) && (e.getSlotType().equals(InventoryType.SlotType.ARMOR)) && (e.getCursor().equals(new ItemStack(armor))) && (!p.hasPermission(armorPerm)) && (!p.hasPermission(armoridPerm)) && (!p.hasPermission("itemp.admin"))) {
				e.setCancelled(true);
			}
	    }
	}
}

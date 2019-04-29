package com.q44920040.minecraft.ChuckBorderCheck;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    private String BorderBlock;
    @Override
    public void onEnable() {
        reloadPlugin();
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }

    @EventHandler
    public void PlayerPlaceBlock(BlockPlaceEvent event){
        Block blockPlaced = event.getBlockPlaced();
        int MainId = blockPlaced.getTypeId();
        int OrderId = blockPlaced.getData();
        String NewId = MainId+";"+OrderId;
        int x = blockPlaced.getX();
        int z = blockPlaced.getZ();
        System.out.println("ID"+NewId);
        System.out.println("此物品");
    }

    private Boolean CalculateBhuckBorder(int x,int z){

        return false;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void reloadPlugin(){
       BorderBlock = getConfig().getString("BorderBlock");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }
}

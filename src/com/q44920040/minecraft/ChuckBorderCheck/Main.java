package com.q44920040.minecraft.ChuckBorderCheck;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        if (CalculateBhuckBorder(x,z)){
            System.out.println("在边缘");
            if (BorderBlock.contains(NewId)){
                event.setCancelled(true);
            }
        }
    }

    private Boolean CalculateBhuckBorder(int x,int z){
        int newx = x%16;
        int newz = z%16;
        return newx<=1||newz<=1||newx>=15||newz>=15;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void reloadPlugin(){
        reloadConfig();
        BorderBlock = getConfig().getString("BorderBlock");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player&&label.equalsIgnoreCase("cbc")){
            if (args.length==1&&args[0].equalsIgnoreCase("reload")){
                reloadPlugin();
                sender.sendMessage("§e§l[CBC]§2重载插件成功");
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}

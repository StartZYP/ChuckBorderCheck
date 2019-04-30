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

import java.io.File;

public class Main extends JavaPlugin implements Listener {
    private String BorderBlock;
    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        reloadPlugin();
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }


    @EventHandler
    public void PlayerPlaceBlock(BlockPlaceEvent event){
        try{
            event.getPlayer();
        }catch (Exception e){
            event.setCancelled(true);
            return;
        }
        Block blockPlaced = event.getBlockPlaced();
        int MainId = blockPlaced.getTypeId();
        int OrderId = blockPlaced.getData();
        String NewId = MainId+":"+OrderId;
        int x = blockPlaced.getX();
        int z = blockPlaced.getZ();
        if (CalculateBhuckBorder(x,z)){
            if (BorderBlock.contains(NewId)){
                event.setCancelled(true);
                try{
                    event.getPlayer().sendMessage(getConfig().getString("Msg"));
                }catch (Exception e){

                }
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
        if (label.equalsIgnoreCase("cbc")){
            if (sender.isOp()&&args.length==1&&args[0].equalsIgnoreCase("reload")){
                reloadPlugin();
                sender.sendMessage("§e§l[CBC]§2重载插件成功");
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}

package com.github.com.ittekikun;

 
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.plugin.java.JavaPlugin;
 
public class AutoKick extends JavaPlugin implements Listener 
{
	public static AutoKick plugin; 
	
	@Override
	public void onEnable()
	{
		
		plugin = this;
		
		this.saveDefaultConfig();
		this.reloadConfig();
		
		 getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable()
	{

	}
	
	@EventHandler(priority = EventPriority.HIGHEST) 
    public void AsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event)
	{
		event.setKickMessage(this.getConfig().getString("message").replaceAll("&NL", "\n").replaceAll("&", "§"));
		event.setLoginResult(Result.KICK_OTHER);
    }
}
package com.github.com.ittekikun;

import java.util.List;

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
		//ここでconfigから許可するPlayerのListを取得する
		List<String> AP = this.getConfig().getStringList("AllowPlayer");

		//プレイヤーがListに入っていなかった場合
		if(!AP.contains(event.getName()))
		{
			//KICKメッセージをconfigから取得して文字列を置き換えしたりする
			event.setKickMessage(this.getConfig().getString("message").replaceAll("&NL", "\n").replaceAll("&", "§"));
			//KICK!
			event.setLoginResult(Result.KICK_OTHER);
		}
	}
}
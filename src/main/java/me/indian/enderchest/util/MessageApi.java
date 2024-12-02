package me.indian.enderchest.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageApi {

    public static void actionBar(final Player p, final String text) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text.replace("<player>", p.getName())));
    }

    public static void chat(final Player p, final String text) {
        p.spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(text.replace("<player>", p.getName())));
    }

    public static void console(final Player p, final String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("<player>", p.getName()));
    }

    public static void console(final String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public static void playerCommand(final Player p, final String command) {
        Bukkit.dispatchCommand(p, command.replace("<player>", p.getName()));
    }

    public static void hoverMessage(final Player p, final String text, final String hovertext) {
        final TextComponent message = new TextComponent(text);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
        p.spigot().sendMessage(message);
    }

    public static void hoverMessage(final Player p, final String text, final String command, final String hovertext, final Boolean run) {
        final TextComponent message = new TextComponent(text);
        if (run) {
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        } else {
            message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        }
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
        p.spigot().sendMessage(message);
    }

    public static void hoverMessageCopy(final Player p, final String text1, final String text2, final String hovertext) {
        final TextComponent message = new TextComponent(text1);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text2));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
        p.spigot().sendMessage(message);
    }
}
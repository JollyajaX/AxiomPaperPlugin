package com.moulberry.axiom;

import com.moulberry.axiom.event.AxiomCreateWorldPropertiesEvent;
import com.moulberry.axiom.world_properties.WorldPropertyCategory;
import com.moulberry.axiom.world_properties.WorldPropertyWidgetType;
import com.moulberry.axiom.world_properties.server.ServerWorldProperty;
import net.kyori.adventure.text.Component;
import net.minecraft.util.Unit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class WorldPropertiesExample implements Listener {

    @EventHandler
    public void onCreateWorldProperties(AxiomCreateWorldPropertiesEvent event) {
        WorldPropertyCategory category = new WorldPropertyCategory("Examples", false);

        ServerWorldProperty<Boolean> checkbox = new ServerWorldProperty<>(new NamespacedKey("axiom", "checkbox"),
            "Checkbox",
            false, WorldPropertyWidgetType.CHECKBOX, false, (player, world, bool) -> {
            world.sendMessage(Component.text("Checkbox: " + bool)); // Do something with input
            return true; // true to sync with client
        });

        ServerWorldProperty<Integer> slider = new ServerWorldProperty<>(new NamespacedKey("axiom", "slider"),
            "Slider",
            false, new WorldPropertyWidgetType.Slider(0, 8), 4, (player, world, integer) -> {
            world.sendMessage(Component.text("Slider: " + integer)); // Do something with input
            return true; // true to sync with client
        });

        ServerWorldProperty<String> textbox = new ServerWorldProperty<>(new NamespacedKey("axiom", "textbox"),
            "Textbox",
            false, WorldPropertyWidgetType.TEXTBOX, "Hello", (player, world, string) -> {
            world.sendMessage(Component.text("Textbox: " + string)); // Do something with input
            return true; // true to sync with client
        });

        ServerWorldProperty<Unit> button = new ServerWorldProperty<>(new NamespacedKey("axiom", "button"),
            "Button",
            false, WorldPropertyWidgetType.BUTTON, Unit.INSTANCE, (player, world, unit) -> {
            world.sendMessage(Component.text("Button pressed")); // Do something with input
            return true; // true to sync with client
        });

        event.addCategory(category, List.of(checkbox, slider, textbox, button));
    }

}

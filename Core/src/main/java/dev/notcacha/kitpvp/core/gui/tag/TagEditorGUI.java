package dev.notcacha.kitpvp.core.gui.tag;

import dev.notcacha.kitpvp.api.tag.Tag;
import me.patothebest.guiframework.gui.anvil.AnvilSlot;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.AnvilButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import me.yushust.message.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class TagEditorGUI extends GUIPage {

    private final MessageHandler messageHandler;
    private final Tag tag;

    public TagEditorGUI(Plugin plugin, Player player, String rawName, MessageHandler messageHandler, Tag tag) {
        super(plugin, player, rawName, 9);
        this.messageHandler = messageHandler;
        this.tag = tag;

        build();
    }

    @Override
    public void buildPage() {
        ItemStackBuilder changePrefix = buildItem("prefix");
        ItemStackBuilder changeSuffix = buildItem("suffix");
        ItemStackBuilder changeListName = buildItem("list_name");

        addButton(buildAnvilButton("prefix", changePrefix), 0);
        addButton(buildAnvilButton("suffix", changeSuffix), 4);
        addButton(buildAnvilButton("list_name", changeListName), 8);
    }

    private ItemStackBuilder buildItem(String type) {
        return new ItemStackBuilder(Material.PAPER)
                .name(messageHandler.get(player, "tag.edit.items." + type + ".name"))
                .lore(messageHandler.get(player, "tag.edit.items." + type + ".lore"));
    }

    private AnvilButton buildAnvilButton(String type, ItemStackBuilder item) {
        AnvilButton anvilButton = new AnvilButton(item);

        anvilButton.onConfirm((event) -> {

            String output = event.getOutput();

            switch (type) {

                case "prefix": {
                    message("prefix");

                    tag.setPrefix(output);
                    event.setWillClose(true);

                    break;
                }

                case "suffix": {
                    message("suffix");

                    tag.setSuffix(output);
                    event.setWillClose(true);

                    break;
                }

                case "list_name": {
                    message("list_name");

                    tag.setListName(output);

                    event.setWillClose(true);

                    break;
                }

            }


        }).onCancel(() -> {})
                .useSlot(AnvilSlot.INPUT_LEFT, item.name(ChatColor.stripColor(item.getItemMeta().getDisplayName())));

        return anvilButton;
    }

    private void message(String type) {
        messageHandler.sendReplacing(player, "tag.edit.success", "%type%", type);
    }
}

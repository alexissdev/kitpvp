package dev.notcacha.kitpvp.core.command.kit;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.core.gui.kit.KitEditorGUI;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

@Command(names = "edit", permission = "kitpvp.kit.edit")
public class KitEditCommand implements CommandClass {

    @Inject private ModelRepository<Kit> kitModelRepository;
    @Inject private MessageHandler messageHandler;
    @Inject private Plugin plugin;

    @Command(names = "")
    public boolean main(@Sender Player player, @OptArg String kitId) {
        if (kitId == null || kitId.trim().isEmpty()) {
            messageHandler.send(player, "kit.editor.messages.usage");
            return true;
        }


        kitModelRepository.findOne(kitId).callback(callback -> {
            Optional<Kit> callbackResponse = callback.getResponse();

            if (!callbackResponse.isPresent()) {
                messageHandler.sendReplacing(player, "kit.editor.messages.error", "%kit_id%", kitId);
                return;
            }

            new KitEditorGUI(plugin, player, messageHandler.get(player, "kit.editor.title").replace("%kit_id%", kitId), messageHandler, callbackResponse.get());

        });

        return true;
    }
}

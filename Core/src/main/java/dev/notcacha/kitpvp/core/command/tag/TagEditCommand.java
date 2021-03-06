package dev.notcacha.kitpvp.core.command.tag;

import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.core.gui.tag.TagEditorGUI;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.util.Optional;

@Command(names = "edit", permission = "kitpvp.tag.edit")
public class TagEditCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ModelRepository<Tag> tagModelRepository;
    @Inject private Plugin plugin;

    @Command(names = "")
    public boolean main(@Sender Player player, @OptArg String tagId) {
        if (tagId == null || tagId.trim().isEmpty()) {
            messageHandler.send(player, "tag.edit.usage");
            return true;
        }

        tagModelRepository.findOne(tagId).callback(callback -> {
            Optional<Tag> callbackResponse = callback.getResponse();

            if (!callbackResponse.isPresent()) {

                messageHandler.sendReplacing(player, "tag.edit.error", "%tag_id%", tagId);

                return;
            }

            new TagEditorGUI(plugin, player, messageHandler.get(player, "tag.edit.title").replace("%tag_id%", tagId), messageHandler, callbackResponse.get());
        });
        return true;
    }
}

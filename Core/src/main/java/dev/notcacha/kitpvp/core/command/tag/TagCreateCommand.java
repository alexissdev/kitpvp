package dev.notcacha.kitpvp.core.command.tag;

import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.tag.DefaultTag;
import dev.notcacha.kitpvp.api.tag.Tag;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "create", permission = "kitpvp.tag.create")
public class TagCreateCommand implements CommandClass {

    @Inject private ModelRepository<Tag> tagModelRepository;
    @Inject private MessageHandler messageHandler;

    @Command(names = "")
    public boolean main(@Sender Player player, @OptArg String tagId) {
        if (tagId == null || tagId.trim().isEmpty()) {

            messageHandler.send(player, "tag.create.usage");

            return true;
        }

        tagModelRepository.findOne(tagId).callback(callback -> {
            if (!callback.isSuccessful()) {
                tagModelRepository.save(new DefaultTag(
                        tagId,
                        "",
                        "",
                        tagId
                ), true);

                messageHandler.sendReplacing(player, "tag.create.success", "%tag_id%", tagId);

                return;
            }


            messageHandler.sendReplacing(player, "tag.create.error", "%tag_id%", tagId);
        });

        return true;
    }
}

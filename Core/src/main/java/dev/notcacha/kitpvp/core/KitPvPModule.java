package dev.notcacha.kitpvp.core;

import dev.notcacha.kitpvp.core.chat.module.ChatModule;
import dev.notcacha.kitpvp.core.hook.module.HookModule;
import dev.notcacha.kitpvp.core.scoreboard.module.ScoreboardModule;
import dev.notcacha.kitpvp.core.translation.module.TranslationModule;
import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.core.command.flow.FlowModule;
import dev.notcacha.kitpvp.core.file.module.FileModule;
import dev.notcacha.kitpvp.core.kit.module.KitModule;
import dev.notcacha.kitpvp.core.mongo.module.MongoModule;
import dev.notcacha.kitpvp.core.service.module.ServiceModule;
import dev.notcacha.kitpvp.core.spawn.module.SpawnModule;
import dev.notcacha.kitpvp.core.tag.module.TagModule;
import dev.notcacha.kitpvp.core.user.module.UserModule;
import org.bukkit.plugin.Plugin;

public class KitPvPModule extends AbstractModule {

    private final Plugin plugin;

    public KitPvPModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(this.plugin);

        install(new ListeningExecutorServiceModule());

        install(new MongoModule());
        install(new MapperModule());
        install(new FileModule(plugin));

        install(new KitModule());
        install(new UserModule());
        install(new TagModule());

        install(new FlowModule());

        install(new ChatModule());
        install(new SpawnModule());
        install(new ServiceModule());

        install(new TranslationModule());
        install(new ScoreboardModule());

        install(new HookModule());
    }
}

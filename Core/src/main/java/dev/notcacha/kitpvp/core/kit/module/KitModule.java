package dev.notcacha.kitpvp.core.kit.module;

import dev.notcacha.kitpvp.api.kit.applier.KitApplier;
import dev.notcacha.kitpvp.core.kit.DefaultKitApplier;
import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.binder.ModelBinder;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.core.binder.CoreModelBinder;
import dev.notcacha.kitpvp.core.util.TypeReferenceUtil;

public class KitModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<Kit> kitModelBinder = new CoreModelBinder<>(
                binder(),
                Kit.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Kit.class)
                )
        );

        kitModelBinder.bindCache().bindDefault();
        kitModelBinder.bindProcessors()
                .bindFind()
                .bindDelete()
                .bindSave();

        kitModelBinder.bindRepository();

        bind(KitApplier.class).to(DefaultKitApplier.class).singleton();
    }
}

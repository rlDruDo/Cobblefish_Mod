package elbrunanzo.cobble.fish;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class EntityTestingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(cobblefish.COBBLEFISH_ENTITY, (dispatcher, context) -> {
            return new cobblefishEntityRenderer(dispatcher);
        });
    }
}
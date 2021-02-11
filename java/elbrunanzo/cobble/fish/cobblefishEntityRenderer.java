package elbrunanzo.cobble.fish;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class cobblefishEntityRenderer extends MobEntityRenderer<cobblefishEntity, cobblefishentityModel> {
    public cobblefishEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new cobblefishentityModel(), 0.3f);
    }

    @Override
    public Identifier getTexture(cobblefishEntity entity) {
        return new Identifier("cobblefish","textures/entity/cube/cod.png");
    }

    protected void setupTransforms(cobblefishEntity cobblefishEntity, MatrixStack matrixStack, float f, float g, float h) {
        super.setupTransforms(cobblefishEntity, matrixStack, f, g, h);
        float i = 4.3F * MathHelper.sin(0.6F * f);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(i));
        if (!cobblefishEntity.isTouchingWater()) {
            matrixStack.translate(0.10000000149011612D, 0.10000000149011612D, -0.10000000149011612D);
            matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90.0F));
        }
    }}

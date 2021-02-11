package elbrunanzo.cobble.fish;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class cobblefishentityModel extends EntityModel<cobblefishEntity> {
    private final ModelPart body;
    private final ModelPart topFin;
    private final ModelPart head;
    private final ModelPart face;
    private final ModelPart rightFin;
    private final ModelPart leftFin;
    private final ModelPart tail;

    public cobblefishentityModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        boolean i = true;
        this.body = new ModelPart(this, 0, 0);
        this.body.addCuboid(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 7.0F);
        this.body.setPivot(0.0F, 22.0F, 0.0F);
        this.head = new ModelPart(this, 11, 0);
        this.head.addCuboid(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F);
        this.head.setPivot(0.0F, 22.0F, 0.0F);
        this.face = new ModelPart(this, 0, 0);
        this.face.addCuboid(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F);
        this.face.setPivot(0.0F, 22.0F, -3.0F);
        this.rightFin = new ModelPart(this, 22, 1);
        this.rightFin.addCuboid(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F);
        this.rightFin.setPivot(-1.0F, 23.0F, 0.0F);
        this.rightFin.roll = -0.7853982F;
        this.leftFin = new ModelPart(this, 22, 4);
        this.leftFin.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F);
        this.leftFin.setPivot(1.0F, 23.0F, 0.0F);
        this.leftFin.roll = 0.7853982F;
        this.tail = new ModelPart(this, 22, 3);
        this.tail.addCuboid(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F);
        this.tail.setPivot(0.0F, 22.0F, 7.0F);
        this.topFin = new ModelPart(this, 20, -6);
        this.topFin.addCuboid(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 6.0F);
        this.topFin.setPivot(0.0F, 20.0F, 0.0F);
    }

    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.body, this.head, this.face, this.rightFin, this.leftFin, this.tail, this.topFin);
    }

    public void setAngles(cobblefishEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float f = 1.0F;
        if (!entity.isTouchingWater()) {
            f = 1.5F;
        }

        this.tail.yaw = -f * 0.45F * MathHelper.sin(0.6F * animationProgress);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        // translate model down
        matrices.translate(0, 0, 0);

        // render fish
        body.render(matrices, vertices, light, overlay);
        topFin.render(matrices, vertices, light, overlay);
        head.render(matrices, vertices,light, overlay);
        face.render(matrices, vertices, light, overlay);
        rightFin.render(matrices, vertices, light, overlay);
        leftFin.render(matrices, vertices, light, overlay);
        tail.render(matrices, vertices, light, overlay);
    }
    }



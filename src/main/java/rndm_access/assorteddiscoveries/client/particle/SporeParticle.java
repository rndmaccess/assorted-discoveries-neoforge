package rndm_access.assorteddiscoveries.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;

public class SporeParticle extends SingleQuadParticle {
    protected SporeParticle(ClientLevel clientLevel, double x, double y, double z,
                            double xd, double yd, double zd, TextureAtlasSprite sprite) {
        super(clientLevel, x, y, z, sprite);
        this.setSize(0.01F, 0.01F);
        this.xd = xd * 0.2F + (Math.random() * 2.0D - 1.0D) * 0.02F;
        this.yd = yd * 0.2F + (Math.random() * 2.0D - 1.0D) * 0.02F;
        this.zd = zd * 0.2F + (Math.random() * 2.0D - 1.0D) * 0.02F;
        this.lifetime = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    public int getLightCoords(float tint) {
        return 240;
    }

    @Override
    public @NonNull Layer getLayer() {
        return Layer.OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        } else {
            this.yd += 0.002D;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.85F;
            this.yd *= 0.85F;
            this.zd *= 0.85F;
        }
    }

    public record Provider(SpriteSet spriteSet) implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType type, @NonNull ClientLevel level,
                                       double x, double y, double z,
                                       double xd, double yd, double zd, @NonNull RandomSource random) {
            return new SporeParticle(level, x, y, z, xd, yd, zd, this.spriteSet.get(random));
        }
    }
}

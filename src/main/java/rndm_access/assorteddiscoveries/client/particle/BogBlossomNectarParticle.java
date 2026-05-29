package rndm_access.assorteddiscoveries.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleLimit;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class BogBlossomNectarParticle extends SingleQuadParticle {
    protected BogBlossomNectarParticle(ClientLevel clientLevel, double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ, TextureAtlasSprite sprite) {
        super(clientLevel, x, y - 0.125D, z, velocityX, velocityY, velocityZ, sprite);
        this.setSize(0.01F, 0.01F);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.6F;
        this.lifetime = Mth.randomBetweenInclusive(clientLevel.getRandom(), 500, 1000);
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.01F;
        this.setColor(1.0F, 0.8F, 0.5F);
    }

    @Override
    public @NonNull Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    @Override
    public void tick() {
        this.updateAge();

        if(this.isAlive()) {
            super.tick();
        }
    }

    private void updateAge() {
        if(this.lifetime-- <= 0) {
            this.remove();
        }
    }

    @Override
    public int getLightCoords(float tint) {
        return 240;
    }

    public record Provider(SpriteSet spriteSet) implements ParticleProvider<SimpleParticleType> {

        @Override
        public Particle createParticle(@NonNull SimpleParticleType particle, @NonNull ClientLevel level,
                                       double x, double y, double z, double velocityX, double velocityY,
                                       double velocityZ, @NonNull RandomSource random) {
            return new BogBlossomNectarParticle(level, x, y, z,
                    0.0D, -0.8D, 0.0D, this.spriteSet.get(random)) {
                public @NonNull Optional<ParticleLimit> getParticleLimit() {
                    return Optional.of(ParticleLimit.SPORE_BLOSSOM);
                }
            };
        }
    }
}

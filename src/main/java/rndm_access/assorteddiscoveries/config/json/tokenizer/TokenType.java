package rndm_access.assorteddiscoveries.config.json.tokenizer;

import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum TokenType implements StringRepresentable {
    LEFT_CURLY("{"), RIGHT_CURLY("}"),
    LEFT_BRACKET("["), RIGHT_BRACKET("]"),
    COLON(":"), COMMA(","),

    KEY("key"), VALUE("value"), ERROR("error");

    private final String string;

    TokenType(String string) {
        this.string = string;
    }

    @Override
    public @NonNull String getSerializedName() {
        return string;
    }
}

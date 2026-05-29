package rndm_access.assorteddiscoveries.config.json.deserializer;

import net.fabricmc.loader.api.FabricLoader;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.config.json.ServerConfig;
import rndm_access.assorteddiscoveries.config.json.exceptions.JsonSyntaxException;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.*;
import rndm_access.assorteddiscoveries.config.json.tokenizer.Token;
import rndm_access.assorteddiscoveries.config.json.tokenizer.TokenList;
import rndm_access.assorteddiscoveries.config.json.tokenizer.ConfigTokenizer;
import rndm_access.assorteddiscoveries.config.json.tokenizer.TokenType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class ConfigDeserializer {
    private TokenList tokenList;
    private final Path configPath;
    private final String configName;

    /**
     * @param configName The name of the config without the file suffix.
     */
    public ConfigDeserializer(String configName) throws IOException {
        this.configName = configName;
        this.configPath = FabricLoader.getInstance().getConfigDir().resolve(configName + ".json5");
    }

    public ServerConfig deserialize() throws JsonSyntaxException {
        // We tokenize here in case this method throws a JsonConfigException
        this.tokenList = new ConfigTokenizer(configPath).tokenize();
        ServerConfig.Builder config = new ServerConfig.Builder(configName);

        if(tokenList.isEmpty()) {
            AssortedDiscoveries.LOGGER.error("Could not load the config file because it was empty!");
            return config.build();
        }

        requireToken(TokenType.LEFT_CURLY);
        deserialize(config);
        requireToken(TokenType.RIGHT_CURLY);
        return config.build();
    }

    private void deserialize(ServerConfig.Builder config) throws JsonSyntaxException {
        while (tokenList.hasNextToken() && !tokenList.match(TokenType.RIGHT_CURLY)) {
            Token keyToken = requireToken(TokenType.KEY);
            requireToken(TokenType.COLON);

            if (tokenList.matchAndConsume(TokenType.LEFT_CURLY)) {
                ConfigCategory category = parseCategory(keyToken);
                config.addCategory(category);
            }

            if (!tokenList.match(TokenType.RIGHT_CURLY) && tokenList.getNext() != null) {
                requireToken(TokenType.COMMA, TokenType.RIGHT_CURLY);
            }
        }
    }

    /**
     * @param input The input to trim!
     * @return The input without extra surrounding quotes if there are any otherwise the input itself.
     */
    private String parseString(String input) {
        if (input.charAt(0) == '"' && input.charAt(input.length() - 1) == '"') {
            return input.substring(1, input.length() - 1);
        }
        return input;
    }

    private ConfigCategory parseCategory(Token keyToken) throws JsonSyntaxException {
        String categoryName = parseString(keyToken.getValue());
        ConfigCategory.Builder category = new ConfigCategory.Builder(categoryName);
        parseEntry(category);
        return category.build();
    }

    private void parseEntry(ConfigCategory.Builder category) throws JsonSyntaxException {
        while (tokenList.hasNextToken() && !tokenList.match(TokenType.RIGHT_CURLY)) {
            Token keyToken = requireToken(TokenType.KEY);
            String key = parseString(keyToken.getValue());
            requireToken(TokenType.COLON);

            if (tokenList.match(TokenType.ERROR)) {
                tokenList.consumeToken();
                AssortedDiscoveries.LOGGER.error("This type for {} is not supported!", keyToken.getValue());
            } else if (tokenList.match(TokenType.LEFT_CURLY)) {
                tokenList.consumeToken();
                ConfigCategory subcategory = parseCategory(keyToken);
                category.addSubcategory(subcategory);
            } else {
                Token token = requireToken(TokenType.VALUE);
                String value = token.getValue();

                if (Objects.equals(value, "true") || Objects.equals(value, "false")) {
                    boolean boolVal = Boolean.parseBoolean(value);
                    BooleanConfigEntry entry = new BooleanConfigEntry(key, boolVal);
                    category.addEntry(entry);
                } else if (isPosInteger(value)) {
                    int intVal = Integer.parseInt(value);
                    IntegerConfigEntry entry = new IntegerConfigEntry(key, intVal);
                    category.addEntry(entry);
                } else {
                    String stringVal = parseString(value);
                    StringConfigEntry entry = new StringConfigEntry(key, stringVal);
                    category.addEntry(entry);
                }
            }

            if (!tokenList.match(TokenType.RIGHT_CURLY)) {
                requireToken(TokenType.COMMA, TokenType.RIGHT_CURLY);
            }
        }
        tokenList.consumeToken(); // Consume the closing right curly for this entry!
    }

    private boolean isPosInteger(String value) {
        for (int i = 0; i < value.length(); i++) {
            if(!Character.isDigit(value.charAt(i)) && value.charAt(0) != '-') {
                return false;
            }
        }
        return true;
    }

    public Token requireToken(TokenType... types) throws JsonSyntaxException {
        if (!tokenList.match(types)) {
            throw new JsonSyntaxException(getSyntaxErrorMessage(types));
        }
        return tokenList.consumeToken();
    }

    private String getSyntaxErrorMessage(TokenType... types) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < types.length; i++) {
            message.append("'").append(types[i].getSerializedName()).append("'");

            if(i != types.length - 1) {
                message.append(" or ");
            }
        }
        message.append(" expected");

        if (tokenList.hasNextToken()) {
            Token currentToken = tokenList.get();

            message.append(", got ");
            if (tokenList.match(TokenType.VALUE)) {
                message.append("'").append(currentToken.getType().getSerializedName()).append("'");
                message.append(" with value ").append("'").append(currentToken.getValue()).append("'");
            } else {
                message.append("'").append(currentToken.getValue()).append("'");
            }

            message.append(" at line ").append(currentToken.getLine() + 1);
        }
        message.append(". Config path: ").append(configPath);

        return message.toString();
    }
}

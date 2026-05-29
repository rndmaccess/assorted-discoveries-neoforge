package rndm_access.assorteddiscoveries.config.json.exceptions;

/**
 * Users should never experience these exceptions their strictly a check to make sure that I didn't break anything!
 */
public class JsonConfigException extends RuntimeException {
    public JsonConfigException(String message) {
        super(message);
    }
}

package rndm_access.assorteddiscoveries.config.json.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TokenList {
    private int position;
    private final List<Token> tokenList;

    public TokenList(ArrayList<Token> tokenList) {
        this.position = 0;
        this.tokenList = tokenList;
    }

    public Token getNext() {
        int nextPos = position + 1;

        if(nextPos < tokenList.size()) {
            return tokenList.get(nextPos);
        }
        return null;
    }

    public Token get() {
        return tokenList.get(position);
    }

    public Token consumeToken() {
        return tokenList.get(position++);
    }

    /**
     * @param tokenTypes The token types to match
     * @return true if the at least one of the token types match the current token.
     *         False if there is no token to match!
     */
    public boolean match(TokenType... tokenTypes) {
        // This check is to prevent an out-of-bounds exception!
        if(!hasNextToken()) {
            return false;
        }

        for (TokenType tokenType : tokenTypes) {
            TokenType currentTokenType = this.get().getType();

            if(Objects.equals(currentTokenType, tokenType)) {
                return true;
            }
        }
        return false;
    }

    public boolean matchAndConsume(TokenType type) {
        if (this.match(type)) {
            consumeToken();
            return true;
        }
        return false;
    }

    public boolean hasNextToken() {
        return position < tokenList.size();
    }

    public boolean isEmpty() {
        return tokenList.isEmpty();
    }
}

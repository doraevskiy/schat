package ru.security.schat.pass.dto.security.tokens;

public final class RESTCredentials {
    private final String rawPassword;

    public RESTCredentials(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String getRawPassword() {
        return rawPassword;
    }
}

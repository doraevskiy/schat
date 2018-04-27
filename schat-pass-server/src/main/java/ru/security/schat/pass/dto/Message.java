package ru.security.schat.pass.dto;

import java.io.Serializable;

public class Message implements Serializable {
    private Integer code;
    private String description;

    public Message() {
    }

    public Message(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final class MessageBuilder {
        private Integer code;
        private String description;

        private MessageBuilder() {
        }

        public static MessageBuilder aMessage() {
            return new MessageBuilder();
        }

        public MessageBuilder withCode(Integer code) {
            this.code = code;
            return this;
        }

        public MessageBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Message build() {
            return new Message(code, description);
        }
    }
}

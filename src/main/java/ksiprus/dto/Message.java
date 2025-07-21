package ksiprus.dto;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private int senderId;
    private int receiverId;
    private String text;
    private LocalDateTime sentAt;

    public Message(int id, int senderId, int receiverId, String text, LocalDateTime sentAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.sentAt = sentAt;
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    // Статический внутренний класс Builder
    public static class Builder {
        private int id;
        private int senderId;
        private int receiverId;
        private String text;
        private LocalDateTime sentAt;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder senderId(int senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder receiverId(int receiverId) {
            this.receiverId = receiverId;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder sentAt(LocalDateTime sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public Message build() {
            // Валидация перед созданием объекта Message
            if (senderId <= 0 || receiverId <= 0 || text == null || sentAt == null) {
                throw new IllegalArgumentException("Поля не могут быть пустыми");
            }
            return new Message(id, senderId, receiverId, text, sentAt);
        }
    }
}

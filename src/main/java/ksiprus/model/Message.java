package ksiprus.model;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private int senderId;
    private int receiverId;
    private String text;
    private LocalDateTime sentAt;

    // Конструктор
    public Message(int id, int senderId, int receiverId, String text, LocalDateTime sentAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.sentAt = sentAt;
    }

    // Геттеры
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

    // Сеттеры (если необходимо)
    public void setId(int id) {
        this.id = id;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    // Статический внутренний класс Builder
    public static class Builder {
        private int id;
        private int senderId;
        private int receiverId;
        private String text;
        private LocalDateTime sentAt;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setSenderId(int senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder setReceiverId(int receiverId) {
            this.receiverId = receiverId;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setSentAt(LocalDateTime sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public Message build() {
            return new Message(id, senderId, receiverId, text, sentAt);
        }
    }
}

package com.code.with.bisky.dto.chatgpt;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptAIDto {

    private String model;
    private List<ChatGptMessage> messages;

}

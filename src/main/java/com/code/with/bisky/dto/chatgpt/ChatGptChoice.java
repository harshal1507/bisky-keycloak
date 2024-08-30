package com.code.with.bisky.dto.chatgpt;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptChoice {


    private Long index;
    private ChatGptMessage message;
    private String finish_reason;
}

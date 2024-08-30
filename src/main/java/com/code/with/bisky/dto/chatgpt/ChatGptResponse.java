package com.code.with.bisky.dto.chatgpt;


import lombok.Data;

import java.util.List;

@Data
public class ChatGptResponse {

    private  String id;
    private  String object;
    private  Long created;
    private List<ChatGptChoice> choices;
    private ChatGptUsage usage;
}

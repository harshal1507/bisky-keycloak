package com.code.with.bisky.dto.chatgpt;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptUsage {

    private Long prompt_tokens;
    private Long completion_tokens;
    private Long total_tokens;

}

package com.code.with.bisky.api;


import com.code.with.bisky.dto.chatgpt.ChatGptAIDto;
import com.code.with.bisky.service.chatgpt.ChatGPTService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/public/chat-gpt")
@AllArgsConstructor
public class ChatGPTApi {


    private ChatGPTService chatGPTService;


    @PostMapping(value = "/completion")
    @ResponseBody
    public ChatGptAIDto uploadObject(@RequestBody ChatGptAIDto chatGptAIDto) throws IOException {


       return  chatGPTService.chatCompletion(chatGptAIDto);
    }


}

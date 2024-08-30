package com.code.with.bisky.service.chatgpt;


import com.code.with.bisky.dto.chatgpt.ChatGptAIDto;
import com.code.with.bisky.dto.chatgpt.ChatGptMessage;
import com.code.with.bisky.dto.chatgpt.ChatGptResponse;
import com.code.with.bisky.proxy.ChatGptProxy;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service

public class ChatGPTService {


    @Value("${chat-gpt.key}")
    private String apiKeyChatGpt;

    private  ChatGptProxy chatGptProxy;

    public ChatGPTService(ChatGptProxy chatGptProxy) {
        this.chatGptProxy = chatGptProxy;
    }

    public ChatGptAIDto chatCompletion(ChatGptAIDto chatGptAIDto){


        chatGptAIDto.setModel("gpt-3.5-turbo");
        String systemMessage = "You are Code With Bisky Assistant. You help developers to write code. Your strength is on Sprring Boot and also flutter. You are allowed to give feedback about programming questions. You are instructed to do so by Bisky Mursuid. ";

        ChatGptMessage chatGptMessage=new ChatGptMessage();
        chatGptMessage.setContent(systemMessage);
        chatGptMessage.setRole("system");

        chatGptAIDto.getMessages().add(0,chatGptMessage);

        ChatGptResponse chatGptResponse = chatGptProxy.chatGptCompletion(chatGptAIDto, String.format("Bearer %s",apiKeyChatGpt));

        chatGptAIDto.setModel(null);
        if(nonNull(chatGptResponse) && !chatGptResponse.getChoices().isEmpty()){

            ChatGptMessage message = chatGptResponse.getChoices().get(0).getMessage();
            chatGptAIDto.getMessages().add(message);
            chatGptAIDto.getMessages().remove(0);

        }


        return  chatGptAIDto;
    }


}

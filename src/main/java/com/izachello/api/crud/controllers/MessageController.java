package com.izachello.api.crud.controllers;

import com.izachello.api.crud.dtos.MessageDTO;
import com.izachello.api.crud.models.Message;
import com.izachello.api.crud.repositories.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    ModelMapper modelMapper = new ModelMapper();

    private Message convertMessageToEntity (MessageDTO messageDto){
        return modelMapper.map(messageDto, Message.class);
    }

    private MessageDTO convertMessageToDTO (Message message){
        return modelMapper.map(message, MessageDTO.class);
    }

//    API CREATE Message
    @PostMapping("/post/message/")
    public Map<String, Object> createMessage(@RequestBody MessageDTO messageDTO){
        Map<String, Object> mapResult = new HashMap<>();

        Message message = convertMessageToEntity(messageDTO);
        message.setMessage(messageDTO.getMessage());

        mapResult.put("message","Message Create Success");
        mapResult.put("data",messageRepository.save(message));

        return mapResult;
    }

//    API READ ALL Messages
    @GetMapping("/get/messages")
    public Map<String, Object> getAllMessages(){
        Map<String, Object> mapResult = new HashMap<>();
        List<MessageDTO> listMessagesDTO = new ArrayList<>();

        for (Message message : messageRepository.findAll()){
            MessageDTO messageDTO = convertMessageToDTO(message);
            listMessagesDTO.add(messageDTO);
        }

        String message;
        if(listMessagesDTO.isEmpty()){
            message="Data is empty";
        }else{
            message="Show all data";
        }

        mapResult.put("message", message);
        mapResult.put("data",listMessagesDTO);
        mapResult.put("total",listMessagesDTO.size());

        return mapResult;
    }

//    API UPDATE Message
    @PutMapping("/put/message")
    public  Map<String, Object> updateMessage (@RequestParam(value ="messageId") long messageId, @RequestBody MessageDTO messageDTO){
        Map<String, Object> mapResult = new HashMap<>();

        Message message = messageRepository.findById(messageId).orElse(null);

        message.setMessage(messageDTO.getMessage());

        mapResult.put("message","Message Update success");
        mapResult.put("data", messageRepository.save(message));

        return mapResult;
    }

//    API DELETE Message
    @DeleteMapping("/delete/message/{messageId}")
    public  Map<String, Object> deleteMessage(@PathVariable(value = "messageId") long messageId){
        Map<String, Object> mapResult = new HashMap<>();

        Message message = messageRepository.findById(messageId).orElse(null);

        messageRepository.delete(message);

        mapResult.put("message","Message Delete Success");

        return  mapResult;
    }
}

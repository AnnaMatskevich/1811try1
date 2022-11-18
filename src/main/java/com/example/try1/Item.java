package com.example.try1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Item {

    @JsonProperty("item_name")
    private String  name;

    @JsonProperty("item_comments")
    private List<String> comments;

    // конструктор
    public Item() {
        this.name="Food";
        comments = new ArrayList<>();
    }
    // конструктор
    public Item(String _name) {
        this.name=_name;
        comments = new ArrayList<>();
    }

    String getname(){
        return this.name;
    }

    public void Addcomment(String comment){
        comments.add(comment);
    }

    public void Updatecomment(Integer ind, String comment){
        comments.set(ind, comment);
    }

    public void Updatename(String name){
        this.name=name;
    }

    public void Deletecomment(Integer ind){
        comments.remove((int) ind);
    }

    // структуру данных User перевести в json
    public String   convert_to_json() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return (mapper.writeValueAsString(this));
    }

    // структуру данных User заполнить из json
    // (предполагается, что правильно заполненная структура придет из сети c ключем -d "{...}" в curl)
    public void     convert_from_json(String json_string) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(json_string);
        // извлечь поле user_name
        name = jsonNode.get("item_name").asText();
    }


}

package com.example.try1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
public class Controller {
    //// final !!!!!!!!!!!!!!!
    private List<String>  items = new ArrayList<>();
    private List<String>  users = new ArrayList<>();
    private List<Integer> id_user = new ArrayList<>();
    private List<Integer> id_item = new ArrayList<>();
    private List<String> comments = new ArrayList<>();
    //private List<ArrayList<Integer>> commentstoitem = new ArrayList<>();


    // curl -i -X POST http://localhost:8080/items/add -H "Content-Type: application/json" -d "health"
    @PostMapping("/items/add")
    public ResponseEntity<Void> additem(@RequestBody String text) {
        items.add(text);
        //commentstoitem.add(new ArrayList<>());
        return ResponseEntity.accepted().build();
    }

    //curl -i -X POST http://localhost:8080/users/add -H "Content-Type: application/json" -d "Lisa"
    @PostMapping("/users/add")
    public ResponseEntity<Void> useritem(@RequestBody String text) {
        users.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl -i -X POST http://localhost:8080/users/delete/2
    @DeleteMapping("/users/delete/{iduser}")
    public ResponseEntity<Void> userdelete(@PathVariable ("iduser") Integer iduser) {
        users.remove((int)iduser);
        return ResponseEntity.accepted().build();
    }

    // curl -X GET http://localhost:8080/items -i
    /*
    @GetMapping("/items")
    public ResponseEntity<String> showItems() throws JsonProcessingException {
        String value = "[\n";

        for (int i=0;i<items.size();i++)
        {
            if (i==items.size()-1){
                value =value+items.get(i).convert_to_json() + "\n";
                continue;
            }
            value = value + items.get(i).convert_to_json() + ",\n";
        }
        value+="]";
        return ResponseEntity.ok(value);
    }
    */

    // curl -X GET http://localhost:8080/items -i
    @GetMapping("/items")
    public ResponseEntity<String> getItems() throws JsonProcessingException {
        String value = "[\n";

        for (int i=0;i<items.size();i++)
        {
            if (i==items.size()-1){
                value =value+items.get(i) + "\n";
                continue;
            }
            value = value + items.get(i) + ",\n";
        }
        value+="]";
        return ResponseEntity.ok(value);
    }


    // curl -X DELETE http://localhost:8080/items/0 -i
    @DeleteMapping("items/{index}")
    public ResponseEntity<Void> deleteitem(@PathVariable("index") Integer index) {
        Integer i=0;
        while (i<comments.size()){
            if (id_item.get(i)==index){
                id_item.remove((int)i);
                id_user.remove((int)i);
                comments.remove((int)i);
                continue;
            }
            i++;
        }
        items.remove((int)index);
        return ResponseEntity.noContent().build();
    }

    // curl -i -X PUT http://localhost:8080/items/update/0 -H "Content-Type: application/json" -H "Accept: */*" -d "food"
    @PutMapping("/items/update/{index}")
    public ResponseEntity<Void> updateitem(@PathVariable("index") Integer index, @RequestBody String text) throws JsonProcessingException{

        items.set((int)index, text);
        return ResponseEntity.accepted().build();
    }

    // curl -X GET http://localhost:8080/items/count -i
    @GetMapping("items/count")
    public ResponseEntity<String> getItemscount() {
        return ResponseEntity.ok(Integer.toString(items.size()));
    }

    // curl -X DELETE http://localhost:8080/items/all -i
    @DeleteMapping("items/all")
    public ResponseEntity<Void> deleteAllItems() {
        items=new ArrayList<>();
        comments=new ArrayList<>();
        id_user=new ArrayList<>();
        id_item=new ArrayList<>();
        return ResponseEntity.noContent().build();
    }

    // curl -X GET http://localhost:8080/items/show/2 -i
    @GetMapping("/items/show/{index}")
    public ResponseEntity<String> getItemByIndex(@PathVariable("index") Integer index) throws JsonProcessingException {
        String  value;
        value = items.get((int)index) + ":\n";
        for (int i=0; i<comments.size(); ++i){
            if (id_item.get(i)==index){
                value+=users.get(id_user.get(i))+ " :";
                value+=comments.get(i)+ " \n";
            }

        }
        return ResponseEntity.ok(value);
    }



    // curl -i -X POST http://localhost:8080/items/0/0/addcomment -H "Content-Type: application/json" -d "what is food"
    @PostMapping("/items/{userid}/{itemid}/addcomment")
    public ResponseEntity<Void> addcomment(@PathVariable("itemid") Integer itemid, @PathVariable("userid") Integer userid, @RequestBody String text) {
        comments.add(text);
        id_item.add((int)itemid);
        id_user.add((int)userid);
        return ResponseEntity.accepted().build();
    }

    // curl -i -X PUT http://localhost:8080/items/updatecomment/1 -H "Content-Type: application/json" -d "okey we know"
    @PutMapping("/items/updatecomment/{indexcom}")
    public ResponseEntity<Void> updatecomment(@PathVariable("indexcom") Integer indexcom, @RequestBody String text) {

        comments.set((int)indexcom, text);
        return ResponseEntity.accepted().build();
    }

    // curl -i -X DELETE http://localhost:8080/items/deletecomment/2
    @DeleteMapping("/items/deletecomment/{index1}")
    public ResponseEntity<Void> deletecomment(@PathVariable("index1") Integer index1) {

        comments.remove((int)index1);
        id_user.remove((int)index1);
        id_item.remove((int)index1);

        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE http://localhost:8080/user/0/deletecomments -i
    @DeleteMapping("/user/{index}/deletecomments")
    public ResponseEntity<Void> userdeleteallcomments (@PathVariable("index") Integer index) {
        Integer i=0;
        while (i<comments.size()){
            if (id_user.get(i)==index){
                id_user.remove((int)i);
                id_item.remove((int)i);
                comments.remove((int)i);
                continue;
            }
            i++;
        }
        return ResponseEntity.accepted().build();
    }


    // curl -X GET http://localhost:8080/user/1/showcomments -i
    @GetMapping("/user/{index}/showcomments")
    public ResponseEntity<String> showusercomments(@PathVariable("index") Integer index) throws JsonProcessingException {
        String  value;
        value = users.get((int)index)+" :\n";
        for (int i=0; i<comments.size(); ++i){
            if (id_user.get(i)==index){
                value+=items.get(id_item.get(i))+ ": " +comments.get(i)+ "\n";
            }
        }
        return ResponseEntity.ok(value);
    }

}
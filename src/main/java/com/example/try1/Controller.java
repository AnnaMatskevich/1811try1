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
    private List<Item>  items = new ArrayList<>();

    // curl -i -X POST http://localhost:8080/items/add -H "Content-Type: application/json" -d "health"
    @PostMapping("/items/add")
    public ResponseEntity<Void> additem(@RequestBody String text) {
        Item item = new Item(text);
        items.add(item);
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


    @GetMapping("/items")
    public ResponseEntity<String> getItems() throws JsonProcessingException {
        String value = "[\n";

        for (int i=0;i<items.size();i++)
        {
            if (i==items.size()-1){
                value =value+items.get(i).getname() + "\n";
                continue;
            }
            value = value + items.get(i).getname() + ",\n";
        }
        value+="]";
        return ResponseEntity.ok(value);
    }


    // curl -X DELETE http://localhost:8080/items/0 -i
    @DeleteMapping("items/{index}")
    public ResponseEntity<Void> deleteitem(@PathVariable("index") Integer index) {
        items.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    // curl -i -X PUT http://localhost:8080/items/update/0 -H "Content-Type: application/json" -H "Accept: */*" -d "{\"item_name\":\"Hw\"}"
    @PutMapping("/items/update/{index}")
    public ResponseEntity<Void> updateitem(@PathVariable("index") Integer index, @RequestBody String text) throws JsonProcessingException{

        items.get(index).Updatename(text);
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
        return ResponseEntity.noContent().build();
    }

    // curl -X GET http://localhost:8080/items/show/2 -i
    @GetMapping("/items/show/{index}")
    public ResponseEntity<String> getItemByIndex(@PathVariable("index") Integer index) throws JsonProcessingException {
        String  value;
        value = items.get(index).convert_to_json();
        return ResponseEntity.ok(value);
    }

    // curl -i -X POST http://localhost:8080/items/addcomment/0 -H "Content-Type: application/json" -d "what is it"
    @PostMapping("/items/addcomment/{index}")
    public ResponseEntity<Void> addcomment(@PathVariable("index") Integer index, @RequestBody String text) {

        items.get(index).Addcomment(text);
        return ResponseEntity.accepted().build();
    }

    // curl -i -X PUT http://localhost:8080/items/0/updetecomment/1 -H "Content-Type: application/json" -d "what is it"
    @PutMapping("/items/{index1}/updetecomment/{index2}")
    public ResponseEntity<Void> updatecomment(@PathVariable("index1") Integer index1, @PathVariable("index2") Integer index2, @RequestBody String text) {

        items.get(index1).Updatecomment(index2, text);
        return ResponseEntity.accepted().build();
    }

    // curl -i -X DELETE http://localhost:8080/items/0/deletecomment/2
    @DeleteMapping("/items/{index1}/deletecomment/{index2}")
    public ResponseEntity<Void> deletecomment(@PathVariable("index1") Integer index1, @PathVariable("index2") Integer index2) {

        items.get(index1).Deletecomment(index2);
        return ResponseEntity.accepted().build();
    }


    /*

    // curl -X GET http://localhost:8080/contacts -i
    @GetMapping("/contacts")
    public ResponseEntity<String> getcontacts() throws JsonProcessingException {
        String value = "[\n";

        for (int i=0;i<contacts.size();i++)
        {
            if (i==contacts.size()-1){
                value =value+contacts.get(i).convert_to_json() + "\n";
                continue;
            }
            value = value + contacts.get(i).convert_to_json() + ",\n";
        }
        value+="\n]";
        return ResponseEntity.ok(value);
    }

    // curl -X GET http://localhost:8080/contacts/show/1 -i
    @GetMapping("/contacts/show/{index}")
    public ResponseEntity<String> getContactByIndex(@PathVariable("index") Integer index) throws JsonProcessingException {
        String  value;
        value = contacts.get(index).convert_to_json();
        return ResponseEntity.ok(value);
    }

    // curl -i -X DELETE http://localhost:8080/contacts/delete/1
    @DeleteMapping("/contacts/delete/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable("index") Integer index) {
        contacts.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    */


    // curl -i -X POST http://localhost:8080/contacts/add -H "Content-Type: application/json" -H "Accept: */*" -d "{\"contact_name\":\"Masha\",\"contact_number\":\"88004872574\", \"contact_email\":\"kdvk.gmail.com\"}"
    /*
    @PostMapping("/contacts/add")
    public ResponseEntity<Void> addContact(@RequestBody String json_user) throws JsonProcessingException
    {
        Contact contact = new Contact();

        contact.convert_from_json(json_user);
        contacts.add(contact);

        return ResponseEntity.accepted().build();
    }
    */
    // curl -i -X PUT http://localhost:8080/contacts/update/0 -H "Content-Type: application/json" -H "Accept: */*" -d "{\"contact_name\":\"Vlad\",\"contact_number\":\"2345678904\", \"contact_email\":\"fsgrhsjut.yandex.com\"}"
    /*
    @PutMapping("/contacts/update/{index}")
    public ResponseEntity<Void> updateContact(@PathVariable("index") Integer index, @RequestBody String json_user) throws JsonProcessingException{
        Contact contact = new Contact();
        contact.convert_from_json(json_user);
        contacts.set(index, contact);
        return ResponseEntity.accepted().build();
    }
    */
}
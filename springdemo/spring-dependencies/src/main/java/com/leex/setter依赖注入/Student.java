package com.leex.setter依赖注入;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Author : 86167
 * @Description : Student 2021/3/21 15:54 86167
 */
public class Student {

    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbys;
    private Map<String,String> card;
    private Set<String> games;
    private String wife;
    private Properties info;

    private List<Address> addressList;
    private Set<Address> addressSet;
    private Map<String, Address> addressMap;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public void setHobbys(List<String> hobbys) {
        this.hobbys = hobbys;
    }

    public void setCard(Map<String, String> card) {
        this.card = card;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    public void setAddressMap(Map<String, Address> addressMap) {
        this.addressMap = addressMap;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String[] getBooks() {
        return books;
    }

    public List<String> getHobbys() {
        return hobbys;
    }

    public Map<String, String> getCard() {
        return card;
    }

    public Set<String> getGames() {
        return games;
    }

    public String getWife() {
        return wife;
    }

    public Properties getInfo() {
        return info;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public Map<String, Address> getAddressMap() {
        return addressMap;
    }

    public void show(){
        System.out.println("name="+ name
                + ",address="+ address.getAddress()
                + ",books="
        );
        for (String book:books){
            System.out.print("<<"+book+">>\t");
        }
        System.out.println("\n爱好:"+hobbys);

        System.out.println("card:"+card);

        System.out.println("games:"+games);

        System.out.println("wife:"+wife);

        System.out.println("info:"+info);

        for (Address address:addressList){
            System.out.println(address.getAddress());
        }

        for (Address address:addressSet){
            System.out.println(address.getAddress());
        }

        for (Map.Entry entry:addressMap.entrySet()){
            System.out.print(entry.getKey() + "--" + ((Address)entry.getValue()).getAddress());
        }
    }

}

package main ;
import java.io.BufferedReader ;
import java.io.Serializable ;
import java.util.List ;
import java.util.Map ;
public abstract class User implements Serializable {
    private String name ;
    private String password ;
    private String role ; 
    public User(String name, String password, String role) {
        this.name = name ;
        this.password = password ;
        this.role = role ;
    }
    public String get_Name() {
        return name ;
    }
    public String get_Password() {
        return password ;
    }
    public String get_Role() {
        return role ;
    }
    public abstract void way(Map<String, User> users, List<Room> rooms, BufferedReader reader);
}
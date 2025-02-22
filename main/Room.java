package main ;
import java.io.Serializable ;
public class Room implements Serializable {
    private String name ;
    private double price ;
    private int privilege ;
    public Room(String name, double price, int privilege) {
        this.name = name ;
        this.price = price ;
        this.privilege = privilege ;
    }
    public String get_Name() {
        return name ;
    }
    public double get_Price() {
        return price ;
    }
    public int get_privilege() {
        return privilege ;
    }
    public void set_privileged(int privilege) {
        this.privilege = privilege ;
    }
    @Override
    public String toString() {
        return name + " | 价格: " + price + " | 剩余数量: " + privilege ;
    }
}
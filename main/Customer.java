package main ;
import java.io.BufferedReader ;
import java.util.List ;
import java.util.Map ;
public class Customer extends User {
    private boolean isVIP ;
    public Customer(String name , String password , boolean isVIP) {
        super(name , password , isVIP ? "VIP" : "regular") ;
        this.isVIP = isVIP ;
    }
    @Override
    public void way(Map<String , User> users, List<Room> rooms, BufferedReader reader) {
        int choice ;
        do {
            try {
                System.out.println("顾客操作:") ;
                System.out.println("1预定房间") ;
                System.out.println("2查询房间") ;
                System.out.println("3退出") ;
                System.out.print("请选择操作: ") ;
                choice = HotelManagementSystem.getIntInput(reader);
                switch (choice) {
                    case 1 :
                        Rooms_book(rooms, reader) ;
                        break ;
                    case 2:
                        Rooms_show(rooms) ;
                        break ;
                    case 3:
                        System.out.println("退出顾客模式") ;
                        break ;
                    default:
                        System.out.println("输入无效") ;
                }
            } 
            catch (Exception e) {
                System.out.println("发生异常: " + e.getMessage()) ;
                choice = -1 ; 
            }
        } 
        while (choice != 3) ;
    }
    private void Rooms_book(List<Room> rooms, BufferedReader reader) {
        try {
            System.out.println("请挑选心水的小舍:") ;
            for (int i = 0 ; i < rooms.size() ; i ++) {
                System.out.println((i + 1) + ". " + rooms.get(i).get_Name() + "||价格: " + rooms.get(i).get_Price() + "||剩余数量: " + rooms.get(i).get_privilege()) ;
            }
            int Rooms_offer = HotelManagementSystem.getIntInput(reader) ;
            if (Rooms_offer > 0 && Rooms_offer <= rooms.size()) {
                Room room = rooms.get(Rooms_offer - 1) ;
                if (room.get_privilege() > 0) {
                    room.set_privileged(room.get_privilege() - 1) ;
                    System.out.println("房间预定成功！") ;
                }
                else {
                    System.out.println("房间已满，无法预定！") ;
                }
            } 
            else {
                System.out.println("输入无效") ;
            }
        } 
        catch (Exception e) {
            System.out.println("错误: " + e.getMessage()) ;
        }
    }
    private void Rooms_show(List<Room> rooms) {
        for (Room room : rooms) {
            System.out.println(room.get_Name() + " || 价格: " + room.get_Price() + " || 剩余数量: " + room.get_privilege()) ;
        }
    }
}
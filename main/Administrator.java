package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
public class Administrator extends User {
    public Administrator(String name, String password) {
        super(name, password , "admin") ;
    }
    @Override
    public void way(Map<String , User> users , List<Room> rooms , BufferedReader reader) {
        int choice ;
        do {
            try {
                System.out.println("管理员操作:") ;
                System.out.println("1管理房间:增/删房间") ;
                System.out.println("2查询房间") ;
                System.out.println("3管理用户:增/删用户") ;
                System.out.println("4退出") ;
                System.out.print("请选择: ") ;
                choice = HotelManagementSystem.getIntInput(reader) ;
                switch (choice) {
                    case 1 :
                        Rooms_manage(rooms , reader) ;
                        break ;
                    case 2 :
                        Rooms_show(rooms) ;
                        break ;
                    case 3 :
                        Users_change(users , reader) ;
                        break ;
                    case 4:
                        System.out.println("退出管理员模式") ;
                        break ;
                    default:
                        System.out.println("输入无效") ;
                }
            } 
            catch (Exception e) 
            {
                System.out.println("错误: " + e.getMessage()) ;
                choice = -1 ; 
            }
        } 
        while (choice != 4) ;
    }
    private void Rooms_manage(List<Room> rooms, BufferedReader reader) {
        try {
            System.out.println("1增加房间") ;
            System.out.println("2删除房间") ;
            System.out.println("3返回上一步") ;
            System.out.print("请选择操作: ") ;
            int choice = HotelManagementSystem.getIntInput(reader) ;
            switch (choice) {
                case 1:
                    Room_add(reader , rooms) ;
                    break ;
                case 2:
                    Room_delete(reader , rooms) ;
                    break ;
                case 3:
                    System.out.println("返回上一步") ;
                    break;
                default:
                    System.out.println("无效的选择") ;
            }
        } 
        catch (Exception e) {
            System.out.println("发生错误: " + e.getMessage()) ;
        }
    }
    private void Room_add(BufferedReader reader, List<Room> rooms) {
        try {
            System.out.print("房间类型: ") ;
            String name = reader.readLine() ;
            System.out.print("房间价格: ") ;
            double price = Double.parseDouble(reader.readLine()) ;
            System.out.print("房间数量: ") ;
            int quantity = Integer.parseInt(reader.readLine()) ;
            rooms.add(new Room(name, price, quantity)) ;
            System.out.println("添加成功！") ;
        } 
        catch (IOException Exception) {
            System.out.println("内容错误: " + Exception.getMessage()) ;
        } 
        catch (NumberFormatException e) {
            System.out.println("格式错误，请输入有效的数字。") ;
        }
    }
    private void Room_delete(BufferedReader reader, List<Room> rooms) {
        try {
            System.out.println("请选择要删除的房间:") ;
            for (int i = 0 ; i < rooms.size() ; i ++) {
                System.out.println((i + 1) + ". " + rooms.get(i).get_Name()) ;
            }
            int room_predelete = HotelManagementSystem.getIntInput(reader) ;
            if (room_predelete > 0 && room_predelete <= rooms.size()) {
                rooms.remove(room_predelete - 1);
                System.out.println("房间删除成功！") ;
            } else {
                System.out.println("输入无效") ;
            }
        } 
        catch (NumberFormatException e) {
            System.out.println("格式错误，请重输入") ;
        }
    }
    private void Rooms_show(List<Room> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("小舍暂未搭建，还请探访下间寒舍。") ;
        } 
        else {
            for (Room room : rooms) {
                System.out.println(room.get_Name() + " || 价格: " + room.get_Price() + " ||剩余数量: " + room.get_privilege()) ;
            }
        }
    }
    private void Users_change(Map<String, User> users, BufferedReader reader) {
        try {
            System.out.println("1增加用户") ;
            System.out.println("2删除用户") ;
            System.out.print("请输入要进行的操作前的数字: ") ;
            int choice = HotelManagementSystem.getIntInput(reader) ;
            reader.readLine() ;
            if (choice == 1) {
                System.out.print("请输入安舍昵称: ") ;
                String name = reader.readLine() ;
                System.out.print("请输入密码: ") ;
                String password = reader.readLine() ;
                System.out.print("用户角色(1管理员 2顾客): ") ;
                int role = HotelManagementSystem.getIntInput(reader);
                if (role == 1) {
                    users.put(name, new Administrator(name, password));
                } 
                else if (role == 2) {
                    users.put(name, new Customer(name, password, false));
                }
                System.out.println("用户添加成功！");
            } 
            else if (choice == 2) {
                System.out.print("请输入要删除的用户名: ");
                String username = reader.readLine();
                if (users.containsKey(username)) {
                    users.remove(username);
                    System.out.println("用户删除成功！") ;
                } 
                else {
                    System.out.println("用户不存在！") ;
                }
            }
        } 
        catch (IOException e) {
            System.out.println("输入时发生错误: " + e.getMessage());
        }
    }
}
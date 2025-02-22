package main ;
import java.io.*;
import java.util.*;
public class HotelManagementSystem {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        PeacefulPremises.welcomeMessage();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"))) {
            // 加载用户和房间信息
            Map<String, User> users = loadUsers();
            List<Room> rooms = loadRooms();
            // 默认管理员账号
            users.putIfAbsent("JianyiJian", new Administrator("JianyiJian", "123456"));
            System.out.println("当前用户列表: " + users);
            int choice;
            do {
                try {
                    System.out.println("0. 返回上一步");
                    System.out.println("1. 登录");
                    System.out.println("2. 退出");
                    System.out.print("请选择操作: ");
                    choice = getIntInput(reader);
                    switch (choice) {
                        case 1:
                            login(users, rooms, reader);
                            break;
                        case 2:
                            System.out.println("退出系统。");
                            break;
                        default:
                            System.out.println("无效的选择，请重新选择。");
                    }
                } 
                catch (Exception e) 
                {
                    System.out.println("发生错误: " + e.getMessage());
                    choice = -1; 
                }
            } 
            while (choice != 2) ;
            saveUsers(users) ;
            saveRooms(rooms) ;
        } 
        catch (Exception e) {
            System.out.println("发生错误: " + e.getMessage());
        }
    }
    private static void login(Map<String, User> users, List<Room> rooms, BufferedReader reader) {
        try {
            System.out.println("请输入用户名:") ;
            String username = reader.readLine().trim() ; 
            System.out.println("请输入密码:") ;
            String password = reader.readLine().trim() ; 
            System.out.println("输入的用户名: [" + username + "]");
            System.out.println("输入的密码: [" + password + "]");
            User user = users.get(username);
            if (user != null && user.get_Password().equals(password)) {
                System.out.println("登录成功！");
                user.way(users, rooms, reader); // 将 reader 传递给 accessSystem 方法
            } else {
                System.out.println("登录失败，用户名或密码错误！");
            }
        } 
        catch (IOException e) {
            System.out.println("输入时发生错误: " + e.getMessage());
        }
    }
    private static void saveUsers(Map<String, User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.out.println("保存用户信息时出错: " + e.getMessage());
        }
    }
    private static void saveRooms(List<Room> rooms) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("rooms.dat"))) {
            out.writeObject(rooms);
        } catch (IOException e) {
            System.out.println("保存房间信息时出错: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    private static Map<String, User> loadUsers() {
        File file = new File("users.dat");
        if (!file.exists()) return new HashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("加载用户信息时出错: " + e.getMessage());
            return new HashMap<>();
        }
    }
    private static List<Room> loadRooms() {
        File file = new File("rooms.dat");
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Room>) in.readObject() ;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("加载房间信息时出错: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public static int getIntInput(BufferedReader reader) {
        while (true) {
            try {
                String input = reader.readLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("请输入一个有效的整数:");
            } catch (IOException e) {
                System.out.println("输入时发生错误: " + e.getMessage());
            }
        }
    }
}

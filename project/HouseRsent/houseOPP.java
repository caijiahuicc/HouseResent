package project.HouseRsent;

import java.time.Year;
import java.util.Scanner;




/**
 * House的对象表示一个房屋属性
 **/
class House{
    //编号   房主    电话    地址    月租   状态（出租/未出租）
    private int id;
    private String name;
    private String phone;
    private String address;
    private int rent;
    private String state;

    public House(int id, String name, String phone, String address, int rent, String state) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.rent = rent;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    //为了方便实现对象输出，使用toString

    @Override
    public String toString() {
//        return "\t编号：" + id + '\n'+
//                "\t房主：" + name + '\n' +
//                "\t电话：" + phone + '\n' +
//                "\t地址：" + address + '\n' +
//                "\t房租：" + rent +'\n'+
//                "\t状态：" + state + '\n';
        return id+"\t\t"+name+"\t\t"+phone+"\t\t"+address+"\t\t"+rent+"\t\t"+state;
    }
}



/**
 * 1，编写mainMenu方法，可以显示主菜单
 */
class HouseView{
    private boolean loop = true;//控制显示菜单
    private String key = " ";//接收用户选择
    HouseService houseService = new HouseService(10);
    Scanner scanner = new Scanner(System.in);



    //编写listHouse显示房屋列表
    public void listHouse(){
        System.out.println("---------------房屋列表--------------");
        System.out.println("编号：\t\t房主：\t\t电话：\t\t地址：\t\t月租：\t\t状态（出租/未出租）；\t\t");
        House[] houses = houseService.list();//得到所有房屋信息
        for (int i = 0;i < houses.length;i++){
            if(houses[i] == null){//如果House的信息为空，就不输出
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("---------------房屋列表显示完毕--------------");
    }

    //addHouse()方法接收用户输入,创建House对象，调用add方法
    public void addHouse(){
        System.out.println("-----------------------添加房源-----------------------");
        System.out.println("姓名：");
        String name = scanner.next();
        System.out.println("电话：");
        String phone = scanner.next();
        System.out.println("地址：");
        String address = scanner.next();
        System.out.println("月租：");
        int rent = scanner.nextInt();
        System.out.println("状态（出租/未出租）：");
        String state = scanner.next();
        //创建一个新的House对象,id是系统分配的，用户不能输入
        House newHouse = new House(0, name, phone, address, rent, state);
        if(houseService.add(newHouse)){
            System.out.println("----------------添加房屋成功------------------");
        }else
            System.out.println("-----------------添加房屋失败------------------");
    }


    //编写删除房源，创建House对象，调用del方法
    public void delHouse(){
        System.out.println("--------------------删除房屋------------------");
        System.out.println("请选择待删除的房屋的编号（-1退出）：");
        int id = scanner.nextInt();
        if(id == -1){
            System.out.println("-------------你放弃了删除房屋信息-------------");
            return;
        }
        System.out.println("确定是否删除（Y/N)：");
        String choice1 = scanner.next();
        if(choice1.equals("Y")){
            System.out.println("请小心选择！");
        }else {
            System.out.println("---------------你放弃了删除！--------------");
        }
        System.out.println("请输入你的选择（Y/N）：");
        String choice2 = scanner.next();
            if (houseService.del(id)) {
                System.out.println("------------------删除房屋信息成功-------------");
            } else
                System.out.println("--------------------删除房屋失败，未找到房屋编号---------");
    }



    public void findHouse(){
        System.out.println("-------------------查找房屋信息--------------");
        System.out.println("请输入要查找的Id：");
        int findId = scanner.nextInt();
        House house = houseService.findById(findId);
        if(house != null){
            System.out.println(house);
        }else {
            System.out.println("未查到相关信息");
        }
    }


    //根据Id修改房屋信息
    //返回的是引用类型（就是数组的元素），再后面对house.SetXX(),就会修改HouseService中的数组元素
    public void updateHouse(){
        System.out.println("-----------------修改房屋信息-----------------");
        System.out.println("请输入要修改房屋的编号（-1表示退出：");
        int choice = scanner.nextInt();
        if(choice == -1){
            System.out.println("你放弃修改房屋信息");
            return;
        }
        //根据输入的Id查找
        House house = houseService.findById(choice);
        if (house == null){
            System.out.println("你输入的信息有误");
            return;
        }
        System.out.println("姓名：("+house.getName()+"):");
        String name = scanner.next();//这里用户如果直接回车表示不修改信息，默认为null；
        if(! " ".equals(name)){
            house.setName(name);
        }

        System.out.println("电话：("+house.getPhone()+"):");
        String phone = scanner.next();//这里用户如果直接回车表示不修改信息，默认为null；
        if(! " ".equals(phone)){
            house.setPhone(phone);
        }


        System.out.println("地址：("+house.getAddress()+"):");
        String address = scanner.next();//这里用户如果直接回车表示不修改信息，默认为null；
        if(! " ".equals(address)){
            house.setAddress(address);
        }



        System.out.println("租金：("+house.getRent()+"):");
        int rent = scanner.nextInt();//这里用户如果直接回车表示不修改信息，默认为null；
        if(rent != -1){
            house.setRent(rent);
        }


            System.out.println("状态：("+house.getState()+"):");
            String state = scanner.next();//这里用户如果直接回车表示不修改信息，默认为null；
            if(! " ".equals(state)){
                house.setState(state);
            }
            System.out.println("-----------修改成功----------");



        }




    public void mainMenu() {
        do {
            System.out.println("---------------房屋出租系统--------------");
            System.out.println("\t\t\t1，新增房源");
            System.out.println("\t\t\t2，查找房屋");
            System.out.println("\t\t\t3，删除房屋");
            System.out.println("\t\t\t4，修改房屋信息");
            System.out.println("\t\t\t5，房屋列表");
            System.out.println("\t\t\t6，退    出");
            System.out.print("请输入你的选择(1~6)：");
            key = scanner.next();
            switch (key){
                case "1":
                    //System.out.println("新增");
                    addHouse();
                    break;
                case "2":
                    findHouse();
                    break;
                case "3":
                    delHouse();
                    break;
                case "4":
                    updateHouse();
                    break;
                case "5":
                    listHouse();
                    break;
                case "6":
                    System.out.println("你确定要退出吗（Y/N）？");
                    String choice = scanner.next();
                if(choice.equals("Y")){
                    loop = false;
                    break;
                }else {
                    break;
                }
                default:
                    System.out.println("你的输入有误，请重新输入！！！");
            }
        } while (loop);
    }
}



/**
 * 1，编写服务方法
 */
class HouseService {
    private House[] houses;//存放House对象；
    private int houseMums = 1;//记录当前有多少房屋信息；
    private int idCounter = 1;//表示当前房源id已经增加到多少了；


    public HouseService(int size) {
        houses = new House[size];//当创建一个HouseService对象，指定数组大小。
        //初始化一个House对象
        houses[0] = new House(1, "Jack", "13098672",
                "汤臣一品", 30000, "未出租");
    }

    //list方法，返回所有的房屋信息
    public House[] list() {
        return houses;
    }

    //add添加新对象
    public boolean add(House newHouse) {
        //判断是否还可以继续添加，不考虑数组扩容
        if (houseMums >= houses.length) {//已满，不能在添加
            System.out.println("数组已满，不能再添加了");
            return false;
        }
        //把newHouse对象加到数组当前数的前面
        houses[houseMums++] = newHouse;//新增加了一个房源
        //设计一个Id自增长的机制
        newHouse.setId(++idCounter);
        return true;
    }


    //创建一个del方法，删除房屋信息
    public boolean del(int delId) {
        //先找到删除的房屋信息对应的下标。要清楚下标和房屋的编号不是一回事，
        int index = -1;
        for (int i = 0; i < houseMums; i++) {
            if (delId == houses[i].getId()) {//要删除的房屋（id），是数组下标为1的元素
                index = i;//使用index记录i;
            }
        }
        if (index == -1) {//说明delId在数组中不存在
            return false;
        }
        for (int i = index; i < houseMums - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[--houseMums] = null;//把当前存在的房屋信息的最后一个置空
        return true;
    }




    public House findById(int findId) {
        for (int i = 0; i < houseMums; i++) {
            if (findId == houses[i].getId()) {
                return houses[i];
            }
        }
        return null;
    }
}
package project.SmallChange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/***
 1，该类是完成零钱通各个功能的类，使用面像对象编程（OOP）
 2，将各个功能对应方法
 */

public class OOP{
    //定义相关变量
    Scanner scanner = new Scanner(System.in);
    String key = "";
    String detile = "--------------零钱通明细--------------";
    double money = 0;
    double balance = 0;
    //Date date = null;
    boolean loop = true;
    Date date = new Date();

    //新变量接收消费的理由
    String note = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//可以用于日期格式化
    public void mainMenu(){
        do{
            System.out.println("====零钱通菜单OOP版====");
            System.out.println("\t\t\t1，零钱通明细");
            System.out.println("\t\t\t2，收益入账");
            System.out.println("\t\t\t3，消费");
            System.out.println("\t\t\t4，退   出");
            System.out.print("请选择（1~4）:");
            key = scanner.next();

            //使用switch分支结构
            switch (key){
                case "1":
                    this.detail();
                    break;
                case "2":
                    this.income();
                    break;
                case "3":
                    this.pay();
                    break;
                case "4":
                    //定义变量判断输入选择
                    this.exit();
                    break;
                default:
                    System.out.println("选择有误，请重新选择");
            }
        }while (loop);
    }

    public void detail(){//零钱通明细
        System.out.println(detile);
    }

    public void income() {
        System.out.print("收益入账金额：");
        money = scanner.nextDouble();
        if (money < 0) {
            System.out.println("收益入账范围有误应该为正值");
            return;//退出方法，不再执行后面的代码
        } else {
            balance += money;
            //拼接收益入账信息到detail
            detile += "\n收益入账\t" + money + "\t" + sdf.format(date) + "\t" + balance;
        }
    }

    public void pay(){
        System.out.print("消费金额：");
        money = scanner.nextInt();
        System.out.print("请输入消费说明：");
        note = scanner.next();
        balance -= money;
        if(balance < 0){
            System.out.println("您的余额不足！");
        }else {
            detile += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + balance;
        }
    }

    public void exit(){
        String choice = "";
        while (true){//要求用户必须输入Y或者N,否则死循环。
            System.out.print("你确定要退出吗？");
            choice = scanner.next();
            if ("y".equals(choice) ||"n".equals(choice)){
                break;
            }
        }
        //当用户退出while后再判断输入的是Y or N,就可以决定是否退出
        if(choice.equals("y")) {
            loop = false;
        }else if(choice.equals("n")){

        }else {
            System.out.print("你的输入有误，请重新输入");
        }
    }
}

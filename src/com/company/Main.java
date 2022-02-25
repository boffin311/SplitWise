package com.company;

import com.company.model.UserModel;
import com.company.services.TransactionServices;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void printStatement(ArrayList<String> statementList){
        for(String statement: statementList){
            System.out.println(statement);
        }
    }
    public static void main(String[] args) {
	// write your code here
        UserModel first= new UserModel("u1","Himanshu", "himanshu@gmail.com", "987654321");
        UserModel second= new UserModel("u2","Rajan", "rajan@gmail.com", "987654321");
        UserModel third= new UserModel("u3","Ankush", "ankush@gmail.com", "987654321");
        UserModel forth= new UserModel("u4","Anant", "anant@gmail.com", "987654321");

        Scanner sc = new Scanner(System.in);
        ArrayList<String > userIdList= new ArrayList<>();
        userIdList.add("u2");
        userIdList.add("u3");
        userIdList.add("u4");
        ArrayList<UserModel> usersList = new ArrayList<>();
        usersList.add(first);
        usersList.add(second);
        usersList.add(third);
        usersList.add(forth);
        TransactionServices services = new TransactionServices(usersList);
        services.divideSumEquallyToAllUsers(userIdList, 1000, "u1");
        sc.forEachRemaining(input-> {
            String[] inputSplitAlongSpace = input.split(" ");
            if (Objects.equals(inputSplitAlongSpace[0], "SHOW")) {
                if (inputSplitAlongSpace.length == 1) {
                    printStatement(services.getCurrentBalancesForAllUsers());
                } else {
                    System.out.println("here");
                    printStatement(services.getCurrentBalancesForParticularUser(inputSplitAlongSpace[1]));
                }
            } else if (Objects.equals(inputSplitAlongSpace[0], "EXPENSE")) {

            }
        });


    }
}

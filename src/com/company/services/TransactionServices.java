package com.company.services;

import com.company.model.UserModel;
import com.company.model.UsersRelationModel;

import java.util.ArrayList;
import java.util.Objects;

public class TransactionServices {
    private UsersRelationModel usersRelationModel = new UsersRelationModel();
    private ArrayList<UserModel> users= new ArrayList<UserModel>();
    public TransactionServices(ArrayList<UserModel> users){
        usersRelationModel.setUsersRelationMap(users);
        this.users = users;
    }

    private void updateAmountBasedOnPriceAndKey(int priceToUpdate, String userWhoPaid, String userWhoOwe){
        if(Objects.equals(userWhoOwe, userWhoPaid)) return;
        String first = usersRelationModel.makeUniqueIdFromUsers(userWhoOwe, userWhoPaid);
        String second = usersRelationModel.makeUniqueIdFromUsers(userWhoPaid, userWhoOwe);

        if(usersRelationModel.getUsersRelationMap().containsKey(first)){
            int amountTillThisTime= usersRelationModel.getUsersRelationMap().get(first);
            usersRelationModel.getUsersRelationMap().put(first, amountTillThisTime - priceToUpdate);
        }else{
            int amountTillThisTime= usersRelationModel.getUsersRelationMap().get(second);
            usersRelationModel.getUsersRelationMap().put(second, amountTillThisTime + priceToUpdate);
        }
    }
    public void divideSumEquallyToAllUsers(ArrayList<String> userIdList,int amountPaid, String userWhoPaid){
        int amountPerUser= amountPaid/ userIdList.size();
          for(String userId:userIdList){
             updateAmountBasedOnPriceAndKey(amountPerUser, userWhoPaid, userId);
          }
    }
    private Boolean isValidPercentage(ArrayList<Integer> percentageList){
        int percentageSum=0;
        for(Integer percent : percentageList){
            percentageSum +=percent;
        }
        return percentageSum == 100;
    }
    private Boolean isExactAmountEqualToPaidAmount(ArrayList<Integer> exactAmountList, int amountPaid){
        int totalAmount=0;
        for(Integer amount: exactAmountList){
            totalAmount+= amount;
        }
        return amountPaid == totalAmount;
    }
    public void divideSumBasedOnPercentage(ArrayList<String> userIdList, int amountPaid, ArrayList<Integer> percentageList,String userWhoPaid){
        if(!isValidPercentage(percentageList)) return ;// We can return with a message also like "Sorry Enter a valid Percentage"
        for(int i=0;i<userIdList.size();++i){
            int amountToAdd = (amountPaid * percentageList.get(i))/100;
            updateAmountBasedOnPriceAndKey(amountToAdd,userWhoPaid,userIdList.get(i) );
        }

    }
    public void divideSumBasedOnExactAmount(ArrayList<String> userIdList,int amountPaid, ArrayList<Integer> exactAmountList, String userWhoPaid){
        if(!isExactAmountEqualToPaidAmount(exactAmountList,amountPaid )) return ;//We can return with a message also like "Sorry Enter a valid Amount"
        for(int i=0;i<userIdList.size(); ++i){
            updateAmountBasedOnPriceAndKey(exactAmountList.get(i), userWhoPaid, userIdList.get(i));
        }
    }

    public ArrayList<String> getCurrentBalancesForAllUsers(){
        return usersRelationModel.getHowMuchUserOwesToEachOther();
    }
    public ArrayList<String> getCurrentBalancesForParticularUser(String userId){
        return usersRelationModel.getHowMuchUserOwesToOthers(userId);
    }
}

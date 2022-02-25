package com.company.model;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//This is a model to relate each User with another one so that we can manage there expenses
public class UsersRelationModel {
    private HashMap<String, Integer> usersRelationMap = new HashMap<>();

    public String makeUniqueIdFromUsers(String firstUser, String secondUser) {
        return firstUser + "#" + secondUser;
    }

    public void setUsersRelationMap(ArrayList<UserModel> usersList) {

        for (int i = 0; i < usersList.size(); ++i) {
            for (int j = i + 1; j < usersList.size(); ++j) {
                String key = makeUniqueIdFromUsers(usersList.get(i).getUserId(), usersList.get(j).getUserId());
                usersRelationMap.put(key, 0);
            }
        }
    }

    private String getStatementBasedOnAmountRemaining(int value, String firstUser, String secondUser) {
        String printingStatement = "";
        if (value < 0) printingStatement = secondUser + " owes " + Math.abs(value) + " from " + firstUser;
        else if (value > 0) printingStatement = firstUser + " owes " + value + " from " + secondUser;
        return printingStatement;
    }

    public HashMap<String, Integer> getUsersRelationMap() {
        return usersRelationMap;
    }

    public ArrayList<String> getHowMuchUserOwesToOthers(String userId) {
        ArrayList<String> result = new ArrayList<>();
        usersRelationMap.forEach((key, value) -> {
            if (value != 0) {
                String[] userKey = key.split("#");
                if (Objects.equals(userKey[0],userId) || Objects.equals(userKey[1], userId)) {
                    String printingStatement = getStatementBasedOnAmountRemaining(value, userKey[0], userKey[1]);
                    result.add(printingStatement);
                }
            }

        });
        return result;
    }

    public ArrayList<String> getHowMuchUserOwesToEachOther() {
        ArrayList<String> listOfStatement = new ArrayList<>();
        usersRelationMap.forEach((key, value) -> {
            if (value != 0) {
                String[] userKey = key.split("#");
                String statement = getStatementBasedOnAmountRemaining(usersRelationMap.get(key), userKey[0], userKey[1]);
                listOfStatement.add(statement);
            }

        });
        return listOfStatement;
    }
}

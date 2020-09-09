package utility;

import java.util.HashMap;

public class UserRoles {

    public String Roles(String username){

        String userRoles;

        HashMap<String, String> map = new HashMap<>();
        map.put("sq637","POWER USER");
        map.put("sx435","QA");
        map.put("su545","Curator");

        if(map.containsKey(username)){
            userRoles=map.get(username);
            return userRoles;
        }else {
            System.out.println(username + "is not valid");
            userRoles = "InValid";
            return userRoles;
        }



    }

}

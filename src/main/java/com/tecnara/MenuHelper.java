package com.tecnara;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MenuHelper {

    Dao dao=new Dao();

    public void loadUsers()  {
        for (User usuario:dao.loadUsers()) {
            System.out.println(usuario.toString());
        }


    }

    public void loadUser(int id) {

        System.out.println(dao.loadUser(id).toString());
    }

    public void addUser(User user) {
        System.out.println(dao.addUser(user));

    }
    public void updateUser(int id, String columnModify, String newValue){

        User user=dao.loadUser(id);
        System.out.println(dao.modifyUser(user,columnModify,newValue));

    }

    public void removeUser(int id){
        System.out.println(dao.deleteUser(id));

    }

    public void filterusersAge(int age){
        List<User> listUser= dao.loadUsers();
        listUser.stream().filter(user -> user.getAge()==age).sorted((o1, o2) -> Integer.compare(o2.getAge(), o1.getAge())).forEach(System.out::println);

    }

    public void filterUsersName(String name){
        List<User> listUser= dao.loadUsers();
        listUser.stream().filter(user -> user.getName().equalsIgnoreCase(name)).forEach(System.out::println);
    }

    public void filterUsersSalary(float salary){
        List<User> listUser= dao.loadUsers();
        listUser.stream().filter(user -> user.getSalary()==salary).forEach(System.out::println);
    }

    public void filterUserMaxSalary(){
        List<User> listUser= dao.loadUsers();
        Optional<User> userMaxSalary = listUser.stream().max((user1, user2)-> Float.compare(user1.getSalary(), user2.getSalary()));

        userMaxSalary.ifPresent(user -> System.out.println(user.toString()));

    }

    public void filterUserMaxAge(){
        List<User> listUser= dao.loadUsers();
        Optional<User> maxAge = listUser.stream().max(Comparator.comparingInt(User::getAge));

        maxAge.ifPresent(user -> System.out.println(user.toString()));

    }

    public void filterUsersRangeSalary(float max, float min){
        List<User> listUser= dao.loadUsers();
        listUser.stream().filter(user->(user.getSalary()<=max && user.getSalary()>=min)).sorted((o1, o2) -> Float.compare(o2.getSalary(), o1.getSalary())).forEach(System.out::println);



    }
    public void filterUsersRangeAge(int maxRangeAge, int minRangeAge) {
        List<User> listUser= dao.loadUsers();
        listUser.stream().filter(user->(user.getAge()<=maxRangeAge && user.getAge()>=minRangeAge)).sorted(Comparator.comparingInt(User::getAge)).forEach(System.out::println);

    }
}

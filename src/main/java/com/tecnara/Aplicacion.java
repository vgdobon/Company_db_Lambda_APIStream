package com.tecnara;

import com.tecnara.utils.Utils;

import java.util.Scanner;

public class Aplicacion {

    MenuHelper menuHelper= new MenuHelper();
    Scanner scanner= new Scanner(System.in);
    boolean closeApp=false;

    public void ejecutar()  {
        System.out.println("Bienvenido a la aplicación de su Empresa");
        scanner.useDelimiter("\n");


        while (!closeApp){
            switch (mostrarMenu()){

            case 1:
                menuHelper.loadUsers();

                break;
            case 2:

                System.out.println("User name:");
                String newUserName=scanner.next();


                System.out.println("User age:");
                int newUserAge=scanner.nextInt();

                System.out.println("User salary:");
                float newUserSalary=scanner.nextFloat();

                User user=null;

                if(!(newUserName.equals("") ||newUserAge<0 || newUserAge>120||newUserSalary<900)){
                    user= new User(newUserName,newUserAge,newUserSalary);
                }else{
                    System.out.println("\n" +
                            "You have not entered the correct data");
                }
                if(user!=null){
                    menuHelper.addUser(user);
                }

                break;

            case 3:

                menuHelper.loadUsers();

                System.out.println("Which user do you want to delete?");
                int idUserRemove=scanner.nextInt();
                menuHelper.removeUser(idUserRemove);

                break;

            case 4:
                menuHelper.loadUsers();

                System.out.println("Write user´s id which you want to update");
                int id=scanner.nextInt();
                menuHelper.loadUser(id);

                System.out.println("Write user´s attribute which you want to update:");
                String attibuteToActt= scanner.next();

                System.out.println("Write the new value");
                String newValue= scanner.next();



                menuHelper.updateUser(id,attibuteToActt,newValue);

                break;

            case 5:
                System.out.println("Write name:");
                String filterUserName=scanner.next();

                menuHelper.filterUsersName(filterUserName);

                break;

            case 6:
                System.out.println("Write age:");
                int filterUserAge=scanner.nextInt();

                menuHelper.filterusersAge(filterUserAge);

                break;

            case 7:
                System.out.println("Write salary:");
                float filterUserSalary=scanner.nextFloat();

                menuHelper.filterUsersSalary(filterUserSalary);

                break;

            case 8:

                menuHelper.filterUserMaxSalary();

                break;

            case 9:

                menuHelper.filterUserMaxAge();

                break;

            case 10:

                System.out.println("Write max range´s age");
                int maxRangeAge=scanner.nextInt();

                System.out.println("Write min range´s age");
                int minRangeAge=scanner.nextInt();

                menuHelper.filterUsersRangeAge(maxRangeAge,minRangeAge);
                break;

            case 11:
                System.out.println("Write max range´s salary");
                float maxRangeSalary=scanner.nextFloat();

                System.out.println("Write min range´s salary");
                float minRangeSalary=scanner.nextFloat();

                menuHelper.filterUsersRangeSalary(maxRangeSalary,minRangeSalary);
                break;


            default:
                closeApp=true;

            }
        }

    }

    public int mostrarMenu(){
        String opcionMenu;
        int opcion = -1;
        boolean isOptionValid=false;
        do {
            System.out.println("Choose an option:\n" +
                    "1.View users\n" +
                    "2.Add user\n" +
                    "3.Remove user\n" +
                    "4.Update user\n" +
                    "5.Filter user\n" +
                    "0.Close app\n");

            opcionMenu = scanner.next();
            if(Utils.isNumeric(opcionMenu) ){
                opcion=Integer.parseInt(opcionMenu);
                if(opcion>=0 && opcion<6){
                    isOptionValid=true;
                }
            }


        }while (!isOptionValid);



        if(opcion==5){
            opcion=filterMenu();
        }

        return opcion;
    }

    private int filterMenu() {
        String opcionMenu;
        int opcion = -1;
        boolean isOptionValid=false;
        do{
            System.out.println("Choose filter option:\n" +
                    "5.Filter by name\n" +
                    "6.Filter by age\n" +
                    "7.Filter by salary\n" +
                    "8.Filter by max salary\n" +
                    "9.Filter by max age\n" +
                    "10.Filter by age range\n"+
                    "11.Filter by salary range\n");
            opcionMenu = scanner.next();

            if(Utils.isNumeric(opcionMenu)){
                opcion=Integer.parseInt(opcionMenu);
                if(opcion>4 && opcion<12){
                    isOptionValid=true;
                }
            }



        }while (!isOptionValid);



        return opcion;
    }

}

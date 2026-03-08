package bai3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class b3 {
    private String id;
    private String name;
    private Date dob;

    public b3(String id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public b3() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    public void inputData(Scanner sc){
        this.id=inputId(sc);
        this.name=inputName(sc);
        this.dob=inputDob(sc);

    }
    public void displayData(){
    }
    public String inputId(Scanner sc){
        do {
            System.out.print("Nhập Id: ");
            String id = sc.nextLine();
            if(id.matches("SV\\d{3}")){
                return id;
            }
            System.err.println("Vui lòng nhập đúng định dạng");
        }while(true);
    }
    public String inputName(Scanner sc){
        do {
            System.out.print("Nhập tên: ");
            String name = sc.nextLine();
            if (name.trim().length()>0&&name.matches("[a-zA-Z]+")){
                return name;
            }
            System.err.println("Vui lòng nhập đúng định dạng");
        }while(true);
    }
    public Date inputDob(Scanner sc){
        do {
            System.out.print("Nhập ngày sinh: ");
            String dob = sc.nextLine();
            Date d=null;
            try{
                d= new SimpleDateFormat("dd/MM/yyyy").parse(dob);

            }catch(ParseException e){
                System.err.println("Vui lòng nhập đúng định dạng");
            }
            return d;

        }while(true);
    }
}

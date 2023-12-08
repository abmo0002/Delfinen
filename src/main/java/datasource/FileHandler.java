package datasource;

import domain_model.Member;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class FileHandler {

    Database database = null;

    public void setDatabase(Database database) {
        this.database = database;
    }


    public void saveData(ArrayList<Member> members) throws FileNotFoundException {

        if (database.isChangesMade() == true) {
            PrintStream output = new PrintStream(new File("memberDatabase.csv"));

            for (Member member : members) {
                output.print(member.getFirstName());
                output.print(";");
                output.print(member.getLastName());
                output.print(";");
                output.print(member.getAge());
                output.print(";");
                output.print(member.isActive());
                output.print(";");
                output.print(member.isCompetitive());
                output.print(";");
                output.print(member.getIdNumber());
                output.print(";");
                output.print(member.isJunior());
                output.println();


            }
            output.flush();
            output.close();
        }
        database.setChangesMade(false);
    }

    public ArrayList<Member> loadData() throws FileNotFoundException {

        Scanner reader = new Scanner(new File("memberDatabase.csv"));

        ArrayList<Member> members = new ArrayList<>();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            System.out.println(line);

            Member memberObject = csvLine(line);

            members.add(memberObject);
        }
        return members;
    }

    private Member csvLine(String line) {
        String[] parts = line.split(";");

        Member member = new Member();

        member.setFirstName(parts[0]);
        member.setLastName(parts[1]);
        int age = Integer.parseInt(parts[2]);
        member.setAge(age);
        boolean membershipsStatus = Boolean.parseBoolean(parts[3]);
        member.setActive(membershipsStatus);
        boolean swimType = Boolean.parseBoolean(parts[4]);
        member.setCompetitive(swimType);
        int idNumber = Integer.parseInt(parts[5]);
        member.setIdNumber(idNumber);
        boolean membershipAgeGroup = Boolean.parseBoolean(parts[6]);
        member.setJunior(membershipAgeGroup);

        return member;

    }
}

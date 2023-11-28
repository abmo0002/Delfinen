package userInterface;

import java.util.ArrayList;

import domain_model.Coach;
import domain_model.Controller;
import domain_model.Database;
import domain_model.Member;
import domain_model.TrainingResult;
import domain_model.CompetitionResult;

import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInterface {
    private Scanner scanner;
    private Database database;
    private Controller controller;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.database = new Database();
        this.controller = new Controller(database);
    }

    public void start() {
        controller.createTestData();
        controller.createTestDataCoach();
        controller.createTournamentResultTestData();
        controller.createPracticeResultTestData();
        boolean menuError;

        do {
            do {
                startPage();
                try {
                    int menuChoice = scanner.nextInt();
                    if (menuChoice == 1) {
                        createMember();
                    } else if (menuChoice == 2) {
                        seeMembers();
                    } else if (menuChoice == 3) {
                        searchMembers();
                    } else if (menuChoice == 4) {
                        editMember();
                    } else if (menuChoice == 5) {
                        deleteMember();
                    } else if (menuChoice == 6) {
                        saveMembers();
                    } else if (menuChoice == 7) {
                        loadMembers();
                    } else if (menuChoice == 8) {
                        seeTeams();
                    } else if (menuChoice == 9) {
                        createResult();
                    } else if (menuChoice == 10) {
                        seeResults();
                    } else if (menuChoice == 11) {
                        endProgram();
                    }
                    menuError = false;
                } catch (InputMismatchException ime) {
                    System.out.println("Skriv et tal");
                    scanner.nextLine();
                    menuError = true;
                }
            } while (menuError);
        } while (true);
    }

    public void startPage() {

        System.out.println("\nMENU: ");
        System.out.println("1. Opret medlem");
        System.out.println("2. Se medlem");
        System.out.println("3. Søg efter medlem");
        System.out.println("4. Rediger medlem");
        System.out.println("5. Slet medlem");
        System.out.println("6. Gem indtastede information");
        System.out.println("7. Load sidste indtastede information");
        System.out.println("8. Se hold");
        System.out.println("9. Registrere resultater ");
        System.out.println("10. Se resultater ");
        System.out.println("11. Afslut program");
        System.out.println("Vælg en valgmulighed: ");

    }

    public void createMember() {
        boolean writingError = false;
        scanner.nextLine();
        System.out.println("Indtast fornavn på nyt medlem: ");
        String firstName = scanner.nextLine();

        System.out.println("Indtast efternavn");
        String lastName = scanner.nextLine();

        System.out.println("Indtast svømmetype. Indtast 'm' for motionist eller 'k' for konkurrencesvømmer");
        boolean competitive = true;
        char swimTypeAnswer;
        do {
            swimTypeAnswer = scanner.nextLine().charAt(0);
            if (swimTypeAnswer == 'k') {
                competitive = true;
                break;
            } else if (swimTypeAnswer == 'm') {
                competitive = false;
                break;
            } else
                System.out.println("Tastefejl, tast 'm' eller 'k'");

        } while (swimTypeAnswer != 'm' || swimTypeAnswer != 'k');


        System.out.println("Indtast alder");
        int age = 0;
        do {
            try {
                age = scanner.nextInt();
                scanner.nextLine();
                writingError = false;
            } catch (InputMismatchException e) {
                System.out.println("Tastefejl, indtast kun tal");
                scanner.nextLine();
                writingError = true;
            }
        } while (writingError);


        System.out.println("Indtast medlems aktivitet i svømmeklubben. Indtast 'a' for aktiv eller 'p' for passiv.");
        boolean active = true;
        char activityAnswer;
        do {
            activityAnswer = scanner.nextLine().charAt(0);
            if (activityAnswer == 'a') {
                active = true;
                break;
            } else if (activityAnswer == 'p') {
                active = false;
                break;
            } else
                System.out.println("Tastefejl, tast 'a' eller 'p'");

        } while (activityAnswer != 'a' || activityAnswer != 'p');


        System.out.println("Indtast medlemsnummer");
        int idNumber = 0;
        do {
            try {
                idNumber = scanner.nextInt();
                scanner.nextLine();
                writingError = false;
            } catch (InputMismatchException e) {
                System.out.println("Tastefejl, indtast kun tal");
                scanner.nextLine();
                writingError = true;
            }
        } while (writingError);

        controller.createMember(firstName, lastName, competitive, age, active, idNumber);
        controller.setJuniorOrSenior();

    }

    public void seeMembers() {
        System.out.println("Registreret medlemmer af svømmeklubben");

        if (controller.getMembers().isEmpty()) {
            System.out.println("Ingen medlemmer blev fundet");
            System.out.println();
        } else {
            for (Member member : controller.getMembers()) {
                System.out.println("Navn: " + member.getFirstName());
                System.out.println("Efternavn: " + member.getLastName());
                if (member.isCompetitive())
                    System.out.println("Svømmetype: Konkurrencesvømmer");
                else
                    System.out.println("Svømmetype: Motionist");
                if (member.isActive())
                    System.out.println("Medlemsstatus: Aktiv");
                else
                    System.out.println("Medlemsstatus: Passiv");
                System.out.println("Alder: " + member.getAge());
                System.out.println("Medlemsnummer: " + member.getIdNumber());

                controller.setJuniorOrSenior();
                if (member.isJunior()) {
                    System.out.println("Aldersgruppe: Junior");
                } else {
                    System.out.println("Aldersgruppe: Senior");
                }
                System.out.println(" ");
            }

        }
    }

    public void searchMembers() {
        System.out.println("Søg efter et medlem");

        System.out.print("Indtast fornavn: ");
        scanner.nextLine();
        String searchTerm = scanner.nextLine();
        System.out.println();

        ArrayList<Member> searchResult = controller.searchMembersFirstName(searchTerm);

        if (searchResult.isEmpty()) {
            System.out.println("Intet medlem med dette fornavn blev fundet");
            System.out.println();
        } else {
            System.out.println("Medlem fundet");
            for (Member member : searchResult) {
                System.out.println("Navn: " + member.getFirstName());
                System.out.println("Efternavn: " + member.getLastName());
                if (member.isCompetitive()) {
                    System.out.println("Svømmetype: Konkurrencesvømmer");
                } else {
                    System.out.println("Svømmetype: Motionist");
                }
                if (member.isActive()) {
                    System.out.println("Medlemsstatus: Aktivt");
                } else {
                    System.out.println("Medlemsstatus: Passivt");
                }
                System.out.println("Alder: " + member.getAge());
                System.out.println("Medlemsnummer: " + member.getIdNumber());
                if (member.isJunior()) {
                    System.out.println("Aldersgruppe: Junior");
                } else {
                    System.out.println("Aldersgruppe: Senior");
                }
                System.out.println();
            }
        }
    }


    public void editMember() {
        boolean writingError;

        System.out.println("Vælg det medlem du vil redigere: ");
        for (int i = 0; i < controller.getMembers().size(); i++) {
            System.out.println(i + 1 + ")" + controller.getMembers().get(i));
        }

        System.out.println("Indtast nummeret på medlemmet du vil redigere: ");
        int number;
        Member editMember = null;
        do {
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                writingError = false;
                editMember = controller.getMembers().get(number - 1);
            } catch (IndexOutOfBoundsException ibe) {
                System.out.println("Indtast kun et af numrene vist på skærmen!");
                scanner.nextLine();
                writingError = true;
            }
        } while (writingError);


        System.out.println("Redigere medlemmets information: " + editMember);
        System.out.println("Indtast nyt data. Hvis du ikke ønsker at redigere tryk 'Enter'.");

        System.out.println("Fornavn: " + editMember.getFirstName());
        String newName = scanner.nextLine();
        if (!newName.isEmpty())
            editMember.setFirstName(newName);


        System.out.println("Efternavn: " + editMember.getLastName());
        String newLName = scanner.nextLine();
        if (!newLName.isEmpty())
            editMember.setLastName(newLName);


        System.out.println("Svømmetype: (Skriv motionist eller konkurrencesvømmer)" + editMember.isCompetitive());
        String newSwimtype = scanner.nextLine();
        if (!newSwimtype.isEmpty()) {
            while (!newSwimtype.equals("motionist") && !newSwimtype.equals("konkurrence")) {
                System.out.println("Fejl. Tast motionist eller konkurrence");
                newSwimtype = scanner.nextLine();
            }
        }

        System.out.println("Alder: " + editMember.getAge());
        do {
            String newAge = scanner.nextLine().trim();
            if (!newAge.isEmpty()) {
                try {
                    editMember.setAge(Integer.parseInt(newAge));
                    writingError = false;

                } catch (NumberFormatException nfe) {
                    System.out.println("Input fejl. Indtast tal.");
                    writingError = true;
                }
            }
        } while (writingError);

        controller.setJuniorOrSenior();

        System.out.println("Medlemsstatus: (aktiv eller passiv)" + editMember.isActive());
        String newActive = scanner.nextLine();
        if (!newActive.isEmpty()) {
            while (!newActive.equals("passiv") && !newActive.equals("aktiv")) {
                System.out.println("Fejl. Tast kun aktiv eller passiv");
                newActive = scanner.nextLine();
            }

            boolean active;
            active = newActive.equals("aktiv");
            editMember.setActive(active);


            System.out.println("Medlemsnummer: " + editMember.getIdNumber());
            do {
                String newIdNumber = scanner.nextLine().trim();
                if (!newIdNumber.isEmpty()) {
                    try {
                        editMember.setAge(Integer.parseInt(newIdNumber));
                        writingError = false;

                    } catch (NumberFormatException e) {
                        System.out.println("Input fejl. Indtast kun tal.");
                        writingError = true;
                    }
                }
            } while (writingError);


        }
    }

    public void deleteMember() {
        boolean delete = false;
        System.out.println("Slet et medlem fra svømmeklubben");
        for (int i = 0; i < controller.getMembers().size(); i++)
            System.out.println(i + 1 + ": " + controller.getMembers().get(i));

        System.out.println("Indtast nummeret på medlemet du vil slette");
        int number = scanner.nextInt();
        scanner.nextLine();

        Member deleteMember = controller.getMembers().get(number - 1);
        controller.deleteMember(deleteMember);
        System.out.println("Det valgte medlem er slettet fra databasen");
    }

    public void saveMembers() {
        controller.saveMembers();
        System.out.println("Indtastede information er gemt" + "\n");
    }

    public void loadMembers() {
        controller.loadMembers();
        System.out.println("Data loaded");
    }

    public void seeTeams() {
        System.out.println("Se seniorhold - Tast 1");
        System.out.println("Se juniorhold - Tast 2");

        int menu = scanner.nextInt();
        if (menu == 1) {
            System.out.println("");
            System.out.println("Senior træner:");

            for (Coach coach : controller.getCoaches()) {
                if (coach.isCoach() == false) {
                    System.out.println(coach.getFirstName() + " " + coach.getLastName());
                }
            }
            System.out.println(" ");
            System.out.println("Senior svømmere:");
            for (Member member : controller.getMembers()) {
                controller.setJuniorOrSenior();
                if (!member.isJunior()) {
                    System.out.println(member.getFirstName() + " \n ");
                }
            }


        } else if (menu == 2) {
            System.out.println("Junior træner:");
            for (Coach coach : controller.getCoaches()) {
                if (coach.isCoach() == true) {
                    System.out.println(coach.getFirstName() + " " + coach.getLastName());
                }
            }
            System.out.println(" ");
            System.out.println("Junior svømmere:");
            for (Member member : controller.getMembers()) {
                controller.setJuniorOrSenior();
                if (member.isJunior()) {
                    System.out.println(member.getFirstName() + " " + member.getLastName() + " \n ");
                }

            }
        }
    }

    public void createResult() {
        boolean writingError = false;
        System.out.println("Registrere træningsresultater - Tast 1");
        System.out.println("Registrere konkurrenceresultater - Tast 2");
        int menu = scanner.nextInt();
        if (menu == 1) {
            boolean isPractice = true;

            System.out.println("Indtast medlemsnummer:");
            int membershipNumber = 0;
            do {
                try {
                    membershipNumber = scanner.nextInt();
                    scanner.nextLine();
                    writingError = false;
                } catch (InputMismatchException e) {
                    System.out.println("Tastefejl, indtast kun tal");
                    scanner.nextLine();
                    writingError = true;
                }
            } while (writingError);

            System.out.println("Indtast træningstid i sekunder:");
            double timeResult = 0;
            do {
                try {
                    timeResult = scanner.nextDouble();
                    scanner.nextLine();
                    writingError = false;
                } catch (InputMismatchException e) {
                    System.out.println("Tastefejl, indtast kun tal");
                    scanner.nextLine();
                    writingError = true;
                }
            } while (writingError);


            System.out.println("Indtast disciplin:");
            String discipline = scanner.nextLine();

            System.out.println("Indtast dato:");
            String date = scanner.nextLine();

            System.out.println("Indtast alder:");
            boolean isJunior;
            int alder;
            alder = scanner.nextInt();
            if (alder < 18) {
                isJunior = true;
            } else {
                isJunior = false;
            }
            controller.createTrainingResult(timeResult, isPractice, discipline, date, membershipNumber, isJunior);


        } else if (menu == 2) {
            boolean isPractice = false;
            System.out.println("Indtast medlemsnummer:");
            int membershipNumber = 0;
            do {
                try {
                    membershipNumber = scanner.nextInt();
                    scanner.nextLine();
                    writingError = false;
                } catch (InputMismatchException e) {
                    System.out.println("Tastefejl, indtast kun tal");
                    scanner.nextLine();
                    writingError = true;
                }
            } while (writingError);

            System.out.println("Indtast konkurrencetid i sekunder:");
            double timeResult = 0;
            do {
                try {
                    timeResult = scanner.nextDouble();
                    scanner.nextLine();
                    writingError = false;
                } catch (InputMismatchException e) {
                    System.out.println("Tastefejl, indtast kun tal");
                    scanner.nextLine();
                    writingError = true;
                }
            } while (writingError);

            System.out.println("Indtast disciplin:");
            String discipline = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Indtast placering:");
            int placement = 0;
            do {
                try {
                    membershipNumber = scanner.nextInt();
                    scanner.nextLine();
                    writingError = false;
                } catch (InputMismatchException e) {
                    System.out.println("Tastefejl, indtast kun tal");
                    scanner.nextLine();
                    writingError = true;
                }
            } while (writingError);

            System.out.println("Indtast navn på stævne:");
            String tournamentName = scanner.nextLine();

            System.out.println("Indtast dato:");
            String date = scanner.nextLine();
            System.out.println("\n");

            controller.createCompetitionResult(timeResult, isPractice, placement, discipline, tournamentName, date, membershipNumber);
        }

    }


    public void seeResults() {
        System.out.println("Se træningsresultater - Tast 1");
        System.out.println("Se konkurrenceresultater - Tast 2");
        int menu = scanner.nextInt();
        if (menu == 1) {
            System.out.println("Træningresultater: " + "\n");
            for (TrainingResult result : controller.getTrainingResults()) {
                System.out.println("Medlemsnummer: " + result.getIdNumber());
                System.out.println("Dicsiplin: " + result.getDiscipline());
                System.out.println("Tid: " + result.getTimeResult());
                System.out.println("Dato: " + result.getDate());
                System.out.println("Junior: " + result.isJunior());
                System.out.println(" ");


            }
        } else if (menu == 2) {
            for (CompetitionResult result : controller.getCompetitionResults()) {
                System.out.println("Medlemsnummer: " + result.getIdNumber());
                System.out.println("Disciplin: " + result.getDiscipline());
                System.out.println("Tid: " + result.getTimeResult());
                System.out.println("Placering: " + result.getPlacement());
                System.out.println("Dato: " + result.getDate());
                System.out.println("Stævne: " + result.getTournamentName());
                System.out.println(" ");


            }

        }

    }


    public void endProgram() {
        System.out.println("Lukker programmet! ");
        System.exit(9);
    }

}



package userInterface;

import java.util.ArrayList;

import domain_model.Coach;
import domain_model.Controller;

import domain_model.Member;
import domain_model.TrainingResult;
import domain_model.CompetitionResult;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void start() {
        controller.createTestData();
        controller.createTestDataCoach();
        controller.createTournamentResultTestData();
        controller.createTrainingResultTestData();
        System.out.println("Velkommen til svømmeklubben Delfin");
        boolean menuError;

        do {
            do {
                startPage();
                try {
                    int menuChoice = scanner.nextInt();
                    if (menuChoice == 1) {
                        createMember();
                    } else if (menuChoice == 2) {
                        editMember();
                    } else if (menuChoice == 3) {
                        seeMembers();
                    } else if (menuChoice == 4) {
                        searchMembers();
                    } else if (menuChoice == 5) {
                        deleteMember();
                    } else if (menuChoice == 6) {
                        saveMembers();
                    } else if (menuChoice == 7) {
                        loadMembers();
                    } else if (menuChoice == 8) {
                        seeMemberships();
                    } else if (menuChoice == 9) {
                        seeTeams();
                    } else if (menuChoice == 10) {
                        createResult();
                    } else if (menuChoice == 11) {
                        editResult();
                    } else if (menuChoice == 12) {
                        seeResults();
                    } else if (menuChoice == 13) {
                        seeTopFive();
                    } else if (menuChoice == 14) {
                        endProgram();
                    }
                    menuError = false;
                } catch (InputMismatchException ime) {
                    System.out.println("Skriv kun tal");
                    scanner.nextLine();
                    menuError = true;
                }
            } while (menuError == true);
        } while (true);
    }

    public void startPage() {

        System.out.println("1. Opret medlem");
        System.out.println("2. Rediger medlemmer");
        System.out.println("3. Se alle medlemmer");
        System.out.println("4. Søg efter et medlem");
        System.out.println("5. Slet medlem");
        System.out.println("6. Gem medlem i databasen");
        System.out.println("7. Load sidste indtastede information");
        System.out.println("8. Kontigentbetaling");
        System.out.println("9. Se hold");
        System.out.println("10. Registrere resultater");
        System.out.println("11. Rediger resultater");
        System.out.println("12. Se resultater");
        System.out.println("13. Se top 5 svømmere");
        System.out.println("14. Afslut programmet");
    }

    public void createMember() {
        boolean writingError = false;
        scanner.nextLine();
        System.out.println("Indtast fornavn");
        String firstName = scanner.nextLine();

        System.out.println("Indtast efternavn");
        String lastName = scanner.nextLine();

        System.out.println("Indtast svømmetype, skriv 'm' for motionistsvømmer eller 'k' for konkurrencesvømmer)");
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
                System.out.println("Tastefejl, skriv 'motionist' eller 'konkurrencesvømmer'");

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
        } while (writingError == true);


        System.out.println("Indtast aktivitet i klubben, 'a' for 'aktivt eller 'p' for passiv)");
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
                System.out.println("Tastefejl, indtast 'aktiv eller 'passiv'");

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
        } while (writingError == true);


        controller.createMember(firstName, lastName, competitive, age, active, idNumber);
        controller.setJuniorOrSenior();

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
                System.out.println("Tastefejl, indtast nummeret på medlemmet du til redigere");
                scanner.nextLine();
                writingError = true;
            }
        } while (writingError == true);


        System.out.println("Redigere information: " + editMember);
        System.out.println("Tast ny data. Ønsker du ikke at redigere, tryk Enter.");

        System.out.println("Fornavn: " + editMember.getFirstName());
        String newName = scanner.nextLine();
        if (!newName.isEmpty())
            editMember.setFirstName(newName);


        System.out.println("Efternavn: " + editMember.getLastName());
        String newLName = scanner.nextLine();
        if (!newLName.isEmpty())
            editMember.setLastName(newLName);


        System.out.println("Svømmetype, indtast 'motionist' eller 'konkurrence')" + editMember.isCompetitive());
        String newSwimtype = scanner.nextLine();
        if (!newSwimtype.isEmpty()) {
            while (!newSwimtype.equals("motionist") && !newSwimtype.equals("konkurrence")) {
                System.out.println("Tastefejl, indtast motionist eller konkurrence");
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
                    System.out.println("Tastefejl, indtast kun tal");
                    writingError = true;
                }
            }
        } while (writingError == true);


        System.out.println("Medlemsstatus, skriv 'aktiv' eller 'passiv')" + editMember.isActive());
        String newActive = scanner.nextLine();
        if (!newActive.isEmpty()) {
            while (!newActive.equals("passiv") && !newActive.equals("aktiv")) {
                System.out.println("Tastefejl, indtast 'aktiv' eller 'passiv'");
                newActive = scanner.nextLine();
            }

            boolean active;
            if (newActive.equals("aktiv")) {
                active = true;
            } else {
                active = false;
            }
            editMember.setActive(active);


            System.out.println("Medlemsnummer: " + editMember.getIdNumber());
            do {
                String newMembershipNumber = scanner.nextLine().trim();
                if (!newMembershipNumber.isEmpty()) {
                    try {
                        editMember.setAge(Integer.parseInt(newMembershipNumber));
                        writingError = false;

                    } catch (NumberFormatException e) {
                        System.out.println("Tastefejl, Indtast kun tal.");
                        writingError = true;
                    }
                }
            } while (writingError == true);

            controller.setJuniorOrSenior();

        }
    }

    public void seeMembers() {
        System.out.println("Registreret medlemmer i systemet");

        if (controller.getMembers().isEmpty()) {
            System.out.println("Ingen medlemmer blev fundet i systemet");
            System.out.println("");
        } else {
            for (Member member : controller.getMembers()) {
                System.out.println("Navn: " + member.getFirstName());
                System.out.println("Efternavn: " + member.getLastName());
                if (member.isCompetitive() == true)
                    System.out.println("Svømmetype: Konkurrencesvømmer");
                else
                    System.out.println("Svømmetype: Motionistsvømmer");
                if (member.isActive() == true)
                    System.out.println("Medlemsstatus: Aktivt");
                else
                    System.out.println("Medlemsstatus: Passivt");
                System.out.println("Alder: " + member.getAge());
                System.out.println("Medlemsnummer: " + member.getIdNumber());

                controller.setJuniorOrSenior();
                if (member.isJunior() == true) {
                    System.out.println("Aldersgruppe: Junior");
                } else {
                    System.out.println("Aldersgruppe: Senior");
                }
                System.out.println(" ");
            }

        }
    }

    public void searchMembers() {

            System.out.print("Indtast fornavn: ");
            scanner.nextLine();
            String searchTerm = scanner.nextLine();
            System.out.println("");

            ArrayList<Member> searchResult = controller.searchMembersFirstName(searchTerm);

            if (searchResult.isEmpty()) {
                System.out.println("Ingen medlemmer med dette navn blev fundet i systemet");
                System.out.println("");
            } else {
                System.out.println("Medlemmer fundet");
                for (Member member : searchResult) {
                    System.out.println("Navn: " + member.getFirstName());
                    System.out.println("Efternavn: " + member.getLastName());
                    if (member.isCompetitive() == true) {
                        System.out.println("Svømmetype: Konkurrencesvømmer");
                    } else {
                        System.out.println("Svømmetype: Motionistsvømmer");
                    }
                    if (member.isActive() == true) {
                        System.out.println("Medlemsstatus: Aktivt");
                    } else {
                        System.out.println("Medlemsstatus: Passivt");
                    }
                    System.out.println("Alder: " + member.getAge());
                    System.out.println("Medlemsnummer: " + member.getIdNumber());
                    if (member.isJunior() == true) {
                        System.out.println("Aldersgruppe: Junior");
                    } else {
                        System.out.println("Aldersgruppe: Senior");
                    }
                    System.out.println("");
                }
            }


            }




    public void deleteMember() {
        boolean delete = false;
        System.out.println("Slet medlemmer fra systemet");
        for (int i = 0; i < controller.getMembers().size(); i++)
            System.out.println(i + 1 + ": " + controller.getMembers().get(i));

        System.out.println("Indtast nummeret på medlemmet du vil slette fra systemet");
        int number = scanner.nextInt();
        scanner.nextLine();

        Member deleteMember = controller.getMembers().get(number - 1);
        controller.deleteMember(deleteMember);
        System.out.println("Valgte medlem er blevet slettet");
    }

    public void saveMembers() {
        controller.saveMembers();
        System.out.println("Medlem gemt i databasen" + "\n");
    }

    public void loadMembers() {
        controller.loadMembers();
        System.out.println("Data er blevetloaded");
    }

    public void seeMemberships() {
        System.out.println("Liste over kontigentbetalinger - Tast 1");
        System.out.println("Samlet årlig indkomst - Tast 2");

        int menu = scanner.nextInt();
        if (menu == 1) {
            System.out.println("Kontingent for hvert medlem: ");
            for (Member member : controller.getMembers()) {
                System.out.println(member.getFirstName() + " " + member.getLastName() + ": " + member.getPayment());
            }
            System.out.println(" ");

        } else if (menu == 2) {
            System.out.println("Samlet kontingent for året: " + controller.getTotalPayment() + " kr." + "\n");


    }
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
                if (member.isJunior() == false) {
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
                if (member.isJunior() == true) {
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
            } while (writingError == true);

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
            } while (writingError == true);


            System.out.println("Indtast disciplin:");
            String disciplin = scanner.nextLine();

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
            controller.createTrainingResult(timeResult, isPractice, disciplin, date, idNumber, isJunior);


        } else if (menu == 2) {
            boolean isPractice = false;
            System.out.println("Indtast medlemsnummer:");
            int idNumber = 0;
            do {
                try {
                    idNumber = scanner.nextInt();
                    scanner.nextLine();
                    writingError = false;
                } catch (InputMismatchException e) {
                    System.out.println("Tastefejl, indtast kun et tal");
                    scanner.nextLine();
                    writingError = true;
                }
            } while (writingError == true);

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
            } while (writingError == true);

            System.out.println("Indtast disciplin:");
            String disciplin = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Indtast placering:");
            int placement = 0;
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
            } while (writingError == true);

            System.out.println("Indtast navn på stævne:");
            String tournamentName = scanner.nextLine();

            System.out.println("Indtast dato:");
            String date = scanner.nextLine();
            System.out.println("\n");

            controller.createCompetitionResult(timeResult, isPractice, placement, disciplin, tournamentName, date, idNumber);
        }

    }

    public void editResult() {
        boolean writingError = false;
        System.out.println("Redigere træningsresultater - Tast 1");
        System.out.println("Redigere konkurrenceresultater - Tast 2");
        int menu = scanner.nextInt();
        if (menu == 1) {

            System.out.println("Vælg den tid du vil redigere: ");
            for (int i = 0; i < controller.getTrainingResults().size(); i++) {
                System.out.println(i + 1 + ")" + controller.getTrainingResults().get(i));
            }

            System.out.println("Indtast nummeret på den valgte tid: ");
            int number;
            TrainingResult editResult = null;

            do {
                try {
                    number = scanner.nextInt();
                    scanner.nextLine();
                    writingError = false;
                    editResult = controller.getTrainingResults().get(number - 1);
                } catch (InputMismatchException ime) {
                    System.out.println("Skriv kun numre, tak!");
                    scanner.nextLine();
                    writingError = true;
                }
            } while (writingError == true);



            System.out.println("Medlemsnummer: " + editResult.getIdNumber());
            do {
                String newMembershipNumber = scanner.nextLine().trim();
                if (!newMembershipNumber.isEmpty()) {
                    try {
                        editResult.setIdNumber(Integer.parseInt(newMembershipNumber));
                        writingError = false;

                    } catch (NumberFormatException nfe) {
                        System.out.println("Tastefejl, indtast kun tal");
                        writingError = true;
                    }
                }
            } while (writingError == true);


            System.out.println("Disciplin: " + editResult.getDiscipline());
            String newDisciplin = scanner.nextLine();
            if (!newDisciplin.isEmpty())
                editResult.setDiscipline(newDisciplin);


            System.out.println("Tid: " + editResult.getResult());
            do {
                String newResult = scanner.nextLine().trim();
                if (!newResult.isEmpty()) {
                    try {
                        editResult.setTimeResult(Integer.parseInt(newResult));
                        writingError = false;

                    } catch (NumberFormatException nfe) {
                        System.out.println("Tastefejl, indtast kun tal");
                        writingError = true;
                    }
                }
            } while (writingError == true);


            System.out.println("Dato: " + editResult.getDate());
            String newDate = scanner.nextLine();
            if (!newDate.isEmpty())
                editResult.setDate(newDate);

        } else if (menu == 2) {

            System.out.println("Vælg den tid du vil ændre: ");
            for (int i = 0; i < controller.getCompetitionResults().size(); i++) {
                System.out.println(i + 1 + ")" + controller.getCompetitionResults().get(i));
            }

            System.out.println("Indtast nummeret på den valgte tid: ");
            int number;
            CompetitionResult editResult = null;

            do {
                try {
                    number = scanner.nextInt();
                    scanner.nextLine();
                    writingError = false;
                    editResult = controller.getCompetitionResults().get(number - 1);
                } catch (InputMismatchException ime) {
                    System.out.println("Tastefejl, indtast kun tal");
                    scanner.nextLine();
                    writingError = true;
                }
            } while (writingError == true);


            System.out.println("Medlemsnummer: " + editResult.getIdNumber());
            do {
                String newMembershipNumber = scanner.nextLine().trim();
                if (!newMembershipNumber.isEmpty()) {
                    try {
                        editResult.setIdNumber(Integer.parseInt(newMembershipNumber));
                        writingError = false;

                    } catch (NumberFormatException e) {
                        System.out.println("Tastefejl, indtast kun tal");
                        writingError = true;
                    }
                }
            } while (writingError == true);


            System.out.println("Disciplin: " + editResult.getDiscipline());
            String newDisciplin = scanner.nextLine();
            if (!newDisciplin.isEmpty())
                editResult.setDiscipline(newDisciplin);


            System.out.println("Tid: " + editResult.getResult());
            do {
                String newResult = scanner.nextLine().trim();
                if (!newResult.isEmpty()) {
                    try {
                        editResult.setTimeResult(Integer.parseInt(newResult));
                        writingError = false;

                    } catch (NumberFormatException e) {
                        System.out.println("Tastefejl, indtast kun tal");
                        writingError = true;
                    }
                }
            } while (writingError == true);


            System.out.println("Placering: " + editResult.getPlacement());
            do {
                String newPlacement = scanner.nextLine().trim();
                if (!newPlacement.isEmpty()) {
                    try {
                        editResult.setTimeResult(Integer.parseInt(newPlacement));
                        writingError = false;

                    } catch (NumberFormatException e) {
                        System.out.println("Tastefejl, indtast kun tal");
                        writingError = true;
                    }
                }
            } while (writingError == true);


            System.out.println("Dato: " + editResult.getDate());
            String newDate = scanner.nextLine();
            if (!newDate.isEmpty())
                editResult.setDate(newDate);


            System.out.println("Stævnenavn: " + editResult.getTournamentName());
            String newTournamentName = scanner.nextLine();
            if (!newTournamentName.isEmpty())
                editResult.setTournamentName(newTournamentName);


        }
    }

    public void seeResults() {
        System.out.println("Se træningsresultater - Tast 1");
        System.out.println(" Se konkurrenceresultater - Tast 2");
        int menu = scanner.nextInt();
        if (menu == 1) {
            System.out.println("Træningresultater: " + "\n");
            for (TrainingResult result : controller.getTrainingResults()) {
                System.out.println("Medlemsnummer: " + result.getIdNumber());
                System.out.println("Dicsiplin: " + result.getDiscipline());
                System.out.println("Tid: " + result.getResult());
                System.out.println("Dato: " + result.getDate());
                System.out.println("Junior: " + result.isJunior());
                System.out.println(" ");


            }
        } else if (menu == 2) {
            for (CompetitionResult result : controller.getCompetitionResults()) {
                System.out.println("Medlemsnummer: " + result.getIdNumber());
                System.out.println("Disciplin: " + result.getDiscipline());
                System.out.println("Tid: " + result.getResult());
                System.out.println("Placering: " + result.getPlacement());
                System.out.println("Dato: " + result.getDate());
                System.out.println("Stævnenavn: " + result.getTournamentName());
                System.out.println(" ");


            }

        }

    }

    public ArrayList<TrainingResult> findJuniorResults() {
        ArrayList<TrainingResult> practiceResultsJunior = new ArrayList<>();
        for (TrainingResult result : controller.getTrainingResults()) {
            if (result.isJunior()) {
                practiceResultsJunior.add(result);
            }
        }
        return practiceResultsJunior;
    }

    public ArrayList<TrainingResult> findSeniorResults() {
        ArrayList<TrainingResult> practiceResultsSenior = new ArrayList<>();
        for (TrainingResult result : controller.getTrainingResults()) {
            if (!result.isJunior()) {
                practiceResultsSenior.add(result);
            }
        }
        return practiceResultsSenior;
    }

    public List<TrainingResult> getTopFive(ArrayList<TrainingResult> results) {
        List<TrainingResult> list = results;
        if (results.size() > 5) {
            return list.subList(0, 5);
        }
        return results;
    }

    public void seeTopFive() {
        System.out.println("Se top 5 svømmere for Junior - Tast 1");
        System.out.println("Se top 5 svømmere for Senior - Tast 2");
        int menu = scanner.nextInt();
        if (menu == 1) {
            controller.sortTopFive();
            System.out.println("Top 5 juniorsvømmere: " + "\n");

            ArrayList<TrainingResult> juniorResults = findJuniorResults();
            List<TrainingResult> juniorResultsTopFive = getTopFive(juniorResults);

            for (TrainingResult practiceResult : juniorResultsTopFive) {
                System.out.println("Discpline: " + practiceResult.getDiscipline() + "\n" +
                        "Resultat: " + practiceResult.getResult() + "\n" +
                        "Medlemsnummer: " + practiceResult.getIdNumber() + "\n");

            }

        } else if (menu == 2) {
            controller.sortTopFive();
            System.out.println("Top 5 seniorsvømmere: " + "\n");

            ArrayList<TrainingResult> seniorResults = findSeniorResults();
            List<TrainingResult> seniorResultsTopFive = getTopFive(seniorResults);

            for (TrainingResult practiceResult : seniorResultsTopFive) {
                System.out.println("Discplin: " + practiceResult.getDiscipline() + "\n" +
                        "Resultat: " + practiceResult.getResult() + "\n" +
                        "Medlemsnummer: " + practiceResult.getIdNumber() + "\n");
            }
        }
    }

    public void endProgram() {
        System.out.println("Lukker programmet... ");
        System.exit(9);
    }
}


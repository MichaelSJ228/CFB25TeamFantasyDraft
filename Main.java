import java.util.Random;
public class Main
{
   public static void main(String[] args)
    {
        Random random = new Random();
        int draftOrder;
        int eachConference;
        int eachGrade;
        int eachTeam;
        int eachValuedGrade;
        int eachRoundPair;
        int eachSum;
        int everyConference;
        int everySum;
        int teamNumberSelected = 0;
        int numberOfValuedGrades = 3;
        int eachPick = 0;
        int roundNumber = 0;
        int[] gradeNumber = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int greatestSum[] = new int[Data.teamGrade.length];
        String[] conferenceName = {"American", "ACC", "Big 12", "Big Ten", "CUSA", "MAC", "MWC", "Pac-12", "SEC", "Sun Belt"};
        String[] gradeName = {"Championship Contender", "Program Tradition", "Campus Lifestyle", "Stadium Atmosphere", "Pro Potential", "Brand Exposure", "Academic Prestige", "Coach Stability", "Coach Prestige", "Athletic Facilities"};
        int[][] conferenceGradeOrder = new int[conferenceName.length][gradeNumber.length];
        int[][] conferenceTeamSum = new int[conferenceName.length][Data.teamGrade.length];
        int[][] teamDrafted = new int[conferenceName.length][Data.teamGrade.length/conferenceName.length*conferenceName.length];
        int[][][] conferenceTeamGrade = new int[conferenceName.length][Data.teamGrade.length][gradeNumber.length];
        System.out.println();
        for(int conferenceNumber = 0; conferenceNumber < conferenceName.length; conferenceNumber++)
        {
            int rand = random.nextInt(conferenceName.length);
			String temp = conferenceName[rand];
			conferenceName[rand] = conferenceName[conferenceNumber];
			conferenceName[conferenceNumber] = temp;
        }
        System.out.println("The draft order goes");
        for(draftOrder = 0; draftOrder < conferenceName.length; draftOrder++)
        {
        System.out.println((draftOrder + 1)+": "+conferenceName[draftOrder]);
        }
        System.out.println();
        for(eachConference = 0; eachConference < conferenceName.length; eachConference++)
        {
            for(eachGrade = 0; eachGrade <gradeNumber.length; eachGrade++)
            {
            conferenceGradeOrder[eachConference][eachGrade] = eachGrade;
            }
        }
        for(eachConference = 0; eachConference < conferenceName.length; eachConference++)
        {
            for(eachGrade = 0; eachGrade < gradeNumber.length; eachGrade++)
            {
                int rand = random.nextInt(gradeNumber.length);
                int temp = conferenceGradeOrder[eachConference][rand];
                conferenceGradeOrder[eachConference][rand] = conferenceGradeOrder[eachConference][eachGrade];
                conferenceGradeOrder[eachConference][eachGrade] = temp;
            }
            System.out.print("The "+conferenceName[eachConference]+" values: ");
            for(eachGrade = 0; eachGrade < numberOfValuedGrades; eachGrade++)
            {
                System.out.print(gradeName[conferenceGradeOrder[eachConference][eachGrade]]+" --- ");
            }
            System.out.println();
            for(eachTeam = 0; eachTeam < Data.teamGrade.length; eachTeam++)
            {
                for(eachGrade = 0; eachGrade < gradeNumber.length; eachGrade++)
                {
                    conferenceTeamGrade[eachConference][eachTeam][eachGrade] = Data.teamGrade[eachTeam][conferenceGradeOrder[eachConference][eachGrade]];
                }
                for(eachValuedGrade = 0; eachValuedGrade < numberOfValuedGrades; eachValuedGrade++)
                {
                    conferenceTeamGrade[eachConference][eachTeam][eachValuedGrade] = 2 * conferenceTeamGrade[eachConference][eachTeam][eachValuedGrade];
                }
                for(eachGrade = 0; eachGrade < gradeNumber.length; eachGrade++)
                {
                    conferenceTeamSum[eachConference][eachTeam] = conferenceTeamSum[eachConference][eachTeam] + conferenceTeamGrade[eachConference][eachTeam][eachGrade];
                }
            }
        }
        eachConference = 0;
        int roundsPairsI = (Data.teamGrade.length/conferenceName.length)/2;
        double roundsPairsD = (Data.teamGrade.length/conferenceName.length)/2.0;
        if(roundsPairsI == roundsPairsD)
        {
            for(eachRoundPair = 0; eachRoundPair < roundsPairsI; eachRoundPair++)
            {
                System.out.println();
                roundNumber++;
                for(eachPick = 0; eachPick < conferenceName.length; eachPick++)
                {
                    for(everyConference = 0; everyConference < conferenceName.length; everyConference++)
                    {
                        for(eachSum = 0; eachSum < Data.teamGrade.length; eachSum++)
                        {
                            if(conferenceTeamSum[everyConference][eachSum] > greatestSum[everyConference])
                            {
                                greatestSum[everyConference] = conferenceTeamSum[everyConference][eachSum];
                                teamNumberSelected = eachSum;
                            }
                        }
                        for(everySum = 0; everySum < conferenceName.length; everySum++)
                            {
                                greatestSum[everyConference] = 0;
                                conferenceTeamSum[everyConference][teamNumberSelected] = 0;
                            }
                    }
                    teamDrafted[eachConference][roundNumber-1] = teamNumberSelected;
                    System.out.println("In round "+(roundNumber)+" with pick "+(eachPick+1)+" the "+conferenceName[eachConference]+" selects "+Data.teamName[teamNumberSelected]);
                    eachConference++;
                }
                System.out.println();
                roundNumber++;
                for(eachPick = 0; eachPick < conferenceName.length; eachPick++)
                {
                    eachConference--;
                    for(everyConference = 0; everyConference < conferenceName.length; everyConference++)
                    {
                        for(eachSum = 0; eachSum < Data.teamGrade.length; eachSum++)
                        {
                            if(conferenceTeamSum[everyConference][eachSum] > greatestSum[everyConference])
                            {
                                greatestSum[everyConference] = conferenceTeamSum[everyConference][eachSum];
                                teamNumberSelected = eachSum;
                            }
                        }
                        for(everySum = 0; everySum < conferenceName.length; everySum++)
                            {
                                greatestSum[everyConference] = 0;
                                conferenceTeamSum[everyConference][teamNumberSelected] = 0;
                            }
                    }
                    teamDrafted[eachConference][roundNumber-1] = teamNumberSelected;
                    System.out.println("In round "+(roundNumber)+" with pick "+(eachPick+1)+" the "+conferenceName[eachConference]+" selects "+Data.teamName[teamNumberSelected]);
                }
            }
        }
        else
        {
            for(eachRoundPair = 0; eachRoundPair < roundsPairsI; eachRoundPair++)
            {
                System.out.println();
                roundNumber++;
                for(eachPick = 0; eachPick < conferenceName.length; eachPick++)
                {
                    for(everyConference = 0; everyConference < conferenceName.length; everyConference++)
                    {
                        for(eachSum = 0; eachSum < Data.teamGrade.length; eachSum++)
                        {
                            if(conferenceTeamSum[everyConference][eachSum] > greatestSum[everyConference])
                            {
                                greatestSum[everyConference] = conferenceTeamSum[everyConference][eachSum];
                                teamNumberSelected = eachSum;
                            }
                        }
                        for(everySum = 0; everySum < conferenceName.length; everySum++)
                            {
                                greatestSum[everyConference] = 0;
                                conferenceTeamSum[everyConference][teamNumberSelected] = 0;
                            }
                    }
                    teamDrafted[eachConference][roundNumber-1] = teamNumberSelected;
                    System.out.println("In round "+(roundNumber)+" with pick "+(eachPick+1)+" the "+conferenceName[eachConference]+" selects "+Data.teamName[teamNumberSelected]);
                    eachConference++;
                }
                System.out.println();
                roundNumber++;
                for(eachPick = 0; eachPick < conferenceName.length; eachPick++)
                {
                    eachConference--;
                    for(everyConference = 0; everyConference < conferenceName.length; everyConference++)
                    {
                        for(eachSum = 0; eachSum < Data.teamGrade.length; eachSum++)
                        {
                            if(conferenceTeamSum[everyConference][eachSum] > greatestSum[everyConference])
                            {
                                greatestSum[everyConference] = conferenceTeamSum[everyConference][eachSum];
                                teamNumberSelected = eachSum;
                            }
                        }
                        for(everySum = 0; everySum < conferenceName.length; everySum++)
                            {
                                greatestSum[everyConference] = 0;
                                conferenceTeamSum[everyConference][teamNumberSelected] = 0;
                            }
                    }
                    teamDrafted[eachConference][roundNumber-1] = teamNumberSelected;
                    System.out.println("In round "+(roundNumber)+" with pick "+(eachPick+1)+" the "+conferenceName[eachConference]+" selects "+Data.teamName[teamNumberSelected]);
                }
            }
            System.out.println();
            roundNumber++;
            for(eachPick = 0; eachPick < conferenceName.length; eachPick++)
            {
                for(everyConference = 0; everyConference < conferenceName.length; everyConference++)
                {
                    for(eachSum = 0; eachSum < Data.teamGrade.length; eachSum++)
                    {
                        if(conferenceTeamSum[everyConference][eachSum] > greatestSum[everyConference])
                        {
                            greatestSum[everyConference] = conferenceTeamSum[everyConference][eachSum];
                            teamNumberSelected = eachSum;
                        }
                    }
                    for(everySum = 0; everySum < conferenceName.length; everySum++)
                    {
                             greatestSum[everyConference] = 0;
                             conferenceTeamSum[everyConference][teamNumberSelected] = 0;
                    }
                }
                teamDrafted[eachConference][roundNumber-1] = teamNumberSelected;
                System.out.println("In round "+(roundNumber)+" with pick "+(eachPick+1)+" the "+conferenceName[eachConference]+" selects "+Data.teamName[teamNumberSelected]);
                eachConference++;
            }
        }
        for(eachConference = 0; eachConference < conferenceName.length; eachConference++)
        {
            System.out.println("\n"+conferenceName[eachConference]+":");
            for(eachTeam = 0; eachTeam < Data.teamGrade.length/conferenceName.length; eachTeam++)
            {
                System.out.println(Data.teamName[teamDrafted[eachConference][eachTeam]]);
            }
        }
        System.out.println("\nIndependent:");
        for(eachTeam = 0; eachTeam < (Data.teamGrade.length-Data.teamGrade.length/conferenceName.length*conferenceName.length); eachTeam++)
        {
            for(everyConference = 0; everyConference < conferenceName.length; everyConference++)
            {
                for(eachSum = 0; eachSum < Data.teamGrade.length; eachSum++)
                {
                    if(conferenceTeamSum[everyConference][eachSum] > greatestSum[everyConference])
                    {
                        greatestSum[everyConference] = conferenceTeamSum[everyConference][eachSum];
                        teamNumberSelected = eachSum;
                    }
                }
                for(everySum = 0; everySum < conferenceName.length; everySum++)
                {
                    greatestSum[everyConference] = 0;
                    conferenceTeamSum[everyConference][teamNumberSelected] = 0;
                }
            }
            System.out.println(Data.teamName[teamNumberSelected]);
            eachConference++;
        }
        System.out.println();
    }
}
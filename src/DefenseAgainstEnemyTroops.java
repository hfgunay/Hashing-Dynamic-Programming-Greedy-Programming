import java.util.ArrayList;

/**
 * This class accomplishes Mission Nuke'm
 */
public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     * @return OptimalEnemyDefenseSolution
     */
    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){
        int tempSol = 0;
        int n = getNumberOfEnemiesArrivingPerHour().size();
        ArrayList<Integer> maxNumbersKilledList = new ArrayList<>();
        ArrayList<Integer> hours = new ArrayList<>();
        int sol = 0;
        int maxNumbersKilled = 0;
        for (int j = 1; j <= n; j++ ) {
            for (int i=0; i <= j ; i++) {
                tempSol = checkEnemyPerHour(getNumberOfEnemiesArrivingPerHour().get(j -1),getRechargedWeaponPower(i));
                int minKilled = checkEnemyPerHour(getNumberOfEnemiesArrivingPerHour().get(j -1), getRechargedWeaponPower(j-i));
                int totalKilled = tempSol + minKilled;
                if(sol < totalKilled) {
                    tempSol += minKilled;
                    sol = tempSol;
                }else if(sol >= totalKilled){
                        int indexOfCurrentSol = maxNumbersKilledList.indexOf(sol)+1;
                        hours.add(indexOfCurrentSol);
                        tempSol = sol;

                } }
            maxNumbersKilledList.add(sol);
            maxNumbersKilled = tempSol;
            tempSol = 0;
            if(j==n){
                hours.add(n);
            }
        }

        return new OptimalEnemyDefenseSolution(maxNumbersKilled,hours);
    }
    public int checkEnemyPerHour(int numberOfEnemiesArrivingPerHour, int rechargedWeaponPower) {
        if (numberOfEnemiesArrivingPerHour >= rechargedWeaponPower) {
            return rechargedWeaponPower;
        }
        else {
            return  numberOfEnemiesArrivingPerHour;
        }
    }
}
/*
sol = getSol(getNumberOfEnemiesArrivingPerHour().get(j -1),getRechargedWeaponPower(i));
                int minEP = getSol(getNumberOfEnemiesArrivingPerHour().get(j -1), getRechargedWeaponPower(j-i));
                if(sol + minEP > tempSol) {
                    sol = sol + minEP;
                    tempSol = sol;
                }else {
                    if(i == 0){
                        sol = tempSol + getSol(getNumberOfEnemiesArrivingPerHour().get(j -1), getRechargedWeaponPower(j - (maxNumbersKilledList.indexOf(tempSol)+1)));
                        if((sol + getSol(getNumberOfEnemiesArrivingPerHour().get(j -1),getRechargedWeaponPower(n - j))) >= (tempSol + getSol(getNumberOfEnemiesArrivingPerHour().get(j-1), getRechargedWeaponPower(n - (maxNumbersKilledList.indexOf(tempSol)+1))))) {
                            hours.add(maxNumbersKilledList.indexOf(tempSol)+1);
                            tempSol = sol;
                        }
                    }else {
                        sol = tempSol;
                    } }
 */
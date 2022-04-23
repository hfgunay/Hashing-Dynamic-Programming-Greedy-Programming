import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class accomplishes Mission Exterminate
 */
public class OptimalFinalDefenseGP
{
    private ArrayList<Integer> bombWeights;

    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }

    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }

    /**
     *
     * @param maxNumberOfAvailableAUAVs the maximum number of available AUAVs to be loaded with bombs
     * @param maxAUAVCapacity the maximum capacity of an AUAV
     * @return the minimum number of AUAVs required using first fit approach over reversely sorted items.
     * Must return -1 if all bombs can't be loaded onto the available AUAVs
     */
    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity)
    {
        Collections.sort(bombWeights, Collections.reverseOrder());
        int minNumberOfAUAVsToDEploy = 0;
        List<Integer> auavList = new ArrayList<Integer>(Collections.nCopies(maxNumberOfAvailableAUAVs, maxAUAVCapacity));
        ArrayList<Integer> loadedBombs = new ArrayList<>();
        for(int i=0; i < bombWeights.size(); i++) {
            for(int j = 0; j < auavList.size(); j++) {
                if(auavList.get(j) >= bombWeights.get(i)) {
                    auavList.set(j, auavList.get(j) - bombWeights.get(i));
                    loadedBombs.add(bombWeights.get(i));
                    break;
                } } }
        for (Integer integer : auavList) {
            if (integer < maxAUAVCapacity) {
                minNumberOfAUAVsToDEploy++;
            }
        }
        if(bombWeights.size() != loadedBombs.size()){
            minNumberOfAUAVsToDEploy = -1;
        }
        return minNumberOfAUAVsToDEploy;
    }
    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);
        }
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");
        }
    }
}

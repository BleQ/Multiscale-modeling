package GrainGrowth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author marcinkrzyzowski
 */
public final class MainGrainBoard {

    /// - Mark Variables
    private final int width;
    
    private final int height;
    
    private Grain boardGrain[][];
    
    private Grain[][] temporaryBoardGrain;
    
    private int numberOfSeeds;
    
    private int[][] temporaryBoardArray;

    private int countGrainsCristal = 0;
    
    private Random random = new Random();
    
    private boolean isPeriodic;
    
    private boolean endSimulation;
    
    private boolean edgeGenerated;
    
    private ArrayList<Integer> grainsToSkip;


    public int getCountGrainsCristal() {
        return countGrainsCristal;
    }

    public boolean isEndSimulation() {
        return endSimulation;
    }

    public MainGrainBoard(int size_x, int size_y) {
        edgeGenerated = false;
        endSimulation = false;
        isPeriodic = false;
        this.width = size_x;
        this.height = size_y;
        numberOfSeeds = 0;
        boardGrain = new Grain[size_x][size_y];
        temporaryBoardGrain = new Grain[size_x][size_y];
        temporaryBoardArray = new int[size_x][size_y];
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                boardGrain[i][j] = new Grain();
                temporaryBoardGrain[i][j] = new Grain();
            }
        }
    }
    
    public Grain[][] randomBoard(int randomSetup, int ringSize, int countRing) {
        ArrayList<Point> points = new ArrayList<>();

            for (int i = 0; i < countRing; i++) {
                int spr = 0;
                int randX = random.nextInt(width);
                int randY = random.nextInt(height);
                boolean findOk = false;
                while (spr < 100) {
                    spr++;
                    findOk = true;

                    for (Point p : points) {
                        findOk = true;
                        /**check if we found grains on board not set new grains id*/
                        if (!p.point2point(randX, randY, ringSize)) {
                            findOk = false;
                             randX = random.nextInt(width);
                            randY = random.nextInt(height);
                            break;
                            }
                        }
                        if (findOk) {
                            numberOfSeeds++;
                            
                            points.add(new Point(randX, randY, 0, numberOfSeeds));
                            break;
                        }
                    }
                }
                for (Point p : points) {
                    boardGrain[p.getX()][p.getY()].setId(p.getId());
                }

        countGrainsCristal = numberOfSeeds;
        return boardGrain;
    }
    
    
    public void changePeriodic() {
        isPeriodic = !isPeriodic;
    }


    public Grain[][] clear() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boardGrain[i][j].setId(0);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 1; j < height; j++) {
                boardGrain[i][j].setIsBoundary(false);
            }
        }
        numberOfSeeds = 0;
        edgeGenerated = false;
        return boardGrain;
    }

    public Grain[][] provideEdgeGrains() {
        for (int i = 1; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (boardGrain[i][j].getId() != boardGrain[i - 1][j].getId() && !boardGrain[i][j].isBoundary()) {
                    boardGrain[i][j].setIsBoundary(true);
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 1; j < height; j++) {
                if (boardGrain[i][j].getId() != boardGrain[i][j - 1].getId() && !boardGrain[i][j].isBoundary()) {
                    boardGrain[i][j].setIsBoundary(true);
                }
            }
        }
        edgeGenerated = true;
        return boardGrain;
    }

    public Grain[][] addGrain(int i, int j) {
        numberOfSeeds++;
        boardGrain[i][j].setId(numberOfSeeds);
        countGrainsCristal = numberOfSeeds;
        return boardGrain;
    }
    
    public ArrayList<Grain> getGrainsOnBorder(){
        ArrayList<Grain> grainList = new ArrayList();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(boardGrain[i][j].isBoundary())
                    grainList.add(boardGrain[i][j]);
            }
        }
        return grainList;
    }

    public int ammountOfNotEmptyCells() {
        int sum = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (boardGrain[i][j].getId() != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }
    /**
     * We are looking for any empty space in the aboard

     * 
     * @param areaSetup
     * @param r
     * @param probability
     * @return 
     */
    public Grain[][] calculate(int areaSetup, int r, int probability) {
        endSimulation = true;
        int tmp[][] = new int[3][3];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temporaryBoardArray[i][j] = boardGrain[i][j].getId();
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (boardGrain[i][j].getId() == 0) { //empty calls
                    endSimulation = false;
                    if (areaSetup == 2)
                    {
                        temporaryBoardArray[i][j] = makeExtendedMoorArea(i,j,probability);
                    }
                    else {
                        tmp = createLocalArea(i, j, areaSetup, false);
                        temporaryBoardArray[i][j] = provideDominantGrainId(tmp);
                    }
                }
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boardGrain[i][j].setId(temporaryBoardArray[i][j]);
            }
        }

        return boardGrain;
    }
    
    private int[][] createLocalArea(int i, int j, int areaSetup, boolean recrystal) {
        int tmp[][] = new int[3][3];

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                int l_x = (width + (i - 1 + k)) % width;
                int l_y = (height + (j - 1 + l)) % height;
                if (recrystal) {
                    if (boardGrain[l_x][l_y].isR()) {
                        tmp[k][l] = boardGrain[l_x][l_y].getId();
                    } else {
                        tmp[k][l] = 0;
                    }
                } else {
                    tmp[k][l] = boardGrain[l_x][l_y].getId();
                }
            }
        }

        if (!isPeriodic) {
            if (i == 0) {
                for (int k = 0; k < 3; k++) {
                    tmp[0][k] = 0;
                }
            }
            if (j == 0) {
                for (int k = 0; k < 3; k++) {
                    tmp[k][0] = 0;
                }
            }
            if (i == width - 1) {
                for (int k = 0; k < 3; k++) {
                    tmp[2][k] = 0;
                }
            }
            if (j == height - 1) {
                for (int k = 0; k < 3; k++) {
                    tmp[k][2] = 0;
                }
            }
        }

        int tmp_area = areaSetup;
        boolean _rand = true;
        while (_rand) {
            switch (tmp_area) {
                case 0: //moor
                {
                    _rand = false;
                    break;
                }
                case 1: //neum
                {
                    tmp[0][0] = 0;
                    tmp[2][0] = 0;
                    tmp[0][2] = 0;
                    tmp[2][2] = 0;
                    _rand = false;
                    break;
                }
                default:
                    _rand = false;
                    break;
            }
        }

        return tmp;
    }
    
    /**
     * 
     * Looking for the most dominant grain, and if we found neighbor then add to hasMap 
     * @param tab
     * @return 
     */
    private int provideDominantGrainId(int[][] tab) {
        ArrayList<Integer> list = new ArrayList<Integer>(); 

        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tab[i][j] != 0 && tab[i][j] != -1 && tab[i][j] != -2 && tab[i][j] != -3 && !grainsToSkip.contains(tab[i][j]))
                list.add(tab[i][j]);
            }
        }

        return list.isEmpty() ? 0 : mostCommon(list);
    }
    
    /**
     * When we found adjacent point change her to id grains
    */
    public static <T> T mostCommon(ArrayList<T> list) {
        Map<T, Integer> map = new LinkedHashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

            Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if ((max == null || e.getValue() > max.getValue()))
                max = e;
        }

        return max.getKey();
    }

    boolean isEdgeGenerated() {
        return edgeGenerated;
    }
    
    /**if found empty call then grows grains depends on the probability */
    private int makeExtendedMoorArea(int x, int y, int probability) {
        int tmp[][] = new int[3][3];
        HashSet<Integer> uniqueIds = new HashSet<>();
        LinkedHashMap<Integer, Integer> configurations = 
                new LinkedHashMap<Integer, Integer>(){{put(0,5); put(1,3); put(7,3);}};
        Set<Map.Entry<Integer,Integer>> configurationsSet = configurations.entrySet(); 

        for (Map.Entry<Integer, Integer> it : configurationsSet){
            tmp = createLocalArea(x ,y ,it.getKey(), false);
            uniqueIds = getUniqueIdsFromNeighborhood(tmp);
            for(Integer id : uniqueIds)
                 if (countOccurrence(id, tmp) > it.getValue()) return id;
        }
        
        tmp = createLocalArea(x ,y ,0, false);
        if(random.nextInt(100)> (100 - probability))
            return provideDominantGrainId(tmp);
        else return 0;
    }
    
    private HashSet<Integer> getUniqueIdsFromNeighborhood(int tmp[][]) {
        HashSet<Integer> uniqueIds = new HashSet<>();
        for(int i =0;i<3;i++)
            for(int j =0;j<3;j++)
                if(tmp[i][j] != 0) uniqueIds.add(tmp[i][j]);
        return uniqueIds;      
    }
    
    private int countOccurrence(int id, int tmp[][]) {
        int count = 0;
        for(int i =0;i<3;i++)
            for(int j =0;j<3;j++)
                if (id == tmp[i][j]) count++;
        
        return count;
    }
    
    public Grain[][] removeAllGrainsExceptSelected(ArrayList<Integer> selectedGrains) {        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(!selectedGrains.contains(boardGrain[i][j].getId()))
                {
                    boardGrain[i][j].setId(0);
            }}
        }
        return boardGrain;
    }
    
    void setGrainsToSkip(ArrayList<Integer> selectedGrainList) {
        grainsToSkip = selectedGrainList;
        return;
    }
/**change id grains for dualphase*/
    Grain[][] dualPhaseIdChange() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                 if (boardGrain[i][j].getId() != 0)
                     boardGrain[i][j].setId(-2);
            }
        }
        return boardGrain;
    }
    
    void drawBoundaries() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(boardGrain[i][j].isBoundary()) boardGrain[i][j].setId(0);
            }
        }
    }
    
    boolean hasBoundariesInNeighbourhood(int x, int y) {
        for (int i = x-1; i <= x+1 && i>0 && i< width; i++) {
            for (int j = y-1; j <= y+1 && j>0 && j< height; j++) {
                if(boardGrain[i][j].isBoundary()) return true;
            }
        }
        return false;
    }
    
    Grain[][] growBoundaries(int size , ArrayList<Integer> selectedGrainList) {
        boardGrain = provideEdgeGrains();
        ArrayList<Grain> grainToSet = new ArrayList<Grain>();
        
        if(selectedGrainList.isEmpty())
        {
            for (int k = size -1; k > 0; k--)
            {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (hasBoundariesInNeighbourhood(i,j))
                    {
                        grainToSet.add(boardGrain[i][j]);
                    }
                }
            }
            for(Grain grain : grainToSet)
            {
                grain.setIsBoundary(true);
                grain.setId(0);
            }
            
        }
        drawBoundaries();
        }
        else
        {
            for (int k = size -1; k > 0; k--)
            {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (hasBoundariesInNeighbourhood(i,j) && selectedGrainList.contains(boardGrain[i][j].getId()))
                    {
                        grainToSet.add(boardGrain[i][j]);
                    }
                }
            }
            clearEdgedifferentThan(selectedGrainList);
            for(Grain grain : grainToSet)
            {
                grain.setIsBoundary(true);
            }
        }
        }
        drawBoundaries();
        return boardGrain;
    }
    
    private void clearEdgedifferentThan(ArrayList<Integer> selectedGrainList) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(boardGrain[i][j].isBoundary() && !selectedGrainList.contains(boardGrain[i][j].getId()))
                    boardGrain[i][j].setIsBoundary(false);
            }
        }
    }
    
    /// Sets board
    void setBoard(Grain boardGrain[][]) {
        this.boardGrain = boardGrain;
    }
    
    /// Returns board
    Grain[][] getBoard() {
        return this.boardGrain;
    }
    
    /// Get grains that should be skip
    ArrayList<Integer> getGrainsToSkip() {
        return this.grainsToSkip;
    }
}

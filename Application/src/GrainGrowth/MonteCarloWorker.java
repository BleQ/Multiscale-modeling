package GrainGrowth;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author marcinkrzyzowski
 */
public final class MonteCarloWorker {

    private final int width;
    
    private final int height;
    
    private Grain[][] grainsArray;
    
    private int seedsCount;
    
    private Random random;
    
    private boolean isPeriodic;
    
    private ArrayList<Point> grains;
    
    private int numberOfChangedItems;
    
    private ArrayList<Integer> idOfGrainsToSkip = new ArrayList();
    
    private boolean isEdgeGenerated;
    
    /// Initializes Monte Carlo Worker
    public MonteCarloWorker() {
        grains = new ArrayList<Point>();
        isPeriodic = false;
        random = new Random();
        this.width = Constants.boardWidth;
        this.height = Constants.boardHeight;
        this.seedsCount = 50;
        grainsArray = new Grain[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grainsArray[i][j] = new Grain();
            }
        }
    }
    
    void setSeedsCount(int count) {
        this.seedsCount = count;
    }
    
    void setBoard(Grain boardGrain[][]) {
        this.grainsArray = boardGrain;
    }
    
    Grain[][] getBoard() {
        return this.grainsArray;
    }
    
    ArrayList<Integer> getGrainsToSkip() {
        return this.idOfGrainsToSkip;
    }
    
    void setGrainsToSkip(ArrayList<Integer> selectedGrainList) {
        idOfGrainsToSkip = selectedGrainList;
        return;
    }

    public void changePeriodic() {
        this.isPeriodic = !isPeriodic;
    }

    public int getNumberOfChangedItems() {
        return numberOfChangedItems;
    }

    public Grain[][] generateRandomBoard() {
        if (seedsCount == 0) {
            ArrayList<Point> tmp = new ArrayList<Point>();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    tmp.add(new Point(i, j, 0, 0));
                }
            }

            for (int i = 0; i < width * height; i++) {
                int rand_id = random.nextInt(tmp.size());
                int rand_x = tmp.get(rand_id).getX();
                int rand_y = tmp.get(rand_id).getY();;
                if(grainsArray[rand_x][rand_y].getId() == 0)
                    while(idOfGrainsToSkip.contains(i+1))
                    grainsArray[rand_x][rand_y].setId(i + 1);
                tmp.remove(rand_id);
            }

        } else {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if(grainsArray[i][j].getId() == 0)
                    {
                        int tmpId = random.nextInt(seedsCount) * 7 + 1;
                        while(idOfGrainsToSkip.contains(tmpId))
                            tmpId = random.nextInt(seedsCount) * 7 + 2;
                        grainsArray[i][j].setId(tmpId);
                    }
                }
            }
        }
        return grainsArray;
    }
    
    public Grain[][] removeAllUnselectedGrains(ArrayList<Integer> selectedGrains) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(!selectedGrains.contains(grainsArray[i][j].getId())) {
                    grainsArray[i][j].setId(0);
                }   
            }
        }
        return grainsArray;
    }
    
    Grain[][] prepareForDualPhase() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                 if (grainsArray[i][j].getId() != 0) {
                    grainsArray[i][j].setId(-2);
                 }
                     
            }
        }
        return grainsArray;
    }
    
    boolean hasBoundariesInNeighbourhood(int x, int y) {
        for (int i = x-1; i <= x+1 && i>0 && i< width; i++) {
            for (int j = y-1; j <= y+1 && j>0 && j< height; j++) {
                if(grainsArray[i][j].isBoundary()) return true;
            }
        }
        return false;
    }
    
    public Grain[][] makeEgdeGrains() {
        for (int i = 1; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grainsArray[i][j].getId() != grainsArray[i - 1][j].getId() && !grainsArray[i][j].isBoundary()) {
                    grainsArray[i][j].setIsBoundary(true);
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 1; j < height; j++) {
                if (grainsArray[i][j].getId() != grainsArray[i][j - 1].getId() && !grainsArray[i][j].isBoundary()) {
                    grainsArray[i][j].setIsBoundary(true);
                }
            }
        }
        isEdgeGenerated = true;
        return grainsArray;
    }

    
    Grain[][] growBoundaries( int size , ArrayList<Integer> selectedGrainList) {
        grainsArray = makeEgdeGrains();
        ArrayList<Grain> grainToSet = new ArrayList<Grain>();
        
        if(selectedGrainList.isEmpty()) {
            for (int k = size -1; k > 0; k--) {
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        if (hasBoundariesInNeighbourhood(i,j)) {
                            grainToSet.add(grainsArray[i][j]);
                        }
                    }
                }
                for(Grain grain : grainToSet) {
                   grain.setIsBoundary(true);
                    grain.setId(0);
                }
            }
        drawBoundaries();
        }
        else {
            for (int k = size -1; k > 0; k--) {
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        if (hasBoundariesInNeighbourhood(i,j) && selectedGrainList.contains(grainsArray[i][j].getId())) {
                            grainToSet.add(grainsArray[i][j]);
                        }
                    }
                }
                clearEdgeIfDiffers(selectedGrainList);
                for(Grain grain : grainToSet) {
                    grain.setIsBoundary(true);
                }
            }
        }
        drawBoundaries();
        return grainsArray;
    }
    
    private void drawBoundaries() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(grainsArray[i][j].isBoundary()) grainsArray[i][j].setId(0);
            }
        }
    }
    
    private void clearEdgeIfDiffers(ArrayList<Integer> selectedGrainList) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(grainsArray[i][j].isBoundary() && !selectedGrainList.contains(grainsArray[i][j].getId()))
                    grainsArray[i][j].setIsBoundary(false);
            }
        }
    }

    public Grain[][] performCalculation(float grainBoundaryEnergy) {
        numberOfChangedItems = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(grainsArray[i][j].getId() > 0 && !idOfGrainsToSkip.contains(grainsArray[i][j].getId()) )
                    grains.add(new Point(i, j, 0, grainsArray[i][j].getId()));
            }
        }
        int numberOfGrains = grains.size();
        int id = 0;
        int randGrain = 0;
        int[][] tab_tmp = new int[3][3];
        int[][] tab = new int[3][3];
        int randomArea = 0;
        float power = 0;

        while (id < numberOfGrains) {
            randGrain = random.nextInt(grains.size());
            tab = createLocalArea(grains.get(randGrain).getX(), grains.get(randGrain).getY());
            power = determinePower(tab, grainBoundaryEnergy);

            if (power > 0) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tab_tmp[i][j] = tab[i][j];
                    }
                }
                randomArea = provideRandomNeighbor(tab, tab[1][1]);
                
                tab_tmp[1][1] = randomArea;

                float power_tmp = determinePower(tab_tmp, grainBoundaryEnergy);

                if (power_tmp <= power) {
                    grainsArray[grains.get(randGrain).getX()][grains.get(randGrain).getY()].setId(randomArea);
                    numberOfChangedItems++;
                }
            }
            grains.remove(randGrain);
            id++;
        }

        return grainsArray;
    }

    private int[][] createLocalArea(int i, int j) {
        int tmp[][] = new int[3][3];

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                int l_x = (width + (i - 1 + k)) % width;
                int l_y = (height + (j - 1 + l)) % height;
                tmp[k][l] = grainsArray[l_x][l_y].getId();
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

        return tmp;
    }

    private float determinePower(int[][] tab, float grainBoundaryEnergy) {
        int power = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tab[i][j] != tab[1][1]) {
                    power++;
                }
            }
        }

        return power * grainBoundaryEnergy;
    }

    private int provideRandomNeighbor(int[][] tab, int self) {
        ArrayList<Point> tmp = new ArrayList<Point>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(tab[i][j] == 0 || (i == 1 && j == 1)) ) {
                     if(tab[i][j] == -2) continue;
                     if(idOfGrainsToSkip.contains(tab[i][j])) continue;
                    tmp.add(new Point(i, j, 0, tab[i][j]));
                }
            }
        }
        if(tmp.size() == 0) return -3;
        return tmp.get(random.nextInt(tmp.size())).getId();
    }
}

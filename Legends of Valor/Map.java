package CS611OOP;

import java.util.*;

enum CellType {
    NEXUS,
    PLAIN,
    KOULOU,
    CAVE,
    BUSH,
    INACCESSIBLE
}

public class Map {
    private static Cell[][] trueMap = new Cell[8][8];
    private static CellType[][] printMap = new CellType[8][8];

    private static void initialtrueMap(){

    }


    private static void randomGenerateMap(){
        Random random = new Random();
        for(int i = 0 ; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(j == 2 || j == 5){
                    printMap[i][j] = CellType.INACCESSIBLE;
                    //trueMap[i][j] = new Inaccessible();
                }
                else if(i == 0 || i == 7){
                    printMap[i][j] = CellType.NEXUS;
                    //trueMap[i][j] = new Nexus();
                }
            }
        }
        randomGenerateLane(1, 6, 0, 1 );
        randomGenerateLane(1, 6, 3, 4 );
        randomGenerateLane(1, 6, 6, 7 );
    }

    private static void randomGenerateLane(int startR, int endR, int startC, int endC){
        Random random = new Random();
        CellType cell = CellType.INACCESSIBLE;
        int size = (endR - startR + 1)*(endC - startC + 1);
        Set<Pair> set = new HashSet<>();

        //Generate a list with random position.
        while(set.size() < size){
            int temp = random.nextInt(5);
            CellType ct = getType(temp);
            // random generate position
            int randomR = random.nextInt(endR - startR + 1) + startR;
            int randomC = random.nextInt(endC - startC + 1) + startC;
            Pair pair = new Pair(randomR, randomC, ct);
            if(set.contains(pair)){
                continue;
            }
            set.add(pair);
        }

        for(Pair p : set){
            int row = p.row;
            int column = p.column;
            CellType type = p.type;
            // Fill the printMap and trueMap
            printMap[row][column] = type;
            trueMap[row][column] = getCellClass(type);
        }

    }

    private static Cell getCellClass(CellType cellType){
        switch (cellType){
            case PLAIN:
                return new Plain();
            case KOULOU:
                return new Koulou();
            case CAVE:
                return new Cave();
            case BUSH:
                return new Bush();
        }
        throw new RuntimeException("Don't have this cell type");
    }

    private static CellType getType(int num){
        switch (num){
            case 0:
            case 1:
                return CellType.PLAIN;
            case 2:
                return CellType.BUSH;
            case 3:
                return CellType.CAVE;
            case 4:
                return CellType.KOULOU;
        }
        throw new RuntimeException("Don't have this type of Cell.");
    }


    private static String getOuterCellStr(char c){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            str.append(c).append(" - ");
        }
        str.append(c).append("   ");
        return str.toString();
    }

    private static String getInnerCellStr(String component){
        return "| " + component + " |   ";
    }

    private static String getCellComponent(int row, int col){
        if (row == 7 && col == 1){
            return "H1   ";
        }else if (row == 1 && col == 3){
            return "H2   ";
        }else if (row == 3 && col == 1){
            return "   M1";
        }else if (row == 1 && col == 4){
            return "   M2";
        }else if (row == 3 && col == 6){
            return "H3 M3";
        }else{
            return "     ";
        }
    }

    private static void createInnerCell(CellType[][] map, List<StringBuilder> printableMap, int row, int col) {
        String component = getCellComponent(row/3, col);
        if (map[row/3][col] == CellType.INACCESSIBLE)
            component = "X X X";
        printableMap.get(row).append(getInnerCellStr(component));
    }

    private static void createOutterCell(CellType[][] map, List<StringBuilder> printableMap, int row, int col) {
        switch (map[row/3][col]){
            case NEXUS:
                printableMap.get(row).append(getOuterCellStr('N'));
                break;
            case PLAIN:
                printableMap.get(row).append(getOuterCellStr('P'));
                break;
            case KOULOU:
                printableMap.get(row).append(getOuterCellStr('K'));
                break;
            case CAVE:
                printableMap.get(row).append(getOuterCellStr('C'));
                break;
            case BUSH:
                printableMap.get(row).append(getOuterCellStr('B'));
                break;
            case INACCESSIBLE:
                printableMap.get(row).append(getOuterCellStr('I'));
                break;
        }
    }

    public static void main(String[] args) {
        int size = 8;

        // Indicative information of what is shown on the grid
        // THe implementation is not dynamic thus changing the following values will not change the map projection
        final int nexus = 0;
        final int plain = 1;
        final int koulou = 2;
        final int cave = 3;
        final int bush = 4;
        final int nonacc = 5;

        int hero1row = 7;
        int hero1col = 1;

        int hero2row = 1;
        int hero2col = 3;

        int hero3row = 3;
        int hero3col = 6;


        int monster1row = 3;
        int monster1col = 1;

        int monster2row = 1;
        int monster2col = 4;

        int monster3row = 3;
        int monster3col = 6;

        CellType[][] map = {
                {CellType.NEXUS, CellType.NEXUS, CellType.INACCESSIBLE, CellType.NEXUS, CellType.NEXUS, CellType.INACCESSIBLE, CellType.NEXUS, CellType.NEXUS},
                {CellType.PLAIN, CellType.PLAIN, CellType.INACCESSIBLE, CellType.CAVE, CellType.PLAIN, CellType.INACCESSIBLE, CellType.BUSH, CellType.BUSH},
                {CellType.PLAIN, CellType.PLAIN, CellType.INACCESSIBLE, CellType.PLAIN, CellType.PLAIN, CellType.INACCESSIBLE, CellType.PLAIN, CellType.PLAIN},
                {CellType.CAVE, CellType.BUSH, CellType.INACCESSIBLE, CellType.BUSH, CellType.KOULOU, CellType.INACCESSIBLE, CellType.KOULOU, CellType.PLAIN},
                {CellType.PLAIN, CellType.PLAIN, CellType.INACCESSIBLE, CellType.BUSH, CellType.PLAIN, CellType.INACCESSIBLE, CellType.PLAIN, CellType.BUSH},
                {CellType.KOULOU, CellType.KOULOU, CellType.INACCESSIBLE, CellType.KOULOU, CellType.PLAIN, CellType.INACCESSIBLE, CellType.PLAIN, CellType.PLAIN},
                {CellType.PLAIN, CellType.PLAIN, CellType.INACCESSIBLE, CellType.PLAIN, CellType.PLAIN, CellType.INACCESSIBLE, CellType.PLAIN, CellType.PLAIN},
                {CellType.NEXUS, CellType.NEXUS, CellType.INACCESSIBLE, CellType.NEXUS, CellType.NEXUS, CellType.INACCESSIBLE, CellType.NEXUS, CellType.NEXUS}
        };

        randomGenerateMap();
        map = printMap;

        List<StringBuilder> printableMap = new ArrayList<StringBuilder>();

        for (int row = 0; row < size * 3; row++) {
            printableMap.add(new StringBuilder());
            if ((row / 3) % 2 == 0){
                for (int col = 0; col < size; col++) {
                    if (row % 2 == 0){
                        createOutterCell(map, printableMap, row, col);
                    }else{
                        createInnerCell(map, printableMap, row, col);
                    }

                    if (col == size - 1)
                        printableMap.get(row).append("\n");
                }
            }else{
                for (int col = 0; col < size; col++) {
                    if (row % 2 == 1){
                        createOutterCell(map, printableMap, row, col);
                    }else{
                        createInnerCell(map, printableMap, row, col);
                    }

                    if (col == size - 1)
                        printableMap.get(row).append("\n");
                }
            }

            if (row % 3 == 2)
                printableMap.get(row).append("\n");
        }

        for (int i = 0; i < size * 3; i++) {
            System.out.print(printableMap.get(i));
        }
    }
}

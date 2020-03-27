public class patternBlink extends Pattern {
    private final static int[][] grid = new int[][] {// 1 represents an "alive" cell in the starting position
        {0,1,0},
        {0,1,0},
        {0,1,0}
    };

    @Override
    public int getSizeX() { return 3; }

    @Override
    public int getSizeY() { return 3; }

    @Override
    public boolean getCell(int x, int y) {
        return grid[y][x] == 1; // 1 represents an "alive" cell in the starting position
    }

}
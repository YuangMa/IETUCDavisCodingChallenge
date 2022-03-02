package CodingChallenge;
import java.util.*;

public class emasSupercomputer {
    static public void main(String[] args){
        List<String> grid = new ArrayList<>();
        grid.add("BBBGBGBBB");
        grid.add("BBBGBGBBB");
        grid.add("BBBGBGBBB");
        grid.add("GGGGGGGGG");
        grid.add("BBBGBGBBB");
        grid.add("BBBGBGBBB");
        grid.add("GGGGGGGGG");
        grid.add("BBBGBGBBB");
        grid.add("BBBGBGBBB");
        grid.add("BBBGBGBBB");
        emasSupercomputer solution = new emasSupercomputer();
        System.out.println(solution.twoPluses(grid));
    }

    // 2 <= m <= 15
    // 2 <= n <= 15
    public int twoPluses(List<String> grid){
        int m = grid.size();
        int n = grid.get(0).length();
        int[][] grid_array = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid.get(i).charAt(j) == 'G'){
                    grid_array[i][j] = 1;
                }else {
                    grid_array[i][j] = 0;
                }
            }
        }

        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid_array[i][j] == 1){
                    int idx1 = getPlus(grid_array, i, j);
                    for(int k = 0; k <= idx1; k++){
                        markGrid(grid_array, i, j, k);
                        int idx2 = findSecPlus(grid_array);
                        int area = (k * 4 + 1) * (idx2 * 4 + 1);
                        max = Math.max(max, area);
                        unMarkGrid(grid_array, i, j, k);
                    }
                }
            }
        }
        return max;
    }


    private int getPlus(int[][] grid_array, int row, int col){
        int m = grid_array.length;
        int n = grid_array[0].length;
        int idx = 0;
        for(int i = 1; i <= Math.min(m, n) / 2; i++){
            if((row + i) < m && (row - i) >= 0 && (col + i) < n && (col - i) >= 0){
                if(grid_array[row + i][col] == 0 || grid_array[row - i][col] == 0 ||
                        grid_array[row][col + i] == 0 || grid_array[row][col - i] == 0){
                    break;
                }else {
                    idx = i;
                }
            }else{
                break;
            }
        }
        return idx;
    }

    private int findSecPlus(int[][] grid_array){
        int m = grid_array.length;
        int n = grid_array[0].length;
        int secMax = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid_array[i][j] == 1){
                    int idx = getPlus(grid_array, i, j);
                    secMax = Math.max(secMax, idx);
                }
            }
        }
        return secMax;
    }


    private void markGrid(int[][] grid_array, int row, int col, int idx){
        for(int i = 0; i <= idx; i++){
            grid_array[row + i][col] = 0;
            grid_array[row - i][col] = 0;
            grid_array[row][col + i] = 0;
            grid_array[row][col - i] = 0;
        }
    }

    private void unMarkGrid(int[][] grid_array, int row, int col, int idx){
        for(int i = 0; i <= idx; i++){
            grid_array[row + i][col] = 1;
            grid_array[row - i][col] = 1;
            grid_array[row][col + i] = 1;
            grid_array[row][col - i] = 1;
        }
    }
}

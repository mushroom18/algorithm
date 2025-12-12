import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final int size;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private final WeightedQuickUnionUF uf; // for percolate check
    private final WeightedQuickUnionUF ufFull; // for isFull check
    private int openSitesCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.size = n;
        this.grid = new boolean[n][n];
        this.openSitesCount = 0;

        int totalSites = n * n;
        uf = new WeightedQuickUnionUF(totalSites + 2);
        ufFull = new WeightedQuickUnionUF(totalSites + 1);
        virtualTopSite = totalSites;
        virtualBottomSite = totalSites + 1;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("row and col must be between 1 and n");
        }
    }

    // convert 2d index to 1d index
    private int index(int row, int col) {
        return (row-1) * size + (col-1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        // IF SITE IS NOT OPEN, OPEN IT
        if (!isOpen(row, col)) {
            int siteIndex = index(row, col);
            grid[row-1][col-1] = true;
            openSitesCount++;
            // if the site is on the top row, connect to virtual top site
            if (row == 1) {
                uf.union(siteIndex, virtualTopSite);
                ufFull.union(siteIndex, virtualTopSite);
            }
            // if the site is on the bottom row, connect to virtual bottom site
            if (row == size) {
                uf.union(siteIndex, virtualBottomSite);
            }
            // connect to the site above
            if (row > 1 && isOpen(row-1, col)) {
                int neighborIndex = index(row-1, col);
                uf.union(siteIndex, neighborIndex);
                ufFull.union(siteIndex, neighborIndex);
            }
            // connect to the site below
            if (row < size && isOpen(row+1, col)) {
                int neighborIndex = index(row+1, col);
                uf.union(siteIndex, neighborIndex);
                ufFull.union(siteIndex, neighborIndex);
            }
            // connect to the left site
            if (col > 1 && isOpen(row, col-1)) {
                int neighborIndex = index(row, col-1);
                uf.union(siteIndex, neighborIndex);
                ufFull.union(siteIndex, neighborIndex);
            }
            // connect to the right site
            if (col < size && isOpen(row, col+1)) {
                int neighborIndex = index(row, col+1);
                uf.union(siteIndex, neighborIndex);
                ufFull.union(siteIndex, neighborIndex);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    // is the site connected to the virtual top site?
    public boolean isFull(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        int siteIndex = index(row, col);
        return ufFull.find(siteIndex) == ufFull.find(virtualTopSite);
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTopSite) == uf.find(virtualBottomSite);
    }

    // test client (optional)
    public static void main(String[] args) {
   
    }
}
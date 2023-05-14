import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * ASSESSMENT SUMMARY
 * Compilation:  PASSED
 * API:          PASSED
 * SpotBugs:     PASSED
 * PMD:          PASSED
 * Checkstyle:   PASSED
 * Correctness:  38/38 tests passed
 * Memory:       8/8 tests passed
 * Timing:       20/20 tests passed
 */
public class Percolation {
    private int n;
    private boolean[][] sites;
    private int openSites;
    private WeightedQuickUnionUF sitesConnectedToTheTop;
    private WeightedQuickUnionUF sitesPercolating;

    /**
     * Create n-by-b grid, with all sites initially blocked
     *
     * @param n size of the gird
     */
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        this.sites = new boolean[n][n];
        this.n = n;
        this.openSites = 0;

        sitesConnectedToTheTop = new WeightedQuickUnionUF(n * n);
        virtualTopSite(sitesConnectedToTheTop);

        sitesPercolating = new WeightedQuickUnionUF(n * n);
        virtualTopSite(sitesPercolating);
        virtualBottomSite(sitesPercolating);
    }

    /**
     * Open the site at (row, col) if it is not open already.
     *
     * @param row row of the site to open
     * @param col col of the site to open
     */
    public void open(int row, int col) {
        isGridItemInRange(row, col);

        if (!isOpen(row, col)) {
            sites[row - 1][col - 1] = true;
            openSites++;

            int index = calculateIndex(row, col);
            // element on the top
            if (!(row - 1 == 0) && isOpen(row - 1, col)) {
                int secondIndex = calculateIndex(row - 1, col);
                sitesPercolating.union(index, secondIndex);
                sitesConnectedToTheTop.union(index, secondIndex);
            }

            // element on the bottom
            if (!(row + 1 > n) && isOpen(row + 1, col)) {
                int secondIndex = calculateIndex(row + 1, col);
                sitesPercolating.union(index, secondIndex);
                sitesConnectedToTheTop.union(index, secondIndex);
            }

            // element on the left
            if (!(col - 1 == 0) && isOpen(row, col - 1)) {
                int secondIndex = calculateIndex(row, col - 1);
                sitesPercolating.union(index, secondIndex);
                sitesConnectedToTheTop.union(index, secondIndex);
            }

            // element on the right
            if (!(col + 1 > n) && isOpen(row, col + 1)) {
                int secondIndex = calculateIndex(row, col + 1);
                sitesPercolating.union(index, secondIndex);
                sitesConnectedToTheTop.union(index, secondIndex);
            }
        }
    }

    /**
     * Check is the site at (row, col) is open?
     *
     * @param row row of the site to check
     * @param col col of the site to check
     * @return {@code true} if the site is open, {@code false} otherwise
     */
    public boolean isOpen(int row, int col) {
        isGridItemInRange(row, col);
        return (sites[row - 1][col - 1]);
    }

    /**
     * Check if the site at (row, col) is full.
     *
     * @param row row of the site to check
     * @param col col of the site to check
     * @return {@code true} if site is full, {@code false} otherwise
     */
    public boolean isFull(int row, int col) {
        isGridItemInRange(row, col);
        if (!isOpen(row, col)) return false;

        return (sitesConnectedToTheTop.find(0) ==
                sitesConnectedToTheTop.find(calculateIndex(row, col)));
    }

    /**
     * Return number of open sites in the grid.
     *
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * Does the system percolate?
     *
     * @return {@code true} if system percolates, {@code false} otherwise
     */
    public boolean percolates() {
        if (n == 1 && !isOpen(1, 1)) return false;
        return (sitesPercolating.find(0) == sitesPercolating.find(n * n - 1));
    }

    private void virtualTopSite(WeightedQuickUnionUF uf) {
        for (int i = 1; i < this.n; i++) {
            uf.union(0, i);
        }
    }

    private void virtualBottomSite(WeightedQuickUnionUF uf) {
        for (int i = n * n - 2; i > (n * n - 1) - n; i--) {
            uf.union(n * n - 1, i);
        }
    }

    private void isGridItemInRange(int row, int col) {
        if ((row < 1 || row > n) || (col < 1 || col > n))
            throw new IllegalArgumentException();
    }

    private int calculateIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }
}

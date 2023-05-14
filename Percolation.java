import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[][] sites;
    private int openSites;
    private WeightedQuickUnionUF sitesConnetedToTheTop;
    private WeightedQuickUnionUF sitesPercolating;


    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.sites = new boolean[n][n];
        this.n = n;
        this.openSites = 0;

        this.sitesConnetedToTheTop = new WeightedQuickUnionUF(n * n);
        this.sitesPercolating = new WeightedQuickUnionUF(n * n);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean isOpen(int row, int col) {
        isGridItemInRange(row, col);
        return (sites[row - 1][col - 1]);
    }

    public void open(int row, int col) {
        isGridItemInRange(row, col);

        if (!isOpen(row, col)) {
            sites[row - 1][col - 1] = true;
            openSites++;

            int index = calculateIndex(row, col);
            // element on the top
            if (!(row - 1 == 0) && isOpen(row - 1, col)) {
                sitesPercolating.union(index, calculateIndex(row - 1, col));
                sitesConnetedToTheTop.union(index, calculateIndex(row - 1, col));
            }

            // element on the bottom
            if (!(row + 1 > n) && isOpen(row + 1, col)) {
                sitesPercolating.union(index, calculateIndex(row + 1, col));
                sitesConnetedToTheTop.union(index, calculateIndex(row + 1, col));
            }

            // element on the left
            if (!(col - 1 == 0) && isOpen(row, col - 1)) {
                sitesPercolating.union(index, calculateIndex(row, col - 1));
                sitesConnetedToTheTop.union(index, calculateIndex(row, col - 1));
            }

            // element on the right
            if (!(col + 1 > n) && isOpen(row, col + 1)) {
                sitesPercolating.union(index, calculateIndex(row, col + 1));
                sitesConnetedToTheTop.union(index, calculateIndex(row, col + 1));
            }
        }
    }

    public boolean isFull(int row, int col) {
        isGridItemInRange(row, col);
        if (!isOpen(row, col)) return false;


        // if (sitesConnetedToTheTop.find(0) != sitesConnetedToTheTop.find(n - 1))
        connectSitesOnFirstLine(this.sitesConnetedToTheTop);
        return (sitesConnetedToTheTop.find(0) ==
                sitesConnetedToTheTop.find(calculateIndex(row, col)));
    }

    public boolean percolates() {
        if (n == 1 && !isOpen(1, 1)) return false;

        // if (sitesPercolating.find(0) != sitesPercolating.find(n - 1))
        connectSitesOnFirstLine(sitesPercolating);
        // if (sitesPercolating.find(n * n - n) != sitesPercolating.find(n * n - 1))
        connectSitesOnLastLine(sitesPercolating);
        return (sitesPercolating.find(0) == sitesPercolating.find(n * n - 1));
    }

    private void isGridItemInRange(int row, int col) {
        if ((row < 1 || row > n) || (col < 1 || col > n))
            throw new IllegalArgumentException();
    }

    private int calculateIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    private void connectSitesOnFirstLine(WeightedQuickUnionUF uf) {
        for (int i = 1; i < this.n; i++) {
            if (sites[0][i - 1] != sites[0][i]) uf.union(0, i);
        }

    }

    private void connectSitesOnLastLine(WeightedQuickUnionUF uf) {
        for (int i = n * n - 2; i > (n * n - 1) - n; i--) {
            uf.union(n * n - 1, i);
        }

        for (int i = n * n - 2; i > (n * n - 1) - n; i--) {
            if (sites[n - 1][i - (n - 1) * n] != sites[n - 1][(i + 1) - (n - 1) * n])
                uf.union(n * n - 1, i);
        }

        // for (int i = 1; i < n; i++) {
        //     if (sites[n - 1][i - 1] != sites[n - 1][i])
        //         uf.union(n * n - 1, (n * n) - 1 - i);
        // }
    }


}

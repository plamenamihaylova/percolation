import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private double[] allThresholds;
    private double trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        // init all sites to be blocked
        Percolation percolation;
        allThresholds = new double[trials];
        double threshold = 0;
        double gridSize = n * n;
        this.trials = trials;

        for (int i = trials; i > 0; i--) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row;
                int col;
                // choose a site uniformly at random among all blocked sites
                do {
                    row = StdRandom.uniformInt(1, n + 1);
                    col = StdRandom.uniformInt(1, n + 1);
                } while (percolation.isOpen(row, col));
                // open the site
                percolation.open(row, col);
                // the fraction of opened sites when the system percolates is an estimate of the percolation threshold
            }
            allThresholds[i - 1] = percolation.numberOfOpenSites() / gridSize;
            threshold += allThresholds[i - 1];
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(allThresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(allThresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));

    }

    // test client (see below)
    public static void main(String[] args) {

        if (args.length != 0) {
            PercolationStats
                    test = new PercolationStats(Integer.parseInt(args[0]),
                                                Integer.parseInt(args[1]));
            double mean = test.mean();
            double stddev = test.stddev();
            double low = test.confidenceLo();
            double high = test.confidenceHi();

            StdOut.printf("%-23s = %.16f", "mean", mean);
            StdOut.println();
            StdOut.printf("%-23s = %.16f", "stddev", stddev);
            StdOut.println();
            StdOut.printf("%-23s = [%.16f, %.16f]", "95% confidence interval", low, high);
        }

    }
}

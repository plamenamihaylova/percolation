<img height="400" src="logo.png" title="percolation logo" width="1000"/>

### Percolation

**Algorithms Part 1 | Coursera course | Week 1
Assignment | [my coursera profile](https://www.coursera.org/user/045cf702be8b31ef1aa039e2b4f07db6)**

---
<!-- TOC -->

* [Percolation](#percolation)
* [Task specification](#task-specification)
    * [Percolation API](#percolation-api)
    * [PercolationStats API](#percolationstats-api)
* [Useful resources that have helped me in completing this assignment](#useful-resources-that-have-helped-me-in-completing-this-assignment)

<!-- TOC -->

---

### Task specification

🔗Detailed specifications for the assignment can be
found [here](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php).

The task is to write a program that estimates the value of the percolation threshold via Monte Carlo simulation.

**Percolation.**
Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the
materials need to be metallic so that the composite system is an electrical conductor?
Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain
through to the bottom (or the oil to gush through to the surface)?
Scientists have defined an abstract process known as percolation to model such situations.

**The model.**
We model a percolation system using an n-by-n grid of sites.
Each site is either open or blocked.
A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left,
right, up, down) open sites.
We say the system percolates if there is a full site in the bottom row.
In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open
site on the bottom row.

**The problem.**
In a famous scientific problem, researchers are interested in the following question: if sites are independently set to
be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system
percolates?
When p equals 0, the system does not percolate; when p equals 1, the system percolates.
When n is sufficiently large, there is a threshold value p* such that when p < p* a random n-by-n grid almost never
percolates, and when p > p*, a random n-by-n grid almost always percolates.
No mathematical solution for determining the percolation threshold p* has yet been derived.

---

#### Percolation API

```
public class Percolation { 
    public Percolation(int n)                   // creates n-by-n grid, with all sites initially blocked
    public void open(int row, int col)          // opens the site (row, col) if it is not open already
    public boolean isOpen(int row, int col)     // is the site (row, col) open?
    public boolean isFull(int row, int col)     // is the site (row, col) full?
    public int numberOfOpenSites()              // returns the number of open sites
    public boolean percolates()                 // does the system percolate?
    public static void main(String[] args)      // test client
}
```

---

#### PercolationStats API

```
public class PercolationStats {
    public PercolationStats(int n, int trials)  // perform independent trials on an n-by-n grid
    public double mean()                        // sample mean of percolation threshold
    public double stddev()                      // sample standard deviation of percolation threshold
    public double confidenceLo()                // low endpoint of 95% confidence interval
    public double confidenceHi()                // high endpoint of 95% confidence interval
    public static void main(String[] args)      // test client (see below)
}
```

---

### Useful resources that have helped me in completing this assignment

🔗[Assignment video pt.1](https://www.youtube.com/watch?v=kIYKCsvG6UI)

🔗[Assignment video pt.2](https://www.youtube.com/watch?v=QDUya7s3_Zg)

🔗[Assignment video pt.3](https://www.youtube.com/watch?v=1RQz8ITHLME)

🔗[Percolation problem exploanation](https://www.youtube.com/watch?v=KNgfOmlLgh8&t=1s)

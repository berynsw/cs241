A) Drew Gawlinski, Beryn Staub-Waldenberg
B) All parts of the program are working to our knowledge.
C) To test our program, we wanted a test-case which we could work through by hand, so we could compare every step to make sure it matched.
We copied an example graph we found online into a new file, test.csv.
We solved most of the issues we encountered by comparing with what we did on paper.

A particularly nasty problem that lead to some confusion was traversing the path during printing the output.
It seemed to get the first 0-2 steps correct, but it would become a mess after that.
We're not entirely sure what the issue was, but it went away when we redid how it worked.

We used an adjacency list instead of an adjacency matrix, which improved the time complexity.
We also used a heap, instead of a normal list to sort the shortest distances.

It uses DigahfndIOSFHISDKF's algorithm, which is not in fact exploring and testing every possible path, which significantly improved efficiency.
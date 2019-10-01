1. Drew Gawlinski, Beryn Staub-Waldenberg

2. All aspects of the program are working.

3.
add O(n)
delete O(n)
highcount O(n)
get_avg_successful_search O(1)
get_avg_unsuccessful_search O(1)

4.

After writing the program, we were confused to find we got a different result than the example on the PDF.
However, after further testing we became suspicious that the example might be wrong.
So, we opened "AliceInWonderland.txt" in a text editor and did a search for "the ".
Sure enough, once we subtracted the three instances of "breathe ", it gave us the same result that our program gave us.
Eventually, we noticed that if we made the hash function treat the string as if it were lower-case, it gave the results that the example gave.
To be honest, it could have been more clear that we were supposed to treat words as lower-case. Nothing in the document mentions that we need to account for this.

As for how we tested that our program was asymptotically efficient, we originally thought it was going to be O(log n), because we were doing Binary Search.
However, upon inspecting the amount of comparisons happening, we realized that we forgot about double hashing's impact on time complexity.
The worst case is O(n * log(n)), which would be if you fed only colliding strings. However, this would be unlikely because of how rare colliding strings are, so realistically, your average time complexity is O(log(n))

Or so we thought. Upon further thinking, we realized that it couldn't possibly be iterating over more words than the amount of words put in, based on how our implementation worked.
This put a hard maximum of O(n). After discussing, we realized the reason that this was the case was because the binary search and the linear search were separate.
They do not compound, so if the binary search tree takes 500 comparisons, it won't change how many times the linear search runs.

This turns our original idea of:
O(n * log n) into O(n + log n) which is just O(n)

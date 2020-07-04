/**
 * Use a HashMap to store the each number and it's count.
 * when k == 0, we look for numbers which have more than two count.
 * when k > 0, for each number, we look for num-2 in the map.
 */


class Solution {
    public int findPairs(int[] nums, int k) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap();
        for(int num : nums) {
            map.putIfAbsent(num, 0);
            map.put(num, map.get(num)+1);
        }
        
        for(Integer num : map.keySet()) {
            if(k == 0 && map.get(num) > 1) ++result;
            else if (k > 0 && map.containsKey(num - k)){
                ++result;
            }
        }
        
        return result;
    }
}

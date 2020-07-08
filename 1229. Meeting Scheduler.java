/**
 * First, sort the two given arrays to make them align upon the starting time.
 * Then, check the two time slots if they have intersection or not from the beginning of the two arrays.
 * If they have intersection, we check if it is bigger than the demanded duration.
 * Also, when no intersection, we move the index of the earlier time zone forward.
 * When intersection occure, we move the time zone with earlier end time forward
 */


class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {

        int index1 = 0, index2 = 0;
        List<Integer> result = new ArrayList();
        
        Comparator<int[]> comp = (a, b) -> a[0] - b[0];
        Arrays.sort(slots1, comp);
        Arrays.sort(slots2, comp);
        
        while(index1 < slots1.length && index2 < slots2.length) {
            if(slots1[index1][1] < slots2[index2][0]) {
                index1 += 1;
            }
            else if(slots1[index1][0] > slots2[index2][1]) {
                index2 += 1;
            }
            else {
                int start = Math.max(slots1[index1][0], slots2[index2][0]);
                int end = Math.min(slots1[index1][1], slots2[index2][1]);
                if(end - start >= duration) {
                    result.add(start);
                    result.add(start + duration);
                    return result;
                }
                
                if(slots1[index1][1] < slots2[index2][1]) {
                    index1 += 1;
                }
                else {
                    index2 += 1;
                }
            }
        }
        
        return result;
    }
}

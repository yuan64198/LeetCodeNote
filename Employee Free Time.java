/**
 * This prolem has the same idea with another prolem "Merge Interval".
 * We first merge the working hours of all employees by using a stack.
 * When the incoming working interval has intersection with the peek of the stack, we pop the peek out, merge the two and put the new interval back.
 * If no intersection, we put the interval into the stack.
 * And we calculate the gap between each two intervals to get the free time interval.
 */

/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        
        List<Interval> workTime = mergeWorkTime(schedule);
        List<Interval> freeTime = new LinkedList();
        
        for(int i = 0; i < workTime.size() - 1; ++i) {
            Interval curr = workTime.get(i);
            Interval next = workTime.get(i + 1);
            freeTime.add(new Interval(curr.end, next.start));
        }
        
        return freeTime;
    }
    private List<Interval> mergeWorkTime(List<List<Interval>> schedule) {
        
        Comparator<Interval> scheduleRank = (a, b) -> a.start - b.start;
        PriorityQueue<Interval> pq = new PriorityQueue(scheduleRank);
        
        schedule.forEach(e -> pq.addAll(e));
        
        Stack<Interval> stack = new Stack();
        
        while(!pq.isEmpty()) {
            Interval curr = pq.poll();
            if(!stack.isEmpty() && curr.start <= stack.peek().end) {
                Interval prev = stack.pop();
                stack.push(new Interval(Math.min(prev.start, curr.start), Math.max(prev.end, curr.end)));
            }
            else {
                stack.push(curr);
            }
        }
        List<Interval> result = new LinkedList();
        while(!stack.isEmpty()) {
            result.add(0, stack.pop());
        }
        return result;
    }
}

/**
 * A lot of corner cases to be awared of.
 */

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList();
        int i = 0;
        while(i < words.length) {
            int start = i;
            int remainWidth = maxWidth;
            StringBuilder sb = new StringBuilder();
            int totalStringWidth = 0;
            while(i < words.length && remainWidth - words[i].length() >= 0) {
                remainWidth -= (words[i].length()+1);
                totalStringWidth += words[i++].length();
            }
            
            int end = i;
            int num_words = end-start;
            
            
            int totalSpaces = maxWidth - totalStringWidth;
            if(num_words == 1 || end == words.length) {
                for(int j=start; j<end; ++j) {
                    sb.append(words[j]);
                    if(sb.length() < maxWidth) sb.append(' ');
                    totalSpaces--;
                }
                if(totalSpaces > 0) {
                    char[] arr = new char[totalSpaces];
                    Arrays.fill(arr, ' ');
                    sb.append(arr);
                }
            }
            else {
                int num_extraSpaces = totalSpaces%(num_words-1);
                int spaceBetweenWords = totalSpaces/(num_words-1);

                char[] arr = new char[spaceBetweenWords];
                Arrays.fill(arr, ' ');

                for(int j=start; j<end; j++) {
                    sb.append(words[j]);
                    if(num_extraSpaces-- > 0) {
                        sb.append(' ');
                    }
                    if(j != end-1) {
                        sb.append(arr);
                    }
                }
            }
            result.add(sb.toString());
        }
        
        return result;
    }
}

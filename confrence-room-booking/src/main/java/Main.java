import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        String[] strs = {"eat","tea”,”elbow”,”ate”,”below”,”bat"};
        List<List<String>> result = groupAnagrams(strs);
        result.forEach(System.out::println);

        //Output: [["bat”],[“below”,”elbow”],[“ate","eat","tea"]]

        // sort: characters
        // ["aet","aet”,”below”,”aet”,”below”,”abt"]


    }


    private static List<List<String>> groupAnagrams(String[] words){
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String word : words) {
            char[] ch = word.toCharArray();
            Arrays.sort(ch);
            String sortedStr = String.valueOf(ch);
            if (!groupMap.containsKey(sortedStr))
                groupMap.put(sortedStr, new ArrayList<>());
            groupMap.get(sortedStr).add(word);
        }
        return new ArrayList<>(groupMap.values());
    }
}
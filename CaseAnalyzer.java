package CSI_Project;

import java.util.*;

public class CaseAnalyzer {

    private static Map<Suspect, Set<String>> guiltyMatchMap = new HashMap<>();

    /**
     * Assign EXACT 3 evidence types the guilty suspect will match.
     * All other suspects get 0 matches.
     */
    public static void assignGuiltyMatches(Suspect guilty, List<Evidence> evidenceList) {

        guiltyMatchMap.clear();

        // Pick 3 random evidence TYPES
        Set<String> allTypes = new HashSet<>();
        for (Evidence e : evidenceList) {
            allTypes.add(e.getType());
        }

        List<String> typeList = new ArrayList<>(allTypes);
        Collections.shuffle(typeList);

        Set<String> chosen3 = new HashSet<>();
        for (int i = 0; i < 3 && i < typeList.size(); i++) {
            chosen3.add(typeList.get(i));
        }

        guiltyMatchMap.put(guilty, chosen3);
    }

    /** Returns number of matches (0–3). */
    public static int countMatchesForSuspect(Suspect suspect, List<Evidence> evidenceList) {

        if (!guiltyMatchMap.containsKey(suspect))
            return 0;  // Innocent suspects match nothing

        Set<String> guiltyTypes = guiltyMatchMap.get(suspect);

        int matches = 0;
        for (Evidence e : evidenceList) {
            if (guiltyTypes.contains(e.getType())) {
                matches++;
            }
        }

        return Math.min(3, matches);
    }

    /** True only if suspect is the assigned guilty one. */
    public static boolean isRealGuilty(Suspect suspect) {
        return guiltyMatchMap.containsKey(suspect);
    }

	public static boolean evidenceMatches(Suspect s, Evidence ev) {
		// TODO Auto-generated method stub
		return false;
	}
}

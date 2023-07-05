package io.zipcoder;


/**
 * @author tariq
 */
public class StringsAndThings {

    /**
     * Given a string, count the number of words ending in 'y' or 'z' -- so the 'y' in "heavy" and the 'z' in "fez" count,
     * but not the 'y' in "yellow" (not case sensitive). We'll say that a y or z is at the end of a word if there is not an alphabetic
     * letter immediately following it. (Note: Character.isLetter(char) tests if a char is an alphabetic letter.)
     * example : countYZ("fez day"); // Should return 2
     *           countYZ("day fez"); // Should return 2
     *           countYZ("day fyyyz"); // Should return 2
     */
    public Integer countYZ(String input){

        int count = 0;
        // \s+ reconizgizes spaces as a delimiter
        String [] word = input.split("\\s+");

        for (int i=0; i<word.length; i++) {
            //iterating thru each of the words
            String splitWord = word[i];
            //get the last character of split words
            char lastChar = splitWord.charAt(splitWord.length()-1);

            if ((lastChar) == 'y' || (lastChar) == 'z') {
                if(Character.isLetter(lastChar)) {
                    count ++;
                }
            }


        }
        return count;
    }

    /**
     * Given two strings, base and remove, return a version of the base string where all instances of the remove string have
     * been removed (not case sensitive). You may assume that the remove string is length 1 or more.
     * Remove only non-overlapping instances, so with "xxx" removing "xx" leaves "x".
     *
     * example : removeString("Hello there", "llo") // Should return "He there"
     *           removeString("Hello there", "e") //  Should return "Hllo thr"
     *           removeString("Hello there", "x") // Should return "Hello there"
     */
    public String removeString(String base, String remove){

        StringBuilder sb = new StringBuilder();
        int removeLength = remove.length ();
        int baseLength = base.length();

        /*starts at index 0 and ends at base length -remove length = remaining base
        which potentially matches with remove string */
        for (int i=0;i<=baseLength-removeLength; ) {

            /*in the substring... i= where the beginning of base and remove match and
             i+remove is taking that index where it matches and adding it to length of
             remove length ...resulting in a substring that specifies where remove length
             is in base length....

             for example:

             in base string "Hello there" and in the remove string "llo"
             after starting at 0 and iteratng ...i=2 bc "llo" starts at index 2
             now if u add 2(i) + 3(the length of the remove string) = 5... 5 is
             the end of substring...

             so "llo" is base.substring(2,5)


             */
            if(base.substring(i,i+removeLength).equalsIgnoreCase(remove)) {
                /* removeLength is getting added to i if substring and remove string match

                During the iteration when i is 2, we find a match between the substring "llo" (extracted from the base string)
                and the remove string "llo".

                Instead of continuing the loop from index 3, we want to skip over the matched substring and continue the search.
                So, we increment i by removeLength, which is 3(llo) in this case.
                */

                i += removeLength;
            } else {
                //appending the leftover non matched chars to sb
                sb.append(base.charAt(i));
                /*incrementing by one to ensure loop loop goes thru base string and either matches
                or appends*/
                i++;

            }
        }
        /*final appending of the remaining characters from the base string, starting from the position
         after the last potential match of the remove string to the rest of base string
        adds 1 to start after the last position where a match could potentially occur.
        */
        sb.append(base.substring(baseLength-removeLength+1));
        return sb.toString();
    }

    /**
     * Given a string, return true if the number of appearances of "is" anywhere in the string is equal
     * to the number of appearances of "not" anywhere in the string (case sensitive)
     *
     * example : containsEqualNumberOfIsAndNot("This is not")  // Should return false
     *           containsEqualNumberOfIsAndNot("This is notnot") // Should return true
     *           containsEqualNumberOfIsAndNot("noisxxnotyynotxisi") // Should return true
     */
    public Boolean containsEqualNumberOfIsAndNot(String input) {

        boolean equalCounts = true;
        //keep track of starting position to look for "is"
        int start =0;
        //keeps track of the occurances of "is"
        int count =0;

        //while loop to count for "is"
        //i think -1 works for start bc it will start at 0
        //will keep checking for is as long as "start" is not -1
        while (start!= -1) {
            //.indexOf find index where is starts
            start =input.indexOf("is", start);
                if (start != -1) {
                    count++;
                    // 2 bc length of 'is' is 2
                    start += 2;
                }

             }
        int countForIs =count;
            //same while loop again for "not"
            start = 0;
            count =0;

            while (start != -1) {
                start = input.indexOf("not",start);
                    if (start != -1) {
                        count++;
                        //3 bc not is 3 letters
                        start+=3;
                    }
            }
            int countForNot = count;

            equalCounts = (countForIs == countForNot);

            return equalCounts;
    }

    /**
     * We'll say that a lowercase 'g' in a string is "happy" if there is another 'g' immediately to its left or right.
     * Return true if all the g's in the given string are happy.
     * example : gHappy("xxggxx") // Should return  true
     *           gHappy("xxgxx") // Should return  false
     *           gHappy("xxggyygxx") // Should return  false
     */
    public Boolean gIsHappy(String input) {

        for (int i = 0; i < input.length(); i++) {
            //with iteration, if statement checks if any char is 'g'
            if (input.charAt(i) == 'g') {
                //another if statement to check chars on the left and right
                //i>0 ensures we dont go out of bounds incase g is the first letter
                //i<input.length()-1 is incase g is the last letter
                if ((i > 0 && input.charAt(i - 1) == 'g') || (i < input.length() - 1 && input.charAt(i + 1) == 'g')) {
                    //jumps out of if statement and does not go to the else

                    return true;

                } else if ((i > 0 && input.charAt(i - 1) != 'g') || (i < input.length() - 1 && input.charAt(i + 1) != 'g')) {

                    return false;
                } else {
                    return false;

                }

            }

        }
        return false;
    }


    /**
     * We'll say that a "triple" in a string is a char appearing three times in a row.
     * Return the number of triples in the given string. The triples may overlap.
     * example :  countTriple("abcXXXabc") // Should return 1
     *            countTriple("xxxabyyyycd") // Should return 3
     *            countTriple("a") // Should return 0
     */
    public Integer countTriple(String input){

        int count =0;
        int letters =input.length();
        /*-2 ensures there are atleast two characters remaining after the current position to evaluate
        triple letter*/
        for (int i=0; i < letters-2;i++) {
            //char currentLetter =input.charAt(i);
            //checking the next two letters on the right
            if (input.charAt(i)==input.charAt(i+1) && input.charAt(i)==input.charAt(i+2)) {
                count++;
            }
        }

        return count;
    }
}




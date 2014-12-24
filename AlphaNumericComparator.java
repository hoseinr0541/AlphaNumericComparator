package com.tests.StringAlgo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Sorter that compares the given Alpha-numeric strings. This iterates through
 * each characters to decide the sort order. There are 2 possible cases while
 * iterating,
 * 
 * <li>When the current character under comparison in at-least one of the given
 * String is a non-digit the particular character alone compared lexically.</li>
 * 
 * <li>When the current character under comparison in both of the String is a
 * digit, the consecutive digit characters in both the Strings will be
 * considered for comparison.</li>
 * 
 * <li>At any point if the comparison confirms the order(either > or <) then
 * the consecutive characters will not be considered.</li>
 * 
 * For ex., this will be the ordered O/P of the given list of Strings.(The bold
 * characters decides its order)
 * <i>
 * 	<b>2</b>b,<b>100</b>b,a<b>1</b>,A<b>2</b>y,a<b>100</b>
 * </i>
 * 
 * @author kannangce
 * 
 */
public class AlphaNumericComparator implements Comparator<String>
{
   /**
    * Does the Alphanumeric sort of the given two string
    */
   public int compare(String theStr1, String theStr2)
   {
       char[] theCharArr1 = theStr1.toCharArray();
       char[] theCharArr2 = theStr2.toCharArray();
       int aPosition = 0;
       if (Character.isDigit(theCharArr1[aPosition]) && Character.isDigit(theCharArr2[aPosition]))
       {
           return sortAsNumber(theCharArr1, theCharArr2, aPosition++ );
       }
       return sortAsString(theCharArr1, theCharArr2, 0);
   }

   /**
    * Sort the given Arrays as string starting from the given position. This will be a simple case
    * insensitive sort of each characters. But at any given position if there are digits in both
    * arrays then the method sortAsNumber will be invoked for the given position.
    * 
    * @param theArray1 The first character array.
    * @param theArray2 The second character array.
    * @param thePosition The position starting from which the calculation will be done.
    * @return positive number when the Array1 is greater than Array2<br/>
    *         negative number when the Array2 is greater than Array1<br/>
    *         zero when the Array1 is equal to Array2
    */
   private int sortAsString(char[] theArray1, char[] theArray2, int thePosition)
   {
       int aResult = 0;
       if (thePosition < theArray1.length && thePosition < theArray2.length)
       {
           aResult = (int)theArray1[thePosition] - (int)theArray2[thePosition];
           if (aResult == 0)
           {
               ++thePosition;
               if (thePosition < theArray1.length && thePosition < theArray2.length)
               {
                   if (Character.isDigit(theArray1[thePosition]) && Character.isDigit(theArray2[thePosition]))
                   {
                       aResult = sortAsNumber(theArray1, theArray2, thePosition);
                   }
                   else
                   {
                       aResult = sortAsString(theArray1, theArray2, thePosition);
                   }
               }
           }
       }
       else
       {
           aResult = theArray1.length - theArray2.length;
       }
       return aResult;
   }

   /**
    * Sorts the characters in the given array as number starting from the given position. When
    * sorted as numbers the consecutive characters starting from the given position upto the first
    * non-digit character will be considered.
    * 
    * @param theArray1 The first character array.
    * @param theArray2 The second character array.
    * @param thePosition The position starting from which the calculation will be done.
    * @return positive number when the Array1 is greater than Array2<br/>
    *         negative number when the Array2 is greater than Array1<br/>
    *         zero when the Array1 is equal to Array2
    */
   private int sortAsNumber(char[] theArray1, char[] theArray2, int thePosition)
   {
       int aResult = 0;
       int aNumberInStr1;
       int aNumberInStr2;
       if (thePosition < theArray1.length && thePosition < theArray2.length)
       {
           if (Character.isDigit(theArray1[thePosition]) && Character.isDigit(theArray1[thePosition]))
           {
               aNumberInStr1 = getNumberInStr(theArray1, thePosition);
               aNumberInStr2 = getNumberInStr(theArray2, thePosition);

               aResult = aNumberInStr1 - aNumberInStr2;

               if (aResult == 0)
               {
                   thePosition = getNonDigitPosition(theArray1, thePosition);
                   if (thePosition != -1)
                   {
                       aResult = sortAsString(theArray1, theArray2, thePosition);
                   }
               }
           }
           else
           {
               aResult = sortAsString(theArray1, theArray2, ++thePosition);
           }
       }
       else
       {
           aResult = theArray1.length - theArray2.length;
       }
       return aResult;
   }

   /**
    * Gets the position of the non digit character in the given array starting from the given
    * position.
    * 
    * @param theCharArr /the character array.
    * @param thePosition The position after which the array need to be checked for non-digit
    *        character.
    * @return The position of the first non-digit character in the array.
    */
   private int getNonDigitPosition(char[] theCharArr, int thePosition)
   {
       for (int i = thePosition; i < theCharArr.length; i++ )
       {
           if ( !Character.isDigit(theCharArr[i]))
           {
               return i;
           }
       }
       return -1;
   }

   /**
    * Gets the integer value of the number starting from the given position of the given array.
    * 
    * @param theCharArray The character array.
    * @param thePosition The position form which the number need to be calculated.
    * @return The integer value of the number.
    */
   private int getNumberInStr(char[] theCharArray, int thePosition)
   {
       int aNumber = 0;
       for (int i = thePosition; i < theCharArray.length; i++ )
       {
           if(!Character.isDigit(theCharArray[i]))
           {
              return aNumber;
           }
           aNumber += aNumber * 10 + (theCharArray[i] - 48);
       }
       return aNumber;
   }
   
   public static void main(String[] a)
	{
		List<String> aList = Arrays.asList(new String[]{"a200","a4","b213","b 3232","b-232","b021","x12312","123x","10b"});
		Collections.sort(aList, new AlphaNumericComparator());
		System.out.println(aList);
	}
}
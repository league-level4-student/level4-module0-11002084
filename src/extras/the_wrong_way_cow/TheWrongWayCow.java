//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!
    	int[][] checkArr = new int[3][3];
    	int[] resultArr = new int[0];
    	int arrSpacesLeft = 3;
    	
        for(int j = 0; j < field.length; j++) {
        	for(int i = 0; i < field[j].length; i++) {
        		if(field[j][i] == 'c') {
        			if(i >= 2 && field[j][i-1] == 'o') {
        				System.out.println(0);
        				resultArr = changeArr(i, j, 0, checkArr, arrSpacesLeft);
        				if(resultArr.length != 0) {
        					System.out.println(resultArr[0] + " " + resultArr[1]);
        					return resultArr;
        				}
        			}
        			else if(i <= field[j].length - 3 && field[j][i+1] == 'o') {
        				System.out.println(1);
        				resultArr = changeArr(i, j, 1, checkArr, arrSpacesLeft);
        				if(resultArr.length != 0) {
        					System.out.println(resultArr[0] + " " + resultArr[1]);
        					return resultArr;
        				}
        			}
        			else if(j >= 2 && field[j-1][i] == 'o') {
        				System.out.println(2);
        				resultArr = changeArr(i, j, 2, checkArr, arrSpacesLeft);
        				if(resultArr.length != 0) {
        					System.out.println(resultArr[0] + " " + resultArr[1]);
        					return resultArr;
        				}
        			}
        			else if(j <= field.length - 3 && field[j+1][i] == 'o') {
        				System.out.println(3);
        				resultArr = changeArr(i, j, 3, checkArr, arrSpacesLeft);
        				if(resultArr.length != 0) {
        					System.out.println(resultArr[0] + " " + resultArr[1]);
        					return resultArr;
        				}
        			}
        			
        			if(arrSpacesLeft > 0) {
        				arrSpacesLeft--;
        			}
        		}
        	}
        }
        return null;
    }
    
    static int[] changeArr(int dimensionOne, int dimensionTwo, int direction, int[][] checkArr, int spacesLeft) {
    	if(spacesLeft > 1) {
    		checkArr[spacesLeft - 1][0] = dimensionOne;
    		checkArr[spacesLeft - 1][1] = dimensionTwo;
    		checkArr[spacesLeft - 1][2] = direction;
    		return new int[0];
    	} else {
    		checkArr[0][0] = dimensionOne;
    		checkArr[0][1] = dimensionTwo;
    		checkArr[0][2] = direction;
    		if(checkArr[0][2] == checkArr[1][2] && checkArr[0][2] == checkArr[2][2]) {
    			return new int[0];
    		} else if(checkArr[0][2] != checkArr[1][2] && checkArr[0][2] != checkArr[2][2]) {
    			return new int[] {checkArr[0][0], checkArr[0][1]};
    		} else if(checkArr[1][2] != checkArr[0][2] && checkArr[1][2] != checkArr[2][2]) {
    			return new int[] {checkArr[1][0], checkArr[1][1]};
    		} else if(checkArr[2][2] != checkArr[0][2] && checkArr[2][2] != checkArr[1][2]){
    			return new int[] {checkArr[2][0], checkArr[2][1]};
    		} else {
    			return new int[0];
    		}
    	}
    }
}

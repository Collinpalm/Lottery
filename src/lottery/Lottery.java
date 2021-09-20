package lottery;

import java.util.Random;
import java.lang.Math;

/* Name:
 * Collin Palm
 * COP3503 Fall 2021
 * Programming Assignment 1
 */
//testing
public class Lottery {
    private static String ticket;//string attribute
    public Lottery(){
        ticket = "";
    }//default constructor
    //contructor that takes a Random object
    public Lottery(Random num){
        int t = 0;
        for(int i = 0;i<6;i++){
            if(i == 0){
                t = num.nextInt(10);
            }else {
                t += (num.nextInt(10)*(int)(Math.pow(10, i)));
            }
        }
        this.ticket = Integer.toString(t);
    }
    //getter method
    public static String GetTicket() {
        return ticket;
    }
    //method to swap sort the array(I could do a fancyier one but I didnt feel like it)
    public static void Sort(Lottery[] arr){
        int len = arr.length-1;
        Lottery temp;
        boolean flag = true;
        while(flag){
            flag = false;
            for (int i = 0; i < len; i++) {
                if (Integer.parseInt(arr[i].GetTicket()) < Integer.parseInt(arr[i + 1].GetTicket())) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = true;
                }
            }
        }
    }
    //method to generate a random ticket
    public static String GenerateRandomWinner(Random num){
        int t = 0;
        for(int i = 0;i<6;i++){
            if(i == 0){
                t = num.nextInt(10);
            }else {
                t = t + (num.nextInt(10)*(int)(Math.pow(10, i)));
            }
        }
        return Integer.toString(t);
    }
    //method to generate a random index to be a winner
    public static int GenerateSelectWinner( int max, Random num){
        return num.nextInt(max);
    }
    //solution 1 that runs in O(n)
    public static boolean Solution1(Lottery[] arr, String winner, int len){
        for(int i = 0; i < len; i++){
            if(arr[i].GetTicket().equals(winner)){
                return true;
            }
        }
        return false;
    }
    //solution 2 that runs in O(logn)
    public static boolean Solution2(Lottery[] arr, int pos, int len, String winner){
        if(len > pos){
            int mid = pos + (len-pos)/2;
            if (arr[mid].GetTicket().equals(winner)) {
                return true;
            }
            if(Integer.parseInt(arr[mid].GetTicket()) > Integer.parseInt(winner)){
                return Solution2(arr, pos, mid-1, winner);
            }
            return Solution2(arr, mid+1, len, winner);
        }
        return false;
    }
}

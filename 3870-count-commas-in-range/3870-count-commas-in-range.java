class Solution {
    public int countCommas(int n) {
       int answer=0;
       for(int i=1;i<=n;i++) {
        if(i<1000){
            answer=answer+0;
        }
        else{
            answer=answer+1;
        }
       }
       return answer;
    }
}
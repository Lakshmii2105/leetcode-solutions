class Solution {
    public int reverseDegree(String s) {
        int sum=0;
        for(int i=1;i<=s.length();i++){
            char ch=s.charAt(i-1);
            int num=27-(ch-'a'+1);
             sum+=i*num;
        }
        return sum;
        
    }
}
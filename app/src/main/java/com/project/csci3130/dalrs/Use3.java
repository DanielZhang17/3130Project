package com.project.csci3130.dalrs;

public class Use3 {
    private String time;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    public Use3(String time, String monday, String tuesday,String wednesday,String thursday,String friday) {
        this.time=time;
        this.monday=monday;
        this.tuesday=tuesday;
        this.wednesday=wednesday;
        this.thursday=thursday;
        this.friday=friday;
    }
    public String gettime(){
        return time;
    }
    /*public void settime(String t){
        time=t;
    }*/
    public String getmonday(){
        return monday;
    }
    /*public void setmonday(String m){
        monday=m;
    }*/

    public String gettuesday(){
        return tuesday;
    }
    /*public void settuesday(String t){
        tuesday=t;
    }*/
    public String getwednesday(){return wednesday;}
    public String getthursday(){ return thursday;}
    public String getfriday(){return friday;}
}


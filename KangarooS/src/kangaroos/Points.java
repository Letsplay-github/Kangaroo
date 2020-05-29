/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kangaroos;


/**
 *
 * @author hello
 */
public class Points implements Comparable<Points>{
    static int numberofcolony = 0;
    static int colony = 3; //colony value

    private LinkedList<Route> route = new LinkedList<>();
    private int pointID;
    private int maxkang;
    private int numroute;
    
    private int food;
    private int numofmale=0;
    private int numoffemale = 0;
    private boolean colonized = false;
    
    public Points(){
    }
    
    public Points(int pointID, int food, int maxkang, int numroute ){
        this.pointID = pointID;
        this.food = food;
        this.maxkang = maxkang;
        this.numroute = numroute;
        
    }
    
    public int getpointID(){
        return pointID;
    }
    
    public int getnumroute(){
        return numroute;
    }
    
    public void addroute(Route newroute){
        this.route.addsortNode(newroute);
    }
    
    public Route getRoute(int i){
        return route.atindex(i);
    }

    public boolean iscolonized(){
        return colonized;
    }

    public void setcolonized(boolean colonized){
        this.colonized = colonized;
    }

    public int getmale(){
        int totalmale=0;
        for(int i = 0;i<JumpGroove.Kangaroos.length();i++)
            if(JumpGroove.Kangaroos.atindex(i).getGender().equals('M')&&JumpGroove.Kangaroos.atindex(i).getpointID()==getpointID())
                totalmale++;
        numofmale = totalmale;
        return numofmale;
    }
    
    public int getfemale(){
        int totalfemale=0;
        for(int i = 0;i<JumpGroove.Kangaroos.length();i++)
            if(JumpGroove.Kangaroos.atindex(i).getGender().equals('F')&&JumpGroove.Kangaroos.atindex(i).getpointID()==getpointID())
                totalfemale++;
        numoffemale = totalfemale;
        return numoffemale;
    }
    
    public int totalKangaroo(){//in current point
        return getmale()+getfemale();
    }
    
    public int getColony(){
        return colony;
    }
    public int getfood(){
        return food;
    }
    
    public void addfood(int food){
        this.food+=food;
    }
    public void minusfood(int minus){
        this.food-=minus;
    
    }
    //
    public void offering(){
        int tempfoodbox=0;
        for(int i=0;i<JumpGroove.Kangaroos.length();i++){
            if(JumpGroove.Kangaroos.atindex(i).getpointID()==(getpointID()));
                JumpGroove.Kangaroos.atindex(i).addFood(1);
                
        }
    }

    
    
    @Override
    public int compareTo(Points o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Point " + pointID +", food =" + food +", numoffemale=" + numoffemale + ", numofmale=" + numofmale + ", numroute=" + numroute ;
    }

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kangaroos;
import java.util.Random;
/**
 *
 * @author hello
 */
public class Kangaroo implements Comparable<Kangaroo> {
    private Character gender;
    private int pouchcapacity;
    private int food = 0;
    private int pointID;
    private Random r = new Random();
    
    public Kangaroo(){
        this.gender = null;
    }
    public Kangaroo(int pointID, Character gender, int pouchcapacity) {
        this.pointID = pointID;
        this.pouchcapacity = pouchcapacity;
        this.gender = gender;
        
    }
    
    public int getpointID(){
        return pointID;
    }
    
    public int getfood(){
        return food;
    }
    
    public void minusfood(int food){
        this.food-=food;
    }
    
    public void collectfood(){
        if((this.food!=this.pouchcapacity)&&(getPoint().getfood()!=0)){
            int tempfood = this.pouchcapacity-this.food;//amount of food needed to fill the pouch
            if(getPoint().getfood()-tempfood<0){
                this.food+=getPoint().getfood();
            }else{
                getPoint().minusfood(tempfood);
                this.food+=tempfood;
            }
        }
    }
    public void addFood(int food){
        this.food+=food;
    }
    public Character getGender() {
        return gender;
    }
    
    public Points getPoint(){
        return JumpGroove.Hafiz.getPoint(this.pointID);
    }

    @Override
    public int compareTo(Kangaroo o) {
        if(this.getGender().equals(o.getGender()))
            return 0;//true
        else return -1;//false
  
    }

    public void tick(){
        collectfood();
        if(this.gender=='M'){

            int tempnumroute = getPoint().getnumroute();//number of route from the point(point that current kangaroo is)
            for (int j = 0; j < tempnumroute; j++) {
                getPoint().getRoute(j);
            }
            
        }

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Kangaroo{" + "gender=" + gender + '}';
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

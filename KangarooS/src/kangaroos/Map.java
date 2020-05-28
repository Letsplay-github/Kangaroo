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
public class Map{ 
    LinkedList<Points> points = new LinkedList<>(); // aku tak buat private sebab senang untuk akses
    int numberofpoints;
    public Map(){
        
    }
    
    public Map(int numberofspot){
        this.numberofpoints = numberofspot;
    }
    
    public int numberofspot(){
        return numberofpoints;
    }
    
    public void tick(){
        for(int i =0;i<numberofpoints;i++){
            points.atindex(i).tick();
        }
    }
}

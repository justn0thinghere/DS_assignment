/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_kanto_adventure;

/**
 *
 * @author User
 */
public class Edge<T extends Comparable<T>,N extends Comparable<N>> { //edge class
    City<T,N>toCity; //pointer to destination city
    N weight; //weight of the edge
    Edge<T,N>nextEdge; //pointer to next edge
    public Edge(){ //create edge object
        toCity = null;
        weight = null;
        nextEdge = null;
    }
    public Edge(City<T,N>destination,N w,Edge<T,N>a){ //create edge object with specific destination city, weight and pointer to next edge
        toCity = destination;
        weight = w;
        nextEdge = a;
    }
}

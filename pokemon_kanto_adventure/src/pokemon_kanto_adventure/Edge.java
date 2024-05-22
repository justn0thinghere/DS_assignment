/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_kanto_adventure;

/**
 *
 * @author User
 */
public class Edge<T extends Comparable<T>,N extends Comparable<N>> {
    City<T,N>toCity;
    N weight;
    Edge<T,N>nextEdge;
    public Edge(){
        toCity = null;
        weight = null;
        nextEdge = null;
    }
    public Edge(City<T,N>destination,N w,Edge<T,N>a){
        toCity = destination;
        weight = w;
        nextEdge = a;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_kanto_adventure;

/**
 *
 * @author User
 */
public class City<T extends Comparable<T>,N extends Comparable<N>> {
    T cityInfo;
    int deg;
    City<T,N>nextCity;
    Edge<T,N>firstEdge;
    public City(){
        cityInfo = null;
        deg = 0;
        nextCity = null;
        firstEdge = null;
    }
    public City(T vInfo,City<T,N>next){
        cityInfo = vInfo;
        deg = 0;
        nextCity = next;
        firstEdge = null;
    }
}

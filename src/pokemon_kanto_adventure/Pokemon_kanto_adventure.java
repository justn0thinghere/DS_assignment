
package pokemon_kanto_adventure;


public class Pokemon_kanto_adventure {


    public static void main(String[] args) {
        library.readallfiles();
        Player a = new Player("Jack");
        a.obtainbadge("Cascade Badge");
        a.obtainbadge("Volcano Badge");
        a.obtainitems("Potion", 2);
        a.obtainitems("Potion", 3);
        a.obtainitems("Poke Ball", 4);
        a.obtainitems("Revive",10);
        a.obtainitems("Super Potion",5);
        a.obtainitems("Hyper Potion", 1);
        a.obtainitems("Max Potion", 3);
        a.showbadges();
        a.showitems();
        a.addPokemon(new Pokemon("Bulbasaur",5));
        a.addPokemon(new Pokemon("Charmander",6));
        a.addPokemon(new Pokemon("Snorlax",100));
        a.showprofile();
        a.findPoke1().takedmg(100);
        a.findPoke2().takedmg(200);
        a.findPoke3().takedmg(3000);
        a.alterteam();
    }
}

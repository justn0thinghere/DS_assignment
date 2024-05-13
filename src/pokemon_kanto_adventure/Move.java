/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_kanto_adventure;

/**
 *
 * @author User
 */
public class Move {
    private String movename;
    private String type;
    private String category;
    private int order;
    private double atk;
    private double def;
    private double sp;
    private double dmghealratio;
    private double foe_atk;
    private double foe_def;
    private double foe_sp;
    private int power;
    private double hphealratio;
    private String description;
    public Move(String n,int lvl){
        movename = n;
        category = library.move_cat.get(movename);
        order = library.move_order.get(movename);
        description = library.move_description.get(movename);
        if(category.equals("dmg")){
            type = library.move_type.get(movename);
            power = library.move_dmg.get(movename).get(lvl);
        }else if(category.equals("stat")){
            atk = library.move_stat.get(movename).get("atk");
            def = library.move_stat.get(movename).get("def");
            sp = library.move_stat.get(movename).get("sp");
            foe_atk = library.move_stat.get(movename).get("foe_atk");
            foe_def = library.move_stat.get(movename).get("foe_def");
            foe_sp = library.move_stat.get(movename).get("foe_sp");
        }else if(category.equals("heal")){
            hphealratio = 0.5;
        }else if(category.equals("sp")){
            atk = library.move_stat.get(movename).get("atk");
            def = library.move_stat.get(movename).get("def");
            sp = library.move_stat.get(movename).get("sp");
            dmghealratio = library.move_stat.get(movename).get("healratio");
            foe_atk = library.move_stat.get(movename).get("foe_atk");
            foe_def = library.move_stat.get(movename).get("foe_def");
            foe_sp = library.move_stat.get(movename).get("foe_sp");
        }
    }
    public String getName(){
        return movename;
    }
    public int getPower(){
        return power;
    }
    public String getType(){
        return type;
    }
    public int getOrder(){
        return order;
    }
    public String getCategory(){
        return category;
    }
    public double getAtk(){
        return atk;
    }
    public double getDef(){
        return def;
    }
    public double getSp(){
        return sp;
    }
    public double getdmgheal(){
        return dmghealratio;
    }
    public double gethpheal(){
        return hphealratio;
    }
    public double getFAtk(){
        return foe_atk;
    }
    public double getFDef(){
        return foe_def;
    }
    public double getFSp(){
        return foe_sp;
    }
    public String description(){
        return description;
    }
}

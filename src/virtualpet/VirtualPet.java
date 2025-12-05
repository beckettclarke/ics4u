package virtualpet;
public class VirtualPet {
  private String name;
  private int energy = 0, weight = 5, happiness = 0, agem = 0, agey = 0;
  public VirtualPet(String newname) {
    this.name = newname;
  }

  public String getName(){
    return this.name;
  }

  @Override
  public String toString(){
    return this.name+"'s Information:\n Energy: "+this.energy+"\n Happiness: "+this.happiness+"\n Weight: "+this.weight+"g\n Age: "+this.agem+" months and "+this.agey+" years";
  }

  public void feed(){
    if (this.energy <= 10){
      this.energy++;
    }
    this.weight++;
  }

  public int getEnergyLevel(){
    return this.energy;
  }

  public int getHappinessLevel(){
    return this.happiness;
  }

  public void play(){
    if (this.weight > 1){
      this.weight--;
    }
    if (this.happiness < 10){
      this.happiness++;
    }
  }

  public void updateStatus(){
    if (happiness > 0){
      happiness--;
    }
    if (energy > 0){
      energy--;
    }
    agem++;
    if (agem >= 12){
      agey++;
      agem = 0;
    }
  }
  
}

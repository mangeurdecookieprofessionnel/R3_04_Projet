package Appli;

public record Move(Square depart, Square arrivee){
	public Square getDepart() {
		return this.depart;
	}
	
	public Square getArrivee() {
		return this.arrivee;
	}
}

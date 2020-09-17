package model;

public class Employee {
	
	private String name;
	private String surmame;
	private String room;
	private String startHour;
	private String endHour;
	private int workTime;
	
	public Employee(String name, String surmame, String room, String startHour, String endHour) {
		super();
		this.name = name;
		this.surmame = surmame;
		this.room = room;
		this.startHour = startHour;
		this.endHour = endHour;
		workTime = Integer.parseInt(endHour)-Integer.parseInt(startHour);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurmame() {
		return surmame;
	}

	public void setSurmame(String surmame) {
		this.surmame = surmame;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public int getWorkTime() {
		return workTime;
	}
	
	
	
}

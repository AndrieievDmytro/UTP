import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Manager extends Worker {
	
	// (assignment 02)
	// attributes:
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (a list of subordinates in all hierarchy)
	
	private final List<Employee> subordinates;
	private final List<Employee> allSubordinates; 
	

	public Manager(String firstName, String surName , Date birthDate, BigDecimal salary, BigDecimal bonus, Date employmentDate, Employee manager) {
		super(firstName, surName, birthDate, salary, bonus, employmentDate, manager);
		subordinates   	= new ArrayList<>();
		allSubordinates = new ArrayList<>();
	}

	public List<Employee> sub(){
		return subordinates;
	}

	public List<Employee> allSub(){
		return allSubordinates;
	}

	@Override
	public void addEmploy(Employee employee){
		subordinates.add(employee);
		allSubordinates.add(employee);
		if (employee instanceof Manager){
			Manager manager = (Manager)employee;
			if ( manager.allSubordinates != null ){
				for(Employee sub : manager.allSubordinates){
					allSubordinates.add(sub);
				}
			}
		}
		addTopManagerEmploees(employee);
	}

	public void addAllEmploy(Employee employee){
		allSubordinates.add(employee);
		if (employee instanceof Manager){
			Manager manager = (Manager)employee;
			if ( manager.allSubordinates != null ){
				for(Employee sub : manager.allSubordinates){
					allSubordinates.add(sub);
				}
			}
		}
		addTopManagerEmploees(employee);
	}

	private void addTopManagerEmploees( Employee employee ){
		Manager topManager = (Manager)this.getManager();
		if ( topManager != null ){
			topManager.addAllEmploy(employee);
		}
	}
}
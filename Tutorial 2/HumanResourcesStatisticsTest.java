
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HumanResourcesStatisticsTest {
	
	// Create a HR structure which resembles the below one:
	//
	// Director (an instance of Manager class (Director does not have a manager)
	//   |- Manager01
	//        |- Worker
	//        |- Worker
	//        |- Trainee
	//        |- ...
	//   |- Manager02
	//        |- ...
	//   |- ...
	//   |- Worker
	//   |- Worker
	//   |- Trainee
	
	private static List <Employee> company;

	public static Worker createWorker(int counter, Manager man){
		
		int years = 25 +counter;
		Date birthDate = getBirthDate(years);
		BigDecimal salary = new BigDecimal( 500 + counter );
		BigDecimal bonus = new BigDecimal( 300 + counter*2 );
		Worker w = new Worker("Worker-" + counter, (counter==5?"A":"")+"WsurName" + counter, birthDate, salary, bonus, getBirthDate( 1 + counter ), man);
		return w;
	}

	public static Trainee createTrainee(int counter, Manager man){
		
		int years = 19 +counter;
		Date birthDate = getBirthDate(years);
		BigDecimal salary = new BigDecimal( 250 + counter );
		Trainee t = new Trainee("   Trainee-" + counter, "TsurName" + counter, birthDate, salary, getBirthDate( 1 + counter ), man);
		return t;
	}

	public static Manager createManager(int counter, Manager man, String lvl){
		
		int years = 40 +counter;
		Date birthDate = getBirthDate(years);
		BigDecimal salary = new BigDecimal( 1000 + counter );
		BigDecimal bonus = new BigDecimal( 500 + counter*2 );
		Manager m = new Manager(lvl + "Manager-" + counter, "MsurName" + counter, birthDate, salary, bonus, getBirthDate( 1 + counter ), man );
		return m;
	}

	public static List<Employee> createCompany(){
		List <Employee>_allEmployees = new ArrayList<>();
		Manager tm = createManager(0, null, "");
		_allEmployees.add(tm);
		for(int i = 1 ; i <= 2 ; i++ ){
			Manager m = createManager(i, tm, " ");
			_allEmployees.add(m);
			for(int j = 0 ; j <= 1 ; j++ ){
				Manager lm = createManager(1 + j + i * 2, m, "  ");
				_allEmployees.add(lm);
				for(int k = 0 ; k <= 1 ; k++ ){
					Worker w = createWorker(k + (j + (i-1) * 2) * 2  , lm);
					_allEmployees.add(w);
				}
				for(int l = 0 ; l <= 1 ; l++ ){
					Trainee t = createTrainee(l + (j + (i-1) * 2) * 2  , lm);
					_allEmployees.add(t);
				}
			}
		}
		return _allEmployees;		
	}

	public static Date getBirthDate(int age)
	{	
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.YEAR, c.get(Calendar.YEAR)-age);
		return c.getTime();
	}

	@Before
	public void before() {
		company = createCompany();
		Assert.assertEquals(23, company.size());
	}


	@Test
	public void payroll() {
		System.out.println("Emploees");
		List <Employee> company = createCompany();
		for ( Employee e : company ){
			System.out.println(e);
		}
		Assert.assertEquals(23, company.size());
		// System.out.println("Payrolls");
		List <PayrollEntry> pr = HumanResourcesStatistics.payroll(company);
		// for ( PayrollEntry p : pr ){
		// 	System.out.println(p);
		// }
		Assert.assertEquals(23, pr.size());
	}

	@Test
	public void subordinatesPayroll() {
		// List <Employee>_allEmployees = createCompany();
		int mNum = 0;
		Employee emp = null;
		for ( Employee e : company ){
			if ( e instanceof Manager ) mNum++;
			emp = e;
			if ( mNum > 3 ) break;
		}
		Assert.assertNotNull(emp);
		if ( emp != null ){
			List <PayrollEntry> pr = HumanResourcesStatistics.subordinatesPayroll((Manager)emp);
			// for ( PayrollEntry p : pr ){
			// 	System.out.println(p);
			// }
			Assert.assertEquals(4, pr.size());
		}
	}

	@Test
	public void bonusTotal() {
		BigDecimal total = HumanResourcesStatistics.bonusTotal(company);
		Assert.assertEquals(new BigDecimal(5998), total);
	}

	@Test
	public void findTheBiggestSalaryWithoutBonus(){
		BigDecimal total = HumanResourcesStatistics.findTheBiggestSalaryWithoutBonus(company);
		Assert.assertEquals(new BigDecimal(1006), total);
	}
	
	@Test
	public void findTheBiggestSalaryWithBonus(){
		BigDecimal total = HumanResourcesStatistics.findTheBiggestSalaryWithBonus(company);
		Assert.assertEquals(new BigDecimal(1518), total);
	}
	

	@Test
	public void findLongestSeniority(){
		Employee e = HumanResourcesStatistics.findLongestSeniority(company);
		Assert.assertEquals("Worker-7", e.getFirstName());
		// System.out.println(e.toString());
	}

	@Test
	public void findEmployeeBySurname(){
		Employee emp = null;
		int mNum = 0;
		for ( Employee e : company ){
			if ( e instanceof Manager ) mNum++;
			emp = e;
			if ( mNum > 5 ) break;
		}
		List <Employee> pr = HumanResourcesStatistics.findEmployeeBySurname((Manager)emp);
		Assert.assertEquals(1, pr.size());
	}
	@Test
	public void moreThanThousand(){
		List <PayrollEntry> pr = HumanResourcesStatistics.moreThanThousand(company);
			Assert.assertEquals(7, pr.size());
		
	}
}
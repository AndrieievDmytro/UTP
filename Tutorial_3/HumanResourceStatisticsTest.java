import java.util.List;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public final class HumanResourceStatisticsTest {

	private static List <Employee> company;

    public static Worker createWorker(int counter, Manager man){
		
		int years = 25 +counter;
		Date birthDate = getBirthDate(years);
		BigDecimal salary = new BigDecimal( 500 + counter );
		BigDecimal bonus = new BigDecimal( 100 + counter*2 );
		Worker w = new Worker("Worker-" + counter, (counter==5?"A":"")+"WsurName" + counter, birthDate, salary, bonus, getBirthDate( 1 + counter ), man);
		return w;
	}

	public static Trainee createTrainee(int counter, Manager man){
		
		int years = 19 +counter;
		Date birthDate = getBirthDate(years);
		BigDecimal salary = new BigDecimal( 250 + counter );
		Trainee t = new Trainee("Trainee-" + counter, "TsurName" + counter, birthDate, salary, getBirthDate( 1 + counter ), man);
		return t;
	}

	public static Manager createManager(int counter, Manager man){
		
		int years = 40 +counter;
		Date birthDate = getBirthDate(years);
		BigDecimal salary = new BigDecimal( 1000 + counter );
		BigDecimal bonus = new BigDecimal( 500 + counter*2 );
		Manager m = new Manager("Manager-" + counter, "MsurName" + counter, birthDate, salary, bonus, getBirthDate( 1 + counter ), man );
		return m;
	}

	public static List<Employee> createCompany(){
		List <Employee>_allEmployees = new ArrayList<>();
		Manager tm = createManager(0, null);
		_allEmployees.add(tm);
		for(int i = 1 ; i <= 2 ; i++ ){
			Manager m = createManager(i, tm);
			_allEmployees.add(m);
			for(int j = 0 ; j <= 1 ; j++ ){
				Manager lm = createManager(1 + j + i * 2, m);
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
	public void olderThanAndEarnMore(){
		company = createCompany();
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(2000,11, 11);
		Date WbirthDate = _calendar.getTime();
		_calendar.set(1930,11, 11);
		Date WempDate = _calendar.getTime();
		Employee emp = new Worker("First", "Second",WbirthDate, BigDecimal.valueOf(1000), BigDecimal.valueOf(11), WempDate, null);

		Assert.assertNotNull(emp);
		List <Employee> pr = HumanResourceStatistics.olderThanAndEarnLess(company, emp);
		// for ( Employee e : pr ){System.out.println(e);}
		Assert.assertEquals(14, pr.size());
	}

	@Test
	public void practiceLengthLongerThan(){
		company = createCompany();
		// company
		// 	.stream()
		// 	.filter(e->(e instanceof Trainee))
		// 	.map(em -> (Trainee) em)
		// 	.forEach(w -> System.out.println((w.getFirstName()+" "+w.getSalary())));
		List <Trainee> t = HumanResourceStatistics.practiceLengthLongerThan(company, 1000);
		// // for ( Trainee e : t ){
		// // 	System.out.println(e);
		// // 	System.out.println("Length of practice: " +  e.getApprLength()+ "\n");
		// }
		Assert.assertEquals(6, t.size());
	}

	@Test
	public void seniorityLongerThanGivenMonth(){
		company = createCompany();
		
		List <Worker> w = HumanResourceStatistics.seniorityLongerThanGivenMonths(company, 60);
		// for (Worker t : w) {System.out.println(t);}

		// Worker le = w.get(w.size() - 1);
		// w.stream().forEach(q -> System.out.print( q.getBonus() + (q == le ? "." : ",") ) );
		Assert.assertEquals(3, w.size());
		// System.out.println("Bonus:");
		// company
		// 	.stream()
		// 	.filter(e->(e instanceof Worker))
		// 	.map(em -> (Worker) em)
		// 	.forEach(em -> System.out.print((em.getBonus()) + ","));
		// System.out.println("\n"+"EmpLength");
		// company
		// 	.stream()
		// 	.filter(e->(e instanceof Worker))
		// 	.map(em -> (Worker) em)
		// 	.forEach(q -> System.out.print((q.getEmpLength()/(365))*12 + ","));	
	}

	@Test
	public void seniorityBetweenOneAndThreeYears(){
		company = createCompany();
		// company.stream()
		// .filter(e->(e instanceof Worker))
		// .map(em -> (Worker) em)
		// .forEach(s -> System.out.print((s.getEmpLength())/364 + ","));
		List<Worker> w = HumanResourceStatistics.seniorityBetweenOneAndThreeYears(company);
		// System.out.println("\n"+"After");
		// w
		// .stream()
		// .filter(e->(e instanceof Worker))
		// .map(em -> (Worker) em)
		// .forEach(e -> System.out.print((e.getEmpLength())/364 + ","));

		Assert.assertEquals(4, w.size());
	}

	@Test
	public void seniorityLongerThanOtherEmployee(){
		company = createCompany();
		Calendar _calendar = Calendar.getInstance();
		_calendar.set(1990,11, 11);
		Date WbirthDate = _calendar.getTime();
		_calendar.set(2019,11, 11);
		Date WempDate = _calendar.getTime();
		Employee emp = new Worker("First", "Second", WbirthDate, BigDecimal.valueOf(1000), BigDecimal.valueOf(11), WempDate, null);

		List<Worker> w = HumanResourceStatistics.seniorityLongerThanOtherEmployee(company, emp);

		// for(Worker t : w){System.out.println(t)};
		Assert.assertNotNull(emp);
		Assert.assertEquals(8, w.size());
	}

	@Test
	public void seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(){
		company = createCompany();

		// company
		// .stream()
		// .filter(e->(e instanceof Worker))
		// .map(em -> (Worker) em)
		// .forEach(e -> System.out.print(e.getAge()+","));

		// System.out.println();

		List<Worker> w = HumanResourceStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(company, 30);
		Assert.assertEquals(2, w.size());

		// w
		// .stream()
		// .filter(e->(e instanceof Worker))
		// .map(em -> (Worker) em)
		// .forEach(e -> System.out.print(e.getAge()+ "," + (e.getEmpLength()/365) + ","));

	}
	

  /*  @Test
    public void isYounger() {
        // Assert.assertTrue(company.get(0).isYounger(company.get(1)));
        // Employee e = company.get(2);
        company.forEach( e-> {
                if ( e.getManager() != null && (e instanceof Manager) ){
                    System.out.println(e);
                    Assert.assertFalse(e.isYounger(e.getManager()));
                }
            }
        );
	} 
	*/
}
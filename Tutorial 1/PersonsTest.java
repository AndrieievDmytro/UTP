import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonsTest {

	private static final int AGE = 20;
	private static final String name = "fName mName lName";

	private Persons <Person,Integer> _persons;

	@Before
	public void before() {
        _persons = new Persons<Person,Integer>();
        _persons.elements().add(new Person(name, AGE));
		Assert.assertEquals(1, _persons.elements().size());
		Assert.assertEquals(AGE, _persons.elements().get(0).age());
	}

	@Test
	public void aggregateAllElements() {
		int aggregate = _persons.aggregateAllElements();
		Assert.assertEquals(AGE, aggregate);
	}

	@Test
	public void cloneElementAtIndex() {
        _persons.elements().add(_persons.cloneElementAtIndex(0));
		_persons.elements().get(1).setAge(_persons.elements().get(1).age() + 1);
		Assert.assertEquals(AGE*2 + 1, _persons.aggregateAllElements().intValue());
		Assert.assertNotSame(_persons.elements().get(0), _persons.elements().get(1));
		Assert.assertEquals(name, _persons.elements().get(0).name());
	}
}
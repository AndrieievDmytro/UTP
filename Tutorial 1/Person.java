public final class Person implements IAggregable<Integer>, IDeeplyCloneable<Person> {
	
	private int _age;
	private PersonName _name;

	public Person() {
	}
	
	public Person(String name, int age ) {
		_age = age;
		_name = new PersonName(name);
	}

	public Person( PersonName name , int age ) {
		_age = age;
		_name = name;
	}
	
	public Person(Person a){
		this( a._name.deepClone(), a.age() );
	}

	public int age() {
		return _age;
	}

	public String name() {
		return _name.aggregate(null);
	}

	public void setAge( int _age ) {
		this._age = _age;
	}

	public void setName( String name ) {
		this._name.setName(name);
	}

	public Integer aggregate(Integer intermediateResult) {
		if  (intermediateResult == null) {
			return _age;
		}
		return _age + intermediateResult;
	}
	
	public Person deepClone() {
		Person clone = new Person(this);
		return clone;
	}
}
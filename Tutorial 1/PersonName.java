public final class PersonName implements IAggregable<String>, IDeeplyCloneable<PersonName> {
	
    private String FName;
    private String MName;
    private String LName;
    

	public PersonName() {
	}
	
	public PersonName(String name ) {
        this.setName(name);
    }
	
	public PersonName(PersonName pn){
		this( pn.getName() );
	}

	public String getName() {
        return aggregate(null);
	}

	public void setName( String name ) {
        String[] names = name.split(" ");
        if ( names.length == 1 ){
            this.FName = name;
        }if (names.length == 2) {
            this.FName = names[0];
            this.LName = names[1];
        } else {
            this.FName = names[0];
            this.MName = names[1];
            this.LName = names[2];
        }
	}

	public String aggregate(String s) {
        String sep = " ";
        if ( s != null ){
            sep = s;
        }
        String name = FName;
        if ( MName.length() > 0 ){
            name += sep + MName;
        }
        if ( LName.length() > 0 ){
            name += sep + LName;
        }
		return name;
	}
	
	public PersonName deepClone() {
		PersonName clone = new PersonName(this);
		return clone;
	}
}
import java.util.List;
import java.util.ArrayList;

public class Persons <TElement extends    IDeeplyCloneable<TElement> & 
                                          IAggregable<TResult>, TResult>
                               implements IContainer<TElement, TResult> 
{
    
    private List<TElement>persons;

    public Persons(){
        persons = new ArrayList<>();   
    }

    public Persons(Persons<TElement,TResult> p){
        for (TElement sourcePerson : p.persons) {
            persons.add(sourcePerson.deepClone());
        }    
    }

    public List<TElement> elements(){
        return persons;
    }

	public TResult aggregateAllElements(){
        TResult aResult = null;
        for (TElement person : persons) {
            aResult = person.aggregate(aResult);
        }
        return aResult;
    };
	
	public TElement cloneElementAtIndex(int index){
        if(index < 0 || persons.size() <= index){
            return null;
        }
        return persons.get(index).deepClone();
    };
}

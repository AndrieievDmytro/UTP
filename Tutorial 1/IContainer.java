import java.util.List;

public interface IContainer<TElement extends IAggregable<TAggregateResult> & 
											 IDeeplyCloneable<TElement>, TAggregateResult> 
{
	
    List<TElement> elements();
	
	TAggregateResult aggregateAllElements();
	
	TElement cloneElementAtIndex(int index);
}
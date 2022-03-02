public interface IAggregable<TResult> {

	TResult aggregate(TResult intermediateResult);
}
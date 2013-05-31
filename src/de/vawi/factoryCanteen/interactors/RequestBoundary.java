package de.vawi.factoryCanteen.interactors;

public interface RequestBoundary<T> {

    public T passRequest();
}
